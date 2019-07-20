<#import "../../spring.ftl" as spring />
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>功能列表</title>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/css/admin.css"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/ztree/zTreeStyle.css"/>
    <style type="text/css">
        .layui-card-header.layuiadmin-card-header-auto {
            padding: 10px;
            height: auto;
        }
        .layuiadmin-card-header-auto .layui-form-item {
            margin-bottom: 0;
        }
    </style>
</head>

<body>
<div class="page-content-wrap">
    <div class="layui-col-md3">
        <ul id="menuTree" class="ztree"></ul>
    </div>
    <div class="layui-card layui-col-md9">
        <form class="layui-form layui-card-header layuiadmin-card-header-auto search-form">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">功能名称</label>
                    <div class="layui-input-inline">
                        <input type="text" name="data.name" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">功能编码</label>
                    <div class="layui-input-inline">
                        <input type="text" name="data.code" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">功能地址</label>
                    <div class="layui-input-inline">
                        <input type="text" name="data.url" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">状态</label>
                    <div class="layui-input-inline">
                        <select name="data.state">
                            <option value="">请选择</option>
                            <option value="1">启用</option>
                            <option value="0">禁用</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <button type="button" class="layui-btn layuiadmin-btn-list search-btn">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                    </button>
                    <button type="reset" class="layui-btn reset-btn">
                        <i class="layui-icon layui-icon-refresh"></i>
                    </button>
                </div>
            </div>
        </form>

        <div class="layui-card-body">
            <div class="tool-btn">
                <button id="addBtn" type="button" class="layui-btn layui-btn-small layui-btn-primary hidden-xs layuiadmin-btn-list"
                        data-type="add"
                        data-url="/system/function/toEdit">添加</button>
                <button id="deleteBtn" type="button" class="layui-btn layui-btn-small layui-btn-primary hidden-xs layuiadmin-btn-list"
                        data-type="batchdel"
                        data-url="/system/function/delete">删除</button>
            </div>
            <script type="text/html" id="tableDataToolbar">
                <a class="layui-btn layui-btn-small layui-btn-primary hidden-xs layui-btn-xs"
                   lay-event="edit"
                   data-url="/system/function/toEdit">编辑</a>
                <a class="layui-btn layui-btn-small layui-btn-danger hidden-xs layui-btn-xs"
                   lay-event="del"
                   data-url="/system/function/delete">删除</a>
            </script>
            <table class="layui-hide" id="tableData" lay-filter="tableData"></table>
        </div>
    </div>
