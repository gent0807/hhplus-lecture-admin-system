package org.example.lecutreAdminSystem.domain.apply.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.lecutreAdminSystem.application.admin.lecture.dto.ApplyResult;
import org.example.lecutreAdminSystem.domain.common.exception.ApplyInvalidException;
import org.example.lecutreAdminSystem.domain.lecture.entity.Lecture;
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

    @Column(name = "lecture_name", nullable = false)
    private String lectureName;

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

    public void validate(){

        checkUserId();

        checkLectureId();

        checkLectureName();

        checkLectureDate();

        checkLectureRoom();

        checkLectureCost();

        checkLectureTime();

    }

    public void checkApplyId() {
        if(this.applyId <= 0){
            throw new ApplyInvalidException(ErrorCode.APPLY_ID_INVALID);
        }
    }

    public void checkUserId() {
        if(this.userId <= 0){
            throw new ApplyInvalidException(ErrorCode.USER_ID_INVALID);
        }
    }

    private void checkLectureId() {
        if(this.lectureId <= 0){
            throw new ApplyInvalidException(ErrorCode.LECTURE_ID_INVALID);
        }
    }

    private void checkLectureName() {
        if(this.lectureName == null){
            throw new ApplyInvalidException(ErrorCode.LECTURE_NAME_NONE);
        }
    }

    public void checkLectureDate() {
        if(this.lectureDate == null){
            throw new ApplyInvalidException(ErrorCode.LECTURE_DATE_NONE);
        }
    }

    public void checkLectureRoom() {
        if(this.room == null){
            throw new ApplyInvalidException(ErrorCode.LECTURE_ROOM_NONE);
        }
    }

    public void checkLectureCost() {
        if(this.cost < 0){
            throw new ApplyInvalidException(ErrorCode.LECTURE_COST_INVALID);
        }
    }

    public void checkLectureTime() {
        if(this.startTime.isAfter(this.endTime)){
            throw new ApplyInvalidException(ErrorCode.LECTURE_TIME_INVALID);
        }
    }

    public static Apply of(long userId, long lectureId, String lectureName, LocalDate lectureDate, String room, Integer cost, LocalTime startTime, LocalTime endTime){

        Apply apply = Apply.builder()
                .userId(userId)
                .lectureId(lectureId)
                .lectureName(lectureName)
                .lectureDate(lectureDate)
                .room(room)
                .cost(cost)
                .startTime(startTime)
                .endTime(endTime)
                .build();

        apply.validate();

        return apply;
    }

    public void checkDuplicatedDateAndTime(Lecture lecture) throws ApplyInvalidException{

        LocalDate date = lecture.getDate();
        LocalTime startTime = lecture.getStartTime();
        LocalTime endTime = lecture.getEndTime();

        if(this.lectureDate.equals(date)){
            if(!(this.startTime.isAfter(endTime) || this.endTime.isBefore(startTime))){
                throw new ApplyInvalidException(ErrorCode.LECTURE_DATE_TIME_DUPLICATED);
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
