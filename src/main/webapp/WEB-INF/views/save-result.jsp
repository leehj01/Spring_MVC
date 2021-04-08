<%@ page import="hello.servlet.domain.member.Member" %><%--
  Created by IntelliJ IDEA.
  User: hwajeong
  Date: 08/04/2021
  Time: 3:56 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
<%--    <li>id=<%=((Member)request.getAttribute("member")).getId()%></li>--%>
    <li>id=${member.id}</li> <%-- 위에랑 같은 코드이다  엄청 간단해 졌다. --%>
    <li>username=${member.username}</li>
    <li>age=${memeber.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
