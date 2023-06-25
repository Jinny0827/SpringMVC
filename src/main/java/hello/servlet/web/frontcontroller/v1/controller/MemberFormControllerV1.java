package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 멤버 폼을 불러오기 위한 컨트롤러
// 문제 해결을 통해 ControllerV1 안에있는 메서드를(오버라이드) 가져온다.
// Alt + Enter
public class MemberFormControllerV1 implements ControllerV1 {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // jsp로 이동하기 위한 경로
        // URL에 적히지 않고 jsp로 바로 넘어간다.
        // localhost:8080/servlet-mvc/members/new-form 바로 이렇게 넘어감
        String viewPath = "/WEB-INF/views/new-form.jsp";

        // 요청에 응답을 위한 경로 지정을 위한 객체 생성
        // 클라이언트로부터 최초에 들어온 요청을 JSP/Servlet 내에서 원하는 자원으로 요청을 넘기는(보내는) 역할을 수행하거나,
        // 특정 자원에 처리를 요청하고 처리 결과를 얻어오는 기능을 수행
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);

        // dispatcher 객체를 이용하여 request 요청, response 응답 해준다.
        dispatcher.forward(request, response);
    }
}
