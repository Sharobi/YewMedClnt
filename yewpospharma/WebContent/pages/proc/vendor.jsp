<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p>
				<%-- <spring:message code="vendor.jsp.title" text="Vendor..." /> --%>
			</p>
			<div class="pull-right">
				<c:if test="${menuByUserDTO.isAll==1}">
					<button class="btn btn-primary" type="button" onclick="openAddEditModal(0)">
						<i class="fa fa-plus"></i>
						<spring:message code="cmn.jsp.btn.add" text="Add" />
					</button>
				</c:if>
			</div>
 
			<table id="example" class="table table-bordered table-striped table-condensed table-hover display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th><spring:message code="vendor.jsp.name" text="Name" /></th>
						<th><spring:message code="vendor.jsp.addrs" text="Address" /></th>
<%-- 						<th><spring:message code="vendor.jsp.pin" text="Pin" /></th> --%>
						<th><spring:message code="vendor.jsp.phn1" text="Contact No. 1" /></th>
						<th><spring:message code="vendor.jsp.rgstratn" text="Registration No." /></th>
						<th><spring:message code="customer.jsp.opbal" text="Opening Balance" /></th>
						<th><spring:message code="customer.jsp.creditLimit" text="creditLimit" /></th>
						<th><spring:message code="customer.jsp.payamt" text="Payable Amount" /> </th>
						<th><spring:message code="customer.jsp.avlLimit" text="Avl. Limit" /></th>
						<th style="width: 11%;"><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty allVendors }">
						<c:forEach items="${allVendors}" var="allVendor">
							<tr>
								<td>${allVendor.name}</td>
								<td>${allVendor.address}</td>
<%-- 								<td>${allVendor.pin}</td> --%>
								<td>${allVendor.phoneNo1}</td>
								<td>${allVendor.registrationNo}</td>
								<td>${allVendor.obBal}</td>
								<td>${allVendor.creditLimit}</td>
								<td>${allVendor.paybleText}</td>
								<td>${allVendor.creditLimit-allVendor.paybleAmount}</td>
								<td><c:if test="${menuByUserDTO.isAll==1}">
										<button class="btn btn-info btn-xs" type="button" onclick="openAddEditModal(${allVendor.id})">
											<i class="fa fa-pencil"></i>
											<spring:message code="cmn.jsp.btn.edit" text="Edit" />
										</button>
									</c:if> <c:if test="${menuByUserDTO.isView==1}">
										<button class="btn btn-theme04 btn-xs" type="button" onclick="showVendorDelModal(${allVendor.id})">
											<i class="fa fa-trash-o "></i>
											<spring:message code="cmn.jsp.btn.dlt" text="Delete" />
										</button>
									</c:if></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>

	<!-- Add/Edit vendor modal start -->
	<div class="modal fade" id="vendorAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
								<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="vendor.jsp.name" text="Name" /> <span class="required_star">*</span></label>
								<div class="col-sm-8">
									<input type="text" id="name" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label" id="addrs_label"><spring:message code="vendor.jsp.addrs" text="Address" /> <span class="required_star">*</span></label>
								<div class="col-sm-8">
									<textarea id="addrs" class="form-control" rows="2"></textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label" id="pin_label"><spring:message code="vendor.jsp.pin" text="Pin" /> <span class="required_star">*</span></label>
								<div class="col-sm-8">
									<input type="text" id="pin" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.city" text="City" /></label>
								<div class="col-sm-8">
									<input type="text" id="city" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.state" text="State" /></label>
								<div class="col-sm-8">
									<input type="text" id="state" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.cntry" text="Country" /></label>
								<div class="col-sm-8">
									<input type="text" id="cntry" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label" id="phn1_label"><spring:message code="vendor.jsp.phn1" text="Contact No. 1" /> <span class="required_star">*</span></label>
								<div class="col-sm-8">
									<input type="text" id="phn1" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label" id="discount_label"><spring:message code="vendor.jsp.discount" text="Discount(%)" /></label>
								<div class="col-sm-8">
									<input type="text" id="discount" class="form-control" />
								</div>
							</div>
							 <div class="form-group hide">
								<label class="col-sm-4 col-sm-4 control-label" id="dueDays_label"><spring:message code="vendor.jsp.duedays" text="Due days" /></label>
								<div class="col-sm-8">
									<input type="text" id="dueDays" class="form-control" />
								</div>
							</div>
						</div>


						<div class="col-sm-6 col-sm-6 ">
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.phn2" text="Contact No. 2" /></label>
								<div class="col-sm-8">
									<input type="text" id="phn2" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.fax" text="FAX" /></label>
								<div class="col-sm-8">
									<input type="text" id="fax" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.email" text="Email" /></label>
								<div class="col-sm-8">
									<input type="text" id="email" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.cntctPerson" text="Contact Person" /></label>
								<div class="col-sm-8">
									<input type="text" id="cntct_person" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label" id="rgstratn_label"><spring:message code="vendor.jsp.rgstratn" text="Registration No." /> <span class="required_star">*</span></label>
								<div class="col-sm-8">
									<input type="text" id="rgstratn" class="form-control" rows="4" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.opbalance" text="Opening Balance" /></label>
								<div class="col-sm-8">
									<input type="text" id="opbalance" class="form-control" rows="4" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.credtlimit" text="Credit Limit" /></label>
								<div class="col-sm-8">
									<input type="text" id="credt_limit" class="form-control" rows="4" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label" id="licenceNo_label"><spring:message code="vendor.jsp.licenceno" text="Licence No" /> <span class="required_star">*</span></label>
								<div class="col-sm-8">
									<input type="text" id="licenceNo" class="form-control" />
								</div>
							</div>
							<div class="form-group hide">
								<label class="col-sm-4 col-sm-4 control-label" id="duePer_label"><spring:message code="vendor.jsp.dueperc" text="Due(%)" /></label>
								<div class="col-sm-8">
									<input type="text" id="duePer" class="form-control" />
								</div>
							</div>
						</div>
					</div>
					<input type="hidden" id="vendor_id" value=""></input>
					<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
				</div>
				<div class="modal-footer" style="border-top: 0px solid #e5e5e5;">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<spring:message code="cmn.jsp.btn.close" text="Close" />
					</button>
					<button type="button" onclick="javascript:addEditVendor()" class="btn btn-theme">
						<spring:message code="cmn.jsp.btn.save" text="SAVE" />
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Add/Edit group modal end -->

</section>
<!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/proc/vendor.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/proc/vendor_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>
<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}
function showVendorDelModal(id)
{
	   document.getElementById('confirmId').value=id;
	   $('#confirmModal').modal('show');
} 

$(document).ready(function() {
    $('#example').DataTable({
    	"lengthChange": false,
    });
    $('.dataTables_filter input').attr("placeholder", getVendorText.dataTablePlaceHolder);
} );
</script>