package org.example.lecutreAdminSystem.interfaces.api.apply;

import lombok.RequiredArgsConstructor;
import org.example.lecutreAdminSystem.application.admin.lecture.LectureAdminFacade;
import org.example.lecutreAdminSystem.application.admin.lecture.dto.LectureAdminFacadeInfo;
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
    public ResponseEntity<List<ApplyResponse>> findCurrentApplies(@RequestParam("userId") long userId, @RequestParam("lectureId") long lectureId) {

        List<LectureAdminFacadeInfo> lectureAdminFacadeInfos = lectureAdminFacade.findCurrentAppliesByUserIdAndLectureId(
                ApplyRequest.builder()
                        .userId(userId)
                        .lectureId(lectureId)
                        .build()
        );

        return ResponseEntity.status(HttpStatus.OK).body(ApplyResponse.from(lectureAdminFacadeInfos));
    }

    /**
     * 특정 사용자의 특정 강의에 대한 수강 신청 정보들을 수강 신청 목록(현황)에 추가한다.
     * @param applyRequests
     * @return ResponseEntity<>
     */
    @PostMapping("/new")
    public ResponseEntity<?> insertNewApplies(@RequestBody List<ApplyRequest> applyRequests) {

        lectureAdminFacade.insertNewApplies(applyRequests);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 특정 수강 신청 정보들을 수강 신청 목록에서 삭제한다.
     * applyRequests
     * @return ResponseEntity<?>
     */
    @DeleteMapping
    public ResponseEntity<?> removeApplies(@RequestParam List<ApplyRequest> applyRequests) {

        lectureAdminFacade.removeApplies(applyRequests);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
