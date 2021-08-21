<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
<!--
.ui-autocomplete {
	overflow-y: scroll; max-height: 250px; width: 300px; word-break: break-all;
}

.blink {
	color: black !important;
	/*     background: white; */
	!
	important;
}
-->
</style>
<c:set var="today" value="<%=new java.util.Date()%>" />
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p></p>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<input type="hidden" id="retailerProfitPrcnt" value="${retailerProfitPrcnt}" /> <input type="hidden" id="isexclusive" value="${sesloggedinStore.isExclusive}" />
			<div class="panel-trx panel-default">
				<div class="panel-body-trx">
					<form modelAttribute="commonResultSetMapper" action="${pageContext.request.contextPath }/stock/stkadjupdatesearch.htm" method="post" id="">
						<table>
							<tr align="center" style="font-weight: bold;">
								<td>Item Name</td>
								<td>Barcode</td>
								<td>HSN</td>
								<td width="10%">Exp Date</td>
								<td width="10%">Batch No</td>
								<td><spring:message code="purinvreg.jsp.frmdt" text="From Date" /></td>
								<td><spring:message code="purinvreg.jsp.todt" text="To Date" /></td>
								<td width="15%" id="vendor_label"><spring:message code="purinvdet.jsp.vendor" text="Vendor" /></td>
							</tr>
							<tr>
								<td style="padding: 0 1px;"><input type="hidden" id="itemid" name="itemId" value="${commonResultSetMapper.itemId}" /><input class="form-control-trx" type="text" name="itemName" id="item_name" placeholder="Please type atleast 2 characters" value="${commonResultSetMapper.itemName}"></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" size="12" id="item_barcode" name="barCode" placeholder="barcode" value="${commonResultSetMapper.barCode}"></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" size="12" id="item_hsnCode" name="hsnCode" placeholder="hsncode" value="${commonResultSetMapper.hsnCode}"></td>
								<td style="padding: 0 1px;">
									<div class="input-group">
										<input type="text" id="invDt" class="form-control-trx exp" name="expiryDateFormat" placeholder="MM / YY" value="${commonResultSetMapper.expiryDateFormat}">
									</div>
								</td>

								<td style="padding: 0 1px;">
									<div class="input-group">
										<input type="text" id="bat" class="form-control-trx" name="batchNo" placeholder="Batch" value="${commonResultSetMapper.batchNo}">
									</div>
								</td>

								<td style="padding: 0 1px;">
									<div class="input-group">
										<input type="text" readonly="readonly" class="form-control-trx" id="stdate" name="startDate" value="${commonResultSetMapper.startDate}">
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-th"></span>
										</div>
									</div>
								</td>
								<td style="padding: 0 1px;">
									<div class="input-group">
										<input type="text" readonly="readonly" class="form-control-trx" id="enddate" name="endDate" value="${commonResultSetMapper.endDate}">
										<div class="input-group-addon">
											<span class="glyphicon glyphicon-th"></span>
										</div>
									</div>
								</td>
								<td style="padding: 0 1px;"><select class="form-control-trx" name="distributorId" id="searchdistributor">
										<option value="0">All</option>
										<c:if test="${!empty allVendors}">
											<c:forEach items="${allVendors}" var="allVendor">
