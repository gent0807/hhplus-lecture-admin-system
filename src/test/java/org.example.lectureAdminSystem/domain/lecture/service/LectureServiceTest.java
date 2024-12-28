package org.example.lectureAdminSystem.domain.lecture.service;

import org.example.lecutreAdminSystem.domain.apply.repository.ApplyRepository;
import org.example.lecutreAdminSystem.domain.lecture.repository.LectureRepository;
import org.example.lecutreAdminSystem.domain.lecture.service.LectureService;
import org.example.lecutreAdminSystem.infrastructure.apply.ApplyRepositoryImpl;
import org.example.lecutreAdminSystem.infrastructure.lecture.LectureRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LectureTest {
    // 의존 상황의 대역 생성
    LectureRepository lectureRepository = mock(LectureRepositoryImpl.class);

    // 의존 상황의 대역 생성
    ApplyRepository applyRepository = mock(ApplyRepositoryImpl.class);

    // 테스트 상황 설정
    LectureService lectureService = new LectureService(lectureRepository, applyRepository);

    @DisplayName("임의의 long type data를 user id에 input => 해당 id에 맞는 PointHistory return")
    @Test

}
