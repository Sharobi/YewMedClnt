var mode="CIH";

$(document).ready(function(){

	getvendorledger(mode,0,0,1);
	$("input:text").focus(function() { $(this).select(); } );
	if($("#custPaymentId").val()!=0)
	{
		$('input:checkbox').prop('checked',true);
		var totseldueamt=0;
		$('#customerduepaymenttable > tbody > tr').each(function() {
			var invid = this.id;
			totseldueamt=Number(totseldueamt)+Number($("#"+invid).find("#custdueamt").text());
		});
		$("#totselpayamt").val(parseFloat(totseldueamt).toFixed(2));

	}
	else
	{
		$('input:checkbox').prop('checked',false);
	}
	var paymodeid = $("#paymenttype").val();
	if (paymodeid == 3) {
		$("#cardno").removeAttr('readonly');
		$("#checkno").attr('readonly','readonly');
		$("#bankname").attr('readonly','readonly');
	} else if (paymodeid == 4) {
		$("#cardno").attr('readonly','readonly');
		$("#checkno").removeAttr('readonly');
		$("#bankname").removeAttr('readonly');
	} else {
		$("#cardno").attr('readonly','readonly');
		$("#checkno").attr('readonly','readonly');
		$("#bankname").attr('readonly','readonly');
	}

	calculateTotalDueAmt();
});

function getSelPaymentMode(paymodeid) {

	if (paymodeid == 3) {
		$("#cardno").removeAttr('readonly');
		$("#checkno").attr('readonly','readonly');
		$("#bankname").attr('readonly','readonly');
		mode='CAB';
	} else if (paymodeid == 4) {
		$("#cardno").attr('readonly','readonly');
		$("#checkno").removeAttr('readonly');
		$("#bankname").removeAttr('readonly');
		mode='CAB';
	} else {
		$("#cardno").attr('readonly','readonly');
		$("#checkno").attr('readonly','readonly');
		$("#bankname").attr('readonly','readonly');
		mode='CIH';
	}
	getvendorledger(mode,0,0,1);

}

function getCustomerPendingPayment() {
	$("#custduepaymentbodylist").text("");
	$("#totselpayamt").val(parseFloat(0).toFixed(2));
	$("#totdueamt").val(parseFloat(0).toFixed(2));
	$("#paymenttype").val(1);
	$("#cardno").attr('readonly','readonly');
	$("#checkno").attr('readonly','readonly');
	$("#bankname").attr('readonly','readonly');
	$("#remamt").val(parseFloat(0).toFixed(2));
	$("#payamt").val(parseFloat(0).toFixed(2));
	$("#pendingamt").val(parseFloat(0).toFixed(2));
	var commonResultMap = {};
	commonResultMap.custId = $("#custId").val();
	commonResultMap.paymentDate = $("#paydt").val();
	//console.log(JSON.stringify(commonResultMap));
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/customer/searchcustomerpendingpayment.htm", commonResultMap, function(response) {
		//console.log(response);
		var customerduepaymentlist = JSON.parse(response);
		for ( var i = 0; i < customerduepaymentlist.length; i++) {
			var customerduepayment = customerduepaymentlist[i];
			$("#outstandingamt").text(parseFloat(customerduepayment.outstandingAmount).toFixed(2));
			var starttrline = "<tr id=" + customerduepayment.invFromType + " >";
			var chkbox = "<td><input id='" + customerduepayment.invFromType + "_modretcheck' class='chkboxcheked' type='checkbox' onclick='getCheckedCustPayDet(" + JSON.stringify(customerduepayment) + ")' value='" + JSON.stringify(customerduepayment) + "' ></td>";
			var invno = "<td>" + customerduepayment.invNo + "</td>";
			var invdate = "<td>" + moment(customerduepayment.invDate).format('YYYY-MM-DD') + "</td>";
			var netAmount = "<td id='custnetamt'>" + parseFloat(customerduepayment.netAmount).toFixed(2) + "</td>";
			/*var advAmount = "<td id='venadjamt'>" + parseFloat(customerduepayment.advAmount).toFixed(2) + "</td>";*/
			var dueAmount = "<td id='custdueamt'>" + parseFloat(customerduepayment.dueAmount).toFixed(2) + "</td>";
			var searchStat = "<td id='searchStat' class='hide' value='1'></td>";
			/*var billno = "<td>" + customerduepayment.billNo + "</td>";*/
			var endtrline = "</tr>";
			createdrowline = starttrline + chkbox + invno + invdate + netAmount + dueAmount + searchStat + endtrline;
			$("#custduepaymentbodylist").append(createdrowline);
		}
		calculateTotalDueAmt();
	});
}

