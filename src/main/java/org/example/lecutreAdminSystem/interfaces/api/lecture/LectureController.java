package org.example.lecutreAdminSystem.interfaces.api.lecture;

import lombok.RequiredArgsConstructor;
import org.example.lecutreAdminSystem.application.admin.lecture.LectureAdminFacade;
import org.example.lecutreAdminSystem.application.admin.lecture.dto.LectureParam;
import org.example.lecutreAdminSystem.application.admin.lecture.dto.LectureResult;
import org.example.lecutreAdminSystem.interfaces.api.lecture.dto.LectureResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureController {

    private final LectureAdminFacade lectureAdminFacade;

    /**
     * 특정 기간과, 특정 시간대를 조회 조건으로 하여 특강 목록 조회하되, 현재 로그인한 user에 따른 수강 신청 가능 여부를 표시해준다.
     * @param userId, startDate, endDate, startTime, endTime
     * @return ResponseEntity<List<LectureResponse>>
     */
    @GetMapping("/all")
    public ResponseEntity<List<LectureResponse>> findAllLecturesByDateAndTimeAndUserIdWithStatus(
            @RequestParam("userId") long userId, @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime, @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate){

        List<LectureResult> lectureResults = lectureAdminFacade.findAllLecturesByDateAndTimeAndUserIdWithStatus(
                LectureParam.convertFromAPIToDomainDTO(userId, startDate, endDate, startTime, endTime));

        return ResponseEntity.status(HttpStatus.OK).body(LectureResponse.convertFromDomainToAPIDTO(lectureResults));
    }
}
