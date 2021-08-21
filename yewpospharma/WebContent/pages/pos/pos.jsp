<%@page import="com.sharobi.yewpos.util.CommonResource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/css/glitter/jquery.gritter.css" />
<style>
::-webkit-scrollbar {
    width: 3px;
}
/*#new_sitediv4::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3); 
    border-radius: 10px;
}*/
::-webkit-scrollbar-thumb {
    border-radius: 10px;
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.5); 
}
<!--
.ui-autocomplete {
	overflow-y: scroll; max-height: 250px; width: 300px; word-break: break-all;
}
-->
</style>
<c:set var="today" value="<%=new java.util.Date()%>" />
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12 hide">
			<p></p>
			<input type="hidden" id="isexclusive" value="${sesloggedinStore.isExclusive}" /> <input type="hidden" id="isesi" value="${sesloggedinStore.isEsi}" />
		<input type="hidden" id="isexclusiversp" value="${sesloggedinStore.isExclusive}"/>
		<input type="hidden" id="retamtsamepage" value="0"/>
		<input type="hidden" id="retamtsamepagesaleid" value="${saleId}"/>
		<input type="hidden" id="confirmvalrsp" value="${saleHeaderDTO.allReturnIds}">
		<input type="hidden" id="samepageret" value="1"/>
		<input type="hidden" id="store_name" value="${sesloggedinStore.name}">
		</div>
		<div class="bs-example bs-example-tabs" data-example-id="togglable-tabs">
			<ul class="nav nav-tabs" id="myTabs" role="tablist">
				<li role="presentation" class="active"><a href="#home" id="home-tab" role="tab" data-toggle="tab" aria-controls="home" aria-expanded="true" style="font-weight: bolder;">Cash Memo</a></li>
				<c:if test="${saleHeaderDTO.holdFlag ==0}">
				<li role="presentation" class=""><a href="#profile" role="tab" id="profile-tab" data-toggle="tab" aria-controls="profile" aria-expanded="false" style="font-weight: bolder;">Return Memo</a></li>
				</c:if>
				
			</ul>
			<div class="tab-content" id="myTabContent">
				<div class="tab-pane fade active in" role="tabpanel" id="home" aria-labelledby="home-tab">
					<p>
					<div class="col-lg-12 col-md-12 col-sm-12" id="header_filter5">
						<div class="panel-trx panel-default">
							<div class="panel-body-trx">
								<table>
									<c:if test="${saleId==0}">
										<input type="hidden" id="esiTypeId" value="0" />
										<input type="hidden" id="esiCodeId" value="0" />
										<tr align="center" style="font-weight: bold;">
											<td><spring:message code="pos.jsp.date" text="Date" /></td>
											<td colspan="2"><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></td>
											<td class="hide"><spring:message code="purinvdet.jsp.discprcnt" text="Disc%" /></td>
											<td><spring:message code="pos.jsp.custContact" text="Cust.Contact" /></td>
											<td><spring:message code="pos.jsp.custName" text="Cust. Name" /></td>
											<td><spring:message code="pos.jsp.custAddr" text="Cust. Addr." /></td>
											<td class="add_td hide"></td>
											<td id="prebilltext"></td>
											<td><spring:message code="pos.jsp.dctrName" text="Doc. Name" /></td>
										</tr>

										<tr>
											<td style="padding: 0 1px;">
												<div class="input-group">
													<input type="text" class="form-control-trx" id="date" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
												</div>
											</td>
											<td width="8%" style="padding: 0 1px;"><input class="form-control-trx" type="text" value="SIM/${sessionScope.sesloggedinUser.finyrCode}/" readonly></td>
											<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="saleinvno" readonly="readonly" value=""><input type="hidden" id="saleId" value="0"></td>
											<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" id="salediscount" value="${sessionScope.sesloggedinUser.vatPer}" type="text"></td>
											<td style="padding: 0 1px;"><input class="form-control-trx" id="salecustph" type="text"><input type="hidden" id="salecustid" value="0"><input type="hidden" id="custCreditLimit" /><input type="hidden" id="custEmail" /></td>
											<td width="10%" style="padding: 0 1px;"><input class="form-control-trx" id="salecustname" type="text"></td>
											<td width="25%" style="padding: 0 1px;"><input class="form-control-trx" id="salecustaddr" type="text"></td>
											<td id="add_cust_td" class="hide">
												<button style="margin-right: 2px;" class="btn btn-primary" type="button" onclick="openAddModal('cust')">
													<i class="fa fa-plus"></i>
												</button>
											</td>
											<td id="prebilltd"><button class="btn btn-primary btn-sm" type="button" onclick="getCustPreviousBill(document.getElementById('salecustph').value)">
													<i class="fa fa-search" aria-hidden="true"></i>
													<spring:message code="pos.jsp.prevbill" text="Prev Bill" />
												</button></td>
											<td width="15%" style="padding: 0 1px;"><input class="form-control-trx" id="saledocname" type="text"><input type="hidden" id="saledocid" value="0"></td>
											<td id="add_doc_td" class="hide">
												<button class="btn btn-primary" type="button" onclick="openAddModal('doc')">
													<i class="fa fa-plus"></i>
												</button>
											</td>
										</tr>
										<c:if test="${sesloggedinStore.isEsi==1}">
											<tr align="center" style="font-weight: bold;">
												<td><spring:message code="pos.jsp.type" text="Type" /></td>
												<td colspan="2"><spring:message code="pos.jsp.pissdt" text="Pres. Issue Dt" /></td>
												<td><spring:message code="pos.jsp.inscardNo" text="Ins/Card No." /></td>
												<td><spring:message code="pos.jsp.rslno" text="Req. Serial No." /></td>
												<td id="blacktext_td" class="hide"></td>
												<td id="codetextid"><spring:message code="pos.jsp.code" text="Code" /></td>
												<td id="pregtextid"><spring:message code="pos.jsp.pregno" text="Pres. Reg. No" /></td>
											</tr>
											<tr align="center" style="font-weight: bold;">
												<td><select class="form-control-trx" name="esitype" id="esitypeid" onchange="getesitype()">
														<option value="0">Select</option>
														<c:if test="${!empty typeMasters}">
															<c:forEach items="${typeMasters}" var="typeMaster">
																<option value="${typeMaster.id}">${typeMaster.typeName}</option>
															</c:forEach>
														</c:if>
												</select></td>
												<td></td>
												<td><input type="text" style="margin-left: -71%;" class="form-control-trx" id="pissuedt" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />"></td>
												<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="ecardno" value="" readonly></td>
												<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="reqslno" value=""></td>
												<td id="black_td" class="hide"></td>
												<td style="padding: 0 1px;" id="codevalid"><select class="form-control-trx" name="pcode" id="pcodeid" onchange="getpode()">
														<option value="0">Select</option>
														<c:if test="${!empty esiCodeMasters}">
															<c:forEach items="${esiCodeMasters}" var="esiCodeMaster">
																<option value="${esiCodeMaster.id}">${esiCodeMaster.code}</option>
															</c:forEach>
														</c:if>
												</select></td>
												<td style="padding: 0 1px;" id="pregvalid"><input class="form-control-trx" type="text" id="prregno" value=""></td>

											</tr>
										</c:if>
									</c:if>
									<c:if test="${saleId!=0}">
										<tr align="center" style="font-weight: bold;">
											<td><spring:message code="pos.jsp.date" text="Date" /></td>
											<td><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></td>
											<td class="hide"><spring:message code="purinvdet.jsp.discprcnt" text="Disc%" /></td>
											<td><spring:message code="pos.jsp.custContact" text="Cust.Contact" /></td>
											<td><spring:message code="pos.jsp.custName" text="Cust. Name" /></td>
											<td><spring:message code="pos.jsp.custAddr" text="Cust. Addr." /></td>
											<td><spring:message code="pos.jsp.dctrName" text="Doc. Name" /></td>
											<td></td>
										</tr>
										<tr>
											<td style="padding: 0 1px;">
												<div class="input-group">
													<fmt:parseDate value="${saleHeaderDTO.invDate}" var="salesInvDate" pattern="MMM dd, yyyy" />
													<input type="text" class="form-control-trx" id="date" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${salesInvDate}" />" readonly>
												</div>
											</td>
											<td width="8%" style="padding: 0 1px;" class="hide"><input class="form-control-trx " type="text" id="saleId" value="${saleHeaderDTO.saleId}" readonly></td>
											<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="saleinvno" value="${saleHeaderDTO.invNo}" readonly></td>
											<td style="padding: 0 1px;" class="hide"><input class="form-control-trx" type="text" id="salediscount" value="${saleHeaderDTO.customerDiscPer}" readonly></td>
											<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="salecustph" value="${saleHeaderDTO.customerPhone}"> <input type="hidden" id="salecustid" value="${saleHeaderDTO.customerId}"><input type="hidden" id="custCreditLimit" value="${saleHeaderDTO.creditLimit}" /></td>
											<td width="10%" style="padding: 0 1px;"><input class="form-control-trx" type="text" id="salecustname" value="${saleHeaderDTO.customerName}"></td>
											<td width="25%" style="padding: 0 1px;"><input class="form-control-trx" id="salecustaddr" type="text" value="${saleHeaderDTO.customerAddress}"></td>
											<td width="15%" style="padding: 0 1px;"><input class="form-control-trx" type="text" id="saledocname" value="${saleHeaderDTO.doctorName}"> <input type="hidden" id="saledocid" value="${saleHeaderDTO.doctorId}"></td>
											<td></td>
											<td id="add_doc_td" class="hide">
												<button class="btn btn-primary" type="button" onclick="openAddModal('doc')">
													<i class="fa fa-plus"></i>
												</button>
											</td>
										</tr>
										<c:if test="${sesloggedinStore.isEsi==1}">
											<input type="hidden" id="esiTypeId" value="${saleHeaderDTO.esiType}" />
											<input type="hidden" id="esiCodeId" value="${saleHeaderDTO.esiCode}" />
											<tr align="center" style="font-weight: bold;">
												<td><spring:message code="pos.jsp.type" text="Type" /></td>
												<td><spring:message code="pos.jsp.pissdt" text="Pres. Issue Dt" /></td>
												<td><spring:message code="pos.jsp.inscardNo" text="Ins/Card No." /></td>
												<td><spring:message code="pos.jsp.rslno" text="Req. Serial No." /></td>
												<td id="codetextid"><spring:message code="pos.jsp.code" text="Code" /></td>
												<td id="pregtextid"><spring:message code="pos.jsp.pregno" text="Pres. Reg. No" /></td>
											</tr>
											<tr align="center" style="font-weight: bold;">
												<td><select class="form-control-trx" name="esitype" id="esitypeid" onchange="getesitype()">
														<option value="0">Select</option>
														<c:if test="${!empty typeMasters}">
															<c:forEach items="${typeMasters}" var="typeMaster">
																<option value="${typeMaster.id}">${typeMaster.typeName}</option>
															</c:forEach>
														</c:if>
												</select></td>
												<fmt:parseDate value="${saleHeaderDTO.prescriptionIssueDate}" var="pDate" pattern="MMM dd, yyyy" />
												<td style="padding: 0 1px;"><input type="text" readonly="readonly" class="form-control-trx" id="pissuedt" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${pDate}" />"></td>
												<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="ecardno" value="${saleHeaderDTO.customerCode}" readonly></td>
												<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="reqslno" value="${saleHeaderDTO.slipNo}"></td>
												<td style="padding: 0 1px;" id="codevalid"><select class="form-control-trx" name="pcode" id="pcodeid" onchange="getpode()">
														<option value="0">Select</option>
														<c:if test="${!empty esiCodeMasters}">
															<c:forEach items="${esiCodeMasters}" var="esiCodeMaster">
																<option value="${esiCodeMaster.id}">${esiCodeMaster.code}</option>
															</c:forEach>
														</c:if>
												</select></td>
												<td style="padding: 0 1px;" id="pregvalid"><input class="form-control-trx" type="text" id="prregno" value="${saleHeaderDTO.prescriptionRegNo}"></td>

											</tr>
										</c:if>
									</c:if>
								</table>
							</div>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12" id="header_filter6">
						<div class="panel-trx panel-default">
							<div class="panel-body-trx" id="header_div">
								<table>
									<tr align="center" style="font-weight: bold;">
										<td><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></td>
										<td>Barcode</td>
										<td><spring:message code="pos.jsp.generic" text="Generic" /></td>
										<td id="pqty_label"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></td>
										<td id="lqty_label"><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></td>
										<td id="sale_rate_label">Rate</td>
										<td id="dis_label"><spring:message code="purinvdet.jsp.discprcnt" text="D%" /></td>
										<td><spring:message code="purinvdet.jsp.batch" text="Batch" /></td>
										<td><spring:message code="purinvdet.jsp.expdt" text="Exp" /></td>
										<td><spring:message code="pos.jsp.rateLs" text="Rate/Ls" /></td>
										<td class="hide"><spring:message code="purinvdet.jsp.vatprcnt" text="Vat%" /></td>
										<td><spring:message code="purinvdet.jsp.total" text="Total" /></td>

									</tr>
									<tr>
										<td width="20%" style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_name" placeholder="Please type atleast 2 characters"> <input type="hidden" id="item_id" value="0"></td>
										<td width="12%"><input class="form-control-trx" type="text" id="item_barcode" placeholder="Scan Barcode"></td>
										<td width="20%" style="padding: 0 1px;"><input type="hidden" id="content_id" value=""></input> <input type="hidden" id="content_Dets" value=""></input><input type="text" id="itemContent" value="" class="form-control-trx" placeholder="Please type atleast 2 characters"></td>
										<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_pqty" value="0"> <input type="hidden" id="item_packunitid" value="0"></td>
										<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_lqty" value="0"><input type="hidden" id="item_looseunitid" value="0"> <input type="hidden" id="item_stockedqty" value="0"></td>
										<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_sale_rate"><input class="form-control-trx" type="hidden" id="item_purChase_rate"></td>
										<td style="padding: 0 1px;" id="item_dis_td"><input class="form-control-trx" type="text" id="item_dis" value="0.0000"><input type="hidden" id="item_discamt" value="0"></td>
										<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_batch" tabindex="-1" readonly></td>
										<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_exp" tabindex="-1" readonly></td>
										<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_rate_ls" tabindex="-1" readonly><input type="hidden" id="item_rate_ls_hid" value="0"></td>
										<td style="padding: 0 1px;" class="hide"><input class="form-control-trx " type="text" id="item_vat" value="${sessionScope.sesloggedinUser.vatPer}" readonly><input type="hidden" id="item_vatamt" value="0"><input class="form-control-trx " type="text" id="item_tax" value="${sessionScope.sesloggedinUser.taxPer}" readonly><input type="hidden" id="item_taxamt" value="0"></td>
										<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_tot" value="0" tabindex="-1" readonly></td>

									</tr>
									<tr align="center" style="font-weight: bold;">
										<td><spring:message code="purinvdet.jsp.mfg" text="Mfg" />.</td>
										<td colspan="2"><spring:message code="itemmstr.jsp.content" text="Content" /></td>
										<td><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></td>
										<td><spring:message code="pos.jsp.mrpPack" text="MRP/Pack" /></td>
										<td><spring:message code="purinvdet.jsp.mrp" text="MRP" /></td>
										<td class="hide"><spring:message code="itemmstr.jsp.schedule" text="Schedule" /></td>
									</tr>
									<tr>
										<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_mfg" tabindex="-1" readonly></td>
										<td colspan="2" style="padding: 0 1px;"><input class="form-control-trx" id="item_content" title="sdfgsdfg" type="text" tabindex="-1" readonly></td>
										<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_conv" tabindex="-1" readonly></td>
										<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_mrp_pack" tabindex="-1" readonly></td>
										<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_mrp" tabindex="-1" readonly></td>

										<td class="hide"><input type="hidden" id="item_sche"><input type="hidden" id="item_grp"><input type="hidden" id="item_taxId"><input type="hidden" id="item_taxPercentage"><input type="hidden" id="item_isGroupTax"><input type="hidden" id="item_CalcTaxAmt" value="0"><input type="hidden" id="item_discount"><input type="hidden" id="item_isDiscount"><input type="hidden" id="item_maxDiscountLimit" value="0"><input type="hidden" id="item_taxMode" value="0"><input type="hidden" id="item_hsnCode" value="0"><input type="hidden" id="item_purCost" value="0"><input type="hidden" id="item_purCostperUnit" value="0"></td>
										<td colspan="2">
											<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
										</td>
										<c:if test="${saleHeaderDTO.isPosted !=1}">
											<c:if test="${saleHeaderDTO.holdFlag ==0}">
												<td><button class="btn btn-success btn-sm" type="button" onclick="addOrUpdateItemToDetailsTable(0)" id="add_btn">
														<spring:message code="cmn.jsp.addcaps" text="ADD" />
													</button>
													<button class="btn btn-success btn-sm hide" style="text-transform: uppercase;" type="button" onclick="addOrUpdateItemToDetailsTable(1)" id="edit_btn">
														<spring:message code="cmn.jsp.btn.update" text="UPDATE" />
													</button></td>
												<td><button class="btn btn-primary btn-sm" type="button" onclick="clearHeaderDiv()">
														<spring:message code="cmn.jsp.btn.clear" text="CLEAR" />
													</button></td>
											</c:if>
										</c:if>

									</tr>
								</table>
							</div>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div style="overflow: auto; height:240px;" id="detail_table8">
							<table id="selitem" style="margin-bottom: 5px;" class="table table-bordered table-striped table-condensed-trx table-hover">
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
										<th class="numeric">Amt</th>
										<th class="numeric"><spring:message code="pos.jsp.rateLs" text="Rate/Ls" /></th>
										<th class="numeric hide"><spring:message code="purinvdet.jsp.vatprcnt" text="VAT%" /></th>
										<th class="numeric"><spring:message code="purinvdet.jsp.taxprcnt" text="TAX%" /></th>
										<th class="numeric"><spring:message code="purinvdet.jsp.disc" text="Disc" />%</th>
										<th class="numeric"><spring:message code="pos.jsp.netamnt" text="Net Amt" /></th>
										<th class="numeric"><spring:message code="purinvdet.jsp.dltbtn" text="Del." /></th>
									</tr>
								</thead>
								<tbody id="saletabitemdetails">
									<%-- 						<c:if test="${saleId!=0}"> value="${saleDetail.totAmount}"--%>
									<c:if test="${!empty saleDetailsDTOs }">
										<c:forEach items="${saleDetailsDTOs}" var="saleDetail">
											<c:choose>
												<c:when test="${saleDetail.scheduleName=='H1' || saleDetail.scheduleName == 'X'}">
													<tr id="${saleDetail.itemUniqueKey}" class='schx' style='cursor: pointer;' onclick="javascript:itemHeaderDivView(this.id);">
												</c:when>
												<c:otherwise>
													<c:choose>
														<c:when test="${saleDetail.groupName=='FOOD' || saleDetail.groupName == 'Food'}">
															<tr id="${saleDetail.itemUniqueKey}" class='foodx' style='cursor: pointer;' onclick="javascript:itemHeaderDivView(this.id);">
														</c:when>
														<c:otherwise>
															<tr id="${saleDetail.itemUniqueKey}" style='cursor: pointer;' onclick="javascript:itemHeaderDivView(this.id);">
														</c:otherwise>
													</c:choose>
												</c:otherwise>
											</c:choose>
											
											

											<td id="saletabname">${saleDetail.itemName}</td>
											<td id="saletabbat">${saleDetail.batchNo}</td>
											<td id="saletabexpdt">${saleDetail.expiryDateFormat}</td>
											<td id="saletabmanname">${saleDetail.manufacturerName}</td>
											<td class="numeric" id="saletabpqty">${saleDetail.packQty}</td>
											<td class="numeric" id="saletablqty">${saleDetail.looseQty}</td>
											<td class="numeric" id="saletabconv">${saleDetail.conversion}</td>
											<td class="numeric" id="saletabmrppack"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleDetail.mrp}" /></td>
											<td class="numeric" id="saletabmrp"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${((saleDetail.packQty*saleDetail.conversion)+saleDetail.looseQty)*saleDetail.mrpPerUnit}" /></td>
											<td class="numeric" id="saletabamt"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${((saleDetail.packQty*saleDetail.conversion)+saleDetail.looseQty)*saleDetail.ratePerUnit}" /></td>
											<td class="numeric" id="saletabrateperunit"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleDetail.ratePerUnit}" /></td>
											<td class="numeric hide" id="saletabvatperc"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleDetail.vatPer}" /></td>
											<td id="saletabtaxPercentage" class="numeric"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleDetail.taxPercentage}" /></td>
											<td class="numeric" id="saletabdiscperc"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleDetail.discPer}" /></td>
											<td class="numeric" id="saletabtotamt"><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${saleDetail.totAmount}" /></td>
											<td><c:if test="${saleHeaderDTO.isPosted !=1}">
													<c:if test="${saleHeaderDTO.holdFlag ==0}">
														<button class="btn btn-theme04 btn-xs" id="${saleDetail.itemUniqueKey}" onclick='javascript:showSelTabItemDelModal(this.id);'>
															<i class="fa fa-trash-o "></i>
														</button>
													</c:if>
												</c:if></td>
											<td id="saletabitembarcode" class="hide">${saleDetail.sku}</td>
											<td id="saletabpunitid" class="hide">${saleDetail.packUnitId}</td>
											<td id="saletablunitid" class="hide">${saleDetail.looseUnitId}</td>
											<td id="saletabvat" class="hide">${saleDetail.vat}</td>
											<td id="saletabdisc" class="hide">${saleDetail.disc}</td>
											<td id="saletabcontent" class="hide">${saleDetail.contentName}</td>
											<td id="saletabitemstkqty" class="hide">${saleDetail.itemId}</td>
											<td id="saletabmrpperunit" class="hide">${saleDetail.mrpPerUnit}</td>
											<td id="saletabrate" class="hide">${saleDetail.rate}</td>
											<td id="saletabschename" class="hide">${saleDetail.scheduleName}</td>
											<td id="saletabtaxperc" class="hide">${saleDetail.taxPer}</td>
											<td id="saletabtax" class="hide">${saleDetail.tax}</td>
											<td id="saletabcontentid" class="hide">${saleDetail.contentId}</td>
											<td id="saletabtaxId" class="hide">${saleDetail.taxId}</td>

											<td id="saletabisGroupTax" class="hide">${saleDetail.isGroupTax}</td>
											<td id="saletabdiscount" class="hide">${saleDetail.discount}</td>
											<td id="saletabisDiscount" class="hide">${saleDetail.isDiscount}</td>
											<td id="saletabmaxDiscountLimit" class="hide">${saleDetail.maxDiscountLimit}</td>
											<td id="saletabitemcalcgstamt" class="hide">${saleDetail.taxAmount}</td>
											<td id="saletabitemtaxmode" class="hide">${saleDetail.taxMode}</td>
											<td id="saletabitemhsncode" class="hide">${saleDetail.hsnCode}</td>
											<td id='saletabpurcost' class='hide'><fmt:formatNumber type="number" maxFractionDigits="4" minFractionDigits="4" groupingUsed="false" value="${((saleDetail.packQty*saleDetail.conversion)+saleDetail.looseQty)*saleDetail.purchaseCostPerUnit}" /></td>
											<td id='saletabpurcostperunit' class='hide'>${saleDetail.purchaseCostPerUnit}</td>
											<td id='saletabitemsalerate' class='hide'>${saleDetail.saleRate}</td>
											<%-- 								<td id='saletabitempurrate' class='hide'>${saleDetail.purchaseRate}</td> --%>
											</tr>
										</c:forEach>
									</c:if>
									<%-- 						</c:if> --%>
								</tbody>
							</table>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
						<p></p>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12" id="footer_detail7">
					<!-- add for account  -->
									 
											
				 
					                           <input type="hidden" id="debitor_code1" value="<spring:message code="accgroup.jsp.deb_code" text="SDE" />">
											  <input type="hidden" id="saleac_code1" value="<spring:message code="accgroup.jsp.saleac_code" text="SA" />">
											 <input type="hidden" id="dutiesandtax_code1" value="<spring:message code="accgroup.jsp.duties_code" text="DT" />">
											 <input type="hidden" id="dicount_code1" value="<spring:message code="accgroup.jsp.disc_code" text="DIS" />">
											 <input type="hidden" id="roundof_code1" value="<spring:message code="accgroup.jsp.roun_code" text="ROD" />">
											  <input type="hidden" id="cash_code1" value="<spring:message code="accgroup.jsp.cash_code" text="CIH" />">
											  <input type="hidden" id="card_code1" value="<spring:message code="accgroup.jsp.card_code" text="CAB" />">
											  <input type="hidden"  id="sales_ledger_id1" value="0">
											 <input type="hidden"  id="duties_ledger_id1" value="0">
											 <input type="hidden"  id="discount_ledger_id1" value="0">
											 <input type="hidden"  id="round_ledger_id1" value="0">
											 <input type="hidden"  id="debitor_ledger_id1" value="0">	
											 <input type="hidden"  id="debitor_cahs_ledger_id1" value="0">	
											 <input type="hidden"    id="card_ledger_id1" value="0">
						<table>
							<tr>
								<td class="font-bold"><spring:message code="purinvdet.jsp.itemcount" text="ItemCount" />:</td>
								<td width="2%" class="font-bold"><span id="totitmcount"></span></td>
								<%-- <td class="font-bold"><spring:message code="purinvdet.jsp.remarks" text="Remarks:" /></td>
					<td style="padding-right: 5px;"><input class="form-control-trx" id="remarks" type="text" value="${saleHeaderDTO.remarks}"></td> --%>
								<td class="font-bold"><spring:message code="purinvdet.jsp.total" text="Total:" />:</td>
								<td style="padding-right: 5px;"><input class="form-control-trx" style="color: #000000;" type="text" id="totgrossamt" value="${saleHeaderDTO.grossAmount}" tabindex="-1" readonly></td>
								<td class="font-bold">Tot.MRP:</td>
								<td style="padding-right: 5px;"><input class="form-control-trx" style="color: #000000;" type="text" id="totmrpamt" value="${saleHeaderDTO.totalMrp}" tabindex="-1" readonly></td>
								<td class="font-bold hide"><spring:message code="purinvdet.jsp.totvat" text="Tot.Vat:" /></td>
								<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="totvatamt" value="${saleHeaderDTO.vatAmount}" tabindex="-1" readonly></td>
								<td class="font-bold hide"><spring:message code="pos.jsp.stax" text="Tot.Tax" /></td>
								<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="tottaxamt" value="${saleHeaderDTO.taxAmount}" tabindex="-1" readonly></td>
								<td class="font-bold "><spring:message code="pos.jsp.stax" text="Tot.Tax" /></td>
								<td style="padding-right: 5px;"><input class="form-control-trx" type="text" id="totgstamt" value="${saleHeaderDTO.taxAmount}" tabindex="-1" readonly></td>
								<td class="font-bold"><spring:message code="purinvdet.jsp.totdisc" text="Tot.Disc:" /></td>
								<td style="padding-right: 5px;"><input class="form-control-trx" type="text" id="totdiscamt" value="${saleHeaderDTO.discAmount}" tabindex="-1" readonly></td>
								<td class="font-bold">ProfitAmt:</td>
								<td style="padding-right: 5px;"><input class="form-control-trx" type="text" id="profitperc" value="" readonly="readonly"></td>
								<td class="font-bold"><spring:message code="purinvdet.jsp.roff" text="R.Off:" /></td>
								<td style="padding-right: 5px;"><input class="form-control-trx" type="text" id="roundoff" value="${saleHeaderDTO.roundoff}" tabindex="-1" readonly="readonly"></td>
								<td><span style="font-size: 16px; font-weight: bold; color: #d43f3a;"><spring:message code="purinvdet.jsp.nettotal" text="NetTotal:" /></span></td>
								<td width="8%"><input class="form-control-trx" style="font-weight: bold; color: #000000; background-color: #ebccd1;" type="text" id="nettot" value="${saleHeaderDTO.netAmount}" tabindex="-1" readonly></td>
							</tr>
							<tr>

								<td class="font-bold" id="remarkstext"><spring:message code="purinvdet.jsp.remarks" text="Remarks:" /></td>
								<td colspan="3" style="padding: 3px 5px 0 0"><input class="form-control-trx" id="remarks" type="text" value="${saleHeaderDTO.remarks}"></td>
								<td colspan="4" class="font-bold hide"><c:if test="${saleId!=0}">
										<c:if test="${saleHeaderDTO.invMode==1}">
											<input type="radio" id="payOption" name="payOption" value="1" checked="checked">&nbsp; <spring:message code="pos.jsp.cash" text="Cash" />
										</c:if>
										<c:if test="${saleHeaderDTO.invMode==2}">
											<input type="radio" id="payOption" name="payOption" value="2" checked="checked">&nbsp; <spring:message code="pos.jsp.card" text="Card" />
										</c:if>
										<c:if test="${saleHeaderDTO.invMode==3}">
											<input type="radio" id="payOption" name="payOption" value="3" checked="checked">&nbsp; <spring:message code="pos.jsp.credit" text="Credit" />
										</c:if>
									</c:if> <c:if test="${saleId==0}">
										<input type="radio" id="payOption" name="payOption" value="1" checked="checked">&nbsp; <spring:message code="pos.jsp.cash" text="Cash" />
										<input type="radio" id="payOption" name="payOption" value="2">&nbsp; <spring:message code="pos.jsp.card" text="Card" />
										<input type="radio" id="payOption" name="payOption" value="3">&nbsp; <spring:message code="pos.jsp.credit" text="Credit" />
									</c:if></td>

								<td class="font-bold"><spring:message code="pos.jsp.spldscprcnt" text="SplDisc(%)" />:</td>
								<td style="padding-right: 5px;"><input class="form-control-trx" type="text" id="spcldiscperc" value="${saleHeaderDTO.specialDiscPer}" onkeyup="calculateSpclDisc()"></td>
								<td class="font-bold"><spring:message code="pos.jsp.spldscamnt" text="Spl.Disc.Amt" />:</td>
								<td style="padding-right: 5px;"><input class="form-control-trx" tabindex="-1" type="text" id="spcldisc" value="${saleHeaderDTO.specialDiscAmount}" onkeyup="calculateSpclDiscPrcnt()"></td>
								 <c:if test="${saleId==0}">
								<td class="font-bold">Ret Amt:</td>
								<td style="padding-right: 5px;"><input class="form-control-trx" readonly="readonly" tabindex="-1" type="text" id="retamtvalsamepage" value="0"></td>
								</c:if>
								
								 <c:if test="${saleId!=0&&saleHeaderDTO.holdFlag ==0}">
								<td class="font-bold">Ret Amt:</td>
								<td style="padding-right: 5px;"><input class="form-control-trx" readonly="readonly" tabindex="-1" type="text" id="retamtvalsamepage" value="${saleHeaderDTO.totalReturnAmount}"></td>
								</c:if>
								
								<%-- <td class="font-bold">RefundAmt:</td>
					<td style="padding-right: 5px;"><input class="form-control-trx" type="text" id="refundAmt" value="" readonly="readonly"></td>
					<td class="font-bold">CashAmt:</td>
					<td style="padding-right: 5px;"><input class="form-control-trx" type="text" id="cashAmt" value="${saleHeaderDTO.cashAmount}"></td>
					<td class="font-bold">CreditAmt:</td>
					<td style="padding-right: 5px;"><input class="form-control-trx" type="text" id="creditAmt" value="${saleHeaderDTO.creditAmount}" readonly="readonly"></td>
					<td class="font-bold">CardAmt:</td>
					<td style="padding-right: 5px;"><input class="form-control-trx" type="text" id="cardAmt" value="${saleHeaderDTO.cardAmount}" readonly="readonly"></td>
					<td style="padding-top: 4px; padding-right: 4px;"><button class="btn btn-primary btn-sm" style="padding: 5px 2px;" type="button" onclick="openCardModal()">CARD</button></td>--%>
								<c:if test="${saleHeaderDTO.isPosted !=1}">

									<td colspan="5" style="padding-top: 4px; padding-right: 4px;">
										<button style="padding: 5px 8px;" class="btn btn-info btn-sm" type="button" onclick="openNewCashMemo()">
											<spring:message code="cmn.jsp.new" text="NEW" />
										</button>
										<%-- <c:if test="${sesloggedinStore.isMailEnable==1}">
											<button style="padding: 5px 8px; text-transform: uppercase;" class="btn btn-success btn-sm"  id="mail_btn" type="button" onclick="openMailModal();">
												<spring:message code="cmn.jsp.btn.mail" text="MAIL" />
											</button>
										</c:if> --%>
										 <c:if test="${saleHeaderDTO.holdFlag ==0}">
											<button class="btn btn-primary btn-sm" style="padding: 5px 30px;" type="button" id="payButtId" onclick="paySaleInvModal()">
												<spring:message code="pos.jsp.payBtn" text="PAY" />
											</button>
										</c:if> <c:if test="${saleHeaderDTO.holdFlag ==1}">
											<button class="btn btn-success btn-sm" style="padding: 5px 30px;" type="button" onclick="paySaleInv()">
												<spring:message code="pos.jsp.payBtnUpdate" text="UPDATE" />
											</button>
											<button class="btn btn-primary btn-sm" style="padding: 5px 30px;" type="button" onclick="paySaleInvModal1()">
												<spring:message code="pos.jsp.payBtnDet" text="PAY DET." />
											</button>
										</c:if> <c:if test="${saleHeaderDTO.holdFlag ==0}">
											<c:if test="${saleId!=0}">
												<button style="padding: 5px 8px;" class="btn btn-danger btn-sm" type="button" id="delButtId" onclick="deleteSalesInv()">
													<spring:message code="cmn.jsp.tblhdr.del" text="DEL" />
												</button>
											</c:if>
											<button style="padding: 5px 30px; text-transform: uppercase;" id="holdButtId" class="btn btn-success btn-sm" type="button" onclick="saveCashmemo()">
												<c:if test="${saleId==0}">
													<spring:message code="cmn.jsp.btn.hold" text="HOLD" />
												</c:if>
												<c:if test="${saleId!=0}">
													<spring:message code="cmn.jsp.btn.hold" text="HOLD" />
												</c:if>
											</button>
										</c:if>
									</td>
								</c:if>
								<c:if test="${saleHeaderDTO.isPosted ==1}">
									<c:if test="${saleHeaderDTO.holdFlag ==1}">
										<td colspan="4" style="padding-top: 4px; padding-right: 4px;">
											<button class="btn btn-primary btn-sm" style="padding: 5px 30px;" type="button" onclick="paySaleInvModal1()">
												<spring:message code="pos.jsp.payBtnDet" text="PAY DET." />
											</button>
										</td>
									</c:if>
								</c:if>
							</tr>
						</table>
					</div>
					</p>
				</div>
				<div class="tab-pane fade" role="tabpanel" id="profile" aria-labelledby="profile-tab">
					<p>
					<div class="col-lg-12 col-md-12 col-sm-12" id="header_filter11">
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
									<div class="col-sm-12 hide" style="text-align: center; font-weight: 700;">
										<spring:message code="rtrnmemo.jsp.printsalereturn" text="Print Sale Return" />
										: <input type="checkbox" id="printSaleReturn" name="printSaleReturn" style="zoom: 1.5; vertical-align: middle; margin: 0px;">
									</div>
								</div>
								
								<input type="hidden" id="confirmvalrspsamepage" value="0">
							</div>
							<div class="modal-footer" style="border-top: 0px;">
								<button type="button" onclick="targetURLrspsamepage()" data-dismiss="modal" class="btn btn-theme">
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

								<td style="padding: 0 1px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retmemoDoc" value="SIM/" size="4" readonly></td>
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
								<td colspan="4"><spring:message code="purinvdet.jsp.billno" text="Bill No" /></td>
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
		<div class="col-lg-12 col-md-12 col-sm-12" id="header_filter22">
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
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_rpqty_rsp" value="0"></td>
							<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="item_rlqty_rsp" value="0"></td>
							
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
			<div style="overflow: auto;height:250px;" id="detail_table1">
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
		<div class="col-lg-12 col-md-12 col-sm-12" id="footer_detail1">
				
				<!-- <fieldset class="col-lg-12 col-md-12 col-sm-12"> -->
				<input type="hidden" id="dueties_and_tax_acc" value="<spring:message code="accgroup.jsp.duties_code" text="DT"/>">
		<input type="hidden" id="saleaccunt_group_code" value="<spring:message code="accgroup.jsp.saleac_code" text="SA"/>">
		<input type="hidden" id="roundoff_group_code" value="<spring:message code="accgroup.jsp.roun_code" text="ROD"/>">
		<input type="hidden" id="debitor_group_code" value="<spring:message code="accgroup.jsp.deb_code" text="SDE"/>">
		<input type="hidden" id="cash_in_hand_group_code" value="<spring:message code="accgroup.jsp.cash_code" text="CIH"/>">
			<input type="hidden"  id="duties_ledger_id" value="0">
			<input type="hidden"  id="round_ledger_id" value="0">
			 <input type="hidden"  id="sales_ledger_id" value="0">
			  <input type="hidden"  id="debitor_ledger_id" value="0"> 
			 <input class="form-control-trx" type="hidden" id="sale_account_rsp"  readonly value="0">
			  <input class="form-control-trx" type="hidden" id="debitor_amt_rsp" readonly value="0">
			  
			<table>
				<tr>


				</tr>
				<tr>
					<td class="font-bold"><spring:message code="purinvdet.jsp.itemcount" text="ItemCount" />:</td>
					<td width="2%" class="font-bold"><span id="totitmcount_rsp">1</span></td>

					<td class="font-bold hide"><spring:message code="purinvdet.jsp.total" text="Total:" />:</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" style="color: #000000;" type="text" id="totgrossamt_rsp" value="${saleReturnDTO.grossAmount}" readonly></td>
					<td class="font-bold hide"><spring:message code="purinvdet.jsp.totvat" text="Tot.Vat:" /></td>
					<td style="padding-right: 5px;" class="hide" ><input class="form-control-trx" type="text" id="totvatamt_rsp" value="${saleReturnDTO.vatAmount}" tabindex="-1" readonly></td>
					<td class="font-bold "><spring:message code="pos.jsp.stax" text="Tot.Tax" /></td>
					<td style="padding-right: 5px;"><input class="form-control-trx" type="text" id="tottaxamt_rsp" value="${saleReturnDTO.taxAmount}" tabindex="-1" readonly></td>
					<td class="font-bold hide"><spring:message code="purinvdet.jsp.totdisc" text="Tot.Disc:" />:</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="" value="" readonly></td>

					
					<td class="font-bold"><spring:message code="purinvdet.jsp.roff" text="R.Off:" />:</td>
					<td style="padding-right: 5px;" width="8%"><input class="form-control-trx" type="text" id="roundoff_rsp" value="<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" groupingUsed="false" value="${saleReturnDTO.roundoff}" />" readonly="readonly"></td>
					<td><span style="font-size: 16px; font-weight: bold; color: #d43f3a;"><spring:message code="rtrnmemo.jsp.retamt" text="Ret.Amt" />:</span></td>
					<td width="8%" style="padding-right: 5px;"><input class="form-control-trx" style="font-weight: bold; color: #000000; background-color: #ebccd1;" type="text" id="totretamt_rsp" value="<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" groupingUsed="false" value="${saleReturnDTO.netAmount}" />" readonly></td>
					
					

				</tr>
				<tr>
					<td class="font-bold"><spring:message code="rtrnmemo.jsp.reason" text="Reason"></spring:message>:</td>
					<td colspan="3" style="padding: 3px 5px 0 0"><input class="form-control-trx" id="remarks_rsp" type="text" value="${saleReturnDTO.remarks}"></td>
					<td class="font-bold hide"><spring:message code="pos.jsp.spldscprcnt" text="Spl.Disc(%)" />:</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" style="color: #000000;" type="text" id="spcldiscperc_rsp" value="${saleReturnDTO.specialDiscPer}" onkeyup="calculateSpclDiscrsp()"></td>
					<td class="font-bold hide"><spring:message code="pos.jsp.spldscamnt" text="Spl.Disc.Amt" />:</td>
					<td style="padding-right: 5px;" class="hide"><input class="form-control-trx" type="text" id="spcldisc_rsp" value="${saleReturnDTO.specialDiscAmount}" readonly></td>
					
					<td colspan="4" style="padding-top: 4px; padding-right: 4px;"><c:if test="${saleReturnDTO.isPosted ==0}">
							<c:if test="${saleHeaderDTO.holdFlag ==0}">
							<c:if test="${menuByUserDTO.isAll==1}">
								<button style="padding: 5px 8px;" class="btn btn-primary btn-sm" type="button" onclick="saveOrUpdateSaleReturnInv()">
									<spring:message code="cmn.jsp.return" text="RETURN" />
								</button>
							</c:if>
							</c:if>
							<button class="btn btn-success btn-sm hide" type="button" id="" onclick="openRetMemo()">
								<spring:message code="cmn.jsp.new" text="NEW" />
							</button>
							<c:if test="${menuByUserDTO.isView==1}">
								<button style="padding: 5px 8px;" class="btn btn-danger btn-sm hide" type="button" id="" onclick="deleteRetSalesInv()">
									<spring:message code="cmn.jsp.tblhdr.del" text="DEL" />
								</button>
							</c:if>
						</c:if></td>
				
				</tr>
			</table>
		</div>
					</p>
				</div>
			</div>
		</div>


	</div>
