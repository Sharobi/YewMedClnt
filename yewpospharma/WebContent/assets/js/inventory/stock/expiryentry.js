
function searchExpiryById()
{
	if( (new Date($('#stdate').val()).getTime() > new Date($('#enddate').val()).getTime()))
	{
		document.getElementById('alertMsg').innerHTML = getFieldText.frmdtGrtEnddt;
	}
	else
	{
		var CommonRelsetmapperObj = {};
		CommonRelsetmapperObj.startDate = $("#stdate").val();
		CommonRelsetmapperObj.endDate = $("#enddate").val();
		CommonRelsetmapperObj.distributorId = $("#seldistributor").val();
		CommonRelsetmapperObj.asOnDate = $("#invDt").val();

		var distributorid = $("#seldistributor").val(); //new
		$("#seldistributor").val(distributorid); //new
		selectedDistributor = distributorid; // new
		//setAllCheck();//new
		
		
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/expiry/searchexpiryinvoicebyid.htm", CommonRelsetmapperObj, function(response) {
			
			 var expdetails = JSON.parse(response);
			
		     var exsistingRows = []; //new
		     var olddistributorid;//new
		     $('#expitem > tbody > tr').each(function() { //new
			       var itemid = this.id;
			       if($("#" + itemid + "_modretcheck").prop("checked") == false){
			    	   $(this).remove();
			       }else{
			    	   olddistributorid = $("#" + itemid).find('#expdistributor').val();
			    	   exsistingRows.push(itemid);
			       }
			     }); 
		     
			    if(expdetails.length>0){//new
		         if(distributorid == olddistributorid){ 
		        	 $("#example-select-all").prop('checked', false);  
		        	 $("#example-select-all").attr("disabled", false);
		         }else{
		        	 $("#example-select-all").prop('checked', false);  
		        	 $("#example-select-all").attr("disabled", true);
		         }
			    }
			 
			for ( var i = 0; i < expdetails.length; i++) {
				 var expdetail = expdetails[i];
				 var check_counter = 0;
				
				 if(exsistingRows.length>0){// new
				 for(var j=0;j<exsistingRows.length;j++){ // new
	            	//var existing_unique_key = this.id;
	            	   if(exsistingRows[j]==expdetail.itemUniqueKey)
	            	    {
	            		   check_counter = 1;
	            	    }
				   }
				 }
				 
	            if(check_counter==0)
	            {
					$(".selectedVendor option[value='" + expdetail.distributorId + "']").attr("selected", "selected");	
					tr = "<tr id='"+expdetail.itemUniqueKey+"'><td><input id='" + expdetail.itemUniqueKey + "_modretcheck' class='chkboxcheked' type='checkbox' onclick='call_vendor(&quot;"+expdetail.itemUniqueKey+"&quot;)'></td>";
					row1 = '<td id="tbl_item_name">' + expdetail.itemName + '</td>';
					row2 = '<td id="tbl_item_id" class="hide">' + expdetail.itemId + '</td>';
					row3 = '<td id="tbl_batch_no">' + expdetail.batchNo + '</td>';
					row4 = '<td id="tbl_exp">' + expdetail.expiryDateFormat + '</td>';
					row5 = '<td id="tbl_punit_name">' + expdetail.packUnitName + '</td>';
					row6 = '<td id="tbl_punit_id" class="hide">' + expdetail.packUnitId + '</td>';
					row7 = '<td id="tbl_rack_name">' + expdetail.rackName + '</td>';
					row8 = '<td id="tbl_rack_id" class="hide">' + expdetail.rackId + '</td>';
					row9 = '<td id="tbl_stock_qty">' + expdetail.stockQty + '</td>';
					row10 = '<td id="tbl_mrp">' + expdetail.mrp + '</td>';
					row11 = '<td id="tbl_rate">' + expdetail.rate + '</td>';
					row12 = '<td>'+$("#vendorListt").html()+'</td>';
					var dateString = expdetail.expiryDate;
					var currentTime = new Date(dateString );
					var month = currentTime.getMonth() + 1;
					var day = currentTime.getDate();
					var year = currentTime.getFullYear();
					var expDate = year + "-" + month + "-" + day;
					row13 = '<td id="tbl_expdt" class="hide">' + expDate + '</td>';
					row14 = '<td id="tbl_pqty" class="hide">' + expdetail.packQty + '</td>';
					row15 = '<td id="tbl_cnvrsn" class="hide">'+expdetail.conversion+'</td>';
					row16 = '<td id="tbl_lqty" class="hide">' + expdetail.looseQty + '</td>';
					row17 = '<td id="tbl_free" class="hide">' + expdetail.freeQty + '</td>';
					row18 = '<td id="tbl_amount" class="hide">' + expdetail.amount + '</td>';
					row19 = '<td id="tbl_netcontent" class="hide">' + expdetail.netContent + '</td>';
					row20 = '<td id="tbl_calclqty" class="hide">' + expdetail.calculateLooseQty + '</td>';
		
					tr = tr + row1 + row2 + row3 + row4 + row5 + row6 + row7 + row8 + row9 + row10 + row11 + row12 + row13 + row14 + row15 + row16 + row17 + row18 + row19 + row20 +'</tr>';
					
					$("#expitem tbody").append(tr);
					$("#" + expdetail.itemUniqueKey).find('#vendorName').attr("id","expdistributor");
					$("#" + expdetail.itemUniqueKey).find('#expdistributor').attr("name","distributorId");
					$("#" + expdetail.itemUniqueKey).find('#expdistributor').attr("class","form-control-trx");
					$("#" + expdetail.itemUniqueKey).find('#expdistributor').attr("onchange","checkSelection()");
					$(".selectedVendor option[value='" + expdetail.distributorId + "']").removeAttr("selected");
					
					 
	            }
	            /*else{
	            }*/
			}
			
		});
	}
	
}

