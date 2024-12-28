package org.example.lecutreAdminSystem.domain.apply.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.lecutreAdminSystem.application.admin.lecture.dto.ApplyResult;
import org.example.lecutreAdminSystem.domain.lecture.entity.Lecture;
import org.example.lecutreAdminSystem.interfaces.api.common.exception.CustomException;
import org.example.lecutreAdminSystem.interfaces.api.common.exception.error.ErrorCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
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

    public static void validate(long applyId, long userId, long lectureId){
        if(applyId <= 1){
            throw new CustomException(ErrorCode.APPLY_ID_INVALID);
        }

        if(userId <= 1){
            throw new CustomException(ErrorCode.USER_ID_INVALID);
        }

        if(lectureId <= 1){
            throw new CustomException(ErrorCode.LECTURE_ID_INVALID);
        }
    }

    public void checkDurableDateAndTime(Lecture lecture) throws CustomException{

        LocalDate date = lecture.getDate();
        LocalTime startTime = lecture.getStartTime();
        LocalTime endTime = lecture.getEndTime();

        if(this.lectureDate.equals(date)){
            if(!(this.startTime.isAfter(endTime) || this.endTime.isBefore(startTime))){
                throw new CustomException(ErrorCode.LECTURE_DATE_TIME_DURABLE);
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
