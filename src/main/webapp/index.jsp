<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>后台管理系统</title>
    <link rel="stylesheet" type="text/css" href="static/css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href=static/css/themes/icon.css>
    <script type="text/javascript" src="static/js/jquery.min.js"></script>
    <script type="text/javascript" src="static/js/jquery.easyui.min.js"></script>
    <script>
        function addTab(title, url){
            if ($('#tt').tabs('exists', title)){
                $('#tt').tabs('select', title);
            } else {
                var content = '<iframe scrolling="yes" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
                $('#tt').tabs('add',{
                    title:title,
                    content:content,
                    closable:true,
                    selected:true
                });

                //alert($('#tt').tabs('getSelected'));
            }
        }
//
//        $(window).resize(function(){
//            setTimeout(function(){
//                $("#tt").tabs({
//                    width:$("#tt").parent().width() - 30,
//                    height:$("#tt").parent().height()
//                });
//            }, 500);
//        });
//
//        $(document).ready(function () {
//            setTimeout(function(){
//                $("#tt").tabs({
//                    width:$("#tt").parent().width() -30,
//                    height:$("#tt").parent().height()
//                });
//            }, 500);
//        });
    </script>
    <style>
        #footer{
            text-align: center;
        }
    </style>
</head>
<body class="easyui-layout">
    <div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">
        后台管理系统
    </div>
    <div data-options="region:'west',split:true,title:'菜单'" style="width:200px;">
        <div class="easyui-accordion" data-options="multiple:true" data-options="fit:true, border:false">
            <div title="派单队列管理" data-options="collapsed:false,collapsible:false" style="overflow:auto;padding:5px;">
                <p><a href="javascript:addTab('派单队列管理', 'http://localhost:8080/springDemo/userQueueManager.do')">派单队列管理</a></p>
            </div>
            <div title="Java" style="padding:10px;">
                <p>Java (Indonesian: Jawa) is an island of Indonesia. With a population of 135 million (excluding the 3.6 million on the island of Madura which is administered as part of the provinces of Java), Java is the world's most populous island, and one of the most densely populated places in the world.</p>
            </div>
            <div title="C#" style="padding:10px;">
                <p>C# is a multi-paradigm programming language encompassing strong typing, imperative, declarative, functional, generic, object-oriented (class-based), and component-oriented programming disciplines.</p>
            </div>
            <div title="Ruby" style="padding:10px;">
                <p>A dynamic, reflective, general-purpose object-oriented programming language.</p>
            </div>
            <div title="Fortran" style="padding:10px;">
                <p>Fortran (previously FORTRAN) is a general-purpose, imperative programming language that is especially suited to numeric computation and scientific computing.</p>
            </div>
        </div>
    </div>
    <div id="footer" data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">
        <div>Copyright © 2010-2014 www.jeasyui.com</div>
    </div>
    <div data-options="region:'center', border:false">
        <div id="tt" class="easyui-tabs" data-options="fit:true">
            <div title="Home" style="padding:20px">
                <p>Click the above button to add a new tab panel.</p>
            </div>
        </div>
    </div>
</body>
</html>