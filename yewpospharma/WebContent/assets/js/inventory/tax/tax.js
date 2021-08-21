function openAddEditModal(id) {
	document.getElementById('alertMsg').innerHTML = '';
	$("#taxAddEditModal").find('input:text').val('');
	$("#taxAddEditModal").find('input:hidden').val('');
	$("#singleTaxList").empty();
	$("#snglTaxDiv").addClass("hide");
	$("#taxIsGrp").val(0);
	$("#taxIsGrp").attr("disabled",false);
	
	if (id == 0) { // add
		$("#saveTaxBtn").removeClass("hide");
		$("#updateTaxBtn").addClass("hide");
		$("#headertext").text(getTaxText.headerTextAdd);
		$('#taxAddEditModal').modal('show');
	} else { // update
		$("#taxId").val(id);
		$("#updateTaxBtn").removeClass("hide");
		$("#saveTaxBtn").addClass("hide");
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjax(BASE_URL + "/tax/getTaxById/" + id + ".htm", function(response) {
			var totResponse = $.parseJSON(response);
			$("#taxMode").val(totResponse.taxMode);
			$('#taxName').val(totResponse.taxName);
			$('#taxPer').val(totResponse.percentage);
			$('#taxDesc').val(totResponse.description);
			$('#taxIsGrp').val(totResponse.isGroup);
			if(totResponse.isGroup==1)
			{
				$("#taxIsGrp").attr("disabled", true);
				getTaxDetById(id);
				$("#headertext").text(getTaxText.headerTextUpdate);
				$('#taxAddEditModal').modal('show');
				
			}
			else
			{
				$("#taxIsGrp").attr("disabled", false);
				$("#headertext").text(getTaxText.headerTextUpdate);
				$('#taxAddEditModal').modal('show');
			}
		}, null);
	}
}

function getTaxDetById(id)
{
	var groupedTaxArr = [];
	var commonResultMap = {};
	commonResultMap.taxId=id;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/tax/getTaxDetbyId.htm", commonResultMap, function(response1) {
		var groupedtaxlist = JSON.parse(response1);
		for ( var i = 0; i < groupedtaxlist.length; i++) {
			var groupedtax = groupedtaxlist[i];
			groupedTaxArr[i]=groupedtax.taxId;
		}
	});
	
	setTimeout(function()
	{
		$("#snglTaxDiv").removeClass("hide");
		var commonResultMapForSingle = {};
		commonResultMapForSingle.isGroup = 1;
		var singletax=null;
		var singletaxlist=null;
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/tax/getSingleTaxs.htm", commonResultMapForSingle, function(response) {
			singletaxlist = JSON.parse(response);
			for ( var i = 0; i < singletaxlist.length; i++) {
				singletax = singletaxlist[i];
				var chkbox = "";
				chkbox = "<td><input id='" + singletax.taxId + "_modretcheck' class='chkboxcheked' type='checkbox' value='" + JSON.stringify(singletax) + "' ></td>";
				var count = 0;
				var starttrline = "<tr id=" + singletax.taxId + " >";
					for ( var j = 0; j < groupedTaxArr.length; j++) {
						var groupedtaxid = groupedTaxArr[j];
						if(groupedtaxid==singletax.taxId)
						{
							chkbox = "<td><input id='" + singletax.taxId + "_modretcheck' class='chkboxcheked' checked='checked' type='checkbox' value='" + JSON.stringify(singletax) + "' ></td>";
						}
						else
						{
							
						}
						
					}
				var taxname = "<td id='sngl_tax_name'>" + singletax.taxName + "</td>";
				var taxprcnt = "<td id='sngl_tax_prcnt'>" + singletax.percentage + "</td>";
				var taxdesc = "<td id='sngl_tax_desc' class='hide'>" + singletax.description + "</td>";
				var taxmode = "<td id='sngl_tax_mode' class='hide'>" + singletax.taxMode + "</td>";
				var endtrline = "</tr>";
				createdrowline = starttrline + chkbox + taxname + taxprcnt + taxdesc + taxmode + endtrline;
				$("#singleTaxList").append(createdrowline);
			}
		}); 
	}, 300);
}

function makeGroupStat()
{
	var grpStat = $("#taxIsGrp").val();
	if(grpStat==1)
	{
		var taxId = $("#taxId").val();
		if (taxId != 0) {
			getTaxDetById(taxId);
		}
		else
		{
			$("#snglTaxDiv").removeClass("hide");
			var commonResultMap = {};
			commonResultMap.isGroup = 1;
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/tax/getSingleTaxs.htm", commonResultMap, function(response) {
				//console.log(response);
				var singletaxlist = JSON.parse(response);
				for ( var i = 0; i < singletaxlist.length; i++) {
					var singletax = singletaxlist[i];
					var starttrline = "<tr id=" + singletax.taxId + " >";
					var chkbox = "<td><input id='" + singletax.taxId + "_modretcheck' class='chkboxcheked' type='checkbox' value='" + JSON.stringify(singletax) + "' ></td>";
					var taxname = "<td id='sngl_tax_name'>" + singletax.taxName + "</td>";
					var taxprcnt = "<td id='sngl_tax_prcnt'>" + singletax.percentage + "</td>";
					var taxdesc = "<td id='sngl_tax_desc' class='hide'>" + singletax.description + "</td>";
					var taxmode = "<td id='sngl_tax_mode' class='hide'>" + singletax.taxMode + "</td>";
					var endtrline = "</tr>";
					createdrowline = starttrline + chkbox + taxname + taxprcnt + taxdesc + taxmode + endtrline;
					$("#singleTaxList").append(createdrowline);
				}
			});
		}
	}
	else
	{
		$("#singleTaxList").empty();
		$("#snglTaxDiv").addClass("hide");
	}
}

