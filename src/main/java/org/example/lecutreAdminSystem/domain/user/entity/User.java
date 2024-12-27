package org.example.lecutreAdminSystem.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncrement
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "age", nullable = false)
    private Long age;

    @Column(name = "job")
    private String job;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public static void validate(long userId){
        if(userId <= 0){
            throw new IllegalArgumentException();
        }

    }
}
