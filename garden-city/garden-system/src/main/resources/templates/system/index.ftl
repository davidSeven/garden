<#import "../spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>网站后台管理模版</title>
    <link rel="icon" type="image/x-icon" href="<@spring.url''/>/static/favicon.ico">
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/css/admin.css"/>
    <style type="text/css">
        .main-layout-header .layui-nav .layui-nav-item {
            height: 50px;
        }
        .main-layout-header .layui-nav .layui-nav-item a {
            color: #c9c9c9;
            line-height: 50px;
        }
        .main-layout-header .layui-nav .layui-nav-bar {
            display: block;
            height: 2px;
            background-color: #20222A;
            top: 0 !important;
        }
        .main-layout-container {
            overflow: hidden;
        }
        .layui-icon-menu {
            font-size: 18px;
            padding-right: 10px;
        }
        .layui-this {
            user-select: none;
        }
    </style>
</head>
<body>
<div class="main-layout" id='main-layout'>
    <!--侧边栏-->
    <div class="main-layout-side">
        <div class="m-logo">
        </div>
        <ul class="layui-nav layui-nav-tree" lay-shrink="all" lay-filter="leftNav">
            <#list menuList as menu>
            <li class="layui-nav-item">
                <a href="javascript:;" data-url="${menu.path!""}" data-id='${menu.id}' data-text="${menu.name}"><i class="layui-icon layui-icon-menu">${menu.icon!""}</i>${menu.name}</a>
                <#if menu.children?? && (menu.children?size > 0)>
                <dl class="layui-nav-child">
                    <#list menu.children as child>
                    <dd><a href="javascript:;" data-url="${child.path!""}" data-id='${child.id}' data-text="${child.name}"><span class="l-line"></span>${child.name}</a></dd>
                    </#list>
                </dl>
                </#if>
            </li>
            </#list>

            <#--
            <li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;"><i class="iconfont">&#xe607;</i>菜单管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" data-url="system/menu/toList" data-id='1' data-text="菜单管理"><span class="l-line"></span>菜单管理</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;"><i class="iconfont">&#xe608;</i>内容管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" data-url="article-list.html" data-id='3' data-text="文章管理"><span class="l-line"></span>文章管理</a></dd>
                    <dd><a href="javascript:;" data-url="danye-list.html" data-id='9' data-text="单页管理"><span class="l-line"></span>单页管理</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;" data-url="email.html" data-id='4' data-text="邮件系统"><i class="iconfont">&#xe603;</i>邮件系统</a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;" data-url="admin-info.html" data-id='5' data-text="个人信息"><i class="iconfont">&#xe606;</i>个人信息</a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;"><i class="iconfont">&#xe60b;</i>系统管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" data-url="system/organization/toList" data-id='31' data-text="组织管理"><span class="l-line"></span>组织管理</a></dd>
                    <dd><a href="javascript:;" data-url="system/position/toList" data-id='32' data-text="职务管理"><span class="l-line"></span>职务管理</a></dd>
                    <dd><a href="javascript:;" data-url="system/user/toList" data-id='33' data-text="用户管理"><span class="l-line"></span>用户管理</a></dd>
                    <dd><a href="javascript:;" data-url="system/role/toList" data-id='39' data-text="角色管理"><span class="l-line"></span>角色管理</a></dd>
                    <dd><a href="javascript:;" data-url="system/group/toList" data-id='40' data-text="群组管理"><span class="l-line"></span>群组管理</a></dd>
                    <dd><a href="javascript:;" data-url="system/function/toList" data-id='139' data-text="功能管理"><span class="l-line"></span>功能管理</a></dd>
                    <dd><a href="javascript:;" data-url="" data-id='139' data-text="数据范围管理"><span class="l-line"></span>数据范围管理</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;"><i class="iconfont">&#xe60b;</i>系统设置</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" data-url="lookup/lookup/toList" data-id='188' data-text="Lookup管理"><span class="l-line"></span>Lookup管理</a></dd>
                </dl>
            </li>
            -->
        </ul>
    </div>
    <!--右侧内容-->
    <div class="main-layout-container">
        <!--头部-->
        <div class="main-layout-header">
            <div class="menu-btn" id="hideBtn">
                <a href="javascript:;">
                    <span class="iconfont">&#xe60e;</span>
                </a>
            </div>
            <ul class="layui-nav" lay-filter="rightNav">
                <li class="layui-nav-item"><a href="javascript:;" data-url="email.html" data-id='4' data-text="邮件系统"><i class="iconfont">&#xe603;</i></a></li>
                <li class="layui-nav-item">
                    <a href="javascript:;" data-url="admin-info.html" data-id='5' data-text="个人信息">${user.roleName!'loading...'}</a>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <#if (Request.user ??) && (Request.user.bizHeadPath ??)>
                            <img src="/static/images${Request.user.bizHeadPath}" class="layui-nav-img">
                        <#else>
                            <img src="/static/user/images/default_head.png" class="layui-nav-img">
                        </#if>
                        ${(Request.user.name)! 'loading...'}
                        </a>
                        <#--<span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
                            <dd><a href="">基本资料</a></dd>
                            <dd><a href="">安全设置</a></dd>
                        </dl>-->
                </li>
                <li class="layui-nav-item"><a href="<@spring.url''/>/logout">退出</a></li>
            </ul>
        </div>
        <!--主体内容-->
        <div class="main-layout-body">
            <!--tab 切换-->
            <div class="layui-tab layui-tab-brief main-layout-tab" lay-filter="tab" lay-allowClose="true">
                <ul class="layui-tab-title">
                    <li class="layui-this welcome">后台主页</li>
                </ul>
                <div class="layui-tab-content">
                    <div class="layui-tab-item layui-show" style="background: #f5f5f5;">
                        <!--1-->
                        <iframe src="welcome.html" width="100%" height="100%" name="iframe" scrolling="auto" class="iframe" framborder="0"></iframe>
                        <!--1end-->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--遮罩-->
    <div class="main-mask">
    </div>
</div>
<script type="text/javascript">
    var scope = {
        link: './welcome.html'
    };
    (function () {
        var userId = '${user.code}';
        var userHead = '${(user.bizHeadPath!'')? replace("\\", "/")}';
        if (userId && userHead) {
            localStorage.setItem("user_head_" + userId, "/static/images" + userHead);
        }
    })();
</script>
<script src="<@spring.url''/>/static/jquery/jquery-3.3.1.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/admin/js/common.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/admin/js/main.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>