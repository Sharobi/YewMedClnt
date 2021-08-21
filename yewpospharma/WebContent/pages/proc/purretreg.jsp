<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p><%-- <spring:message code="purretrnreg.jsp.title" text="Purchase Return Register" />... --%></p>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<div class="panel-trx panel-default">
				<div class="panel-body-trx">
					<form modelAttribute="commonResultSetMapper" action="${pageContext.request.contextPath }/purret/searchpurreturnmemoinvoice.htm" method="post" id="searchForm">
						<table>
							<tr align="center" style="font-weight: bold;">
								<td><spring:message code="purinvreg.jsp.frmdt" text="From Date" /></td>
								<td><spring:message code="purinvreg.jsp.todt" text="To Date" /></td>
								<td colspan="4"><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></td>
								<td width="34%"><spring:message code="purinvdet.jsp.vendor" text="Vendor" /></td>
							</tr>
							<tr>
								<td width="197px;" style="padding: 0 1px;">
									<div class="input-group">
										<input type="text" readonly="readonly" class="form-control-trx" id="stdate" name="startDate" value="${commonResultSetMapper.startDate}">
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-th"></span>
										</div>
									</div>
								</td>
								<td width="197px;" style="padding: 0 1px;">
									<!-- <div class="input-group date" data-provide="datepicker"> -->
									<div class="input-group">
										<input type="text" readonly="readonly" class="form-control-trx" id="enddate" name="endDate" value="${commonResultSetMapper.endDate}">
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-th"></span>
										</div>
									</div>
								</td>
								<%-- <td style="padding: 0 1px;">
									<div class="input-group">
										<span class="input-group-addon" id="">PRM/${sessionScope.sesloggedinUser.finyrCode}/</span> <input class="form-control-trx" type="text" name="invoiceNo" value="${commonResultSetMapper.invoiceNo}">
									</div>
								</td> --%>
								<td width="155px;" style="padding: 0 1px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retmemoDoc" value="PRM/" size="10" ></td>
								<c:if test="${empty commonResultSetMapper.finyrCode}">
									<td width="50px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" name="finyrCode" value="${sessionScope.sesloggedinUser.finyrCode}" size="5"></td>
								</c:if>
								<c:if test="${!empty commonResultSetMapper.finyrCode}">
									<td width="50px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" name="finyrCode" value="${commonResultSetMapper.finyrCode}" size="5"></td>
								</c:if>
								<td style="padding: 0 1px;" width="2.5%"><input class="form-control-trx" type="text" style="padding: 4px 0px;" value="/" readonly></td>
								<td width="202px;"><div class="input-group"><input class="form-control-trx" type="text" name="invoiceNo" value="${commonResultSetMapper.invoiceNo}"></div></td>
								
								<td style="padding: 0 1px;"><select class="form-control-trx" name="distributorId" id="seldistributor">
										<c:if test="${!empty allVendors}">
										<option value="0">All</option>
											<c:forEach items="${allVendors}" var="allVendor">
												<c:if test="${commonResultSetMapper.distributorId==allVendor.id}">
													<option value="${allVendor.id}" selected="selected">${allVendor.name}</option>
												</c:if>
												<c:if test="${commonResultSetMapper.distributorId!=allVendor.id}">
													<option value="${allVendor.id}">${allVendor.name}</option>
												</c:if>
											</c:forEach>
										</c:if>
								</select></td>
								<td style="padding: 0 2px;"><button type="button" id="search_btn" class="btn btn-theme">Search</button></td>
								<td id="postall_td">
									<button type="button" id="postall_btn" onclick="postAllPR()" class="btn btn-theme">
										Post All
									</button>
								</td>
							</tr>
							<tr>
								<td colspan="6"><div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<table id="purrettable" class="table table-bordered table-striped table-condensed table-hover">
				<thead>
					<tr>
						<th><input name="select_all" value="1" id="example-select-all" type="checkbox" style="zoom: 2;"/></th>
						<th><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></th>
						<th><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></th>
						<th><spring:message code="purinvdet.jsp.vendor" text="Vendor" /></th>
						<th class="numeric"><spring:message code="purinvdet.jsp.total" text="Total"/></th>
						<th class="numeric"><spring:message code="purinvdet.jsp.totmrp" text="Tot.MRP:" /></th>
						<th class="numeric"><spring:message code="purinvdet.jsp.nettotal" text="NetTotal:" /></th>
						<th><spring:message code="purinvdet.jsp.remarks" text="Remarks:" /></th>
						<th><spring:message code="purinvreg.jsp.status" text="Status" /></th>
						<th><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty purchaseReturnDTOs }">
						<c:forEach items="${purchaseReturnDTOs}" var="purretchase">
							<tr id="${purretchase.purchaseReturnId}">
								<c:choose>
			        					 <c:when test = "${purretchase.isPosted==0}">
			           							 <td><input type="checkbox" name="id[]" id="${purretchase.purchaseReturnId}" value="${purretchase.purchaseReturnId}" style="zoom: 1.5;" onchange="setChange(this);"></td>
			         					 </c:when>
			        
			        					 <c:otherwise>
			          							 <td></td>
			        					 </c:otherwise>
			      				</c:choose>
								<td>${purretchase.invNo}</td>
								<td><fmt:parseDate value="${purretchase.invDate}" var="parsedInvDate" pattern="MMM dd, yyyy" /> <fmt:formatDate pattern="yyyy-MM-dd" value="${parsedInvDate}" /></td>
								<td>${purretchase.distributorName}</td>
								<td>${purretchase.grossAmount}</td>
								<td>${purretchase.totalMrp}</td>
								<td>${purretchase.netAmount}</td>
								<td>${purretchase.remarks}</td>
								<td><c:if test="${purretchase.isPosted==1}">
								Posted
								</c:if> <c:if test="${purretchase.isPosted==0}">
								Unposted
								</c:if></td>
								<td><c:if test="${purretchase.isPosted==1}">
										<button class="btn btn-success btn-xs" onclick="window.location.href='${pageContext.request.contextPath}/purret/loadpurreturn.htm?purRetId=ty678ui-90ewsd88-${purretchase.purchaseReturnId}-71dglm35-jkas3try-dfvcbh231'"><spring:message code="cmn.jsp.btn.view" text="View" /></button>
									</c:if> <c:if test="${purretchase.isPosted==0}">
										<c:if test="${menuByUserDTO.isAll==1}">
											<button class="btn btn-theme btn-xs" type="button" onclick="postPurchaseRetInv(${purretchase.purchaseReturnId})">Post</button>
											<button class="btn btn-info btn-xs" type="button" onclick="window.location.href='${pageContext.request.contextPath}/purret/loadpurreturn.htm?purRetId=ty678ui-90ewsd88-${purretchase.purchaseReturnId}-71dglm35-jkas3try-dfvcbh231'">
												<i class="fa fa-pencil"></i>
												<spring:message code="cmn.jsp.btn.edit" text="Edit" />
											</button>
										</c:if>
										<c:if test="${menuByUserDTO.isView==1}">
											<button class="btn btn-theme04 btn-xs" type="button" onclick="deletePurchaseRetInv(${purretchase.purchaseReturnId})">
												<i class="fa fa-trash-o "></i>
												<spring:message code="cmn.jsp.btn.dlt" text="Delete" />
											</button>
										</c:if>
									</c:if></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</section>
