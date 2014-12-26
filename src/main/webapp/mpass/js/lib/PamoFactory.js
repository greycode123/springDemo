/**
 * 与PAMO关联的模块工厂
 */
(function(window){
    window.PamoFactory = {};
    var ua = navigator.userAgent.toUpperCase(),callindex=0;
    // 当前环境是否为Android平台
    var isAndroid = ua.indexOf('ANDROID') != -1;
    // 当前环境是否为IOS平台
    var isIos = ua.indexOf('IPHONE OS') != -1;
    // 最多重载次数
    var reloadCountMax = 2;

    /**
     * 调用一个Native方法
     * @param {String} name 方法名称
     */
    function callApp(name) {
        // 获取传递给Native方法的参数
        var args = Array.prototype.slice.call(arguments, 1);
        var callback = '', item = null;
        // 遍历参数
        for(var i = 0, len = args.length; i < len; i++) {
            item = args[i];
            if(item === "undefined") {
                item = '';
            }
            // 如果参数是一个Function类型, 则将Function存储到window对象, 并将函数名传递给Native
            if(typeof (item) == 'function') {
                callback = name + 'Callback' + i;
                window[callback] = item;
                item = callback;
            }
            args[i] = item;
        }
        if(isAndroid) {// Android平台
            try {
                for(var i = 0, len = args.length; i < len; i++) {
                    // args[i] = '"' + args[i] + '"';
                    args[i] = '\'' + args[i] + '\'';
                }
                eval('window.android.' + name + '(' + args.join(',') + ')');
            } catch(e) {
                console.log(e)
            }
        } else if(isIos) {// IOS平台
            if(args.length) {
                args = '|' + args.join('|');
            }
            // IOS通过location.href调用Native方法, _call变量存储一个随机数确保每次调用时URL不一致
            callindex++;
            location.href = '#ios:' + name + args + '|' + callindex;
        }
    }

    PamoFactory.openVoice = function(methodName){
        if (PamoFactory.isMatchedPhone()) {
            callApp('startVoiceIdentify', methodName);
        } else {
            window.location.href = "#ios:MEOA|startVoiceIdentify|" + methodName + "|" + new Date().getTime();
        }
    }

    PamoFactory.closeVoice = function(){
        if (PamoFactory.isMatchedPhone()) {
            callApp('stopVoiceIdentify');
        } else {
            window.location.href = "#ios:MEOA|stopVoiceIdentify|" + new Date().getTime();
        }
    }

    PamoFactory.changeTitle = function(titleName) {
        if (PamoFactory.isMatchedPhone()) {
            console.info("phone changeTitle,titleName="+titleName);
            callApp("changeTitle",titleName);
        } else {
            console.info("no phone changeTitle,titleName="+titleName);
        }
    };
    //显示遮罩层
    PamoFactory.showLoading = function() {
        console.info("PamoFactory.showLoading");
        callApp("showLoading");
    };

    //隐藏遮罩层
    PamoFactory.hideLoading = function() {
        if (PamoFactory.isMatchedPhone()) {

        } else {

        }
    };

    //强制关闭遮罩层
    PamoFactory.loadFinish = function() {
        console.info("PamoFactory.loadFinish");
        callApp("loadFinish");
    };

    //返回到navtive首页,native会调用window.back
    PamoFactory.createBackToHome = function() {
        console.info("enter PamoFactory.createBackToHome");
        //调用native是异步的，导致某些设备(如小米，MX2/3)在签报首页无法返回到工作台
        //故取消pamo的判断
        if (isPhone()) {
            window.back = function(){
                console.info("phone createBackToHome");
                callApp("backToHome");
            };
        } else {
            console.info("no phone createBackToHome");
        }
    };

    //重新加载页面
    PamoFactory.reload = function() {
        if (PamoFactory.isMatchedPhone()) {

        } else {

        }
    };

    //获取系统版本号
    PamoFactory.getSysVerison = function() {
        if (PamoFactory.isMatchedPhone()) {

        } else {

        }
    };

    //获取客户端版本号
    PamoFactory.getClientVersion = function() {
        if (PamoFactory.isMatchedPhone()) {

        } else {

        }
    };

    //显示native提示框
    PamoFactory.showWarnHUD = function(title) {
        console.info("enter PamoFactory.showWarnHUD.title:"+title);
        callApp("showWarnHUD",title);
    };

    //talkingData信息采集
    PamoFactory.sendTalkingData = function(title1,title2) {
        console.info("enter PamoFactory.sendTalkingData");
        if (PamoFactory.isMatchedPhone()) {
            console.info("phone talkingData,title1="+title1+",title2="+title2);
            callApp("sendTalkingData",title1,title2);
        } else {
            console.info("no phone talkingData,title1="+title1+",title2="+title2);
        }
    };

    PamoFactory.createWindowBack = function(callback){
        console.info("enter PamoFactory.createWindowBack");
        if(typeof(callback) == "function"){
            console.info("create window.back");
            window.back = callback;
        }
    };

    function isPhone(){
        console.info("device type:"+sessionStorage.getItem("type"));
        if(sessionStorage.getItem("type") == "phone")
            return true;
        return false;
    };

    function isPamo(){
        console.info("in isPamo.sysVersion："+sessionStorage.getItem("sysVersion"));
        console.info("in isPamo.isPamo："+sessionStorage.getItem("isPamo"));
        var isPamo = sessionStorage.getItem("isPamo");
        if(isPamo == "true"){
            return true;
        } else if(isPamo == null){
            console.info("before call native getSysVerison.sysVersion");
            callApp("getSysVerison",function(sysVersion){
                console.info("called native getSysVerison.sysVersion",sysVersion);
                sessionStorage.setItem("sysVersion",sysVersion);
                if(sysVersion){
                    sessionStorage.setItem("isPamo","true");
                    return true;
                }else{
                    sessionStorage.setItem("isPamo","false");
                    return false;
                }
            });
        }
        console.info("sysVersion："+sessionStorage.getItem("sysVersion"));
        console.info("isPamo："+sessionStorage.getItem("isPamo"));
        return false;
    };

    PamoFactory.isMatchedPhone = function(){
        if(isPhone()&&isPamo())
            return true;
        return false;
    };

    /**
     * 显示头部
     * ID: DOM元素HEADER的ID
     * CALLBACK： 执行特殊操作的方法
     */
    PamoFactory.showHeader = function(header, callback){
        if(isPhone() && !isPamo()){
            var temp = callback || function(){
                header.show();
                header.next().css('top', header.height() + 'px !important;');
            };
            temp();
        }
    };

    // 重载页面
    PamoFactory.reload = function(){
        sessionStorage.reloadCount++;
        callApp("reload");
    };

    PamoFactory.canReload = function(){
        // 已经刷新的次数
        if (!sessionStorage.reloadCount)
            sessionStorage.reloadCount = 0;
        if (PamoFactory.isMatchedPhone() && sessionStorage.reloadCount < reloadCountMax) {
            return true;
        }
        return false;
    };

}(window));