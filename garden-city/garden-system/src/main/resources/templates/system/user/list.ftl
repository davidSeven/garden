<#import "../../spring.ftl" as spring />
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>用户列表</title>
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
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">文章ID</label>
                    <div class="layui-input-inline">
                        <input type="text" name="id" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">作者</label>
                    <div class="layui-input-inline">
                        <input type="text" name="author" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">标题</label>
                    <div class="layui-input-inline">
                        <input type="text" name="title" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">文章标签</label>
                    <div class="layui-input-inline">
                        <select name="label">
                            <option value="">请选择标签</option>
                            <option value="0">美食</option>
                            <option value="1">新闻</option>
                            <option value="2">八卦</option>
                            <option value="3">体育</option>
                            <option value="4">音乐</option>
                        </select><div class="layui-unselect layui-form-select"><div class="layui-select-title"><input type="text" placeholder="请选择标签" value="" readonly="" class="layui-input layui-unselect"><i class="layui-edge"></i></div><dl class="layui-anim layui-anim-upbit"><dd lay-value="" class="layui-select-tips">请选择标签</dd><dd lay-value="0" class="">美食</dd><dd lay-value="1" class="">新闻</dd><dd lay-value="2" class="">八卦</dd><dd lay-value="3" class="">体育</dd><dd lay-value="4" class="">音乐</dd></dl></div>
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn layuiadmin-btn-list" lay-submit="" lay-filter="LAY-app-contlist-search">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                    </button>
                </div>
            </div>
        </div>

        <div class="layui-card-body">
            <div class="tool-btn">
                <button id="addBtn" class="layui-btn layuiadmin-btn-list" data-type="add">添加</button>
                <button id="deleteBtn" class="layui-btn layuiadmin-btn-list" data-type="batchdel">删除</button>
            </div>
            <table class="layui-hide" id="tableData"></table>
        </div>
    </div>
</div>
<script src="<@spring.url''/>/static/jquery/jquery-3.3.1.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/admin/js/common.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['jquery', 'table', 'layer', 'form'], function () {
        var table = layui.table;

        var datas = [{"id":10000,"username":"user-0","sex":"女","city":"城市-0","sign":"签名-0","experience":255,"logins":24,"wealth":82830700,"classify":"作家","score":57},{"id":10001,"username":"user-1","sex":"男","city":"城市-1","sign":"签名-1","experience":884,"logins":58,"wealth":64928690,"classify":"词人","score":27},{"id":10002,"username":"user-2","sex":"女","city":"城市-2","sign":"签名-2","experience":650,"logins":77,"wealth":6298078,"classify":"酱油","score":31},{"id":10003,"username":"user-3","sex":"女","city":"城市-3","sign":"签名-3","experience":362,"logins":157,"wealth":37117017,"classify":"诗人","score":68},{"id":10004,"username":"user-4","sex":"男","city":"城市-4","sign":"签名-4","experience":807,"logins":51,"wealth":76263262,"classify":"作家","score":6},{"id":10005,"username":"user-5","sex":"女","city":"城市-5","sign":"签名-5","experience":173,"logins":68,"wealth":60344147,"classify":"作家","score":87},{"id":10006,"username":"user-6","sex":"女","city":"城市-6","sign":"签名-6","experience":982,"logins":37,"wealth":57768166,"classify":"作家","score":34},{"id":10007,"username":"user-7","sex":"男","city":"城市-7","sign":"签名-7","experience":727,"logins":150,"wealth":82030578,"classify":"作家","score":28},{"id":10008,"username":"user-8","sex":"男","city":"城市-8","sign":"签名-8","experience":951,"logins":133,"wealth":16503371,"classify":"词人","score":14},{"id":10009,"username":"user-9","sex":"女","city":"城市-9","sign":"签名-9","experience":484,"logins":25,"wealth":86801934,"classify":"词人","score":75}];

        table.render({
            elem: '#tableData'
            ,url:'/system/user/pageList'
            ,method: 'post'
            ,page: {
                limit: 10
                ,limits: [10, 20, 50, 200]
            }
            //,height: '350'
            ,height: 'full-200'
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
                {field:'code', width:120, title: '用户编号'}
                ,{field:'name', width:120, title: '用户姓名', sort: true}
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
            ]]
        });

        function formatDate(value) {
            if (value) {
                return new Date(value).format("yyyy-MM-dd hh:mm:ss");
            }
            return "";
        }

        setTimeout(function () {
            table.reload("tableData", {
                where: {
                    "data.id": "123"
                }
            });
        }, 2000);
    });
</script>
</body>
</html>
