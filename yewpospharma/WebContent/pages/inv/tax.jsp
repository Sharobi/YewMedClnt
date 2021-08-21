<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p>Tax...</p>
			<div class="pull-right">
				<c:if test="${menuByUserDTO.isAll==1}">
					<button class="btn btn-primary" type="button" onclick="openAddEditModal(0)"><i class="fa fa-plus"></i>&nbsp; <spring:message code="cmn.jsp.btn.add" text="Add" /></button>
				</c:if>
			</div>
			
			<table id="example" class="table table-bordered table-striped table-condensed table-hover display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>Name</th>
						<th>Description</th>
						<th>Percentage</th>
						<th>Tax Type</th>
						<th>Tax Category</th>
						<th><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty allTaxes }">
						<c:forEach items="${allTaxes}" var="allTax">
							<tr>
								<td>${allTax.taxName}</td>
								<td>${allTax.description}</td>
								<td>${allTax.percentage}</td>
								<c:if test="${allTax.isGroup==0}">
									<td>Single</td>
								</c:if>
								<c:if test="${allTax.isGroup==1}">
									<td>Group</td>
								</c:if>
								<td>${allTax.taxMode}</td>
								<td>
									<c:if test="${menuByUserDTO.isAll==1}">
										<button class="btn btn-info btn-xs" type="button" onclick="openAddEditModal(${allTax.taxId});"><i class="fa fa-pencil"></i>&nbsp; <spring:message code="cmn.jsp.btn.edit" text="Edit" /></button>
									</c:if>
									<c:if test="${menuByUserDTO.isView==1}">
										<button class="btn btn-theme04 btn-xs" type="button" onclick="showtaxDelModal(${allTax.taxId})"><i class="fa fa-trash-o "></i>&nbsp;<spring:message code="cmn.jsp.btn.dlt" text="Delete" /></button>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
	
	<!-- Add/Edit tax modal start -->
			<div class="modal fade" id="taxAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
						        <h4 class="modal-title" id="myModalLabel">
						        	<span id="headertext"></span>
						        </h4>
						      </div>
						      <div class="modal-body">
						        <div class="form-horizontal">
						        	<div class="form-group">
						        	<input type="hidden" id="taxId" value=""></input>
                              			<label class="col-sm-4 col-sm-4 control-label" id="taxCat_label">Tax Category<span class="required_star">*</span></label>
                              			
                              			<div class="col-sm-8">
                              				<select id="taxMode" class="form-control">
                              					<option value="NOTAX">NOTAX</option>
                              					<option value="VAT">VAT</option>
                              					<option value="GST">GST</option>
                              					<option value="CGST">CGST</option>
                              					<option value="SGST">SGST</option>
                              					<option value="IGST">IGST</option>
                              					<option value="OTHER">OTHER</option>
                              				</select>
                              			</div>
                          			</div>
						        	<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="taxName_label">Name <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                                  			<input type="text" id="taxName" class="form-control">
                              			</div>
                          			</div>
                          			<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="taxPer_label">Percentage <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                                  			<input type="text" id="taxPer" class="form-control" onkeydown="numcheck(event)">
                              			</div>
                          			</div>
                          			<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="taxDesc_label">Description <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                                  			<input type="text" id="taxDesc" class="form-control">
                              			</div>
                          			</div>
                          			<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="taxIsgrp_label">Is Group<span class="required_star">*</span></label>
                              			
                              			<div class="col-sm-8">
                              				<select id="taxIsGrp" onchange="makeGroupStat();" class="form-control">
                              					<option value="0">No</option>
                              					<option value="1">Yes</option>
                              				</select>
                              			</div>
                          			</div>
                          			<div id="snglTaxDiv" class="form-group hide">
                              			<table id="singleTaxListTbl" class="table table-bordered table-striped table-condensed-trx table-hover">
                              				<thead>
                              					<tr>
                              						<th></th>
                              						<th>Name</th>
                              						<th>Percentage</th>
                              					</tr>
                              				</thead>
                              				<tbody id="singleTaxList"></tbody>
                              			</table>
                          			</div>
						        </div>
						        <input type="hidden" id="tax_id" value=""></input>
						        <div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
						        <button type="button" id="saveTaxBtn" onclick="javascript:addEditTax()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.save" text="SAVE" /></button>
						        <button type="button" id="updateTaxBtn" onclick="javascript:addEditTax()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.update" text="Update" /></button>
						      </div>
						    </div>
						  </div>
			</div>      
			<!-- Add/Edit tax modal end -->
	
</section>
<!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/inventory/tax/tax.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/inventory/tax/tax_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>
<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}
function showtaxDelModal(id)
{
	   document.getElementById('confirmId').value=id;
	   $('#confirmModal').modal('show');
} 

$(document).ready(function() {
    $('#example').DataTable({
    	"lengthChange": false,
    });
    $('.dataTables_filter input').attr("placeholder", getTaxText.dataTablePlaceHolder);
} );
</script>