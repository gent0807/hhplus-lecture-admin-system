package org.example.lecutreAdminSystem.interfaces.api.lecture;

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
@RequestMapping("/lecture")
public class LectureController {

    private final LectureAdminFacade lectureAdminFacade;

    /**
     * 특강 전체 목록을 조회
     * @return ResponseEntity<List<LectureResult>>
     */
    @GetMapping("/list")
    public ResponseEntity<List<LectureResult>> getLectures(){
        return new ResponseEntity<List<LectureResult>>(HttpStatus.OK);
    }

    /**
     * 날짜를 입력하면 해당 날짜에 수강 신청 가능한 특강 목록이 조회된다.
     * @param date
     * @return ResponseEntity<List<LectureResult>>
     */
    @GetMapping("/list/{date}")
    public ResponseEntity<List<LectureResult>> getLecturesByDate(@PathVariable String date){
        return new ResponseEntity<List<LectureResult>>(HttpStatus.OK);
    }

}