<%-- 												<option value="${allVendor.id}">${allVendor.name}</option> --%>
												<c:if test="${allVendor.id==commonResultSetMapper.distributorId}">
														<option value="${allVendor.id}" selected="selected">${allVendor.name}</option>
													</c:if>
													<c:if test="${allVendor.id!=commonResultSetMapper.distributorId}">
														<option value="${allVendor.id}">${allVendor.name}</option>
													</c:if>
											</c:forEach>
											
										</c:if>
								</select></td>
								<td style="padding: 0 2px;"><button type="submit" id="search_btn" class="btn btn-theme">Search</button></td>
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
			<div style="max-height: 440px; height: 440px; overflow: auto;">
				<table id="expitem" class="table table-bordered table-striped table-condensed-trx table-hover">
					<thead>
						<tr>
							<th style="text-align: center;"><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></th>
							<th id="batch_label" style="text-align: center;"><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
							<th id="exp_label" style="text-align: center;"><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
							<th id="pqty_label" style="text-align: center;"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
							<th id="lqty_label" style="text-align: center;"><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></th>
							<th id="ratio_label" style="text-align: center;"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></th>
							<th id="mrp_label" style="text-align: center;"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
							<th id="rate_label" style="text-align: center;"><spring:message code="purinvdet.jsp.rate" text="Rate" /></th>
							<th id="salerate_label" style="text-align: center;"><spring:message code="purinvdet.jsp.salerate" text="Sale Rate" /></th>
							<th style="text-align: center;"><spring:message code="purinvdet.jsp.vendor" text="Vendor" /></th>
							<th style="width: 12%; text-align: center;">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty stockDetailsAdjustmentDTOs }">
							<c:forEach items="${stockDetailsAdjustmentDTOs}" var="stockDetailsAdjustmentDTO">
							<c:if test="${stockDetailsAdjustmentDTO.isSale==0}">
								<tr id="${stockDetailsAdjustmentDTO.id}">
									<td id="tbl_item_name"><input type="hidden" readonly="readonly" id="hiditmid_${stockDetailsAdjustmentDTO.id}" value="${stockDetailsAdjustmentDTO.itemId}"><input class="form-control-trx" type="text" readonly="readonly" value="${stockDetailsAdjustmentDTO.itemName}"></td>
									<td id="tbl_batch_no"><input type="hidden" id="hidbat_${stockDetailsAdjustmentDTO.id}" value="${stockDetailsAdjustmentDTO.batchNo}"><input class="form-control-trx" size="8" type="text" id="bat_${stockDetailsAdjustmentDTO.id}" value="${stockDetailsAdjustmentDTO.batchNo}" onkeyup="changebat(${stockDetailsAdjustmentDTO.id})"></td>
									<td id="tbl_exp"><input type="hidden" id="hidexp_${stockDetailsAdjustmentDTO.id}" value="${stockDetailsAdjustmentDTO.expiryDateFormat}"><input class="form-control-trx exp" size="5" type="text" id="exp_${stockDetailsAdjustmentDTO.id}" value="${stockDetailsAdjustmentDTO.expiryDateFormat}" onkeyup="changeexp(${stockDetailsAdjustmentDTO.id})"></td>
									<td id="tbl_punit_name"><input type="hidden" id="hidpqty_${stockDetailsAdjustmentDTO.id}" value="${stockDetailsAdjustmentDTO.packQty}"><input class="form-control-trx" size="3" onkeydown="numcheck(event)" type="text" id="pqty_${stockDetailsAdjustmentDTO.id}" value="${stockDetailsAdjustmentDTO.packQty}" onkeyup="changepqty(${stockDetailsAdjustmentDTO.id})"></td>
									<c:choose>
									 <c:when test="${stockDetailsAdjustmentDTO.conversion==1}">
									 <td id="tbl_punit_id"><input type="hidden" id="hidlqty_${stockDetailsAdjustmentDTO.id}" value="${stockDetailsAdjustmentDTO.looseQty}"><input class="form-control-trx" readonly="readonly" size="3" type="text" id="lqty_${stockDetailsAdjustmentDTO.id}" value="${stockDetailsAdjustmentDTO.looseQty}" onkeyup="changelqty(${stockDetailsAdjustmentDTO.id})"></td>
									 </c:when>
									 <c:otherwise>
									 <td id="tbl_punit_id"><input type="hidden" id="hidlqty_${stockDetailsAdjustmentDTO.id}" value="${stockDetailsAdjustmentDTO.looseQty}"><input class="form-control-trx" size="3" type="text" id="lqty_${stockDetailsAdjustmentDTO.id}" value="${stockDetailsAdjustmentDTO.looseQty}" onkeyup="changelqty(${stockDetailsAdjustmentDTO.id})"></td>
									 </c:otherwise>
									</c:choose>
									
									<td id="tbl_rack_name"><input type="hidden" id="hidcon_${stockDetailsAdjustmentDTO.id}" value="${stockDetailsAdjustmentDTO.conversion}"><input class="form-control-trx" size="2" type="text" id="con_${stockDetailsAdjustmentDTO.id}" value="${stockDetailsAdjustmentDTO.conversion}" onkeyup="changeconv(${stockDetailsAdjustmentDTO.id})"></td>
									<td id="tbl_rack_id"><input type="hidden" id="hidtax_${stockDetailsAdjustmentDTO.id}" value="${stockDetailsAdjustmentDTO.taxPercentage}"><input type="hidden"  id="hidmrp_${stockDetailsAdjustmentDTO.id}" value="${stockDetailsAdjustmentDTO.mrp}"><input class="form-control-trx"  onkeydown="numcheck(event)"  size="5" type="text" id="mrp_${stockDetailsAdjustmentDTO.id}" value="${stockDetailsAdjustmentDTO.mrp}" onkeyup="calculateSaleRate(${stockDetailsAdjustmentDTO.id})"></td>
									<td id="tbl_stock_qty"><input type="hidden" id="hidrate_${stockDetailsAdjustmentDTO.id}" value="${stockDetailsAdjustmentDTO.rate}"><input class="form-control-trx" size="5" type="text" id="rate_${stockDetailsAdjustmentDTO.id}" value="${stockDetailsAdjustmentDTO.rate}" onkeydown="numcheck(event)"  onkeyup="checkRate(${stockDetailsAdjustmentDTO.id})"></td>
									<td id="tbl_mrp"><input type="hidden" id="hidsalerate_${stockDetailsAdjustmentDTO.id}" value="${stockDetailsAdjustmentDTO.saleRate}"><input class="form-control-trx" type="text" size="5" id="salerate_${stockDetailsAdjustmentDTO.id}" value="${stockDetailsAdjustmentDTO.saleRate}" onkeydown="numcheck(event)"  onkeyup="checkSaleRate(${stockDetailsAdjustmentDTO.id})"></td>
									<td id="tbl_item_id"><input type="hidden" id="hiddist_${stockDetailsAdjustmentDTO.id}" value="${stockDetailsAdjustmentDTO.distributorId}"><select name="VendorName" id="tbl_vendorName_${stockDetailsAdjustmentDTO.id}" class="form-control-trx selectedVendor" onchange="chVen(${stockDetailsAdjustmentDTO.id})">
											<c:if test="${!empty allVendors}">
												<c:forEach items="${allVendors}" var="allVendor">
													<c:if test="${allVendor.id==stockDetailsAdjustmentDTO.distributorId}">
														<option value="${allVendor.id}" selected="selected">${allVendor.name}</option>
													</c:if>
													<c:if test="${allVendor.id!=stockDetailsAdjustmentDTO.distributorId}">
														<option value="${allVendor.id}">${allVendor.name}</option>
													</c:if>
												</c:forEach>
											</c:if>
									</select></td>
									<td><c:if test="${stockDetailsAdjustmentDTO.isSale==0}">
											<c:if test="${menuByUserDTO.isAll==1}">
												<button class="btn btn-info btn-xs " type="button" id="edbt_${stockDetailsAdjustmentDTO.id}" onclick="editOpStk(${stockDetailsAdjustmentDTO.id});">
													<i class="fa fa-pencil"></i>&nbsp;
													<spring:message code="cmn.jsp.btn.edit" text="Edit" />
												</button>
											</c:if>
											<c:if test="${menuByUserDTO.isView==1}">
												<button class="btn btn-theme04 btn-xs" type="button" onclick="deleteOpStk(${stockDetailsAdjustmentDTO.id});">
													<i class="fa fa-trash-o "></i>&nbsp;
													<spring:message code="cmn.jsp.btn.dlt" text="Delete" />
												</button>
											</c:if>
										</c:if>
										<c:if test="${stockDetailsAdjustmentDTO.isSale==1}">Already in use</c:if>
										</td>
								</tr>
								</c:if>
							</c:forEach>
						</c:if>

					</tbody>
				</table>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<p></p>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12"></div>
	</div>
