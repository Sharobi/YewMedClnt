var BASE_URL = "${pageContext.request.contextPath}";
	var storeId = $("#storeIdrsp").val();
	var cmpnyId = $("#companyIdrsp").val();
	var createdBy = $("#useridrsp").val();
	var finyrId = $("#finyrIdrsp").val();
	function showConfirmModal() {
		$('#confirmMessageModal_rsp').modal('show');
	}
$( document ).ready(function() {
//	alert("dr="+$("#confirmvalrsp").val());


	
	
	
		var itemcount = 0;
		$('#salretitem tbody tr').each(function() {
			itemcount++;
		});
		$("#totitmcount_rsp").text(itemcount);
		/*$('#saleinvtable').DataTable({
			"lengthChange" : false,
		 "searching": false,
		"pageLength": 12 
		});*/
		var currentDate = new Date();
		var startDateFrom = new Date((currentDate.getFullYear() - 1), 3, 1);
		$('#stdate').datepicker({
			format : 'yyyy-mm-dd',
			startDate : startDateFrom,
			autoclose : true,
		});
		$('#enddate').datepicker({
			format : 'yyyy-mm-dd',
			endDate : currentDate,
			autoclose : true,
		});
		//  		$('.dataTables_filter input').attr("placeholder", getCashMemoText.dataTablePlaceHolder);
	
	$("#item_name_rsp").autocomplete({
		source : function(	request,
							response) {
			if (request.term.length >= 2) {
				$.ajax({
					url : BASE_URL + "/item/autocompleteitem.htm",
					type : "GET",
					data : {
						tagName : request.term
					},
					dataType : "json",
					success : function(data) {
						response($.map(data, function(v) {
							return {
								label : v.itemName,
								itemCode : v.itemId,
								//tagCode : v.tagCode
								items : v,
							};

						}));
					},
					error : function(error) {
						alert('error: ' + error);
					}
				});
			}
		},
		select : function(	e,
							ui) {
			console.log(ui.item.itemCode);
			console.log(ui.item.label);
			console.log(ui.item.items);
			/*$("#itemid").val(ui.item.itemCode);
			$("#modmanufname").text(ui.item.items.manufacturerName);
			$("#modcontentname").text(ui.item.items.contentName);
			$("#modrackname").text(ui.item.items.schdName);
			$("#modgroupname").text(ui.item.items.groupName);
			*/
			$("#itemsaledetailitemname_rsp").html(ui.item.label);
			// call new  ajax for item details
			getItemDetailsrsp(ui.item.itemCode);
			// call new ajax end

		},
		change : function(	e,
							ui) {
			if (!(ui.item))
				e.target.value = "";
		},
	});
	
	$('#item_barcode_rsp').on('keydown', function(e) {
		if (e.which == 13) {
			e.preventDefault();
			var barcode = $('#item_barcode_rsp').val();
			var CommResultsetObj = {};
			CommResultsetObj.sku = barcode;
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/item/getitembybarcode.htm", CommResultsetObj, function(response) {
				var itemdetobj = JSON.parse(response);
				if (itemdetobj.itemId==0) {
					$("#inputbarcode_rsp").text(barcode);
					$("#noItemBarcodeModal_rsp").modal("show");
				} else {
				getItemDetailsrsp(itemdetobj.itemId);
				}
			});
		}
	});
	
});
function getRetSaleDetbyInvId(invno,retmemoFinyr) {
	$("#searchmodtbody_rsp").html("");
	if (invno == '' || invno == 0) {
      
	} else {
		$('#pleasewaitModal').modal('show');
		var CommonRelsetmapperObj = {};
		CommonRelsetmapperObj.invoiceNo =$("#retmemoDoc").val()+retmemoFinyr+$("#retmemoSlash").val()+ invno;
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/retunmemo/getretrsalealldetbyinvno.htm", CommonRelsetmapperObj, function(response) {
			console.log("res=" + response);
			$('#pleasewaitModal').modal('hide');
			var saledetails = JSON.parse(response);
			if (saledetails.length == 0) {
				//$("#searchmodinvno_rsp").html(invno);
				$("#saledetnotfounddiv_rsp").removeClass("hide");
				$("#saledetmodtable_rsp").addClass("hide");
				$("#saledetfounddiv_rsp").addClass("hide");
				$("#nocashmemofoundokbut_rsp").hide();
			} else {
				$("#saledetnotfounddiv_rsp").addClass("hide");
				$("#saledetmodtable_rsp").removeClass("hide");
				$("#saledetfounddiv_rsp").removeClass("hide");
				$("#nocashmemofoundokbut_rsp").show();
				for ( var i = 0; i < saledetails.length; i++) {
					var saledetail = saledetails[i];
					$("#searchmodinvno_rsp").html(saledetail.saleInvNo);
					$("#searchmodinvdate_rsp").html(moment(saledetail.saleInvDate).format('YYYY-MM-DD'));
					$("#searchmodtotamt_rsp").html(saledetail.netAmount);
					$("#searchmodcustspcldisc_rsp").html(parseFloat(saledetail.specialDiscPer).toFixed(2));
					$("#searchmodcustcont_rsp").html(saledetail.customerPhone);
					$("#searchmodcustname_rsp").html(saledetail.customerName);
					$("#searchmoddocname_rsp").html(saledetail.doctorName);
					$("#spcldiscperc_rsp").val(0);
					var strng_itemKey='"'+saledetail.itemUniqueKey+'"';
					var starttrline = "<tr id=" + saledetail.itemUniqueKey + " style='cursor: pointer;'>";
					var chkbox = "<td><input id='" + saledetail.itemUniqueKey + "_modretcheck' class='chkboxcheked' type='checkbox' onchange='disablePLqtyrsp("+strng_itemKey+");' value='" + JSON.stringify(saledetail) + "' ></td>";
					var itmname = "<td>" + saledetail.itemName + "</td>";
					var batch = "<td>" + saledetail.batchNo + "</td>";
					var exp = "<td>" + saledetail.expiryDateFormat + "</td>";
					var packQty = "<td>" + saledetail.packQty + "</td>";
					var looseQty = "<td>" + saledetail.looseQty + "</td>";
					var prevReturnPackQty = "<td>" + saledetail.prevReturnPackQty + "</td>";
					var prevReturnLooseQty = "<td>" + saledetail.prevReturnLooseQty + "</td>";
					var inputPackQty = "<td><input type='text' value='0' readonly='readonly' id='" + saledetail.itemUniqueKey + "_irpqty' size='3' onkeyup='calPQtyrsp(" + saledetail.conversion + "," + saledetail.ratePerUnit + ",this.value,&quot;" + saledetail.itemUniqueKey + "&quot;," + saledetail.packQty + "," + saledetail.looseQty + ","+saledetail.taxPercentage+","+saledetail.discPer+")'/><input type='hidden' value='" + saledetail.taxPercentage + "' id='" + saledetail.itemUniqueKey + "_irtaxpercentage'/></td>";
					var inputLooseQty = "<td><input type='text' value='0' readonly='readonly' id='" + saledetail.itemUniqueKey + "_irlqty' size='3' onkeyup='calLqtyrsp(" + saledetail.conversion + "," + saledetail.ratePerUnit + ",this.value,&quot;" + saledetail.itemUniqueKey + "&quot;," + saledetail.packQty + "," + saledetail.looseQty + ","+saledetail.taxPercentage+","+saledetail.discPer+")'/></td>";
					var mrp = "<td>" + parseFloat(saledetail.mrpPerUnit).toFixed(2) + "</td>";//
					var rate = "<td>" + parseFloat(saledetail.ratePerUnit).toFixed(2) + "</td>";
					var retamt = "<td id='" + saledetail.itemUniqueKey + "_retamt'>0.00</td>";
					var endtrline = "</tr>";
					createdrowline = starttrline + chkbox + itmname + batch + exp + packQty + looseQty + prevReturnPackQty + prevReturnLooseQty + inputPackQty + inputLooseQty + mrp + rate + retamt + endtrline;
					$("#searchmodtbody_rsp").append(createdrowline);
				}
			}
		});
		$('#saledetailModal_rsp').modal('show');
	}

}
function calPQtyrsp(	conv,
					rate,
					pqty,
					itemid,packqty,lsqty,taxperc,disc) {
	var lqty = $("#" + itemid + "_irlqty").val();
	var totselqty=((packqty * conv) + Number(lsqty));
	var totqty=((pqty * conv) + Number(lqty));
	//	console.log("lqty="+lqty);
	if(totqty>totselqty){
		$("#" + itemid + "_irpqty").val(0);
		$("#" + itemid + "_irlqty").val(0);
		//	console.log("retamt="+retamt);
		$("#" + itemid + "_retamt").text(parseFloat(0).toFixed(2));
	}else{
	var retamt = ((pqty * conv) + Number(lqty)) * rate;
		console.log("retamt="+retamt);
	retamt=Number(retamt)-((Number(retamt)*Number(disc))/100);
	console.log("disc retamt="+retamt);
	//$("#" + itemid + "_retamt").text(parseFloat(retamt).toFixed(2));
	var gstamt = parseFloat(retamt * taxperc / 100).toFixed(4);
	console.log(gstamt);
	$("#" + itemid + "_irtaxpercentage").val(parseFloat(gstamt).toFixed(4));
	$("#" + itemid + "_retamt").text(parseFloat(Number(retamt)).toFixed(2));
	var isexclu = $("#isexclusiversp").val();
	if (isexclu == 1) {
		$("#" + itemid + "_retamt").text(parseFloat(Number(retamt) + Number(gstamt)).toFixed(2));
	}
	}
}
function calLqtyrsp(	conv,
					rate,
					lqty,
					itemid,packqty,lsqty,taxperc,disc) {
	var pqty = $("#" + itemid + "_irpqty").val();
	var totselqty=((packqty * conv) + Number(lsqty));
	var totqty=((pqty * conv) + Number(lqty));
	if(totqty>totselqty){
		$("#" + itemid + "_irpqty").val(0);
		$("#" + itemid + "_irlqty").val(0);
		//	console.log("retamt="+retamt);
		$("#" + itemid + "_retamt").text(parseFloat(0).toFixed(2));
	}else{
		var retamt = ((pqty * conv) + Number(lqty)) * rate;
			console.log("retamt="+retamt);
		retamt=Number(retamt)-((Number(retamt)*Number(disc))/100);
		console.log("disc retamt="+retamt);
		//$("#" + itemid + "_retamt").text(parseFloat(retamt).toFixed(2));
		var gstamt = parseFloat(retamt * taxperc / 100).toFixed(4);
		console.log(gstamt);
		$("#" + itemid + "_irtaxpercentage").val(parseFloat(gstamt).toFixed(4));
		$("#" + itemid + "_retamt").text(parseFloat(Number(retamt)).toFixed(2));
		var isexclu = $("#isexclusiversp").val();
		if (isexclu == 1) {
			$("#" + itemid + "_retamt").text(parseFloat(Number(retamt) + Number(gstamt)).toFixed(2));
		}
	}
	

}

