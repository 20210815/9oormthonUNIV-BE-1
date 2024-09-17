package goorm.study.controller;

import goorm.code.ErrorCode;
import goorm.code.ResponseCode;
import goorm.study.Exception.UserAlreadyExistsException;
import goorm.study.dto.User.UserRequestDto;
import goorm.study.dto.response.ErrorResponseDTO;
import goorm.study.dto.response.ResponseDTO;
import goorm.study.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Validated
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO<?>> userSignUp(@Valid @RequestBody UserRequestDto userRequestDto){
            userService.signUp(userRequestDto);
            return ResponseEntity
                    .status(ResponseCode.SUCCESS_SIGNUP.getStatus().value())
                    .body(new ResponseDTO<>(ResponseCode.SUCCESS_SIGNUP, userRequestDto.getUsername() + "의 회원가입이 성공적으로 이루어졌습니다."));
    }
}
