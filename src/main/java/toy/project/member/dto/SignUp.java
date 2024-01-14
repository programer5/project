package toy.project.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.project.member.domain.Member;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class SignUp {

    private String username;
    private String password;
    private String nickname;
    private String address;
    private String phone;
    private String profileImg;
    private List<String> roles = new ArrayList<>();

    public Member toEntity(String encodedPassword, List<String> roles) {

        return Member.builder()
                .username(username)
                .password(encodedPassword)
                .nickname(nickname)
                .address(address)
                .phone(phone)
                .profileImg(profileImg)
                .roles(roles)
                .build();
    }

    @Builder
    public SignUp(String username, String password, String nickname, String address, String phone, String profileImg, List<String> roles) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.address = address;
        this.phone = phone;
        this.profileImg = profileImg;
        this.roles = roles;
    }
}
