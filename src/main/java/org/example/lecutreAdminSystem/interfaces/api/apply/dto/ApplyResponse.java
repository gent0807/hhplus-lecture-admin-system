package org.example.lecutreAdminSystem.interfaces.api.apply.dto;

import org.example.lecutreAdminSystem.application.admin.lecture.dto.ApplyResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record ApplyResponse(
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
) {
    public static List<ApplyResponse> convertFromDomainToAPIDTO(List<ApplyResult> applyResults) {
        return applyResults.stream()
                .map(applyResult ->
                        new ApplyResponse(
                                applyResult.applyId(),
                                applyResult.userId(),
                                applyResult.lectureId(),
                                applyResult.lectureDate(),
                                applyResult.room(),
                                applyResult.cost(),
                                applyResult.startTime(),
                                applyResult.endTime(),
                                applyResult.createdAt(),
                                applyResult.updatedAt()))
                .toList();
    }
}
