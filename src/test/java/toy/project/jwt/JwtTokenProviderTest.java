package toy.project.jwt;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import toy.project.jwt.dto.JwtToken;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@SpringBootTest
class JwtTokenProviderTest {

    @Value("${custom.jwt.secretKey}")
    private String secretKey;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private Authentication authentication;

    @Test
    void secretKeyExist() {
        assertThat(secretKey).isNotNull();
    }

    @Test
    @DisplayName("멤버 정보를 가지고 AccessToken, RefreshToken 생성")
    @WithMockUser()
    void createToken() {
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);
        String accessToken = jwtToken.getAccessToken();
        String refreshToken = jwtToken.getRefreshToken();
        log.info("accessToken ={}", accessToken);
        log.info("refreshToken ={}", refreshToken);
    }

}