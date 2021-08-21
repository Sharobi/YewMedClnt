<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<style>
<!--
.ui-autocomplete {
	overflow-y: scroll; max-height: 250px; width: 300px; word-break: break-all; z-index: 2150000000 !important;
}
-->
 .nameStyle {
  float:left;
  margin:17px 0px 0px 22px;
  width: 290px;
  display: inline-block;
  zoom: 1;
  position: relative;
  display: block;
  padding: .55em 1em;
  padding-right: 3.05em;
  line-height: normal;
  text-decoration: none;
  color: #fff;
  background: #0dbacb;
  /*font-weight: bold;*/
  text-transform: uppercase;
  border-radius: 3px;
 } 
 .store-name {
    color:white;
    float: left;
    font-weight: bold;
    font-size: 20px;
    margin: 17px 0px 0px 22px;
}
</style>
<!--header start-->
      <header class="header black-bg">
              <div class="sidebar-toggle-box">
                  <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
              </div>
            <!--logo start-->
            <a href="${pageContext.request.contextPath }/home/welcome.htm" class="logo"><img src="${pageContext.request.contextPath }/assets/images/logo/yewpos_medsoft_logo.png"></a>
            <!--logo end-->
           
            <c:if test="${sesloggedinUser.isAdmin == 1}">
			   <select class="form-control-trx nameStyle idealselect" name="allstorelists"  id="allstorelists"  style="height: 32px;width:300px;display:none;" onchange="changeChildStore(this.value)">
	 			 <!-- <option value="0">Select...</option> -->
		 		   <c:forEach items="${allstoresdata}" var="store">
		 		     <option value="${store.id}" ${sesloggedinUser.storeId== store.id ? 'selected' : ''}>${store.name}</option>
					</c:forEach>
 				</select>
 				 <div style="display:none;" id="stNameDiv" class="nameStyle"> ${sesloggedinStore.name}</div> 
		     </c:if> 
             <c:if test="${sesloggedinUser.isAdmin != 1}">
	            <div class="nav store-name">
		            ${sesloggedinStore.companyMaster.name}
		            ${sesloggedinStore.name}
		        </div>
		    </c:if> 
             <div class="nav notify-row"><div style="text-transform: uppercase;color:#fff;font-weight: bold;"><spring:message code="header.jsp.date" text="DATE"/> : <span style="color: #0dbacb;"><fmt:formatDate pattern="yyyy-MM-dd" value="${today}" /></span><span> &nbsp;&nbsp;Financial Year&nbsp;:</span><span style="color: #0dbacb;">&nbsp;${sessionScope.sesloggedinUser.description}</span></div>
             </div>
            <div class="top-menu">
            	<ul class="nav pull-right top-menu">
            		<li class="logout" style="margin-top: 18px;color: #fff;"><spring:message code="header.jsp.welcome" text="Welcome"/> &nbsp;<strong>${sessionScope.sesloggedinUser.userName} &nbsp;</strong></li>
                    <li><a class="logout" href="${pageContext.request.contextPath }/authentication/logout.htm"><spring:message code="header.jsp.logout" text="Logout"/></a></li>
            	</ul>
            </div>
            <input type="hidden" id="isExclusive" value="${sesloggedinStore.isExclusive}" />
        </header>
<!--header end-->
<!-- modal starts -->
<div class="modal fade" id="itemhistoryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 900px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">
					Item History
				</h4>
			</div>
			<div class="modal-body" id="itemHistoryModalDiv">
				<div>
					<table>
						<tr>
							<td>Item Name:</td>
							<td><input class="form-control-trx" type="text" id="searchitem_forhistory" value="" placeholder="(Please type atleast 2 characters)"><input class="form-control-trx" type="hidden" id="itemid_forhistory" value="0"></td>
							<td style="padding: 0 1px;">From Date:</td>
							<td style="padding: 0 1px;"><input type="text" readonly="readonly" class="form-control-trx" id="strtdateitemhis" name="startDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
										</td>
							<td style="padding: 0 1px;">To Date:</td>
							<td style="padding: 0 1px;"><input type="text" readonly="readonly" class="form-control-trx" id="enddateitemhis" name="endDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
										</td>
							<td><button class="btn btn-theme btn-sm" onclick="stDtGrtEdDt()">
								<spring:message code="cmn.jsp.search" text="Search" />
							</button></td>
						</tr>
					</table>
				</div>
				<p></p>
				<div style="height: 330px;" id="">
					<table id="itemhistorytbl" class="table table-bordered table-striped table-condensed-trx table-hover">
						<thead>
							<tr>
								<th rowspan="2">Batch</th>
								<th rowspan="2">Exp.Date</th>
								<th colspan="2">Receipt Qty ( <span id="totrecepqty" style="color: #F47A20;">0</span> )</th>
								<th colspan="2">Issue Qty ( <span id="totissueqty" style="color: #F47A20;">0</span> )</th>
								<th>Calc Qty ( <span id="totcalcqty" style="color: #F47A20;">0</span> )</th>
								<th rowspan="2" style="width: 122px;">Inv. No</th>
								<th rowspan="2">Inv. Date</th>
								<th rowspan="2">Party Name</th>
							</tr>
							<tr>
								<th>Pack</th>
								<th>Loose</th>
								<th>Pack</th>
								<th>Loose</th>
								<th>Type</th>
							</tr>
						</thead>
						<tbody id="itemhisbody">

						</tbody>
					</table>
				</div>
				 <div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsgitmhis"></div>
			</div>
			<div class="modal-footer" style="border-top: 0px solid #e5e5e5;">
				<%-- <button type="button" onclick="" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button> --%>
			</div>
		</div>
	</div>
</div>
<!-- modal starts -->
<script type="text/javascript">
$( document ).ready(function() { // new added 26.7.2019
	var pathname = window.location.pathname;
	if(pathname  == "/yewpospharma/home/welcome.htm"){
	      $('.idealselect').hide();
	      $('#stNameDiv').show();
	}else{
		  $('.idealselect').show();
	}
});
</script>
