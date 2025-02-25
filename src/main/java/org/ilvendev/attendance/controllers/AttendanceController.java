package org.ilvendev.attendance.controllers;

import lombok.AllArgsConstructor;
import org.ilvendev.attendance.services.AttendanceTimeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/attendance")
@AllArgsConstructor
public class AttendanceController {

    private AttendanceTimeService attendanceTimeService;

}
