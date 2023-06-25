package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV1 implements ControllerV1 {

    //사용을 위한 객체 생성
    private MemberRepository memberRepository = MemberRepository.getInstance();
    
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 클라이언트의 요청 값(회원 정보)을 DTO인 member에 저장한다.
        String username = request.getParameter("username");
        // 넘어오는 요청값은 문자열(String)이지만 나이는 정수형이므로 int로 강제 타입변환
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        // Model에 데이터를 보관하고 클라이언트 필요시 view에 쏴준다. "member" 사용
        // 스프링에서 데이터 앞단에 쏴준것
        request.setAttribute("member", member);

        // 클라이언트 요청에 따라 이동하기 위한 경로 지정을 위한 객체 생성
        // 스프링에서 return 에 주소박은거라고 생각하면 된다.
        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }
}
