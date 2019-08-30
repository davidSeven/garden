<#import "../../spring.ftl" as spring />
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>设置角色</title>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/css/admin.css"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/ztree/zTreeStyle.css"/>
    <style type="text/css">
        html, body {
            height: 100%;
        }
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
                    <label class="layui-form-label">角色编号</label>
                    <div class="layui-input-inline">
                        <input type="text" name="data.code" placeholder="请输入" autocomplete="off" class="layui-input">
                        <input type="hidden" name="data.state" value="1" />
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">角色名称</label>
                    <div class="layui-input-inline">
                        <input type="text" name="data.name" placeholder="请输入" autocomplete="off" class="layui-input">
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
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="submitBtn">确定</button>
                <button type="button" class="layui-btn layui-btn-primary close-btn">关闭</button>
            </div>
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

        var userParams = null;
        // 初始化方法
        layui.init = function (params) {
            userParams = params;
        };

        table.render({
            elem: '#tableData'
            ,url:'/system/role/pageList'
            ,method: 'post'
            ,page: {
                limit: 20
                ,limits: [10, 20, 50, 200]
            }
            // ,height: '300'
            ,height: 'full-160'
            ,done: function (response, curr, count) {
                //$(".layui-card-body").resetTableHeight();
            }
            ,where: {
                'data.state': '1'
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
            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {type:'numbers'}
                ,{type:'radio'}
                ,{field:'code', width:120, title: '角色编号'}
                ,{field:'name', width:200, title: '角色名称', sort: false}
                ,{field:'state', width:120, title: '状态', align: 'center', templet: function (row) {
                    if ("1" === row.state) {
                        return '<span class="layui-badge layui-bg-blue">启用</span>';
                    } else {
                        return '<span class="layui-badge layui-bg-gray">禁用</span>';
                    }
                }}
            ]]
        });

        layui.refresh = function() {
            table.reload("tableData");
        };

        // 监听提交
        form.on('submit(submitBtn)', function() {
            // 获取选中的数据
            var checkStatus = table.checkStatus('tableData')
                    ,data = checkStatus.data;
            if (data.length === 1) {
                ajaxPost('/system/user/setRole', {userId: userParams.id, roleId: data[0].id}, function (data) {
                    if (data.success) {
                        layer.msg('操作成功', {icon: 1});
                        closePage();
                    } else {
                        layer.msg(data.msg, {icon: 2});
                    }
                });
            } else {
                layer.msg('请选择一条记录', {icon: 7});
            }
            return false;
        });

        // 点击行选中
        $(document).on("click", ".layui-table-body table.layui-table tbody tr", function(){
            var obj = event ? event.target : event.srcElement;
            var tag = obj.tagName;
            var radio = $(this).find("td div.laytable-cell-radio div.layui-form-radio I");
            if(radio.length !== 0){
                if(tag === 'DIV') {
                    radio.click();
                }
            }
        });

        $(document).on("click", "td div.laytable-cell-radio div.layui-form-radio", function(e){
            e.stopPropagation();
        });
    });
</script>
</body>
</html>
