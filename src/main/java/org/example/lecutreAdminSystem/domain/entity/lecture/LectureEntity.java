package org.example.lecutreAdminSystem.domain.entity.lecture;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class LectureEntity {
    @Id
    private long id;
}
