package com.my.project.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 255, nullable = false, unique = true)
    private String password;

    @Column(length = 50, nullable = false)
    private String role;

    @Builder
    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static Member setEmailAndRole(String email, String role) {
        Member member = new Member();
        member.email = email;
        member.role = role;
        return member;
    }

}
