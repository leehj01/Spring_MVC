package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    private Long id; // 회원 리파짓토리에 저장할 예정
    private String username;
    private int age;

    public Member(){

    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
