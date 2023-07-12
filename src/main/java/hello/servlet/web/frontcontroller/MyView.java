package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {

    private String viewPath;

    // 생성자 생성(기본 생성자는 자동으로 생성된다)
    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    // 렌더링을 위한 메서드 생성(객체)
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 렌더링 하는 로직
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }

    // 렌더링 시 모델 값도 같이 넘겨준다.
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {

        // 일단 Model에 담겨있는 값을 다 꺼내야한다.
        // 요청(Attribute)에 model의 값을 다 담아놓는다.
        modelToRequestAttribute(model, request);

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}
