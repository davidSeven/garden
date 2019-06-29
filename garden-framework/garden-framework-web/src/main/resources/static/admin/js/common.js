layui.config({
    base: '/static/admin/js/module/'
}).extend({
    dialog: 'dialog'
});

layui.use(['form', 'jquery', 'laydate', 'layer', 'laypage', 'dialog', 'element'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        dialog = layui.dialog;
    //获取当前iframe的name值
    var iframeObj = $(window.frameElement).attr('name');
    //全选
    form.on('checkbox(allChoose)', function (data) {
        var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
        child.each(function (index, item) {
            item.checked = data.elem.checked;
        });
        form.render('checkbox');
    });
    //渲染表单
    form.render();
    //顶部添加
    $('.addBtn').click(function () {
        var url = $(this).attr('data-url');
        //将iframeObj传递给父级窗口,执行操作完成刷新
        parent.page("菜单添加", url, iframeObj, w = "700px", h = "620px");
        return false;
    }).mouseenter(function () {
        dialog.tips('添加', '.addBtn');
    });
    //顶部排序
    $('.listOrderBtn').click(function () {
        var url = $(this).attr('data-url');
        dialog.confirm({
            message: '您确定要进行排序吗？',
            success: function () {
                layer.msg('确定了')
            },
            cancel: function () {
                layer.msg('取消了')
            }
        });
        return false;
    }).mouseenter(function () {
        dialog.tips('批量排序', '.listOrderBtn');
    });
    //顶部批量删除
    $('.delBtn').click(function () {
        var url = $(this).attr('data-url');
        dialog.confirm({
            message: '您确定要删除选中项',
            success: function () {
                layer.msg('删除了')
            },
            cancel: function () {
                layer.msg('取消了')
            }
        });
        return false;
    }).mouseenter(function () {
        dialog.tips('批量删除', '.delBtn');
    });
    //列表添加
    $('#table-list').on('click', '.add-btn', function () {
        var url = $(this).attr('data-url');
        //将iframeObj传递给父级窗口
        parent.page("菜单添加", url, iframeObj, w = "700px", h = "620px");
        return false;
    });
    //列表删除
    $('#table-list').on('click', '.del-btn', function () {
        var url = $(this).attr('data-url');
        var id = $(this).attr('data-id');
        dialog.confirm({
            message: '您确定要进行删除吗？',
            success: function () {
                layer.msg('确定了')
            },
            cancel: function () {
                layer.msg('取消了')
            }
        });
        return false;
    });
    //列表跳转
    $('#table-list,.tool-btn').on('click', '.go-btn', function () {
        var url = $(this).attr('data-url');
        var id = $(this).attr('data-id');
        window.location.href = url + "?id=" + id;
        return false;
    });
    //编辑栏目
    $('#table-list').on('click', '.edit-btn', function () {
        var That = $(this);
        var id = That.attr('data-id');
        var url = That.attr('data-url');
        //将iframeObj传递给父级窗口
        parent.page("菜单编辑", url + "?id=" + id, iframeObj, w = "700px", h = "620px");
        return false;
    })
});

/**
 * 控制iframe窗口的刷新操作
 */
var iframeObjName;

//父级弹出页面
function page(title, url, obj, w, h) {
    if (!title) {
        title = false;
    }
    if (!url) {
        url = "404.html";
    }
    if (!w) {
        w = '700px';
    }
    if (!h) {
        h = '350px';
    }
    iframeObjName = obj;
    //如果手机端，全屏显示
    if (window.innerWidth <= 768) {
        var index = layer.open({
            type: 2,
            title: title,
            area: [320, h],
            fixed: false, //不固定
            content: url
        });
        layer.full(index);
    } else {
        var index = layer.open({
            type: 2,
            title: title,
            area: [w, h],
            fixed: false, //不固定
            content: url
        });
    }
}

/**
 * 刷新子页,关闭弹窗
 */
function refresh() {
    //根据传递的name值，获取子iframe窗口，执行刷新
    if (window.frames[iframeObjName]) {
        window.frames[iframeObjName].location.reload();
    } else {
        window.location.reload();
    }
    layer.closeAll();
}

/**
 * 序列化表单
 * @param formId
 * @param data
 */
function jsonData(formId, data) {
    var form = document.getElementById(formId);
    if (data) {
        function setValue(name, value) {

        }
        for (var name in data) {
            if (data.hasOwnProperty(name)) {
                setValue(name, data[name]);
            }
        }
    } else {
        data = {};
        function setValue(data, field) {
            var value = getValue(field);
            if (value) {
                data[field.name] = value;
            }
        }
        function getValue(field) {
            var value = null;
            if ('input' === field.type) {
                value = field.value;
            } else if ('checkbox' === field.type) {
                if (field.checked) {
                    value = field.value;
                }
            } else if ('radio' === field.type) {
                if (field.checked) {
                    value = field.value;
                }
            } else if ('textarea' === field.type) {
                value = field.value;
            }
            return value;
        }
        for (var i = 0; i < form.elements.length; i++) {
            var field = form.elements[i];
            setValue(data, field);
        }
    }
    return data;
}