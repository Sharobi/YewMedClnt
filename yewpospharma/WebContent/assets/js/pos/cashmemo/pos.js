var remarks = $("#remarkstext").text();
var samepageret = 0;
var cusInCredit = 0;
var selectedItemData = {}; // new added 10.6.2019
$(document).ready(function() {
	var retpagesamesaleid = $("#retamtsamepagesaleid").val();
	
	$("#pcodeid").val($("#esiCodeId").val());
	$("#esitypeid").val($("#esiTypeId").val() == '' ? 0 : $("#esiTypeId").val());
	var etype = $("#esitypeid").val();

	if (etype == 1) {
		$("#codetextid").removeClass("hide");
		$("#pregtextid").removeClass("hide");
		$("#codevalid").removeClass("hide");
		$("#pregvalid").removeClass("hide");
	} else if (etype == 3) {
		$("#codetextid").addClass("hide");
		$("#pregtextid").addClass("hide");
		$("#codevalid").addClass("hide");
		$("#pregvalid").addClass("hide");
		$("#remarkstext").text("Place of Treatment");
		//		alert(etype);
		$("#remarks").autocomplete({
			source : function(	request,
								response) {
				$.ajax({
					url : BASE_URL + "/pos/getplaceoftreatmentautocomplete.htm",
					type : "GET",
					data : {
						tagName : request.term
					},

					dataType : "json",
					success : function(data) {
						response($.map(data, function(v) {
							return {
								label : v.remarks,
								items : v,
							};

						}));
					},
					error : function(error) {
						alert('error: ' + error);
					}
				});
			},
			position : {
				my : "left bottom",
				at : "left top",
			},
			select : function(	e,
								ui) {

				$("#remarks").val(ui.item.label);

				//		$("#prebilltd").removeClass("hide");
				//		$("#prebilltext").removeClass("hide");
			},

		});
	} else {
		$("#codetextid").addClass("hide");
		$("#pregtextid").addClass("hide");
		$("#codevalid").addClass("hide");
		$("#pregvalid").addClass("hide");
	}
	$("input:text").focus(function() {
		$(this).select();
	});
	$("#cardExpMod").keyup(function() {
		if ($(this).val().length == 2) {
			if ($(this).val() <= 12 && $(this).val() > 0) {
				$(this).val($(this).val() + "/");
			} else {
				$(this).val("");
			}

		}
	});

	$('#item_barcode').on('keydown', function(e) {
		if (e.which == 13) {
			e.preventDefault();
			var barcode = $('#item_barcode').val();
			getItemDetailsByBarcode(barcode);
		}
	});
	function getItemDetailsByBarcode(barcode) {
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjax(BASE_URL + "/stock/getcurrstocksku/" + barcode + ".htm", function(resp) {
			console.log(resp);
			var stkdet = JSON.parse(resp);
			if ($.isEmptyObject(stkdet)) {
				$("#inputbarcode").text(barcode);
				$("#noItemBarcodeModal").modal("show");
			} else {
				createItemStockDetails(resp, stkdet[0].itemId);
				$("#stkdetModal").modal("show");
			}

		}, null);
	}
	$("#cardFourDigitMod").keyup(function() {
		if (isNaN($("#cardFourDigitMod").val())) {
			document.getElementById('alertmessagecont').innerHTML = getFieldText.numberCheck + " in Card Four Digit.";
			$(this).focus();
			return false;
		} else {
			document.getElementById('alertmessagecont').innerHTML = "";
		}
	});
	var currentDate = new Date();
	$('#date').datepicker({
		format : 'yyyy-mm-dd',
		endDate : currentDate,
		autoclose : true,
	});
	$('#pissuedt').datepicker({
		format : 'yyyy-mm-dd',
		endDate : currentDate,
		autoclose : true,
	});
	var itemcount = 0;
	$('#selitem tbody tr').each(function() {
		itemcount++;
	});
	$("#totitmcount").text(itemcount);
	if (itemcount == 0) {
		$("#payButtId").addClass('disabled');
		$("#delButtId").addClass('disabled');
		$("#holdButtId").addClass('disabled');
	} else {
		$("#payButtId").removeClass("disabled");
		$("#delButtId").removeClass("disabled");
		$("#holdButtId").removeClass("disabled");
	}

	if ($('input[name=payOption]').is(":checked")) {
		//console.log($("input[name=payOption]:checked").val());
		var chkval = $("input[name=payOption]:checked").val();
		//console.log(chkval);
		if (chkval == 1 || chkval == 3) {
			$("#cardDiv").addClass("hide");
		}
	}
	if ($("#tenderamt").val() > 0) {
		//		alert($("#payretadjamt").val());
		//		$("#refundAmt").val(parseFloat($("#tenderamt").val()-$("#nettot").val()).toFixed(2));
		$("#refundAmt").val(parseFloat($("#tenderamt").val() - $("#cashAmt").val()).toFixed(2));
	} else {
		$("#refundAmt").val(parseFloat(0).toFixed(2));
	}

	$("#salediscount").keyup(function() {
		if (isNaN($("#salediscount").val())) {
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck + " in D%";
			$(this).focus();
			return false;
		} else {
			$("#item_dis").val(parseFloat($("#salediscount").val()).toFixed(4));
			document.getElementById('alertMsg').innerHTML = "";
		}
	});
	//item_packqty change calculation
	$("#item_pqty").keyup(function() {
		var pqty = $(this).val();
		var lqty = $("#item_lqty").val();
		var item_vat = $("#item_vat").val();
		var item_tax = $("#item_tax").val();
		var item_taxPercentage = $("#item_taxPercentage").val();
		var item_dis = $("#item_dis").val();
		//		var item_rate_ls = $("#item_mrp").val();
		var item_rate_ls = $("#item_rate_ls").val();
		var conv = $("#item_conv").val();
		var totlqty = (pqty * conv) + Number(lqty);
		// for current stock cal
		var itemid = $("#item_id").val();
		var itembatch = $("#item_batch").val();
		var itemexp = $("#item_exp").val();
		var itemmrppack = $("#item_mrp_pack").val();

		var CommResultsetObj = {};
		CommResultsetObj.itemId = itemid.split("_")[0];
		CommResultsetObj.batchNo = itembatch;
		CommResultsetObj.expiryDateFormat = itemexp;
		CommResultsetObj.mrp = itemmrppack;
		CommResultsetObj.saleId = $("#saleId").val();

		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/stock/getcurrstockbybatexpmrp.htm", CommResultsetObj, function(response) {
			//console.log("response=" + response);
			var itemcurstkdets = JSON.parse(response);
			for ( var i = 0; i < itemcurstkdets.length; i++) {
				var itemcurstkdet = itemcurstkdets[i];
				//console.log("response=" + itemcurstkdet.calculateLooseQty);
				var calculateLooseQty = itemcurstkdet.calculateLooseQty;
				if (totlqty > calculateLooseQty) {
					$("#currStkGraterModal").modal("show");
					$("#currstkofitm").text(itemcurstkdet.stockQty);
				}
			}
		});

		//	var item_rate_ls = $("#item_rate_ls_hid").val();

		var total = 0;
		var discamt = 0;
		var vatamt = 0;
		var taxamt = 0;
		var gstamt = 0;

		/*discamt = item_rate_ls * item_dis / 100;
		$("#item_discamt").val(parseFloat(discamt * totlqty).toFixed(4));
		var rate = item_rate_ls - discamt;
		$("#item_rate_ls").val(parseFloat(rate).toFixed(4));
		total = parseFloat(totlqty * rate).toFixed(4);*/
		total = parseFloat(totlqty * item_rate_ls).toFixed(4);
		discamt = total * item_dis / 100;
		$("#item_discamt").val(parseFloat(discamt).toFixed(4));
		total = total - discamt;
		$("#item_tot").val(parseFloat(total).toFixed(4));
		$("#item_rate_ls").val(parseFloat(item_rate_ls).toFixed(4));

		vatamt = parseFloat(total * item_vat / 100).toFixed(4);
		$("#item_vatamt").val(parseFloat(vatamt).toFixed(4));
		taxamt = parseFloat(total * item_tax / 100).toFixed(4);
		$("#item_taxamt").val(parseFloat(taxamt).toFixed(4));
		gstamt = parseFloat(total * item_taxPercentage / 100).toFixed(4);
		$("#item_CalcTaxAmt").val(parseFloat(gstamt).toFixed(4));
		var isexclu = $("#isexclusive").val();
		if (isexclu == 1) {
			$("#item_tot").val(parseFloat(Number(total) + Number(gstamt)).toFixed(4));
		}
	});

	//item_looseqty change calculation
	$("#item_lqty").keyup(function() {
		var lqty = $(this).val();
		var pqty = $("#item_pqty").val();
		var item_vat = $("#item_vat").val();
		var item_tax = $("#item_tax").val();
		var item_taxPercentage = $("#item_taxPercentage").val();
		var item_dis = $("#item_dis").val();
		//	var item_rate_ls = $("#item_rate_ls_hid").val();
		//		var item_rate_ls = $("#item_mrp").val();
		var item_rate_ls = $("#item_rate_ls").val();
		var conv = $("#item_conv").val();
		var totlqty = (pqty * conv) + Number(lqty);
		// for current stock cal
		var itemid = $("#item_id").val();
		var itembatch = $("#item_batch").val();
		var itemexp = $("#item_exp").val();
		var itemmrppack = $("#item_mrp_pack").val();

		var CommResultsetObj = {};
		CommResultsetObj.itemId = itemid.split("_")[0];
		CommResultsetObj.batchNo = itembatch;
		CommResultsetObj.expiryDateFormat = itemexp;
		CommResultsetObj.mrp = itemmrppack;
		CommResultsetObj.saleId = $("#saleId").val();

		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/stock/getcurrstockbybatexpmrp.htm", CommResultsetObj, function(response) {
			//	console.log("response=" + response);
			var itemcurstkdets = JSON.parse(response);
			for ( var i = 0; i < itemcurstkdets.length; i++) {
				var itemcurstkdet = itemcurstkdets[i];
				//	console.log("response=" + itemcurstkdet.calculateLooseQty);
				var calculateLooseQty = itemcurstkdet.calculateLooseQty;
				if (totlqty > calculateLooseQty) {
					$("#currStkGraterModal").modal("show");
					$("#currstkofitm").text(itemcurstkdet.stockQty);
				}
			}
		});
		// for current stock cal end
		var total = 0;
		var discamt = 0;
		var vatamt = 0;
		var taxamt = 0;
		var gstamt = 0;

		/*discamt = item_rate_ls * item_dis / 100;
		$("#item_discamt").val(parseFloat(discamt * totlqty).toFixed(4));
		var rate = item_rate_ls - discamt;
		$("#item_rate_ls").val(parseFloat(rate).toFixed(4));
		total = parseFloat(totlqty * rate).toFixed(4);*/
		total = parseFloat(totlqty * item_rate_ls).toFixed(4);
		discamt = total * item_dis / 100;
		$("#item_discamt").val(parseFloat(discamt).toFixed(4));
		total = total - discamt;
		$("#item_tot").val(parseFloat(total).toFixed(4));
		$("#item_rate_ls").val(parseFloat(item_rate_ls).toFixed(4));
		vatamt = parseFloat(total * item_vat / 100).toFixed(4);
		$("#item_vatamt").val(parseFloat(vatamt).toFixed(4));
		taxamt = parseFloat(total * item_tax / 100).toFixed(4);
		$("#item_taxamt").val(parseFloat(taxamt).toFixed(4));
		gstamt = parseFloat(total * item_taxPercentage / 100).toFixed(4);
		$("#item_CalcTaxAmt").val(parseFloat(gstamt).toFixed(4));
		var isexclu = $("#isexclusive").val();
		if (isexclu == 1) {
			$("#item_tot").val(parseFloat(Number(total) + Number(gstamt)).toFixed(4));
		}
	});

	//item_discount change calculation
	$("#item_dis").keyup(function() {
		var item_dis = $(this).val();
		var item_maxdisper = $("#item_maxDiscountLimit").val();
		var item_taxPercentage = $("#item_taxPercentage").val();
		if (Number(item_dis) > Number(item_maxdisper)) {
			$("#itemmaxdisperspan").text(parseFloat(item_maxdisper).toFixed(2));
			$("#item_dis").val(parseFloat(0).toFixed(4));
			$("#itemMaxDisModal").modal("show");
		}
		var pqty = $("#item_pqty").val();
		var item_vat = $("#item_vat").val();
		var item_tax = $("#item_tax").val();
		var lqty = $("#item_lqty").val();
		//	var item_rate_ls = $("#item_rate_ls_hid").val();
		//		var item_rate_ls = $("#item_mrp").val();
		var item_rate_ls = $("#item_rate_ls").val();
		var conv = $("#item_conv").val();
		var total = 0;
		var discamt = 0;
		var vatamt = 0;
		var taxamt = 0;
		var gstamt = 0;

		var totlqty = (pqty * conv) + Number(lqty);
		/*discamt = item_rate_ls * item_dis / 100;
		$("#item_discamt").val(parseFloat(discamt * totlqty).toFixed(4));
		var rate = item_rate_ls - discamt;
		$("#item_rate_ls").val(parseFloat(rate).toFixed(4));
		total = parseFloat(totlqty * rate).toFixed(4);*/
		total = parseFloat(totlqty * item_rate_ls).toFixed(4);
		discamt = total * item_dis / 100;
		$("#item_discamt").val(parseFloat(discamt).toFixed(4));
		total = total - discamt;
		$("#item_tot").val(parseFloat(total).toFixed(4));
		$("#item_rate_ls").val(parseFloat(item_rate_ls).toFixed(4));

		vatamt = parseFloat(total * item_vat / 100).toFixed(4);
		$("#item_vatamt").val(parseFloat(vatamt).toFixed(4));
		taxamt = parseFloat(total * item_tax / 100).toFixed(4);
		$("#item_taxamt").val(parseFloat(taxamt).toFixed(4));
		gstamt = parseFloat(total * item_taxPercentage / 100).toFixed(4);
		$("#item_CalcTaxAmt").val(parseFloat(gstamt).toFixed(4));
		var isexclu = $("#isexclusive").val();
		if (isexclu == 1) {
			$("#item_tot").val(parseFloat(Number(total) + Number(gstamt)).toFixed(4));
		}
	});

	//item_sale_rate change calculation
	$("#item_sale_rate").keyup(function() {
		var item_salerate = $(this).val();
		var isexclu = $("#isexclusive").val();
		var itemmrppack = $("#item_mrp_pack").val();
		var item_taxPercentage = $("#item_taxPercentage").val();
		if (isexclu == 1) {
			var mop = (100 * itemmrppack) / (100 + Number(item_taxPercentage));
			if (item_salerate > mop) {
				$("#item_sale_rate").val(parseFloat(mop).toFixed(4));
				item_salerate = mop;
			}
		} else {
			if (item_salerate > itemmrppack) {
				$("#item_sale_rate").val(parseFloat(itemmrppack).toFixed(4));
				item_salerate = itemmrppack;
			} else {

			}
		}

		var item_dis = $("#item_dis").val();
		var pqty = $("#item_pqty").val();
		var item_vat = $("#item_vat").val();
		var item_tax = $("#item_tax").val();
		var lqty = $("#item_lqty").val();
		//	var item_rate_ls = $("#item_rate_ls_hid").val();
		//var item_rate_ls = $("#item_mrp").val();
		var conv = $("#item_conv").val();
		var item_rate_ls = item_salerate / conv;
		var total = 0;
		var discamt = 0;
		var vatamt = 0;
		var taxamt = 0;
		var gstamt = 0;

		var totlqty = (pqty * conv) + Number(lqty);
		/*discamt = item_rate_ls * item_dis / 100;
		$("#item_discamt").val(parseFloat(discamt * totlqty).toFixed(4));
		var rate = item_rate_ls - discamt;
		$("#item_rate_ls").val(parseFloat(rate).toFixed(4));
		total = parseFloat(totlqty * rate).toFixed(4);*/
		total = parseFloat(totlqty * item_rate_ls).toFixed(4);
		discamt = total * item_dis / 100;
		$("#item_discamt").val(parseFloat(discamt).toFixed(4));
		total = total - discamt;
		$("#item_tot").val(parseFloat(total).toFixed(4));
		$("#item_rate_ls").val(parseFloat(item_rate_ls).toFixed(4));
		vatamt = parseFloat(total * item_vat / 100).toFixed(4);
		$("#item_vatamt").val(parseFloat(vatamt).toFixed(4));
		taxamt = parseFloat(total * item_tax / 100).toFixed(4);
		$("#item_taxamt").val(parseFloat(taxamt).toFixed(4));
		gstamt = parseFloat(total * item_taxPercentage / 100).toFixed(4);
		$("#item_CalcTaxAmt").val(parseFloat(gstamt).toFixed(4));

		if (isexclu == 1) {
			$("#item_tot").val(parseFloat(Number(total) + Number(gstamt)).toFixed(4));
		}
	});

	var pqty_field = $("#pqty_label").text();

	var lqty_field = $("#lqty_label").text();

	$("#item_pqty").keyup(function() {
		if (isNaN($("#item_pqty").val())) {
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck + " in " + pqty_field;
			$(this).focus();
			return false;
		} else {
			if ($("#item_pqty").val() < 0) {
				document.getElementById('alertMsg').innerHTML = pqty_field + " " + getFieldText.checkNegative;
				$(this).focus();
				return false;
			} else {
				document.getElementById('alertMsg').innerHTML = "";
			}
		}
	});

	$("#item_lqty").keyup(function() {
		if (isNaN($("#item_lqty").val())) {
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck + " in " + lqty_field;
			$(this).focus();
			return false;
		} else {
			if ($("#item_lqty").val() < 0) {
				document.getElementById('alertMsg').innerHTML = lqty_field + " " + getFieldText.checkNegative;
				$(this).focus();
				return false;
			} else {
				document.getElementById('alertMsg').innerHTML = "";
			}
		}
	});

	$("#item_dis").keyup(function() {
		if (isNaN($("#item_dis").val())) {
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck + " in D%";
			$(this).focus();
			return false;
		} else {
			document.getElementById('alertMsg').innerHTML = "";
		}
	});
	
	$("#tenderamt").keyup(function(e) {
		AmtChange(e);
	});
	
	$("#cashAmt").keyup(function(e) {
		AmtChange(e);
	});
	
	$("#cardAmt").keyup(function(e) {
		AmtChange(e);
	});
	
	function AmtChange(e) {
		/*if (isNaN($("#"+e.target.id.toString()).val())) {
			//			$("#confirmval").val(0);
			document.getElementById('alertmessagecont').innerHTML = getFieldText.numberCheck + " in Cash Amount";
			//			showConfirmModal();
			$(this).focus();
			return false;
		} else {*/
			if ($("#"+e.target.id.toString()).val() == '') {
				$("#"+e.target.id.toString()).val(0);
			}
			document.getElementById('alertmessagecont').innerHTML = "";
			//changed for sale order advance
			var nettot = parseFloat($("#paymodnettot").val());
			//var nettot = $("#nettot").val();

			var cashamt = parseFloat($("#cashAmt").val());
			var tenderamt = parseFloat($("#tenderamt").val());
			var payretadjamt = parseFloat($("#payretadjamt").val());
			var cardAmt = parseFloat($("#cardAmt").val());
			var refundAmt = parseFloat($("#refundAmt").val());
			var creditAmt = parseFloat($("#creditAmt").val());
			
			nettot = nettot - Number(payretadjamt);
			
			if (cashamt == '') {
				cashamt = 0.00;
			}
			if (cardAmt == '') {
				cardAmt = 0.00;
			}
			if (tenderamt == '') {
				tenderamt = 0.00;
			}
			if (payretadjamt == '') {
				payretadjamt = 0.00;
			}
			
			if(e.target.id == "tenderamt") {
//				alert("in tender amount");
				if (tenderamt > nettot) {
					cashamt = nettot;
					refundAmt = parseFloat(tenderamt - nettot);
					creditAmt = (parseFloat(0));
					cardAmt = (parseFloat(0));
				}
				else if(tenderamt < nettot) {
					cashamt = (parseFloat(tenderamt));
					refundAmt = (parseFloat(0));
					
					//here was credit customer
				} else if(tenderamt == nettot) {
					cashamt = tenderamt;
					refundAmt = (parseFloat(0));
					creditAmt = (parseFloat(0));
					cardAmt = (parseFloat(0));
				}
				
			}
			else if(e.target.id == "cashAmt") {
//				alert("in cash amount");
				if(cashamt > tenderamt){
					tenderamt = cashamt;
					refundAmt = tenderamt - cashamt;
				} else if(cashamt<=tenderamt){
					refundAmt = tenderamt - cashamt;
				} 
				if(cashamt>nettot){
					tenderamt = cashamt;
					cashamt = nettot;
					refundAmt = tenderamt - cashamt;
					creditAmt = 0;
					cardAmt = 0;
				} else if(cashamt<nettot){
					if(cashamt>tenderamt){
						tenderamt = cashamt;
						refundAmt = tenderamt - cashamt;
					} else if(cashamt<=tenderamt){
						refundAmt = tenderamt - cashamt;
					}
				} else if(cashamt == nettot){
					cashamt = nettot;
					refundAmt = tenderamt - cashamt;
					creditAmt = 0;
					cardAmt = 0;
				}
			}
			else if(e.target.id == "cardAmt") {
//				alert("in card amount");
				if (cardAmt < nettot) {
//					var creditcustomer = $("#salecustid").val();
					var creditcustomer = $("#creditLimit").val();
					if(creditcustomer==0){
						//$("#carddiv").stop().show("slow");
						cashamt = (parseFloat(nettot - Number(cardAmt)));
						if(cashamt>tenderamt){
							tenderamt = cashamt;
						}
						refundAmt = tenderamt - cashamt;
						creditAmt = (parseFloat(0));
					}else{
						var totamt = cardAmt + cashamt;
						if(totamt < nettot) {
							creditAmt = (parseFloat(nettot - totamt));
						}
						else {
		//					$("#carddiv").stop().hide("slow");
							creditAmt = (parseFloat(nettot - cardAmt));
							cashamt = (parseFloat(0));
							tenderamt = (parseFloat(0));
							refundAmt = (parseFloat(0));
						}
					}
				} else if (cardAmt >= nettot) {
					cardAmt = nettot;
					creditAmt = (parseFloat(0));
					cashamt = (parseFloat(0));
					tenderamt = (parseFloat(0));
					refundAmt = (parseFloat(0));
				}
			}

			if(e.target.id != "cardAmt") {
//				var creditcustomer = $("#salecustid").val();
				var creditcustomer = $("#creditLimit").val();
				if(creditcustomer==0){
					//$("#carddiv").stop().show("slow");
					cardAmt = (parseFloat(nettot - Number(cashamt)));
					creditAmt = (parseFloat(0));
				}else{
					var totamt = cardAmt + cashamt;
					if(totamt < nettot) {
						creditAmt = (parseFloat(nettot - totamt));
					}
					else {
	//					$("#carddiv").stop().hide("slow");
						creditAmt = (parseFloat(nettot - cashamt));
						cardAmt = (parseFloat(0));
					}
				}
			}
			
			if(e.target.id == "tenderamt") {
				tenderamt = Number(tenderamt);
			} else {
				tenderamt = parseFloat(tenderamt).toFixed(2);
			}
			if(e.target.id == "cashAmt") {
				cashamt = Number(cashamt);
			} else {
				cashamt = parseFloat(cashamt).toFixed(2);
			}
			if(e.target.id == "cardAmt") {
				cardAmt = Number(cardAmt);
			} else {
				cardAmt = parseFloat(cardAmt).toFixed(2);
			}
			$("#cashAmt").val(cashamt);
			$("#tenderamt").val(tenderamt);
			$("#cardAmt").val(cardAmt);
			$("#refundAmt").val(parseFloat(refundAmt).toFixed(2));
			$("#creditAmt").val(parseFloat(creditAmt).toFixed(2));
			
			if(cardAmt>0){
				$("#carddiv").stop().show("slow");
			} else {
				$("#carddiv").stop().hide("slow");
			}
//		}
		
	}
	
	$("#salecustph").autocomplete({
		source : function(	request,
							response) {
			if (request.term.length >= 2) {
				$.ajax({
					url : BASE_URL + "/customer/getcustomerwithcreditlistautocomplete.htm",
					type : "GET",
					data : {
						tagName : request.term
					},

					dataType : "json",

					success : function(data) {
						if (!$.trim(data)) {
							$('#add_cust_td').removeClass("hide");
							$('.add_td').removeClass("hide");
							$('#blacktext_td').removeClass("hide");
							$('#black_td').removeClass("hide");
						} else {
							$('#add_cust_td').addClass("hide");
							$('.add_td').addClass("hide");
							$('#blacktext_td').addClass("hide");
							$('#black_td').addClass("hide");
						}
						response($.map(data, function(v) {
							return {
								label : v.phoneNo,
								itemCode : v.id,
								name : v.name,
								creditLimit : v.creditLimit,
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
			console.log("items=" + ui.item.creditLimit);
			$("#salecustid").val(ui.item.itemCode);
			$("#salecustname").val(ui.item.name);
			$("#custCreditLimit").val(ui.item.creditLimit);
			$("#ecardno").val(ui.item.items.code);
			$("#custEmail").val(ui.item.items.fax); // new added 10.5.2019
		},
		change : function(	e,
							ui) {
			if (!(ui.item))
				//		e.target.value = "";
				$("#salecustid").val(0);
			//$("#salecustname").val("");
		},
	});

	$("#salecustname").autocomplete({
		source : function(	request,
							response) {
			//		if (request.term.length >= 2) {
			$.ajax({
				url : BASE_URL + "/customer/getcustomerwithcreditlistautocompletebyname.htm",
				type : "GET",
				data : {
					tagName : request.term
				},

				dataType : "json",
				success : function(data) {
					if (!$.trim(data)) {
						$('#add_cust_td').removeClass("hide");
						$('.add_td').removeClass("hide");
						$('#blacktext_td').removeClass("hide");
						$('#black_td').removeClass("hide");
					} else {
						$('#add_cust_td').addClass("hide");
						$('.add_td').addClass("hide");
						$('#blacktext_td').addClass("hide");
						$('#black_td').addClass("hide");
					}
					response($.map(data, function(v) {
						return {
							label : v.name,
							itemCode : v.id,
							phno : v.phoneNo,
							address : v.address,
							creditLimit : v.creditLimit,
							items : v,
						};

					}));
				},
				error : function(error) {
					alert('error: ' + error);
				}
			});
			//		}
		},
		select : function(	e,
							ui) {
             /*alert(ui.item.items.fax);*/
			$("#salecustid").val(ui.item.itemCode);
			$("#salecustph").val(ui.item.phno);
			$("#salecustaddr").val(ui.item.address);
			$("#custCreditLimit").val(ui.item.creditLimit);
			$("#ecardno").val(ui.item.items.code);
			$("#custEmail").val(ui.item.items.fax); // new added 10.5.2019 
			//		$("#prebilltd").removeClass("hide");
			//		$("#prebilltext").removeClass("hide");
		},
		change : function(	e,
							ui) {
			if (!(ui.item))
				//e.target.value = "";
				$("#salecustid").val(0);
			//$("#salecustph").val(0000000000);
		},
	});
	// Doctor autocomplete
	$("#saledocname").autocomplete({
		source : function(	request,
							response) {
			//		if (request.term.length >= 2) {
			$.ajax({
				url : BASE_URL + "/invsetup/getdoctorlistautocompletebyname.htm",
				type : "GET",
				data : {
					tagName : request.term
				},

				dataType : "json",

				success : function(data) {
					if (!$.trim(data)) {
						$('#add_doc_td').removeClass("hide");
						//$('.add_td').removeClass("hide");
					} else {
						$('#add_doc_td').addClass("hide");
						//$('.add_td').addClass("hide");
					}
					response($.map(data, function(v) {
						return {
							label : v.name,
							itemCode : v.id,
							phno : v.phoneNo,
							items : v,
						};

					}));
				},
				error : function(error) {
					alert('error: ' + error);
				}
			});
			//		}
		},
		select : function(	e,
							ui) {

			$("#saledocid").val(ui.item.itemCode);
			//		$("#salecustph").val(ui.item.phno);
		},
		change : function(	e,
							ui) {
			if (!(ui.item)) {
				//		e.target.value = "";
				$("#saledocid").val(0);
			}
		},
	});

	$("#item_name").autocomplete({
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
			//		console.log(ui.item.itemCode);
			//		console.log(ui.item.label);
			console.log(ui.item.items);
			$("#itemid").val(ui.item.itemCode);
			$("#modmanufname").text(ui.item.items.manufacturerName);
			$("#modcontentname").text(ui.item.items.contentName);
			$("#modrackname").text(ui.item.items.schdName);
			$("#modgroupname").text(ui.item.items.groupName);
			$("#moditemnote").text("");
			// call new  ajax for item details
			getItemDetails(ui.item.itemCode);
			// call new ajax end

		},
		change : function(	e,
							ui) {
			if (!(ui.item))
				e.target.value = "";
		},
	});

	$("#itemContent").autocomplete({
		source : function(	request,
							response) {
			if (request.term.length >= 2) {
				$.ajax({
					url : BASE_URL + "/content/autocompleteitemcontent.htm",
					type : "GET",
					data : {
						tagName : request.term
					},

					dataType : "json",

					success : function(data) {
						response($.map(data, function(v) {
							return {
								label : v.name,
								itemCode : v.id,
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
			//console.log(ui.item.itemCode)
			//console.log(ui.item.label)
			$("#content_id").val(ui.item.itemCode);
			$("#content_Dets").val(ui.item.label);

			getItemByContent(ui.item.itemCode);
		},
		change : function(	e,
							ui) {
			if (!(ui.item))
				e.target.value = "";
		},
	});

});

function getesitype() {
	var etype = $("#esitypeid").val();

	//alert(remarks);
	//alert(etype);
	if (etype == 1) {
		$("#codetextid").removeClass("hide");
		$("#pregtextid").removeClass("hide");
		$("#codevalid").removeClass("hide");
		$("#pregvalid").removeClass("hide");
		$("#remarkstext").text(remarks);
	} else if (etype == 3) {
		$("#codetextid").addClass("hide");
		$("#pregtextid").addClass("hide");
		$("#codevalid").addClass("hide");
		$("#pregvalid").addClass("hide");
		$("#remarkstext").text("Place of Treatment");
		$("#remarks").autocomplete({
			source : function(	request,
								response) {

				$.ajax({
					url : BASE_URL + "/pos/getplaceoftreatmentautocomplete.htm",
					type : "GET",
					cache : false,
					data : {
						tagName : request.term
					},

					dataType : "json",
					success : function(data) {
						response($.map(data, function(v) {
							return {
								label : v.remarks,
								items : v,
							};
						}));
					},
					error : function(error) {
						alert('error: ' + error);
					}
				});

			},
			position : {
				my : "left bottom",
				at : "left top",
			},
			select : function(	e,
								ui) {

				$("#remarks").val(ui.item.label);

			},

		});
	} else {
		$("#codetextid").addClass("hide");
		$("#pregtextid").addClass("hide");
		$("#codevalid").addClass("hide");
		$("#pregvalid").addClass("hide");
		$("#remarkstext").text(remarks);
	}

}

$("#cardbut").click(function() {
	$("#carddiv").toggle("slow");
});
function closeCurrStkModal() {
	$("#item_pqty").val(0);
	$("#item_lqty").val(0);
	$("#item_tot").val(parseFloat(0).toFixed(2));
}
function calculateTotalMRP() {
	var grandtotalMRP = 0.00;
	var itemcount = 0;
	$('#selitem tbody tr').each(function() {
		var itmmrp = $(this).find("#saletabmrp").html();
		grandtotalMRP = grandtotalMRP + Number(itmmrp);
		itemcount++;
	});
	$("#totitmcount").text(itemcount);
	$("#totmrpamt").val(parseFloat(grandtotalMRP).toFixed(2));
	if (itemcount == 0) {
		$("#payButtId").addClass('disabled');
		$("#delButtId").addClass('disabled');
		$("#holdButtId").addClass('disabled');
	} else {
		$("#payButtId").removeClass("disabled");
		$("#delButtId").removeClass("disabled");
		$("#holdButtId").removeClass("disabled");
	}
}
function calculateTotalamt() {
	var totsaleamt = 0.00;
	$('#selitem tbody tr').each(function() {
		var saleamt = $(this).find("#saletabamt").html();
		totsaleamt = totsaleamt + Number(saleamt);
	});
	$("#totgrossamt").val(parseFloat(totsaleamt).toFixed(2));
}
function calculateTotalVat() {
	var grandtotalVat = 0.00;
	$('#selitem tbody tr').each(function() {
		var itmvat = $(this).find("#saletabvat").html();

		grandtotalVat = grandtotalVat + Number(itmvat);
	});
	$("#totvatamt").val(parseFloat(grandtotalVat).toFixed(2));
}
function calculateTotalGSTamt() {
	var grandtotalGST = 0.00;
	$('#selitem tbody tr').each(function() {
		var itmgst = $(this).find("#saletabitemcalcgstamt").html();

		grandtotalGST = grandtotalGST + Number(itmgst);
	});
	$("#totgstamt").val(parseFloat(grandtotalGST).toFixed(2));
}
function calculateTotalPurchaseamt() {
	var grandtotalPur = 0.00;
	$('#selitem tbody tr').each(function() {
		var itmpur = $(this).find("#saletabpurcost").html();
		grandtotalPur = grandtotalPur + Number(itmpur);
	});
	var nettot = $("#nettot").val();
	$("#profitperc").val(parseFloat(Number(nettot) - Number(grandtotalPur)).toFixed(2));
}
function calculateTotalTax() {
	var grandtotalTax = 0.00;
	$('#selitem tbody tr').each(function() {
		var itmtax = $(this).find("#saletabtax").html();

		grandtotalTax = grandtotalTax + Number(itmtax);
	});
	$("#tottaxamt").val(parseFloat(grandtotalTax).toFixed(2));
}
function calculateTotalDisc() {
	var grandtotalDisc = 0.00;
	$('#selitem tbody tr').each(function() {
		var itmmrp = $(this).find("#saletabdisc").html();
		grandtotalDisc = grandtotalDisc + Number(itmmrp);
	});
	$("#totdiscamt").val(parseFloat(grandtotalDisc).toFixed(4));
//	$("#totdiscamt").val(grandtotalDisc);
	var disc = $("#spcldiscperc").val();
	if (disc != 0) {
		$("#spcldisc").val(parseFloat(grandtotalDisc).toFixed(4));
	}
}
function calculateNetTotal() {
	var grandNetTotal = 0.00;
	$('#selitem tbody tr').each(function() {
		var itmmrp = $(this).find("#saletabtotamt").html();
		grandNetTotal = grandNetTotal + Number(itmmrp);
	});
	var roundednetamnt = Math.round(Number(grandNetTotal));
	//console.log("roundednetamnt=" + parseFloat(roundednetamnt).toFixed(2));
	$("#nettot").val(parseFloat(roundednetamnt).toFixed(2));
	var roundoff = Number(roundednetamnt) - Number(grandNetTotal);
	//console.log("roundoff=" + parseFloat(roundoff).toFixed(2));
	$("#roundoff").val(parseFloat(roundoff).toFixed(2));
	// value of tenderamt and cash amt
	//	$("#tenderamt").val(parseFloat(roundednetamnt).toFixed(2));
	$("#tenderamt").val(parseFloat(0).toFixed(2));
	$("#refundAmt").val(parseFloat(0).toFixed(2));
	$("#creditAmt").val(parseFloat(Number(roundednetamnt)-Number($("#payretadjamt").val())).toFixed(2));
	$("#paymodnettot").val(parseFloat(roundednetamnt).toFixed(2));
}
function calculateSpclDisc() {
	calSpclDisNew();
	
	calculateTotalMRP();
	calculateTotalamt();
	calculateTotalVat();
	calculateTotalTax();
	calculateTotalDisc();
	calculateNetTotal();
	//	calculateSpclDisc();
	calculateTotalGSTamt();
	calculateTotalPurchaseamt();
	$("#spcldisc").val($("#totdiscamt").val());
	/*var discper = $("#spcldiscperc").val();
	var grandNetTotal = 0.00;
	$('#selitem tbody tr').each(function() {
		var itmmrp = $(this).find("#saletabtotamt").html();
		grandNetTotal = grandNetTotal + Number(itmmrp);
	});
	var discamt = grandNetTotal * discper / 100;
	var newnettot = grandNetTotal - discamt;
	var roundednetamnt = Math.round(Number(newnettot));
	$("#spcldisc").val(parseFloat(discamt).toFixed(2));
	$("#nettot").val(parseFloat(roundednetamnt).toFixed(2));
	var roundoff = Number(roundednetamnt) - Number(newnettot);
	$("#roundoff").val(parseFloat(roundoff).toFixed(2));
	// cal pur amt
	var grandtotalPur = 0.00;
	$('#selitem tbody tr').each(function() {
		var itmpur = $(this).find("#saletabpurcost").html();
		grandtotalPur = grandtotalPur + Number(itmpur);
	});
	var profitamt = Number(roundednetamnt) - Number(grandtotalPur);
	$("#profitperc").val(parseFloat(profitamt).toFixed(2));
	// value of tenderamt and cash amt
	//	$("#tenderamt").val(parseFloat(roundednetamnt).toFixed(2));
	$("#tenderamt").val(parseFloat(0).toFixed(2));
	$("#refundAmt").val(parseFloat(0).toFixed(2));
	$("#creditAmt").val(parseFloat(roundednetamnt).toFixed(2));
	$("#paymodnettot").val(parseFloat(roundednetamnt).toFixed(2));*/
}

/*$("spcldisc").keyup(function() {
	var discper = 100 / Number($("nettot").val()) * $("spcldisc").val();
	$("#spcldiscperc").val(discper);
	calSpclDisNew();

});*/
function calSpclDisNew() {
	var discper = 0;
	if ($("#spcldiscperc").val() == "") {
		discper = 0;
	}if (Number($("#spcldiscperc").val()) >100) {
		$("#spcldiscperc").val(100);
		discper = 100;
	}
	else {
		discper = $("#spcldiscperc").val();
	}

	var isexclu = $("#isexclusive").val();
	var grandNetTotal = 0.00;
	$('#selitem tbody tr').each(function() {
		var itemid = this.id; //$("#spcldiscperc").attr("id");
		var total = 0;
		var discamt = 0;
		var gstamt = 0;

		var itmpqty = $(this).find("#saletabpqty").html();
		var itmlqty = $(this).find("#saletablqty").html();
		var itmconv = $(this).find("#saletabconv").html();
		var salerateperunit = $(this).find("#saletabrateperunit").html();
		var saletabmaxDiscountLimit = $(this).find("#saletabmaxDiscountLimit").html();
		var item_taxPercentage = $(this).find("#saletabtaxPercentage").html();
		var saletabisDiscount = $(this).find("#saletabisDiscount").html();

		var totlqty = Number(itmpqty) * Number(itmconv) + Number(itmlqty);
		total = parseFloat(totlqty * salerateperunit).toFixed(4);
		if (Number(saletabisDiscount) == 1) {
			if (Number(discper) > Number(saletabmaxDiscountLimit)) {
				discamt = total * Number(saletabmaxDiscountLimit) / 100;
				$("#selitem tr#" + itemid).find('#saletabdiscperc').text(parseFloat(saletabmaxDiscountLimit).toFixed(4));
			} else {
				discamt = total * Number(discper) / 100;
				$("#selitem tr#" + itemid).find('#saletabdiscperc').text(parseFloat(discper).toFixed(4));
			}
		}
		//alert(discamt);
		total = total - discamt;
		//alert(item_taxPercentage);
		gstamt = parseFloat(total * item_taxPercentage / 100).toFixed(4);
		if (isexclu == 1) {
			total = (parseFloat(Number(total) + Number(gstamt)).toFixed(4));
		}
		$("#selitem tr#" + itemid).find('#saletabtotamt').text(parseFloat(total).toFixed(4));
		$("#selitem tr#" + itemid).find('#saletabdisc').text(parseFloat(discamt).toFixed(4));
		$("#selitem tr#" + itemid).find('#saletabitemcalcgstamt').text(parseFloat(gstamt).toFixed(4));

	});
	
}
//sleep time expects milliseconds
function sleep (time) {
  return new Promise((resolve) => setTimeout(resolve, time));
}
$("#spcldisc").blur(function() {
	$(this).val(parseFloat($(this).val()).toFixed(4));
});
function calculateSpclDiscPrcnt() {
//	sleep(600).then(() => {
	/*if(disctimer!=null)
		clearInterval(disctimer);
	
	disctimer = setInterval(function(){*/
		var total = 0;
		if((Number($("#nettot").val())==0 && $("#saletabitemdetails tr").length==0)) {
			$("#spcldisc").val(parseFloat(0).toFixed(2));
			return false;
		}
		if($("#spcldisc").val()=='') {
			$("#spcldisc").val(parseFloat(0).toFixed(2));
		}
		$('#selitem tbody tr').each(function() {
			var itemid = this.id; //$("#spcldiscperc").attr("id");
//			var total = 0;
			var discamt = 0;
			var gstamt = 0;

			var itmpqty = $(this).find("#saletabpqty").html();
			var itmlqty = $(this).find("#saletablqty").html();
			var itmconv = $(this).find("#saletabconv").html();
			var salerateperunit = $(this).find("#saletabrateperunit").html();
			var totlqty = Number(itmpqty) * Number(itmconv) + Number(itmlqty);
			total += Number(totlqty * salerateperunit);
		});
//		var nettot = Number($("#nettot").val());
		var spcldisc = Number($("#spcldisc").val());
		var discper = 0;
		if(total != 0) {
			discper = parseFloat(100 / total * spcldisc).toFixed(4);
		}
		if(discper<=100) {
			$("#spcldiscperc").val(discper);
		} else {
			$("#spcldiscperc").val(100);
		}
		
		calSpclDisNew();
		
		calculateTotalMRP();
		calculateTotalamt();
		calculateTotalVat();
		calculateTotalTax();
//		calculateTotalDisc();
		calculateNetTotal();
		//	calculateSpclDisc();
		calculateTotalGSTamt();
		calculateTotalPurchaseamt();
		if(discper>=100) {
			calculateTotalDisc();
		}
//		$("#spcldisc").val($("#totdiscamt").val());
		
//	},1000);
//	});
		/*var discamt = $("#spcldisc").val();
		var grandNetTotal = 0.00;
		$('#selitem tbody tr').each(function() {
			var itmmrp = $(this).find("#saletabtotamt").html();
			grandNetTotal = grandNetTotal + Number(itmmrp);
		});
		var discper = (discamt * 100) / grandNetTotal;
		var newnettot = grandNetTotal - discamt;
		var roundednetamnt = Math.round(Number(newnettot));
		$("#spcldiscperc").val(parseFloat(discper).toFixed(2));
		$("#nettot").val(parseFloat(roundednetamnt).toFixed(2));
		var roundoff = Number(roundednetamnt) - Number(newnettot);
		$("#roundoff").val(parseFloat(roundoff).toFixed(2));
		// value of tenderamt and cash amt
		//	$("#tenderamt").val(parseFloat(roundednetamnt).toFixed(2));
		$("#tenderamt").val(parseFloat(0).toFixed(2));
		$("#refundAmt").val(parseFloat(0).toFixed(2));
		$("#creditAmt").val(parseFloat(roundednetamnt).toFixed(2));
		$("#paymodnettot").val(parseFloat(roundednetamnt).toFixed(2));*/
//		calSpclDisNew();
//	});
}

function clearHeaderDiv() {
	$("#header_div").find('input:text').val('');
	$("#header_div").find('input:hidden').val('');
	$('#item_name').prop('readonly', false);
	$('#item_barcode').prop('readonly', false);
	$("#add_btn").removeClass("hide");
	$("#edit_btn").addClass("hide");
	$("#item_id").val("0");
	$("#item_dis").val(parseFloat($("#salediscount").val()).toFixed(4));
	$("#item_name").focus();
	$("#itemContent").prop('readonly', false);
	$("#content_Dets").val("");
	$("#content_id").val("0");
	//	document.getElementById('alertMsg').innerHTML = "";
};
function clearSaleInv() {
	//	$("#saleInvDiv").find('input:text').val('');
	closeRetAdjMod();
	var nettot = $("#nettot").val();
	$("#creditAmt").val(parseFloat(nettot).toFixed(2));
	$("#paymodnettot").val(parseFloat(nettot).toFixed(2));
	$("#tenderamt").val(parseFloat(0).toFixed(2));
	$("#refundAmt").val(parseFloat(0).toFixed(2));
	$("#cashAmt").val(parseFloat(0).toFixed(2));
	$("#cardAmt").val(parseFloat(0).toFixed(2));
	$("#cardExpMod").val('');
	$("#cardFourDigitMod").val('');
	if ($("#retamtvalsamepage").val() != 0) {
		var s = $("#retamtvalsamepage").val();
		if ($("#retamtvalsamepage").val() == undefined || $("#retamtvalsamepage").val() == "") {
			s = 0;
		}
		$("#payretadjamt").val(parseFloat(s).toFixed(2));
	}
	document.getElementById('alertmessagecont').innerHTML = "";

}
function ExistsOk() {
	clearHeaderDiv();
}
/*function showSelItemDelModal(trId) {
	$("#confirmId").val(trId);
	$('#confirmModal').modal('show');
}
function DoConfirm() {
	var itmid = $("#confirmId").val();
	$('#saletabitemdetails tr#' + itmid).remove();
	clearHeaderDiv();
	calculateTotalMRP();
	calculateTotalVat();
	calculateTotalDisc();
	calculateNetTotal();
}*/

function getCustPreviousBill(custphno) {
	//	console.log("custphno=" + custphno.length);
	if (custphno == '' || custphno.length < 10) {

	} else {
		$("#saleheaderdet").text("");
		$("#cusprebillphno").text(custphno + "/" + $('#salecustname').val());
		$('#pleasewaitModal').modal('show');
		var commonresultsetmap = {};
		commonresultsetmap.custPh = custphno;
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/pos/getsaleheaderdetbycustomerphno.htm", commonresultsetmap, function(response) {
			$('#pleasewaitModal').modal('hide');
			//			console.log("response=" + response);
			var headerdetails = JSON.parse(response);
			if (headerdetails.length == 0) {
				$("#prevsaledetnotfounddiv").removeClass("hide");
				$("#saledetmodtable").addClass("hide");
			} else {
				$("#prevsaledetnotfounddiv").addClass("hide");
				$("#saledetmodtable").removeClass("hide");
				for ( var i = 0; i < headerdetails.length; i++) {
					var headerdetail = headerdetails[i];
					var poststr = "";
					if (headerdetail.isPosted == 0) {
						poststr = "Unposted";
					} else {
						poststr = "Posted";
					}
					var starttrline = "<tr id=" + headerdetail.saleId + " style='cursor: pointer;' onclick='javascript:cusSelDet(" + headerdetail.saleId + ",&quot;" + headerdetail.invNo + "&quot;)'>";
					var invno = "<td>" + headerdetail.invNo + "</td>";
					var invdate = "<td>" + moment(headerdetail.invDate).format('YYYY-MM-DD') + " / " + headerdetail.invTime + "</td>";
					var invModeName = "<td>" + headerdetail.invModeName + "</td>";
					var grossAmount = "<td>" + parseFloat(headerdetail.grossAmount).toFixed(2) + "</td>";
					var discAmount = "<td>" + parseFloat(headerdetail.discAmount).toFixed(2) + "</td>";//
					var netAmount = "<td>" + parseFloat(headerdetail.netAmount).toFixed(2) + "</td>";
					var poststr1 = "<td>" + poststr + "</td>";
					var endtrline = "</tr>";
					createdrowline = starttrline + invno + invdate + invModeName + grossAmount + discAmount + netAmount + poststr1 + endtrline;
					$("#saleheaderdet").append(createdrowline);
				}
			}

		});
		$("#custLastBillModal").modal("show");
	}

}

function cusSelDet(	selid,
					saleinvno) {

	$("#custLastBillModal").modal("hide");
	$("#saletabitemdetails").text("");
	$("#searchmodtbody").text("");
	//$("#saledetailModal").modal("show");
	$("#searchmodinvno").text(saleinvno);
	var commonresultsetmap = {};
	commonresultsetmap.saleId = selid;
	var generatedHtml = "";
	var tablestart = '<div style="max-height: 175px;overflow: auto;"><table id="searchmodtable" class="table table-bordered table-condensed-trx" style="bgcolor:#000;margin-bottom: 1px;cursor: pointer;">';
	var tableend = '</table></div>';
	var headerrow = '<tr><td>Item Name</td><td>P.Qty</td><td>L.Qty</td></tr>';
	generatedHtml += tablestart + headerrow;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/pos/getsaledetbycustomerphno.htm", commonresultsetmap, function(response) {
		//	console.log("response=" + response);
		var cashmemodetails = JSON.parse(response);
		for ( var i = 0; i < cashmemodetails.length; i++) {
			var cashmemodet = cashmemodetails[i];
			//var mrpamt = ((cashmemodet.packQty * cashmemodet.conversion) + Number(cashmemodet.looseQty)) * cashmemodet.mrp;

			var starttrline = "<tr id='" + cashmemodet.itemUniqueKey + "' onclick='getCheckedPreBillDet(" + JSON.stringify(cashmemodet) + ")'>";
			var chkbox = "<td><input id='" + cashmemodet.itemUniqueKey + "_modretcheck' class='chkboxcheked' type='checkbox' onclick='getCheckedPreBillDet(" + JSON.stringify(cashmemodet) + ")' value='" + JSON.stringify(cashmemodet) + "'> </td>";
			var name = "<td id='presaletabname'>" + cashmemodet.itemName + "</td>";
			var batchno = "<td id='presaletabbat'>" + cashmemodet.batchNo + "</td>";
			var expiryDateFormat = "<td id='presaletabexpdt'>" + cashmemodet.expiryDateFormat + "</td>";
			//var manfacname = "<td id='saletabmanname'>" + cashmemodet.manufacturerName + "</td>";
			var packQty = "<td id='presaletabpqty'>" + cashmemodet.packQty + "</td>";
			var looseQty = "<td id='presaletablqty'>" + cashmemodet.looseQty + "</td>";
			//var conversion = "<td id='saletabconv'>" + cashmemodet.conversion + "</td>";
			var mrpperunit = "<td id='presaletabmrppack'>" + parseFloat(cashmemodet.mrpPerUnit).toFixed(4) + "</td>";
			//	var mrp = "<td id='saletabmrp'>" + parseFloat(cashmemodet.mrp).toFixed(4) + "</td>";
			var rateperunit = "<td id='presaletabrateperunit' >" + parseFloat(cashmemodet.ratePerUnit).toFixed(4) + "</td>";
			/*var vatperc = "<td id='saletabvatperc' class='hide'>" + parseFloat(cashmemodet.vatPer).toFixed(4) + "</td>";
			var discperc = "<td id='saletabdiscperc'>" + parseFloat(cashmemodet.discPer).toFixed(4) + "</td>";
			var totamt = "<td id='saletabtotamt'>" + parseFloat(cashmemodet.totAmount).toFixed(4) + "</td>";
			var rowdelete = "<td><button class='btn btn-theme04 btn-xs' id='" + cashmemodet.itemUniqueKey + "' onclick='javascript:showSelTabItemDelModal(this.id);'><i class='fa fa-trash-o '></i></button></td>";
			var punitid = "<td id='saletabpunitid' class='hide'>" + cashmemodet.packUnitId + "</td>";
			var lunitid = "<td id='saletablunitid' class='hide'>" + cashmemodet.looseUnitId + "</td>";
			var content = "<td id='saletabcontent' class='hide'>" + cashmemodet.contentName + "</td>";
			var vatamt = "<td id='saletabvat' class='hide'>" + cashmemodet.vat + "</td>";
			var discamt = "<td id='saletabdisc' class='hide'>" + cashmemodet.disc + "</td>";
			var stockedqty = "<td id='saletabitemstkqty' class='hide'>" + cashmemodet.disc + "</td>";
			var mrpperunit = "<td id='saletabmrpperunit' class='hide'>" + parseFloat(cashmemodet.mrpPerUnit).toFixed(4) + "</td>";
			var rate = "<td id='saletabrate' class='hide'>" + cashmemodet.ratePerUnit + "</td>";
			var schename = "<td id='saletabsche' class='hide'>" + cashmemodet.scheduleName + "</td>";*/
			var endtrline = "</tr>";

			//			createdrowline = starttrline + name + batchno + expiryDateFormat + manfacname + packQty + looseQty + conversion + mrppack + mrp + rateperunit + vatperc + vatamt + discperc + discamt + totamt + rowdelete + punitid + lunitid + content + stockedqty + mrpperunit + rate + schename + endtrline;
			//			$("#saletabitemdetails").append(createdrowline);
			createdrowline = starttrline + name + packQty + looseQty + endtrline;
			$("#searchmodtbody").append(createdrowline);
			generatedHtml += createdrowline;
		}
		generatedHtml += tableend;

		/*clearHeaderDiv();
		calculateTotalMRP();
		calculateTotalVat();
		calculateTotalDisc();
		calculateNetTotal();*/

		var unique_id = $.gritter.add({
			// (string | mandatory) the heading of the notification
			title : "Sale Details for " + saleinvno + "",
			// (string | mandatory) the text inside the notification
			text : '' + generatedHtml + '',
			// (string | optional) the image to display on the left
			//image: 'assets/img/ui-sam.jpg',
			// (bool | optional) if you want it to fade out on its own or just sit there
			sticky : true,
			// (int | optional) the time you want it to be alive for before fading out
			time : '',
			// (string | optional) the class name you want to apply to that specific message
			class_name : 'my-sticky-class'
		});
		var curid = unique_id - 1;
		if (curid > 0) {
			$.gritter.remove(curid, {});
		}

	});

}

function getCheckedPreBillDet(cashmemodet) {
	var itemid = (cashmemodet.itemUniqueKey).split("_")[0];
	//alert(itemid);
	$("#item_name").val(cashmemodet.itemName);
	getItemDetails(itemid);
}

function saveOrUpdateSaleInv() {

	if ($("#item_id").val() == 0 || $("#item_id").val() == "") {
		if (isNaN($("#tenderamt").val())) {
			//			$("#confirmval").val(0);
			document.getElementById('alertmessagecont').innerHTML = getFieldText.numberCheck + " in Tender Amount";
			//showConfirmModal();
			return false;
		} else {
			document.getElementById('alertmessagecont').innerHTML = "";
		}

		if (isNaN($("#cashAmt").val())) {
			//			$("#confirmval").val(0);
			document.getElementById('alertmessagecont').innerHTML = getFieldText.numberCheck + " in Cash Amount";
			//showConfirmModal();
			return false;
		} else {
			document.getElementById('alertmessagecont').innerHTML = "";
		}

		if (isNaN($("#cardExpMod").val())) {
			//			$("#confirmval").val(0);
			document.getElementById('alertmessagecont').innerHTML = getFieldText.numberCheck + " in Card Expiry";
			//showConfirmModal();
			return false;
		} else {
			document.getElementById('alertmessagecont').innerHTML = "";
		}

		if (isNaN($("#cardFourDigitMod").val())) {
			//			$("#confirmval").val(0);
			document.getElementById('alertmessagecont').innerHTML = getFieldText.numberCheck + " in Card Four Digit";
			//showConfirmModal();
			return false;
		} else {
			document.getElementById('alertmessagecont').innerHTML = "";
		}

		var saleid = $("#saleId").val();
		var sale = {};
		var allsaledetails = [];
		var allsalereturndetails = [];
		var selitemlength = $('#selitem >tbody >tr').length;
		$('#selitem > tbody  > tr').each(function() {
			var saledetails = {};
			var itemid = this.id;
			var totlooseqty = (parseFloat($(this).find("#saletabpqty").text()) * parseFloat($(this).find("#saletabconv").text())) + parseFloat($(this).find("#saletablqty").text());
			saledetails.itemId = itemid.split("_")[0];
			saledetails.batchNo = $(this).find("#saletabbat").text();
			saledetails.expiryDateFormat = $(this).find("#saletabexpdt").text();
			saledetails.packUnitId = $(this).find("#saletabpunitid").text();
			saledetails.packQty = $(this).find("#saletabpqty").text();
			saledetails.looseQty = $(this).find("#saletablqty").text();
			saledetails.looseUnitId = $(this).find("#saletablunitid").text();
			saledetails.conversion = $(this).find("#saletabconv").text();
			saledetails.mrp = $(this).find("#saletabmrppack").text();
			saledetails.amount = $(this).find("#saletabamt").text();
			saledetails.itemTotalMrp = $(this).find("#saletabmrp").text();
			saledetails.mrpPerUnit = $(this).find("#saletabmrpperunit").text();// parseFloat(parseFloat($(this).find("#saletabmrppack").text())/ parseFloat($(this).find("#saletabconv").text())).toFixed(2);
			saledetails.ratePerUnit = $(this).find("#saletabrateperunit").text();
			saledetails.rate = $(this).find("#saletabitemsalerate").text();// $(this).find("#saletabrateperunit").text() * parseFloat($(this).find("#saletabconv").text());
			saledetails.vat = 0;// $(this).find("#saletabvat").text();
			saledetails.vatPer = $(this).find("#saletabvatperc").text();
			saledetails.tax = 0;//$(this).find("#saletabtax").text();
			saledetails.taxPer = $(this).find("#saletabtaxperc").text();
			saledetails.disc = $(this).find("#saletabdisc").text();
			saledetails.discPer = $(this).find("#saletabdiscperc").text();
			saledetails.totAmount = $(this).find("#saletabtotamt").text();
			saledetails.taxId = $(this).find("#saletabtaxId").text();
			saledetails.taxPercentage = $(this).find("#saletabtaxPercentage").text();
			saledetails.taxAmount = $(this).find("#saletabitemcalcgstamt").text();
			saledetails.isGroupTax = $(this).find("#saletabisGroupTax").text();
			saledetails.taxMode = $(this).find("#saletabitemtaxmode").text();
			saledetails.purchaseCostPerUnit = $(this).find("#saletabpurcostperunit").text();
			saledetails.saleRate = $(this).find("#saletabitemsalerate").text();
			saledetails.isExclusive = $("#isexclusive").val();
			allsaledetails.push(saledetails);
		});
		sale.salesDetails = allsaledetails;
		sale.id = saleid;
		
		//for same page return
		var cval = $("#confirmvalrsp").val();
//alert("cval="+cval);
		if (cval == 0) {

		} else {
			var cc = cval.split(",");
			for ( var i = 0; i < cc.length; i++) {
				console.log("ret id=" + cc[i]);
				//alert("ret id=" + cc[i]);
				var CommonResultSetMapper = {};
				CommonResultSetMapper.saleReturnId = cc[i];
				var ajaxCallObjectret = new CustomBrowserXMLObject();
				ajaxCallObjectret.callAjaxPost(BASE_URL + "/retunmemo/getsaleretdetbyid.htm", CommonResultSetMapper, function(res) {
				//	alert("res=" + res);
					var retdetails = JSON.parse(res);
					var salereturndetailssamepage = {};
					salereturndetailssamepage.id = retdetails.saleReturnId;
					salereturndetailssamepage.invNo = retdetails.invNo;
					salereturndetailssamepage.invDate = moment(retdetails.invDate).format('YYYY-MM-DD');// invdate
					salereturndetailssamepage.adjAmount = retdetails.netAmount;// adjustment amt
					allsalereturndetails.push(salereturndetailssamepage);
				});
			}
			
		}
		
		if (saleid == 0) {// create

		} else {// edit
			sale.invNo = $("#saleinvno").val();
		}

		sale.invDate = $("#date").val();
		if ($("#salecustname").val() == "") {
			sale.customerId = 0;
		} else {
			sale.customerId = $("#salecustid").val();
			sale.customerName = $("#salecustname").val();
			if ($("#salecustph").val() == "") {
				sale.customerPhone = 0000000000;
			} else {
				sale.customerPhone = $("#salecustph").val();
			}
			if ($("#salecustaddr").val() == "") {
				sale.customerAddress = "";
			} else {
				sale.customerAddress = $("#salecustaddr").val();
			}

		}

		if ($("#saledocname").val() == "") {
			sale.doctorId = 0;
		} else {
			sale.doctorId = $("#saledocid").val();
			sale.doctorName = $("#saledocname").val();
		}
		sale.grossAmount = $("#totgrossamt").val();
		sale.totalMrp = $("#totmrpamt").val();
		sale.vatAmount = 0;//$("#totvatamt").val();
		sale.taxAmount = $("#totgstamt").val();// $("#tottaxamt").val();
		sale.discAmount = $("#totdiscamt").val();
		sale.roundoff = $("#roundoff").val();
		sale.netAmount = $("#nettot").val();
		sale.remarks = $("#remarks").val();
		sale.creditAmount = 0.00;
		if ($("#spcldiscperc").val() == "") {
			sale.specialDiscPer = 0.00;
		} else {
			sale.specialDiscPer = $("#spcldiscperc").val();
		}

		sale.specialDiscAmount = $("#spcldisc").val();
		if ($("#isesi").val() == 1) {
			sale.esiType = $("#esitypeid").val();
			sale.prescriptionIssueDate = $("#pissuedt").val();
			sale.slipNo = $("#reqslno").val();
			sale.esiCode = $("#pcodeid").val();
			sale.prescriptionRegNo = $("#prregno").val();
		}
		/*sale.tenderAmount = $("#tenderamt").val();
		sale.cashAmount = $("#cashAmt").val();
		sale.cardAmount = $("#cardAmt").val();
		sale.cardExpDate =$("#cardExpMod").val();
		sale.cardFourDigit =$("#cardFourDigitMod").val();*/
		sale.holdFlag = 0;// 1 for pay 0 for hold
		if ($('input[name=payOption]').is(":checked")) {
			sale.invMode = $("#payOption").val();

		}
		
		
		if (selitemlength > 0) {
			$('#pleasewaitModal').modal('show');
			setTimeout(function() {
				sale.saleReturns = allsalereturndetails;
				console.log("sale json: " + JSON.stringify(sale));
				var ajaxCallObject = new CustomBrowserXMLObject();
				ajaxCallObject.callAjaxPost(BASE_URL + "/pos/createorupdatesalesinvoice.htm", sale, function(response) {
					$('#pleasewaitModal').modal('hide');
					//console.log("save sale inv id=" + response);
					if (response == '0') {
						document.getElementById('confirmmessagecont').innerHTML = getCashMemoText.dataNotAdd;
						$("#confirmval").val(0);
						showConfirmModal();
					} else {
						document.getElementById('confirmmessagecont').innerHTML = getCashMemoText.dataSucAdd;
					//	$("#confirmval").val(response); commented for same page return issue
						$("#confirmval").val(0);
						showConfirmModal();
						location.href = BASE_URL + "/reprintmemo/cashmemo.htm?reprint=N&backUrl=cashmemo&saleId=3465fg-trw73sxz-" + response + "-utew09-qdd55-4320jhhgrt";
					}

				});
			}, 1000);
		}
	} else {
		$("#confirmval").val(-1);
		document.getElementById('confirmmessagecont').innerHTML = getCashMemoText.addEditChckBefrSave;
		showConfirmModal();
	}
}
function saveCashmemo() {
	/*if ($("#salecustname").val() == "" || $("#saledocname").val() == "") {
		$("#saveWithoutCustAndDocNameModal").modal("show");
	} else {
		var s = $("#retamtvalsamepage").val();
		if ($("#retamtvalsamepage").val() == undefined || $("#retamtvalsamepage").val() == "") {
			s = 0;
		}
		if ($("#retamtsamepage").val() == 0) {
			if (Number($("#nettot").val()) > Number(s)) {
				saveOrUpdateSaleInv();
			} else {
				$("#samepageretvalgreater_rsp").modal("show");
			}
		} else {
			$("#samepageretnotsave_rsp").modal("show");
		}
	}*/
	
	var s = $("#retamtvalsamepage").val();
	if ($("#retamtvalsamepage").val() == undefined || $("#retamtvalsamepage").val() == "") {
		s = 0;
	}
	if ($("#retamtsamepage").val() == 0) {
		if (Number($("#nettot").val()) > Number(s)) {
			if ($("#salecustname").val() == "" || $("#saledocname").val() == "") {
				$("#saveWithoutCustAndDocNameModal").modal("show");
			} else {
			saveOrUpdateSaleInv();
			}
		} else {
			$("#samepageretvalgreater_rsp").modal("show");
		}
	} else {
		$("#samepageretnotsave_rsp").modal("show");
	}
}
function paySaleInvModal1() {
	//alert("hi");
	$("#payModal").modal("show");
}
function paySaleInvModal() {
	//$("#creditAmt").val($("#paymodnettot").val());
	var s = $("#retamtvalsamepage").val();
	//alert("s="+s);
	if ($("#retamtvalsamepage").val() == undefined || $("#retamtvalsamepage").val() == "") {
		s = 0;
	}
	if ($("#retamtsamepage").val() == 0) {
		if (Number($("#nettot").val()) > Number(s)) {

			var creditcustomer = $("#salecustid").val();
			if (creditcustomer == 0) // check credit customer or not
			{
				$("#payretadjamt").val(parseFloat(s).toFixed(2));
//				$("#creditAmt").val(parseFloat($("#paymodnettot").val()-s).toFixed(2));
				//$("#creditAmt").val($("#paymodnettot").val());
				$("#creditAmt").val(0);
				$("#creditLimit").val(0.0);
				$('#notCreditCustDiv').removeClass("hide");
				$('#creditCustDiv').addClass("hide");
				
				var returnadjasment=$("#payretadjamt").val();
				if (returnadjasment>0) {
					$("#tenderamt").val(parseFloat($("#paymodnettot").val()-returnadjasment).toFixed(2));
					$("#cashAmt").val(parseFloat($("#paymodnettot").val()-returnadjasment).toFixed(2));
				}else
				{	
					$("#tenderamt").val(parseFloat($("#paymodnettot").val()).toFixed(2));
					$("#cashAmt").val(parseFloat($("#paymodnettot").val()).toFixed(2));

				}
				
			} else {
				calculateTotalMRP();
				calculateTotalamt();
				calculateTotalVat();
				calculateTotalTax();
				calculateTotalDisc();
				calculateNetTotal();
				//calculateSpclDisc();
				calculateTotalGSTamt();
				calculateTotalPurchaseamt();
				//$("#creditAmt").val($("#paymodnettot").val());
				$("#payretadjamt").val(parseFloat(s).toFixed(2));
				$("#creditAmt").val(parseFloat($("#paymodnettot").val()-s).toFixed(2));
				$("#creditLimit").val(parseFloat($("#custCreditLimit").val()).toFixed(2));
				$('#notCreditCustDiv').addClass("hide");
				$('#creditCustDiv').removeClass("hide");
			}
			
			// add new start
			var customarEmail =  $("#custEmail").val();
			if(customarEmail == ""){
				$('#mailOptionDiv').addClass("hide");
			}else{
				$('#mailOptionDiv').removeClass("hide");
				
			}
			// add new end
			
			$("#payModal").modal("show");
			if ($("#salecustname").val() == "" || $("#saledocname").val() == "") {
				$("#docorcus").text("without doctor or customer details");
			} else {
				$("#docorcus").text("");
			}
		} else {
			$("#samepageretvalgreater_rsp").modal("show");
		}
	} else {
		
		$("#samepageretnotsave_rsp").modal("show");
	}
	
	if(isAccount == 1) {
		getvendorledger_sale($('#dutiesandtax_code1').val(),0,0,0);// for duties and tax
		getvendorledger_sale($('#roundof_code1').val(),0,0,1);// for round off  
		getvendorledger_sale($('#saleac_code1').val(),0,0,2);// for sale account
		getvendorledger_sale($('#debitor_code1').val(),0,$('#salecustid').val(),3);// for sunndry creditor 
		getvendorledger_sale($('#dicount_code1').val(),0,0,4);// for sunndry debitor
		getvendorledger_sale($('#cash_code1').val(),0,0,5);// for sunndry debitor as cash
		getvendorledger_sale($('#card_code1').val(),0,0,6);// for sunndry debitor as cash
	}
	
}

function updateSaleInv() {
	var saleid = $("#saleId").val();
	var sale = {};
	var selitemlength = $('#selitem >tbody >tr').length;
	sale.id = saleid;
	if (saleid == 0) {// create

	} else {// edit
		sale.invNo = $("#saleinvno").val();
	}

	sale.invDate = $("#date").val();
	sale.customerDiscPer = $("#salediscount").val();
	if ($("#salecustname").val() == "") {
		sale.customerId = 0;
	} else {
		sale.customerId = $("#salecustid").val();
		sale.customerName = $("#salecustname").val();
		if ($("#salecustph").val() == "") {
			sale.customerPhone = 0000000000;
		} else {
			sale.customerPhone = $("#salecustph").val();
		}
		if ($("#salecustaddr").val() == "") {
			sale.customerAddress = "";
		} else {
			sale.customerAddress = $("#salecustaddr").val();
		}
	}
	if ($("#saledocname").val() == "") {
		sale.doctorId = 0;
	} else {
		sale.doctorId = $("#saledocid").val();
		sale.doctorName = $("#saledocname").val();
	}
	
	if (selitemlength > 0) {
		$("#payModal").modal("hide");
		$('#pleasewaitModal').modal('show');
		setTimeout(function() {
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/pos/createorupdatesalesinvoice.htm", sale, function(response) {
				$('#pleasewaitModal').modal('hide');
				//				console.log("save sale inv id=" + response);
				if (response == '0') {
					document.getElementById('confirmmessagecont').innerHTML = getCashMemoText.dataNotPaid;
					$("#confirmval").val(0);
					showConfirmModal();
				} else {

					if ($('input[name=printInv]').is(":checked")) {
						if (dotMatrixPrint == 1) {
							$('#pleasewaitModal').modal('show');
							var CommonResultSetMapper = {};
							CommonResultSetMapper.saleId = response;
							CommonResultSetMapper.noteLineOne = n2;
							CommonResultSetMapper.noteLineTwo = n1;
							CommonResultSetMapper.isRePrint = 0;
							var ajaxCallObject = new CustomBrowserXMLObject();
							ajaxCallObject.callAjaxPost(BASE_URL + "/pos/salebillprint.htm", CommonResultSetMapper, function(res) {
								$('#pleasewaitModal').modal('hide');
								location.href = BASE_URL + "/pos/cashmemo.htm";

							});

						} else {
							location.href = BASE_URL + "/reprintmemo/cashmemo.htm?reprint=N&backUrl=cashmemo&saleId=3465fg-trw73sxz-" + response + "-utew09-qdd55-4320jhhgrt";
						}

					} else {
						document.getElementById('confirmmessagecont').innerHTML = getCashMemoText.dataSucPaid;
						$("#confirmval").val(response);
						showConfirmModal();
					}
					}
	
				});
			}, 300);
		}
}

function paySaleInv() {
	document.getElementById('alertmessagecont').innerHTML = "";
	var notvallid = 1;
	if(parseFloat($("#paymodnettot").val()) != (parseFloat($("#creditAmt").val())+parseFloat($("#cashAmt").val())+parseFloat($("#cardAmt").val()) + parseFloat($("#payretadjamt").val()))) {
		document.getElementById('alertmessagecont').innerHTML = "Please pay the exact bill amount.";
		notvallid = 0;
		return false;
	}
	if ($("#item_id").val() == 0 || $("#item_id").val() == "") {
		if (isNaN($("#tenderamt").val())) {
			//			$("#confirmval").val(0);
			document.getElementById('alertmessagecont').innerHTML = getFieldText.numberCheck + " in Tender Amount";
			//showConfirmModal();
			notvallid = 0;
			return false;
		} else {
			document.getElementById('alertmessagecont').innerHTML = "";
		}

		if (isNaN($("#cashAmt").val())) {
			//			$("#confirmval").val(0);
			document.getElementById('alertmessagecont').innerHTML = getFieldText.numberCheck + " in Cash Amount";
			//showConfirmModal();
			notvallid = 0;
			return false;
		} else {
			document.getElementById('alertmessagecont').innerHTML = "";
		}

		/*if (isNaN($("#cardExpMod").val())) {
			//			$("#confirmval").val(0);
			document.getElementById('alertmessagecont').innerHTML = getFieldText.numberCheck + " in Card Four Digit";
			//showConfirmModal();
			notvallid = 0;
			return false;
		} else {
			document.getElementById('alertmessagecont').innerHTML = "";
		}*/

		if (isNaN($("#cardFourDigitMod").val())) {
			//			$("#confirmval").val(0);
			document.getElementById('alertmessagecont').innerHTML = getFieldText.numberCheck + " in Card Four Digit";
			//showConfirmModal();
			notvallid = 0;
			return false;
		} else {
			document.getElementById('alertmessagecont').innerHTML = "";
		}
		var creditAmt = $("#creditAmt").val().trim();

		var creditcustomer = $("#salecustid").val();
		//if(creditcustomer==0) {   // check credit customer or not
		if (creditAmt > 0) {
			if (creditcustomer == 0) // check credit customer or not
			{
				document.getElementById('alertmessagecont').innerHTML = getCashMemoText.billAmtLessCashCardError;
				notvallid = 0;
				return false;
			} else {
				if (Number(creditAmt) > Number($("#creditLimit").val())) {
					document.getElementById('alertmessagecont').innerHTML = getCashMemoText.custCreditAmtCheck;
					notvallid = 0;
					return false;
				} else {
					document.getElementById('alertmessagecont').innerHTML = "";

				}
			}

		} else {

			//document.getElementById('alertmessagecont').innerHTML = ""; previous
			if (Number(creditAmt) > Number($("#creditLimit").val())) {
				document.getElementById('alertmessagecont').innerHTML = getCashMemoText.custCreditAmtCheck;
				notvallid = 0;
				return false;
			} else {
				document.getElementById('alertmessagecont').innerHTML = "";

			}
		}
		/*}else{
			if($("#creditAmt").val() > $("#creditLimit").val())
			{
				document.getElementById('alertmessagecont').innerHTML = getCashMemoText.custCreditAmtCheck;
				notvallid = 0;
				return false;
			}
			else
			{
				document.getElementById('alertmessagecont').innerHTML = "";
			}
		}*/
		var saleid = $("#saleId").val();
		var sale = {};
		var allsaledetails = [];
		var allsalereturndetails = [];
		var selitemlength = $('#selitem >tbody >tr').length;
		$('#selitem > tbody  > tr').each(function() {
			var saledetails = {};
			var itemid = this.id;
			var totlooseqty = (parseFloat($(this).find("#saletabpqty").text()) * parseFloat($(this).find("#saletabconv").text())) + parseFloat($(this).find("#saletablqty").text());
			saledetails.itemId = itemid.split("_")[0];
			saledetails.batchNo = $(this).find("#saletabbat").text();
			saledetails.expiryDateFormat = $(this).find("#saletabexpdt").text();
			saledetails.packUnitId = $(this).find("#saletabpunitid").text();
			saledetails.packQty = $(this).find("#saletabpqty").text();
			saledetails.looseQty = $(this).find("#saletablqty").text();
			saledetails.looseUnitId = $(this).find("#saletablunitid").text();
			saledetails.conversion = $(this).find("#saletabconv").text();
			saledetails.mrp = $(this).find("#saletabmrppack").text();
			saledetails.amount = $(this).find("#saletabamt").text();
			saledetails.itemTotalMrp = $(this).find("#saletabmrp").text();
			saledetails.mrpPerUnit = $(this).find("#saletabmrpperunit").text();// parseFloat(parseFloat($(this).find("#saletabmrppack").text())/ parseFloat($(this).find("#saletabconv").text())).toFixed(2);
			saledetails.ratePerUnit = $(this).find("#saletabrateperunit").text();
			saledetails.rate = $(this).find("#saletabitemsalerate").text();//$(this).find("#saletabrateperunit").text() * parseFloat($(this).find("#saletabconv").text());
			saledetails.vat = 0;// $(this).find("#saletabvat").text();
			saledetails.vatPer = $(this).find("#saletabvatperc").text();
			saledetails.tax = 0;//$(this).find("#saletabtax").text();
			saledetails.taxPer = $(this).find("#saletabtaxperc").text();
			saledetails.disc = $(this).find("#saletabdisc").text();
			saledetails.discPer = $(this).find("#saletabdiscperc").text();
			saledetails.totAmount = $(this).find("#saletabtotamt").text();
			saledetails.taxId = $(this).find("#saletabtaxId").text();
			saledetails.taxPercentage = $(this).find("#saletabtaxPercentage").text();
			saledetails.taxAmount = $(this).find("#saletabitemcalcgstamt").text();
			saledetails.isGroupTax = $(this).find("#saletabisGroupTax").text();
			saledetails.taxMode = $(this).find("#saletabitemtaxmode").text();
			saledetails.purchaseCostPerUnit = $(this).find("#saletabpurcostperunit").text();
			saledetails.saleRate = $(this).find("#saletabitemsalerate").text();
			saledetails.isExclusive = $("#isexclusive").val();
			allsaledetails.push(saledetails);
		});
		sale.salesDetails = allsaledetails;
		sale.id = saleid;
		if (saleid == 0) {// create

		} else {// edit
			sale.invNo = $("#saleinvno").val();
		}

		sale.invDate = $("#date").val();
		sale.customerDiscPer = $("#salediscount").val();
		if ($("#salecustname").val() == "") {
			sale.customerId = 0;
			//added on 18.10.2019
			if ($("#salecustph").val() == "") {
				sale.customerPhone = 0000000000;
			} else {
				sale.customerPhone = $("#salecustph").val();
			}
			if ($("#salecustaddr").val() == "") {
				sale.customerAddress = "";
			} else {
				sale.customerAddress = $("#salecustaddr").val();
			}
			//end
		} else {
			sale.customerId = $("#salecustid").val();
			sale.customerName = $("#salecustname").val();
			if ($("#salecustph").val() == "") {
				sale.customerPhone = 0000000000;
			} else {
				sale.customerPhone = $("#salecustph").val();
			}
			if ($("#salecustaddr").val() == "") {
				sale.customerAddress = "";
			} else {
				sale.customerAddress = $("#salecustaddr").val();
			}
		}
		if ($("#saledocname").val() == "") {
			sale.doctorId = 0;
		} else {
			sale.doctorId = $("#saledocid").val();
			sale.doctorName = $("#saledocname").val();
		}
		sale.grossAmount = $("#totgrossamt").val();
		sale.totalMrp = $("#totmrpamt").val();
		sale.vatAmount = 0;//$("#totvatamt").val();
		sale.taxAmount = $("#totgstamt").val();// $("#tottaxamt").val();
		sale.discAmount = $("#totdiscamt").val();
		sale.roundoff = $("#roundoff").val();
		sale.netAmount = $("#nettot").val();
		sale.remarks = $("#remarks").val();
		if ($("#spcldiscperc").val() == "") {
			sale.specialDiscPer = 0.00;
		} else {
			sale.specialDiscPer = $("#spcldiscperc").val();
		}
		sale.specialDiscAmount = $("#spcldisc").val();
		sale.tenderAmount = $("#tenderamt").val();
		sale.cashAmount = $("#cashAmt").val();
		sale.creditAmount = $("#creditAmt").val();
		sale.cardAmount = $("#cardAmt").val();
		sale.cardExpDate = $("#cardExpMod").val();
		sale.cardFourDigit = $("#cardFourDigitMod").val();
		if ($("#isesi").val() == 1) {
			sale.esiType = $("#esitypeid").val();
			sale.prescriptionIssueDate = $("#pissuedt").val();
			sale.slipNo = $("#reqslno").val();
			sale.esiCode = $("#pcodeid").val();
			sale.prescriptionRegNo = $("#prregno").val();
		}
		sale.printCount = $("#printInv").is(":checked") ? 1 : 0;
		sale.holdFlag = 1;// 1 for pay 0 for hold
		if ($('input[name=payOption]').is(":checked")) {
			//			console.log($("input[name=payOption]:checked").val());
			sale.invMode = $("#payOption").val();

		}
		// ret adj amt cal
		sale.adjAmount = $("#payretadjamt").val();
		//for same page return
		var cval = $("#confirmvalrsp").val();

		if (cval == 0) {

		} else {
			var cc = cval.split(",");
			for ( var i = 0; i < cc.length; i++) {
				console.log("ret id=" + cc[i]);
				var CommonResultSetMapper = {};
				CommonResultSetMapper.saleReturnId = cc[i];
				var ajaxCallObjectret = new CustomBrowserXMLObject();
				ajaxCallObjectret.callAjaxPost(BASE_URL + "/retunmemo/getsaleretdetbyid.htm", CommonResultSetMapper, function(res) {
					var retdetails = JSON.parse(res);
					var salereturndetailssamepage = {};
					salereturndetailssamepage.id = retdetails.saleReturnId;
					salereturndetailssamepage.invNo = retdetails.invNo;
					salereturndetailssamepage.invDate = moment(retdetails.invDate).format('YYYY-MM-DD');// invdate
					salereturndetailssamepage.adjAmount = retdetails.netAmount;// adjustment amt
					allsalereturndetails.push(salereturndetailssamepage);
				});
			}
		}
		//var selretitemlength = $('#retadjtable >tbody >tr').length;
		$('#retadjtable > tbody  > tr').each(function() {
			var salereturndetails = {};
			var saleretid = this.id;
			var invno = $(this).find("td:eq(0)").text();
			salereturndetails.id = saleretid;
			salereturndetails.invNo = invno;
			salereturndetails.invDate = $(this).find("td:eq(1)").text();// invdate
			salereturndetails.adjAmount = $(this).find("td:eq(4)").text();// adjustment amt
			allsalereturndetails.push(salereturndetails);
		});

		sale.saleReturns = allsalereturndetails;
		
		sale.duties_ledger_id= $('#duties_ledger_id1').val();
		sale.round_ledger_id= $('#round_ledger_id1').val();
		sale.sale_ledger_id= $('#sales_ledger_id1').val();
		sale.discount_ledger_id= $('#discount_ledger_id1').val();
		sale.debitor_ledger_id= $('#debitor_ledger_id1').val();
		sale.debitor_cash_ledger_id=$('#debitor_cahs_ledger_id1').val();
		sale.card_ledger_id=$('#card_ledger_id1').val();
		
		/*setTimeout(function() {
			console.log("sale json: " + JSON.stringify(sale));
		}, 200);*/

		if (selitemlength > 0 && notvallid > 0) {
			$("#payModal").modal("hide");
			$('#pleasewaitModal').modal('show');
			setTimeout(function() {
				var ajaxCallObject = new CustomBrowserXMLObject();
				ajaxCallObject.callAjaxPost(BASE_URL + "/pos/createorupdatesalesinvoice.htm", sale, function(response) {
					$('#pleasewaitModal').modal('hide');
					//				console.log("save sale inv id=" + response);
					if (response == '0') {
						document.getElementById('confirmmessagecont').innerHTML = getCashMemoText.dataNotPaid;
						$("#confirmval").val(0);
						showConfirmModal();
					} else {
						
						if ($('input[name=emailInv]').is(":checked")) { // new added 10.5.2019
							sendInvEmail(response);
						} 
						
						if ($('input[name=printInv]').is(":checked")) {
							
							if (dotMatrixPrint == 1) { 
								$('#pleasewaitModal').modal('show');
								var CommonResultSetMapper = {};
								CommonResultSetMapper.saleId = response;
								CommonResultSetMapper.noteLineOne = n2;
								CommonResultSetMapper.noteLineTwo = n1;
								CommonResultSetMapper.isRePrint = 0;
								var ajaxCallObject = new CustomBrowserXMLObject();
								ajaxCallObject.callAjaxPost(BASE_URL + "/pos/salebillprint.htm", CommonResultSetMapper, function(res) {
									$('#pleasewaitModal').modal('hide');
									location.href = BASE_URL + "/pos/cashmemo.htm";

								});

							} else {
								location.href = BASE_URL + "/reprintmemo/cashmemo.htm?reprint=N&backUrl=cashmemo&saleId=3465fg-trw73sxz-" + response + "-utew09-qdd55-4320jhhgrt";
							}

						} else {
							document.getElementById('confirmmessagecont').innerHTML = getCashMemoText.dataSucPaid;
							$("#confirmval").val(response);
							showConfirmModal();
						}
					}

				});
			}, 300);
		}
	} else {
		$("#payModal").modal("hide");
		$("#confirmval").val(-1);
		document.getElementById('confirmmessagecont').innerHTML = getCashMemoText.addEditChckBefrSave;
		showConfirmModal();
	}
}

function getItemByContent(contentid) {
	var itemsbycontentidtbl = $('#itemsbycontentidtbl').DataTable();
	itemsbycontentidtbl.destroy();
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/item/getitemsbycontent/" + contentid + ".htm", function(resp) {

		$("#itemsbycontentid").text("");
		var itemsbycontent = JSON.parse(resp);
		//	alert(itemdetail.length);
		var totstkitm = 0;
		var totlooseitm = 0;
		var conv = 0;
		var totlooseqty = 0;
		if (itemsbycontent.length == 0) {
			$("#contentnotmatchdiv").removeClass("hide");
			$("#contentnotfoundname").text($("#content_Dets").val());
			$("#modsearchedcontent").text($("#content_Dets").val());
			$("#contentnotmatchid").val(contentid);
			$("#itemsearchtable").addClass("hide");

		} else {
			$("#contentnotmatchdiv").addClass("hide");
			$("#itemsearchtable").removeClass("hide");
			$("#modsearchedcontent").text($("#content_Dets").val());
			for ( var i = 0; i < itemsbycontent.length; i++) {
				var genericitem = itemsbycontent[i];
				var starttrline = "<tr id='" + genericitem.itemId + "' style='cursor: pointer;' onclick='getGenericItems(" + JSON.stringify(genericitem) + ")'>";
				var itemname = "<td >" + genericitem.itemName + "</td>";
				var endtrline = "</tr>";
				createdrowline = starttrline + itemname + endtrline;
				$("#itemsbycontentid").append(createdrowline);
			}

		}
		$("#itemsByContentModal").modal("show");

		$('#itemsbycontentidtbl').DataTable({
			"lengthChange" : false,
			"pagingType" : "full",
		});

	}, null);

}
/*function getItemByContent(contentid) {
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/item/getitemsbycontent/" + contentid + ".htm", function(resp) {
		var table = $('#itemsbycontentid_tbl').DataTable();
		table.clear().draw();
		var rowNode= new Array();
		$("#itemsbycontentid").text("");
		var itemsbycontent = JSON.parse(resp);
		//	alert(itemdetail.length);
		var totstkitm = 0;
		var totlooseitm = 0;
		var conv = 0;
		var totlooseqty = 0;
		if (itemsbycontent.length == 0) {
			$("#contentnotmatchdiv").removeClass("hide");
			$("#contentnotfoundname").text($("#itemContent").val());
			$("#contentnotmatchid").val(contentid);
			$("#itemsearchtable").addClass("hide");

		} else {
			$("#contentnotmatchdiv").addClass("hide");
			$("#itemsearchtable").removeClass("hide");
			
			
			for ( var i = 0; i < itemsbycontent.length; i++) {

				var j = -1;
	            var r = new Array();
				var itemnameid = itemsbycontent[i];
				r[++j] = "<tr id='" + itemnameid.itemId + "' onclick='selItem(" + itemnameid.itemId + ")'><td id='itemnamebycontent'>" + itemnameid.itemName + "</td></tr>";
				//var itemid = "<td class='hide'>" + itemnameid.itemId + "</td>";
				//r[++j] = "</tr>";
				//createdrowline = starttrline + itemname + itemid + endtrline;
				rowNode =table.row.add(r);
				//$("#itemsbycontentid").append(createdrowline);
			}
			rowNode.draw().node();
		}		
		$("#itemsByContentModal").modal("show");

	}, null);
}*/

function getGenericItems(genericitem) {
	$("#itemsByContentModal").modal("hide");
	$("#item_name").val(genericitem.itemName.split("::")[0]);
	getItemDetails(genericitem.itemId);
}

function getItemDetails(itemid) {
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/stock/getcurrstock/" + itemid + ".htm", function(resp) {
		console.log("data"+resp);
		createItemStockDetails(resp, itemid);
		$("#stkdetModal").modal("show");

	}, null);
}


function createItemStockDetails(itemstkdetails,
								itemid) {
	//	$("#snditmtopodiv").addClass("hide");
	$("#snditmpo").addClass("hide");
	//	console.log("itemstkdetails="+itemstkdetails);
	$("#itemdetails").text("");
	//$("#moditemname").text($("#item_name").val());
	var itemdetail = JSON.parse(itemstkdetails);
	//	alert(itemdetail.length);
	var totstkitm = 0;
	var totlooseitm = 0;
	var conv = 0;
	var totlooseqty = 0;
	var totloosereorderlevelqty = 0;
	$("#senditemtopoid").val(itemid);
	if (itemdetail.length == 0) {
		//		$("#snditmtopodiv").removeClass("hide");
		//		$("#snditmpo").addClass("hide");
		$("#itemnotfounddiv").removeClass("hide");
		$("#itemnotfoundname").text($("#item_name").val());
		$("#itemnotfoundid").val(itemid);
		$("#modtable").addClass("hide");

	} else {
		$("#itemnotfounddiv").addClass("hide");
		$("#modtable").removeClass("hide");
		selectedItemData = itemdetail[0]; // new added 10.6.2019
		for ( var i = 0; i < itemdetail.length; i++) {
			var itmstkdet = itemdetail[i];
			$("#moditemname").text(itmstkdet.itemName);
			// $("#item_name").text(itmstkdet.itemName);
			$("#modmanufname").text(itmstkdet.manufacturerName);
			$("#modcontentname").text(itmstkdet.contentName);
			$("#modrackname").text(itmstkdet.rackName);
			$("#modgroupname").text(itmstkdet.groupName);
			$("#moditemnote").text(itmstkdet.note);
			if (itmstkdet.stockQty == 0) {
				//				$("#snditmpo").addClass("hide");
				//				$("#snditmtopodiv").removeClass("hide");
				$("#itemnotfounddiv").removeClass("hide");
				$("#itemnotfoundname").text($("#item_name").val());
				$("#itemnotfoundid").val(itemid);
				$("#modtable").addClass("hide");
			} else {
				$("#itemnotfoundid").val(itemid);
				totstkitm = Number(totstkitm) + parseFloat(itmstkdet.stockQty);
				//			console.log("looseQty=" + parseFloat(itmstkdet.looseQty));
				totlooseitm = Number(totlooseitm) + parseFloat(itmstkdet.looseQty);
				totlooseqty = Number(totlooseqty) + parseFloat(itmstkdet.conversion * itmstkdet.packQty + Number(itmstkdet.looseQty));
				conv = parseFloat(itmstkdet.conversion);
				totloosereorderlevelqty = Number(itmstkdet.claculateLooseReorderLevelQty);
				var starttrline = "";
				var expiryDateFormat = "";
				if (itmstkdet.expiryStatusMode == 1) {
					starttrline = "<tr id=" + itmstkdet.itemUniqueKey + " class='schx'>";
					expiryDateFormat = "<td>" + itmstkdet.expiryDateFormat + "(" + itmstkdet.expiryStatus + ")</td>";
				} else {
					starttrline = "<tr id=" + itmstkdet.itemUniqueKey + "  style='cursor: pointer;' onclick='javascript:getClickeditmdet(" + JSON.stringify(itmstkdet) + ")' >";
					expiryDateFormat = "<td>" + itmstkdet.expiryDateFormat + "</td>";
				}
				var batchno = "<td>" + itmstkdet.batchNo + "</td>";

				var stockQty = "<td>" + itmstkdet.stockQty + "</td>";
				var holdQty = "<td>" + itmstkdet.holdQty + "</td>";
				var mrppack = "<td>" + parseFloat(itmstkdet.mrp).toFixed(2) + "</td>";
				var mrp = "<td>" + parseFloat(itmstkdet.mrp / itmstkdet.conversion).toFixed(2) + "</td>";
				var conversion = "<td>" + itmstkdet.conversion + "</td>";
				var packing = "<td>" + itmstkdet.netContent + "</td>";
				var purInvNo = "<td class='hide'>" + itmstkdet.purInvNo + "</td>";
				var purInvDate = "<td>" + itmstkdet.purInvDate + "</td>";
				var purInvDate = "<td class='hide'>" + moment(itmstkdet.purInvDate).format('YYYY-MM-DD') +  "</td>";
				var endtrline = "</tr>";
				createdrowline = starttrline + batchno + expiryDateFormat + stockQty + holdQty + mrppack + mrp + conversion + packing + purInvNo + purInvDate + endtrline;
				$("#itemdetails").append(createdrowline);
			}
			//		console.log("totlooseqty="+totlooseqty);
			//		$("#totalcurrstkitm").text(totstkitm + "/" + totlooseitm + " [ " + (totstkitm * conv + totlooseitm) + " ]");
			$("#totalcurrstkitm").text(totstkitm + "/" + totlooseitm + " [ " + (totlooseqty) + " ]");

			$("#itemstockdetails>tbody>tr:first").addClass('rowActive');
		}
		/*if (totlooseqty <= totloosereorderlevelqty) {
			$("#snditmtopodiv").removeClass("hide");
			$("#itemnotfoundid").val(itemid);
		} else {
			$("#snditmtopodiv").addClass("hide");
			$("#snditmpo").addClass("hide");
		}*/
	}

}

function selectRow(newRow,key) {
	// make sure the parameter is a jQuery object
	newRow = $(newRow);

	// exit early if we don't have a new row
	if (newRow.length == 0)
		return true;

	// unselect the old row
	var oldRow = $('.rowActive');
	oldRow.removeClass('rowActive');

	// select the new row
	newRow.addClass('rowActive');

	// keep track of the id
	//  $('.pane h2').text(newRow.attr('id'));

	// we could use location.hash = '#' + newRow.attr('id'), but instead we will do...

	// scrolling magic
	var rowTop = newRow.position().top;
	var rowBottom = rowTop + newRow.height();
	var $table = $('#itemstockdetails'); // store instead of calling twice
	var tableHeight = $table.height();
	var currentScroll = $table.scrollTop();
	var currentScrollDiv = $('#modtable').scrollTop();
	//alert("cur:"+currentScroll+":curDiv:"+currentScrollDiv+":rowTop:"+rowTop+":rowBottom:"+rowBottom+":tableHeight:"+tableHeight);
	if (rowTop < 0) {
		// scroll up
		$('#modtable').scrollTop(parseInt(currentScroll + rowTop));
	} else if (rowBottom > tableHeight) {
		// scroll down
		if(key=="down")
			{
			var scrollAmount = Number(rowBottom - tableHeight);
			$('#modtable').scrollTop(parseInt(currentScroll + scrollAmount));
			}
		else
			{
			$('#modtable').scrollTop(parseInt(currentScrollDiv - 25));
			}
	    
	}

	return false;
}

$('#stkdetModal').keydown(function(e) {
	var currentRow = $('.rowActive');
	if (e.keyCode == 40) {
		return selectRow(currentRow.next(),"down");
	}
	if (e.keyCode == 38) {
		return selectRow(currentRow.prev(),"up");
	}
	if (e.keyCode == 13) {
		currentRow.click();
	}
});

$('#stkdetModal').on('shown.bs.modal', function() {
	//$("#itemstockdetails>tbody>tr:first").addClass('rowActive');
});

function getClickeditmdet(clickitmdet) {
	//	var clickeditemdetail = JSON.parse(clickitmdet);
	//	$("#item_id").val(clickitmdet.itemId);
	$("#item_id").val(clickitmdet.itemUniqueKey);
	$("#item_name").val(clickitmdet.itemName);
	$("#item_barcode").val(clickitmdet.sku);
	$("#item_batch").val(clickitmdet.batchNo);
	$("#item_exp").val(clickitmdet.expiryDateFormat);
	$("#item_packunitid").val(clickitmdet.packUnitId);
	$("#item_looseunitid").val(clickitmdet.packUnitId);
	$("#item_stockedqty").val(clickitmdet.packQty);
	$("#item_mrp_pack").val(parseFloat(clickitmdet.mrp).toFixed(4));
	$("#item_mrp").val(parseFloat(clickitmdet.mrp / clickitmdet.conversion).toFixed(4));
	$("#item_rate_ls").val(parseFloat(clickitmdet.mrp / clickitmdet.conversion).toFixed(4));
	$("#item_rate_ls_hid").val(parseFloat(clickitmdet.mrp / clickitmdet.conversion).toFixed(4));
	$('#item_pqty').val(0);
	$('#item_lqty').val(0);
	var isdis = clickitmdet.isDiscount;
	if (isdis == 0) {
		$("#item_dis").val(parseFloat(0).toFixed(4));
		$("#item_dis_td").hide();
		$("#dis_label").hide();
		$("#item_discount").val(0);
	} else {
		$("#item_dis_td").show();
		$("#dis_label").show();
		$("#item_discount").val(clickitmdet.discount);
		$("#item_maxdisper").val(parseFloat(clickitmdet.maxDiscountLimit).toFixed(4));
	}
	//	$("#item_dis").val(parseFloat(0).toFixed(4));//clickitmdet.itemId
	$("#item_vat").val(parseFloat(clickitmdet.vatPer).toFixed(4));
	$("#item_tax").val(parseFloat(clickitmdet.taxPer).toFixed(4));
	$("#item_mfg").val(clickitmdet.manufacturerName);
	$('#item_mfg').prop('title', clickitmdet.manufacturerName);
	$("#item_content").val(clickitmdet.contentName);
	$("#itemContent").val(clickitmdet.contentName);
	$("#itemContent").val(clickitmdet.contentName);
	$("#content_Dets").val(clickitmdet.contentName);
	$('#item_content').prop('title', clickitmdet.contentName);
	$("#item_conv").val(clickitmdet.conversion);
	$("#item_sche").val(clickitmdet.scheduleName);
	$("#item_grp").val(clickitmdet.groupName);
	$("#item_taxId").val(clickitmdet.taxId);
	$("#item_taxPercentage").val(clickitmdet.taxPercentage);
	$("#item_isGroupTax").val(clickitmdet.isGroupTax);
	//	$("#item_dis").val(parseFloat(clickitmdet.discount).toFixed(4));
	//	$("#item_discount").val(clickitmdet.discount);
	$("#item_isDiscount").val(clickitmdet.isDiscount);
	$("#item_maxDiscountLimit").val(clickitmdet.maxDiscountLimit);
	$("#item_taxMode").val(clickitmdet.taxMode);
	$("#item_hsnCode").val(clickitmdet.hsnCode);
	$("#item_purCost").val(clickitmdet.purchaseCostPerUnit);
	$("#item_purCostperUnit").val(clickitmdet.purchaseCostPerUnit);
	var salerate = clickitmdet.saleRate;
	var isexclu = $("#isexclusive").val();
	//	alert(isexclu);
	if (salerate == 0) {
		if (isexclu == 1) {
			var mop = (100 * clickitmdet.mrp) / (100 + clickitmdet.taxPercentage);
			$("#item_sale_rate").val(parseFloat(mop).toFixed(4));
			$("#item_rate_ls").val(parseFloat(mop / clickitmdet.conversion).toFixed(4));
			$("#item_rate_ls_hid").val(parseFloat(mop / clickitmdet.conversion).toFixed(4));
		} else {
			$("#item_sale_rate").val(parseFloat(clickitmdet.mrp).toFixed(4));
		}

	} else {
		$("#item_sale_rate").val(parseFloat(clickitmdet.saleRate).toFixed(4));
		$("#item_rate_ls").val(parseFloat(clickitmdet.saleRate / clickitmdet.conversion).toFixed(4));
		$("#item_rate_ls_hid").val(parseFloat(clickitmdet.saleRate / clickitmdet.conversion).toFixed(4));
	}

	$("#item_purChase_rate").val(clickitmdet.purchaseRate);
	$("#stkdetModal").modal("hide");
}

function itemHeaderDivView(itemid) {
	$("#item_id").val(itemid);
	$('#item_name').prop('readonly', true);
	$('#item_barcode').prop('readonly', true);
	$('#itemContent').prop('readonly', true);
	$("#item_name").val($("#selitem tr#" + itemid).find('#saletabname').text());
	$("#item_barcode").val($("#selitem tr#" + itemid).find('#saletabitembarcode').text());
	$("#item_batch").val($("#selitem tr#" + itemid).find('#saletabbat').text());
	$("#item_exp").val($("#selitem tr#" + itemid).find('#saletabexpdt').text());
	$("#item_pqty").val($("#selitem tr#" + itemid).find('#saletabpqty').text());
	$("#item_packunitid").val($("#selitem tr#" + itemid).find('#saletabpunitid').text());
	$("#item_lqty").val($("#selitem tr#" + itemid).find('#saletablqty').text());
	$("#item_looseunitid").val($("#selitem tr#" + itemid).find('#saletablunitid').text());
	$("#item_mrp_pack").val($("#selitem tr#" + itemid).find('#saletabmrppack').text());
	$("#item_mrp").val($("#selitem tr#" + itemid).find('#saletabmrpperunit').text());
	$("#item_rate_ls").val($("#selitem tr#" + itemid).find('#saletabrateperunit').text());
	$("#item_rate_ls_hid").val($("#selitem tr#" + itemid).find('#saletabrate').text());
	$("#item_dis").val($("#selitem tr#" + itemid).find('#saletabdiscperc').text());
	$("#item_discamt").val($("#selitem tr#" + itemid).find('#saletabdisc').text());
	$("#item_vat").val($("#selitem tr#" + itemid).find('#saletabvatperc').text());
	$("#item_vatamt").val($("#selitem tr#" + itemid).find('#saletabvat').text());
	$("#item_tax").val($("#selitem tr#" + itemid).find('#saletabtaxperc').text());
	$("#item_taxamt").val($("#selitem tr#" + itemid).find('#saletabtax').text());
	$("#item_tot").val($("#selitem tr#" + itemid).find('#saletabtotamt').text());
	$("#item_mfg").val($("#selitem tr#" + itemid).find('#saletabmanname').text());
	$("#item_content").val($("#selitem tr#" + itemid).find('#saletabcontent').text());
	$("#item_conv").val($("#selitem tr#" + itemid).find('#saletabconv').text());
	$("#content_Dets").val($("#selitem tr#" + itemid).find('#saletabcontent').text());
	$("#itemContent").val($("#selitem tr#" + itemid).find('#saletabcontent').text());
	$("#content_id").val($("#selitem tr#" + itemid).find('#saletabcontentid').text());
	$("#item_taxId").val($("#selitem tr#" + itemid).find('#saletabtaxId').text());
	$("#item_taxPercentage").val($("#selitem tr#" + itemid).find('#saletabtaxPercentage').text());
	$("#item_isGroupTax").val($("#selitem tr#" + itemid).find('#saletabisGroupTax').text());
	$("#item_discount").val($("#selitem tr#" + itemid).find('#saletabdiscount').text());
	var isDis = $("#selitem tr#" + itemid).find('#saletabisDiscount').text();
	if (isDis == 0) {
		$("#item_dis").val(parseFloat(0).toFixed(4));
		$("#item_dis_td").hide();
		$("#dis_label").hide();
	} else {
		$("#item_dis_td").show();
		$("#dis_label").show();
		$("#item_isDiscount").val($("#selitem tr#" + itemid).find('#saletabisDiscount').text());
	}

	$("#item_maxDiscountLimit").val($("#selitem tr#" + itemid).find('#saletabmaxDiscountLimit').text());
	$("#item_CalcTaxAmt").val($("#selitem tr#" + itemid).find('#saletabitemcalcgstamt').text());
	$("#item_taxMode").val($("#selitem tr#" + itemid).find('#saletabitemtaxmode').text());
	$("#item_hsnCode").val($("#selitem tr#" + itemid).find('#saletabitemhsncode').text());
	$("#item_purCost").val($("#selitem tr#" + itemid).find('#saletabpurcost').text());
	$("#item_purCostperUnit").val($("#selitem tr#" + itemid).find('#saletabpurcostperunit').text());
	$("#item_sale_rate").val($("#selitem tr#" + itemid).find('#saletabitemsalerate').text());
	$("#item_purChase_rate").val($("#selitem tr#" + itemid).find('#saletabitempurrate').text());
	$("#add_btn").addClass("hide");
	$("#edit_btn").removeClass("hide");
}

function Validation() {
	var counter = 0;

	var pqty_field = $("#pqty_label").text();

	var lqty_field = $("#lqty_label").text();

	var sale_rate_label = $("#sale_rate_label").text();

	/*var field_names = [ [ "item_pqty", pqty_field ] ];

	if (fieldValidation(field_names) > 0) {
		counter = 1;
		return counter;
		return false;
	} else {
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}*/

	if (($("#item_pqty").val() == "") && ($("#item_lqty").val() == "")) {
		document.getElementById('alertMsg').innerHTML = pqty_field + " / " + lqty_field + " " + getFieldText.fieldempty;
		counter = 1;
		return counter;
		return false;
	} else {
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	if (($("#item_pqty").val() == 0) && ($("#item_lqty").val() == 0)) {
		document.getElementById('alertMsg').innerHTML = pqty_field + " / " + lqty_field + " " + getFieldText.fieldReq;
		counter = 1;
		return counter;
		return false;
	} else {
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	if (isNaN($("#item_pqty").val())) {
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck + " in " + pqty_field;
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	} else {
		if ($("#item_pqty").val() < 0) {
			document.getElementById('alertMsg').innerHTML = pqty_field + " " + getFieldText.checkNegative;
			$(this).focus();
			counter = 1;
			return counter;
			return false;
		} else {
			counter = 0;
			document.getElementById('alertMsg').innerHTML = "";
		}
	}

	if (isNaN($("#item_lqty").val())) {
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck + " in " + lqty_field;
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	} else {
		if ($("#item_lqty").val() < 0) {
			document.getElementById('alertMsg').innerHTML = lqty_field + " " + getFieldText.checkNegative;
			$(this).focus();
			counter = 1;
			return counter;
			return false;
		} else {
			counter = 0;
			document.getElementById('alertMsg').innerHTML = "";
		}
	}

	if (isNaN($("#item_dis").val())) {
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck + " in D%";
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	} else {
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	if ($("#item_sale_rate").val() == "" || $("#item_sale_rate").val() == 0 || isNaN($("#item_sale_rate").val())) {
		document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck + " in " + sale_rate_label;
		$(this).focus();
		counter = 1;
		return counter;
		return false;
	} else {
		counter = 0;
		document.getElementById('alertMsg').innerHTML = "";
	}

	return counter;
}

function addOrUpdateItemToDetailsTable(operation) {

	if (Validation() == 1) {
		return false;
	} else {
		document.getElementById('alertMsg').innerHTML = "";
	}

	//console.log("call");
	//$("#saletabitemdetails").text("");
	var itemid = $("#item_id").val();
	var batch = $('#item_batch').val();
	var preuniquekey = itemid + batch;
	if (Number(itemid) == 0) {
		return false;
	}
	//	console.log("itemid=" + itemid);
	//	console.log("batch=" + batch);
	console.log("preuniquekey=" + preuniquekey);
	var itempresent = 0;
	if (operation == 1) { //edit

	} else { // add
		$('#selitem > tbody  > tr').each(function() {
			/*console.log("tbl_itemid=" + this.id);
			console.log("itemid=" + itemid);*/
			var addbatch = $("#selitem tr#" + this.id).find('#saletabbat').text();
			console.log("addbatch=" + addbatch);
			//			
			var addeditmuniquekey = this.id + addbatch;
			console.log("addeditmuniquekey=" + addeditmuniquekey);
			//			if (Number(this.id) == Number(itemid)) {
			//				itempresent = 1;
			//			}
			if (preuniquekey == addeditmuniquekey) {
				itempresent = 1;
			}
		});
	}
	//console.log("itempresent=" + itempresent);
	if (itempresent == 1) {
		$('#itemExistsModal').modal('show');
	} else {
		if ($("#item_sche").val() == 'SCHEDULE H1' || $("#item_sche").val() == 'SCHEDULE X') {
			$('#scheleXorH1Modal').modal('show');
			$('#operationtype').val(operation);
		} else {
			addItemtotable(operation);
		}

	}
}

function addItemtotable(operation) {
	var itemid = $("#item_id").val();

	if (Number(itemid) == 0) {
		return false;
	}
	var mrpamt = (($('#item_pqty').val() * $('#item_conv').val()) + Number($('#item_lqty').val())) * $('#item_mrp').val();
	var starttrline = "";
	if ($("#item_sche").val() == 'H1' || $("#item_sche").val() == 'X') {
		starttrline = "<tr id='" + itemid + "' class='schx' style='cursor: pointer;' onclick='javascript:itemHeaderDivView(this.id);' >";
	} else {
		if ($("#item_grp").val() == 'FOOD' || $("#item_grp").val() == 'Food') {
			starttrline = "<tr id='" + itemid + "' class='foodx' style='cursor: pointer;' onclick='javascript:itemHeaderDivView(this.id);' >";
		} else {
			starttrline = "<tr id='" + itemid + "' style='cursor: pointer;' onclick='javascript:itemHeaderDivView(this.id);' >";
		}
	}
	
	var pqty = 0;
	var lqty = 0;
	var disc = 0.0;
	if (($('#item_pqty').val() == "") || ($('#item_pqty').val() == null)) {
		pqty = 0;
	} else {
		pqty = $('#item_pqty').val();
	}
	if (($('#item_lqty').val() == "") || ($('#item_lqty').val() == null)) {
		lqty = 0;
	} else {
		lqty = $('#item_lqty').val();
	}
	if (($('#item_dis').val() == "") || ($('#item_dis').val() == null)) {
		disc = 0;
	} else {
		disc = $('#item_dis').val();
	}

	var purcost = (($('#item_pqty').val() * $('#item_conv').val()) + Number($('#item_lqty').val())) * $('#item_purCostperUnit').val();
	var calamt = (($('#item_pqty').val() * $('#item_conv').val()) + Number($('#item_lqty').val())) * $('#item_rate_ls').val();

	var name = "<td id='saletabname'>" + $('#item_name').val() + "</td>";
	var batchno = "<td id='saletabbat'>" + $('#item_batch').val() + "</td>";
	var expiryDateFormat = "<td id='saletabexpdt'>" + $('#item_exp').val() + "</td>";
	var manfacname = "<td id='saletabmanname'>" + $('#item_mfg').val() + "</td>";
	var packQty = "<td id='saletabpqty'>" + pqty + "</td>";
	var looseQty = "<td id='saletablqty'>" + lqty + "</td>";
	var conversion = "<td id='saletabconv'>" + $('#item_conv').val() + "</td>";
	var mrppack = "<td id='saletabmrppack'>" + $('#item_mrp_pack').val() + "</td>";
	var mrp = "<td id='saletabmrp'>" + parseFloat(mrpamt).toFixed(4) + "</td>";
	var amt = "<td id='saletabamt'>" + parseFloat(calamt).toFixed(4) + "</td>";
	var rateperunit = "<td id='saletabrateperunit' >" + $('#item_rate_ls').val() + "</td>";
	var vatperc = "<td id='saletabvatperc' class='hide'>" + $('#item_vat').val() + "</td>";
	var item_taxPercentage = "<td id='saletabtaxPercentage'>" + parseFloat($('#item_taxPercentage').val()).toFixed(4) + "</td>";
	var discperc = "<td id='saletabdiscperc'>" + parseFloat(disc).toFixed(4) + "</td>";
	var totamt = "<td id='saletabtotamt'>" + $('#item_tot').val() + "</td>";
	var rowdelete = "<td><button class='btn btn-theme04 btn-xs' id='" + itemid + "' onclick='javascript:showSelTabItemDelModal(this.id);'><i class='fa fa-trash-o '></i></button></td>";
	var itembarcode = "<td id='saletabitembarcode' class='hide'>" + $('#item_barcode').val() + "</td>";
	var punitid = "<td id='saletabpunitid' class='hide'>" + $('#item_packunitid').val() + "</td>";
	var lunitid = "<td id='saletablunitid' class='hide'>" + $('#item_looseunitid').val() + "</td>";
	var content = "<td id='saletabcontent' class='hide'>" + $('#item_content').val() + "</td>";
	var vatamt = "<td id='saletabvat' class='hide'>" + $('#item_vatamt').val() + "</td>";
	var discamt = "<td id='saletabdisc' class='hide'>" + $('#item_discamt').val() + "</td>";
	var stockedqty = "<td id='saletabitemstkqty' class='hide'>" + $('#item_stockedqty').val() + "</td>";
	var mrpperunit = "<td id='saletabmrpperunit' class='hide'>" + $('#item_mrp').val() + "</td>";
	var rate = "<td id='saletabrate' class='hide'>" + $('#item_rate_ls').val() + "</td>";
	var schename = "<td id='saletabsche' class='hide'>" + $('#item_sche').val() + "</td>";
	var groupname = "<td id='saletabsche' class='hide'>" + $('#item_grp').val() + "</td>";
	var taxperc = "<td id='saletabtaxperc' class='hide'>" + $('#item_tax').val() + "</td>";
	var taxamt = "<td id='saletabtax' class='hide'>" + $('#item_taxamt').val() + "</td>";
	//var contentname = "<td id='saletabcontent' class='hide'>" + $('#content_Dets').val() + "</td>"
	var contentid = "<td id='saletabcontentid' class='hide'>" + $('#content_id').val() + "</td>";
	var item_taxId = "<td id='saletabtaxId' class='hide'>" + $('#item_taxId').val() + "</td>";

	var item_isGroupTax = "<td id='saletabisGroupTax' class='hide'>" + $('#item_isGroupTax').val() + "</td>";
	var item_discount = "<td id='saletabdiscount' class='hide'>" + parseFloat(disc).toFixed(4) + "</td>";
	var item_isDiscount = "<td id='saletabisDiscount' class='hide'>" + $('#item_isDiscount').val() + "</td>";
	var item_maxDiscountLimit = "<td id='saletabmaxDiscountLimit' class='hide'>" + $('#item_maxDiscountLimit').val() + "</td>";
	var item_CalcTaxAmt = "<td id='saletabitemcalcgstamt' class='hide'>" + $('#item_CalcTaxAmt').val() + "</td>";
	var item_taxMode = "<td id='saletabitemtaxmode' class='hide'>" + $('#item_taxMode').val() + "</td>";
	var item_hsnCode = "<td id='saletabitemhsncode' class='hide'>" + $('#item_hsnCode').val() + "</td>";
	var item_purCost = "<td id='saletabpurcost' class='hide'>" + parseFloat(purcost).toFixed(4) + "</td>";
	var item_purCostperUnit = "<td id='saletabpurcostperunit' class='hide'>" + $('#item_purCostperUnit').val() + "</td>";
	var salerate = "<td id='saletabitemsalerate' class='hide'>" + parseFloat($('#item_sale_rate').val()).toFixed(4) + "</td>";
	var item_purChase_rate = "<td id='saletabitempurrate' class='hide'>" + $('#item_purChase_rate').val() + "</td>";
	var endtrline = "</tr>";

	if (operation == 1) { // edit
		var disc = 0.0;
		if (($('#item_pqty').val() == "") || ($('#item_pqty').val() == null)) {
			pqty = 0;
		} else {
			pqty = $('#item_pqty').val();
		}
		if (($('#item_lqty').val() == "") || ($('#item_lqty').val() == null)) {
			lqty = 0;
		} else {
			lqty = $('#item_lqty').val();
		}
		if (($('#item_dis').val() == "") || ($('#item_dis').val() == null)) {
			disc = 0;
		} else {
			disc = $('#item_dis').val();
		}

		$("#item_id").val(itemid);
		$("#selitem tr#" + itemid).find('#saletabname').text($('#item_name').val());
		$("#selitem tr#" + itemid).find('#saletabitembarcode').text($('#item_barcode').val());
		$("#selitem tr#" + itemid).find('#saletabbat').text($("#item_batch").val());
		$("#selitem tr#" + itemid).find('#saletabexpdt').text($("#item_exp").val());
		$("#selitem tr#" + itemid).find('#saletabpqty').text(pqty);
		$("#selitem tr#" + itemid).find('#saletabpunitid').text($("#item_packunitid").val());
		$("#selitem tr#" + itemid).find('#saletablqty').text(lqty);
		$("#selitem tr#" + itemid).find('#saletablunitid').text($("#item_looseunitid").val());
		$("#selitem tr#" + itemid).find('#saletabmrppack').text($("#item_mrp_pack").val());
		$("#selitem tr#" + itemid).find('#saletabmrp').text(parseFloat(mrpamt).toFixed(4));
		$("#selitem tr#" + itemid).find('#saletabamt').text(parseFloat(calamt).toFixed(4));
		$("#selitem tr#" + itemid).find('#saletabmrpperunit').text($("#item_mrp").val());
		$("#selitem tr#" + itemid).find('#saletabrateperunit').text($("#item_rate_ls").val());
		$("#selitem tr#" + itemid).find('#saletabrate').text($("#item_rate_ls_hid").val());
		$("#selitem tr#" + itemid).find('#saletabdiscperc').text(parseFloat(disc).toFixed(4));
		$("#selitem tr#" + itemid).find('#saletabdisc').text($("#item_discamt").val());
		$("#selitem tr#" + itemid).find('#saletabvatperc').text($("#item_vat").val());
		$("#selitem tr#" + itemid).find('#saletabvat').text($("#item_vatamt").val());
		$("#selitem tr#" + itemid).find('#saletabtotamt').text($("#item_tot").val());
		$("#selitem tr#" + itemid).find('#saletabmanname').text($("#item_mfg").val());
		$("#selitem tr#" + itemid).find('#saletabcontent').text($("#item_content").val());
		$("#selitem tr#" + itemid).find('#saletabconv').text($("#item_conv").val());
		$("#selitem tr#" + itemid).find('#saletabtaxperc').text($("#item_tax").val());
		$("#selitem tr#" + itemid).find('#saletabtax').text($("#item_taxamt").val());
		$("#selitem tr#" + itemid).find('#saletabcontent').text($("#content_Dets").val());
		$("#selitem tr#" + itemid).find('#saletabcontentid').text($("#content_id").val());
		$("#selitem tr#" + itemid).find('#saletabtaxId').text($("#item_taxId").val());
		$("#selitem tr#" + itemid).find('#saletabtaxPercentage').text($("#item_taxPercentage").val());
		$("#selitem tr#" + itemid).find('#saletabisGroupTax').text($("#item_isGroupTax").val());
		$("#selitem tr#" + itemid).find('#saletabdiscount').text(parseFloat(disc).toFixed(4));
		$("#selitem tr#" + itemid).find('#saletabisDiscount').text($("#item_isDiscount").val());
		$("#selitem tr#" + itemid).find('#saletabmaxDiscountLimit').text($("#item_maxDiscountLimit").val());
		$("#selitem tr#" + itemid).find('#saletabitemcalcgstamt').text($("#item_CalcTaxAmt").val());
		$("#selitem tr#" + itemid).find('#saletabitemtaxmode').text($("#item_taxMode").val());
		$("#selitem tr#" + itemid).find('#saletabitemhsncode').text($("#item_hsnCode").val());
		$("#selitem tr#" + itemid).find('#saletabpurcost').text(parseFloat(purcost).toFixed(4));
		$("#selitem tr#" + itemid).find('#saletabpurcostperunit').text($("#item_purCostperUnit").val());
		$("#selitem tr#" + itemid).find('#saletabitemsalerate').text($("#item_sale_rate").val());
		$("#selitem tr#" + itemid).find('#saletabitempurrate').text($("#item_purChase_rate").val());
	} else {
		createdrowline = starttrline + name + batchno + expiryDateFormat + manfacname + packQty + looseQty + conversion + mrppack + mrp + amt + rateperunit + vatperc + item_taxPercentage + vatamt + discperc + discamt + totamt + rowdelete + itembarcode + punitid + lunitid + content + stockedqty + mrpperunit + rate + schename +groupname+ taxperc + taxamt + contentid + item_taxId + item_isGroupTax + item_discount + item_isDiscount + item_maxDiscountLimit + item_CalcTaxAmt + item_taxMode + item_hsnCode + item_purCost + item_purCostperUnit + salerate + item_purChase_rate + endtrline;
//		$("#saletabitemdetails").append(createdrowline);
		$("#saletabitemdetails").prepend(createdrowline);
	}

	clearHeaderDiv();
	calculateTotalMRP();
	calculateTotalamt();
	calculateTotalVat();
	calculateTotalTax();
	calculateTotalDisc();
	calculateNetTotal();
	//calculateSpclDisc();
	calculateTotalGSTamt();
	calculateTotalPurchaseamt();
}

function deleteSalesInv() {
	$('#confirmModalPos').modal('show');
}
function DoConfirmPos() {
	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.saleId = $("#saleId").val();

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/pos/deletesalesinv.htm", CommonRelsetmapperObj, function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == '1') {
			document.getElementById('confirmmessagecont').innerHTML = getCashMemoText.dataSucDelete;
			showConfirmModal();
		} else {
			document.getElementById('confirmmessagecont').innerHTML = getCashMemoText.dataNotDelete;
			showConfirmModal();
		}

	});
}
function sendItemToPO() {
	$("#snditmpo").addClass("hide");
	var itemnotfoundid = $("#senditemtopoid").val();
	var podate = $("#date").val();
	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.itemId = itemnotfoundid;
	CommonRelsetmapperObj.invDate = podate;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/purorder/createtemppofromsale.htm", CommonRelsetmapperObj, function(response) {
		console.log(response);
		var status = JSON.parse(response);
		if (status.length == 0) {

		} else {
			$("#snditmpo").removeClass("hide");
			if (status.id > 0) {
				document.getElementById('snditmpo').innerHTML = getFieldText.greaterthanzero;
			} else if (status.id == 0) {
				document.getElementById('snditmpo').innerHTML = getFieldText.zero;
			} else if (status.id == -1) {
				document.getElementById('snditmpo').innerHTML = getFieldText.minusone;
			} else if (status.id == -2) {
				document.getElementById('snditmpo').innerHTML = getFieldText.minustwo;
			} else if (status.id == -3) {
				document.getElementById('snditmpo').innerHTML = getFieldText.minusthree;
			} else {
				document.getElementById('snditmpo').innerHTML = getFieldText.zero;
			}
		}
	});
}
function getGenericMed() {
	$("#snditmpo").addClass("hide");
	$("#alternatemeddetails").text("");
	$("#alternateMeditemname").text($("#item_name").val());
	
	$("#selectedItemName").text(selectedItemData.itemName); // new added 10.6.2019
	$("#selectedItemPrice").text(parseFloat(selectedItemData.mrp).toFixed(2));// new added 10.6.2019
	$("#selectedItemMfgName").text(selectedItemData.manufacturerName);// new added 10.6.2019
	$("#selectedItemNetCont").text(selectedItemData.netContent);// new added 10.6.2019
	$("#selectedItemGrpName").text(selectedItemData.groupName);// new added 10.6.2019
	
	var selectedItemMrp=selectedItemData.mrp;// new added 10.6.2019
	
	var itemnotfoundid = $("#itemnotfoundid").val();
	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.itemId = itemnotfoundid;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/item/getalternatemedicine.htm", CommonRelsetmapperObj, function(response) {
		console.log(response);
		var altmedlist = JSON.parse(response);
		if (altmedlist.length == 0) {
			$("#altmeditemnotfounddiv").removeClass("hide");
			/*$("#snditmpo").removeClass("hide");*/
			$("#alternateMedtable").addClass("hide");
			$("#altmeditemnotfoundname").text($("#item_name").val());
		} else {
			$("#altmeditemnotfounddiv").addClass("hide");
			$("#alternateMedtable").removeClass("hide");
			//$("#snditmpo").addClass("hide");
			for ( var i = 0; i < altmedlist.length; i++) {
				var altmed = altmedlist[i];
				//		console.log(altmed.itemId);
				
				var discountAmt = 0.0;
					if(altmed.isDiscount == 1){
						discountAmt = parseFloat((altmed.price * altmed.discount)/100).toFixed(2) 
					}
				
				
				var starttrline = "<tr id=" + altmed.itemId + " style='cursor: pointer;height: 35px;' onclick='javascript:selAltMedAlert(" + altmed.itemId + ",&quot;" + altmed.itemName + "&quot;)'>";
				var name = "<td>" + altmed.itemName + "</td>";
				var manufacturerName = "<td>" + altmed.manufacturerName + "</td>";
				var netContent = "<td>" + altmed.netContent + "</td>";
				var price = "<td>" + parseFloat(altmed.price).toFixed(2) + "</td>";
				var ourprice = "<td>" + parseFloat(Number(altmed.price) - Number(discountAmt)).toFixed(2) + "</td>";// new added 10.6.2019
				var yousave = "<td style='font-weight: bold;color:#108e5a'>" + parseFloat(Number(selectedItemMrp)-(Number(altmed.price) - Number(discountAmt))).toFixed(2) + " ("+ parseFloat( ((Number(selectedItemMrp)-(Number(altmed.price) - Number(discountAmt))) * 100) / Number(selectedItemMrp) ).toFixed(2)+"%)"+ "</td>"; // new added 10.6.2019
				var stockQty = "<td>" + altmed.stockQty + "</td>";//
				var endtrline = "</tr>";
				createdrowline = starttrline + name + manufacturerName + netContent + price + ourprice + yousave+ stockQty + endtrline;
				$("#alternatemeddetails").append(createdrowline); 
			}
		}
		$('#alternateMedModal').modal('show');
	});
}
/*function selAltMed(	itemid,
					itemname) {
	//console.log("itemname" + itemname);
	//console.log("itemid" + itemid);
	$("#item_name").val(itemname);
	$('#alternateMedModal').modal('hide');
	getItemDetails(itemid);

}*/
function selAltMed(	) {
//console.log("itemname" + itemname);
//console.log("itemid" + itemid);
	var itemid =document.getElementById('altItemId').value;
	var itemname =document.getElementById('altItemName').value;
$("#item_name").val(itemname);
$('#alternateMedModal').modal('hide');
getItemDetails(itemid);

}
function openCardModal() {
	$('#cardModal').modal('show');
	var crdamt = $("#cardAmt").val();
	if (crdamt == '') {
		crdamt = 0.00;
	}
	var cashAmt = $("#cashAmt").val();
	if (cashAmt == '') {
		cashAmt = 0.00;
	}
//	var credittot = parseFloat($("#nettot").val() - cashAmt - crdamt).toFixed(2);
	var credittot = parseFloat($("#nettot").val() - cashAmt - crdamt-$("#payretadjamt").val()).toFixed(2);
	$('#cardAmtMod').val(credittot);
	$("#cardExpMod").val($("#cardExpMod").val());
	$("#cardFourDigitMod").val($("#cardFourDigitMod").val());
}
$("#cardAmtMod").keyup(function() {
	var crdamt = $(this).val();
	var cashAmt = $("#cashAmt").val();
	if (cashAmt == '') {
		cashAmt = 0.00;
	}
//	var credittot = parseFloat($("#nettot").val() - cashAmt - $("#cardAmt").val()).toFixed(2);
	var credittot = parseFloat($("#nettot").val() - cashAmt - $("#cardAmt").val()-$("#payretadjamt").val()).toFixed(2);
	//$('#cardAmtMod').val(credittot);
});
function holdCardDetails() {
	var cardamt = Number($("#cardAmt").val()) + Number(parseFloat($('#cardAmtMod').val()).toFixed(2));
	$("#cardAmt").val(parseFloat(cardamt).toFixed(2));
	var cashAmt = $("#cashAmt").val();
	if (cashAmt == '') {
		cashAmt = 0.00;
	}
//	var creditamt = parseFloat($("#nettot").val() - cashAmt - $("#cardAmt").val()).toFixed(2);//$("#creditAmt").val();
	var creditamt = parseFloat($("#nettot").val() - cashAmt - $("#cardAmt").val()-$("#payretadjamt").val()).toFixed(2);
//	var totamt = Number(cardamt) + Number(cashAmt);
	var totamt = Number(cardamt) + Number(cashAmt)+Number($("#payretadjamt").val());
	var nettot = $("#nettot").val();
	if (parseFloat(totamt).toFixed(2) < parseFloat(nettot).toFixed(2)) {
		//		alert(parseFloat(nettot-totamt).toFixed(2));
		$("#creditAmt").val(parseFloat(nettot - totamt).toFixed(2));
	} else {
		$("#creditAmt").val(parseFloat(0).toFixed(2));
	}

}
function openNewCashMemo() {
	location.href = BASE_URL + '/pos/cashmemo.htm';
}
function targetURL() {
	//console.log("con val=" + $("#confirmval").val());
	if ($("#confirmval").val() == 0) {
		//		$("#header_div").find('input:text').val('');
		//		$("#header_div").find('input:hidden').val('');
		//		location.href = "#";
		clearHeaderDiv();
		location.href = BASE_URL + '/pos/cashmemo.htm';
	} else if ($("#confirmval").val() == -1) {
		location.href = "#";
	} else {
		location.href = BASE_URL + '/pos/cashmemo.htm?saleId=3465fg-trw73sxz-' + $("#confirmval").val() + '-utew09-qdd55-4320jhhgrt';
	}
}
function openretadjmod() {
	$("#billamtheaderAdj").text(parseFloat($("#nettot").val()).toFixed(2));
	$("#retAdjModal").modal("show");
}
function getMemoDetForAdj() {
	$("#showretadjtbody").text("");

	if ($("#retamtvalsamepage").val() != 0) {
		var s = $("#retamtvalsamepage").val();
		if ($("#retamtvalsamepage").val() == undefined || $("#retamtvalsamepage").val() == "") {
			s = 0;
		}
		$("#payretadjamt").val(parseFloat(s).toFixed(2));
	}

	var CommonRelsetmapperObj = {};
	if ($("#retadjmemono").val() == '') {
	} else {
		CommonRelsetmapperObj.invoiceNo = $("#retadjmemoDoc").val() + $("#retadjmemoFinyr").val() + $("#retadjmemoSlash").val() + $("#retadjmemono").val();
	}
	if ($("#retadjcustph").val() == '') {
	} else {
		CommonRelsetmapperObj.custPh = $("#retadjcustph").val();
	}

	CommonRelsetmapperObj.custId = 0;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/retunmemo/getretdetforadj.htm", CommonRelsetmapperObj, function(response) {
		//console.log(response);
		var retadjlist = JSON.parse(response);
		//	console.log(retadjlist.length);
		if (retadjlist.length == 0) {
			$("#nocashmemofound").text("No data found.");
		} else {
			$("#nocashmemofound").text("");
			for ( var i = 0; i < retadjlist.length; i++) {
				var retadj = retadjlist[i];
				$("#retadjcustph").val(retadj.customerPhone);
				$("#retadjcustname").val(retadj.customerName);
				var starttrline = "<tr id=" + retadj.saleReturnId + " >";
				var invno = "<td>" + retadj.invNo + "</td>";
				var invdate = "<td>" + moment(retadj.invDate).format('YYYY-MM-DD') + "</td>";
				var netAmount = "<td id='retnetamt_" + retadj.saleReturnId + "'>" + parseFloat(retadj.netAmount).toFixed(2) + "</td>";
				var preAdjAmount = "<td id='retpreadjamt_" + retadj.saleReturnId + "'>" + parseFloat(retadj.preAdjAmount).toFixed(2) + "</td>";
				var adjAmount = "<td><input type='text' size='6' style='line-height: 14px;' id='adjamt_" + retadj.saleReturnId + "' value='" + parseFloat(retadj.netAmount - retadj.preAdjAmount).toFixed(2) + "' onkeyup='checkAdjAmt(this.value," + retadj.saleReturnId + ")' style'line-height: 14px;'> </td>";//
				var rowadd = "<td><button class='btn btn-success btn-xs' id='adjamtaddbut_" + retadj.saleReturnId + "' onclick='javascript:addItemforadj(" + JSON.stringify(retadj) + ");'><i class='fa fa-plus'></i></button></td>";
				var endtrline = "</tr>";
				createdrowline = starttrline + invno + invdate + netAmount + preAdjAmount + adjAmount + rowadd + endtrline;
				$("#showretadjtbody").append(createdrowline);
			}
		}

	});

}

