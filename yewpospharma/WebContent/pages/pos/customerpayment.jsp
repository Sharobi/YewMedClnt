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
				
			 
					<table>
						<tr>	
							<td style="font-weight: bold; padding: 0 2px;"><spring:message code="customer.jsp.name" text="Customer Name" />:</td>
							<td style="padding: 0 2px;">
                        		<div class="input-group">
                        			<input type="hidden" id="custPaymentId" value="${custPaymentId}" />
                        			<c:if test="${custPaymentId==0}">
                            			<input type="text" name="custName" id="custName" class="form-control-trx" value="${custName}" readonly="readonly" /><input type="hidden" name="custId" id="custId" value="${cid}" />
                            		</c:if>
                            		<c:if test="${custPaymentId!=0}">
                            			<input type="text" name="custName" id="custName" class="form-control-trx" value="${custHeaderDTO.customerName}" readonly="readonly" /><input type="hidden" name="custId" id="custId" value="${custHeaderDTO.customerId}" />
                            		</c:if>
                        		</div>
							</td>						
							<td style="font-weight: bold; padding: 0 2px;"><spring:message code="pos.jsp.date" text="Date" /> :</td>
							<td style="padding: 0 2px;">
								<div class="input-group">
									<input type="text" class="form-control-trx" id="paydt" name="payDate" readonly value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
								</div>
							</td>
							<c:if test="${custPaymentId==0}">
								<td style="padding: 0 2px;"><button type="button" id="search_btn" onclick="getCustomerPendingPayment();" class="btn btn-theme"><spring:message code="cmn.jsp.search" text="Search" /></button></td>
							</c:if>
						</tr>
						<tr>
						<c:if test="${custPaymentId==0}">
						<td style="font-weight: bold;"><spring:message code="vendorpayment.jsp.outstanding.amt" text="Outstanding Amt" />: <span id="outstandingamt">${outstandingAmnt}</span></td>
						</c:if>
						</tr>
						<%-- <tr>
						<td style="font-weight: bold;"><spring:message code="vendorpayment.jsp.outstanding.amt" text="Outstanding Amt" />: <span id="outstandingamt">0.00</span></td>
						</tr> --%>

					</table>
				</div>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<div style="overflow: auto;" id="detail_table">
				<table id="customerduepaymenttable" style="margin-bottom: 5px;" class="table table-bordered table-striped table-condensed-trx table-hover">
					<thead>
						<tr>
							<th></th>
							<th><spring:message code="vendorpayment.jsp.invno" text="Inv.No" /></th>
							<th><spring:message code="vendorpayment.jsp.invdt" text="Inv.Date" /></th>
							<th class="numeric"><spring:message code="vendorpayment.jsp.totamt" text="Total Amt" />.</th>
							<%-- <th class="numeric"><spring:message code="purinvdet.jsp.crnote" text="Adv.Amt." /></th> --%>
							<th class="numeric"><spring:message code="vendorpayment.jsp.dueamt" text="Due Amt" />.</th>
							<%-- <th><spring:message code="customer.jsp.tag" text="Customer" /> &nbsp; <spring:message code="purinvdet.jsp.billno" text="Bill No" /></th> --%>
						</tr>
					</thead>
					<tbody id="custduepaymentbodylist">
						<%-- <c:if test="${custPaymentId!=0}"> --%>
							<c:if test="${!empty custDetailsDTOs }">
								<c:forEach items="${custDetailsDTOs}" var="custDetails">
									<tr id="${custDetails.invFromType}">
										<td><input id='${custDetails.invFromType}_modretcheck' class='chkboxcheked' type='checkbox' onclick='getCheckedCustPayDetById()' value='${custDetails.invFrom}' ></td>
										<td>${custDetails.invNo}</td>
										<td><fmt:parseDate value="${custDetails.invDate}" var="paymentInvDate" pattern="MMM dd, yyyy" /> <fmt:formatDate pattern="yyyy-MM-dd" value="${paymentInvDate}" /></td>
										<td id='custnetamt'>${custDetails.netAmount}</td>
										<td id='custdueamt'>${custDetails.dueAmount}</td>
										<td id='searchStat' class='hide' value='0'></td>
									</tr>
								</c:forEach>
							</c:if>
						<%-- </c:if> --%>
					</tbody>
				</table>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
		</div>
		
		<c:if test="${custPaymentId==0}">
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
						<input type="text" style="margin-bottom: 2px; text-align: right;" class="form-control-trx" id="payamt" name="" value="0.00"  onkeydown="numcheck(event)" >
					</div>
					<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.cheqeno" text="Cheque No" />:</div>
					<div class="col-md-2 col-sm-2" style="font-weight: bold;">
						<input type="text" style="margin-bottom: 2px; text-align: right;" class="form-control-trx" id="checkno" name="" readonly value="">
					</div>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.cardno" text="Card No" />:</div>
					<div class="col-md-2 col-sm-2" style="font-weight: bold;">
						<input type="text" class="form-control-trx" style="margin-bottom: 2px; text-align: right;" id="cardno" placeholder="Last 4 digits" maxlength="4" name="" readonly value="">
					</div>
					<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.bankname" text="Bank Name" />:</div>
					<div class="col-md-3 col-sm-3" style="font-weight: bold;">
						<input type="text" style="margin-bottom: 2px; text-align: right;" class="form-control-trx" id="bankname" name="" readonly value="">
					</div>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-md-3 col-sm-3 hide" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.pendingamt" text="Pending Amount" />:</div>
					<div class="col-md-2 col-sm-2 hide" style="font-weight: bold;">
						<input type="text" style="margin-bottom: 2px; text-align: right;" class="form-control-trx" id="pendingamt" name="" readonly value="0.00">
					</div>
				</div>
				  <input type="hidden" id="dr_legder_id" value="0">
				 
			      <input type="hidden" id="cr_legder_id" value="0">
			      <input type="hidden" id="cr_amount" value="0">
				  <input type="hidden" id="dr_amount" value="0">
					 
		 
			
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="itemmstr.jsp.remarks" text="Remarks" />:</div>
					<div class="col-md-3 col-sm-3">
						<input type="text" class="form-control-trx" style="margin-bottom: 2px;" id="remarks" name="">
					</div>
					<div class="col-md-2 col-sm-2"></div>
					<div class="col-md-4 col-sm-4">
						<button class="btn btn-danger" type="button" id="" onclick="location.href='${pageContext.request.contextPath}/customer/loadcustomer.htm'">CANCEL</button>
						<button class="btn btn-success" type="button" id="save_btn" onclick="saveCustomerPayment(1)"><spring:message code="cmn.jsp.btn.savecaps" text="SAVE" /></button>
						<button class="btn btn-success" type="button" id="save_btn" onclick="saveCustomerPayment(2)"><spring:message code="cmn.jsp.btn.savepost" text="SAVE & POST" /></button>
					</div>
				</div>
				<!-- </div>
				</div> -->
	
			</div>
		</c:if>
		
		<c:if test="${custPaymentId!=0}">
			<c:if test="${custHeaderDTO.isPosted==0}">
				<div class="col-lg-12 col-md-12 col-sm-12" id="footer_detail">
					<!-- <div class="panel-trx panel-default">
						<div class="panel-body-trx"> -->
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.totpayableamt" text="Total Selected Payable Amt" />:</div>
						<div class="col-md-2 col-sm-2" style="font-weight: bold;">
							<input type="text" style="margin-bottom: 2px; text-align: right;" class="form-control-trx" id="totselpayamt" name="" readonly value="${custHeaderDTO.paybleAmount}">
						</div>
						<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.totdueamt" text="Total Due Amount" />:</div>
						<div class="col-md-2 col-sm-2" style="font-weight: bold;">
							<input type="text" style="margin-bottom: 2px; text-align: right; color: #000000; background-color: #ebccd1;" class="form-control-trx" id="totdueamt" name="" readonly value="">
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.paymnttype" text="Payment Type" />:</div>
						<div class="col-md-2 col-sm-2" style="font-weight: bold;">
							<select class="form-control-trx" name="" id="paymenttype" style="margin-bottom: 2px;" onchange="getSelPaymentMode(document.getElementById('paymenttype').value)">
								<c:forEach items="${paymentModes}" var="paymentmode">
									<c:if test="${paymentmode.id!=2}">
										<c:if test="${paymentmode.id==custHeaderDTO.invMode}">
											<option value="${paymentmode.id}" selected="selected">${paymentmode.mode}</option>
										</c:if>
										<c:if test="${paymentmode.id!=custHeaderDTO.invMode}">
											<option value="${paymentmode.id}">${paymentmode.mode}</option>
										</c:if>
									</c:if>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.remainingamt" text="Remaining Amount" />:</div>
						<div class="col-md-2 col-sm-2" style="font-weight: bold;">
							<input type="text" class="form-control-trx" style="margin-bottom: 2px; text-align: right;" id="remamt" name="" readonly value="${custHeaderDTO.remainingAmount}">
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.payamt" text="Pay Amount" />:</div>
						<div class="col-md-2 col-sm-2" style="font-weight: bold;">
							<input type="text" style="margin-bottom: 2px; text-align: right;" class="form-control-trx" id="payamt"  onkeydown="numcheck(event)" name="" value="${custHeaderDTO.payAmount}">
						</div>
						<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.cheqeno" text="Cheque No" />:</div>
						<div class="col-md-2 col-sm-2" style="font-weight: bold;">
							<input type="text" style="margin-bottom: 2px; text-align: right;" class="form-control-trx" id="checkno" name="" readonly value="${custHeaderDTO.chequeNo}">
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.cardno" text="Card No" />:</div>
						<div class="col-md-2 col-sm-2" style="font-weight: bold;">
							<input type="text" class="form-control-trx" style="margin-bottom: 2px; text-align: right;" id="cardno" name="" readonly value="${custHeaderDTO.cardNo}">
						</div>
						<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.bankname" text="Bank Name" />:</div>
						<div class="col-md-3 col-sm-3" style="font-weight: bold;">
							<input type="text" style="margin-bottom: 2px; text-align: right;" class="form-control-trx" id="bankname" name="" readonly value="${custHeaderDTO.bankName}">
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-md-3 col-sm-3 hide" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.pendingamt" text="Pending Amount" />:</div>
						<div class="col-md-2 col-sm-2 hide" style="font-weight: bold;">
							<input type="text" style="margin-bottom: 2px; text-align: right;" class="form-control-trx" id="pendingamt" name="" readonly value="">
						</div>
					</div>
					
					
					<div class="col-lg-12 col-md-12 col-sm-12">						
						<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="itemmstr.jsp.remarks" text="Remarks" />:</div>
						<div class="col-md-3 col-sm-3">
							<input type="text" class="form-control-trx" style="margin-bottom: 2px;" id="remarks" name="" value="${custHeaderDTO.remarks}">
						</div>
						<div class="col-md-2 col-sm-2"></div>
						<c:if test="${custHeaderDTO.isPosted==0}">
							<div class="col-md-4 col-sm-4">
								<button class="btn btn-danger hide" type="button" id="" onclick="location.href='${pageContext.request.contextPath}/customer/loadcustomer.htm'">CANCEL</button>
								<button class="btn btn-success hide" type="button" style="text-transform: uppercase;" id="save_btn" onclick="saveCustomerPayment(1)"><spring:message code="cmn.jsp.btn.update" text="UPDATE" /></button>
								<button class="btn btn-success hide" type="button" id="save_btn" onclick="saveCustomerPayment(2)"><spring:message code="cmn.jsp.btn.updatepost" text="UPDATE & POST" /></button>
							</div>
						</c:if>
					</div>
					<%-- <div class="col-lg-12 col-md-12 col-sm-12">						
						<div class="col-md-3 col-sm-3"></div>
						<div class="col-md-3 col-sm-3"></div>
						<div class="col-md-2 col-sm-2"></div>
						<c:if test="${custHeaderDTO.isPosted==0}">
							<div class="col-md-4 col-sm-4">
								<button class="btn btn-danger" type="button" id="" onclick="location.href='${pageContext.request.contextPath}/customer/loadcustomerpaydet/${custHeaderDTO.customerId}/${custHeaderDTO.customerName}.htm'">CANCEL</button>
								<button class="btn btn-success" type="button" style="text-transform: uppercase;" id="save_btn" onclick="saveCustomerPayment(1)"><spring:message code="cmn.jsp.btn.update" text="UPDATE" /></button>
								<button class="btn btn-success" type="button" id="save_btn" onclick="saveCustomerPayment(2)"><spring:message code="cmn.jsp.btn.updatepost" text="UPDATE & POST" /></button>
							</div>
						</c:if>
					</div> --%>
					<!-- </div>
					</div> -->
		
				</div>
				
			</c:if>
			
			<c:if test="${custHeaderDTO.isPosted!=0}">
				<div class="col-lg-12 col-md-12 col-sm-12" id="footer_detail">
					<!-- <div class="panel-trx panel-default">
						<div class="panel-body-trx"> -->
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.totpayableamt" text="Total Selected Payable Amt" />:</div>
						<div class="col-md-2 col-sm-2" style="font-weight: bold;">
							<input type="text" style="margin-bottom: 2px; text-align: right;" class="form-control-trx" id="totselpayamt" name="" readonly value="${custHeaderDTO.paybleAmount}">
						</div>
						<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.totdueamt" text="Total Due Amount" />:</div>
						<div class="col-md-2 col-sm-2" style="font-weight: bold;">
							<input type="text" style="margin-bottom: 2px; text-align: right; color: #000000; background-color: #ebccd1;" class="form-control-trx" id="totdueamt" name="" readonly value="">
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.paymnttype" text="Payment Type" />:</div>
						<div class="col-md-2 col-sm-2" style="font-weight: bold;">
							<select class="form-control-trx" name="" id="paymenttype" style="margin-bottom: 2px;" onchange="getSelPaymentMode(document.getElementById('paymenttype').value)">
								<%-- <c:forEach items="${paymentModes}" var="paymentmode">
									<c:if test="${paymentmode.id!=2}">
										<c:if test="${paymentmode.id==custHeaderDTO.invMode}">
											<option value="${paymentmode.id}" selected="selected">${paymentmode.mode}</option>
										</c:if>
										<option value="${paymentmode.id}">${paymentmode.mode}</option>
									</c:if>
								</c:forEach> --%>
								
								<c:forEach items="${paymentModes}" var="paymentmode">
									<c:if test="${paymentmode.id!=2}">
										<c:if test="${paymentmode.id==custHeaderDTO.invMode}">
											<option value="${paymentmode.id}" selected="selected">${paymentmode.mode}</option>
										</c:if>
										<c:if test="${paymentmode.id!=custHeaderDTO.invMode}">
											<option value="${paymentmode.id}">${paymentmode.mode}</option>
										</c:if>
									</c:if>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.cheqeno" text="Cheque No" />:</div>
						<div class="col-md-2 col-sm-2" style="font-weight: bold;">
							<input type="text" style="margin-bottom: 2px; text-align: right;" class="form-control-trx" id="checkno" name="" readonly value="${custHeaderDTO.chequeNo}">
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.payamt" text="Pay Amount" />:</div>
						<div class="col-md-2 col-sm-2" style="font-weight: bold;">
							<input type="text" style="margin-bottom: 2px; text-align: right;" class="form-control-trx" id="payamt" name="" value="${custHeaderDTO.payAmount}">
						</div>
						<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.cardno" text="Card No" />:</div>
						<div class="col-md-2 col-sm-2" style="font-weight: bold;">
							<input type="text" class="form-control-trx" style="margin-bottom: 2px; text-align: right;" id="cardno" name="" readonly value="${custHeaderDTO.cardNo}">
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="itemmstr.jsp.remarks" text="Remarks" />:</div>
						<div class="col-md-2 col-sm-2">
							<input type="text" class="form-control-trx" style="margin-bottom: 2px;" id="remarks" name="" value="${custHeaderDTO.remarks}">
						</div>
						<div class="col-md-3 col-sm-3" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.bankname" text="Bank Name" />:</div>
						<div class="col-md-3 col-sm-3" style="font-weight: bold;">
							<input type="text" style="margin-bottom: 2px; text-align: right;" class="form-control-trx" id="bankname" name="" readonly value="${custHeaderDTO.bankName}">
						</div>						
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="col-md-3 col-sm-3 hide" style="font-weight: bold;"><spring:message code="vendorpayment.jsp.pendingamt" text="Pending Amount" />:</div>
						<div class="col-md-2 col-sm-2 hide" style="font-weight: bold;">
							<input type="text" style="margin-bottom: 2px; text-align: right;" class="form-control-trx" id="pendingamt" name="" readonly value="">
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
						
						<div class="col-md-2 col-sm-2"></div>
						
					</div>
					<!-- </div>
					</div> -->
		
				</div>
				
			</c:if>
		</c:if>
		
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
				<spring:message code="vendorpayment.jsp.totpayamtgrtzeorerrmsg" text="Total pay amount should be greater than zero" />.
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>

	<!-- Confirm Print customer payment  -->

				<div class="modal fade" id="confirmPrintcustomerecipetModal" style="text-align: center;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
									<span id="customerReceiptconfirmmess"> <%-- <spring:message code="purinv.jsp.addsucmsg" text="Purchase Invoice successfully added!" /> --%>
									</span> <br>
									<%-- <label class="col-sm-4 col-sm-4 control-label"><spring:message code="purinv.jsp.printpur" text="Print Purchase invoice" /> : </label> --%>
									<input type="hidden" id="is_acc" value="${is_account}">
										<!-- when account is not used  -->
									<c:if test="${is_account==1}">
									<div class="col-sm-12 " style="text-align: center; font-weight: 700;" >
								 
										<label class="checkbox-inline"> <spring:message code="customer.jsp.print" text="Print Customer Receipt Voucher" /> : <input type="checkbox" onclick="printCustomerPayment();" id="customer_receipt" name="customer_receipt" style="zoom: 1.5; vertical-align: middle; margin: 0px;">
										</label>

									</div>
									</c:if>
									<!-- when account is not used  -->
									<c:if test="${is_account==0}">
									<div class="col-sm-12 hide" style="text-align: center; font-weight: 700;" >
								 
										<label class="checkbox-inline"> <spring:message code="customer.jsp.print" text="Print Customer Receipt Voucher" /> : <input type="checkbox" onclick="printCustomerPayment();" id="customer_receipt" name="customer_receipt" style="zoom: 1.5; vertical-align: middle; margin: 0px;">
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
				<!-- Confirm Print customer Modal ends -->


<script src="${pageContext.request.contextPath }/assets/js/pos/customer/customerPayment.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/pos/customer/customer_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>
<script type="text/javascript">
	var BASE_URL = "${pageContext.request.contextPath}";
	var group_code="SDE";
	var ref_id=${cid};
	$(document).ready(function() {
	
		getvendorledger(group_code,0,ref_id,0);				
		$('#vendorpaymenttable').DataTable({
			"lengthChange" : false,
			/* "searching": false,
			"pageLength": 12 */
			"columnDefs" : [ {
				className : "text-right",
				"targets" : [ 3 ]
			}, ]
		});

		var currentDate = new Date();
		var startDateFrom = new Date((currentDate.getFullYear() - 1), 3, 1);
		$('#paydt').datepicker({
			format : 'yyyy-mm-dd',
			startDate : startDateFrom,
			endDate : currentDate,
			autoclose : true,
		});
		
		//$('.dataTables_filter input').attr("placeholder", getPurInvRetRegText.dataTablePlaceHolder);
		strtDtGrtEndDt();
	});
	

</script>