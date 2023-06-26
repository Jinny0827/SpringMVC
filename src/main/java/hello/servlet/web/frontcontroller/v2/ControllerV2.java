package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// v1(버전1)과 같은 내용이다
// 요청이 왔을때 응답하는 1차 관문
// 여기서 요청에 대한 세부적인 컨트롤러를 분리해줄것이다.
public interface ControllerV2 {

    // MyView로 반환해서 JSP를 렌더링해줄 예정
    //IOExcetpion, ServeltException -> 2가지 예외를 사용할 예정
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    
}
