package goorm.study.dto.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserRequestDto {
    @NotNull(message = "아이디는 비워둘 수 없습니다.")
    @JsonProperty(value = "userId", required = true)
    private String userId; //학번

    @NotNull(message = "비밀번호는 비워둘 수 없습니다.")
    @JsonProperty(value = "password", required = true)
    private String password; //비밀번호

    @NotNull(message = "이름은 비워둘 수 없습니다.")
    @JsonProperty(value = "username", required = true)
    private String username; //이름

    @NotNull(message = "유형은 비워둘 수 없습니다.")
    @JsonProperty(value = "userType", required = true)
    private String userType; //학생, 교수, 관리자
}
