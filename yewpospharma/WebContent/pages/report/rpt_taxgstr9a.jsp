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
								<td>Financial Year</td>
								<td></td>
						</tr>
						<tr>
						<fmt:parseDate value="${sessionScope.sesloggedinUser.startDate}" var="parsedStrtDate" pattern="yyyy-MM-dd" />
						<fmt:parseDate value="${sessionScope.sesloggedinUser.endDate}" var="parsedEndDate" pattern="yyyy-MM-dd" />
								<td style="padding: 0 1px;">
									<div class="input-group">
										<input type="hidden" id="sessionstrtdate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${parsedStrtDate}" />">
										<input type="text" readonly="readonly" class="form-control-trx" id="strtdate" name="startDate" value="">
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-th"></span>
										</div>
									</div>
								</td>
								<td style="padding: 0 1px;">
									<!-- <div class="input-group date" data-provide="datepicker"> -->
									<div class="input-group">
										<input type="hidden" id="sessionenddate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${parsedEndDate}" />">
										<input type="text" readonly="readonly" class="form-control-trx" id="enddate" name="endDate" value="">
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-th"></span>
										</div>
									</div>
								</td>
								<td style="padding: 0 1px;width: 26%;"><select class="form-control-trx" name="finYear" id="finyear" onchange="setFinYear(this.value);">
									<c:if test="${!empty allFinYear}">
										<c:forEach items="${allFinYear}" var="allFin">
													<option value="${allFin.id}~${allFin.startDate}~${allFin.endDate}">${allFin.code}</option>
											</c:forEach>
										</c:if>	 
								</select></td>
								<!-- <td style="padding: 0 1px;width: 26%;"><select id="rptType" class="form-control" style="height: 29px;">
		                           <option value="1">PDF</option>
		                           <option value="2">Excel</option>
		                        </select></td> -->
								<td>
									<div class="col-lg-4 col-md-4 col-sm-12">
										<button type="button" class="btn btn-theme" onclick="getCurrSale()"><spring:message code="cmn.jsp.btn.submit" text="Submit" /></button>
									</div>
								</td>
								<td><div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div></td>
					</table>
					</div>
					
				</div>
				<div id="rpttaxsummarygstr9adiv" style="height: 450px;width: 100%;"></div>
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
/* var isExclusive="${isExclusive}"; */
// $(document).ready(function() {
var compid=$("#compid").val();
var storeid=$("#storeid").val();
var finyrid=$("#finyrid").val();
var gstType = $("#isExclusive").val();
// });

$(document).ready(function() {
	
	var data = $("#finyear").val();
	var findata = data.split('~');
	$("#finyrid").val(findata[0]);
	$("#strtdate").val(GetFormattedDate(findata[1]));
	$("#enddate").val(GetFormattedDate(findata[2])); 
	
});

	var pdf_url_gstr9a='<%=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_REP_TAX_GSTR9A)%>';
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
	
	/* $('#strtdate').datepicker({
		format : 'yyyy-mm-dd',
		startDate : sessionstrtdate,
		autoclose: true,
	});
	$('#enddate').datepicker({
		format : 'yyyy-mm-dd',
		endDate : endDt,
		autoclose: true,
	});
	 */
	
	
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
			var pdfline="<iframe src='"+pdf_url_gstr9a+"?cmpnyId="+compid+"&storeId="+storeid+"&finYrId="+finyrid+"&startDate="+strtdate+"&endDate="+enddate+"&gstType="+gstType+"' style='width:100%; height:450px;' frameborder='0'></iframe>";
			document.getElementById('rpttaxsummarygstr9adiv').innerHTML=pdfline;
		}
	}
	
	
	function setFinYear(value){
		var data = value.split('~');
		$("#finyrid").val(data[0]);
		$("#strtdate").val(GetFormattedDate(data[1]));
		$("#enddate").val(GetFormattedDate(data[2]));
	}
	
	
	function GetFormattedDate(date) {
		var mydate = new Date(date);
		var date = mydate.getDate();
		var month = mydate.getMonth()+1;
		var year = mydate.getFullYear();
		if(month < 10){month = "0"+month;}
		if(date < 10){date = "0"+date;}
		return year + "-" + month + "-" + date;
	}
	
</script>