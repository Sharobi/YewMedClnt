<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
<!--
.ui-autocomplete {
	overflow-y: scroll;max-height: 250px;width: 300px; word-break: break-all;
}
-->
</style>
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p></p>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<div class="panel-trx panel-default">
				<%-- <!-- Footer error modal start -->

				<div class="modal fade" id="footerErrorModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
										  <div class="modal-dialog">
										    <div class="modal-content">
										      <div class="modal-header">
										        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										        <h4 class="modal-title" id="myModalLabel"><spring:message code="footer.jsp.alert" text="Alert!"></spring:message></h4>
										      </div>
										      <div class="modal-body">
												<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsgforSave"></div>
											  </div>
										      <div class="modal-footer">
										        <button type="button" onclick="ExistsOk()" data-dismiss="modal" class="btn btn-theme"><spring:message code="footer.jsp.btn.ok" text="OK" /></button>
										      </div>
										    </div>
										  </div>
				</div>      
				<!-- Footer error modal end --> --%>
			
				<div class="panel-body-trx">
				<input type="hidden" id="retailerProfitPrcnt" value="${retailerProfitPrcnt}" />
				<input type="hidden" id="expalertrequiremnt" value="${expalertrequiremnt}" />
				<input type="hidden" id="noofdue" value="${noOfDue }" />
					<table>
						<tr align="center" style="font-weight: bold;">
							<td><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></td>
							<td id="duedate_label"><spring:message code="purorder.jsp.duedt" text="Due Date" /></td>
							<td><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></td>
							<td><spring:message code="purinvdet.jsp.ordrno" text="Ord No" /></td>
							<td id="bill_label"><spring:message code="purinvdet.jsp.billno" text="Bill No" /></td>
							<td width="25%" id="vendor_label"><spring:message code="purinvdet.jsp.vendor" text="Vendor" /></td>
							<td><spring:message code="purinvdet.jsp.discprcnt" text="Disc%" /></td>
							<td width="9%"><spring:message code="purinvdet.jsp.mode" text="Mode" /></td>
						</tr>
						<tr>
							<fmt:parseDate value="${purchaseHeader.invDate}" var="parsedInvDate" pattern="MMM dd, yyyy" />
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="invdt" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${parsedInvDate}" />" readonly></td>
							<fmt:parseDate value="${purchaseHeader.dueDate}" var="parsedDeuDate" pattern="MMM dd, yyyy" />
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="duedt" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${parsedDeuDate}" />" readonly></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="invno" value="${purchaseHeader.invNo}" readonly><input type="hidden" id="orderno" value="${purchaseHeader.id}"></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="purorderinvno" value="${purchaseHeader.purchaseOrderInvNo}" readonly></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="billno" value="${purchaseHeader.billNo}" ${purchaseHeader.isPosted==1 ? 'readonly' : ''}></td>
							<td style="padding: 0 1px;">
								<%-- <input class="form-control-trx" type="text" id="distname" value="${purchaseHeader.distName}" readonly>
								<input type="hidden" id="seldistributor" value="${purchaseHeader.distributorId}"> --%>
								<select class="form-control-trx" name="distributorId" id="seldistributor" onchange="getvendordisval()" ${purchaseHeader.isPosted==1 ? 'disabled' : ''}>
									<option value="0">Select</option>
									<c:if test="${!empty allVendors}">
										<c:forEach items="${allVendors}" var="allVendor">
											<option value="${allVendor.id}_${allVendor.discount}" ${allVendor.id==purchaseHeader.distributorId ? 'selected' : '' }>${allVendor.name}</option>
										</c:forEach>
									</c:if>
									<option value="-1">Add Vendor</option>
								</select>
							</td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="distdiscprcnt" value="${purchaseHeader.distDiscPer}" readonly></td>
							<td style="padding: 0 1px;">
								<select class="form-control-trx" id="slctMode" onchange="chngeMode();">
									<option value="P">Purchase</option>
									<option value="R">Return</option>
								</select>
							</td>
							<td colspan="2" >
								<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<div class="panel-trx panel-default">
				<div class="panel-body-trx" id="header_div">
					<table id="header_pur_tbl">
						<tr align="center" style="font-weight: bold;">
							<td><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></td>
							<td colspan="2"><spring:message code="cmn.jsp.barcode" text="Barcode" /></td>
							<c:if test="${purchaseHeader.isPosted==0}">
							<td>
								<span id="newItemLabel"><spring:message code="cmn.jsp.capitalize.new" text="New" /></span>
								<span id="editItemLabel" class="hide"><spring:message code="cmn.jsp.btn.edit" text="Edit" /></span>
							</td>
							</c:if>
							<td id="batch_label"><spring:message code="purinvdet.jsp.batch" text="Batch" /></td>
							<td id="exp_label"><spring:message code="purinvdet.jsp.expdt" text="Exp" /></td>
							<td id="pqty_label"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></td>
							<td id="ratio_label"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></td>
							<td><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></td>
							<td id="free_label"><spring:message code="purinvdet.jsp.free" text="Free" /></td>
							<td id="mrp_label"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></td>
							<td id="rate_label"><spring:message code="purinvdet.jsp.rate" text="Rate" /></td>
							<td id="salerate_label"><spring:message code="purinvdet.jsp.salerate" text="Sale Rate" /></td>
							<td class="hide"><spring:message code="purinvdet.jsp.ma" text="Ma%" /></td>
							
							<td><spring:message code="purinvdet.jsp.total" text="Total" /></td>
						</tr>
						<tr>
							<td width="20%" style="padding: 0 1px;"><input type="hidden" id="itemid" /><input class="form-control-trx" type="text" id="item_name" placeholder="Please type atleast 2 characters"></td>
							<td style="padding: 0 1px;" colspan="2"><input class="form-control-trx" type="text" id="purbarcode" placeholder="Scan Barcode"><input type="hidden" id="purHsnCode" /></td>
							<c:if test="${purchaseHeader.isPosted==0}">
							<td style="padding: 0 1px;">
								<button class="btn btn-primary btn-sm" type="button" id="addNewItemBtn" style="padding: 4px 10px;" onclick="addNewItem()">
									<i class="fa fa-plus"></i>
									ITEM
								</button>
								<button class="btn btn-info btn-sm hide" id="editNewItemBtn" type="button" style="padding: 4px 10px;" onclick="addNewItem()">
									<i class="fa fa-pencil"></i> ITEM
								</button>
							</td>
							</c:if>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="batch_no"><input type="hidden" id="purorderid" value="0" /></td>
							<td style="padding: 0 1px;"><div class="input-group">
									<input type="text" class="form-control-trx" id="exp" name="expDate" placeholder="MM/YY" maxlength="5" onchange="expiryCalculation($(this).val());">
								</div></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="pqty"><input type="hidden" id="punitid" /></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="ratio"></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="lqty" tabindex="-1" readonly="readonly"></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="free"></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="mrp"></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="rate"><input type="hidden" id="rateNonFree" /></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="sale_rate"></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="ma"></td>
							
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="total" tabindex="-1" readonly></td>
						</tr>
						<tr align="center" style="font-weight: bold;">
							<td><spring:message code="purinvdet.jsp.mfg" text="Mfg" /></td>
							<td><spring:message code="itemmstr.jsp.group" text="Grp" /></td>
							<td><spring:message code="itemmstr.jsp.schedule" text="Sch" /></td>
							<td class="hide"><spring:message code="purinvdet.jsp.edprcnt" text="Ed%" /></td>
							<td class="hide"><spring:message code="purinvdet.jsp.ed" text="Ed" /></td>
							<td ><spring:message code="purinvdet.jsp.taxprcnt" text="Tax%" /></td>
							<td><spring:message code="purinvdet.jsp.tax" text="Tax" /></td>
							<td class="hide">TAX%</td>
							<td class="hide">TAX</td>
							<td><spring:message code="purinvdet.jsp.discprcnt" text="D%" /></td>
							<td><spring:message code="purinvdet.jsp.disc" text="Disc" /></td>
							<td colspan="3"><spring:message code="purinvdet.jsp.checkfreeitem" text="Check if free item"/></td>
						</tr>
						<tr>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="mfg" tabindex="-1" readonly><input type="hidden" id="mfgid" /></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="grp" tabindex="-1" readonly><input type="hidden" id="grpid" /></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="sch" tabindex="-1" readonly><input type="hidden" id="schid" /></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="edpercnt"></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="ed"></td>
							
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="taxprcnt" value="${sessionScope.sesloggedinUser.taxPer}" tabindex="-1" readonly><input type="hidden" id="purTaxId" /></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="tax" tabindex="-1" readonly><input type="hidden" id="purtaxmode" /><input type="hidden" id="purisgrptax" /></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="vatprcnt" value="${sessionScope.sesloggedinUser.vatPer}"></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="vat" tabindex="-1" readonly></td>
							
							<%-- <c:if test="${sessionScope.sesloggedinUser.isTaxRegNo==0}">
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="taxprcnt" value="${sessionScope.sesloggedinUser.taxPer}"></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="tax" tabindex="-1" readonly></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="vatprcnt" tabindex="-1" readonly></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="vat" tabindex="-1" readonly></td>
							</c:if>
							<c:if test="${sessionScope.sesloggedinUser.isTaxRegNo==1}">
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="taxprcnt" tabindex="-1" readonly></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="tax" tabindex="-1" readonly></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="vatprcnt" value="${sessionScope.sesloggedinUser.vatPer}"></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="vat" tabindex="-1" readonly></td>
							</c:if> --%>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="dprcnt" value="0"></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="disc" tabindex="-1" readonly></td>
							<td class="hide"><input type="hidden" id="id" /></td>
							<td class="hide"><input type="hidden" id="tblrow_id" /></td>
							<td colspan="3" style="padding: 0 1px;text-align: center;">
								<input type="checkbox" id="freeCheck" name="freeCheck" style="zoom: 1.5">
							</td>
							<c:if test="${purchaseHeader.isPosted==0}">
							    <c:if test="${menuByUserDTO.isAll==1}">
								<td style="padding: 0 1px;">
								<button class="btn btn-success btn-sm" type="button" id="add_btn">
									<spring:message code="cmn.jsp.addcaps" text="ADD" />
								</button>
								<button class="btn btn-success btn-sm hide" style="text-transform: uppercase;" type="button" id="edit_btn">
									<spring:message code="cmn.jsp.btn.update" text="UPDATE" />
								</button></td>
								<td><button class="btn btn-primary btn-sm" id="clear_btn" type="button" onclick="clearHeaderDiv();">
										<spring:message code="cmn.jsp.btn.clear" text="CLEAR" />
									</button>
								</td>
								</c:if>
							</c:if>
							
						</tr>
					</table>
					
					<table id="header_ret_tbl" class="hide">
						<tr align="center" style="font-weight: bold;">
								<td><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></td>
								<td><spring:message code="cmn.jsp.barcode" text="Barcode" /></td>
								<td id="batch_label"><spring:message code="purinvdet.jsp.batch" text="Batch" /></td>
								<td id="exp_label"><spring:message code="purinvdet.jsp.expdt" text="Exp" /></td>
								<td id="pqty_ret_label"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></td>
								<td><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></td>
								<td><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></td>
								<td><spring:message code="purinvdet.jsp.free" text="Free" /></td>
								<td><spring:message code="purinvdet.jsp.mrp" text="MRP" /></td>
								<td id="rate_ret_label"><spring:message code="purinvdet.jsp.rate" text="Rate" /></td>
								<td class="hide"><spring:message code="purinvdet.jsp.ma" text="Ma%" /></td>
								
								<td><spring:message code="purinvdet.jsp.total" text="Total" /></td>
							</tr>
							<tr>
								<td width="20%" style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_name_ret" placeholder="Please type atleast 2 characters"> <input type="hidden" id="itemid_ret" value="0"></td>
								<td width="12%"><input class="form-control-trx" type="text" id="barcode_ret" placeholder="Scan Barcode"><input type="hidden" id="purHsnCode_ret" /><input type="hidden" id="purInvId_ret" /><input type="hidden" id="purInvNo_ret" /></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="batch_no_ret" tabindex="-1" readonly></td>
								<td style="padding: 0 1px;"><div class="input-group">
										<input type="text" class="form-control-trx" id="exp_ret" name="expDate" placeholder="MM/YY" maxlength="5" tabindex="-1" readonly>
									</div></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="pqty_ret"><input type="hidden" id="punitid_ret" /></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="lqty_ret" tabindex="-1" readonly></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="ratio_ret" tabindex="-1" readonly></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="free_ret" tabindex="-1" readonly><input type="hidden" id="prevfree_ret"></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="mrp_ret" tabindex="-1" readonly></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="rate_ret" ></td>
								<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="ma" value="0" tabindex="-1" readonly></td>
								
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="total_ret" tabindex="-1" readonly></td>
							</tr>
							<tr align="center" style="font-weight: bold;">
								<td><spring:message code="purinvdet.jsp.mfg" text="Mfg" /></td>
								<td><spring:message code="itemmstr.jsp.group" text="Grp" /></td>
								<td><spring:message code="itemmstr.jsp.schedule" text="Sch" /></td>
								<td class="hide"><spring:message code="purinvdet.jsp.edprcnt" text="Ed%" /></td>
								<td class="hide"><spring:message code="purinvdet.jsp.ed" text="Ed" /></td>
								<td><spring:message code="purinvdet.jsp.taxprcnt" text="Tax%" /></td>
								<td><spring:message code="purinvdet.jsp.tax" text="Tax" /></td>
								<td class="hide">TAX%</td>
								<td class="hide">TAX</td>
								<td><spring:message code="purinvdet.jsp.discprcnt" text="D%" /></td>
								<td><spring:message code="purinvdet.jsp.disc" text="Disc" /></td>
								<td><spring:message code="purretrn.jsp.bpqty" text="B.P.Qty" /></td>
								<td><spring:message code="" text="Return Reason" /></td>
							</tr>
							<tr>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="mfg_ret" tabindex="-1" readonly><input type="hidden" id="mfgid_ret" /></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="grp_ret" tabindex="-1" readonly><input type="hidden" id="grpid_ret" /></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="sch_ret" tabindex="-1" readonly><input type="hidden" id="schid_ret" /></td>
								<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="edpercnt" tabindex="-1" readonly></td>
								<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="ed" tabindex="-1" readonly> </td>
								<%-- <c:if test="${sessionScope.sesloggedinUser.isTaxRegNo==0}">
								<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="taxprcnt" value="${sessionScope.sesloggedinUser.taxPer}" tabindex="-1" readonly></td>
								<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="tax" tabindex="-1" readonly></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="vatprcnt" tabindex="-1" readonly></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="vat" tabindex="-1" readonly></td>
								</c:if> 
								<c:if test="${sessionScope.sesloggedinUser.isTaxRegNo==1}">--%>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="taxprcnt_ret" tabindex="-1" readonly><input type="hidden" id="purTaxId_ret" /></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="tax_ret" tabindex="-1" readonly><input type="hidden" id="purtaxmode_ret" /><input type="hidden" id="purisgrptax_ret" /></td>
								<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="vatprcnt" value="${sessionScope.sesloggedinUser.vatPer}"></td>
								<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="vat" tabindex="-1" readonly></td>
								
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="dprcnt_ret" value="0" tabindex="-1" readonly></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="disc_ret" tabindex="-1" readonly></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="billpqty_ret" tabindex="-1" value="0" readonly></td>
								<td class="hide"><input type="hidden" id="tblrow_id_ret" /></td>
								<td style="padding: 0 1px;">
									<select class="form-control-trx" id="slctRetReason">
					          			<c:if test="${!empty allReturnReasonTypes}">
											<c:forEach items="${allReturnReasonTypes}" var="allReturnReasonType">
													<option value="${allReturnReasonType.id}">${allReturnReasonType.typeName}</option>
											</c:forEach>
										</c:if>
									</select>
								</td>
								
								<td>
									<button class="btn btn-success btn-sm" type="button" id="add_btn_ret" onclick="addForRet();">
										<spring:message code="cmn.jsp.return" text="Return" />
									</button>
									<button class="btn btn-success btn-sm hide" style="text-transform: uppercase;" type="button" id="edit_btn_ret" onclick="editForRet();">
										<spring:message code="cmn.jsp.btn.update" text="UPDATE" />
									</button>
								</td>
								<td><button class="btn btn-primary btn-sm" type="button" onclick="clearHeaderDiv();">
										<spring:message code="cmn.jsp.btn.clear" text="CLEAR" />
									</button></td>
							</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<div id="purHistoryDiv" class="custom-alert custom-alert-info alert-dismissable hide">
				Purchase History Of <strong><span id="itemnameofpurhis"></span></strong><a onclick="closePurHisDet()" aria-label="close" class="pull-right" style="cursor: pointer;"><i class="fa fa-times" aria-hidden="true"></i></a>
				<table id="" style="margin-bottom: 5px;" class="table table-bordered table-striped table-condensed-trx table-hover">
					<thead>
						<tr>
							<td>Inv.No</td>
							<td>Inv.Date</td>
							<td>Vendor</td>
							<td>Batch</td>
							<td>Exp.Date</td>
							<td>Pur.Qty</td>
							<td>Free.Qty</td>
							<td>UOM</td>
							<td>MRP</td>
							<td>Rate</td>
							<td>Disc(%)</td>
							<td>Gross Amt</td>
						</tr>
					</thead>
					<tbody id="itmpurhistboby"></tbody>
				</table>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<div style="max-height: 270px; height: 270px; overflow: auto;">
				<table id="peitem" style="margin-bottom: 5px;" class="table table-bordered table-striped table-condensed-trx table-hover">
					<thead>
						<tr>
							<th><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></th>
							<th><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
							<th><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.free" text="Free" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.rate" text="Rate" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.salerate" text="Sale Rate" /></th>
							<!-- <th class="numeric">Ed%</th> -->
							<th class="numeric hide"><spring:message code="purinvdet.jsp.ed" text="Ed" /></th>
							<!-- <th class="numeric">Tax%</th> -->							
							<th class="numeric"><spring:message code="purinvdet.jsp.taxprcnt" text="Tax%" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.tax" text="Tax" /></th>
							<!-- <th class="numeric">Vat%</th> -->
							<th class="numeric hide"><spring:message code="purinvdet.jsp.vat" text="Vat" /></th>
							<!-- <th class="numeric">D%</th> -->						
							<th class="numeric"><spring:message code="purinvdet.jsp.discprcnt" text="Disc%" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.disc" text="Disc" /></th>	
							<th class="numeric"><spring:message code="purinvdet.jsp.amnt" text="Amt" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.totmrp" text="Tot.MRP" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.netamt" text="Net Amt" /></th>
							<!-- <th class="numeric">Ma%</th> -->
							<c:if test="${purchaseHeader.isPosted==0}">
							<th class="numeric"><spring:message code="purinvdet.jsp.dltbtn" text="Del." /></th>
							</c:if>
							<th class="numeric"><spring:message code="cmn.jsp.btn.print" text="Print" /></th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty purchaseDetails }">
							<c:forEach items="${purchaseDetails}" var="purchaseDetail" varStatus="purIndex">
								<tr id="tblrow_${purchaseDetail.itemId}_${purchaseDetail.batchNo}" style="cursor: pointer;" onclick="javascript:itemDetailView(${purchaseDetail.itemId},'${purchaseDetail.batchNo}');">
									<td id="tbl_item_name">${purchaseDetail.itemName}</td>
									<td id="tbl_batch_no">${purchaseDetail.batchNo}</td>
									<td id="tbl_exp">${purchaseDetail.expiryDateFormat}</td>
									<td id="tbl_pqty" class="numeric">${purchaseDetail.packQty}</td>
									<td id="tbl_lqty" class="numeric">${purchaseDetail.looseQty}</td>
									<td id="tbl_ratio" class="numeric">${purchaseDetail.conversion}</td>
									<td id="tbl_free" class="numeric">${purchaseDetail.freeQty}</td>
									<td id="tbl_mrp" class="numeric">${purchaseDetail.mrp}</td>
									<td id="tbl_rate" class="numeric">${purchaseDetail.rate}</td>
									<td id="tbl_saleRate" class="numeric">${purchaseDetail.saleRate}</td>
									<td id="tbl_ed" class="numeric hide">${purchaseDetail.ed}</td>
									<td id="tbl_taxprcnt">${purchaseDetail.taxPercentage}</td>
									<td id="tbl_tax" class="numeric">${purchaseDetail.taxAmount}</td>
									<td id="tbl_vat" class="numeric hide">${purchaseDetail.vat}</td>
									<td id="tbl_dprcnt">${purchaseDetail.discPer}</td>
									<td id="tbl_disc" class="numeric">${purchaseDetail.disc}</td>
									<td id="tbl_amnt" class="numeric">${purchaseDetail.amount}</td>
									<td id="tbl_totamnt" class="numeric">${purchaseDetail.totAmount}</td>
									<td id="tbl_netamt" class="numeric">${purchaseDetail.purchaseNetAmount}</td>
									<c:if test="${purchaseHeader.isPosted==0}">
									<c:if test="${menuByUserDTO.isView==1}">
									<td><button class="btn btn-theme04 btn-xs" onclick="javascript:showPurItemDelModal('${purchaseDetail.itemId}_${purchaseDetail.batchNo}');">
											<i class="fa fa-trash-o "></i>
										</button></td>
									</c:if>
									</c:if>
									<%-- <c:if test="${sessionScope.sesloggedinUser.companyId != 18}"> --%>
									<td><button class="btn btn-success btn-xs" id="printBySkuBtn" onclick="javascript:printByBarcode('${purchaseDetail.itemId}','${purchaseDetail.itemName}','${purchaseDetail.sku}',${purchaseDetail.mrp},${purchaseDetail.saleRate},'${purchaseDetail.batchNo}','${purchaseDetail.expiryDateFormat}',${purchaseDetail.packQty+purchaseDetail.freeQty},'${sessionScope.sesloggedinStore.name}','${purchaseDetail.grpName}');">
										<spring:message code="cmn.jsp.btn.print" text="Print" />
									</button></td>
									<%-- </c:if> --%>
									<td id="tbl_ma" class="hide">0.0</td>
									<td id="tbl_grp" class="hide">${purchaseDetail.grpName}</td>
									<td id="tbl_sch" class="hide">${purchaseDetail.schdName}</td>
									<td id="tbl_mfg" class="hide">${purchaseDetail.manuName}</td>
									<td id="tbl_edprcnt" class="hide">${purchaseDetail.edPer}</td>
									<td id="tbl_taxid" class="hide">${purchaseDetail.taxId}</td>
									<td id="tbl_taxmode" class="hide">${purchaseDetail.taxMode}</td>
									<td id="tbl_isgrptax" class="hide">${purchaseDetail.isGroupTax}</td>
									<td id="tbl_vatprcnt" class="hide">${purchaseDetail.vatPer}</td>
									<td id="tbl_grpid" class="hide">${purchaseDetail.grpId}</td>
									<td id="tbl_schid" class="hide">${purchaseDetail.schdId}</td>
									<td id="tbl_mfgid" class="hide">${purchaseDetail.manuId}</td>
									<td id="tbl_punitid" class="hide">${purchaseDetail.packUnitId}</td>
									<td id="tbl_id" class="hide">${purchaseDetail.id}</td>
									<td id="tbl_itemid" class="hide">${purchaseDetail.itemId}</td>
									<c:choose>
										<c:when test="${purchaseDetail.rate==0}">
											<td id="tbl_isFree" class="hide">Y</td>
										</c:when>
										<c:otherwise>
											<td id="tbl_isFree" class="hide">N</td>
										</c:otherwise>
									</c:choose>
									<td id="tbl_ltAdj" class="hide">${purchaseDetail.itemLotAdjAmount}</td>
									<td id="tbl_sku" class="hide">${purchaseDetail.sku}</td>
									<td id="tbl_hsn" class="hide">${purchaseDetail.hsnCode}</td>
									<td id="tbl_poid" class="hide">${purchaseDetail.purchaseOrderId}</td>
									<td id="tbl_predprbfrspdp" class="hide">${purchaseDetail.discPer}</td>
									<td id="purrettabpqtyhide" class="hide">0</td>
									<td id="tbl_mode" class="hide">P</td>
									<td id="tbl_ret_reason" class="hide"></td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<p></p>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			 <input type="hidden" id="creditor_code1" value="<spring:message code="accgroup.jsp.creditor_code" text="SCR" />">
											  <input type="hidden" id="purchase_code1" value="<spring:message code="accgroup.jsp.purchase_code1" text="PA" />">
											 <input type="hidden" id="dutiesandtax_code1" value="<spring:message code="accgroup.jsp.duties_code" text="DT" />">
											 <input type="hidden" id="dicount_code1" value="<spring:message code="accgroup.jsp.disc_code" text="DIS" />">
											 <input type="hidden" id="roundof_code1" value="<spring:message code="accgroup.jsp.roun_code" text="ROD" />">
											   
											  <input type="hidden" id="lot_adjasment_code" value="<spring:message code="accgroup.jsp.lotadjs_group_code" text="LOT" />">
											<%--   <input type="hidden" id="oth_adjasment_code" value="<spring:message code="accgroup.jsp.othadjs_group_code" text="LOT" />"> --%>
											   <input type="hidden" id="cash_code" value="<spring:message code="accgroup.jsp.cash_code" text="CIH" />">
											  
		
			<input type="hidden" id="temp_net_total_amt_backup" value="0">
			<input type="hidden" id="temp_supplier_amt_backup" value="0">
			<input type="hidden"  id="purchase_ledger_id1" value="0">
			<input type="hidden"  id="duties_ledger_id1" value="0">
			<input type="hidden"  id="round_ledger_id1" value="0">
			<input type="hidden"  id="discount_ledger_id1" value="0">
			<input type="hidden"  id="lotadj_ledger_id1" value="0">
			<input type="hidden"  id="creditor_ledger_id1" value="0">
				
			<table > 
			
				 
				<tr>
 
					<!-- total -->
					<td class="font-bold"><spring:message code="purinvdet.jsp.total" text="Total" />:</td>
					<td width="10%"><input class="form-control-trx" style="color: #000000;" type="text" id="totgrosamnt" value="${purchaseHeader.grossAmount}" tabindex="-1" readonly><input type="hidden" id="totgrosamnt_ret"></td>
					
					<%-- 
				 --%>
					<td class="font-bold hide"><spring:message code="purinvdet.jsp.toted" text="Tot.ED:" /></td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="toted" value="${purchaseHeader.edAmount}" tabindex="-1" readonly></td>
					<td class="font-bold hide"><spring:message code="purinvdet.jsp.totvat" text="Tot.Vat:" />:</td>
					<!-- for tax -->
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="totvatamnt" value="${purchaseHeader.vatAmount}" tabindex="-1" readonly></td>
					<td class="font-bold"><spring:message code="purinvdet.jsp.tottax" text="Tot.Tax:" />:</td>
					<td width="9%"><input class="form-control-trx" type="text" id="tottaxamnt" value="${purchaseHeader.taxAmount}" tabindex="-1" readonly><input type="hidden" id="tottaxamnt_ret"></td>
					<!-- roundoff -->
					<td class="font-bold"><spring:message code="purinvdet.jsp.roff" text="R.Off:" />:</td>
					<td width="6%"><input class="form-control-trx" type="text" id="roundoff" value="${purchaseHeader.roundoff}" tabindex="-1" readonly="readonly"><input type="hidden" id="roundoff_ret"></td>
					
					
					<td class="font-bold"><spring:message code="purinvdet.jsp.totdisc" text="Tot.Disc:" />:</td>
					<td width="7%"><input class="form-control-trx" type="text" id="totdiscamnt" value="${purchaseHeader.discAmount}" tabindex="-1" readonly><input type="hidden" id="totdiscamnt_ret"></td>
					
					<td class="font-bold"  width="7%"><spring:message code="purinvdet.jsp.totmrp" text="Tot.MRP" />:</td>
					<td style="padding-right: 5px;" width="11%"><input class="form-control-trx" style="color: #000000;" type="text" id="totgrosmrp" value="${purchaseHeader.totalMrp}" tabindex="-1" readonly><input type="hidden" id="totgrosmrp_ret"></td>
					 
					<!-- net total -->
					<td colspan="2" width="9%">
					
					<center><span style="font-size: 16px; font-weight: bold; color: #d43f3a;"><spring:message code="purinvdet.jsp.nettotal" text="NetTotal:" />:</span></center>
					<input class="form-control-trx" style="font-weight: bold; color: #000000; " type="hidden" value="0.0" id="creditor_amt" tabindex="-1" readonly></td>
					<td width="10%" colspan="2"><input class="form-control-trx" style="font-weight: bold; color: #000000; background-color: #ebccd1;" type="text" id="totnetamnt" tabindex="-1" readonly value="${purchaseHeader.netAmount}"></td>
					
				
				</tr>
				<tr>
				<td class="font-bold"><spring:message code="purinvdet.jsp.itemcount" text="ItemCount:" />:&nbsp;</td>
					<td width="3%" class="font-bold"><span id="itemcount">0</span><input type="hidden" id="prevItemCount" /></td> 
					<td colspan="2" width="7%"  class="font-bold"><spring:message code="purinvdet.jsp.cashpurchase" text="CashPurchase:" />: <c:choose>
							<c:when test="${purchaseHeader.invMode==1}">
								<input type="checkbox" id="invmode" value="${purchaseHeader.invMode}" checked="checked"  onclick="call_cash_ledger();" >
							</c:when>
							<c:otherwise>
								<input type="checkbox" id="invmode" value="${purchaseHeader.invMode}"  onclick="call_cash_ledger();">
							</c:otherwise>
						</c:choose>
					</td>
				
					<td class="font-bold"><spring:message code="purinvdet.jsp.spcldisc" text="Spl.Disc%:" />:</td>
					<td style="padding: 2px 5px 0 0;"><input class="form-control-trx" type="text" id="spldisc" onkeydown="numcheck(event)" value="${purchaseHeader.specDiscPer}"><input type="hidden" id="spldiscamt" value="${purchaseHeader.specDiscAmount}"></td>
					<!-- <td style="padding: 3px 5px 0 0;"><input class="form-control-trx" type="text" readonly></td> -->
				
					<%-- <td class="font-bold"><spring:message code="purinvdet.jsp.totdisc" text="Tot.Disc:" />:</td>
					<td style="padding: 2px 5px 0 0;"><input class="form-control-trx" type="text" id="totdiscamnt" value="${purchaseHeader.discAmount}" tabindex="-1" readonly></td> --%>
					
						<td class="font-bold" width="4%"><spring:message code="purinvdet.jsp.ltadj" text="Lt.Adj" />:</td>
					<td style="padding: 2px 5px 0 0"><input class="form-control-trx" type="text" id="totltadj" value="${purchaseHeader.lotAdjAmount}" tabindex="-1" readonly="readonly"><input type="hidden" id="totltadj_ret" /></td>
					
					
					
					<td class="font-bold" id="suppbillamt_label"><spring:message code="purinvdet.jsp.suppBillAmt" text="Supp.Bill.Amt" />:</td>
					<td style="padding: 2px 5px 0 0"><input class="form-control-trx" type="text" id="suppbillamt"  onkeydown="numcheck(event)" value="${purchaseHeader.distributorBillAmount}"></td>
					<td class="font-bold"><spring:message code="purinvdet.jsp.othadj" text="Oth.Adj.Amt" />:</td>
					<td style="padding: 2px 5px 0 0"><input class="form-control-trx" type="text" id="othadjamt" value="${purchaseHeader.otherAdjAmount}" tabindex="-1" readonly="readonly"></td>
					<td class="font-bold"><spring:message code="purinvdet.jsp.billamnt" text="Bill.Amt." />:</td>
					<td style="padding: 2px 5px 0 0"><input class="form-control-trx" type="text" id="billamt" value="${purchaseHeader.netAmount-(purchaseHeader.expiryAdjAmount+purchaseHeader.adjAmount)}" tabindex="-1" readonly="readonly"></td>
					
				
				</tr>
				<tr>
					<td class="font-bold"><spring:message code="purinvdet.jsp.remarks" text="Remarks:" />:</td>
					<td style="padding: 1px 5px 0 0"><input class="form-control-trx" type="text" id="remarks" value="${purchaseHeader.remarks}"></td>
					
				
					<td class="font-bold"><spring:message code="purinvdet.jsp.expadj" text="Exp.Adj" />:</td>
					<td style="padding: 1px 5px 0 0"><input class="form-control-trx" type="text" id="expadj" value="${purchaseHeader.expiryAdjAmount}" tabindex="-1" readonly="readonly"></td>
					
					<c:if test="${purchaseHeader.isPosted==0}">
						<td style="padding-top: 1px; padding-right: 4px;"><button class="btn btn-primary btn-sm" type="button" onclick="openexpadjmod()" title="<spring:message code="purinv.jsp.retrnexptitle" text="Return Expiry" />"><i class="fa fa-reply"></i> <spring:message code="purinv.jsp.retbtn" text="RET" /></button></td>
					</c:if>
					<%-- <c:if test="${sessionScope.sesloggedinUser.companyId != 18}"> --%>
						<td>
							<button style="padding: 5px 8px;" id="new_btn" onclick="javascript:printByBarcodeAll('${sessionScope.sesloggedinStore.name}');" class="btn btn-info btn-sm" type="button">
								Print all
							</button>
						</td>
						<%-- </c:if> --%>
				 
					<td class="font-bold"><spring:message code="purinvdet.jsp.retadj" text="Ret.Adj" />:</td>
					<td style="padding: 1px 5px 0 0"><input class="form-control-trx" type="text" id="retadj" value="${purchaseHeader.adjAmount}" tabindex="-1" readonly="readonly"><input type="hidden" id="prevrtrnadjamnt" value="${purchaseHeader.adjAmount}"></td>
					
					<c:if test="${purchaseHeader.isPosted==0}">
						<td style="padding-top: 1px; padding-right: 4px;"><button class="btn btn-primary btn-sm" type="button" id="retAdj_btn" onclick="openretadjmod()" title="<spring:message code="pos.jsp.retrnpur" text="Return Purchase" />"><i class="fa fa-reply"></i> <spring:message code="purinv.jsp.retbtn" text="RET" /></button></td>
						</c:if>
				
						<td class="font-bold" id="cr_noteLabel"><spring:message code="purinvdet.jsp.crnote" text="Adv.Amt." />:
						<input class="form-control-trx" type="text" style="width:45%;display: inline-block;" id="crnote"  onkeydown="numcheck(event)" value="${purchaseHeader.advAmount}">
						</td>
					<td style="padding: 2px 5px 0 0"></td>
					
					
					<c:if test="${purchaseHeader.isPosted==0}">
					
						
						<td colspan="4" style="padding-top: 1px;">
							<c:if test="${menuByUserDTO.isAll==1}">
							<button class="btn btn-primary btn-sm" type="button" onclick="postPurchaseInv()">
								<spring:message code="cmn.jsp.btn.post" text="POST" />
							</button>
							</c:if>
							<button style="padding: 5px 8px;" id="new_btn" onclick="javascript:toCashMemo();" class="btn btn-info btn-sm" type="button">
								<spring:message code="cmn.jsp.new" text="NEW" />
							</button>
							<c:if test="${menuByUserDTO.isView==1}">
							<button style="padding: 5px 8px;" class="btn btn-danger btn-sm" type="button" onclick="deletePurchaseInv()">
								<spring:message code="cmn.jsp.tblhdr.del" text="DEL" />
							</button>
							</c:if>
							<c:if test="${menuByUserDTO.isAll==1}">
							<button style="padding: 5px 8px; text-transform: uppercase;" class="btn btn-success btn-sm"  id="save_btn" type="button">
								<spring:message code="cmn.jsp.btn.update" text="UPDATE" />
							</button>
							</c:if>
						</td>
					</c:if>
					<c:if test="${purchaseHeader.isPosted!=0}">
						<td colspan="4" style="padding-top: 1px;">
							<button style="padding: 5px 8px;" id="new_btn" onclick="javascript:toCashMemo();" class="btn btn-info btn-sm" type="button">
								<spring:message code="cmn.jsp.new" text="NEW" />
							</button>
						</td>
					</c:if>
				</tr>
			</table>
		</div>
	</div>
