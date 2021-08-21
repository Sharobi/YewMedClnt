<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">
<style>
<!--
.ui-autocomplete {
	overflow-y: scroll; max-height: 250px; width: 300px; word-break: break-all;
}
-->
</style>
<section class="wrapper">
	<div class="row">
		<p>
			<spring:message code="itemmstr.jsp.title" text="Item Master..." />
		<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
		</p>

		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<form modelAttribute="itemMaster" role="form" action="${pageContext.request.contextPath}/item/addorupdateitem.htm" method="POST" id="item_form">
						<c:choose>
							<c:when test="${item_id==0}">
								<div class="col-md-6 col-sm-12">
									<div class="form-horizontal">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="itemmstr.jsp.name" text="Item Name" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<input type="hidden" value="" id="itemid" name="id"> <input type="text" value="" id="itemName" class="form-control-trx" onblur="checkSameItem(document.getElementById('itemName').value,0)" name="name" placeholder="<spring:message code="itemmstr.jsp.name" text="Item Name" />">
											</div>
										</div>
										<div class="form-group" id="itmnameexistdiv" style="display: none;">
											<div class="alert alert-danger">
												<strong><spring:message code="footer.jsp.alert" text="Alert!" /></strong>
												<spring:message code="itemmstr.jsp.exist.error" text="Item name already exist, please try other." />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="barcode_label">Barcode</label>
											<div class="col-sm-8">
											 <input type="text" value="" id="sku" class="form-control-trx" name="sku" placeholder="Barcode">
											</div>
										</div>										
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="grp_label"><spring:message code="itemmstr.jsp.group" text="Group" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<div class="input-group">
													<select class="form-control-trx" name="groupId" id="grpSelect">
														<c:if test="${!empty allGroups}">
															<c:forEach items="${allGroups}" var="allGroup">
																<option value="${allGroup.id}">${allGroup.name}</option>
															</c:forEach>
														</c:if>
													</select>
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openGroupMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="schdle_label"><spring:message code="itemmstr.jsp.schedule" text="Schedule" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<div class="input-group">
													<select class="form-control-trx" name="scheduleId" id="scheSelect">
														<c:if test="${!empty allSchedules}">
															<c:forEach items="${allSchedules}" var="allSchedule">
																<option value="${allSchedule.id}">${allSchedule.name}</option>
															</c:forEach>
														</c:if>
													</select>
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openSchMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="brand_label"><spring:message code="itemmstr.jsp.brand" text="Brand" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="brandId" value="0" class="form-control-trx" name="brandId"><input type="text" value="${itemMaster.brandMaster.name}" id="itemBrand" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.brand" text="Brand" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openBrandMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="vat_label"><spring:message code="itemmstr.jsp.vat" text="Vat" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemvat" class="form-control-trx" value="0" name="vat" placeholder="<spring:message code="itemmstr.jsp.vat" text="Vat" />">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="pckUnit_label"><spring:message code="itemmstr.jsp.packingunit" text="Packing Unit" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="packUnitId" value="" class="form-control-trx" name="packUnitId"><input type="text" id="itemPackingUnit" value="${itemMaster.packUnit.description}" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.packingunit" text="Packing Unit" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openUnitMod(2)">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="reOrder_label"><spring:message code="itemmstr.jsp.reorderlevel" text="Re-Order Level" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemreorderlevel" value="0" class="form-control-trx" name="reorderLevel" placeholder="<spring:message code="itemmstr.jsp.reorderlevel" text="Re-Order Level" />">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"
												id="purchasetax_label">Purchase Tax <span
												class="required_star">*</span></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="purchaseTaxId" value=""
														class="form-control-trx" name="purchaseTaxId"><input type="hidden" id="purchaseTaxPerc" value=""
														class="form-control-trx" name="purchaseTaxPercentage"><input
														type="text" id="purchaseTax" value=""
														class="form-control-trx" name="purchaseTax"
														placeholder="Purchase Tax(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openTaxMod('P')">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"
												id="saletax_label">Sale Tax <span
												class="required_star">*</span> </label>
											<div class="col-sm-8">
											 	<div class="input-group">
													<input type="hidden" id="saleTaxId" value=""
														class="form-control-trx" name="saleTaxId">
														<input type="hidden" id="saleTaxPerc" value=""
														class="form-control-trx" name="saleTaxPercentage"><input
														type="text" id="saleTax" value="" class="form-control-trx"
														name="saleTax"
														placeholder="Sale Tax(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openTaxMod('P')">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="price_label"><spring:message code="itemmstr.jsp.price" text="Price" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<input type="text" id="itemPrice" onkeydown="numcheck(event)" value="" class="form-control-trx" name="price" placeholder="<spring:message code="itemmstr.jsp.price" text="Price" />">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="rack_label"><spring:message code="itemmstr.jsp.rack" text="Rack" /></label>
											<div class="col-sm-8">
												<select class="form-control-trx" name="rackId" id="rackSelect">
													<option value="0">Select...</option>
													<c:if test="${!empty allRacks}">
														<c:forEach items="${allRacks}" var="allRack">
															<option value="${allRack.id}">${allRack.name}</option>
														</c:forEach>
													</c:if>
												</select>
											</div>
										</div>
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="isonmrp_label"><spring:message code="itemmstr.jsp.isonmrp" text="IsOnMRP" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<select class="form-control-trx" name="isOnMrp" id="isonmrp">
													<option value="0">NO</option>
													<option value="1">YES</option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label">HSN Code</label>
											<div class="col-sm-8">
											 <input type="text" value="" id="hsnCode" class="form-control-trx" name="hsnCode" placeholder="HSN Code">
											</div>
										</div>										
									</div>
								</div>
								<div class="col-md-6 col-sm-12">
									<div class="form-horizontal">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="code_label"><spring:message code="itemmstr.jsp.code" text="Item Code" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<input type="text" id="itemCode" value="" class="form-control-trx" name="code" placeholder="<spring:message code="itemmstr.jsp.code" text="Item Code" />">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="cntnt_label"><spring:message code="pos.jsp.generic" text="Generic" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="content_id" value=""></input> <input type="hidden" id="content_Dets" value=""></input> <input type="hidden" id="contentId" value="" class="form-control-trx" name="contentId"><input type="text" id="itemContent" value="" class="form-control-trx" placeholder="<spring:message code="pos.jsp.generic" text="Generic" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button type="button" class="btn btn-default" id="contentdetails" onclick="contentDetailsMod()" style="padding: 6px;">
															<i class="fa fa-info-circle" aria-hidden="true"></i>
														</button>
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openContentMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>

										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="cat_label"><spring:message code="itemmstr.jsp.category" text="Category" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<select class="form-control-trx" name="categoryId" id="catSelect">
													<option value="1">Category 1</option>
													<option value="2">Category 2</option>
												</select>
											</div>
										</div>
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="subCat_label"><spring:message code="itemmstr.jsp.subcat" text="Sub-Category" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<select class="form-control-trx" name="subCategoryId" id="subCatSelect">
													<option value="1">Sub-Category 1</option>
													<option value="2">Sub-Category 2</option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="mnfctr_label"><spring:message code="itemmstr.jsp.manufacturer" text="Manufacturer" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="manufacturerId" value="" class="form-control-trx" name="manufacturerId"><input type="text" id="itemManufacturer" value="" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.manufacturer" text="Manufacturer" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openManufactMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="cnvrsn_label"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<input type="text" id="itemconversion" value="" class="form-control-trx" name="conversion" placeholder="<spring:message code="purinvdet.jsp.ratio" text="Ratio" />">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.isLseSale" text="Is Loose Sale" /></label>
											<div class="col-sm-8">
												<input type="checkbox" id="lseSaleChk" name="lseSaleChk" style="zoom: 1.5; vertical-align: middle; margin: 0px;" onchange="lseSaleChkBox();"><input type="hidden" name="isLooseSale" id="isLooseSale" />
											</div>
										</div>
										<div class="form-group hide" id="lseUnitDiv">
											<label class="col-sm-4 col-sm-4 control-label" id="lseUnit_label"><spring:message code="itemmstr.jsp.looseunit" text="Loose Unit" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="looseUnitId" value="" class="form-control-trx" name="looseUnitId"><input type="text" id="itemLooseUnit" value="" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.looseunit" text="Loose Unit" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openUnitMod(1)">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="reOrderUnit_label"><spring:message code="itemmstr.jsp.Reorderunit" text="Re-Order Unit" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<input type="hidden" value="" id="reorderLevelUnitId" class="form-control-trx" name="reorderLevelUnitId"> <input type="text" id="itemreorderUnitId" value="" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.Reorderunit" text="Re-Order Unit" />(Please type atleast 2 characters)">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="netcntnt_label"><spring:message code="itemmstr.jsp.netcontent" text="Net Content" /></label>
											<div class="col-sm-8">
												<input type="text" id="netcontent" value="" class="form-control-trx" name="netContent" placeholder="<spring:message code="itemmstr.jsp.netcontent" text="Net Content" />">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="reprintcash.jsp.note" text="Note" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemRemarks" value="" class="form-control-trx" name="note" placeholder="<spring:message code="reprintcash.jsp.note" text="Note" />">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.markupperc" text="Markup (%)" /></label>
											<div class="col-sm-8">
												<input type="number" id="itemMarkup" value="" class="form-control-trx" name="markup" placeholder="<spring:message code="itemmstr.jsp.markupperc" text="Markup (%)" />">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.strength" text="Strength" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemstrength" value="" class="form-control-trx" name="strength" placeholder="<spring:message code="itemmstr.jsp.strength" text="Strength" />">
											</div>
										</div>										
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label">Is Discount</label>
											<div class="col-sm-8">
											<input type="hidden" id="isDiscounthid" value="1">
												<select class="form-control-trx" name="isDiscount" id="isDiscount">
													<option value="1">YES</option>
													<option value="0">NO</option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label">Max Discount%</label>
											<div class="col-sm-8">
												<input type="text" id="maxDiscountLimit" onkeydown="numcheck(event)" value="0.0" class="form-control-trx" name="maxDiscountLimit" placeholder="Max Discount%">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label">Discount%</label>
											<div class="col-sm-8">
												<input type="text" id="discount" onkeydown="numcheck(event)" value="0.0" class="form-control-trx" name="discount" placeholder="Discount%">
											</div>
										</div>
										
									</div>
								</div>

							</c:when>
							
							<c:otherwise>
								<div class="col-md-6 col-sm-12">
									<div class="form-horizontal">
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="itemmstr.jsp.name" text="Item Name" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<input type="hidden" value="${itemMaster.id}" id="itemid" name="id"> <input type="text" value="${itemMaster.name}" id="itemName" class="form-control-trx" onblur="checkSameItem(document.getElementById('itemName').value,${itemMaster.id})" name="name" placeholder="<spring:message code="itemmstr.jsp.name" text="Item Name" />">
											</div>
										</div>
										<div class="form-group" id="itmnameexistdiv" style="display: none;">
											<div class="alert alert-danger">
												<strong><spring:message code="footer.jsp.alert" text="Alert!" /></strong>
												<spring:message code="itemmstr.jsp.exist.error" text="Item name already exist, please try other." />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="barcode_label">Barcode</label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.sku}" id="sku" class="form-control-trx" name="sku" placeholder="Barcode">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="grp_label"><spring:message code="itemmstr.jsp.group" text="Group" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<div class="input-group">
													<select class="form-control-trx" name="groupId" id="grpSelect">
														<c:if test="${!empty allGroups}">
															<c:forEach items="${allGroups}" var="allGroup">
																<c:if test="${allGroup.id==itemMaster.groupMaster.id}">
																	<option value="${itemMaster.groupMaster.id}" selected="selected">${itemMaster.groupMaster.name}</option>
																</c:if>
																<c:if test="${allGroup.id!=itemMaster.groupMaster.id}">
																	<option value="${allGroup.id}">${allGroup.name}</option>
																</c:if>
															</c:forEach>
														</c:if>
													</select>
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openGroupMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="schdle_label"><spring:message code="itemmstr.jsp.schedule" text="Schedule" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<div class="input-group">
													<select class="form-control-trx" name="scheduleId" id="scheSelect">
														<c:if test="${!empty allSchedules}">
															<c:forEach items="${allSchedules}" var="allSchedule">
																<c:if test="${allSchedule.id==itemMaster.scheduleMaster.id}">
																	<option value="${itemMaster.scheduleMaster.id}" selected="selected">${itemMaster.scheduleMaster.name}</option>
																</c:if>
																<c:if test="${allSchedule.id!=itemMaster.scheduleMaster.id}">
																	<option value="${allSchedule.id}">${allSchedule.name}</option>
																</c:if>																
															</c:forEach>
														</c:if>
													</select>
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openSchMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="brand_label"><spring:message code="itemmstr.jsp.brand" text="Brand" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<input type="hidden" id="brandId" value="${itemMaster.brandId}" class="form-control-trx" name="brandId"><input type="text" value="${itemMaster.brandMaster.name}" id="itemBrand" readonly="readonly" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.brand" text="Brand" />(Please type atleast 2 characters)">
											</div>
										</div>
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="vat_label"><spring:message code="itemmstr.jsp.vat" text="Vat" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemvat" class="form-control-trx" value="${itemMaster.vat}" name="vat" placeholder="<spring:message code="itemmstr.jsp.vat" text="Vat" />">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="pckUnit_label"><spring:message code="itemmstr.jsp.packingunit" text="Packing Unit" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="packUnitId" value="${itemMaster.packUnitId}" class="form-control-trx" name="packUnitId"><input type="text" id="itemPackingUnit" value="${itemMaster.packUnit.description}" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.packingunit" text="Packing Unit" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openUnitMod(2)">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="reOrder_label"><spring:message code="itemmstr.jsp.reorderlevel" text="Re-Order Level" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemreorderlevel" value="${itemMaster.reorderLevel}" class="form-control-trx" name="reorderLevel" placeholder="<spring:message code="itemmstr.jsp.reorderlevel" text="Re-Order Level" />">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"
												id="purchasetax_label">Purchase Tax<span
												class="required_star">*</span></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="purchaseTaxId" value="${itemMaster.purchaseTax.id}"
														class="form-control-trx" name="purchaseTaxId"><input type="hidden" id="purchaseTaxPerc" value="${itemMaster.purchaseTax.percentage}"
														class="form-control-trx" name="purchaseTaxPercentage"><input
														type="text" id="purchaseTax" value="${itemMaster.purchaseTax.name}"
														class="form-control-trx" name="purchaseTax"
														placeholder="Purchase Tax(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openTaxMod('P')">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"
												id="saletax_label">Sale Tax<span
												class="required_star">*</span> </label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="saleTaxId" value="${itemMaster.saleTax.id}"
														class="form-controltrx" name="saleTaxId"><input type="hidden" id="saleTaxPerc" value="${itemMaster.saleTax.percentage}"
														class="form-control-trx" name="saleTaxPercentage"><input
														type="text" id="saleTax" value="${itemMaster.saleTax.name}" class="form-control-trx"
														name="saleTax"
														placeholder="Sale Tax(Please type atleast 2 characters)">													
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openTaxMod('P')">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="price_label"><spring:message code="itemmstr.jsp.price" text="Price" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<input type="text" id="itemPrice" onkeydown="numcheck(event)" value="${itemMaster.price}" class="form-control-trx" name="price" placeholder="<spring:message code="itemmstr.jsp.price" text="Price" />">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="rack_label"><spring:message code="itemmstr.jsp.rack" text="Rack" /></label>
											<div class="col-sm-8">
												<select class="form-control-trx" name="rackId" id="rackSelect">
													<c:if test="${!empty allRacks}">
														<option value="0">Select...</option>
														<c:forEach items="${allRacks}" var="allRack">
															<option value="${allRack.id}">${allRack.name}</option>
														</c:forEach>
													</c:if>
												</select> <input type="hidden" id="edited_rackId" value="${itemMaster.rackId}" />
											</div>
										</div>
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="isonmrp_label"><spring:message code="itemmstr.jsp.isonmrp" text="IsOnMRP" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<select class="form-control-trx" name="isOnMrp" id="isonmrp">
													<option value="0">NO</option>
													<option value="1">YES</option>
												</select>
												<input type="hidden" id="edited_isonmrpId" value="${itemMaster.isOnMrp}" />
											</div>
										</div>										
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label">HSN Code</label>
											<div class="col-sm-8">
											 <input type="text" value="${itemMaster.hsnCode}" id="hsnCode" class="form-control-trx" name="hsnCode" placeholder="HSN Code">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-sm-12">
									<div class="form-horizontal">
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="code_label"><spring:message code="itemmstr.jsp.code" text="Item Code" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<input type="text" id="itemCode" readonly="readonly" value="${itemMaster.code}" class="form-control-trx" name="code" placeholder="<spring:message code="itemmstr.jsp.code" text="Item Code" />">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="cntnt_label"><spring:message code="itemmstr.jsp.content" text="Content" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="content_id" value="${itemMaster.contentId}"></input> <input type="hidden" id="content_Dets" value="${itemMaster.contentMaster.name}"></input> <input type="hidden" id="contentId" value="${itemMaster.contentId}" class="form-control-trx" name="contentId"><input type="text" id="itemContent" value="${itemMaster.contentMaster.name}" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.content" text="Content" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button type="button" class="btn btn-default" id="contentdetails" onclick="contentDetailsMod()" style="height: 140%;">
															<i class="fa fa-info-circle" aria-hidden="true"></i>
														</button>
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openContentMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>

										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="cat_label"><spring:message code="itemmstr.jsp.category" text="Category" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">

												<input type="text" value="${itemMaster.categoryMaster.name}" id="catSelect" class="form-control-trx" readonly="readonly" />

												<!-- <select class="form-control-trx" name="categoryId" id="catSelect">
												<option value="1">Category 1</option>
												<option value="2">Category 2</option>
											</select> -->
												<input type="hidden" name="categoryId" value="${itemMaster.categoryId}" />
											</div>
										</div>
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="subCat_label"><spring:message code="itemmstr.jsp.subcat" text="Sub-Category" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">

												<input type="text" value="${itemMaster.subCategoryMaster.name}" id="subCatSelect" class="form-control-trx" readonly="readonly" />

												<!-- <select class="form-control-trx" name="subCategoryId" id="subCatSelect">
												<option value="1">Sub-Category 1</option>
												<option value="2">Sub-Category 2</option>
											</select> -->
												<input type="hidden" name="subCategoryId" value="${itemMaster.subCategoryId}" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="mnfctr_label"><spring:message code="itemmstr.jsp.manufacturer" text="Manufacturer" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="manufacturerId" value="${itemMaster.manufacturerId}" class="form-control-trx" name="manufacturerId"><input type="text" id="itemManufacturer" value="${itemMaster.manufacturerMaster.name}" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.manufacturer" text="Manufacturer" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openManufactMod()">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="cnvrsn_label"><spring:message code="purinvdet.jsp.ratio" text="Ratio" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<input type="text" id="itemconversion" value="${itemMaster.conversion}" class="form-control-trx" name="conversion" placeholder="<spring:message code="purinvdet.jsp.ratio" text="Ratio" />">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.isLseSale" text="Is Loose Sale" /></label>
											<div class="col-sm-8">
												<input type="checkbox" id="lseSaleChk" name="lseSaleChk" style="zoom: 1.5; vertical-align: middle; margin: 0px;" onchange="lseSaleChkBox();"><input type="hidden" name="isLooseSale" id="isLooseSale" value="${itemMaster.isLooseSale}" />
											</div>
										</div>
										<div class="form-group hide" id="lseUnitDiv">
											<label class="col-sm-4 col-sm-4 control-label" id="lseUnit_label"><spring:message code="itemmstr.jsp.looseunit" text="Loose Unit" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<div class="input-group">
													<input type="hidden" id="looseUnitId" value="${itemMaster.looseUnitId}" class="form-control-trx" name="looseUnitId"><input type="text" id="itemLooseUnit" value="${itemMaster.looseUnit.description}" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.looseunit" text="Loose Unit" />(Please type atleast 2 characters)">
													<div class="input-group-btn">
														<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openUnitMod(1)">
															<i class="fa fa-plus"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group hide">
											<label class="col-sm-4 col-sm-4 control-label" id="reOrderUnit_label"><spring:message code="itemmstr.jsp.Reorderunit" text="Re-Order Unit" /> <span class="required_star">*</span></label>
											<div class="col-sm-8">
												<input type="hidden" value="${itemMaster.reorderLevelUnitId}" id="reorderLevelUnitId" class="form-control-trx" name="reorderLevelUnitId"> <input type="text" id="itemreorderUnitId" readonly="readonly" value="${itemMaster.packUnit.description}" class="form-control-trx" placeholder="<spring:message code="itemmstr.jsp.Reorderunit" text="Re-Order Unit" />(Please type atleast 2 characters)">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label" id="netcntnt_label"><spring:message code="itemmstr.jsp.netcontent" text="Net Content" /></label>
											<div class="col-sm-8">
												<input type="text" id="netcontent" value="${itemMaster.netContent}" class="form-control-trx" name="netContent" placeholder="<spring:message code="itemmstr.jsp.netcontent" text="Net Content" />">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="reprintcash.jsp.note" text="Note" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemRemarks" value="${itemMaster.note}" class="form-control-trx" name="note" placeholder="<spring:message code="reprintcash.jsp.note" text="Note" />">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.markupperc" text="Markup (%)" /></label>
											<div class="col-sm-8">
												<input type="number" id="itemMarkup" value="${itemMaster.markup}" class="form-control-trx" name="markup" placeholder="<spring:message code="itemmstr.jsp.markupperc" text="Markup (%)" />">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label"><spring:message code="itemmstr.jsp.strength" text="Strength" /></label>
											<div class="col-sm-8">
												<input type="text" id="itemstrength" value="${itemMaster.strength}" class="form-control-trx" name="strength" placeholder="<spring:message code="itemmstr.jsp.strength" text="Strength" />">
											</div>
										</div>										
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label">Is Discount</label>
											<div class="col-sm-8">
											<input type="hidden" id="isDiscounthid" value="${itemMaster.isDiscount}">
												<select class="form-control-trx" name="isDiscount" id="isDiscount">
													<option value="1">YES</option>
													<option value="0">NO</option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label">Max Discount%</label>
											<div class="col-sm-8">
												<input type="text" id="maxDiscountLimit" onkeydown="numcheck(event)" value="${itemMaster.maxDiscountLimit}" class="form-control-trx" name="maxDiscountLimit" placeholder="Max Discount%">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 col-sm-4 control-label">Discount%</label>
											<div class="col-sm-8">
												<input type="text" id="discount" onkeydown="numcheck(event)" value="${itemMaster.discount}" class="form-control-trx" name="discount" placeholder="Discount%">
											</div>
										</div>
									</div>
								</div>

							</c:otherwise>
						</c:choose>
						<div class="pull-right">
							<button type="button" class="btn btn-default" data-dismiss="modal" onclick="window.location.href='${pageContext.request.contextPath}/item/loaditem.htm'">
								<spring:message code="cmn.jsp.btn.close" text="Close" />
							</button>
							<c:if test="${menuByUserDTO.isAll==1}">
								<c:if test="${item_id==0}">
									<button class="btn btn-primary" type="button" id="itemaddbut" onclick="javascript:clicksub()">
										<i class="fa fa-plus"></i>
										<spring:message code="cmn.jsp.btn.add" text="Add" />
									</button>
								</c:if>
								<c:if test="${item_id!=0}">
									<button class="btn btn-info" type="button" id="itemupdatebut" onclick="javascript:clicksub()">
										<i class="fa fa-pencil"></i>
										<spring:message code="cmn.jsp.btn.update" text="Update" />
									</button>
									<button class="btn btn-theme04" type="button" onclick="showItemDelModal(${item_id})">
										<i class="fa fa-trash-o "></i>
										<spring:message code="cmn.jsp.btn.dlt" text="Delete" />
									</button>
								</c:if>
								<!-- 								<input type="submit" value="Submit" /> -->
							</c:if>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</section>

