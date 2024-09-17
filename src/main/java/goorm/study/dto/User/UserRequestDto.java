package goorm.study.dto.User;

import goorm.study.entity.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserRequestDto {
    private String id; //학번
    private String passwd; //비밀번호
    private String username; //이름
    private String userType; //학생, 교수, 관리자
}
