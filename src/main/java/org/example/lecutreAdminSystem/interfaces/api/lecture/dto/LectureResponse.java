package org.example.lecutreAdminSystem.interfaces.api.lecture.dto;

import org.example.lecutreAdminSystem.application.admin.lecture.enumeration.LECTURE_STATUS;
import org.example.lecutreAdminSystem.application.admin.lecture.dto.LectureResult;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record LectureResponse(
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
) {
    public static List<LectureResponse> convertFromDomainToAPIDTO(List<LectureResult> lectureResults) {
       return lectureResults.stream()
                .map(lectureResult ->
                        new LectureResponse(
                                lectureResult.lectureId(),
                                lectureResult.lectureName(),

                                lectureResult.teacherId(),
                                lectureResult.teacherName(),
                                lectureResult.teacherAge(),
                                lectureResult.teacherJob(),
                                lectureResult.teacherMajor(),
                                lectureResult.teacherGender(),
                                lectureResult.teacherRate(),

                                lectureResult.currentStudentCount(),
                                lectureResult.maxStudentCount(),
                                lectureResult.cost(),
                                lectureResult.room(),
                                lectureResult.date(),
                                lectureResult.startTime(),
                                lectureResult.endTime(),

                                lectureResult.status()
                                ))
                .toList();

    }
}
