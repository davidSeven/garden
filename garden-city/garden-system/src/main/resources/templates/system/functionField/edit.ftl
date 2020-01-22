<#import "../../spring.ftl" as spring />
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
</head>
<body>
<div class="wrap-container">
    <form id="editForm" class="layui-form" style="width: 90%;padding-top: 20px;">
        <div class="layui-form-item">
            <label class="layui-form-label lay-required">功能名称</label>
            <div class="layui-input-block">
                <input type="hidden" name="id">
                <input type="hidden" name="menuId">
                <input type="text" name="name" required lay-verify="required" placeholder="请输入功能名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label lay-required">功能编码</label>
            <div class="layui-input-block">
                <input type="text" name="code" required lay-verify="required" placeholder="请输入功能编码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label lay-required">功能地址</label>
            <div class="layui-input-block">
                <input type="text" name="url" required lay-verify="required" placeholder="请输入功能地址" autocomplete="off" class="layui-input">
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
            <div class="layui-input-block">
                <button type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="editForm">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                <button type="button" class="layui-btn layui-btn-primary close-btn">关闭</button>
            </div>
        </div>
    </form>
</div>
<script src="<@spring.url''/>/static/jquery/jquery-3.3.1.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/admin/js/common.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    // edit
    layui.use(['form', 'jquery'], function() {
        var form = layui.form,
                $ = layui.jquery;

        var index = parent.layer.getFrameIndex(window.name);
        console.log('current page index:' + index);

        // 初始化方法
        layui.init = function (params) {
            // 处理初始值
            if (params.isInsert) {
                var nodes = params.nodes;
                // 新增
                if (nodes && nodes.length) {
                    var node = nodes[0];
                    $("input[name='menuId']").val(node.id);
                }
            } else {
                // 修改
                jsonData("editForm", params.data);
                form.render();
            }
        };

        //监听提交
        form.on('submit(editForm)', function(data) {
            console.log(data.field);
            var id = data.field.id;
            var url = '/system/function/add';
            if (id) {
                url = '/system/function/edit';
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
    });
</script>
</body>
</html>