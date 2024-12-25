package org.example.lecutreAdminSystem.interfaces.api.lecture.dto;

import org.example.lecutreAdminSystem.application.admin.lecture.dto.LectureAdminFacadeInfo;

import java.util.List;

public record LectureResponse(
        long userId
) {
    public static List<LectureResponse> from(List<LectureAdminFacadeInfo> lectureAdminFacadeInfos) {
       return lectureAdminFacadeInfos.stream()
                .map(info -> new LectureResponse(info.userId()))
                .toList();

    }
}
