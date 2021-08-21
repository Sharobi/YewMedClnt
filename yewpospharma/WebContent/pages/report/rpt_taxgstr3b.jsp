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
								<td>
									<div class="col-lg-4 col-md-4 col-sm-12">
										<button type="button" class="btn btn-theme" onclick="getCurrSale()"><spring:message code="cmn.jsp.btn.submit" text="Submit" /></button>
									</div>
								</td>
								<td><div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div></td>
					</table>
					</div>
					
				</div>
				<div id="rpttaxsummarydiv" style="height: 450px;width: 100%;"></div>
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
// $(document).ready(function() {
var compid=$("#compid").val();
var storeid=$("#storeid").val();
var finyrid=$("#finyrid").val();

// });
var pdf_url_tax_sum='<%=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_REP_TAX_GSTR3B)%>';
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
	
	
	
	function getCurrSale(){
		var strtdate=$("#strtdate").val();
		var enddate=$("#enddate").val();
		if( (new Date(strtdate).getTime() > new Date(enddate).getTime()))
		{
			document.getElementById('alertMsg').innerHTML = getFieldText.frmdtGrtEnddt;
		}
		else
		{
			document.getElementById('alertMsg').innerHTML = "";
			var pdfline="<iframe src='"+pdf_url_tax_sum+"?cmpnyId="+compid+"&storeId="+storeid+"&finYrId="+finyrid+"&startDate="+strtdate+"&endDate="+enddate+"' style='width:100%; height:450px;' frameborder='0'></iframe>";
			document.getElementById('rpttaxsummarydiv').innerHTML=pdfline;
		}
	}
	
</script>