package org.example.lecutreAdminSystem.application.admin.lecture.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record ApplyResult(
        Long applyId,
        Long userId,
        Long lectureId,
        LocalDate lectureDate,
        String room,
        Integer cost,
        LocalTime startTime,
        LocalTime endTime,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
){

}