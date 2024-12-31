package org.example.lecutreAdminSystem.infrastructure.apply;

import lombok.RequiredArgsConstructor;
import org.example.lecutreAdminSystem.domain.apply.entity.Apply;
import org.example.lecutreAdminSystem.domain.apply.repository.ApplyRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ApplyRepositoryImpl implements ApplyRepository {
    private final ApplyJPARepository jpaRepository;

    @Override
    public Optional<List<Apply>> findByUserId(Long userId) {
        return jpaRepository.findByUserId(userId);
    }

    @Override
    public Optional<List<Apply>> findByUserIdAndLectureId(Long userId, Long lectureId) {
        return jpaRepository.findByUserIdAndLectureId(userId, lectureId);
    }

    @Override
    public Optional<List<Apply>> findAll() {
        return Optional.ofNullable(jpaRepository.findAll());
    }

    @Override
    public boolean existsByUserIdAndLectureId(Long userId, Long lectureId) {
        return jpaRepository.existsByUserIdAndLectureId(userId, lectureId);
    }

    @Override
    public void deleteByUserIdAndLectureId(Long userId, Long lectureId) {
        jpaRepository.deleteByUserIdAndLectureId(userId, lectureId);
    }

    @Override
    public void save(Apply apply) {
        jpaRepository.save(apply);
    }

}
