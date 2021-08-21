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
<link href="${pageContext.request.contextPath }/assets/css/bootstrap/bootstrap.css" rel="stylesheet">
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"> -->
<title><spring:message code="reprintcash.jsp.title" text="Cash Memo" /></title>
 <style>
    /*.row {
        margin-left:0px;
        margin-right:0px;
        display:flex
    }*/
    th {
       text-align: center;
       padding: 2px;
       border: 1px solid black;
       font-size:11px
    }
    td{
        padding: 2px;
        border-right: 1px solid black;
        font-size:10.5px
    }
    /*table {
      border-top: 2px solid black;
      border-right: 2px solid black;
      border-left: 2px solid black
    }*/
    h4 {
       margin-bottom:0px;
       font-size:15px;
    }
    /*.col-ms-1 {
       width:8%;
    }
    .col-ms-10 {
       width:84%;
    }*/
    /*.col-ms-3 {
        width:25%;
    }
    .col-ms-6 {
        width:50%;
    }*/
 </style>
</head>
<body>
<div style="margin-top:10px;width:100%;display:flex">
    <!--  <div style="width:1%"> </div> -->
     <div style="width:100%"> 
         <div style="border-right: 1px solid black;border-left: 1px solid black;border-top: 1px solid black">
          <div style="width:100%;display:flex">
          	   <div style="border-right:1px solid black;width:16%;text-align:center;">  
          	      <c:if test="${sessionScope.sesloggedinUser.companyId == 413 || sessionScope.sesloggedinUser.companyId == 414 || sessionScope.sesloggedinUser.companyId == 1}">
                   <img src="${pageContext.request.contextPath}/assets/images/logo/dabadost_logo.png" alt="" style="width:90%;margin-top:20px;">
          	      </c:if>
          	   </div>
          	   <div style="border-right:1px solid black;width:30%"> 
          	   	     <p style="font-size:11px;font-weight:700;margin:0px;padding-left:3px;">${sessionScope.sesloggedinStore.name} </p>
          	   	     <p style="font-size:11px;margin:0px;padding-left:3px;"> ${sessionScope.sesloggedinStore.address},${sessionScope.sesloggedinStore.phone} </p>
          	   	     <p style="font-size:11px;margin:0px;padding-left:3px;"> E-mail: ${sessionScope.sesloggedinStore.email} </p> 
          	   	     <p style="font-size:11px;margin-top:40px;margin-bottom:10px;padding-left:3px;"> <b> DL NO.: </b> <span> ${sessionScope.sesloggedinStore.dlLicenceNo} </span> </p>
          	   	     <p style="font-size:11px;margin:0px;padding-left:3px;"> <b> GST NO: </b> <span> ${sessionScope.sesloggedinStore.taxRegNo} </span> </p>
          	   </div>
          	   <div style="border-right:1px solid black;width:30%">  
          	   		<div style="width:100%;display:flex">
          	   			<div style="border-right:1px solid black;border-bottom:1px solid black;width:50%"> 
                           <p style="text-align:center;font-size:11px;font-weight:700;margin-top:5px;padding-left:3px;"> <b> GST INVOICE </b> </p>
                           <p style="font-size:11px;margin:0px;padding-left:3px;"> <b> Inv No: </b> <span> <b> ${saleHeaderDTO.invNo}</b> </span> </p>
                           <p style="font-size:11px;margin:0px;padding-left:3px;"> <b> Dated: </b> <span>
                               <fmt:parseDate value="${saleHeaderDTO.invDate}" var="parsedInvDate" pattern="MMM dd, yyyy" /> 
                               <fmt:formatDate pattern="yyyy-MM-dd" value="${parsedInvDate}" />
                            </span> </p>
          	   			</div>
          	   			<div style="width:50%;border-bottom:1px solid black"> 
          	   				<p style="font-size:11px;margin-top:30px;margin-bottom:0px;padding-left:3px;">  Pay Mode: <span> <b> ${saleHeaderDTO.invModeName} </b> </span> </p>
          	   				<p style="font-size:11px;margin:0px;padding-left:3px;">  <b> Time: </b> <span> <fmt:formatDate pattern="HH:mm a" value="${today}" /> </span> </p>
          	   			</div>
          	   		</div>
          	   		<div style="width:100%;text-align:center">
          	   			<p style="font-size:20px;font-weight:bold;margin:0 0 1px;"> Customer Care No  </p>
          	   			<p style="font-size:20px;font-weight:bold;margin:0 0 1px;"> 8433808080  </p>
          	   		</div>
          	   </div>
          	   <div style="width:24%">  
                    <p style="font-size:11px;margin-top:30px;margin-bottom:0px;padding-left:3px;"> <b> Patient Name: </b> <span> <b>${saleHeaderDTO.customerName} </b> </span> </p>
          	   	    <p style="font-size:11px;margin-top:50px;margin-bottom:0px;padding-left:3px;">  <b> Doctor Name: </b> <span>${saleHeaderDTO.doctorName} </span> </p>
          	   	    <p style="font-size:11px;margin:0px;padding-left:3px;"> Dr.Add: <span> </span> </p>
          	   </div>

          </div>

          
         </div>

           <!-- ....................................2nd part...................................  -->   

      <div style="padding-bottom:50px">   
           <div style="border-bottom:1px solid black">
  <table style="width:100%;border-top: 1px solid black;border-right: 1px solid black;border-left: 1px solid black">
    <tr>
      <th>S.No.</th>
      <th>SHELF</th>
      <th>PRODUCT NAME</th>
      <th>MFG</th>
      <th>PACK</th>
      <th>HSN</th>
      <th>BATCH</th>
      <th>EXP.</th>
      <th>QTY</th>
      <th>MRP/UNIT</th>
      <th>DISC%</th>
      <th>GST%</th>
      <th>AMOUNT</th>

    </tr>
      <c:set var="noofline" value="10"></c:set>
	  <c:set var="nooflinerem" value="0"></c:set>
	  <c:set var="totdiscpers" value="0"></c:set>
	  <c:if test="${!empty saleDetailsDTOs }">
		  <c:set var="nooflinerem" value="${saleDetailsDTOs.size()}"></c:set>
		  <c:forEach items="${saleDetailsDTOs}" var="saleDetail" varStatus="loop">
			   <tr style="border-block-end: 1px solid black;">
			   <c:set var="totdiscpers">
			   <fmt:formatNumber type="number" groupingUsed="false"
					minFractionDigits="2" maxFractionDigits="2"
					value="${totdiscpers + saleDetail.discPer}" /></c:set>
				  <td style="text-align:right">${loop.index+1}</td>
			      <td></td>
			      <td>${saleDetail.itemName}</td>
			      <td>${saleDetail.manufacturerCode}</td>
			      <td style="text-align:right">${saleDetail.conversion}'S</td>
			      <td>${saleDetail.hsnCode}</td>
			      <td>${saleDetail.batchNo}</td>
			      <td>${saleDetail.expiryDateFormat}</td>
			      <td style="text-align:right">${saleDetail.packQty * saleDetail.conversion + saleDetail.looseQty}</td>
			      <td style="text-align:right">${saleDetail.mrp}</td>
			      <td style="text-align:right">${saleDetail.discPer}</td>
			      <td style="text-align:right">${saleDetail.taxPercentage}</td>
			      <td style="text-align:right">${saleDetail.totAmount}</td>
			    </tr>
		   </c:forEach>
		   <c:if test="${nooflinerem<10 }">
		   <c:forEach var = "i" begin = "1" end = "${10-nooflinerem}">
			      <tr style="height:15px">
			      <td colspan="13"></td>
			    </tr>
             </c:forEach>
		   </c:if>
		</c:if>
     <c:set var="printabledisc"> 
         <fmt:formatNumber type="number"
					maxFractionDigits="0"
					value="${totdiscpers / nooflinerem}" />
	</c:set>
    <tr style="height:20px;border-top:1px solid black;border-bottom:1px solid black"> 
      <td colspan="8"><b> Amt In Words: <span id="totInvValueWord"></span> </b></td>
      <td colspan="2" style="border:0px"> <b> TOTAL: </b> </td>
      <td colspan="1" style="text-align:center;border:0px"> <span> <b> <fmt:formatNumber value="${saleHeaderDTO.discAmount}" currencySymbol="" type="currency" /> </b> </span> </td>
      <td colspan="2" style="text-align:right;border:0px;border-right:1px solid black"> <span> <b> <fmt:formatNumber value="${saleHeaderDTO.netAmount - saleHeaderDTO.roundoff}" currencySymbol="" type="currency" /> </b> </span> </td>
    </tr>

    <tr style="text-align:center;border-bottom:1px solid black"> 
      <td colspan="13" style="font-size:22px;">  <b> YOUR SAVINGS: <span style="margin-left:20px;"> <fmt:formatNumber value="${saleHeaderDTO.discAmount}" currencySymbol="" type="currency" />(${printabledisc}%) </span> </b> </td>
    </tr>

    <tr> 
      <td colspan="10" rowspan="3" valign="top" style="border-right:0px"> <b> Return Policy: </b> </td>
      <td colspan="2" style="border-right:0px"> <b> <span> SGST Amt. </span> <span style="float:right"> :</span> </b> </td>
      <td colspan="1" style="text-align:right"> <span> <fmt:formatNumber value="${saleHeaderDTO.sgst}" currencySymbol="" type="currency" /> </span> </td>
    </tr>
    <tr> 
      <td colspan="2" style="border-right:0px"> <b> <span> CGST Amt. </span> <span style="float:right"> :</span> </b> </td>
      <td colspan="1" style="text-align:right"> <span> <fmt:formatNumber value="${saleHeaderDTO.cgst}" currencySymbol="" type="currency" /> </span> </td>
    </tr>
    <tr> 
      <td colspan="2" style="border-right:0px"> <b> <span> TOT GST Amt. </span> <span style="float:right"> :</span> </b> </td>
      <td colspan="1" style="text-align:right"> <span> <fmt:formatNumber value="${saleHeaderDTO.cgst + saleHeaderDTO.sgst}" currencySymbol="" type="currency" /> </span> </td>
    </tr>

    <tr style="border-top:1px solid black"> 
      <td colspan="3" rowspan="3" style="width:300px">  
      		<P style="border-bottom:1px solid black;margin: 0 0 2px;line-spacing:3px;">We hereby certify that our registration certificate under the applicable Goods Service Tax Act is in force on the date that the supply covered by this invoice has been effected by us in regular course of business. </P>
      		<p style="text-align:right;padding-right:20px;margin: 0 0 0px;"> Page 1 of 1</p>
      </td>
      <td colspan="4" rowspan="3" valign="top"> 
      	    <p style="font-size:10px;"> <b> For ${sessionScope.sesloggedinStore.name} </b> </p>
      	    <p style="text-align:right;padding-top:60px;margin: 0 0 0px"> <b> Pharmacist/Authorized </b> </p>															
      </td>
      <td colspan="3" rowspan="3" valign="bottom"> <b> Prep By:${sessionScope.sesloggedinUser.userCode} </b> </td>
      <td colspan="2" style="border-right:0px;border-bottom:1px solid black"> <b> <span> INV AMT. </span> <span style="float:right"> :</span> </b> </td>
      <td colspan="1" style="border-bottom:1px solid black;text-align:right"> <b> <span> <fmt:formatNumber value="${saleHeaderDTO.netAmount - saleHeaderDTO.roundoff}" currencySymbol="" type="currency" /> </span> </b> </td>
    </tr>
    <tr>
      <td colspan="2" rowspan="1" style="border-right:0px;border-bottom:1px solid black">  
      		<span> Round Off. </span> <span style="float:right"> :</span>
      </td>
      <td colspan="2" rowspan="1" style="border-bottom:1px solid black;text-align:right">  
      		<span> <fmt:formatNumber value="${saleHeaderDTO.roundoff}" currencySymbol="" type="currency" /> </span>
      </td>
    </tr>
    <tr>
      <td colspan="2" rowspan="1" style="border-right:0px">  
      		<b> <span> GRAND TOTAL </span> <span style="float:right"> :</span> </b>
      </td>
      <td colspan="2" rowspan="1" style="text-align:right">  
      		<b> <span id="totInvValue"> <fmt:formatNumber value="${saleHeaderDTO.netAmount-saleHeaderDTO.adjAmount}" currencySymbol="" type="currency" groupingUsed="false" /> </span> </b>
      </td>
    </tr>
    <!--   <td colspan="7" rowspan="1" style="width:250px;border-bottom:2px solid black">  
      		page 1 of 1
      </td>
      <td colspan="3" rowspan="1" style="width:250px;border-bottom:2px solid black">  
      		page 1 of 1
      </td>
    </tr> -->
    
  </table>
</div>
</div>

     </div>
     <!-- <div style="width:1%"> </div> --><input type="hidden" id="backUrl" value="${backUrl}" />
</div>
    <script src="${pageContext.request.contextPath }/assets/js/common/jquery-1.8.3.min.js"></script>
    <script src="${pageContext.request.contextPath }/assets/js/common/bill_setup.js"></script>
	<script type="text/javascript">
		var BASE_URL = "${pageContext.request.contextPath}";
	</script>

</body>
</html>