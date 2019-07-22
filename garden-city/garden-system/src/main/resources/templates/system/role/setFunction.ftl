<#import "../../spring.ftl" as spring />
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>权限设置</title>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/css/admin.css"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/ztree/zTreeStyle.css"/>
    <style type="text/css">

    </style>
</head>

<body>
<div class="layui-fluid" style="padding-top: 15px">
    <div class="layui-col-md12">
        <ul id="organizationTree" class="ztree" style="height: 450px; overflow: scroll; border: 1px solid #333;"></ul>
    </div>
    <form id="editForm" class="layui-form" style="padding-top: 20px;">
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left: 0; text-align: center;">
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="editForm">保存</button>
                <button type="button" class="layui-btn layui-btn-primary close-btn">关闭</button>
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
        var roleId = null;

        function getTree() {

            var checkList = [];
            ajaxPost('/system/role/getRoleFunction', {roleId: roleId}, function (data) {
                if (data.success) {
                    checkList = data.data;
                    doCallback();
                } else {
                    parent.layer.msg(data.msg, {icon: 2});
                }
            });

            var orgList = [];
            ajaxPost('/system/organization/list', null, function (data) {
                if (data.success) {
                    orgList = data.data;
                    doCallback();
                } else {
                    parent.layer.msg(data.msg, {icon: 2});
                }
            });

            var dataInit = 0;
            function doCallback() {
                dataInit ++;
                if (dataInit === 2) {

                    // 处理选中
                    if (orgList && orgList.length && checkList && checkList.length) {
                        var checkMap = {};
                        $.each(checkList, function (i, v) {
                           checkMap[v.functionId] = true;
                        });

                        $.each(orgList, function (i, v) {
                           if (checkMap.hasOwnProperty(v.id)) {
                               v.checked = true;
                           }
                        });
                    }

                    callback(orgList);
                }
            }

            // 成功后的回调
            function callback(datas) {
                if (datas && datas.length) {
                    $.each(datas, function (i, v) {
                        var ns = [v.name];
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
                    check: {
                        enable: true,
                        chkStyle: "checkbox",
                        chkboxType: { "Y": "p", "N": "ps" }
                    },
                    callback: {
                        onClick: function (event, treeId, treeNode) {
                            console.log(treeNode);
                        }
                    }
                };

                var treeObj = $.fn.zTree.init($("#organizationTree"), setting, datas);
                treeObj.expandAll(true);
            }
        }
        layui.refresh = function() {
            getTree();
        };
        layui.refresh();

        // 初始化方法
        layui.init = function (params) {
            roleId = params.data[0].id;
        };

        //监听提交
        form.on('submit(editForm)', function(data) {

            var treeObj = $.fn.zTree.getZTreeObj("organizationTree");
            var nodes = treeObj.getCheckedNodes(true);
            console.log(nodes);
            var roleFunctionList = [];
            if (nodes && nodes.length) {
                $.each(nodes, function (i, v) {
                   roleFunctionList.push({ functionId: v.id });
                });
            }
            var vo = {
                roleId: roleId,
                roleFunctionList: roleFunctionList
            };
            var url = '/system/role/saveRoleFunction';
            ajaxPost(url, {voJson: JSON.stringify(vo)}, function (data) {
                if (data.success) {
                    parent.layer.msg('操作成功', {icon: 1});
                    closePage();
                } else {
                    parent.layer.msg(data.msg, {icon: 2});
                }
            });
            return false;
        });
    });
</script>
</body>

</html>