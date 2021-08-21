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
				<input type="hidden" id="store_name" value="${sesloggedinStore.name}">
				<input type="hidden" id="retailerProfitPrcnt" value="${retailerProfitPrcnt}" />
				<input type="hidden" id="orderQtyFrmSale" value="${purOrderQtyFromSale}" />
				<input type="hidden" id="saleHistoryDay" value="${saleHistoryDay}" />
				<input type="hidden" id="dayToPurchase" value="${dayToPurchase}" />
				<div class="panel-body-trx">
					<table>
						<tr align="center" style="font-weight: bold;">
							<td><spring:message code="purorder.jsp.orderdt" text="Order Date" /></td>
							<td id="duedate_label"><spring:message code="purorder.jsp.duedt" text="Due Date" /></td>
							<td><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></td>
							<td id="vendor_label"><spring:message code="purinvdet.jsp.vendor" text="Vendor" /></td>
							<td><spring:message code="purinvdet.jsp.discprcnt" text="Disc%" /></td>
							<c:if test="${purchaseOrderHeader.isPosted==0}">
							<td><spring:message code="purorder.jsp.ordertype" text="Order Type" /></td>
							</c:if>
						</tr>
						<tr>
							<fmt:parseDate value="${purchaseOrderHeader.invDate}" var="parsedInvDate" pattern="MMM dd, yyyy" />
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="invdt" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${parsedInvDate}" />" readonly></td>
							<fmt:parseDate value="${purchaseOrderHeader.dueDate}" var="parsedDeuDate" pattern="MMM dd, yyyy" />
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="duedt" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${parsedDeuDate}" />" readonly></td>							
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="invno" value="${purchaseOrderHeader.invNo}" readonly><input type="hidden" id="orderno" value="${purchaseOrderHeader.purchaseOrderId}" readonly></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="distname" value="${purchaseOrderHeader.distributorName}" readonly><input type="hidden" id="seldistributor" value="${purchaseOrderHeader.distributorId}"><input type="hidden" id="distEmail" value="${distEmail}" /></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="distdiscprcnt" value="${purchaseOrderHeader.distributorDiscPer}" readonly></td>
							<c:if test="${purchaseOrderHeader.isPosted==0}">
							<td style="padding: 0 1px;">
								<select class="form-control-trx" id="ordertype" onchange="getOrderByType()">
									<option value="0">Select</option>
									<option value="AUTO">Auto</option>
									<option value="SALE">Sale</option>
								</select>
							</td>
							</c:if>
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
					<table>
						<tr align="center" style="font-weight: bold;">
							<td><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></td>
							<td colspan="2" class="hide"><spring:message code="cmn.jsp.barcode" text="Barcode" /></td>
							<c:if test="${purchaseHeader.isPosted==0}">
							<td>
								<span id="newItemLabel"><spring:message code="cmn.jsp.capitalize.new" text="New" /></span>
								<span id="editItemLabel" class="hide"><spring:message code="cmn.jsp.btn.edit" text="Edit" /></span>
							</td>
							</c:if>
							<td id="batch_label" class="hide"><spring:message code="purinvdet.jsp.batch" text="Batch" /></td>
							<td id="exp_label" class="hide"><spring:message code="purinvdet.jsp.expdt" text="Exp" /></td>
							<td id="pqty_label"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></td>
							<td id="ratio_label"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></td>
							<td><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></td>
							<td id="mrp_label"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></td>
							<td id="rate_label"><spring:message code="purinvdet.jsp.rate" text="Rate" /></td>
							<td id="salerate_label"><spring:message code="purinvdet.jsp.salerate" text="Sale Rate" /></td>
							<td class="hide"><spring:message code="purinvdet.jsp.ma" text="Ma%" /></td>
							
							<td><spring:message code="purinvdet.jsp.total" text="Total" /></td>
						</tr>
						<tr>
							<td width="20%" style="padding: 0 1px;"><input type="hidden" id="itemid" /><input class="form-control-trx" type="text" id="item_name" placeholder="Please type atleast 2 characters"></td>
							<td style="padding: 0 1px;" colspan="2" class="hide"><input class="form-control-trx" type="text" id="purbarcode" placeholder="Scan Barcode"><input type="hidden" id="purHsnCode" /></td>
							<c:if test="${purchaseHeader.isPosted==0}">
							<td style="padding: 0 1px;">
								<button class="btn btn-primary btn-sm" type="button" id="addNewItemBtn" style="padding: 4px 10px;width: 100%;" onclick="addNewItem()">
									<i class="fa fa-plus"></i>
									ITEM
								</button>
								<button class="btn btn-info btn-sm hide" id="editNewItemBtn" type="button" style="padding: 4px 10px;width: 100%;" onclick="addNewItem()">
									<i class="fa fa-pencil"></i> ITEM
								</button>
							</td>
							</c:if>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="batch_no"></td>
							<td style="padding: 0 1px;" class="hide"><div class="input-group">
									<input type="text" class="form-control-trx" id="exp" name="expDate" placeholder="MM/YY" maxlength="5" onchange="expiryCalculation($(this).val());">
								</div></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="pqty"><input type="hidden" id="punitid" /></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="ratio"></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="lqty" tabindex="-1" readonly="readonly"></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="mrp"></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="rate"></td>
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
							
							<c:if test="${purchaseOrderHeader.isPosted==0}">
							    <c:if test="${menuByUserDTO.isAll==1}">
								<td style="padding: 0 1px;">
								<button class="btn btn-success btn-sm" type="button" id="add_btn">
									<spring:message code="cmn.jsp.addcaps" text="ADD" />
								</button>
								<button class="btn btn-success btn-sm hide" style="text-transform: uppercase;" type="button" id="edit_btn">
									<spring:message code="cmn.jsp.btn.update" text="UPDATE" />
								</button></td>
								<td><button class="btn btn-primary btn-sm" id="clear_btn" type="button">
										<spring:message code="cmn.jsp.btn.clear" text="CLEAR" />
									</button>
								</td>
								</c:if>
							</c:if>
							
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
							<th class="hide"><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
							<th class="hide"><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></th>
							<c:if test="${purchaseOrderHeader.isPosted==1}">
							<th class="numeric"><spring:message code="purorder.jsp.recpqty" text="Rec.Pack.Qty" /></th>
							<th class="numeric"><spring:message code="purorder.jsp.reclqty" text="Rec.Loose.Qty" /></th>
							</c:if>
							<th class="numeric"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.rate" text="Rate" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.salerate" text="Sale Rate" /></th>
							<!-- <th class="numeric">Ed%</th> -->
							<th class="numeric hide"><spring:message code="purinvdet.jsp.ed" text="Ed" /></th>
							<!-- <th class="numeric">Tax%</th> -->
							<th class="numeric"><spring:message code="purinvdet.jsp.tax" text="Tax" /></th>
							<!-- <th class="numeric">Vat%</th> -->
							<th class="numeric hide"><spring:message code="purinvdet.jsp.vat" text="Vat" /></th>
							<!-- <th class="numeric">D%</th> -->
							<th class="numeric"><spring:message code="purinvdet.jsp.disc" text="Disc" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.amnt" text="Amt" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.totmrp" text="Tot.MRP" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.netamt" text="Net Amt" /></th>
							<!-- <th class="numeric">Ma%</th> -->
							<c:if test="${purchaseOrderHeader.isPosted==0}">
							<th class="numeric"><spring:message code="purinvdet.jsp.dltbtn" text="Del." /></th>
							</c:if>
						</tr>
					</thead>
					<tbody id="polisttbody">
						<c:if test="${!empty purchaseOrderDetails }">
							<c:forEach items="${purchaseOrderDetails}" var="purchaseOrderDetail" varStatus="purIndex">
								<tr id="tblrow_${purchaseOrderDetail.itemId}" style="cursor: pointer;" onclick="javascript:itemDetailView(${purchaseOrderDetail.itemId});">
									<td id="tbl_item_name">${purchaseOrderDetail.itemName}</td>
									<td id="tbl_batch_no" class="hide"></td>
									<td id="tbl_exp" class="hide"></td>
									<td id="tbl_pqty" class="numeric">${purchaseOrderDetail.packQty}</td>
									<td id="tbl_lqty" class="numeric">${purchaseOrderDetail.looseQty}</td>
									<c:if test="${purchaseOrderHeader.isPosted==1}">
									<td id="tbl_recpqty" class="numeric">${purchaseOrderDetail.recPackQty}</td>
									<td id="tbl_reclqty" class="numeric">${purchaseOrderDetail.recLooseQty}</td>
									</c:if>
									<td id="tbl_ratio" class="numeric">${purchaseOrderDetail.conversion}</td>
									<td id="tbl_mrp" class="numeric">${purchaseOrderDetail.mrp}</td>
									<td id="tbl_rate" class="numeric">${purchaseOrderDetail.rate}</td>
									<td id="tbl_saleRate" class="numeric">${purchaseOrderDetail.saleRate}</td>
									<td id="tbl_ed" class="numeric hide"></td>
									<td id="tbl_tax" class="numeric">${purchaseOrderDetail.taxAmount}</td>
									<td id="tbl_vat" class="numeric hide"></td>
									<td id="tbl_disc" class="numeric">${purchaseOrderDetail.disc}</td>
									<td id="tbl_amnt" class="numeric">${purchaseOrderDetail.grossAmount}</td>
									<td id="tbl_totamnt" class="numeric">${purchaseOrderDetail.itemTotalMrp}</td>
									<td id="tbl_netamt" class="numeric">${purchaseOrderDetail.netAmount}</td>
									<c:if test="${purchaseOrderHeader.isPosted==0}">
									<c:if test="${menuByUserDTO.isView==1}">
									<td><button class="btn btn-theme04 btn-xs" onclick="javascript:showPurItemDelModal(${purchaseOrderDetail.itemId});">
											<i class="fa fa-trash-o "></i>
										</button></td>
									</c:if>
									</c:if>	
									<td id="tbl_ma" class="hide">0.0</td>
									<td id="tbl_grp" class="hide">${purchaseOrderDetail.groupName}</td>
									<td id="tbl_sch" class="hide">${purchaseOrderDetail.scheduleName}</td>
									<td id="tbl_mfg" class="hide">${purchaseOrderDetail.manufacturerName}</td>
									<td id="tbl_edprcnt" class="hide"></td>
									<td id="tbl_taxprcnt" class="hide">${purchaseOrderDetail.taxPercentage}</td>
									<td id="tbl_taxid" class="hide">${purchaseOrderDetail.taxId}</td>
									<td id="tbl_taxmode" class="hide">${purchaseOrderDetail.taxMode}</td>
									<td id="tbl_isgrptax" class="hide">${purchaseOrderDetail.isGroupTax}</td>
									<td id="tbl_vatprcnt" class="hide"></td>
									<td id="tbl_dprcnt" class="hide">${purchaseOrderDetail.discPer}</td>
									<td id="tbl_grpid" class="hide">${purchaseOrderDetail.groupId}</td>
									<td id="tbl_schid" class="hide">${purchaseOrderDetail.scheduleId}</td>
									<td id="tbl_mfgid" class="hide">${purchaseOrderDetail.manufacturerId}</td>
									<td id="tbl_punitid" class="hide">${purchaseOrderDetail.packUnitId}</td>
									<td id="tbl_id" class="hide">${purchaseOrderDetail.id}</td>
									<td id="tbl_itemid" class="hide">${purchaseOrderDetail.itemId}</td>
									<c:choose>
										<c:when test="${purchaseOrderDetail.rate==0}">
											<td id="tbl_isFree" class="hide">Y</td>
										</c:when>
										<c:otherwise>
											<td id="tbl_isFree" class="hide">N</td>
										</c:otherwise>
									</c:choose>
									<td id="tbl_ltAdj" class="hide"></td>
									<td id="tbl_sku" class="hide">${purchaseOrderDetail.sku}</td>
									<td id="tbl_hsn" class="hide">${purchaseOrderDetail.hsnCode}</td>
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
			<table>
				<tr>
					<td class="font-bold"><spring:message code="purinvdet.jsp.itemcount" text="ItemCount:" />:&nbsp;</td>
					<td width="3%" class="font-bold"><span id="itemcount">0</span><input type="hidden" id="prevItemCount" /></td>
					<td class="font-bold"><spring:message code="purinvdet.jsp.total" text="Total" />:</td>
					<td style="padding-right: 5px;"><input class="form-control-trx" style="color: #000000;" type="text" id="totgrosamnt" value="${purchaseOrderHeader.grossAmount}" tabindex="-1" readonly></td>
					<td class="font-bold"><spring:message code="purinvdet.jsp.totmrp" text="Tot.MRP" />:</td>
					<td style="padding-right: 5px;"><input class="form-control-trx" style="color: #000000;" type="text" id="totgrosmrp" value="${purchaseOrderHeader.totalMrp}" tabindex="-1" readonly></td>
					<td class="font-bold hide"><spring:message code="purinvdet.jsp.toted" text="Tot.ED:" /></td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="toted" tabindex="-1" readonly></td>
					<td class="font-bold hide"><spring:message code="purinvdet.jsp.totvat" text="Tot.Vat:" />:</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="totvatamnt" tabindex="-1" readonly></td>
					<td class="font-bold"><spring:message code="purinvdet.jsp.tottax" text="Tot.Tax:" />:</td>
					<td style="padding-right: 5px;"><input class="form-control-trx" type="text" id="tottaxamnt" value="${purchaseOrderHeader.taxAmount}" tabindex="-1" readonly></td>
					<td class="font-bold"><spring:message code="purinvdet.jsp.totdisc" text="Tot.Disc:" />:</td>
					<td style="padding: 2px 5px 0 0;"><input class="form-control-trx" type="text" id="totdiscamnt" value="${purchaseOrderHeader.discAmount}" tabindex="-1" readonly></td>
					<td class="font-bold"><spring:message code="purinvdet.jsp.roff" text="R.Off:" />:</td>
					<td style="padding: 2px 5px 0 0;"><input class="form-control-trx" type="text" id="roundoff" value="${purchaseOrderHeader.roundoff}" tabindex="-1" readonly="readonly"></td>
					<td><span style="font-size: 16px; font-weight: bold; color: #d43f3a;"><spring:message code="purinvdet.jsp.nettotal" text="NetTotal:" />:</span></td>
					<td width="8%"><input class="form-control-trx" style="font-weight: bold; color: #000000; background-color: #ebccd1;" type="text" id="totnetamnt" tabindex="-1" readonly value="${purchaseOrderHeader.netAmount}"></td>
				</tr>				
				<tr>
					<td class="font-bold"><spring:message code="purinvdet.jsp.remarks" text="Remarks:" />:</td>
					<td colspan="3" style="padding: 1px 5px 0 0"><input class="form-control-trx" type="text" id="remarks" value="${purchaseOrderHeader.remarks}"></td>
					
					<c:if test="${purchaseOrderHeader.isPosted==0}">
						
						<td colspan="4" style="padding-top: 1px;">
						   <c:if test="${menuByUserDTO.isAll==1}">
							<button class="btn btn-primary btn-sm" type="button" onclick="postPurchaseOrder()">
								<spring:message code="cmn.jsp.btn.post" text="POST" />
							</button>
						   </c:if>
							<button style="padding: 5px 8px;" id="new_btn" onclick="javascript:toPurchaseOrder();" class="btn btn-info btn-sm" type="button">
								<spring:message code="cmn.jsp.new" text="NEW" />
							</button>
							<c:if test="${menuByUserDTO.isView==1}">
							<button style="padding: 5px 8px;" class="btn btn-danger btn-sm" type="button" onclick="deletePurchaseOrder()">
								<spring:message code="cmn.jsp.tblhdr.del" text="DEL" />
							</button>
							</c:if>
							<c:if test="${menuByUserDTO.isAll==1}">
							<button style="padding: 5px 8px; text-transform: uppercase;" class="btn btn-success btn-sm"  id="save_btn" type="button">
								<spring:message code="cmn.jsp.btn.update" text="UPDATE" />
							</button>
							<c:if test="${sesloggedinStore.isMailEnable==1}">
								<button style="padding: 5px 8px; text-transform: uppercase;" class="btn btn-success btn-sm"  id="mail_btn" type="button" onclick="openMailModal();">
									<spring:message code="cmn.jsp.btn.mail" text="MAIL" />
								</button>
							</c:if>
							</c:if>
						</td>
					</c:if>
					<%-- <c:if test="${purchaseOrderHeader.isPosted==1}">
						<c:if test="${purchaseOrderHeader.status!=3}">
						<td colspan="4" style="padding-top: 1px;">
							<button style="padding: 5px 8px; text-transform: uppercase;" class="btn btn-warning btn-sm" onclick="closeOrder();"  id="close_btn" type="button">
								<spring:message code="cmn.jsp.btn.close" text="CLOSE" />
							</button>
						</td>
						</c:if>
					</c:if> --%>
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
				<input type="hidden" id="confirmId" value=""><input type="hidden" id="confirmType" />
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" id="cnfrm_cancel_btn">
					<spring:message code="footer.jsp.btn.cancel" text="Cancel" />
				</button>
				<button type="button" onclick="DoConfirmPODel()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Confirm Modal end -->

