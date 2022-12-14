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
 * 获取父级窗口
 * @param iframeName 父级窗口名称
 * @param window 窗口对象，默认当前窗口对象
 */
function getParentWindow(iframeName, window) {
    if (!window) {
        // 默认当前窗口对象
        window = this.window;
    }
    if (window.frames[iframeName]) {
        return window.frames[iframeName];
    } else {
        // 对子窗体进行二次检索
        var length = window.frames.length;
        for (var i = 0; i < length; i++) {
            if (window.frames[i].frames && window.frames[i].frames[iframeName]) {
                return window.frames[i].frames[iframeName];
            }
        }
    }
    // 没找到
    return null;
}

/**
 * 控制iframe窗口的刷新操作
 */
var iframeObjName;
var iframeObjMap = {};

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
            iframeObjMap['layui-layer-iframe' + index] = parent.iframeObjName;
            // 延迟10ms，等待页面加载完成
            setTimeout(function () {
                // 调用layui.init
                if (iframe && iframe.layui && iframe.layui.init) {
                    console.log("--- 存在layui.init方法，执行layui.init方法 ---");
                    iframe.layui.init(params);
                } else {
                    if (iframe) {
                        // 窗口已经加载出来了,layui还没加载完
                        console.log("--- 不存在layui.init方法");
                        // 创建callback方法
                        iframe['_initCallback'] = function (layui) {
                            console.log("--- 执行_initCallback方法");
                            layui.init(params);
                        };
                    } else {
                        console.log("--- 不存在iframe");
                    }
                }
            }, 30);
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
            // 获取到父级窗口
            var parentWindow = getParentWindow(obj);
            if (parentWindow) {
                doRefresh(parentWindow);
            } else {
                console.log('未获取到parentWindow');
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

// 处理页面加载未完成的问题
if (window['_initCallback']) {
    window['_initCallback'](layui);
}

/**
 * 关闭子页面
 */
function closePage() {
    setRefresh();
    var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
    parent.layer.close(index); // 再执行关闭
}

/**
 * 关闭子页面，但不刷新
 */
function closePageNoRefresh() {
    var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
    parent.layer.close(index); // 再执行关闭
}

function setRefresh() {
    // 设置父级窗口是否刷新
    var iframeObjName = parent.iframeObjMap[window.name];
    var parentWindow = getParentWindow(iframeObjName, parent.window);
    if (parentWindow) {
        parentWindow.layui._refresh = true;
    } else {
        console.log('未获取到parentWindow');
    }
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
 * @param isDebug 开启调试模式
 */
function jsonData(formId, data, isDebug) {
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

    if (!isDebug) {
        isDebug = false;
    }

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
            if (!value) {
                return;
            }
            var name = field.name;
            // 复选框特殊处理
            if (isCheckbox(field)) {
                if (data.hasOwnProperty(name)) {
                    data[name].push(value);
                } else {
                    data[name] = [value];
                }
            } else {
                data[name] = value;
            }
        }

        function isCheckbox(field) {
            return 'checkbox' === field.type;
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

function ajaxPostDelete(url, params, successFn, errorFn, completeFn) {
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
        traditional: true,
        success: function (data) {
            successFn && successFn(data);
        },
        error: function () {
            // console.log(arguments);
            errorFn && errorFn();
        },
        complete: function () {
            // console.log(arguments);
            parent.layer.close(loadingIndex);
            completeFn && completeFn();
        }
    });
}

function ajaxPost(url, params, successFn, errorFn, completeFn) {
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
            // console.log(arguments);
            errorFn && errorFn();
        },
        complete: function () {
            // console.log(arguments);
            parent.layer.close(loadingIndex);
            completeFn && completeFn();
        }
    });
}

