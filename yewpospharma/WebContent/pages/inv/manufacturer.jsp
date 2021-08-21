<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p>
				<%-- <spring:message code="manufacturer.jsp.title" text="Manufacturer..." /> --%>
			</p>
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="col-lg-8 col-md-8 col-sm-12">
						<form modelAttribute="manufacturerMaster" class="form-inline" role="form" action="${pageContext.request.contextPath }/manufacturer/searchManufacturer.htm" method="post">
							<div class="form-group">

								<input type="text" class="form-control" placeholder='<spring:message code="manufacturer.jsp.name" text="Manufacturer Name" />' name="name" value="${manufacturerMaster.name}">
							</div>
							<div class="form-group">
								<select class="form-control" name="qryCondition" >
									<option value="like" >LIKE</option>
									<option value="equals">=</option>
								</select>
							</div>

							<button type="submit" class="btn btn-theme">
								<spring:message code="cmn.jsp.search" text="Search" />
							</button>
						</form>
					</div>
					<div class="pull-right">
						<c:if test="${menuByUserDTO.isAll==1}">
							<button class="btn btn-primary" type="button" onclick="openAddEditModal(0,'','','','')">
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
						<th><spring:message code="manufacturer.jsp.code" text="Manufacturer Code" /></th>
						<th><spring:message code="manufacturer.jsp.name" text="Manufacturer Name" /></th>
						<th><spring:message code="manufacturer.jsp.addrs" text="Address" /></th>
						<th><spring:message code="manufacturer.jsp.phn" text="Contact No." /></th>
						<th class="hide"><spring:message code="manufacturer.jsp.fax" text="FAX" /></th>
						<th class="hide"><spring:message code="manufacturer.jsp.email" text="Email" /></th>
						<th class="hide"><spring:message code="manufacturer.jsp.url" text="URL" /></th>
						<th style="width: 11%;"><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty allManufacturers }">
						<c:forEach items="${allManufacturers}" var="allManufacturer">
							<tr>
								<td>${allManufacturer.code}</td>
								<td>${allManufacturer.name}</td>
								<td>${allManufacturer.address}</td>
								<td>${allManufacturer.phone}</td>
								<td class="hide">${allManufacturer.fax}</td>
								<td class="hide">${allManufacturer.email}</td>
								<td class="hide">${allManufacturer.url}</td>
								<td><c:if test="${menuByUserDTO.isAll==1}">
								<div class="form-inline">
										<button class="btn btn-info btn-xs" type="button" onclick="openAddEditModal(${allManufacturer.id},&quot;${allManufacturer.code}&quot;,&quot;${allManufacturer.name}&quot;,&quot;${allManufacturer.address}&quot;,&quot;${allManufacturer.phone}&quot;,&quot;${allManufacturer.fax}&quot;,&quot;${allManufacturer.email}&quot;,&quot;${allManufacturer.url}&quot;)">
											<i class="fa fa-pencil"></i>
											<spring:message code="cmn.jsp.btn.edit" text="Edit" />
										</button>
									</c:if> <c:if test="${menuByUserDTO.isView==1}">
										<button class="btn btn-theme04 btn-xs" type="button" onclick="showManufacturerDelModal(${allManufacturer.id})">
											<i class="fa fa-trash-o "></i>
											<spring:message code="cmn.jsp.btn.dlt" text="Delete" />
										</button>
									</c:if>
									</div>
									</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>

	<!-- Add/Edit Content modal start -->
	<div class="modal fade" id="manufacturerAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
							<label class="col-sm-4 col-sm-4 control-label" id="code_label"><spring:message code="manufacturer.jsp.code" text="Manufacturer Code" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="manufctr_code" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="manufacturer.jsp.name" text="Manufacturer Name" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="manufctr_name" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="addrs_label"><spring:message code="manufacturer.jsp.addrs" text="Address" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<textarea  id="addrs" class="form-control" rows="4"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="manufacturer.jsp.phn" text="Contact No." /></label>
							<div class="col-sm-8">
								<input type="text" id="phn" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="manufacturer.jsp.fax" text="FAX" /></label>
							<div class="col-sm-8">
								<input type="text" id="fax" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="manufacturer.jsp.email" text="Email" /></label>
							<div class="col-sm-8">
								<input type="text" id="email" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="manufacturer.jsp.url" text="URL" /></label>
							<div class="col-sm-8">
								<input type="text" id="url" class="form-control" />
							</div>
						</div>
					</div>
					<input type="hidden" id="manufctr_id" value=""></input>
					<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<spring:message code="cmn.jsp.btn.close" text="Close" />
					</button>
					<button type="button" onclick="javascript:addEditManufacturer()" class="btn btn-theme">
						<spring:message code="cmn.jsp.btn.save" text="SAVE" />
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Add/Edit Content modal end -->

</section>
<!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/inventory/manufacturer/manufacturer.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/inventory/manufacturer/manufacturer_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>
<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}
function showManufacturerDelModal(id)
{
	   document.getElementById('confirmId').value=id;
	   $('#confirmModal').modal('show');
} 

$(document).ready(function() {
    $('#example').DataTable({
    	"lengthChange": false,
    });
    $('.dataTables_filter input').attr("placeholder", getManufacturerText.dataTablePlaceHolder);
} );
</script>