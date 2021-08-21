function openAddEditModal(id,name) {
	document.getElementById('alertMsg').innerHTML = '';
	$("#RackName").val(name);
	$("#RackId").val(id);
	if (id == 0) { // add
		$("#headertext").text(getRackText.headerTextAdd);
	} else { // update
		$("#headertext").text(getRackText.headerTextUpdate);
	}
	$('#rackAddEditModal').modal('show');
}

function addEditRack()
{
	document.getElementById('alertMsg').innerHTML='';
	var rackName=$('#RackName').val();
	var id=$('#RackId').val();
	
	var name_label = $("#name_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));
	
	var field_names = [["RackName",name_field]];
	if(fieldValidation(field_names)>0)
		{			
		}
	else
		{
		$('#rackAddEditModal').modal('hide');
		$('#pleasewaitModal').modal('show');
		if(id==0)
		{
			var d = new Date();
			var RackMasterObj = {};
			RackMasterObj.name = rackName;
			
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/addrack.htm", RackMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				if (response == 'success') {
					document.getElementById('confirmmessagecont').innerHTML=getRackText.dataSucAdd;
					showConfirmModal();
				}
				else{
					document.getElementById('confirmmessagecont').innerHTML=getRackText.dataNotAdd;
					showConfirmModal();
				}
			});
		}
		else
		{
			var d = new Date();
			var RackMasterObj = {};
			RackMasterObj.id = id;
			RackMasterObj.name = rackName;	
			
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/editrack.htm", RackMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				if (response == 'success') {
					document.getElementById('confirmmessagecont').innerHTML=getRackText.dataSucUpdate;
					showConfirmModal();
				}
				else{
					document.getElementById('confirmmessagecont').innerHTML=getRackText.dataNotUpdate;
					showConfirmModal();
				}
			});
		}
	}
}

function DoConfirm() {
	$('#pleasewaitModal').modal('show');
	var id = document.getElementById('confirmId').value;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/invsetup/deleteRack/" + id + ".htm", function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == 'success') {
			document.getElementById('confirmmessagecont').innerHTML = getRackText.dataSucDelete;
			showConfirmModal();
		} else {
			document.getElementById('confirmmessagecont').innerHTML = getRackText.dataNotDelete;
			showConfirmModal();
		}
	}, null);
}

function targetURL() {
	location.href = BASE_URL + '/invsetup/rack.htm';
}