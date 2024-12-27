package org.example.lecutreAdminSystem.infrastructure.lecture;

import lombok.RequiredArgsConstructor;
import org.example.lecutreAdminSystem.domain.lecture.entity.Lecture;
import org.example.lecutreAdminSystem.domain.lecture.repository.LectureRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
@Repository
public class LectureRepositoryImpl implements LectureRepository {
    private final LectureJPARepository jpaRepository;

    @Override
    public List<Lecture> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public List<Lecture> findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(String startDate, String endDate, String startTime, String endTime) {
        return jpaRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(startDate, endDate, startTime, endTime);
    }

    @Override
    public Lecture findById(Long lectureId) {
        return jpaRepository.findById(lectureId).orElse(null);
    }

    @Override
    public Lecture save(Lecture lecture) {
        return jpaRepository.save(lecture);
    }

    @Override
    public boolean existsById(Long lectureId) {
        return jpaRepository.existsById(lectureId);
    }
}
