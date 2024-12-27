package org.example.lecutreAdminSystem.infrastructure.lecture;

import lombok.RequiredArgsConstructor;
import org.example.lecutreAdminSystem.domain.lecture.entity.Lecture;
import org.example.lecutreAdminSystem.domain.lecture.repository.LectureRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class LectureRepositoryImpl implements LectureRepository {
    private final LectureJPARepository jpaRepository;

    @Override
    public List<Lecture> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public List<Lecture> findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(LocalDateTime startTime, LocalDateTime endTime) {
        return jpaRepository.findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(startTime, endTime);
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
