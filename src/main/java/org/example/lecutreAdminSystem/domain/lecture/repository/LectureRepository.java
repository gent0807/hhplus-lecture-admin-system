package org.example.lecutreAdminSystem.domain.lecture.repository;

import org.example.lecutreAdminSystem.domain.lecture.entity.Lecture;

import java.time.LocalDateTime;
import java.util.List;

public interface LectureRepository {
    List<Lecture> findAll();

    List<Lecture> findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(LocalDateTime startTime, LocalDateTime endTime);

    Lecture findById(Long lectureId);

    Lecture save(Lecture lecture);

    boolean existsById(Long lectureId);
}
