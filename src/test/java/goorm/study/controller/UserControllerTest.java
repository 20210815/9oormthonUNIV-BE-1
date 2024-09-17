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
        userService.signUp(new UserRequestDto("20210815", "1234", "이규민", "STUDENT"));
        System.out.println("회원가입 성공");
    }
}