function disablePLqtyrsp(itemKey)
{
	if ($("#" + itemKey + "_modretcheck").is(":checked")) {
		$("#" + itemKey + "_irpqty").attr("readonly",false);
		$("#" + itemKey + "_irlqty").attr("readonly",false);
	}
	else
	{
		$("#" + itemKey + "_irpqty").attr("readonly",true);
		$("#" + itemKey + "_irlqty").attr("readonly",true);
	}
}

function getmodretcheckeditemlistrsp() {
	var len=$('.chkboxcheked:checked').length;
	var count = 0;
	$('#searchmodtable_rsp > tbody > tr').each(function() {
		var itemid = this.id;
		console.log("len="+len);
		if(len==0){
			$("#alertmessagecont_rsp").text("Please check at least one item.");
		}else{
			$("#alertmessagecont_rsp").text("");
			if ($("#" + itemid + "_modretcheck").is(":checked")) {
				var pqty = $(this).find("#" + itemid + "_irpqty").val();
				var lqty = $(this).find("#" + itemid + "_irlqty").val();
				if((pqty == "") && (lqty == ""))
				{
					count = count+1;
				}
				if ((pqty == 0) && (lqty == 0))
				{
					count = count+1;					
				}
				else
				{						
					
				}
				if(count==0)
				{
					$("#alertmessagecont_rsp").text("");
					var saledetail = $("#" + itemid + "_modretcheck").val();
					var itemdetail = JSON.parse(saledetail);
			 
					insertModDatatoRetTable(itemdetail);
				}
				else
				{					
					
				}
			}
		}
	});
	if(count>0)
	{
		$("#alertmessagecont_rsp").text("Ret P.Qty/Ret L.Qty "+getFieldText.fieldReq);
		$('#saledetailModal_rsp').modal('show');
		$("#salerettabitemdetails").empty();
		
		$("#saleretcustid").val("");
		$("#saleretcustph").val("");
		$("#saleretcustname").val("");
		$("#saleretdocname").val("");
		$("#saleretdocid").val("");
	}
	else
	{					
		$('#saledetailModal_rsp').modal('hide');
	}
	/*if(len!=0){
		$('#saledetailModal_rsp').modal('hide');
	}*/
	calculateTotalVatrsp();
	calculateTotalTaxrsp();
	calculateNetTotalrsp();
	calculateTotalGSTrsp();
	calculateSpclDiscrsp();
}

