<#import "../spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>编辑</title>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/layui/css/layui.css" />
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/css/admin.css" />
    <style type="text/css">
        .layui-upload-img {
            width: 30px;
            height: 30px;
            margin-right: 10px;
            border-radius: 50%;
        }
    </style>
</head>
<body>
<div class="wrap-container">
    <form id="editForm" class="layui-form" style="width: 90%;padding-top: 20px;">
        <div class="layui-form-item">
            <label class="layui-form-label lay-required">编码</label>
            <div class="layui-input-block">
                <input type="hidden" name="id">
                <input type="text" name="code" required lay-verify="required" placeholder="请输入编码" autocomplete="off" class="layui-input"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label lay-required">内容</label>
            <div class="layui-input-block">
                <input type="text" name="value" required lay-verify="required" placeholder="请输入内容" autocomplete="off" class="layui-input"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label lay-required">语言类型</label>
            <div class="layui-input-block">
                <input type="radio" name="languageType" value="zh" title="中文" checked>
                <input type="radio" name="languageType" value="en" title="英文">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
                <input type="radio" name="state" value="1" title="启用" checked>
                <input type="radio" name="state" value="0" title="禁用">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <input type="text" name="remark" placeholder="请输入备注" autocomplete="off" class="layui-input"/>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="editForm">立即提交</button>
                <button id="closeBtn" type="button" class="layui-btn layui-btn-primary">关闭</button>
            </div>
        </div>
    </form>
</div>
<script src="<@spring.url''/>/static/jquery/jquery-3.3.1.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/admin/js/common.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    // edit
    layui.use(['form', 'jquery', 'upload'], function() {
        var form = layui.form,
                upload = layui.upload,
                $ = layui.jquery,
                iframeObj = $(window.frameElement).attr('name');

        var index = parent.layer.getFrameIndex(window.name);
        console.log('current page index:' + index);

        // 初始化方法
        layui.init = function (params) {
            // 处理初始值
            if (params.isInsert) {
                // 新增
            } else {
                // 修改
                jsonData("editForm", params.data);
                form.render();
            }
        };

        // 监听提交
        form.on('submit(editForm)', function(data) {
            // console.log(data.field);
            var id = data.field.id;
            var url = '/i18n/i18n/add';
            if (id) {
                url = '/i18n/i18n/edit';
            }
            ajaxPost(url, data.field, function (data) {
                if (data.success) {
                    layer.msg('操作成功', {icon: 1});
                    closePage();
                } else {
                    layer.msg(data.msg, {icon: 2});
                }
            });
            return false;
        });

        $("#closeBtn").click(function () {
            closePageNoRefresh();
        });
    });
</script>
</body>
</html>