<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<style>
<!--
.ui-autocomplete {
	overflow-y: scroll; height: 250px; width: 300px; word-break: break-all;
}
-->
</style>
<c:set var="today" value="<%=new java.util.Date()%>" />
		<section class="wrapper">
			<div class="row">
          		<div class="col-lg-12">
          		<p></p>
          		</div>
          		<div id="reasonTypeDiv" class="hide">
	          		<select id="returnReasonTypeList" class="form-control-trx selectedReason">
	          			<option value="0">Select</option>
	          			<c:if test="${!empty allReturnReasonTypes}">
							<c:forEach items="${allReturnReasonTypes}" var="allReturnReasonType">
									<option value="${allReturnReasonType.id}">${allReturnReasonType.typeName}</option>
							</c:forEach>
						</c:if>
	          		</select>
          		</div>
          		<input type="hidden" id="returnReasonList" value="${allReturnReasonTypes}" />
          		<div class="col-lg-12 col-md-12 col-sm-12" id="header_filter1">
          		<input type="hidden" id="userid"
				value="${sessionScope.sesloggedinUser.id}"> <input
				type="hidden" id="companyId"
				value="${sessionScope.sesloggedinUser.companyId}"> <input
				type="hidden" id="storeId"
				value="${sessionScope.sesloggedinUser.storeId}"> <input
				type="hidden" id="finyrId"
				value="${sessionScope.sesloggedinUser.finyrId}">
					<div class="panel-trx panel-default">
						<div class="panel-body-trx">
						<input type="hidden" id="purretid" value="${purchaseReturnDTO.purchaseReturnId}" />
							<table>								
								<c:if test="${purRetId==0}">
								<tr align="center" style="font-weight: bold;">
									<td><spring:message code="pos.jsp.date" text="Date" /></td>
									<td><spring:message code="purinvdet.jsp.vendor" text="Vendor" /></td>
									<td colspan="2"><spring:message code="purretrn.jsp.retinvno" text="Pur.Ret Inv No" /></td>
									<td colspan="4"><spring:message code="purretrn.jsp.invno" text="Pur. Inv No" /></td>
									<td><spring:message code="" text="Set Reason for All" /></td>
								</tr>
								<tr>
								<td style="padding: 0 1px;">
										<div class="input-group" style="width: 100%;">
											<input type="text" class="form-control-trx" readonly="readonly" id="retinvdt" name="retinvDate" size="10" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}"/>">
										</div>
									</td>
									<td style="padding: 0 1px;">
										<select class="form-control-trx" name="" id="slctedvendorid" onchange="onchangeDistributor()">
												<c:if test="${!empty allVendors}">
										         <option value="0">Select</option>
											<c:forEach items="${allVendors}" var="allVendor">
													<option value="${allVendor.id}">${allVendor.name}</option>
											</c:forEach>
										</c:if>
										</select>
									</td>
									<td width="10%" style="padding: 0 1px;"><input class="form-control-trx" type="text" tabindex="-1" value="PRM/${sessionScope.sesloggedinUser.finyrCode}/" readonly></td>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text"  tabindex="-1" readonly size="12"></td>
									<%-- <td width="8%" style="padding: 0 1px;"><input class="form-control-trx" tabindex="-1" type="text" value="PIM/${sessionScope.sesloggedinUser.finyrCode}/" readonly></td> --%>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retpurDoc" value="PIM/" size="4" ></td>
									<td><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retpurFinyr" value="${sessionScope.sesloggedinUser.finyrCode}" size="5"></td>
									<td style="padding: 0 1px;" width="2.5%"><input class="form-control-trx" type="text" style="padding: 4px 0px;" id="retpurSlash" value="/" readonly></td>
									<td style="padding: 0 1px;" width="16%">
										<div class="input-group">
										<input class="form-control-trx" type="text" id="purinvno" value="" size="14">
										<div class="input-group-addon" onclick="veiwPurDetForRet(document.getElementById('purinvno').value,document.getElementById('retpurFinyr').value)">
											<span class="fa fa-search" ></span>
										</div>
									</div>
									</td>
									<td style="padding: 0 1px;">
										<select class="form-control-trx" name="" id="slctCmnReason" onchange="chngeCmnReason();">
											<option value="0">Select</option>
						          			<c:if test="${!empty allReturnReasonTypes}">
												<c:forEach items="${allReturnReasonTypes}" var="allReturnReasonType">
														<option value="${allReturnReasonType.id}">${allReturnReasonType.typeName}</option>
												</c:forEach>
											</c:if>
										</select>
									</td>
								</tr>
								</c:if>
								<c:if test="${purRetId!=0}">
								<tr align="center" style="font-weight: bold;">
								<td><spring:message code="pos.jsp.date" text="Date" /></td>
									<td><spring:message code="purinvdet.jsp.vendor" text="Vendor" /></td>
									<td colspan="1"><spring:message code="purretrn.jsp.retinvno" text="Pur.Ret Inv No" /></td>
									<td colspan="4"><spring:message code="purretrn.jsp.invno" text="Pur. Inv No" /></td>
									<td><spring:message code="" text="Set Reason for All" /></td>
								</tr>
								<tr>
								<td >
										<div class="input-group" style="width: 100%;">
											<input type="text" class="form-control-trx" id="retinvdt" name="retinvDate" readonly="readonly" size="10" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
										</div>
									</td>
									<td style="padding: 0 1px;">
										<select class="form-control-trx" name="" id="slctedvendorid" readonly="readonly">
										         <option value="${purchaseReturnDTO.distributorId}">${purchaseReturnDTO.distributorName}</option>
										          
										</select>
									</td>
