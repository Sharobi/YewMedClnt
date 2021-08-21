<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
<!--
.ui-autocomplete {
	overflow-y: scroll; max-height: 250px; width: 300px; word-break: break-all;
}
-->
</style>
<c:set var="today" value="<%=new java.util.Date()%>" />
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p></p>
			<input type="hidden" id="isexclusiversp" value="${sesloggedinStore.isExclusive}"/>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12" id="header_filter1">
			<input type="hidden" id="useridrsp" value="${sessionScope.sesloggedinUser.id}"> <input type="hidden" id="companyIdrsp" value="${sessionScope.sesloggedinUser.companyId}"> <input type="hidden" id="storeIdrsp" value="${sessionScope.sesloggedinUser.storeId}"> <input type="hidden" id="finyrIdrsp" value="${sessionScope.sesloggedinUser.finyrId}">
			<div class="panel-trx panel-default">

				<!-- Confirm Print Sale Return Modal Starts -->

				<div class="modal fade" id="confirmPrintSaleReturnModalrsp" style="text-align: center;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
									<spring:message code="rtrnmemo.jsp.addsuccmsg" text="Sales Return Invoice successfully added!" />
									<br>
									<div class="col-sm-12" style="text-align: center; font-weight: 700;">
										<spring:message code="rtrnmemo.jsp.printsalereturn" text="Print Sale Return" />
										: <input type="checkbox" id="printSaleReturn" name="printSaleReturn" style="zoom: 1.5; vertical-align: middle; margin: 0px;">
									</div>
								</div>
								<input type="hidden" id="confirmvalrsp" value="">
							</div>
							<div class="modal-footer" style="border-top: 0px;">
								<button type="button" onclick="targetURLrsp()" data-dismiss="modal" class="btn btn-theme">
									<spring:message code="footer.jsp.btn.ok" text="OK" />
								</button>
							</div>
						</div>
					</div>
				</div>
				<!-- Confirm Print Purchase Modal ends -->


				<div class="panel-body-trx">
					<table>
						<c:if test="${saleRetId==0}">
							<tr align="center" style="font-weight: bold;">
								<td><spring:message code="pos.jsp.date" text="Date" /></td>
								<td colspan="2"><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></td>
								<td colspan="4"><spring:message code="returnmemo.jsp.cashmemono" text="Cash Memo No." /></td>
								<td><spring:message code="pos.jsp.custContact" text="Cust.Contact" /></td>
								<td><spring:message code="pos.jsp.custName" text="Cust. Name" /></td>
								<td><spring:message code="pos.jsp.dctrName" text="Doc. Name" /></td>
							</tr>

							<tr>
								<td style="padding: 0 1px;">
									<div class="input-group">
										<input type="text" class="form-control-trx" id="datersp" readonly="readonly" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
									</div>
								</td>
								<td width="9%" style="padding: 0 1px;"><input class="form-control-trx" type="text" value="SRM/${sessionScope.sesloggedinUser.finyrCode}/" readonly></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="" readonly="readonly" value=""><input type="hidden" id="saleretId" value="0"></td>
								<%-- <td width="8%" style="padding: 0 1px;"><input
									class="form-control-trx" type="text"
									value="SIM/${sessionScope.sesloggedinUser.finyrCode}/" readonly></td> --%>

								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retmemoDoc" value="SIM/" size="15" ></td>
								<td><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retmemoFinyr" value="${sessionScope.sesloggedinUser.finyrCode}" size="5"></td>
								<td style="padding: 0 1px;" width="2.5%"><input class="form-control-trx" type="text" style="padding: 4px 0px;" id="retmemoSlash" value="/" readonly></td>

								<td style="padding: 0 1px;">
									<div class="input-group">
										<input class="form-control-trx" type="text" id="invnorsp" value="">
										<div class="input-group-addon" onclick="getRetSaleDetbyInvId(document.getElementById('invnorsp').value,document.getElementById('retmemoFinyr').value)">
											<span class="fa fa-search"></span>
										</div>
									</div>

								</td>
								<td style="padding: 0 1px;"><input class="form-control-trx" id="saleretcustph" type="text"></td>
								<input id="saleretcustid" type="hidden" value="0">
								</td>
								<td width="20%" style="padding: 0 1px;"><input class="form-control-trx" id="saleretcustname" type="text"></td>
								<td width="20%" style="padding: 0 1px;"><input class="form-control-trx" id="saleretdocname" type="text"></td>
								<input id="saleretdocid" type="hidden">
								</td>
							</tr>
						</c:if>
						<c:if test="${saleRetId!=0}">
							<tr align="center" style="font-weight: bold;">
								<td><spring:message code="pos.jsp.date" text="Date" /></td>
								<td colspan="1"><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></td>
								<td colspan="4"><spring:message code="returnmemo.jsp.cashmemono" text="Cash Memo No." /></td>
								<td><spring:message code="pos.jsp.custContact" text="Cust.Contact" /></td>
								<td><spring:message code="pos.jsp.custName" text="Cust. Name" /></td>
								<td><spring:message code="pos.jsp.dctrName" text="Doc. Name" /></td>
							</tr>

							<tr>
								<td style="padding: 0 1px;">
									<div class="input-group">
										<fmt:parseDate value="${saleReturnDTO.invDate}" var="salesRetInvDate" pattern="MMM dd, yyyy" />
										<input type="text" class="form-control-trx" id="datersp" readonly="readonly" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${salesRetInvDate}" />">
										<%-- 										<input type="text" class="form-control-trx" id="date" value="${saleReturnDTO.invDate}" /> --%>
									</div>
								</td>
								<td width="8%" style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" value="SRM/${sessionScope.sesloggedinUser.finyrCode}/" readonly></td>
								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" readonly="readonly" id="saleretinvno" value="${saleReturnDTO.invNo}"><input type="hidden" id="saleretId" value="${saleReturnDTO.saleReturnId}"></td>
								<%-- <td width="8%" style="padding: 0 1px;"><input
									class="form-control-trx" type="text"
									value="SIM/${sessionScope.sesloggedinUser.finyrCode}/" readonly></td> --%>

								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retmemoDoc" value="SIM/" size="4" readonly></td>
								<td><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retmemoFinyr" value="${sessionScope.sesloggedinUser.finyrCode}" size="5"></td>
								<td style="padding: 0 1px;" width="2.5%"><input class="form-control-trx" type="text" style="padding: 4px 0px;" id="retmemoSlash" value="/" readonly></td>

								<td style="padding: 0 1px;">
									<div class="input-group">
										<input class="form-control-trx" type="text" id="invnorsp" value="">
										<div class="input-group-addon">
											<span class="fa fa-search" onclick="getRetSaleDetbyInvId(document.getElementById('invnorsp').value,document.getElementById('retmemoFinyr').value)"></span>
										</div>
									</div>
								</td>
								<td style="padding: 0 1px;"><input class="form-control-trx" id="saleretcustph" type="text" value="${saleReturnDTO.customerPhone}" readonly></td>
								<input id="saleretcustid" type="hidden" value="${saleReturnDTO.customerId}">
								</td>
								<td width="20%" style="padding: 0 1px;"><input class="form-control-trx" id="saleretcustname" type="text" value="${saleReturnDTO.customerName}" readonly></td>
								<td width="20%" style="padding: 0 1px;"><input class="form-control-trx" id="saleretdocname" type="text" value="${saleReturnDTO.doctorName}" readonly></td>
								<input id="saleretdocid" type="hidden" value="${saleReturnDTO.doctorId}">
								</td>
							</tr>
						</c:if>
					</table>
				</div>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12" id="header_filter2">
			<div class="panel-trx panel-default">
				<div class="panel-body-trx" id="header_div_rsp">
					<table>
						<tr align="center" style="font-weight: bold;">
							<td><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></td>
							<td>Barcode</td>
							<td id="pqty_label"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></td>
							<td id="lqty_label"><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></td>
							
							<td><spring:message code="purinvdet.jsp.batch" text="Batch" /></td>
							<td><spring:message code="purinvdet.jsp.expdt" text="Exp" /></td>

							<td><spring:message code="pos.jsp.mrpPack" text="MRP/Pack" /></td>
							<td><spring:message code="purinvdet.jsp.mrp" text="MRP" /></td>
							<td><spring:message code="rtrnmemo.jsp.srate" text="S.Rate" /></td>
							<td><spring:message code="rtrnmemo.jsp.srate/ls" text="S.Rate/Ls" /></td>


							<td><spring:message code="purinvdet.jsp.total" text="Total" /></td>
						</tr>
						<tr>
							<!-- 						data-toggle="modal" data-target="#itemsaledetailModal" -->
							<td width="20%" style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_name_rsp" placeholder="Please type atleast 2 characters"><input type="hidden" id="item_id_rsp" value="0"></td>
							<td width="12%"><input class="form-control-trx" type="text"  placeholder="Scan Barcode" id="item_barcode_rsp"></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_rpqty_rsp" onkeydown="numcheck(event)" value="0"></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_rlqty_rsp" onkeydown="numcheck(event)" value="0"></td>
							
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_bat_rsp" readonly></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_exp_rsp" readonly></td>

							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_mrpperpack_rsp" readonly></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_mrp_rsp" readonly></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_rate_rsp" readonly></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_rateperloose_rsp" readonly></td>

							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_netamt_rsp" value="0" readonly></td>
						</tr>
						<tr align="center" style="font-weight: bold;">
							<td><spring:message code="purinvdet.jsp.mfg" text="Mfg" /></td>
							<td colspan="3"><spring:message code="itemmstr.jsp.content" text="Content" /></td>
							<td><spring:message code="purinvdet.jsp.discprcnt" text="D%" /></td>
							<td><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></td>
							<td><spring:message code="purretrn.jsp.bpqty" text="B.P.Qty" /></td>
							<td><spring:message code="purretrn.jsp.blqty" text="B.L.Qty" /></td>
							<td class="hide"><spring:message code="purinvdet.jsp.pqty" text="P.Qty Hide" /></td>
							<td class="hide"><spring:message code="purinvdet.jsp.lqty" text="L.Qty Hide" /></td>
						</tr>
						<tr>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_mfg_rsp" readonly></td>
							<td colspan="3" style="padding: 0 1px;"><input class="form-control-trx" id="item_content_rsp" title="" type="text" readonly></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_disper_rsp" value="0" readonly></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_conv_rsp" readonly></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_pqty_rsp" value="0" readonly></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_lqty_rsp" value="0" readonly></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="hidden" id="item_rpqty_hide_rsp" value="0"></td>
							<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="hidden" id="item_rlqty_hide_rsp" value="0"><input type="hidden" id="item_taxId_rsp"><input type="hidden" id="item_taxPercentage_rsp"><input type="hidden" id="item_isGroupTax_rsp"><input type="hidden" id="item_CalcTaxAmt_rsp" value="0"><input type="hidden" id="item_discount_rsp"><input type="hidden" id="item_isDiscount_rsp"><input type="hidden" id="item_maxDiscountLimit_rsp" value="0"><input type="hidden" id="item_taxMode_rsp" value="0"><input type="hidden" id="item_hsnCode_rsp" value="0"></td>
							<td colspan="2">
								<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg_rsp"></div>
							</td>
							<c:if test="${saleReturnDTO.isPosted ==0}">
								<td><button class="btn btn-success btn-sm" type="button" onclick="addOrUpdateItemToDetailsTablersp(0)" id="add_btn_rsp">
										<spring:message code="cmn.jsp.addcaps" text="ADD" />
									</button>
									<button class="btn btn-success btn-sm hide" style="text-transform: uppercase;" type="button" onclick="addOrUpdateItemToDetailsTablersp(1)" id="edit_btn_rsp">
										<spring:message code="cmn.jsp.btn.update" text="UPDATE" />
									</button></td>
								<td><button class="btn btn-primary btn-sm" type="button" onclick="clearHeaderDivrsp()">
										<spring:message code="cmn.jsp.btn.clear" text="CLEAR" />
									</button></td>
							</c:if>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<div style="overflow: auto;" id="detail_table">
				<table id="salretitem" style="margin-bottom: 5px;" class="table table-bordered table-striped table-condensed-trx table-hover">
					<thead>
						<tr>
							<th><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></th>
							<th><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
							<th><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
							<th><spring:message code="purinvdet.jsp.mfg" text="Mfg" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></th>
							<th class="numeric"><spring:message code="pos.jsp.mrpPack" text="MRP/Pack" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
							<th class="numeric"><spring:message code="rtrnmemo.jsp.srate/ls" text="Rate/Ls" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.disc" text="Disc" />%</th>
							<th class="numeric"><spring:message code="pos.jsp.netamnt" text="Net Amt" /></th>
							<th class="numeric"><spring:message code="purinvdet.jsp.dltbtn" text="Del." /></th>
						</tr>
					</thead>
					<tbody id="salerettabitemdetails">
						<c:if test="${!empty saleReturnDetailsDTOs }">
							<c:forEach items="${saleReturnDetailsDTOs}" var="saleRetDetail">
								<tr id="${saleRetDetail.itemUniqueKey}" style='cursor: pointer;' onclick="javascript:itemHeaderDivViewrsp(this.id);">
									<td id="salerettabname">${saleRetDetail.itemName}</td>
									<td id="salerettabbat">${saleRetDetail.batchNo}</td>
									<td id="salerettabexpdt">${saleRetDetail.expiryDateFormat}</td>
									<td id="salerettabmanname">${saleRetDetail.manufacturerName}</td>
									<td class="numeric" id="salerettabpqty">${saleRetDetail.packQty}</td>
									<td class="numeric" id="salerettablqty">${saleRetDetail.looseQty}</td>
									<td class="numeric" id="salerettabconv">${saleRetDetail.conversion}</td>
									<td class="numeric" id="salerettabmrppack"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleRetDetail.mrp}" /></td>
									<td class="numeric" id="salerettabmrp"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleRetDetail.mrpPerUnit}" /></td>
									<td class="numeric" id="salerettabrateperunit"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleRetDetail.ratePerUnit}" /></td>
									<td class="numeric" id="salerettabdiscperc"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleRetDetail.discPer}" /></td>
									<td class="numeric" id="salerettabtotamt"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleRetDetail.totAmount}" /></td>
									<td><c:if test="${saleReturnDTO.isPosted ==0}">
											<button class="btn btn-theme04 btn-xs" id="${saleRetDetail.itemUniqueKey}" onclick='javascript:showSelRetItemDelModal(this.id);'>
												<i class="fa fa-trash-o "></i>
											</button>
										</c:if></td>
									<td class="hide" id="salepqty_rsp">${saleRetDetail.remainingPackQty}</td>
									<td class="hide" id="salelqty_rsp">${saleRetDetail.remainingLooseQty}</td>
									<td id="salerettabpunitid" class="hide">${saleRetDetail.packUnitId}</td>
									<td id="salerettablunitid" class="hide">${saleRetDetail.looseUnitId}</td>
									<td id="salerettabcontent" class="hide">${saleRetDetail.contentName}</td>
									<td id="salerettabrate" class="hide">${saleRetDetail.rate}</td>
									<td id="salerettabdisamt" class="hide">${saleRetDetail.disc}</td>
									<td id="salerettabed" class="hide">${saleRetDetail.edPer}</td>
									<td id="salerettabedamt" class="hide">${saleRetDetail.ed}</td>
									<td id="salerettabtax" class="hide">${saleRetDetail.taxPer}</td>
									<td id="salerettabtaxamt" class="hide">${saleRetDetail.tax}</td>
									<td id="salerettabvat" class="hide">${saleRetDetail.vatPer}</td>
									<td id="salerettabvatamt" class="hide">${saleRetDetail.vat}</td>
									<td id="salerettabsaleid" class="hide">${saleRetDetail.saleId}</td>
									<td id="salerettabsaleinvno" class="hide">${saleRetDetail.saleInvNo}</td>
									<td class="hide" id="salerettabpqtyhide">${saleRetDetail.hidePackQty}</td>
									<td class="hide" id="salerettablqtyhide">${saleRetDetail.hideLooseQty}</td>
									<td id='salerettabsku' class='hide'>${saleRetDetail.sku}</td>
									<td id='salerettabtaxId' class='hide'>${saleRetDetail.taxId}</td>
									<td id='salerettabtaxPercentage' class='hide'>${saleRetDetail.taxPercentage}</td>
									<td id='salerettabitemTaxAmount' class='hide'>${saleRetDetail.taxAmount}</td>
									<td id='salerettabisGroupTax' class='hide'>${saleRetDetail.isGroupTax}</td>
									<td id='salerettabtaxMode' class='hide'>${saleRetDetail.taxMode}</td>
									<td id='salerettabhsnCode' class='hide'>${saleRetDetail.hsnCode}</td>
									<!-- 									<td class="hide" id="salerettabpqtyhide">0</td> -->
									<!-- 									<td class="hide" id="salerettablqtyhide">0</td> -->
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
		
		<!-- for details balance account   -->
		<div   class="col-lg-12 col-md-12 col-sm-12" id="footer_detail">
		
	 
		
		<input type="hidden" id="dueties_and_tax_acc" value="<spring:message code="accgroup.jsp.duties_code" text="DT"/>">
		<input type="hidden" id="saleaccunt_group_code" value="<spring:message code="accgroup.jsp.saleac_code" text="SA"/>">
		<input type="hidden" id="roundoff_group_code" value="<spring:message code="accgroup.jsp.roun_code" text="ROD"/>">
		<input type="hidden" id="debitor_group_code" value="<spring:message code="accgroup.jsp.deb_code" text="SDE"/>">
	 
		<input type="hidden" id="cash_in_hand_group_code" value="<spring:message code="accgroup.jsp.cash_code" text="CIH"/>">
		<input type="hidden"  id="duties_ledger_id" value="0">
		 <input type="hidden"  id="round_ledger_id" value="0">
		 <input type="hidden"  id="sales_ledger_id" value="0">
		  <input type="hidden"  id="debitor_ledger_id" value="0">
		<input class="form-control-trx" type="hidden" id="debitor_amt_rsp" readonly value="0">
		  <input class="form-control-trx" type="hidden" id="sale_account_rsp"  readonly value="0">
		 
			<table  >
		
						 
				
				<!--  for  account  end-->
				
					<tr>
					<td  width="8%" class="font-bold"><spring:message code="purinvdet.jsp.itemcount" text="ItemCount" />:</td>
					<td width="2%" class="font-bold"><span id="totitmcount_rsp">1</span></td>

					<td class="font-bold hide"><spring:message code="purinvdet.jsp.total" text="Total:" />:</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" style="color: #000000;" type="text" id="totgrossamt_rsp" value="${saleReturnDTO.grossAmount}" readonly></td>
					<td class="font-bold hide"><spring:message code="purinvdet.jsp.totvat" text="Tot.Vat:" /></td>
					<td style="padding-right: 5px;" class="hide" >
					<input class="form-control-trx" type="text" id="totvatamt_rsp" value="${saleReturnDTO.vatAmount}" tabindex="-1" readonly>
					
					</td>
					<td class="font-bold " ><spring:message code="pos.jsp.stax" text="Tot.Tax" /></td>
					<td style="padding-right: 5px;">
					
					<input class="form-control-trx" type="text" id="tottaxamt_rsp" value="${saleReturnDTO.taxAmount}" tabindex="-1" readonly>
					</td>
					<td class="font-bold hide"><spring:message code="purinvdet.jsp.totdisc" text="Tot.Disc:" />:</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="" value="" readonly></td>

					
					<td class="font-bold"><spring:message code="purinvdet.jsp.roff" text="R.Off:" />:</td>
					<td style="padding-right: 5px;" width="8%">
					<input class="form-control-trx" type="text" id="roundoff_rsp" value="<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" groupingUsed="false" value="${saleReturnDTO.roundoff}" />" readonly="readonly"></td>
			  	<td><span style="font-size: 16px; font-weight: bold; color: #d43f3a;"><spring:message code="rtrnmemo.jsp.retamt" text="Ret.Amt" />:</span></td>
					<td width="15%" style="padding-right: 5px;"><input class="form-control-trx" style="font-weight: bold; color: #000000; background-color: #ebccd1;" type="text" id="totretamt_rsp" value="<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" groupingUsed="false" value="${saleReturnDTO.netAmount}" />" readonly></td>
					
					
					 

				</tr>
		
				
				<tr>
					<td class="font-bold"><spring:message code="rtrnmemo.jsp.reason" text="Reason"></spring:message>:</td>
					<td colspan="5" style="padding: 3px 5px 0 0"><input class="form-control-trx" id="remarks_rsp" type="text" value="${saleReturnDTO.remarks}"></td>
					
				
					<td class="font-bold hide"><spring:message code="pos.jsp.spldscprcnt" text="Spl.Disc(%)" />:</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" style="color: #000000;" type="text" id="spcldiscperc_rsp" value="${saleReturnDTO.specialDiscPer}" onkeyup="calculateSpclDiscrsp()"></td>
					<td class="font-bold hide"><spring:message code="pos.jsp.spldscamnt" text="Spl.Disc.Amt" />:</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="spcldisc_rsp" value="${saleReturnDTO.specialDiscAmount}" readonly></td>
					
					<td colspan="2" style="padding-top: 4px; padding-right: 4px;"><c:if test="${saleReturnDTO.isPosted ==0}">
							<c:if test="${menuByUserDTO.isAll==1}">
								<button style="padding: 5px 8px;" class="btn btn-primary btn-sm" type="button" onclick="saveOrUpdateSaleReturnInv()">
									<spring:message code="cmn.jsp.return" text="RETURN" />
								</button>
							</c:if>
							<button class="btn btn-success btn-sm" type="button" id="" onclick="openRetMemo()">
								<spring:message code="cmn.jsp.new" text="NEW" />
							</button>
							<c:if test="${menuByUserDTO.isView==1}">
								<button style="padding: 5px 8px;" class="btn btn-danger btn-sm" type="button" id="" onclick="deleteRetSalesInv()">
									<spring:message code="cmn.jsp.tblhdr.del" text="DEL" />
								</button>
							</c:if>
						</c:if></td>
				
				</tr>
			</table>
			 
			
		</div>

	</div>