function checkAdjAmt(	inputval,
						retid) {
	var netamt = $("#retnetamt_" + retid).text();
	var preadjamt = $("#retpreadjamt_" + retid).text();
	var totamt = Number(netamt) - Number(preadjamt);
	//console.log(totamt);
	if (parseFloat(inputval) > parseFloat(totamt)) {
		$("#adjamt_" + retid).val(parseFloat(totamt).toFixed(2));
	}

}

function addItemforadj(retadj) {
	//$("#retadjtbody").text("");
	var retpresent = 0;
	var totretadjamt = $("#totretadjamt").text();
	if ($("#payretadjamt").val() != 0) {
		if (Number($("#totretadjamt").text()) == 0) {
			samepageret = 1;
			totretadjamt = Number($("#totretadjamt").text()) + Number($("#payretadjamt").val());
		}
	}

	//		var totretadjamt = Number($("#totretadjamt").text()) + Number($("#payretadjamt").val());
	var paymodnettot = $("#paymodnettot").val();

	$('#retadjtable > tbody  > tr').each(function() {
		var retid = this.id;
		if (retid == retadj.saleReturnId) {
			retpresent = 1;
		}
	});
	if (retpresent == 1) {

	} else {
		var adjamt = $("#adjamt_" + retadj.saleReturnId).val();
		totretadjamt = Number(totretadjamt) + Number(adjamt);
		//		totretadjamt = Number(totretadjamt) + Number(adjamt) + Number($("#payretadjamt").val());
		if (parseFloat(totretadjamt) > parseFloat(paymodnettot)) {
			$("#greaterbillamt").text("Adjust return amt : (" + totretadjamt + ") is greater than bill amount :(" + paymodnettot + ")");
			totretadjamt = Number(totretadjamt) - Number(adjamt);
		} else {
			$("#greaterbillamt").text("");
			$("#adjamtaddbut_" + retadj.saleReturnId).hide();
			//$("#adjamt_"+retadj.saleReturnId).prop('readonly', true);
			var starttrline = "<tr id=" + retadj.saleReturnId + " >";
			var invno = "<td>" + retadj.invNo + "</td>";
			var invdate = "<td>" + moment(retadj.invDate).format('YYYY-MM-DD') + "</td>";
			var netAmount = "<td id='retnetamt'>" + parseFloat(retadj.netAmount).toFixed(2) + "</td>";
			var preAdjAmount = "<td id='retpreadjamt'>" + parseFloat(retadj.preAdjAmount).toFixed(2) + "</td>";
			var adjAmount = "<td id='retadjamt'>" + parseFloat(adjamt).toFixed(2) + "</td>";//
			var rowadd = "<td><button class='btn btn-danger btn-xs'  onclick='javascript:showSelItemDelModal(" + retadj.saleReturnId + ");'><i class='fa fa-trash-o '></i></button></td>";
			var endtrline = "</tr>";
			createdrowline = starttrline + invno + invdate + netAmount + preAdjAmount + adjAmount + rowadd + endtrline;
			$("#retadjtbody").append(createdrowline);
			calculateAdjAmount();
			calculateRetTotnteamt();
			calculateRetpreAdjAmount();
		}

		//var retadj = JSON.parse(clickretadj);
		$("#payretadjamt").val(parseFloat(totretadjamt).toFixed(2));
		$("#totretadjamt").text(parseFloat(totretadjamt).toFixed(2));
		var paymodnettot = $("#paymodnettot").val();
		//		$("#paymodnettot").val(parseFloat(paymodnettot-parseFloat(totretadjamt)).toFixed(2));
		$("#creditAmt").val(parseFloat(paymodnettot - parseFloat(totretadjamt)).toFixed(2));
		//$("#nettot").val(parseFloat(paymodnettot-parseFloat(totretadjamt)).toFixed(2));
	}

}

