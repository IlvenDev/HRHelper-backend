// src/main/java/org/ilvendev/attendance/dto/HoursSummaryDto.java
package org.ilvendev.logic;

public record HoursSummary(
        long regular,
        long overtimeDay,
        long overtimeNight,
        long overtimeHoliday,
        long leaveVacation,
        long leaveUnpaid,
        long leaveCircumstance,
        long leavePregnant,
        long leaveParental,
        long leaveTraining,
        long leaveHigherPower,
        long leaveJobSearch,
        long leaveBlood,
        long leaveCarer,
        long sickLeave
) {}