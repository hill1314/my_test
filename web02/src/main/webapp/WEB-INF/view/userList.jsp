<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/10
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title>userList</title>
</head>

<body>
    <div><%=request.getContextPath()%></div>
    <table>
        <thead>
        <tr><th>id</th><th>name</th></tr>
        </thead>
        <tbody>
            <c:forEach items="${userList}" var="user">
            <tr> <td>${user.userId}</td> <td>${user.userName}</td>
                <td><a href="<%=request.getContextPath()%>/user/getUser?userId=${user.userId}">userDetail</a></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</body>

<script>

</script>

</html>