function insertModDatatoRetTable(itemdetail) {
	var saleretcustid = $("#saleretcustid").val();
		if (saleretcustid==0 || saleretcustid == itemdetail.customerId) {
			console.log("same user");
			var uniquechk=0;
//			var newunikey =itemdetail.itemId+itemdetail.saleInvNo;
			var newunikey =itemdetail.itemUniqueKey+itemdetail.saleInvNo;
			console.log("newunikey="+newunikey);
			$('#salretitem tbody tr').each(function() {
				var itemid=this.id;
				var saleinvno = $(this).find("#salerettabsaleinvno").html();
				var preunikey = itemid+saleinvno;
				console.log("preunikey="+preunikey);
				if(newunikey==preunikey){
					uniquechk=1;
				}
			});
			console.log("uniquechk="+uniquechk);
			if(uniquechk==1){
				$("#sameItemInvModal_rsp").modal("show");
			}else{
				var calretamt = $("#" + itemdetail.itemUniqueKey + "_retamt").text();
				var lqty = $("#" + itemdetail.itemUniqueKey + "_irlqty").val();
				var pqty = $("#" + itemdetail.itemUniqueKey + "_irpqty").val();
				var calctaxperc = $("#" + itemdetail.itemUniqueKey + "_irtaxpercentage").val();
				$("#saleretcustid").val(itemdetail.customerId);
				$("#saleretcustph").val(itemdetail.customerPhone);
				$("#saleretcustname").val(itemdetail.customerName);
				$("#saleretdocname").val(itemdetail.doctorName);
				$("#saleretdocid").val(itemdetail.doctorId);
				var starttrline = "<tr id=" + itemdetail.itemUniqueKey + " style='cursor: pointer;' onclick='javascript:itemHeaderDivViewrsp(this.id)'>";
				var itmname = "<td id='salerettabname'>" + itemdetail.itemName + "</td>";
				var batch = "<td id='salerettabbat'>" + itemdetail.batchNo + "</td>";
				var exp = "<td id='salerettabexpdt'>" + itemdetail.expiryDateFormat + "</td>";
				var mfg = "<td id='salerettabmanname'>" + itemdetail.manufacturerName + "</td>";
				var packQty = "<td id='salerettabpqty'>" + pqty + "</td>";
				var looseQty = "<td id='salerettablqty'>" + lqty + "</td>";
				var conv = "<td id='salerettabconv'>" + itemdetail.conversion + "</td>";
				var mrpperpack = "<td id='salerettabmrppack'>" + parseFloat(itemdetail.mrp).toFixed(4) + "</td>";
				var mrp = "<td id='salerettabmrp'>" + parseFloat(itemdetail.mrpPerUnit).toFixed(4) + "</td>";
				var rateperls = "<td id='salerettabrateperunit'>" + parseFloat(itemdetail.ratePerUnit).toFixed(4) + "</td>";
				var disc = "<td id='salerettabdiscperc'>" + parseFloat(itemdetail.discPer).toFixed(4) + "</td>";
				var retamt = "<td id='salerettabtotamt'>" + parseFloat(calretamt).toFixed(4) + "</td>";
				var rowdelete = "<td><button class='btn btn-theme04 btn-xs' id='" + itemdetail.itemUniqueKey + "' onclick='javascript:showSelRetItemDelModal(this.id);'><i class='fa fa-trash-o '></i></button></td>";
				var salepty = "<td id='salepqty_rsp' class='hide'>" + itemdetail.packQty + "</td>";
				var salelqty_rsp = "<td id='salelqty_rsp' class='hide'>" + itemdetail.looseQty + "</td>";
				var punitid = "<td id='salerettabpunitid' class='hide'>" + itemdetail.packUnitId + "</td>";
				var salerettablunitid = "<td id='salerettablunitid' class='hide'>" + itemdetail.looseUnitId + "</td>";
				var salerettabcontent = "<td id='salerettabcontent' class='hide'>" + itemdetail.contentName + "</td>";
				var salerettabrate = "<td id='salerettabrate' class='hide'>" + parseFloat(itemdetail.rate).toFixed(4) + "</td>";
				var salerettabdisamt = "<td id='salerettabdisamt' class='hide'>" + itemdetail.disc + "</td>";
				var salerettabed = "<td id='salerettabed' class='hide'>" + itemdetail.edPer + "</td>";
				var salerettabedamt = "<td id='salerettabedamt' class='hide'>" + itemdetail.ed + "</td>";
				var taxPer = "<td id='salerettabtax' class='hide'>" + itemdetail.taxPer + "</td>";
				var salerettabtaxamt = "<td id='salerettabtaxamt' class='hide'>" + itemdetail.tax + "</td>";
				var vatPer = "<td id='salerettabvat' class='hide'>" + itemdetail.vatPer + "</td>";
				var salerettabvatamt = "<td id='salerettabvatamt' class='hide'>" + itemdetail.vat + "</td>";
				var salerettabsaleid = "<td id='salerettabsaleid' class='hide'>" + itemdetail.saleId + "</td>";
				var salerettabsaleinvno = "<td id='salerettabsaleinvno' class='hide'>" + itemdetail.saleInvNo + "</td>";
				var packQtyhide = "<td id='salerettabpqtyhide' class='hide'>" + itemdetail.hidePackQty + "</td>";
				var looseQtyhide = "<td id='salerettablqtyhide' class='hide'>" + itemdetail.hideLooseQty + "</td>";
				var salerettabsku = "<td id='salerettabsku' class='hide'>" + itemdetail.sku + "</td>";
				var salerettabtaxId = "<td id='salerettabtaxId' class='hide'>" + itemdetail.taxId + "</td>";
				var salerettabtaxName = "<td id='salerettabtaxName' class='hide'>" + itemdetail.taxName + "</td>";
				var salerettabtaxPercentage = "<td id='salerettabtaxPercentage' class='hide'>" + itemdetail.taxPercentage + "</td>";
				var salerettabitemTaxAmount = "<td id='salerettabitemTaxAmount' class='hide'>" + parseFloat(calctaxperc).toFixed(4) + "</td>";
				var salerettabisGroupTax = "<td id='salerettabisGroupTax' class='hide'>" + itemdetail.isGroupTax + "</td>";
				var salerettabtaxMode = "<td id='salerettabtaxMode' class='hide'>" + itemdetail.taxMode + "</td>";
				var salerettabhsnCode = "<td id='salerettabhsnCode' class='hide'>" + itemdetail.hsnCode + "</td>";
				var endtrline = "</tr>";
				createdrowline = starttrline + itmname + batch + exp + mfg + packQty + looseQty + conv + mrpperpack + mrp + rateperls + disc + retamt + rowdelete + salepty + salelqty_rsp + punitid + salerettablunitid + salerettabcontent + salerettabrate + salerettabdisamt + salerettabed + salerettabedamt + taxPer + salerettabtaxamt + vatPer + salerettabvatamt + salerettabsaleid + salerettabsaleinvno+packQtyhide+looseQtyhide+salerettabsku+salerettabtaxId+salerettabtaxName+salerettabtaxPercentage+salerettabitemTaxAmount+salerettabisGroupTax+salerettabtaxMode+salerettabhsnCode + endtrline;
				$("#salerettabitemdetails").append(createdrowline);
				
				/*
				 * add 7_3_2018 
				 */
				
		 
				getvendorledger($('#dueties_and_tax_acc').val(),0,0,0);// for duties and tax
				getvendorledger($('#roundoff_group_code').val(),0,0,1);// for round off  
				getvendorledger($('#saleaccunt_group_code').val(),0,0,2);// for sale account
				if (itemdetail.customerId==0) {
				 
					getvendorledger($('#cash_in_hand_group_code').val(),0,0,3);// for cash ledger credit
					
				}else {
					 
							getvendorledger($('#debitor_group_code').val(),0,itemdetail.customerId,3);// for sunndry debitor credit
				}
		
				
			}
			
		} else {
			$("#sameUserModal_rsp").modal("show");
		}
		
}

