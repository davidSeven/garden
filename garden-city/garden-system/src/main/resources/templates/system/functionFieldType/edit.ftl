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
            <input type="hidden" name="functionId">
            <input type="hidden" name="type">
            <label class="layui-form-label">权限字段</label>
            <div id="fieldDiv" class="layui-input-block">
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
        layui.init = function (data) {
            // 填充默认值
            jsonData("editForm", data);
            // 查询字段相关信息
            var url = "/system/function-field-type/list-view";
            var params = {
                functionId: data.functionId,
                type: data.type
            };
            ajaxPost(url, params, function (data) {
                if (data.success) {
                    var fields = data.data;
                    if (fields) {
                        var length = fields.length;
                        for (var i = 0; i < length; i++) {
                            var field = fields[i];
                            $("#fieldDiv").append('<input type="checkbox" name="fields" value="'+field.functionFieldId+'" lay-skin="primary" title="'+field.name+'"'+(field.type ? 'checked=""':'')+'>');
                        }
                    }
                }
                // 渲染，动态添加的内容需要渲染后才会显示
                form.render();
            });
        };

        //监听提交
        form.on('submit(editForm)', function(data) {
            console.log(data.field);
            var list = [];
            $("input:checkbox[name='fields']:checked").each(function(i){
                var value = $(this).val();
                list.push({
                    functionFieldId: value
                });
            });
            var params = {
                functionId: data.field.functionId,
                type: data.field.type,
                list: list
            };
            var url = '/system/function-field-type/save';
            ajaxPost(url, {json: JSON.stringify(params)}, function (data) {
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