function deletePurchaseOrder(poid){
	$('#confirmModalPur').modal('show');
	$("#confirmid").val(poid);
	$("#confirmtype").val(0); // for delete set as zero
	
}

function DoConfirmPurOrder(){
	var poid=$("#confirmid").val();
	var type=$("#confirmtype").val();
	if(type==1){// for post
		var CommonRelsetmapperObj = {};
		CommonRelsetmapperObj.purchaseOrderId = poid;

		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/purorder/postpurorder.htm", CommonRelsetmapperObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			/*if (response == '1') {
				document.getElementById('confirmmessagecont').innerHTML = getPurInvRegText.dataSucPost;
				showConfirmModal();
			} else {
				document.getElementById('confirmmessagecont').innerHTML = getPurInvRegText.dataNotPost;
				showConfirmModal();
			}*/
			
			var status = JSON.parse(response);
			chngeResultStat(status);

		});
	}
	if(type==0){// for delete
		var CommonRelsetmapperObj = {};
		CommonRelsetmapperObj.purchaseOrderId = poid;
	
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/purorder/deletepurorder.htm", CommonRelsetmapperObj, function(response) {
			/*$('#pleasewaitModal').modal('hide');
			if (response == '1') {
				document.getElementById('confirmmessagecont').innerHTML = getPurOrderRegText.dataSucDelete;
				showConfirmModal();
			} else {
				document.getElementById('confirmmessagecont').innerHTML = getPurOrderRegText.dataNotDelete;
				showConfirmModal();
			}*/
			var status = JSON.parse(response);
			chngeResultStat(status);
	
		});
	}
	if(type==2){// for close
		var CommonRelsetmapperObj = {};
		CommonRelsetmapperObj.purchaseOrderId = poid;
	
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/purorder/closepurorder.htm", CommonRelsetmapperObj, function(response) {
			
			var status = JSON.parse(response);
			chngeResultStat(status);
	
		});
	}
}

function postPurchaseInv(pinvid){
	$('#confirmModalPur').modal('show');
	$("#confirmid").val(pinvid);
	$("#confirmtype").val(1); // for post set as one
	
}

function closePurchaseOrder(pinvid){
	$('#confirmModalPur').modal('show');
	$("#confirmid").val(pinvid);
	$("#confirmtype").val(2); // for close set as two
	
}

function targetURL(){
	location.href = BASE_URL + '/purorder/loadpurordregister.htm';
}