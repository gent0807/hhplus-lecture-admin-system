package org.example.lecutreAdminSystem.interfaces.api.common.exception.error;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseErrorDTO {
    String code;
    String message;
}
