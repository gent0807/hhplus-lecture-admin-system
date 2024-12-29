package org.example.lecutreAdminSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@Validated
public class LectureAdminApp {

    public static void main(String[] args) {
        SpringApplication.run(LectureAdminApp.class, args);
    }

}
