package toy.project.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.project.jwt.dto.JwtToken;
import toy.project.member.dto.MemberDto;
import toy.project.member.dto.SignIn;
import toy.project.member.dto.SignUp;
import toy.project.member.service.MemberService;
import toy.project.security.util.SecurityUtil;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signIn")
    public JwtToken signIn(@RequestBody @Valid SignIn signIn) {
        JwtToken jwtToken = memberService.signIn(signIn);
        log.info("request username = {}, password = {}", signIn.getUsername(), signIn.getPassword());
        log.info("jwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(), jwtToken.getRefreshToken());
        return jwtToken;
    }

    @PostMapping("/signUp")
    public ResponseEntity<MemberDto> signUp(@RequestBody @Valid SignUp signUp) {
        MemberDto memberDto = memberService.signUp(signUp);
        return ResponseEntity.ok(memberDto);
    }

    @PostMapping("/test")
    public String test() {
        return SecurityUtil.getCurrentUsername();
    }
}
