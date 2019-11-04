<#import "../spring.ftl" as spring />
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
    <link rel="stylesheet" type="text/css" href="<@spring.url''/>/static/cropper/cropper.min.css" />
    <style type="text/css">
    </style>
</head>
<body>
<div class="wrap-container" style="padding: 15px;">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-xs9">
            <div id="readyimg"
                 style="height: 360px;width: 100%;border: 1px dashed black;background-color: rgb(247, 247, 247);">
                <img id="img" src="" alt="">
            </div>
        </div>
        <div class="layui-col-xs3">
            <div>预览</div>
            <div id="img-preview" style="width: 180px;height: 120px;overflow: hidden;border: 1px dashed black;">
            </div>
        </div>
    </div>
    <div class="layui-row" style="margin-bottom:20px; ">
        <input type="file" id="cropper_imgUpload" name="file" style="display:none" accept="image/*">
        <button type="button" onclick="javascript:$('#cropper_imgUpload').click();" class="layui-btn layui-icon layui-icon-upload-drag">选择图片</button>
        <button id="closeBtn" type="button" class="layui-btn layui-btn-primary layui-icon layui-icon-close">取消上传</button>
    </div>
    <div class="layui-row layui-hide oper-btn">
        <div class="layui-col-xs9">
            <button type="button" class="layui-btn layui-icon layui-icon-left" cropper-event="rotate" data-option="-15"
                    title="Rotate -90 degrees">向左旋转
            </button>
            <button type="button" class="layui-btn layui-icon layui-icon-right" cropper-event="rotate" data-option="15"
                    title="Rotate 90 degrees">向右旋转
            </button>
            <button type="button" class="layui-btn layui-icon layui-icon-refresh" cropper-event="reset"
                    title="reset-image">重置图片
            </button>
        </div>
        <div class="layui-col-xs2 layui-col-xs-offset1">
            <button type="button" class="layui-btn layui-btn-fluid" id="keep-save" cropper-event="confirmSave">保存修改
            </button>
        </div>
    </div>
</div>
<script src="<@spring.url''/>/static/jquery/jquery-3.3.1.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/admin/js/common.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/cropper/cropper.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/cropper/jquery-cropper.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<@spring.url''/>/static/image-compressor/image-compressor.min.js" type="text/javascript" charset="utf-8"></script>
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

        var imageEle = $("#img")
                , preview = '#img-preview'
                , file = $("input[name='file']")
                , uploadImageMaxSize = 2048 //文件上传大小限制
                , options = {
            aspectRatio: 1,
            viewMode: 1,
            preview: preview,
            dragMode: 'none',
            responsive: false,
            restore: false
            // cropBoxMovable:false,
            // cropBoxResizable:false,
        };

        var changeFileName = "";

        // 找到上传图片的input标签绑定change事件
        $("#cropper_imgUpload").change(function () {
            $(".oper-btn").removeClass("layui-hide");
            // 1. 创建一个读取文件的对象
            var fileReader = new FileReader();
            changeFileName = this.files[0].name;
            fileReader.readAsDataURL(this.files[0]);
            fileReader.onload = function () {
                // 2. 等上一步读完文件之后才 把图片加载到img标签中
                // imageEle.attr("src", fileReader.result); //图片链接（base64）
                imageEle.cropper(options);
                imageEle.cropper("replace", fileReader.result, false);
            };
        });

        $(".layui-btn").on('click', function () {
            var event = $(this).attr("cropper-event");
            //监听确认保存图像
            if (event === 'confirmSave') {
                imageEle.cropper("getCroppedCanvas").toBlob(function (crBlob) {
                    // 对图片进行压缩
                    var oldSize = crBlob.size;
                    var imageCompressor = new ImageCompressor();
                    imageCompressor.compress(crBlob, {quality: 0.7}).then(function (blob) {
                        var nowSize = blob.size;
                        console.log("quality: 0.7 compression ratio: " + (nowSize / oldSize).toFixed(2));

                        var filesize = (blob["size"] / 1024).toFixed(2);
                        if (filesize < uploadImageMaxSize) {
                            var formData = new FormData();
                            formData.append('bizCode', bizCode);
                            formData.append('bizId', bizId);
                            formData.append('fileCode', fileCode);
                            // 获取到文件名
                            formData.append('file', blob, changeFileName);
                            $.ajax({
                                method: "post",
                                url: '/file/fileInfo/upload', //用于文件上传的服务器端请求地址
                                data: formData,
                                processData: false,
                                contentType: false,
                                success: function (response) {
                                    if (response.success) {
                                        // 代开裁剪页面的回调函数
                                        // parent.cropperCallback && parent.cropperCallback(response.data, '{{index}}');

                                        // 调用父级窗口的回调
                                        if (parent.window.frames[iframeObj]) {
                                            var cropperCallback = parent.window.frames[iframeObj].layui.cropperCallback;
                                            cropperCallback && cropperCallback(response.data);
                                        }
                                        // 上传
                                        layer.msg("上传成功", {
                                            icon: 1,
                                            time: 2500 // 默认三秒
                                        }, function () {
                                            // 回调关闭弹出层
                                            var index = parent.layer.getFrameIndex(window.name);
                                            parent.layer.close(index);
                                        });
                                        $(".canvanslog").attr("src", response.data[0].fp_show);
                                    } else {
                                        layer.alert(response.msg, {icon: 2});
                                    }
                                },
                                error: function (response) {
                                    layer.alert("网络异常", {icon: 2});
                                }
                            });
                        } else {
                            layer.alert("上传图片大小不超过" + uploadImageMaxSize + "KB", {icon: 2})
                        }
                    });
                }, 'image/jpeg');
            } else if (event === 'rotate') {
                //监听旋转
                var option = $(this).attr('data-option');
                imageEle.cropper('rotate', option);
            } else if (event === 'reset') {
                //重设图片
                imageEle.cropper('reset');
            }
        });

        $("#closeBtn").click(function () {
            closePage();
        });
    });
</script>
</body>
</html>