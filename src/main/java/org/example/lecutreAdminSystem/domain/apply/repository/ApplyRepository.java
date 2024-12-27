package org.example.lecutreAdminSystem.domain.apply.repository;

import org.example.lecutreAdminSystem.domain.apply.entity.Apply;

import java.util.List;
import java.util.Optional;

public interface ApplyRepository {
    List<Apply> findByUserId(Long userId);

    List<Apply> findByUserIdAndLectureId(Long userId, Long lectureId);

    List<Apply> findAll();

    boolean existsByUserIdAndLectureId(Long userId, Long lectureId);

    void deleteByUserIdAndLectureId(Long userId, Long lectureId);

    void save(Apply apply);
}