</div>
<script src="<@spring.url''/>/static/jquery/jquery-3.3.1.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/admin/js/common.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/ztree/jquery.ztree.all.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['jquery', 'table', 'layer', 'form'], function () {
        var $ = layui.jquery
                , layer = layui.layer
                , form = layui.form
                , table = layui.table
                //获取当前iframe的name值
                , iframeObj = $(window.frameElement).attr('name');

        var index = parent.layer.getFrameIndex(window.name);
        console.log('current page index:' + index);

        var config = {
            showIcon: true,
            showDisabled: true
        };

        // 当前选中节点
        var currentNode = null;

        function getTree() {

            ajaxPost('/system/menu/list', null, function (data) {
                if (data.success) {
                    callback && callback(data.data);
                } else {
                    parent.layer.msg(data.msg, {icon: 2});
                }
            });

            // 成功后的回调
            function callback(datas) {
                if (datas && datas.length) {
                    $.each(datas, function (i, v) {
                        var ns = [v.name];
                        if (v.icon && config.showIcon) {
                            ns.push('<i style="margin-left: 5px;" class="layui-icon ' + v.icon + '"></i>');
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

                            currentNode = treeNode;

                            // 刷新表格
                            table.reload("tableData", {
                                where: {
                                    "data.menuId": currentNode.id
                                },
                                url: '/system/function/pageList'
                            });
                        }
                    }
                };

                var treeObj = $.fn.zTree.init($("#menuTree"), setting, datas);
                treeObj.expandAll(true);
            }
        }
        // 新增，编辑后回调
        layui.refresh = function() {
            // 刷新表格
            table.reload("tableData", {
                where: {
                    "data.menuId": currentNode.id
                },
                url: '/system/function/pageList'
            });
        };
        getTree();

        function getTableData() {
            table.render({
                elem: '#tableData'
                //, url: '/system/function/pageList'
                , data: []
                , method: 'post'
                , page: {
                    limit: 20
                    , limits: [10, 20, 50, 200]
                }
                //,height: '350'
                , height: 'full-220'
                , done: function (response, curr, count) {
                    //$(".layui-card-body").resetTableHeight();
                }
                , request: {
                    pageName: 'pageSize.page'
                    , limitName: 'pageSize.pageSize'
                }
                , parseData: function (response) {
                    return {
                        "code": response.code,  //解析接口状态
                        "msg": response.msg,    //解析提示文本
                        "count": response.data ? response.data.rowTotal : 0,    //解析数据长度
                        "data": response.data ? response.data.rows : []         //解析数据列表
                    };
                }
                // ,data: datas
                , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                , cols: [[
                    {type: 'numbers'}
                    , {type: 'checkbox'}
                    , {field: 'name', width: 120, title: '功能名称'}
                    , {field: 'code', width: 120, title: '功能编码', sort: true}
                    , {field: 'url', width: 120, title: '功能地址', sort: true}
                    , {
                        field: 'state', width: 120, title: '状态', align: 'center', templet: function (row) {
                            if ("1" === row.state) {
                                return '<span class="layui-badge layui-bg-blue">启用</span>';
                            } else {
                                return '<span class="layui-badge layui-bg-gray">禁用</span>';
                            }
                        }
                    }
                    , {field: 'createdBy', width: 120, title: '创建人'}
                    , {
                        field: 'creationDate', width: 160, title: '创建时间', templet: function (row) {
                            return formatDate(row.creationDate);
                        }
                    }
                    , {field: 'updatedBy', width: 120, title: '修改人'}
                    , {
                        field: 'updationDate', width: 160, title: '修改时间', templet: function (row) {
                            return formatDate(row.updationDate);
                        }
                    }
                    , {fixed: 'right', title: '操作', toolbar: '#tableDataToolbar', width: 120}
                ]]
            });
        }
        getTableData();

        function formatDate(value) {
            if (value) {
                return new Date(value).format("yyyy-MM-dd hh:mm:ss");
            }
            return "";
        }

        $("#addBtn").click(function () {
            // 获取选中的数据
            var treeObj = $.fn.zTree.getZTreeObj("menuTree");
            var nodes = treeObj.getSelectedNodes();
            if (!nodes.length) {
                layer.msg('请选择一条记录', {icon: 7});
                return false;
            }
            console.log(nodes);

            var url = $(this).attr('data-url');
            //将iframeObj传递给父级窗口,执行操作完成刷新
            parent.page("编辑", url, iframeObj, w = "650px", h = "400px", {isInsert: true, nodes: nodes});
            return false;
        });

        // 删除
        $("#deleteBtn").click(function () {
            // 获取选中的数据
            var checkStatus = table.checkStatus('tableData')
                    ,data = checkStatus.data;
            if (data.length === 1) {
                var url = $(this).attr('data-url');
                layer.confirm('确定删除选中数据吗', function(index){
                    ajaxPost(url, {id: data[0].id}, function (data) {
                        if (data.success) {
                            layer.msg('操作成功', {icon: 1});
                            layui.refresh();
                        } else {
                            layer.msg(data.msg, {icon: 2});
                        }
                    });
                });
            } else {
                layer.msg('请选择一条记录', {icon: 7});
            }
            return false;
        });

        //监听行工具事件
        table.on('tool(tableData)', function(obj){
            var url = $(this).attr('data-url');
            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('确定删除选中数据吗', function(index){
                    ajaxPost(url, {id: data.id}, function (data) {
                        if (data.success) {
                            layer.msg('操作成功', {icon: 1});
                            layui.refresh();
                        } else {
                            layer.msg(data.msg, {icon: 2});
                        }
                    });
                    // layer.close(index);
                });
            } else if(obj.event === 'edit'){
                //将iframeObj传递给父级窗口,执行操作完成刷新
                parent.page("编辑", url, iframeObj, w = "650px", h = "400px", {isInsert: false, data: data});
            }
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