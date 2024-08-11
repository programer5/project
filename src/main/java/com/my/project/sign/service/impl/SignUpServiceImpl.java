package com.my.project.sign.service.impl;

import com.my.project.exception.member.EmailDuplicateException;
import com.my.project.member.entity.Member;
import com.my.project.member.repository.MemberRepository;
import com.my.project.sign.dto.SignUpRequestDto;
import com.my.project.sign.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SignUpServiceImpl implements SignUpService {

    private final MemberRepository memberRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Long signUp(SignUpRequestDto signUpRequestDto) {
        boolean existMember = memberRepository.existsByEmail(signUpRequestDto.getEmail());

        if (existMember) {
            throw new EmailDuplicateException(signUpRequestDto.getEmail());
        }

        Member member = Member.builder()
                .email(signUpRequestDto.getEmail())
                .password(passwordEncrypt(signUpRequestDto.getPassword()))
                .build();

        Member saveMember = memberRepository.save(member);
        return saveMember.getId();
    }

    private String passwordEncrypt(String password) {
        return passwordEncoder.encode(password);
    }
}
