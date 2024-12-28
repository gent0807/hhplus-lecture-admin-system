package org.example.lecutreAdminSystem.domain.lecture.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.lecutreAdminSystem.application.admin.lecture.dto.LectureResult;
import org.example.lecutreAdminSystem.application.admin.lecture.enumeration.LECTURE_STATUS;
import org.example.lecutreAdminSystem.domain.apply.entity.Apply;
import org.example.lecutreAdminSystem.domain.apply.repository.ApplyRepository;
import org.example.lecutreAdminSystem.domain.lecture.entity.Lecture;
import org.example.lecutreAdminSystem.domain.lecture.repository.LectureRepository;
import org.example.lecutreAdminSystem.interfaces.api.common.exception.CustomException;
import org.example.lecutreAdminSystem.interfaces.api.common.exception.error.ErrorCode;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    private final ApplyRepository applyRepository;

    public List<Lecture> findLecturesByDateAndTime(LocalDate startDate, LocalDate endDate, LocalDateTime startTime, LocalDateTime endTime) {

        List<Lecture> lecturesByTime = lectureRepository.findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(startTime, endTime).orElseThrow(()->new CustomException(ErrorCode.LECTURE_NONE));

        return lecturesByTime.stream().filter(lecture -> {

            return ((lecture.getDate().equals(startDate) || lecture.getDate().isAfter(startDate))
                        && (lecture.getDate().equals(endDate) || lecture.getDate().isBefore(endDate)));

        }).toList();
    }

    // 특강 현재 수강 인원이 0명 미만이면 수강 신청 가능 상태 OFF, 30명 이상이면 수강 신청 가능 상태를 OFF로
    // 특강 목록에서 강의일이 오늘 이전인 강의들은 수강 신청 가능 상태를 OFF으로
    // 특강 목록에서 현재 유저의 수강 신청 목록에 이미 있는 강의는 전체 특강 목록에서 수강 신청 가능 상태를 OFF으로
    // 특강 목록에서 현재 유저의 수강 신청 목록에 있는 강의 날짜가 같고 강의 시간대가 겹치면 수강 신청 가능 상태를 OFF으로
    public List<LectureResult> findLecturesWithStatus(List<Apply> currentApplies, List<Lecture> lecturesByDateAndTime) {
        
        return lecturesByDateAndTime.stream().map(lecture -> {
            
            LECTURE_STATUS lectureStatus = LECTURE_STATUS.ON;
            
            try{
                lecture.checkStudentMaxCount();
                lecture.checkStudentMinCount();
                lecture.checkLectureDate();
            }catch (CustomException e){
                lectureStatus = LECTURE_STATUS.OFF;
            }

            // 수강 신청 목록에 해당 수강 신청 정보가 이미 있는지 확인
            if(currentApplies.stream().anyMatch(apply -> apply.getLectureId().equals(lecture.getLectureId()))){
                lectureStatus = LECTURE_STATUS.OFF;
            }
            
            // 현재 유저의 수강 신청 목록에 있는 강의들 중 확인중인 강의와 강의 날짜가 같고 강의 시간대가 겹치는 것이 하나라도 있는지 확인
            if(currentApplies.stream().filter(apply -> apply.getLectureId().longValue() != lecture.getLectureId().longValue())
                    .filter(apply -> apply.getLectureDate().equals(lecture.getDate()))
                    .map(apply -> {

                        try {
                            apply.checkDurableDateAndTime(lecture);
                        } catch (CustomException e) {
                            return LECTURE_STATUS.OFF;
                        }
                        
                        return LECTURE_STATUS.ON;

                    }).anyMatch(status -> status == LECTURE_STATUS.OFF)){
                lectureStatus = LECTURE_STATUS.OFF;
            }
            
            
            
            return lecture.convertFromEntityToDomainDTO(lectureStatus);
        }).toList();

    }

    public void checkLectureApplyStatus(Long lectureId)  {

        // 강의 정보 유효성 검사
        Lecture.validate(lectureId);

        // 해당 강의 아이디로 강의 조회
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(()->new CustomException(ErrorCode.LECTURE_NONE));

        // 현재 수강인원 체크
        lecture.checkStudentMaxCount();

        // 강의일이 이미 지났는지 체크
        lecture.checkLectureDate();

    }

    public void checkLectureCancelStatus(Long lectureId) {

        // 강의 정보 유효성 검사
        Lecture.validate(lectureId);

        // 해당 강의 아이디로 강의 조회
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(()->new CustomException(ErrorCode.LECTURE_NONE));

        // 현재 수강인원 확인
        lecture.checkStudentMinCount();

    }

    @Transactional
    public void saveLecture(long lectureId, long dx) {

        // 강의 정보 유효성 검사
        Lecture.validate(lectureId);

        // 해당 강의 아이디로 강의 조회
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(()->new CustomException(ErrorCode.LECTURE_NONE));

        // 수강 인원 set
        lecture.setCurrentStudentCount(lecture.getCurrentStudentCount() + dx);

        // 해당 강의의 현재 수강 인원 update
        lectureRepository.save(lecture);
    }


}