function calculateRetTotnteamt() {
	var rettotnteamt = 0.00;
	$('#retadjtable tbody tr').each(function() {
		var netAmount = $(this).find("#retnetamt").html();
		rettotnteamt = rettotnteamt + Number(netAmount);
	});
	$("#totretnetamt").html(parseFloat(rettotnteamt).toFixed(2));
}

function calculateRetpreAdjAmount() {
	var rettotpreAdjAmount = 0.00;
	$('#retadjtable tbody tr').each(function() {
		var preAdjAmount = $(this).find("#retpreadjamt").html();
		rettotpreAdjAmount = rettotpreAdjAmount + Number(preAdjAmount);
	});
	$("#totretpreadjamt").html(parseFloat(rettotpreAdjAmount).toFixed(2));
}

function calculateAdjAmount() {
	var rettotadjAmount = 0.00;
	$('#retadjtable tbody tr').each(function() {
		var adjAmount = $(this).find("#retadjamt").html();
		rettotadjAmount = rettotadjAmount + Number(adjAmount);
	});
	$("#totretadjamt").html(parseFloat(rettotadjAmount).toFixed(2));
}

function showSelItemDelModal(trId) {
	$("#confirmIdret").val(trId);
	$('#confirmModal').modal('show');
}
function showSelTabItemDelModal(trId) {
	$("#saletableitemdelid").val(trId);
	$('#saletableItemDelModal').modal('show');
}
function closeSaletableItemDel(trId) {
	$('#saletabitemdetails tr#' + trId).remove();
	clearHeaderDiv();
	calculateTotalMRP();
	calculateTotalamt();
	calculateTotalVat();
	calculateTotalTax();
	calculateTotalDisc();
	calculateNetTotal();
	//calculateSpclDisc();
	calculateTotalGSTamt();
	calculateTotalPurchaseamt();
}
function DoConfirm() {
	var itmid = $("#confirmIdret").val();

	$('#retadjtbody tr#' + itmid).remove();
	$("#adjamtaddbut_" + itmid).show();
	$("#greaterbillamt").text("");
	$("#payretadjamt").val(parseFloat(0).toFixed(2));
	if ($("#retamtvalsamepage").val() != 0) {
		var s = $("#retamtvalsamepage").val();
		if ($("#retamtvalsamepage").val() == undefined || $("#retamtvalsamepage").val() == "") {
			s = 0;
		}
		$("#payretadjamt").val(parseFloat(s).toFixed(2));
	}
	var cardAmt = $("#cardAmt").val();
	var cashamt = $("#cashAmt").val();
	var nettot = $("#nettot").val();
//	var totamt = (Number(cashamt) + Number(cardAmt));
	var totamt = (Number(cashamt) + Number(cardAmt))+Number($("#payretadjamt").val());
	$("#creditAmt").val(parseFloat(nettot - totamt).toFixed(2));
	calculateAdjAmount();
	calculateRetpreAdjAmount();
	calculateRetTotnteamt();
}
function closeRetAdjMod() {
	$("#retadjcustph").val("");
	$("#retadjcustname").val("");
	$("#retadjtbody").text("");
	$("#showretadjtbody").text("");
	$("#totretnetamt").html(parseFloat(0).toFixed(2));
	$("#totretadjamt").html(parseFloat(0).toFixed(2));
	$("#totretpreadjamt").html(parseFloat(0).toFixed(2));
	$("#payretadjamt").val(parseFloat(0).toFixed(2));
	if ($("#retamtvalsamepage").val() != 0) {
		var s = $("#retamtvalsamepage").val();
		if ($("#retamtvalsamepage").val() == undefined || $("#retamtvalsamepage").val() == "") {
			s = 0;
		}
		$("#payretadjamt").val(parseFloat(s).toFixed(2));
	}
	var cardAmt = $("#cardAmt").val();
	var cashamt = $("#cashAmt").val();
	var nettot = $("#nettot").val();
//	var totamt = (Number(cashamt) + Number(cardAmt));
	var totamt = (Number(cashamt) + Number(cardAmt))+Number($("#payretadjamt").val());
	$("#creditAmt").val(parseFloat(nettot - totamt).toFixed(2));
}
function okRetAdjMod() {
	$("#greaterbillamt").text("");
	var totretadjamt = $("#totretadjamt").text();
	//	$("#payretadjamt").val(parseFloat(totretadjamt).toFixed(2));
	$("#payretadjamt").val(parseFloat(Number(totretadjamt)).toFixed(2));
	var cardAmt = $("#cardAmt").val();
	var cashamt = $("#cashAmt").val();
	var nettot = $("#nettot").val();
	totretadjamt = $("#payretadjamt").val();
	$("#totretadjamt").text(totretadjamt);
	var totamt = (Number(cashamt) + Number(cardAmt) + Number(totretadjamt));
	$("#creditAmt").val(parseFloat(nettot - parseFloat(totamt)).toFixed(2));
	
	$("#cashAmt").val(parseFloat(Number(nettot) - Number(totretadjamt)).toFixed(2));
	$("#tenderamt").val(parseFloat(Number(nettot) - Number(totretadjamt)).toFixed(2));
	$("#refundAmt").val(0);
	$("#creditAmt").val(0);
	$("#cardAmt").val(0);
}

