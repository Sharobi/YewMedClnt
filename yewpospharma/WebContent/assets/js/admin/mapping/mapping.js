/**
 * 
 */
function openAddEditModal(mappingId,userId,roleId)
{
	 
	 document.getElementById('alertMsg').innerHTML = '';
	 if(mappingId == ''){
		    $("#headertext").text(getMappingText.headerTextAdd);
			$("#mappingId").val("");
			$("#userData").val("0");
			$("#roleData").val("0");
			$('#mappingAddEditModal').modal('show');
	 }else{
		    $("#headertext").text(getMappingText.headerTextUpdate);
		    $("#mappingId").val(mappingId);
		    $("#userData").val(userId);
			$("#roleData").val(roleId);
			$('#mappingAddEditModal').modal('show');
	 }
	 
	
}


function addEditRoleUserMapping()
{
	document.getElementById('alertMsg').innerHTML='';
	var id = $("#mappingId").val();
	var user = $("#userData").val();
	var role = $("#roleData").val();
	var userName = $("#userData").find("option:selected").text(); 
	var roleName = $("#roleData").find("option:selected").text();
	 
	
			var user_label = $("#user_label").text();
			var user_field = user_label.substring(0, user_label.lastIndexOf(" "));	
			var role_label = $("#role_label").text();
			var role_field = role_label.substring(0, role_label.lastIndexOf(" "));
			
			
			if(user=='0')
			{
				document.getElementById('alertMsg').innerHTML=user_field+ " " + getMappingText.fieldReq;
				return false;
			}
			
			if(role=='0')
			{
				document.getElementById('alertMsg').innerHTML=role_field+ " " + getMappingText.fieldReq;
				return false;
			}
			
			    $('#mappingAddEditModal').modal('hide');
				/*$('#pleasewaitModal').modal('show');*/
				if(id==0)
				{
					var userrolemapping = {};
				    userrolemapping.userId=user;
					userrolemapping.userName=$('#userData :selected').attr('label'); 
					userrolemapping.roleId=role;
					userrolemapping.roleName=$('#roleData :selected').attr('label'); 
					
					var ajaxCallObject = new CustomBrowserXMLObject();
					ajaxCallObject.callAjaxPost(BASE_URL + "/mapping/addMapping.htm", userrolemapping, function(response) {
						$('#pleasewaitModal').modal('hide');
						var status = JSON.parse(response);
						chngeResultStat(status);
					});
				}
				else
				{
					var userrolemapping = {};
					userrolemapping.id=id;
					userrolemapping.userId=user;
					userrolemapping.userName=$('#userData :selected').attr('label'); 
					userrolemapping.roleId=role;
					userrolemapping.roleName=$('#roleData :selected').attr('label'); 
					
					var ajaxCallObject = new CustomBrowserXMLObject();
					ajaxCallObject.callAjaxPost(BASE_URL + "/mapping/updateMapping.htm", userrolemapping, function(response) {
						/*$('#pleasewaitModal').modal('hide');*/
						var status = JSON.parse(response);
						chngeResultStat(status);
					});
				}
			
		
}

function showRoleDelConfirmationModal(id)
{
	   document.getElementById('confirmId').value=id;
	   $('#confirmModal').modal('show');
}
 function DoConfirm()
 {
	   /*$('#pleasewaitModal').modal('show');*/
		var id = document.getElementById('confirmId').value;
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjax(BASE_URL + "/mapping/deleteMapping/" + id + ".htm", function(response) {
			/*$('#pleasewaitModal').modal('hide');*/
			var status =JSON.parse(response).id;
			if (status == 1) {
				document.getElementById('confirmmessagecont').innerHTML = getMappingText.dataSucDelete;
				showConfirmModal();
			} else if(status == -1){
				document.getElementById('confirmmessagecont').innerHTML = getMappingText.dataNotDeleteforused;
				showConfirmModal();
			}else{
				document.getElementById('confirmmessagecont').innerHTML = getMappingText.dataNotDelete;
				showConfirmModal();
			}
		}, null);
	 
	 
 }
 function showConfirmModal()
 {
 	$('#confirmMessageModal').modal('show');
 }
 function targetURL() 
 {
		location.href = BASE_URL + '/mapping/map.htm';
 }

