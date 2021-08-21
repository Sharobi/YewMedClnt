<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p></p>
		</div>

		<div class="col-lg-12 col-md-12 col-sm-12" id="header_filter1">
			<div class="panel-trx panel-default">
				<div class="panel-body-trx">
					<input type="hidden" id="vid" value="${vid}" />
					<table>
						<tr>
							<td style="font-weight: bold;"><spring:message code="purinvdet.jsp.vendor" text="Vendor" /> :</td>
							<td style="padding: 0 2px;">
							<select class="form-control-trx" name="distributorId" id="seldistributor" onchange="getSelectedVendorPendingPayment(document.getElementById('seldistributor').value)">
									<c:if test="${!empty allVendors}">
										<option value="0_0">Select</option>
										<c:forEach items="${allVendors}" var="allVendor">
											<option value="${allVendor.id}_${allVendor.outstandingAmount}">${allVendor.name}</option>
										</c:forEach>
									</c:if>
							</select></td>
							<td style="font-weight: bold; padding: 0 2px;"><spring:message code="pos.jsp.date" text="Date" /> :</td>
							<td style="padding: 0 2px;">
								<div class="input-group">
									<input type="text" class="form-control-trx" id="paydt" name="payDate" readonly value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
								</div>
							</td>
						</tr>
						<tr>
						<td style="font-weight: bold;"><spring:message code="vendorpayment.jsp.outstanding.amt" text="Outstanding Amt" />: <span id="outstandingamt">0.00</span>     </td>
						</tr>

					</table>
				</div>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<div style="overflow: auto;" id="detail_table">
				<table id="vendorduepaymenttable" style="margin-bottom: 5px;" class="table table-bordered table-striped table-condensed-trx table-hover">
					<thead>
						<tr>
							<th></th>
							<th><spring:message code="vendorpayment.jsp.invno" text="Inv.No" /></th>
							<th><spring:message code="vendorpayment.jsp.invdt" text="Inv.Date" /></th>
							<th class="numeric"><spring:message code="vendorpayment.jsp.totamt" text="Total Amt" />.</th>
							<th class="numeric"><spring:message code="purinvdet.jsp.crnote" text="Adv.Amt." /></th>
							<th class="numeric">Oth.Adj.Amt.</th>
							<th class="numeric"><spring:message code="vendorpayment.jsp.dueamt" text="Due Amt" />.</th>
							<th><spring:message code="purinvdet.jsp.vendor" text="Vendor" /> &nbsp; <spring:message code="purinvdet.jsp.billno" text="Bill No" /></th>
						</tr>
					</thead>
					<tbody id="vendorduepaymentbodylist">

					</tbody>
				</table>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12" id="footer_detail">
			<!-- <div class="panel-trx panel-default">
				<div class="panel-body-trx"> -->
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.totpayableamt" text="Total Selected Payable Amt" />:</div>
				<div class="col-md-2 col-sm-2" style="font-weight: bold;">
					<input type="text" style="margin-bottom: 2px; text-align: right;" class="form-control-trx" id="totselpayamt" name="" readonly value="0.00">
				</div>
				<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.totdueamt" text="Total Due Amount" />:</div>
				<div class="col-md-2 col-sm-2" style="font-weight: bold;">
					<input type="text" style="margin-bottom: 2px; text-align: right; color: #000000; background-color: #ebccd1;" class="form-control-trx" id="totdueamt" name="" readonly value="0.00">
				</div>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.paymnttype" text="Payment Type" />:</div>
				<div class="col-md-2 col-sm-2" style="font-weight: bold;">
					<select class="form-control-trx" name="" id="paymenttype" style="margin-bottom: 2px;" onchange="getSelPaymentMode(document.getElementById('paymenttype').value)">
						<c:forEach items="${paymentModes}" var="paymentmode">
							<c:if test="${paymentmode.id!=2}">
								<option value="${paymentmode.id}">${paymentmode.mode}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.remainingamt" text="Remaining Amount" />:</div>
				<div class="col-md-2 col-sm-2" style="font-weight: bold;">
					<input type="text" class="form-control-trx" style="margin-bottom: 2px; text-align: right;" id="remamt" name="" readonly value="0.00">
				</div>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.payamt" text="Pay Amount" />:</div>
				<div class="col-md-2 col-sm-2" style="font-weight: bold;">
					<input type="text" style="margin-bottom: 2px; text-align: right;" class="form-control-trx" id="payamt" name="" value="0.00">
				</div>
				<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.cheqeno" text="Cheque No" />:</div>
				<div class="col-md-2 col-sm-2" style="font-weight: bold;">
				<input type="text" style="margin-bottom: 2px; text-align: right;" class="form-control-trx" id="checkno" name="" readonly value="">
				</div>
			<%-- 	  <div class="col-md-1 col-sm-1" style="font-weight: bold;">Date:</div>
				<div class="col-md-2 col-sm-2" style="font-weight: bold;">
					<input type="text" style="margin-bottom: 2px;" class="form-control-trx" id="checkdate" name="" disabled value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
				</div>  --%>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.cardno" text="Card No" />:</div>
				<div class="col-md-2 col-sm-2" style="font-weight: bold;">

					<input type="text" class="form-control-trx" style="margin-bottom: 2px; text-align: right;" id="cardno" name="" placeholder="<spring:message code="pos.jsp.cardlstdigit" text="Card Last Four Digit" />" readonly value="">
				</div>
				<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.bankname" text="Bank Name" />:</div>
				<div class="col-md-3 col-sm-3" style="font-weight: bold;">
					<input type="text" style="margin-bottom: 2px; text-align: right;" class="form-control-trx" id="bankname" name="" readonly value="">
				</div>
			</div>

				<div class="col-lg-12 col-md-12 col-sm-12">
			 <div class="col-md-3 col-sm-3" style="font-weight: bold;">Date:</div>
				<div class="col-md-2 col-sm-2" style="font-weight: bold;">
					<input type="text" style="margin-bottom: 2px;" class="form-control-trx" id="checkdate" name="" disabled value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
				</div>
			</div>

			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="col-md-3 col-sm-3 hide" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.pendingamt" text="Pending Amount" />:</div>
				<div class="col-md-2 col-sm-2 hide" style="font-weight: bold;">
					<input type="text" style="margin-bottom: 2px; text-align: right;" class="form-control-trx" id="pendingamt" name="" readonly value="0.00">
				</div>
			</div>



		  <input type="hidden" id="cr_legder_id" value="0">
		   <input type="hidden" id="dr_legder_id" value="0">
		   <input type="hidden" id="cr_amount" name="cr_amount" value="0">
			<input type="hidden"  id="dr_amount" name="dr_amount"  value="0">



			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="itemmstr.jsp.remarks" text="Remarks" />:</div>
				<div class="col-md-3 col-sm-3">
					<input type="text" class="form-control-trx" style="margin-bottom: 2px;" id="remarks" name="">

				</div>
				<div class="col-md-2 col-sm-2"></div>
				<div class="col-md-3 col-sm-3">
					<button class="btn btn-danger" type="button" id="" onclick="location.href='${pageContext.request.contextPath}/vendor/loadvendorpayreg.htm'">CANCEL</button>
					<button class="btn btn-success" type="button" id="save_btn" onclick="saveVendorPayment()"><spring:message code="cmn.jsp.btn.savecaps" text="SAVE" /></button>
				</div>
			</div>
			<!-- </div>
			</div> -->

		</div>
	</div>