function viewretadjamt() {
	$("#viewretadjtbody").text("");
	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.saleId = $("#saleId").val();
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/retunmemo/getretdetforadjbysaleid.htm", CommonRelsetmapperObj, function(response) {
		//console.log(response);
		var retadjlist = JSON.parse(response);
		for ( var i = 0; i < retadjlist.length; i++) {
			var retadj = retadjlist[i];
			var starttrline = "<tr id=" + retadj.saleReturnId + " >";
			var invno = "<td>" + retadj.invNo + "</td>";
			var invdate = "<td>" + moment(retadj.invDate).format('YYYY-MM-DD') + "</td>";
			var netAmount = "<td id='retnetamt'>" + parseFloat(retadj.netAmount).toFixed(2) + "</td>";
			//			var preAdjAmount = "<td id='retpreadjamt'>" + parseFloat(retadj.preAdjAmount).toFixed(2) + "</td>";
			var adjAmount = "<td id='retadjamt'>" + parseFloat(retadj.adjAmount).toFixed(2) + "</td>";//
			var endtrline = "</tr>";
			createdrowline = starttrline + invno + invdate + netAmount + adjAmount + endtrline;
			$("#viewretadjtbody").append(createdrowline);
		}
	});

	$("#viewretAdjModal").modal("show");
}

