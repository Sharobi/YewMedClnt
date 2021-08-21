function openAddEditModal(id,name,cat_id,cat_name) {
	document.getElementById('alertMsg').innerHTML = '';
	$("#SubCatName").val(name);
	$("#SubCatId").val(id);
	if (id == 0) { // add
		$("#headertext").text(getSubCategoryText.headerTextAdd);	
		$("#catNameList").attr("disabled",false);
		$("#catNameList").val(0);
	} else { // update
		$("#headertext").text(getSubCategoryText.headerTextUpdate);	
		$("#catNameList").val(cat_id);
		$("#catNameList").attr("disabled",true);
	}

	$('#subcatAddEditModal').modal('show');
}


function addEditSubCat()
{
	document.getElementById('alertMsg').innerHTML='';
	var subCatName=$('#SubCatName').val();
	var subCatId=$('#SubCatId').val();
	var CatId=$('#catNameList').val();
	if(CatId==0)
	{
		document.getElementById('alertMsg').innerHTML=getSubCategoryText.catBlankError;
	}
	else
		{
			var name_label = $("#name_label").text();
			var name_field = name_label.substring(0, name_label.lastIndexOf(" "));
		
			var field_names = [["SubCatName",name_field]];
			if(fieldValidation(field_names)>0)
				{			
				}
			else
				{
				$('#subcatAddEditModal').modal('hide');
				$('#pleasewaitModal').modal('show');
				if(subCatId==0)
				{
					var d = new Date();
					//var CatId=$('#catNameList').val();
					var SubCategoryMasterObj = {};
					SubCategoryMasterObj.name = subCatName;
					SubCategoryMasterObj.categoryId = CatId;
					
					var ajaxCallObject = new CustomBrowserXMLObject();
					ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/addsubcat.htm", SubCategoryMasterObj, function(response) {
						$('#pleasewaitModal').modal('hide');
						if (response == 'success') {
							document.getElementById('confirmmessagecont').innerHTML=getSubCategoryText.dataSucAdd;
							showConfirmModal();
						}
						else{
							document.getElementById('confirmmessagecont').innerHTML=getSubCategoryText.dataNotAdd;
							showConfirmModal();
						}
					});
				}
				else
				{
					var d = new Date();
					var SubCategoryMasterObj = {};
					SubCategoryMasterObj.id = subCatId;
					SubCategoryMasterObj.name = subCatName;		
					
					var ajaxCallObject = new CustomBrowserXMLObject();
					ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/editsubcat.htm", SubCategoryMasterObj, function(response) {
						$('#pleasewaitModal').modal('hide');
						if (response == 'success') {
							document.getElementById('confirmmessagecont').innerHTML=getSubCategoryText.dataSucUpdate;
							showConfirmModal();
						}
						else{
							document.getElementById('confirmmessagecont').innerHTML=getSubCategoryText.dataNotUpdate;
							showConfirmModal();
						}
					});
				}
			}
		}
}

function DoConfirm() {
	$('#pleasewaitModal').modal('show');
	var id = document.getElementById('confirmId').value;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/invsetup/deleteSubCat/" + id + ".htm", function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == 'success') {
			document.getElementById('confirmmessagecont').innerHTML = getSubCategoryText.dataSucDelete;
			showConfirmModal();
		} else {
			document.getElementById('confirmmessagecont').innerHTML = getSubCategoryText.dataNotDelete;
			showConfirmModal();
		}
	}, null);
}

function targetURL() {
	location.href = BASE_URL + '/invsetup/subcategory.htm';
}