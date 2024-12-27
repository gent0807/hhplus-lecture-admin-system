package org.example.lecutreAdminSystem.domain.lecture.repository;

import org.example.lecutreAdminSystem.domain.lecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository {
    List<Lecture> findAll();

    List<Lecture> findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(String startDate, String endDate, String startTime, String endTime);

    Lecture findById(Long lectureId);

    Lecture save(Lecture lecture);

    boolean existsById(Long lectureId);
}
