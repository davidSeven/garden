<#import "../../spring.ftl" as spring />
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
                            permission="system:menu:add"
                            data-url="/system/menu/toEdit"><i class="layui-icon">&#xe654;</i></button>
                    <button id="editBtn" class="layui-btn layui-btn-small layui-btn-primary hidden-xs has-permission"
                            permission="system:menu:update"
                            data-url="/system/menu/toEdit"><i class="layui-icon">&#xe642;</i></button>
                    <button id="deleteBtn" class="layui-btn layui-btn-small layui-btn-primary hidden-xs has-permission"
                            permission="system:menu:del"
                            data-url="/system/menu/delete"><i class="layui-icon layui-icon-delete"></i></button>
                    <button id="refreshBtn" class="layui-btn layui-btn-small layui-btn-primary hidden-xs has-permission"
                            permission="system:menu:list"
                            ><i class="layui-icon layui-icon-refresh"></i></button>
                </div>
            </div>
        </form>
        <ul id="menuTree" class="ztree"></ul>
    </div>
    <div id="viewForm" class="layui-form layui-col-md9">
        <div class="layui-form-item">
            <label class="layui-form-label">上级</label>
            <div class="layui-input-block">
                <input type="text" name="parentName" autocomplete="off" class="layui-input" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">名称</label>
            <div class="layui-input-block">
                <input type="text" name="name" autocomplete="off" class="layui-input" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">地址</label>
            <div class="layui-input-block">
                <input type="text" name="path" autocomplete="off" class="layui-input" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
                <input type="radio" name="state" value="1" title="启用" checked disabled="disabled">
                <input type="radio" name="state" value="0" title="禁用" disabled="disabled">
            </div>
        </div>
        <div id="item-icon" class="layui-form-item" style="display: none;">
            <label class="layui-form-label">图标</label>
            <div class="layui-input-block">
                <input type="text" name="icon" autocomplete="off" class="layui-input" disabled="disabled">
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
    </div>
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

        var config = {
            showIcon: true,
            showDisabled: true
        };

        function getTree() {

            /*var loadingIndex = parent.layer.msg('加载中', {
                icon: 16
                ,shade: 0.2
                ,time: 0
            });*/
            if (hasPermissions("system:menu:list")) {
                ajaxPost('/system/menu/list', null, function (data) {
                    if (data.success) {
                        callback && callback(data.data);
                    } else {
                        parent.layer.msg(data.msg, {icon: 2});
                    }
                });
            } else {
                callback([]);
            }

            // 成功后的回调
            function callback(datas) {
                if (datas && datas.length) {
                    $.each(datas, function (i, v) {
                        var ns = [v.name];
                        if (v.icon && config.showIcon) {
                            ns.push('<i style="margin-left: 5px;" class="layui-icon">' + v.icon + '</i>');
                        }
                        v['name_ztree'] = ns.join("");
                    });
                }

                var setting = {
                    view: {
                        dblClickExpand: false,
                        showLine: true,
                        selectedMulti: false,
                        nameIsHTML: true,
                        fontCss: function (treeId, treeNode) {
                            if (treeNode.state === '0' && config.showDisabled) {
                                return {
                                    "color": "#c9c9c9"
                                };
                            }
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
                            name: "name_ztree",
                            title: "name"
                        }
                    },
                    callback: {
                        onClick: function (event, treeId, treeNode) {
                            console.log(treeNode);
                            if (!treeNode["parentId"]) {
                                $("#item-icon").show();
                            } else {
                                $("#item-icon").hide();
                            }
                            // 获取到父级名称
                            var parentNode = treeNode.getParentNode();
                            if (parentNode) {
                                treeNode.parentName = parentNode.name;
                            }
                            // 设置表单数据
                            jsonData("viewForm", treeNode);
                            form.render();
                        }
                    }
                };

                var treeObj = $.fn.zTree.init($("#menuTree"), setting, datas);
                treeObj.expandAll(true);
            }
        }
        layui.refresh = function() {
            getTree();
        };
        layui.refresh();

        $("#addBtn").click(function () {
            // 获取选中的数据
            var treeObj = $.fn.zTree.getZTreeObj("menuTree");
            var nodes = treeObj.getSelectedNodes();
            console.log(nodes);

            var url = $(this).attr('data-url');
            //将iframeObj传递给父级窗口,执行操作完成刷新
            parent.page("编辑", url, iframeObj, w = "700px", h = "560px", {isInsert: true, nodes: nodes});
            return false;
        });

        // 修改
        $("#editBtn").click(function () {
            // 获取选中的数据
            var treeObj = $.fn.zTree.getZTreeObj("menuTree");
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
            var treeObj = $.fn.zTree.getZTreeObj("menuTree");
            var nodes = treeObj.getSelectedNodes();
            console.log(nodes);
            if (nodes.length === 1) {
                //询问框
                layer.confirm('确定删除选中数据吗', {
                    // btn: ['1','2'] //按钮
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