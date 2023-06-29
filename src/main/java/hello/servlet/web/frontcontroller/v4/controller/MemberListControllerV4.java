package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        // 저장한 model값을 memberRepository에서 가져와서 List 타입으로 뿌려준다.
        // 회원 ***전체 목 록*** 이기때문에 List 타입
        List<Member> members = memberRepository.findAll();

        // jsp에 모델값을 뿌리기 위해 memberRepository에 담긴 값을 담아준다.
        model.put("members", members);

        return "members";
    }
}