function getCheckedCustPayDetById(){
	var totseldueamt=0;
	$('#customerduepaymenttable > tbody > tr').each(function() {
		var invid = this.id;
			if ($("#" + invid + "_modretcheck").is(":checked")) {
				totseldueamt=Number(totseldueamt)+Number($("#"+invid).find("#custdueamt").text());
				/*if(vendorpaydet.invFrom=='PURRTN'){
					totseldueamt=totseldueamt-Number(vendorpaydet.dueAmount);
				}else{
					totseldueamt=totseldueamt+Number(vendorpaydet.dueAmount);
				}*/

			}

	});
//	if(totseldueamt<0){
//		$("#totselpayamt").val(parseFloat(0).toFixed(2));
//	}else{
		$("#totselpayamt").val(parseFloat(totseldueamt).toFixed(2));
//	}
	calculateTotalDueAmt();
}
function getCheckedCustPayDet(custpaydet){
	var totseldueamt=0;
	$('#customerduepaymenttable > tbody > tr').each(function() {
		var invid = this.id;
			if ($("#" + invid + "_modretcheck").is(":checked")) {
				var customerpaydetail = $("#" + invid + "_modretcheck").val();
				//console.log("vendorpaydetail="+vendorpaydetail);
				var customerpaydet = JSON.parse(customerpaydetail);
				totseldueamt=totseldueamt+Number(customerpaydet.dueAmount);
				/*if(vendorpaydet.invFrom=='PURRTN'){
					totseldueamt=totseldueamt-Number(vendorpaydet.dueAmount);
				}else{
					totseldueamt=totseldueamt+Number(vendorpaydet.dueAmount);
				}*/

			}

	});
//	alert(parseFloat(totseldueamt).toFixed(2));
//	if(totseldueamt<0){
//		$("#totselpayamt").val(parseFloat(0).toFixed(2));
//	}else{
		$("#totselpayamt").val(parseFloat(totseldueamt).toFixed(2));
//	}
	calculateTotalDueAmt();
}

$("#payamt").keyup(function() {
	if (isNaN($("#payamt").val())) {
		//document.getElementById('alertmessagecont').innerHTML = getFieldText.numberCheck + " in Card Four Digit.";
		$(this).focus();
		return false;
	} else {
		var payamt = $(this).val();
		var pendingamt=$("#totselpayamt").val()-payamt;
		var remaningamt=$("#totdueamt").val()-payamt;
		$("#pendingamt").val(parseFloat(pendingamt).toFixed(2));
		$("#remamt").val(parseFloat(remaningamt).toFixed(2));
		//document.getElementById('alertmessagecont').innerHTML = "";

		/*
		 *  add on 3_2_2018
		 */
				$('#cr_amount').val(parseFloat(payamt));
				$('#dr_amount').val(parseFloat(payamt));
	}



});

function calculateTotalDueAmt() {
	var totalDueAmt = 0.00;
	$('#customerduepaymenttable tbody tr').each(function() {
		var dueAmt = $(this).find("#custdueamt").html();
		totalDueAmt = totalDueAmt + Number(dueAmt);
	});
	var outstandingamt=$("#outstandingamt").text();
	$("#totdueamt").val(parseFloat(totalDueAmt+Number(outstandingamt)).toFixed(2));
}

function postCustomerPayment(paymentId){
	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.paymentId = paymentId;

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/customer/postcustomerpayment.htm", CommonRelsetmapperObj, function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == '1') {
			$("#confirmval").val($("#custId").val());
			$("#cnfrmCustName").val($("#custName").val());
			document.getElementById('confirmmessagecont').innerHTML = getCustomerText.dataSucPost;
			showConfirmModal();
		} else {
			$("#confirmval").val(-1);
			document.getElementById('confirmmessagecont').innerHTML = getCustomerText.dataNotPost;
			showConfirmModal();
		}

	});
}
$(document).bind("keyup keydown", function(e){
    if(e.ctrlKey && e.which == 83){
    	saveCustomerPayment(1);
        return false;
    }
});

