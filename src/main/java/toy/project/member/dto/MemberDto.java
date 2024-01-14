package toy.project.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.project.member.domain.Member;

@Getter
@NoArgsConstructor
public class MemberDto {

    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String address;
    private String phone;
    private String profileImg;

    @Builder
    public MemberDto(Long id, String username, String password, String nickname, String address, String phone, String profileImg) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.address = address;
        this.phone = phone;
        this.profileImg = profileImg;
    }

    static public MemberDto toDto(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .password(member.getPassword())
                .username(member.getUsername())
                .nickname(member.getNickname())
                .address(member.getAddress())
                .phone(member.getPhone())
                .profileImg(member.getProfileImg()).build();
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .username(username)
                .nickname(nickname)
                .address(address)
                .phone(phone)
                .profileImg(profileImg)
                .build();
    }
}
