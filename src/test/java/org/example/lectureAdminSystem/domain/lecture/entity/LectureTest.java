package org.example.lectureAdminSystem.domain.lecture.entity;

import org.example.lecutreAdminSystem.domain.common.exception.LectureInvalidException;
import org.example.lecutreAdminSystem.domain.lecture.entity.Lecture;
import org.example.lecutreAdminSystem.interfaces.api.common.exception.error.ErrorCode;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;


public class LectureTest {

    Lecture lecture = new Lecture();

    @Test
    void 강의_id에_1보다_작은_값을_입력했을_때_LectureInvalidException() {

        lecture.setLectureId(0L);

        assertThatThrownBy(()->lecture.checkLectureId())
                .isExactlyInstanceOf(LectureInvalidException.class)
                .extracting("errorCode")
                .isEqualTo(ErrorCode.LECTURE_ID_INVALID);



    }

    @Test
    void 강의_최대_인원_수_보다_수강_신청_인원_수가_많은_상황에서_최대_인원_수_확인하면_LectureInvalidException() {

        lecture.setMaxStudentCount(30L);

        lecture.setCurrentStudentCount(lecture.getMaxStudentCount() + 1L);

        assertThatThrownBy(()->lecture.checkStudentMaxCount())
                .isExactlyInstanceOf(LectureInvalidException.class)
                .extracting("errorCode")
                .isEqualTo(ErrorCode.LECTURE_OVER_MAX_STUDENT);
    }


    @Test
    void 강의_현재_수강인원이_1명보다_작은_상황에서_최소_인원_수_확인하면_LectureInvalidException() {

        lecture.setCurrentStudentCount(0L);

        assertThatThrownBy(()->lecture.checkStudentMinCount())
                .isExactlyInstanceOf(LectureInvalidException.class)
                        .extracting("errorCode")
                                .isEqualTo(ErrorCode.LECTURE_UNDER_MIN_STUDENT);
    }

    @Test
    void 강의일이_오늘_이전인_상황에서_강의일_확인하면_LectureInvalidException() {

        lecture.setDate(LocalDate.now().minusDays(1L));

        assertThatThrownBy(()->lecture.checkLectureDate())
                .isExactlyInstanceOf(LectureInvalidException.class)
                .extracting("errorCode")
                .isEqualTo(ErrorCode.APPLY_ALREADY_END);
    }
}
