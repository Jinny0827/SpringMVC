package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    // 회원 정보 저장을 위한 로직을 가져오기 위해
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        // 회원정보 저장을 위해 member 객체를 불러와 값을 파라미터에 대입시키고
        // memberRepository를 가져와서 회원정보 저장
        Member member = new Member(username, age);
        memberRepository.save(member);

        // 경로와 데이터를 같이 넘겨주기 위해 ModelView 객체 생성하여
        // 경로는 "save-result", 데이터는 getModel()에 put()해서 데이터를 보내준다.
        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);

        return mv;
    }
}
