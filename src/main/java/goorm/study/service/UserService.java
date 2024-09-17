package goorm.study.service;

import goorm.study.dto.User.UserRequestDto;
import goorm.study.dto.User.UserResponseDto;
import goorm.study.entity.User;
import goorm.study.entity.UserType;
import goorm.study.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User signUp(UserRequestDto userRequestDto) {
        // type에 있는지 확인 필요
        User newUser = new User(userRequestDto.getId(), userRequestDto.getPasswd(), userRequestDto.getUsername(), UserType.valueOf(userRequestDto.getUserType()));
        userRepository.save(newUser);
        return newUser;
    }
}