function openAddModal(addSubjct) {
	document.getElementById('custAddAlertMsg').innerHTML = '';
	document.getElementById('docAddAlertMsg').innerHTML = '';
	if (addSubjct == "cust") { // Customer add
		$('#customerAddEditModal').find('input:text').val('');
		$('#customerAddEditModal').find('input:hidden').val('');
		$("#headertext").text(getCustomerText.headerTextAdd);
		$("#customerName").val($("#salecustname").val());
		$("#phn").val($("#salecustph").val());
		$("#addrs").val($("#salecustaddr").val());
		$('#customerAddEditModal').modal('show');
	} else if (addSubjct == "doc") { // Doctor add
		$('#doctorAddEditModal').find('input:text').val('');
		$('#doctorAddEditModal').find('input:hidden').val('');
		$("#headertextDoc").text(getDoctorText.headerTextAdd);
		$("#dctrName").val($("#saledocname").val());
		$('#doctorAddEditModal').modal('show');
	}

}
/*
function addEditCustomer() {
	document.getElementById('custAddAlertMsg').innerHTML = '';
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
	if (isNaN($("#pin").val())) {
		document.getElementById('custAddAlertMsg').innerHTML = getFieldText.numberCheck + " in Pin";
		$(this).focus();
		return false;
	} else {
		document.getElementById('custAddAlertMsg').innerHTML = "";
	}
	if (Number($("#phn").val().length) < 10) {
		document.getElementById('custAddAlertMsg').innerHTML = "Mobile Number should be 10 digit.";
		$(this).focus();
		return false;
	} else {
		document.getElementById('custAddAlertMsg').innerHTML = "";
	}
	if ($('#opbal').val() == 0 || $('#opbal').val() == "") {
		var opbal = 0;
	} else {
		if (isNaN($("#opbal").val())) {
			document.getElementById('custAddAlertMsg').innerHTML = getFieldText.numberCheck + " in Opening Balance";
			$(this).focus();
			return false;
		} else {
			document.getElementById('custAddAlertMsg').innerHTML = "";
			var opbal = $('#opbal').val();
		}
	}
	if ($('#creditLimitAdd').val() == 0 || $('#creditLimitAdd').val() == "") {
		var creditLimit = 0.0;
	} else {
		if (isNaN($("#creditLimitAdd").val())) {
			document.getElementById('custAddAlertMsg').innerHTML = getFieldText.numberCheck + " in Credit Limit";
			$(this).focus();
			return false;
		} else {
			document.getElementById('custAddAlertMsg').innerHTML = "";
			var creditLimit = $('#creditLimitAdd').val();
		}
	}

	var name_label = $("#cust_name_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));

	var phn_label = $("#phn_label").text();
	var phn_field = phn_label.substring(0, phn_label.lastIndexOf(" "));

	var field_names = [ [ "customerName", name_field ], [ "phn", phn_field ] ];

	if (fieldValidationWithAlertDivId(field_names, "custAddAlertMsg") > 0) {
	} else {

		$('#customerAddEditModal').modal('hide');
		$('#pleasewaitModal').modal('show');
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
		CustomerMasterObj.gender = slgender;
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/customer/addcustomer.htm", CustomerMasterObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			var status = JSON.parse(response);
			if (status.id > 0) {
				$("#salecustname").val(name);
				$("#salecustph").val(phn);
				$("#salecustaddr").val(addrs);
				$("#ecardno").val(code);
				$("#salecustid").val(status.id);
				$('#add_cust_td').addClass("hide");
				$("#custCreditLimit").val(Number(creditLimit) - Number(opbal));
				$('.add_td').addClass("hide");
				$('#blacktext_td').addClass("hide");
				$('#black_td').addClass("hide");
				document.getElementById('confirmmessagecont').innerHTML = getFieldText.greaterthanzero;
				showConfirmModal();
				$("#confirmval").val(-1);
			} else if (status.id == 0) {
				document.getElementById('confirmmessagecont').innerHTML = getFieldText.zero;
				showConfirmModal();
				$("#confirmval").val(-1);
			} else if (status.id == -1) {
				document.getElementById('confirmmessagecont').innerHTML = getFieldText.minusone;
				showConfirmModal();
				$("#confirmval").val(-1);
			} else if (status.id == -2) {
				document.getElementById('confirmmessagecont').innerHTML = getFieldText.minustwo;
				showConfirmModal();
				$("#confirmval").val(-1);
			} else if (status.id == -3) {
				document.getElementById('confirmmessagecont').innerHTML = getFieldText.minusthree;
				showConfirmModal();
				$("#confirmval").val(-1);
			} else if (status.id == -10) {
				document.getElementById('confirmmessagecont').innerHTML = getFieldText.mailsentsuccess;
				showConfirmModal();
				$("#confirmval").val(-1);
			} else {
				document.getElementById('confirmmessagecont').innerHTML = getFieldText.zero;
				showConfirmModal();
				$("#confirmval").val(-1);
			}

			if (response == 0) {
				document.getElementById('confirmmessagecont').innerHTML = getCustomerText.dataNotAdd;
				showConfirmModal();
				$("#confirmval").val(-1);
			} else {
				$("#salecustname").val(name);
				$("#salecustph").val(phn);
				$("#salecustid").val(response);
				$('#add_cust_td').addClass("hide");
				$("#custCreditLimit").val(creditLimit);
				$('.add_td').addClass("hide");
				document.getElementById('confirmmessagecont').innerHTML = getCustomerText.dataSucAdd;
				showConfirmModal();
				$("#confirmval").val(-1);
			}

		});
	}
}
*/

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
	var creditLimit = 0;
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
	
	if($('#creditLimitpop').val()==0 || $('#creditLimitpop').val()=="")
	{
		creditLimit = 0.0;
	}
	else
	{
		if(isNaN($("#creditLimitpop").val()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.numberCheck+" in Credit Limit";
			$(this).focus();
			return false;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
			creditLimit = $('#creditLimitpop').val();
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
		if (cust_id == 0 || cust_id == "") { // add unit
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
		    CustomerMasterObj.consiName = consiNm;
		    CustomerMasterObj.consiAddress = consiAddr;
		    CustomerMasterObj.consiPhone = consiPhone;
		    CustomerMasterObj.consiGstNo = consiGstNo;
		    CustomerMasterObj.consiStateId=0;
			
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/customer/addcustomer.htm", CustomerMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				console.log("customer add response: "+ response);
				var status = JSON.parse(response);
				var salecustid = status.id;
				if (status.id > 0) {
					$("#salecustname").val(name);
					$("#salecustph").val(phn);
					$("#salecustaddr").val(addrs);
					$("#ecardno").val(code);
					$("#salecustid").val(salecustid);
					$("#custEmail").val(fax);
					$('#add_cust_td').addClass("hide");
					$("#custCreditLimit").val(Number(creditLimit) - Number(opbal));
					$('.add_td').addClass("hide");
					$('#blacktext_td').addClass("hide");
					$('#black_td').addClass("hide");
					document.getElementById('confirmmessagecont').innerHTML = getFieldText.greaterthanzero;
					showConfirmModal();
					$("#confirmval").val(-1);
				} else if (status.id == 0) {
					document.getElementById('confirmmessagecont').innerHTML = getFieldText.zero;
					showConfirmModal();
					$("#confirmval").val(-1);
				} else if (status.id == -1) {
					document.getElementById('confirmmessagecont').innerHTML = getFieldText.minusone;
					showConfirmModal();
					$("#confirmval").val(-1);
				} else if (status.id == -2) {
					document.getElementById('confirmmessagecont').innerHTML = getFieldText.minustwo;
					showConfirmModal();
					$("#confirmval").val(-1);
				} else if (status.id == -3) {
					document.getElementById('confirmmessagecont').innerHTML = getFieldText.minusthree;
					showConfirmModal();
					$("#confirmval").val(-1);
				} else if (status.id == -10) {
					document.getElementById('confirmmessagecont').innerHTML = getFieldText.mailsentsuccess;
					showConfirmModal();
					$("#confirmval").val(-1);
				} else {
					document.getElementById('confirmmessagecont').innerHTML = getFieldText.zero;
					showConfirmModal();
					$("#confirmval").val(-1);
				}

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
			
			
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/customer/editcustomer.htm", CustomerMasterObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				var status = JSON.parse(response);
				var salecustid = status.id;
				
				if (status.id > 0) {
					$("#salecustname").val(name);
					$("#salecustph").val(phn);
					$("#salecustaddr").val(addrs);
					$("#ecardno").val(code);
					$("#salecustid").val(salecustid);
					$("#custEmail").val(fax);
					$('#add_cust_td').addClass("hide");
					//$("#custCreditLimit").val(Number(creditLimit) - Number(opbal));
					$("#custCreditLimit").val(parseFloat(Number(creditLimit) - Number(opbal) - cusInCredit).toFixed(2));
					$("#creditLimit").val(parseFloat(Number(creditLimit) - Number(opbal) - cusInCredit).toFixed(2));
					//$("#custCreditLimit").val(Number(creditLimit)+Number($("#custCreditLimit").val()));
					$('.add_td').addClass("hide");
					$('#blacktext_td').addClass("hide");
					$('#black_td').addClass("hide");
					document.getElementById('confirmmessagecont').innerHTML = getFieldText.greaterthanzero;
					showConfirmModal();
					$("#confirmval").val(-1);
				} else if (status.id == 0) {
					document.getElementById('confirmmessagecont').innerHTML = getFieldText.zero;
					showConfirmModal();
					$("#confirmval").val(-1);
				} else if (status.id == -1) {
					document.getElementById('confirmmessagecont').innerHTML = getFieldText.minusone;
					showConfirmModal();
					$("#confirmval").val(-1);
				} else if (status.id == -2) {
					document.getElementById('confirmmessagecont').innerHTML = getFieldText.minustwo;
					showConfirmModal();
					$("#confirmval").val(-1);
				} else if (status.id == -3) {
					document.getElementById('confirmmessagecont').innerHTML = getFieldText.minusthree;
					showConfirmModal();
					$("#confirmval").val(-1);
				} else if (status.id == -10) {
					document.getElementById('confirmmessagecont').innerHTML = getFieldText.mailsentsuccess;
					showConfirmModal();
					$("#confirmval").val(-1);
				} else {
					document.getElementById('confirmmessagecont').innerHTML = getFieldText.zero;
					showConfirmModal();
					$("#confirmval").val(-1);
				}

			});

		}
	}
}
function isEmpty(val) {
    return (val === undefined || val == null || val.length <= 0) ? true : false;
}
function addEditDoctor() {
	document.getElementById('docAddAlertMsg').innerHTML = '';
	var pin = $('#pin').val();
	var name = $('#dctrName').val();
	var code = $('#code').val();
	var qualification = $('#qualification').val();
	var speciality = $('#speciality').val();
	var addrs = $('#addrs').val();
	var city = $('#city').val();
	var state = $('#state').val();
	var country = $('#country').val();
	var phn = $('#phn').val();
	var fax = $('#fax').val();
	if ($('#locked').val() == "on") {
		var locked = 1;
	} else {
		var locked = 0;
	}

	if (isNaN($("#pin").val())) {
		document.getElementById('docAddAlertMsg').innerHTML = getFieldText.numberCheck + " in Pin";
		$(this).focus();
		return false;
	} else {
		document.getElementById('docAddAlertMsg').innerHTML = "";
	}

	if ($('#opbal').val() == 0 || $('#opbal').val() == "") {
		var opbal = 0;
	} else {
		if (isNaN($("#opbal").val())) {
			document.getElementById('docAddAlertMsg').innerHTML = getFieldText.numberCheck + " in Opening Balance";
			$(this).focus();
			return false;
		} else {
			document.getElementById('docAddAlertMsg').innerHTML = "";
			var opbal = $('#opbal').val();
		}
	}
	if ($('#commPer').val() == 0 || $('#commPer').val() == "") {
		var commPer = 0;
	} else {
		if (isNaN($("#commPer").val())) {
			document.getElementById('docAddAlertMsg').innerHTML = getFieldText.numberCheck + " in Commision Percentage";
			$(this).focus();
			return false;
		} else {
			document.getElementById('docAddAlertMsg').innerHTML = "";
			var commPer = $('#commPer').val();
		}
	}

	var name_label = $("#doc_name_label").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));

	var field_names = [ [ "dctrName", name_field ] ];
	if (fieldValidationWithAlertDivId(field_names, "docAddAlertMsg") > 0) {
	} else {
		$('#doctorAddEditModal').modal('hide');
		$('#pleasewaitModal').modal('show');
		var DoctorMasterObj = {};
		DoctorMasterObj.code = code;
		DoctorMasterObj.name = name;
		DoctorMasterObj.qualification = qualification;
		DoctorMasterObj.speciality = speciality;
		DoctorMasterObj.address = addrs;
		DoctorMasterObj.pin = pin;
		DoctorMasterObj.city = city;
		DoctorMasterObj.state = state;
		DoctorMasterObj.country = country;
		DoctorMasterObj.phoneNo = phn;
		DoctorMasterObj.fax = fax;
		DoctorMasterObj.opBal = opbal;
		DoctorMasterObj.commPer = commPer;
		DoctorMasterObj.isLocked = locked;
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/adddocctr.htm", DoctorMasterObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			if (response == 0) {
				document.getElementById('confirmmessagecont').innerHTML = getDoctorText.dataNotAdd;
				showConfirmModal();
				$("#confirmval").val(-1);
			} else {
				$("#saledocname").val(name);
				$("#saledocid").val(response);
				$('#add_doc_td').addClass("hide");
				$('.add_td').addClass("hide");
				document.getElementById('confirmmessagecont').innerHTML = getDoctorText.dataSucAdd;
				showConfirmModal();
				$("#confirmval").val(-1);
			}

		});
	}
}

