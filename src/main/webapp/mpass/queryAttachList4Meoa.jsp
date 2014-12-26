<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>公章系统 - mpass</title>
    <meta charset="utf-8"/>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link rel="stylesheet" type="text/css" href="css/af.ui.base.css" />
    <link rel="stylesheet" type="text/css" href="css/icon.css" />
    <link rel="stylesheet" type="text/css" href="css/app.css" />
    <script>
        var CONFIG = {
            rootURL:'/pass',
            meoaUrl:'https://mo-stg.pa18.com/oas_meoa'
        };

        var USER = {
            account: 'PAICDOM\YUBIN002',
            name: '于斌',
            stationID: '1',
            deptNo: 'PA002_S000000003',
            deptName: '中国平安人寿保险股份有限公司',
            deptNoLevel: '20',
            coordinatorDepts : [

                {"grantID":"0","deptName":"中国平安人寿保险股份有限公司","deptNo" : "PA002_S000000003","deptLevel":"20"},

            ],
        };
    </script>
</head>
<body>
<div id="afui" class="mp">
    <div id="splashscreen" style="color:#FFF; border-radius: 0;" class='ui-loader heavy'>
        公章系统 - mpass<br>
        <br><span class='ui-icon ui-icon-loading spin'></span>
        <h1>加载中...</h1>
    </div>
    <div id="header"></div>
    <div id="content"></div>
    <div id="navbar"></div>
</div>
<script type="text/javascript" charset="utf-8" src="js/lib/appframework.js"></script>
<script type="text/javascript" charset="utf-8" src="js/lib/appframework.ui.custom.js?1"></script>
<script type="text/javascript" charset="utf-8" src="js/lib/template.js"></script>
<script type="text/javascript" charset="utf-8" src="js/lib/PamoFactory.js"></script>
<script type="text/javascript" charset="utf-8" src="js/mpassAPI.js"></script>
<script type="text/javascript" charset="utf-8" src="js/app.js"></script>
<script type="text/javascript" charset="utf-8" src="js/lib/iscroll.js"></script>
<script>
    if (!((window.DocumentTouch && document instanceof DocumentTouch) || 'ontouchstart' in window)) {
        var script = document.createElement("script");
        script.src = "js/lib/af.desktopBrowsers.js";
        var tag = $("head").append(script);
    }


    App.defaultPage="#attachSealInfo";
    App.init();

</script>
</body>
</html>