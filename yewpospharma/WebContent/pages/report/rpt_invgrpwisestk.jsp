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
					<div class="col-sm-12 col-sm-12">
					<input type="hidden" id="compid" value="${sesloggedinUser.companyId}">
					<input type="hidden" id="storeid" value="${sesloggedinUser.storeId}">
					<input type="hidden" id="finyrid" value="${sesloggedinUser.finyrId}">
					<input type="hidden" id="itemid" value="0">
					<input type="hidden" id="manuid" value="0">
					<input type="hidden" id="contid" value="0">
					<input type="hidden" id="noofexpmon" value="0">
					<table>
						<tr align="center" style="font-weight: bold;">
								<td><spring:message code="purinvreg.jsp.dt" text="Date" /></td>
								<td></td>
							</tr>
							<tr>
								<fmt:parseDate value="${sessionScope.sesloggedinUser.endDate}" var="parsedEndDate" pattern="yyyy-MM-dd" />
								<fmt:parseDate value="${sessionScope.sesloggedinUser.startDate}" var="parsedStrtDate" pattern="yyyy-MM-dd" />
								<td style="padding: 0 1px;">
									<div class="input-group">
										<input type="hidden" id="sessionenddate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${parsedEndDate}" />">
										<input type="hidden" id="sessionstrtdate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${parsedStrtDate}" />">
										<input type="text" readonly="readonly" class="form-control-trx" id="enddate" name="endDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-th"></span>
										</div>
									</div>
								</td>
								<td>
									<div class="col-lg-4 col-md-4 col-sm-12">
										<button type="button" class="btn btn-theme" onclick="getStockGrpWise()">Get Group Wise Stock Value</button>
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

var pdf_url_sale_item='<%=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_REP_INV_STOCK_GROUP_WISE)%>';
	var currentDate = new Date();
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
	$('#enddate').datepicker({
		format : 'yyyy-mm-dd',
		startDate : sessionstrtdate,
		endDate : endDt,
		autoclose: true,
	});
	
	function getStockGrpWise(){
		var asOndate=$("#enddate").val();
		var compid=$("#compid").val();
		var storeid=$("#storeid").val();
		var finyrid=$("#finyrid").val();
		var manuid=$("#manuid").val();
		var contid=$("#contid").val();
		var itemid=$("#itemid").val();
		var noexpmnth=$("#noofexpmon").val();
		document.getElementById('alertMsg').innerHTML = "";
		var pdfline="<iframe src='"+pdf_url_sale_item+"?cmpnyId="+compid+"&storeId="+storeid+"&finYrId="+finyrid+"&manuId="+manuid+"&contentId="+contid+"&itemId="+itemid+"&asOnDate="+asOndate+"&noOfExpiryMonth="+noexpmnth+"' style='width:100%; height:450px;' frameborder='0'></iframe>";
		document.getElementById('rptpossaleregdiv').innerHTML=pdfline;
		
	}
	
</script>