package hello.servlet;

import hello.servlet.web.springmvc.v1.SpringMemberFormControllerV1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@ServletComponentScan // 서블릿 자동 등록


@SpringBootApplication
public class ServletApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}

	// 직접 Bean 처리해줘도 된다.
	// Bean 처리하는 이유는 스프링 컨테이너 안에 객체로 저장하기 위해서
	// 그리고 캡슐화를 통해 보안을 높이기 위해서 - 밖에서 안의 정보를 수정할 수 없게
	// 스프링의 역주입을 생각하면 된다. - 스프링이 시키는대로 하는 개발자
	/*@Bean
	SpringMemberFormControllerV1 springMemberFormControllerV1() {
		return new SpringMemberFormControllerV1();
	}*/

}
