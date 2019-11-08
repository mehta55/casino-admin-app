<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nagarro.util.Constants"%>
<!DOCTYPE html>
<html>
<head>
<title>Casino Admin | Customers</title>
</head>

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
	integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://bootswatch.com/4/journal/bootstrap.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<link href='<spring:url value="/resources/css/styles.css"/>'
	rel="stylesheet" />
<script type="text/javascript"
	src='<spring:url value="/resources/js/script.js"/>'></script>

<body>
	<%@ include file="nav.jsp"%>

	<div class="row mt-5">
		<div class="col-md-8 m-auto ">
			<div class="card card-body ">
				<%@ include file="alert.jsp"%>

				<div class="row" style="margin: auto;">
					<form class="form-filter" action="./customers?page=1" method="post">
						<div class="row">

							<div class="col-3">
								<input name="nameFilter" type="text" class="form-control"
									placeholder="Name" value="${nameFilter}">
							</div>
							<div class="col-4">
								<input name="emailFilter" type="text" class="form-control"
									placeholder="Email" value="${emailFilter}">
							</div>
							<div class="col-3">
								<input name="contactNumberFilter" type="tel"
									class="form-control" placeholder="Contact Number"
									value="${contactNumberFilter}">

							</div>
							<div class="col-auto">
								<button type="submit" class="btn btn-dark ">Filter</button>
							</div>
						</div>
					</form>
					<div class="col-auto">
						<a href="./customers?page=1&nameFilter=&emailFilter=&contactNumberFilter="><button  class="btn btn-dark ">Reset</button></a>
					</div>
				</div>


				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col">S.No.</th>
							<th scope="col">Name</th>
							<th scope="col">E-mail</th>
							<th scope="col">Date Of Birth</th>
							<th scope="col">Contact</th>
							<th scope="col">Balance (Rs)</th>
							<th scope="col">Recharge</th>

						</tr>
					</thead>
					<tbody>
						<c:set var="sno" value="${(pageNo - 1) * Constants.PAGE_SIZE}" />
						<c:forEach items="${customers}" var="customer">
							<tr class="table-light" data-id="${customer.id}">
								<th scope="row">${sno = sno + 1}</th>
								<td>${customer.name}</td>
								<td>${customer.email}</td>
								<td>${customer.dateOfBirth}</td>
								<td>${customer.contactNumber}</td>
								<td>${customer.balance}</td>
								<td>
									<button type="submit" class="recharge btn">
										<i class="fas fa-coins"></i>&nbsp<i class="fas fa-plus"></i>
									</button>
								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>



				<div class=pagination-div>
					<ul class="pagination ">
						<li class="page-item "><a id="prev" class="page-link"
							href="./customers?page=${pageNo-1}&nameFilter=${nameFilter}&emailFilter=${emailFilter}&contactNumberFilter=${contactNumberFilter}">&laquo;</a></li>

						<c:forEach var="i" begin="1" end="${totalPages}">
							<c:set var="page_href" scope="session"
								value="./customers?page=${i}&nameFilter=${nameFilter}&emailFilter=${emailFilter}&contactNumberFilter=${contactNumberFilter}" />
							<li class="page-item "><a id="page${i}" class="page-link"
								href="${page_href}">${i}</a></li>

						</c:forEach>

						<li class="page-item"><a id="next" class="page-link"
							href="./customers?page=${pageNo+1}&nameFilter=${nameFilter}&emailFilter=${emailFilter}&contactNumberFilter=${contactNumberFilter}">&raquo;</a></li>
					</ul>
				</div>

			</div>
		</div>
	</div>


	<%@ include file="rechargeModal.jsp"%>


</body>
</html>

</body>
<script>
	var element = document.getElementsByClassName("close")[0];
	var pageNo = '${pageNo}';
	var totalPages = '${totalPages}'
	var menuToActivate = '${currPage}';
</script>
</html>
