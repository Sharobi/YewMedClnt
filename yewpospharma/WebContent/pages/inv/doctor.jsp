<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p><%-- <spring:message code="dctr.jsp.title" text="Doctor..." /> --%></p>
			<div class="pull-right">
				<c:if test="${menuByUserDTO.isAll==1}">
					<button class="btn btn-primary" type="button" onclick="openAddEditModal(0)"><i class="fa fa-plus"></i> <spring:message code="cmn.jsp.btn.add" text="Add" /></button>
				</c:if>
			</div>
			
			<table id="example" class="table table-bordered table-striped table-condensed table-hover display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th><spring:message code="dctr.jsp.name" text="Doctor Name" /></th>
						<th><spring:message code="dctr.jsp.code" text="Code" /></th>
						<th><spring:message code="dctr.jsp.qualification" text="Qualification" /></th>
						<th><spring:message code="dctr.jsp.speciality" text="Speciality" /></th>
						<th><spring:message code="dctr.jsp.country" text="Country" /></th>
						<th><spring:message code="dctr.jsp.phn" text="Phone No." /></th>
						<th><spring:message code="dctr.jsp.opbal" text="Opening Balance" /></th>
						<th><spring:message code="dctr.jsp.commPer" text="Commision Percentage" /></th>
						<th><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty allDctrs }">
						<c:forEach items="${allDctrs}" var="allDctr">
							<tr>
								<td>${allDctr.name}</td>
								<td>${allDctr.code}</td>
								<td>${allDctr.qualification}</td>
								<td>${allDctr.speciality}</td>
								<td>${allDctr.country}</td>
								<td>${allDctr.phoneNo}</td>
								<td>${allDctr.opBal}</td>
								<td>${allDctr.commPer}</td>
								<td>
									<c:if test="${menuByUserDTO.isAll==1}">
										<button class="btn btn-info btn-xs" type="button" onclick="openAddEditModal(${allDctr.id})"><i class="fa fa-pencil"></i> <spring:message code="cmn.jsp.btn.edit" text="Edit" /></button>
									</c:if>
									<c:if test="${menuByUserDTO.isView==1}">	
										<button class="btn btn-theme04 btn-xs" type="button" onclick="showDoctorDelModal(${allDctr.id})"><i class="fa fa-trash-o "></i> <spring:message code="cmn.jsp.btn.dlt" text="Delete" /></button>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
	
	<!-- Add/Edit group modal start -->
			<div class="modal fade" id="doctorAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
	                              			<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="dctr.jsp.name" text="Doctor Name" /> <span class="required_star">*</span></label>
	                              			<div class="col-sm-8">
	                                  			<input type="text" id="dctrName" class="form-control">
	                              			</div>
	                          			</div>
	                          			<div class="form-group">
	                              			<label class="col-sm-4 col-sm-4 control-label" id="code_label"><spring:message code="dctr.jsp.code" text="Code" /></label>
	                              			<div class="col-sm-8">
	                                  			<input type="text" id="code" class="form-control">
	                              			</div>
	                          			</div>
	                          			<div class="form-group">
	                              			<label class="col-sm-4 col-sm-4 control-label" id="locked_label"><spring:message code="dctr.jsp.locked" text="Is Locked" /></label>
	                              			<div class="col-sm-8">
	                                  			<input type="checkbox" id="locked">
	                              			</div>
	                          			</div>
	                          			<div class="form-group">
	                              			<label class="col-sm-4 col-sm-4 control-label" id="qualification_label"><spring:message code="dctr.jsp.qualification" text="Qualification" /></label>
	                              			<div class="col-sm-8">
	                                  			<input type="text" id="qualification" class="form-control">
	                              			</div>
	                          			</div>
	                          			<div class="form-group">
	                              			<label class="col-sm-4 col-sm-4 control-label" id="speciality_label"><spring:message code="dctr.jsp.speciality" text="Speciality" /></label>
	                              			<div class="col-sm-8">
	                                  			<input type="text" id="speciality" class="form-control">
	                              			</div>
	                          			</div>
	                          			<div class="form-group">
	                              			<label class="col-sm-4 col-sm-4 control-label" id="addrs_label"><spring:message code="dctr.jsp.address" text="Address" /></label>
	                              			<div class="col-sm-8">
	                                  			<input type="text" id="addrs" class="form-control">
	                              			</div>
	                          			</div>
	                          			<div class="form-group">
	                              			<label class="col-sm-4 col-sm-4 control-label" id="pin_label"><spring:message code="dctr.jsp.pin" text="Pin" /></label>
	                              			<div class="col-sm-8">
	                                  			<input type="text" id="pin" class="form-control">
	                              			</div>
	                          			</div>
                          			</div>
                          			<div class="col-sm-6 col-sm-6 ">
	                          			<div class="form-group">
	                              			<label class="col-sm-4 col-sm-4 control-label" id="city_label"><spring:message code="dctr.jsp.city" text="City" /></label>
	                              			<div class="col-sm-8">
	                                  			<input type="text" id="city" class="form-control">
	                              			</div>
	                          			</div>
	                          			<div class="form-group">
	                              			<label class="col-sm-4 col-sm-4 control-label" id="state_label"><spring:message code="dctr.jsp.state" text="State" /></label>
	                              			<div class="col-sm-8">
	                                  			<input type="text" id="state" class="form-control">
	                              			</div>
	                          			</div>
	                          			<div class="form-group">
	                              			<label class="col-sm-4 col-sm-4 control-label" id="country_label"><spring:message code="dctr.jsp.country" text="Country" /></label>
	                              			<div class="col-sm-8">
	                                  			<input type="text" id="country" class="form-control">
	                              			</div>
	                          			</div>
	                          			<div class="form-group">
	                              			<label class="col-sm-4 col-sm-4 control-label" id="phn_label"><spring:message code="dctr.jsp.phn" text="Phone No." /></label>
	                              			<div class="col-sm-8">
	                                  			<input type="text" id="phn" class="form-control">
	                              			</div>
	                          			</div>
	                          			<div class="form-group">
	                              			<label class="col-sm-4 col-sm-4 control-label" id="fax_label"><spring:message code="dctr.jsp.fax" text="Fax" /></label>
	                              			<div class="col-sm-8">
	                                  			<input type="text" id="fax" class="form-control">
	                              			</div>
	                          			</div>
	                          			<div class="form-group">
	                              			<label class="col-sm-4 col-sm-4 control-label" id="opbal_label"><spring:message code="dctr.jsp.opbal" text="Opening Balance" /></label>
	                              			<div class="col-sm-8">
	                                  			<input type="text" id="opbal" class="form-control">
	                              			</div>
	                          			</div>
	                          			<div class="form-group">
	                              			<label class="col-sm-4 col-sm-4 control-label" id="commPer_label"><spring:message code="dctr.jsp.commPer" text="Commision Percentage" /></label>
	                              			<div class="col-sm-8">
	                                  			<input type="text" id="commPer" class="form-control">
	                              			</div>
	                          			</div>
                          			</div>
                          		 </div>
						        <input type="hidden" id="dctr_id" value=""></input>
						        <div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
						      </div>
						      <div class="modal-footer" style="border-top: 0px solid #e5e5e5;">
						        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
						        <button type="button" onclick="javascript:addEditDoctor()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.save" text="SAVE" /></button>
						      </div>
						    </div>
						  </div>
			</div>      
			<!-- Add/Edit group modal end -->
	
</section>
<!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/inventory/doctor/doctor.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/inventory/doctor/doctor_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>
<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}
function showDoctorDelModal(id)
{
	   document.getElementById('confirmId').value=id;
	   $('#confirmModal').modal('show');
} 

$(document).ready(function() {
    $('#example').DataTable({
    	"lengthChange": false,
    });
    $('.dataTables_filter input').attr("placeholder", getDoctorText.dataTablePlaceHolder);
} );
</script>