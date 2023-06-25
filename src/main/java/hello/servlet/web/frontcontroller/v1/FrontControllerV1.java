package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerV1 extends HttpServlet {

    // 매핑 정보 만들기
    // 어떤 url이 호출되어도 ControllerV1을 꺼내서 호출해라
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    // 매핑 정보를 담아둘 생성자 만들기
    public FrontControllerV1() {

        // 아래의 3가지 매핑 패턴이 오면 ControllerV1을 참조한 3가지 컨트롤러가 호출된다.
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
        
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        // /front-controller/v1/members/new-form 이 부분을 얻어 낼 수 있다.
        String requestURI = request.getRequestURI();

        // 파라미터 부분을 얻어내서 키로 사용하고 앞서 담아놓은 controllerMap 타입을 통해 값을 가져올 수 있다.
        // 값을 controllerv1 타입으로 담아서 객체 생성
        ControllerV1 controller = controllerMap.get(requestURI);

        // 클라이언트의 요청 매핑이 없는 경우 예외처리
        if(controller == null) {
            // http의 응답 처리를 404(낫 파운드 = 없다) 처리해준다.
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
            // 요청의 값이 있다면
            controller.process(request, response);
    }
}






