function addEditTax()
{
	document.getElementById('alertMsg').innerHTML = '';
	var taxId = $("#taxId").val();
	
	var name_label = $("#taxName_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));
	
	var prct_label = $("#taxPer_label").text();
	var prcnt_field = prct_label.substring(0, name_label.lastIndexOf(" "));
	
	var desc_label = $("#taxDesc_label").text();
	var desc_field = desc_label.substring(0, name_label.lastIndexOf(" "));
	
	var field_names = [["taxName",name_field],["taxDesc",desc_field]];
	
	if(fieldValidation(field_names)>0)
		{
			
		}
		else {		
		if($("#taxPer").val()=="")
		{
			document.getElementById('alertMsg').innerHTML = prcnt_field + " " + getFieldText.fieldReq;
		}
		else
		{
			var TaxMasterObj = {};
			TaxMasterObj.name = $("#taxName").val();
			TaxMasterObj.percentage=$("#taxPer").val();
			TaxMasterObj.description=$("#taxDesc").val();
			TaxMasterObj.taxMode=$("#taxMode").val();
			var grpStat = $("#taxIsGrp").val();
			if(grpStat==1)
			{
				var grpPrcnt = 0.0;
				var count = 0;
				TaxMasterObj.isGroup=1;
				var allTaxGrpdetails = [];
				$('#singleTaxListTbl > tbody > tr').each(function() {
					var taxid = this.id;
						if ($("#" + taxid + "_modretcheck").is(":checked")) {
							count = count + 1;
							var taxGrpDetails = {};
							taxGrpDetails.taxId=taxid;	
							grpPrcnt = Number(grpPrcnt) + Number($("#"+taxid).find("#sngl_tax_prcnt").text());
							taxGrpDetails.percentage=$("#"+taxid).find("#sngl_tax_prcnt").text();
							taxGrpDetails.description=$("#"+taxid).find("#sngl_tax_desc").text();
							taxGrpDetails.taxMode=$("#"+taxid).find("#sngl_tax_mode").text();
							allTaxGrpdetails.push(taxGrpDetails);
						}
				});
				TaxMasterObj.taxGrpDetailsMasters=allTaxGrpdetails;
				
				if(count<2)
				{
					document.getElementById('alertMsg').innerHTML = getTaxText.notSlctAnyTaxErr;
					return false;
				}
				else
				{
					document.getElementById('alertMsg').innerHTML = "";
				}
				
				if(Number($("#taxPer").val())!=Number(grpPrcnt))
				{
					document.getElementById('alertMsg').innerHTML = getTaxText.prcntGtGroupedPrcntErr;
					return false;
				}
				else
				{
					document.getElementById('alertMsg').innerHTML = "";
				}
			}
			else
			{
				TaxMasterObj.isGroup=0;
			}
			$('#taxAddEditModal').modal('hide');
			if (taxId == 0) { // add brand		 * 
			$('#pleasewaitModal').modal('show');
				var ajaxCallObject = new CustomBrowserXMLObject();
				ajaxCallObject.callAjaxPost(BASE_URL + "/tax/addtax.htm", TaxMasterObj, function(response) {
					$('#pleasewaitModal').modal('hide');
					var status = JSON.parse(response);
					chngeResultStat(status);
	
				});
			} else {// edit brand
				$('#pleasewaitModal').modal('show');
				TaxMasterObj.id = taxId;
	 
				var ajaxCallObject = new CustomBrowserXMLObject();
				ajaxCallObject.callAjaxPost(BASE_URL + "/tax/updatetax.htm", TaxMasterObj, function(response) {
					$('#pleasewaitModal').modal('hide');
					var status = JSON.parse(response);
					chngeResultStat(status);
	
				});
	
			}
		}
	}
}

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}

function showtaxDelModal(id)
{
	   document.getElementById('confirmId').value=id;
	   $('#confirmModal').modal('show');
} 

function DoConfirm() {
	$('#pleasewaitModal').modal('show');
	var id = document.getElementById('confirmId').value;
	var commonResultMap = {};
	commonResultMap.taxId=id;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/tax/deleteTax.htm", commonResultMap, function(response) {
		$('#pleasewaitModal').modal('hide');
		var status = JSON.parse(response);
		chngeResultStat(status);
	}, null);
}

function targetURL() {
	location.href = BASE_URL + '/tax/loadtax.htm';
}


function numcheck(e) {
    // Allow: backspace, delete, tab, escape, enter and .
    if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
        // Allow: Ctrl+A, Command+A
        (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) ||
        // Allow: home, end, left, right, down, up
        (e.keyCode >= 35 && e.keyCode <= 40)) {
        // let it happen, don't do anything
        return;
    }
    // Ensure that it is a number and stop the keypress
    if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
        e.preventDefault();
    }
}