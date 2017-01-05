<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>菜单</title>
    <%@include file="./WEB-INF/common/common.jsp"%><!--静态包含,含jquery引用-->
    <link href="<%=request.getContextPath()%>/css/menu/VerticalMenu.css" rel="stylesheet" />
    <link href="http://www.jq22.com/jquery/font-awesome.4.6.0.css" rel="stylesheet" media="screen">
    <script src="<%=request.getContextPath()%>/js/menu/VerticalMenuJs.js"></script>
</head>
<body>
    <div>
        <div class="VerticalMenu">
            <div>
                <div><span>用户管理</span><i class="fa fa-angle-right fa-lg"></i></div>
                <div name="xz">
                    <div>
                        <div><span>查询用户</span></div>
                    </div>
                    <div>
                        <div><span>添加用户</span></div>
                    </div>
                </div>
            </div>
            <div>
                <div><span >商品管理</span><i class="fa fa-angle-right fa-lg"></i></div>
                <div name="xz">
                    <div>
                        <div><span>查询商品</span></div>
                    </div>
                    <div>
                        <div><span>新增商品</span></div>
                    </div>
                    <div>
                        <div><span>进货</span></div>
                    </div>
                </div>
            </div>
            <div>
                <div><span>订单管理</span><i class="fa fa-angle-right fa-lg"></i></div>
                <div name="xz">
                    <div>
                        <div><span>新建订单</span></div>
                    </div>
                    <div>
                        <div><span>查询订单</span></div>
                    </div>
                </div>
            </div>
            <div>
                <div><span>帐务管理</span><i class="fa fa-angle-right fa-lg"></i></div>
                <div name="xz">
                    <div>
                        <div><span>用户还款</span></div>
                    </div>
                    <div>
                        <div><span>账单查询</span></div>
                    </div>
                </div>
            </div>
            <div>
                <div><span>报表管理</span><i class="fa fa-angle-right fa-lg"></i></div>
                <div name="xz">
                    <div>
                        <div><span>商品销售报表</span></div>
                    </div>
                    <div>
                        <div><span>帐务报表</span></div>
                    </div>
                </div>
            </div>
            <div>
                <div><span>配置管理</span><i class="fa fa-angle-right fa-lg"></i></div>
                <div name="xz">
                    <div>
                        <div><span>商品分类</span></div>
                    </div>
                    <div>
                        <div><span>商品包装单位</span></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>