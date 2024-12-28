package org.example.lecutreAdminSystem.interfaces.api.common.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_ID_INVALID(HttpStatus.BAD_REQUEST, "USER_ID_INVALID", "유효하지 않은 사용자 아이디입니다."),
    LECTURE_ID_INVALID(HttpStatus.BAD_REQUEST, "LECTURE_ID_INVALID", "유효하지 않은 강의 아이디입니다."),
    APPLY_ID_INVALID(HttpStatus.BAD_REQUEST, "APPLY_ID_INVALID", "유효하지 않은 수강신청 아이디입니다."),
    LECTURE_OVER_MAX_STUDENT(HttpStatus.NOT_EXTENDED, "LECTURE_OVER_MAX_STUDENT", "수강신청 인원이 마감되었습니다."),
    LECTURE_UNDER_MIN_STUDENT(HttpStatus.NOT_EXTENDED, "LECTURE_UNDER_MIN_STUDENT", "수강신청된 인원이 없으므로 수강신청 취소는 불가합니다."),
    LECTURE_DATE_TIME_DURABLE(HttpStatus.NOT_EXTENDED, "LECTURE_DATE_TIME_DURABLE", "기존 신청된 강의와 강의 시간이 중복됩니다"),
    LECTURE_NONE(HttpStatus.NOT_FOUND, "LECTURE_NONE", "해당 강의가 존재하지 않습니다."),
    APPLY_ALREADY_END(HttpStatus.NOT_EXTENDED, "APPLY_ALREADY_END", "수강신청 기간이 이미 종료되었습니다."),
    APPLY_EXIST(HttpStatus.NOT_EXTENDED, "APPLY_EXIST", "이미 동일한 강의를 수강신청하였습니다."),
    APPLY_NONE(HttpStatus.NOT_FOUND, "APPLY_NONE", "수강신청 정보가 존재하지 않습니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "INVALID_PASSWORD", "비밀번호가 일치하지 않습니다.");
    // ...

    private final HttpStatus httpStatus;	// HttpStatus
    private final String code;
    private final String message;
}
