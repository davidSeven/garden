layui.use(['layer', 'form', 'element', 'jquery', 'dialog'], function () {
    var element = layui.element;
    var $ = layui.jquery;
    var hideBtn = $('#hideBtn');
    var mainLayout = $('#main-layout');
    var mainMask = $('.main-mask');
    // 监听导航点击
    element.on('nav(leftNav)', function (elem) {
        // var navA = $(elem).find('a');
        var navA = elem;
        var id = navA.attr('data-id');
        var url = navA.attr('data-url');
        var text = navA.attr('data-text');
        if (!url) {
            return;
        }
        var isActive = $('.main-layout-tab .layui-tab-title').find("li[lay-id=" + id + "]");
        if (isActive.length > 0) {
            // 切换到选项卡
            element.tabChange('tab', id);
            var $layuiShow = $(".layui-tab-item.layui-show");
            var src = $layuiShow.find("iframe").attr("src");
            $layuiShow.find("iframe").attr("src", src);
        } else {
            // 添加一个新的页签
            element.tabAdd('tab', {
                title: text,
                content: '<iframe src="' + url + '" name="iframe' + id + '" class="iframe" framborder="0" data-id="' + id + '" scrolling="auto" width="100%"  height="100%"></iframe>',
                id: id
            });
            element.tabChange('tab', id);
            // 更新url
            var href = location.href;
            var indexOf;
            if ((indexOf = href.indexOf("#!")) !== -1) {
                href = href.substr(0, indexOf - 1);
            }
            location.href = href + "#!" + url;
        }
        mainLayout.removeClass('hide-side');
    });

    // 页面加载完成
    (function () {
        var href = location.href;
        var indexOf;
        if ((indexOf = href.indexOf("#!")) !== -1) {
            var url = href.substr(indexOf + 2);
            // 找到对应的菜单标签
            var $a = $(".main-layout-side .layui-nav-item a[data-url='" + url + "']");
            if ($a.length) {
                // 展开父级
                $a.parents(".layui-nav-child").prev().click();
                // 加载
                $a.click();
            } else {
                console.log("the url not find:" + url);
            }
        }
    })();

    // 监听导航点击
    element.on('nav(rightNav)', function (elem) {
        // var navA = $(elem).find('a');
        var navA = elem;
        var id = navA.attr('data-id');
        var url = navA.attr('data-url');
        var text = navA.attr('data-text');
        if (!url) {
            return;
        }
        var isActive = $('.main-layout-tab .layui-tab-title').find("li[lay-id=" + id + "]");
        if (isActive.length > 0) {
            // 切换到选项卡
            element.tabChange('tab', id);
        } else {
            element.tabAdd('tab', {
                title: text,
                content: '<iframe src="' + url + '" name="iframe' + id + '" class="iframe" framborder="0" data-id="' + id + '" scrolling="auto" width="100%"  height="100%"></iframe>',
                id: id
            });
            element.tabChange('tab', id);
        }
        mainLayout.removeClass('hide-side');
    });
    // 菜单隐藏显示
    hideBtn.on('click', function () {
        if (!mainLayout.hasClass('hide-side')) {
            mainLayout.addClass('hide-side');
        } else {
            mainLayout.removeClass('hide-side');
        }
    });
    // 遮罩点击隐藏
    mainMask.on('click', function () {
        mainLayout.removeClass('hide-side');
    });
    // 双击tab头刷新
    $(".main-layout-tab .layui-tab-title").on("dblclick", "li", function () {
        var $layuiShow = $(".layui-tab-item.layui-show");
        var src = $layuiShow.find("iframe").attr("src");
        $layuiShow.find("iframe").attr("src", src);
    });
    //示范一个公告层
//	layer.open({
//		  type: 1
//		  ,title: false //不显示标题栏
//		  ,closeBtn: false
//		  ,area: '300px;'
//		  ,shade: 0.8
//		  ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
//		  ,resize: false
//		  ,btn: ['火速围观', '残忍拒绝']
//		  ,btnAlign: 'c'
//		  ,moveType: 1 //拖拽模式，0或者1
//		  ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">后台模版1.1版本今日更新：<br><br><br>数据列表页...<br><br>编辑删除弹出功能<br><br>失去焦点排序功能<br>数据列表页<br>数据列表页<br>数据列表页</div>'
//		  ,success: function(layero){
//		    var btn = layero.find('.layui-layer-btn');
//		    btn.find('.layui-layer-btn0').attr({
//		      href: 'http://www.layui.com/'
//		      ,target: '_blank'
//		    });
//		  }
//		});

    $(function () {
        // 加载权限数据
        ajaxPost('/getPermissionSet', null, function (response) {
            if (response.success) {
                $.each(response.data, function (i, v) {
                    permissions[v] = 0x1;
                });
            }
        });
        // 加载国际化
        ajaxPost('/i18n/i18n/currentI18nList', null, function (response) {
            if (response.success) {
                i18ns = response.data;
            }
        });
    });
});