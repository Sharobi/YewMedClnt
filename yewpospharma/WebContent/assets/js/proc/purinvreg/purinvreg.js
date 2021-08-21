function deletePurchaseInv(pinvid){
	$('#confirmModalPur').modal('show');
	$("#confirmid").val(pinvid);
	$("#confirmtype").val(0); // for delete set as zero
	
}

function DoConfirmPur(){
	var pinvid=$("#confirmid").val();
	var type=$("#confirmtype").val();
	if(type==1){// for post
		var CommonRelsetmapperObj = {};
		CommonRelsetmapperObj.purInvId = pinvid;

		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/purinv/postpurinv.htm", CommonRelsetmapperObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			if (response == '1') {
				document.getElementById('confirmmessagecont').innerHTML = getPurInvRegText.dataSucPost;
				showConfirmModal();
			} else {
				document.getElementById('confirmmessagecont').innerHTML = getPurInvRegText.dataNotPost;
				showConfirmModal();
			}

		});
	}
	if(type==0){// for delete
		var CommonRelsetmapperObj = {};
		CommonRelsetmapperObj.purInvId = pinvid;

		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/purinv/deletepurinv.htm", CommonRelsetmapperObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			if (response == '1') {
				document.getElementById('confirmmessagecont').innerHTML = getPurInvRegText.dataSucDelete;
				showConfirmModal();
			}
			else if (response == '-7') {
				document.getElementById('confirmmessagecont').innerHTML = getPurInvText.dataInUseErr+" deleted!";
				showConfirmModal();
			}
			else {
				document.getElementById('confirmmessagecont').innerHTML = getPurInvRegText.dataNotDelete;
				showConfirmModal();
			}

		});
	}
}

function postPurchaseInv(pinvid){
	$('#confirmModalPur').modal('show');
	$("#confirmid").val(pinvid);
	$("#confirmtype").val(1); // for post set as one
	
}

/* =============== Post All Functionality Starts =============== */

function postAllPI(){
	var purchase = {};
	var allpurchasedetails = [];
	/*$('#purinvtable > tbody  > tr input[type="checkbox"]').each(function() {
		if(this.checked){
			var purchasedetails = {};
			purchasedetails.purchaseId = this.id;
			allpurchasedetails.push(purchasedetails);
		}
	});*/
	if($("#example-select-all").prop("checked") == true){
		 var rows = $("#purinvtable").dataTable().fnGetNodes();
		 for(var i=0;i<rows.length;i++)
	        {      
	              var status=$.trim( $(rows[i]).find("td:eq(12)").html());
	              if(status == "Unposted"){
	            	var purchasedetails = {};
				    purchasedetails.purchaseId = rows[i].id;
				    allpurchasedetails.push(purchasedetails);
				}
	        }
	   }else{
		   $('#purinvtable > tbody  > tr input[type="checkbox"]').each(function() {
			if(this.checked){
				var purchasedetails = {};
				purchasedetails.purchaseId = this.id;
				allpurchasedetails.push(purchasedetails);
			}
		});
	   }
	purchase.purchaseDetails = allpurchasedetails;
	if($.isEmptyObject(purchase.purchaseDetails)){
		console.log("empty");
		document.getElementById('emptyalertmsg').innerHTML =  getFieldText.selectdata;
		$('#emptyAlertModal').modal('show');
	}else{
		//console.log("checkedid="+JSON.stringify(sale));
		$('#pleasewaitModal').modal('show');
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/purinv/postallselectedpurchaseinvoice.htm", purchase, function(response) {
			$('#pleasewaitModal').modal('hide');
			console.log(response);
			if (response == '0') {
				document.getElementById('confirmmessagecont').innerHTML = getPurInvRegText.dataNotPost;
				showConfirmModal();
				
			} else {
				document.getElementById('confirmmessagecont').innerHTML = getPurInvRegText.dataSucPost;
				showConfirmModal();
			}

		});
	}
}

/* =============== Post All Functionality Ends =============== */


function targetURL(){
	location.href = BASE_URL + '/purinv/loadpurinvregister.htm';
}

function setChange(d){
	if (!d.checked) {
		$("#example-select-all").prop("checked", false);
  }
}

function printPurchaseInv(purchaseid){
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/purinv/printPurchaseInvoice/" + purchaseid + ".htm", function(resp) {
		
		$("#printablediv").html(resp);
		localPrint();
	}, null);
	
	
}
function localPrint() {
    //alert("Print 80");
	var divToPrint = document.getElementById('printablediv');
	//document.getElementById('removePrint80px').style.display = 'none';
	newWin = window.open("");
	newWin.document.write(divToPrint.outerHTML);
	newWin.document.close();
	newWin.focus();
	newWin.print();
	//document.getElementById('printablediv').style.display = 'block';

	newWin.close();

}

//24.08.2019
$(document).ready(function() {
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
							custId : v.id,
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
			$("#salecustid").val(ui.item.custId);
			$("#salecustph").val(ui.item.phno);
		},
		change : function(	e,
							ui) {
			if (!(ui.item))
				//e.target.value = "";
				$("#salecustid").val(0);
			//$("#salecustph").val(0000000000);
		},
	});
	
	
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
								custId : v.id,
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
			//console.log("items=" + ui.item.creditLimit);
			$("#salecustid").val(ui.item.custId);
			$("#salecustname").val(ui.item.name);
		},
		change : function(	e,
							ui) {
			if (!(ui.item))
				//		e.target.value = "";
				$("#salecustid").val(0);
			//$("#salecustname").val("");
		},
	});

	
	
	function Reprint80mm(saleid){
		alert("Reprint80mm");
		if(dotMatrixPrint==1){
			$('#pleasewaitModal').modal('show');
			var CommonResultSetMapper = {};
			CommonResultSetMapper.saleId = saleid;
			CommonResultSetMapper.noteLineOne=n2;
			CommonResultSetMapper.noteLineTwo=n1;
			CommonResultSetMapper.isRePrint=1;
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/pos/salebillprint.htm", CommonResultSetMapper, function(res) {
				$('#pleasewaitModal').modal('hide');

			});

		}else{
			location.href = BASE_URL + "/reprintmemo/cashmemo80mm.htm?reprint=Y&backUrl=pos/reprintcashmemo&saleId=3465fg-trw73sxz-"+saleid+"-utew09-qdd55-4320jhhgrt";
		}
	}

});
