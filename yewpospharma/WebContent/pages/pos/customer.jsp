<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">
<c:set var="today" value="<%=new java.util.Date()%>" />
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p>
				<%-- <spring:message code="customer.jsp.title" text="Customer..." /> --%>
			</p>
		
			
			
				<div class="panel panel-default">
					<div class="panel-body">
					
						<div class="col-lg-8 col-md-8 col-sm-12">
							<form modelAttribute="commonResultSetMapper" class="form-inline" role="form" action="${pageContext.request.contextPath }/customer/loadcustomer.htm" method="post">
								<div class="form-group">
								
			<input type="text" class="form-control" placeholder="Customer name" name="custName" value="${custName}">
						 
								</div>
								<div class="form-group">
									<select class="form-control" name="qryCondition" >
										<option value="like">LIKE</option>
										<option value="equals">=</option>
									</select>
								</div>
								<button type="submit" class="btn btn-theme"><spring:message code="cmn.jsp.search" text="Search" /></button>
							</form>
						</div>
					
								 	<div class="pull-right">
				<c:if test="${menuByUserDTO.isAll==1}">
					<button class="btn btn-primary" type="button" onclick="openAddEditModal(0)">
						<i class="fa fa-plus"></i>
						<spring:message code="cmn.jsp.btn.add" text="Add" />
					</button>
				</c:if>
			</div>
					</div>
				</div>

			<table id="example" class="table table-bordered table-striped table-condensed table-hover display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th><spring:message code="customer.jsp.name" text="Customer Name" /></th>
						<th><c:if test="${sesloggedinStore.isEsi==1}">
								<spring:message code="customer.jsp.inscardno" text="Ins/Card No" />
							</c:if>
							<c:if test="${sesloggedinStore.isEsi==0}">
								<spring:message code="customer.jsp.code" text="Code" />
							</c:if></th>
						<th><spring:message code="customer.jsp.address" text="Address" /></th>

						<th><spring:message code="customer.jsp.phn" text="Phone No." /></th>
						<th><spring:message code="customer.jsp.opbal" text="Opening Balance" /></th>
						<th><spring:message code="customer.jsp.creditLimit" text="creditLimit" /></th>
						<th><spring:message code="customer.jsp.recamt" text="Receivable Amount" /></th>
						<th><spring:message code="customer.jsp.avlLimit" text="Avl. Limit" /></th>
						<th style="width: 21%"><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty allCustomers }">
						<c:forEach items="${allCustomers}" var="allCustomer">
							<tr>
								<td>${allCustomer.name}</td>
								<td>${allCustomer.code}</td>
								<td>${allCustomer.address}</td>

								<td>${allCustomer.phoneNo}</td>
								<td>${allCustomer.obBal}</td>
								<td>${allCustomer.creditLimit}</td>
								<td>${allCustomer.paybleText}</td>
								<td>${allCustomer.creditLimit-allCustomer.paybleAmount}</td>
								<td><c:if test="${menuByUserDTO.isAll==1}">
										<button class="btn btn-info btn-xs" type="button" style="text-transform: uppercase; font-size: 11px;" onclick="openAddEditModal(${allCustomer.id})">
											<i class="fa fa-pencil"></i>
											<spring:message code="cmn.jsp.btn.edit" text="Edit" />
										</button>
									</c:if>
									<button class="btn btn-primary btn-xs" style="font-size: 11px;" type="button" onclick="window.location.href='${pageContext.request.contextPath }/customer/loadcustomerpay/${allCustomer.id}/${allCustomer.outstandingAmount}/${allCustomer.name}.htm'">
										<spring:message code="pos.jsp.receiptBtn" text="RECEIPT" />
									</button>
									<button class="btn btn-success btn-xs" style="font-size: 11px;" type="button" onclick="window.location.href='${pageContext.request.contextPath }/customer/loadcustomerpaydet/${allCustomer.id}/${allCustomer.outstandingAmount}/${allCustomer.name}.htm'">
										<spring:message code="pos.jsp.receiptBtnDet" text="RECEIPT DET." />
									</button> <c:if test="${menuByUserDTO.isView==1}">
										<button class="btn btn-theme04 btn-xs" style="font-size: 11px;" type="button" onclick="showCustomerDelModal(${allCustomer.id})">
											<i class="fa fa-trash-o "></i>
											<spring:message code="cmn.jsp.tblhdr.del" text="Del" />
										</button>
									</c:if></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</section>