<%-- 									<td width="10%" style="padding: 0 1px;"><input class="form-control-trx" type="text" tabindex="-1" value="PRM/${sessionScope.sesloggedinUser.finyrCode}/" readonly></td> --%>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="purretinvno"  tabindex="-1" value="${purchaseReturnDTO.invNo}" readonly size="12"></td>
									<%-- <td width="8%" style="padding: 0 1px;"><input class="form-control-trx" tabindex="-1" type="text" value="PIM/${sessionScope.sesloggedinUser.finyrCode}/" readonly></td> --%>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retpurDoc" value="PIM/" size="4" readonly></td>
									<td><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retpurFinyr" value="${sessionScope.sesloggedinUser.finyrCode}" size="5"></td>
									<td style="padding: 0 1px;" width="2.5%"><input class="form-control-trx" type="text" style="padding: 4px 0px;" id="retpurSlash" value="/" readonly></td>
									<td style="padding: 0 1px;" width="16%">
										<div class="input-group">
										<input class="form-control-trx" type="text" id="purinvno" value="" size="14">
										<div class="input-group-addon" onclick="veiwPurDetForRet(document.getElementById('purinvno').value,document.getElementById('retpurFinyr').value)">
											<span class="fa fa-search"></span>
										</div>
									</div>
									</td>
									<td style="padding: 0 1px;">
										<select class="form-control-trx" name="" id="slctCmnReason" onchange="chngeCmnReason();">
											<option value="0">Select</option>
						          			<c:if test="${!empty allReturnReasonTypes}">
												<c:forEach items="${allReturnReasonTypes}" var="allReturnReasonType">
														<option value="${allReturnReasonType.id}">${allReturnReasonType.typeName}</option>
												</c:forEach>
											</c:if>
										</select>
									</td>
								</tr>
								</c:if>
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
									<td><spring:message code="cmn.jsp.barcode" text="Barcode" /></td>
									<td id="batch_label"><spring:message code="purinvdet.jsp.batch" text="Batch" /></td>
									<td id="exp_label"><spring:message code="purinvdet.jsp.expdt" text="Exp" /></td>
									<td id="pqty_label"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></td>
									<td><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></td>
									<td><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></td>
									<td><spring:message code="purinvdet.jsp.free" text="Free" /></td>
									<td><spring:message code="purinvdet.jsp.mrp" text="MRP" /></td>
									<td id="rate_label"><spring:message code="purinvdet.jsp.rate" text="Rate" /></td>
									<td class="hide"><spring:message code="purinvdet.jsp.ma" text="Ma%" /></td>
									
									<td><spring:message code="purinvdet.jsp.total" text="Total" /></td>
								</tr>
								<tr>
									<td width="20%" style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_name" placeholder="Please type atleast 2 characters"> <input type="hidden" id="item_id" value="0"></td>
									<td width="12%"><input class="form-control-trx" type="text" id="barcode" placeholder="Scan Barcode"><input type="hidden" id="purHsnCode" /></td>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="batch_no" tabindex="-1" readonly></td>
									<td style="padding: 0 1px;"><div class="input-group">
											<input type="text" class="form-control-trx" id="exp" name="expDate" placeholder="MM/YY" maxlength="5" tabindex="-1" readonly>
										</div></td>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="pqty"></td>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="lqty" tabindex="-1" readonly></td>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="ratio" tabindex="-1" readonly></td>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="free" tabindex="-1" readonly><input type="hidden" id="prevfree" /></td>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="mrp" tabindex="-1" readonly></td>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="rate" ></td>
									<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="ma" value="0" tabindex="-1" readonly></td>
									
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
									<td><spring:message code="purretrn.jsp.bpqty" text="B.P.Qty" /></td>
								</tr>
								<tr>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="mfg" tabindex="-1" readonly></td>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="grp" tabindex="-1" readonly></td>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="sch" tabindex="-1" readonly></td>
									<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="edpercnt" tabindex="-1" readonly></td>
									<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="ed" tabindex="-1" readonly> </td>
									<%-- <c:if test="${sessionScope.sesloggedinUser.isTaxRegNo==0}">
									<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="taxprcnt" value="${sessionScope.sesloggedinUser.taxPer}" tabindex="-1" readonly></td>
									<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="tax" tabindex="-1" readonly></td>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="vatprcnt" tabindex="-1" readonly></td>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="vat" tabindex="-1" readonly></td>
									</c:if> 
									<c:if test="${sessionScope.sesloggedinUser.isTaxRegNo==1}">--%>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="taxprcnt" tabindex="-1" readonly><input type="hidden" id="purRetTaxId" /></td>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="tax" tabindex="-1" readonly><input type="hidden" id="purrettaxmode" /><input type="hidden" id="purretisgrptax" /></td>
									<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="vatprcnt" value="${sessionScope.sesloggedinUser.vatPer}"></td>
									<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="vat" tabindex="-1" readonly></td>
									
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="dprcnt" value="0" tabindex="-1" readonly></td>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="disc" tabindex="-1" readonly></td>
									<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="billpqty" tabindex="-1" value="0" readonly></td>
									
									<c:if test="${purchaseReturnDTO.isPosted==0}">
									<td>
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
					<div style="overflow: auto;" id="detail_table">
						<table id="purretitem" style="margin-bottom: 5px;" class="table table-bordered table-striped table-condensed-trx table-hover">
							<thead>
								<tr>
									<th><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></th>
									<th><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
									<th><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
		                            <th><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
									<th><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></th>
									<th><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></th>
									<th><spring:message code="purinvdet.jsp.free" text="Free" /></th>
									<th><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
									<th><spring:message code="purinvdet.jsp.rate" text="Rate" /></th>
		                            <!-- <th class="numeric">Ed%</th> -->
		                            <th class="numeric hide"><spring:message code="purinvdet.jsp.ed" text="Ed" /></th>
		                            <!-- <th class="numeric">Tax%</th> -->
		                            <th class="numeric"><spring:message code="purinvdet.jsp.tax" text="Tax" /></th>
		                            <!-- <th class="numeric">Vat%</th> -->
		                            <th class="numeric hide"><spring:message code="purinvdet.jsp.vat" text="Vat" /></th>
		                            <!-- <th class="numeric">D%</th> -->
		                            <th class="numeric"><spring:message code="purinvdet.jsp.disc" text="Disc" /></th>
		                            <th class="numeric"><spring:message code="purinvdet.jsp.amnt" text="Amt" /></th>
		                            <th class="numeric" ><spring:message code="purinvdet.jsp.totmrp" text="Tot.MRP" /></th>
		                            <th><spring:message code="purinvdet.jsp.returnreason" text="Return Reason" /></th>
		                            <!-- <th class="numeric">Ma%</th> -->
		                            <th class="numeric"><spring:message code="purinvdet.jsp.dltbtn" text="Del." /></th>
								</tr>
							</thead>
							<tbody id="purrettabitemdetails">
							<c:if test="${!empty purchaseReturnDetailsDTOs }">
							<c:forEach items="${purchaseReturnDetailsDTOs}" var="purRetDetail" varStatus="purIndex">
							
								<tr id="${purRetDetail.itemUniqueKey}" style="cursor: pointer;" onclick="javascript:itemDetailView(this.id);">
									<td id="tbl_item_name">${purRetDetail.itemName}</td>
									<td id="tbl_batch_no">${purRetDetail.batchNo}</td>
									<td id="tbl_exp">${purRetDetail.expiryDateFormat}</td>
									<td id="tbl_pqty" class="numeric">${purRetDetail.packQty}</td>
									<td id="tbl_lqty" class="numeric">${purRetDetail.looseQty}</td>
									<td id="tbl_ratio" class="numeric">${purRetDetail.conversion}</td>
									<td id="tbl_free" class="numeric">${purRetDetail.freeQty}</td>
									<td id="tbl_mrp" class="numeric"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" groupingUsed="false" value="${purRetDetail.mrp}" /></td>
									<td id="tbl_rate" class="numeric"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" groupingUsed="false" value="${purRetDetail.rate}" /></td>
									<td id="tbl_ed" class="numeric hide"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${purRetDetail.ed}" /></td>
									<td id="tbl_tax" class="numeric"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${purRetDetail.taxAmount}" /></td>
									<td id="tbl_vat" class="numeric hide"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${purRetDetail.vat}" /></td>
									<td id="tbl_disc" class="numeric"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${purRetDetail.disc}" /></td>
									<td id="tbl_amnt" class="numeric"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" groupingUsed="false" value="${purRetDetail.amount}" /></td>
									<td id="tbl_totamnt" class="numeric"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" groupingUsed="false" value="${purRetDetail.totAmount}" /></td>
									<td>
										<select class="form-control-trx" id="tbl_reason">
											<option value="0">Select</option>
											
											<c:if test="${!empty allReturnReasonTypes}">
												<c:forEach items="${allReturnReasonTypes}" var="allReturnReasonType">
													<c:choose>
														<c:when test="${allReturnReasonType.id==purRetDetail.reasonId}">
															<option value="${purRetDetail.reasonId}" selected="selected">${purRetDetail.reasonTypeName}</option>
														</c:when>
														<c:otherwise>
															<option value="${allReturnReasonType.id}">${allReturnReasonType.typeName}</option>
														</c:otherwise>
													</c:choose>														
												</c:forEach>
											</c:if>
										</select>
									</td>
									<c:if test="${purchaseReturnDTO.isPosted==0}">
									<c:if test="${menuByUserDTO.isView==1}">
									<td><button class="btn btn-theme04 btn-xs" id="${purRetDetail.itemUniqueKey}" onclick="javascript:showPurRetItemDelModal(this.id);">
											<i class="fa fa-trash-o "></i>
										</button></td>
									</c:if>
									</c:if>	
									<c:if test="${purchaseReturnDTO.isPosted==1}">
									<td></td>
									</c:if>
									<td id="tbl_ma" class="hide">0.0</td>
									<td id="tbl_grp" class="hide">${purRetDetail.grpName}</td>
									<td id="tbl_sch" class="hide">${purRetDetail.scheduleName}</td>
									<td id="tbl_mfg" class="hide">${purRetDetail.manufacturerName}</td>
									<td id="tbl_edprcnt" class="hide">${purRetDetail.edPer}</td>
									<td id="tbl_taxprcnt" class="hide">${purRetDetail.taxPercentage}</td>
									<td id="tbl_vatprcnt" class="hide">${purRetDetail.vatPer}</td>
									<td id="tbl_dprcnt" class="hide">${purRetDetail.discPer}</td>
									<td id="tbl_grpid" class="hide">${purRetDetail.grpId}</td>
									<td id="tbl_schid" class="hide">${purRetDetail.scheduleId}</td>
									<td id="tbl_mfgid" class="hide">${purRetDetail.manufacturerId}</td>
									<td id="tbl_punitid" class="hide">${purRetDetail.packUnitId}</td>
									<td id="tbl_id" class="hide">${purRetDetail.itemId}</td>
									<td id="tbl_itemid" class="hide">${purRetDetail.itemId}</td>
									<td id="tbl_invno" class="hide">${purRetDetail.purchaseInvNo}</td>
									<td id="tbl_invid" class="hide">${purRetDetail.purchaseId}</td>
									<td id="tbl_ltAdj" class="hide">${purRetDetail.itemLotAdjAmount}</td>
									<td id="tbl_taxid" class="hide">${purRetDetail.taxId}</td>
									<td id="tbl_taxmode" class="hide">${purRetDetail.taxMode}</td>
									<td id="tbl_taxisgrptax" class="hide">${purRetDetail.isGroupTax}</td>
									<td id="tbl_sku" class="hide">${purRetDetail.sku}</td>
									<td id="tbl_hsn" class="hide">${purRetDetail.hsnCode}</td>
								    <td class="hide" id="purrettabpqtyhide">${purRetDetail.remainingPackQty+purRetDetail.packQty}</td>
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
				
				<div class="col-lg-12 col-md-12 col-sm-12" id="footer_detail">
				
				<fieldset  class="col-lg-12 col-md-12 col-sm-12">
				<!--  for ledger group code -->
		<input type="hidden" id="dueties_and_tax_acc" value="<spring:message code="accgroup.jsp.duties_code" text="DT"/>">
		<input type="hidden" id="pursaccunt_group_code" value="<spring:message code="accgroup.jsp.parchase_code" text="PA"/>">
		<input type="hidden" id="roundoff_group_code" value="<spring:message code="accgroup.jsp.roun_code" text="ROD"/>">
		<input type="hidden" id="crditor_group_code" value="<spring:message code="accgroup.jsp.crd_code" text="SCR"/>">
	    <input type="hidden" id="dicount_group_code" value="<spring:message code="accgroup.jsp.disc_code" text="DIS"/>">
	    <input type="hidden" id="lot_group_code" value="<spring:message code="accgroup.jsp.lotadjs_group_code" text="LOT"/>">   
	    
	    
		
				 
					<table    >
					
					<!--  for accounting start here -->
					
							<tr>
							
							
							
						<!-- purchase accoutn -->
							<th class="font-bold" colspan="2">
							
								<input type="hidden"  id="purchase_ledger_id" value="0">
					       <!--   <span id="purchase_html"></span> -->
							</th>
							<!--  for duties and tax -->
							<th class="font-bold" colspan="2">
							<input type="hidden"  id="duties_ledger_id" value="0">
					        <!--  <span id="duties_html"></span> -->
						     </th>
						     
						     <!--  for discount ledger -->
							<th class="font-bold" colspan="2">
								<input type="hidden"  id="discount_ledger_id" value="0">
					     	<!-- <span id="discount_html"></span> -->
							
							</th>
								<!-- for round off account  -->
							<th class="font-bold" colspan="2">
							
							<input type="hidden"  id="round_ledger_id" value="0">
					     	<!-- <span id="round_html"></span> -->
							
							</th>
							
							<!--  for sunddy creditor  -->
							<th class="font-bold"  colspan="2">
						    <input type="hidden"  id="creditor_ledger_id" value="0">
				         	<!-- <span id="creditor_html"></span> -->
							</th>
						<%-- 	<th class="font-bold"><spring:message code="purinvdet.jsp.totmrp" text="Tot.MRP:" /></th>
							<th style="padding-right: 5px;"><input class="form-control-trx" style="color: #000000;" type="text" id="totgrosmrp" tabindex="-1" readonly value='<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" groupingUsed="false" value="${purchaseReturnDTO.totalMrp}" />'></td>
							<th class="font-bold hide"><spring:message code="purinvdet.jsp.toted" text="Tot.ED:" /></th> --%>
							<th style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="toted" tabindex="-1" readonly value='<fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${purchaseReturnDTO.edAmount}" />'></th>
							<th class="font-bold hide"><spring:message code="purinvdet.jsp.totvat" text="Tot.Vat:" /></th>
							<th style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="totvatamnt" tabindex="-1" readonly value='<fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${purchaseReturnDTO.vatAmount}" />'></th>
							
						
							
						</tr>
						
					<!--  for accounting end  here -->
					
					
						<tr>
							<td class="font-bold"><spring:message code="purinvdet.jsp.itemcount" text="ItemCount:" />:&nbsp;</td>
							<td width="3%" class="font-bold"><span id="totitmcount">0</span></td>
							<td class="font-bold"><spring:message code="purinvdet.jsp.total" text="Total" />:</td>
							<td style="padding-right: 5px;"><input class="form-control-trx" style="color: #000000;" type="text" id="totgrosamnt" tabindex="-1" readonly value='<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" groupingUsed="false" value="${purchaseReturnDTO.grossAmount}" />'></td>
							<%-- <td class="font-bold"><spring:message code="purinvdet.jsp.totmrp" text="Tot.MRP:" /></td> --%>
							<%-- <td style="padding-right: 5px;"><input class="form-control-trx" style="color: #000000;" type="text" id="totgrosmrp" tabindex="-1" readonly value='<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" groupingUsed="false" value="${purchaseReturnDTO.totalMrp}" />'></td> --%>
							<td class="font-bold hide"><spring:message code="purinvdet.jsp.toted" text="Tot.ED:" /></td>
							<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="toted" tabindex="-1" readonly value='<fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${purchaseReturnDTO.edAmount}" />'></td>
							<td class="font-bold hide"><spring:message code="purinvdet.jsp.totvat" text="Tot.Vat:" /></td>
							<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="totvatamnt" tabindex="-1" readonly value='<fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${purchaseReturnDTO.vatAmount}" />'></td>
							<td class="font-bold"><spring:message code="purinvdet.jsp.tottax" text="Tot.Tax:" /></td>
							<td style="padding-right: 5px;"><input class="form-control-trx" type="text" id="tottaxamnt" tabindex="-1" readonly value='<fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${purchaseReturnDTO.taxAmount}" />'></td>
							<td class="font-bold"><spring:message code="purinvdet.jsp.totdisc" text="Tot.Disc:" /></td>
							<td style="padding-right: 5px;"><input class="form-control-trx" type="text" id="totdiscamnt" tabindex="-1" readonly value='<fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${purchaseReturnDTO.discAmount}" />'></td>
		
							<td class="font-bold"><spring:message code="purinvdet.jsp.roff" text="R.Off:" /></td>
							<td style="padding-right: 5px;" width="7%"><input class="form-control-trx" type="text" id="roundoff" tabindex="-1" readonly="readonly" value='<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" groupingUsed="false" value="${purchaseReturnDTO.roundoff}" />'></td>
							
							 
							<td><span style="font-size: 16px; font-weight: bold; color: #d43f3a;"><spring:message code="purinvdet.jsp.nettotal" text="NetTotal:" /></span></td>
							<td width="12%"><input class="form-control-trx" style="font-weight: bold; color: #000000; background-color: #ebccd1;" type="text" id="totnetamnt" tabindex="-1" readonly value='<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" groupingUsed="false" value="${purchaseReturnDTO.netAmount}" />'>
							<input  type="hidden" id="debit_amount" tabindex="-1" readonly value="0.00">
							</td>
							
							 
						</tr>
						
						
						
						<tr>
							<td class="font-bold"><spring:message code="itemmstr.jsp.remarks" text="Remarks" />:</td>
							<td colspan="4" style="padding: 3px 5px 0 0"><input class="form-control-trx" type="text" id="remarks" value="${purchaseReturnDTO.remarks}"></td>
							
							<td class="font-bold" colspan="1">
							
							<input type="hidden"  id="lot_ledger_id" value="0">
					     	<!-- <span id="lot_html"></span> -->
							
							 <spring:message code="purinvdet.jsp.ltadj" text="Lt.Adj" />:</td>
							<td style="padding: 3px 5px 0 0"><input class="form-control-trx" type="text" id="totltadj" tabindex="-1" readonly="readonly" value='<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" groupingUsed="false" value="${purchaseReturnDTO.lotAdjAmount}" />'></td>
							
							 
							 <td class="font-bold"><spring:message code="purinvdet.jsp.totmrp" text="Tot.MRP:" /></td>
							<th style="padding-right: 5px;" width="10%;"><input class="form-control-trx" style="color: #000000;" type="text" id="totgrosmrp" tabindex="-1" readonly value='<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" groupingUsed="false" value="${purchaseReturnDTO.totalMrp}" />'></td>
							
							
							
							<td  style="padding-top: 4px;" colspan="3">
							<button class="btn btn-primary btn-sm hide" type="button"><spring:message code="cmn.jsp.btn.post" text="POST" /></button>
							    <c:if test="${purchaseReturnDTO.isPosted==0}">
								<button style="padding: 5px 8px;margin-left: 6px;" class="btn btn-primary btn-sm" type="button" id="ret_btn"><spring:message code="cmn.jsp.return" text="RETURN" /></button>
								</c:if>
								<button style="padding: 5px 8px;" class="btn btn-info btn-sm" type="button" onclick="newPurRetInv()"><spring:message code="cmn.jsp.new" text="NEW" /></button>
								<button style="padding: 5px 8px;" class="btn btn-danger btn-sm hide" type="button"><spring:message code="cmn.jsp.tblhdr.del" text="DEL" /></button>
								
							</td>
						</tr>
					</table>
					</fieldset>
				</div>
          	</div>
		</section><!--/wrapper -->
		
		<!-- Modal Starts -->
			<div class="modal fade" id="purdetailModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 766px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="purretrn.jsp.purdetfor" text="Purchase Details for" /> 
					<spring:message code="purinvdet.jsp.invno" text="Invoice No" />
					. <span id="searchmodinvno"> PIM/${sessionScope.sesloggedinUser.finyrCode}/</span>
				</h4>
			</div>
			<div class="modal-body">
				<div id="purdetnotfounddiv" class="hide">
					<span class="font-bold"><spring:message code="purretrn.jsp.memonotfoundfornumbermsg" text="No Memo found for this invoice number" />.</span>
				</div>
				<div id="purdetfounddiv">
					<div>
						<span class="font-bold"><spring:message code="rtrnmemo.jsp.salereturnmodal.invdt" text="Invoice Date" />
							: </span> <span id="searchmodinvdate">2017-02-09</span>
					</div>
					<div>
						<span class="font-bold"><spring:message code="rtrnmemo.jsp.salereturnmodal.invamnt" text="Invoice Amount" /> : </span> <span id="searchmodtotamt">100.00</span>
					</div>
					
					<div>
						<span class="font-bold"><spring:message code="printpur.jsp.vendorname" text="Vendor Name" /> :</span> <span
							id="searchmodvendorname">Med Plus</span>
					</div>
				</div>
				<div style="max-height: 300px; height: 200px; overflow: auto;"
					id="purdetmodtable">
					<table id="searchmodtable"
						class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th></th>
								<th><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></th>
								<th><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
								<th><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
								<th><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
								<th><spring:message code="purinvdet.jsp.free" text="Free" /></th>
								<th><spring:message code="purinvdet.jsp.stockqty" text="Stock Qty" /></th>
								<th><spring:message code="purretrn.jsp.preretpqty" text="Pre Ret P.Qty" /></th>
								<th><spring:message code="purretrn.jsp.retpqty" text="Ret P.Qty" /></th>
								<th><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
								<th><spring:message code="purinvdet.jsp.rate" text="Rate" /></th>
								<th class="hide"><spring:message code="purinvdet.jsp.edprcnt" text="Ed%" /></th>
								<th><spring:message code="purinvdet.jsp.taxprcnt" text="Tax%" /></th>
								<th class="hide"><spring:message code="purinvdet.jsp.vatprcnt" text="Vat%" /></th>
								<th><spring:message code="purretrn.jsp.discper" text="Dis%" /></th>
								<th><spring:message code="purretrn.jsp.retamt" text="Ret Amt" /></th>
							</tr>
						</thead>
						<tbody id="searchmodtbody">

						</tbody>
						<div>
							<span id="alertmessagecont"
								style="font-weight: bold; color: red;"> </span>
						</div>

					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" onclick="closePurdetailModal();">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" class="btn btn-primary"
					onclick="getmodretcheckeditemlist()" id="purdetailModal_okbtn">
					<spring:message code="footer.jsp.btn.ok" text="Ok" />
				</button>
			</div>
		</div>
	</div>
