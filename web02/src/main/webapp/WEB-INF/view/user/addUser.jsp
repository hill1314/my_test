<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/10
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<html>
<head>
    <title>addUser</title>
</head>
<body>
    <div>
        <form id="userForm" action="<%=request.getContextPath()%>/user/saveUser" method="post">
            <table>
                <tbody>
                <tr>  <td>客户名称:</td> <td><input type="text" name="userName" /></td> </tr>
                <tr>  <td>登录帐号:</td> <td><input type="text" name="loginName" /></td> </tr>
                <tr>  <td>别名:</td> <td><input type="text" name="nickName" /> </td></tr>
                <tr>  <td>手机号码:</td> <td><input type="text" name="telephone" /> </td></tr>
                <tr>  <td>电话号码: </td><td><input type="text" name="phone" /></td> </tr>
                <tr>  <td>住址: </td><td><input type="text" name="address" /></td> </tr>
                <tr>  <td>邮箱: </td><td><input type="text" name="email" /> </td></tr>
                <tr>  <td>备注: </td><td><input type="text" name="note" /></td> </tr>
                </tbody>
            </table>
            <input type="submit" value="保存" />
        </form>
    </div>
</body>
<script>
    function submit() {
        alert(111);
        var jsonData = $("#userForm").serialize();
        alert(jsonData);

        $.ajax({
            url:"<%=request.getContextPath()%>/user/saveUser",
            type:"POST",
            data:jsonData,
            dataType:"json",
            success:function(data){
                console.log(data);
                alert(data);
                if(data.resultCode=="0000"){
                    alert("保存成功！");
                }else{
                    alert("保存失败！"+data.resultMsg);
                }
            },
            error: function (xhr, status, err) {
                alert("出错了" + xhr.status + "，错误信息：" + xhr.responseText);
            }
        });
    }
</script>
</html>