function getSaleItem() {
	var CommResultsetObj = {};
	var itemid = $("#senditemtopoid").val();
	console.log("itemid=" + itemid);
	CommResultsetObj.itemId = itemid;

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/pos/saleitemdetforret.htm", CommResultsetObj, function(response) {
		console.log("response=" + response);
		var itemcurstkdets = JSON.parse(response);

	});
}

// =================================== Shortcut key functionality ================================================

/* ========= For "Prev Bill" Button (Using Ctrl+B) ========= */

$(document).bind("keyup keydown", function(e) {
	if (e.ctrlKey && e.which == 66) {
		getCustPreviousBill(document.getElementById('salecustph').value);
		return false;
	}
});

/* ============================ End ======================== */

/* ============ For "Add" Button (Using Ctrl+Shift+A) ============ */

$(document).bind("keyup", function(e) {
	if (e.ctrlKey && e.shiftKey && e.which == 65) {
		addOrUpdateItemToDetailsTable(0);
		return false;
	}
});

/* ============================ End ======================== */

/* ============ For "Update" Button (Using Alt+U) ============ */

$(document).bind("keyup", function(e) {
	if (e.altKey && e.which == 85) {
		addOrUpdateItemToDetailsTable(1);
		return false;
	}
});

/* ============================ End ======================== */

