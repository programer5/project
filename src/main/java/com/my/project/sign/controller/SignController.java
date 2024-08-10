package com.my.project.sign.controller;

import com.my.project.sign.dto.SignUpRequestDto;
import com.my.project.sign.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignController {

    private final SignUpService signUpService;

    @GetMapping("/api/v1/signup")
    public int signUp(SignUpRequestDto SignUpRequestDto) {
        return signUpService.signUp(SignUpRequestDto);
    }
}
