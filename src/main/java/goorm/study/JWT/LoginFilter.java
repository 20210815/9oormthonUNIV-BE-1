package goorm.study.JWT;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import goorm.study.entity.User;
import goorm.study.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Base64;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    // 로그인 처리 롤
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        User loginUser = userRepository.findById(username)
                .orElseThrow(() -> new AuthenticationServiceException("사용자를 찾을 수 없습니다."));

        // 비밀번호를 Base64로 인코딩하여 비교
        if(isPasswordValid(password, loginUser.getPassword())) {
            // 비밀번호가 일치할 경우 JWT 토큰 생성
            String accessToken = jwtUtil.createJwt("accessToken", username, 86400000L);
            String refreshToken = jwtUtil.createJwt("refreshToken", username,  86400000L);

            // 응답에 토큰을 추가하여 반환
            response.setHeader("accessToken", "Bearer " + accessToken);
            response.setHeader("refreshToken", "Bearer " + refreshToken);

            //ResponseDTO responseDTO = new ResponseDTO<>(ResponseCode.SUCCESS_LOGIN, responseApplicant);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = null;

            try {
                jsonResponse = objectMapper.writeValueAsString("로그인 성공");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            try {
                response.getWriter().write(jsonResponse);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
        else {
            throw new AuthenticationServiceException("비밀번호가 일치하지 않습니다.");
        }
    }

    // 로그인 실패 시 처리
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {

        response.setStatus(401);

        //ErrorResponseDTO responseDTO = new ErrorResponseDTO(ErrorCode.USER_NOT_FOUND);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(objectMapper);
        response.getWriter().write(jsonResponse);
    }

    public static boolean isPasswordValid(String rawPassword, String encPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // encPassword를 Base64로 디코딩하는 대신, BCrypt 해시된 비밀번호로 바로 비교
        return passwordEncoder.matches(rawPassword, encPassword);
    }
}
