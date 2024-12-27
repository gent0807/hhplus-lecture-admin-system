package org.example.lecutreAdminSystem.domain.teacher.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TEACHERS")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;

    @Column(name = "teacher_name", nullable = false)
    private String teacherName;

    @Column(name = "age", nullable = false)
    private Long age;

    @Column(name="job")
    private String job;

    @Column(name="major")
    private String major;

    @Column(name="gender")
    private String gender;

    @Column(name="rate")
    private Float rate;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public boolean validate(){
        return true;
    }
}
