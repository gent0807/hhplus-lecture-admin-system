package org.example.lecutreAdminSystem.interfaces.api.apply.dto;

import org.example.lecutreAdminSystem.application.admin.lecture.dto.LectureAdminFacadeInfo;

import java.util.List;

public record ApplyResponse(
        long userId
) {
    public static List<ApplyResponse> from(List<LectureAdminFacadeInfo> lectureAdminFacadeInfos) {
        return lectureAdminFacadeInfos.stream()
                .map(info -> new ApplyResponse(info.userId()))
                .toList();
    }
}
