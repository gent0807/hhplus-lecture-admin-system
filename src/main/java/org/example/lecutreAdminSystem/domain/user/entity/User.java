package org.example.lecutreAdminSystem.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private long age;

    @Column(name = "wm")
    private String wm;

    @Column(name="job")
    private String job;

    public boolean validate(){
        return true;
    }
}
