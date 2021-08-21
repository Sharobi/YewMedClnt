<%@page import="com.sharobi.yewpos.util.CommonResource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p><spring:message code="accgroup.jsp.trialbalanceview" text="Trial Balance  Report..." /></p>
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="col-sm-12 col-sm-12">
					<input type="hidden" id="compid" value="${sesloggedinUser.companyId}">
					<input type="hidden" id="storeid" value="${sesloggedinUser.storeId}">
					<input type="hidden" id="finyrid" value="${sesloggedinUser.finyrId}">
					<!-- <input type="hidden" id="manufid" value="0">
					<input type="hidden" id="contentid" value="0"> -->
					<center>
					<span id="error_rpt" style="color:red;"></span>
					</center>
					<table >
						<tr align="center" style="font-weight: bold;">
			 
					 
								<td align="center"> <spring:message code="accgroup.jsp.asondate" text="As on Date" /> 	</td>
								<td><spring:message code="accgroup.jsp.report_type" text="Report Type" /> </td>
								<td><spring:message code="accgroup.jsp.notinclouding" text="Not Including Zero" /> </td>
								<td><spring:message code="accgroup.jsp.reportformat" text="Format" /> </td>
							</tr>
						<tr>
						<fmt:parseDate value="${sessionScope.sesloggedinUser.startDate}" var="parsedStrtDate" pattern="yyyy-MM-dd" />
						<fmt:parseDate value="${sessionScope.sesloggedinUser.endDate}" var="parsedEndDate" pattern="yyyy-MM-dd" />
						
						 
						 
								
								
								<td align="center" style="padding: 0 1px;">
									<!-- <div class="input-group date" data-provide="datepicker"> -->
									<div class="input-group">
										<input type="hidden" id="sessionenddate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${parsedEndDate}" />">
										<input type="text" readonly="readonly" class="form-control-trx" id="enddate" name="endDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-th"></span>
										</div>
									</div>
								</td>
								
							
									
								<!-- report type group /leger-->
						 	<td style="padding: 0 1px;" >
									<select class="form-control-trx" name="trial_type" id="trial_type"  >
									 
										<option value="1">Group Wise</option>
										<option value="2">Ledger Wise</option>
									 
									</select>
								</td> 
						 
						  	<td style="padding: 0 1px;"  align="center" >
							 				 <input type="checkbox" id="isExcludingZero" name="isExcludingZero" >
								</td> 
		
								
								<td>
									<select class="form-control-trx" name="report_format" id="report_format">
										<option value="1">Pdf</option>
									 	<option value="2">XLSX</option>
									</select>
									
								</td>
							
								
								<td>
									<div class="col-lg-4 col-md-4 col-sm-12">
										<button type="button" class="btn btn-theme" onclick="getCurrPurchaseItems()"><spring:message code="cmn.jsp.btn.submit" text="Submit" /></button>
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
	$("#ason_heading").addClass('hide');
	
	
});
 
 

/*
 * for  report type on change function 
 */

 
 
// $(document).ready(function() {
var compid=$("#compid").val();
var storeid=$("#storeid").val();
var finyrid=$("#finyrid").val();

// });
var pdf_url_purchase_itemwise='<%=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_ACCOUNT_TRIAL_BALANCE)%>';


 
	

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
	
	var startDateFrom = new Date((currentDate.getFullYear() - 1), 3, 1);
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
	
	function getCurrPurchaseItems(){
		
	 
				var asOnDate=$("#enddate").val();
				var trial_type= $('#trial_type').val();
				var report_format= $('#report_format').val();
 
				var isExcludingZero=0; 
					
					if (document.getElementById("isExcludingZero").checked==true) {
						isExcludingZero=1;
					}
			    	if (document.getElementById("isExcludingZero").checked==false) {
					isExcludingZero=0;
				 }
			    	
			    	 
			  
					var  final_url=pdf_url_purchase_itemwise+"?cmpnyId="+compid+"&storeId="+storeid+"&finYrId="+finyrid+"&asOnDate="+asOnDate+"&trial_type="+trial_type+"&isExcludingZero="+isExcludingZero+"&report_format="+report_format+"";
					 
					 
					var pdfline="<iframe src='"+final_url+"' style='width:100%; height:450px;' frameborder='0'></iframe>";
				
					document.getElementById('rptpossaleregdiv').innerHTML=pdfline;
		 
		
		
	}
	
</script>