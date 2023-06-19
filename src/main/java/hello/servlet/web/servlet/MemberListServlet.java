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
import java.util.List;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {

    // MemberRepository 객체 생성 -> 회원(Member)의 저장과 조회를 위한 클래스
    // 싱글톤으로 생성해놨기 때문에 생성자로 직접 객체 생성 불가
    // public 접근제한자로 설정해놓은 메서드를 통해서 생성
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // java.util.List
        // 회원 목록 전체를 뽑아내는 members 객체 생성 - findAll()
        List<Member> members = memberRepository.findAll();

        // 메시지 바디에 응답을 위한 세팅 - html, utf-8
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        w.write("<html>");
        w.write("<head>");
        w.write("    <meta charset=\"UTF-8\">");
        w.write("    <title>Title</title>");
        w.write("</head>");
        w.write("<body>");
        w.write("<a href=\"/index.html\">메인</a>");
        w.write("<table>");
        w.write("    <thead>");
        w.write("    <th>id</th>");
        w.write("    <th>username</th>");
        w.write("    <th>age</th>");
        w.write("    </thead>");
        w.write("    <tbody>");
        // JSP나 HTML이 아닌 메시지 바디에 직접적으로 쏴주는 자바 클래스이기 때문에
        // forEach(jstl)이 아닌 for문으로 직접 반복 응답 출력 가능
        for(Member member : members) {
            w.write("    <tr>");
            w.write("        <td>"+member.getId()+"</td>");
            w.write("        <td>"+member.getUsername()+"</td>");
            w.write("        <td>"+member.getAge()+"</td>");
            w.write("    </tr>");
        }

        w.write("    </tbody>");
        w.write("</table>");
        w.write("</body>");
        w.write("</html>");
    }

}
