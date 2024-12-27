package org.example.lecutreAdminSystem.domain.lecture.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.lecutreAdminSystem.application.admin.lecture.dto.LectureResult;
import org.example.lecutreAdminSystem.application.admin.lecture.enumeration.LECTURE_STATUS;
import org.example.lecutreAdminSystem.domain.teacher.entity.Teacher;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public static void validate(long lectureId) throws IllegalArgumentException {
        if(lectureId <= 0){
            throw new IllegalArgumentException();
        }
    }

    public void checkStudentMaxCount() throws IllegalArgumentException {
        if(this.currentStudentCount >= this.maxStudentCount){
            throw new IllegalArgumentException();
        }
    }

    public void checkLectureDate() throws IllegalArgumentException{
        LocalDate today = LocalDate.now();

        if(today.isAfter(this.date)){
            throw new IllegalArgumentException();
        }
    }

    public void checkStudentMinCount() throws IllegalArgumentException{
        if(this.currentStudentCount < 1){
            throw new IllegalArgumentException();
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