</section>

<div class="modal fade" id="stkdetModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 700px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="pos.jsp.stckdetailsfor" text="Stock Details for" />
					<span id="moditemname"> Telma AM</span>
				</h4>
			</div>
			<div class="modal-body">
				<div>
					<span class="font-bold"><spring:message code="itemmstr.jsp.manufacturer" text="Manufacturer" /> :</span> <span id="modmanufname">Zoltan (Glenmark Pharmaceuticals Ltd.)</span>
				</div>
				<div>
					<span class="font-bold"><spring:message code="itemmstr.jsp.content" text="Content" /> :</span> <span id="modcontentname"> AMLODIPINE BESILATE(5 MG)+TELMISARTAN(40 MG)</span>
				</div>
				<div>
					<span class="font-bold"><spring:message code="itemmstr.jsp.group" text="Group" /> :</span> <span id="modgroupname"> Syrup</span>
				</div>
				<div>
					<span class="font-bold"><spring:message code="itemmstr.jsp.rack" text="Rack" /> :</span> <span id="modrackname"> A </span>
				</div>
				<div>
					<span class="font-bold">Note :</span> <span id="moditemnote"> </span>
				</div>
				<div >
				      <span>
				       <%-- <button type="button" onclick="getGenericMed()" data-dismiss="modal" class="btn btn-theme">
							<spring:message code="footer.jsp.btn.getaltmed" text="Get Alternate Medicine" />
						</button> --%>
						<button type="button" onclick="getGenericMed()"  class="btn btn-theme">
							<spring:message code="footer.jsp.btn.getaltmed" text="Get Alternate Medicine" />
						</button>
				      </span>
				      <span id="snditmtopodiv">
						<input type="hidden" id="senditemtopoid">
						<button type="button" onclick="sendItemToPO()" class="btn btn-theme">Send Item To PO</button>
						&nbsp; &nbsp;<span id="snditmpo" class="alert alert-success hide" style="padding: 6px;"></span>
					</span>
				</div>
				<div id="itemnotfounddiv" class="hide">
					<input type="hidden" id="itemnotfoundid"> <span class="font-bold" id="itemnotfound"><spring:message code="pos.jsp.nostockerror" text="No stock found for" /> <span id="itemnotfoundname"> </span>

						<%-- <button type="button" onclick="getGenericMed()" data-dismiss="modal" class="btn btn-theme">
							<spring:message code="footer.jsp.btn.getaltmed" text="Get Alternate Medicine" />
						</button> --%></span>
				</div>
				<div style="max-height: 300px; height: 200px; overflow: auto; margin-top: 1%;" id="modtable">
					<table id="itemstockdetails" class="table table-bordered table-condensed-trx table-hover">
						<thead>
							<tr>
								<th><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
								<th><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
								<th><spring:message code="pos.jsp.currstock" text="Cur Stock" /></th>
								<th><spring:message code="pos.jsp.holdstock" text="Hold Stock" /></th>
								<th><spring:message code="pos.jsp.mrpPack" text="MRP/Pack" /></th>
								<th class="numeric"><spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
								<th class="numeric"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /></th>
								<th><spring:message code="pos.jsp.packing" text="Packing" /></th>
								<th class="hide"><spring:message code="pos.jsp.invNo" text="Inv No" /></th>
								<th class="hide"><spring:message code="pos.jsp.invDate" text="Inv Date" /></th>
							</tr>
						</thead>
						<tbody id="itemdetails">

						</tbody>
						<tfoot>
							<tr>
								<td></td>
								<td></td>
								<td class="font-bold" id="totalcurrstkitm">48/0 [480]</td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</tfoot>

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