<!-- Content Details modal start -->
<div class="modal fade" id="contentDetailsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
						<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="content.jsp.name" text="Content" /> </label>
						<div class="col-sm-8">
							<textarea id="contentVal" class="form-control-trx" rows="4"></textarea>
						</div>
					</div>
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
<!-- Content Details modal end -->

<div class="modal fade" id="confirmMessageModalNewItem" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!" />
				</h4>
			</div>
			<div class="modal-body">
				<input type="hidden" id="rqstType" />
				<input type="hidden" id="objctId" />
				<input type="hidden" id="objctName" />
				<input type="hidden" id="objctType" />
				<input type="hidden" id="taxPrcnt" />
				<div id="confirmmessagecontNewItem"></div>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-theme" onclick="targetAction();">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>

<!-- Add/Edit tax modal start -->
<div class="modal fade" id="taxAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
	        <h4 class="modal-title" id="myModalLabel">
	        	<span id="taxheadertext"></span>
	        </h4>
	      </div>
	      <div class="modal-body">
	        <div class="form-horizontal">
	        	<div class="form-group">
	        	<input type="hidden" id="taxId" value=""></input><input type="hidden" id="taxType" />
                         			<label class="col-sm-4 col-sm-4 control-label" id="taxCat_label">Tax Category<span class="required_star">*</span></label>
                         			
                         			<div class="col-sm-8">
                         				<select id="taxMode" class="form-control">
                         					<option value="NOTAX">NOTAX</option>
                         					<option value="VAT">VAT</option>
                         					<option value="GST">GST</option>
                         					<option value="CGST">CGST</option>
                         					<option value="SGST">SGST</option>
                         					<option value="IGST">IGST</option>
                         					<option value="OTHER">OTHER</option>
                         				</select>
                         			</div>
                     			</div>
	        	<div class="form-group">
                         			<label class="col-sm-4 col-sm-4 control-label" id="taxName_label">Name <span class="required_star">*</span></label>
                         			<div class="col-sm-8">
                             			<input type="text" id="taxName" class="form-control">
                         			</div>
                     			</div>
                     			<div class="form-group">
                         			<label class="col-sm-4 col-sm-4 control-label" id="taxPer_label">Percentage <span class="required_star">*</span></label>
                         			<div class="col-sm-8">
                             			<input type="text" id="taxPer" class="form-control">
                         			</div>
                     			</div>
                     			<div class="form-group">
                         			<label class="col-sm-4 col-sm-4 control-label" id="taxDesc_label">Description <span class="required_star">*</span></label>
                         			<div class="col-sm-8">
                             			<input type="text" id="taxDesc" class="form-control">
                         			</div>
                     			</div>
                     			<div class="form-group">
                         			<label class="col-sm-4 col-sm-4 control-label" id="taxIsgrp_label">Is Group<span class="required_star">*</span></label>
                         			
                         			<div class="col-sm-8">
                         				<select id="taxIsGrp" onchange="makeGroupStat();" class="form-control">
                         					<option value="0">No</option>
                         					<option value="1">Yes</option>
                         				</select>
                         			</div>
                     			</div>
                     			<div id="snglTaxDiv" class="form-group hide">
                         			<table id="singleTaxListTbl" class="table table-bordered table-striped table-condensed-trx table-hover">
                         				<thead>
                         					<tr>
                         						<th></th>
                         						<th>Name</th>
                         						<th>Percentage</th>
                         					</tr>
                         				</thead>
                         				<tbody id="singleTaxList"></tbody>
                         			</table>
                     			</div>
	        </div>
	        <input type="hidden" id="tax_id" value=""></input>
	        <div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsgTax"></div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
	        <button type="button" id="saveTaxBtn" onclick="javascript:addEditTax()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.save" text="SAVE" /></button>
	        <button type="button" id="updateTaxBtn" onclick="javascript:addEditTax()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.update" text="Update" /></button>
	      </div>
	    </div>
	  </div>
