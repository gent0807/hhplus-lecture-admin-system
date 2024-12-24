package org.example.lecutreAdminSystem.application.facade;

import org.example.lecutreAdminSystem.application.facade.dto.LectureAdminInfo;
import org.example.lecutreAdminSystem.interfaces.api.apply.dto.ApplyRequest;
import org.example.lecutreAdminSystem.interfaces.api.lecture.dto.LectureRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LectureAdmin {
    public List<LectureAdminInfo> getAllLecturesWithStatusByUserId(long userId) {

        return null;
    }

    public List<LectureAdminInfo> findLecturesByDateAndTimeAndUserId(LectureRequest lectureRequest) {
        return null;
    }

    public List<LectureAdminInfo> findCurrentAppliesByUserIdAndLectureId(ApplyRequest applyRequest) {
        return null;
    }

    public void insertNewApply(ApplyRequest applyRequest) {
    }

    public void removeApply(long applyId) {
    }
}
