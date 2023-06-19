package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    // 싱글톤으로 생성되었으므로 사용가능한 메서드를 이용해서 객체 생성 - 기본생성자 -> private으로 접근 제한해놓음
    private MemberRepository memberRepository = MemberRepository.getInstance();
    

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("MemberSaveServlet.service");
        //getParameter로 요청값을 꺼내서 변수에 대입
        String username = request.getParameter("username");
        // 넘어오는 요청값은 문자열(String)이지만 나이는 정수형이므로 int로 강제 타입변환
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        // 응답을 위한 html 코드를 내려본다.
        // html로 세팅, utf-8로 인코딩세팅
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        // 직접 메시지바디에 적어서 응답(html 형식으로)
        PrintWriter w = response.getWriter();
        w.write("<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                "    <li>id="+member.getId()+"</li>\n" +
                "    <li>username="+member.getUsername()+"</li>\n" +
                "    <li>age="+member.getAge()+"</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");
    }
}
