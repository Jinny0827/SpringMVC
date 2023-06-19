package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/*
    1. 파라미터 전송 기능
    2. http://localhost:8080/request-param?username=hello&age=20

 */
// get으로 쿼리파라미터 조회
// get은 url에 쿼리 파라미터가 같이 담겨져서 넘어온다. (보안성x)
// post로 메시지바디에 요청자가 넣은 정보를 쿼리 파라미터로 넘어오게해서 조회
// post는 쿼리파라미터의 정보를 url에서 숨긴채로 넘어온다.(보안성o)
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("전체 파라미터 조회 - start");

        // paramName은 ** 쿼리파라미터의 이름 ** , .getParameter(paramName)은 = 뒤에 붙은 ** 사용자가 입력한 값(내용) **
        request.getParameterNames().asIterator()
                        .forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName)));

        System.out.println("전체 파라미터 조회 - end");

        System.out.println();

        System.out.println("단일 파라미터 조회 - start");

        String username = request.getParameter("username");
        String age = request.getParameter("age");

        System.out.println("username = " + username);
        System.out.println("age = " + age);

        System.out.println("단일 파라미터 조회 - end");

        System.out.println();

        // 중복된 파라미터의 값도 가능(username = hello, hello2)

        System.out.println("중복된(이름이 같은) 파라미터 조회");
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("username = " + name);
        }

        // 허전한 응답을 채우기 위해 응답바디메시지 작성
        response.getWriter().write("ok");
    }
}
