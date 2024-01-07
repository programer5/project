package toy.project.member.service;

import toy.project.jwt.dto.JwtToken;

public interface MemberService {

    JwtToken signIn(String username, String password);
}
