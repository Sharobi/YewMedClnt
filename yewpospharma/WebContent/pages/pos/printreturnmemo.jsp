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
<title><spring:message code="printrtrnsales.jsp.title" text="Credit Note"/></title>
</head>
<body>

<div id="printdiv" class="print hide">
<div style="margin: 0 auto;height: auto;width: 100%;float: left;font-family: monospace;">
	<p style="text-align: center;"><spring:message code="printrtrnsales.jsp.title" text="Credit Note"/><br>
	<span style="text-align: center;"><b>${sessionScope.sesloggedinStore.name}</b></span><br>
	<span style="text-align: center;"><b>${sessionScope.sesloggedinStore.address}</b></span><br>
	<span style="text-align: center;">${sessionScope.sesloggedinStore.phone}</span></p>
	<div style="float: left;height: auto;width: 100%;margin-bottom: 5%;">
	
		<div id="left_div" style="float: left">
			<div>
       			<label style="width: 10%;font-weight: 600;"><spring:message code="purinvdet.jsp.invno" text="Invoice No" />. :</label>
       			<label id="invNo_label">${saleReturnDTO.invNo}</label>
   			</div>
			<div>
       			<label style="width: 10%;font-weight: 600;"><spring:message code="printrtrnsales.jsp.credit" text="Credit" />:</label>
       			<label id="dctrName_label">${saleReturnDTO.customerName}</label>
   			</div>
   			<div>
       			<label style="width: 10%;font-weight: 600;"><spring:message code="customer.jsp.phn" text="Phone No." />:</label>
       			<label id="patientName_label">${saleReturnDTO.customerPhone}</label>
   			</div>
		</div>
		<div id="right_div" style="float: right;">		
			<div>
				<fmt:parseDate value="${saleReturnDTO.invDate}" var="salesRetInvDate" pattern="MMM dd, yyyy" />
       			<label style="width: 10%;font-weight: 600;"><spring:message code="rtrnmemo.jsp.salereturnmodal.invdt" text="Invoice Date" />:</label>
       			<label><fmt:formatDate pattern="yyyy-MM-dd" value="${salesRetInvDate}" /></label>
   			</div>			
			<div>
       			<label style="width: 10%;font-weight: 600;"><spring:message code="reprintcash.jsp.dttime" text="Date Time" />:</label>
       			<label><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${today}" /></label>
   			</div>
			<div>
       			<label style="width: 10%;font-weight: 600;"><spring:message code="cmn.print.jsp.vattincstno" text="VAT/TIN/CST No" />.:</label>
       			<label>${sessionScope.sesloggedinUser.taxRegNo}</label>
   			</div>
		</div>
		
	</div>
	<div class="col-lg-12" style="margin-bottom: 5%;">
		<p>
			<spring:message code="printrtrnsales.jsp.line1" text="A sum of" />&nbsp;<label>${stringAmount}</label><span style="margin-left: 22%;font-weight: 600;"><spring:message code="printrtrnsales.jsp.rs" text="Rs." />&nbsp;<label style="border: 1px solid black;padding: 5px 18px">${saleReturnDTO.netAmount}</label></span>
		</p>
		<p>
			<spring:message code="printrtrnsales.jsp.line2" text="Being excess of amount,adjusted as of Date." />
		</p>
	</div>
	<br><br><br>
	<div class="col-lg-12" style="float: right;margin-right: 5%;">
		<label style="width: 15%;font-weight: 600; border-top: 1px solid black;"><spring:message code="printrtrnsales.jsp.cashiersign" text="Cashier Signature" /></label>
		<input type="hidden" id="backUrl" value="${backUrl}" />
	</div>
</div>	
</div>


<script src="${pageContext.request.contextPath }/assets/js/common/jquery-1.8.3.min.js"></script>
<script type="text/javascript">

var BASE_URL = "${pageContext.request.contextPath}";
$(document).ready(function(){
	window.print();
	setTimeout(function(){ location.href = BASE_URL + '/retunmemo/' + $("#backUrl").val() + '.htm'; }, 50);//13-03-2019
	/* location.href = BASE_URL + '/retunmemo/'+$("#backUrl").val()+'.htm'; */
});

</script>

</body>
</html>