</div>      
<!-- Add/Edit tax modal end -->

<!-- Add/Edit unit modal start -->
<div class="modal fade" id="unitAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<span id="headertext">Add Unit</span>
				</h4>
			</div>
			<div class="modal-body">
				<div class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label" id="code_label"><spring:message code="unit.jsp.tblhdr.code" text="Unit Code" /> <span class="required_star">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="unitCodeId" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label" id="desc_label"><spring:message code="unit.jsp.tblhdr.desc" text="Unit Description" /> <span class="required_star">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="unitDescId" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label" id="type_label"><spring:message code="unit.jsp.tblhdr.typeId" text="Unit Type Id" /><span class="required_star">*</span></label>

						<div class="col-sm-8">
							<select id="unitTypeId" class="form-control">
								<option value="1">Loose Unit</option>
								<option value="2">Packing Unit</option>
							</select>
						</div>

						<!-- <div class="col-sm-8">
                                  			<input type="text" id="unitTypeId" class="form-control">
                              			</div> -->
					</div>
				</div>
				<input type="hidden" id="unit_id" value=""></input>
				<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsgunit"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="javascript:addEditUnit()" class="btn btn-theme">
					<spring:message code="cmn.jsp.btn.save" text="SAVE" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Add/Edit unit modal end -->

