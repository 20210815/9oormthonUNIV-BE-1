package goorm.study.controller;

import goorm.study.dto.User.UserRequestDto;
import goorm.study.entity.User;
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
    public User userSignUp(@RequestBody UserRequestDto userRequestDto) {
        return userService.signUp(userRequestDto);
    }
}
