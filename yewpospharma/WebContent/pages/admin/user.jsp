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
							<%-- <form modelAttribute="commonResultSetMapper" class="form-inline" role="form" action="${pageContext.request.contextPath }/usermanagement/searchUser.htm" method="post">
								<div class="form-group">

									<input type="text" class="form-control" placeholder="User Name" name="User Name" value="${commonResultSetMapper.groupName}">
								</div>
								<div class="form-group">
									<select class="form-control" name="qryCondition" >
										<option value="like">LIKE</option>
										<option value="equals">=</option>
									</select>
								</div>
								<button type="submit" class="btn btn-theme"><spring:message code="cmn.jsp.search" text="Search" /></button>
							</form> --%>
						</div>

						<div class="pull-right">
							<button class="btn btn-primary" type="button" onclick="javascript:openAddEditModal('','','','','','','','','')"><i class="fa fa-plus"></i>&nbsp;<spring:message code="cmn.jsp.btn.add" text="Add" /></button>
						</div>
					</div>
				</div>

				<!-- Add/Edit userAddEditModal Starts -->

					<div class="modal fade" id="userAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
                              			<label class="col-sm-4 col-sm-4 control-label" id="fName_label"><spring:message code="user.jsp.fname" text="First Name" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                              			    <input type="text" id="fName" class="form-control">
                              			</div>
                          			</div>
                          			<div class="form-group">
										<label class="col-sm-4 col-sm-4 control-label" id="lName_label"><spring:message code="user.jsp.lname" text="Last Name" /> <span class="required_star">*</span>
										</label>
										<div class="col-sm-8">
											<input type="text" id="lName" class="form-control">
										</div>
									</div>
						        	<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="email_label"><spring:message code="user.jsp.email" text="Email" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                              				<input type="text" id="email" class="form-control">
                              			</div>
                          			</div>
                          			<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="uName_label"><spring:message code="user.jsp.user.jsp.username" text="User Name" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                              				<input type="text" id="uName" class="form-control">
                              			</div>
                          			</div>
						        	<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="password_label"><spring:message code="user.jsp.password" text="Password" /> <span class="required_star" id="pwd">*</span></label>
                              			<div class="col-sm-8">
                              				<input type="password" id="password" class="form-control">
                              			</div>
                          			</div>
                          			<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="phone_label"><spring:message code="user.jsp.phone" text="Contact No" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                              				<input type="text" id="phone" class="form-control">
                              			</div>
                          			</div>
                          			<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="storeId_label"><spring:message code="user.jsp.store" text="Store Name" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                              				<select class="form-control"  id="storeName">
								 			   <!-- <option value="0">Select...</option>  -->
									 		   <c:forEach items="${allstoresdata}" var="store">
									 		     <option value="${store.id}" ${sesloggedinUser.storeId== store.id ? 'selected' : ''}>${store.name}</option>
												</c:forEach>
							 				</select>
                              			</div>
                          			</div>
                          			<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="isactive_label"><spring:message code="user.jsp.is_active" text="Is Active" /></label>
                              			<div class="col-sm-8">
                                  			<select id="activityStatus" class="form-control" >
					                           <option value="1" selected>YES</option>
					                           <option value="0">NO</option>
					                        </select>
                              			</div>
                          			</div>
                          			<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="islocked_label"><spring:message code="user.jsp.is_locked" text="Is Locked" /></label>
                              			<div class="col-sm-8">
	                              			<select id="lockStatus" class="form-control" >
					                           <option value="1">YES</option>
					                           <option value="0" selected>NO</option>
					                        </select>
                                  		</div>
                          			</div>
						        </div>
						        <input type="hidden" id="userId" value="">
						        <div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal" ><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
						        <button type="button" onclick="javascript:addEditUser()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.save" text="SAVE" /></button>
						      </div>
						    </div>
						  </div>
				</div>

				<!-- Add/Edit accGroupAddEditModal Modal Ends -->



				<table id="example" class="table table-bordered table-striped table-condensed table-hover display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th><spring:message code="user.jsp.fname" text="First Name" /></th>
						<th><spring:message code="user.jsp.lname" text="Last Name" /></th>
						<th><spring:message code="user.jsp.username" text="User Name" /></th>
						<th><spring:message code="user.jsp.email" text="Email" /></th>
						<th><spring:message code="user.jsp.phone" text="Contact No" /></th>
						<th><spring:message code="user.jsp.is_active" text="Is Active" /></th>
						<th><spring:message code="user.jsp.is_locked" text="Is Locked" /></th>
						<th><spring:message code="user.jsp.action" text="Actions" /></th>
						
					</tr>
				</thead>

				<tbody>
				<c:if test="${!empty allusers}">
					<c:forEach items="${allusers}" var="user" varStatus="index">
						<tr>
					    <td>${user.fname}</td>
					    <td>${user.lname}</td>
						<td>${user.userName}</td>
						<td>${user.email}</td>
						<td>${user.phone}</td>
					    <td>${user.isActive == '0' ? 'In Active' : 'Active'}</td>
						<td>${user.isLocked == '0' ? 'Un Locked' : 'Locked'}</td>
						<td>
							<button class="btn btn-info btn-xs" type="button" onclick="javascript:openAddEditModal(${user.id},&quot;${user.fname}&quot;,&quot;${user.lname}&quot;,&quot;${user.userName}&quot;,&quot;${user.email }&quot;,${user.storeId},&quot;${user.phone}&quot;,${user.isActive},${user.isLocked})"><i class="fa fa-pencil"></i>&nbsp;<spring:message code="cmn.jsp.btn.edit" text="Edit" /></button>
							<%-- <button class="btn btn-theme04 btn-xs" type="button" onclick="showUserDelModal(${user.id})"><i class="fa fa-trash-o "></i>&nbsp;<spring:message code="cmn.jsp.btn.dlt" text="Delete" /></button> --%>
							
						</td>
					   </tr>
					</c:forEach>
				</c:if>
				</tbody>
			</table>
          		</div>
          	</div>
		</section><!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/admin/user/user.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/admin/user/user_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>

<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";
var activeStoreId = "${sesloggedinUser.storeId}";
$(document).ready(function() {
    $('#example').DataTable({
    	"lengthChange": false,
    });
    $('.dataTables_filter input').attr("placeholder", getUserText.dataTablePlaceHolder);
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