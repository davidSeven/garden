<#import "../spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>上传文件</title>
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/layui/css/layui.css" />
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/admin/css/admin.css" />
    <style type="text/css">
    </style>
</head>
<body>
<div class="wrap-container" style="padding: 15px;">
    <div class="layui-row">
        <div class="layui-form-item">
            <label class="layui-form-label">文件：</label>
            <div class="layui-input-block">
                <label class="layui-form-label" style="width: 100%; text-align: left;" id="fileName"></label>
            </div>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-form-item">
            <input type="file" id="cropper_imgUpload" name="file" style="display:none" accept="*/*">
            <button type="button" onclick="javascript:$('#cropper_imgUpload').click();" class="layui-btn layui-icon layui-icon-upload-drag">选择文件</button>

        </div>
    </div>
    <div class="layui-row oper-btn">
        <div class="layui-form-item">
            <button type="button" class="layui-btn layui-icon layui-icon-ok layui-btn-disabled" id="keep-save" disabled cropper-event="confirmSave">保存修改</button>
            <button id="closeBtn" type="button" class="layui-btn layui-btn-primary layui-icon layui-icon-close">取消上传</button>
        </div>
    </div>
</div>
<script src="<@spring.url''/>/static/jquery/jquery-3.3.1.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/admin/js/common.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    // edit
    layui.use(['element', 'layer', 'upload'], function() {
        var element = layui.element;
        var $ = layui.jquery;
        var layer = layui.layer;

        var index = parent.layer.getFrameIndex(window.name);
        console.log('current page index:' + index);

        var iframeObj = null;
        var fileCode = null;
        var bizCode = null;
        var bizId = null;
        // 初始化方法
        layui.init = function (params) {
            console.log("chooseImg init");

            // 父窗口对象名称
            if (params) {
                iframeObj = params.iframeObj;
                fileCode = params.fileCode;
                bizCode = params.bizCode;
                bizId = params.bizId;
            }
        };

        var changeFileName = "";
        var changeFile = null;

        // 找到上传图片的input标签绑定change事件
        $("#cropper_imgUpload").change(function () {
            $("#keep-save").removeAttr("disabled").removeClass("layui-btn-disabled");
            // 1. 创建一个读取文件的对象
            changeFileName = this.files[0].name;
            changeFile = this.files[0];
            $("#fileName").text(changeFileName);
        });

        $(".layui-btn").on('click', function () {
            var event = $(this).attr("cropper-event");
            //监听确认保存图像
            if (event === 'confirmSave') {
                var formData = new FormData();
                formData.append('bizCode', bizCode);
                formData.append('bizId', bizId);
                formData.append('fileCode', fileCode);
                // 获取到文件名
                formData.append('file', changeFile, changeFileName);
                $.ajax({
                    method: "post",
                    url: '/file/fileInfo/upload', //用于文件上传的服务器端请求地址
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (response) {
                        if (response.success) {
                            // 调用父级窗口的回调
                            if (parent.window.frames[iframeObj]) {
                                var cropperCallback = parent.window.frames[iframeObj].layui.cropperCallback;
                                cropperCallback && cropperCallback(response.data);
                            }
                            // 上传
                            layer.msg("上传成功", {
                                icon: 1,
                                time: 1000 // 默认三秒
                            }, function () {
                                // 回调关闭弹出层
                                closePage();
                            });
                        } else {
                            layer.alert(response.msg, {icon: 2});
                        }
                    },
                    error: function (response) {
                        layer.alert("网络异常", {icon: 2});
                    }
                });
            }
        });

        $("#closeBtn").click(function () {
            closePageNoRefresh();
        });
    });
</script>
</body>
</html>