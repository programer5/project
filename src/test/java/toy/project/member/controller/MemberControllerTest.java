package toy.project.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import toy.project.common.DatabaseCleanUp;
import toy.project.jwt.dto.JwtToken;
import toy.project.member.dto.MemberDto;
import toy.project.member.dto.SignIn;
import toy.project.member.dto.SignUp;
import toy.project.member.service.MemberService;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberControllerTest {

    @Autowired
    DatabaseCleanUp databaseCleanUp;
    @Autowired
    MemberService memberService;
    @Autowired
    TestRestTemplate testRestTemplate;
    @LocalServerPort
    int randomServerPort;

    private SignUp signUp;

    @BeforeEach
    void beforeEach() {
        signUp = SignUp.builder()
                .username("member")
                .password("1234")
                .nickname("닉네임")
                .address("서울시 광진구")
                .phone("010-1234-5678")
                .build();
    }

    @AfterEach
    void afterEach() {
        databaseCleanUp.truncateAllEntity();
    }

    @Test
    void signUpTest() {
        String url = "http://localhost:" + randomServerPort + "/members/signUp";
        ResponseEntity<MemberDto> responseEntity = testRestTemplate.postForEntity(url, signUp, MemberDto.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        MemberDto body = responseEntity.getBody();
        assertThat(body.getUsername()).isEqualTo(signUp.getUsername());
        assertThat(body.getNickname()).isEqualTo(signUp.getNickname());
    }

    @Test
    void signInTest() {
        MemberDto memberDto = memberService.signUp(signUp);

        SignIn signIn = SignIn.builder()
                .username(memberDto.getUsername())
                .password(memberDto.getPassword())
                .build();

        JwtToken jwtToken = memberService.signIn(signIn);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(jwtToken.getAccessToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        log.info("httpHeaders = {}", httpHeaders);

        String url = "http://localhost:" + randomServerPort + "/members/test";
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(url, new HttpEntity<>(httpHeaders), String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(signIn.getUsername());

    }

}