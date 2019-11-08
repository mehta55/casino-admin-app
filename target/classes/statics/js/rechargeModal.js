var modal = document.getElementById("myModal");
var rechargeBtns = document.getElementsByClassName("recharge btn");
var rechargeModalName = document.getElementById("recharge-modal-name");
var rechargeModalId =  document.getElementById("recharge-modal-id");

Array.prototype.forEach.call(rechargeBtns, (rechargeBtn) => {
	rechargeBtn.onclick = function(event) {
		  var customerListRechargeBtn = event.currentTarget;
		  rechargeModalName.value = customerListRechargeBtn .parentElement.parentElement.childNodes[3].textContent;
		  rechargeModalId.value = customerListRechargeBtn .parentElement.parentElement.dataset.id;
		  modal.style.display = "block";
		}
	});



window.onclick = function(event) {
	if (event.target == modal) {
	   modal.style.display = "none";
  }
}
