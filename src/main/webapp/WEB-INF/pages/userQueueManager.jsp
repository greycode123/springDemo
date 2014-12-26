<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Basic CRUD Application - jQuery EasyUI CRUD Demo</title>
	<link rel="stylesheet" type="text/css" href="static/css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href=static/css/themes/icon.css>
	<script type="text/javascript" src="static/js/jquery.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.edatagrid.js"></script>
</head>
<body>
<table id="dg" title=用户派单队列 class="easyui-datagrid"
	   toolbar="#toolbar" pagination="true" idField="queueId"
	   rownumbers="true" fitColumns="true" singleSelect="true" striped="true" method="post">
	<thead>
	<tr>
		<th field="userName" width="50"  editor="text">用户名</th>
		<th field="userStatus" width="50"  editor="text">状态</th>
	</tr>
	</thead>
</table>
<div id="toolbar">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#dg').edatagrid('addRow')">New</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:$('#dg').edatagrid('destroyRow')">Destroy</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:$('#dg').edatagrid('saveRow')">Save</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="javascript:$('#dg').edatagrid('cancelRow')">Cancel</a>
</div>
<script type="text/javascript">
	$(function(){
		$('#dg').edatagrid({
			url: './getUserQueues.do',
			saveUrl: './saveUserQueue.do',
			updateUrl: './updateUserQueue.do',
			destroyUrl: './destroyUserQueue.do'
		});
	});
</script>
</body>
</html>