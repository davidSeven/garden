<#import "../spring.ftl" as spring />
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
            <label class="layui-form-label">ไธ็บง็ผ็?</label>
            <div class="layui-input-block">
                <input type="text" name="parentCode" autocomplete="off" class="layui-input" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">็ผ็?</label>
            <div class="layui-input-block">
                <input type="text" name="code" autocomplete="off" class="layui-input" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">ๅ็งฐ</label>
            <div class="layui-input-block">
                <input type="text" name="name" autocomplete="off" class="layui-input" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">ๅผ</label>
            <div class="layui-input-block">
                <input type="text" name="value" autocomplete="off" class="layui-input" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">็ถๆ</label>
            <div class="layui-input-block">
                <input type="radio" name="state" value="1" title="ๅฏ็จ" checked disabled="disabled">
                <input type="radio" name="state" value="0" title="็ฆ็จ" disabled="disabled">
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

        // ๅฝๅ้ไธญid
        var currentSelectId = null;

        // ๆฅ่ฏขๆ?ไฟกๆฏ
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

            // ๆๅๅ็ๅ่ฐ
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
                            // ๅ?่ฝฝๅณไพง่กจๅไฟกๆฏ
                            renderTreeNodeViewForm(treeNode);
                        }
                    }
                };
                // ๅ?่ฝฝๆ?
                var treeObj = $.fn.zTree.init($("#dataTree"), setting, datas);
                treeObj.expandAll(true);
                // ๆ?ๅ?่ฝฝๅฎๆๅ่ฐ
                callback && callback(treeObj);
                // ้ๆฐ้ไธญ
                if (currentSelectId) {
                    var treeNode = treeObj.getNodeByParam("id", currentSelectId, null);
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
            // ่ทๅๅฐ็ถ็บงๅ็งฐ
            var parentNode = treeNode.getParentNode();
            if (parentNode) {
                treeNode.parentCode = parentNode.code;
            }
            // ่ฎพ็ฝฎ่กจๅๆฐๆฎ
            jsonData("viewForm", treeNode);
            form.render();
        }

        // ็ผ่พๅ่ฐ
        layui.editCallback = function(id) {
            currentSelectId = id;
            layui.refresh();
        };

        $("#addBtn").click(function () {
            // ่ทๅ้ไธญ็ๆฐๆฎ
            var treeObj = $.fn.zTree.getZTreeObj("dataTree");
            var nodes = treeObj.getSelectedNodes();
            var url = $(this).attr('data-url');
            //ๅฐiframeObjไผ?้็ป็ถ็บง็ชๅฃ,ๆง่กๆไฝๅฎๆๅทๆฐ
            parent.page("็ผ่พ", url, iframeObj, w = "700px", h = "560px", {isInsert: true, nodes: nodes});
            return false;
        });

        // ไฟฎๆน
        $("#editBtn").click(function () {
            // ่ทๅ้ไธญ็ๆฐๆฎ
            var treeObj = $.fn.zTree.getZTreeObj("dataTree");
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
            var treeObj = $.fn.zTree.getZTreeObj("dataTree");
            var nodes = treeObj.getSelectedNodes();
            if (nodes.length === 1) {
                //่ฏข้ฎๆก
                layer.confirm('็กฎๅฎๅ?้ค้ไธญๆฐๆฎๅ', {
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