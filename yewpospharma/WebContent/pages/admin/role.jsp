<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sharobi.yewpos.role.model.MenuByUserDTO" %>
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">
<style>
.sel_all_b{
	border: 0;
    background-color: #0099e5;
    color: #fff;
    border-radius: 2px;
   } 
 .sel_all_base{
    border: 0;
    background-color: #0099e5;
    color: #fff;
    border-radius: 2px;
 }
 .show_inline{
  
   
 }
 .checkbox-inline{
    width: 32%;
    margin-left: 0 !important;
 }
</style>
      <section class="wrapper">
			<div class="row">
          		<div class="col-lg-12">
          		       <div class="panel panel-default">
					       <div class="panel-body">
                                <div class="col-lg-8 col-md-8 col-sm-12">
							   </div>
                                <div class="pull-right">
									<button class="btn btn-primary" type="button" onclick="javascript:openAddEditModal('','','')"><i class="fa fa-plus"></i>&nbsp;<spring:message code="cmn.jsp.btn.add" text="Add" /></button>
								</div>
					       </div>
				    </div>
			  <table id="roleTbl" class="table table-bordered table-striped table-condensed table-hover display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th><spring:message code="role.jsp.roleId" text="Role ID" /></th>
						<th><spring:message code="role.jsp.roleName" text="Role Name" /></th>
						<th><spring:message code="user.jsp.action" text="Actions" /></th>
					</tr>
				</thead>
				<tbody>
				<c:if test="${!empty allroles}">
					<c:forEach items="${allroles}" var="role" varStatus="index">
						<tr>
					    <td>${role.id}</td>
					    <td>${role.name}</td>
					    <td>
							<button class="btn btn-info btn-xs" type="button" onclick="javascript:openAddEditModal(${role.id},&quot;${role.name}&quot;,${role.storeId})"><i class="fa fa-pencil"></i>&nbsp;<spring:message code="cmn.jsp.btn.edit" text="Edit" /></button>
						    <button class="btn btn-theme04 btn-xs" type="button" onclick="showRoleDelConfirmationModal(${role.id})"><i class="fa fa-trash-o "></i>&nbsp;<spring:message code="cmn.jsp.btn.dlt" text="Delete" /></button> 
						</td>
					   </tr>
					</c:forEach>
				</c:if>
				</tbody>
			</table>
				    
			  <div class="modal fade" id="roleAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
						        <h4 class="modal-title" id="myModalLabel">
						        	<span id="headertext"></span>
						        </h4>
						      </div>
						     <%--  <form modelAttribute="role" action="${pageContext.request.contextPath }/rolemanagement/addRole.htm" method="post" id="roleForm"> --%>
						        
						      <div class="modal-body" id="menuDiv">
						        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
						             <div class="form-horizontal">
							             <div class="form-group">
											<label class="col-sm-3 col-sm-3 control-label" id="roleName_label"><spring:message code="role.jsp.roleName" text="Role Name" /> <span class="required_star">*</span>
											</label>
											<div class="col-sm-9">
												<input type="text" id="roleName" class="form-control" name="roleName">
												<input type="text" id="roleId" name="roleId" class="hide" value=''>
											</div>
										</div>
										<div class="form-group">
										    <label class="col-sm-3 col-sm-3 control-label" id="storeId_label"><spring:message code="user.jsp.store" text="Store Name" /> <span class="required_star">*</span></label>
	                              			<div class="col-sm-9">
	                              				<select class="form-control"  id="storeName">
									 			   <!-- <option value="0">Select...</option>  -->
										 		   <c:forEach items="${allstoresdata}" var="store">
										 		     <option value="${store.id}" ${sesloggedinUser.storeId== store.id ? 'selected' : ''}>${store.name}</option>
													</c:forEach>
								 				</select>
	                              			</div>
	                          			</div>
                          			</div>
						          <button type="button" class="sel_all_base" data-chks="collapse_base" data-stat="1"><span>Select</span> All</button><br>
									<c:if test="${! empty sesappMenuList}">
									<c:forEach items="${sesappMenuList}" var="appList">
									      <c:if test="${appList.menuId != 108}">
										   <div class="panel panel-default maindiv" id="panel_${appList.menuId}">
													<div class="panel-heading show_inline" role="tab" id="heading_${appList.menuId}">
													    <input type="checkbox" style="width:5%;float:left; margin-left: -5px !important;" 
												               name="${appList.menuName}"
															   id="${appList.menuId}"
												               value="1" class="parent" >
														<h4 class="panel-title" style="width:98%;">
														      <a role="button" style="display: block; width:100%;" data-toggle="collapse"  href="#collapse_${appList.menuId}" aria-expanded="true" aria-controls="collapse_${appList.menuId}">${appList.menuName}</a>
														</h4>
											        </div>
										            <div id="collapse_${appList.menuId}" class="panel-collapse collapse out" role="tabpanel" aria-labelledby="collapse_${appList.menuId}">
					                                     <div class="panel-body">
					                                       <c:forEach items="${appList.subMenuList}" var="subMenu">
														          <c:choose>
														              <c:when test="${! empty subMenu.subMenuList}">
														                   <div class="panel panel-default maindiv" id="panel_${subMenu.menuId}">
													                            <div class="panel-heading" role="tab" id="heading_${subMenu.menuId}">
													                              <input type="checkbox" style="width:5%;float:left;margin-left: -5px !important;" 
																	               name="${subMenu.menuName}"
																				   id="${subMenu.menuId}"
																	               value="1" class="child"  >
													                              <h4 class="panel-title" style="width:98%;" aria-expanded="false">
																					  <a role="button" style="display: block; width:100%;" data-toggle="collapse"  href="#collapse_${subMenu.menuId}" aria-expanded="true" aria-controls="collapse_${subMenu.menuId}"> ${subMenu.menuName}</a>
																					</h4>
																				</div>
																			     <div id="collapse_${subMenu.menuId}" class="panel-collapse collapse out" role="tabpanel" aria-labelledby="collapse_${subMenu.menuId}">
																					 <div class="panel-body">
																					     <c:forEach items="${subMenu.subMenuList}" var="childsubMenu">
																					        <c:if test="${childsubMenu.isNone==0 }">
																					          <label class="checkbox-inline">
																								<input type="checkbox" 
																					             name="${childsubMenu.menuName}"
																								 id="${childsubMenu.menuId}"
																					             value="1"  class="grandChild" >${childsubMenu.menuName}</label>
																							 </c:if>
																						 </c:forEach>
																					</div>
																			   </div>
												                            </div>
																	   </c:when>
																	   <c:otherwise>
																			<c:if test="${subMenu.isNone==0 }">
																			   <label class="checkbox-inline">
																				<input type="checkbox" 
																				 id="${subMenu.menuId}"
																				 name="${subMenu.menuName}" 
																				 value="1" class="child" >${subMenu.menuName}
																				 </label>
																			</c:if>
															          </c:otherwise>
																	</c:choose>
														  </c:forEach>
					                                    </div>
					                               </div>
											</div>
											</c:if>
									</c:forEach>
								</c:if>
								
							</div>
						 
											        
					     <div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
					    </div>
					    <div class="modal-footer">
					     <button type="button" class="btn btn-default" data-dismiss="modal" ><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
					     <button type="button" class="btn btn-default"  onclick="addOrUpdateRole();"><spring:message code="cmn.jsp.btn.save" text="SAVE" /></button>
					    </div>
					    <!-- </form> -->
					    </div>
					  </div>
					</div>
       </div>
  </div>
