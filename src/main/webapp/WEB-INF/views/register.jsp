<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Casino Admin | Register</title>
</head>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
	integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://bootswatch.com/4/journal/bootstrap.min.css" />
<link href='<spring:url value="/resources/css/styles.css"/>'
	rel="stylesheet" />
<script type="text/javascript"
	src='<spring:url value="/resources/js/script.js"/>'></script>

<body>
	<%@ include file="nav.jsp"%>

	<div class="row mt-4">
		<div class="col-md-8 m-auto">
			<div class="card card-body">
				<%@ include file="alert.jsp"%>

				<form:form method="POST" modelAttribute="customer"
					action="./register" enctype="multipart/form-data">
					<div class="form-group">
						<spring:bind path="name">
							<form:label path="name">Name</form:label>
							<form:input path="name" id="name" name="name"
								class="form-control" placeholder="Enter Name" value="${name}" required="required"/>
						</spring:bind>
					</div>

					<div class="form-group">
						<spring:bind path="email">
							<form:label path="email">Email</form:label>
							<form:input path="email" id="email" name="email"
								class="form-control" placeholder="Enter Email" value="${email}" required="required"/>
						</spring:bind>
					</div>
					<div class="form-group">
						<spring:bind path="dateOfBirth">
							<form:label path="dateOfBirth">DOB</form:label>
							<form:input path="dateOfBirth" type="date" id="dob" name="dob"
								class="form-control" placeholder="Enter Date Of Birth"
								value="${dob}" required="required"/>
						</spring:bind>
					</div>
					<div class="form-group">
						<spring:bind path="contactNumber">
							<form:label path="contactNumber">Contact</form:label>
							<form:input path="contactNumber" type="tel" id="contact"
								name="contactNumber" class="form-control"
								placeholder="Enter Contact Number"
								aria-describedby="contactHelp"
								pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" value="${contactNumber}" required="required"/>
							<small id="contactHelp" class="form-text text-muted">*Format:
								123-456-7890</small>
						</spring:bind>
					</div>

					<div class="form-group">
						
						<input type="file" class="form-control-file" id="idProof"
							name="idProof" aria-describedby="fileHelp" accept=".jpg,.png" required="required"> 
							<small
							id="fileHelp" class="form-text text-muted">*Attach image
							of any one - Adhaar card, Driving License or PAN card</small>
					</div>

					<button type="submit" class="btn btn-dark btn-block">Register</button>

				</form:form>
			</div>
		</div>
	</div>

</body>
<script>
	var menuToActivate = '${currPage}';
	var element = document.getElementsByClassName("close")[0];
	var alertElement = element.parentNode;
</script>
</html>
