package goorm.study.controller;

import goorm.study.dto.User.UserRequestDto;
import goorm.study.entity.User;
import goorm.study.service.UserService;
import org.aspectj.lang.annotation.RequiredTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {
    @Autowired
    UserService userService;

    @Test
    void userSignUp() {
        UserRequestDto newUser = new UserRequestDto("20210815", "1234", "이규민", "STUDENT");

        User savedUser = userService.signUp(newUser);

        assertNotNull(savedUser, "null이 없어야 함");
        assertEquals("20210815", savedUser.getUserId(), "동일한 유저 아이디 사용 불가");
        assertSame("STUDENT", savedUser.getUserType().name(), "존재하는 타입이 없음");
    }
}