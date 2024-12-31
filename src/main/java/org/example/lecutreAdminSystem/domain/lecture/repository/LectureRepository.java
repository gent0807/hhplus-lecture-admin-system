package org.example.lecutreAdminSystem.domain.lecture.repository;

import org.example.lecutreAdminSystem.domain.lecture.entity.Lecture;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LectureRepository {
    Optional<List<Lecture>> findAll();

    Optional<List<Lecture>> findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(LocalDateTime startTime, LocalDateTime endTime);

    Optional<Lecture> findById(Long lectureId);

    Lecture save(Lecture lecture);

    boolean existsById(Long lectureId);
}
