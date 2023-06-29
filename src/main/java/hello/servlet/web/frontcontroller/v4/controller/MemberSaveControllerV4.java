package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        // 요청자의 값을 가져온다. - 이름, 나이
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        // 가져온 값을 Member 타입으로 넣어 객체 생성
        // member값을 저장하는 로직을 가진 MemberRepository를 사용해서 저장
        Member member = new Member(username, age);
        memberRepository.save(member);

        model.put("member",member);
        return "save-result";

    }
}
