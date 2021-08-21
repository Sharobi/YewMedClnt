/**
 * 
 */
function openAddEditModal(id,name,storeid)
{   
	 document.getElementById('alertMsg').innerHTML='';
	 if(id == ''){
		$("#headertext").text(getRoleText.headerTextAdd);
		$("#roleId").val('');
		$("#roleName").val('');
		$('#menuDiv').find('input[type=checkbox]:checked').removeAttr('checked');
	  
	 }else{
		$("#headertext").text(getRoleText.headerTextUpdate);
		var commonResultObj = {};
		commonResultObj.id=id;
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/rolemanagement/getRolebyId.htm", commonResultObj, function(response) {
		var menuList = JSON.parse(response);
		$("#roleId").val(id);
		$("#roleName").val(name);
		$("#storeName").val(storeid);
		   for(var i=0;i<menuList.length;i++){
			   if(menuList[i].isAll == 1){
				   $("#"+menuList[i].menuId).prop( "checked", true );
			   }else{
				   $("#"+menuList[i].menuId).prop( "checked", false );
			   }
		   }
		   
		});
	  }
	
	 $('#roleAddEditModal').modal('show');
	
}

function addOrUpdateRole()
{
	document.getElementById('alertMsg').innerHTML='';
	var roleId = $("#roleId").val();
	var role = {};
	role.name = $("#roleName").val();
	role.storeId = $("#storeName").val();
	var menuList =[];
	$('#menuDiv input:checkbox').each(function() {
		var id = $(this).attr('id');
		var menu ={};
		menu.menuId = id;
		if($("#"+id).prop("checked") == true){
			 menu.isAll = 1;
		 }else{
			 menu.isAll = 0;
		 }
		menuList.push(menu);
	});
	role.menuList=menuList;
	console.log(JSON.stringify(role));
	if($("#roleName").val() == ''){
		document.getElementById('alertMsg').innerHTML=getRoleText.roleNameRequire;
		return;
	  }else{
			if(roleId == ''){
				var ajaxCallObject = new CustomBrowserXMLObject();
				ajaxCallObject.callAjaxPost(BASE_URL + "/rolemanagement/addRole.htm", role, function(response) {
					var status = JSON.parse(response);
					$('#roleAddEditModal').modal('hide');
					chngeResultStat(status);
					
				});
		    }else{
		    	role.id=roleId;
		    	var ajaxCallObject = new CustomBrowserXMLObject();
				ajaxCallObject.callAjaxPost(BASE_URL + "/rolemanagement/updateRole.htm", role, function(response) {
					var status = JSON.parse(response);
					$('#roleAddEditModal').modal('hide');
					chngeResultStat(status);
					
				});
		    	
		    }
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
		ajaxCallObject.callAjax(BASE_URL + "/rolemanagement/deleteRole/" + id + ".htm", function(response) {
			/*$('#pleasewaitModal').modal('hide');*/
			var status =JSON.parse(response).id;
			if (status == 1) {
				document.getElementById('confirmmessagecont').innerHTML = getRoleText.dataSucDelete;
				showConfirmModal();
			} else if(status == -1){
				document.getElementById('confirmmessagecont').innerHTML = getRoleText.dataNotDeleteforused;
				showConfirmModal();
			}else{
				document.getElementById('confirmmessagecont').innerHTML = getRoleText.dataNotDelete;
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
		location.href = BASE_URL + '/rolemanagement/role.htm';
 }