<!-- Add/Edit brand modal start -->
<div class="modal fade" id="brandAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<span id="headertextbrand">Add Brand</span>
				</h4>
			</div>
			<div class="modal-body">
				<div class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="brand.jsp.tblhdr.name" text="Brand Name" /> <span class="required_star">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="brandName" class="form-control">
						</div>
					</div>
				</div>
				<input type="hidden" id="brandId" value=""></input>
				<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsgbrand"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="javascript:addEditBrand()" class="btn btn-theme">
					<spring:message code="cmn.jsp.btn.save" text="SAVE" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Add/Edit brand modal end -->
<!-- Add/Edit Content modal start -->
<div class="modal fade" id="contentAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<span id="headertext">Add Content</span>
				</h4>
			</div>
			<div class="modal-body">
				<div class="form-horizontal">

					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="content.jsp.name" text="Content Name" /> <span class="required_star">*</span></label>
						<div class="col-sm-8">
							<textarea id="content_name" class="form-control" rows="4"></textarea>
						</div>
					</div>
				</div>
				<input type="hidden" id="content_id" value=""></input>
				<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsgcontent"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="javascript:addEditContent()" class="btn btn-theme">
					<spring:message code="cmn.jsp.btn.save" text="SAVE" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Add/Edit Content modal end -->
