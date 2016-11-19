
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" type="text/css">
<html>
<head>
<meta charset="utf-8">
<title>首页</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/img/taohua.jpg" type="image/x-icon" />
</head>
<body>

<!-- 文字区不需要请连css部分代码一并删除 -->
<h1><small>全屏</small>
	自动切换背景
	<small>适用浏览器：360、FireFox、Chrome、Opera、傲游、搜狗、世界之窗. 不支持Safari、IE8及以下浏览器。</small>
</h1>

<!-- 你可以添加个多“.slideshow-image”项目, 记得修改CSS -->
<div class="slideshow">
	<div class="slideshow-image" style="background-image: url('<%=request.getContextPath()%>/img/home/1.jpg')"></div>
	<div class="slideshow-image" style="background-image: url('<%=request.getContextPath()%>/img/home/2.jpg')"></div>
	<div class="slideshow-image" style="background-image: url('<%=request.getContextPath()%>/img/home/3.jpg')"></div>
	<div class="slideshow-image" style="background-image: url('<%=request.getContextPath()%>/img/home/4.jpg')"></div>
</div>

</body>
</html>

