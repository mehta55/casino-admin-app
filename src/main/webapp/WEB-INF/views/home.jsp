<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<title> Casino Admin | Home </title>
</head>

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
	integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://bootswatch.com/4/journal/bootstrap.min.css" />
<link href='<spring:url value="/resources/css/styles.css"/>' rel="stylesheet" />
<script type="text/javascript" src='<spring:url value="/resources/js/script.js"/>'></script>
<body>
	<%@ include file="nav.jsp" %>
<script>
var menuToActivate = '${currPage}';
</script>
</body>
</html>