<!-- Add/Edit Manufacturer modal start -->
<div class="modal fade" id="manufacturerAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<span id="headertext">Add Manufacturer</span>
				</h4>
			</div>
			<div class="modal-body">
				<div class="form-horizontal">

					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label" id="code_label"><spring:message code="manufacturer.jsp.code" text="Manufacturer Code" /> <span class="required_star">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="manufctr_code" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="manufacturer.jsp.name" text="Manufacturer Name" /> <span class="required_star">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="manufctr_name" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label" id="addrs_label"><spring:message code="manufacturer.jsp.addrs" text="Address" /> <span class="required_star">*</span></label>
						<div class="col-sm-8">
							<textarea id="addrsmnuf" class="form-control" rows="4"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label"><spring:message code="manufacturer.jsp.phn" text="Contact No." /></label>
						<div class="col-sm-8">
							<input type="text" id="phn" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label"><spring:message code="manufacturer.jsp.fax" text="FAX" /></label>
						<div class="col-sm-8">
							<input type="text" id="fax" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label"><spring:message code="manufacturer.jsp.email" text="Email" /></label>
						<div class="col-sm-8">
							<input type="text" id="email" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 col-sm-4 control-label"><spring:message code="manufacturer.jsp.url" text="URL" /></label>
						<div class="col-sm-8">
							<input type="text" id="url" class="form-control" />
						</div>
					</div>
				</div>
				<input type="hidden" id="manufctr_id" value=""></input>
				<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsgmanu"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button type="button" onclick="javascript:addEditManufacturer()" class="btn btn-theme">
					<spring:message code="cmn.jsp.btn.save" text="SAVE" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Add/Edit Manufacturer modal end -->