<!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/proc/purinvretreg/purinvretreg.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/proc/purinvretreg/purinvretreg_en.js"></script>	
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>
<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}
	$(document).ready(function() {
		var table = $('#purrettable').DataTable({
			"lengthChange" : false,
		/* "searching": false,
		"pageLength": 12 */
		"columnDefs": [
		               { className: "text-right", "targets": [3,4,5] },
		             ]
		});
		var currentDate = new Date();
		var startDateFrom = new Date((currentDate.getFullYear() - 1), 3, 1);
		$('#stdate').datepicker({
			format : 'yyyy-mm-dd',
			startDate : startDateFrom,
			autoclose: true,
		});
		$('#enddate').datepicker({
			format : 'yyyy-mm-dd',
			endDate : currentDate,
			autoclose: true,
		});
		$('.dataTables_filter input').attr("placeholder", getPurInvRetRegText.dataTablePlaceHolder);
		strtDtGrtEndDt();
		
		// Handle click on "Select all" control
	  	   $('#example-select-all').click(function(){
	  	      // Check/uncheck all checkboxes in the table
	  	      var rows = table.rows({ 'search': 'applied' }).nodes();	  	      
	  	      $('input[type="checkbox"]', rows).prop('checked', this.checked);
	  	     /* if ($('#example-select-all').is(":checked")) {
	  	 		  $("#postall_td").removeClass("hide");
	  	 	  }else{
	  	 		 $("#postall_td").addClass("hide");
	  	 	  } */
	  	   });
		
	});
	
</script>