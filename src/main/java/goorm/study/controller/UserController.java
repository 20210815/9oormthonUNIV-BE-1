package goorm.study.controller;

import goorm.study.dto.User.UserRequestDto;
import goorm.study.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public String userSignUp(@RequestBody UserRequestDto userRequestDto) {
        userService.signUp(userRequestDto);
        return "회원가입 성공";
    }
}