function saveCustomerPayment(type){
	$('#alertMsg').html("");
	var paymenttype = $("#paymenttype").val();
	var totselpayamt=$("#totselpayamt").val();
	var payamt=$("#payamt").val();
	var totdueamt=$("#totdueamt").val();
	var outstandingamnt = $("#outstandingamt").text();
//	if(totselpayamt>0 && payamt>0){
		if(payamt>0){
		var customerPayment = {};
		var allCustomerPaymentdetails = [];
		var count = 0;
		$('#customerduepaymenttable > tbody > tr').each(function() {
			var invid = this.id;
				if ($("#" + invid + "_modretcheck").is(":checked")) {
					count = 1;
					var customerPaymentdetails = {};
					var customerpaydetail = $("#" + invid + "_modretcheck").val();
					if($("#searchStat").val()==1)
					{
						var customerpaydet = JSON.parse(customerpaydetail);
						customerPaymentdetails.invFrom=customerpaydet.invFrom;
					}
					else
					{
						customerPaymentdetails.invFrom=customerpaydetail;
					}
					customerPaymentdetails.invId=invid.split("_")[0];
					allCustomerPaymentdetails.push(customerPaymentdetails);
				}
		});
		customerPayment.customerPaymentDetails=allCustomerPaymentdetails;
		customerPayment.id=$("#custPaymentId").val();
		customerPayment.customerId=$("#custId").val();
		customerPayment.invDate=$("#paydt").val();
		customerPayment.invMode=$("#paymenttype").val();
		customerPayment.customerId=$("#custId").val();
		customerPayment.payableAmount=$("#totselpayamt").val();
		customerPayment.payAmount=$("#payamt").val();
		customerPayment.netAmount=Number($("#outstandingamt").text())+Number($("#totselpayamt").val());
		customerPayment.remainingAmount=(Number($("#outstandingamt").text())+Number($("#totselpayamt").val()))-$("#payamt").val();
		customerPayment.remarks=$("#remarks").val();
		if(paymenttype=="3")
		{
			if($("#cardno").val()=="")
			{

				//document.getElementById('alertMsg').innerHTML = "Please Enter Card No.";
				$("#confirmval").val(-1);
				document.getElementById('confirmmessagecont').innerHTML = getCustomerText.blankCardNo;
				showConfirmModal();
				return false;
			}
			else
			{
				if(Number($("#cardno").val().length)<4)
				{
					$("#confirmval").val(-1);
					document.getElementById('confirmmessagecont').innerHTML = getCustomerText.cardNoLsFour;
					showConfirmModal();
					return false;
				}
				else
				{
					customerPayment.cardNo=$("#cardno").val();
				}
			}
		}
		if(paymenttype=="4")
		{
			if(($("#checkno").val()=="") || ($("#checkno").val()==0))
			{
				//document.getElementById('alertMsg').innerHTML = "Please Enter Valid Cheque No.";
				$("#confirmval").val(-1);
				document.getElementById('confirmmessagecont').innerHTML = getCustomerText.blankChequeNo;
				showConfirmModal();
				return false;
			}
			if(isNaN($("#checkno").val()))
			{
				//document.getElementById('alertMsg').innerHTML = "Please Enter Valid Cheque No.";
				$("#confirmval").val(-1);
				document.getElementById('confirmmessagecont').innerHTML = getCustomerText.intChequeNo;
				showConfirmModal();
				return false;
			}
			else
			{
				document.getElementById('alertMsg').innerHTML = "";
				customerPayment.chequeNo=$("#checkno").val();
				customerPayment.chequeDate = $("#paydt").val();

				if(($("#bankname").val()!="") || ($("#bankname").val()!=0))
				{
					customerPayment.bankName = $("#bankname").val();
				}
				else{}

			}
		}
		//alert(JSON.stringify(customerPayment));
		var ajaxCallObject = new CustomBrowserXMLObject();


		var cr_amount=$("#cr_amount").val();
		var dr_amount=$("#dr_amount").val();
		var cr_account_id=$("#cr_legder_id").val();
		var dr_account_id=$("#dr_legder_id").val();


		customerPayment.cr_amount=cr_amount;
		customerPayment.dr_amount=dr_amount;
		customerPayment.cr_account_id=cr_account_id;
		customerPayment.dr_account_id=dr_account_id;
		var error=0;

		 if (cr_amount==0) {
			error=1;

			$('#alertMsg').html("please enter credit amount");
		}

		if (dr_amount==0) {
			error=1;
			$('#alertMsg').html("please enter debit amount");
		}
		if (cr_account_id==0) {
			error=1;
			$('#alertMsg').html("please enter valid Cr/by ledger account");
		}
		if (dr_account_id==0) {
			error=1;
			$('#alertMsg').html("please enter valid Dr/by ledger account");
		}

		if (cr_amount!=dr_amount)
			{
				$('#alertMsg').html("please enter debit amount equal to credit amount ");
				error=1;
			}

		if(count == 0)
		{
			if(Number(payamt)>Number(outstandingamnt))
			{
				$("#confirmval").val(-1);
				document.getElementById('confirmmessagecont').innerHTML = getCustomerText.payGrtOutstndamnt;
				showConfirmModal();
				return false;
			}
			else
			{

				if(type==1) // 1 for only save or update
				{

					if (error==0) {
						$('#pleasewaitModal').modal('show');
						ajaxCallObject.callAjaxPost(BASE_URL + "/customer/createorupdatecustomerpayment.htm", customerPayment, function(response) {
							$('#pleasewaitModal').modal('hide');
							if (response == '0') {
								$("#confirmval").val(-1);
								document.getElementById('confirmmessagecont').innerHTML = getCustomerText.paymentNotDone;
								showConfirmModal();
							} else {
								//sessionStorage.setItem("seldistributorId", $("#seldistributor").val());
								$("#confirmval").val($("#custId").val());
								$("#cnfrmCustName").val($("#custName").val());
								$('#confirmvalfrsaveupdate').val(response);
								 document.getElementById('customerReceiptconfirmmess').innerHTML = getCustomerText.paymentDone;
								$('#confirmPrintcustomerecipetModal').modal('show');

							}


						});
					}


				}
				else // 2 for save or update and post
				{
					if (error==0) {
						$('#pleasewaitModal').modal('show');
						ajaxCallObject.callAjaxPost(BASE_URL + "/customer/createorupdatwithpostcustomerpayment.htm", customerPayment, function(response) {
							$('#pleasewaitModal').modal('hide');
							if (response == '1') {
								$("#confirmval").val($("#custId").val());
								$("#cnfrmCustName").val($("#custName").val());
								document.getElementById('confirmmessagecont').innerHTML = getCustomerText.dataSucPost;
								showConfirmModal();
							} else {
								$("#confirmval").val(-1);
								document.getElementById('confirmmessagecont').innerHTML = getCustomerText.dataNotPost;
								showConfirmModal();
							}


						});
					}


				}
			}
		}
		else
		{

			if(type==1) // 1 for only save or update
			{





				if (error==0) {
					$('#pleasewaitModal').modal('show');
					ajaxCallObject.callAjaxPost(BASE_URL + "/customer/createorupdatecustomerpayment.htm", customerPayment, function(response) {
						$('#pleasewaitModal').modal('hide');
						if (response == '0') {
							$("#confirmval").val(-1);
							document.getElementById('confirmmessagecont').innerHTML = getCustomerText.paymentNotDone;
							showConfirmModal();
						} else {
							//sessionStorage.setItem("seldistributorId", $("#seldistributor").val());
							$("#confirmval").val($("#custId").val());
							$("#cnfrmCustName").val($("#custName").val());
							//document.getElementById('confirmmessagecont').innerHTML = getCustomerText.paymentDone;
							//showConfirmModal();

							$('#confirmvalfrsaveupdate').val(response);
							 document.getElementById('customerReceiptconfirmmess').innerHTML = getCustomerText.paymentDone;
							$('#confirmPrintcustomerecipetModal').modal('show');

						}


					});
				}



			}
			else // 2 for save or update and post
			{

				if (error==0) {
					$('#pleasewaitModal').modal('show');
					ajaxCallObject.callAjaxPost(BASE_URL + "/customer/createorupdatwithpostcustomerpayment.htm", customerPayment, function(response) {
						$('#pleasewaitModal').modal('hide');
						if (response == '1') {
							$("#confirmval").val($("#custId").val());
							$("#cnfrmCustName").val($("#custName").val());
							document.getElementById('confirmmessagecont').innerHTML = getCustomerText.dataSucPost;
							showConfirmModal();
						} else {
							$("#confirmval").val(-1);
							document.getElementById('confirmmessagecont').innerHTML = getCustomerText.dataNotPost;
							showConfirmModal();
						}


					});
				}

			}
		}

	}else{
		$("#totpayamtnegModal").modal("show");
	}

}