</section>
<div class="modal fade" id="totpayamtnegModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
				<spring:message code="vendorpayment.jsp.totamtgrtzeorerrmsg" text="Total due amount should be greater than zero" />.
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>

	<!-- Confirm Print vendor payment  -->

				<div class="modal fade" id="confirmPrintVendorModal" style="text-align: center;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
								<h4 class="modal-title" id="myModalLabel">
									<spring:message code="footer.jsp.alert" text="Alert!" />
								</h4>
							</div>
							<div class="modal-body">
								<div id="confirprintmmessagecont">
									<span id="vendorPaymentconfirmmess"> <%-- <spring:message code="purinv.jsp.addsucmsg" text="Purchase Invoice successfully added!" /> --%>
									</span> <br>
									<%-- <label class="col-sm-4 col-sm-4 control-label"><spring:message code="purinv.jsp.printpur" text="Print Purchase invoice" /> : </label> --%>
									<input type="hidden" id="is_acc" value="${is_account}">
										<!-- when account is not used  -->
									<c:if test="${is_account==1}">
									<div class="col-sm-12" style="text-align: center; font-weight: 700;">
										<label class="checkbox-inline"> <spring:message code="vendorpayment.jsp.print" text="Print Vendor Payment Voucher" /> : <input type="checkbox" onclick="printVendorPayment();" id="vendor_payment" name="vendor_payment" style="zoom: 1.5; vertical-align: middle; margin: 0px;">
										</label>

									</div>
									</c:if>
									<!-- when account is not used  -->
									<c:if test="${is_account==0}">
									<div class="col-sm-12 hide" style="text-align: center; font-weight: 700;" >
										<label class="checkbox-inline"> <spring:message code="vendorpayment.jsp.print" text="Print Vendor Payment Voucher" /> : <input type="checkbox" id="vendor_payment" name="vendor_payment" style="zoom: 1.5; vertical-align: middle; margin: 0px;">
										</label>

									</div>
									</c:if>

								</div>
								<input type="hidden" id="confirmvalfrsaveupdate" value="">
							</div>
							<div class="modal-footer" style="border-top: 0px;">
								<button type="button" onclick="targetActionPur()" data-dismiss="modal" class="btn btn-theme">
									<spring:message code="footer.jsp.btn.ok" text="OK" />
								</button>
							</div>
						</div>
					</div>
				</div>
				<!-- Confirm Print Purchase Modal ends -->

<script src="${pageContext.request.contextPath }/assets/js/proc/vendor/vendorpayment.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/proc/purinvreg/purinvreg_en.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/proc/vendor/vendorpayment_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>
<script type="text/javascript">
	var isAccount = "${isAccount}";
	var BASE_URL = "${pageContext.request.contextPath}";
	  $("#cr_amount").val(null);
	 $("#dr_amount").val(null);
	  $("#cr_legder_id").val(0);
      $("#dr_legder_id").val(0);

	function numcheck(e) {
	    // Allow: backspace, delete, tab, escape, enter and .
	    if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
	        // Allow: Ctrl+A, Command+A
	        (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) ||
	        // Allow: home, end, left, right, down, up
	        (e.keyCode >= 35 && e.keyCode <= 40)) {
	        // let it happen, don't do anything
	        return;
	    }
	    // Ensure that it is a number and stop the keypress
	    if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
	        e.preventDefault();
	    }
	}
</script>