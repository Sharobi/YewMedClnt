<%@page import="com.sharobi.yewpos.util.CommonResource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">

<section class="wrapper" id="print_section">
	<div class="row">
		<div class="col-lg-12">
			<p><%-- <spring:message code="reprintcashmemo.jsp.title" text="Reprint Cash Memo..." /> --%></p>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<div class="panel-trx panel-default">
				<div class="panel-body-trx">
					<form modelAttribute="commonResultSetMapper" action="${pageContext.request.contextPath }/reprintmemo/searchreprintsalesinvoice.htm" method="post" id="searchForm">
						<table>
							<tr align="center" style="font-weight: bold;">
								<td><spring:message code="purinvreg.jsp.frmdt" text="From Date" /></td>
								<td><spring:message code="purinvreg.jsp.todt" text="To Date" /></td>
								<td colspan="4"><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></td>
								<td><spring:message code="pos.jsp.custContact" text="Cust.Contact" /></td>
								<td><spring:message code="pos.jsp.custName" text="Cust. Name" /></td>
							</tr>
							<tr>
								<td style="padding: 0 1px;">
									<div class="input-group">
										<input type="text" readonly="readonly" class="form-control-trx" id="stdate" name="startDate" value="${commonResultSetMapper.startDate}">
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-th"></span>
										</div>
									</div>
								</td>
								<td style="padding: 0 1px;">
									<!-- <div class="input-group date" data-provide="datepicker"> -->
									<div class="input-group">
										<input type="text" readonly="readonly" class="form-control-trx" id="enddate" name="endDate" value="${commonResultSetMapper.endDate}">
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-th"></span>
										</div>
									</div>
								</td>
								<%-- <td style="padding: 0 1px;">
									<div class="input-group">
										<span class="input-group-addon" id="basic-addon3">SIM/${sessionScope.sesloggedinUser.finyrCode}/</span> <input class="form-control-trx" type="text" name="invoiceNo" value="${commonResultSetMapper.invoiceNo}">
									</div>
								</td> --%>
								
								<td width="145px;" style="padding: 0 1px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retmemoDoc" value="SIM/" size="15" ></td>
								<c:if test="${empty commonResultSetMapper.finyrCode}">
									<td width="50px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retmemoFinyr" name="finyrCode" value="${sessionScope.sesloggedinUser.finyrCode}" size="5"></td>
								</c:if>
								<c:if test="${!empty commonResultSetMapper.finyrCode}">
									<td width="50px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retmemoFinyr" name="finyrCode" value="${commonResultSetMapper.finyrCode}" size="5"></td>
								</c:if>
								<td style="padding: 0 1px;" width="2.5%"><input class="form-control-trx" type="text" style="padding: 4px 0px;" id="retmemoSlash" value="/" readonly></td>
								<td><div class="input-group"><input class="form-control-trx" type="text" name="invoiceNo" value="${commonResultSetMapper.invoiceNo}"></div></td>
								
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="salecustph" name="custPh" value="${commonResultSetMapper.custPh}"> <input type="hidden" id="salecustid" name="custId" value="${commonResultSetMapper.custId}"></td>
								<td><div class="input-group"><input class="form-control-trx" type="text" id="salecustname" name="custName" value="${commonResultSetMapper.custName}"></div></td>	
								
								<td style="padding: 0 2px;"><button type="button" id="search_btn" class="btn btn-theme"><spring:message code="cmn.jsp.search" text="Search" /></button></td>
								<td><div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div></td>
							</tr>
						</table>
					</form>
					
				</div>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<table id="purinvtable" class="table table-bordered table-striped table-condensed table-hover">
				<thead>
					<tr>
						<th><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></th>
						<th><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></th>
						<th><spring:message code="pos.jsp.custname" text="Cust.Name" /></th>
						<th><spring:message code="pos.jsp.dctrName" text="Doc. Name" /></th>
						<th><spring:message code="reprintcash.jsp.invmode" text="Inv Mode" /></th>
						<th class="numeric"><spring:message code="purinvdet.jsp.total" text="Total"/></th>
<%-- 						<th class="numeric"><spring:message code="purinvdet.jsp.totmrp" text="Tot.MRP:" /></th> --%>
						<th><spring:message code="reprintcash.jsp.disamt" text="Dis Amt." /></th>
						<th class="numeric"><spring:message code="purinvdet.jsp.nettotal" text="NetTotal:" /></th>
						<th class="numeric"><spring:message code="pos.jsp.adjamt" text="Adj Amt" /></th>
						<th><spring:message code="purinvdet.jsp.remarks" text="Remarks:" /></th>
						<th><spring:message code="purinvreg.jsp.status" text="Status" /></th>
						<th style="width:120px;"><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty saleDetailsAllDTOs }">
						<c:forEach items="${saleDetailsAllDTOs}" var="saledetail">
