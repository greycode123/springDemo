;(function(){
    template.defaults.openTag = '{';
    template.defaults.closeTag = '}';

    /**
     * 对时间戳进行格式化，
     * @param timestamp 要格式化的日期的时间戳
     * @param format 进行格式化的模式字符串
     *     支持的模式字母有：
     *     y:年,
     *     M:年中的月份(1-12),
     *     d:月份中的天(1-31),
     *     h:小时(0-23),
     *     m:分(0-59),
     *     s:秒(0-59),
     *     S:毫秒(0-999),
     *     q:季度(1-4)
     * @return String
     * @author yanis.wang
     * @see http://yaniswang.com/frontend/2013/02/16/dateformat-performance/
     */
    template.helper('timeStampFormat', function (timestamp, format) {

        if(timestamp.replace(/(^\s*)|(\s*$)/g, "") == ""){
            return "";
        }

        var date = new Date();
        date.setTime(timestamp)

        var map = {
            "M": date.getMonth() + 1, //月份
            "d": date.getDate(), //日
            "h": date.getHours(), //小时
            "m": date.getMinutes(), //分
            "s": date.getSeconds(), //秒
            "q": Math.floor((date.getMonth() + 3) / 3), //季度
            "S": date.getMilliseconds() //毫秒
        };
        format = format.replace(/([yMdhmsqS])+/g, function(all, t){
            var v = map[t];
            if(v !== undefined){
                if(all.length > 1){
                    v = '0' + v;
                    v = v.substr(v.length-2);
                }
                return v;
            }
            else if(t === 'y'){
                return (date.getFullYear() + '').substr(4 - all.length);
            }
            return all;
        });
        return format;
    });

    template.helper('convert2Html', function (content) {
        content=content.replace(/\r\n/g, "<br>");
        content=content.replace(/\n/g, "<br>");
        return content;
    });
})();

