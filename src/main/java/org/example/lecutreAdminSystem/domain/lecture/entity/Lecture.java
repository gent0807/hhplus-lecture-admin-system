package org.example.lecutreAdminSystem.domain.lecture.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Lecture {
    @Id
    private long id;

    public boolean validate(){
        return true;
    }
}
