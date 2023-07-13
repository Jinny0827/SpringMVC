package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/*@Component
@RequestMapping*/
// 매핑정보를 입력시키기 위해선 스프링 빈 처리가 필요하다
// @Component 어노테이션이 필요한데
// @Controller 어노테이션 안에 @Component 가 달려있기 때문에 빈처리가 된거고 매핑처리도 가능한것이다.
@Controller
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        System.out.println("SpringMVC 패턴으로 만든 멤버폼");
        return new ModelAndView("new-form");
    }

}
