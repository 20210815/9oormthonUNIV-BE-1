package goorm.study.service;

import goorm.study.Exception.UserAlreadyExistsException;
import goorm.study.dto.User.UserRequestDto;
import goorm.study.dto.User.UserResponseDto;
import goorm.study.entity.User;
import goorm.study.entity.UserType;
import goorm.study.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public void signUp(UserRequestDto userRequestDto){
        // type에 있는지 확인 필요

        if (userRequestDto.getUserId() == null) {
            throw new IllegalArgumentException("아이디를 비워둘 수 없습니다.");
        }

        // 동일한 아이디 회원가입 불가 처리
        if (userRepository.existsById(userRequestDto.getUserId())) {
            throw new UserAlreadyExistsException("이미 존재하는 회원입니다.");
        }
        // 비밀번호 암호화
        User newUser = new User(userRequestDto.getUserId(),bCryptPasswordEncoder.encode(userRequestDto.getPassword()), userRequestDto.getUsername(), UserType.valueOf(userRequestDto.getUserType()));
        userRepository.save(newUser);
    }
}
