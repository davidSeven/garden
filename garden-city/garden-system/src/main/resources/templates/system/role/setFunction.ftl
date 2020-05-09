<#import "../../spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>权限设置</title>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/css/admin.css"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/ztree/zTreeStyle.css"/>
    <style type="text/css">

    </style>
</head>
<body>
<div class="page-content-wrap" style="padding: 10px;">
    <div class="layui-row">
        <div class="layui-col-xs5 layui-col-sm5">
            <ul id="organizationTree" class="ztree" style="height: 450px; overflow: scroll; border: 1px solid #CCC"></ul>
        </div>
        <div class="layui-col-xs7 layui-col-sm7">
            <form id="fieldForm" class="layui-form">
                <input type="hidden" name="functionId"/>
                <div class="layui-card">
                    <div class="layui-card-header">权限字段</div>
                    <div id="permissionDiv" class="layui-card-body">
                        <span class="layui-badge layui-bg-gray">没有可以配置的字段</span>
                    </div>
                </div>
                <div class="layui-card">
                    <div class="layui-card-header">敏感字段</div>
                    <div id="sensitiveDiv" class="layui-card-body">
                        <span class="layui-badge layui-bg-gray">没有可以配置的字段</span>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="layui-row">
        <form id="editForm" class="layui-form" style="padding-top: 20px;">
            <div class="layui-form-item">
                <div class="layui-input-block" style="margin-left: 0; text-align: center;">
                    <button class="layui-btn layui-btn-normal" lay-submit lay-filter="editForm">保存</button>
                    <button type="button" class="layui-btn layui-btn-primary close-btn">关闭</button>
                </div>
            </div>
        </form>
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
        var roleId = null;

        function getTree() {
            var orgList = [];
            ajaxPost('/system/role/getMenuFunction', {id: roleId}, function (data) {
                if (data.success) {
                    orgList = data.data;
                    callback(orgList);
                } else {
                    parent.layer.msg(data.msg, {icon: 2});
                }
            });

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
                        chkboxType: { "Y": "ps", "N": "ps" }
                    },
                    callback: {
                        onClick: function (event, treeId, treeNode) {
                            console.log(treeNode);
                            layui.getRoleFunctionField(treeNode.id);
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

        // 初始化方法
        layui.init = function (params) {
            roleId = params.data[0].id;

            // 赋值之后再去查询
            layui.refresh();
        };

        // 字段缓存信息
        var roleFunctionFieldMap = {
            // functionId: [ {} ]
        };

        // 处理字段信息
        layui.handlerFieldMap = function() {
            var $input = $("input[name='functionId']");
            var currentFunctionId = $input.val();
            if (null != currentFunctionId) {
                var data = jsonData("fieldForm");
                console.log(data);
                var fieldList = [];
                for (var key in data) {
                    if (!data.hasOwnProperty(key)) {
                        continue;
                    }
                    var values = data[key];
                    var i;
                    if (key.indexOf("p_") === 0) {
                        for (i = 0; i < values.length; i++) {
                            fieldList.push({
                                fieldId: values[i],
                                type: 10
                            });
                        }
                    } else if (key.indexOf("s_") === 0) {
                        for (i = 0; i < values.length; i++) {
                            fieldList.push({
                                fieldId: values[i],
                                type: 20
                            });
                        }
                    }
                }
                roleFunctionFieldMap[currentFunctionId] = fieldList;
            }
        };

        layui.getRoleFunctionField = function (functionId) {
            layui.handlerFieldMap();
            var $input = $("input[name='functionId']");
            // 设置functionId
            $input.val(functionId);
            function getFieldMap(fieldList, type) {
                if (fieldList && fieldList.length) {
                    var map = {};
                    for (var i = 0; i < fieldList.length; i++) {
                        var field = fieldList[i];
                        if (field.type === type) {
                            map[field.fieldId] = 1;
                        }
                    }
                    return map;
                }
            }

            // 查询字段相关信息
            ajaxPost('/system/role/getRoleFunctionField', {roleId: roleId, functionId: functionId}, function (data) {
                if (data.success) {
                    var map = data.data;
                    var permissionList = map[10];
                    var sensitiveList = map[20];
                    var $permissionDiv = $("#permissionDiv");
                    // 获取缓存数据
                    var fieldList = roleFunctionFieldMap[functionId];
                    console.log(fieldList);
                    var fieldMap10 = getFieldMap(fieldList, 10);
                    var fieldMap20 = getFieldMap(fieldList, 20);
                    var checked;
                    if (permissionList && permissionList.length) {
                        $permissionDiv.empty();
                        for (var i = 0; i < permissionList.length; i++) {
                            var p_field = permissionList[i];
                            checked = p_field.checked;
                            if (fieldMap10) {
                                checked = fieldMap10.hasOwnProperty(p_field.functionFieldId);
                            }
                            $permissionDiv.append('<input type="checkbox" name="p_fields" value="'+p_field.functionFieldId+'" lay-skin="primary" title="'+p_field.name+'" '+(checked ? 'checked=""':'')+'>');
                        }
                    } else {
                        $permissionDiv.html('<span class="layui-badge layui-bg-gray">没有可以配置的字段</span>');
                    }
                    var $sensitiveDiv = $("#sensitiveDiv");
                    if (sensitiveList && sensitiveList.length) {
                        $sensitiveDiv.empty();
                        for (var j = 0; j < sensitiveList.length; j++) {
                            var s_field = sensitiveList[j];
                            checked = s_field.checked;
                            if (fieldMap20) {
                                checked = fieldMap20.hasOwnProperty(s_field.functionFieldId);
                            }
                            $sensitiveDiv.append('<input type="checkbox" name="s_fields" value="'+s_field.functionFieldId+'" lay-skin="primary" title="'+s_field.name+'" '+(checked ? 'checked=""':'')+'>');
                        }
                    } else {
                        $sensitiveDiv.html('<span class="layui-badge layui-bg-gray">没有可以配置的字段</span>');
                    }
                    form.render();
                } else {
                    parent.layer.msg(data.msg, {icon: 2});
                }
            });
        };

        //监听提交
        form.on('submit(editForm)', function(data) {

            var treeObj = $.fn.zTree.getZTreeObj("organizationTree");
            var nodes = treeObj.getCheckedNodes(true);
            console.log(nodes);
            var voList = [];
            if (nodes && nodes.length) {
                $.each(nodes, function (i, v) {
                   voList.push({
                       type: v.type,
                       id: v.id
                   });
                });
            }
            var voFieldList = [];
            layui.handlerFieldMap();
            console.log(roleFunctionFieldMap);
            for(var key in roleFunctionFieldMap) {
                if (!roleFunctionFieldMap.hasOwnProperty(key)) {
                    continue;
                }
                var fieldList = roleFunctionFieldMap[key];
                if (fieldList && fieldList.length) {
                    for (var i = 0; i < fieldList.length; i++) {
                        var field = fieldList[i];
                        voFieldList.push({
                            roleId: roleId,
                            functionId: key,
                            functionFieldId: field.fieldId,
                            type: field.type
                        });
                    }
                }
            }
            // 保存对象
            var vo = {
                roleId: roleId,
                voList: voList,
                fieldList: voFieldList
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