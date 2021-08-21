$(document).ready(function(){
	var currentDate = new Date();
	//alert(currentDate);
	//$('#date').val(moment().format('YYYY-MM-DD'));
	$('#date').datepicker({
		format : 'yyyy-mm-dd',
		endDate : currentDate,
		autoclose : true,
	});
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
	
	$("#creditLimit").keyup(function(){
		if(isNaN($("#creditLimit").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Credit Limit";
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
	$('#customerAddEditModal').find('input:text').val('');
	$('#customerAddEditModal').find('input:hidden').val('');
	if (id == 0) { // add
		$("#headertext").text(getCustomerText.headerTextAdd);
	} else { // update
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjax(BASE_URL + "/customer/getCustomerbyId/" + id + ".htm", function(response) {
			$("#cust_id").val($.parseJSON(response).id);
			$('#pin').val($.parseJSON(response).pin);
			$('#customerName').val($.parseJSON(response).name);
			$('#code').val($.parseJSON(response).code);
			$('#addrs').val($.parseJSON(response).address);
			$('#city').val($.parseJSON(response).city);
			$('#state').val($.parseJSON(response).state);
			$('#country').val($.parseJSON(response).country);
			$('#phn').val($.parseJSON(response).phoneNo);
			$('#fax').val($.parseJSON(response).fax);
			$('#opbal').val($.parseJSON(response).obBal);
			$('#creditLimit').val($.parseJSON(response).creditLimit);
			$("#aadharcard").val($.parseJSON(response).addharCardNo);
			$("#slgender").val($.parseJSON(response).gender);
			$("#age").val($.parseJSON(response).age);
			$("#guardian_name").val($.parseJSON(response).guardian_name);
			$("#gstNo").val($.parseJSON(response).gstNo);
			
			$("#consiNm").val($.parseJSON(response).consiName);
			$("#consiAddr").val($.parseJSON(response).consiAddress);
			$("#consiPhone").val($.parseJSON(response).consiPhone);
			$("#consiGstNo").val($.parseJSON(response).consiGstNo);
			$("#consiStateId").val($.parseJSON(response).consiStateId);
		}, null);
		$("#headertext").text(getCustomerText.headerTextUpdate);
	}
	$('#customerAddEditModal').modal('show');
}

function addEditCustomer() {
	document.getElementById('alertMsg').innerHTML = '';
	var cust_id = $("#cust_id").val();
	var pin = $('#pin').val();
	var name = $('#customerName').val();
	var code = $('#code').val();
	var qualification = $('#qualification').val();
	var speciality = $('#speciality').val();
	var addrs = $('#addrs').val();
	var city = $('#city').val();
	var state = $('#state').val();
	var country = $('#country').val();
	var phn = $('#phn').val();
	var fax = $('#fax').val();
	var slgender = $('#slgender').val();
	var age= $('#age').val();
	var guardian_name= $('#guardian_name').val();
	var gstNo= $('#gstNo').val();
	var consiNm= $('#consiNm').val();
	var consiAddr=$('#consiAddr').val();
	var consiPhone=$('#consiPhone').val();
	var consiGstNo=$('#consiGstNo').val();
	var consiStateId=$("#consiStateId").val();
	if (isEmpty(age)) {
		age=0;
	}
	
	
	if (Number($("#phn").val().length)<10) {
		document.getElementById('alertMsg').innerHTML = "Mobile Number should be 10 digit.";
		$(this).focus();
		return false;
	} else {
		document.getElementById('alertMsg').innerHTML = "";
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
	
	if(phn.length<10)
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.phnDigitLengthCheckErr;
		$("#phn").focus();
		return false;
	}
	else
	{
		document.getElementById('alertMsg').innerHTML = "";
	}
	
	if($('#creditLimit').val()==0 || $('#creditLimit').val()=="")
	{
		var creditLimit = 0.0;
	}
	else
	{
		if(isNaN($("#creditLimit").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Credit Limit";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
			var creditLimit = $('#creditLimit').val();
		}
	}

	var name_label = $("#name_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));
	
	var phn_label = $("#phn_label").text();
	var phn_field = phn_label.substring(0, phn_label.lastIndexOf(" "));
	
	var field_names = [["customerName",name_field],["phn",phn_field]];
	
	if(fieldValidation(field_names)>0)
		{			
		}
	else {
		$('#customerAddEditModal').modal('hide');
		$('#pleasewaitModal').modal('show');
		if (cust_id == 0) { // add unit
			var CustomerMasterObj = {};
			CustomerMasterObj.code = code;
			CustomerMasterObj.name = name;
			CustomerMasterObj.address = addrs;
			CustomerMasterObj.pin = pin;			
			CustomerMasterObj.city = city;
			CustomerMasterObj.state = state;
			CustomerMasterObj.country = country;			
			CustomerMasterObj.phoneNo = phn;
			CustomerMasterObj.fax = fax;
			CustomerMasterObj.obBal = opbal;
			CustomerMasterObj.creditLimit = creditLimit;
			CustomerMasterObj.addharCardNo = $("#aadharcard").val();
			CustomerMasterObj.gender = slgender;
		 	CustomerMasterObj.guardian_name = guardian_name;
		    CustomerMasterObj.age = age;
		    CustomerMasterObj.gstNo = gstNo;
		    CustomerMasterObj.consiName = consiNm;
		    CustomerMasterObj.consiAddress = consiAddr;
		    CustomerMasterObj.consiPhone = consiPhone;
		    CustomerMasterObj.consiGstNo = consiGstNo;
		    CustomerMasterObj.consiStateId=0;
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/customer/addcustomer.htm", CustomerMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				var status = JSON.parse(response);
				chngeResultStat(status);
				/*if (response == 0) {
					document.getElementById('confirmmessagecont').innerHTML = getCustomerText.dataNotAdd;
					showConfirmModal();					
				} else {
					document.getElementById('confirmmessagecont').innerHTML = getCustomerText.dataSucAdd;
					showConfirmModal();
				}*/

			});
		} else {// edit unit
			var CustomerMasterObj = {};
			CustomerMasterObj.id = cust_id;
			CustomerMasterObj.code = code;
			CustomerMasterObj.name = name;
			CustomerMasterObj.address = addrs;
			CustomerMasterObj.pin = pin;			
			CustomerMasterObj.city = city;
			CustomerMasterObj.state = state;
			CustomerMasterObj.country = country;			
			CustomerMasterObj.phoneNo = phn;
			CustomerMasterObj.fax = fax;
			CustomerMasterObj.obBal = opbal;
			CustomerMasterObj.creditLimit = creditLimit;
			CustomerMasterObj.addharCardNo = $("#aadharcard").val();
			CustomerMasterObj.gender = slgender;
		 	CustomerMasterObj.guardian_name = guardian_name;
		 	CustomerMasterObj.age = age;
		    CustomerMasterObj.gstNo = gstNo;
		    CustomerMasterObj.consiName = consiNm;
		    CustomerMasterObj.consiAddress = consiAddr;
		    CustomerMasterObj.consiPhone = consiPhone;
		    CustomerMasterObj.consiGstNo = consiGstNo;
		    CustomerMasterObj.consiStateId=consiStateId;
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/customer/editcustomer.htm", CustomerMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				var status = JSON.parse(response);
				chngeResultStat(status);
				/*if (response == 'success') {
					document.getElementById('confirmmessagecont').innerHTML = getCustomerText.dataSucUpdate;
					showConfirmModal();
				} else {
					document.getElementById('confirmmessagecont').innerHTML = getCustomerText.dataNotUpdate;
					showConfirmModal();
				}*/

			});

		}
	}
}

function DoConfirm() {
	$('#pleasewaitModal').modal('show');
	var id = document.getElementById('confirmId').value;
	
	var CommonResultSetMapper = {};
	CommonResultSetMapper.custId = id;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/customer/delcustomer.htm", CommonResultSetMapper, function(response) {
		$('#pleasewaitModal').modal('hide');
		var status = JSON.parse(response);
		chngeResultStat(status);
	});
	/*ajaxCallObject.callAjax(BASE_URL + "/customer/deleteCustomer/" + id + ".htm", function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == 'success') {
			document.getElementById('confirmmessagecont').innerHTML = getCustomerText.dataSucDelete;
			showConfirmModal();
		} else {
			document.getElementById('confirmmessagecont').innerHTML = getCustomerText.dataNotDelete;
			showConfirmModal();
		}
	}, null);*/
}

function targetURL() {
	location.href = BASE_URL + '/customer/loadcustomer.htm';
}
function isEmpty(val) {



    return (val === undefined || val == null || val.length <= 0) ? true : false;
}