<div class="modal fade" id="itemsByContentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="pos.jsp.itemnameforgeneric" text="Item name for " />
					<span id="modsearchedcontent"> Telma AM</span>
				</h4>
			</div>
			<div class="modal-body">
				<div id="contentnotmatchdiv" class="hide">
					<input type="hidden" id="contentnotmatchid"> <span class="font-bold" id="itemnotmatchcontent"><spring:message code="pos.jsp.itemnotmatchcontent" text="No Item match with this content " />: <span id="contentnotfoundname"> </span> </span>
				</div>
				<div id="itemsearchtable">
					<table id="itemsbycontentidtbl" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th>Item Name</th>
							</tr>
						</thead>
						<tbody id="itemsbycontentid">

						</tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer" style="border-top: 0px solid #e5e5e5;">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="alternateMedModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style=" width: 750px;margin: auto;">
		<div class="modal-content" >
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="pos.jsp.altrntmed" text="Alternate Medicine for" />
					<span id="alternateMeditemname"> Telma AM</span>
				</h4>
			</div>

			<div class="modal-body" style="height:450px; overflow-y: scroll;">
			     <spring:message code="pos.jsp.Prescription" text="Prescription" />:
			     <table id="selectedMeddetails" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th><spring:message code="pos.jsp.name" text="Name" /></th>
								<th><spring:message code="accgroup.jsp.groupname" text="Group Name" /></th>
								<th style="width: 30%;"><spring:message code="pos.jsp.mfgname" text="Mfg. Name" /></th>
								<th><spring:message code="pos.jsp.netcntnt" text="Net.Cont" /></th>
								<th><spring:message code="itemmstr.jsp.price" text="Price" /></th>
								

							</tr>
						</thead>
						<tbody>
						  <tr>
						      <td><span id="selectedItemName"></span></td>
						       <td><span id="selectedItemGrpName"></span></td>
						      <td><span id="selectedItemMfgName"></span></td>
						      <td><span id="selectedItemNetCont"></span></td>
						     <td><span id="selectedItemPrice"></span></td>
						   </tr>
						</tbody>
					</table>
			     
			    <spring:message code="pos.jsp.alternate" text="Alternative Medicine" />:
				<div id="altmeditemnotfounddiv" class="hide">
					<span class="font-bold" id="altmeditemnotfound"><spring:message code="pos.jsp.altrntmederror" text="No alternate medicine found for" /></span> <span id="altmeditemnotfoundname"> </span>
				</div>
				<div style="max-height: 350px; height: 300px; overflow: auto;" id="alternateMedtable">
					<table id="alternateMeddetails" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th><spring:message code="pos.jsp.name" text="Name" /></th>
								<th style="width: 30%;"><spring:message code="pos.jsp.mfgname" text="Mfg. Name" /></th>
								<th><spring:message code="pos.jsp.netcntnt" text="Net.Cont" /></th>
								<th><spring:message code="itemmstr.jsp.price" text="Price" /></th>
								<th><spring:message code="ourprice" text="Our Price" /></th>
								<th><spring:message code="itemmstr.jsp.difference" text="You Save" /></th>
								<th><spring:message code="pos.jsp.stkqty" text="Stk.Qty" /></th>

							</tr>
						</thead>
						<tbody id="alternatemeddetails">

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

