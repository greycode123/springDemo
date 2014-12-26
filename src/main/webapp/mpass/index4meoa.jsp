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
    <link rel="stylesheet" type="text/css" href="css/build-main.css" />
    <style>
        html, #afui{

        }
    </style>
    <script>
        var CONFIG = {
            rootURL:'https://mo-stg.pa18.com/pass',
            meoaUrl:'https://mo-stg.pa18.com/oas_meoa'
        };

        var USER = {
            account: 'PAICDOM\ZOULI004',
            name: '邹丽',
            stationID: '1',
            deptNo: 'PA002_S000000463',
            deptName: '寿险四川分公司',
            deptNoLevel: '40',
            coordinatorDepts : [

                {"grantID":"0","deptName":"寿险四川分公司","deptNo" : "PA002_S000000463","deptLevel":"40"},

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
<script type="text/javascript" charset="utf-8" src="js/build-main.js"></script>
<script>
    setTimeout(function(){
        alert($('#afui').attr('style'));
    },3000)
</script>
</body>
</html>