<!-- Add/Edit group modal start -->
<div class="modal fade" id="customerAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 836px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<span id="headertext"></span>
				</h4>
			</div>
			<div class="modal-body">
				<div class="form-horizontal">
					<div class="col-sm-6 col-sm-6 ">
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="customer.jsp.name" text="Customer Name" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="customerName" class="form-control">
							</div>
						</div>
						
						
						
						
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="phn_label"><spring:message code="customer.jsp.phn" text="Phone No." /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="phn" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="addrs_label"><spring:message code="customer.jsp.address" text="Address" /></label>
							<div class="col-sm-8">
								<input type="text" id="addrs" class="form-control">
							</div>
						</div>
 
						<!-- age -->
							<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="age_label"><spring:message code="customer.jsp.age" text="Age" /></label>
							<div class="col-sm-8">
								<input type="text" id="age"  maxlength="3" class="form-control">
							</div>
						</div>
						
						<!-- guardians name -->
							<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="guardian_label"><spring:message code="customer.jsp.guardianname" text="Guardians name" /></label>
							<div class="col-sm-8">
								<input type="text" id="guardian_name" class="form-control">
							</div>
						</div>
						
					
					
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="customer.jsp.aadharno" text="Aadhar card no." /></label>
							<div class="col-sm-8">
								<input type="text" id="aadharcard" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label">Gender</label>
							<div class="col-sm-8">
								<select class="form-control" name="gender" id="slgender" onchange="getGender()">
									<c:if test="${!empty genderlist}">
										<c:forEach items="${genderlist}" var="glist">
											<option value="${glist.name}">${glist.name}</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>
					<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="customer.jsp.gstNo" text="Gst No." /></label>
							<div class="col-sm-8">
								<input type="text" id="gstNo" class="form-control">
							</div>
						</div>
											
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="customer.jsp.consiName" text="Consignee Name" /></label>
							<div class="col-sm-8">
								<input type="text" id="consiNm" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="customer.jsp.consiAddress" text="Consignee Address" /></label>
							<div class="col-sm-8">
								<input type="text" id="consiAddr" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-sm-6 ">
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="city_label"><spring:message code="customer.jsp.city" text="City" /></label>
							<div class="col-sm-8">
								<input type="text" id="city" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="state_label"><spring:message code="customer.jsp.state" text="State" /></label>
							<div class="col-sm-8">
								<input type="text" id="state" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="country_label"><spring:message code="customer.jsp.country" text="Country" /></label>
							<div class="col-sm-8">
								<input type="text" id="country" class="form-control">
							</div>
						</div>
							
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="pin_label"><spring:message code="customer.jsp.pin" text="Pin" /></label>
							<div class="col-sm-8">
								<input type="text" id="pin" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="code_label"><c:if test="${sesloggedinStore.isEsi==1}">
									<spring:message code="customer.jsp.inscardno" text="Ins/Card No" />
								</c:if>
								<c:if test="${sesloggedinStore.isEsi==0}">
									<spring:message code="customer.jsp.code" text="Code" />
								</c:if></label>
							<div class="col-sm-8">
								<input type="text" id="code" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="fax_label"><spring:message code="manufacturer.jsp.email" text="Email" /><%-- <spring:message code="customer.jsp.fax" text="Fax" /> --%></label>
							<div class="col-sm-8">
								<input type="text" id="fax" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="opbal_label"><spring:message code="customer.jsp.opbal" text="Opening Balance" /></label>
							<div class="col-sm-8">
								<input type="text" id="opbal" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="customer.jsp.creditLimit" text="Credit Limit" /></label>
							<div class="col-sm-8">
								<input type="text" id="creditLimit" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="customer.jsp.consiPhone" text="Consignee Phone" /></label>
							<div class="col-sm-8">
								<input type="text" id="consiPhone" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="customer.jsp.consiGstNo" text="Consignee GstNo" /></label>
							<div class="col-sm-8">
								<input type="text" id="consiGstNo" class="form-control">
							</div>
						</div>
						<div class="form-group hide">		
							<div class="col-sm-8">
								<input type="text" id="consiStateId" class="form-control">
							</div>
						</div>
						<div class="form-group hide">
							<label class="col-sm-4 col-sm-4 control-label">D.O.B</label>
							<div class="col-sm-8">
								 <input type="text" class="form-control" id="date" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />"/>
							</div>
						</div>
					</div>
				</div>
				<input type="hidden" id="cust_id" value=""></input>
				<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
			</div>
			<div class="modal-footer" style="border-top: 0px solid #e5e5e5;">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="javascript:addEditCustomer()" class="btn btn-theme">
					<spring:message code="cmn.jsp.btn.save" text="SAVE" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Add/Edit group modal end -->
<!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/pos/customer/customer.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/pos/customer/customer_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>
<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}
function showCustomerDelModal(id)
{
	   document.getElementById('confirmId').value=id;
	   $('#confirmModal').modal('show');
} 

$(document).ready(function() {
    $('#example').DataTable({
    	"lengthChange": false,
    	"columnDefs": [
		               { className: "text-right", "targets": [4,5,7] },
		             ]
    });
    $('.dataTables_filter input').attr("placeholder", getCustomerText.dataTablePlaceHolder);
} );
</script>