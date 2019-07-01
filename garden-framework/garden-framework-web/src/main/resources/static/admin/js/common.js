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
function page(title, url, obj, w, h, params) {
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

    // 窗口打开后
    function success(layero, index) {
        console.log("--- success function start ---");
        try {
            var iframe = window['layui-layer-iframe' + index];
            if (iframe && iframe.init) {
                console.log("--- 存在init方法，执行init方法 ---");
                iframe.init(params);
            }
        } catch (e) {
            console.error(e);
        }
        console.log("--- success function end ---");
    }
    // 窗口关闭
    function end() {
        console.log("--- end function start ---");
        try {
            if (window.frames[obj]) {
                var refresh = window.frames[obj].layui.refresh,
                    _refresh = window.frames[obj].layui._refresh;
                if (_refresh && refresh) {
                    console.log("--- 存在refresh方法，执行refresh方法 ---");
                    refresh();
                }
                window.frames[obj].layui._refresh = false;
            }
        } catch (e) {
            console.error(e);
        }
        console.log("--- end function end ---");
    }

    iframeObjName = obj;
    //如果手机端，全屏显示
    if (window.innerWidth <= 768) {
        var index = layer.open({
            type: 2,
            title: title,
            area: [320, h],
            fixed: false, //不固定
            content: url,
            success: success,
            end: end
        });
        layer.full(index);
    } else {
        var index = layer.open({
            type: 2,
            title: title,
            area: [w, h],
            fixed: false, //不固定
            content: url,
            success: success,
            end: end
        });
    }
}

/**
 * 关闭子页面
 */
function closePage() {
    setRefresh();
    var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
    parent.layer.close(index); // 再执行关闭
}

function setRefresh() {
    if (parent.window.frames[parent.iframeObjName]) {
        parent.window.frames[parent.iframeObjName].layui._refresh = true;
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
 * 设置layui select的值
 * @param field
 * @param value
 */
function setSelectValue(field, value) {
    field.value = value;
    var nextSibling = field.nextSibling;
    if (nextSibling && nextSibling.className && nextSibling.className.indexOf("layui-form-select") !== -1) {
        // 根据layui现有结构改造
        // select的下拉
        var options = field.options;
        // select下拉数据
        var optionsData = {};
        for (var i = 0; i < options.length; i++) {
            optionsData[options[i].value] = options[i].text;
        }
        var children = nextSibling.children;
        // 显示的input赋值
        children[0].children[0].value = optionsData[value];
        // layui下拉增加选中样式
        var dd = children[1].children;
        for (var j = 0; j < dd.length; j++) {
            if (dd[j].className && dd[j].className.indexOf("layui-this") !== -1) {
                dd[j].className = "";
            }
            if (dd[j].getAttribute("lay-value") === value) {
                dd[j].className = "layui-this";
            }
        }
    }
}

/**
 * 序列化表单
 * @param formId
 * @param data
 */
function jsonData(formId, data) {
    var form = document.getElementById(formId);
    if (!form) {
        console.warn("this element id [" + formId + "] is not exits");
        return;
    }

    var isDebug = false;

    // 递归查找
    function getChildren(element) {
        var elements = [];
        // 判断元素节点类型
        if (element.nodeType === 1) {
            // 判断元素是否携带name属性
            if (element.name) {
                elements.push(element);
            }
            // 获取元素子级节点数量
            var childElementCount = element.childElementCount;
            if (childElementCount) {
                // 获取元素子级节点
                var children = element.children;
                for (var i = 0; i < childElementCount; i++) {
                    elements = elements.concat(getChildren(children[i]));
                }
            }
        }
        return elements;
    }

    var elements = getChildren(form);

    if (data) {
        if (isDebug) {
            console.log("do set value");
        }

        function getElementsByName(name) {
            var names = [];
            for (var i = 0; i < elements.length; i++) {
                if (isDebug) {
                    console.log("elements[i].name：" + elements[i].name + "，name：" + name);
                }
                if (elements[i].name === name) {
                    names.push(elements[i]);
                }
            }
            return names;
        }

        function setValue(name, value) {
            var names = getElementsByName(name);
            for (var i = 0; i < names.length; i++) {
                setElementValue(names[i], value);
            }
        }

        function setElementValue(field, value) {
            if (isDebug) {
                console.log("field.nodeName：" + field.nodeName);
            }
            if ('INPUT' === field.nodeName) {
                if ('text' === field.type) {
                    field.value = value;
                } else if ('hidden' === field.type) {
                    field.value = value;
                } else if ('password' === field.type) {
                    field.value = value;
                } else if ('checkbox' === field.type) {
                    if (field.value === value) {
                        field.checked = true;
                    }
                } else if ('radio' === field.type) {
                    if (field.value === value) {
                        field.checked = true;
                    }
                }
            } else if ('TEXTAREA' === field.nodeName) {
                field.value = value;
            } else if ('SELECT' === field.nodeName) {
                // setSelectValue(field, value);
                field.value = value;
            }
        }

        for (var name in data) {
            if (data.hasOwnProperty(name)) {
                setValue(name, data[name]);
            }
        }
    } else {
        if (isDebug) {
            console.log("do get value");
        }

        data = {};

        function getValue(data, field) {
            var value = getElementValue(field);
            if (value) {
                data[field.name] = value;
            }
        }

        function getElementValue(field) {
            if (isDebug) {
                console.log("field.nodeName：" + field.nodeName);
            }
            var value = null;
            if ('INPUT' === field.nodeName) {
                if ('text' === field.type) {
                    value = field.value;
                } else if ('hidden' === field.type) {
                    value = field.value;
                } else if ('password' === field.type) {
                    value = field.value;
                } else if ('checkbox' === field.type) {
                    if (field.checked) {
                        value = field.value;
                    }
                } else if ('radio' === field.type) {
                    if (field.checked) {
                        value = field.value;
                    }
                }
            } else if ('TEXTAREA' === field.nodeName) {
                value = field.value;
            } else if ('SELECT' === field.nodeName) {
                value = field.value;
            }
            return value;
        }

        for (var i = 0; i < elements.length; i++) {
            var field = elements[i];
            getValue(data, field);
        }
    }
    return data;
}

function ajax(url, params) {

}

// 权限
var permissions = {
    "system.menu.add": 0x1,
    "system.menu.update": 0x1,
    "system.menu.refresh": 0x1
};
function hasPermission(key) {
    return permissions.hasOwnProperty(key);
}
function renderPermission() {
    var elements = $(".has-permission");
    $.each(elements, function (i, v) {
        var $v = $(v);
        var key = $v.attr("permission");
        if (key && !hasPermission(key)) {
            $v.remove();
        } else {
            $v.removeAttr("permission");
            $v.removeClass("has-permission");
        }
    });
}
renderPermission();
