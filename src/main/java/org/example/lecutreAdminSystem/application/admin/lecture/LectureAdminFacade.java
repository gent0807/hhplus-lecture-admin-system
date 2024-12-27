package org.example.lecutreAdminSystem.application.admin.lecture;

import lombok.RequiredArgsConstructor;
import org.example.lecutreAdminSystem.application.admin.lecture.dto.*;
import org.example.lecutreAdminSystem.application.admin.lecture.enumeration.APPLY_RESULT;
import org.example.lecutreAdminSystem.application.admin.lecture.enumeration.CANCLE_RESULT;
import org.example.lecutreAdminSystem.domain.apply.entity.Apply;
import org.example.lecutreAdminSystem.domain.apply.service.ApplyService;
import org.example.lecutreAdminSystem.domain.lecture.entity.Lecture;
import org.example.lecutreAdminSystem.domain.lecture.service.LectureService;
import org.example.lecutreAdminSystem.domain.user.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LectureAdminFacade {

    private final UserService userService;
    private final LectureService lectureService;
    private final ApplyService applyService;


    /**
     * 특정 기간과, 특정 시간대를 조회 조건으로 하여 특강 목록 조회하되, 현재 로그인한 user에 따른 수강 신청 가능 여부를 표시해준다.
     * @param lectureParam
     * @return
     */
    public List<LectureResult> findAllLecturesByDateAndTimeAndUserIdWithStatus(LectureParam lectureParam) throws IllegalArgumentException{

        // 1. 현재 유저의 수강 신청 목록을 가져온다.
        // 2. 특정 기간과, 특정 시간대에 진행되는 특강 목록을 가져온다.
        // 3. 특강 현재 수강 인원이 0명 미만이면 => exception 발생, 30명 이상이면 수강 신청 가능 상태를 OFF로
        // 4. 전체 특강 목록에서 수강 신청 가능 여부가 OFF가 아니면서, 강의일이 오늘 이전인 강의들은 수강 신청 가능 상태를 OFF으로
        // 5. 전체 특강 목록에서 수강 신청 가능 여부가 OFF가 아니면서, 현재 유저의 수강 신청 목록에 이미 있는 강의는 전체 특강 목록에서 수강 신청 가능 상태를 OFF으로
        // 6. 전체 특강 목록에서 수강 신청 가능 여부가 OFF가 아니면서, 현재 유저의 수강 신청 목록에 있는 강의 날짜가 같고 강의 시간대가 겹치면 수강 신청 가능 상태를 OFF으로
        // 7. 전체 특강 목록에서 수강 신청 가능 여부가 OFF가 아닌 것들은 전부 수강 신청 가능 상태를 ON으로

        // 현재 사용자의 수강 신청 현황 조회
        List<Apply> currentApplies = applyService.findCurrentApplies(lectureParam.getUserId());

        // 특정 기간, 특정 시간대에 진행되는 특강 목록을 가져온다.
        List<Lecture> lecturesByDateAndTime = lectureService.findLecturesByDateAndTime(lectureParam.getStartDate(), lectureParam.getEndDate(), lectureParam.getStartTime(), lectureParam.getEndTime());

        // 특강 목록에 수강 신청 가능 상태를 표시해준다.
        return lectureService.findLecturesWithStatus(currentApplies, lecturesByDateAndTime);
    }

    /**
     * 특정 사용자 혹은 특정 강의에 대한 현재 수강 신청 목록(현황)을 조회한다.
     * @param applyParam
     * @return
     */
    public List<ApplyResult> findCurrentAppliesByUserIdAndLectureId(ApplyParam applyParam) {

        // 1. 특정 사용자 혹은 특정 강의에 대한 현재 수강 신청 목록(현황)을 조회한다.
        List<Apply> currentApplies = applyService.findCurrentAppliesByUserIdAndLectureId(applyParam.getApplyId(), applyParam.getUserId(), applyParam.getLectureId());

        // 2. 엔티티를 DTO 형식으로 옮겨준다.
        return applyService.convertToDTOList(currentApplies);

    }

    /**
     * 특정 사용자의 특정 강의에 대한 수강 신청 정보를 수강 신청 목록(현황)에 추가한다.
     *
     * @param applyParam
     */
    public APPLY_RESULT insertNewApplies(ApplyParam applyParam) {
        // 1. 수강 신청 강의의 수강 신청 가능 여부를 판별한다.

        // 1-1. 수강 신청하려는 강의의 정보를 가져온다. 강의가 존재하지 않으면 exception
        // 1-2. 현재 수강 인원이 30명 이상이면 불가, exception, 0명 미만이어도 exception
        // 1-3. 강의일이 이미 지난 강의인지 판단한다. 이미 지났으면 exception.

        // 1-4. 현재 사용자의 수강 신청 목록을 가져온다. 수강신청 목록이 없으면 통과
        // 1-5. 이미 신청된 강의인지 판단한다. 이미 신청된 강의이면 exception
        // 1-6. 현재 수강 신청 목록에 같은 날짜에 겹치는 시간대로 신청된 강의가 있으면 exception

        try {
            // 수강 신청하려는 강의 자체가 수강 가능한 지 확인
            lectureService.checkLectureApplyStatus(applyParam.getLectureId());

            // 수강 신청 목록에 대조하여 강의의 수강 가능 여부 확인
            applyService.checkLectureApplyStatus(applyParam.getApplyId(), applyParam.getUserId(), applyParam.getLectureId());

        }catch (IllegalArgumentException e){
            return APPLY_RESULT.FAIL;
        }

        // 2. 수강 신청한다.
        applyService.saveApply(applyParam.getUserId(), applyParam.getLectureId());

        // 3. 수강 신청 성공한 강의는 현재 수강 인원을 증산한다.
        lectureService.saveLecture(applyParam.getLectureId(), 1);

        return APPLY_RESULT.SUCCESS;
    }

    /**
     * 특정 수강 신청 정보를 수강 신청 목록에서 삭제한다.
     *
     * @param applyParam
                    */
    public CANCLE_RESULT removeApplies(ApplyParam applyParam){
        // 1. 수강 취소하려는 강의가 현재 강의 목록에 있는지와 현재 수강 신청 목록에 있는지를 확인한다. 없으면 exception
        // 2. 수강 취소하려는 강의의 현재 수강 인원이 1명 이상인지 확인, 1명 미만이면 exception
        try {
            // 수강 취소하려는 강의 자체가 수강 취소 가능한 상태인지 확인
            lectureService.checkLectureCancelStatus(applyParam.getLectureId());

            // 수강 신청 목록에 대조하여 강의의 수강 취소 가능 여부 확인
            applyService.checkLectureCancleStatus(applyParam.getApplyId(), applyParam.getUserId(), applyParam.getLectureId());

        }catch (IllegalArgumentException e){
            return CANCLE_RESULT.FAIL;
        }

        // 3. 수강 취소한다.
        applyService.removeApply(applyParam);

        // 4. 수강 취소 성공한 강의는 현재 수강 인원을 감산한다.
        lectureService.saveLecture(applyParam.getLectureId(), -1L);

        return CANCLE_RESULT.SUCCESS;

    }
}