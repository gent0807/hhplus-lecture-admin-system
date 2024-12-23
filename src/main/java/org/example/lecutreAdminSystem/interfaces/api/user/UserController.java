package org.example.lecutreAdminSystem.interfaces.api.user;

import lombok.RequiredArgsConstructor;
import org.example.lecutreAdminSystem.application.facade.LectureAdminFacade;
import org.example.lecutreAdminSystem.application.facade.dto.LectureResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final LectureAdminFacade lectureAdminFacade;

    /**
     * 특정 id를 입력하면 해당 id의 수강 신청 가능한 특강 목록이 조회된다.
     * @param id
     * @return ResponseEntity<List<LectureResult>>
     */
    @GetMapping("{id}/lectures")
    public ResponseEntity<List<LectureResult>> getLecturesByUserId(@PathVariable Long id) {

        return new ResponseEntity<List<LectureResult>>(HttpStatus.OK);
    }
}