</div>

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

<div class="modal fade" id="sameItemInvModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
				<spring:message code="rtrnmemo.jsp.itemalrdyaddedmsg"
					text="Item already added for this invoice." />
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
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
				<spring:message code="purretrn.jsp.returndfrntmemomsg" text="You are trying to return different Vendor/Distributor Memo." />
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
		<!-- Modal Ends -->
<script src="${pageContext.request.contextPath }/assets/js/proc/purreturn/purinvret.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/proc/purreturn/purinvret_en.js"></script>
</c:if>
<script type="text/javascript">
	var BASE_URL = "${pageContext.request.contextPath}";
	var storeId = $("#storeId").val();
	var cmpnyId = $("#companyId").val();
	var createdBy = $("#userid").val();
	var finyrId = $("#finyrId").val();
	function showConfirmModal() {
		$('#confirmMessageModal').modal('show');
	}
	$(document).ready(function() {
		
		var return_id=${purRetId};
		var return_vendor_id=${purchaseReturnDTO.distributorId};
	 
		if (return_id!=0) {

			var tem_net_total=${purchaseReturnDTO.netAmount};
			$('#debit_amount').val(tem_net_total);
			
			getvendorledger($('#dueties_and_tax_acc').val(),0,0,0);// for duties and tax
			getvendorledger($('#roundoff_group_code').val(),0,0,1);// for round off  
			getvendorledger($('#pursaccunt_group_code').val(),0,0,2);// for sale account
			getvendorledger($('#crditor_group_code').val(),0,return_vendor_id,3);// for sunndry creditor 
			getvendorledger($('#dicount_group_code').val(),0,0,4);// for sunndry creditor 
			getvendorledger($('#lot_group_code').val(),0,0,5);// for lot adjasment
		}
		
		
		$("input:text").focus(function() {
			$(this).select();
		});
				
		var itemcount = 0;
		$('#purretitem tbody tr').each(function() {
			itemcount++;
		});
		$("#totitmcount").text(itemcount);
		$('#saleinvtable').DataTable({
			"lengthChange" : false,
		/* "searching": false,
		"pageLength": 12 */
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
			endDate : currentDate,
			autoclose : true,
		});
		//  		$('.dataTables_filter input').attr("placeholder", getCashMemoText.dataTablePlaceHolder);
	});
</script>