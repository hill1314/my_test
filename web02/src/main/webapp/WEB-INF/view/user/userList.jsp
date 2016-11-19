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

        var dataJson = [
            { rank: 1, company: 'Exxon Mobil', revenues: '339938.0', profits: '36130.0' },
            { rank: 2, company: 'Wal-Mart Stores', revenues: '315654.0', profits: '11231.0' },
            { rank: 3, company: 'Royal Dutch Shell', revenues: '306731.0', profits: '25311.0' },
            { rank: 4, company: 'BP', revenues: '267600.0', profits: '22341.0' },
            { rank: 5, company: 'General Motors', revenues: '192604.0', profits: '-10567.0' },
            { rank: 6, company: 'Chevron', revenues: '189481.0', profits: '14099.0' },
            { rank: 7, company: 'DaimlerChrysler', revenues: '186106.3', profits: '3536.3' },
            { rank: 8, company: 'Toyota Motor', revenues: '185805.0', profits: '12119.6' },
            { rank: 9, company: 'Ford Motor', revenues: '177210.0', profits: '2024.0' },
            { rank: 10, company: 'ConocoPhillips', revenues: '166683.0', profits: '13529.0' },
            { rank: 11, company: 'General Electric', revenues: '157153.0', profits: '16353.0' },
            { rank: 12, company: 'Total', revenues: '152360.7', profits: '15250.0' },
            { rank: 13, company: 'ING Group', revenues: '138235.3', profits: '8958.9' },
            { rank: 14, company: 'Citigroup', revenues: '131045.0', profits: '24589.0' },
            { rank: 15, company: 'AXA', revenues: '129839.2', profits: '5186.5' },
            { rank: 16, company: 'Allianz', revenues: '121406.0', profits: '5442.4' },
            { rank: 17, company: 'Volkswagen', revenues: '118376.6', profits: '1391.7' },
            { rank: 18, company: 'Fortis', revenues: '112351.4', profits: '4896.3' },
            { rank: 19, company: 'Credit Agricole', revenues: '110764.6', profits: '7434.3' },
            { rank: 20, company: 'American Intl. Group', revenues: '108905.0', profits: '10477.0' }
        ];

        var obj = { width: 700, height: 400, title: "用户列表",resizable:true,draggable:true };

        obj.colModel = [
            { title: "用户ID", width: 100, dataType: "string", dataIndx: "userId" },
            { title: "用户名称", width: 200, dataType: "string", dataIndx: "userName" },
            { title: "Profits ($ millions)", width: 150, dataType: "float", align: "right", dataIndx: "revenues" },
            { title: "Profits ($ millions)", width: 150, dataType: "float", align: "right", dataIndx: "profits" }
        ];

        obj.dataModel = { data: dataJson };
        $("#grid_array").pqGrid(obj);

    });

    function doQuery() {
        $.ajax({
            type: "POST",
            url:"<%=request.getContextPath()%>/user/userList",
            data:"",
            dataType:"json",
            success:function(data){
                console.log(data);
                console.log(typeof data);
//        console.log(eval("("+data+")"));
        console.log(jQuery.parseJSON(data));

            },
            error:function(data){
                alert("error");
            }
        });
    }
        
</script>    
</head>
<body>
<div>
    <input type="button" id="doQuery" value="查询" onclick="doQuery()"/>
</div>
<%--<div>--%>
    <%--<table>--%>
        <%--<thead>--%>
        <%--<tr><th>id</th><th>name</th></tr>--%>
        <%--</thead>--%>
        <%--<tbody>--%>
        <%--<c:forEach items="${userList}" var="user">--%>
            <%--<tr> <td>${user.userId}</td> <td>${user.userName}</td>--%>
                <%--<td><a href="<%=request.getContextPath()%>/user/getUser?userId=${user.userId}">查看</a></td>--%>
                <%--<td><a href="<%=request.getContextPath()%>/user/delUser?userId=${user.userId}">删除</a></td>--%>
            <%--</tr>--%>
        <%--</c:forEach>--%>
        <%--</tbody>--%>
    <%--</table>--%>
<%--</div>--%>

<div id="grid_array" style="margin:100px;"></div>

</body>
</html>