function itemHeaderDivViewrsp(itemid) {
	$("#item_id_rsp").val(itemid);
	$('#item_name_rsp').prop('readonly', true);
	$('#item_barcode_rsp').prop('readonly', true);
	$("#item_name_rsp").val($("#salretitem tr#" + itemid).find('#salerettabname').text());
	$("#item_barcode_rsp").val($("#salretitem tr#" + itemid).find('#salerettabsku').text());
	$("#item_rpqty_rsp").val($("#salretitem tr#" + itemid).find('#salerettabpqty').text());
	$("#item_rlqty_rsp").val($("#salretitem tr#" + itemid).find('#salerettablqty').text());
	$("#item_disper_rsp").val($("#salretitem tr#" + itemid).find('#salerettabdiscperc').text());
	$("#item_bat_rsp").val($("#salretitem tr#" + itemid).find('#salerettabbat').text());
	$("#item_exp_rsp").val($("#salretitem tr#" + itemid).find('#salerettabexpdt').text());
	$("#item_mrpperpack_rsp").val($("#salretitem tr#" + itemid).find('#salerettabmrppack').text());
	$("#item_mrp_rsp").val($("#salretitem tr#" + itemid).find('#salerettabmrp').text());
	$("#item_rate_rsp").val($("#salretitem tr#" + itemid).find('#salerettabrate').text());
	$("#item_rateperloose_rsp").val($("#salretitem tr#" + itemid).find('#salerettabrateperunit').text());
	$("#item_netamt_rsp").val($("#salretitem tr#" + itemid).find('#salerettabtotamt').text());
	$("#item_mfg_rsp").val($("#salretitem tr#" + itemid).find('#salerettabmanname').text());
	$("#item_content_rsp").val($("#salretitem tr#" + itemid).find('#salerettabcontent').text());
	$("#item_conv_rsp").val($("#salretitem tr#" + itemid).find('#salerettabconv').text());
	$("#item_pqty_rsp").val($("#salretitem tr#" + itemid).find('#salepqty_rsp').text());
	$("#item_lqty_rsp").val($("#salretitem tr#" + itemid).find('#salelqty_rsp').text());
	$("#item_rpqty_hide_rsp").val($("#salretitem tr#" + itemid).find('#salerettabpqtyhide').text());
	$("#item_rlqty_hide_rsp").val($("#salretitem tr#" + itemid).find('#salerettablqtyhide').text());
	
	$("#item_taxId_rsp").val($("#salretitem tr#" + itemid).find('#salerettabtaxId').text());
	$("#item_taxPercentage_rsp").val($("#salretitem tr#" + itemid).find('#salerettabtaxPercentage').text());
	$("#item_isGroupTax_rsp").val($("#salretitem tr#" + itemid).find('#salerettabisGroupTax').text());
//	$("#item_discount_rsp").val($("#salretitem tr#" + itemid).find('#saletabdiscount').text());
//	$("#item_maxDiscountLimit_rsp").val($("#salretitem tr#" + itemid).find('#saletabmaxDiscountLimit').text());
	$("#item_CalcTaxAmt_rsp").val($("#salretitem tr#" + itemid).find('#salerettabitemTaxAmount').text());
	$("#item_taxMode_rsp").val($("#salretitem tr#" + itemid).find('#salerettabtaxMode').text());
	$("#item_hsnCode_rsp").val($("#salretitem tr#" + itemid).find('#salerettabhsnCode').text());
	$("#add_btn_rsp").addClass("hide");
	$("#edit_btn_rsp").removeClass("hide");
}
function clearHeaderDivrsp() {
	$("#header_div_rsp").find('input:text').val('');
	$("#header_div_rsp").find('input:hidden').val('');
	$('#item_name_rsp').prop('readonly', false);
	$("#add_btn_rsp").removeClass("hide");
	$("#edit_btn_rsp").addClass("hide");
	$("#item_id_rsp").val("0");
	$("#item_name_rsp").focus();
	document.getElementById('alertMsg_rsp').innerHTML = "";
	//	$("#item_dis").val(parseFloat($("#salediscount").val()).toFixed(4));
	//	document.getElementById('alertMsg_rsp').innerHTML = "";
};

function calculateTotalVatrsp() {
	var grandtotalVat = 0.00;
	$('#salretitem tbody tr').each(function() {
		var itmvat = $(this).find("#salerettabvatamt").html();
		
		grandtotalVat = grandtotalVat + Number(itmvat);
	});
	$("#totvatamt_rsp").val(parseFloat(grandtotalVat).toFixed(2));
}

function calculateTotalTaxrsp() {
	var grandtotalTax = 0.00;
	$('#salretitem tbody tr').each(function() {
		var itmtax = $(this).find("#salerettabtaxamt").html();
		
		grandtotalTax = grandtotalTax + Number(itmtax);
	});
//	$("#tottaxamt_rsp").val(parseFloat(grandtotalTax).toFixed(2));
}

function calculateTotalGSTrsp() {
	var grandtotalGST = 0.00;
	$('#salretitem tbody tr').each(function() {
		var itmtax = $(this).find("#salerettabitemTaxAmount").html();
		
		grandtotalGST = grandtotalGST + Number(itmtax);
	});
	$("#tottaxamt_rsp").val(parseFloat(grandtotalGST).toFixed(4));
}


function calculateNetTotalrsp() {
	var itemcount = 0;
	var grandNetTotal = 0.00;
	var grandtotalGST = 0.00;
	$('#salretitem tbody tr').each(function() {
		var itmmrp = $(this).find("#salerettabtotamt").html();
		grandNetTotal = grandNetTotal + Number(itmmrp);
		itemcount++;
		
		var itmtax = $(this).find("#salerettabitemTaxAmount").html();
		
		grandtotalGST = grandtotalGST + Number(itmtax);
		
	});
	$("#totitmcount_rsp").text(itemcount);
	$("#totgrossamt_rsp").val(parseFloat(grandNetTotal).toFixed(2));
	var roundednetamnt = Math.round(Number(grandNetTotal));
	$("#totretamt_rsp").val(parseFloat(roundednetamnt).toFixed(2));
	 $("#retamtsamepage").val(parseFloat(roundednetamnt).toFixed(2));
	var roundoff_rsp = Number(roundednetamnt) - Number(grandNetTotal);
	console.log("roundoff_rsp="+roundoff_rsp);
	$("#roundoff_rsp").val(parseFloat(roundoff_rsp).toFixed(2));
	
	 
	
	var sale_acc=(parseFloat(roundednetamnt)-parseFloat(roundoff_rsp))- parseFloat(grandtotalGST);
	
	
	$('#sale_account_rsp').val(sale_acc.toFixed(2));
	$('#debitor_amt_rsp').val(roundednetamnt.toFixed(2));
}

function calculateSpclDiscrsp() {
	var discper=$("#spcldiscperc_rsp").val();
	var grandNetTotal = 0.00;
	$('#salretitem tbody tr').each(function() {
		var itmmrp = $(this).find("#salerettabtotamt").html();
		grandNetTotal = grandNetTotal + Number(itmmrp);
	});
	var discamt=grandNetTotal*discper/100;
	var newnettot=grandNetTotal-discamt;
	var roundednetamnt = Math.round(Number(newnettot));
	$("#totgrossamt_rsp").val(parseFloat(grandNetTotal).toFixed(2));
	$("#spcldisc_rsp").val(parseFloat(discamt).toFixed(2));
//	var roundednetamnt = Math.round(Number(grandNetTotal));
	$("#totretamt_rsp").val(parseFloat(roundednetamnt).toFixed(2));
	$('#debitor_amt_rsp').val(roundednetamnt.toFixed(2));
	
	 $("#retamtsamepage").val(parseFloat(roundednetamnt).toFixed(2));
	var roundoff_rsp = Number(roundednetamnt) - Number(newnettot);
	console.log("roundoff_rsp="+roundoff_rsp);
	$("#roundoff_rsp").val(parseFloat(roundoff_rsp).toFixed(2));
}

