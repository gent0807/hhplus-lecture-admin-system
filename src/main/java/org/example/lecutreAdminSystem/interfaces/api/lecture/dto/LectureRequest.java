package org.example.lecutreAdminSystem.interfaces.api.lecture.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LectureRequest {
    private long userId;
    private long lectureId;
    private String lectureName;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
}
