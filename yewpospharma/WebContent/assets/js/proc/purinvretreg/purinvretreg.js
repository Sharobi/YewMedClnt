function deletePurchaseRetInv(pretinvid){
	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.purchaseReturnId = pretinvid;

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/purret/deletepurretinv.htm", CommonRelsetmapperObj, function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == '1') {
			document.getElementById('confirmmessagecont').innerHTML = getPurInvRetRegText.dataSucDelete;
			showConfirmModal();
		} else {
			document.getElementById('confirmmessagecont').innerHTML = getPurInvRetRegText.dataNotDelete;
			showConfirmModal();
		}

	});
}
function postPurchaseRetInv(pretinvid){
	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.purchaseReturnId = pretinvid;

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/purret/postpurretinv.htm", CommonRelsetmapperObj, function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == '1') {
			document.getElementById('confirmmessagecont').innerHTML = getPurInvRetRegText.dataSucPost;
			showConfirmModal();
		} else {
			document.getElementById('confirmmessagecont').innerHTML = getPurInvRetRegText.dataNotPost;
			showConfirmModal();
		}

	});
}

/* =============== Post All Functionality Starts =============== */

function postAllPR(){
	var purchaseReturn = {};
	var allpurchasereturndetails = [];
	/*$('#purrettable > tbody  > tr input[type="checkbox"]').each(function() {
		if(this.checked){
			var purchasereturndetails = {};
			purchasereturndetails.purchaseReturnId = this.id;
			allpurchasereturndetails.push(purchasereturndetails);
		}
	});*/
	if($("#example-select-all").prop("checked") == true){
		 var rows = $("#purrettable").dataTable().fnGetNodes();
		 for(var i=0;i<rows.length;i++)
	        {      
	              var status=$.trim( $(rows[i]).find("td:eq(8)").html());
	              if(status == "Unposted"){
				     var purchasereturndetails = {};
				     purchasereturndetails.purchaseReturnId = rows[i].id;
				     allpurchasereturndetails.push(purchasereturndetails);
				   
				}
	        }
	   }else{
		  $('#purrettable > tbody  > tr input[type="checkbox"]').each(function() {
			if(this.checked){
				var purchasereturndetails = {};
				purchasereturndetails.purchaseReturnId = this.id;
				allpurchasereturndetails.push(purchasereturndetails);
			}
		});
	   }
	purchaseReturn.purchaseReturnDetails = allpurchasereturndetails;
	if($.isEmptyObject(purchaseReturn.purchaseReturnDetails)){
		console.log("empty");
		document.getElementById('emptyalertmsg').innerHTML =  getFieldText.selectdata;
		$('#emptyAlertModal').modal('show');
	}else{
		//console.log("checkedid="+JSON.stringify(sale));
		$('#pleasewaitModal').modal('show');
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/purret/postallselectedpurchasereturn.htm", purchaseReturn, function(response) {
			$('#pleasewaitModal').modal('hide');
			console.log(response);
			if (response == '0') {
				document.getElementById('confirmmessagecont').innerHTML = getPurInvRetRegText.dataNotPost;
				showConfirmModal();
				
			} else {
				document.getElementById('confirmmessagecont').innerHTML = getPurInvRetRegText.dataSucPost;
				showConfirmModal();
			}

		});
	}
}

/* =============== Post All Functionality Ends =============== */

function targetURL(){
	location.href = BASE_URL + '/purret/loadpurretregister.htm';
}

function setChange(d){
	if (!d.checked) {
		$("#example-select-all").prop("checked", false);
  }
}