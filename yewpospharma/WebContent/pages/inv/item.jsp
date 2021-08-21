<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p>
				<%-- <spring:message code="item.jsp.title" text="Item..." /> --%>
			</p>
			<input type="hidden" id="result" value="${result}" />
			<input type="hidden" id="add_edit_delete" value="${add_edit_delete}" />
			
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="col-lg-10 col-md-8 col-sm-12">
					<span style="float:left;">
						<form modelAttribute="commonResultSetMapper" class="form-inline" role="form" action="${pageContext.request.contextPath }/item/searchitem.htm" method="post" id="searchForm">
							<div class="form-group">
								<label class="control-label"><spring:message code="item.jsp.searchBy" text="Search By" /></label>
								<select class="form-control" id="searchBySelect" name="searchCriteria">
									<option value="name">Item Name</option>
									<option value="content">Content</option>
									<option value="manfc">Manufacturer</option>
									<!-- <option value="sch">Schedule</option> -->
									<option value="hsn">HSN</option>
								</select>	
								<input type="hidden" id="criteriaName" value="${searchCriteria}" />							
								<input type="text" class="form-control cmn" id="itemName1" value="${commonResultSetMapper.itemName}" placeholder="<spring:message code="item.jsp.name" text="Item Name" />" name="itemName" >
								<input type="text" class="form-control cmn hide" id="contentName1" value="${commonResultSetMapper.contentName}" placeholder="<spring:message code="itemmstr.jsp.content" text="Content" />" name="contentName" >
								<input type="text" class="form-control cmn hide" id="manufacturerName1" value="${commonResultSetMapper.manufacturerName}" placeholder="<spring:message code="itemmstr.jsp.manufacturer" text="Manufacturer" />" name="manufacturerName" >
								<%-- <input type="text" class="form-control cmn hide" id="schedule1" value="${commonResultSetMapper.schdName}" placeholder="Schedule" name="schdName" > --%>
								<input type="text" class="form-control cmn hide" id="hsn1" value="${commonResultSetMapper.hsnCode}" placeholder="HSN Code" name="hsnCode" >
							</div>
							<div class="form-group hide">

								<input type="text" class="form-control" placeholder="<spring:message code="item.jsp.code" text="Item Code" />">
							</div>
							<div class="form-group">
								<select class="form-control" name="queryCondn">
									<option value="like">LIKE</option>
									<option value="equals">=</option>
								</select>
							</div>
							<button type="submit" id="searchBtn" class="btn btn-theme">
								<spring:message code="cmn.jsp.search" text="Search" />
							</button>
						</form></span>
						
						<span style="float:right;">
						<div id="saveitemaster">
								<span id="operationbtnspan"></span>
									<a href="javascript:" id="itemlistExport" class="btn btn-theme"><spring:message code="cmn.jsp.export" text="EXPORT" /></a> 
								</div></span>
							<form modelAttribute="commonResultSetMapper" align="center" class="form-inline" role="form" action="${pageContext.request.contextPath }/item/searchtransitem.htm" method="post" id="searchTransForm">
							<button type="submit" id="searchTransItemBtn" class="btn btn-theme">
								<spring:message code="cmn.jsp.searchTransItem" text="Trans Item" />
							</button>
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
						<th><spring:message code="item.jsp.name" text="Item Name" /></th>
						<th><spring:message code="itemmstr.jsp.group" text="Group" /></th>
						<th><spring:message code="itemmstr.jsp.schedule" text="Schedule" /></th>
						<th><spring:message code="itemmstr.jsp.content" text="Content" /></th>
						<th><spring:message code="itemmstr.jsp.manufacturer" text="Manufacturer" /></th>
						<th><spring:message code="item.jsp.packunit" text="PUnit" /></th>
						<th><spring:message code="item.jsp.looseunit" text="LUnit" /></th>
						<th><spring:message code="itemmstr.jsp.rack" text="Rack" /></th>
						<th><spring:message code="itemmstr.jsp.price" text="Price" /></th>
						<th style="width: 15%;"><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty allItems }">
						<c:forEach items="${allItems}" var="allitem">
							<tr>
								<td>${allitem.itemName}</td>
								<td>${allitem.groupName}</td>
								<td>${allitem.schdName}</td>
								<td>${allitem.contentName}</td>
								<td>${allitem.manufacturerName}</td>
								<td>${allitem.packUnitName}</td>
								<td>${allitem.looseUnitName}</td>
								<td>${allitem.rackName}</td>
								<td>${allitem.price}</td>
								<td><c:if test="${menuByUserDTO.isAll==1}">
										<button class="btn btn-info btn-xs" type="button" onclick="openAddEditModal(${allitem.itemId})">
											<i class="fa fa-pencil"></i>
											<spring:message code="cmn.jsp.btn.edit" text="Edit" />
										</button>
									</c:if> <c:if test="${menuByUserDTO.isView==1}">
										<button class="btn btn-theme04 btn-xs" type="button" onclick="showItemDelModal(${allitem.itemId})">
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
	<!-- Add/Edit unit modal start -->
	<div class="modal fade" id="itemAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="item.jsp.name" text="Item Name" /></label>
							<div class="col-sm-8">
								<input type="text" id="itemName" class="form-control">
							</div>
						</div>
					</div>
					<input type="hidden" id="itemId" value=""></input>
					<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<spring:message code="cmn.jsp.btn.close" text="Close" />
					</button>
					<button type="button" onclick="javascript:addEditItem()" class="btn btn-theme">
						<spring:message code="cmn.jsp.btn.save" text="SAVE" />
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Add/Edit unit modal end -->
</section>
<!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/inventory/item/item.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/inventory/item/item_en.js"></script>	
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/common/table2excel.js"></script>  
</c:if>
<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}
function showItemDelModal(id)
{
	   document.getElementById('confirmId').value=id;
	   $('#confirmModal').modal('show');
} 

$(document).ready(function() {
	
	checkAddEditDeleteResult();
	
    $('#example').DataTable({
    	"lengthChange": false,
    });
    $('.dataTables_filter input').attr("placeholder", getItemText.dataTablePlaceHolder);
} );



$(function () {
    $("#itemlistExport").click(function () {  
    	$('<table>').append($("#example").DataTable().$('tr').clone()).table2excel({  
            filename: "item_list.xls"  
        });  
    }); 

});  

</script>