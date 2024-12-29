package org.example.lecutreAdminSystem.interfaces.api.apply.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.lecutreAdminSystem.interfaces.api.common.validation.annotation.ApplyId;
import org.example.lecutreAdminSystem.interfaces.api.common.validation.annotation.LectureId;
import org.example.lecutreAdminSystem.interfaces.api.common.validation.annotation.UserId;

@Getter
@Setter
@Builder
public class ApplyRequest {

    private Long applyId;

    @UserId
    private Long userId;

    @LectureId
    private Long lectureId;
}
