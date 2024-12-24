package org.example.lecutreAdminSystem.interfaces.api.apply;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apply")
public class ApplyController {

    /**
     * 사용자 id와 강의 id를 입력하면 특강 신청 목록에 추가된다.
     * @param uid
     * @param lid
     * @return ResponseEntity<?>
     */
    @PostMapping("{user_id}/{lecture_id}")
    public ResponseEntity<?> applyLecture(@PathVariable String uid, @PathVariable String lid) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 사용자 id와 강의 id를 입력하면 특강 신청 목록에서 삭제된다.
     * @param uid
     * @param lid
     * @return ResponseEntity<?>
     */
    @DeleteMapping("{user_id}/{lecture_id}")
    public ResponseEntity<?> removeApply(@PathVariable String uid, @PathVariable String lid) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
