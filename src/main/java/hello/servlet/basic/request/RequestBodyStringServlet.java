package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//postman 이용해서 요청을 만들어줌
//요청의 데이터 타입이 문자열
//HTTP API의 메시지 바디를 가져오기 위한 클래스
@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // HTTP API의 메시지 바디를 가져올때는 getInputStream을 사용한다
        // ServletInputStream 객체 타입으로 가져오기 때문에 문자열로 변환이 필요하다.
        // StreamUtils 라는 스프링에서 제공하는 유틸리티 사용
        // 매개변수는 (Stream 타입의 객체와 인코딩 타입)을 적시해준다.
        ServletInputStream inputStream =  request.getInputStream();
        String messageBody =  StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("ok");
        
    }
}
