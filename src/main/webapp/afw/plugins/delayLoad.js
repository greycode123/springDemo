/**
 * delayLoad.js af延迟加载插件
 * 说明：当访问A页面时，会自动加载A页面的资源。
 * author：gongwang
 */
/* global af*/
(function($) {
    "use strict";

    if (!$||!$.ui) {
        throw "This plugin requires App Framework UI";
    }

    var delayLoaded = {};

    var loadHtml = function(what){

        var id = what.getAttribute('id');
        var delayURL = what.getAttribute("data-delay");

        if(delayURL && (!delayLoaded[id] || delayLoaded[id] == false)){

            $.ajax({
                url: intel.xdk.webRoot + what.getAttribute('data-delay'),
                async:false,
                success: function(data) {
                    if (data.length > 0) {
                        $.ui.updatePanel(id, data);
                        $.ui.parseScriptTags($.query("#" + id).get(0));
                    }
                    delayLoaded[id] = true;

                    var fnc = what.getAttribute("data-delayLoad");

                    if(fnc)
                        $.ui.dispatchPanelEvent(fnc,what);
                },
                error: function(msg) {
                    delayLoaded[id] = false;
                }
            });
        }
    };

    $(document).on('afui:ready', function(){

        var firstDiv = $.ui.firstDiv;

        if($.ui.loadDefaultHash == true){
            var firstDivId  = $.ui.getPanelId(window.location.hash);
            if(firstDivId){
                firstDiv = $(firstDivId).get(0);
            }
        }

        if($.ui.showLoading) $.ui.showMask();
        loadHtml(firstDiv);
        if($.ui.showLoading) $.ui.hideMask();

        $.ui.parsePanelFunctions(firstDiv);

        if($.ui.loadDefaultHash == true && firstDiv.getAttribute('id') != $.ui.firstDiv.getAttribute('id'))
        {
            if($.ui.showLoading) $.ui.showMask();
            loadHtml($.ui.firstDiv);
            if($.ui.showLoading) $.ui.hideMask();

        }
    });

    $.ui.customClickHandler = function(theTarget,e){
        $(document).trigger("customClickOtherBeforeHandler", null, {theTarget:theTarget});

        var what = $.ui.getPanelId(theTarget.hash);
        //what = $.query(what).get(0);

        if(what){

            $.query(what).trigger("loadPanel", null, {theTarget:theTarget});

            what = $.query(what).get(0);


            if($.ui.showLoading) $.ui.showMask();
            loadHtml(what);
            if($.ui.showLoading) $.ui.hideMask();
        }

        $(document).trigger("customClickOtherAfterHandler");

        return false;
    }
})(af);