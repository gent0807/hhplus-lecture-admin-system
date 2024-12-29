package org.example.lectureAdminSystem.domain.lecture.entity;

import org.example.lecutreAdminSystem.domain.lecture.entity.Lecture;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.Mockito.mock;


public class LectureTest {

    Lecture lecture = new Lecture();

    @Test
    void 강의_id에_1보다_작은_값을_입력했을_때_LectureInvalidException() {
        assertThatIllegalArgumentException()
                .isThrownBy(()->lecture.validate(-1L));
    }

    @Test
    void 강의_최대_인원_수_보다_수강_신청_인원_수가_많은_상황에서_최대_인원_수_확인하면_LectureInvalidException() {

        lecture.setMaxStudentCount(30L);

        lecture.setCurrentStudentCount(lecture.getMaxStudentCount() + 1L);

        assertThatIllegalArgumentException()
                .isThrownBy(()->lecture.checkStudentMaxCount());
    }

    @Test
    void 강의_현재_수강인원이_1명보다_작은_상황에서_최소_인원_수_확인하면_LectureInvalidException() {

        lecture.setCurrentStudentCount(0L);

        assertThatIllegalArgumentException()
                .isThrownBy(()->lecture.checkStudentMinCount());
    }

    @Test
    void 강의일이_오늘_이전인_상황에서_강의일_확인하면_LectureInvalidException() {

        lecture.setMaxStudentCount(30L);

        lecture.setCurrentStudentCount(lecture.getMaxStudentCount() + 1L);

        assertThatIllegalArgumentException()
                .isThrownBy(()->lecture.checkLectureDate());
    }
}