</section>
<!-- Item exists start -->

				<div class="modal fade" id="itemExistsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
										  <div class="modal-dialog">
										    <div class="modal-content">
										      <div class="modal-header">
										       <!--  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
										        <h4 class="modal-title" id="myModalLabel"><spring:message code="footer.jsp.alert" text="Alert!"></spring:message></h4>
										      </div>
										      <div class="modal-body">
										        <spring:message code="itemmstr.jsp.exist.error" text="Item already exist, please try other." />
							</div>
										      <div class="modal-footer">
										        <button type="button" onclick="ExistsOk()" data-dismiss="modal" class="btn btn-theme"><spring:message code="footer.jsp.btn.ok" text="OK" /></button>
										      </div>
										    </div>
										  </div>
				</div>      
				<!-- Item exists end -->
<!--/wrapper -->

<!-- Return Adj Modal Start -->
<div class="modal fade" id="retAdjModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 666px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="pos.jsp.retrnmemotoadjst" text="Return Purchase to Adjust for Bill Amt" />. <span id="billamtheaderAdj"></span>
				</h4>
			</div>
			<div class="modal-body">

				<table>
					<tr>
						<td><spring:message code="pos.jsp.memono" text="Memo.No" />:</td>
						<td style="padding: 0 1px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retadjmemoDoc" value="PRM/" size="15" ></td>
						<td><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retadjmemoFinyr" value="${sessionScope.sesloggedinUser.finyrCode}" size="5"></td>
						<td style="padding: 0 1px;" width="2.5%"><input class="form-control-trx" type="text" style="padding: 4px 0px;" id="retadjmemoSlash" value="/" readonly></td>
						<td><input class="form-control-trx" type="text" id="retadjmemono" value="" size="10"></td>
						<td style="padding: 0 1px;"><spring:message code="purinvdet.jsp.vendor" text="Vendor" />:</td>
						<td style="padding: 0 1px;" id="adjVendor"></td>
						<td><input type="hidden" id="vendorIdforAdj" /></td>
						<td><button class="btn btn-theme btn-sm" onclick="getMemoDetForAdj()">
								<spring:message code="cmn.jsp.search" text="Search" />
							</button></td>
					</tr>
				</table>
				<div>
					<p>
						<span id="nocashmemofound" style="font-weight: bold; color: red;"></span>
					</p>
				</div>
				<div style="max-height: 300px; overflow: auto;" id="showretadjDetails">
					<table id="" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th><spring:message code="purinvdet.jsp.invno" text="Invoice No" />.</th>
								<th><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></th>
								<th><spring:message code="pos.jsp.netamnt" text="Net Amt" /></th>
								<th><spring:message code="pos.jsp.preadjamt" text="Pre Adj Amt" /></th>
								<th><spring:message code="pos.jsp.adjamt" text="Adj Amt" /></th>
								<th><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
							</tr>
						</thead>
						<tbody id="showretadjtbody">

						</tbody>
					</table>
				</div>
				<div>
					<p><spring:message code="pos.jsp.addedmemos" text="Added Memos" /></p>
					<span id="greaterbillamt" style="font-weight: bold; color: red;"></span>
				</div>
				<div style="max-height: 300px; overflow: auto;" id="retadjDetails">
					<table id="retadjtable" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th><spring:message code="purinvdet.jsp.invno" text="Invoice No" />.</th>
								<th><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></th>
								<th><spring:message code="pos.jsp.netamnt" text="Net Amt" /></th>
								<th><spring:message code="pos.jsp.preadjamt" text="Pre Adj Amt" /></th>
								<th><spring:message code="pos.jsp.adjamt" text="Adj Amt" /></th>
								<th><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
							</tr>
						</thead>
						<tbody id="retadjtbody">

						</tbody>
						<tfoot>
							<tr>
								<td></td>
								<td><span class="font-bold"><spring:message code="purinvdet.jsp.total" text="Total" /></span></td>
								<td><span class="font-bold" id="totretnetamt">0.00</span></td>
								<td><span class="font-bold" id="totretpreadjamt">0.00</span></td>
								<td><span class="font-bold" id="totretadjamt">0.00</span></td>
								<td></td>
							</tr>
						</tfoot>

					</table>
				</div>
				<div>
					<p><spring:message code="pos.jsp.prevrtnadjdetails" text="Previous Return Adjustment Details" /></p>
				</div>
				<div style="max-height: 300px; overflow: auto;" id="prevretadjDetails">
					<table id="prevretadjtable" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th><spring:message code="purinvdet.jsp.invno" text="Invoice No" />.</th>
								<th><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></th>
								<th><spring:message code="pos.jsp.netamnt" text="Net Amt" /></th>
								<th><spring:message code="pos.jsp.preadjamt" text="Pre Adj Amt" /></th>
								<th><spring:message code="pos.jsp.adjamt" text="Adj Amt" /></th>
							</tr>
						</thead>
						<tbody id="prevretadjtbody">

						</tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer">

				<button type="button" class="btn btn-default" onclick="closeRetAdjMod()" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="okRetAdjMod()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Return Adj Modal End -->

