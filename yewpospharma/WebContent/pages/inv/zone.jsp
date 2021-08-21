<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet">
		<section class="wrapper">
			<div class="row">
          		<div class="col-lg-12">
   				<p><%-- <spring:message code="subcat.jsp.title" text="SubCategory..." /> --%></p>
   				<div class="panel panel-default">
					<div class="panel-body">
					
						<div class="col-lg-8 col-md-8 col-sm-12">
							<form modelAttribute="commonResultSetMapper" class="form-inline" role="form" action="${pageContext.request.contextPath }/invsetup/searchzone.htm" method="post">
								<div class="form-group">
	
									<input type="text" class="form-control" placeholder="Zone Name" name="name" value="${commonResultSetMapper.name}">
								</div>
								<div class="form-group">
									<select class="form-control" name="qryCondition" >
										<option value="like">LIKE</option>
										<option value="equals">=</option>
									</select>
								</div>
								<button type="submit" class="btn btn-theme"><spring:message code="cmn.jsp.search" text="Search" /></button>
							</form>
						</div>
					
						<div class="pull-right">
							<c:if test="${menuByUserDTO.isAll==1}">
								<button class="btn btn-primary" type="button" onclick="javascript:openAddEditModal(0,0,0,0,'','')"><i class="fa fa-plus"></i>&nbsp;<spring:message code="cmn.jsp.btn.add" text="Add" /></button>
							</c:if>
						</div>
					</div>
				</div>
							
				<!-- Add/Edit zoneAddEditModal Starts -->
				
					<div class="modal fade" id="zoneAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
                              			<label class="col-sm-4 col-sm-4 control-label" id="country_label"><spring:message code="city.jsp.countryname" text="Country Name" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                              				<select id="countryList" class="form-control" onchange="getStateByCountry()">
                              					<option value="0">Select</option>
	                              				<c:if test="${!empty countryMasters}">
													<c:forEach items="${countryMasters}" var="countryMaster">
														<option value="${countryMaster.id}">${countryMaster.name}</option>
													</c:forEach>
												</c:if>
                              				</select>
                              			</div>
                          			</div>	
                          			<div class="form-group">
										<label class="col-sm-4 col-sm-4 control-label" id="state_label"><spring:message code="city.jsp.statename" text="State Name" />
											<span class="required_star">*</span>
										</label>
										<div class="col-sm-8">
											<select class="form-control-trx" name="statelist" id="stateList" onchange="getCityByState()">
												
											</select>
										</div>
									</div>				        
						        	<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="city_label"><spring:message code="city.jsp.cityname" text="City Name" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                              				<select id="cityList" name="cityList" class="form-control">
                              				</select>
                              			</div>
                          			</div>
						        	<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label"><spring:message code="city.jsp.districtname" text="District Name" /></label>
                              			<div class="col-sm-8">
                                  			<input type="text" id="districtName" class="form-control">
                              			</div>
                          			</div>
                          			<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="name_label"><spring:message code="city.jsp.zonename" text="Zone Name" /></label>
                              			<div class="col-sm-8">
                                  			<input type="text" id="zoneName" class="form-control">
                              			</div>
                          			</div>
						        </div>
						        <input type="hidden" id="zoneId" value="">
						        <div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal" ><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
						        <button type="button" onclick="javascript:addEditZone()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.save" text="SAVE" /></button>
						      </div>
						    </div>
						  </div>
				</div> 
				
				<!-- Add/Edit zoneAddEditModal Modal Ends -->
				
			
				
				<table id="example" class="table table-bordered table-striped table-condensed table-hover display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th><spring:message code="city.jsp.zonename" text="Zone" /></th>
						<th><spring:message code="city.jsp.districtname" text="District" /></th>
						<th><spring:message code="city.jsp.cityname" text="City" /></th>
						<th><spring:message code="city.jsp.statename" text="State" /></th>
						<th><spring:message code="city.jsp.countryname" text="Country" /></th>
						<th><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
					</tr>
				</thead>
				
				<tbody>
				<c:if test="${!empty allZones }">
					<c:forEach items="${allZones}" var="allZone" varStatus="index">
						<tr>
						<td>${allZone.name}</td>
						<td>${allZone.districtName}</td>
						<td>${allZone.cityName}</td>
						<td>${allZone.stateName}</td>
						<td>${allZone.countryName}</td>	
						<td>
							<c:if test="${menuByUserDTO.isAll==1}">
								<button class="btn btn-info btn-xs" type="button" onclick="javascript:openAddEditModal(${allZone.id},${allZone.countryId},${allZone.stateId},${allZone.cityId},&quot;${allZone.districtName}&quot;,&quot;${allZone.name}&quot;)"><i class="fa fa-pencil"></i>&nbsp;<spring:message code="cmn.jsp.btn.edit" text="Edit" /></button>
							</c:if>
							<c:if test="${menuByUserDTO.isView==1}">
								<button class="btn btn-theme04 btn-xs" type="button" onclick="showAccGroupDelModal(${allZone.id})"><i class="fa fa-trash-o "></i>&nbsp;<spring:message code="cmn.jsp.btn.dlt" text="Delete" /></button>
							</c:if>
						</td>
					</tr>
					</c:forEach>
				</c:if>
				</tbody>
			</table>		
          		</div>
          	</div>
		</section><!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/inventory/zone/zone.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/inventory/zone/zone_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>

<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}

function showAccGroupDelModal(id)
{
	   document.getElementById('confirmId').value=id;
	   $('#confirmModal').modal('show');
} 

$(document).ready(function() {
    $('#example').DataTable({
    	"lengthChange": false,
    });
    $('.dataTables_filter input').attr("placeholder", getZoneText.dataTablePlaceHolder);
} );
</script>