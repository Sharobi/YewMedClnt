<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p>
				<%-- <spring:message code="modcanreturnmemo.jsp.title" text="Modify/Cancel Return Memo..." /> --%>
			</p>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<div class="panel-trx panel-default">
				<div class="panel-body-trx">
					<form modelAttribute="commonResultSetMapper" action="${pageContext.request.contextPath }/retunmemo/searchreturnmemoinvoice.htm" method="post" id="searchForm">
						<table>
							<tr align="center" style="font-weight: bold;">
								<td><spring:message code="purinvreg.jsp.frmdt" text="From Date" /></td>
								<td><spring:message code="purinvreg.jsp.todt" text="To Date" /></td>
								<td colspan="4"><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></td>
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
										<span class="input-group-addon" id="basic-addon3">SRM/${sessionScope.sesloggedinUser.finyrCode}/</span> <input class="form-control-trx" type="text" name="invoiceNo" value="${commonResultSetMapper.invoiceNo}">
									</div>
								</td> --%>
								
								<td width="145px;" style="padding: 0 1px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retmemoDoc" value="SRM/" size="15" ></td>
								<c:if test="${empty commonResultSetMapper.finyrCode}">
									<td width="50px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" name="finyrCode" value="${sessionScope.sesloggedinUser.finyrCode}" size="5"></td>
								</c:if>
								<c:if test="${!empty commonResultSetMapper.finyrCode}">
									<td width="50px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" name="finyrCode" value="${commonResultSetMapper.finyrCode}" size="5"></td>
								</c:if>
								<td style="padding: 0 1px;" width="2.5%"><input class="form-control-trx" type="text" style="padding: 4px 0px;" value="/" readonly></td>
								<td width="202px;"><div class="input-group"><input class="form-control-trx" type="text" name="invoiceNo" value="${commonResultSetMapper.invoiceNo}"></div></td>
								

								<td style="padding: 0 2px;"><button type="button" id="search_btn" class="btn btn-theme">
										<spring:message code="cmn.jsp.search" text="Search" />
									</button></td>
									<td id="postall_td"><button type="button" id="postall_btn" onclick="postAllRetSelData()" class="btn btn-theme">
										Post All
									</button></td>
								<td><div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<table id="saleinvtable" class="table table-bordered table-striped table-condensed table-hover">
				<thead>
					<tr>
					<th><input name="select_all" value="1" id="example-select-all" type="checkbox" style="zoom: 2;"/></th>
						<th><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></th>
						<th><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></th>
						<th><spring:message code="pos.jsp.custname" text="Cust.Name" /></th>
						<th><spring:message code="pos.jsp.dctrName" text="Doc. Name" /></th>
						<%-- <th><spring:message code="reprintcash.jsp.invmode" text="Inv Mode" /></th> --%>
						<th class="numeric"><spring:message code="purinvdet.jsp.total" text="Total" /></th>
						<%-- 						<th class="numeric"><spring:message code="purinvdet.jsp.totmrp" text="Tot.MRP:" /></th> --%>
						<th><spring:message code="reprintcash.jsp.disamt" text="Dis Amt." /></th>
						<th class="numeric"><spring:message code="purinvdet.jsp.nettotal" text="NetTotal:" /></th>
						<th class="numeric"><spring:message code="purinvdet.jsp.adjamt" text="Adj Amt:" /></th>
						<th><spring:message code="purinvdet.jsp.remarks" text="Remarks:" /></th>
						<th><spring:message code="purinvreg.jsp.status" text="Status" /></th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty saleReturnDTOs }">
						<c:forEach items="${saleReturnDTOs}" var="salereturndetail">
							<tr id="${salereturndetail.saleReturnId}">
							   <c:choose>
			        					 <c:when test = "${salereturndetail.isPosted==0}">
			           							 <td><input type="checkbox" name="id[]" id="${salereturndetail.saleReturnId}" value="${salereturndetail.saleReturnId}" style="zoom: 1.5;"  onchange="setChange(this);"></td>
			         					 </c:when>
			        
			        					 <c:otherwise>
			          							 <td></td>
			        					 </c:otherwise>
			      				</c:choose>
								<td>${salereturndetail.invNo}</td>
								<td><fmt:parseDate value="${salereturndetail.invDate}" var="saleInvDate" pattern="MMM dd, yyyy" /> <fmt:formatDate pattern="yyyy-MM-dd" value="${saleInvDate}"/></td>
								<%-- <td>${salereturndetail.invModeName}</td> --%>
								<td>${salereturndetail.customerName}</td>
								<td>${salereturndetail.doctorName}</td>
								<td>${salereturndetail.grossAmount}</td>
								<%-- 								<td>${salereturndetail.totalMrp}</td> --%>
								<td>${salereturndetail.discAmount}</td>
								<td>${salereturndetail.netAmount}</td>
								<td>${salereturndetail.adjAmount}</td>
								<td>${salereturndetail.remarks}</td>
								<td><c:if test="${salereturndetail.isPosted==1}">
								Posted
								</c:if> <c:if test="${salereturndetail.isPosted==0}">
								Unposted
								</c:if></td>
								<td><c:if test="${salereturndetail.isPosted==1}">
										<button class="btn btn-success btn-xs" onclick="window.location.href='${pageContext.request.contextPath}/retunmemo/loadreturnmemo.htm?saleRetId=0954fg-hjk4565wer-${salereturndetail.saleReturnId}-kjsa45-uyt6yu-9811fnczas'"><spring:message code="cmn.jsp.btn.view" text="View" /></button>
									</c:if> <c:if test="${salereturndetail.isPosted==0}">
										<%-- <c:if test="${salereturndetail.holdFlag ==0}"> --%>
											<c:if test="${menuByUserDTO.isAll==1}">
												<button class="btn btn-theme btn-xs" type="button" onclick="postSalesReturnInv(${salereturndetail.saleReturnId})">Post</button>
												<button class="btn btn-info btn-xs" type="button" onclick="window.location.href='${pageContext.request.contextPath}/retunmemo/loadreturnmemo.htm?saleRetId=0954fg-hjk4565wer-${salereturndetail.saleReturnId}-kjsa45-uyt6yu-9811fnczas'">
													<i class="fa fa-pencil"></i>
													<spring:message code="cmn.jsp.btn.edit" text="Edit" />
												</button>
											</c:if>
											<c:if test="${menuByUserDTO.isView==1}">
												<button class="btn btn-theme04 btn-xs" type="button" onclick="deleteSalesRetInv(${salereturndetail.saleReturnId})">
													<i class="fa fa-trash-o "></i>
													<spring:message code="cmn.jsp.btn.dlt" text="Delete" />
												</button>
											</c:if>
										<%-- </c:if>
										<c:if test="${salereturndetail.holdFlag ==1}">
											<c:if test="${menuByUserDTO.isAll==1}">
												<button class="btn btn-theme btn-xs" type="button" onclick="postSalesReturnInv(${salereturndetail.saleId})">Post</button>
											</c:if>
										</c:if> --%>

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
<!-- Confirm Modal Start -->

