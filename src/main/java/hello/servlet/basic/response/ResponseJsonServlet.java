package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    //객체 사용 위해서 ObjectMapper 사용 객체 -> 문자 변환
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // HTML이나 텍스트 메시지 응답시엔 text/html 혹은 text/plain
        //Content-Type : application/json
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("jeong");
        helloData.setAge(30);

        //{"username":"jeong", "age":30}
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);


    }
}
