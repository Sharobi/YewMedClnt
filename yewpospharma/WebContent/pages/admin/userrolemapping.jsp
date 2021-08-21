<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">
		<section class="wrapper">
			<div class="row">
          		<div class="col-lg-12">
   				<p><%-- <spring:message code="subcat.jsp.title" text="SubCategory..." /> --%></p>
   				<div class="panel panel-default">
					<div class="panel-body">

						<div class="col-lg-8 col-md-8 col-sm-12">
							
						</div>

						<div class="pull-right">
							<button class="btn btn-primary" type="button" onclick="javascript:openAddEditModal('','','')"><i class="fa fa-plus"></i>&nbsp;<spring:message code="cmn.jsp.btn.add" text="Add" /></button>
						</div>
					</div>
				</div>

				<!-- Add/Edit userAddEditModal Starts -->

					<div class="modal fade" id="mappingAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
                              			<label class="col-sm-4 col-sm-4 control-label" id="user_label"><spring:message code="mapping.jsp.user" text="User" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                              				<select class="form-control"  id="userData">
								 			   <option value="0" selected>Select...</option> 
									 		   <c:forEach items="${allusers}" var="user">
									 		     <option value="${user.id}">${user.userName}</option>
												</c:forEach>
							 				</select>
                              			</div>
                          			</div>
                          			 <div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="role_label"><spring:message code="mapping.jsp.role" text="Role" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                              				<select class="form-control"  id="roleData">
								 		       <option value="0" selected>Select...</option>  
									 		   <c:forEach items="${allroles}" var="role">
									 		     <option value="${role.id}">${role.name}</option>
												</c:forEach>
							 				</select>
                              			</div>
                          			</div>
						        </div>
						        <input type="hidden" id="mappingId" value="">
						        <div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal" ><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
						        <button type="button" onclick="javascript:addEditRoleUserMapping()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.save" text="SAVE" /></button>
						      </div>
						    </div>
						  </div>
				</div>

				<!-- Add/Edit accGroupAddEditModal Modal Ends -->



				<table id="example" class="table table-bordered table-striped table-condensed table-hover display" cellspacing="0" width="100%">
				<thead>
					<tr>
						
						<th><spring:message code="user.jsp.username" text="User Name" /></th>
						<th><spring:message code="role.jsp.roleName" text="Role Name" /></th>
						<th><spring:message code="user.jsp.action" text="Actions" /></th>
					</tr>
				</thead>

				<tbody>
				<c:if test="${!empty allmapping}">
					<c:forEach items="${allmapping}" var="mapping" varStatus="index">
						<tr>
					    <td>${mapping.userName}</td>
						<td>${mapping.roleName}</td>
						<td>
							<button class="btn btn-info btn-xs" type="button" onclick="javascript:openAddEditModal(${mapping.id},${mapping.userId},${mapping.roleId})"><i class="fa fa-pencil"></i>&nbsp;<spring:message code="cmn.jsp.btn.edit" text="Edit" /></button>
							<%-- <button class="btn btn-theme04 btn-xs" type="button" onclick="showUserDelModal(${mapping.id})"><i class="fa fa-trash-o "></i>&nbsp;<spring:message code="cmn.jsp.btn.dlt" text="Delete" /></button>  --%>
							
						</td>
					   </tr>
					</c:forEach>
				</c:if>
				</tbody>
			</table>
          		</div>
          	</div>
		</section><!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/admin/mapping/mapping.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/admin/mapping/mapping_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>

<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";
var activeStoreId = "${sesloggedinUser.storeId}";
/* var roleArray=[]; */

$(document).ready(function() {
	/* <c:forEach items="${allroles}" var="role" varStatus="loop">
	  var role={};
	 role.name = '${role.name}';
	 role.id = '${role.id}'; 
	 roleArray.push(role);
	</c:forEach> */
	
	
    $('#example').DataTable({
    	"lengthChange": false,
    });
    $('.dataTables_filter input').attr("placeholder", getMappingText.dataTablePlaceHolder);
});
function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}

function showUserDelModal(id)
{
	   document.getElementById('confirmId').value=id;
	   $('#confirmModal').modal('show');
}


</script>