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
    <title>addUser</title>
</head>
<body>
    <div>
        <form id="userForm" action="/user/saveUser">
            <table>
                <tbody>
                <tr>  客户名称: <input type="text" name="user.userName" /> </tr>
                <tr>  登录帐号: <input type="text" name="user.loginName" /> </tr>
                <tr>  别名: <input type="text" name="user.nickName" /> </tr>
                <tr>  手机号码: <input type="text" name="user.telephone" /> </tr>
                <tr>  电话号码: <input type="text" name="user.phone" /> </tr>
                <tr>  住址: <input type="text" name="user.address" /> </tr>
                <tr>  邮箱: <input type="text" name="user.email" /> </tr>
                <tr>  备注: <input type="text" name="user.note" /> </tr>
                <tr>   <input type="button" value="保存" onclick="submit();"/> </tr>
                </tbody>
            </table>
        </form>
    </div>
</body>
<script>
    function submit() {
        var jsonData = $("#userForm").serialize();
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
