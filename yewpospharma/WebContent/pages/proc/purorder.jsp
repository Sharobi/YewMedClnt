<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style>
<!--
.ui-autocomplete {
	overflow-y: scroll; max-height: 250px; width: 300px; word-break: break-all; z-index: 2150000000 !important;
}
-->
</style>
<c:set var="today" value="<%=new java.util.Date()%>" />

<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p></p>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12" id="header_filter1">
			<div class="panel-trx panel-default">


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
									<span id="purAddEditMsg">
										<%-- <spring:message code="purinv.jsp.addsucmsg" text="Purchase Invoice successfully added!" /> --%>
									</span> <br>
									<%-- <label class="col-sm-4 col-sm-4 control-label"><spring:message code="purinv.jsp.printpur" text="Print Purchase invoice" /> : </label> --%>
									<div class="col-sm-12" style="text-align: center; font-weight: 700;">
										<label class="checkbox-inline"> <spring:message code="purinv.jsp.printpur" text="Print Purchase invoice" /> : <input type="checkbox" id="printPurchase" name="printPurchase" style="zoom: 1.5; vertical-align: middle; margin: 0px;">
										</label>

									</div>
								</div>
								<input type="hidden" id="confirmval" value="">
							</div>
							<div class="modal-footer" style="border-top: 0px;">
								<button type="button" onclick="targetURL()" data-dismiss="modal" class="btn btn-theme">
									<spring:message code="footer.jsp.btn.ok" text="OK" />
								</button>
							</div>
						</div>
					</div>
				</div>
				<!-- Confirm Print Purchase Modal ends -->


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
								<spring:message code="itemmstr.jsp.nameexist.error" text="Item Name already exist, please try other." />
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

				<!-- Footer error modal start -->

				<div class="modal fade" id="footerErrorModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
								<h4 class="modal-title" id="myModalLabel">
									<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
								</h4>
							</div>
							<div class="modal-body">
								<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsgforSave"></div>
							</div>
							<div class="modal-footer">
								<button type="button" onclick="ExistsOk()" data-dismiss="modal" class="btn btn-theme">
									<spring:message code="footer.jsp.btn.ok" text="OK" />
								</button>
							</div>
						</div>
					</div>
				</div>
				<!-- Footer error modal end -->


				<div class="panel-body-trx">
				<input type="hidden" id="retailerProfitPrcnt" value="${retailerProfitPrcnt}" />
				<input type="hidden" id="orderQtyFrmSale" value="${purOrderQtyFromSale}" />
				<input type="hidden" id="saleHistoryDay" value="${saleHistoryDay}" />
				<input type="hidden" id="dayToPurchase" value="${dayToPurchase}" />
					<table>
						<tr align="center" style="font-weight: bold;">
							<td><spring:message code="purorder.jsp.orderdt" text="Order Date" /></td>
							<td id="duedate_label"><spring:message code="purorder.jsp.duedt" text="Due Date" /></td>
							<td width="40%" id="vendor_label"><spring:message code="purinvdet.jsp.vendor" text="Vendor" /></td>
							<td><spring:message code="purinvdet.jsp.discprcnt" text="Disc%" /></td>
							<td><spring:message code="purorder.jsp.ordertype" text="Order Type" /></td>
						</tr>
						<tr>
							<td style="padding: 0 1px;">
								<div class="input-group">
									<input type="text" class="form-control-trx" id="orderdt" name="orderDate" readonly="readonly" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
								</div>
							</td>
							<td style="padding: 0 1px;">
								<div class="input-group">
									<input type="text" class="form-control-trx" id="duedt" name="dueDate">
									<input type="hidden" id="noOfDue" value="${noOfDue}" />
								</div>
							</td>
							<td style="padding: 0 1px;"><select class="form-control-trx" name="distributorId" id="seldistributor" onchange="getvendordisval()">
									<option value="0_0.0">Select</option>
									<c:if test="${!empty allVendors}">
										<c:forEach items="${allVendors}" var="allVendor">
											<option value="${allVendor.id}_${allVendor.discount}">${allVendor.name}</option>
										</c:forEach>
									</c:if>
									<option value="-1">Add Vendor</option>
							</select></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" readonly="readonly" id="vendordis"></td>
							<td class="hide"><input class="form-control-trx" type="hidden" id="vendorchnge1"></td>
							<td style="padding: 0 1px;">
								<select class="form-control-trx" id="ordertype" onchange="getOrderByType()">
									<option value="0">Select</option>
									<option value="AUTO">Auto</option>
									<option value="SALE">Sale</option>
								</select>
							</td>
							<td colspan="2">
								<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
							</td>

						</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12" id="header_filter2">
			<div class="panel-trx panel-default">
				<div class="panel-body-trx" id="header_div">
					<table>
						<tr align="center" style="font-weight: bold;">
							<td><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></td>
							<td colspan="2" class="hide"><spring:message code="cmn.jsp.barcode" text="Barcode" /></td>
							<td>
								<span id="newItemLabel"><spring:message code="cmn.jsp.capitalize.new" text="New" /></span>
								<span id="editItemLabel" class="hide"><spring:message code="cmn.jsp.btn.edit" text="Edit" /></span>
							</td>
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
							<td class="hide" style="padding: 0 1px;" colspan="2"><input class="form-control-trx" type="text" id="purbarcode" placeholder="Scan Barcode"><input type="hidden" id="purHsnCode" /></td>
							<td style="padding: 0 1px;">
								<button class="btn btn-primary btn-sm" id="addNewItemBtn" type="button" style="padding: 4px 10px;width: 100%;" onclick="addNewItem()">
									<i class="fa fa-plus"></i> ITEM
								</button>
								<button class="btn btn-info btn-sm hide" id="editNewItemBtn" type="button" style="padding: 4px 10px;width: 100%;" onclick="addNewItem()">
									<i class="fa fa-pencil"></i> ITEM
								</button>
							</td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="batch_no"></td>
							<td style="padding: 0 1px;" class="hide"><div class="input-group">
									<input type="text" class="form-control-trx" id="exp" name="expDate" placeholder="MM/YY" maxlength="5" onchange="expiryCalculation($(this).val());">
								</div></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="pqty"><input type="hidden" id="punitid" /></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="ratio"></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="lqty" tabindex="-1" readonly></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="mrp"></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="rate"></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="sale_rate"></td>
							<td class="hide" style="padding: 0 1px;"><input class="form-control-trx" type="text" id="ma" value="0"></td>

							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="total" tabindex="-1" readonly></td>
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
						</tr>
						<tr>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="mfg" tabindex="-1" readonly><input type="hidden" id="mfgid" /></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="grp" tabindex="-1" readonly><input type="hidden" id="grpid" /></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="sch" tabindex="-1" readonly><input type="hidden" id="schid" /></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="edpercnt"></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="ed"></td>
							<td style="padding: 0 1px;" ><input class="form-control-trx" type="text" id="taxprcnt" value="${sessionScope.sesloggedinUser.taxPer}" tabindex="-1" readonly><input type="hidden" id="purTaxId" /></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="tax" tabindex="-1" readonly><input type="hidden" id="purtaxmode" /><input type="hidden" id="purisgrptax" /></td>
							<td style="padding: 0 1px;"  class="hide"><input class="form-control-trx" type="text" id="vatprcnt" value="${sessionScope.sesloggedinUser.vatPer}"></td>
							<td style="padding: 0 1px;"  class="hide"><input class="form-control-trx" type="text" id="vat" tabindex="-1" readonly></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="dprcnt" value="0"></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="disc" tabindex="-1" readonly></td>
							<td class="hide"><input type="hidden" id="id" /></td>
							<td class="hide"><input type="hidden" id="tblrow_id" /></td>
							<td style="padding: 0 1px;">
								<button class="btn btn-success btn-sm" type="button" id="add_btn">
									<spring:message code="cmn.jsp.addcaps" text="ADD" />
								</button>
								<button class="btn btn-success btn-sm hide" style="text-transform: uppercase;" type="button" id="edit_btn">
									<spring:message code="cmn.jsp.btn.update" text="UPDATE" />
								</button>
							</td>
							<td><button class="btn btn-primary btn-sm" id="clear_btn" type="button">
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
			<div style="overflow: auto;" id="detail_table">
				<table id="peitem" style="margin-bottom: 5px;" class="table table-bordered table-striped table-condensed-trx table-hover">
					<thead>
						<tr>
							<th><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></th>
							<th class="hide"><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
							<th class="hide"><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></th>
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
							<th class="numeric"><spring:message code="purinvdet.jsp.dltbtn" text="Del." /></th>
						</tr>
					</thead>
					<tbody id="polisttbody">

					</tbody>
				</table>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12" id="footer_detail">
			<table>
				<tr>
					<td class="font-bold"><spring:message code="purinvdet.jsp.itemcount" text="ItemCount" />:&nbsp;</td>
					<td width="3%" class="font-bold"><span id="itemcount">0</span></td>
					<td class="font-bold"><spring:message code="purinvdet.jsp.total" text="Total" />:</td>
					<td style="padding-right: 5px;"><input class="form-control-trx" style="color: #000000;" type="text" id="totgrosamnt" tabindex="-1" readonly></td>
					<td class="font-bold"><spring:message code="purinvdet.jsp.totmrp" text="Tot.MRP" />:</td>
					<td style="padding-right: 5px;"><input class="form-control-trx" style="color: #000000;" type="text" id="totgrosmrp" tabindex="-1" readonly></td>
					<td class="font-bold hide"><spring:message code="purinvdet.jsp.toted" text="Tot.ED" />:</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="toted" tabindex="-1" readonly></td>
					<td class="font-bold hide"><spring:message code="purinvdet.jsp.totvat" text="Tot.Vat" />:</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="totvatamnt" tabindex="-1" readonly></td>
					<td class="font-bold"><spring:message code="purinvdet.jsp.tottax" text="Tot.Tax" />:</td>
					<td style="padding-right: 5px;"><input class="form-control-trx" type="text" id="tottaxamnt" tabindex="-1" readonly></td>
					<td class="font-bold"><spring:message code="purinvdet.jsp.totdisc" text="Tot.Disc" />:</td>
					<td style="padding: 2px 5px 0 0;"><input class="form-control-trx" type="text" id="totdiscamnt" tabindex="-1" readonly></td>
					<td class="font-bold"><spring:message code="purinvdet.jsp.roff" text="R.Off" />:</td>
					<td style="padding: 2px 5px 0 0;"><input class="form-control-trx" type="text" id="roundoff" tabindex="-1" readonly="readonly"></td>


					<td><span style="font-size: 16px; font-weight: bold; color: #d43f3a;"><spring:message code="purinvdet.jsp.nettotal" text="NetTotal" /></span>:</td>
					<td width="8%"><input class="form-control-trx" style="font-weight: bold; color: #000000; background-color: #ebccd1;" type="text" id="totnetamnt" tabindex="-1" readonly></td>
				</tr>
				
				<tr>
					<td class="font-bold"><spring:message code="purinvdet.jsp.remarks" text="Remarks" />:</td>
					<td colspan="3" style="padding: 1px 5px 0 0"><input class="form-control-trx" type="text" id="remarks"></td>

					<td style="padding-top: 1px; padding-right: 4px;"><button class="btn btn-primary btn-sm hide" type="button">
							<spring:message code="cmn.jsp.btn.post" text="POST" />
						</button></td>
					<td colspan="4" style="padding-top: 1px;">
						<button style="padding: 5px 8px;" class="btn btn-info btn-sm" type="button" id="new_btn" onclick="javascript:toPurchaseOrder();">
							<spring:message code="cmn.jsp.new" text="NEW" />
						</button>
						<button style="padding: 5px 8px;" class="btn btn-danger btn-sm hide" type="button">
							<spring:message code="cmn.jsp.tblhdr.del" text="DEL" />
						</button>
						<button style="padding: 5px 8px;" class="btn btn-success btn-sm" type="button" id="save_btn">
							<spring:message code="cmn.jsp.btn.savecaps" text="SAVE" />
						</button>
					</td>
				</tr>
			</table>
		</div>
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
							<td style="padding: 0 1px;"><select class="form-control-trx" id="orderdistributor" onchange="getvendordisval()">
									<option value="0">Select</option>
									<c:if test="${!empty allVendors}">
										<c:forEach items="${allVendors}" var="allVendor">
											<option value="${allVendor.id}">${allVendor.name}</option>
										</c:forEach>
									</c:if>
							</select></td>
							<td width="54%">
								<input type="hidden" id="manufacturerId_order" value="0" class="form-control-trx" name="manufacturerId">
								<input type="text" id="itemManufacturer_order" value="" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.manufacturer" text="Manufacturer" />(Please type atleast 2 characters)">
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

