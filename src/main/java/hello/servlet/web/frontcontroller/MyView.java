package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
}
