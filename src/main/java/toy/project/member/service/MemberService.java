package toy.project.member.service;

import toy.project.jwt.dto.JwtToken;
import toy.project.member.dto.MemberDto;
import toy.project.member.dto.SignIn;
import toy.project.member.dto.SignUp;

public interface MemberService {

    JwtToken signIn(SignIn signIn);
    MemberDto signUp(SignUp signUp);
}
