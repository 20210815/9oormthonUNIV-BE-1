package goorm.study.controller;

import goorm.study.JWT.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JwtUtilTest {
    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void testGenerateToken() {
        // Given: 유효한 사용자 이름
        String username = "20210815";
        료
        // When: JWT 토큰을 생성
        String token = jwtUtil.createJwt("accessToken", username, 86400000L);

        // Then: 토큰이 null이 아니고 빈 문자열이 아님을 확인
        assertNotNull(token, "JWT 토큰이 null이어서는 안 됩니다.");
        assertFalse(token.isEmpty(), "JWT 토큰이 비어 있어서는 안 됩니다.");

        // Then: 토큰에서 사용자 이름을 추출하고, 원래의 사용자 이름과 일치하는지 확인
        String extractedUsername = jwtUtil.getUsername(token);
        assertEquals(username, extractedUsername, "토큰에서 추출한 사용자 이름이 원래 이름과 일치해야 합니다.");
    }

    @Test
    public void testValidateToken() {
        // Given: 유효한 사용자 이름
        String username = "20210815";

        // When: JWT 토큰을 생성
        String token = jwtUtil.createJwt("accessToken", username, 86400000L);

        // Then: 토큰이 유효한지 확인
        boolean isValid = jwtUtil.isExpired(token);
        assertFalse(isValid, "JWT 토큰은 유효해야 합니다.");
    }
}
