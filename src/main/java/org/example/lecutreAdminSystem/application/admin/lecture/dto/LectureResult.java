package org.example.lecutreAdminSystem.application.admin.lecture.dto;

import org.example.lecutreAdminSystem.application.admin.lecture.enumeration.LECTURE_STATUS;
import org.example.lecutreAdminSystem.domain.teacher.entity.Teacher;

import java.time.LocalDate;
import java.time.LocalTime;

public record LectureResult(
        Long lectureId,
        String lectureName,

        Long teacherId,
        String teacherName,
        Long teacherAge,
        String teacherJob,
        String teacherMajor,
        String teacherGender,
        Float teacherRate,

        Long currentStudentCount,
        Long maxStudentCount,
        Integer cost,
        String room,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,

        LECTURE_STATUS status

){

}