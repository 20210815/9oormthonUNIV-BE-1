package goorm.study.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "userEntity")
@Builder
public class User {

    @Id
    @Column(name = "user_id")
    private String userId; //학번

    private String password; //비밀번호

    private String username; //이름

    @Enumerated(EnumType.STRING)
    private UserType userType; //학생, 교수, 관리자


}
