package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printStartLine(request);

        System.out.println("---------------------------------------------------------------");

        //Header의 전체 내용 조회
        printHeaders(request);

        //Header들을 편리하게 각각 조회
        printHeaderUtils(request);
        
        //Header의 기타 정보
        printHeaderETC(request);
    }

    private static void printHeaderETC(HttpServletRequest request) {
        System.out.println("--- 기타 조회 start ---");

        System.out.println("[Remote 정보]");
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost()); //
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr()); //
        System.out.println("request.getRemotePort() = " + request.getRemotePort()); //
        System.out.println();

        System.out.println("[Local 정보]");
        System.out.println("request.getLocalName() = " + request.getLocalName()); //
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr()); //
        System.out.println("request.getLocalPort() = " + request.getLocalPort()); //

        System.out.println("--- 기타 조회 end ---");
        System.out.println();
    }

    private static void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header 편의 조회 start ---");
        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " + request.getServerName()); //Host 헤더
        System.out.println("request.getServerPort() = " + request.getServerPort()); //Host 헤더
        System.out.println();

        System.out.println("[Accept-Language 편의 조회]");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));
        System.out.println("request.getLocale() = " + request.getLocale());
        System.out.println();

        System.out.println("[cookie 편의 조회]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();

        System.out.println("[Content 편의 조회]");
        System.out.println("request.getContentType() = " + request.getContentType());
        System.out.println("request.getContentLength() = " + request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());

        System.out.println("--- Header 편의 조회 end ---");
        System.out.println();
    }

    private static void printHeaders(HttpServletRequest request) {

        //옛날 방식으로 request안의 Header 내용 확인
      /*  Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + " : " + headerName);
        }*/

        // asIterator와 람다식을 이용한 request Header 내용 확인
        // asIterator - 컬렉션 프레임 워크에 저장된 요소를 읽어오는 방법
        // forEachRemaining은 모든 요소가 처리되거나 적업에서 예외가 발생할 때까지 남은 각 요소에 대해 지정된 작업을 수행합니다.
        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println("headerName" + headerName));

    }

    // HttpServletRequest의 내용 전부 출력
    private static void printStartLine(HttpServletRequest request) {
        System.out.println("request.getMethod = " + request.getMethod());
        //GET 메서드

        System.out.println("request.getProtocol = " + request.getProtocol());
        // HTTP/1.1 

        System.out.println("request.getScheme = " + request.getScheme());
        // http 스키마이름

        System.out.println("request.getRequestURL = " + request.getRequestURL());
        // http://locolhost:8080/request-header 전체 URL

        System.out.println("request.getRequestURI = " + request.getRequestURI());
        // /request-test
        
        System.out.println("request.getQueryString = " + request.getQueryString());
        // username=hi url 뒤에 붙은 쿼리스트링(url뒤에 붙은 ?~~~~ 내용)

        System.out.println("request.isSecure() = " + request.isSecure());
        // https 사용 유무
    }
    

}
