<#import "../spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>菜单列表</title>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/css/admin.css"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/ztree/zTreeStyle.css"/>
    <style type="text/css">

    </style>
</head>
<body>
<div class="page-content-wrap">
    <div class="layui-col-md3">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <div class="layui-inline tool-btn" style="margin-right: 0;">
                    <button id="addBtn" class="layui-btn layui-btn-small layui-btn-primary hidden-xs has-permission"
                            permission="dictionary:dictionary:add"
                            data-url="/dictionary/dictionary/toEdit"><i class="layui-icon">&#xe654;</i></button>
                    <button id="editBtn" class="layui-btn layui-btn-small layui-btn-primary hidden-xs has-permission"
                            permission="dictionary:dictionary:edit"
                            data-url="/dictionary/dictionary/toEdit"><i class="layui-icon">&#xe642;</i></button>
                    <button id="deleteBtn" class="layui-btn layui-btn-small layui-btn-primary hidden-xs has-permission"
                            permission="dictionary:dictionary:del"
                            data-url="/dictionary/dictionary/delete"><i class="layui-icon layui-icon-delete"></i></button>
                    <button id="refreshBtn" class="layui-btn layui-btn-small layui-btn-primary hidden-xs has-permission"
                            permission="dictionary:dictionary:list"
                            ><i class="layui-icon layui-icon-refresh"></i></button>
                </div>
            </div>
        </form>
        <ul id="dataTree" class="ztree"></ul>
    </div>
    <form id="viewForm" class="layui-form layui-col-md9">
        <div class="layui-form-item">
            <label class="layui-form-label">上级编码</label>
            <div class="layui-input-block">
                <input type="text" name="parentCode" autocomplete="off" class="layui-input" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">编码</label>
            <div class="layui-input-block">
                <input type="text" name="code" autocomplete="off" class="layui-input" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">名称</label>
            <div class="layui-input-block">
                <input type="text" name="name" autocomplete="off" class="layui-input" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">值</label>
            <div class="layui-input-block">
                <input type="text" name="value" autocomplete="off" class="layui-input" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
                <input type="radio" name="state" value="1" title="启用" checked disabled="disabled">
                <input type="radio" name="state" value="0" title="禁用" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">顺序</label>
            <div class="layui-input-block">
                <input type="text" name="sorts" autocomplete="off" class="layui-input" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea name="remark" class="layui-textarea" disabled="disabled"></textarea>
            </div>
        </div>
    </form>
</div>
<script src="<@spring.url''/>/static/jquery/jquery-3.3.1.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/admin/js/common.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/ztree/jquery.ztree.all.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['jquery', 'layer', 'form'], function () {
        var $ = layui.jquery
                , layer = layui.layer
                , form = layui.form
                //获取当前iframe的name值
                , iframeObj = $(window.frameElement).attr('name');

        var index = parent.layer.getFrameIndex(window.name);
        console.log('current page index:' + index);

        // 当前选中id
        var currentSelectId = null;

        // 查询树信息
        function getTree(callback) {

            if (hasPermissions("dictionary:dictionary:list")) {
                ajaxPost('/dictionary/dictionary/list', null, function (data) {
                    if (data.success) {
                        initTree && initTree(data.data);
                    } else {
                        parent.layer.msg(data.msg, {icon: 2});
                    }
                });
            } else {
                initTree([]);
            }

            // 成功后的回调
            function initTree(datas) {

                var setting = {
                    view: {
                        dblClickExpand: false,
                        showLine: true,
                        selectedMulti: false,
                        nameIsHTML: true,
                        fontCss: function (treeId, treeNode) {
                        }
                    },
                    data: {
                        simpleData: {
                            enable: true,
                            idKey: "id",
                            pIdKey: "parentId",
                            rootPId: ""
                        },
                        key: {
                            icon: "icon_name_ztree",
                            name: "name",
                            title: "name"
                        }
                    },
                    callback: {
                        onClick: function (event, treeId, treeNode) {
                            currentSelectId = treeNode.id;
                            // 加载右侧表单信息
                            renderTreeNodeViewForm(treeNode);
                        }
                    }
                };
                // 加载树
                var treeObj = $.fn.zTree.init($("#dataTree"), setting, datas);
                treeObj.expandAll(true);
                // 树加载完成回调
                callback && callback(treeObj);
                // 重新选中
                if (currentSelectId) {
                    var treeNode = treeObj.getNodeByParam("id", currentSelectId, null);
                    if (treeNode) {
                        treeObj.selectNode(treeNode);
                        renderTreeNodeViewForm(treeNode);
                    }
                }
            }
        }
        // 刷新树
        layui.refresh = function(callback) {
            // 清空表单数据
            $("#viewForm")[0].reset();
            getTree(callback);
        };
        layui.refresh();

        function renderTreeNodeViewForm(treeNode) {
            // 获取到父级名称
            var parentNode = treeNode.getParentNode();
            if (parentNode) {
                treeNode.parentCode = parentNode.code;
            }
            // 设置表单数据
            jsonData("viewForm", treeNode);
            form.render();
        }

        // 编辑回调
        layui.editCallback = function(id) {
            currentSelectId = id;
            layui.refresh();
        };

        $("#addBtn").click(function () {
            // 获取选中的数据
            var treeObj = $.fn.zTree.getZTreeObj("dataTree");
            var nodes = treeObj.getSelectedNodes();
            var url = $(this).attr('data-url');
            //将iframeObj传递给父级窗口,执行操作完成刷新
            parent.page("编辑", url, iframeObj, w = "700px", h = "560px", {isInsert: true, nodes: nodes});
            return false;
        });

        // 修改
        $("#editBtn").click(function () {
            // 获取选中的数据
            var treeObj = $.fn.zTree.getZTreeObj("dataTree");
            var nodes = treeObj.getSelectedNodes();
            console.log(nodes);

            if (nodes.length === 1) {
                var url = $(this).attr('data-url');
                //将iframeObj传递给父级窗口,执行操作完成刷新
                parent.page("编辑", url, iframeObj, w = "700px", h = "560px", {isInsert: false, nodes: nodes});
            } else {
                layer.msg('请选择一条记录', {icon: 7});
            }
            return false;
        });

        // 删除
        $("#deleteBtn").click(function () {
            // 获取选中的数据
            var treeObj = $.fn.zTree.getZTreeObj("dataTree");
            var nodes = treeObj.getSelectedNodes();
            if (nodes.length === 1) {
                //询问框
                layer.confirm('确定删除选中数据吗', {
                    icon: 3
                }, function(){
                    // 确定
                    callback && callback();
                }, function(){
                    // 取消
                });
                // callback
                var url = $(this).attr('data-url');
                function callback() {
                    ajaxPost(url, {id: nodes[0].id}, function (data) {
                        if (data.success) {
                            layer.msg('操作成功', {icon: 1});
                            layui.refresh();
                        } else {
                            layer.msg(data.msg, {icon: 2});
                        }
                    });
                }
            } else {
                layer.msg('请选择一条记录', {icon: 7});
            }
            return false;
        });

        // 刷新
        $("#refreshBtn").click(function () {
            layui.refresh();
            return false;
        });
    });
</script>
</body>

</html>