package com.stream.garden.system.view;

import java.util.HashMap;
import java.util.Map;

/**
 * @author garden
 * @date 2019-09-30 14:08
 */
public class FreeMarkerViewCache {

    private static Map<String, String> viewCacheMap = new HashMap<>();

    static {
        viewCacheMap.put("system/login.ftl", "<#import \"../spring.ftl\" as spring />\n" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\n" +
                "\t<head>\n" +
                "\t\t<meta charset=\"UTF-8\">\n" +
                "\t\t<meta name=\"viewport\" content=\"width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no\" />\n" +
                "\t\t<title><@spring.message code=\"message.login.topTitle\"/></title>\n" +
                "        <link rel=\"icon\" type=\"image/x-icon\" href=\"<@spring.url''/>/static/favicon.ico\">\n" +
                "\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"<@spring.url''/>/static/admin/layui/css/layui.css\" />\n" +
                "\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"<@spring.url''/>/static/admin/css/login.css\" />\n" +
                "\t</head>\n" +
                "\n" +
                "\t<body>\n" +
                "\t\t<div class=\"m-login-bg\">\n" +
                "\t\t\t<div class=\"m-login\">\n" +
                "\t\t\t\t<h3><@spring.message code=\"message.login.loginTitle\"/></h3>\n" +
                "\t\t\t\t<div class=\"m-login-warp\">\n" +
                "\t\t\t\t\t<form class=\"layui-form\" method=\"post\" action=\"<@spring.url''/>/login\">\n" +
                "\t\t\t\t\t\t<div class=\"layui-form-item\">\n" +
                "                            <img id=\"userHeadImg\" src=\"/static/user/images/default_head.png\" class=\"layui-nav-img\" style=\"position: absolute; margin-top: 8px; margin-left: 8px; height: 25px; width: 25px;\">\n" +
                "\t\t\t\t\t\t\t<input type=\"text\" name=\"username\" required lay-verify=\"required\" placeholder=\"用户名\" autocomplete=\"off\" class=\"layui-input\" style=\"padding-left: 45px;\" onblur=\"layui.loadHead(this)\">\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t<div class=\"layui-form-item\">\n" +
                "                            <i class=\"layui-icon layui-icon-password\" style=\"position: absolute; margin-top: 6px; margin-left: 8px; font-size: 25px;\"></i>\n" +
                "\t\t\t\t\t\t\t<input type=\"password\" name=\"password\" required lay-verify=\"required|jse\" placeholder=\"密码\" autocomplete=\"off\" class=\"layui-input\" style=\"padding-left: 45px;\">\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t<#--<div class=\"layui-form-item\">\n" +
                "\t\t\t\t\t\t\t<div class=\"layui-inline\">\n" +
                "\t\t\t\t\t\t\t\t<input type=\"text\" name=\"verity\" required lay-verify=\"required\" placeholder=\"验证码\" autocomplete=\"off\" class=\"layui-input\">\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t<div class=\"layui-inline\">\n" +
                "\t\t\t\t\t\t\t\t<img class=\"verifyImg\" onclick=\"this.src=this.src+'?c='+Math.random();\" src=\"../admin/images/login/yzm.jpg\" />\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t</div>-->\n" +
                "                        <#if Request.login_error_msg??>\n" +
                "                            <div class=\"layui-form-item\">\n" +
                "                                <div class=\"layui-form-mid layui-word-aux\" style=\"color: #dd0000 !important;\">${Request.login_error_msg}</div>\n" +
                "                            </div>\n" +
                "                        </#if>\n" +
                "\t\t\t\t\t\t<div class=\"layui-form-item m-login-btn\">\n" +
                "\t\t\t\t\t\t\t<div class=\"layui-inline\">\n" +
                "\t\t\t\t\t\t\t\t<button class=\"layui-btn layui-btn-normal\" lay-submit lay-filter=\"login\">登录</button>\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t<div class=\"layui-inline\">\n" +
                "\t\t\t\t\t\t\t\t<button type=\"reset\" class=\"layui-btn layui-btn-primary\">取消</button>\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</form>\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t\t<p class=\"copyright\">Copyright 2019-2019 by garden</p>\n" +
                "\t\t\t</div>\n" +
                "\t\t</div>\n" +
                "\t\t<script src=\"<@spring.url''/>/static/admin/layui/layui.js\" type=\"text/javascript\" charset=\"utf-8\"></script>\n" +
                "\t\t<script src=\"<@spring.url''/>/static/jsencrypt/jsencrypt.js\" type=\"text/javascript\" charset=\"utf-8\"></script>\n" +
                "\t\t<script src=\"<@spring.url''/>/static/login/public-key.js\" type=\"text/javascript\" charset=\"utf-8\"></script>\n" +
                "\t\t<script type=\"text/javascript\">\n" +
                "            // 处理子页面里出现登录页面的问题\n" +
                "            if (window.top !== window) {\n" +
                "                window.top.location.href = window.location.href;\n" +
                "            }\n" +
                "\t\t\tlayui.use(['form'], function() {\n" +
                "\t\t\t\tvar form = layui.form;\n" +
                "\n" +
                "                var jse = new JSEncrypt();\n" +
                "                jse.setPublicKey(publicKey);\n" +
                "\n" +
                "\t\t\t\t//自定义验证规则\n" +
                "\t\t\t\tform.verify({\n" +
                "                    jse: function (value, element) {\n" +
                "                        element.value = jse.encrypt(value);\n" +
                "                    }\n" +
                "\t\t\t\t});\n" +
                "\n" +
                "\t\t\t\t//监听提交\n" +
                "\t\t\t\tform.on('submit(login)', function(data) {\n" +
                "\t\t\t\t});\n" +
                "\n" +
                "\t\t\t\tlayui.loadHead = function (input) {\n" +
                "\t\t\t\t    console.log('layui loadHead');\n" +
                "                    loadHead(input.value);\n" +
                "                };\n" +
                "\n" +
                "                function loadHead(value) {\n" +
                "                    console.log('function loadHead');\n" +
                "                    var img = document.getElementById(\"userHeadImg\");\n" +
                "\t\t\t\t    if (value) {\n" +
                "                        var userHead = localStorage.getItem(\"user_head_\" + value);\n" +
                "                        if (userHead) {\n" +
                "                            img.src = userHead;\n" +
                "                            return true;\n" +
                "                        }\n" +
                "                    }\n" +
                "                    img.src = \"/static/user/images/default_head.png\";\n" +
                "\t\t\t\t    return false;\n" +
                "                }\n" +
                "\t\t\t});\n" +
                "\t\t</script>\n" +
                "\t</body>\n" +
                "\n" +
                "</html>");
    }

    public static String getTemplate(String url) {
        return viewCacheMap.get(url);
    }
}
