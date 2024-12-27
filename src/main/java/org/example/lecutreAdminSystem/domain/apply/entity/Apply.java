package org.example.lecutreAdminSystem.domain.apply.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.lecutreAdminSystem.application.admin.lecture.dto.ApplyResult;
import org.example.lecutreAdminSystem.domain.lecture.entity.Lecture;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "APPLIES")
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apply_id", nullable = false)
    private Long applyId;

    @Column(name = "user_id", unique = true, nullable = false)
    private Long userId;

    @Column(name = "lecture_id", unique = true, nullable = false)
    private Long lectureId;

    @Column(name = "lecture_date", nullable = false)
    private LocalDate lectureDate;

    @Column(name= "room", nullable = false)
    private String room;

    @Column(name="cost", nullable = false, columnDefinition = "INT UNSIGNED default 50000")
    private Integer cost;

    @Column(name="start_time", nullable = false)
    private LocalTime startTime;

    @Column(name="end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    public static void validate(long applyId, long userId, long lectureId) throws IllegalArgumentException{
        if(applyId <= 0 || userId <= 0 || lectureId <= 0){
            throw new IllegalArgumentException();
        }
    }

    public void checkDurableDate(Lecture lecture) {

        if(lecture == null){
            throw new IllegalArgumentException();
        }

        LocalDate date = lecture.getDate();
        LocalTime startTime = lecture.getStartTime();
        LocalTime endTime = lecture.getEndTime();

        if(this.lectureDate.equals(date)){
            if(!(this.startTime.isAfter(endTime) || this.endTime.isBefore(startTime))){
                throw new IllegalArgumentException();
            }
        }
    }

    public ApplyResult convertFromEntityToDomainDTO() {
        return new ApplyResult(
                this.getApplyId(),
                this.getUserId(),
                this.getLectureId(),
                this.getLectureDate(),
                this.getRoom(),
                this.getCost(),
                this.getStartTime(),
                this.getEndTime(),
                this.getCreatedAt(),
                this.getUpdatedAt()
        );
    }
}
