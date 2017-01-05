<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
    <%@include file="../../common/common.jsp"%><!--静态包含,含jquery引用-->
    <!--jQuery dependencies-->
    <script src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script>

    <!--PQ Grid files-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/grid/pqgrid.min.css" />
    <script src="<%=request.getContextPath()%>/js/grid/pqgrid.min.js"></script>
    <!--PQ Grid Office theme-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/grid/pqgrid.css" />

<script>
    $(function () {
        doQuery();
    });
    
    function loadData(dataJson) {
        $.each(dataJson,function (n,user) {
//            console.log(n,user);
            user.operation="<a href=\"<%=request.getContextPath()%>/user/getUser?userId="+user.userId+"\">查看</a><br>"
                    +"<a href=\"<%=request.getContextPath()%>/user/delUser?userId="+user.userId+"\">删除</a>";
        });

        var obj = { width: 1000, height: 200, title: "用户列表",resizable:true,draggable:true };
        obj.colModel = [
            { title: "用户ID", width: 100, dataType: "string", dataIndx: "userId" },
            { title: "用户名称", width: 100,dataType: "string", dataIndx: "userName" },
            { title: "登录帐号", width: 100,dataType: "string", dataIndx: "loginName" },
            { title: "别名",width: 100,dataType: "string", dataIndx: "nickName" },
            { title: "手机号",width: 100,dataType: "string", dataIndx: "telephone" },
            { title: "电话",width: 100,dataType: "string", dataIndx: "phone" },
            { title: "地址",width: 100,dataType: "string", dataIndx: "address" },
            { title: "备注",width: 100,dataType: "string", dataIndx: "note" },
            { title: "操作",width: 100,dataType: "string", dataIndx: "operation" }
            ];
        obj.dataModel = { data: dataJson };
        $("#grid_array").pqGrid(obj);
    }

    function doQuery() {
        $.ajax({
            url:"<%=request.getContextPath()%>/user/userList",
            type: "POST",
            async:true,    //或false,是否异步
            data:"",
            dataType:"json",
            success:function(data,textStatus,jqXHR){
//                console.log(data);
                loadData(data);
            },
            error:function(xhr,textStatus){
                console.log('错误')
                console.log(xhr)
                console.log(textStatus)
            },
        });
    }
        
</script>    
</head>
<body>
<div>
    <input type="button" id="doQuery" value="查询" onclick="doQuery()"/>
</div>

<div id="grid_array" style="margin:100px;"></div>

</body>
</html>
