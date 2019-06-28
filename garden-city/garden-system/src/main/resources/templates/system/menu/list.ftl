<#import "../../spring.ftl" as spring />
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>网站后台管理模版</title>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/layui/css/layui.css" />
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/css/admin.css" />
</head>

<body>
<div class="page-content-wrap">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <div class="layui-inline tool-btn">
                <button class="layui-btn layui-btn-small layui-btn-normal addBtn hidden-xs" data-url="menu-add.html"><i class="layui-icon">&#xe654;</i></button>
                <button class="layui-btn layui-btn-small layui-btn-warm listOrderBtn hidden-xs" data-url="menu-add.html"><i class="iconfont">&#xe656;</i></button>
            </div>
            <div class="layui-inline">
                <input type="text" name="title" required lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-inline">
                <select name="states" lay-filter="status">
                    <option value="">请选择一个状态</option>
                    <option value="010">显示</option>
                    <option value="021">隐藏</option>
                </select>
            </div>
            <button class="layui-btn layui-btn-normal" lay-submit="search">搜索</button>
        </div>
    </form>
    <div class="layui-form" id="table-list">

        <div id="test12" class="demo-tree-more"></div>
    </div>
</div>
<script src="<@spring.url''/>/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script>
    layui.use(['tree'], function(){
        var a = 1
                ,layer = layui.layer

                //模拟数据
                ,data = [{
                    title: '一级1'
                    ,id: 1
                    ,checked: true
                    ,spread: true
                    ,children: [{
                        title: '二级1-1 可允许跳转'
                        ,id: 3
                        ,children: [{
                            title: '三级1-1-3'
                            ,id: 23
                            ,children: [{
                                title: '四级1-1-3-1'
                                ,id: 24
                                ,children: [{
                                    title: '五级1-1-3-1-1'
                                    ,id: 30
                                },{
                                    title: '五级1-1-3-1-2'
                                    ,id: 31
                                }]
                            }]
                        },{
                            title: '三级1-1-1'
                            ,id: 7
                            ,children: [{
                                title: '四级1-1-1-1 可允许跳转'
                                ,id: 15
                            }]
                        },{
                            title: '三级1-1-2'
                            ,id: 8
                            ,children: [{
                                title: '四级1-1-2-1'
                                ,id: 32
                            }]
                        }]
                    },{
                        title: '二级1-2'
                        ,id: 4
                        ,spread: true
                        ,children: [{
                            title: '三级1-2-1'
                            ,id: 9
                            ,disabled: true
                        },{
                            title: '三级1-2-2'
                            ,id: 10
                        }]
                    },{
                        title: '二级1-3'
                        ,id: 20
                        ,children: [{
                            title: '三级1-3-1'
                            ,id: 21
                        },{
                            title: '三级1-3-2'
                            ,id: 22
                        }]
                    }]
                },{
                    title: '一级2'
                    ,id: 2
                    ,spread: true
                    ,children: [{
                        title: '二级2-1'
                        ,id: 5
                        ,spread: true
                        ,children: [{
                            title: '三级2-1-1'
                            ,id: 11
                        },{
                            title: '三级2-1-2'
                            ,id: 12
                        }]
                    },{
                        title: '二级2-2'
                        ,id: 6
                        ,children: [{
                            title: '三级2-2-1'
                            ,id: 13
                        },{
                            title: '三级2-2-2'
                            ,id: 14
                            ,disabled: true
                        }]
                    }]
                },{
                    title: '一级3'
                    ,id: 16
                    ,children: [{
                        title: '二级3-1'
                        ,id: 17
                        ,fixed: true
                        ,children: [{
                            title: '三级3-1-1'
                            ,id: 18
                        },{
                            title: '三级3-1-2'
                            ,id: 19
                        }]
                    },{
                        title: '二级3-2'
                        ,id: 27
                        ,children: [{
                            title: '三级3-2-1'
                            ,id: 28
                        },{
                            title: '三级3-2-2'
                            ,id: 29
                        }]
                    }]
                }];

        //基本演示
        var tree = layui.tree({
            elem: '#test12'
            ,nodes: data
            ,showCheckbox: true  //是否显示复选框
            ,id: 'demoId1'
            ,isJump: true //是否允许点击节点时弹出新窗口跳转
            ,click: function(obj){
                var data = obj.data;  //获取当前点击的节点数据
                layer.msg('状态：'+ obj.state + '<br>节点数据：' + JSON.stringify(data));
            }
        });

    });
</script>
</body>

</html>