</section>
<!--/wrapper -->

<!-- Modal Starts -->
<div class="modal fade" id="saledetailModal_rsp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 766px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="rtrnmemo.jsp.salereturnmodal.invno" text="Sale Details for " />
					<spring:message code="purinvdet.jsp.invno" text="Invoice No" />
					. <span id="searchmodinvno_rsp"> SIM/${sessionScope.sesloggedinUser.finyrCode}/</span>
				</h4>
			</div>
			<div class="modal-body">
				<div id="saledetnotfounddiv_rsp" class="hide">
					<span class="font-bold"><spring:message code="rtrnmemo.jsp.memonotfoundfornumbermsg" text="No Cash memo found for this invoice number" />.</span>
				</div>
				<div id="saledetfounddiv_rsp">
					<div>
						<span class="font-bold"><spring:message code="rtrnmemo.jsp.salereturnmodal.invdt" text="Invoice Date" /> : </span> <span id="searchmodinvdate_rsp">2017-02-09</span>
					</div>
					<div>
						<div class="col-md-6 col-sm-6" style="padding: 0px;">
							<span class="font-bold"><spring:message code="rtrnmemo.jsp.salereturnmodal.invamnt" text="Invoice Amount" /> : </span> <span id="searchmodtotamt_rsp">100.00</span>
						</div>
						<div class="col-md-6 col-sm-6">
							<span class="font-bold">Bill Disc(%): </span><span id="searchmodcustspcldisc_rsp" class="font-bold" style="color: #ed7ba3;">0.00</span>
						</div>
					</div>
					<div>
						<div class="col-md-6 col-sm-6" style="padding: 0px;">
							<span class="font-bold"><spring:message code="pos.jsp.custContact" text="Cust.Contact" /> : </span><span id="searchmodcustcont_rsp">9568034213</span>
						</div>
						<div class="col-md-6 col-sm-6">
							<span class="font-bold"><spring:message code="pos.jsp.custName" text="Cust. Name" /> : </span><span id="searchmodcustname_rsp">S Maity</span>
						</div>

					</div>
					<div>
						<span class="font-bold"><spring:message code="pos.jsp.dctrName" text="Doc. Name" /> :</span> <span id="searchmoddocname_rsp">Dr T Roy</span>
					</div>
				</div>
				<div style="max-height: 300px; height: 200px; overflow: auto;" id="saledetmodtable_rsp">
					<table id="searchmodtable_rsp" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th></th>
								<th><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></th>
								<th><spring:message code="rtrnmemo.jsp.salereturnmodal.batch" text="Batch No" /></th>
								<th><spring:message code="rtrnmemo.jsp.salereturnmodal.exp" text="Exp Dt" /></th>
								<th><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
								<th><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></th>
								<th><spring:message code="purinvdet.jsp.preretpqty" text="Pre Ret P.Qty" /></th>
								<th><spring:message code="purinvdet.jsp.preretlqty" text="Pre Ret L.Qty" /></th>
								<th><spring:message code="purinvdet.jsp.retpqty" text="Ret P.Qty" /></th>
								<th><spring:message code="purinvdet.jsp.retlqty" text="Ret L.Qty" /></th>
								<th><spring:message code="purinvdet.jsp.mrpls" text="MRP/Ls" /></th>
								<th><spring:message code="purinvdet.jsp.ratels" text="Rate/Ls" /></th>
								<th><spring:message code="purinvdet.jsp.retamt" text="Ret Amt" /></th>
							</tr>
						</thead>
						<tbody id="searchmodtbody_rsp">

						</tbody>
						<div>
							<span id="alertmessagecont_rsp" style="font-weight: bold; color: red;"> </span>
						</div>

					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" class="btn btn-primary" id="nocashmemofoundokbut_rsp" onclick="getmodretcheckeditemlistrsp()">
					<spring:message code="footer.jsp.btn.ok" text="Ok" />
				</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="itemsaledetailModal_rsp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 800px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="rtrnmemo.jsp.salereturnmodal.invno" text="Sale Details for" />
					<span id="itemsaledetailitemname_rsp"> Telma AM</span>
				</h4>
			</div>
			<div class="modal-body">
				<div id="itemsaledetnotfounddiv_rsp" class="hide">
					<span class="font-bold"><span id=""><spring:message code="rtrnmemo.jsp.memonotfoundmsg" text="No Cashmemo found for this item" /></span>
				</div>
				<div style="max-height: 300px; height: 200px; overflow: auto;" id="itemsaledetmodtable_rsp">
					<table id="" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>

								<th><spring:message code="purinvdet.jsp.invno" text="Invoice No" />.</th>
								<th><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></th>
								<th><spring:message code="pos.jsp.custContact" text="Cust.Contact" /></th>
								<th><spring:message code="pos.jsp.custName" text="Cust. Name" /></th>
								<th><spring:message code="pos.jsp.dctrName" text="Doc. Name" /></th>
								<th><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
								<th><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
								<th class="numeric"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
								<th class="numeric"><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></th>
								<th class="numeric"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
								<th class="numeric"><spring:message code="purinvdet.jsp.rate" text="Rate" /></th>
								<th class="numeric"><spring:message code="pos.jsp.netamnt" text="Net Amt" /></th>
								<th class="numeric" style="background-color: yellow;">Spcl Disc(%)</th>
							</tr>
						</thead>
						<tbody id="itemsearchmodtbody_rsp">

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