function deleteCustPaymentInv(custpayinvid){
	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.paymentId = custpayinvid;

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/customer/deletecustpayinv.htm", CommonRelsetmapperObj, function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == '1') {
			$("#confirmval").val($("#custId").val());
			$("#cnfrmCustName").val($("#custName").val());
			document.getElementById('confirmmessagecont').innerHTML = getCustomerText.paymentSucDelete;
			showConfirmModal();
		} else {
			$("#confirmval").val(-1);
			document.getElementById('confirmmessagecont').innerHTML = getCustomerText.paymentNotDelete;
			showConfirmModal();
		}

	});
}

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}

function targetURL(){
	var custId=$("#confirmval").val();
	var custName = $("#cnfrmCustName").val();
	if($("#confirmval").val()==-1)
	{
		location.href = '#';
	}
	else
	{
//		location.href = BASE_URL + '/customer/loadcustomerpaydet/'+custId+'/'+custName+'.htm';
		location.href = BASE_URL + '/customer/loadcustomer.htm';
	}
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



function getvendorledger(group_code,acc_id,ref_id,para)
{
	 var keyword=ref_id.toString();
	  var trackname=keyword.split("_");

	var commonobj={};
	if (para==0) { // for creditor

		commonobj.groupCode=group_code;
		commonobj.accountID=acc_id;
		commonobj.referenceID=trackname[0];
	}


	if (para==1) { // for debitor

		commonobj.groupCode=group_code;
		commonobj.accountID=0;
		commonobj.referenceID=0;
	}



	$('#pleasewaitModal').modal('show');

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/vendor/searchledgerusinggroup.htm", commonobj, function(response) {
		$('#pleasewaitModal').modal('hide');

		var status = JSON.parse(response);

		if (para==0) {

			$.each(status, function(i) {

				 $('#cr_ledger').val(status[i].name);
				 $('#cr_legder_id').val(status[i].id);
				//$("#cr_ledger").attr("disabled",true);
			});
		}

		if (para==1) {

			$.each(status, function(i) {

				 $('#dr_ledger').val(status[i].name);
				 $('#dr_legder_id').val(status[i].id);

			});
		}


		//chngeResultStat(status);
	});

}

