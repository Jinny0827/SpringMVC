package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        // 위에서 boolean 타입으로 검증 후 true로 내려왔기 때문에 강제타입변환가능
        // handler가 ControllerV3 타입의 기능이랑 일치한다.
        ControllerV3 controller = (ControllerV3) handler;

        // 파라미터가 Map 타입이 필요하다.
        // V3 패키지 안에 frontController에서 createParamMap을 가져온다.
        Map<String, String> paramMap = createParamMap(request);

        // 반환타입인 ModelView에 대입해서 경로명 반환
        // 현재 경로명은 Map 타입으로 ("경로명", request.getParameter -> 경로명)으로 지정
        // ModelView에서 경로와 모델(데이터)를 반환해주기 때문에 여기서 파라미터(경로)를 반환해준다.
        // ControllerV3에서 해당하는 핸들러(컨트롤러)를 호출
        ModelView mv = controller.process(paramMap);

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
