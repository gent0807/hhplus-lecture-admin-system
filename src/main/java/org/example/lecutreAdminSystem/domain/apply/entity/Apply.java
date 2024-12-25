package org.example.lecutreAdminSystem.domain.apply.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Apply {

    @Id
    private long id;

    public boolean validate(){
        return true;
    }
}