<%-- <div class="modal fade" id="cardModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">

				<h4 class="modal-title" id="myModalLabel">Card Details</h4>
			</div>
			<div class="modal-body">
				<div class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label">Card Amount :</label>
						<div class="col-sm-8">
							<input class="form-control-trx" type="text" id="cardAmtMod" value="${saleHeaderDTO.cardAmount}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label">Card Expiry :</label>
						<div class="col-sm-8">
							<input class="form-control-trx" type="text" id="cardExpMod" value="${saleHeaderDTO.cardExpDate}" placeholder="MM/YY">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label">Card Last Four Digit :</label>
						<div class="col-sm-8">
							<input class="form-control-trx" type="text" id="cardFourDigitMod" value="${saleHeaderDTO.cardFourDigit}" placeholder="XXXX">
						</div>
					</div>
				</div>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="button" onclick="holdCardDetails()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div> --%>
<div class="modal fade" id="payModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- 				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="pos.jsp.payBtn" text="Pay" />
				</h4>
			</div>
			<c:if test="${saleHeaderDTO.holdFlag ==0}">
				<div class="modal-body" id="saleInvDiv">
					<div class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" style="font-size: 16px; font-weight: bold; color: #d43f3a;"><spring:message code="pos.jsp.billamt" text="Bill Amount" /> :</label>
							<div class="col-sm-8">
								<input class="form-control-trx" type="text" style="font-weight: bold; color: #000000; background-color: #ebccd1;" id="paymodnettot" value="${saleHeaderDTO.netAmount}" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.retrnadjamt" text="Return Adj. Amount" /> :</label>
							<div class="col-sm-5">
								<input class="form-control-trx" type="text" id="payretadjamt" value="${saleHeaderDTO.adjAmount}" readonly="readonly">
							</div>
							<div class="col-sm-3" style="padding-left: 10px;">
								<button class="btn btn-primary btn-sm" type="button" onclick="openretadjmod()" id="" style="text-transform: uppercase;">
									<i class="fa fa-reply"></i>
									<spring:message code="pos.jsp.retrnmemo" text="Return Memo" />
								</button>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.tenderamt" text="Tender Amount" /> :</label>
							<div class="col-sm-8">
								<input class="form-control-trx" type="text" id="tenderamt" value="${saleHeaderDTO.tenderAmount}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.refundamt" text="Refund Amount" /> :</label>
							<div class="col-sm-8">
								<input class="form-control-trx" type="text" id="refundAmt" value="" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.cashamt" text="Cash Amount" /> :</label>
							<div class="col-sm-8">
								<input class="form-control-trx" type="text" id="cashAmt" value="${saleHeaderDTO.cashAmount}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.creditamt" text="Credit Amount" /> :</label>
							<div id="creditCustDiv" class="col-sm-8 col-sm-8 hide">
								<div class="col-sm-4" style="padding: 0;">
									<input class="form-control-trx" type="text" id="creditAmt" value="${saleHeaderDTO.creditAmount}" readonly="readonly">
								</div>
								<div class="col-sm-2">
									<label class="control-label"><spring:message code="pos.jsp.avlamt" text="Avl Limit" /> :</label>
								</div>
								<div class="col-sm-4" style="padding: 0;">
									<input class="form-control-trx" type="text" id="creditLimit" readonly="readonly">
								</div>
								<div class="col-sm-2">
									<button class="btn btn-primary btn-sm" onclick="javascript:openAddEditModal($('#salecustid').val())">+</button>
								</div>
							</div>
							<div id="notCreditCustDiv">
								<div class="col-sm-8">
									<input class="form-control-trx" type="text" id="creditAmt" value="${saleHeaderDTO.creditAmount}" readonly="readonly">
								</div>
							</div>
						</div>
						<div id="carddiv" style="display: none;">
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.cardamt" text="Card Amount" /> :</label>
								<div class="col-sm-8">
									<input class="form-control-trx" type="text" id="cardAmt" value="${saleHeaderDTO.cardAmount}">

								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.cardexp" text="Card Expiry" /> :</label>
								<div class="col-sm-8">
									<input class="form-control-trx" type="text" id="cardExpMod" value="${saleHeaderDTO.cardExpDate}" placeholder="MM/YY" maxlength="5">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.cardlstdigit" text="Card Last Four Digit" /> :</label>
								<div class="col-sm-8">
									<input class="form-control-trx" type="text" id="cardFourDigitMod" value="${saleHeaderDTO.cardFourDigit}" placeholder="XXXX" maxlength="4">
								</div>
							</div>
						</div>
						<div class="form-group">
							<c:if test="${saleHeaderDTO.holdFlag ==0}">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.prntinv" text="Print Invoice" /> : </label>
								<div class="col-sm-6">
									<input type="checkbox" id="printInv" name="printInv" checked="checked" style="zoom: 1.5; vertical-align: middle; margin: 0px;">
								</div>
							</c:if>
							<div class="col-sm-2">
								<button class="btn btn-primary btn-sm" type="button" id="cardbut" style="text-transform: uppercase;">
									<i class="fa fa-credit-card" aria-hidden="true"></i>
									<spring:message code="pos.jsp.card" text="CARD" />
								</button>
							</div>
						</div>
						 <c:if test="${sesloggedinStore.isMailEnable==1}"> 
							<div class="form-group" id="mailOptionDiv">
								   <label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.emailinv" text="Email Invoice" /> : </label>
									<div class="col-sm-6">
											<input type="checkbox" id="emailInv" name="emailInv"  style="zoom: 1.5; vertical-align: middle; margin: 0px;">
									</div>
							</div>
					   </c:if> 
						<c:if test="${saleHeaderDTO.holdFlag ==0}">
							<strong><spring:message code="pos.jsp.asktopay" text="Are you sure to pay" /> <span id="docorcus"> </span> ? </strong>
						</c:if>
						<div>
							<span id="alertmessagecont" style="font-weight: bold; color: red;"> </span>
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${saleHeaderDTO.holdFlag ==1}">
				<div class="modal-body" id="saleInvDivpaydet">
					<div class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" style="font-size: 16px; font-weight: bold; color: #d43f3a;"><spring:message code="pos.jsp.billamt" text="Bill Amount" /> :</label>
							<div class="col-sm-8">
								<input class="form-control-trx" type="text" style="font-weight: bold; color: #000000; background-color: #ebccd1;" id="paymodnettot" value="${saleHeaderDTO.netAmount}" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.retrnadjamt" text="Return Adj. Amount" /> :</label>
							<div class="col-sm-5">
								<input class="form-control-trx" type="text" id="viewpayretadjamt" value="${saleHeaderDTO.adjAmount}" readonly="readonly">
							</div>
							<div class="col-sm-3" style="padding-left: 6px;">
								<button class="btn btn-primary btn-sm" type="button" onclick="viewretadjamt()" id="" style="text-transform: uppercase;">
									<i class="fa fa-reply"></i>
									<spring:message code="pos.jsp.retrnmemodt" text="Ret Memo Det" />
									.
								</button>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.tenderamt" text="Tender Amount" /> :</label>
							<div class="col-sm-8">
								<input class="form-control-trx" type="text" id="tenderamt" value="${saleHeaderDTO.tenderAmount}" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.refundamt" text="Refund Amount" /> :</label>
							<div class="col-sm-8">
								<input class="form-control-trx" type="text" id="refundAmt" value="" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.cashamt" text="Cash Amount" /> :</label>
							<div class="col-sm-8">
								<input class="form-control-trx" type="text" id="cashAmt" value="${saleHeaderDTO.cashAmount}" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.creditamt" text="Credit Amount" /> :</label>
							<div class="col-sm-8">
								<input class="form-control-trx" type="text" id="creditAmt" value="${saleHeaderDTO.creditAmount}" readonly="readonly">
							</div>
						</div>
						<div id="carddiv" style="display: none;">
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.cardamt" text="Card Amount" /> :</label>
								<div class="col-sm-8">
									<input class="form-control-trx" type="text" id="cardAmt" value="${saleHeaderDTO.cardAmount}" readonly="readonly">

								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.cardexp" text="Card Expiry" /> :</label>
								<div class="col-sm-8">
									<input class="form-control-trx" type="text" id="cardExpMod" value="${saleHeaderDTO.cardExpDate}" placeholder="MM/YY" maxlength="5" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.cardlstdigit" text="Card Last Four Digit" /> :</label>
								<div class="col-sm-8">
									<input class="form-control-trx" type="text" id="cardFourDigitMod" value="${saleHeaderDTO.cardFourDigit}" placeholder="XXXX" maxlength="4" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="form-group">
							<c:if test="${saleHeaderDTO.holdFlag ==0}">
								<label class="col-sm-4 col-sm-4 control-label"><spring:message code="pos.jsp.prntinv" text="Print Invoice" /> : </label>
								<div class="col-sm-6">
									<input type="checkbox" id="printInv" name="printInv" checked="checked" style="zoom: 1.5">
								</div>
							</c:if>
							<div class="col-sm-2">
								<button class="btn btn-primary btn-sm" type="button" id="cardbut" style="text-transform: uppercase;">
									<i class="fa fa-credit-card" aria-hidden="true"></i>
									<spring:message code="pos.jsp.card" text="Card" />
								</button>
							</div>
						</div>
						<c:if test="${saleHeaderDTO.holdFlag ==0}">
							<strong><spring:message code="pos.jsp.asktopay" text="Are you sure to pay" /> ?</strong>
						</c:if>
					</div>
				</div>
			</c:if>
					<!--  for account in pos -->
					
					
												 <input type="hidden" id="debitor_code1" value="<spring:message code="accgroup.jsp.deb_code" text="SDE" />">
											  <input type="hidden" id="saleac_code1" value="<spring:message code="accgroup.jsp.saleac_code" text="SA" />">
											 <input type="hidden" id="dutiesandtax_code1" value="<spring:message code="accgroup.jsp.duties_code" text="DT" />">
											 <input type="hidden" id="dicount_code1" value="<spring:message code="accgroup.jsp.disc_code" text="DIS" />">
											 <input type="hidden" id="roundof_code1" value="<spring:message code="accgroup.jsp.roun_code" text="ROD" />">
											  <input type="hidden" id="cash_code1" value="<spring:message code="accgroup.jsp.cash_code" text="CIH" />">
											  <input type="hidden" id="card_code1" value="<spring:message code="accgroup.jsp.card_code" text="CAB" />">
											  <input type="hidden"  id="sales_ledger_id1" value="0">
											  <input  type="hidden" id="pos_sale" value="0">
												<input type="hidden"  id="duties_ledger_id1" value="0">
												<input  type="hidden" style=" color: #000000; " id="pos_duties"  value="0" >
												<input type="hidden"  id="discount_ledger_id1" value="0">
												<input   type="hidden"   id="pos_discount" value="0" >
												<input type="hidden"  id="round_ledger_id1" value="0">
												<input   type="hidden"   id="pos_round"  value="0" >
												 <input type="hidden"  id="debitor_ledger_id1" value="0">	
												<input  type="hidden"  id="pos_debit"  value="0">
												<input type="hidden"  id="debitor_cahs_ledger_id1" value="0">	
												<input   type="hidden"   id="pos_cash_debit" value="0" >	
												<input type="hidden" id="card_ledger_id_final" value="0">
												<input  type="hidden"   id="card_debit_amt"  value="0" >
												<input type="hidden"    id="card_ledger_id1" name="card_ledger_id_val" > 
			   
											  
				
				
	
			<div class="modal-footer">
				<c:if test="${saleHeaderDTO.holdFlag ==0}">
					<button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearSaleInv()">
						<spring:message code="cmn.jsp.btn.close" text="Close" />
					</button>
				</c:if>
				<c:if test="${saleHeaderDTO.holdFlag ==1}">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<spring:message code="cmn.jsp.btn.close" text="Close" />
					</button>
				</c:if>
				<c:if test="${saleHeaderDTO.holdFlag ==0}">
					<button type="button" onclick="paySaleInv()" style="padding: 6px 30px;" class="btn btn-theme">
						<spring:message code="footer.jsp.btn.ok" text="OK" />
					</button>
				</c:if>
			</div>
		</div>
	</div>
