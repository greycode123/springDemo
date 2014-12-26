/**
 * delayLoad.js af 浏览器history管理插件
 *  说明：当访问A页面时，会自动加载A页面的资源。
 * author：gongwang
 */
/* global af*/
;(function($) {
    "use strict";

    if (!$||!$.ui) {
        throw "This plugin requires App Framework UI";
    }

    $.ui.manageHistory = false;

    var _history = [];
    var startPath = window.location.pathname + window.location.search;

    var _add2History = function(hash,noState){
        console.log("_add2History:" + hash);
        if(noState){//不添加浏览器历史记录
            _history.shift(hash);
            window.history.replaceState(hash,'',hash);
        }else{
            window.history.pushState(hash, hash, startPath + "#" + hash);
        }
        _history.unshift(hash);
        console.log(_history);
    }

    $(document).on('customClickOtherBeforeHandler', function(opts){

        var theTarget = opts.theTarget;

        console.log("opts:" + opts);
        console.log("theTarget:" + theTarget);

        var what = $.ui.getPanelId(theTarget.hash);
        //what = $.query(what).get(0);

        //如果panel
        if(what){
            _add2History(what.replace("#",""), false);
        }else{
            console.log("id:" + what.id);
        }
    });


})(af);