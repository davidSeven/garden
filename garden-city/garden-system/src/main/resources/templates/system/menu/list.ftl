<#import "../../spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>่ๅๅ่กจ</title>
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
                            permission="system:menu:edit"
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
    <form id="viewForm" class="layui-form layui-col-md9">
        <div class="layui-form-item">
            <label class="layui-form-label">ไธ็บง</label>
            <div class="layui-input-block">
                <input type="text" name="parentName" autocomplete="off" class="layui-input" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">ๅ็งฐ</label>
            <div class="layui-input-block">
                <input type="text" name="name" autocomplete="off" class="layui-input" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">ๅฐๅ</label>
            <div class="layui-input-block">
                <input type="text" name="path" autocomplete="off" class="layui-input" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">็ถๆ</label>
            <div class="layui-input-block">
                <input type="radio" name="state" value="1" title="ๅฏ็จ" checked disabled="disabled">
                <input type="radio" name="state" value="0" title="็ฆ็จ" disabled="disabled">
            </div>
        </div>
        <div id="item-icon" class="layui-form-item" style="display: none;">
            <label class="layui-form-label">ๅพๆ?</label>
            <div class="layui-input-block">
                <input type="text" name="icon" autocomplete="off" class="layui-input" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">้กบๅบ</label>
            <div class="layui-input-block">
                <input type="text" name="sorts" autocomplete="off" class="layui-input" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">ๅคๆณจ</label>
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
                //่ทๅๅฝๅiframe็nameๅผ
                , iframeObj = $(window.frameElement).attr('name');

        var index = parent.layer.getFrameIndex(window.name);
        console.log('current page index:' + index);

        var config = {
            showIcon: true,
            showDisabled: true
        };

        // ๅฝๅ้ไธญ่ๅid
        var currentSelectMenuId = null;

        // ๆฅ่ฏขๆ?ไฟกๆฏ
        function getTree(callback) {

            /*var loadingIndex = parent.layer.msg('ๅ?่ฝฝไธญ', {
                icon: 16
                ,shade: 0.2
                ,time: 0
            });*/
            if (hasPermissions("system:menu:list")) {
                ajaxPost('/system/menu/list', null, function (data) {
                    if (data.success) {
                        initTree && initTree(data.data);
                    } else {
                        parent.layer.msg(data.msg, {icon: 2});
                    }
                });
            } else {
                initTree([]);
            }

            // ๆๅๅ็ๅ่ฐ
            function initTree(datas) {
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
                            // console.log(treeNode);
                            currentSelectMenuId = treeNode.id;
                            // ๅ?่ฝฝๅณไพง่กจๅไฟกๆฏ
                            renderTreeNodeViewForm(treeNode);
                        }
                    }
                };
                // ๅ?่ฝฝๆ?
                var treeObj = $.fn.zTree.init($("#menuTree"), setting, datas);
                treeObj.expandAll(true);
                // ๆ?ๅ?่ฝฝๅฎๆๅ่ฐ
                callback && callback(treeObj);
                // ้ๆฐ้ไธญ
                if (currentSelectMenuId) {
                    var treeNode = treeObj.getNodeByParam("id", currentSelectMenuId, null);
                    if (treeNode) {
                        treeObj.selectNode(treeNode);
                        renderTreeNodeViewForm(treeNode);
                    }
                }
            }
        }
        // ๅทๆฐๆ?
        layui.refresh = function(callback) {
            // ๆธ็ฉบ่กจๅๆฐๆฎ
            $("#viewForm")[0].reset();
            getTree(callback);
        };
        layui.refresh();

        function renderTreeNodeViewForm(treeNode) {
            if (!treeNode["parentId"]) {
                $("#item-icon").show();
            } else {
                $("#item-icon").hide();
            }
            // ่ทๅๅฐ็ถ็บงๅ็งฐ
            var parentNode = treeNode.getParentNode();
            if (parentNode) {
                treeNode.parentName = parentNode.name;
            }
            // ่ฎพ็ฝฎ่กจๅๆฐๆฎ
            jsonData("viewForm", treeNode);
            form.render();
        }

        // ็ผ่พๅ่ฐ
        layui.editCallback = function(id) {
            currentSelectMenuId = id;
            layui.refresh();
        };

        $("#addBtn").click(function () {
            // ่ทๅ้ไธญ็ๆฐๆฎ
            var treeObj = $.fn.zTree.getZTreeObj("menuTree");
            var nodes = treeObj.getSelectedNodes();
            console.log(nodes);

            var url = $(this).attr('data-url');
            //ๅฐiframeObjไผ?้็ป็ถ็บง็ชๅฃ,ๆง่กๆไฝๅฎๆๅทๆฐ
            parent.page("็ผ่พ", url, iframeObj, w = "700px", h = "560px", {isInsert: true, nodes: nodes});
            return false;
        });

        // ไฟฎๆน
        $("#editBtn").click(function () {
            // ่ทๅ้ไธญ็ๆฐๆฎ
            var treeObj = $.fn.zTree.getZTreeObj("menuTree");
            var nodes = treeObj.getSelectedNodes();
            console.log(nodes);

            if (nodes.length === 1) {
                var url = $(this).attr('data-url');
                //ๅฐiframeObjไผ?้็ป็ถ็บง็ชๅฃ,ๆง่กๆไฝๅฎๆๅทๆฐ
                parent.page("็ผ่พ", url, iframeObj, w = "700px", h = "560px", {isInsert: false, nodes: nodes});
            } else {
                layer.msg('่ฏท้ๆฉไธๆก่ฎฐๅฝ', {icon: 7});
            }
            return false;
        });

        // ๅ?้ค
        $("#deleteBtn").click(function () {
            // ่ทๅ้ไธญ็ๆฐๆฎ
            var treeObj = $.fn.zTree.getZTreeObj("menuTree");
            var nodes = treeObj.getSelectedNodes();
            console.log(nodes);
            if (nodes.length === 1) {
                //่ฏข้ฎๆก
                layer.confirm('็กฎๅฎๅ?้ค้ไธญๆฐๆฎๅ', {
                    // btn: ['1','2'] //ๆ้ฎ
                    icon: 3
                }, function(){
                    // ็กฎๅฎ
                    callback && callback();
                }, function(){
                    // ๅๆถ
                });
                // callback
                var url = $(this).attr('data-url');
                function callback() {
                    ajaxPost(url, {id: nodes[0].id}, function (data) {
                        if (data.success) {
                            layer.msg('ๆไฝๆๅ', {icon: 1});
                            layui.refresh();
                        } else {
                            layer.msg(data.msg, {icon: 2});
                        }
                    });
                }
            } else {
                layer.msg('่ฏท้ๆฉไธๆก่ฎฐๅฝ', {icon: 7});
            }
            return false;
        });

        // ๅทๆฐ
        $("#refreshBtn").click(function () {
            layui.refresh();
            return false;
        });
    });
</script>
</body>

</html>