$(document).ready(function(){
	$("search_btn").click(function(){
		alert("hi");
		if( (new Date($('#stdate').val()).getTime() > new Date($('#enddate').val()).getTime()))
		{
			alert("error");
			document.getElementById('confirmmessagecont').innerHTML = getFieldText.frmdtGrtEnddt;
			showConfirmModal();
			e.preventDefault();
		}
		else
		{
			//alert();
			$("#searchForm").submit();
		}
	});
});
function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}
function Reprint(saleId)
{
	var invno = "";
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjax(BASE_URL + "/reprintmemo/cashmemo.htm?saleId=3465fg-trw73sxz-"+saleId+"-utew09-qdd55-4320jhhgrt", function(response) {
		invno = $.parseJSON(response).invNo;
		$("#invNo_label").text($.parseJSON(response).invNo);
		$('#dctrName_label').text($.parseJSON(response).doctorName);
		$('#patientName_label').text($.parseJSON(response).customerName);
		$('#vatNo_label').val($.parseJSON(response).vatAmount);
		/*$('#addrs').val($.parseJSON(response).address);
		$('#city').val($.parseJSON(response).city);
		$('#state').val($.parseJSON(response).state);
		$('#country').val($.parseJSON(response).country);
		$('#phn').val($.parseJSON(response).phoneNo);
		$('#fax').val($.parseJSON(response).fax);
		$('#opbal').val($.parseJSON(response).obBal);
		$('#creditLimit').val($.parseJSON(response).creditLimit);*/
	}, null);
	
	var divContents = $("#printdiv").html();
	$(".print_div").css('float','left');
	$("#left_div").css('float','left');
	$("#right_div").css('float','right');
	
	var line1= '<label style="width: 10%;font-weight: 600;"><spring:message code="purinvdet.jsp.invno" text="Invoice No" />. :</label><label id="invNo_label">'+invno+'</label>';
	
    var printWindow = window.open('', 'printWindow', 'width=750,height=550,top=50,left=50,resizable=yes');
    //var printWindow = window.open(''+BASE_URL+'/pages/pos/print.jsp');
    printWindow.document.write('<html><head>');
    //printWindow.document.write('<style>#detail_tbl th{width: 5%;}.print_div	{float: left;}#left_div{float: left;}#right_div{float: right;}</style>');
    printWindow.document.write('<link rel="stylesheet" href="'+BASE_URL+'/assets/css/bootstrap/bootstrap.css" media="print" />');
    printWindow.document.write('<link rel="stylesheet" href="'+BASE_URL+'/assets/css/style.css" media="print" />');
    printWindow.document.write('<link rel="stylesheet" href="'+BASE_URL+'/assets/css/font-awesome.css" media="print" />');
    printWindow.document.write('<link rel="stylesheet" href="'+BASE_URL+'/assets/css/style-responsive.css" media="print" />');
    printWindow.document.write('</head><body >');
    printWindow.document.write(line1);
    printWindow.document.write('</body></html>');
    printWindow.document.close();
    printWindow.print();
    
    //printWindow.close();
}