;(function($) {
    $.ui.autoLaunch = false;
    $.ui.animateHeaders = false;
    $.ui.resetScrollers = false;
    $.ui.useOSThemes = false;
    $.ui.backButtonText = "\u8fd4\u56de"; // 返回
    $.ui.loadingText = "\u52aa\u529b\u52a0\u8f7d\u4e2d..."; // 努力加载中...

    window.App = {
        header:'',
        content:'',
        footer:'',
        defaultPage:"#taskList/taskId=22",
        pages:[],
        htmlRoot:"html",
        delayLoaded:{},
        currentHash: window.location.hash,

        page:function(id,factory){
            return ((id && factory)?this.addPage:this.getPage).call(this,id,factory);
        },

        addPage:function(id,factory){
            this.pages[id] = new factory();
        },

        getPage:function(id){
            return this.pages[id];
        },
        /**
         * 获取当前页面的参数，
         * 注：在page.unload方法中不能使用该方法
         */
        getParams : function(hash) {
            //// var url = hash ? hash: window.location.href; android 4.0.3的bug，不能使用
            var url = hash ? hash: App.currentHash;

            var query, param = {};

            if (url.indexOf('?') != -1 || url.indexOf('#') != -1) {

                var arr = url.split('?');

                if (url.indexOf('?') != -1) {
                    arr = url.split('?');
                    query = arr[1];
                }

                if (url.indexOf('#') != -1) {
                    arr = url.split('#');
                    query = arr[1];

                    arr = query.split('/');
                    query = arr[1];
                }

                if (query) {
                    var seg, s;
                    seg = query.split('&');
                    for (var i = 0; i < seg.length; i++) {
                        if (!seg[i]) continue;
                        s = seg[i].split('=');
                        param[s[0]] = s[1];
                    }
                }
            }
            return param;
        },

        tmpl:function(tplId, data){
            var html = data ? template(tplId, data) : '';
            return html;
        },

        toggleSubFooter : function(height, time){

            var $footer = $("#navbar");
            var $subfooter = $footer.find(".subNavBar");
            var showSubFooter = $footer.data("showSubFooter");

            if(showSubFooter == "true")
            {
                this.hideSubFooter(time);
            }else {
                this.showSubFooter(time);
            }
        },

        showSubFooter:function(time){

            var $footer = $("#navbar");
            var $subfooter = $footer.find(".subNavBar");
            var showSubFooter = $footer.data("showSubFooter");

            if(!showSubFooter || showSubFooter == "false")
            {
                $footer.data("originalHeight", $footer.height());

                //$.blockUI(0.001);
                //$.query("BODY DIV#mask").unbind("touchstart");
                //$.query("BODY DIV#mask").unbind("touchmove");

                $footer.css3Animate({
                    height: ($footer.height() + $subfooter.height()) + "px",
                    time: time + "ms",
                });

//    				$("#mask").css3Animate({
//    					opacity: 0.3,
//    			        time: time + "ms",
//    			    });

//    				$("#mask").bind("click",function(e){
//    					console.log("I was clicked oncew" + e);
//    					_hideSubFooter();
//    			    });

                //$footer.addClass("ontop");
                $footer.data("showSubFooter", "true");
            }
        },

        hideSubFooter : function(time){

            var $footer = $("#navbar");
            var $subfooter = $footer.find(".subNavBar");
            var showSubFooter = $footer.data("showSubFooter");
            if(showSubFooter == "true"){
                $footer.css3Animate({
                    height: $footer.data("originalHeight") + "px",
                    time: time + "ms",
                });

//    				$("#mask").css3Animate({
//    					opacity: 0,
//    			        time: time + "ms",
//    			        complete:function(){
//    			        	$.unblockUI();
//    			        }
//    			    });
//
//    				$("#mask").unbind("click");

                $footer.data("showSubFooter", "false");
                //$footer.removeClass("ontop");

            }
        },

        popup : function(opts){

            opts.title = "\u63d0\u793a"; //提示
            opts.cancelText = "\u53d6\u6d88";//取消
            opts.doneText = "\u786e\u5b9a";//确定

            $.query("#afui").popup(opts);
        },

        alert : function(text){
            var opts = {};
            opts.cancelOnly = true;
            opts.message = text;
            opts.title = "\u63d0\u793a";
            opts.cancelText = "\u786e\u5b9a";

            $.query("#afui").popup(opts);
        },
        loadHtml : function(id,loadSuccess){
            var htmlURL = "html/" + id + ".html";

            $.ajax({
                url: htmlURL,
                success: function(data) {
                    App.delayLoaded[id] = true;
                    loadSuccess(data);
                },
                error: function(msg) {
                    App.alert('\u52a0\u8f7d\u9875\u9762\u8d44\u6e90\u51fa\u9519\uff1a' + htmlURL + " " + msg);//加载页面资源出错：
                    App.delayLoaded[id] = false;
                }
            });
        },
        createPageDiv : function(panelId, data){
            var $newContentDiv = $.create("div", {
                id: panelId,
                className:'panel',
                html:data
            });

            App.content.append($newContentDiv);

            var page = App.page(panelId);

            if(page)
            {
                if(page.title) $newContentDiv.attr('title', page.title);
                if(page.header) $newContentDiv.data('header', page.header);
                if(page.footer) $newContentDiv.data('footer', page.footer);
                if(page.scroller === 'none') $newContentDiv.css('overflow', 'hidden');
                if(page.xScroll) $newContentDiv.addClass('x-scroll');
                if(page.jsScrolling) $newContentDiv.attr('js-scrolling', 'true')

                if($.ui.launchCompleted === true){
                    $.ui.addContentDiv(panelId, data);
                }

                if($.isFunction(page.init)){
                    console.log(panelId + '.init start');
                    page.init();
                    page.inited = true;
                    console.log(panelId + '.init end');
                }
            }
        },
        loadPageDiv : function(hash, afterPageLoaded){
            var self = this;

            var what = $.ui.getPanelId(hash);

            if(what){

                self.currentHash = hash;
                console.log("App.currentHash=" + self.currentHash);

                var panelId = what.replace("#", "");

                var pageDom = $.query("#" + panelId).get(0);

                // 如果页面不存在
                if (!pageDom) {
                    if($.ui.showLoading) self.showMask();
                    App.loadHtml(panelId, function(data){
                        App.createPageDiv(panelId, data);
                        if($.ui.showLoading) self.hideMask();
                        if(afterPageLoaded) afterPageLoaded();
                    });
                } else{
                    if(afterPageLoaded) afterPageLoaded();
                }
            }
        },
        gotoPage : function(pageID){
            this.loadPageDiv(pageID, function(){
                $.ui.loadDiv(pageID);
            });
        },
        init:function(){

            var self = this;

            var customClickHandlerFunc = function(theTarget,e){

                var $target = $(theTarget);
                if ($target.attr("beforClickHandle")
                    && !$target.attr("beforClickHandle")())
                    return true;

                var pageID = $.ui.getPanelId(theTarget.hash).replace('#', '');

                var pageDom = $.query('#' + pageID).get(0);

                // 如果页面已加载
                if(pageDom){
                    App.currentHash = theTarget.hash;
                    return false;
                } else{ // 如果页面还未加载，
                    self.loadPageDiv(theTarget.hash, function(){
                        if (theTarget.href.toLowerCase().indexOf("javascript:") !== -1 || theTarget.getAttribute("data-ignore")) {
                            return;
                        }
                        //external links
                        if (theTarget.hash.indexOf("#") === -1 && theTarget.target.length > 0) {
                            if (theTarget.href.toLowerCase().indexOf("javascript:") !== 0) {
                                if ($.ui.isIntel) {
                                    e.preventDefault();
                                    intel.xdk.device.launchExternal(theTarget.href);
                                } else if (!$.os.desktop) {
                                    e.target.target = "_blank";
                                }
                            }
                            return;
                        }

                        /* IE 10 fixes*/

                        var href = theTarget.href,
                            prefix = location.protocol + "//" + location.hostname + ":" + location.port + location.pathname;
                        if (href.indexOf(prefix) === 0) {
                            href = href.substring(prefix.length);
                        }
                        //empty links
                        if (href === "#" || (href.indexOf("#") === href.length - 1) || (href.length === 0 && theTarget.hash.length === 0)) return e.preventDefault();

                        //internal links
                        //http urls
                        var urlRegex=/^((http|https|file):\/\/)/;
                        //only call prevent default on http/fileurls.  If it's a protocol handler, do not call prevent default.
                        //It will fall through to the ajax call and fail
                        if(theTarget.href.indexOf(":") !== -1 &&urlRegex.test(theTarget.href))
                            e.preventDefault();
                        var mytransition = theTarget.getAttribute("data-transition");
                        var resetHistory = theTarget.getAttribute("data-resetHistory");
                        resetHistory = resetHistory && resetHistory.toLowerCase() === "true" ? true : false;
                        href = theTarget.hash.length > 0 ? theTarget.hash : href;
                        $.ui.loadContent(href, resetHistory, 0, mytransition, theTarget);
                    });

                    return true;
                }
            }

            //自定义点击事件，用于自动创建page信息，并加载page的资源文件
            $.ui.customClickHandler = customClickHandlerFunc;

            $(document).ready(function(){
                self.header = $('#header');
                self.content = $('#content');
                self.footer = $('#navbar');

                self.content.on("loadpanel","div.panel", function(e){
                    var page = self.page(e.target.id);
                    if(page){

                        page.historyLenght = $.ui.history.length;

                        // 如果page.backCache=false,则后退时也会执行load，
                        if(page.backCache === false || (!page._backCache && $.isFunction(page.load))){
                            console.log(e.target.id + '.load start');
                            page.load();
                            console.log(e.target.id + '.load end');
                        }
                    }
                });

                self.content.on("unloadpanel","div.panel", function(e){
                    var page = self.pages[e.target.id];

                    if(page){

                        var lastHistoryLenght = page.historyLenght;

                        page.historyLenght =  $.ui.history.length;

                        if(lastHistoryLenght < page.historyLenght){
                            page._backCache = true;
                        } else{
                            page._backCache=false;
                        }

                        if($.isFunction(page.unload)){
                            console.log(e.target.id + '.unload start');
                            page.unload();
                            console.log(e.target.id + '.unload end');
                        }
                    }

                    if($.ui.showLoading) $.ui.hideMask();

                    self.hideSubFooter();
                });

                // 加载第一个页面start
                var firstPageHash= window.location.hash;

                // 加载默认首页
                var firstPage= $.ui.getPanelId(firstPageHash);
                firstPage = firstPage || self.defaultPage;
                if(firstPage != self.defaultPage){
                    App.loadPageDiv(self.defaultPage);
                }

                firstPageHash = firstPageHash || self.defaultPage

                console.log("addFirstDiv:" + firstPageHash);
                App.loadPageDiv(firstPageHash, function(){
                    $.ui.launch();
                });
                // 加载第一个页面end
            });

            //处理系统返回按钮
            $.ui.ready(function(){
                $(document).off("click", "#header #backButton");

                self.header.on("tap", "#backButton, .backButton", function(){
                    App.goBack();
                });
            });
        }
    };
})($);

