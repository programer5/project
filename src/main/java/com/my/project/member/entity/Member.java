package com.my.project.member.entity;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Member {

    private String email;
    private String password;
    private String role;

    @Builder
    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
