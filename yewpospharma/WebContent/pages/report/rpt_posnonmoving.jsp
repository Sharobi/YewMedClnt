<%@page import="com.sharobi.yewpos.util.CommonResource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="col-sm-3 col-sm-3">
					<input type="hidden" id="compid" value="${sesloggedinUser.companyId}">
					<input type="hidden" id="storeid" value="${sesloggedinUser.storeId}">
					<input type="hidden" id="finyrid" value="${sesloggedinUser.finyrId}">
					<input type="hidden" id="noofexpmon" value="5">
						<input type="text" class="form-control" id="asondate" readonly="readonly" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" /> ">
					</div>
					
					<div class="col-lg-4 col-md-4 col-sm-12">
						<button type="submit" class="btn btn-theme" onclick="getNonMovingItem()">Get Non Moving Item</button>
					</div>
				</div>
				<div id="rptnonmovitemdiv"></div>
			</div>
		</div>
	</div>
</section>
<!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/bootstrap/bootstrap-datepicker.min.js"></script>
<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";

var pdf_url_stock_reg='<%=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_REP_INV_NONMOVING_ITEM)%>';
	var currentDate = new Date();
	/* $('#date').datepicker({
		format : 'yyyy-mm-dd',
		endDate : currentDate,
	}); */
	function getNonMovingItem(){
		var compid=$("#compid").val();
		var storeid=$("#storeid").val();
		var finyrid=$("#finyrid").val();
		var asondate=$("#asondate").val();
		var noofexpmon=$("#noofexpmon").val();
		
		var pdfline="<iframe src='"+pdf_url_stock_reg+"?cmpnyId="+compid+"&storeId="+storeid+"&finYrId="+finyrid+"&asOnDate="+asondate+"&noOfMonth="+noofexpmon+"' style='width:100%; height:450px;' frameborder='0'></iframe>";
		document.getElementById('rptnonmovitemdiv').innerHTML=pdfline;
	}

</script>