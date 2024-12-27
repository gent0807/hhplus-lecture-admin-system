package org.example.lecutreAdminSystem.application.admin.lecture.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.lecutreAdminSystem.interfaces.api.apply.dto.ApplyRequest;

import java.util.List;

@Getter
@Setter
@Builder
public class ApplyParam {
    private Long applyId;
    private Long userId;
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
