<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p><%-- <spring:message code="customerpaymentreg.jsp.title" text="Customer Payment Details..." /> --%></p>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<div class="panel-trx panel-default">
				<div class="panel-body-trx">
					<form modelAttribute="commonResultSetMapper" action="${pageContext.request.contextPath }/customer/searchcustomerpayregister.htm" method="post" id="searchForm">
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
								<td width="40px;" style="padding: 0 1px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retmemoDoc" value="CPM/" size="4" readonly></td>
								<c:if test="${empty commonResultSetMapper.finyrCode}">
									<td width="50px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" name="finyrCode" value="${sessionScope.sesloggedinUser.finyrCode}" size="5"></td>
								</c:if>
								<c:if test="${!empty commonResultSetMapper.finyrCode}">
									<td width="50px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" name="finyrCode" value="${commonResultSetMapper.finyrCode}" size="5"></td>
								</c:if>
								<td style="padding: 0 1px;" width="2.5%"><input class="form-control-trx" type="text" style="padding: 4px 0px;" value="/" readonly></td>
								<td width="202px;"><div class="input-group"><input class="form-control-trx" type="text" name="invoiceNo" value="${commonResultSetMapper.invoiceNo}"></div></td>


								<td style="padding: 0 2px;"><button type="button" id="search_btn" class="btn btn-theme"><spring:message code="cmn.jsp.search" text="Search" /></button></td>
								<td>
									<div class="form-group">
                              			<label class="col-sm-5 col-sm-5 control-label" style="padding-top: 2%;" id="name_label"><spring:message code="customer.jsp.name" text="Customer Name" /></label>
                              			<div class="col-sm-7">
                                  			<input type="text" name="custName" id="custName" class="form-control" value="${custName}" readonly="readonly" /><input type="hidden" name="custId" id="custId" value="${cid}" />
                              			</div>
                          			</div>
								</td>
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
					<c:if test="${!empty custPaymentDetailsAllDTOs }">
						<c:forEach items="${custPaymentDetailsAllDTOs}" var="custPaymentDetails">
							<tr>
								<td>${custPaymentDetails.invNo}</td>
								<td><fmt:parseDate value="${custPaymentDetails.invDate}" var="paymentInvDate" pattern="MMM dd, yyyy" /> <fmt:formatDate pattern="yyyy-MM-dd" value="${paymentInvDate}" /></td>
								<td>${custPaymentDetails.netAmount}</td>
								<td>${custPaymentDetails.payAmount}</td>
								<td>${custPaymentDetails.remainingAmount}</td>
								<td>${custPaymentDetails.invModeName}</td>
								<td>${custPaymentDetails.createdByUser}</td>
								<td>${custPaymentDetails.remarks}</td>
								<td>
								<c:if test="${custPaymentDetails.isPosted==0}">
									<c:if test="${menuByUserDTO.isAll==1}">
										<button class="btn btn-theme btn-xs" type="button" onclick="postCustomerPayment(${custPaymentDetails.paymentId})"><spring:message code="cmn.jsp.btn.post" text="Post" /></button>
										<button class="btn btn-info btn-xs" type="button" onclick="window.location.href='${pageContext.request.contextPath}/customer/loadcustomerpaybyid.htm?custPaymentId=3465fg-trw73sxz-${custPaymentDetails.paymentId}-utew09-qdd55-4320jhhgrt'">
<!-- 											<i class="fa fa-pencil"></i> -->
											<spring:message code="cmn.jsp.btn.view" text="View" />
										</button>
									</c:if>
									<c:if test="${menuByUserDTO.isView==1}">
										<button class="btn btn-theme04 btn-xs" type="button" onclick="deleteCustPaymentInv(${custPaymentDetails.paymentId});">
											<i class="fa fa-trash-o "></i>
											<spring:message code="cmn.jsp.btn.dlt" text="Delete" />
										</button>
									</c:if>
								</c:if>
								<c:if test="${custPaymentDetails.isPosted==1}">
									<button class="btn btn-success btn-xs" onclick="window.location.href='${pageContext.request.contextPath}/customer/loadcustomerpaybyid.htm?custPaymentId=3465fg-trw73sxz-${custPaymentDetails.paymentId}-utew09-qdd55-4320jhhgrt'"><spring:message code="cmn.jsp.btn.view" text="View" /></button>
								</c:if>

									<button class="btn btn-success btn-xs ${is_acc==1? '':'hide'}" onclick="window.location.href='${pageContext.request.contextPath}/customer/printpaymentreceipt/${custPaymentDetails.paymentId}.htm'">Print</button>

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
<script src="${pageContext.request.contextPath }/assets/js/pos/customer/customerPayment.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/pos/customer/customer_en.js"></script>
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