
function openAddEditModal(id,name) {
	document.getElementById('alertMsg').innerHTML = '';
	$("#content_id").val(id);
	//$('#content_code').val(code);
	$('#content_name').val(name);
	if (id == 0) { // add
		$("#headertext").text(getContentText.headerTextAdd);
	} else { // update
		$("#headertext").text(getContentText.headerTextUpdate);
	}
	$('#contentAddEditModal').modal('show');
}

function addEditContent() {
	document.getElementById('alertMsg').innerHTML = '';
	var content_id = $("#content_id").val();
	//var content_code = $('#content_code').val();
	var content_name = $('#content_name').val();
	
	var name_label = $("#name_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));
	
	var field_names = [["content_name",name_field]];
	if(fieldValidation(field_names)>0)
		{			
		}
	else {
		$('#contentAddEditModal').modal('hide');
		$('#pleasewaitModal').modal('show');
		if (content_id == 0) { // add Content
			var ContentMasterObj = {};
			//ContentMasterObj.code = content_code;
			ContentMasterObj.name = content_name;
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/content/addContent.htm", ContentMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				var status = JSON.parse(response);
				chngeResultStat(status);

			});
		} else {// edit Content
			var ContentMasterObj = {};
			ContentMasterObj.id = content_id;
			//ContentMasterObj.code = content_code;
			ContentMasterObj.name = content_name;
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/content/editContent.htm", ContentMasterObj, function(response) {
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
	ajaxCallObject.callAjax(BASE_URL + "/content/deleteContent/" + id + ".htm", function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == 'success') {
			document.getElementById('confirmmessagecont').innerHTML = getContentText.dataSucDelete;
			showConfirmModal();
		} else {
			document.getElementById('confirmmessagecont').innerHTML = getContentText.dataNotDelete;
			showConfirmModal();
		}
	}, null);
}

function targetURL() {
	location.href = BASE_URL + '/content/loadcontent.htm';
}
