<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="printpur.jsp.title" text="Purchase Invoice"/></title>
</head>
<body>

<div id="printdiv" class="print hide">
<div style="margin: 0 auto;height: auto;width: 100%;float: left;font-family: monospace;">
	<p style="text-align: center;"><spring:message code="printpur.jsp.title" text="Purchase Invoice"/><br>
	<span style="text-align: center;"><b>${sessionScope.sesloggedinStore.name}</b></span><br>
	<span style="text-align: center;">Chemist & Druggist</span><br>
	<span style="text-align: center;">${sessionScope.sesloggedinStore.address},${sessionScope.sesloggedinStore.phone}</span><br>
	<span style="text-align: center;">D.L.No.: ${sessionScope.sesloggedinStore.dlLicenceNo} ,State Membership No. : ${sessionScope.sesloggedinStore.stateLicenceNo}</span></p>
	<div style="float: center;height: auto;width: 100%;margin-bottom: 15px;">
		<div id="left_div" style="float: left">
			<div>
       			<label style="width: 10%;font-weight: 600;"><spring:message code="printpur.jsp.vendorname" text="Vendor Name" />. :</label>
       			<label id="invNo_label">${distributorDetails.name}</label>
   			</div>
			<div>
       			<label style="width: 10%;font-weight: 600;"><spring:message code="printpur.jsp.vendoraddrs" text="Vendor Address" />:</label>
       			<label id="dctrName_label">${distributorDetails.address}</label>
   			</div>
		</div>
		<div id="right_div" style="float: right;">		
			<div>
       			<label style="width: 10%;font-weight: 600;"><spring:message code="purinvdet.jsp.invno" text="Invoice No" />. :</label>
       			<label id="invNo_label">${purchaseHeader.invNo}</label>
   			</div>			
			<div>
				<fmt:parseDate value="${purchaseHeader.invDate}" var="parsedInvDate" pattern="MMM dd, yyyy" />
       			<label style="width: 10%;font-weight: 600;"><spring:message code="rtrnmemo.jsp.salereturnmodal.invdt" text="Invoice Date" />:</label>
       			<label><fmt:formatDate pattern="yyyy-MM-dd" value="${parsedInvDate}" /></label>
   			</div>
			<div>
       			<label style="width: 10%;font-weight: 600;"><spring:message code="cmn.print.jsp.vattincstno" text="GSTIN" />.:</label>
       			<label>${sessionScope.sesloggedinStore.taxRegNo}</label>
   			</div>
		</div>
	</div>
	<div style="float: center;margin-bottom: 5px;width: 100%;">
			<table id="peitem" style="text-align: center; border-collapse: collapse;border-bottom: 1px dashed;width: 100%;">
				<thead>
					<tr style="border-bottom: 1px dashed;">
						<th><spring:message code="reprintcash.jsp.invno" text="SNo" /></th>
						<th><spring:message code="item.jsp.name" text="Item Name" /></th>
						<th><spring:message code="purinvdet.jsp.mfg" text="MFG" /></th>
						<%-- <th width="17%;"><spring:message code="printpur.jsp.pack" text="Pack" /></th> --%>
						<th><spring:message code="purinvdet.jsp.batch" text="Batch"/></th>
						<th><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
						<th>&nbsp;<spring:message code="printpur.jsp.taxprcnt" text="Tax%" /></th>
						<%-- <th width="5%;">&nbsp;<spring:message code="printpur.jsp.edprcnt" text="Ed%" /></th> --%>
						<th>&nbsp;<spring:message code="reprintcash.jsp.dpcnt" text="Disc%" /></th>
						<th>&nbsp;<spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
						<th>&nbsp;<spring:message code="purinvdet.jsp.rate" text="Rate" /></th>
						<th>Qty</th>
						<%-- <th><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></th> --%>
						<th><spring:message code="purinvdet.jsp.free" text="Free" /></th>
						<th>&nbsp;<spring:message code="printpur.jsp.amnt" text="Amount" /></th>
					</tr>
				 </thead>
				 <tbody>
				 <c:if test="${!empty purchaseDetails }">
					<c:forEach items="${purchaseDetails}" var="purchaseDetail" varStatus="loop">
						<tr>
							<td>${loop.index+1}</td>
							<td style="text-align: left;">${purchaseDetail.itemName}</td>
							<td style="text-align: left;">${purchaseDetail.manuCode}</td>
							<!-- <td width="17%;">10 Files</td> -->
							<td>${purchaseDetail.batchNo}</td>
							<td>${purchaseDetail.expiryDateFormat}</td>
							<td><fmt:formatNumber value="${purchaseDetail.taxPercentage}" type="number"/></td>
							<%-- <td width="5%;"><fmt:formatNumber value="${purchaseDetail.edPer}" currencySymbol="" type="currency"/></td> --%>
							<td><fmt:formatNumber value="${purchaseDetail.discPer}" type="number"/></td>
							<td style="text-align: right;"><fmt:formatNumber value="${purchaseDetail.mrp}" currencySymbol="" type="currency"/></td>
							<td style="text-align: right;"><fmt:formatNumber value="${purchaseDetail.rate}" currencySymbol="" type="currency"/></td>
							<td>${purchaseDetail.packQty}</td>
							<%-- <td>${purchaseDetail.looseQty}</td> --%>
							<td><fmt:formatNumber value="${purchaseDetail.freeQty}" type="number"/></td>
							<td style="text-align: right;"><fmt:formatNumber value="${purchaseDetail.amount}" currencySymbol="" type="currency"/></td>
						</tr>
					</c:forEach>
					</c:if>
				</tbody>
			</table>
	</div>
	<div style="float: center;margin-bottom: 5px;"> <!-- For Return -->
		<c:if test="${!empty purchaseReturnDetailsDTOs }">
		<fmt:parseDate value="${returnDt}" var="parsedReturnDate" pattern="MMM dd, yyyy" />
		<p><span style="text-decoration: underline;font-weight: bold;text-align: left;">Purchase Return Adjustment Note</span>&nbsp;&nbsp;&nbsp;<span>Return No: ${purReturnId}</span>&nbsp;&nbsp;&nbsp;<span>Date : <fmt:formatDate pattern="yyyy-MM-dd" value="${parsedReturnDate}" /></span></p><br>
			<table id="peitem_return" style="text-align: center; border-collapse: collapse;border-bottom: 1px dashed;width: 100%;">
				<thead>
					<tr style="border-bottom: 1px dashed;">
						<th><spring:message code="reprintcash.jsp.invno" text="SNo" /></th>
						<th><spring:message code="item.jsp.name" text="Item Name" /></th>
						<th><spring:message code="purinvdet.jsp.mfg" text="MFG" /></th>
						<%-- <th width="17%;"><spring:message code="printpur.jsp.pack" text="Pack" /></th> --%>
						<th><spring:message code="purinvdet.jsp.batch" text="Batch"/></th>
						<th><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
						<th>&nbsp;<spring:message code="printpur.jsp.taxprcnt" text="Tax%" /></th>
						<%-- <th width="5%;">&nbsp;<spring:message code="printpur.jsp.edprcnt" text="Ed%" /></th> --%>
						<th>&nbsp;<spring:message code="reprintcash.jsp.dpcnt" text="Disc%" /></th>
						<th>&nbsp;<spring:message code="purinvdet.jsp.mrp" text="MRP" /></th>
						<th>&nbsp;<spring:message code="purinvdet.jsp.rate" text="Rate" /></th>
						<th>Qty</th>
						<%-- <th><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></th> --%>
						<th><spring:message code="purinvdet.jsp.free" text="Free" /></th>
						<th>&nbsp;<spring:message code="printpur.jsp.amnt" text="Amount" /></th>
					</tr>
				 </thead>
				 <tbody>
					 <c:forEach items="${purchaseReturnDetailsDTOs}" var="purchaseReturnDetail" varStatus="loop">
						<tr>
							<td>${loop.index+1}</td>
							<td style="text-align: left;">${purchaseReturnDetail.itemName}</td>
							<td style="text-align: left;">${purchaseReturnDetail.manufacturerCode}</td>
							<!-- <td width="17%;">10 Files</td> -->
							<td>${purchaseReturnDetail.batchNo}</td>
							<td>${purchaseReturnDetail.expiryDateFormat}</td>
							<td><fmt:formatNumber value="${purchaseReturnDetail.taxPercentage}" currencySymbol="" type="currency"/></td>
							<%-- <td width="5%;"><fmt:formatNumber value="${purchaseReturnDetail.edPer}" currencySymbol="" type="currency"/></td> --%>
							<td><fmt:formatNumber value="${purchaseReturnDetail.discPer}" currencySymbol="" type="currency"/></td>
							<td style="text-align: right;"><fmt:formatNumber value="${purchaseReturnDetail.mrp}" currencySymbol="" type="currency"/></td>
							<td style="text-align: right;"><fmt:formatNumber value="${purchaseReturnDetail.rate}" currencySymbol="" type="currency"/></td>
							<td>${purchaseReturnDetail.packQty}</td>
							<%-- <td>${purchaseReturnDetail.looseQty}</td> --%>
							<td><fmt:formatNumber value="${purchaseReturnDetail.freeQty}" currencySymbol="" type="currency"/></td>
							<td style="text-align: right;"><fmt:formatNumber value="${purchaseReturnDetail.amount}" currencySymbol="" type="currency"/></td>
						</tr>
					</c:forEach> 
				</tbody>
			</table>			
		</c:if>
	</div>
	<br><br>
	<div style="float: center;width: 100%">
		<table width="100%">
			<tr>
				<td style="font-weight: 600;"><spring:message code="printpur.jsp.totitems" text="Items" />:</td>
				<td><span id="itemcount">0</span></td>
				<td style="font-weight: 600;"><spring:message code="printpur.jsp.subtot" text="Sub Total" />:</td>
				<td><fmt:formatNumber value="${purchaseHeader.grossAmount}" currencySymbol="" type="currency"/></td>
				<td style="font-weight: 600;"><spring:message code="purinvdet.jsp.totmrp" text="Tot.MRP" />:</td>
				<td><fmt:formatNumber value="${purchaseHeader.totalMrp}" currencySymbol="" type="currency"/></td>
				<td style="font-weight: 600;"><spring:message code="printpur.jsp.nettot" text="Net Total" />:</td>
				<td style="font-weight: 600;"><fmt:formatNumber value="${purchaseHeader.netAmount}" currencySymbol="" type="currency"/></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr>	
				<td style="font-weight: 600;"><spring:message code="purinvdet.jsp.tottax" text="Tot.Tax" />:</td>
				<td><fmt:formatNumber value="${purchaseHeader.taxAmount}" currencySymbol="" type="currency"/></td>
				<%-- <td width="8%;" style="font-weight: 600;"><spring:message code="printpur.jsp.toted" text="Tot.ED" />:</td>
				<td width="5%;"><fmt:formatNumber value="${purchaseHeader.edAmount}" currencySymbol="" type="currency"/></td> --%>
				<td style="font-weight: 600;"><spring:message code="purinvdet.jsp.totdisc" text="Tot.Disc" />:</td>
				<td><fmt:formatNumber value="${purchaseHeader.discAmount}" currencySymbol="" type="currency"/></td>
				<td style="font-weight: 600;"><spring:message code="reprintcash.jsp.roff" text="RoundOff" />:</td>
				<td><fmt:formatNumber value="${purchaseHeader.roundoff}" currencySymbol="" type="currency"/></td>
				<td style="font-weight: 600;"><spring:message code="purinvdet.jsp.retadj" text="Ret.Adj" />:</td>
				<td><fmt:formatNumber value="${purchaseHeader.adjAmount}" currencySymbol="" type="currency"/></td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<%-- <td width="1%;" style="font-weight: 600;"><spring:message code="purinvdet.jsp.retadj" text="Ret.Adj" />:</td>
				<td width="9%;"><fmt:formatNumber value="${purchaseHeader.adjAmount}" currencySymbol="" type="currency"/></td> --%>
				<td style="font-weight: 600;"><spring:message code="printpur.jsp.ltadjamt" text="Lt.Adj.Amt" />:</td>
				<td><fmt:formatNumber value="${purchaseHeader.lotAdjAmount}" currencySymbol="" type="currency"/></td>
			</tr>
		</table>
		<table>
			<tr>
				<td width="15%;" style="font-weight: 600;"><spring:message code="printpur.jsp.frvendor" text="For" />&nbsp;${distributorDetails.name}:</td>
			</tr>
		</table>
		<table>
			<tr>
				<td width="10%;" style="font-weight: 600;"><spring:message code="reprintcash.jsp.note" text="Note" />:</td>
				<td></td>
			</tr>
		</table>
		<input type="hidden" id="backUrl" value="${backUrl}" />
	</div>
</div>	
</div>


<script src="${pageContext.request.contextPath }/assets/js/common/jquery-1.8.3.min.js"> </script>
<script type="text/javascript">
var BASE_URL = "${pageContext.request.contextPath}";
$(document).ready(function(){
	var rowCount = $('#peitem >tbody >tr').length;
	$("#itemcount").text(rowCount);
	window.print();
	setTimeout(function(){ location.href = BASE_URL + '/purinv/'+$("#backUrl").val()+'.htm';}, 50);////13-2-2020
	/* location.href = BASE_URL + '/purinv/'+$("#backUrl").val()+'.htm'; */
});

</script>

</body>
</html>