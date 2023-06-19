package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

//getter, setter로 DTO지정
@Getter
@Setter
public class Member {

    //id는 자동 증가하는 인트값(시퀀스, 오토인크리먼트), username과 age는 사용자가 입력해야하는 값
    private Long id;
    private String username;
    private int age;

    //기본 생성자
    public Member() {}

    //사용자의 이름과 나이를 지정하는 생성자(메서드)
    public Member(String username, int age){
        this.username = username;
        this.age = age;
    }
}
