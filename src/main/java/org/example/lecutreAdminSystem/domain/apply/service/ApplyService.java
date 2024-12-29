package org.example.lecutreAdminSystem.domain.apply.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.lecutreAdminSystem.application.admin.lecture.dto.ApplyParam;
import org.example.lecutreAdminSystem.application.admin.lecture.dto.ApplyResult;
import org.example.lecutreAdminSystem.application.admin.lecture.dto.LectureParam;
import org.example.lecutreAdminSystem.domain.apply.entity.Apply;
import org.example.lecutreAdminSystem.domain.apply.repository.ApplyRepository;
import org.example.lecutreAdminSystem.domain.common.exception.*;
import org.example.lecutreAdminSystem.domain.lecture.entity.Lecture;
import org.example.lecutreAdminSystem.domain.lecture.repository.LectureRepository;
import org.example.lecutreAdminSystem.domain.user.entity.User;
import org.example.lecutreAdminSystem.interfaces.api.common.exception.error.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class ApplyService {

    private final ApplyRepository applyRepository;

    private final LectureRepository lectureRepository;

    public List<Apply> findCurrentApplies(@Valid LectureParam lectureParam) {

        long userId = lectureParam.getUserId();

        // 유저 정보 정보 유효성 검사
        User.validate(userId);

        return applyRepository.findByUserId(userId).orElseThrow(()->new ApplyByUserIdNotFoundException(ErrorCode.APPLY_NONE));
    }

    public List<ApplyResult> findCurrentAppliesByUserIdAndLectureId(ApplyParam applyParam){

        Apply.validate(applyParam.getApplyId(), applyParam.getUserId(), applyParam.getLectureId());

        return applyRepository.findByUserIdAndLectureId(applyParam.getUserId(), applyParam.getLectureId())
                .orElseThrow(()->new ApplyByUserIdAndLectureIdNotFoundException(ErrorCode.APPLY_NONE))
                .stream().map(apply -> {
                    return apply.convertFromEntityToDomainDTO();}).toList();

    }

    public List<ApplyResult> convertToDTOList(List<Apply> currentApplies) {
        return currentApplies.stream().map(apply -> apply.convertFromEntityToDomainDTO()).toList();
    }

    public void checkLectureApplyStatus(ApplyParam applyParam){

        long applyId = applyParam.getApplyId();

        long userId = applyParam.getUserId();

        long lectureId = applyParam.getLectureId();

        // 수강 신청 정보 유효성 검사
        Apply.validate(applyId,userId,lectureId);

        // 수강 신청 목록에 해당 수강 신청 정보가 이미 있는지 확인
        if(applyRepository.existsByUserIdAndLectureId(userId, lectureId)){
            throw new ApplyInvalidException(ErrorCode.APPLY_EXIST);
        }

        List<Apply> applies = applyRepository.findByUserId(userId).orElseThrow(()->new ApplyByUserIdNotFoundException(ErrorCode.APPLY_NONE));


        applies.stream().filter(apply ->
                apply.getLectureId().longValue() != lectureId
        ).forEach(apply -> {

            apply.checkDuplicatedDateAndTime(lectureRepository.findById(lectureId).orElseThrow(()->new ApplyByLectureIdNotFoundException(ErrorCode.LECTURE_NONE)));


        });


    }
    public void checkLectureCancleStatus(ApplyParam applyParam) {

        long applyId = applyParam.getApplyId();

        long userId = applyParam.getUserId();

        long lectureId = applyParam.getLectureId();

        // 수강 신청 정보 유효성 검증
        Apply.validate(applyId, userId, lectureId);

        // 수강 신청 목록에 수강 취소하려는 수강 신청 정보가 있는지 확인
        if(!applyRepository.existsByUserIdAndLectureId(userId, lectureId)){
            throw new ApplyInvalidException(ErrorCode.APPLY_NONE);
        }

    }

    @Transactional
    public void saveApply(ApplyParam applyParam) {

        long userId = applyParam.getUserId();
        long lectureId = applyParam.getLectureId();

        // 강의 정보 유효성 검사
        Lecture.validate(lectureId);

        // 강의 테이블에서 해당 강의 아이디로 조회
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(()->new LectureByIdNotFoundException(ErrorCode.LECTURE_NONE));

        // 수강 신청 테이블에 수강 신청정보 저장
        applyRepository.save(Apply.builder()
                        .userId(userId)
                        .lectureId(lecture.getLectureId())
                        .lectureDate(lecture.getDate())
                        .room(lecture.getRoom())
                        .cost(lecture.getCost())
                        .startTime(lecture.getStartTime())
                        .endTime(lecture.getEndTime())
                        .build());
    }


    @Transactional
    public void removeApply(ApplyParam applyParam) {

        // 수강 신청 정보 유효성 검사
        Apply.validate(applyParam.getApplyId(),applyParam.getUserId(),applyParam.getLectureId());


        applyRepository.deleteByUserIdAndLectureId(applyParam.getUserId(), applyParam.getLectureId());
    }



}
