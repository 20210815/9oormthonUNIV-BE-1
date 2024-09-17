package goorm.study.controller;

import goorm.study.Exception.UserAlreadyExistsException;
import goorm.study.dto.User.UserRequestDto;
import goorm.study.entity.User;
import goorm.study.service.UserService;
import org.aspectj.lang.annotation.RequiredTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {
    @Autowired
    UserService userService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void userSignUp() throws Exception {
        UserRequestDto newUserDto = new UserRequestDto("202108151", "1234", "이규민", "STUDENT");

        assertNotNull(newUserDto, "null이 없어야 함");
        assertNotEquals(bCryptPasswordEncoder.encode(newUserDto.getPasswd()), newUserDto.getPasswd(), "암호화되지 않음");
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