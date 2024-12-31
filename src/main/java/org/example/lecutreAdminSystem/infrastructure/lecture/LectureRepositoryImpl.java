package org.example.lecutreAdminSystem.infrastructure.lecture;

import lombok.RequiredArgsConstructor;
import org.example.lecutreAdminSystem.domain.lecture.entity.Lecture;
import org.example.lecutreAdminSystem.domain.lecture.repository.LectureRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class LectureRepositoryImpl implements LectureRepository {
    private final LectureJPARepository jpaRepository;

    @Override
    public Optional<List<Lecture>> findAll() {
        return Optional.ofNullable(jpaRepository.findAll());
    }

    @Override
    public Optional<List<Lecture>> findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(LocalDateTime startTime, LocalDateTime endTime) {
        return jpaRepository.findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(startTime, endTime);
    }

    @Override
    public Optional<Lecture> findById(Long lectureId) {
        return jpaRepository.findById(lectureId);
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
