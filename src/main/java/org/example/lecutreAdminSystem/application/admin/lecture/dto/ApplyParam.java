package org.example.lecutreAdminSystem.application.admin.lecture.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.lecutreAdminSystem.interfaces.api.apply.dto.ApplyRequest;
import org.example.lecutreAdminSystem.interfaces.api.common.validation.annotation.ApplyId;
import org.example.lecutreAdminSystem.interfaces.api.common.validation.annotation.LectureId;
import org.example.lecutreAdminSystem.interfaces.api.common.validation.annotation.UserId;
import org.example.lecutreAdminSystem.interfaces.api.common.validation.interfaces.*;

import java.util.List;

@Getter
@Setter
@Builder
public class ApplyParam {

    @ApplyId(groups = {RemoveApply.class})
    private Long applyId;

    @UserId(groups = {SearchLectureStatusByApply.class, SaveApply.class, RemoveApply.class})
    private Long userId;

    @LectureId(groups = {SearchLectureStatus.class, SearchLectureStatusByApply.class, SaveApply.class, RemoveApply.class, UpdateLecture.class})
    private Long lectureId;

    public static ApplyParam convertFromAPIToDomainDTO(Long applyId, Long userId, Long lectureId) {
        return ApplyParam.builder()
                .applyId(applyId)
                .userId(userId)
                .lectureId(lectureId)
                .build();
    }

    public static List<ApplyParam> convertFromAPIToDomainDTOList(List<ApplyRequest> applyRequests) {
        List<ApplyParam> applyParams = applyRequests.stream().map(
                applyRequest -> ApplyParam.builder()
                        .applyId(applyRequest.getApplyId())
                        .userId(applyRequest.getUserId())
                        .lectureId(applyRequest.getLectureId())
                        .build()).toList();
        return applyParams;
    }
}
