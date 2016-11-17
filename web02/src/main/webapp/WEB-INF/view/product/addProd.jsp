<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/17
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>

<html>
<head>
    <title>添加产品</title>
</head>
<body>
<div>
    <form id="userForm" action="<%=request.getContextPath()%>/prod/saveProd" method="post">
        <table>
            <tbody>
            <tr>  <td>产品名称:</td> <td><input type="text" name="userName" /></td> </tr>
            <tr>  <td>产品编号:</td> <td><input type="text" name="nickName" /> </td></tr>
            <tr>  <td>产品类别:</td> <td><input type="text" name="loginName" /></td> </tr>
            <tr>  <td>单价:</td> <td><input type="text" name="telephone" /> </td></tr>
            <tr>  <td>单位: </td><td><input type="text" name="phone" /></td> </tr>
            <tr>  <td>描述: </td><td><input type="text" name="address" /></td> </tr>
            </tbody>
        </table>
        <input type="submit" value="保存" />
    </form>
</div>

</body>

</html>
