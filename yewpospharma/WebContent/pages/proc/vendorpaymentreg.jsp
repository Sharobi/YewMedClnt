<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p><%-- <spring:message code="vendorpaymentreg.jsp.title" text="Vendor Payment Register..." /> --%></p>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<div class="panel-trx panel-default">
				<div class="panel-body-trx">

					<form modelAttribute="commonResultSetMapper" action="${pageContext.request.contextPath }/vendor/searchvendorpayregister.htm" method="post" id="searchForm">
						<table>
							<tr align="center" style="font-weight: bold;">
								<td><spring:message code="purinvreg.jsp.frmdt" text="From Date" /></td>
								<td><spring:message code="purinvreg.jsp.todt" text="To Date" /></td>
								<td colspan="4"><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></td>
								<td width="28%"><spring:message code="purinvdet.jsp.vendor" text="Vendor" /></td>
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
									<div class="input-group">
										<input type="text" readonly="readonly" class="form-control-trx" id="enddate" name="endDate" value="${commonResultSetMapper.endDate}">
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-th"></span>
										</div>
									</div>
								</td>
								<%-- <td style="padding: 0 1px;">
									<div class="input-group">
										<span class="input-group-addon" id="basic-addon3">VPM/${sessionScope.sesloggedinUser.finyrCode}/</span> <input class="form-control-trx" type="text" name="invoiceNo" value="${commonResultSetMapper.invoiceNo}">
									</div>
								</td> --%>
								<td width="145px;" style="padding: 0 1px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retmemoDoc" value="VPM/" size="15" ></td>
								<c:if test="${empty commonResultSetMapper.finyrCode}">
									<td width="50px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" name="finyrCode" value="${sessionScope.sesloggedinUser.finyrCode}" size="5"></td>
								</c:if>
								<c:if test="${!empty commonResultSetMapper.finyrCode}">
									<td width="50px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" name="finyrCode" value="${commonResultSetMapper.finyrCode}" size="5"></td>
								</c:if>
								<td style="padding: 0 1px;" width="2.5%"><input class="form-control-trx" type="text" style="padding: 4px 0px;" id="retmemoSlash" value="/" readonly></td>
								<td width="202px;"><div class="input-group"><input class="form-control-trx" type="text" name="invoiceNo" value="${commonResultSetMapper.invoiceNo}"></div></td>

								<td style="padding: 0 1px;"><select class="form-control-trx" name="distributorId" id="seldistributor">
										<c:if test="${!empty allVendors}">
										<option value="0">All</option>
											<c:forEach items="${allVendors}" var="allVendor">
												<c:if test="${commonResultSetMapper.distributorId==allVendor.id}">
													<option value="${allVendor.id}" selected="selected">${allVendor.name}</option>
												</c:if>
												<c:if test="${commonResultSetMapper.distributorId!=allVendor.id}">
													<option value="${allVendor.id}">${allVendor.name}</option>
												</c:if>
											</c:forEach>
										</c:if>
								</select></td>
								<td style="padding: 0 2px;"><button type="button" id="search_btn" class="btn btn-theme"><spring:message code="cmn.jsp.search" text="Search" /></button></td>
								<td style="padding: 0 2px;"><button type="button" id="" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/vendor/loadvendorpay/0.htm'"><i class="fa fa-money"></i> <spring:message code="pos.jsp.payBtn" text="PAY" /></button></td>
							</tr>
							<tr>
								<td colspan="6"><div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<table id="vendorpaymenttable" class="table table-bordered table-striped table-condensed table-hover">
				<thead>
					<tr>
						<th><spring:message code="vendorpaymentreg.jsp.paymntno" text="Payment No" /></th>
						<th><spring:message code="pos.jsp.date" text="Date" /></th>
						<th><spring:message code="purinvdet.jsp.vendor" text="Vendor" /></th>
						<th class="numeric"><spring:message code="pos.jsp.netamnt" text="Net Amt" /></th>
						<th class="numeric"><spring:message code="vendorpaymentreg.jsp.paidamt" text="Paid Amt" /></th>
						<th class="numeric"><spring:message code="vendorpaymentreg.jsp.remamt" text="Rem Amt" /></th>
						<th><spring:message code="vendorpaymentreg.jsp.paymntmode" text="Payment Mode" /></th>
						<th><spring:message code="vendorpaymentreg.jsp.createdby" text="Created By" /></th>
						<th><spring:message code="itemmstr.jsp.remarks" text="Remarks" /></th>
						<th><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty paymentDetailsAllDTOs }">
						<c:forEach items="${paymentDetailsAllDTOs}" var="paymentDetails">
							<tr>
								<td>${paymentDetails.invNo}</td>
								<td><fmt:parseDate value="${paymentDetails.invDate}" var="paymentInvDate" pattern="MMM dd, yyyy" /> <fmt:formatDate pattern="yyyy-MM-dd" value="${paymentInvDate}" /></td>
								<td>${paymentDetails.supplierName}</td>
								<td>${paymentDetails.netAmount}</td>
								<td>${paymentDetails.payAmount}</td>
								<td>${paymentDetails.remainingAmount}</td>
								<td>
								<c:if test="${paymentDetails.invModeName=='Cheque'}">
								<a style="cursor: pointer;" onclick='getChequeDetView(&quot;<fmt:formatDate pattern="yyyy-MM-dd" value="${paymentInvDate}"/>&quot;,&quot;${paymentDetails.chequeNo}&quot;,&quot;${paymentDetails.bankName}&quot;)'>${paymentDetails.invModeName}</a>
								</c:if>
								<c:if test="${paymentDetails.invModeName!='Cheque'}">${paymentDetails.invModeName}
								</c:if>
								</td>
								<td>${paymentDetails.createdByUser}</td>
								<td>${paymentDetails.remarks}</td>
								<td>
								<c:if test="${paymentDetails.isPosted==0}">
									<c:if test="${menuByUserDTO.isAll==1}">
										<button class="btn btn-theme btn-xs" type="button" onclick="postVendorPayment(${paymentDetails.paymentId})"><spring:message code="cmn.jsp.btn.post" text="Post" /></button>
									</c:if>
								</c:if>
								<button class="btn btn-success btn-xs" type="button" onclick="getPayDetView(${paymentDetails.paymentId},&quot;${paymentDetails.supplierName}&quot;)">
											<spring:message code="cmn.jsp.btn.view" text="View" />
										</button>
											<button class="btn btn-success btn-xs ${is_acc==1? '':'hide'}" onclick="window.location.href='${pageContext.request.contextPath}/vendor/vendorpaymentprint/${paymentDetails.paymentId}.htm'">Print</button>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</section>
