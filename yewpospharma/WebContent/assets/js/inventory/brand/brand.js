function openAddEditModal(id,name,des,utypeid) {
	document.getElementById('alertMsg').innerHTML = '';
	$("#brandId").val(id);
	$('#brandName').val(name);
	/*$('#unitDescId').val(des);
	$('#unitTypeId').val(utypeid);*/
	if (id == 0) { // add
		$("#headertext").text(getBrandText.headerTextAdd);
	} else { // update
		$("#headertext").text(getBrandText.headerTextUpdate);
	}
	$('#brandAddEditModal').modal('show');
}

function addEditBrand() {
	document.getElementById('alertMsg').innerHTML = '';
	var brandId = $("#brandId").val();
	var brandName = $('#brandName').val();

	var name_label = $("#name_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));
	
	var field_names = [["brandName",name_field]];
	
	if(fieldValidation(field_names)>0)
		{
			
		}
		else {
		$('#brandAddEditModal').modal('hide');
		$('#pleasewaitModal').modal('show');
		if (brandId == 0) { // add brand
			var BrandMasterObj = {};
			BrandMasterObj.name = brandName;

			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/brand/addbrand.htm", BrandMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				if (response == 'success') {
					document.getElementById('confirmmessagecont').innerHTML = getBrandText.dataSucAdd;
					showConfirmModal();
				} else {
					document.getElementById('confirmmessagecont').innerHTML = getBrandText.dataNotAdd;
					showConfirmModal();
				}

			});
		} else {// edit brand
			var BrandMasterObj = {};
			BrandMasterObj.id = brandId;
			BrandMasterObj.name = brandName;
 
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/brand/editbrand.htm", BrandMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				if (response == 'success') {
					document.getElementById('confirmmessagecont').innerHTML = getBrandText.dataSucUpdate;
					showConfirmModal();
				} else {
					document.getElementById('confirmmessagecont').innerHTML = getBrandText.dataNotUpdate;
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
	ajaxCallObject.callAjax(BASE_URL + "/brand/deleteBrand/" + id + ".htm", function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == 'success') {
			document.getElementById('confirmmessagecont').innerHTML = getBrandText.dataSucDelete;
			showConfirmModal();
		} else {
			document.getElementById('confirmmessagecont').innerHTML = getBrandText.dataNotDelete;
			showConfirmModal();
		}
	}, null);
}

function targetURL() {
	location.href = BASE_URL + '/brand/loadbrand.htm';
}
