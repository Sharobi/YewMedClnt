function fieldValidation(field_names) {
	var error_counter = 0;
	var first_error_field = "";
	for ( var i = 0; i < field_names.length; i++) {
		var field_id = field_names[i][0];
		var field_name = field_names[i][1];
		var field_value = $("#" + field_id + "").val();
		if (field_value == 0 || field_value == "") {
			if (error_counter == 0) {
				first_error_field = field_name;
			}
			if (error_counter > 0) {
				document.getElementById('alertMsg').innerHTML = first_error_field + " " + getFieldText.fieldReq;
			} else {
				document.getElementById('alertMsg').innerHTML = field_name + " " + getFieldText.fieldReq;
			}
			error_counter++;
		}

	}
	return error_counter;
}

function fieldValidationWithAlertDivId(	field_names,
										alertIdName) {
	var error_counter = 0;
	var first_error_field = "";
	for ( var i = 0; i < field_names.length; i++) {
		var field_id = field_names[i][0];
		var field_name = field_names[i][1];
		var field_value = $("#" + field_id + "").val();
		if (field_value == 0 || field_value == "") {
			if (error_counter == 0) {
				first_error_field = field_name;
			}
			if (error_counter > 0) {
				document.getElementById(alertIdName).innerHTML = first_error_field + " " + getFieldText.fieldReq;
			} else {
				document.getElementById(alertIdName).innerHTML = field_name + " " + getFieldText.fieldReq;
			}
			error_counter++;
		}

	}
	return error_counter;
}

function strtDtGrtEndDt() {
	$("#search_btn").click(function() {
		if ((new Date($('#stdate').val()).getTime() > new Date($('#enddate').val()).getTime())) {
			document.getElementById('alertMsg').innerHTML = getFieldText.frmdtGrtEnddt;
			e.preventDefault();
		} else {
			$("#searchForm").submit();
		}
	});
}

function chngeResultStat(status) {
	if (status.id > 0) {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.greaterthanzero;
		showConfirmModal();
	} else if (status.id == 0) {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.zero;
		showConfirmModal();
	} else if (status.id == -1) {
		
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.minusone;
		showConfirmModal();
	} else if (status.id == -2) {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.minustwo;
		showConfirmModal();
	} else if (status.id == -3) {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.minusthree;
		showConfirmModal();
	}else if (status.id == -10) {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.mailsentsuccess;
		showConfirmModal();
	}
	else if (status.id == -11) {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.dataPostSuccess;
		showConfirmModal();
	}
	else if (status.id == -12) {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.dataCloseSuccess;
		showConfirmModal();
	}
	else if (status.id == -22) {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.noemailfound;
		showConfirmModal();
	}
	else {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.zero;
		showConfirmModal();
	}

}

function chngeResultStatForNewItem(status) {
	if (status.id > 0) {
		document.getElementById('confirmmessagecontNewItem').innerHTML = getFieldText.greaterthanzero;
		showConfirmModalNewItem();
	} else if (status.id == 0) {
		document.getElementById('confirmmessagecontNewItem').innerHTML = getFieldText.zero;
		showConfirmModalNewItem();
	} else if (status.id == -1) {
		document.getElementById('confirmmessagecontNewItem').innerHTML = getFieldText.minusone;
		showConfirmModalNewItem();
	} else if (status.id == -2) {
		document.getElementById('confirmmessagecontNewItem').innerHTML = getFieldText.minustwo;
		showConfirmModalNewItem();
	} else if (status.id == -3) {
		document.getElementById('confirmmessagecontNewItem').innerHTML = getFieldText.minusthree;
		showConfirmModalNewItem();
	}else if (status.id == -10) {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.mailsentsuccess;
		showConfirmModalNewItem();
	} 
	else if (status.id == -11) {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.dataPostSuccess;
		showConfirmModalNewItem();
	}
	else if (status.id == -12) {
		document.getElementById('confirmmessagecont').innerHTML = getFieldText.dataCloseSuccess;
		showConfirmModalNewItem();
	}else {
		document.getElementById('confirmmessagecontNewItem').innerHTML = getFieldText.zero;
		showConfirmModalNewItem();
	}

}

/*####################### SHORTCUT KEYS ############################*/

var pressedkeys = ",";

$(window).blur(function(){
  //console.log("window blurred");
  pressedkeys = ",";
})
var keys=",";
  for(var i=8;i<=222;i++){
    keys+=i+",";
  }
$(this).on( 'keyup', function( e ){
  pressedkeys=pressedkeys.replace(","+e.which+",",",");
});
function containKeys(nokeys,press) {
  var ks = press.split(",");
  for (var i = 0; i < ks.length; i++) {
    if(nokeys.includes(","+ks[i]+",")) {
      return true;
    }
  }
  return false;
}

/* ============ For Home page "Main" Function ============ */

$(document).bind("keydown", function(e) {
	//alert("add_btn");
	if(!pressedkeys.includes(","+e.which+",")){
        pressedkeys += e.which+",";
      }
	/* ============ For "POS" Button (Using F4) ============ */
	if (e.which == 115) {
		var nokeys=keys.replace(",115,",",");
        if(!containKeys(nokeys,pressedkeys))
        {
        	$("#nav-accordion").find("a[href$='/pos/cashmemo.htm']").get(0).click();
        }
		return false;
	}
	
	/* ============ For "Add" Button (Using F8) ============ */
	if (e.which == 119) {
		var nokeys=keys.replace(",119,",",");
        if(!containKeys(nokeys,pressedkeys))
        {
        	$("#nav-accordion").find("a[href$='/purinv/loadpurinvoice.htm']").get(0).click();
        }
		return false;
	}
	
	
	/* ============ For "Add" Button (Using F9) ============ */
	if (e.which == 120) {
		var nokeys=keys.replace(",120,",",");
        if(!containKeys(nokeys,pressedkeys))
        {
        	$("#nav-accordion").find("a[href$='/posreport/salesummary.htm']").get(0).click();
        }
		return false;
	}
});

