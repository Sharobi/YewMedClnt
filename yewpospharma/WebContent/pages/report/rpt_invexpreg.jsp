<%@page import="com.sharobi.yewpos.util.CommonResource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<section class="wrapper">
	<div class="row">
		<div class="col-lg-12">
			<p><spring:message code="rptinvexpreg.jsp.title" text="Expiry Register..." /></p>
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="col-sm-12 col-sm-12">
					<input type="hidden" id="compid" value="${sesloggedinUser.companyId}">
					<input type="hidden" id="storeid" value="${sesloggedinUser.storeId}">
					<input type="hidden" id="finyrid" value="${sesloggedinUser.finyrId}">
					<input type="hidden" id="asondate" value=null>
					<table>
						<tr align="center" style="font-weight: bold;">
								<td><spring:message code="purinvdet.jsp.itemname" text="Item Name" /></td>
								<td><spring:message code="rptinvexpreg.jsp.upcmngmnth" text="Upcoming Month" /></td>
								<td><spring:message code="purinvdet.jsp.vendor" text="Vendor" /></td>
								<td><spring:message code="itemmstr.jsp.rack" text="Rack" /></td>
								<td></td>
							</tr>
							<tr>
								<td style="padding: 0 1px;width: 33%;"><input type="hidden" id="itemid" value="0" /><input class="form-control-trx" type="text" id="item_name" placeholder="Please type atleast 2 characters"></td>
								<td style="padding: 0 1px;">
									<select class="form-control-trx" id="expmnthnmbr">
										<option value="0">0</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
									</select>
								</td>
								<td style="padding: 0 1px;width: 26%;"><select class="form-control-trx" name="distributorId" id="seldistributor">
										<c:if test="${!empty allVendors}">
										<option value="0">All</option>
											<c:forEach items="${allVendors}" var="allVendor">
													<option value="${allVendor.id}">${allVendor.name}</option>
											</c:forEach>
										</c:if>
								</select></td>
								<td style="padding: 0 1px;"><select class="form-control-trx" name="rackId" id="rackSelect">
										<option value="0">Select...</option>
										<c:if test="${!empty allRacks}">
											<c:forEach items="${allRacks}" var="allRack">
												<option value="${allRack.id}">${allRack.name}</option>
											</c:forEach>
										</c:if>
									</select></td>
								
								<td>
									<div class="col-lg-4 col-md-4 col-sm-12">
										<button type="button" class="btn btn-theme" onclick="getCurrSalesItems()"><spring:message code="cmn.jsp.btn.submit" text="Submit" /></button>
									</div>
								</td>
					</table>
					</div>
					
				</div>
				<div id="rptpossaleregdiv" style="height: 450px;width: 100%;"></div>
			</div>
		</div>
	</div>
</section>
<!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/bootstrap/bootstrap-datepicker.min.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>
<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";
 $(document).ready(function() {
	 $("#item_name").autocomplete({
			source : function(	request,
								response) {
				if (request.term.length >= 2) {
					$.ajax({
						url : BASE_URL + "/item/autocompleteitem.htm",
						type : "GET",
						data : {
							tagName : request.term
						},
						dataType : "json",
						success : function(data) {
							response($.map(data, function(v) {
								return {
									label : v.itemName,
									itemCode : v.itemId,
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
				console.log(ui.item.itemCode);
				console.log(ui.item.label);
				$("#itemid").val(ui.item.itemCode);
			},
			change : function(	e,
								ui) {
				if (!(ui.item))
					e.target.value = "";
			},
		}); 
 });

var pdf_url_sale_item='<%=CommonResource.getProperty(CommonResource.TARGET_WEBSERVICE_ENDPOINT)+CommonResource.getProperty(CommonResource.WEBSERVICE_REP_INV_EXPREGIS)%>';
	var currentDate = new Date();
	function getCurrSalesItems(){

		var compid=$("#compid").val();
		var storeid=$("#storeid").val();
		var finyrid=$("#finyrid").val();
		var asondate=$("#asondate").val();
		var expmnthnmbr=$("#expmnthnmbr").val();
		if($("#item_name").val()=="")
		{
			var itemid=0;
		}
		else
		{
			var itemid=$("#itemid").val();
		}
		var seldistributor=$("#seldistributor").val();
		var rackSelect=$("#rackSelect").val();
		
		//alert("itemid:"+itemid+",asondate:"+asondate+",expmnthnmbr:"+expmnthnmbr+",seldistributor:"+seldistributor+",rackSelect:"+rackSelect);
			var pdfline="<iframe src='"+pdf_url_sale_item+"?cmpnyId="+compid+"&storeId="+storeid+"&finYrId="+finyrid+"&itemId="+itemid+"&asOnDate="+asondate+"&noOfExpiryMonth="+expmnthnmbr+"&distributorId="+seldistributor+"&rackId="+rackSelect+"' style='width:100%; height:450px;' frameborder='0'></iframe>";
			document.getElementById('rptpossaleregdiv').innerHTML=pdfline;
	}
	
</script>