<!-- Expiry Adj Modal Start -->
<div class="modal fade" id="expAdjModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 666px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="purinv.jsp.retrnexptoadjst" text="Return Expiry to Adjust for Bill Amt" />
					. <span id="billamtheaderExpAdj"></span>
				</h4>
			</div>
			<div class="modal-body">

				<table>
					<tr>
						<td><spring:message code="pos.jsp.memono" text="Memo.No" />:</td>
						<td style="padding: 0 1px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retadjexpDoc" value="EXP/" size="15" ></td>
						<td><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retadjexpFinyr" value="${sessionScope.sesloggedinUser.finyrCode}" size="5"></td>
						<td style="padding: 0 1px;" width="2.5%"><input class="form-control-trx" type="text" style="padding: 4px 0px;" id="retadjexpSlash" value="/" readonly></td>
						<td><input class="form-control-trx" type="text" id="retadjexpno" value="" size="10"></td>
						<td style="padding: 0 1px;"><spring:message code="purinvdet.jsp.vendor" text="Vendor" />:</td>
						<td style="padding: 0 1px;" id="expAdjVendor"></td>
						<td><input type="hidden" id="expVendorIdforAdj" /></td>
						<td><button class="btn btn-theme btn-sm" onclick="getExpDetForAdj()">
								<spring:message code="cmn.jsp.search" text="Search" />
							</button></td>
					</tr>
					<tr>
						<td><input type="radio" name="rateOrmrp" value="rate" checked onchange="adjOnMrpRate();"><spring:message code="purinvdet.jsp.rate" text="Rate" /></td>
						<td><input type="radio" name="rateOrmrp" value="mrp" onchange="adjOnMrpRate();"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></td>
					</tr>
				</table>
				<div>
					<p>
						<span id="noexpfound" style="font-weight: bold; color: red;"></span>
					</p>
				</div>
				<div style="max-height: 300px; overflow: auto;" id="showretadjDetails">
					<table id="showretadjDetailsTbl" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th><spring:message code="purinvdet.jsp.invno" text="Invoice No" />.</th>
								<th><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></th>
								<th><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></th>
								<th><spring:message code="reprintcash.jsp.totqty" text="Tot.Qty" /></th>
								<th><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
								<th><spring:message code="purinvdet.jsp.rate" text="Rate" /></th>
								<th><spring:message code="pos.jsp.adjamt" text="Adj Amt" /></th>
								<th><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
							</tr>
						</thead>
						<tbody id="showretadjexptbody">

						</tbody>
					</table>
				</div>
				<div>
					<p>
						<spring:message code="pos.jsp.addedmemos" text="Added Memos" />
					</p>
					<span id="expgreaterbillamt" style="font-weight: bold; color: red;"></span>
				</div>
				<div style="max-height: 300px; overflow: auto;" id="retadjDetails">
					<table id="retexpadjtable" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th><spring:message code="purinvdet.jsp.invno" text="Invoice No" />.</th>
								<th><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></th>
								<th><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></th>
								<th><spring:message code="reprintcash.jsp.totqty" text="Tot.Qty" /></th>
								<th><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
								<th><spring:message code="purinvdet.jsp.rate" text="Rate" /></th>
								<th><spring:message code="pos.jsp.adjamt" text="Adj Amt" /></th>
								<th><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
							</tr>
						</thead>
						<tbody id="retadjexptbody">

						</tbody>
						<tfoot>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td><span class="font-bold"><spring:message code="purinvdet.jsp.total" text="Total" /></span></td>
								<td><span class="font-bold" id="totretexpadjamt">0.00</span></td>
								<td></td>
							</tr>
						</tfoot>

					</table>
				</div>
			</div>
			<div class="modal-footer">

				<button type="button" class="btn btn-default" onclick="closeRetExpAdjMod()" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="okRetExpAdjMod()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Expiry Adj Modal End -->

