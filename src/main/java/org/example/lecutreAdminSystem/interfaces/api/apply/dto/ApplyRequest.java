package org.example.lecutreAdminSystem.interfaces.api.apply.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApplyRequest {
    private long id;
    private long userId;
    private long lectureId;
}
