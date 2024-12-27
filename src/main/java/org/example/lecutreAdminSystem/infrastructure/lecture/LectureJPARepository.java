package org.example.lecutreAdminSystem.infrastructure.lecture;

import org.example.lecutreAdminSystem.domain.lecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureJPARepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(String startDate, String endDate, String startTime, String endTime);
}