<div class="modal fade" id="paydetailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 766px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="purinvdet.jsp.vendor" text="Vendor" /> :
					 <span id="vendorname"> </span>
				</h4>
			</div>
			<div class="modal-body">
				<table id="vendorpaymenttable" style="margin-bottom: 5px;" class="table table-bordered table-striped table-condensed-trx table-hover">
					<thead>
						<tr>
							<th><spring:message code="vendorpayment.jsp.invno" text="Inv.No" /></th>
							<th><spring:message code="vendorpayment.jsp.invdt" text="Inv.Date" /></th>
							<th class="numeric"><spring:message code="vendorpayment.jsp.totamt" text="Total Amt" />.</th>
							<th class="numeric"><spring:message code="purinvdet.jsp.crnote" text="Adv.Amt." /></th>
							<th class="numeric">Oth.Adj.Amt.</th>
							<th class="numeric"><spring:message code="vendorpayment.jsp.dueamt" text="Due Amt" />.</th>
							<th><spring:message code="purinvdet.jsp.vendor" text="Vendor" /> &nbsp; <spring:message code="purinvdet.jsp.billno" text="Bill No" /></th>
						</tr>
					</thead>
					<tbody id="vendorpaymentbodylist">

					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="chequeDetailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 766px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="purinvdet.jsp.chequeDetails" text="Cheque Details" /> :
					 <span id="vendorname"> </span>
				</h4>
			</div>
			<div class="modal-body">
				<table id="vendorpaymenttable" style="margin-bottom: 5px;" class="table table-bordered table-striped table-condensed-trx table-hover">
					<thead>
						<tr>
							<th><spring:message code="vendorpayment.jsp.chequeDate" text="Cheque.Date" /></th>
							<th class="numeric"><spring:message code="vendorpayment.jsp.chequeNo" text="Cheque.No." />.</th>
							<th class="numeric"><spring:message code="purinvdet.jsp.BankNamme" text="BankName." /></th>
						</tr>
					</thead>
					
				<tbody>
					<%-- <tr>
						<td><fmt:parseDate value="${chequeDetailModaltable.invDate}"/></td>
						<td>${chequeDetailModaltable.chequeNo}</td>
						<td>${chequeDetailModaltable.bankName}</td>
					</tr> --%>
				</tbody>
					 <tbody id="chequeDetailModaltable">
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
			</div>
		</div>
	</div>
</div>
<!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/proc/purinvreg/purinvreg.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/proc/vendor/vendorpayment.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/proc/purinvreg/purinvreg_en.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/proc/vendor/vendorpayment_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>
<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";


	$(document).ready(function() {
		$('#vendorpaymenttable').DataTable({
			"lengthChange" : false,
		/* "searching": false,
		"pageLength": 12 */
		"columnDefs": [
		               { className: "text-right", "targets": [3] },
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
		//$('.dataTables_filter input').attr("placeholder", getPurInvRetRegText.dataTablePlaceHolder);
		strtDtGrtEndDt();
	});

</script>