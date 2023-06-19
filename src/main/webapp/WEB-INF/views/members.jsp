<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href = "/index.html">메인</a>
<table>
  <thead>
  <th>id</th>
  <th>username</th>
  <th>age</th>
  </thead>
  <%-- for루프를 통해 tr안의 td를 통해서 동적인 세부내용을 뿌려줘야한다 --%>
  <tbody>
  <c:forEach var = "members" items = "${members}">
    <tr>
      <td>${members.id}</td>
      <td>${members.username}</td>
      <td>${members.age}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>

</body>
</html>