</div>
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
				<spring:message code="itemmstr.jsp.exist.error" text="Item with same batch and expiry already exist, please try other." />
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
<div class="modal fade" id="saveWithoutCustAndDocNameModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body">
				<spring:message code="pos.jsp.asktoholdcashmemo" text="Want to hold cashmemo without Doctor/Customer name" />
				?
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="saveOrUpdateSaleInv()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
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
				<input type="hidden" id="confirmIdret">
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
<div class="modal fade" id="scheleXorH1Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold" style="color: red;">
				<spring:message code="pos.jsp.clctprescriptionmsg" text="Please collect the prescription and add Doctor & Customer name." />
				<input type="hidden" id="operationtype">
			</div>
			<div class="modal-footer">
				<button type="button" onclick="addItemtotable(document.getElementById('operationtype').value)" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="currStkGraterModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
				<spring:message code="pos.jsp.totqtygrtcrntstckmsg" text="Total quantity is greater than current stock" />
				( <span id="currstkofitm"></span> ).
			</div>
			<div class="modal-footer">
				<button type="button" onclick="closeCurrStkModal()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Cust Last Bills Modal -->
<div class="modal fade" id="custLastBillModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="pos.jsp.prevbillfor" text="Previous Bills for" />
					<span id="cusprebillphno"> </span>
				</h4>
			</div>
			<div class="modal-body">
				<div id="prevsaledetnotfounddiv" class="hide">
					<span class="font-bold" id="prevsalenotfound"> <spring:message code="pos.jsp.prevcashmemonotprsnterr" text="Previous cashmemo not found for this Customer" />. 
				</div>
				<div style="max-height: 300px; height: 200px; overflow: auto;" id="saledetmodtable">
					<table id="" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th><spring:message code="purinvdet.jsp.invno" text="Invoice No" /></th>
								<th><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></th>
								<th><spring:message code="modcancashmemo.jsp.invmode" text="Inv Mode" /></th>
								<th class="numeric"><spring:message code="purinvdet.jsp.total" text="Total" /></th>
								<th><spring:message code="modcancashmemo.jsp.discount" text="Dis.Amt." /></th>
								<th class="numeric"><spring:message code="purinvdet.jsp.nettotal" text="NetTotal" /></th>
								<th><spring:message code="purinvreg.jsp.status" text="Status" /></th>
							</tr>
						</thead>
						<tbody id="saleheaderdet">

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


