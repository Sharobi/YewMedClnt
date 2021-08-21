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
							<form modelAttribute="commonResultSetMapper" class="form-inline" role="form" action="${pageContext.request.contextPath }/accntgrp/searchaccntgrp.htm" method="post">
								<div class="form-group">
								
	<input type="text" class="form-control" placeholder="Group Name" name="groupName" value="${groupName}">
						 
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
								<button class="btn btn-primary" type="button" onclick="javascript:openAddEditModal(0,'','','',0)"><i class="fa fa-plus"></i>&nbsp;<spring:message code="cmn.jsp.btn.add" text="Add" /></button>
							</c:if>
						</div>
					</div>
				</div>
				
           		<%-- <input type="hidden" id="cmpny_id" value="${sessionScope.sesloggedinUser.companyId}" ></input>
           		<input type="hidden" id="user_id" value="${sessionScope.sesloggedinUser.id}" ></input> --%>
							
				<!-- Add/Edit accGroupAddEditModal Modal Starts -->
				
					<div class="modal fade" id="accGroupAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
                              			<label class="col-sm-4 col-sm-4 control-label"><spring:message code="accgroup.jsp.acctype.name" text="Account Type" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                              				<select id="accTypeList" class="form-control">
                              					<option value="0">Select</option>
                              					<c:if test="${!empty allAccTypes}">
                              						<c:forEach items="${allAccTypes}" var="allAccType">
                              							<option value="${allAccType.id}">${allAccType.typeName}</option>
                              						</c:forEach>
                              					</c:if>
                              				</select>
                              			</div>
                          			</div>
						        	<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="accgroup.jsp.name" text="Name" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                                  			<input type="text" id="accGroupName" class="form-control">
                              			</div>
                          			</div>
                          			<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="name_label2"><spring:message code="accgroup.jsp.code" text="Code" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                                  			<input type="text" id="accGroupCode" class="form-control">
                              			</div>
                          			</div>
                          			<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label"><spring:message code="accgroup.jsp.desc" text="Description" /></label>
                              			<div class="col-sm-8">
                                  			<input type="text" id="accGroupDesc" class="form-control">
                              			</div>
                          			</div>
						        </div>
						        <input type="hidden" id="accGroupId" value="">
						        <div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal" ><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
						        
						       
						        <button id="acc_group" type="button" onclick="javascript:addEditaccGroup()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.save" text="SAVE" /></button>
						   
						        
						        
						        
						        
						      </div>
						    </div>
						  </div>
				</div> 
				
				<!-- Add/Edit accGroupAddEditModal Modal Ends -->
				
			  <span id="is_sys"></span>
				
				<table id="example" class="table table-bordered table-striped table-condensed table-hover display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th><spring:message code="accgroup.jsp.type" text="Account Type" /></th>
						<th><spring:message code="accgroup.jsp.name" text="Name" /></th>
						<th><spring:message code="accgroup.jsp.code" text="Code" /></th>
					
						<th><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
					</tr>
				</thead>
				
				<tbody>
				<c:if test="${!empty allAccGroups }">
					<c:forEach items="${allAccGroups}" var="allAccGroup" varStatus="index">
						<tr>
						<td>${allAccGroup.accountTypeName}</td>	
						<td>${allAccGroup.name}</td>
						<td>${allAccGroup.code}</td>	
					
						<td>
							<c:if test="${menuByUserDTO.isAll==1 }">
								<button class="btn btn-info btn-xs" type="button" onclick="javascript:openAddEditModal(${allAccGroup.id},&quot;${allAccGroup.name}&quot;,&quot;${allAccGroup.description}&quot;,&quot;${allAccGroup.code}&quot;,${allAccGroup.accountTypeId},${allAccGroup.is_system})"><i class="fa fa-pencil"></i>&nbsp;<spring:message code="cmn.jsp.btn.edit" text="Edit" /></button>
							</c:if>
							<c:if test="${menuByUserDTO.isView==1 && allAccGroup.is_system==0}">
								<button class="btn btn-theme04 btn-xs" type="button" onclick="showAccGroupDelModal(${allAccGroup.id})"><i class="fa fa-trash-o "></i>&nbsp;<spring:message code="cmn.jsp.btn.dlt" text="Delete" /></button>
							</c:if>
						</td>
					</tr>
					</c:forEach>
				</c:if>
				</tbody>
			</table>		
          		</div>
          	</div>
		</section><!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/accounts/accgroup/accgroup.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/accounts/accgroup/accgroup_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>

<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}

function showAccGroupDelModal(id)
{
	
	 
		   document.getElementById('confirmId').value=id;
		   $('#confirmModal').modal('show');
		 
	
} 
$(document).ready(function() {
	
    $('#example').DataTable({
    	"lengthChange": false
    });
    $(".dataTables_filter input").attr("placeholder", getLedgerText.dataTablePlaceHolder);
});


</script>