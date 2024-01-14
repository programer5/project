package toy.project.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class SignIn {
    private String username;
    private String password;

    @Builder
    public SignIn(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