<div class="modal fade" id="confirmModalPos" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.confrmation" text="Confirmation!"></spring:message>
				</h4>
			</div>
			<div class="modal-body">
				<spring:message code="footer.jsp.cnfrmquestion" text="Are you sure?" />
				<input type="hidden" id="confirmid"> <input type="hidden" id="confirmtype">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" id="cnfrm_cancel_btn">
					<spring:message code="footer.jsp.btn.cancel" text="Cancel" />
				</button>
				<button type="button" onclick="DoConfirmPos()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Confirm Modal end -->

<script src="${pageContext.request.contextPath }/assets/js/pos/returnmemo/returnmemo.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/pos/returnmemo/retcashmemo_en.js"></script>
</c:if>
<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}
	$(document).ready(function() {
		var table =$('#saleinvtable').DataTable({
			"lengthChange" : false,
		/* "searching": false,
		"pageLength": 12 */
		"columnDefs": [
		               { className: "text-right", "targets": [4,5,6,7] },
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
 		$('.dataTables_filter input').attr("placeholder", getRetCashMemoText.dataTablePlaceHolder);
 		strtDtGrtEndDt();
 		
 		 // Handle click on "Select all" control
  	   $('#example-select-all').on('click', function(){
  	      // Check/uncheck all checkboxes in the table
  	      var rows = table.rows({ 'search': 'applied' }).nodes();
  	      $('input[type="checkbox"]', rows).prop('checked', this.checked);
  	    /*  if ($('#example-select-all').is(":checked")) {
  	 		  $("#postall_td").removeClass("hide");
  	 	  }else{
  	 		 $("#postall_td").addClass("hide");
  	 	  } */
  	   });
 		 
	});
	
</script>