package org.example.lecutreAdminSystem.domain.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.lecutreAdminSystem.interfaces.api.common.exception.error.ErrorCode;

@Getter
@Setter
@AllArgsConstructor
public class ApplyInvalidException extends IllegalArgumentException{
    ErrorCode errorCode;
}