</section>

<!-- Add vendor modal start -->
<div class="modal fade" id="vendorAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
							<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="vendor.jsp.name" text="Name" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="name" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="addrs_label"><spring:message code="vendor.jsp.addrs" text="Address" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<textarea id="addrs" class="form-control" rows="2"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="pin_label"><spring:message code="vendor.jsp.pin" text="Pin" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="pin" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.city" text="City" /></label>
							<div class="col-sm-8">
								<input type="text" id="city" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.state" text="State" /></label>
							<div class="col-sm-8">
								<input type="text" id="state" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.cntry" text="Country" /></label>
							<div class="col-sm-8">
								<input type="text" id="cntry" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="phn1_label"><spring:message code="vendor.jsp.phn1" text="Contact No. 1" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="phn1" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="discount_label"><spring:message code="vendor.jsp.discount" text="Discount(%)" /></label>
							<div class="col-sm-8">
								<input type="text" id="discount" class="form-control" />
							</div>
						</div>
					</div>


					<div class="col-sm-6 col-sm-6 ">
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.phn2" text="Contact No. 2" /></label>
							<div class="col-sm-8">
								<input type="text" id="phn2" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.fax" text="FAX" /></label>
							<div class="col-sm-8">
								<input type="text" id="fax" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.email" text="Email" /></label>
							<div class="col-sm-8">
								<input type="text" id="email" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.cntctPerson" text="Contact Person" /></label>
							<div class="col-sm-8">
								<input type="text" id="cntct_person" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="rgstratn_label"><spring:message code="vendor.jsp.rgstratn" text="Registration No." /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="rgstratn" class="form-control" rows="4" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.opbalance" text="Opening Balance" /></label>
							<div class="col-sm-8">
								<input type="text" id="opbalance" class="form-control" rows="4" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="vendor.jsp.credtlimit" text="Credit Limit" /></label>
							<div class="col-sm-8">
								<input type="text" id="credt_limit" class="form-control" rows="4" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="licenceNo_label"><spring:message code="vendor.jsp.licenceno" text="Licence No" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="licenceNo" class="form-control" />
							</div>
						</div>
					</div>
				</div>
				<input type="hidden" id="vendor_id" value=""></input>
				<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="vendorAddAlertMsg"></div>
			</div>
			<div class="modal-footer" style="border-top: 0px solid #e5e5e5;">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="javascript:addEditVendor()" class="btn btn-theme">
					<spring:message code="cmn.jsp.btn.save" text="SAVE" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Add vendor modal end -->