/* ============ For "Clear" Button (Using Ctrl+Shift+C) ============ */

$(document).bind("keyup", function(e) {
	if (e.ctrlKey && e.shiftKey && e.which == 67) {
		clearHeaderDiv();
		return false;
	}
});

/* ============================ End ======================== */

/* ========= For "New" Button (Using Ctrl+N) ========= */

$(document).bind("keyup", function(e) {
	if (e.ctrlKey && e.which == 78) {
		openNewCashMemo();
		return false;
	}
});

/* ============================ End ======================== */
// ============================================= end =============================================================

function openAddEditModal(id) {
	document.getElementById('alertMsg').innerHTML = '';
	$('#customerAddEditModal').find('input:text').val('');
	$('#customerAddEditModal').find('input:hidden').val('');
	if (id == 0) { // add
		$("#headertext").text(getCustomerText.headerTextAdd);
	} else { // update
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjax(BASE_URL + "/customer/getCustomerbyId/" + id + ".htm", function(response) {
			console.log($.parseJSON(response));
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
			$('#creditLimitpop').val($.parseJSON(response).creditLimit);
			$("#aadharcard").val($.parseJSON(response).addharCardNo);
			$("#slgender").val($.parseJSON(response).gender);
			$("#age").val($.parseJSON(response).age);
			$("#guardian_name").val($.parseJSON(response).guardian_name);
			
			if($("#custCreditLimit").val() != "") {
				cusInCredit = Number($.parseJSON(response).creditLimit) - Number($.parseJSON(response).obBal) - Number($("#custCreditLimit").val())
			}
		}, null);
		$("#headertext").text(getCustomerText.headerTextUpdate);
	}
	$('#customerAddEditModal').modal('show');
}