function getcheckedexpiryitemlist() {
	var len=$('.chkboxcheked:checked').length;
	var count = 0;
	var ExpiryObj = {};
	var ExpiryDetailsObjArr = [];
	var i = 0;
	var j=0;
	var expId = $("#expId").val();
	var total_amt=0;
	var first=0;
    var dif_vendor=0;
    $("#alertMsg").text(" ");
	// loop start
	$('#expitem > tbody > tr').each(function() {
		var ExpiryDetailsObj = {};
		var itemid = this.id;
		console.log("len="+len);
		
		if(len==0){
			$("#alertMsg").text("Please check at least one item.");
		}
		else if(len==-1){
			$("#alertMsg").text("Please check at least one item.");
			$("#alertMsg").text("Please select vendor of checked item.");
		}
		else{
			$("#alertMsg").text("");
			if ($("#" + itemid + "_modretcheck").is(":checked")) {
				 j++;
				if($("#" + itemid).find('#expdistributor').val()==0)
				{
					$(this).attr('checked', false);
					len=-1;
				}
				else
				{
					if (j==1) {// for vendor call first
						 
						first=$("#" + itemid).find('#expdistributor').val();
					}
					if (first==$("#" + itemid).find('#expdistributor').val()) {// checking same vendor and calulation 
					 
						var expiryItemDetails = JSON.stringify($("#" + itemid + "_modretcheck").val());
						//alert($.parseJSON(expiryItemDetails).itemName);
						ExpiryDetailsObj.id=0;
						ExpiryDetailsObj.itemId=$("#" + itemid).find('#tbl_item_id').text();
						ExpiryDetailsObj.itemName=$("#" + itemid).find('#tbl_item_name').text();
						ExpiryDetailsObj.itemUniqueKey=itemid;
						ExpiryDetailsObj.batchNo=$("#" + itemid).find('#tbl_batch_no').text();
						ExpiryDetailsObj.expiryDateFormat=$("#" + itemid).find('#tbl_exp').text();
						ExpiryDetailsObj.packUnitName=$("#" + itemid).find('#tbl_punit_name').text();
						ExpiryDetailsObj.packUnitId=$("#" + itemid).find('#tbl_punit_id').text();
						ExpiryDetailsObj.rackName=$("#" + itemid).find('#tbl_rack_name').text();
						ExpiryDetailsObj.rackId=$("#" + itemid).find('#tbl_rack_id').text();
						ExpiryDetailsObj.stockQty=$("#" + itemid).find('#tbl_stock_qty').text();
						ExpiryDetailsObj.mrp=$("#" + itemid).find('#tbl_mrp').text();
						ExpiryDetailsObj.rate=$("#" + itemid).find('#tbl_rate').text();
						ExpiryDetailsObj.distributorId=$("#" + itemid).find('#expdistributor').val();
						ExpiryDetailsObj.distributorName=$("#" + itemid).find('#expdistributor option:selected').text();
						ExpiryDetailsObj.expiryDate=$("#" + itemid).find('#tbl_expdt').text();
						ExpiryDetailsObj.packQty=$("#" + itemid).find('#tbl_pqty').text();
						ExpiryDetailsObj.conversion=$("#" + itemid).find('#tbl_cnvrsn').text();
						ExpiryDetailsObj.looseQty=$("#" + itemid).find('#tbl_lqty').text();
						ExpiryDetailsObj.freeQty=$("#" + itemid).find('#tbl_free').text();
						ExpiryDetailsObj.amount=$("#" + itemid).find('#tbl_amount').text();
						ExpiryDetailsObj.netContent=$("#" + itemid).find('#tbl_netcontent').text();
						ExpiryDetailsObj.calculateLooseQty=$("#" + itemid).find('#tbl_calclqty').text();
						ExpiryDetailsObjArr[i]=ExpiryDetailsObj;
						i=i+1;
						
						//total_amt= parseFloat(total_amt)+( $("#" + itemid).find('#tbl_pqty').text() * parseFloat($("#" + itemid).find('#tbl_rate').text()));
						var itemPrice = (Number($("#" + itemid).find('#tbl_pqty').text() *  $("#" + itemid).find('#tbl_cnvrsn').text()) + Number($("#" + itemid).find('#tbl_lqty').text())) * ( parseFloat($("#" + itemid).find('#tbl_rate').text()) / $("#" + itemid).find('#tbl_cnvrsn').text());     
						total_amt= parseFloat(total_amt)+(parseFloat(itemPrice));				
						
					}else {
				 
						dif_vendor=1;
						
					}
					
					
					
				
				}// else
			}
		}// else
	});// loop end 

	ExpiryObj.expiryDetails=ExpiryDetailsObjArr;
	ExpiryObj.id=expId;
	if (expId == 0) {// create

	} else {// edit
		ExpiryObj.invNo = $("#expiryInvoiceNo").val();
	}
	ExpiryObj.fromDate=$("#stdate").val();
	ExpiryObj.toDate=$("#enddate").val();
	ExpiryObj.invDate=$("#invDt").val();
	ExpiryObj.remarks=$("#remarks").val();
	

	ExpiryObj.cr_amount= total_amt;
	ExpiryObj.dr_amount= total_amt;
	ExpiryObj.cr_account_id=$('#exp_ledger_id').val();
	ExpiryObj.dr_account_id= $('#creditor_legder_id').val();
	
	
	console.log("ExpiryObj json: " + JSON.stringify(ExpiryObj));
	if(len>0 && dif_vendor==0){
		
		
		$('#pleasewaitModal').modal('show');
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/expiry/createorupdateexpiryinvoice.htm", ExpiryObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			console.log("save pur inv id="+response);
			if (response == '0') {
				if (expId == 0)
				{
					document.getElementById('confirmmessagecont').innerHTML = getStockInvText.expDataNotAdd;
				}
				else
				{
					document.getElementById('confirmmessagecont').innerHTML = getStockInvText.expDataNotUpdate;
				}
				$("#confirmval").val(0);
				showConfirmModal();
			} else {
				if (expId == 0)
				{
					document.getElementById('confirmmessagecont').innerHTML = getStockInvText.expDataSucAdd;
				}
				else
				{
					document.getElementById('confirmmessagecont').innerHTML = getStockInvText.expDataSucUpdate;
				}
				$("#confirmval").val(response);
				showConfirmModal();
			}
	
		});
	}
	else{
		$("#alertMsg").text("Please select all same vendor");
	}
	
}

