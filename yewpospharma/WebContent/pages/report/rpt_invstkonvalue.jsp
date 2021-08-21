<%@page import="com.sharobi.yewpos.util.CommonResource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="col-sm-3 col-sm-3">
					<input type="hidden" id="compid" value="${sesloggedinUser.companyId}">
					<input type="hidden" id="storeid" value="${sesloggedinUser.storeId}">
					<input type="hidden" id="finyrid" value="${sesloggedinUser.finyrId}">
					<input type="hidden" id="manufid" value="0">
					<input type="hidden" id="contentid" value="0">
					<input type="hidden" id="noofexpmon" value="0">
					<fmt:parseDate value="${sessionScope.sesloggedinUser.startDate}" var="parsedStrtDate" pattern="yyyy-MM-dd" />
					<fmt:parseDate value="${sessionScope.sesloggedinUser.endDate}" var="parsedEndDate" pattern="yyyy-MM-dd" />
						<%-- <input type="text" class="form-control-trx" id="asondate" readonly="readonly" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" /> "> --%>
						<div class="input-group">
							<input type="hidden" id="sessionstrtdate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${parsedStrtDate}" />">
							<input type="hidden" id="sessionenddate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${parsedEndDate}" />">
							<input type="text" readonly="readonly" class="form-control-trx" id="asondate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
							<div class="input-group-addon">
								<span class="glyphicon glyphicon-th"></span>
							</div>
						</div>
					</div>
					<div class="col-sm-5 col-sm-5">
							<input type="hidden" id="itemid" value="0" /><input class="form-control-trx" type="text" id="item_name" placeholder="Item Name(Please type atleast 2 characters)">
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12">
						<button type="submit" class="btn btn-theme" onclick="getCurrStkOnVal()">Get Stock on Value</button>
					</div>
				</div>
				<div id="rptinvstkonvaldiv" style="height: 450px;width: 100%;"></div>
			</div>
		</div>
	</div>
</section>
<!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/bootstrap/bootstrap-datepicker.min.js"></script>
<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";
$(document).ready(function() {
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
				console.log(ui.item.itemCode);
				console.log(ui.item.label);
				$("#itemid").val(ui.item.itemCode);
			},
			change : function(	e,
								ui) {
				if (!(ui.item))
					e.target.value = "";
			},
		}); 
});

var pdf_url_stockonval_reg='<%=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_REP_INV_STOCKONVALUE)%>';
	var currentDate = new Date();
	var sessionstrtdate = $("#sessionstrtdate").val();
	var sessionenddate = $("#sessionenddate").val();
	var endDt = new Date();		
	if( (currentDate.getTime() > new Date(sessionenddate).getTime()))
	{
		endDt = sessionenddate;
	}
	else
	{
		endDt = currentDate;
	}
	var startDateFrom = new Date((currentDate.getFullYear() - 1), 3, 1);
	$('#asondate').datepicker({
		format : 'yyyy-mm-dd',
		startDate : sessionstrtdate,
		endDate : endDt,
		autoclose: true,
	});
	function getCurrStkOnVal(){
		var compid=$("#compid").val();
		var storeid=$("#storeid").val();
		var finyrid=$("#finyrid").val();
		var manufid=$("#manufid").val();
		var contentid=$("#contentid").val();
		if($("#item_name").val()=="")
		{
			var itemid=0;
		}
		else
		{
			var itemid=$("#itemid").val();
		}
		var asondate=$("#asondate").val();
		var noofexpmon=$("#noofexpmon").val();
		
		var pdfline="<iframe src='"+pdf_url_stockonval_reg+"?cmpnyId="+compid+"&storeId="+storeid+"&finYrId="+finyrid+"&manuId="+manufid+"&contentId="+contentid+"&itemId="+itemid+"&asOnDate="+asondate+"&noOfExpiryMonth="+noofexpmon+"' style='width:100%; height:450px;' frameborder='0'></iframe>";
		document.getElementById('rptinvstkonvaldiv').innerHTML=pdfline;
	}
	
</script>