<!-- Confirm Modal Start -->

<div class="modal fade" id="purDelConfirmModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
				<input type="hidden" id="confirmId" value="">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" id="cnfrm_cancel_btn">
					<spring:message code="footer.jsp.btn.cancel" text="Cancel" />
				</button>
				<button type="button" onclick="DoConfirmPurDel()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Confirm Modal end -->

<!-- Confirm Print Purchase Modal Starts -->

<div class="modal fade" id="confirmPrintPurchaseModal" style="text-align: center;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!" />
				</h4>
			</div>
			<div class="modal-body">
				<div id="confirprintmmessagecont">
					<span id="purAddEditMsg"><%-- <spring:message code="purinv.jsp.addsucmsg" text="Purchase Invoice successfully added!" /> --%></span><br>
					<%-- <label class="col-sm-4 col-sm-4 control-label"><spring:message code="purinv.jsp.printpur" text="Print Purchase invoice" /> : </label> --%>
					<div class="col-sm-12" style=" text-align: center;font-weight: 700;">
						<spring:message code="purinv.jsp.printpur" text="Print Purchase invoice" /> :
						<input type="checkbox" id="printPurchase" name="printPurchase" style="zoom: 1.5;vertical-align: middle;margin: 0px;">
					</div>
				</div>
				<input type="hidden" id="confirmval" value="">
			</div>
			<div class="modal-footer" style=" border-top: 0px;">
				<button type="button" onclick="targetURL()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Confirm Print Purchase Modal ends -->
