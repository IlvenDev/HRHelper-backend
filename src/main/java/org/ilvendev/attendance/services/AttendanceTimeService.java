package org.ilvendev.attendance.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ilvendev.attendance.domain.AttendanceTime;
import org.ilvendev.attendance.dto.AttendanceEditRequest;
import org.ilvendev.attendance.dto.AttendanceTimeRequest;
import org.ilvendev.attendance.dto.AttendanceTimeResponse;
import org.ilvendev.attendance.dto.OvertimeSummary;
import org.ilvendev.attendance.mappers.AttendanceTimeMapper;
import org.ilvendev.attendance.repositories.AttendanceTimeRepository;
import org.ilvendev.exceptions.resource_exceptions.ResourceNotFoundException;
import org.ilvendev.profiles.domain.Employee;
import org.ilvendev.profiles.dto.EmployeeBasicResponse;
import org.ilvendev.profiles.mappers.EmployeeMapper;
import org.ilvendev.profiles.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class AttendanceTimeService {

    private final AttendanceTimeRepository attendanceTimeRepository;
    private final AttendanceTimeMapper attendanceTimeMapper;
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    // init attendance
    // fin attendance
    // get all
    // get for employee
    // get from date to date (no employee)
    // get for employee from date to date

    // need to add updating but only when admin

    // Need to add checking if exists
    @Transactional
    public AttendanceTimeResponse initializeAttendance(AttendanceTimeRequest request){
        log.debug("Initializing attendance request");

        AttendanceTime attendanceToSave = attendanceTimeMapper.toEntity(request);

        AttendanceTimeResponse initializedAttendance = attendanceTimeMapper.toResponse(attendanceTimeRepository.save(attendanceToSave));
        log.info("Initialized attendance request with ID: {}", initializedAttendance);

        return initializedAttendance;
    }

    @Transactional
    public AttendanceTimeResponse finalizeAttendance(Integer id, LocalTime endTime, boolean breakTaken){
        log.debug("Finalizing attendance request");

        AttendanceTime fetchedAttendance = attendanceTimeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance time", id.toString()));

        fetchedAttendance.setEndTime(endTime);
        fetchedAttendance.setBreakTaken(breakTaken);

        AttendanceTime finalizedAttendance = attendanceTimeRepository.save(fetchedAttendance);

        return attendanceTimeMapper.toResponse(finalizedAttendance);
    }

    @Transactional
    public AttendanceTimeResponse editAttendance(Integer id, AttendanceEditRequest editRequest){
        log.debug("Editing attendance with id {}", id);

        AttendanceTime fetchedAttendance = attendanceTimeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance time", id.toString()));

        // Apply the changes
        fetchedAttendance.setStartTime(editRequest.getStartTime());
        fetchedAttendance.setEndTime(editRequest.getEndTime());
        fetchedAttendance.setBreakTaken(editRequest.isBreakTaken());
        fetchedAttendance.setDate(editRequest.getDate());

        // Save and return updated response
        AttendanceTime finalizedAttendance = attendanceTimeRepository.save(fetchedAttendance);
        return attendanceTimeMapper.toResponse(finalizedAttendance);
    }


    public List<AttendanceTimeResponse> getAllRecords(){
        log.debug("Fetching all attendance records");

        return attendanceTimeMapper.toResponseList(attendanceTimeRepository.findAll());
    }

    // Might be good to add empty list checking, but that could be in the controller or on the frontend
    public List<AttendanceTimeResponse> getForEmployee(Integer id){
        log.debug("Fetching employee {} attendance records", id);

        Employee fetchedEmployee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", id.toString()));

        return attendanceTimeMapper.toResponseList(attendanceTimeRepository.findByEmployee(fetchedEmployee));
    }

    public List<AttendanceTimeResponse> getByDateBetween(LocalDate startDate, LocalDate endDate){
        return attendanceTimeMapper.toResponseList(attendanceTimeRepository.findByDateBetween(startDate, endDate));
    }

    public List<AttendanceTimeResponse> getByEmployeeAndDateBetween(Employee employee, LocalDate startDate, LocalDate endDate){
        return attendanceTimeMapper.toResponseList(attendanceTimeRepository.findByEmployeeAndDateBetween(employee, startDate, endDate));
    }

    public long getTotalWorkedMinutesInMonth(int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.plusMonths(1);

        List<AttendanceTime> attendances = attendanceTimeRepository.findByDateBetween(start, end);

        return attendances.stream()
                .filter(a -> a.getEndTime() != null)
                .mapToLong(a -> Duration.between(a.getStartTime(), a.getEndTime()).toMinutes())
                .sum();
    }

    public double getTotalWorkedHoursInMonth(int year, int month) {
        long totalMinutes = getTotalWorkedMinutesInMonth(year, month);
        return totalMinutes / 60.0;
    }

    public OvertimeSummary calculateOvertime(Integer employeeId, List<LocalDate> holidays) {
        List<AttendanceTime> attendances = attendanceTimeRepository.findByEmployeeId(employeeId);

        OvertimeSummary summary = new OvertimeSummary(0, 0, 0);

        for (AttendanceTime at : attendances) {
            if (at.getEndTime() == null) continue; // zabezpieczenie

            long workedMinutes = Duration.between(at.getStartTime(), at.getEndTime()).toMinutes();
            if (at.isBreakTaken()) {
                workedMinutes -= 30; // przykładowo 30 min przerwy
            }
            if (workedMinutes <= 0) continue;

            LocalDate date = at.getDate();
            DayOfWeek dayOfWeek = date.getDayOfWeek();

            // Sprawdź święto lub niedzielę
            if (dayOfWeek == DayOfWeek.SUNDAY || holidays.contains(date)) {
                summary.setSundayHolidayMinutes(summary.getSundayHolidayMinutes() + workedMinutes);
                continue;
            }

            // Licz godziny nocne i zwykłe
            // Przyjmij, że godziny nocne to praca między 22:00 a 6:00

            LocalTime start = at.getStartTime();
            LocalTime end = at.getEndTime();

            // Oblicz minuty nocne:
            long nightMinutes = calculateNightMinutes(start, end);
            long regularMinutes = workedMinutes - nightMinutes;

            summary.setNightMinutes(summary.getNightMinutes() + nightMinutes);
            summary.setRegularMinutes(summary.getRegularMinutes() + regularMinutes);
        }
        return summary;
    }

    private long calculateNightMinutes(LocalTime start, LocalTime end) {
        // Zakładamy dwie strefy nocne:
        // 22:00 - 23:59:59.999999999 (LocalTime.MAX)
        // 00:00 - 06:00

        LocalTime nightStart1 = LocalTime.of(22, 0);
        LocalTime nightEnd1 = LocalTime.MAX; // 23:59:59.999999999

        LocalTime nightStart2 = LocalTime.MIDNIGHT; // 00:00
        LocalTime nightEnd2 = LocalTime.of(6, 0);

        long nightMinutes = 0;

        if (end.isBefore(start)) {
            // Praca przechodzi przez północ, np. 21:00 - 02:00
            nightMinutes += overlapMinutes(start, LocalTime.MAX, nightStart1, nightEnd1);
            nightMinutes += overlapMinutes(LocalTime.MIDNIGHT, end, nightStart2, nightEnd2);
        } else {
            // Praca w ciągu jednego dnia
            nightMinutes += overlapMinutes(start, end, nightStart1, nightEnd1);
            nightMinutes += overlapMinutes(start, end, nightStart2, nightEnd2);
        }

        return nightMinutes;
    }

    private long overlapMinutes(LocalTime start, LocalTime end, LocalTime intervalStart, LocalTime intervalEnd) {
        LocalTime maxStart = start.isAfter(intervalStart) ? start : intervalStart;
        LocalTime minEnd = end.isBefore(intervalEnd) ? end : intervalEnd;

        if (minEnd.isBefore(maxStart)) return 0;

        return Duration.between(maxStart, minEnd).toMinutes();
    }

    public List<AttendanceTimeResponse> getTodayAttendances() {
        LocalDate today = LocalDate.now();
        return attendanceTimeRepository.findByDate(today).stream()
                .filter(a -> a.getStartTime() != null)
                .map(attendanceTimeMapper::toResponse)
                .toList();
    }

    public List<EmployeeBasicResponse> getEmployeesMissingAttendance(int days) {
        LocalDate today = LocalDate.now();
        LocalDate from = today.minusDays(days - 1); // e.g. days=2 → od wczoraj
        // 1) znajdź ID tych, którzy mieli obecność
        List<Integer> presentIds = attendanceTimeRepository
                .findDistinctEmployeeIdsBetweenDates(from, today);

        return employeeMapper.toBasicResponseList(employeeRepository.findAll().stream()
                .filter(e -> !presentIds.contains(e.getId()))
                .toList());
    }

//    private long overlapMinutes(LocalTime start, LocalTime end, LocalTime intervalStart, LocalTime intervalEnd) {
//        // Pomocnicza metoda do liczenia pokrycia interwału [start, end] z [intervalStart, intervalEnd]
//        LocalTime maxStart = start.isAfter(intervalStart) ? start : intervalStart;
//        LocalTime minEnd = end.isBefore(intervalEnd) ? end : intervalEnd;
//
//        if (minEnd.isBefore(maxStart)) return 0;
//
//        return Duration.between(maxStart, minEnd).toMinutes();
//    }
}
