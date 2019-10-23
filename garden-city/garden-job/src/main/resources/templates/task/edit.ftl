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
            <label class="layui-form-label lay-required">任务名称</label>
            <div class="layui-input-block">
                <input type="text" name="name" required lay-verify="required" placeholder="请输入任务名称" autocomplete="off" class="layui-input"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label lay-required">Cron</label>
            <div class="layui-input-block">
                <input type="text" name="cron" required lay-verify="required" placeholder="请输入Cron" autocomplete="off" class="layui-input"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label lay-required">路径</label>
            <div class="layui-input-block">
                <input type="text" name="url" required lay-verify="required" placeholder="请输入路径" autocomplete="off" class="layui-input"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">参数</label>
            <div class="layui-input-block">
                <input type="text" name="params" placeholder="请输入参数" autocomplete="off" class="layui-input"/>
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
        console.log('layui init');
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

        // 处理页面加载未完成的问题
        if (window['_initCallback']) {
            window['_initCallback'](layui);
        }

        // 监听提交
        form.on('submit(editForm)', function(data) {
            // console.log(data.field);
            var id = data.field.id;
            var url = '/job/task/add';
            if (id) {
                url = '/job/task/edit';
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