
function openAddEditModal(id,name,des) {
	document.getElementById('alertMsg').innerHTML = '';
	$("#grp_id").val(id);
	$('#grpName').val(name);
	$('#grpDesc').val(des);
	if (id == 0) { // add
		$("#headertext").text(getGroupText.headerTextAdd);
	} else { // update
		$("#headertext").text(getGroupText.headerTextUpdate);
	}
	$('#groupAddEditModal').modal('show');
}

function addEditGroup() {
	document.getElementById('alertMsg').innerHTML = '';
	var grp_id = $("#grp_id").val();
	var groupName = $('#grpName').val();
	var groupDesc = $('#grpDesc').val();

	var name_label = $("#name_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));
	
	var desc_label = $("#desc_label").text();
	var desc_field = desc_label.substring(0, desc_label.lastIndexOf(" "));
	
	var field_names = [["grpName",name_field],["grpDesc",desc_field]];
	if(fieldValidation(field_names)>0)
		{			
		}
	else {
		$('#groupAddEditModal').modal('hide');
		$('#pleasewaitModal').modal('show');
		if (grp_id == 0) { // add unit
			var groupMasterObj = {};
			groupMasterObj.name = groupName;
			groupMasterObj.description = groupDesc;	

			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/addgroup.htm", groupMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				var status = JSON.parse(response);
				chngeResultStat(status);

			});
		} else {// edit unit
			var groupMasterObj = {};
			groupMasterObj.id = grp_id;
			groupMasterObj.name = groupName;
			groupMasterObj.description = groupDesc;

			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/editgroup.htm", groupMasterObj, function(response) {
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
	ajaxCallObject.callAjax(BASE_URL + "/invsetup/deleteGroup/" + id + ".htm", function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == 'success') {
			document.getElementById('confirmmessagecont').innerHTML = getGroupText.dataSucDelete;
			showConfirmModal();
		} else {
			document.getElementById('confirmmessagecont').innerHTML = getGroupText.dataNotDelete;
			showConfirmModal();
		}
	}, null);
}

function targetURL() {
	location.href = BASE_URL + '/invsetup/group.htm';
}