<div class="modal fade" id="saledetailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 766px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="rtrnmemo.jsp.salereturnmodal.invno" text="Sale Details for " />
					<spring:message code="purinvdet.jsp.invno" text="Invoice No" />
					. <span id="searchmodinvno"> SIM/${sessionScope.sesloggedinUser.finyrCode}/00001</span>
				</h4>
			</div>
			<div class="modal-body">

				<div style="max-height: 300px; height: 200px; overflow: auto;" id="saledetmodtable">
					<table id="searchmodtable" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th></th>
								<th><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></th>
								<th><spring:message code="rtrnmemo.jsp.salereturnmodal.batch" text="Batch No" /></th>
								<th><spring:message code="rtrnmemo.jsp.salereturnmodal.exp" text="Exp Dt" /></th>
								<th><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
								<th><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></th>

								<th><spring:message code="purinvdet.jsp.mrpls" text="MRP/Ls" /></th>
								<th><spring:message code="purinvdet.jsp.ratels" text="Rate/Ls" /></th>
							</tr>
						</thead>
						<tbody id="searchmodtbody">

						</tbody>
						<div>
							<span id="alertmessagecont" style="font-weight: bold; color: red;"> </span>
						</div>

					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" class="btn btn-primary" onclick="">
					<spring:message code="footer.jsp.btn.ok" text="Ok" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- End Cust Last Bills MOdal -->
