package org.example.lecutreAdminSystem.domain.lecture.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.lecutreAdminSystem.application.admin.lecture.dto.LectureResult;
import org.example.lecutreAdminSystem.application.admin.lecture.enumeration.LECTURE_STATUS;
import org.example.lecutreAdminSystem.domain.common.exception.ApplyInvalidException;
import org.example.lecutreAdminSystem.domain.common.exception.LectureInvalidException;
import org.example.lecutreAdminSystem.domain.teacher.entity.Teacher;
import org.example.lecutreAdminSystem.interfaces.api.common.exception.error.ErrorCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@AllArgsConstructor
@Entity
@Table(name = "LECTURES")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id", nullable = false)
    private Long lectureId;

    @Column(name = "lecture_name", nullable = false)
    private String lectureName;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @Column(name="current_student_count", nullable = false, columnDefinition = "BIGINT UNSIGNED default 0")
    private Long currentStudentCount;

    @Column(name="max_student_count", nullable = false, columnDefinition = "BIGINT UNSIGNED default 30")
    private Long maxStudentCount;

    @Column(name="cost", nullable = false, columnDefinition = "INT UNSIGNED default 50000")
    private Integer cost;

    @Column(name="room", nullable = false)
    private String room;

    @Column(name="date", nullable = false)
    private LocalDate date;

    @Column(name="start_time", nullable = false)
    private LocalTime startTime;

    @Column(name="end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public void validate() throws LectureInvalidException{

        checkLectureName();

        checkTeacher();

        checkStudentCurrentCount();

        checkStudentMaxCount();

        checkLectureCost();

        checkLectureRoom();

        checkLectureDate();

        checkLectureTime();


    }

    public void checkLectureId() throws LectureInvalidException{
        if(this.lectureId <= 0){
            throw new LectureInvalidException(ErrorCode.LECTURE_ID_INVALID);
        }

    }

    private void checkLectureName() throws LectureInvalidException{
        if(this.lectureName == null){
            throw new ApplyInvalidException(ErrorCode.LECTURE_NAME_NONE);
        }
    }

    private void checkTeacher() throws LectureInvalidException{
        if(this.teacher == null){
            throw new LectureInvalidException(ErrorCode.LECTURE_TEACHER_NONE);
        }
    }


    private void checkStudentCurrentCount() throws LectureInvalidException{
        if(this.currentStudentCount == null){
            throw new LectureInvalidException(ErrorCode.LECTURE_CURRENT_COUNT_NONE);
        }
    }

    public void checkStudentMaxCount() throws LectureInvalidException {

        if(this.maxStudentCount <= 0){
            throw new LectureInvalidException(ErrorCode.LECTURE_MAX_COUNT_INVALID);
        }

        if(this.currentStudentCount >= this.maxStudentCount){
            throw new LectureInvalidException(ErrorCode.LECTURE_OVER_MAX_STUDENT);
        }
    }

    public void checkStudentMinCount() throws LectureInvalidException {

        if(this.currentStudentCount < 0){
            throw new LectureInvalidException(ErrorCode.LECTURE_CURRENT_COUNT_INVALID);
        }

        if(this.currentStudentCount < 1){
            throw new LectureInvalidException(ErrorCode.LECTURE_UNDER_MIN_STUDENT);
        }
    }

    public void checkLectureCost() throws LectureInvalidException{
        if(this.cost < 0){
            throw new LectureInvalidException(ErrorCode.LECTURE_COST_INVALID);
        }
    }

    private void checkLectureRoom() throws LectureInvalidException{
        if(this.room == null){
            throw new LectureInvalidException(ErrorCode.LECTURE_ROOM_NONE);
        }
    }

    public void checkLectureDate() throws LectureInvalidException{
        LocalDate today = LocalDate.now();

        if(today.isAfter(this.date) || today.isEqual(this.date)){
            throw new LectureInvalidException(ErrorCode.APPLY_ALREADY_END);
        }
    }

    public void checkLectureTime() throws LectureInvalidException{

        if(this.startTime == null){
            throw new LectureInvalidException(ErrorCode.LECTURE_START_TIME_NONE);
        }

        if(this.endTime == null){
            throw new LectureInvalidException(ErrorCode.LECTURE_END_TIME_NONE);
        }

        if(this.startTime.isAfter(this.endTime)){
            throw new LectureInvalidException(ErrorCode.LECTURE_TIME_INVALID);
        }
    }

    public static Lecture of(String lectureName, Teacher teacher, Long currentStudentCount, Long maxStudentCount, Integer cost, String room, LocalDate date, LocalTime startTime, LocalTime endTime){

        Lecture lecture = Lecture.builder()
                .lectureName(lectureName)
                .teacher(teacher)
                .currentStudentCount(currentStudentCount)
                .maxStudentCount(maxStudentCount)
                .cost(cost)
                .room(room)
                .date(date)
                .startTime(startTime)
                .endTime(endTime)
                .build();

        lecture.validate();

        return lecture;
    }

    public LectureResult convertFromEntityToDomainDTO(LECTURE_STATUS status){

        Teacher teacher = this.getTeacher();

        return new LectureResult(
                this.getLectureId(),
                this.getLectureName(),
                teacher.getTeacherId(),
                teacher.getTeacherName(),
                teacher.getAge(),
                teacher.getJob(),
                teacher.getMajor(),
                teacher.getGender(),
                teacher.getRate(),
                this.getCurrentStudentCount(),
                this.getMaxStudentCount(),
                this.getCost(),
                this.getRoom(),
                this.getDate(),
                this.getStartTime(),
                this.getEndTime(),
                status
        );
    }
}
