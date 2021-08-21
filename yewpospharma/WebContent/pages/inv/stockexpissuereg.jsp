<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
<!--
.ui-autocomplete {
	overflow-y: scroll;max-height: 250px; width: 300px; word-break: break-all;
}
-->
</style>
<c:set var="today" value="<%=new java.util.Date()%>" />
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p><%-- <spring:message code="expissuereg.jsp.title" text="Expiry Issue Register..." /> --%></p>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<div class="panel-trx panel-default">
			
				<div class="panel-body-trx">
					<form modelAttribute="commonResultSetMapper" action="${pageContext.request.contextPath }/expiry/searchexpiryinvoiceregister.htm" method="post" id="searchForm">
					<table>
							<tr align="center" style="font-weight: bold;">
								<td><spring:message code="purinvreg.jsp.frmdt" text="From Date" /></td>
								<td><spring:message code="purinvreg.jsp.todt" text="To Date" /></td>								
								<td colspan="4"><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></td>
							</tr>
							<tr>
								<td width="197px;" style="padding: 0 1px;">
										<div class="input-group">
											<input type="text" readonly="readonly" class="form-control-trx" id="stdate" name="startDate" value="${commonResultSetMapper.startDate}">
											<div class="input-group-addon">
												<span class="glyphicon glyphicon-th"></span>
											</div>
										</div>
								</td>
								<td width="197px;" style="padding: 0 1px;">
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
										<span class="input-group-addon" id="basic-addon3">EXP/${sessionScope.sesloggedinUser.finyrCode}/</span> <input class="form-control-trx" type="text" name="invoiceNo" value="${commonResultSetMapper.invoiceNo}">
									</div>
								</td> --%>
								<td width="40px;" style="padding: 0 1px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" value="EXP/" size="4" readonly></td>
								<c:if test="${empty commonResultSetMapper.finyrCode}">
									<td width="50px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" name="finyrCode" value="${sessionScope.sesloggedinUser.finyrCode}" size="5"></td>
								</c:if>
								<c:if test="${!empty commonResultSetMapper.finyrCode}">
									<td width="50px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" name="finyrCode" value="${commonResultSetMapper.finyrCode}" size="5"></td>
								</c:if>
								<td style="padding: 0 1px;" width="2.5%"><input class="form-control-trx" type="text" style="padding: 4px 0px;" id="retmemoSlash" value="/" readonly></td>
								<td width="202px;"><div class="input-group"><input class="form-control-trx" type="text" name="invoiceNo" value="${commonResultSetMapper.invoiceNo}"></div></td>
								
								<td style="padding: 0 2px;"><button type="button" id="search_btn" class="btn btn-theme">Search</button></td>
								<td colspan="2" >
									<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
								</td>
							</tr>
					</table>
				 </form>				 				 
				</div>
			</div>
		</div>	
		<div class="col-lg-12 col-md-12 col-sm-12">
			<table id="expitem" class="table table-bordered table-striped table-condensed table-hover">
				<thead>
					<tr>
						<th><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></th>
						<th><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></th>						
						<th><spring:message code="purinvdet.jsp.remarks" text="Remarks:" /></th>
						<th><spring:message code="purinvreg.jsp.status" text="Status" /></th>						
						<th><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty expiryList }">
						<c:forEach items="${expiryList}" var="expiry">
							<tr>
								<td>${expiry.invNo}</td>
								<td><fmt:parseDate value="${expiry.invDate}" var="parsedInvDate" pattern="MMM dd, yyyy" /> <fmt:formatDate pattern="yyyy-MM-dd" value="${parsedInvDate}" /></td>
								<td>${expiry.remarks}</td>
								<td>
									<c:if test="${expiry.isPosted==1}">
									Posted
									</c:if>
									<c:if test="${expiry.isPosted==0}">
									Unposted
									</c:if>
								</td>
								<td>
									<c:if test="${expiry.isPosted==1}">
										<button class="btn btn-success btn-xs" onclick="window.location.href='${pageContext.request.contextPath}/expiry/loadexpinvoicedet/${expiry.expiryId}.htm'"><spring:message code="cmn.jsp.btn.view" text="View" /></button>
									</c:if>
									<c:if test="${expiry.isPosted==0}">
										<c:if test="${menuByUserDTO.isAll==1}">
											<button class="btn btn-theme btn-xs" type="button" onclick="postExpiryInv(${expiry.expiryId})">Post</button>
											<button class="btn btn-info btn-xs" type="button" onclick="window.location.href='${pageContext.request.contextPath}/expiry/loadexpinvoicedet/${expiry.expiryId}.htm'">
												<i class="fa fa-pencil"></i>
												<spring:message code="cmn.jsp.btn.edit" text="Edit" />
											</button>
										</c:if>
										<c:if test="${menuByUserDTO.isView==1}">
											<button class="btn btn-theme04 btn-xs" type="button" onclick="deleteExpiryInv(${expiry.expiryId})">
												<i class="fa fa-trash-o "></i>
												<spring:message code="cmn.jsp.btn.dlt" text="Delete" />
											</button>
										</c:if>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</section>
<!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/inventory/stock/expiryissuereg.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/proc/vendor_en.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/inventory/stock/stock_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>
<script type="text/javascript">
	var BASE_URL = "${pageContext.request.contextPath}";
	
	$(document).ready(function() {
		$('#expitem').DataTable({
			"lengthChange" : false,
		/* "searching": false,
		"pageLength": 12 */
		/* "columnDefs": [
		               { className: "text-right", "targets": [3,4,5] },
		             ] */
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
			//endDate : currentDate,
			autoclose: true,
		});
		strtDtGrtEndDt();
	});
</script>
