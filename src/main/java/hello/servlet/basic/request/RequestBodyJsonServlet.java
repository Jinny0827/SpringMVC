package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
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
//요청의 데이터 타입이 json타입
@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // JSON 데이터 파싱해서 request 요청 데이터 조회 방법1
        ServletInputStream inputStream = request.getInputStream();

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody);

        // JSON 데이터 파싱해서 request 요청 데이터 조회 방법2
        // 이 방법은 DTO 클래스와 매칭해서 어떤 데이터가 메시지 바디를 통해 넘어왔는지 확인하는 방법이다.
        // HelloData 클래스 객체 생성
        // 생성된 객체 안 필드 가져오기
        // 여기서 생성된 객체는 메시지 바디안에 적히는 json타입의 값
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        System.out.println("HelloData.username = " + helloData.getUsername());
        System.out.println("HelloData.age = " + helloData.getAge());
        
        response.getWriter().write("ok");
    }
}
