package org.example.lecutreAdminSystem.interfaces.api.lecture;

import lombok.RequiredArgsConstructor;
import org.example.lecutreAdminSystem.application.admin.lecture.LectureAdminFacade;
import org.example.lecutreAdminSystem.application.admin.lecture.dto.LectureAdminFacadeInfo;
import org.example.lecutreAdminSystem.interfaces.api.lecture.dto.LectureRequest;
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
     * 특정 기간과, 특정 시간대, 현재 로그인한 user에 따른 수강 신청 가능 여부를 표시하여  전체 특강 목록을 조회한다
     * @param userId, startDate, endDate, startTime, endTime
     * @return ResponseEntity<List<LectureResponse>>
     */
    @GetMapping("/all")
    public ResponseEntity<List<LectureResponse>> findAllLecturesByDateAndTimeAndUserIdWithStatus(
            @RequestParam("userId") long userId, @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime, @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate){

        List<LectureAdminFacadeInfo> lectureAdminFacadeInfos = lectureAdminFacade.findAllLecturesByDateAndTimeAndUserIdWithStatus(
                LectureRequest.builder()
                        .userId(userId)
                        .startTime(startTime)
                        .endTime(endTime)
                        .startDate(startDate)
                        .endDate(endDate)
                        .build());

        return ResponseEntity.status(HttpStatus.OK).body(LectureResponse.from(lectureAdminFacadeInfos));
    }



}
