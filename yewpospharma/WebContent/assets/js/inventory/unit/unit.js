
function openAddEditModal(id,code,des,utypeid) {
	document.getElementById('alertMsg').innerHTML = '';
	$("#unit_id").val(id);
	$('#unitCodeId').val(code);
	$('#unitDescId').val(des);
	$('#unitTypeId').val(utypeid);
	if (id == 0) { // add
		$('#unitCodeId').attr("disabled",false);
		$("#headertext").text(getUnitText.headerTextAdd);
	} else { // update
		$('#unitCodeId').attr("disabled",true);
		$("#headertext").text(getUnitText.headerTextUpdate);
	}
	$('#unitAddEditModal').modal('show');
}

function addEditUnit() {
	document.getElementById('alertMsg').innerHTML = '';
	var unit_id = $("#unit_id").val();
	var unitCode = $('#unitCodeId').val();
	var unitDesc = $('#unitDescId').val();
	var unitTypeId = $('#unitTypeId').val();

	var code_label = $("#code_label").text();
	var code_field = code_label.substring(0, code_label.lastIndexOf(" "));
	
	var desc_label = $("#desc_label").text();
	var desc_field = desc_label.substring(0, desc_label.lastIndexOf(" "));
	
	var type_label = $("#type_label").text();
	var type_field = type_label.substring(0, type_label.lastIndexOf(" "));
	
	var field_names = [["unitCodeId",code_field],["unitDescId",desc_field],["unitTypeId",type_field]];
	if(fieldValidation(field_names)>0)
		{			
		}
	else {
		$('#unitAddEditModal').modal('hide');
		$('#pleasewaitModal').modal('show');
		if (unit_id == 0) { // add unit
			var UnitMasterObj = {};
			UnitMasterObj.code = unitCode;
			UnitMasterObj.description = unitDesc;
			UnitMasterObj.typeId = unitTypeId;
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/addunit.htm", UnitMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				var status = JSON.parse(response);
				chngeResultStat(status);

			});
		} else {// edit unit
			var UnitMasterObj = {};
			UnitMasterObj.id = unit_id;
			UnitMasterObj.code = unitCode;
			UnitMasterObj.description = unitDesc;
			UnitMasterObj.typeId = unitTypeId;

			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/editunit.htm", UnitMasterObj, function(response) {
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
	ajaxCallObject.callAjax(BASE_URL + "/invsetup/deleteUnit/" + id + ".htm", function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == 'success') {
			document.getElementById('confirmmessagecont').innerHTML = getUnitText.dataSucDelete;
			showConfirmModal();
		} else {
			document.getElementById('confirmmessagecont').innerHTML = getUnitText.dataNotDelete;
			showConfirmModal();
		}
	}, null);
}

function targetURL() {
	location.href = BASE_URL + '/invsetup/unit.htm';
}