<!-- Add/Edit group modal start -->
	<div class="modal fade" id="groupAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
		        <h4 class="modal-title" id="myModalLabel">
		        	<span id="headertext">Add Group</span>
		        </h4>
		      </div>
		      <div class="modal-body">
		        <div class="form-horizontal">
		        	<div class="form-group">
                          			<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="grp.jsp.name" text="Group Name" /> <span class="required_star">*</span></label>
                          			<div class="col-sm-8">
                              			<input type="text" id="grpName" class="form-control">
                          			</div>
                      			</div>
                      			<div class="form-group">
                          			<label class="col-sm-4 col-sm-4 control-label" id="desc_label"><spring:message code="grp.jsp.desc" text="Group Description" /> <span class="required_star">*</span></label>
                          			<div class="col-sm-8">
                              			<input type="text" id="grpDesc" class="form-control">
                          			</div>
                      			</div>
                      		 </div>
		        <input type="hidden" id="grp_id" value=""></input>
		        <div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsgGroup"></div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
		        <button type="button" onclick="javascript:addEditGroup()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.save" text="SAVE" /></button>
		      </div>
		    </div>
		  </div>
	</div>      
<!-- Add/Edit group modal end -->

<!-- Add/Edit Schedule Modal Starts -->
				
	<div class="modal fade" id="scheduleAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
	        <h4 class="modal-title" id="myModalLabel">
	        	<span id="scheheadertext"></span>
	        </h4>
	      </div>
	      <div class="modal-body">
	        <div class="form-horizontal">
	        	<div class="form-group">
                         			<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="schedule.jsp.scheduleName" text="Schedule Name" /> <span class="required_star">*</span></label>
                         			<div class="col-sm-8">
                             			<input type="text" id="scheduleName" class="form-control">
                         			</div>
                     			</div>
	        </div>
	        <input type="hidden" id="scheduleId" value="">
	        <div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsgSche"></div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
	        <button type="button" onclick="javascript:addEditSchedule()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.save" text="SAVE" /></button>
	      </div>
	    </div>
	  </div>
