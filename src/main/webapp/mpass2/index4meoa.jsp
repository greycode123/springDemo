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
            rootURL:'http://iqcd-d0078:7002/pass',
            meoaUrl:'http://hcd-it-375:7001/oas_meoa/'
        };

        var USER = {
            account: 'PAICDOM\LULN',
            name: '卢旅娜',
            stationID: '1',
            deptNo: 'PA002_S000000003',
            deptName: '中国平安人寿保险股份有限公司',
            deptNoLevel: '20',
            coordinatorDepts : [
                {"grantID":"FDAAD46748BF3F68E04400144F269612",
                    "deptName":"中国平安人寿保险股份有限公司",
                    "deptNo" : "PA002_S000000003","deptLevel":"20"}
            ]
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
<script type="text/javascript" charset="utf-8" src="js/lib/appframework.ui.js"></script>
<script type="text/javascript" charset="utf-8" src="js/lib/template.js"></script>
<script type="text/javascript" charset="utf-8" src="js/mpassAPI.js"></script>
<script type="text/javascript" charset="utf-8" src="js/app.js"></script>
<script>
    if (!((window.DocumentTouch && document instanceof DocumentTouch) || 'ontouchstart' in window)) {
        var script = document.createElement("script");
        script.src = "js/lib/af.desktopBrowsers.js";
        var tag = $("head").append(script);
    }
</script>
</body>
</html>