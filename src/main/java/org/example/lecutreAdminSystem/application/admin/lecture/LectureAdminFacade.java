package org.example.lecutreAdminSystem.application.admin.lecture;

import lombok.RequiredArgsConstructor;
import org.example.lecutreAdminSystem.application.admin.lecture.dto.LectureAdminFacadeInfo;
import org.example.lecutreAdminSystem.interfaces.api.apply.dto.ApplyRequest;
import org.example.lecutreAdminSystem.interfaces.api.lecture.dto.LectureRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LectureAdminFacade {


    public List<LectureAdminFacadeInfo> findAllLecturesByDateAndTimeAndUserIdWithStatus(LectureRequest lectureRequest) {
        return null;
    }

    public List<LectureAdminFacadeInfo> findCurrentAppliesByUserIdAndLectureId(ApplyRequest applyRequest) {
        return null;
    }

    public void insertNewApplies(List<ApplyRequest> applyRequests) {

    }

    public void removeApplies(List<ApplyRequest> applyIds) {

    }
}
