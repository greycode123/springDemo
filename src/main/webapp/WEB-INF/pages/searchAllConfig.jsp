<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/dataTables.bootstrap.css"/>">
    <script type="text/javascript" src="<c:url value="/js/jquery-1.11.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/jquery.dataTables.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/dataTables.bootstrap.js"/>"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#example').dataTable();
        } );
    </script>
</head>
<body>
    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>名称</th>
        <th>值</th>
        <th>描述</th>
    </tr>
    </thead>

    <tfoot>
    <tr>
        <th>名称</th>
        <th>值</th>
        <th>描述</th>
    </tr>
    </tfoot>

    <tbody>
        <c:forEach var="item" items="${configList}">
            <tr>
                <td><c:out value="${item.key}" /></td>
                <td><c:out value="${item.value}" /> </td>
                <td></td>
            </tr>
        </c:forEach>
    </tbody>
    </table>
</body>
</html>