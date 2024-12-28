package org.example.lecutreAdminSystem.application.admin.lecture.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
public class LectureParam {
    private Long userId;
    private Long lectureId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public static LectureParam convertFromAPIToDomainDTO(Long userId, String startDate, String endDate, String startTime, String endTime) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return LectureParam.builder()
                .userId(userId)
                .startDate(LocalDate.parse(startDate, dateFormatter))
                .endDate(LocalDate.parse(endDate, dateFormatter))
                .startTime(LocalDateTime.parse(startTime, dateTimeFormatter))
                .endTime(LocalDateTime.parse(endTime, dateTimeFormatter))
                .build();
    }
}
