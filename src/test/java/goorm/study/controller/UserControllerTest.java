package goorm.study.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import goorm.study.Exception.UserAlreadyExistsException;
import goorm.study.dto.User.UserRequestDto;
import goorm.study.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    UserService userService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper; // JSON 직렬화에 사용

    @Test
    void userSignUp() throws Exception {
        UserRequestDto newUserDto = new UserRequestDto("202108151", "1234", "이규민", "STUDENT");

        assertNotNull(newUserDto, "null이 없어야 함");
        assertNotEquals(bCryptPasswordEncoder.encode(newUserDto.getPassword()), newUserDto.getPassword(), "암호화되지 않음");
        assertSame("STUDENT", newUserDto.getUserType(), "존재하는 타입이 없음");
    }

    @Test
    void userSignUpException() throws Exception {
        UserRequestDto newUserDto = new UserRequestDto("202108151", "1234", "이규민", "STUDENT");
        userService.signUp(newUserDto);

        assertThrows(UserAlreadyExistsException.class, () -> {
            userService.signUp(newUserDto);
        }, "이미 존재하는 유저입니다.");

    }


}