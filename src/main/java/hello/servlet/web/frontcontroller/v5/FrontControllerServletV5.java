package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    // 어떤 핸들러(컨트롤러)가 들어오든 처리 할 수 있는 Object 타입으로 Map 생성
    private final Map<String, Object> handlerMappingMap = new HashMap<>();

    // 핸들러(컨트롤러)의 리스트 중 하나를 뽑아써야 하기 때문에 List 타입으로 생성
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    // 매핑정보를 위해 생성자 생성
    // handlerMappingMap을 사용
    // 리스트 타입 handlerAdapters에 매핑정보들을 담아준다. put();
    // ControllerV3의 기능들을 사용했기 때문에 ControllerV3HandlerAdapter를 매개변수로 삼는다.
    // ControllerV3HandlerAdapter는 실제 클래스 구현이 아닌 ControllerV3의 기능들을 검증 후 참조해서 사용한다.
    public FrontControllerServletV5() {
        // 요청자의 경로정보들을 미리 담아서 매핑한 정보들
        initHandlerMappingMap();

        // 요청자의 경로정보들을 검증한 객체 목록들을 담은 ModelView 객체 목록들
        initHandlerAdapters();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        // 반환 타입이 ControllerV3가 아닌 Object로 반환
        // requestURI의 키값이 /front-controller/v3/members/new-form 이면
        // value값은 new MemberFormControllerV3()로 반환
        Object handler = handlerMappingMap.get(requestURI);



    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }
}
