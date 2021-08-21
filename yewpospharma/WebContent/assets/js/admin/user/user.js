/**
 * Administrator User Management.
 */

function openAddEditModal(id,fname,lname,userName,email,storeId,phone,isActive,isLocked) 
 {
	document.getElementById('alertMsg').innerHTML = '';
	if (id == "") { // add
		$("#headertext").text(getUserText.headerTextAdd);
		$("#userId").val("");
		$("#fName").val("");
		$("#lName").val("");
		$("#uName").val("");
		$("#email").val("");
		$("#storeName").val(activeStoreId);
		$("#phone").val("");
		$("#activityStatus").val('1');
		$("#lockStatus").val('0');
		$("#password").val("");
		$("#pwd").css('visibility', 'visible');
		$('#userAddEditModal').modal('show');
	} else { // update
		/*var commonResultObj = {};
		commonResultObj.id=id;
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/usermanagement/getUserbyId.htm", commonResultObj, function(response) {*/
			/*var data = JSON.parse(response);*/
			$("#headertext").text(getUserText.headerTextUpdate);
			$("#userId").val(id);
			$("#fName").val(fname);
			$("#lName").val(lname);
			$("#uName").val(userName);
			$("#email").val(email);
			$("#storeName").val(storeId);
			$("#phone").val(phone);
			$("#activityStatus").val(isActive);
			$("#lockStatus").val(isLocked);
			$("#password").val("");
			$("#pwd").css('visibility', 'hidden');
			$('#userAddEditModal').modal('show');
		/*});*/
	}	
}


function addEditUser()
{
	document.getElementById('alertMsg').innerHTML='';
	var id = $("#userId").val();
	var fname = $("#fName").val();
	var lname = $("#lName").val();
	var userName = $("#uName").val();
	var email = $("#email").val();
	var storeId = $("#storeName").val();
	var phone = $("#phone").val();
	var isActive = $("#activityStatus").val();
	var isLocked = $("#lockStatus").val();
	var password = $("#password").val();
	
			var fname_label = $("#fName_label").text();
			var fname_field = fname_label.substring(0, fname_label.lastIndexOf(" "));	
			var lname_label = $("#lName_label").text();
			var lname_field = lname_label.substring(0, lname_label.lastIndexOf(" "));
			var email_label = $("#email_label").text();
			var email_field = email_label.substring(0, email_label.lastIndexOf(" "));
			var storeId_label = $("#storeId_label").text();
			var storeId_field = storeId_label.substring(0, storeId_label.lastIndexOf(" "));
			var phone_label = $("#phone_label").text();
			var phone_field = phone_label.substring(0, phone_label.lastIndexOf(" "));
			var isActive_label = $("#isActive_label").text();
			var isActive_field = isActive_label.substring(0, isActive_label.lastIndexOf(" "));
			var isLocked_label = $("#isLocked_label").text();
			var isLocked_field = isLocked_label.substring(0, isLocked_label.lastIndexOf(" "));
			var password_label = $("#password_label").text();
			var password_field = password_label.substring(0, password_label.lastIndexOf(" "));
			var uname_label = $("#uName_label").text();
			var uname_field = uname_label.substring(0, uname_label.lastIndexOf(" "));
			
			if(fname=="" || fname==undefined)
			{
				document.getElementById('alertMsg').innerHTML=fname_field+ " " + getUserText.fieldReq;
				return false;
			}
			
			if(lname=="" || lname==undefined)
			{
				document.getElementById('alertMsg').innerHTML=lname_field+ " " + getUserText.fieldReq;
				return false;
			}
			
			
			if(email=="" || email==undefined)
			{
				document.getElementById('alertMsg').innerHTML=email_field+ " " + getUserText.fieldReq;
				return false;
			}
			
			
			if(phone=="" || phone==undefined)
			{
				document.getElementById('alertMsg').innerHTML=phone_field+ " " + getUserText.fieldReq;
				return false;
			}
			if(isActive=="" || isActive==undefined)
			{
				document.getElementById('alertMsg').innerHTML=isActive_field+ " " + getUserText.fieldReq;
				return false;
			}
			if(isLocked=="" || isLocked==undefined)
			{
				document.getElementById('alertMsg').innerHTML=isLocked_field+ " " + getUserText.fieldReq;
				return false;
			}
			if(id == ""){
				if(password=="" || password==undefined)
				{
					document.getElementById('alertMsg').innerHTML=password_field+ " " + getUserText.fieldReq;
					return false;
				}
			}
			if(userName=="" || userName==undefined)
			{
				document.getElementById('alertMsg').innerHTML=uname_field+ " " + getUserText.fieldReq;
				return false;
			}
			
			$('#userAddEditModal').modal('hide');
				$('#pleasewaitModal').modal('show');
				if(id==0)
				{
					var commonResultObj = {};
					commonResultObj.fname=fname;
					commonResultObj.lname=lname;
					commonResultObj.userName=userName;
					commonResultObj.password=password;
					commonResultObj.email=email;
					commonResultObj.storeId=storeId;
					commonResultObj.phone=phone;
					commonResultObj.isActive=isActive;
					commonResultObj.isLocked=isLocked;
					
					var ajaxCallObject = new CustomBrowserXMLObject();
					ajaxCallObject.callAjaxPost(BASE_URL + "/usermanagement/addUser.htm", commonResultObj, function(response) {
						$('#pleasewaitModal').modal('hide');
						var status = JSON.parse(response);
						chngeResultStat(status);
					});
				}
				else
				{
					var commonResultObj = {};
					commonResultObj.id=id;
					commonResultObj.fname=fname;
					commonResultObj.lname=lname;
					commonResultObj.userName=userName;
					commonResultObj.password=password;
					commonResultObj.email=email;
					commonResultObj.storeId=storeId;
					commonResultObj.phone=phone;
					commonResultObj.isActive=isActive;
					commonResultObj.isLocked=isLocked;
					
					
					var ajaxCallObject = new CustomBrowserXMLObject();
					ajaxCallObject.callAjaxPost(BASE_URL + "/usermanagement/editUser.htm", commonResultObj, function(response) {
						$('#pleasewaitModal').modal('hide');
						var status = JSON.parse(response);
						chngeResultStat(status);
					});
				}
			
		
}

function DoConfirm() 
 {
	$('#pleasewaitModal').modal('show');
	var id = document.getElementById('confirmId').value;
	var commonResultObj = {};
	commonResultObj.id = id;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/usermanagement/deleteUser.htm", commonResultObj, function(response) {
		$('#pleasewaitModal').modal('hide');
		var status = JSON.parse(response);
		chngeResultStat(status);
	}, null);
}

function targetURL() 
 {
	location.href = BASE_URL + '/usermanagement/user.htm';
 }