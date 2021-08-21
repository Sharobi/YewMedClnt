function deleteSalesRetInv(sinvid){
	$('#confirmModalPos').modal('show');
	$("#confirmid").val(sinvid);
	$("#confirmtype").val(0); // for delete set as zero
	
}
function postSalesReturnInv(sinvid){
	$('#confirmModalPos').modal('show');
	$("#confirmid").val(sinvid);
	$("#confirmtype").val(1); // for post set as one
	
}
function DoConfirmPos(){
	var sinvid=$("#confirmid").val();
	var type=$("#confirmtype").val();
	if(type==1){// for post
		var CommonRelsetmapperObj = {};
		CommonRelsetmapperObj.saleReturnId = sinvid;

		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/retunmemo/postretsalesinv.htm", CommonRelsetmapperObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			if (response == '1') {
				document.getElementById('confirmmessagecont').innerHTML = getRetCashMemoText.dataSucPost;
				showConfirmModal();
			} else {
				document.getElementById('confirmmessagecont').innerHTML = getRetCashMemoText.dataNotPost;
				showConfirmModal();
			}

		});
	}
	if(type==0){// for delete
		var CommonRelsetmapperObj = {};
		CommonRelsetmapperObj.saleReturnId = sinvid;

		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/retunmemo/deleteretsalesinv.htm", CommonRelsetmapperObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			if (response == '1') {
				document.getElementById('confirmmessagecont').innerHTML = getRetCashMemoText.dataSucDelete;
				showConfirmModal();
			} else {
				document.getElementById('confirmmessagecont').innerHTML = getRetCashMemoText.dataNotDelete;
				showConfirmModal();
			}

		});
	}
}
function postAllRetSelData(){
	var salereturn = {};
	var allsalereturndetails = [];
	/*$('#saleinvtable > tbody  > tr input[type="checkbox"]').each(function() {
		if(this.checked){
			var salereturndetails = {};
			salereturndetails.saleReturnId = this.id;
			allsalereturndetails.push(salereturndetails);
		}
	});*/
	if($("#example-select-all").prop("checked") == true){
		 var rows = $("#saleinvtable").dataTable().fnGetNodes();
		 for(var i=0;i<rows.length;i++)
	        {      
	              var status=$.trim( $(rows[i]).find("td:eq(10)").html());
	              if(status == "Unposted"){
	            	  var salereturndetails = {};
	      			  salereturndetails.saleReturnId = rows[i].id;
	      			  allsalereturndetails.push(salereturndetails);	            	       
	               }
	        }
	   }else{
		   $('#saleinvtable > tbody  > tr input[type="checkbox"]').each(function() {
				if(this.checked){
					var salereturndetails = {};
					salereturndetails.saleReturnId = this.id;
					allsalereturndetails.push(salereturndetails);	     
				}
			});
	   }
	salereturn.saleReturnDetails = allsalereturndetails;
	if($.isEmptyObject( salereturn.saleReturnDetails )){
		document.getElementById('emptyalertmsg').innerHTML =  getFieldText.selectdata;
		$('#emptyAlertModal').modal('show');
		console.log("empty");
	}else{
		//console.log("checkedid="+JSON.stringify(sale));
		$('#pleasewaitModal').modal('show');
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/retunmemo/postallselectedreturnsalesinvoice.htm", salereturn, function(response) {
			$('#pleasewaitModal').modal('hide');
			console.log(response);
			if (response == '0') {
				document.getElementById('confirmmessagecont').innerHTML = getRetCashMemoText.dataNotPost;
				showConfirmModal();
				
			} else {
				document.getElementById('confirmmessagecont').innerHTML = getRetCashMemoText.dataSucPost;
				showConfirmModal();
			}

		});
	}
}
function targetURL(){
	location.href = BASE_URL + '/retunmemo/modifyreturnmemo.htm';
}

function setChange(d){
	if (!d.checked) {
		$("#example-select-all").prop("checked", false);
  }
}