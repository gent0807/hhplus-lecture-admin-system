package org.example.lecutreAdminSystem.domain.lecture.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Lecture {
    @Id
    private long id;
}