<!-- Mail Purchase Order Modal Starts -->

<div class="modal fade" id="mailPurchaseOrderModal" style="text-align: center;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.mailpurorder" text="Mail Purchase Order" />
				</h4>
			</div>
			<div class="modal-body">
				<div class="form-horizontal">
					<div class="form-group">
               			<label class="col-sm-2 col-sm-2 control-label" id="mailTo_label">To <span class="required_star">*</span></label>
               			<div class="col-sm-10">
                   			<input type="text" id="mailTo" class="form-control">
               			</div>
           			</div>
           			<div class="form-group">
               			<label class="col-sm-2 col-sm-2 control-label" id="mailSub_label">Subject</label>
               			<div class="col-sm-10">
                   			<input type="text" id="mailSub" class="form-control">
               			</div>
           			</div>
           			<div class="form-group">
               			<label class="col-sm-2 col-sm-2 control-label" id="mailBody_label">Body</label>
               			<div class="col-sm-10">               			
                   			<textarea id="mailMsgBody" rows="10" class="form-control">
                   			</textarea>
               			</div>
           			</div>
				</div>
			</div>
			<div class="modal-footer" style=" border-top: 0px;">
				<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
				<button type="button" id="saveTaxBtn" onclick="javascript:sendMail()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.send" text="SEND" /></button>
			</div>
		</div>
	</div>
