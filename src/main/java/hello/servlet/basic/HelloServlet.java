package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 서블릿 요청 응답을 시험해본 것
@WebServlet(name = "helloservlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        // 요청에 딸려오는 쿼리파라미터 설정(?username=~~~)
        // 쿼리파라미터에 대한 내용을 콘솔에 찍어본다.
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        // 응답을 위한 셋팅
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("hello " + username);

    }
}
