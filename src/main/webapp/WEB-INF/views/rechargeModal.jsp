<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<link href='<spring:url value="/resources/css/styles.css"/>'
	rel="stylesheet" />
<body>


	<!-- Trigger/Open The Modal -->

	<!-- The Modal -->
	<div id="myModal" class="modal">

		<!-- Modal content -->
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Recharge Balance</h5>

			</div>
			<div class="modal-body">
				<form action="./recharge" method="post">
					<div class="form-group">
						<label for="name">Customer Name</label> <input type="text"
							id="recharge-modal-name" name="recharge-modal-name"
							class="form-control" value="" readonly />
					</div>

					<div class="form-group">
						<label for="rechargeAmount">Recharge Amount (Rs)</label> <input
							type="number" min="500" max="10000" step="500"
							class="form-control" value="500"
							name="recharge-modal-rechargeAmount" id="rechargeAmount"
							onkeydown="return false">
					</div>

					<input type="hidden" name="recharge-modal-id"
						id="recharge-modal-id" value="" /> <input type="hidden"
						name="redirect-url" id="redirect-url"
						value="./customers?page=${pageNo}&nameFilter=${nameFilter}&emailFilter=${emailFilter}&contactNumberFilter=${contactNumberFilter}" />
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-dark">Recharge</button>
				<button type="button" id="btn-close"
					onclick="document.getElementById('myModal').style.display='none'"
					class="btn btn-light ">Close</button>
			</div>
			</form>
		</div>


	</div>
	<script type="text/javascript"
		src='<spring:url value="/resources/js/rechargeModal.js"/>'></script>

</body>
</html>