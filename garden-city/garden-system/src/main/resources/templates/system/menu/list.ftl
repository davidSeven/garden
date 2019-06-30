<#import "../../spring.ftl" as spring />
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>菜单列表</title>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/css/admin.css"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/ztree/css/zTreeStyle.css"/>
    <style type="text/css">

    </style>
</head>

<body>
<div class="page-content-wrap">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <div class="layui-inline tool-btn">
                <button id="editBtn" class="layui-btn layui-btn-small layui-btn-normal hidden-xs"
                        data-url="/system/menu/toEdit"><i class="layui-icon">&#xe654;</i></button>
                <button class="layui-btn layui-btn-small layui-btn-warm hidden-xs" data-url="menu-add.html"><i
                        class="iconfont">&#xe656;</i></button>
            </div>
        </div>
    </form>
    <div class="layui-col-md4">
        <ul id="menuTree" class="ztree"></ul>
    </div>
    <div class="layui-form layui-col-md8" id="table-list">
        <div class="layui-form-item">
            <label class="layui-form-label lay-required">输入框</label>
            <div class="layui-input-inline">
                <input type="text" name="title" required lay-verify="required" placeholder="请输入标题" autocomplete="off"
                       class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">辅助文字</div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">选择框</label>
            <div class="layui-input-block">
                <select name="city" lay-verify="required">
                    <option value=""></option>
                    <option value="0">北京</option>
                    <option value="1">上海</option>
                    <option value="2">广州</option>
                    <option value="3">深圳</option>
                    <option value="4">杭州</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">复选框</label>
            <div class="layui-input-block">
                <input type="checkbox" name="like[write]" title="写作">
                <input type="checkbox" name="like[read]" title="阅读" checked>
                <input type="checkbox" name="like[dai]" title="发呆">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">开关</label>
            <div class="layui-input-block">
                <input type="checkbox" name="switch" lay-skin="switch">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">单选框</label>
            <div class="layui-input-block">
                <input type="radio" name="sex" value="男" title="男">
                <input type="radio" name="sex" value="女" title="女" checked>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">文本域</label>
            <div class="layui-input-block">
                <textarea name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>
</div>
<script src="<@spring.url''/>/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/admin/js/common.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/jquery/jquery-3.3.1.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/ztree/jquery.ztree.all.min.js" type="text/javascript" charset="utf-8"></script>
<script>
    layui.use(['jquery', 'tree', 'layer', 'form'], function () {
        var $ = layui.jquery
                , tree = layui.tree
                , layer = layui.layer
                , dialog = layui.dialog
                , form = layui.form
                //获取当前iframe的name值
                , iframeObj = $(window.frameElement).attr('name')
                //模拟数据
                , datas = null;

        $.ajax({
            async: false, // 同步
            type: 'post',
            url: '/system/menu/getTree',
            data: {},
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    datas = data.data;
                } else {
                    layer.msg(data.msg, {icon: 2});
                }
            },
            error: function () {

            }
        });

        var zNodes = [
            {id: 1, pId: 0, name: "[core] 基本功能 演示", open: true},
            {id: 101, pId: 1, name: "最简单的树 --  标准 JSON 数据", file: "core/standardData"},
            {id: 102, pId: 1, name: "最简单的树 --  简单 JSON 数据", file: "core/simpleData"},

            {id: 2, pId: 0, name: "[excheck] 复/单选框功能 演示", open: false},
            {id: 201, pId: 2, name: "Checkbox 勾选操作", file: "excheck/checkbox"},
            {id: 206, pId: 2, name: "Checkbox nocheck 演示", file: "excheck/checkbox_nocheck"}
        ];

        var setting = {
            view: {
                dblClickExpand: false,
                showLine: true,
                selectedMulti: false
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: ""
                }
            }
        };

        $.fn.zTree.init($("#menuTree"), setting, zNodes);

        console.log(menuTree);

        $("#editBtn").click(function () {
            var url = $(this).attr('data-url');
            //将iframeObj传递给父级窗口,执行操作完成刷新
            page("编辑", url, iframeObj, w = "700px", h = "560px");

            var treeObj = $.fn.zTree.getZTreeObj("menuTree");
            var nodes = treeObj.getSelectedNodes();
            console.log(nodes);

            return false;
        });
    });
</script>
</body>

</html>