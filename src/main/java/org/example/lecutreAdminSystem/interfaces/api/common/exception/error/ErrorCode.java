package org.example.lecutreAdminSystem.interfaces.api.common.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_ID_INVALID(HttpStatus.BAD_REQUEST, "USER_ID_INVALID", "유효하지 않은 사용자 아이디입니다."),
    
    LECTURE_ID_INVALID(HttpStatus.BAD_REQUEST, "LECTURE_ID_INVALID", "유효하지 않은 강의 아이디입니다."),
    LECTURE_OVER_MAX_STUDENT(HttpStatus.NOT_ACCEPTABLE, "LECTURE_OVER_MAX_STUDENT", "수강신청 인원이 마감되었습니다."),
    LECTURE_UNDER_MIN_STUDENT(HttpStatus.NOT_ACCEPTABLE, "LECTURE_UNDER_MIN_STUDENT", "수강신청된 인원이 없으므로 수강신청 취소는 불가합니다."),
    LECTURE_DATE_TIME_DUPLICATED(HttpStatus.NOT_ACCEPTABLE, "LECTURE_DATE_TIME_DURABLE", "기존 신청된 강의와 강의 시간이 중복됩니다"),
    LECTURE_NONE(HttpStatus.NOT_FOUND, "LECTURE_NONE", "해당 강의가 존재하지 않습니다."),
    LECTURE_COST_INVALID(HttpStatus.NOT_ACCEPTABLE, "LECTURE_COST_INVALID", "올바르지 않은 강의 수강료입니다."),
    LECTURE_MAX_COUNT_INVALID(HttpStatus.NOT_ACCEPTABLE, "LECTURE_MAX_COUNT_INVALID", "유효하지 않은 강의 최대 인원입니다."),
    LECTURE_CURRENT_COUNT_INVALID(HttpStatus.NOT_ACCEPTABLE, "LECTURE_CURRENT_COUNT_INVALID", "유효하지 않은 현재 강의 인원입니다."),
    LECTURE_DATE_NONE(HttpStatus.NOT_ACCEPTABLE, "LECTURE_DATE_NONE", "강의일은 필수 사항입니다."),
    LECTURE_ROOM_NONE(HttpStatus.NOT_ACCEPTABLE, "LECTURE_ROOM_NONE", "강의실은 필수 사항입니다."),
    
    APPLY_ID_INVALID(HttpStatus.BAD_REQUEST, "APPLY_ID_INVALID", "유효하지 않은 수강신청 아이디입니다."),
    APPLY_ALREADY_END(HttpStatus.NOT_ACCEPTABLE, "APPLY_ALREADY_END", "수강신청 기간이 이미 종료되었습니다."),
    APPLY_EXIST(HttpStatus.NOT_ACCEPTABLE, "APPLY_EXIST", "이미 동일한 강의를 수강신청하였습니다."),
    APPLY_NONE(HttpStatus.NOT_FOUND, "APPLY_NONE", "수강신청 정보가 존재하지 않습니다."),
    LECTURE_TIME_INVALID(HttpStatus.BAD_REQUEST, "TIME_INVALID", "시작 시각과 종료 시각을 올바르지 않습니다.");
    
    private final HttpStatus httpStatus;	// HttpStatus
    private final String code;
    private final String message;
}
