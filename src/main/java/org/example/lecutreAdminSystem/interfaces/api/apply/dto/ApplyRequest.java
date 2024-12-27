package org.example.lecutreAdminSystem.interfaces.api.apply.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApplyRequest {
    private Long applyId;
    private Long userId;
    private Long lectureId;
}
