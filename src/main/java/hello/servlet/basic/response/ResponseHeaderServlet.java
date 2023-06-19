package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    // response의 기본 사용법
    // 응답 (메시지)코드 생성, 헤더 생성, 바디 생성
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 응답 코드(매직 넘버 = 의미있는 숫자) 셋팅
        // 매개변수 안에 200, 300 ... 이런식으로 적을 수 있지만..
        // HttpServletResponse. 을 하고 SC_ 뒤에 적힌 코드와 단어를 매칭시켜서 숙지하자
        response.setStatus(HttpServletResponse.SC_OK);
        
        // response-header (헤더 생성) 의 데이터 셋팅
        // request의 헤더 생성처럼 컨텐트 타입(text/plain)과 Cache-Control(캐쉬데이터를 어떻게 사용할지) 키 , 값으로 조절
        // content-type에 charset=utf-8을 안맞추면 ISO로 엔코딩타입이 지정되는데 이렇게 되면 나중에 한글이 깨질 수 있다.
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        // 캐쉬를 완전히 무효화
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma","no-cache");
        // 내가 원하는 헤더를 셋팅해서 응답헤더메시지에 같이 보낼 수 있다.
        response.setHeader("my-header", "hello");

        // 헤더의 편의 메서드 사용
        content(response);

        // 쿠키 정보를 추가하는 편의 메서드
        cookie(response);

        // Redirect 관련 편의 메서드
        redirect(response);

        // 메시지 바디 생성(문자열 ok)
        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }

    private static void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html

        //response.setStatus(HttpServletResponse.SC_FOUND); -> 302
        //response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }

    private static void cookie(HttpServletResponse response) {

        //(setHeader) Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); // 생명주기 600초
        response.addCookie(cookie); //응답에 쿠키정보 추가

    }

    private static void content(HttpServletResponse response) {

        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        // Content-Length 는 생략해도 알아서 계산해서 응답메시지에 적혀서 응답한다.
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");

        // **** 위에서 setHeader로 컨텐트타입, 인코딩 타입을 2줄로 압축해서 사용가능하다
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        // response.setContentLength(2); //(생략시 자동 생성)

    }
}
