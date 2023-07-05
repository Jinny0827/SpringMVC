package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

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

        // 핸들러(컨트롤러)를 찾는 로직이네
        // 요청에 맞는 핸들러!
        Object handler = getHandler(request);

        if(handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 핸들러 어댑터를 통해 핸들러와 매칭시켜주는 것이네!
        // 매칭이 되서 V3 컨트롤러의 기능컨트롤러가 있다면 adapter값으로 반환!
        // 없다면 IllegalException을 통해 예외 반환!
        // 핸들러 어댑터 찾아와
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        // 모델뷰 반환을 위해 adapter 객체를 통해
        // handle 메서드 호출
        ModelView mv = adapter.handle(request, response, handler);

        // 논리경로명을 물리경로명(.jsp)로 만들기 위해서
        // viewResolver 메서드를 가져와야한다.
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        // MyView를 통해서 렌더링
        // 렌더링을 위한 render 메서드 완성
        // getModel()을 통해 렌더링시 데이터도 담아서 렌더링
        view.render(mv.getModel(), request, response);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        // 요청자의 url 값을 가져와서
        // 핸들러 어댑터에 있는 값인지 확인한다.
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if(adapter.supports(handler)) {
                // 핸들러(컨트롤러)를 지원할 수 있다면
                // 참인 값을 a에 담아준다.
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler-Adpater를 찾을 수 없습니다." + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();

        // 반환 타입이 ControllerV3가 아닌 Object로 반환
        // requestURI의 키값이 /front-controller/v3/members/new-form 이면
        // value값은 new MemberFormControllerV3()로 반환
       return handlerMappingMap.get(requestURI);
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        //v3
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        //v4
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private MyView viewResolver(String viewName) {
        MyView view = new MyView("/WEB-INF/views/" + viewName + ".jsp");
        return view;
    }
}