<!-- Return Adj Modal -->
<div class="modal fade" id="retAdjModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 666px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="pos.jsp.retrnmemotoadjst" text="Return Memo to Adjust for Bill Amt" />
					. <span id="billamtheaderAdj"></span>
				</h4>
			</div>
			<div class="modal-body">

				<table>
					<tr>
						<td><spring:message code="pos.jsp.memono" text="Memo.No" />:</td>
						<td style="padding: 0 1px;"><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retadjmemoDoc" value="SRM/" size="4" readonly></td>
						<td><input class="form-control-trx" type="text" style="padding: 4px 1px;" id="retadjmemoFinyr" value="${sessionScope.sesloggedinUser.finyrCode}" size="5"></td>
						<td style="padding: 0 1px;" width="2.5%"><input class="form-control-trx" type="text" style="padding: 4px 0px;" id="retadjmemoSlash" value="/" readonly></td>
						<td><input class="form-control-trx" type="text" id="retadjmemono" value="" size="10"></td>
						<td style="padding: 0 1px;"><spring:message code="pos.jsp.custphn" text="Cust.Ph" />:</td>
						<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="retadjcustph" value=""></td>
						<td style="padding: 0 1px;"><spring:message code="pos.jsp.custname" text="Cust.Name" />:</td>
						<td style="padding: 0 1px;"><input class="form-control-trx" type="text" id="retadjcustname" readonly="readonly" value=""></td>
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
					<p>
						<spring:message code="pos.jsp.addedmemos" text="Added Memos" />
					</p>
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

<div class="modal fade" id="viewretAdjModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 666px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="pos.jsp.retrnmemodetails" text="Return Memo Details" />
				</h4>
			</div>
			<div class="modal-body">

				<div>
					<p>
						<spring:message code="pos.jsp.addedmemos" text="Added Memos" />
					</p>
				</div>
				<div style="max-height: 300px; overflow: auto;" id="viewretadjDetails">
					<table id="viewretadjtable" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th><spring:message code="purinvdet.jsp.invno" text="Invoice No" />.</th>
								<th><spring:message code="purinvdet.jsp.invdt" text="Inv Date" /></th>
								<th><spring:message code="pos.jsp.netamnt" text="Net Amt" /></th>
								<!-- 								<th>Pre Adj Amt</th> -->
								<th><spring:message code="pos.jsp.adjamt" text="Adj Amt" /></th>
							</tr>
						</thead>
						<tbody id="viewretadjtbody">

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
<!-- End Return Adj Modal -->

<div class="modal fade" id="confirmModalRetadj" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
				<button type="button" onclick="DoConfirmRetAdj()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>

