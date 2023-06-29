package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {

        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 비지니스 로직
        System.out.println("FrontControllerServletV3.service");

        //url 값을 받아서 객체화 시킨다.
        String requestURI = request.getRequestURI();

        // 객체화시킨 URI값이 controller에 있는 값인지 비교해본다.
        // 비교과정을 위해 Controllermap을 ControllerV3 타입으로 객체 생성

        ControllerV3 controller = controllerMap.get(requestURI);

        if(controller == null) {

            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 요청 url값이 있다면?
        // ModelView를 통해서 렌더링과 모델을 넘겨주는 과정이 필요하기 때문에...
        // paramMap을 통해 파라미터값을 다 꺼내오고 paramMap에 데이터를 다 집어넣는다.

        Map<String, String> paramMap = createParamMap(request);

        // process가 Map타입 매개변수이고 그것을 ModelView 타입 객체로 생성
        // 요청 정보 - 경로와 데이터를 저장
        ModelView mv = controller.process(paramMap);

        // view를 해결해주는 viewResolver 생성
        // 요청자의 요청 경로를 논리적으로 가져온다.
        // 경로와 데이터중 경로에 대한 정보를 객체화
        String viewName = mv.getViewName();

        // 물리적으로 요청 경로를 변환
        // /WEB-INF/views/new-form.jsp -> 물리적 완성본
        // 로직의 레벨을 맞추기 위해서 extracting method를 통해 세부 로직을 메서드화 시켜준다.
        MyView view = viewResolver(viewName);

        // 렌더링을 위해 보낸다.
        view.render(mv.getModel(), request, response);


    }

    private MyView viewResolver(String viewName) {
        MyView view = new MyView("/WEB-INF/views/" + viewName + ".jsp");
        return view;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
