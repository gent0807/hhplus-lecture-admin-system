package org.example.lecutreAdminSystem.infrastructure.lecture;

import org.example.lecutreAdminSystem.domain.lecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LectureJPARepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(LocalDateTime startTime, LocalDateTime endTime);
}
