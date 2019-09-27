<#import "../../spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>编辑</title>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/layui/css/layui.css" />
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/css/admin.css" />
    <style type="text/css">
        .layui-upload-img {
            width: 30px;
            height: 30px;
            margin-right: 10px;
            border-radius: 50%;
        }
    </style>
</head>
<body>
<div class="wrap-container">
    <form id="editForm" class="layui-form" style="width: 90%;padding-top: 20px;">
        <div class="layui-form-item">
            <label class="layui-form-label lay-required">用户编码</label>
            <div class="layui-input-block">
                <input type="hidden" name="id"/>
                <input type="text" name="code" required lay-verify="required" placeholder="请输入用户编码" autocomplete="off" class="layui-input"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label lay-required">用户姓名</label>
            <div class="layui-input-block">
                <input type="text" name="name" required lay-verify="required" placeholder="请输入用户姓名" autocomplete="off" class="layui-input"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">头像</label>
            <div class="layui-input-block">
                <img class="layui-upload-img" id="uploadImg" src=""/>
                <input type="hidden" name="bizCode"/>
                <input type="hidden" name="bizId"/>
                <input type="hidden" name="bizHeadPath"/>
                <#--<button type="button" class="layui-btn" id="uploadImgBtn">上传图片</button>-->
                <button id="uploadImgBtn" type="button" class="layui-btn"
                        data-url="/file/fileManage/toChooseImg">选择图片</button>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
                <input type="radio" name="state" value="1" title="启用" checked/>
                <input type="radio" name="state" value="0" title="禁用"/>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="editForm">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script src="<@spring.url''/>/static/jquery/jquery-3.3.1.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/admin/js/common.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    // edit
    layui.use(['form', 'jquery', 'upload'], function() {
        console.log('layui init');
        var form = layui.form,
                upload = layui.upload,
                $ = layui.jquery,
                iframeObj = $(window.frameElement).attr('name');

        var index = parent.layer.getFrameIndex(window.name);
        console.log('current page index:' + index);

        // 初始化方法
        layui.init = function (params) {
            // 处理初始值
            if (params.isInsert) {
                // 新增
            } else {
                // 修改
                jsonData("editForm", params.data);
                form.render();

                // 判断是否有头像
                if (params.data.bizCode && params.data.bizId) {
                    showUploadImg(params.data.bizCode, params.data.bizId, params.data.bizHeadPath);
                }
            }
        };
        
        function showUploadImg(bizCode, bizId, visitPath) {
            // 显示图片
            if (visitPath) {
                $("#uploadImg").attr("src", "/images" + visitPath);
            } else {
                $("#uploadImg").attr("src", "/file/fileInfo/download/" + bizCode + "/" + bizId);
            }
        }

        // 选择图片
        $("#uploadImgBtn").click(function () {
            var url = $(this).attr('data-url');
            //将iframeObj传递给父级窗口,执行操作完成刷新
            var params = {
                iframeObj: iframeObj,
                bizCode: 'user_head',
                bizId: new Date().getTime(),
                fileCode: 'garden_user_head'
            };
            parent.page("选择图片", url, iframeObj, w = "850px", h = "550px", params);
            return false;
        });

        layui.refresh = function() {
            console.log("user edit refresh");
        };

        layui.cropperCallback = function(data) {
            var file = data[0];
            $("input[name='bizCode']").val(file.bizCode);
            $("input[name='bizId']").val(file.bizId);
            $("input[name='bizHeadPath']").val(file.visitPath);
            showUploadImg(file.bizCode, file.bizId, file.visitPath);
        };

        // 图片上传
        /*upload.render({
            elem: '#uploadImgBtn',
            url: '/file/fileInfo/upload',
            data: {
                bizCode: 'user-img',
                bizId: (new Date().getTime())
            },
            done: function (data) {
                if (data.success) {
                    var file = data.data[0];
                    $("input[name='bizCode']").val(file.bizCode);
                    $("input[name='bizId']").val(file.bizId);
                    showUploadImg(file.bizCode, file.bizId);
                }
            },
            error: function () {
                layer.msg('上传失败', {icon: 2});
            }
        });*/

        // 监听提交
        form.on('submit(editForm)', function(data) {
            console.log(data.field);
            var id = data.field.id;
            var url = '/system/user/add';
            if (id) {
                url = '/system/user/edit';
            }
            ajaxPost(url, data.field, function (data) {
                if (data.success) {
                    layer.msg('操作成功', {icon: 1});
                    closePage();
                } else {
                    layer.msg(data.msg, {icon: 2});
                }
            });
            return false;
        });
    });
</script>
</body>
</html>