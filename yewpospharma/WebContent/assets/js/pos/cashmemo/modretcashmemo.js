function deleteSalesInv(sinvid){
	$('#confirmModalPos').modal('show');
	$("#confirmid").val(sinvid);
	$("#confirmtype").val(0); // for delete set as zero
	
}
function postSalesInv(sinvid){
	$('#confirmModalPos').modal('show');
	$("#confirmid").val(sinvid);
	$("#confirmtype").val(1); // for post set as one
	
}
function DoConfirmPos(){
	var sinvid=$("#confirmid").val();
	var type=$("#confirmtype").val();
	if(type==1){// for post
		var CommonRelsetmapperObj = {};
		CommonRelsetmapperObj.saleId = sinvid;

		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/pos/postsalesinv.htm", CommonRelsetmapperObj, function(response) {
			$('#pleasewaitModal').modal('hide');
			if (response == '1') {
				document.getElementById('confirmmessagecont').innerHTML = getCashMemoText.dataSucPost;
				showConfirmModal();
			} else {
				document.getElementById('confirmmessagecont').innerHTML = getCashMemoText.dataNotPost;
				showConfirmModal();
			}

		});
	}
	if(type==0){// for delete
		var CommonRelsetmapperObj = {};
		CommonRelsetmapperObj.saleId = sinvid;

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
}
function postAllSelData(){
	var sale = {};
	var allsaledetails = [];
	/*$('#saleinvtable > tbody  > tr input[type="checkbox"]').each(function() {
		if(this.checked){
			var saledetails = {};
			saledetails.saleId = this.id;
			allsaledetails.push(saledetails);
		}
	});*/
	  if($("#example-select-all").prop("checked") == true){
			 var rows = $("#saleinvtable").dataTable().fnGetNodes();
			 for(var i=0;i<rows.length;i++)
		        {      
		              var status=$.trim( $(rows[i]).find("td:eq(11)").html());
		              if(status == "Unposted"){
		            	       var saledetails = {};
		          			  saledetails.saleId = rows[i].id;
		           			  allsaledetails.push(saledetails); 
		               }
		        }
		   }else{
			   $('#saleinvtable > tbody  > tr input[type="checkbox"]').each(function() {
					if(this.checked){
						var saledetails = {};
						saledetails.saleId = this.id;
						allsaledetails.push(saledetails);
					}
				});
		   }
	sale.salesDetails = allsaledetails;
	if($.isEmptyObject( sale.salesDetails )){
		console.log("empty");
		document.getElementById('emptyalertmsg').innerHTML = getFieldText.selectdata;
		$('#emptyAlertModal').modal('show');
	}else{
		//console.log("checkedid="+JSON.stringify(sale));
		$('#pleasewaitModal').modal('show');
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/pos/postallselectedesalesinvoice.htm", sale, function(response) {
			$('#pleasewaitModal').modal('hide');
			console.log(response);
			if (response == '0') {
				document.getElementById('confirmmessagecont').innerHTML = getCashMemoText.dataNotPost;
				showConfirmModal();
				
			} else {
				document.getElementById('confirmmessagecont').innerHTML = getCashMemoText.dataSucPost;
				showConfirmModal();
			}

		});
	}
	
	
}
function targetURL(){
	location.href = BASE_URL + '/pos/modifycashmemo.htm';
}

//new added
function setChange(d){
	if (!d.checked) {
		$("#example-select-all").prop("checked", false);
  }
}

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

});


