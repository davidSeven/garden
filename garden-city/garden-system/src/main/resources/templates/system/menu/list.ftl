<#import "../../spring.ftl" as spring />
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>菜单列表</title>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/css/admin.css"/>
</head>

<body>
<div class="page-content-wrap">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <div class="layui-inline tool-btn">
                <button id="editBtn" class="layui-btn layui-btn-small layui-btn-normal hidden-xs"
                        data-url="system/menu/toEdit"><i class="layui-icon">&#xe654;</i></button>
                <button class="layui-btn layui-btn-small layui-btn-warm hidden-xs" data-url="menu-add.html"><i
                        class="iconfont">&#xe656;</i></button>
            </div>
        </div>
    </form>
    <div id="test12" class="demo-tree-more layui-col-md4"></div>
    <div class="layui-form layui-col-md8" id="table-list">
        <div class="layui-form-item">
            <label class="layui-form-label">输入框</label>
            <div class="layui-input-block">
                <input type="text" name="title" required lay-verify="required" placeholder="请输入标题" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码框</label>
            <div class="layui-input-inline">
                <input type="password" name="password" required lay-verify="required" placeholder="请输入密码"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">辅助文字</div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">选择框</label>
            <div class="layui-input-block">
                <select name="city" lay-verify="required">
                    <option value=""></option>
                    <option value="0">北京</option>
                    <option value="1">上海</option>
                    <option value="2">广州</option>
                    <option value="3">深圳</option>
                    <option value="4">杭州</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">复选框</label>
            <div class="layui-input-block">
                <input type="checkbox" name="like[write]" title="写作">
                <input type="checkbox" name="like[read]" title="阅读" checked>
                <input type="checkbox" name="like[dai]" title="发呆">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">开关</label>
            <div class="layui-input-block">
                <input type="checkbox" name="switch" lay-skin="switch">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">单选框</label>
            <div class="layui-input-block">
                <input type="radio" name="sex" value="男" title="男">
                <input type="radio" name="sex" value="女" title="女" checked>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">文本域</label>
            <div class="layui-input-block">
                <textarea name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>
</div>
<script src="<@spring.url''/>/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script>
    layui.config({
        base: '/static/admin/js/module/'
    }).extend({
        dialog: 'dialog'
    });
    layui.use(['jquery', 'tree', 'layer', 'form', 'dialog'], function () {
        var $ = layui.jquery
                , layer = layui.layer
                , dialog = layui.dialog
                //获取当前iframe的name值
                ,iframeObj = $(window.frameElement).attr('name')
                //模拟数据
                , data = [{
                    title: '一级1'
                    , id: 1
                    , checked: true
                    , spread: true
                    , children: [{
                        title: '二级1-1 可允许跳转'
                        , id: 3
                        , children: [{
                            title: '三级1-1-3'
                            , id: 23
                            , children: [{
                                title: '四级1-1-3-1'
                                , id: 24
                                , children: [{
                                    title: '五级1-1-3-1-1'
                                    , id: 30
                                }, {
                                    title: '五级1-1-3-1-2'
                                    , id: 31
                                }]
                            }]
                        }, {
                            title: '三级1-1-1'
                            , id: 7
                            , children: [{
                                title: '四级1-1-1-1 可允许跳转'
                                , id: 15
                            }]
                        }, {
                            title: '三级1-1-2'
                            , id: 8
                            , children: [{
                                title: '四级1-1-2-1'
                                , id: 32
                            }]
                        }]
                    }, {
                        title: '二级1-2'
                        , id: 4
                        , spread: true
                        , children: [{
                            title: '三级1-2-1'
                            , id: 9
                            , disabled: true
                        }, {
                            title: '三级1-2-2'
                            , id: 10
                        }]
                    }, {
                        title: '二级1-3'
                        , id: 20
                        , children: [{
                            title: '三级1-3-1'
                            , id: 21
                        }, {
                            title: '三级1-3-2'
                            , id: 22
                        }]
                    }]
                }, {
                    title: '一级2'
                    , id: 2
                    , spread: true
                    , children: [{
                        title: '二级2-1'
                        , id: 5
                        , spread: true
                        , children: [{
                            title: '三级2-1-1'
                            , id: 11
                        }, {
                            title: '三级2-1-2'
                            , id: 12
                        }]
                    }, {
                        title: '二级2-2'
                        , id: 6
                        , children: [{
                            title: '三级2-2-1'
                            , id: 13
                        }, {
                            title: '三级2-2-2'
                            , id: 14
                            , disabled: true
                        }]
                    }]
                }, {
                    title: '一级3'
                    , id: 16
                    , children: [{
                        title: '二级3-1'
                        , id: 17
                        , fixed: true
                        , children: [{
                            title: '三级3-1-1'
                            , id: 18
                        }, {
                            title: '三级3-1-2'
                            , id: 19
                        }]
                    }, {
                        title: '二级3-2'
                        , id: 27
                        , children: [{
                            title: '三级3-2-1'
                            , id: 28
                        }, {
                            title: '三级3-2-2'
                            , id: 29
                        }]
                    }]
                }];

        // 加载树结构
        layui.tree({
            elem: '#test12'
            , nodes: data
            , showCheckbox: true  //是否显示复选框
            , id: 'demoId1'
            , isJump: true //是否允许点击节点时弹出新窗口跳转
            , click: function (obj) {
                var data = obj.data;  //获取当前点击的节点数据
                layer.msg('状态：' + obj.state + '<br>节点数据：' + JSON.stringify(data));
            }
        });

        $("#editBtn").click(function () {
            var url=$(this).attr('data-url');
            //将iframeObj传递给父级窗口,执行操作完成刷新
            parent.page("编辑", url, iframeObj, w = "700px", h = "560px");
            return false;
        });

        console.log(parent);
        console.log(parent.page);
    });
</script>
</body>

</html>