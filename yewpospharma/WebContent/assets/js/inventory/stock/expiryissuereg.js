function deleteExpiryInv(expid){
	
	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.expiryId = expid;

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/expiry/deleteexpinv.htm", CommonRelsetmapperObj, function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == '1') {
			$("#confirmval").val(0);
			document.getElementById('confirmmessagecont').innerHTML = getStockInvText.expDataSucDelete;
			showConfirmModal();
		} else {
			$("#confirmval").val(-1);
			document.getElementById('confirmmessagecont').innerHTML = getStockInvText.expDataNotDelete;
			showConfirmModal();
		}

	});
}

function postExpiryInv(expid){
	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.expiryId = expid;

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/expiry/postexpiryinv.htm", CommonRelsetmapperObj, function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == '1') {
			$("#confirmval").val(0);
			document.getElementById('confirmmessagecont').innerHTML = getStockInvText.expDataSucPost;
			showConfirmModal();
		} else {
			$("#confirmval").val(0);
			document.getElementById('confirmmessagecont').innerHTML = getStockInvText.expDataNotPost;
			showConfirmModal();
		}

	});
}

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}

function targetURL(){
	var result=$("#confirmval").val();
	console.log("save expiry inv id targetURL="+result);
	if(result==0){
		location.href = BASE_URL + '/stock/expissuereg.htm';
	}
	else if(result==-1){
		location.href = "#";
	}
}