</section><!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/ajax/ajax.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/admin/role/role.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/admin/role/role_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>

<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";
var activeStoreId = "${sesloggedinUser.storeId}";
$(document).ready(function() {
    $('#roleTbl').DataTable({
    	"lengthChange": false,
    });
    $('.dataTables_filter input').attr("placeholder", getRoleText.dataTablePlaceHolder);
});


$(".sel_all_base").click(function() {
	var stat = $(this).attr("data-stat");
	if(stat=="1") {
		$('.maindiv :checkbox:enabled').prop('checked', true);
		$(this).find("span").html("Deselect");
		$(this).attr("data-stat","0");
	}
	else if(stat=="0") {
		$('.maindiv :checkbox:enabled').prop('checked', false);
		$(this).find("span").html("Select");
		$(this).attr("data-stat","1");
	}
});

$(".parent").click(function() { 
	var id=$(this).attr('id');
	if($(this).prop("checked") == true) {
		$('#collapse_' + id + ' :checkbox:enabled').prop('checked', true);
	}
	else  {
		$('#collapse_' + id + ' :checkbox:enabled').prop('checked', false);
	}
});

$(".child").click(function() { 
	var id=$(this).attr('id');
	var parentId = $('#'+id).closest('.panel-collapse').attr('id');
	var length = parentId.length;
	    parentId = parentId.substring(9,length);
	/* alert(id + "  "+ parentId); */
	if($(this).prop("checked") == true) {
		$('#collapse_' + id + ' :checkbox:enabled').prop('checked', true);
		$("#"+ parentId).prop("checked", true);
		
	}
	else{
		$('#collapse_' + id + ' :checkbox:enabled').prop('checked', false);
		var gfchk = 0;
		$("#collapse_"+ parentId+" input[type='checkbox']").each(
				function() {
					console.log($(this).attr('id'));
					if($(this).prop("checked") == true) {
						gfchk = 1;
						return;
					}
				}
		);
		if(gfchk==1) {
			$("#"+ parentId).prop("checked", true);
		} else {
			$("#"+ parentId).prop("checked", false);
		}
	}
});


$(".grandChild").click(function() { 
	var id=$(this).attr('id');
	var childId = $('#'+id).closest('.panel-collapse').attr('id');
	var childlength = childId.length;
	    childId = childId.substring(9,childlength); 
	var parentId = $('#'+childId).closest('.panel-collapse').attr('id');
	var parentlength = parentId.length;
	    parentId = parentId.substring(9,parentlength); 
	 /*alert(id + " " + childId + " " + parentId); */
	
	if($(this).prop("checked") == true) {
		$("#"+ parentId).prop("checked", true);
		$("#"+ childId).prop("checked", true);
	}
	else{
		var fchk = 0;
		$("#collapse_"+ childId+" input[type='checkbox']").each(
				function() {
					console.log($(this).attr('id'));
					if($(this).prop("checked") == true) {
						fchk = 1;
						return;
					}
				}
		);
		if(fchk==1) {
			$("#"+ childId).prop("checked", true);
		} else {
			$("#"+ childId).prop("checked", false);
		}
		
		var gfchk = 0;
		$("#collapse_"+ parentId+" input[type='checkbox']").each(
				function() {
					console.log($(this).attr('id'));
					if($(this).prop("checked") == true) {
						gfchk = 1;
						return;
					}
				}
		);
		if(gfchk==1) {
			$("#"+ parentId).prop("checked", true);
		} else {
			$("#"+ parentId).prop("checked", false);
		}
		
		
	}
});

</script>