<!-- Add/Edit group modal start -->
<div class="modal fade" id="customerAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
							<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="customer.jsp.name" text="Customer Name" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="customerName" class="form-control">
							</div>
						</div>
						
						
						
						
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="phn_label"><spring:message code="customer.jsp.phn" text="Phone No." /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="phn" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="addrs_label"><spring:message code="customer.jsp.address" text="Address" /></label>
							<div class="col-sm-8">
								<input type="text" id="addrs" class="form-control">
							</div>
						</div>
 
						<!-- age -->
							<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="age_label"><spring:message code="customer.jsp.age" text="Age" /></label>
							<div class="col-sm-8">
								<input type="text" id="age"  maxlength="3" class="form-control">
							</div>
						</div>
						
						<!-- guardians name -->
							<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="guardian_label"><spring:message code="customer.jsp.guardianname" text="Guardians name" /></label>
							<div class="col-sm-8">
								<input type="text" id="guardian_name" class="form-control">
							</div>
						</div>
						
					
					
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="customer.jsp.aadharno" text="Aadhar card no." /></label>
							<div class="col-sm-8">
								<input type="text" id="aadharcard" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label">Gender</label>
							<div class="col-sm-8">
								<select class="form-control" name="gender" id="slgender" onchange="getGender()">
									<c:if test="${!empty genderlist}">
										<c:forEach items="${genderlist}" var="glist">
											<option value="${glist.name}">${glist.name}</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="customer.jsp.consiName" text="Consignee Name" /></label>
							<div class="col-sm-8">
								<input type="text" id="consiNm" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="customer.jsp.consiAddress" text="Consignee Address" /></label>
							<div class="col-sm-8">
								<input type="text" id="consiAddr" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="customer.jsp.consiPhone" text="Consignee Phone" /></label>
							<div class="col-sm-8">
								<input type="text" id="consiPhone" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-sm-6 ">
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="city_label"><spring:message code="customer.jsp.city" text="City" /></label>
							<div class="col-sm-8">
								<input type="text" id="city" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="state_label"><spring:message code="customer.jsp.state" text="State" /></label>
							<div class="col-sm-8">
								<input type="text" id="state" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="country_label"><spring:message code="customer.jsp.country" text="Country" /></label>
							<div class="col-sm-8">
								<input type="text" id="country" class="form-control">
							</div>
						</div>
							
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="pin_label"><spring:message code="customer.jsp.pin" text="Pin" /></label>
							<div class="col-sm-8">
								<input type="text" id="pin" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="code_label"><c:if test="${sesloggedinStore.isEsi==1}">
									<spring:message code="customer.jsp.inscardno" text="Ins/Card No" />
								</c:if>
								<c:if test="${sesloggedinStore.isEsi==0}">
									<spring:message code="customer.jsp.code" text="Code" />
								</c:if></label>
							<div class="col-sm-8">
								<input type="text" id="code" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="fax_label"><spring:message code="manufacturer.jsp.email" text="Email" /><%-- <spring:message code="customer.jsp.fax" text="Fax" /> --%></label>
							<div class="col-sm-8">
								<input type="text" id="fax" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="opbal_label"><spring:message code="customer.jsp.opbal" text="Opening Balance" /></label>
							<div class="col-sm-8">
								<input type="text" id="opbal" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="customer.jsp.creditLimit" text="Credit Limit" /></label>
							<div class="col-sm-8">
								<input type="text" id="creditLimitpop" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="customer.jsp.consiGstNo" text="Consignee GstNo" /></label>
							<div class="col-sm-8">
								<input type="text" id="consiGstNo" class="form-control">
							</div>
						</div>
						<div class="form-group hide">		
							<div class="col-sm-8">
								<input type="text" id="consiStateId" class="form-control">
							</div>
						</div>
						<div class="form-group hide">
							<label class="col-sm-4 col-sm-4 control-label">D.O.B</label>
							<div class="col-sm-8">
								 <input type="text" class="form-control" id="date" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />"/>
							</div>
						</div>
					</div>
				</div>
				<input type="hidden" id="cust_id" value=""></input>
				<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="custAddAlertMsg"></div>
			</div>
			<div class="modal-footer" style="border-top: 0px solid #e5e5e5;">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="javascript:addEditCustomer()" class="btn btn-theme">
					<spring:message code="cmn.jsp.btn.save" text="SAVE" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Add/Edit group modal end -->

<!-- Add doctor modal start -->
<div class="modal fade" id="doctorAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 836px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<span id="headertextDoc"></span>
				</h4>
			</div>
			<div class="modal-body">
				<div class="form-horizontal">
					<div class="col-sm-6 col-sm-6 ">
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="doc_name_label"><spring:message code="dctr.jsp.name" text="Doctor Name" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="dctrName" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="code_label"><spring:message code="dctr.jsp.code" text="Code" /></label>
							<div class="col-sm-8">
								<input type="text" id="code" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="locked_label"><spring:message code="dctr.jsp.locked" text="Is Locked" /></label>
							<div class="col-sm-8">
								<input type="checkbox" id="locked">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="qualification_label"><spring:message code="dctr.jsp.qualification" text="Qualification" /></label>
							<div class="col-sm-8">
								<input type="text" id="qualification" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="speciality_label"><spring:message code="dctr.jsp.speciality" text="Speciality" /></label>
							<div class="col-sm-8">
								<input type="text" id="speciality" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="addrs_label"><spring:message code="dctr.jsp.address" text="Address" /></label>
							<div class="col-sm-8">
								<input type="text" id="addrs" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="pin_label"><spring:message code="dctr.jsp.pin" text="Pin" /></label>
							<div class="col-sm-8">
								<input type="text" id="pin" class="form-control">
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-sm-6 ">
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="city_label"><spring:message code="dctr.jsp.city" text="City" /></label>
							<div class="col-sm-8">
								<input type="text" id="city" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="state_label"><spring:message code="dctr.jsp.state" text="State" /></label>
							<div class="col-sm-8">
								<input type="text" id="state" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="country_label"><spring:message code="dctr.jsp.country" text="Country" /></label>
							<div class="col-sm-8">
								<input type="text" id="country" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="phn_label"><spring:message code="dctr.jsp.phn" text="Phone No." /></label>
							<div class="col-sm-8">
								<input type="text" id="phn" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="fax_label"><spring:message code="dctr.jsp.fax" text="Fax" /></label>
							<div class="col-sm-8">
								<input type="text" id="fax" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="opbal_label"><spring:message code="dctr.jsp.opbal" text="Opening Balance" /></label>
							<div class="col-sm-8">
								<input type="text" id="opbal" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="commPer_label"><spring:message code="dctr.jsp.commPer" text="Commision Percentage" /></label>
							<div class="col-sm-8">
								<input type="text" id="commPer" class="form-control">
							</div>
						</div>
					</div>
				</div>
				<input type="hidden" id="dctr_id" value=""></input>
				<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="docAddAlertMsg"></div>
			</div>
			<div class="modal-footer" style="border-top: 0px solid #e5e5e5;">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="javascript:addEditDoctor()" class="btn btn-theme">
					<spring:message code="cmn.jsp.btn.save" text="SAVE" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Add doctor modal end -->

<div class="modal fade" id="saletableItemDelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
				<spring:message code="footer.jsp.cnfrmquestion" text="Are you sure?" />
				<input type="hidden" id="saletableitemdelid" value="0">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="footer.jsp.btn.cancel" text="Cancel" />
				</button>
				<button type="button" onclick="closeSaletableItemDel(document.getElementById('saletableitemdelid').value)" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="itemMaxDisModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
				Maximum discount allowed to this item is <span id="itemmaxdisperspan"> </span> (%)
			</div>
			<div class="modal-footer">

				<button type="button" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="noItemBarcodeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body font-bold">
				No item found against this(<span id="inputbarcode"></span>) barcode. Please check the input barcode.
			</div>
			<div class="modal-footer">

				<button type="button" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!--/wrapper -->
<!-- Return page Modal Starts -->

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
				<input type="hidden" id="confirmvalrsp" value="${saleHeaderDTO.allReturnIds}">
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

<!-- Confirm Modal end -->
<div class="modal fade" id="confirmMessageModal_rsp_check" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!" />
				</h4>
			</div>
			<div class="modal-body">
				<div id="confirmmessagecontrspcheck"></div>
				
			</div>
			<div class="modal-footer">
				<button type="button" onclick="targetURLrspcheck()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="samepageretnotsave_rsp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!" />
				</h4>
			</div>
			<div class="modal-body">
				<div> Please save the return memo first.</div>
			</div>
			<div class="modal-footer">
				<button type="button" onclick="" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="samepageretvalgreater_rsp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!" />
				</h4>
			</div>
			<div class="modal-body">
				<div> Return amount is greater than or equals Net-Total. Please add sell item for adjust.</div>
			</div>
			<div class="modal-footer">
				<button type="button" onclick="" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="saleAltMedAlertModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!" />
				</h4>
			</div>
			<div class="modal-body">
			     <input type="hidden" id="altItemId" />
			     <input type="hidden" id="altItemName" />
			     <div id="confirmmessagesalealtmed" style=" font-weight:bold;color:#e26708;font-size: 17px;"></div>
				
			</div>
			<div class="modal-footer">
				<button type="button" onclick="selAltMed()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
				<button type="button" onclick="notSelAltMed()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
			</div>
		</div>
	</div>
</div>
<style>
.rowActive {
	background-color: #C9F9FA !important;
}
</style>


<script src="${pageContext.request.contextPath }/assets/js/pos/cashmemo/pos.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/glitter/jquery.gritter.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/pos/customer/customer_en.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/inventory/doctor/doctor_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/pos/cashmemo/cashmemo_en.js"></script>
</c:if>
<script src="${pageContext.request.contextPath }/assets/js/pos/returnmemo/returnsalememo.js"></script>
<%-- <script src="${pageContext.request.contextPath}/assets/js/pos/customer/customer.js"></script> --%>
<c:if test="${pageContext.response.locale == 'en'}">
<%-- 	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script> --%>
	<script src="${pageContext.request.contextPath }/assets/js/pos/returnmemo/retcashmemo_en.js"></script>
</c:if>
<script type="text/javascript">
	var BASE_URL = "${pageContext.request.contextPath}";
	var dotMatrixPrint='<%=CommonResource.getProperty(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_PRINT)%>';
	var n2='<%=CommonResource.getProperty(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_NOTELINE_ONE)%>';
	var n1='<%=CommonResource.getProperty(CommonResource.GENERAL_SETTING_SALEBILL_DOTMATRIX_NOTELINE_TWO)%>';
	var isAccount = ${isAccount};
	console.log("isAccount = "+isAccount);
	//$("#add_cust_td").hide();
	function showConfirmModal() {
		$('#confirmMessageModal').modal('show');
	}
	/* $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
		  var target = $(e.target).attr("href") // activated tab
		  alert(target);
		}); */
	$(document).ready(function() {
		
		//calculateTotalPurchaseamt();
		var grandtotalPur = 0.00;
		$('#selitem tbody tr').each(function() {
			var itmpur = $(this).find("#saletabpurcost").html();
			grandtotalPur = grandtotalPur + Number(itmpur);
		});
		var nettot = $("#nettot").val();
		$("#profitperc").val(parseFloat(Number(nettot) - Number(grandtotalPur)).toFixed(2));
	});
	
</script>