function showSelRetItemDelModal(trId) {
	$("#confirmId_rsp").val(trId);
	$('#confirmModal_rsp').modal('show');
}
function DoConfirmrsp() {
	var itmid = $("#confirmId_rsp").val();
	$('#salerettabitemdetails tr#' + itmid).remove();
	clearHeaderDivrsp();
	calculateTotalGSTrsp();
	calculateNetTotalrsp();
}
function Validationrsp() {
	var counter = 0;

	var pqty_field = $("#pqty_label").text();

	var lqty_field = $("#lqty_label").text();

	if (($("#item_rpqty_rsp").val() == "") || ($("#item_rlqty_rsp").val() == "")) {
		document.getElementById('alertMsg_rsp').innerHTML = pqty_field + " / " + lqty_field + " " + getFieldText.fieldempty;
		counter = 1;
		return counter;
		return false;
	} else {
		counter = 0;
		document.getElementById('alertMsg_rsp').innerHTML = "";
	}

	if (($("#item_rpqty_rsp").val() == 0) && ($("#item_rlqty_rsp").val() == 0)) {
		document.getElementById('alertMsg_rsp').innerHTML = pqty_field + " / " + lqty_field + " " + getFieldText.fieldReq;
		counter = 1;
		return counter;
		return false;
	} else {
		counter = 0;
		document.getElementById('alertMsg_rsp').innerHTML = "";
	}

	if (isNaN($("#item_rpqty_rsp").val())) {
		document.getElementById('alertMsg_rsp').innerHTML = getFieldText.numberCheck + " in P.Qty";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	} else {
		if($("#item_rpqty_rsp").val()<0)
		{
			document.getElementById('alertMsg_rsp').innerHTML = pqty_field+" "+getFieldText.checkNegative;
			$(this).focus();
			counter = 1;
			return counter;
			return false;
		}
		else
		{
			counter = 0;
			document.getElementById('alertMsg_rsp').innerHTML = "";
		}		
	}
	
	if (isNaN($("#item_rlqty_rsp").val())) {
		document.getElementById('alertMsg_rsp').innerHTML = getFieldText.numberCheck + " in L.Qty";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	} else {
		if($("#item_rlqty_rsp").val()<0)
		{
			document.getElementById('alertMsg_rsp').innerHTML = lqty_field+" "+getFieldText.checkNegative;
			$(this).focus();
			counter = 1;
			return counter;
			return false;
		}
		else
		{
			counter = 0;
			document.getElementById('alertMsg_rsp').innerHTML = "";
		}		
	}

	return counter;
}

function addOrUpdateItemToDetailsTablersp(operation) {

		if (Validationrsp() == 1) {
			return false;
		} else {
			document.getElementById('alertMsg_rsp').innerHTML = "";
		}

	console.log("call");
	//$("#saletabitemdetails").text("");
	var itemid = $("#item_id_rsp").val();

	if (Number(itemid) == 0) {
		return false;
	}
	console.log("itemid=" + itemid);
	var retamt = (($('#item_rpqty_rsp').val() * $('#item_conv_rsp').val()) + Number($('#item_rlqty_rsp').val())) * $('#item_rateperloose_rsp').val();
	var item_disper_rsp=$("#item_disper_rsp").val();
	var item_taxPercentage_rsp=$("#item_taxPercentage_rsp").val();
	retamt=Number(retamt)-((Number(retamt)*Number(item_disper_rsp))/100);
	console.log("disc retamt="+retamt);
	//$("#" + itemid + "_retamt").text(parseFloat(retamt).toFixed(2));
	console.log("item_taxPercentage_rsp="+item_taxPercentage_rsp);
	var gstamt = parseFloat(retamt * item_taxPercentage_rsp / 100).toFixed(4);
	console.log(gstamt);
	var isexclu = $("#isexclusiversp").val();
	if (isexclu == 1) {
		retamt=(parseFloat(Number(retamt) + Number(gstamt)).toFixed(4));
	}
	
	var itempresent = 0;
	if (operation == 1) { //edit
		$("#item_id_rsp").val(itemid);
		$("#salretitem tr#" + itemid).find('#salerettabpqty').text($("#item_rpqty_rsp").val());
		$("#salretitem tr#" + itemid).find('#salerettablqty').text($("#item_rlqty_rsp").val());
		$("#salretitem tr#" + itemid).find('#salerettabitemTaxAmount').text(parseFloat(gstamt).toFixed(4));
		$("#salretitem tr#" + itemid).find('#salerettabtotamt').text(parseFloat(retamt).toFixed(4));
	} else { // add

		/*$('#selitem > tbody  > tr').each(function() {
			console.log("tbl_itemid=" + this.id);
			console.log("itemid=" + itemid);
			if (Number(this.id) == Number(itemid)) {
				itempresent = 1;
			}
		});
		}
		console.log("itempresent=" + itempresent);
		if (itempresent == 1) {
		$('#itemExistsModal').modal('show');
		} else {
		if ($("#item_sche").val() == 'H1' || $("#item_sche").val() == 'X') {
			$('#scheleXorH1Modal').modal('show');
			$('#operationtype').val(operation);
		} else {
			addItemtotable(operation);
		}

		}*/
	}
	clearHeaderDivrsp();
	calculateNetTotalrsp();
	calculateTotalVatrsp();
	calculateTotalTaxrsp();
	calculateTotalGSTrsp();
	calculateSpclDiscrsp();
}
//item_looseqty change calculation
$("#item_rlqty_rsp").keyup(function() {
	var inputlqty = $(this).val();
	var inputpqty = $("#item_rpqty_rsp").val();
	var lqty = $("#item_rlqty_hide_rsp").val();
	var pqty = $("#item_rpqty_hide_rsp").val();
	var conv = $("#item_conv_rsp").val();
	var item_taxPercentage_rsp = $("#item_taxPercentage_rsp").val();
	var item_dis = $("#item_disper_rsp").val();
	var totlqty = (pqty * conv) + Number(lqty);
	var inputtotlqty = (inputpqty * conv) + Number(inputlqty);
	var billpqty = $("#item_pqty_rsp").val();
	var billlsqty = $("#item_lqty_rsp").val();
	var billtotlqty = (billpqty * conv) + Number(billlsqty);
	var totremqty=Number(billtotlqty)+Number(totlqty);
	console.log("inputtotlqty="+inputtotlqty);
	console.log("totremqty="+totremqty);
	/*if (totlqty > billtotlqty) {
		$("#currStkGraterModal_rsp").modal("show");
	}*/
	if (inputtotlqty > totremqty) {
		$("#currStkGraterModal_rsp").modal("show");
	}
	var item_rate_ls = $('#item_rateperloose_rsp').val();
	//var total = parseFloat(inputtotlqty * item_rate_ls).toFixed(4);
	var retamt = inputtotlqty * item_rate_ls;
	var discamt = retamt * item_dis / 100;
	console.log("discamt="+discamt);
	$("#item_discamt").val(parseFloat(discamt).toFixed(4));
	retamt = retamt - discamt;
	console.log("after disc retamt="+retamt);
	$("#item_netamt_rsp").val(parseFloat(retamt).toFixed(2));
	var gstamt = parseFloat(retamt * item_taxPercentage_rsp / 100).toFixed(4);
	console.log("gstamt="+gstamt);
	$("#item_CalcTaxAmt_rsp").val(parseFloat(gstamt).toFixed(4));
	var isexclu = $("#isexclusiversp").val();
	if (isexclu == 1) {
		$("#item_netamt_rsp").val(parseFloat(Number(retamt) + Number(gstamt)).toFixed(4));
	}

});