<div class="modal fade" id="currStkGraterModal_rsp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
				<spring:message code="rtrnmemo.jsp.inputgrtsellmsg" text="Input quantity is greater than sell/remaining quantity." />
			</div>
			<div class="modal-footer">
				<button type="button" onclick="closeCurrStkModalrsp()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="sameItemInvModal_rsp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
				<spring:message code="rtrnmemo.jsp.itemalrdyaddedmsg" text="Item already added for this invoice." />
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="sameUserModal_rsp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
				<spring:message code="rtrnmemo.jsp.returndfrntmemomsg" text="You are trying to return different user cashmemo." />
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="confirmModalPos_rsp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" id="cnfrm_cancel_btn">
					<spring:message code="footer.jsp.btn.cancel" text="Cancel" />
				</button>
				<button type="button" onclick="DoConfirmPosrsp()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Modal Ends -->
<div class="modal fade" id="noItemBarcodeModal_rsp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
				No item found against this(<span id="inputbarcode_rsp"></span>) barcode. Please check the input barcode.
			</div>
			<div class="modal-footer">

				<button type="button" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Confirm Modal Start -->

<div class="modal fade" id="confirmModal_rsp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
				<input type="hidden" id="confirmId_rsp" value="">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" id="cnfrm_cancel_btn">
					<spring:message code="footer.jsp.btn.cancel" text="Cancel" />
				</button>
				<button type="button" onclick="DoConfirmrsp()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Confirm Modal end -->