function postExpiryInv(){
	var expId = $("#expId").val();
	var len=$('.chkboxcheked:checked').length;
	if(len>0)
	{
		var CommonRelsetmapperObj = {};
		CommonRelsetmapperObj.expiryId = expId;
	
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/expiry/postexpiryinv.htm", CommonRelsetmapperObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			if (response == '1') {
				$("#confirmval").val(0);
				document.getElementById('confirmmessagecont').innerHTML = getStockInvText.expDataSucPost;
				showConfirmModal();
			} else {
				$("#confirmval").val(0);
				document.getElementById('confirmmessagecont').innerHTML = getStockInvText.expDataNotPost;
				showConfirmModal();
			}
	
		});
	}
	else
	{
		$("#confirmval").val(2);
		document.getElementById('confirmmessagecont').innerHTML = getStockInvText.noItemChckErr;
		showConfirmModal();
	}
}

function deleteExpiryInv(){
	var expId = $("#expId").val();
   document.getElementById('confirmId').value=expId;
   $('#confirmModal').modal('show');   
}

function DoConfirm() {
	var expid = document.getElementById('confirmId').value;

	var CommonRelsetmapperObj = {};
	CommonRelsetmapperObj.expiryId = expid;

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/expiry/deleteexpinv.htm", CommonRelsetmapperObj, function(response) {
		$('#pleasewaitModal').modal('hide');
		if (response == '1') {
			$("#confirmval").val(0);
			document.getElementById('confirmmessagecont').innerHTML = getStockInvText.expDataSucDelete;
			showConfirmModal();
		} else {
			$("#confirmval").val(-1);
			document.getElementById('confirmmessagecont').innerHTML = getStockInvText.expDataNotDelete;
			showConfirmModal();
		}

	});
}

