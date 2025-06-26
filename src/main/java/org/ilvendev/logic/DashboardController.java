package org.ilvendev.logic;

import lombok.RequiredArgsConstructor;
import org.ilvendev.logic.DashboardService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping("/monthly-summary")
    public HoursSummary monthlySummary(
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam(required = false) List<String> holidays
    ) {
        // Parsujemy List<String> → List<LocalDate>
        List<LocalDate> holidayDates = (holidays == null)
                ? List.of()
                : holidays.stream()
                .map(LocalDate::parse)        // zakładamy ISO-8601, czyli "YYYY-MM-DD"
                .collect(Collectors.toList());

        return dashboardService.getMonthlySummary(year, month, holidayDates);
    }
}

