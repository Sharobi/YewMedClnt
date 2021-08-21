<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="reprintcash.jsp.title"
		text="Cash Memo" /></title>
<style>
body {
	font-size: 9px;
}

table {
	border-collapse: collapse;
}

table tr th {
	font-weight: normal;
}
/* table  tr td {
    border: 1px solid;
} */
</style>
</head>
<body>

	<div id="printdiv" class="print hide">
		<div
			style="margin: 0 auto; height: auto; width: 100%; float: center; font-family: verdana;">
			<input type="hidden" value="${reprint}" id="reprint_stat" />
			<%-- <p id="reprint_p" class="hide"
				style="text-align: center; margin: 0; padding: 0;">
				<spring:message code="reprintcash.jsp.tag" text="Reprint" />
				: ${printCountRes}
			</p> --%>
			<c:choose>
				<c:when test="${saleHeaderDTO.creditAmount gt 0.00}">
					<p style="text-align: center; margin: 0; padding: 0;">
						<spring:message code="reprintcredit.jsp.title" text="Credit Memo" />
						/
						<spring:message code="reprintcredit.jsp.prescrptnreg"
							text="Prescription Register" />
					</p>
				</c:when>
				<c:otherwise>
					<p style="text-align: center; margin: 0; padding: 0;">
						<%-- <spring:message code="reprintcash.jsp.title" text="Cash Memo" />
						/
						<spring:message code="reprintcredit.jsp.prescrptnreg"
							text="Prescription Register" /> --%>
							<span style="font-size:10px; font-weight: bold;" ><spring:message code="reprintcredit.jsp.sndtitle"
							text="Tax Invoice / Cash Memo cum / Money Receipt" /></span>
					</p>
				</c:otherwise>
			</c:choose>
			<p id="storecontId"
				style="text-align: center; margin: 0; padding: 0;">
				<b>${sessionScope.sesloggedinStore.name}</b>
			</p>
			<p id="taglinecontId"
				style="text-align: center; margin: 0; padding: 0;"></p>
			<p id="addcontId" style="text-align: center; margin: 0; padding: 0;">Chemist
				& Druggist,
				${sessionScope.sesloggedinStore.address},<br>				
				Phone: ${sessionScope.sesloggedinStore.phone}</p>
			<p id="dlcontId" style="text-align: center; margin: 0; padding: 0;">
				D.L.No.: ${sessionScope.sesloggedinStore.dlLicenceNo} ,<%-- State Membership No. :
				${sessionScope.sesloggedinStore.stateLicenceNo} --%><br>
				GST NO : ${sessionScope.sesloggedinStore.taxRegNo}
				</p>

			<div
				style="float: center; height: auto; width: 100%; margin-bottom: 5px;">
				<div id="billinginfo_div" style="margin-bottom:30px;">
					<%-- <div>
       			<label style="width: 10%;font-weight: 600;"><b><spring:message code="reprintcash.jsp.billdetails" text="Bill Details" /></b>:</label>
   			</div> --%>
					<c:if test="${saleHeaderDTO.esiType!=''}">
						<div id="etype">
							<label style="width: 10%;">Bill Type:
								${saleHeaderDTO.esiType}</label>
						</div>
						<div id="ccode">
							<label style="width: 10%;">Card No:
								${saleHeaderDTO.customerCode}</label>
						</div>
					</c:if>
					
					
					
					<div>
						<label style="width: 10%; font-size: 12px;"> Consignee </label>
					</div>
					<div>--------------------------------------------</div>
						<c:if test="${saleHeaderDTO.consiName==''}">
						<div>
						<label style="width: 10%;">
						<%-- <spring:message
								code="reprintcash.jsp.ptntname" text="Patient Name" />:</label>  --%>
								Name:</label>
								<label
							id="patientName_label"><b>${saleHeaderDTO.customerName}</b>
							<%-- <span id="genders">${saleHeaderDTO.gender}</span></label> --%>
							</label>
					</div>
					<c:if test="${saleHeaderDTO.customerPhone!=''}">
					<div>
						<label style="width: 10%;"><%-- <spring:message
								code="reprintcash.jsp.ptntphn" text="Patient Ph No." /> --%>
								Ph No.:</label> <label
							id="patientName_label">${saleHeaderDTO.customerPhone}</label>
					</div>
					</c:if>
					<div style="width: 50%;">
						<label style="width: 10%;"><%-- <spring:message
								code="reprintcash.jsp.ptntaddr" text="Patient Address" /> --%>
								Address:</label> <label
							id="patientName_label">${saleHeaderDTO.customerAddress}</label>
					</div>
					<c:if test="${saleHeaderDTO.customerGstNo!=''}">
						<div id="placetreatment">
							<label style="width: 10%;">GstNo:</label> <label>${saleHeaderDTO.customerGstNo}</label>
						</div>
					</c:if>
						
					</c:if>
					<c:if test="${saleHeaderDTO.consiName!=''}">
					<div>
						<label style="width: 10%;">Name:</label> <label
							id="consiName_label"><b>${saleHeaderDTO.consiName}</b></label>
					</div>
					<div style="width:50%;">
						<label style="width: 10%;">Address:</label> <label
							id="consiAddress_label">${saleHeaderDTO.consiAddress}</label>
					</div>
					
					<c:if test="${saleHeaderDTO.consiGstNo!=''}">
					<div>
						<label style="width: 10%;">GstNo:</label> <label
							id="consiGstNo_label">${saleHeaderDTO.consiGstNo}</label>
					</div>
					</c:if>
					
					<div>
						<label style="width: 10%;">Phone:</label> <label
							id="consiPhone_label">${saleHeaderDTO.customerPhone}</label>
					</div>
					
					</c:if>
					
					
					<c:if test="${saleHeaderDTO.esiType=='RSBY'}">
						<div id="placetreatment">
							<label style="width: 10%;">Place Of Treatment:</label> <label>${saleHeaderDTO.remarks}</label>
						</div>
					</c:if>
					<%-- <div>
						<label style="width: 10%;"><spring:message
								code="dctr.jsp.name" text="Doctor's Name" />:</label> <label
							id="dctrName_label">${saleHeaderDTO.doctorName}</label>
					</div> --%>
					<div>--------------------------------------------</div>
					<div>
						<label style="width: 10%; font-size: 12px;"> Buyers (If other than cosignee) </label>
					</div>
						<div>
						<label style="width: 10%;">
						<%-- <spring:message
								code="reprintcash.jsp.ptntname" text="Patient Name" />:</label>  --%>
								Name:</label>
								<label
							id="patientName_label"><b>${saleHeaderDTO.customerName}</b>
							<%-- <span id="genders">${saleHeaderDTO.gender}</span></label> --%>
					</div>
					<div style="width:50%">
						<label style="width: 10%;"><%-- <spring:message
								code="reprintcash.jsp.ptntaddr" text="Patient Address" /> --%>Address1:</label> <label
							id="patientAddr1_label"><span style="word-break: break-all;">${saleHeaderDTO.guardianName}</span></label>
					</div>
					<div style="width:50%">
						<label style="width: 10%;"><%-- <spring:message
								code="reprintcash.jsp.ptntaddr" text="Patient Address" /> --%>Address2:</label> <label
							id="patientAddr2_label"><span style="word-break: break-all;">${saleHeaderDTO.customerAddress}</span></label>
					</div>
					<%-- <div>
						<label style="width: 10%;"><spring:message
								code="reprintcash.jsp.ptntphn" text="Patient Ph No." />
								Ph No.:</label> <label
							id="patientName_label">${saleHeaderDTO.customerPhone}</label>
					</div> --%>
					
					<c:if test="${saleHeaderDTO.customerGstNo!=''}">
						<div id="placetreatment">
							<label style="width: 10%;">GstNo:</label> <label>${saleHeaderDTO.customerGstNo}</label>
						</div>
					</c:if>

				</div>
				
				<div id="invinfo_div">
				
					<c:if test="${saleHeaderDTO.esiType!=''}">
					
						<div id="slno">
							<label style="width: 10%;">SL No: ${saleHeaderDTO.slipNo}</label>
						</div>
						<div id="pid">
							<fmt:parseDate value="${saleHeaderDTO.prescriptionIssueDate}"
								var="pissuDate" pattern="MMM dd, yyyy" />
							<label style="width: 10%;">Pres. Issue Dt: <fmt:formatDate
									pattern="yyyy-MM-dd" value="${pissuDate}" /></label>
						</div>
					</c:if>
					<div>
						<label style="width: 10%;"><spring:message
								code="purinvdet.jsp.invno" text="Invoice No" />. :</label> <label
							id="invNo_label">${saleHeaderDTO.invNo}</label>
					</div>
					<div>
						<fmt:parseDate value="${saleHeaderDTO.invDate}"
							var="parsedInvDate" pattern="MMM dd, yyyy" />
						<label style="width: 10%;"><spring:message
								code="rtrnmemo.jsp.salereturnmodal.invdt" text="Invoice Date" />:</label>
						<label><fmt:formatDate pattern="dd-MM-yyyy"
								value="${parsedInvDate}" /></label>
					</div>
					<div>
						<label style="width: 10%;"><spring:message
								code="reprintcash.jsp.dttime" text="Date Time" />:</label> <label><fmt:formatDate
								pattern="dd-MM-yyyy HH:mm:ss" value="${today}" /></label>
					</div>
					<%-- <div>
						<label style="width: 10%;"><spring:message
								code="cmn.print.jsp.vattincstno" text="GSTIN" />.:</label> <label>${sessionScope.sesloggedinStore.taxRegNo}</label>
					</div> --%>
					<div style="height:30px;"></div>
					<div>
						<label style="width: 10%;">Supplier's Ref. : <span></span></label>
					</div>
					<div>
						<label style="width: 10%;">Others Ref. : <span></span></label>
					</div>
					<div>
						<label style="width: 10%;">Buyer's Order No. : <span></span></label>
					</div>
					
				</div>

			</div>
			
			<div style="float: center; margin-bottom: 35px; width: 100%;">
				
				<table id="billitemtable"
					style="text-align: center; border-collapse: collapse; width: 100%; ">
					
					<thead style="border-bottom: 1px dashed;">
						<tr>
							<th><spring:message code="reprintcash.jsp.invno" text="SNo" /></th>
							<th><spring:message code="item.jsp.name" text="Item Name" /></th>
							<%-- <th width="17%;"><spring:message code="purinvdet.jsp.mfg" text="MFG" /></th> --%>
							<th><spring:message code="cmn.print.jsp.hsn" text="HSN" /></th>
							<th class="mfg"><spring:message code="purinvdet.jsp.mfg"
									text="MFG" /></th>
							<th class="grp"><spring:message code="purinvdet.jsp.grp"
									text="GRP" /></th>
							<th class="sch"><spring:message code="purinvdet.jsp.Sch"
									text="SCH" /></th>
							<th class="net">NET</th>
							<th><spring:message code="reprintcash.jsp.qty" text="Qty" /></th>
							<th><spring:message code="purinvdet.jsp.batch" text="Batch" /></th>
							<th><spring:message code="purinvdet.jsp.expdt" text="Exp" /></th>
							<th class="unit"><spring:message code="expiry.jsp.unit"
									text="Unit" /></th>
							<th><spring:message code="purinvdet.jsp.rate" text="Rate" /></th>
							<%-- <th width="5%;"><spring:message code="purinvdet.jsp.pqty" text="P.Qty" /></th>
						<th width="5%;"><spring:message code="purinvdet.jsp.lqty" text="L.Qty" /></th> --%>
							<th class="smrp">&nbsp;<spring:message
									code="purinvdet.jsp.mrp" text="MRP" />&nbsp;
							</th>
							<%-- <th width="5%;">&nbsp;<spring:message code="purinvdet.jsp.vatprcnt" text="Vat%" />&nbsp;</th> --%>
							<th>&nbsp;<spring:message code="purinvdet.jsp.total"
									text="Total" />&nbsp;
							</th>
							<th>&nbsp;<spring:message code="purinvdet.jsp.disc"
									text="Disc" />&nbsp;
							</th>
							<th>&nbsp;<spring:message code="reprintcash.jsp.taxableamt"
									text="Taxable Amt." />&nbsp;
							</th>
							<th class="cgst">&nbsp;<spring:message
									code="reprintcash.jsp.cgst" text="CGST(%)" />&nbsp;
							</th>
							<th class="sgst">&nbsp;<spring:message
									code="reprintcash.jsp.sgst" text="SGST(%)" />&nbsp;
							</th>
							<%-- <th width="10%;">&nbsp;<spring:message code="reprintcash.jsp.igst" text="IGST(%)" />&nbsp;</th> --%>
						</tr>
					</thead>
					<tbody style="border-bottom: 1px dashed;">
						<c:set var="noofline" value="35"></c:set>
						<c:set var="nooflinerem" value="0"></c:set>
						<c:set var="totaldisc" value="0"></c:set>
						<c:if test="${!empty saleDetailsDTOs }">
							<c:set var="nooflinerem"
								value="${noofline-saleDetailsDTOs.size()}"></c:set>
							<c:forEach items="${saleDetailsDTOs}" var="saleDetail"
								varStatus="loop">
								<tr>
									<td>${loop.index+1}</td>
									<td style="text-align: left; white-space: nowrap;">${saleDetail.itemName}</td>
									<%-- <td width="17%;">${saleDetail.manufacturerCode}</td> --%>
									<td>${saleDetail.hsnCode}</td>
									<td class="mfg">${saleDetail.manufacturerCode}</td>
									<td class="grp">${fn:substring(saleDetail.groupName, 0, 3)}</td>
									<td class="sch">${fn:replace(saleDetail.scheduleName, 'SCHEDULE', '')}</td>
									<td class="net">${saleDetail.netContent}</td>
									<td>${saleDetail.packQty * saleDetail.conversion + saleDetail.looseQty}</td>
									<td>${saleDetail.batchNo}</td>
									<td>${saleDetail.expiryDateFormat}</td>
									<td class="unit">${saleDetail.looseUnitName}</td>
									<%-- <td width="5%;">${saleDetail.packQty}</td>
							<td width="5%;">${saleDetail.looseQty}</td> --%>
									<td width="5%;" style="text-align: right;"><fmt:formatNumber
											type="number" minFractionDigits="2" maxFractionDigits="2"
											value="${saleDetail.ratePerUnit}" /></td>
									<td class="smrp"><fmt:formatNumber
											value="${saleDetail.mrp}" currencySymbol="" type="currency" /></td>
									<%-- <td width="5%;"><fmt:formatNumber value="${saleDetail.vatPer}" currencySymbol="" type="currency"/></td> --%>
									<td style="text-align: right;"><fmt:formatNumber
											value="${saleDetail.totalAmount}" currencySymbol=""
											type="currency" /></td>
									<%-- <td style="text-align: right;"><fmt:formatNumber
											value="${saleDetail.disc}" currencySymbol="" type="currency" /></td> --%>
										 <td style="text-align: right;"><fmt:formatNumber
											value="${((saleDetail.packQty * saleDetail.conversion + saleDetail.looseQty)*saleDetail.mrpPerUnit)*saleDetail.discPer/100}" currencySymbol="" type="currency" /></td>
											<c:set var="totaldisc"
											value="${totaldisc+((saleDetail.packQty * saleDetail.conversion + saleDetail.looseQty)*saleDetail.mrpPerUnit)*saleDetail.discPer/100}"></c:set>	
									<%-- <td style="text-align: right;"><fmt:formatNumber
											value="${saleDetail.taxableAmount}" currencySymbol=""
											type="currency" /></td> --%>
											<td style="text-align: right;"><fmt:formatNumber
											value="${saleDetail.totAmount}" currencySymbol=""
											type="currency" /></td>
									<%-- <td width="5%;" style="text-align: right;"><fmt:formatNumber value="${saleDetail.cgstPercentage}" currencySymbol="" type="currency"/></td> --%>
									<td class="cgst" style="text-align: right;"><fmt:formatNumber
											value="${saleDetail.cgst}" currencySymbol="" type="currency" />(${saleDetail.cgstPercentage}%)</td>
									<%-- <td width="5%;" style="text-align: right;"><fmt:formatNumber value="${saleDetail.sgstPercentage}" currencySymbol="" type="currency"/></td> --%>
									<td class="sgst" style="text-align: right;"><fmt:formatNumber
											value="${saleDetail.sgst}" currencySymbol="" type="currency" />(${saleDetail.sgstPercentage}%)</td>
									<%-- <td width="5%;" style="text-align: right;"><fmt:formatNumber value="${saleDetail.igstPercentage}" currencySymbol="" type="currency"/></td> --%>
									<%-- <td width="10%;" style="text-align: right;"><fmt:formatNumber value="${saleDetail.igst}" currencySymbol="" type="currency"/>(${saleDetail.igstPercentage}%)</td> --%>
								</tr>

							</c:forEach>
						</c:if>
						<c:if test="${nooflinerem gt 0 }">
							<c:forEach var="i" begin="0" end="${nooflinerem}">
								<tr class="extraRow" height="9px;"></tr>
							</c:forEach>
						</c:if>
					</tbody>
					<tfoot>
						<tr>
							<td></td>
							<td>Sale Ret. Amt.:</td>
							<td><fmt:formatNumber value="${saleHeaderDTO.adjAmount}"
									currencySymbol="" type="currency" /></td>
							<td class="mfg">MRP:</td>
							<td class="grp"></td>
							<td class="sch"></td>
							<td class="net"></td>
							<td>${saleHeaderDTO.totalMrp}</td>
							<td class="smrp"></td>
							<td><spring:message code="reprintcash.jsp.r.off"
									text="RoundOff" />:</td>
							<td><fmt:formatNumber value="${saleHeaderDTO.roundoff}"
									currencySymbol="" type="currency" /></td>
							<td class="unit"></td>
							<td><spring:message code="purinvdet.jsp.total" text="Total" />:</td>
							<td style="text-align: right;"><fmt:formatNumber
									value="${saleHeaderDTO.totalAmount}" currencySymbol=""
									type="currency" /></td>
							<%-- <td style="text-align: right;"><fmt:formatNumber
									value="${saleHeaderDTO.discAmount}" currencySymbol=""
									type="currency" /></td> --%>
									<td style="text-align: right;"><fmt:formatNumber
									value="${totaldisc}" currencySymbol=""
									type="currency" /></td>
							<%-- <td style="text-align: right;"><fmt:formatNumber
									value="${saleHeaderDTO.taxableAmount}" currencySymbol=""
									type="currency" /></td> --%>
							<td style="text-align: right;"><fmt:formatNumber
									value="${saleHeaderDTO.taxableAmount+saleHeaderDTO.taxAmount}" currencySymbol=""
									type="currency" /></td>
							<td class="cgst"><fmt:formatNumber
									value="${saleHeaderDTO.cgst}" currencySymbol="" type="currency" /></td>

							<td class="sgst"><fmt:formatNumber
									value="${saleHeaderDTO.sgst}" currencySymbol="" type="currency" /></td>

							<%-- <td ><fmt:formatNumber value="${saleHeaderDTO.igst}" currencySymbol="" type="currency"/></td> --%>
						</tr>
					</tfoot>
				</table>
			</div>
			<br>
			<div style="margin-bottom: 15px;">
				<table style="width: 100%;">
					<tr>
						<%-- <td><spring:message code="reprintcash.jsp.totinvfigr" text="Total Invoice Value (In Figures)" />: <span id="totInvValue" style="text-align: left; font-weight: bold;"> <fmt:formatNumber value="${saleHeaderDTO.netAmount-saleHeaderDTO.adjAmount}" currencySymbol="" type="currency" groupingUsed="false" />
						</span></td> --%>
						<td><spring:message code="reprintcash.jsp.totinvfigr"
								text="Total Invoice Value (In Figures)" />:</td>
						<td><span id="totInvValue"
							style="text-align: left; font-weight: bold;"> <fmt:formatNumber
									value="${saleHeaderDTO.netAmount-saleHeaderDTO.adjAmount}"
									currencySymbol="" type="currency" groupingUsed="false" />
						</span></td>
						<%-- <td  id="totInvValue" style="text-align: left; font-weight: bold;">
					<fmt:formatNumber value="${saleHeaderDTO.netAmount}" currencySymbol="" type="currency" groupingUsed = "false"/>
				</td> --%>
					</tr>
					<tr>
						<td><spring:message code="reprintcash.jsp.totinvword"
								text="Total Invoice Value (In Words)" />: <span
							id="totInvValueWord"
							style="text-align: left; white-space: nowrap; font-weight: normal;">
						</span></td>
						<!-- <td id="totInvValueWord" style="text-align: left;white-space:nowrap;"></td> -->
					</tr>
					<tr id="reversetaxRow">
						<td><spring:message
								code="reprintcash.jsp.taxamtrspctrvrschrg"
								text="Amt of Tax subject to Rev. Chrgs" />: <span
							style="font-weight: bold;"> CGST = <fmt:formatNumber
									value="${saleHeaderDTO.cgst}" currencySymbol="" type="currency" />
								SGST = <fmt:formatNumber value="${saleHeaderDTO.sgst}"
									currencySymbol="" type="currency" />
						</span></td>
						<%-- <td style="font-weight: bold;">
					<span style="padding-left: 20%"><fmt:formatNumber value="${saleHeaderDTO.cgst}" currencySymbol="" type="currency"/></span>
					<span  style="padding-left: 10%"><fmt:formatNumber value="${saleHeaderDTO.sgst}" currencySymbol="" type="currency"/></span>
					<span  style="padding-left: 12%"><fmt:formatNumber value="${saleHeaderDTO.igst}" currencySymbol="" type="currency"/></span>
				</td> --%>
					</tr>
				</table>
			</div>
			<div id="taxdetailscont" style="margin-bottom: 15px;">
				<!-- tax details table -->
				<table id="taxdetailtable" style="width: 100%;">
					<thead style="border-bottom: 1px dashed;">
						<tr>
							<th>Tax%</th>
							<th>Taxable</th>
							<th>CGST%</th>
							<th>CGST AMT</th>
							<th>SGST%</th>
							<th>SGST AMT</th>
							<!-- <th>IGST%</th>
					<th>IGST AMT</th> -->
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty taxDetailsDTOs }">
							<c:forEach items="${taxDetailsDTOs}" var="taxDetail"
								varStatus="loop">
								<tr style="text-align: center;">
									<td>${taxDetail.taxPercentage}</td>
									<td><fmt:formatNumber value="${taxDetail.taxableAmount}"
											currencySymbol="" type="currency" /></td>
									<td>${taxDetail.cgstPercentage}</td>
									<td><fmt:formatNumber value="${taxDetail.cgst}"
											currencySymbol="" type="currency" /></td>
									<td>${taxDetail.sgstPercentage}</td>
									<td><fmt:formatNumber value="${taxDetail.sgst}"
											currencySymbol="" type="currency" /></td>
									<%-- <td>${taxDetail.igstPercentage}</td>
							<td>${taxDetail.igst}</td> --%>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<!-- end tax details table -->
			</div>
			
			<div>
			<div style="height:80px;"></div>
				<div style="width:100%;display: flex;">

					
						<div style="width:70%"><span>Remarks: ${saleHeaderDTO.remarks}</span></div>
						<div style="width:30%"> <span
							id="cmpybankdtls"><spring:message
									code="reprintcash.jsp.cmpybankdtls"
									text="Company's Bank Details" /></span></div>
					
					</div>
					<div style="width:100%;display: flex;">
						<div style="width:70%"><span>Company's PAN :</span>AAUFG5870A</div>
						<div style="width:30%"><span
							id="bankName"><spring:message
									code="reprintcash.jsp.bankName" text="Bank Name" />Bank Of India</span></div>
					</div>
					
					
					<div style="width:100%;display: flex;">
						<div style="width:70%"><span>&nbsp;</span></div>
						<div style="width:30%"><span
							id="cmpAcno"><spring:message code="reprintcash.jsp.acno"
									text="A/c No." />432530110000146</span></div>
					</div>
					
					
						<div style="width:100%;display: flex;">
						<div style="width:70%"><span></span></div>
						<div style="width:30%"><span
							id="branchandifs"><spring:message code="reprintcash.jsp.branchandifs"
									text="Branch & IFS code" />Michael Nagar Branch & BKID0004325</span></div>
					</div>
				

				<div style="height:50px;"></div>
				<table style="width: 100%;">
 
					<tr>
					
						<td><spring:message code="reprintcash.jsp.note" text="Note" />:
							<span  id="noteline1"></span></td>
						<td></td>
						<td></td>
						
					</tr>
					<tr>
						<td><span id="noteline2"></span></td>
						<%-- <td width="25%" style="white-space: nowrap;"><span
							id="salesmansig"><spring:message
									code="reprintcash.jsp.sigsalesman" text="Sig. of Salesman" /></span></td> --%>
						<td width="25%" style="white-space: nowrap;"><span
							id="pharmacistsig"><spring:message
									code="reprintcash.jsp.auth" text="Authorised Signatory" /></span></td>
					</tr>
				</table>
			</div>
			<div style="float: left;">
				<input type="hidden" id="backUrl" value="${backUrl}" />
			</div>
		</div>
	</div>


	<script
		src="${pageContext.request.contextPath }/assets/js/common/jquery-1.8.3.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/assets/js/common/bill_setup.js"></script>
	<script type="text/javascript">
		var BASE_URL = "${pageContext.request.contextPath}";
	</script>

</body>
</html>