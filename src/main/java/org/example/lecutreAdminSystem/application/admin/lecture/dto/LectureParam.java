package org.example.lecutreAdminSystem.application.admin.lecture.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.lecutreAdminSystem.interfaces.api.common.validation.annotation.LectureId;
import org.example.lecutreAdminSystem.interfaces.api.common.validation.interfaces.SearchLectureByDateAndTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
public class LectureParam {

    private Long userId;

    @LectureId(groups = {SearchLectureByDateAndTime.class})
    private Long lectureId;

    @NotNull(groups = {SearchLectureByDateAndTime.class}, message = "조회 시작일은 필수입니다.")
    private LocalDate startDate;

    @NotNull(groups = {SearchLectureByDateAndTime.class}, message = "조회 종료일은 필수입니다.")
    private LocalDate endDate;

    @NotNull(groups = {SearchLectureByDateAndTime.class}, message = "조회 시작 시각은 필수입니다.")
    private LocalDateTime startTime;

    @NotNull(groups = {SearchLectureByDateAndTime.class}, message = "조회 종료 시각은 필수입니다.")
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