</div>
<!-- Mail Purchase Order Modal ends -->

<!-- Purchase Order By Type Modal Start -->
<div class="modal fade" id="purOrdrByTypeModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 850px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="purorder.jsp.slctitemfrmordrtype" text="Select item for Purchase Order" /> 
				</h4>
			</div>
			<div class="modal-body">
				<div id="purordernotfounddiv" class="hide">
					<span class="font-bold"><spring:message code="purorder.jsp.ordernotfoundmsg" text="No Purchase Order found for this Order Type" />.</span>
				</div>
				<div id="slctVendorManfDiv">
					<table>
						<tr>
							<td style="padding: 0 1px;">
									<input class="form-control-trx" type="text" value="${purchaseOrderHeader.distributorName}" readonly><input type="hidden" id="orderdistributor" value="${purchaseOrderHeader.distributorId}"><input type="hidden" id="distEmail" value="${distEmail}" />
							</td>
							<td width="54%">
								<input type="hidden" id="manufacturerId" value="0" class="form-control-trx" name="manufacturerId">
								<input type="text" id="itemManufacturer" value="" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.manufacturer" text="Manufacturer" />(Please type atleast 2 characters)">
							</td>
							<td><button class="btn btn-theme btn-sm" onclick="getAutoOrder()">
								<spring:message code="cmn.jsp.search" text="Search" />
							</button></td>
						</tr>
						<tr></tr>
						<tr></tr>
					</table>
				</div>
				<div></div>
				<div style="max-height: 300px; height: 200px; overflow: auto;"
					id="purordrbytypediv"> 
					<table id="purordrbytypetable"
						class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th><input type="checkbox" id="selectAll" name="selectAll" style="zoom: 1.5; vertical-align: middle; margin: 0px;" onchange="selctAllChkBox();"></th>
								<th><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></th>
								<th><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
								<th><spring:message code="purorder.jsp.recqty" text="Rec.Qty" /></th>
								<th><spring:message code="purorder.jsp.orderqty" text="Ord.Qty" /></th>
								<th><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
								<th><spring:message code="purinvdet.jsp.rate" text="Rate" /></th>
								<th><spring:message code="purinvdet.jsp.taxprcnt" text="Tax%" /></th>
								<th><spring:message code="purretrn.jsp.discper" text="Dis%" /></th>
								<th><spring:message code="purorder.jsp.salehistoryday" text="Sale History Day" /></th>
								<th><spring:message code="purorder.jsp.daystopurvhase" text="How many days to purchase" /></th>
								<th><spring:message code="purorder.jsp.calqty" text="Cal.Qty" /></th>
								<th><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
								<th><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
							</tr>
						</thead>
						<tbody id="purordrbytypetbody">

						</tbody>
					</table>					
					<div>
						<span id="alertmessagecont"
							style="font-weight: bold; color: red;"> </span>
					</div>
				</div>
				<div style="max-height: 300px; overflow: auto;" id="purHistDetailsDiv" class="hide">
					Purchase History Of <strong><span id="itemnameofpurHistDetails"></span></strong><a onclick="closePurHisDetForOrdrType()" aria-label="close" class="pull-right" style="cursor: pointer;"><i class="fa fa-times" aria-hidden="true"></i></a>
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
						<tbody id="purHistDetailsTbody"></tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer" id="footerDiv">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" class="btn btn-primary" id="purOrdrByTypeModal_okbtn"
					onclick="getmodcheckedpoitemlist()">
					<spring:message code="footer.jsp.btn.ok" text="Ok" />
				</button>
			</div>
		</div>
	</div>
</div>

<!-- Purchase Order By Type Modal End -->

<!-- Same Vendor Exist Modal Start -->

<div class="modal fade" id="sameVendorModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
				<spring:message code="purorder.jsp.orderdfrntmemomsg" text="You are trying to order different Vendor/Distributor Memo." />
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>

<!-- Same Vendor Exist Modal End -->

<jsp:include page="/pages/common/commonnewitemadd.jsp" />
<script src="${pageContext.request.contextPath }/assets/js/proc/purorder/purorderdet.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/proc/purorder/purorder_en.js"></script>
</c:if>
<script type="text/javascript">
	var BASE_URL = "${pageContext.request.contextPath}";
	/* function addNewItem(){
		$('#itemmasterModal').modal('show');
	} */
	$(document).ready(function() {
		$("#dprcnt").val($("#distdiscprcnt").val());
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