/*
 * add on 2_3_2018
 */
function isEmpty(val) {
    return (val === undefined || val == null || val.length <= 0) ? true : false;
}
function searchledger(id) {
	var cr_id="#cr_ledger";
	var dr_id="#dr_ledger";
	var ids="";
	$('#alertMsg').html("");
	if (id==1) {// for dr
		ids=dr_id;

	}
	if (id==2) {// for cr

		ids=cr_id;

	}

	   if (isEmpty($(ids).val())) {

	        $(ids).val('');
	    }

	   var type=3;




    $(ids).autocomplete({
        source: function(request,
            response) {


            if (request.term.length >= 2) {
        	//	$('#pleasewaitModal').modal('show');
                $.ajax({
                    url: BASE_URL + "/pos/searchledgerbytype.htm",
                    type: "POST",
                    data: {
                        tagName: request.term,
                        type:type
                    },
                    dataType: "json",
                    success: function(data) {
                        /*	console.log("res_len"+data.length);*/
                   // 	$('#pleasewaitModal').modal('hide');
                        response($.map(data, function(v) {



                            return {
                                label: v.name,
                                itemCode: v.id,
                                //code : v.code
                                items: v
                            };

                        }));
                    },
                    error: function(error) {

                        $('#alertMsg').html('error: ' + error);
                    }
                });
            }
        },
        select: function(e,
            ui) {
            //		console.log(ui.item.itemCode);
            //		console.log(ui.item.label);


           $('#dr_ledger').val(ui.item.label);
		    $('#dr_legder_id').val(ui.item.itemCode);




        },
        change: function(e, ui) {
            if (!(ui.item))
                e.target.value = "";
        },
    });
}

function targetActionPur(){
	 var result=$("#confirmvalfrsaveupdate").val();
 	console.log("vendorpayment statu="+result);
	if(result==0){
		location.href = "#";
	}

	else {

		location.href = BASE_URL + '/customer/loadcustomer.htm';
	}
}

function printCustomerPayment()
{
	if ($('input[name=customer_receipt]').is(":checked"))
	{

		location.href= BASE_URL + '/customer/printpaymentreceipt/'+$("#confirmvalfrsaveupdate").val()+'.htm';

		//location.href = BASE_URL + '/customer/loadcustomer.htm';

	}
	else
	{
		location.href = BASE_URL + '/customer/loadcustomer.htm';
	}
}


