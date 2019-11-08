window.onload = function() {
		
		document.getElementById(menuToActivate).className = "nav-item active";
		

		if (element) {
			element.onclick = function() {
				var alertElement = element.parentNode;
				alertElement.parentNode.removeChild(alertElement);
			}
		}
		
		document.getElementById("page" + pageNo).className = "page-link-active";

		if (pageNo == 1) {
			document.getElementById("prev").parentNode.className = "page-item disabled";
		}

		if (pageNo == totalPages) {
			document.getElementById("next").parentNode.className = "page-item disabled";
		}
		
	};