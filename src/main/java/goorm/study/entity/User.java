package groom.study.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
    @Id
    @Column
    private String id; //학번

    private String passwd; //비밀번호

    @Enumerated(EnumType.STRING)
    private UserType userType; //학생, 교수, 관리자

    
}
