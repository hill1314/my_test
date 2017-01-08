<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>Fullscreen Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <%@include file="./WEB-INF/common/common.jsp"%><!--静态包含,含jquery引用-->
        <!-- CSS -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/login/reset.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/login/supersized.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/login/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <!-- Javascript -->
        <script src="http://apps.bdimg.com/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/js/supersized.3.2.7.min.js"></script>

    </head>

    <body oncontextmenu="return false">
        <div class="page-container">
            <h1>Login</h1>
            <form action="" method="post">
				<div>
					<input type="text" name="username" class="username" placeholder="Username" autocomplete="off"/>
				</div>
                <div>
					<input type="password" name="password" class="password" placeholder="Password" oncontextmenu="return false" onpaste="return false" />
                </div>
                <div>
                    <input type="code" name="code" class="code" placeholder="请输入验证码" oncontextmenu="return false" onpaste="return false" />
                </div>
                <div>
                    <img id="vimg"  title="点击更换" onclick="this.src='<%=request.getContextPath()%>/getImgCode?date='+new Date()"
                         src="<%=request.getContextPath()%>/getImgCode">
                </div>

                <button id="submit" type="button">Sign in</button>
            </form>
            <div class="connect">
                <p>If we can only encounter each other rather than stay with each other,then I wish we had never encountered.</p>
				<p style="margin-top:20px;">如果只是遇见，不能停留，不如不遇见。</p>
            </div>
        </div>
		<div class="alert" style="display:none">
			<h2>消息</h2>
			<div class="alert_con">
				<p id="ts"></p>
				<p style="line-height:70px"><a class="btn">确定</a></p>
			</div>
		</div>
    </body>

    <script>

        $(".btn").click(function(){
            is_hide();
        })
        var u = $("input[name=username]");
        var p = $("input[name=password]");
        var code = $("input[name=code]");

        /**
         * 登录
         */
        $("#submit").live('click',function(){
            if(u.val() == '' || p.val() =='' || code.val()=='')
            {
                $("#ts").html("用户名、密码、验证码 都不能为空~");
                is_show();
                return false;
            }
            else{
                var reg = /^[0-9A-Za-z]+$/;
                if(!reg.exec(u.val()))
                {
                    $("#ts").html("用户名错误");
                    is_show();
                    return false;
                }
            }

            var json = {
                userName:u.val(),
                password:p.val(),
                userCode:code.val()
            };

            $.ajax({
                url:"<%=request.getContextPath()%>/login",
                type:"POST",
                data:json,
                dataType:"json",
                success:function(data){
                    console.log(data);
                    if(data.resultCode!="0000"){
                        alert(data.resultMsg);
                        $("#vimg").click();
                    }else{
                        window.self.location="<%=request.getContextPath()%>/menu.jsp";
                    }
                },
                error:function(xhr,textStatus){
                    console.log('错误')
                    console.log(xhr)
                    console.log(textStatus)
                }
            });

        });
        window.onload = function()
        {
            $(".connect p").eq(0).animate({"left":"0%"}, 600);
            $(".connect p").eq(1).animate({"left":"0%"}, 400);
        }
        function is_hide(){ $(".alert").animate({"top":"-40%"}, 300) }
        function is_show(){ $(".alert").show().animate({"top":"45%"}, 300) }

        jQuery(function($){
            $.supersized({
                // Functionality
                slide_interval     : 6000,    // Length between transitions
                transition         : 1,    // 0-None, 1-Fade, 2-Slide Top, 3-Slide Right, 4-Slide Bottom, 5-Slide Left, 6-Carousel Right, 7-Carousel Left
                transition_speed   : 3000,    // Speed of transition
                performance        : 1,    // 0-Normal, 1-Hybrid speed/quality, 2-Optimizes image quality, 3-Optimizes transition speed // (Only works for Firefox/IE, not Webkit)

                // Size & Position
                min_width          : 0,    // Min width allowed (in pixels)
                min_height         : 0,    // Min height allowed (in pixels)
                vertical_center    : 1,    // Vertically center background
                horizontal_center  : 1,    // Horizontally center background
                fit_always         : 0,    // Image will never exceed browser width or height (Ignores min. dimensions)
                fit_portrait       : 1,    // Portrait images will not exceed browser height
                fit_landscape      : 0,    // Landscape images will not exceed browser width

                // Components
                slide_links        : 'blank',    // Individual links for each slide (Options: false, 'num', 'name', 'blank')
                slides             : [    // Slideshow Images
                    {image : '<%=request.getContextPath()%>/img/login/1.jpg'},
                    {image : '<%=request.getContextPath()%>/img/login/2.jpg'},
                    {image : '<%=request.getContextPath()%>/img/login/3.jpg'}
                ]

            });

        });
    </script>

</html>