App.Toast = (function($){
    var toast_type = 'toast',_toast,timer,
        TEMPLATE = {
            toast : '<a href="#">{value}</a>',
            success : '<a class="icon check-circle"> </a>{value}',
            error : '<a class="icon close-circle"> </a>{value}',
            info : '<a class="icon info-circle"> </a>{value}'
        }

    var _init = function(){
        //全局只有一个实例
        $('#afui').append('<div id="mp_toast" style="z-index: 30000; display: none;">toast</div>');
        _toast = $('#mp_toast');
    }

    /**
     * 关闭消息提示
     */
    var hide = function(){
        _toast.hide();
    }
    /**
     * 显示消息提示
     * @param type 类型  toast|success|error|info  空格 + class name 可以实现自定义样式
     * @param text 文字内容
     * @param duration 持续时间 为0则不自动关闭,默认为2000ms
     */
    var show = function(text, type, duration){

        type = type || 'toast';

        if(timer) clearTimeout(timer);
        var classname = type.split(/\s/);
        toast_type = classname[0];
        _toast.attr('class', type).html(TEMPLATE[toast_type].replace('{value}',text)).show();
        //J.anim(_toast,'scaleIn');
        if(duration !== 0){//为0 不自动关闭
            timer = setTimeout(hide,duration || 2000);
        }
    }

    _init();

    return {
        show : show,
        hide : hide
    }
})($);

App.showToast = function(text, type, duration){
    App.Toast.show(text, type, duration);
}

App.goBack = function(num){
    num = num || 1;
    self.currentHash = $.ui.history[$.ui.history.length - num].target;
    console.log('App.currentHash=' + self.currentHash);
    $.ui.goBack(num);
}

App.showMask = function(text){
    $.ui.showMask(text);
}

App.hideMask = function(){
    $.ui.hideMask();
}

function showHide(objToHide, obj) {
    var el = $("#" + objToHide)[0];

    if(obj){
        if (obj.className == "expanded") {
            obj.className = "collapsed";
        } else {
            obj.className = "expanded";
        }
    }

    $(el).toggle();
}