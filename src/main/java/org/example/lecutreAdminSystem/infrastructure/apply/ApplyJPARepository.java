package org.example.lecutreAdminSystem.infrastructure.apply;

import org.example.lecutreAdminSystem.domain.apply.entity.Apply;
import org.example.lecutreAdminSystem.domain.apply.repository.ApplyRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplyJPARepository extends JpaRepository<Apply, Long> {
    Optional<List<Apply>> findByUserId(Long userId);

    Optional<List<Apply>> findByUserIdAndLectureId(Long userId, Long lectureId);

    boolean existsByUserIdAndLectureId(Long userId, Long lectureId);

    void deleteByUserIdAndLectureId(Long userId, Long lectureId);

}
