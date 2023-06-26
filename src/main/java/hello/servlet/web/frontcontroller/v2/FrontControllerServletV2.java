package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {

        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 로그용
        System.out.println("FrontControllerServletV2.service");

        // 요청의 url값을 받아서 객체화시킨다.
        String requestURI = request.getRequestURI();

        // url값을 controllerMap의 키값에 해당시켜본다.
        ControllerV2 controller = controllerMap.get(requestURI);

        //검증
        if(controller == null) {
            // url값이 저장된것이 아니라면?
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 요청의 url이 존재한다면?
        // MyView에서 렌더링 시키기 위해서 객체 생성
        // Exception을 사용해서 MyView의 예외처리할 경우 에러가 난다.
        // -> try, catch를 사용해서 예외처리를 또해야할수도 있다.
        MyView view = controller.process(request, response);
        view.render(request, response);

    }
}