// 权限
var permissions = {
};
function initPermissions() {
    if (window.top === window) {
        // 加载权限数据
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

// 加载国际化信息
var i18ns = {};
function initI18ns() {
    if (window.top === window) {
        // 加载权限数据
    } else {
        // 获取顶级页面已经获取到的数据
        i18ns = window.top.i18ns;
    }
}
initI18ns();

// 获取国际化信息
function getI18n(code, args) {
    var value = i18ns[code];
    if (value && args && args.length) {
        for (var i = 0; i < args.length; i++) {
            value = value.replace("{" + i + "}", args[i]);
        }
    }
    return value;
}

// lookup缓存
var lookups = {};
var LOOKUP_LOG_ENABLE = true;
// 初始化lookup
function initLookups() {
    if (window.top === window) {
        // 加载权限数据
    } else {
        // 获取顶级页面已经获取到的数据
        lookups = window.top.lookups;
    }
}
initLookups();

// 把lookup设置到缓存中
function setLookups(code, data) {
    // 本地缓存
    if (LOOKUP_LOG_ENABLE) {
        console.log("lookup-已设置缓存：[" + code + "]" + " ---> window.name:" + window.name);
    }
    lookups[code] = data;
    // 判断是否存在父级，在给父级，避免下次进入再次查询
    if (window.top !== window) {
        window.top.setLookups(code, data);
    }
}

// 获取lookup数据
function getLookup(lookupCode, callback) {
    if (LOOKUP_LOG_ENABLE) {
        console.log("lookup-正在获取：[" + lookupCode + "]");
    }
    if (lookups.hasOwnProperty(lookupCode)) {
        if (LOOKUP_LOG_ENABLE) {
            console.log("lookup-已从缓存中获取：[" + lookupCode + "]");
        }
        callback && callback(lookups[lookupCode]);
    } else {
        if (LOOKUP_LOG_ENABLE) {
            console.log("lookup-正在向服务器请求数据：[" + lookupCode + "]");
        }
        var url = "/lookup/lookupItem/get";
        ajaxPost(url, {parentCode: lookupCode}, function (data) {
            if (data.success) {
                if (LOOKUP_LOG_ENABLE) {
                    console.log("lookup-请求成功：[" + lookupCode + "]");
                }
                setLookups(lookupCode, data.data);
                callback && callback(data.data);
            }
        });
    }
}

// 处理统用按钮，下拉选项
$(".lookup").each(function (i, v) {
    var $v = $(v);
    // 获取编码
    var code = $v.attr("lookupcode");
    if (!code) {
        // continue;
        return;
    }
    // 属性
    var value = $v.attr("l-value") || "code";
    var name = $v.attr("l-name") || "name";
    if ("SELECT" === v.tagName) {
        getLookup(code, function (list) {
            var options = [];
            for (var i in list) {
                var item = list[i];
                options.push("<option value=\""+item[value]+"\">"+item[name]+"</option>");
            }
            $v.append(options.join(""));
            if (layui && layui.form) {
                layui.form.render();
            }
        });
    } else if ("INPUT" === v.tagName) {
        var type = $v.attr("type");
        var inputName = $v.attr("name");
        if ("radio" === type) {
            var firstChecked = true;
            getLookup(code, function (list) {
                var options = [];
                for (var i in list) {
                    var item = list[i];
                    if (firstChecked && "0" === i) {
                        options.push("<input type=\"radio\" name=\"" + inputName + "\" value=\"" + item[value] + "\" title=\"" + item[name] + "\" checked>");
                    } else {
                        options.push("<input type=\"radio\" name=\"" + inputName + "\" value=\"" + item[value] + "\" title=\"" + item[name] + "\">");
                    }
                }
                $v.after(options.join(""));
                $v.remove();
                if (layui && layui.form) {
                    layui.form.render();
                }
            });
        } else if ("checkbox" === type) {
            var firstChecked = false;
            getLookup(code, function (list) {
                var options = [];
                for (var i in list) {
                    var item = list[i];
                    if (firstChecked && "0" === i) {
                        options.push("<input type=\"checkbox\" name=\"" + inputName + "\" value=\"" + item[value] + "\" title=\"" + item[name] + "\" checked>");
                    } else {
                        options.push("<input type=\"checkbox\" name=\"" + inputName + "\" value=\"" + item[value] + "\" title=\"" + item[name] + "\">");
                    }
                }
                $v.after(options.join(""));
                $v.remove();
                if (layui && layui.form) {
                    layui.form.render();
                }
            });
        }
    }
});