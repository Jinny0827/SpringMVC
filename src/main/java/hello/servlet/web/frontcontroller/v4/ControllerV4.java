package hello.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {

    // HTTP 요청이 왔을때 프론트컨트롤러가 받아들여서 기능 별로 컨트롤러를 나누기 위해서 사용
    // 각 기능별 컨트롤러가 사용할 메서드
    // 여기는 컨트롤러들의 집합체
    // 논리적 경로와 모델(데이터)를 동시에 반환하기 위해서 매개변수 사용
    // paramMap 은 정해놓은 경로가 들어왔을때 연결시켜줄 컨트롤러 경로
    // model은 request.getParameter()로 요청자가 요청시 보낸 값, 서버가 응답할때 넣어줄 모델(데이터) 객체

    // 주석으로 메서드에 대한 매개변수 이름, return 값을 적어주고 싶을땐
    // /** + 엔터
    /**
     *
     * @param paramMap
     * @param model
     * @return viewName(논리경로명)
     */
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
