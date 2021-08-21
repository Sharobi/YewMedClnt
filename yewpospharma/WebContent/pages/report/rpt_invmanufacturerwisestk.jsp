<%@page import="com.sharobi.yewpos.util.CommonResource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
<%-- 			<p><spring:message code="rptsalereg.jsp.title" text="Sales Register..." /></p> --%>
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="col-sm-12 col-sm-12">
					<input type="hidden" id="compid" value="${sesloggedinUser.companyId}">
					<input type="hidden" id="storeid" value="${sesloggedinUser.storeId}">
					<input type="hidden" id="finyrid" value="${sesloggedinUser.finyrId}">
					<table>
						<tr align="center" style="font-weight: bold;">
								<td><spring:message code="purinvreg.jsp.frmdt" text="From Date" /></td>
								<td><spring:message code="purinvreg.jsp.todt" text="To Date" /></td>
								<td></td>
							</tr>
						<tr>
						<fmt:parseDate value="${sessionScope.sesloggedinUser.startDate}" var="parsedStrtDate" pattern="yyyy-MM-dd" />
						<fmt:parseDate value="${sessionScope.sesloggedinUser.endDate}" var="parsedEndDate" pattern="yyyy-MM-dd" />
								<td style="padding: 0 1px;">
									<div class="input-group">
										<input type="hidden" id="sessionstrtdate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${parsedStrtDate}" />">
										<input type="text" readonly="readonly" class="form-control-trx" id="strtdate" name="startDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-th"></span>
										</div>
									</div>
								</td>
								<td style="padding: 0 1px;">
									<!-- <div class="input-group date" data-provide="datepicker"> -->
									<div class="input-group">
										<input type="hidden" id="sessionenddate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${parsedEndDate}" />">
										<input type="text" readonly="readonly" class="form-control-trx" id="enddate" name="endDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-th"></span>
										</div>
									</div>
								</td>
								<td style="padding: 0 1px;width: 45%;">
									<!-- <input type="hidden" id="manufacturerId" value="0" /><input class="form-control-trx" type="text" id="manufacturerId" placeholder="Manufacture Name(Please type atleast 2 characters)"> -->
									<input type="hidden" id="manufacturerId" value="" class="form-control-trx" name="manufacturerId"><input type="text" id="itemManufacturer" value="" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.manufacturer" text="Manufacturer" />(Please type atleast 2 characters)">
								</td>
								<%-- 
								<td style="padding: 0 1px;width: 26%;">
									<select class="form-control-trx" name="distributorid" id="distributorid">
											<c:if test="${!empty allVendors}">
												<c:forEach items="${allVendors}" var="allVendor">
														<option value="${allVendor.id}">${allVendor.name}</option>
												</c:forEach>
											</c:if>
									</select>
								</td> --%>
								<td>
									<div class="col-lg-4 col-md-4 col-sm-12">
										<button type="button" class="btn btn-theme" onclick="getManufacturerWiseStocks()"><spring:message code="cmn.jsp.btn.submit" text="Submit" /></button>
									</div>
								</td>
								<td><div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div></td>
					</table>
					</div>
					
				</div>
				<div id="rptpossaleregdiv" style="height: 450px;width: 100%;"></div>
			</div>
		</div>
	</div>
</section>
<!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/bootstrap/bootstrap-datepicker.min.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>
<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";

$(document).ready(function() {
	$("#itemManufacturer").autocomplete({
		source : function(	request,
							response) {
			if (request.term.length >= 2) {
				$.ajax({
					url : BASE_URL + "/manufacturer/autocompleteitemmanufacturer.htm",
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
			console.log(ui.item.itemCode)
			console.log(ui.item.label)
			$("#manufacturerId").val(ui.item.itemCode);
		},
		change : function(	e,
							ui) {
			if (!(ui.item))
				e.target.value = "";
		},
	});
});
// $(document).ready(function() {
	var scheduleid=$("#scheduleid").val();
	function getSelcetedSche(){
		scheduleid=$("#scheduleid").val();
	}
var compid=$("#compid").val();
var storeid=$("#storeid").val();
var finyrid=$("#finyrid").val();

// });
var pdf_url_manufacturerwise_stocks='<%=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_REP_INV_MANUFACTURERWISE_STOCK)%>';
	//var currentDate = new Date();
	/* $('#date').datepicker({
		format : 'yyyy-mm-dd',
		endDate : currentDate,
	}); */
	
	var currentDate = new Date();
	var startDateFrom = new Date((currentDate.getFullYear() - 1), 3, 1);
	
	var sessionstrtdate = $("#sessionstrtdate").val();
	var sessionenddate = $("#sessionenddate").val();
	var endDt = new Date();		
	if( (currentDate.getTime() > new Date(sessionenddate).getTime()))
	{
		endDt = sessionenddate;
		$('#enddate').val(sessionenddate);
	}
	else
	{
		endDt = currentDate;
	}
	
	$('#strtdate').datepicker({
		format : 'yyyy-mm-dd',
		startDate : sessionstrtdate,
		autoclose: true,
	});
	$('#enddate').datepicker({
		format : 'yyyy-mm-dd',
		endDate : endDt,
		autoclose: true,
	});
	
	function getManufacturerWiseStocks(){
		var strtdate=$("#strtdate").val();
		var enddate=$("#enddate").val();
		if ($("#itemManufacturer").val() == "") {
			var manufid = 0;
		} else {
			var manufid = $("#manufacturerId").val();
		}
		if( (new Date(strtdate).getTime() > new Date(enddate).getTime()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.frmdtGrtEnddt;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
			var pdfline="<iframe src='"+pdf_url_manufacturerwise_stocks+"?cmpnyId="+compid+"&storeId="+storeid+"&finYrId="+finyrid+"&startDate="+strtdate+"&endDate="+enddate+"&manufacturerId="+manufid+"' style='width:100%; height:450px;' frameborder='0'></iframe>";
			//console.log("pdfline: " +pdfline);
			document.getElementById('rptpossaleregdiv').innerHTML=pdfline;
		}
	}
	
</script>