<div class="modal fade" id="confirmMessageModal_rsp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!" />
				</h4>
			</div>
			<div class="modal-body">
				<div id="confirmmessagecontrsp"></div>
				<input type="hidden" id="confirmvalrsp" value="">
				<input type="hidden" id="cnfrmCustName" />
			</div>
			<div class="modal-footer">
				<button type="button" onclick="targetURLrsp()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<script src="${pageContext.request.contextPath }/assets/js/pos/returnmemo/returnsalememo.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/pos/returnmemo/retcashmemo_en.js"></script>
</c:if>
<script type="text/javascript">
	
$( document ).ready(function() {
	/*
	 * add 7_3_2018 
	 */
	
	 
	var sale_return_id=${saleRetId};
	var sale_return_customer_id=${saleReturnDTO.customerId};
	
	var tem_return_tax_amount=${saleReturnDTO.taxAmount};
	var tem_return_net_amount=${saleReturnDTO.netAmount};
	var tem_return_roundoff=${saleReturnDTO.roundoff};
	

	
	
	if (sale_return_id!=0) {
		
		if (tem_return_roundoff>0) {
			 
			$('#debitor_amt_rsp').val(tem_return_net_amount.toFixed(2));
			var temp_val=(tem_return_net_amount-tem_return_tax_amount-tem_return_roundoff);
			$('#sale_account_rsp').val(temp_val.toFixed(2));
			 
		}else
			{
			 
			$('#debitor_amt_rsp').val(tem_return_net_amount.toFixed(2));
			var temp_val=(tem_return_net_amount-tem_return_tax_amount+Math.abs(tem_return_roundoff));
			$('#sale_account_rsp').val(temp_val.toFixed(2));
			}
		
		getvendorledger($('#dueties_and_tax_acc').val(),0,0,0);// for duties and tax
		getvendorledger($('#roundoff_group_code').val(),0,0,1); // for round off  
		getvendorledger($('#saleaccunt_group_code').val(),0,0,2); // for sale account
		
		 
		if (sale_return_customer_id==0) {
			
			getvendorledger($('#cash_in_hand_group_code').val(),0,0,3);// for sunndry debitor credit
		}else
			{
			getvendorledger($('#debitor_group_code').val(),0,sale_return_customer_id,3);// for sunndry debitor credit
			}
		
	}
	
	
});
 
	
</script>