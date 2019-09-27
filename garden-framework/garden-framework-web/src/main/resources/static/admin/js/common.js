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


// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.format = function(fmt) {
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
};

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
            // 延迟10ms，等待页面加载完成
            setTimeout(function () {
                // 调用layui.init
                if (iframe && iframe.layui && iframe.layui.init) {
                    console.log("--- 存在layui.init方法，执行layui.init方法 ---");
                    iframe.layui.init(params);
                }
            }, 10);
        } catch (e) {
            console.error(e);
        }
        console.log("--- success function end ---");
    }
    // 窗口关闭
    function end() {
        console.log("--- end function start ---");
        try {
            // 执行页面刷新
            function doRefresh(frame) {
                var refresh = frame.layui.refresh,
                    _refresh = frame.layui._refresh;
                if (_refresh && refresh) {
                    console.log("--- 存在refresh方法，执行refresh方法 ---");
                    refresh();
                }
                frame.layui._refresh = false;
            }
            if (window.frames[obj]) {
                doRefresh(window.frames[obj]);
            } else {
                // 对子窗体进行二次检索
                var length = window.frames.length;
                for (var i = 0; i < length; i++) {
                    if (window.frames[i].frames && window.frames[i].frames[obj]) {
                        doRefresh(window.frames[i].frames[obj]);
                        break;
                    }
                }
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
    var parentObjName = parent.iframeObjName;
    var parentWindow = parent.window;
    if (parentWindow.frames[parentObjName]) {
        parentWindow.frames[parentObjName].layui._refresh = true;
    } else {
        // 对子窗体进行二次检索
        var length = parentWindow.frames.length;
        for (var i = 0; i < length; i++) {
            if (parentWindow.frames[i].frames && parentWindow.frames[i].frames[parentObjName]) {
                parentWindow.frames[i].frames[parentObjName].layui._refresh = true;
                break;
            }
        }
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
    var form;
    if (formId.startsWith(".")) {
        form = document.getElementsByClassName(formId.substr(1))[0];
    } else {
        form = document.getElementById(formId);
    }
    if (!form) {
        console.warn("this element [" + formId + "] is not exits");
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

function ajaxPost(url, params, successFn, errorFn) {
    // loading
    var loadingIndex = parent.layer.msg('加载中', {
        icon: 16
        ,shade: 0.2
        ,time: 0
    });
    // ajax
    $.ajax({
        // async: false, // 同步
        type: 'post',
        url: url,
        data: params,
        dataType: "json",
        success: function (data) {
            successFn && successFn(data);
        },
        error: function () {
            console.log(arguments);
            errorFn && errorFn();
        },
        complete: function () {
            console.log(arguments);
            parent.layer.close(loadingIndex);
        }
    });
}

// 权限
var permissions = {
};
function initPermissions() {
    if (window.top === window) {
        // 加载权限数据
        permissions = {
            "system.menu.add": 0x1,
            "system.menu.update": 0x1,
            "system.menu.delete": 0x1,
            "system.menu.refresh": 0x1
        };
    } else {
        // 获取顶级页面已经获取到的数据
        permissions = window.top.permissions;
    }
}
initPermissions();
function hasPermissions(key) {
    if (!key) {
        return false;
    }
    key = key.replace(" ", "");
    var length = key.length;
    var keys = [];
    var has = true;
    if (key.indexOf("&&") !== -1) {
        keys = key.split("&&");
        for (var i = 0; i < keys.length; i++) {
            // 只要有一个不存在，设置为false
            if (!hasPermission(keys[i])) {
                has = false;
                break;
            }
        }
        return has;
    } else if (key.indexOf("||") !== -1) {
        keys = key.split("||");
        has = false;
        for (var j = 0; j < keys.length; j++) {
            // 只要有一个存在，设置为true
            if (hasPermission(keys[j])) {
                has = true;
                break;
            }
        }
        return has;
    } else {
        return hasPermission(key);
    }
}
function hasPermission(key) {
    return permissions.hasOwnProperty(key);
}
function renderPermission() {
    var elements = $(".has-permission");
    $.each(elements, function (i, v) {
        var $v = $(v);
        var key = $v.attr("permission");
        if (key && !hasPermissions(key)) {
            $v.remove();
        } else {
            $v.removeAttr("permission");
            $v.removeClass("has-permission");
        }
    });
}
renderPermission();

// 调整表格高度
$.fn.resetTableHeight = function () {
    var $this;
    if (this.length > 1) {
        $this = $(this[0]);
    } else {
        $this = this;
    }

    var height = $this.height(), tableHeight, toolBtnHeight = 0;
    var $toolBtn = $this.find(".tool-btn");
    if ($toolBtn.length) {
        toolBtnHeight = $toolBtn.height();
    }
    tableHeight = height - toolBtnHeight - 20;
    $this.find(".layui-table-view").height(tableHeight);
    console.log(tableHeight)
};

// 公共查询
$(".search-form .search-btn").click(function (e) {
    var where = jsonData(".search-form");
    if ($.isEmptyObject(where)) {
        where = null;
    }
    layui.table.reload("tableData", {
        where: where
    });
});
$(".search-form .reset-btn").click(function (e) {
    var where = jsonData(".search-form");
    if ($.isEmptyObject(where)) {
        where = null;
    }
    layui.table.reload("tableData", {
        where: where
    });
});
$(".close-btn").click(function (e) {
    var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
    parent.layer.close(index); // 再执行关闭
});