//item_packqty change calculation
$("#item_rpqty_rsp").keyup(function() {
	var inputpqty = $(this).val();
	var inputlqty = $("#item_rlqty_rsp").val();
	var lqty = $("#item_rlqty_hide_rsp").val();
	var pqty = $("#item_rpqty_hide_rsp").val();
	var conv = $("#item_conv_rsp").val();
	var item_taxPercentage_rsp = $("#item_taxPercentage_rsp").val();
	var item_dis = $("#item_disper_rsp").val();
	var totlqty = (pqty * conv) + Number(lqty);
	var inputtotlqty = (inputpqty * conv) + Number(inputlqty);
	var billpqty = $("#item_pqty_rsp").val();
	var billlsqty = $("#item_lqty_rsp").val();
	var billtotlqty = (billpqty * conv) + Number(billlsqty);
	console.log("billtotlqty="+billtotlqty);
	var totremqty=Number(billtotlqty)+Number(totlqty);
	console.log("totremqty="+totremqty);
	/*if (totlqty > billtotlqty) {
		$("#currStkGraterModal_rsp").modal("show");
	}*/
	if (inputtotlqty > totremqty) {
		$("#currStkGraterModal_rsp").modal("show");
	}
//	var retamt = totlqty * $('#item_rateperloose_rsp').val();
	var item_rate_ls = $('#item_rateperloose_rsp').val();
	//var total = parseFloat(inputtotlqty * item_rate_ls).toFixed(4);
	var retamt = inputtotlqty * item_rate_ls;
	console.log("retamt="+retamt);
	console.log("item_dis="+item_dis);
	var discamt = retamt * item_dis / 100;
	console.log("discamt="+discamt);
	$("#item_discamt").val(parseFloat(discamt).toFixed(4));
	retamt = retamt - discamt;
	console.log("after disc retamt="+retamt);
	$("#item_netamt_rsp").val(parseFloat(retamt).toFixed(2));
	var gstamt = parseFloat(retamt * item_taxPercentage_rsp / 100).toFixed(4);
	console.log(gstamt);
	$("#item_CalcTaxAmt_rsp").val(parseFloat(gstamt).toFixed(4));
	var isexclu = $("#isexclusiversp").val();
	if (isexclu == 1) {
		$("#item_netamt_rsp").val(parseFloat(Number(retamt) + Number(gstamt)).toFixed(4));
	}
});

var pqty_field = $("#pqty_label").text();

var lqty_field = $("#lqty_label").text();

$("#item_rpqty_rsp").keyup(function() {
	if (isNaN($("#item_rpqty_rsp").val())) {
		document.getElementById('alertMsg_rsp').innerHTML = getFieldText.numberCheck + " in "+pqty_field;
		$(this).focus();
		return false;
	} else {
		if($("#item_rpqty_rsp").val()<0)
		{
			document.getElementById('alertMsg_rsp').innerHTML = pqty_field+" "+getFieldText.checkNegative;
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg_rsp').innerHTML = "";
		}		
	}
});

$("#item_rlqty_rsp").keyup(function() {
	if (isNaN($("#item_rlqty_rsp").val())) {
		document.getElementById('alertMsg_rsp').innerHTML = getFieldText.numberCheck + " in "+lqty_field;
		$(this).focus();
		return false;
	} else {
		if($("#item_rlqty_rsp").val()<0)
		{
			document.getElementById('alertMsg_rsp').innerHTML = lqty_field+" "+getFieldText.checkNegative;
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg_rsp').innerHTML = "";
		}		
	}
});

function closeCurrStkModalrsp() {
	$("#item_rpqty_rsp").val(0);
	$("#item_rlqty_rsp").val(0);
	$("#item_netamt_rsp").val(parseFloat(0).toFixed(2));
}



function getItemDetailsrsp(itemid) {
	$("#itemsearchmodtbody_rsp").text("");
	$('#pleasewaitModal').modal('show');
	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.itemId = itemid;

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/retunmemo/getretrsalealldetbyitem.htm", CommonRelsetmapperObj, function(response) {
		console.log("res=" + response);
		$('#pleasewaitModal').modal('hide');
		var saledetails = JSON.parse(response);
		if (saledetails.length == 0) {
			$("#itemsaledetnotfounddiv_rsp").removeClass("hide");
			$("#itemsaledetmodtable_rsp").addClass("hide");
		} else {
			$("#itemsaledetnotfounddiv_rsp").addClass("hide");
			$("#itemsaledetmodtable_rsp").removeClass("hide");
			var isexclu = $("#isexclusiversp").val();
			for ( var i = 0; i < saledetails.length; i++) {
				var saledetail = saledetails[i];
				$("#itemsaledetailitemname_rsp").html(saledetail.itemName);
				var starttrline = "<tr id=" + saledetail.itemUniqueKey + " style='cursor: pointer;' onclick='insertModDatarsp(" + JSON.stringify(saledetail) + ")'>";
				var saleInvNo = "<td>" + saledetail.saleInvNo + "</td>";
				var saleInvDate = "<td>" + moment(saledetail.saleInvDate).format('YYYY-MM-DD') + "</td>";
				var customerPhone = "<td>" + saledetail.customerPhone + "</td>";
				var customerName = "<td>" + saledetail.customerName + "</td>";
				var doctorName = "<td>" + saledetail.doctorName + "</td>";
				var batch = "<td>" + saledetail.batchNo + "</td>";
				var exp = "<td>" + saledetail.expiryDateFormat + "</td>";
				var packQty = "<td>" + saledetail.packQty + "</td>";
				var looseQty = "<td>" + saledetail.looseQty + "</td>";
				var inputPackQty = "<td class='hide'><input type='hidden' value='" + saledetail.packQty + "' id='" + saledetail.itemUniqueKey + "_irpqty'/><input type='hidden' value='" + saledetail.itemTaxAmount + "' id='" + saledetail.itemUniqueKey + "_irtaxpercentage'/></td>";
				var inputLooseQty = "<td class='hide'><input type='hidden' value='" + saledetail.looseQty + "' id='" + saledetail.itemUniqueKey + "_irlqty'/></td>";
				var mrp = "<td>" + parseFloat(saledetail.mrp).toFixed(2) + "</td>";//
				var rate = "<td>" + parseFloat(saledetail.rate).toFixed(2) + "</td>";
				var calretamtrsp=0;
				var calprelseqty=(saledetail.prevReturnPackQty * saledetail.conversion)+saledetail.prevReturnLooseQty+saledetail.calculateLooseQty;
				if (isexclu == 1) {
					
					calretamtrsp=parseFloat((saledetail.calculateLooseQty * saledetail.ratePerUnit)+Number(saledetail.itemTaxAmount/calprelseqty)-Number(saledetail.disc)).toFixed(2);
				}else{
					calretamtrsp=parseFloat((saledetail.calculateLooseQty * saledetail.ratePerUnit)-Number(saledetail.disc)).toFixed(2);
				}
				var retamt = "<td id='" + saledetail.itemUniqueKey + "_retamt'>" + parseFloat(calretamtrsp).toFixed(2) + "</td>";
//				var retamt = "<td id='" + saledetail.itemUniqueKey + "_retamt'>" + parseFloat(0).toFixed(2) + "</td>";
				var specialDiscPer = "<td style='background-color: yellow;'>" + parseFloat(saledetail.specialDiscPer).toFixed(2) + "</td>";
				var endtrline = "</tr>";
				createdrowline = starttrline + saleInvNo + saleInvDate + customerPhone + customerName + doctorName + batch + exp + packQty + looseQty + inputPackQty + inputLooseQty + mrp + rate + retamt+specialDiscPer + endtrline;
				$("#itemsearchmodtbody_rsp").append(createdrowline);
			}
		}
	});
	$('#itemsaledetailModal_rsp').modal('show');
}
function insertModDatarsp(saledetail){
	
 
	insertModDatatoRetTable(saledetail);
	$('#itemsaledetailModal_rsp').modal('hide');
	calculateTotalVatrsp();
	calculateTotalTaxrsp();
	calculateTotalGSTrsp();
	calculateNetTotalrsp();
	calculateSpclDiscrsp();
	clearHeaderDivrsp();
}

