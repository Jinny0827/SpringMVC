package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import org.springframework.ui.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        // 핸들러가 ControllerV4의 기능을 지원하는가?
        return (handler instanceof ControllerV4);
    }

    // 기능을 지원한다면 로직이 흘러내려온다.
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        // 기능 지원 검증이 완료되었으므로 타입 변환이 가능하다.
        ControllerV4 controller = (ControllerV4) handler;

        // 컨트롤러v4 기능을 수행하기 위해서 process()를 호출
        // process()의 매개변수로 paramMap과 Model 매개변수가 존재하므로 생성해준다.

        // paramMap은 v3handlerAdapter를 통해 가져온다.
        Map<String, String> paramMap = createParamMap(request);

        // model은 HashMap<>();을 호출하여 만든다.
        HashMap<String, Object> model = new HashMap<>();

        // 매개변수 값을 정의해줬다.
        // 반환 값이 ModelView 객체이므로 만들어준다.
        String viewName = controller.process(paramMap, model);

        // 어댑터를 통해서 ModelView 객체를 반환한다.
        // 논리경로명과 데이터(Model)을 직접 지정해주고 반환
        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        // 파라미터로 넘어온 값인 paramName을 키값으로..
        // paramName에 해당하는 파라미터값을 value값으로 지정
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
