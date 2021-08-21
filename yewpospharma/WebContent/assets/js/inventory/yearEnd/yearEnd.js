function yearendstktrnsfr()
{
	document.getElementById('askFrPostedDiv').innerHTML = getYearEndText.askFrPosted;
	$("#askFrPostedModal").modal("show");
}

function confirm()
{
	$('#confirmModal').modal('show');
}

function DoConfirm()
{
	var commonResultMap = {};
	commonResultMap.asOnDate=$("#enddate").val();
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/yearend/stktrnsfrfryrend.htm", commonResultMap, function(response) {
		$('#pleasewaitModal').modal('hide');
		var status = JSON.parse(response);
		chngeResultStat(status);
	});
}

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}

function targetURL() {
	location.href = BASE_URL + '/yearend/loadyearend.htm';
}