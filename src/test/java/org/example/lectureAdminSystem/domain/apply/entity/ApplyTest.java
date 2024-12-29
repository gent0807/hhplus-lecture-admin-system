package org.example.lectureAdminSystem.domain.apply.entity;

import org.assertj.core.api.Assertions;
import org.example.lecutreAdminSystem.domain.apply.entity.Apply;
import org.example.lecutreAdminSystem.domain.lecture.entity.Lecture;
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
    void 수강신청_id에_1보다_작은_값을_입력했을_때_AppplyInvalidException() {
        assertThatIllegalArgumentException()
                .isThrownBy(()->apply.validate(0L, 1L, 1L));
    }

    @Test
    void 강의_id에_1보다_작은_값을_입력했을_때_ApplyInvalidException() {

        assertThatIllegalArgumentException()
                .isThrownBy(()->apply.validate(1L, 1L, 0L));
    }

    @Test
    void 사용자_id에_1보다_작은_값을_입력했을_때_ApplyInvalidException() {

        assertThatIllegalArgumentException()
                .isThrownBy(()->apply.validate(1L, 0L, 1L));
    }

    @Test
    void 수강신청_하려는_강의와_이미_수강신청된_강의의_강의일과_강의시간대_겹치면_ApplyInvalidException() {

        LocalDate date = LocalDate.now();

        LocalTime startTime = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute());

        LocalTime endTime = LocalTime.of(startTime.getHour() + 2, startTime.getMinute());

        apply.setLectureDate(date);

        apply.setStartTime(startTime);

        apply.setEndTime(endTime);

        when(lecture.getDate())
                .thenReturn(date);

        when(lecture.getStartTime())
            .thenReturn(startTime);

        when(lecture.getEndTime())
                .thenReturn(endTime);

        assertThatIllegalArgumentException()
                .isThrownBy(()->apply.checkDuplicatedDateAndTime(lecture));

        Mockito.verify(lecture).getDate();
        Mockito.verify(lecture).getStartTime();
        Mockito.verify(lecture).getEndTime();
    }
}
