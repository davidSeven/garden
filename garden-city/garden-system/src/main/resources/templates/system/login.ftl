<#import "../spring.ftl" as spring />
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title><@spring.message code="message.login.topTitle"/></title>
        <link rel="icon" type="image/x-icon" href="<@spring.url''/>/static/favicon.ico">
		<link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/layui/css/layui.css" />
		<link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/css/login.css" />
	</head>

	<body>
		<div class="m-login-bg">
			<div class="m-login">
				<h3><@spring.message code="message.login.loginTitle"/></h3>
				<div class="m-login-warp">
					<form class="layui-form" method="post" action="<@spring.url''/>/login">
						<div class="layui-form-item">
                            <img id="userHeadImg" src="/static/user/images/default_head.png" class="layui-nav-img" style="position: absolute; margin-top: 8px; margin-left: 8px; height: 25px; width: 25px;">
							<input type="text" name="username" required lay-verify="required" placeholder="用户名" autocomplete="off" class="layui-input" style="padding-left: 45px;" onblur="layui.loadHead(this)">
						</div>
						<div class="layui-form-item">
                            <i class="layui-icon layui-icon-password" style="position: absolute; margin-top: 6px; margin-left: 8px; font-size: 25px;"></i>
							<input type="password" name="password" required lay-verify="required|jse" placeholder="密码" autocomplete="off" class="layui-input" style="padding-left: 45px;">
						</div>
						<#--<div class="layui-form-item">
							<div class="layui-inline">
								<input type="text" name="verity" required lay-verify="required" placeholder="验证码" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-inline">
								<img class="verifyImg" onclick="this.src=this.src+'?c='+Math.random();" src="../admin/images/login/yzm.jpg" />
							</div>
						</div>-->
                        <#if Request.login_error_msg??>
                            <div class="layui-form-item">
                                <div class="layui-form-mid layui-word-aux" style="color: #dd0000 !important;">${Request.login_error_msg}</div>
                            </div>
                        </#if>
						<div class="layui-form-item m-login-btn">
							<div class="layui-inline">
								<button class="layui-btn layui-btn-normal" lay-submit lay-filter="login">登录</button>
							</div>
							<div class="layui-inline">
								<button type="reset" class="layui-btn layui-btn-primary">取消</button>
							</div>
						</div>
					</form>
				</div>
				<p class="copyright">Copyright 2019-2019 by garden</p>
			</div>
		</div>
		<script src="<@spring.url''/>/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
		<script src="<@spring.url''/>/static/jsencrypt/jsencrypt.js" type="text/javascript" charset="utf-8"></script>
		<script src="<@spring.url''/>/static/login/public-key.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
            // 处理子页面里出现登录页面的问题
            if (window.top !== window) {
                window.top.location.href = window.location.href;
            }
			layui.use(['form'], function() {
				var form = layui.form;

                var jse = new JSEncrypt();
                jse.setPublicKey(publicKey);

				//自定义验证规则
				form.verify({
                    jse: function (value, element) {
                        element.value = jse.encrypt(value);
                    }
				});

				//监听提交
				form.on('submit(login)', function(data) {
				});

				layui.loadHead = function (input) {
				    console.log('layui loadHead');
                    loadHead(input.value);
                };

                function loadHead(value) {
                    console.log('function loadHead');
                    var img = document.getElementById("userHeadImg");
				    if (value) {
                        var userHead = localStorage.getItem("user_head_" + value);
                        if (userHead) {
                            img.src = userHead;
                            return true;
                        }
                    }
                    img.src = "/static/user/images/default_head.png";
				    return false;
                }
			});
		</script>
	</body>

</html>