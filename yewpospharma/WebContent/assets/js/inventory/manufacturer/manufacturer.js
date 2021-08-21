
function openAddEditModal(id,code,name,address,phn,fax,email,url) {
	document.getElementById('alertMsg').innerHTML = '';
	$("#manufctr_id").val(id);
	$('#manufctr_code').val(code);
	$('#manufctr_name').val(name);
	$('#addrs').val(address);
	$('#phn').val(phn);
	$('#fax').val(fax);
	$('#email').val(email);
	$('#url').val(url);
	if (id == 0) { // add
		$('#manufctr_code').attr("disabled",false);
		$("#headertext").text(getManufacturerText.headerTextAdd);
	} else { // update
		$('#manufctr_code').attr("disabled",true);
		$("#headertext").text(getManufacturerText.headerTextUpdate);
	}
	$('#manufacturerAddEditModal').modal('show');
}

function addEditManufacturer() {
	document.getElementById('alertMsg').innerHTML = '';
	var manufctr_id = $("#manufctr_id").val();
	var manufctr_code = $('#manufctr_code').val();
	var manufctr_name = $('#manufctr_name').val();
	var addrs = $('#addrs').val();
	var phn = $('#phn').val();
	var fax = $('#fax').val();
	var email = $('#email').val();
	var url = $('#url').val();
	
	var code_label = $("#code_label").text();
	var code_field = code_label.substring(0, code_label.lastIndexOf(" "));
	
	var name_label = $("#name_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));
	
	var addrs_label = $("#addrs_label").text();
	var addrs_field = addrs_label.substring(0, addrs_label.lastIndexOf(" "));
	
	var field_names = [["manufctr_code",code_field],["manufctr_name",name_field],["addrs",addrs_field]];
	
	
	
	if(fieldValidation(field_names)>0)
		{
			
		}
		else {
		$('#manufacturerAddEditModal').modal('hide');
		$('#pleasewaitModal').modal('show');
		if (manufctr_id == 0) { // add Manufacturer
			var ManufacturerMasterObj = {};
			ManufacturerMasterObj.code = manufctr_code;
			ManufacturerMasterObj.name = manufctr_name;
			ManufacturerMasterObj.address = addrs;
			ManufacturerMasterObj.phone = phn;
			ManufacturerMasterObj.fax = fax;
			ManufacturerMasterObj.email = email;
			ManufacturerMasterObj.url = url;
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/manufacturer/addManufacturer.htm", ManufacturerMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				var status = JSON.parse(response);
				chngeResultStat(status);

			});
		} else {// edit Manufacturer
			var ManufacturerMasterObj = {};
			ManufacturerMasterObj.id = manufctr_id;
			ManufacturerMasterObj.code = manufctr_code;
			ManufacturerMasterObj.name = manufctr_name;
			ManufacturerMasterObj.address = addrs;
			ManufacturerMasterObj.phone = phn;
			ManufacturerMasterObj.fax = fax;
			ManufacturerMasterObj.email = email;
			ManufacturerMasterObj.url = url;
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/manufacturer/editManufacturer.htm", ManufacturerMasterObj, function(response) {
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
	ajaxCallObject.callAjax(BASE_URL + "/manufacturer/deleteManufacturer/" + id + ".htm", function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == 'success') {
			document.getElementById('confirmmessagecont').innerHTML = getManufacturerText.dataSucDelete;
			showConfirmModal();
		} else {
			document.getElementById('confirmmessagecont').innerHTML = getManufacturerText.dataNotDelete;
			showConfirmModal();
		}
	}, null);
}



function targetURL() {
	location.href = BASE_URL + '/manufacturer/loadmanufacturer.htm';
}


