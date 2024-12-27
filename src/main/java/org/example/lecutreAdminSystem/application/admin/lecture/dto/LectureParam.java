package org.example.lecutreAdminSystem.application.admin.lecture.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LectureParam {
    private Long userId;
    private Long lectureId;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;

    public static LectureParam convertFromAPIToDomainDTO(Long userId, String startDate, String endDate, String startTime, String endTime) {
        return LectureParam.builder()
                .userId(userId)
                .startDate(startDate)
                .endDate(endDate)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }
}