</div> 
				
<!-- Add/Edit Schedule Modal Ends -->
<script src="${pageContext.request.contextPath }/assets/js/inventory/item/item.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/inventory/item/item_en.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/common/commonnewitem_en.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/inventory/tax/tax_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>
<script type="text/javascript">
	var BASE_URL = "${pageContext.request.contextPath}";
	$(document).ready(function(){
		$('#isDiscount').val($("#isDiscounthid").val());isLooseSale
		if($("#isLooseSale").val()==1)
		{
			$("#lseSaleChk").attr("checked",true);
			$("#lseUnitDiv").removeClass("hide");
		}
		else
		{
			$("#lseSaleChk").attr("checked",false);
			$("#lseUnitDiv").addClass("hide");
		}
	});
	function showConfirmModal()
	{
		$('#confirmMessageModal').modal('show');
	}
	function showConfirmModalNewItem() {
		$('#confirmMessageModalNewItem').modal('show');
	}
	function showItemDelModal(id)
	{
		   document.getElementById('confirmId').value=id;
		   $('#confirmModal').modal('show');
	} 
	
	$(function() {

		$("#itemBrand").autocomplete({
			source : function(	request,
								response) {
				if (request.term.length >= 2) {
					$.ajax({
						url : BASE_URL + "/brand/autocompleteitembrand.htm",
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
				$("#brandId").val(ui.item.itemCode);
				// $("#itemCode").val(ui.item.itemCode);
				//$("#autocompleteItemId").val(ui.item.itemCode);
				/*var disc = 0.0;
				if (ui.item.items.promotionFlag == 'Y')
					disc = ui.item.items.promotionValue;
				additemtoOrder(ui.item.items.id, ui.item.items.name, ui.item.items.price, disc, ui.item.items.vat, ui.item.items.serviceTax, ui.item.items.promotionFlag, ui.item.items.promotionValue);*/

			},
			change : function(	e,
								ui) {
				if (!(ui.item))
					e.target.value = "";
			},

		});

		$("#itemManufacturer").autocomplete({
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
				$("#manufacturerId").val(ui.item.itemCode);
			},
			change : function(	e,
								ui) {
				if (!(ui.item))
					e.target.value = "";
			},
		});

		$("#itemContent").autocomplete({
			source : function(	request,
								response) {
				if (request.term.length >= 2) {
					$.ajax({
						url : BASE_URL + "/content/autocompleteitemcontent.htm",
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
				$("#contentId").val(ui.item.itemCode);
				$("#content_id").val(ui.item.itemCode);
				$("#content_Dets").val(ui.item.label);
			},
			change : function(	e,
								ui) {
				if (!(ui.item))
					e.target.value = "";
			},
		});

		$("#itemLooseUnit").autocomplete({
			source : function(	request,
								response) {
				if (request.term.length >= 2) {
					$.ajax({
						url : BASE_URL + "/invsetup/autocompleteitemlooseunit.htm",
						type : "GET",
						data : {
							tagName : request.term
						},

						dataType : "json",

						success : function(data) {
							response($.map(data, function(v) {
								return {
									label : v.code,
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
				$("#looseUnitId").val(ui.item.itemCode);
			},
			change : function(	e,
								ui) {
				if (!(ui.item))
					e.target.value = "";
			},
		});

		$("#itemPackingUnit").autocomplete({
			source : function(	request,
								response) {
				if (request.term.length >= 2) {
					$.ajax({
						url : BASE_URL + "/invsetup/autocompleteitempackingunit.htm",
						type : "GET",
						data : {
							tagName : request.term
						},

						dataType : "json",

						success : function(data) {
							response($.map(data, function(v) {
								return {
									label : v.code,
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
				//$("#itemid").val(ui.item.itemCode);
				$("#packUnitId").val(ui.item.itemCode);
				$("#reorderLevelUnitId").val(ui.item.itemCode);
				$("#itemreorderUnitId").val(ui.item.label);
				$("#looseUnitId").val(ui.item.itemCode);
				$("#itemLooseUnit").val(ui.item.label);
			},
			change : function(	e,
								ui) {
				if (!(ui.item))
					e.target.value = "";
			},
		});

		$("#itemreorderUnitId").autocomplete({
			source : function(	request,
								response) {
				if (request.term.length >= 2) {
					$.ajax({
						url : BASE_URL + "/invsetup/autocompleteitempackingunit.htm",
						type : "GET",
						data : {
							tagName : request.term
						},

						dataType : "json",

						success : function(data) {
							response($.map(data, function(v) {
								return {
									label : v.code,
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
				$("#reorderLevelUnitId").val(ui.item.itemCode);

			},
			change : function(	e,
								ui) {
				if (!(ui.item))
					e.target.value = "";
			},
		});
		
		$("#purchaseTax").autocomplete({
			source : function(	request,
								response) {
				if (request.term.length >= 2) {
					$.ajax({
						url : BASE_URL + "/tax/gettaxlistforautocomplete.htm",
						type : "GET",
						data : {
							tagName : request.term
						},

						dataType : "json",

						success : function(data) {
							response($.map(data, function(v) {
								console.log("v="+v);
								return {
									label : v.taxName,
									itemCode : v.taxId,
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
				$("#purchaseTaxId").val(ui.item.itemCode);
				$("#purchaseTaxPerc").val(ui.item.items.percentage);
				$("#saleTax").val(ui.item.label);
				$("#saleTaxId").val(ui.item.itemCode);
				$("#saleTaxPerc").val(ui.item.items.percentage);

			},
			change : function(	e,
								ui) {
				if (!(ui.item))
					e.target.value = "";
			},
		});
		
		$("#saleTax").autocomplete({
			source : function(	request,
								response) {
				if (request.term.length >= 2) {
					$.ajax({
						url : BASE_URL + "/tax/gettaxlistforautocomplete.htm",
						type : "GET",
						data : {
							tagName : request.term
						},

						dataType : "json",

						success : function(data) {
							response($.map(data, function(v) {
								return {
									label : v.taxName,
									itemCode : v.taxId,
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
				$("#saleTaxId").val(ui.item.itemCode);
				$("#saleTaxPerc").val(ui.item.items.percentage);

			},
			change : function(	e,
								ui) {
				if (!(ui.item))
					e.target.value = "";
			},
		});

	});
</script>