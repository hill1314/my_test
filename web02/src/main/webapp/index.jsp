<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/13
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>--%>
<script src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>

<html>
  <head>
    <title>index</title>
  </head>
  <body>
  测试:<input type="text" id="test" value="111"/>
  <input type="button" value="测试" onclick="test()">
  </body>
  <script>
    function test() {
      alert(123);
      alert( $("#test").attr("value"));
//      alert( $("#test").value());
//      $.ajax({type : "POST",
//        url : "/prod/test",
//        data : {
//          userId : 123,
//          'add[]' : [1,2,3],
//          'del[]' :[4]
//        },
//        success : function (data){
//          alert(333);
//        }
//      });
    }

  </script>
</html>
