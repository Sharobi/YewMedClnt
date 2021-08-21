function openAddEditModal(id,name) {
	document.getElementById('alertMsg').innerHTML = '';
	$("#scheduleName").val(name);
	$("#scheduleId").val(id);
	if (id == 0) { // add
		$("#headertext").text(getScheduleText.headerTextAdd);
	} else { // update
		$("#headertext").text(getScheduleText.headerTextUpdate);
	}
	$('#scheduleAddEditModal').modal('show');
}

function addEditSchedule()
{
	document.getElementById('alertMsg').innerHTML='';
	var scheduleName=$('#scheduleName').val();
	var id=$('#scheduleId').val();
	
	var name_label = $("#name_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));
	
	var field_names = [["scheduleName",name_field]];
	if(fieldValidation(field_names)>0)
		{			
		}
	else
		{
		$('#scheduleAddEditModal').modal('hide');
		$('#pleasewaitModal').modal('show');
		if(id==0)
		{
			var d = new Date();
			var ScheduleMasterObj = {};
			ScheduleMasterObj.name = scheduleName;
			
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/addSchedule.htm", ScheduleMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				var status = JSON.parse(response);
				chngeResultStat(status);
			});
		}
		else
		{
			var d = new Date();
			var ScheduleMasterObj = {};
			ScheduleMasterObj.id = id;
			ScheduleMasterObj.name = scheduleName;	
			
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/editSchedule.htm", ScheduleMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				var status = JSON.parse(response);
				chngeResultStat(status);
			});
		}
	}
}

function DoConfirm() {
	$('#pleasewaitModal').modal('show');
	var id = document.getElementById('confirmId').value;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/invsetup/deleteSchedule/" + id + ".htm", function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == 'success') {
			document.getElementById('confirmmessagecont').innerHTML = getScheduleText.dataSucDelete;
			showConfirmModal();
		} else {
			document.getElementById('confirmmessagecont').innerHTML = getScheduleText.dataNotDelete;
			showConfirmModal();
		}
	}, null);
}

function targetURL() {
	location.href = BASE_URL + '/invsetup/schedule.htm';
}