<#import "../spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>Lookup列表</title>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/css/admin.css"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/ztree/zTreeStyle.css"/>
    <style type="text/css">
        body {
            background-color: #f2f2f2;
        }
        .layui-fluid {
            padding: 5px;
        }
        .layui-card-header.layuiadmin-card-header-auto {
            padding: 10px;
            height: auto;
        }
        .layuiadmin-card-header-auto .layui-form-item {
            margin-bottom: 0;
        }
        /* tab页高度 */
        .layui-tab-content .layui-tab-item {
            width: 100%;
            height: 100%;
            background: #fff;
        }

        .layui-fluid {
            padding: 0;
        }
    </style>
</head>

<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-tab-item layui-show">
            <form id="viewForm" class="layui-form layui-card-header layuiadmin-card-header-auto search-form">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">编号</label>
                        <div class="layui-input-inline">
                            <input type="text" name="code" autocomplete="off" class="layui-input" disabled="disabled"/>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">名称</label>
                        <div class="layui-input-inline">
                            <input type="text" name="name" autocomplete="off" class="layui-input" disabled="disabled"/>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">状态</label>
                        <div class="layui-input-inline">
                            <input type="radio" name="state" value="1" title="启用" checked disabled="disabled">
                            <input type="radio" name="state" value="0" title="禁用" disabled="disabled">
                        </div>
                    </div>
                </div>
            </form>

            <div class="layui-card-body">
                <div class="tool-btn">
                    <button id="addBtn" type="button" class="layui-btn layui-btn-small layui-btn-primary hidden-xs layuiadmin-btn-list"
                            data-type="add"
                            data-url="/lookup/lookupItem/toEdit">添加</button>
                    <button id="deleteBtn" type="button" class="layui-btn layui-btn-small layui-btn-primary hidden-xs layuiadmin-btn-list"
                            data-type="batchdel"
                            data-url="/lookup/lookupItem/delete">删除</button>
                </div>
                <script type="text/html" id="tableDataToolbar">
                    <a class="layui-btn layui-btn-small layui-btn-primary hidden-xs layui-btn-xs"
                       lay-event="edit"
                       data-url="/lookup/lookupItem/toEdit">编辑</a>
                    <a class="layui-btn layui-btn-small layui-btn-danger hidden-xs layui-btn-xs"
                       lay-event="del"
                       data-url="/lookup/lookupItem/delete">删除</a>
                </script>
                <table class="layui-hide" id="tableData" lay-filter="tableData"></table>
            </div>
        </div>
    </div>
</div>
<script src="<@spring.url''/>/static/jquery/jquery-3.3.1.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/admin/js/common.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['jquery', 'table', 'layer', 'form', 'element'], function () {
        var table = layui.table,
                form = layui.form,
                element = layui.element,
                iframeObj = $(window.frameElement).attr('name');

        var LOOKUP_CODE = "";

        layui.init = function(params){
            console.log("iframeObj：" + iframeObj);

            console.log(params);
            jsonData("viewForm", params);
            form.render();

            LOOKUP_CODE = params.code;

            initTable(params.code);
        };

        function initTable(parentCode) {
            table.render({
                elem: '#tableData'
                ,url:'/lookup/lookupItem/pageList'
                ,method: 'post'
                ,page: {
                    limit: 20
                    ,limits: [10, 20, 50, 200]
                }
                // ,height: 'full'
                ,height: 'full-146'
                ,done: function (response, curr, count) {
                    //$(".layui-card-body").resetTableHeight();
                }
                ,where: {
                    "data.parentCode": parentCode
                }
                ,request: {
                    pageName: 'pageSize.page'
                    , limitName: 'pageSize.pageSize'
                }
                ,parseData: function (response) {
                    return {
                        "code": response.code,  //解析接口状态
                        "msg": response.msg,    //解析提示文本
                        "count": response.data ? response.data.rowTotal : 0,    //解析数据长度
                        "data": response.data ? response.data.rows : []         //解析数据列表
                    };
                }
                // ,data: datas
                ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                ,cols: [[
                    {type:'numbers'}
                    ,{type:'checkbox'}
                    ,{field:'code', width:100, title: '编号'}
                    ,{field:'name', width:100, title: '名称', sort: false}
                    ,{field:'state', width:100, title: '状态', align: 'center', templet: function (row) {
                            if ("1" === row.state) {
                                return '<span class="layui-badge layui-bg-blue">启用</span>';
                            } else {
                                return '<span class="layui-badge layui-bg-gray">禁用</span>';
                            }
                        }}
                    ,{field:'createdBy', width:100, title: '创建人'}
                    ,{field:'creationDate', width:160, title: '创建时间', templet: function (row) {
                            return formatDate(row.creationDate);
                        }
                    }
                    ,{field:'updatedBy', width:100, title: '修改人'}
                    ,{field:'updationDate', width:160, title: '修改时间', templet: function (row) {
                            return formatDate(row.updationDate);
                        }
                    }
                    ,{fixed: 'right', title:'操作', toolbar: '#tableDataToolbar', width:150}
                ]]
            });
        }

        function formatDate(value) {
            if (value) {
                return new Date(value).format("yyyy-MM-dd hh:mm:ss");
            }
            return "";
        }

        layui.refresh = function() {
            table.reload("tableData");
        };

        // 新增
        $("#addBtn").click(function () {
            var url = $(this).attr('data-url');
            //将iframeObj传递给父级窗口,执行操作完成刷新
            top.page("编辑", url, iframeObj, w = "650px", h = "350px", {isInsert: true, parentCode: LOOKUP_CODE});
            return false;
        });

        // 修改
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
                    // layer.close(index);
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
                top.page("编辑", url, iframeObj, w = "650px", h = "350px", {isInsert: false, data: data});
            }
        });
    });
</script>
</body>
</html>
