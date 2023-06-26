package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // dispatcher 객체를 생성하지 않고 직접 반환
        // dispatcher 객체는 MyView에 저장되어있다.

        // MyView myView = new MyView("/WEB-INF/views/new-form.jsp");
        // 조금 더 줄여볼 수 있다.
        // myView 끝에 커서를 두고 인라인 사용(ctrl+alt+n)

        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}
