package toy.project.member.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.project.jwt.JwtTokenProvider;
import toy.project.jwt.dto.JwtToken;
import toy.project.member.dto.MemberDto;
import toy.project.member.dto.SignIn;
import toy.project.member.dto.SignUp;
import toy.project.member.repository.MemberRepository;
import toy.project.member.service.MemberService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public JwtToken signIn(SignIn signIn) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(signIn.getUsername(), signIn.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);

        return jwtToken;
    }

    @Transactional
    @Override
    public MemberDto signUp(SignUp signUp) {

        memberRepository.existsByUsername(signUp.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("이미 사용 중인 사용자 이름입니다."));

        String encodePassword = passwordEncoder.encode(signUp.getPassword());
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        return MemberDto.toDto(memberRepository.save(signUp.toEntity(encodePassword, roles)));
    }
}
