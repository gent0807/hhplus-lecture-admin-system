package org.example.lectureAdminSystem.domain.apply.entity;

import org.example.lecutreAdminSystem.domain.apply.entity.Apply;
import org.example.lecutreAdminSystem.domain.lecture.entity.Lecture;
import org.example.lecutreAdminSystem.infrastructure.lecture.LectureRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApplyTest {

    Apply apply = new Apply();

    Lecture lecture = mock(Lecture.class);

    @Test
    void 수강신청_id에_1보다_작은_값을_입력했을_때_CustomException() {
        assertThatIllegalArgumentException()
                .isThrownBy(()->apply.validate(0L, 1L, 1L));
    }

    @Test
    void 강의_id에_1보다_작은_값을_입력했을_때_CustomException() {

        assertThatIllegalArgumentException()
                .isThrownBy(()->apply.validate(1L, 1L, 0L));
    }

    @Test
    void 사용자_id에_1보다_작은_값을_입력했을_때_CustomException() {

        assertThatIllegalArgumentException()
                .isThrownBy(()->apply.validate(1L, 0L, 1L));
    }

    @Test
    void 수강신청_하려는_강의와_이미_수강신청된_강의의_강의일과_강의시간대_확인시_겹치면_CustomException() {

        Lecture lectureSample = new Lecture();

        LocalDate date = LocalDate.now();

        LocalTime startTime = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute());

        LocalTime endTime = LocalTime.of(LocalTime.now().getHour() + 1, LocalTime.now().getMinute()).plusMinutes(30);

        lectureSample.setDate(date);

        lectureSample.setStartTime(startTime);

        lectureSample.setEndTime(endTime);

        apply.setLectureDate(lectureSample.getDate());

        apply.setStartTime(lectureSample.getStartTime());

        apply.setEndTime(lectureSample.getEndTime());

        when(lecture.getDate())
                .thenReturn(lectureSample.getDate());

        when(lecture.getStartTime())
            .thenReturn(lectureSample.getStartTime());

        when(lecture.getEndTime())
                .thenReturn(lectureSample.getEndTime());

        assertThatIllegalArgumentException()
                .isThrownBy(()->apply.checkDurableDateAndTime(lecture));

        Mockito.verify(lectureSample).setDate(date);
        Mockito.verify(lectureSample).setStartTime(startTime);
        Mockito.verify(lectureSample).setEndTime(endTime);
    }
}