<!-- Item exists start -->

<div class="modal fade" id="itemExistsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body">
				<spring:message code="itemmstr.jsp.exist.error" text="Item already exist, please try other." />
			</div>
			<div class="modal-footer">
				<button type="button" onclick="ExistsOk()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Item exists end -->
<div class="modal fade" id="expModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
				<span id="inputbarcode"></span>
			</div>
			<div class="modal-footer">

				<button type="button" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="confirmModalStk" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
				<input type="hidden" id="confirmIdStk">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" id="cnfrm_cancel_btn">
					<spring:message code="footer.jsp.btn.cancel" text="Cancel" />
				</button>
				<button type="button" onclick="ConfirmDel()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/inventory/stock/opstkadj.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/proc/vendor_en.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/inventory/stock/stock_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>
<script type="text/javascript">


function numcheck(e) {
    // Allow: backspace, delete, tab, escape, enter and .
    if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
        // Allow: Ctrl+A, Command+A
        (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) ||
        // Allow: home, end, left, right, down, up
        (e.keyCode >= 35 && e.keyCode <= 40)) {
        // let it happen, don't do anything
        return;
    }
    // Ensure that it is a number and stop the keypress
    if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
        e.preventDefault();
    }
}
	var BASE_URL = "${pageContext.request.contextPath}";
	function showConfirmModal() {
		$('#confirmMessageModal').modal('show');
	}
	$(document).ready(function() {
		//call the blink function on the element you want to blink
	    blink(".myDiv", -1, 500);  //blink the element (infinite times)//blink a div with the ID of myDiv
		$("#exp").keyup(function() {
			if ($(this).val().length == 2) {
				if ($(this).val() <= 12 && $(this).val() > 0) {
					$(this).val($(this).val() + "/");
				} else {
					$(this).val("");
				}

			}
		});

		var currentDate = new Date();
		var startDateFrom = new Date((currentDate.getFullYear() - 1), 3, 1);
		$('#stdate').datepicker({
			format : 'yyyy-mm-dd',
			startDate : startDateFrom,
			autoclose : true,
		});
		$('#enddate').datepicker({
			format : 'yyyy-mm-dd',
			//endDate : currentDate,
			autoclose : true,
		});
		strtDtGrtEndDt();
		$("#item_name").autocomplete({
			source : function(	request,
								response) {
				if (request.term.length >= 2) {
					$.ajax({
						url : BASE_URL + "/item/autocompleteitem.htm",
						type : "GET",
						data : {
							tagName : request.term
						},
						dataType : "json",
						success : function(data) {
							response($.map(data, function(v) {
								return {
									label : v.itemName,
									itemCode : v.itemId,
									//tagCode : v.tagCode
									items : v,
								};

							}));
						},
						error : function(error) {
							alert('error: ' + error);
						}
					});
				}
			},
			select : function(	e,
								ui) {
				//		console.log(ui.item.itemCode);
				//		console.log(ui.item.label);
				console.log(ui.item.items);
				$("#itemid").val(ui.item.itemCode);

				// call new  ajax for item details
				//getItemDetails(ui.item.itemCode);
				// call new ajax end

			},
			change : function(	e,
								ui) {
				if (!(ui.item))
					e.target.value = "";
			},
		});
	});
	/**
	 * Purpose: blink a page element
	 * Preconditions: the element you want to apply the blink to, the number of times to blink the element (or -1 for infinite times), the speed of the blink
	 **/
	function blink(elem, times, speed) {
	    if (times > 0 || times < 0) {
	        if ($(elem).hasClass("blink")) 
	            $(elem).removeClass("blink");
	        else
	            $(elem).addClass("blink");
	    }

	    clearTimeout(function () {
	        blink(elem, times, speed);
	    });

	    if (times > 0 || times < 0) {
	        setTimeout(function () {
	            blink(elem, times, speed);
	        }, speed);
	        times -= .5;
	    }
	}
	$(".exp").keyup(function() {
		if ($(this).val().length == 2) {
			if ($(this).val() <= 12 && $(this).val() > 0) {
				$(this).val($(this).val() + "/");
			} else {
				$(this).val("");
			}

		}
	});
</script>
