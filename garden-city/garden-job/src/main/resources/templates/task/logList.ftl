<#import "../spring.ftl" as spring />
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>任务日志列表</title>
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
    </style>
</head>

<body>
<div class="layui-fluid">
    <div class="layui-card">
        <form class="layui-form layui-card-header layuiadmin-card-header-auto search-form">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">类型</label>
                    <div class="layui-input-inline">
                        <select name="data.type">
                            <option value="">请选择</option>
                            <option value="1">成功</option>
                            <option value="0">失败</option>
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
                <button id="deleteBtn" type="button" class="layui-btn layui-btn-small layui-btn-primary hidden-xs layuiadmin-btn-list"
                        data-type="batchdel"
                        data-url="/job/taskLog/delete">删除</button>
            </div>
            <script type="text/html" id="tableDataToolbar">
                <a class="layui-btn layui-btn-small layui-btn-danger hidden-xs layui-btn-xs"
                   lay-event="del"
                   data-url="/job/taskLog/delete">删除</a>
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

        layui.init = function(params){
            console.log("iframeObj：" + iframeObj);
            console.log(params);

            initTable(params.data.id);
        };

        function initTable(taskId) {
            table.render({
                elem: '#tableData'
                ,url:'/job/taskLog/pageList'
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
                ,where: {
                    "data.taskId": taskId
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
                    ,{field:'type', width:70, title: '类型', align: 'center', templet: function (row) {
                            if ("0" === row.type) {
                                return '<span class="layui-badge layui-bg-gray">失败</span>';
                            } else if ("1" === row.type) {
                                return '<span class="layui-badge layui-bg-blue">成功</span>';
                            }
                            return row.type;
                        }}
                    ,{field:'responseContent', width:290, title: '响应内容'}
                    ,{field:'beginDate', width:160, title: '开始时间', templet: function (row) {
                            return formatDate(row.beginDate);
                        }
                    }
                    ,{field:'endDate', width:160, title: '结束时间', templet: function (row) {
                            return formatDate(row.endDate);
                        }
                    }
                    ,{field:'consumeTime', width:90, title: '执行耗时'}
                    ,{fixed: 'right', title:'操作', toolbar: '#tableDataToolbar', width:70}
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