/* ============================ Home page End ======================== */

/* ============ For "Purchase Invoice" Function ============ */
$(document).bind("keydown", function(e) {
	//alert("add_btn");
	if(!pressedkeys.includes(","+e.which+",")){
        pressedkeys += e.which+",";
      }
	/* ============ For "Add" Button (Using Alt+A) ============ */
	if (e.altKey && e.which == 65) {
		var nokeys=keys.replace(",65,",",");
        nokeys = nokeys.replace(",18,",",");
        if(!containKeys(nokeys,pressedkeys))
        {
        	$("#add_btn").click();
        }
		return false;
	}
	

	/* ============ For "Edit_btn" Button (Using Ctrl+U) ============ */
	if (e.altKey && e.which == 85) {
		var nokeys=keys.replace(",85,",",");
        nokeys = nokeys.replace(",18,",",");
        if(!containKeys(nokeys,pressedkeys))
        {
        	$("#edit_btn").click();
        }
		return false;
	}
/* ============ For "Clear" Button (Using Ctrl+Shift+C) ============ */
		if (e.ctrlKey && e.shiftKey && e.which == 67) {
			var nokeys=keys.replace(",67,",",");
	        nokeys = nokeys.replace(",16,",",");
	        nokeys = nokeys.replace(",17,",",");
	        if(!containKeys(nokeys,pressedkeys))
	        {
			$("#clear_btn").click();
	        }
			return false;
		}
		
		
/* ========= For "New" Button (Using Alt+N) ========= */
		if (e.altKey && e.which == 78) {
			var nokeys=keys.replace(",78,",",");
	        nokeys = nokeys.replace(",18,",",");
	        if(!containKeys(nokeys,pressedkeys))
	        {
	        	if(window.location.href.includes("/cashmemo")) {
	        		openNewCashMemo();
	        	}else if(window.location.href.includes("loadpurinvoicedet/")) {
	        		toCashMemo();
	        	} else if(window.location.href.includes("/loadpurinvoice")) {
	        		newPurInv();
	        	}
	        }
			return false;
		}
			/*if (e.altKey && e.which == 78) {
				newPurInv();
				return false;
			}*/
			
			
/* ========= For "Pay" Button (Using Alt+M) ========= */
				if (e.altKey && e.which == 77) {
					var nokeys=keys.replace(",77,",",");
			        nokeys = nokeys.replace(",18,",",");
			        if(!containKeys(nokeys,pressedkeys))
			        {
			        	if(window.location.href.includes("/cashmemo")) {
			        		paySaleInvModal();
			        	} else if(window.location.href.includes("/loadpurinvoice")) {
			        		$("#save_btn").click();//deletePurchaseInv()
			        	}
					
			        }
					return false;
				}
				/*if (e.altKey && e.which == 77) {
					//paySaleInvModal();
					$("#save_btn").click();
					return false;
				}*/
				
/* ============ For "Item" Button (Using ALt+I) ============ */
				if (e.altKey && e.which == 73) {
					var nokeys=keys.replace(",73,",",");
			        nokeys = nokeys.replace(",18,",",");
			        if(!containKeys(nokeys,pressedkeys))
			        {
			        	addNewItem();
					//$("#addNewItemBtn").click();
			        }
					return false;
				}
				
				/*======== Purchase Invoice Details Start ========*/
				/* ============ For "Delete" Button (Using Alt+D) ============ */
				if (e.altKey && e.which == 68) {
					var nokeys=keys.replace(",68,",",");
				    nokeys = nokeys.replace(",18,",",");
				    if(!containKeys(nokeys,pressedkeys))
				    {
				    	deletePurchaseInv();
				    	//$("#add_btn").click();
				    }
					return false;
				}
				
				/* ============ For "Post" Button (Using Alt+Shift+P) ============ */
				if (e.altKey && e.shiftKey && e.which == 80) {
					var nokeys=keys.replace(",80,",",");
			        nokeys = nokeys.replace(",16,",",");
			        nokeys = nokeys.replace(",18,",",");
			        if(!containKeys(nokeys,pressedkeys))
			        {
			        	postPurchaseInv();
			        	//$("#add_btn").click();
			        }
					return false;
				}
				
				/* ============ For "Ret" Button (Using Alt+R) ============ */
				if (e.altKey && e.which == 82) {
					var nokeys=keys.replace(",82,",",");
			        nokeys = nokeys.replace(",18,",",");
			        if(!containKeys(nokeys,pressedkeys))
			        {
			        	//openretadjmod();
			        	$("#retAdj_btn").click();
			        }
					return false;
				}
				
				/*======== Purchase Invoice Details End ========*/
	
});

/* ============================ Purchase Invoice End ======================== */

//new added 25.7.2019
function changeChildStore(storeId){
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/home/changeStore/" + storeId + ".htm", function(resp) {
	console.log(resp);
	if(resp == "success")
		location.reload(true);
	}, null);
}