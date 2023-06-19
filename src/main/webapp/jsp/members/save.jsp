<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Member 클래스와 MemberRepository 클래스 사용을 위해 import 해줘야한다. --%>
<%@ page import = "hello.servlet.domain.member.Member" %>
<%@ page import = "hello.servlet.domain.member.MemberRepository" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("MemberSaveServlet.service");
    //getParameter로 요청값을 꺼내서 변수에 대입
    String username = request.getParameter("username");
    // 넘어오는 요청값은 문자열(String)이지만 나이는 정수형이므로 int로 강제 타입변환
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);

%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>성공</h2>
    <ul>
        <li>id = <%=member.getId()%></li>
        <li>username = <%=member.getUsername()%></li>
        <li>age= <%=member.getAge()%></li>
    </ul>
    <a href = "/index.html">메인</a>

</body>
</html>
