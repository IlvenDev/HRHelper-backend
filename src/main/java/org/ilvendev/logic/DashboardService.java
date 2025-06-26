package org.ilvendev.logic;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ilvendev.attendance.domain.AttendanceTime;
import org.ilvendev.attendance.repositories.AttendanceTimeRepository;
import org.ilvendev.enums.LeaveStatus;
import org.ilvendev.enums.LeaveType;
import org.ilvendev.leaves.domain.Leave;
import org.ilvendev.leaves.repositories.LeaveRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class DashboardService {

    private final AttendanceTimeRepository attendanceRepo;
    private final LeaveRepository leaveRepo;

    public HoursSummary getMonthlySummary(int year, int month, List<LocalDate> holidays) {
        LocalDate periodStart = LocalDate.of(year, month, 1);
        LocalDate periodEnd   = periodStart.withDayOfMonth(periodStart.lengthOfMonth());

        // 1) Attendance
        List<AttendanceTime> atts = attendanceRepo.findByDateBetween(periodStart, periodEnd);
        log.info("ATT records from {} to {}: {}", periodStart, periodEnd, atts.size());

        long totalOvertimeNight = 0;
        long totalOvertimeHol   = 0;
        long totalOvertimeDay   = 0;
        long totalRegular       = 0;

        for (AttendanceTime at : atts) {
            if (at.getEndTime() == null) continue;
            long workedMin = Duration.between(at.getStartTime(), at.getEndTime()).toMinutes();
            if (at.isBreakTaken()) workedMin -= 30;
            if (workedMin <= 0) continue;

            LocalDate d = at.getDate();
            boolean isHoliday = d.getDayOfWeek() == DayOfWeek.SUNDAY || holidays.contains(d);

            if (isHoliday) {
                totalOvertimeHol += workedMin;
                continue;
            }

            long nightMin = calculateNightMinutes(at.getStartTime(), at.getEndTime());
            long dayMin   = workedMin - nightMin;

            // Łączna praca dzienna i nocna
            long totalWorkToday = dayMin + nightMin;

            // Do 8h → regular
            long regular = Math.min(totalWorkToday, 480);
            totalRegular += regular;

            // Reszta → nadgodziny (dzienne i nocne)
            long overtime = totalWorkToday - regular;

            // Proporcjonalne rozdzielenie nadgodzin
            if (overtime > 0) {
                double ratioNight = (double) nightMin / totalWorkToday;
                double ratioDay   = (double) dayMin / totalWorkToday;

                totalOvertimeNight += Math.round(overtime * ratioNight);
                totalOvertimeDay   += Math.round(overtime * ratioDay);
            }

        }


        log.info("Regular min: {}; OT day min: {}; OT night min: {}; OT hol min: {}",
                totalRegular, totalOvertimeDay, totalOvertimeNight, totalOvertimeHol);

        // 2) Leaves
        List<Leave> leaves = leaveRepo.findApprovedLeavesInPeriod(
                LeaveStatus.ZATWIERDZONE, periodStart, periodEnd);
        log.info("Approved leaves in period: {}", leaves.size());

        Map<LeaveType, Long> leaveMinutes = new EnumMap<>(LeaveType.class);
        for (LeaveType t : LeaveType.values()) {
            leaveMinutes.put(t, 0L);
        }

        for (Leave l : leaves) {
            LocalDate from = l.getDataStart().isBefore(periodStart) ? periodStart : l.getDataStart();
            LocalDate to   = l.getDataKoniec().isAfter(periodEnd)   ? periodEnd   : l.getDataKoniec();
            long days = ChronoUnit.DAYS.between(from, to) + 1;
            long mins  = days * 8 * 60;
            leaveMinutes.merge(l.getRodzaj(), mins, Long::sum);
            log.debug("Leave {}: {} days → {} min", l.getRodzaj(), days, mins);

        }

        // 3) Build DTO (dzielimy minuty na godziny)
        return new HoursSummary(
                totalRegular / 60,
                totalOvertimeDay   / 60,
                totalOvertimeNight / 60,
                totalOvertimeHol   / 60,
                leaveMinutes.get(LeaveType.WYPOCZYNKOWY)       / 60,
                leaveMinutes.get(LeaveType.BEZPŁATNY)          / 60,
                leaveMinutes.get(LeaveType.OKOLICZNOŚCIOWY)    / 60,
                leaveMinutes.get(LeaveType.MACIERZYŃSKI)       / 60,
                leaveMinutes.get(LeaveType.WYCHOWAWCZY)        / 60,
                leaveMinutes.get(LeaveType.SZKOLENIOWY)        / 60,
                leaveMinutes.get(LeaveType.SIŁA_WYŻSZA)        / 60,
                leaveMinutes.get(LeaveType.NA_POSZUKIWANIE_PRACY) / 60,
                leaveMinutes.get(LeaveType.ODDANIE_KRWI)       / 60,
                leaveMinutes.get(LeaveType.OPIEKUŃCZY)         / 60,
                leaveMinutes.get(LeaveType.CHOROBOWY)          / 60
        );
    }

    private long calculateNightMinutes(LocalTime start, LocalTime end) {
        LocalTime s1 = LocalTime.of(22, 0), e1 = LocalTime.MAX;
        LocalTime s2 = LocalTime.MIDNIGHT,  e2 = LocalTime.of(6, 0);
        long m = 0;
        if (end.isBefore(start)) {
            m += overlapMinutes(start, LocalTime.MAX, s1, e1);
            m += overlapMinutes(LocalTime.MIDNIGHT, end, s2, e2);
        } else {
            m += overlapMinutes(start, end, s1, e1);
            m += overlapMinutes(start, end, s2, e2);
        }
        return m;
    }

    private long overlapMinutes(LocalTime a, LocalTime b, LocalTime c, LocalTime d) {
        LocalTime s = a.isAfter(c) ? a : c;
        LocalTime e = b.isBefore(d) ? b : d;
        return e.isAfter(s) ? Duration.between(s, e).toMinutes() : 0;
    }
}
