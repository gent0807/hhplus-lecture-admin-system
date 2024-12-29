package org.example.lectureAdminSystem.domain.apply.entity;

import org.assertj.core.api.Assertions;
import org.example.lecutreAdminSystem.domain.apply.entity.Apply;
import org.example.lecutreAdminSystem.domain.common.exception.ApplyInvalidException;
import org.example.lecutreAdminSystem.domain.lecture.entity.Lecture;
import org.example.lecutreAdminSystem.interfaces.api.common.exception.error.ErrorCode;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApplyTest {

    Lecture lecture = mock(Lecture.class);

    Apply apply = new Apply();

    @Test
    void 수강_신청하려는_강의와_이미_수강신청된_강의의_강의일과_강의시간대_겹치면_ApplyInvalidException() {

        LocalDate date = LocalDate.now();

        LocalTime startTime = LocalTime.of(14, LocalTime.now().getMinute());

        LocalTime endTime = LocalTime.of(startTime.getHour() + 2, startTime.getMinute());

        setCheckingDuplicatedDateAndTime(date, startTime, endTime, date, startTime, endTime);

        assertThatThrownBy(()->apply.checkDuplicatedDateAndTime(lecture))
                .isExactlyInstanceOf(ApplyInvalidException.class)
                .extracting("errorCode")
                .isEqualTo(ErrorCode.LECTURE_DATE_TIME_DUPLICATED);

        Mockito.verify(lecture).getDate();
        Mockito.verify(lecture).getStartTime();
        Mockito.verify(lecture).getEndTime();
    }

    @Test
    void 수강_신청하려는_강의의_시작일과_이미_수강_신청된_강의일이_다른_경우_NoException() {
        LocalDate today = LocalDate.now();

        LocalTime startTime = LocalTime.of(14, LocalTime.now().getMinute());

        LocalTime endTime = LocalTime.of(startTime.getHour() + 2, startTime.getMinute());

        LocalDate lectureDateToApply = today.plusDays(10L);

        LocalDate lectureDateApplied = today.plusDays(5L);

        setCheckingDuplicatedDateAndTime(lectureDateApplied, startTime, endTime, lectureDateToApply, startTime, endTime);

        assertThatNoException().isThrownBy(()->apply.checkDuplicatedDateAndTime(lecture));

        Mockito.verify(lecture).getDate();
        Mockito.verify(lecture).getStartTime();
        Mockito.verify(lecture).getEndTime();
    }



    @Test
    void 수강_신청하려는_강의의_종료시간이_이미_수강_신청된_강의시작시간_보다_앞인_경우_NoException() {
        LocalDate today = LocalDate.now();

        LocalDate lectureDateToApply = today.plusDays(10L);

        LocalTime startTimeToApply = LocalTime.of(14, LocalTime.now().getMinute());

        LocalTime endTimeToApply = LocalTime.of(startTimeToApply.getHour() + 2, startTimeToApply.getMinute());

        LocalDate lectureDateApplied = lectureDateToApply;

        LocalTime startTimeApplied = LocalTime.of(endTimeToApply.getHour() + 2, LocalTime.now().getMinute());

        LocalTime endTimeApplied = LocalTime.of(startTimeApplied.getHour() + 2, startTimeApplied.getMinute());

        setCheckingDuplicatedDateAndTime(lectureDateApplied, startTimeApplied, endTimeApplied, lectureDateToApply, startTimeToApply, endTimeToApply);

        assertThatNoException().isThrownBy(()->apply.checkDuplicatedDateAndTime(lecture));

        Mockito.verify(lecture).getDate();
        Mockito.verify(lecture).getStartTime();
        Mockito.verify(lecture).getEndTime();
    }

    @Test
    void 수강_신청하려는_강의의_시작시간이_이미_수강_신청된_강의종료시간_보다_뒤인_경우_NoException() {
        LocalDate today = LocalDate.now();

        LocalDate lectureDateApplied = today.plusDays(10L);

        LocalTime startTimeApplied = LocalTime.of(14, LocalTime.now().getMinute());

        LocalTime endTimeApplied = LocalTime.of(startTimeApplied.getHour() + 2, startTimeApplied.getMinute());

        LocalDate lectureDateToApply = lectureDateApplied;

        LocalTime startTimeToApply = LocalTime.of( endTimeApplied.getHour() +2, LocalTime.now().getMinute());

        LocalTime endTimeToApply = LocalTime.of(startTimeToApply.getHour() + 2, startTimeToApply.getMinute());



        setCheckingDuplicatedDateAndTime(lectureDateApplied, startTimeApplied, endTimeApplied, lectureDateToApply, startTimeToApply, endTimeToApply);

        assertThatNoException().isThrownBy(()->apply.checkDuplicatedDateAndTime(lecture));

        Mockito.verify(lecture).getDate();
        Mockito.verify(lecture).getStartTime();
        Mockito.verify(lecture).getEndTime();
    }

    private void setCheckingDuplicatedDateAndTime(LocalDate lectureDateApplied, LocalTime startTimeApplid, LocalTime endTimeApplied,
                                                  LocalDate lectureDateToApply, LocalTime startTimeToApply, LocalTime endTimeToApply) {
        apply.setLectureDate(lectureDateApplied);

        apply.setStartTime(startTimeApplid);

        apply.setEndTime(endTimeApplied);

        when(lecture.getDate())
                .thenReturn(lectureDateToApply);

        when(lecture.getStartTime())
                .thenReturn(startTimeToApply);

        when(lecture.getEndTime())
                .thenReturn(endTimeToApply);
    }




}
