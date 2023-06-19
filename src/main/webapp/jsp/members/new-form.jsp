<%--
JSP에는 세팅 한줄이 꼭 필요하다.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action = "/jsp/members/save.jsp" method = "post">
    username : <input type = "text" name = "username"/>
    age : <input type = "number" name = "age"/>
    <button type = "submit">전송</button>
    <button type = "reset">다시입력</button>
</form>

</body>
</html>
