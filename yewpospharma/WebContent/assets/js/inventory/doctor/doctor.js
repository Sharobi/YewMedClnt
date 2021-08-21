$(document).ready(function(){
	$("#opbal").keyup(function(){
		if(isNaN($("#opbal").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Opening Balance";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
		}
	});
	
	$("#commPer").keyup(function(){
		if(isNaN($("#commPer").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Commision Percentage";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
		}
	});
	
	$("#pin").keyup(function(){
		if(isNaN($("#pin").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Pin";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
		}
	});
});

function openAddEditModal(id) {
	document.getElementById('alertMsg').innerHTML = '';
	$('#doctorAddEditModal').find('input:text').val('');
	$('#doctorAddEditModal').find('input:hidden').val('');
	if (id == 0) { // add
		$("#headertext").text(getDoctorText.headerTextAdd);
		$("#locked").prop('checked', false);
	} else { // update
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjax(BASE_URL + "/invsetup/getDoctorbyId/" + id + ".htm", function(response) {
			$("#dctr_id").val($.parseJSON(response).id);
			$('#pin').val($.parseJSON(response).pin);
			$('#dctrName').val($.parseJSON(response).name);
			$('#code').val($.parseJSON(response).code);
			$('#qualification').val($.parseJSON(response).qualification);
			$('#speciality').val($.parseJSON(response).speciality);
			$('#addrs').val($.parseJSON(response).address);
			$('#city').val($.parseJSON(response).city);
			$('#state').val($.parseJSON(response).state);
			$('#country').val($.parseJSON(response).country);
			$('#phn').val($.parseJSON(response).phoneNo);
			$('#fax').val($.parseJSON(response).fax);
			$('#opbal').val($.parseJSON(response).opBal);
			$('#commPer').val($.parseJSON(response).commPer);
			if($.parseJSON(response).isLocked==1)
			{
				$("#locked").prop('checked', true);
			}
		}, null);
		$("#headertext").text(getDoctorText.headerTextUpdate);
	}
	$('#doctorAddEditModal').modal('show');
}

function addEditDoctor() {
	document.getElementById('alertMsg').innerHTML = '';
	var dctr_id = $("#dctr_id").val();
	var pin = $('#pin').val();
	var name = $('#dctrName').val();
	var code = $('#code').val();
	var qualification = $('#qualification').val();
	var speciality = $('#speciality').val();
	var addrs = $('#addrs').val();
	var city = $('#city').val();
	var state = $('#state').val();
	var country = $('#country').val();
	var phn = $('#phn').val();
	var fax = $('#fax').val();
	if($('#locked').is(":checked"))
	{
		var locked = 1;
	}
	else
	{
		var locked = 0;
	}
	
	if(isNaN($("#pin").val()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Pin";
		$(this).focus();
		return false;
	}
	else
	{
		document.getElementById('alertMsg').innerHTML = "";
	}
	
	if($('#opbal').val()==0 || $('#opbal').val()=="")
	{
		var opbal = 0;
	}
	else
	{
		if(isNaN($("#opbal").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Opening Balance";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
			var opbal = $('#opbal').val();
		}
	}
	if($('#commPer').val()==0 || $('#commPer').val()=="")
	{
		var commPer = 0;
	}
	else
	{
		if(isNaN($("#commPer").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Commision Percentage";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
			var commPer = $('#commPer').val();
		}
	}
	
	var name_label = $("#name_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));
	
	var field_names = [["dctrName",name_field]];
	if(fieldValidation(field_names)>0)
		{			
		}
	else {
		$('#doctorAddEditModal').modal('hide');
		$('#pleasewaitModal').modal('show');
		if (dctr_id == 0) { // add unit
			var DoctorMasterObj = {};
			DoctorMasterObj.code = code;
			DoctorMasterObj.name = name;
			DoctorMasterObj.qualification = qualification;			
			DoctorMasterObj.speciality = speciality;
			DoctorMasterObj.address = addrs;
			DoctorMasterObj.pin = pin;			
			DoctorMasterObj.city = city;
			DoctorMasterObj.state = state;
			DoctorMasterObj.country = country;			
			DoctorMasterObj.phoneNo = phn;
			DoctorMasterObj.fax = fax;
			DoctorMasterObj.opBal = opbal;
			DoctorMasterObj.commPer = commPer;
			DoctorMasterObj.isLocked = locked;
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/adddocctr.htm", DoctorMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				if (response == 0) {
					document.getElementById('confirmmessagecont').innerHTML = getDoctorText.dataNotAdd;
					showConfirmModal();					
				} else {
					document.getElementById('confirmmessagecont').innerHTML = getDoctorText.dataSucAdd;
					showConfirmModal();
				}

			});
		} else {// edit unit
			var DoctorMasterObj = {};
			DoctorMasterObj.id = dctr_id;
			DoctorMasterObj.code = code;
			DoctorMasterObj.name = name;
			DoctorMasterObj.qualification = qualification;			
			DoctorMasterObj.speciality = speciality;
			DoctorMasterObj.address = addrs;
			DoctorMasterObj.pin = pin;			
			DoctorMasterObj.city = city;
			DoctorMasterObj.state = state;
			DoctorMasterObj.country = country;			
			DoctorMasterObj.phoneNo = phn;
			DoctorMasterObj.fax = fax;
			DoctorMasterObj.opBal = opbal;
			DoctorMasterObj.commPer = commPer;
			DoctorMasterObj.isLocked = locked;
			
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/editdoctor.htm", DoctorMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				if (response == 'success') {
					document.getElementById('confirmmessagecont').innerHTML = getDoctorText.dataSucUpdate;
					showConfirmModal();
				} else {
					document.getElementById('confirmmessagecont').innerHTML = getDoctorText.dataNotUpdate;
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
	ajaxCallObject.callAjax(BASE_URL + "/invsetup/deleteDoctor/" + id + ".htm", function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == 'success') {
			document.getElementById('confirmmessagecont').innerHTML = getDoctorText.dataSucDelete;
			showConfirmModal();
		} else {
			document.getElementById('confirmmessagecont').innerHTML = getDoctorText.dataNotDelete;
			showConfirmModal();
		}
	}, null);
}

function targetURL() {
	location.href = BASE_URL + '/invsetup/doctor.htm';
}