<%-- 						 <c:if test="${saledetail.holdFlag==1 && saledetail.isPosted==1}"> --%>
<%-- 						 <c:if test="${saledetail.holdFlag==1}"> --%>
							<tr>
								<td>${saledetail.invNo}</td>
								<td><fmt:parseDate value="${saledetail.invDate}" var="saleInvDate" pattern="MMM dd, yyyy" /> <fmt:formatDate pattern="yyyy-MM-dd" value="${saleInvDate}" /></td>
								<td>${saledetail.customerName}</td>
								<td>${saledetail.doctorName}</td>
								<td>${saledetail.invModeName}</td>
								<td>${saledetail.grossAmount}</td>
<%-- 								<td>${saledetail.totalMrp}</td> --%>
								<td>${saledetail.discAmount}</td>
								<td>${saledetail.netAmount}</td>
								<td>${saledetail.adjAmount}</td>
								<td>${saledetail.remarks}</td>
								<td><c:if test="${saledetail.isPosted==1}">
								Posted
								</c:if>
									<c:if test="${saledetail.isPosted==0}">
								Unposted
								</c:if></td>
								<td>
<%-- 									<button class="btn btn-success btn-xs" type="button" onclick="Reprint(${saledetail.saleId});" onclick="window.location.href='${pageContext.request.contextPath}/reprintmemo/cashmemo.htm?reprint=Y&backUrl=reprintbacktosearch&saleId=3465fg-trw73sxz-${saledetail.saleId}-utew09-qdd55-4320jhhgrt'"> --%>
									<button class="btn btn-success btn-xs" type="button" onclick="Reprint(${saledetail.saleId});">	
										<spring:message code="cmn.jsp.btn.print" text="Print" />
									</button>
									<button class="btn btn-success btn-xs" title="Print Sale Invoice in 80MM "  type="button" onclick="Reprint80mm(${saledetail.saleId});">
											 <i class="fa fa-print"></i> <spring:message code="cmn.jsp.btn.print80mm" text="Print80mm" />
									</button>
								</td>
							</tr>
<%-- 						  </c:if> --%>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</section>


<!--/wrapper -->

<script src="${pageContext.request.contextPath }/assets/js/proc/purinvreg/purinvreg.js"></script>

<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/proc/purinvreg/purinvreg_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>

<!-- <style>
/*
All common (screen) styles here, in the normal way.
*/
@media print {
  #print_section,#sidebar { display: none; }/* Hide everything ... */
  #printdiv { display: block; }/* ... except the required div */
}
</style> -->

<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";
var dotMatrixPrint='<%=CommonResource.getProperty(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_PRINT)%>';
var n2='<%=CommonResource.getProperty(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_NOTELINE_ONE)%>';
var n1='<%=CommonResource.getProperty(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_NOTELINE_TWO)%>';

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}
	$(document).ready(function() {
		$('#purinvtable').DataTable({
			"lengthChange" : false,
		/* "searching": false,
		"pageLength": 12 */
		"columnDefs": [
		               { className: "text-right", "targets": [5,6,7,8] },
		             ]
		});
		var currentDate = new Date();
		var startDateFrom = new Date((currentDate.getFullYear() - 1), 3, 1);
		$('#stdate').datepicker({
			format : 'yyyy-mm-dd',
			startDate : startDateFrom,
			autoclose: true,
		});
		$('#enddate').datepicker({
			format : 'yyyy-mm-dd',
			endDate : currentDate,
			autoclose: true,
		});
// 		$('.dataTables_filter input').attr("placeholder", getPurInvRegText.dataTablePlaceHolder);

		strtDtGrtEndDt();
	});
	
	
	function Reprint80mm(saleid){
		if(dotMatrixPrint==1){
			$('#pleasewaitModal').modal('show');
			var CommonResultSetMapper = {};
			CommonResultSetMapper.saleId = saleid;
			CommonResultSetMapper.noteLineOne=n2;
			CommonResultSetMapper.noteLineTwo=n1;
			CommonResultSetMapper.isRePrint=1;
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/pos/salebillprint.htm", CommonResultSetMapper, function(res) {
				$('#pleasewaitModal').modal('hide');

			});

		}else{
			location.href = BASE_URL + "/reprintmemo/cashmemo80mm.htm?reprint=Y&backUrl=reprintcashmemo&saleId=3465fg-trw73sxz-"+saleid+"-utew09-qdd55-4320jhhgrt";
		}
	}
	function Reprint(saleid){
		if(dotMatrixPrint==1){
			$('#pleasewaitModal').modal('show');
			var CommonResultSetMapper = {};
			CommonResultSetMapper.saleId = saleid;
			CommonResultSetMapper.noteLineOne=n2;
			CommonResultSetMapper.noteLineTwo=n1;
			CommonResultSetMapper.isRePrint=1;
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/pos/salebillprint.htm", CommonResultSetMapper, function(res) {
				$('#pleasewaitModal').modal('hide');

			});
			
		}else{
			location.href = BASE_URL + "/reprintmemo/cashmemo.htm?reprint=Y&backUrl=reprintbacktosearch&saleId=3465fg-trw73sxz-"+saleid+"-utew09-qdd55-4320jhhgrt";
		}
	}
</script>