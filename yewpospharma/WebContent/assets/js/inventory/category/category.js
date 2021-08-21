function openAddEditModal(id,name) {
	document.getElementById('alertMsg').innerHTML = '';
	$("#CatName").val(name);
	$("#CatId").val(id);
	if (id == 0) { // add
		$("#headertext").text(getCategoryText.headerTextAdd);
	} else { // update
		$("#headertext").text(getCategoryText.headerTextUpdate);
	}
	$('#catAddEditModal').modal('show');
}

function addEditCat()
{
	document.getElementById('alertMsg').innerHTML='';
	var catName=$('#CatName').val();
	var id=$('#CatId').val();
	var catname_label = $("#catname_label").text();
	var CatName_field = catname_label.substring(0, catname_label.lastIndexOf(" "));
	var field_names = [["CatName",CatName_field]];
	if(fieldValidation(field_names)>0)
		{			
		}
	else
		{
		$('#catAddEditModal').modal('hide');
		$('#pleasewaitModal').modal('show');
		if(id==0)
		{
			var d = new Date();
			var CategoryMasterObj = {};
			CategoryMasterObj.name = catName;
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/addcat.htm", CategoryMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				if (response == 'success') {
					document.getElementById('confirmmessagecont').innerHTML=getCategoryText.dataSucAdd;
					showConfirmModal();
				}
				else{
					document.getElementById('confirmmessagecont').innerHTML=getCategoryText.dataNotAdd;
					showConfirmModal();
				}
			});
		}
		else
		{
			var d = new Date();
			var CategoryMasterObj = {};
			CategoryMasterObj.id = id;
			CategoryMasterObj.name = catName;		
			
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/editcat.htm", CategoryMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				if (response == 'success') {
					document.getElementById('confirmmessagecont').innerHTML=getCategoryText.dataSucUpdate;
					showConfirmModal();
				}
				else{
					document.getElementById('confirmmessagecont').innerHTML=getCategoryText.dataNotUpdate;
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
	ajaxCallObject.callAjax(BASE_URL + "/invsetup/deleteCat/" + id + ".htm", function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == 'success') {
			document.getElementById('confirmmessagecont').innerHTML = getCategoryText.dataSucDelete;
			showConfirmModal();
		} else {
			document.getElementById('confirmmessagecont').innerHTML = getCategoryText.dataNotDelete;
			showConfirmModal();
		}
	}, null);
}

function targetURL() {
	location.href = BASE_URL + '/invsetup/category.htm';
}