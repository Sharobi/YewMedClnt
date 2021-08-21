<%@page import="com.sharobi.yewpos.util.CommonResource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p><spring:message code="stktrnsfryrend.jsp.title" text="Stock Transfer for Year End..." /></p>
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="col-sm-12 col-sm-12">
					<table>
						<tr align="center" style="font-weight: bold;">
								<td><spring:message code="accgroup.jsp.asondate" text="As on Date" /></td>
								<td></td>
							</tr>
						<tr>
						<fmt:parseDate value="${sessionScope.sesloggedinUser.endDate}" var="parsedEndDate" pattern="yyyy-MM-dd" />
								<td style="padding: 0 1px;">
									<div class="input-group">
										<input type="hidden" id="sessionenddate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${parsedEndDate}" />">
										<input type="text" readonly="readonly" class="form-control" id="enddate" name="endDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-th"></span>
										</div>
									</div>
								</td>
								<td>
									<div class="col-lg-4 col-md-4 col-sm-12">
										<button type="button" class="btn btn-theme" onclick="yearendstktrnsfr()"><spring:message code="cmn.jsp.btn.submit" text="Submit" /></button>
									</div>
								</td>
					</table>
					</div>
					<div style="height: 450px;width: 100%;"></div>
				</div>
			</div>
		</div>
	</div>
</section>

<!-- Confirm Modal Start -->

<div class="modal fade" id="askFrPostedModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!" />
				</h4>
			</div>
			<div class="modal-body">
				<div id="askFrPostedDiv"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="confirm()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Confirm Modal end -->

<!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/bootstrap/bootstrap-datepicker.min.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/inventory/yearEnd/yearEnd.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
<script src="${pageContext.request.contextPath}/assets/js/inventory/yearEnd/yearEnd_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>
<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";
	
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
	
	
	
</script>