package hello.servlet.web.frontcontroller.v4;


import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// HTTP 요청이 들어와서 처음 만나는 곳
// 기능별로 컨트롤러에 보내주는 곳
// 요청별 응답을 지정해주는 곳
// Servlet 이므로 WebServlet 과 HttpServlet 사용
@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    // 기능별 컨트롤러 분기를 위한 로직 구성
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        // 회원가입 form 응답기능
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        // 회원가입 회원 저장기능
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        // 회원목록 불러오기 기능
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //로그용
        System.out.println("FrontControllerV4.service");

        //요청자의 요청 url 검증
        // 요청자 URL 객체 저장
        String requestURI = request.getRequestURI();

        // controllerMap에 요청자 url 객체 저장
        // 객체의 타입은 ControllerV4
        // controllerMap 의 키는 String, 값은 ControllerV4 였기 때문에
        // 값에 대입해서 객체 생성한다.
        ControllerV4 controller = controllerMap.get(requestURI);

        // 사용자 url이 controllerMap에 저장되어있는 URL값과 같은지 비교
        if(controller == null) {

            // 등록된 url이 아닐 경우 404 에러를 뿌려준다.
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        
        // 등록된 url이 맞다면
        // url 경로와 모델(데이터)를 담아서 넘겨줄 준비
        Map<String, String> paramMap =  createParamMap(request);
        Map<String, Object> model = new HashMap<>(); // 추가된 부분

        // 논리적 경로 이름 (new-form)
        String viewName = controller.process(paramMap, model);

        // 논리적 경로 이름을 이용하여 물리적 경로이름으로 변경
        MyView view = viewReslover(viewName);

        // 물리적 경로로 렌더링할때 model(데이터)도 같이 들어가면서 렌더링
        view.render(model, request, response);


    }

    private MyView viewReslover(String viewName) {
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
