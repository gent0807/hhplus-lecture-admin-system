package org.example.lecutreAdminSystem.interfaces.api.apply;

import lombok.RequiredArgsConstructor;
import org.example.lecutreAdminSystem.application.admin.lecture.enumeration.APPLY_RESULT;
import org.example.lecutreAdminSystem.application.admin.lecture.enumeration.CANCLE_RESULT;
import org.example.lecutreAdminSystem.application.admin.lecture.LectureAdminFacade;
import org.example.lecutreAdminSystem.application.admin.lecture.dto.ApplyParam;
import org.example.lecutreAdminSystem.application.admin.lecture.dto.ApplyResult;
import org.example.lecutreAdminSystem.interfaces.api.apply.dto.ApplyRequest;
import org.example.lecutreAdminSystem.interfaces.api.apply.dto.ApplyResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/applies")
public class ApplyController {

    private final LectureAdminFacade lectureAdminFacade;

    /**
     * 특정 사용자 혹은 특정 강의에 대한 현재 수강 신청 목록(현황)을 조회한다.
     * @param userId, lectureId
     * @return ResponseEntity<List<ApplyResponse>>
     */
    @GetMapping("/current")
    public ResponseEntity<List<ApplyResponse>> findCurrentAppliesByUserIdAndLectureId(@RequestParam("userId") long userId, @RequestParam("lectureId") long lectureId) {

        List<ApplyResult> applyResults = lectureAdminFacade.findCurrentAppliesByUserIdAndLectureId(
                ApplyParam.convertFromAPIToDomainDTO(null, userId, lectureId));

        return ResponseEntity.status(HttpStatus.OK).body(ApplyResponse.convertFromDomainToAPIDTO(applyResults));
    }

    /**
     * 특정 사용자의 특정 강의에 대한 수강 신청 정보를 수강 신청 목록(현황)에 추가한다.
     * @param applyRequest
     * @return ResponseEntity<>
     */
    @PostMapping("/new")
    public ResponseEntity<?> insertNewApplies(@RequestBody ApplyRequest applyRequest) throws Exception {

        APPLY_RESULT status = lectureAdminFacade.insertNewApplies(ApplyParam.convertFromAPIToDomainDTO(applyRequest.getApplyId(), applyRequest.getUserId(), applyRequest.getLectureId()));

        if(status == APPLY_RESULT.FAIL){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

        if(status == APPLY_RESULT.SUCCESS){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 특정 수강 신청 정보를 수강 신청 목록에서 삭제한다.
     * applyRequest
     * @return ResponseEntity<?>
     */
    @DeleteMapping
    public ResponseEntity<?> removeApplies(@RequestParam ApplyRequest applyRequest) throws Exception {

        CANCLE_RESULT status = lectureAdminFacade.removeApplies(ApplyParam.convertFromAPIToDomainDTO(applyRequest.getApplyId(), applyRequest.getUserId(), applyRequest.getLectureId()));

        if(status == CANCLE_RESULT.FAIL){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

        if(status == CANCLE_RESULT.SUCCESS){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
