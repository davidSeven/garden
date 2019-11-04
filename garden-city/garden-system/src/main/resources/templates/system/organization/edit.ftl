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
            <label class="layui-form-label">上级</label>
            <div class="layui-input-block">
                <input type="hidden" name="id">
                <input type="hidden" name="parentId">
                <input type="text" name="parentName" autocomplete="off" class="layui-input" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label lay-required">名称</label>
            <div class="layui-input-block">
                <input type="text" name="name" required lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
                <input type="radio" name="state" value="1" title="启用" checked>
                <input type="radio" name="state" value="0" title="禁用">
            </div>
        </div>
        <div id="item-icon" class="layui-form-item" style="display: none;">
            <label class="layui-form-label">图标</label>
            <div class="layui-input-block">
                <input type="text" name="icon" placeholder="请输入图标" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">顺序</label>
            <div class="layui-input-block">
                <input type="text" name="sorts" lay-verify="sorts" placeholder="请输入顺序" maxlength="4" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea name="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="editForm">立即提交</button>
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
            var nodes = params.nodes;
            var isInsert = params.isInsert;
            var parentId;
            // 处理初始值
            if (isInsert) {
                // 新增
                if (nodes && nodes.length) {
                    var node = nodes[0];
                    $("input[name='parentId']").val(parentId = node.id);
                    $("input[name='parentName']").val(node.name);
                }
            } else {
                // 修改
                parentId = nodes[0]["parentId"];
                jsonData("editForm", nodes[0]);
                form.render();
            }

        };

        // 表单验证
        form.verify({
            sorts: function (value, item) {
                if (!value) {
                    return;
                }
                if (!/^[0-9]+$/.test(value)) {
                    return "请输入整数数字";
                }
            }
            //sorts: [/^[0-9]+$/, "请输入整数数字"]
        });

        //监听提交
        form.on('submit(editForm)', function(data) {
            console.log(data.field);

            var id = data.field.id;
            var url = '/system/organization/add';
            if (id) {
                url = '/system/organization/edit';
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