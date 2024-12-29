package org.example.lecutreAdminSystem.domain.lecture.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.lecutreAdminSystem.application.admin.lecture.dto.LectureResult;
import org.example.lecutreAdminSystem.application.admin.lecture.enumeration.LECTURE_STATUS;
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

    public static void validate(long lectureId)  {
        if(lectureId <= 0){
            throw new LectureInvalidException(ErrorCode.LECTURE_ID_INVALID);
        }
    }

    public void checkStudentMaxCount() throws LectureInvalidException {
        if(this.currentStudentCount >= this.maxStudentCount){
            throw new LectureInvalidException(ErrorCode.LECTURE_OVER_MAX_STUDENT);
        }
    }

    public void checkStudentMinCount() throws LectureInvalidException {
        if(this.currentStudentCount < 1){
            throw new LectureInvalidException(ErrorCode.LECTURE_UNDER_MIN_STUDENT);
        }
    }

    public void checkLectureDate() throws LectureInvalidException{
        LocalDate today = LocalDate.now();

        if(today.isAfter(this.date) || today.isEqual(this.date)){
            throw new LectureInvalidException(ErrorCode.APPLY_ALREADY_END);
        }
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