function getvendorledger_sale(group_code,acc_id,ref_id,para)
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

	if (para==2) { // for sale 
		
		commonobj.groupCode=group_code;
		commonobj.accountID=0;
		commonobj.referenceID=0;
		commonobj.id=1;
 
	}
	
if (para==3) { // for debitor  
		
		if (ref_id==0) { // when vendor is not present 
			commonobj.groupCode=$('#cash_code1').val();
			commonobj.accountID=0;
			commonobj.referenceID=0;
			commonobj.id=1;
			cash_sale=1;
			
		}else {
			cash_sale=0;// when vendor present 
			commonobj.groupCode=group_code;
			commonobj.accountID=0;
			commonobj.referenceID=ref_id;
			commonobj.id=1;
		}
			
 
	}
	
if (para==4) { // for discount 
	
	commonobj.groupCode=group_code;
	commonobj.accountID=0;
	commonobj.referenceID=0;
	commonobj.id=1;
	 
}
if (para==5) { // for cash 
	
	commonobj.groupCode=group_code;
	commonobj.accountID=0;
	commonobj.referenceID=0;
	commonobj.id=1;
	 
}

if (para==6) { // for card 
	
	commonobj.groupCode=group_code;
	commonobj.accountID=0;
	commonobj.referenceID=0;
	commonobj.id=1;
	 
}



$('#pleasewaitModal').modal('show');
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/vendor/searchledgerusinggroup.htm", commonobj, function(response) {
		$('#pleasewaitModal').modal('hide');
	 
		var status = JSON.parse(response);
		
		if (para==0) {// for duties and tax 
			console.log(" for duties and tax ");
			
			$.each(status, function(i) {
				 
			//	 $('#duties_html1').html("Cr-"+status[i].name);
				 $('#duties_ledger_id1').val(status[i].id);
			 
			});
		}	
		
		if (para==1) {// for round off 
			console.log("for round off ");
			$.each(status, function(i) {
				 
				// $('#round_html1').html(status[i].name);
				 $('#round_ledger_id1').val(status[i].id);
				 
			});
		}
	if (para==2) { // for sale 
				
		console.log("for sale ");
		 
 
		$.each(status, function(i) {
					 
				//	 $('#sales_html1').html("Cr-"+status[i].name);
					 $('#sales_ledger_id1').val(status[i].id);
					 
				});	
		 
			}
		
	if (para==3) {// for debitor 
		
		console.log("for debitor ");
		
				$.each(status, function(i) {
					 
					// $('#debitor_html1').html("Dr-"+status[i].name );
					 $('#debitor_ledger_id1').val(status[i].id);
					 
				});	
		 }

	if (para==4) {// for discount 
		console.log("for discount ");
		$.each(status, function(i) {
			 
			// $('#discount_html1').html("Dr-"+status[i].name );
			 $('#discount_ledger_id1').val(status[i].id);
			 
		});	
		 }
	if (para==5) {// for cash 
		console.log("for cash ");
		$.each(status, function(i) {
			 
			
			
		//	 $('#cash_ledger_html1').html("Dr-"+status[i].name );
			 $('#debitor_cahs_ledger_id1').val(status[i].id);
			 
		});	
		 }
	if (para==6) {// for card 
		console.log(" for card ");
		$.each(status, function(i) {
			 
			
			
			// $('#card_html1').html("Dr-"+status[i].name );
			 $('#card_ledger_id1').val(status[i].id);
			 
		});	
		 }
		
		//chngeResultStat(status);
	});
	
}

function sendInvEmail(id){
	var customarEmail =  $("#custEmail").val();
	/*alert(id + " "+customarEmail);*/
	if(customarEmail != ""){
		var storeName=  $("#store_name").val();
		var mailBody = getCashMemoText.mailRefer+" "+$("#salecustname").val()+",\n"+getCashMemoText.mailMsgSo;
		var mailSubjct =  storeName + getCashMemoText.mailSubjct;
		var emailBeanObj = {};
		emailBeanObj.toAddr = customarEmail;
		emailBeanObj.subject = mailSubjct;
		emailBeanObj.messageBody = mailBody;
		emailBeanObj.transId = id;
		emailBeanObj.transType = "SI";
		emailBeanObj.isAttachment = "Y";
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/mail/sendmail.htm", emailBeanObj, function(response) {
			console.log("send mail response:"+response);
		});
	}else{
		var status = '-22';
		chngeResultStat(status);
	}
}

function selAltMedAlert(itemid,itemname){
	$("#altItemId").val(itemid);
	$("#altItemName").val(itemname);
	var msg = "Do you want to take alternate medicine "+itemname+" ?";
	$("#confirmmessagesalealtmed").html(msg);
	$('#saleAltMedAlertModal').modal('show');
	
}
function notSelAltMed(){
	$('#alternateMedModal').modal('hide');
}
