package org.example.lecutreAdminSystem.interfaces.api.lecture;

import lombok.RequiredArgsConstructor;
import org.example.lecutreAdminSystem.application.facade.LectureAdmin;
import org.example.lecutreAdminSystem.interfaces.api.lecture.dto.LectureResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {

    private final LectureAdmin lectureAdmin;

    /**
     * 특강 전체 목록을 조회
     * @return ResponseEntity<List<LectureResponse>>
     */
    @GetMapping("/list")
    public ResponseEntity<List<LectureResponse>> getLectures(){
        return new ResponseEntity<List<LectureResponse>>(HttpStatus.OK);
    }

    /**
     * 날짜를 입력하면 해당 날짜에 수강 신청 가능한 특강 목록이 조회된다.
     * @param date
     * @return ResponseEntity<List<LectureResponse>>
     */
    @GetMapping("/list/{date}")
    public ResponseEntity<List<LectureResponse>> getLecturesByDate(@PathVariable String date){
        return new ResponseEntity<List<LectureResponse>>(HttpStatus.OK);
    }

    /**
     * 특정 id를 입력하면 해당 id의 수강 신청 가능한 특강 목록이 조회된다.
     * @param id
     * @return ResponseEntity<List<LectureResponse>>
     */
    @GetMapping("/list/{id}")
    public ResponseEntity<List<LectureResponse>> getLecturesByUserId(@PathVariable Long id) {

        return new ResponseEntity<List<LectureResponse>>(HttpStatus.OK);
    }



}