<!--/wrapper -->
<jsp:include page="/pages/common/commonnewitemadd.jsp" />
<script src="${pageContext.request.contextPath }/assets/js/proc/purorder/purorder.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath }/assets/js/proc/purorder/purorder_en.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/proc/vendor_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>
<script type="text/javascript">
	var BASE_URL = "${pageContext.request.contextPath}";

	function showConfirmModal() {
		$('#confirmMessageModal').modal('show');
	}	
	
	$(document).ready(function() {
		$("input:text").focus(function() {
			$(this).select();
		});
		
		var today = new Date();
		today.setDate(today.getDate()+parseInt($("#noOfDue").val()));
		var yyyy = today.getFullYear().toString();
	    var mm = (today.getMonth()+1).toString(); // getMonth() is zero-based
	    var dd  = today.getDate().toString();
		$("#duedt").val(yyyy+"-"+(mm[1]?mm:"0"+mm[0])+"-"+(dd[1]?dd:"0"+dd[0]));

		$('#duedt').datepicker({
			format : 'yyyy-mm-dd',
			startDate : today,
			autoclose: true,
		});		
		
		$("#exp").keyup(function() {
			if ($(this).val().length == 2) {
				if ($(this).val() <= 12 && $(this).val() > 0) {
					$(this).val($(this).val() + "/");
				} else {
					$(this).val("");
				}

			}
		});
		
		$("#itemManufacturer_order").autocomplete({
			source : function(	request,
								response) {
				if (request.term.length >= 2) {
					$.ajax({
						url : BASE_URL + "/manufacturer/autocompleteitemmanufacturer.htm",
						type : "GET",
						data : {
							tagName : request.term
						},

						dataType : "json",

						success : function(data) {
							response($.map(data, function(v) {
								return {
									label : v.name,
									itemCode : v.id,
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
				console.log(ui.item.itemCode)
				console.log(ui.item.label)
				$("#manufacturerId_order").val(ui.item.itemCode);
			},
			change : function(	e,
								ui) {
				if (!(ui.item))
					e.target.value = "";
			},
		});
		
	});
</script>
