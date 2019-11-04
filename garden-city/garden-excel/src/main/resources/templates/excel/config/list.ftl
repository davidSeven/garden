<#import "../../spring.ftl" as spring />
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>导入导出管理</title>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/css/admin.css"/>
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
    </style>
</head>

<body>
<div class="layui-fluid">
    <div class="layui-card">
        <form class="layui-form layui-card-header layuiadmin-card-header-auto search-form">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"><@spring.message code="message.i18n.code"/></label>
                    <div class="layui-input-inline">
                        <input type="text" name="data.code" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">语言类型</label>
                    <div class="layui-input-inline">
                        <select name="data.languageType" class="lookup" lookupcode="LANGUAGE_TYPE">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">状态</label>
                    <div class="layui-input-inline">
                        <select name="data.state" class="lookup" lookupcode="COMMON_STATE">
                            <option value="">请选择</option>
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
                        data-url="/excel/config/toEdit">添加</button>
                <button id="deleteBtn" type="button" class="layui-btn layui-btn-small layui-btn-primary hidden-xs layuiadmin-btn-list"
                        data-type="batchdel"
                        data-url="/excel/config/delete">删除</button>
                <button id="reloadBtn" type="button" class="layui-btn layui-btn-small layui-btn-primary hidden-xs layuiadmin-btn-list"
                        data-type="reload"
                        data-url="/excel/config/reload">重新加载</button>
            </div>
            <script type="text/html" id="tableDataToolbar">
                <a class="layui-btn layui-btn-small layui-btn-primary hidden-xs layui-btn-xs"
                   lay-event="edit"
                   data-url="/excel/config/toEdit">编辑</a>
                <a class="layui-btn layui-btn-small layui-btn-danger hidden-xs layui-btn-xs"
                   lay-event="del"
                   data-url="/excel/config/delete">删除</a>
            </script>
            <table class="layui-hide" id="tableData" lay-filter="tableData"></table>
        </div>
    </div>
</div>
<script src="<@spring.url''/>/static/jquery/jquery-3.3.1.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/admin/js/common.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['jquery', 'table', 'layer', 'form'], function () {
        var table = layui.table,
                form = layui.form,
                iframeObj = $(window.frameElement).attr('name');

        table.render({
            elem: '#tableData'
            ,url:'/excel/config/pageList'
            ,method: 'post'
            ,page: {
                limit: 20
                ,limits: [10, 20, 50, 200]
            }
            //,height: '350'
            ,height: 'full-156'
            ,done: function (response, curr, count) {
                //$(".layui-card-body").resetTableHeight();
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
                ,{field:'code', width:200, title: getI18n("message.i18n.code")}
                ,{field:'value', width:200, title: '内容'}
                ,{field:'languageType', width:100, title: '语言类型', align: 'center', templet: function (row) {
                        if ("zh" === row.languageType) {
                            return '中文';
                        } else if ("en" === row.languageType) {
                            return '英文';
                        }
                        return row.state;
                    }}
                ,{field:'state', width:80, title: '状态', align: 'center', templet: function (row) {
                    if ("0" === row.state) {
                        return '<span class="layui-badge layui-bg-gray">禁用</span>';
                    } else if ("1" === row.state) {
                        return '<span class="layui-badge layui-bg-blue">启用</span>';
                    }
                    return row.state;
                }}
                ,{field:'createdBy', width:120, title: '创建人'}
                ,{field:'creationDate', width:160, title: '创建时间', templet: function (row) {
                        return formatDate(row.creationDate);
                    }
                }
                ,{field:'updatedBy', width:120, title: '修改人'}
                ,{field:'updationDate', width:160, title: '修改时间', templet: function (row) {
                        return formatDate(row.updationDate);
                    }
                }
                ,{fixed: 'right', title:'操作', toolbar: '#tableDataToolbar', width:120}
            ]]
        });

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
            // 将iframeObj传递给父级窗口,执行操作完成刷新
            parent.page("编辑", url, iframeObj, w = "650px", h = "450px", {isInsert: true});
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
                    // layer.close(index);
                });
            } else {
                layer.msg('请选择一条记录', {icon: 7});
            }
            return false;
        });

        // 重新加载国际化
        $("#reloadBtn").click(function () {
            var url = $(this).attr('data-url');
            ajaxPost(url, null, function (data) {
                if (data.success) {
                    layer.msg('操作成功', {icon: 1});
                    layui.refresh();
                } else {
                    layer.msg(data.msg, {icon: 2});
                }
            });
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
                parent.page("编辑", url, iframeObj, w = "650px", h = "450px", {isInsert: false, data: data});
            }
        });

        // 点击行选中
        $(document).on("click", ".layui-table-body table.layui-table tbody tr", function(){
            var obj = event ? event.target : event.srcElement;
            var tag = obj.tagName;
            var checkbox = $(this).find("td div.laytable-cell-checkbox div.layui-form-checkbox I");
            if(checkbox.length !== 0){
                if(tag === 'DIV') {
                    checkbox.click();
                }
            }
        });

        $(document).on("click", "td div.laytable-cell-checkbox div.layui-form-checkbox", function(e){
            e.stopPropagation();
        });

    });
</script>
</body>
</html>