function saveOrUpdateSaleReturnInv() {

	if ($("#item_id_rsp").val() == 0 || $("#item_id_rsp").val() == "") {
		var saleretid = $("#saleretId").val();
		var salereturn = {};
		var allsaleretdetails = [];
		var selitemlength = $('#salretitem >tbody >tr').length;
		$('#salretitem > tbody  > tr').each(function() {
			var saleretdetails = {};
			var itemid = this.id;
			//var totlooseqty = (parseFloat($(this).find("#saletabpqty").text()) * parseFloat($(this).find("#saletabconv").text())) + parseFloat($(this).find("#saletablqty").text());
			saleretdetails.itemId = itemid.split("_")[0];
			saleretdetails.batchNo = $(this).find("#salerettabbat").text();
			saleretdetails.expiryDateFormat = $(this).find("#salerettabexpdt").text();
			saleretdetails.packUnitId = $(this).find("#salerettabpunitid").text();
			saleretdetails.packQty = $(this).find("#salerettabpqty").text();
			saleretdetails.looseQty = $(this).find("#salerettablqty").text();
			saleretdetails.looseUnitId = $(this).find("#salerettablunitid").text();
			saleretdetails.conversion = $(this).find("#salerettabconv").text();
			saleretdetails.mrp = $(this).find("#salerettabmrppack").text();
			saleretdetails.mrpPerUnit = $(this).find("#salerettabmrp").text();// parseFloat(parseFloat($(this).find("#saletabmrppack").text())/ parseFloat($(this).find("#saletabconv").text())).toFixed(2);
			saleretdetails.ratePerUnit = $(this).find("#salerettabrateperunit").text();
			saleretdetails.amount = (Number(saleretdetails.packQty) * Number(saleretdetails.conversion) + Number(saleretdetails.looseQty)) * Number(saleretdetails.ratePerUnit);
			saleretdetails.rate = $(this).find("#salerettabrate").text();
			saleretdetails.vat = $(this).find("#salerettabvatamt").text();
			saleretdetails.vatPer = $(this).find("#salerettabvat").text();
			saleretdetails.disc = $(this).find("#salerettabdisamt").text();
			saleretdetails.discPer = $(this).find("#salerettabdiscperc").text();
			saleretdetails.totAmount = $(this).find("#salerettabtotamt").text();
			saleretdetails.saleId = $(this).find("#salerettabsaleid").text();
			saleretdetails.saleInvNo = $(this).find("#salerettabsaleinvno").text();
//			saleretdetails.sku = $(this).find("#salerettabsku").text();
			saleretdetails.taxId = $(this).find("#salerettabtaxId").text();
			saleretdetails.taxPercentage = $(this).find("#salerettabtaxPercentage").text();
			saleretdetails.taxAmount = $(this).find("#salerettabitemTaxAmount").text();
			saleretdetails.isGroupTax = $(this).find("#salerettabisGroupTax").text();
			saleretdetails.taxMode = $(this).find("#salerettabtaxMode").text();
			saleretdetails.reasonId = 0;
			saleretdetails.storeId = storeId;
			saleretdetails.finyrId = finyrId;
			saleretdetails.companyId = cmpnyId;
			saleretdetails.createdBy = createdBy;
			allsaleretdetails.push(saleretdetails);
		});
		salereturn.saleReturnDetails = allsaleretdetails;
		salereturn.id = saleretid;
		if (saleretid == 0) {// create

		} else {// edit
			salereturn.invNo = $("#saleretinvno").val();
		}

		salereturn.invDate = $("#datersp").val();
		if ($("#saleretcustname").val() == "") {
			salereturn.customerId = 0;
		} else {
			salereturn.customerId = $("#saleretcustid").val();
			salereturn.customerName = $("#saleretcustname").val();
			if ($("#saleretcustph").val() == "") {
				salereturn.customerPhone = 0000000000;
			} else {
				salereturn.customerPhone = $("#saleretcustph").val();
			}

		}

		if ($("#saleretdocname").val() == "") {
			salereturn.doctorId = 0;
		} else {
			salereturn.doctorId = $("#saleretdocid").val();
			salereturn.doctorName = $("#saleretdocname").val();
		}
		salereturn.grossAmount = $("#totgrossamt_rsp").val();
		salereturn.vatAmount =$("#totvatamt_rsp").val();
		salereturn.taxAmount =$("#tottaxamt_rsp").val();
		salereturn.discAmount =0;// $("#totdiscamt").val();
		salereturn.roundoff = $("#roundoff_rsp").val();
		salereturn.netAmount = $("#totretamt_rsp").val();
		salereturn.remarks = $("#remarks_rsp").val();
		if($("#spcldiscperc_rsp").val()==""|| $("#spcldiscperc_rsp").val()==null){
			salereturn.specialDiscPer = 0;
		}else{
			salereturn.specialDiscPer = $("#spcldiscperc_rsp").val();
		}
		
		
		salereturn.specialDiscAmount = $("#spcldisc_rsp").val();
		/*salereturn.creditAmount = $("#creditAmt").val();
		sale.tenderAmount = $("#tenderamt").val();
		sale.cashAmount = $("#cashAmt").val();
		sale.cardAmount = $("#cardAmt").val();
		sale.cardExpDate =$("#cardExpMod").val();
		sale.cardFourDigit =$("#cardFourDigitMod").val();*/
		salereturn.isposted = 0;
//		salereturn.invMode = "Credit";
		
		
		/*
		 * for account add on 7_3_2018
		 */
		
		salereturn.sale_account_credit_amt= $('#sale_account_rsp').val();
		salereturn.debitor_credit_amt= $('#debitor_amt_rsp').val();
		
		salereturn.duties_ledger_id= $('#duties_ledger_id').val();
		salereturn.round_ledger_id= $('#round_ledger_id').val();
		salereturn.sales_ledger_id= $('#sales_ledger_id').val();
		salereturn.debitor_ledger_id= $('#debitor_ledger_id').val();
		
		
		
		console.log("sale json: " + JSON.stringify(salereturn));
		if (selitemlength > 0) {
			$('#pleasewaitModal').modal('show');
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/retunmemo/createorupdatesalesreturn.htm", salereturn, function(response) {
				$('#pleasewaitModal').modal('hide');
				//alert("save saleret inv id=" + response);
				if (response == '0') {
					document.getElementById('confirmmessagecontrsp').innerHTML = getRetCashMemoText.dataNotAdd;
					$("#confirmvalrsp").val(0);
					showConfirmModal();
				} else {
					/*document.getElementById('confirmmessagecontrsp').innerHTML = getRetCashMemoText.dataSucAdd;
					$("#confirmvalrsp").val(response);
					showConfirmModal();*/
				//	alert("cv="+$("#confirmvalrsp").val());
					if($("#confirmvalrsp").val()==0||$("#confirmvalrsp").val()==""){
						$("#confirmvalrsp").val(response);
					}else{
						
						$("#confirmvalrsp").val($("#confirmvalrsp").val()+","+response);
				//		alert("cv111="+$("#confirmvalrsp").val());
					}
					$("#retamtsamepage").val(0);
					$("#confirmvalrspsamepage").val(Number($("#totretamt_rsp").val()));
					$('#confirmPrintSaleReturnModalrsp').modal('show');
				}

			});
		}
	} else {
		if($("#samepageret").val()==1){
		//	$("#confirmvalrsp").val(-1);
			document.getElementById('confirmmessagecontrspcheck').innerHTML = getRetCashMemoText.addEditChckBefrSave;
			$("#confirmMessageModal_rsp_check").modal("show");
		}else{
			$("#confirmvalrsp").val(-1);
			document.getElementById('confirmmessagecontrsp').innerHTML = getRetCashMemoText.addEditChckBefrSave;
			showConfirmModal();
		}
		
	}
}
function openRetMemo(){
	location.href = BASE_URL + '/retunmemo/loadreturnmemo.htm';
}
function targetURLrspcheck(){
	location.href = "#";
}
function targetURLrsp() {
	console.log("con val=" + $("#confirmvalrsp").val());
	if ($("#confirmvalrsp").val() == 0) {
		clearHeaderDivrsp();
		location.href = BASE_URL + '/retunmemo/loadreturnmemo.htm';
	} else if ($("#confirmvalrsp").val() == -1) {
		location.href = "#";
	} else {
		
		if ($('input[name=printSaleReturn]').is(":checked")) 
		{
			location.href = BASE_URL + "/retunmemo/printreturnmemo.htm?backUrl=loadreturnmemo&saleRetId=0954fg-hjk4565wer-"+$("#confirmvalrsp").val()+"-kjsa45-uyt6yu-9811fnczas";
		} 
		else 
		{
			location.href = BASE_URL + '/retunmemo/loadreturnmemo.htm?saleRetId=0954fg-hjk4565wer-' + $("#confirmvalrsp").val() + '-kjsa45-uyt6yu-9811fnczas';
		}
	}
}
function targetURLrspsamepage(){
	var cval=$("#confirmvalrsp").val();
	var cvalsp=$("#confirmvalrspsamepage").val();
	//alert("cval="+cval);
	//alert("$('#myTabs a:first')");
	//alert("1st val="+$("#retamtvalsamepage").val());
	//alert("cvalsp="+cvalsp);
	$("#retamtvalsamepage").val(parseFloat(Number($("#retamtvalsamepage").val())+Number(cvalsp)).toFixed(2));
	//alert("2nd val="+$("#retamtvalsamepage").val());
	$("#payretadjamt").val(Number($("#payretadjamt").val())+Number(cvalsp));
	clearAllRetMemo();
	$('#myTabs a:first').tab('show');
	//location.href = "#";
}