<!-- Ret On Pur Bill Modal Starts -->

<div class="modal fade" id="itempurdetailModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 766px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="purretrn.jsp.purdetfor" text="Purchase Details for" />
					<span id="itempurdetailitemname"> Telma AM</span>
				</h4>
			</div>
			<div class="modal-body">
				<div id="itempurdetnotfounddiv" class="hide">
					<span class="font-bold"><span id=""><spring:message code="purretrn.jsp.purmemonotfoundforitemmsg" text="No Purchase memo found for this item or select the vendor" />.</span>
				</div>
				<div style="max-height: 300px; height: 200px; overflow: auto;"
					id="itempurdetmodtable">
					<table id=""
						class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>

								<th><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></th>
								<th><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></th>
								<th><spring:message code="purinvdet.jsp.vendor" text="Vendor" /></th>
								<th><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
								<th><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
								<th><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
								<th><spring:message code="purinvdet.jsp.free" text="Free" /></th>
								<th><spring:message code="purinvdet.jsp.stockqty" text="Stock Qty" /></th>
								<th><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
								<th class="numeric"><spring:message code="purinvdet.jsp.rate" text="Rate" /></th>
								<th class="hide"><spring:message code="purinvdet.jsp.ed" text="Ed" /></th>
								<th><spring:message code="purinvdet.jsp.tax" text="Tax" /></th>
								<th class="hide"><spring:message code="purinvdet.jsp.vat" text="Vat" /></th>
								<th><spring:message code="purinvdet.jsp.disc" text="Disc" /></th>
								<th class="numeric"><spring:message code="pos.jsp.netamnt" text="Net Amt" /></th>
							</tr>
						</thead>
						<tbody id="itemsearchmodtbody">

						</tbody>


					</table>
				</div>
			</div>
			<div class="modal-footer">

				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
			</div>
		</div>
	</div>