function newExpiryInv(){
	location.href = BASE_URL + '/stock/expissue.htm';
}

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}

function targetURL(){
	var result=$("#confirmval").val();
	console.log("save expiry inv id targetURL="+result);
	if(result==0){
		location.href = BASE_URL + '/stock/expissue.htm';
	}
	else if(result==-1){
		location.href = "#";
	}
	else
	{
			location.href = BASE_URL + '/expiry/loadexpinvoicedet/'+result+'.htm';
	}
}


/*
 * add on 2_3_2018 
 */
function isEmpty(val) {
    return (val === undefined || val == null || val.length <= 0) ? true : false;
}

/*
 * for legder search
 */


function getvendorledger_expire(group_code,acc_id,ref_id,para)
{
	 //var keyword=ref_id.toString();
	//  var trackname=keyword.split("_");
	/*
	 * 	commonobj.id=1; is call another procedure 
	 */
	
 
	var commonobj={};
	if (para==0) { // for party ledger 
		
		commonobj.groupCode=group_code;
		commonobj.accountID=0;
		commonobj.referenceID=ref_id;
		commonobj.id=1;
		 
	 
	}
	
	
	if (para==1) { // for exp ledger
		
		commonobj.groupCode=group_code;
		commonobj.accountID=0;
		commonobj.referenceID=0;
		commonobj.id=1;
		 
	}
 
	

//$('#pleasewaitModal').modal('show');
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/vendor/searchledgerusinggroup.htm", commonobj, function(response) {
		//$('#pleasewaitModal').modal('hide');
	 
		var status = JSON.parse(response);
		
		if (para==0) {// for duties and tax 
			console.log("para0");
			
			$.each(status, function(i) {
				 
				 $('#creditor_legder_id_val').val(""+status[i].name);
				 $('#creditor_legder_id').val(status[i].id);
			 
			});
		}	
		
		if (para==1) {// for round off 
			console.log("para1");
			$.each(status, function(i) {
				 
				 $('#exp_ledger_id_val').val(status[i].name);
				 $('#exp_ledger_id').val(status[i].id);
				 
			});
		}
 
	
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



function call_vendor(id)
{
		var len=$('.chkboxcheked:checked').length;
		$("#alertMsg").text("");
		var first=0;
		var i=0;
		var dif_vendor=0;
		
		var total_sale_rate=0;

		
		 if ($("#" + id + "_modretcheck").prop("checked") == false) {
			 $("#example-select-all").attr('checked', false);
		 }
		
		
		
		
 
	if (len!=0) {
		
		
		$('#expitem > tbody > tr').each(function() {
			var itemid = this.id;
		     if ($("#" + itemid + "_modretcheck").is(":checked")) {
					 i++;
				 
				 if($("#" + itemid).find('#expdistributor').val()==0)
					{
						$(this).attr('checked', false);
						len=-1;
					
					}else {
						
						if (i==1) {// for vendor call first
							 
							first=$("#" + itemid).find('#expdistributor').val();
						}
						if (first==$("#" + itemid).find('#expdistributor').val()) {// checking same vendor and calulation 
						 
							
							// total_sale_rate=parseFloat(total_sale_rate)+parseFloat($("#" + itemid).find('#tbl_rate').text());
							  // total_sale_rate=parseFloat(total_sale_rate)+parseFloat( $("#" + itemid).find('#tbl_pqty').text() * $("#" + itemid).find('#tbl_rate').text() );

							var itemPrice = (Number($("#" + itemid).find('#tbl_pqty').text() *  $("#" + itemid).find('#tbl_cnvrsn').text()) + Number($("#" + itemid).find('#tbl_lqty').text())) * ( parseFloat($("#" + itemid).find('#tbl_rate').text()) / $("#" + itemid).find('#tbl_cnvrsn').text());     
							total_sale_rate= parseFloat(total_sale_rate)+(parseFloat(itemPrice));
							
						}else {
					 
							dif_vendor=1;
							//$('this').prop('checked', false);
							$("#alertMsg").text("Please select all same vendor");
						}
						
					}
				 
				}
		 
		});
		
		if (dif_vendor==1) {
			$("#" + id + "_modretcheck").prop('checked', false);
		}else {
			getvendorledger_expire($('#creditor_code1').val(),0,first,0);// for expe	
		}
		
	 
		$('#dr_amount').val(total_sale_rate.toFixed(2));
		$('#cr_amount').val(total_sale_rate.toFixed(2));
	}else{
		$('#dr_amount').val("");
		$('#cr_amount').val("");	
	}
	
	
	
			
}

function bulkSelectCalculation(){
	var len=$('.chkboxcheked:checked').length;
	$("#alertMsg").text("");
	var first=0;
	var i=0;
	var dif_vendor=0;
	var total_sale_rate=0;
    var id="";
    if (len!=0) {
	
	  $('#expitem > tbody > tr').each(function() {
		var itemid = this.id;
	     if ($("#" + itemid + "_modretcheck").is(":checked")) {
				 i++;
			 
			 if($("#" + itemid).find('#expdistributor').val()==0)
				{
					$(this).attr('checked', false);
					len=-1;
				
				}else {
					
					if (i==1) {// for vendor call first
						 
						first=$("#" + itemid).find('#expdistributor').val();
					}
					if (first==$("#" + itemid).find('#expdistributor').val()) {// checking same vendor and calulation 
					 
						
						 //total_sale_rate=parseFloat(total_sale_rate)+parseFloat($("#" + itemid).find('#tbl_rate').text());
						  // total_sale_rate=parseFloat(total_sale_rate)+parseFloat( $("#" + itemid).find('#tbl_pqty').text() * $("#" + itemid).find('#tbl_rate').text() );
						
						var itemPrice = (Number($("#" + itemid).find('#tbl_pqty').text() *  $("#" + itemid).find('#tbl_cnvrsn').text()) + Number($("#" + itemid).find('#tbl_lqty').text())) * ( parseFloat($("#" + itemid).find('#tbl_rate').text()) / $("#" + itemid).find('#tbl_cnvrsn').text());     
						total_sale_rate= parseFloat(total_sale_rate)+(parseFloat(itemPrice));
					
					
					}else {
				 
						dif_vendor=1;
						id=itemid;
						//$('this').prop('checked', false);
						$("#alertMsg").text("Please select all same vendor");
					}
					
				}
			 
			}
	 
	});
	  
	  
	  
	
	if (dif_vendor==1) {
		$("#" + id + "_modretcheck").prop('checked', false);
	}else {
		getvendorledger_expire($('#creditor_code1').val(),0,first,0);// for expe	
	}
	$('#dr_amount').val(total_sale_rate.toFixed(2));
	$('#cr_amount').val(total_sale_rate.toFixed(2));
}else{
	$('#dr_amount').val("");
	$('#cr_amount').val("");	
}
	
}
 


function  setAllCheck(){
	$("#seldistributor").val(selectedDistributor);
	  if(selectedDistributor == "0" ){
		  $("#example-select-all").attr('checked', false);  
		 $("#example-select-all").attr("disabled", true);
	  }else if(selectedDistributor == "" ){
		  $("#example-select-all").attr('checked', false); 
		  $("#example-select-all").attr("disabled", true);
	  }else{
		  $("#example-select-all").attr("disabled", false);
	  }
	  
	  var rowCount = $('#expitem tr').length;
	  var i=1;
	  $('#expitem > tbody > tr').each(function() {
		  var itemid = this.id;
		  if ($("#" + itemid + "_modretcheck").is(":checked")) {
		     i++;	 
		  }
      });
	   if(rowCount == i && rowCount >1){
		  $("#example-select-all").attr('checked', true);  
	  }
	  
   } 

$('#example-select-all').on('click', function(){
     // Check/uncheck all checkboxes in the table
    // var rows = table.rows({ 'search': 'applied' }).nodes();
    // $('input[type="checkbox"]', rows).prop('checked', this.checked);
	if ($('#example-select-all').prop("checked") == true) {
	   	  $('#expitem > tbody > tr').each(function() {
		         var itemid = this.id;
		         $("#" + itemid + "_modretcheck").prop("checked", true);
		        
	         });
	     }else{
	   	   $('#expitem > tbody > tr').each(function() {
			       var itemid = this.id;
			       $("#" + itemid + "_modretcheck").prop('checked', false);  
			       
	              });
	     }
	    bulkSelectCalculation();
  
  });

function checkSelection(){
	 var selectedDistributors = [];
	$('#expitem > tbody > tr').each(function() {
		var itemid = this.id;
		var distributorid = $("#" + itemid).find('#expdistributor').val();
		selectedDistributors.push(distributorid);
		
	});
	var status = selectedDistributors.allValuesSame(); 
	if(status == true){
		 $("#example-select-all").attr("disabled", false);
	}else{
		 $("#example-select-all").prop('checked', false);  
		 $("#example-select-all").attr("disabled", true);
	}
	
}

Array.prototype.allValuesSame = function() {
    for(var i = 1; i < this.length; i++)
         {
        if(this[i] !== this[0])
            return false;
       }
     return true;
}




