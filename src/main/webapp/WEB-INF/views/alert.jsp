<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<html>
<body>

	<c:forEach items="${errors}" var="error">
		<div class="alert alert-danger alert-dismissible fade show"
			role="alert">
			${error}
			<button type="button" class="close"
				data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:forEach>

	<c:forEach items="${msgs}" var="msg">
		<div class="alert alert-success alert-dismissible fade show"
			role="alert">
			${msg}
			<button type="button" class="close"
				data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:forEach>

</body>
<script></script>
</html>