</div>
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
				<button type="button" id="vendorCloseBtn" class="btn btn-default" data-dismiss="modal">
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

<!-- Ret On Pur Bill Modal Ends -->
<jsp:include page="/pages/common/commonnewitemadd.jsp" />
<script src="${pageContext.request.contextPath }/assets/js/proc/purinvoice/purinvdet.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/proc/purinvoice/purinv_en.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/proc/vendor_en.js"></script>
</c:if>
<script type="text/javascript">
	var BASE_URL = "${pageContext.request.contextPath}";
	var storeId = "${sessionScope.sesloggedinUser.storeId}";
	var cmpnyId = "${sessionScope.sesloggedinUser.companyId}";
	var createdBy = "${sessionScope.sesloggedinUser.id}";
	var finyrId = "${sessionScope.sesloggedinUser.finyrId}";
	var initBillNo = "${purchaseHeader.billNo}";
	
	/* function addNewItem(){
		$('#itemmasterModal').modal('show');
	} */
	$(document).ready(function() {
		
		getvendorledger_pur($('#dutiesandtax_code1').val(),0,0,0); // for duties and tax
		getvendorledger_pur($('#roundof_code1').val(),0,0,1); // for round off  
		getvendorledger_pur($('#purchase_code1').val(),0,0,2); // for purchase account
	
		getvendorledger_pur($('#dicount_code1').val(),0,0,4);// for discount account
		getvendorledger_pur($('#lot_adjasment_code').val(),0,0,5);// for lot adjasment 
		
		var header_mode=${purchaseHeader.invMode};
		 
		if (header_mode==1) {
			getvendorledger_pur($('#cash_code').val(),0, 0,3);// for cash creditor
			
			$("#crnote").prop('disabled', true);
		}
		else
			{
			getvendorledger_pur($('#creditor_code1').val(),0,$('#seldistributor').val(),3);//  creditor
			$("#crnote").prop('disabled', false);
			}
	
		
		var tem_adv_amt=${purchaseHeader.advAmount};
		var tem_sup_amt=${purchaseHeader.distributorBillAmount};
		var tem_oth_ajd=${purchaseHeader.otherAdjAmount};
		var tem_net_total=${purchaseHeader.netAmount};
		
		$('#temp_net_total_amt_backup').val(parseFloat(tem_net_total).toFixed(2));
		$('#temp_supplier_amt_backup').val(parseFloat(tem_sup_amt).toFixed(2));
		if (tem_sup_amt==0) {// when supply not  present 
			
			$('#creditor_amt').val((parseFloat(tem_net_total)-parseFloat(tem_adv_amt)).toFixed(2));
			
		}else// supplyer presnt
			{
					$('#creditor_amt').val((parseFloat(tem_sup_amt)-parseFloat(tem_adv_amt)).toFixed(2));
			
			}
		
	
		
		if($("#duedt").val()=="")
		{
			var invdt = new Date($("#invdt").val());
			invdt.setDate(invdt.getDate() + parseInt($("#noofdue").val()));
			var yyyy = invdt.getFullYear().toString();
			var mm = (invdt.getMonth() + 1).toString(); // getMonth() is zero-based
			var dd = invdt.getDate().toString();
			$("#duedt").val(yyyy + "-" + (mm[1] ? mm : "0" + mm[0]) + "-" + (dd[1] ? dd : "0" + dd[0]));
		}	
		$("#dprcnt").val($("#distdiscprcnt").val());
		if($("#spldisc").val()!=0.0)
		{
			$("#spldisc").attr("disabled",true);
		}
		
		$("input:text").focus(function() { $(this).select(); } );
        $("#exp").keyup(function(){
            if ($(this).val().length == 2){
            	if($(this).val()<=12 && $(this).val()>0){
            		$(this).val($(this).val() + "/");
            	}else{
            		$(this).val("");
            	}
                
            }
        });
	});
</script>