function deleteRetSalesInv() {
	var saleretId=$("#saleretId").val();
	if(saleretId!=0){
		$('#confirmModalPos_rsp').modal('show');
	}
}
function DoConfirmPosrsp() {
	$('#pleasewaitModal').modal('show');
	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.saleReturnId = $("#saleretId").val();

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/retunmemo/deleteretsalesinv.htm", CommonRelsetmapperObj, function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == '1') {
			document.getElementById('confirmmessagecontrsp').innerHTML = getRetCashMemoText.dataSucDelete;
			showConfirmModal();
		} else {
			document.getElementById('confirmmessagecontrsp').innerHTML = getRetCashMemoText.dataNotDelete;
			showConfirmModal();
		}

	});
}

function clearAllRetMemo(){
	clearHeaderDivrsp();
	$("#salerettabitemdetails").empty();
	$("#saleretId").val(0);
	$("#invnorsp").val("");
	$("#saleretcustph").val("");
	$("#saleretcustid").val(0);
	$("#saleretcustname").val("");
	$("#saleretdocname").val("");
	$("#saleretdocid").val(0);
	$('#footer_detail1').find('input:text').val('');    
}




function getvendorledger(group_code,acc_id,ref_id,para)
{
	 //var keyword=ref_id.toString();
	//  var trackname=keyword.split("_");
	/*
	 * 	commonobj.id=1; is call another procedure 
	 */
	
 
	var commonobj={};
	if (para==0) { // for duties and tax 
		
		commonobj.groupCode=group_code;
		commonobj.accountID=0;
		commonobj.referenceID=0;
		commonobj.id=1;

	 
	}
	
	
	if (para==1) { // for round off 
		
		commonobj.groupCode=group_code;
		commonobj.accountID=0;
		commonobj.referenceID=0;
		commonobj.id=1;
	}

	if (para==2) { // for sales 
		
		commonobj.groupCode=group_code;
		commonobj.accountID=0;
		commonobj.referenceID=0;
		commonobj.id=1;
	}
	
if (para==3) { // for debitor 
		
		if (ref_id==0) {
			commonobj.groupCode=group_code;
			commonobj.accountID=0;
			commonobj.referenceID=0;
			commonobj.id=1;
		}else {
			commonobj.groupCode=group_code;
			commonobj.accountID=0;
			commonobj.referenceID=ref_id;
			commonobj.id=1;
		}
		
	}
	
$('#pleasewaitModal').modal('show');
 
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/vendor/searchledgerusinggroup.htm", commonobj, function(response) {
		$('#pleasewaitModal').modal('hide');
	 
		var status = JSON.parse(response);
		
		if (para==0) {// for duties and tax 
			console.log("para0");
			
			$.each(status, function(i) {
				 
			//	 $('#duties_html').html(status[i].name+" (Debit) ");
				 $('#duties_ledger_id').val(status[i].id);
			 
			});
		}	
		
		if (para==1) {// for round off 
			console.log("para1");
			$.each(status, function(i) {
				 
				// $('#round_html').html(status[i].name);
				 $('#round_ledger_id').val(status[i].id);
				 
			});
		}
	if (para==2) { // for sales 
				
		console.log("para2");
				$.each(status, function(i) {
					 
				//	 $('#sales_html').html(status[i].name+" (Debit) ");
					 $('#sales_ledger_id').val(status[i].id);
					 
				});
			}
		
	if (para==3) {// for debitor 
		console.log("para3");
		$.each(status, function(i) {
			 
			// $('#debitor_html').html(status[i].name +" (Credit) ");
			 $('#debitor_ledger_id').val(status[i].id);
			 
		});
}

		
		
		//chngeResultStat(status);
	});
	
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