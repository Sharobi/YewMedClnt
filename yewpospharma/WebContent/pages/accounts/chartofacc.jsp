<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet"> 
		<section class="wrapper">
			<div class="row">
          		<div class="col-lg-12">
   				<p><%-- <spring:message code="subcat.jsp.title" text="SubCategory..." /> --%></p>
   				<div class="panel panel-default">
					<div class="panel-body">
					
						<div class="col-lg-8 col-md-8 col-sm-12">
						 
						
							<div class="row">
							
							<div class="col-sm-6"><h3><spring:message code="accgroup.jsp.chartofaccount" text="Chart of Accounts" /></h3> </div>
							<div class="col-sm-3">
							<spring:message code="accgroup.jsp.asondate" text="As on Date" />
						 <form id="chart" action="chartofaccnt.htm" method="post">
								<input type="text" id="entrydate" name="asOnDate" class="form-control">
								</form>
							 
								</div>
									<div class="col-sm-3"></div>
							
							 
						</div>
						
						
						
						<%-- 	<form modelAttribute="commonResultSetMapper" class="form-inline" role="form" action="${pageContext.request.contextPath }/accntgrp/searchaccntgrp.htm" method="post">
								<div class="form-group">
	
									<input type="text" class="form-control" placeholder="Group Name" name="groupName" value="${commonResultSetMapper.groupName}">
								</div>
								<div class="form-group">
									<select class="form-control" name="qryCondition" >
										<option value="like">LIKE</option>
										<option value="equals">=</option>
									</select>
								</div>
								
							
								<button type="submit" class="btn btn-theme"><spring:message code="cmn.jsp.search" text="Search" /></button>
							</form> --%>
						</div>
					
				
					
					<%-- 
						<div class="pull-right">
							<c:if test="${menuByUserDTO.isAll==1}">
								<button class="btn btn-primary" type="button" onclick="javascript:openAddEditModal(0,'','','',0)"><i class="fa fa-plus"></i>&nbsp;<spring:message code="cmn.jsp.btn.add" text="Add" /></button>
							</c:if>
						</div> --%>
					</div>
				</div>
				
           		<%-- <input type="hidden" id="cmpny_id" value="${sessionScope.sesloggedinUser.companyId}" ></input>
           		<input type="hidden" id="user_id" value="${sessionScope.sesloggedinUser.id}" ></input> --%>
							
 
				
				
				
				
				<!-- ledger add modal   start here  -->
				
				<div class="modal fade" id="legersetupmodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 1000px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel"><span id="ledgersetupe"></span></h4>
			</div>
			<div class="modal-body" id="AccountSetupModalBody">
		 		<div class="col-md-6 col-sm-12"> 
			 
					<div class="form-horizontal">
					
					<!-- this for accountName -->
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="accountName_label"><spring:message code="accsetup.jsp.partyaccountname" text="Account Name" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<input type="hidden" value="" id="accountId" name="id"> 
								<input type="text" value="" id="accountNameId" class="form-control-trx" onblur="checkSameItem()" name="accountName" placeholder="<spring:message code="accsetup.jsp.partyaccountname" text="Item Name" />">
							</div>
						</div>
						<!-- this for accont Name exist -->
						<div class="form-group" id="accountname" style="display: none;">
						<div class="col-sm-4"></div>
						<div class="col-sm-8">
							<div class="alert alert-danger">
								<strong><spring:message code="footer.jsp.alert" text="Alert!" /></strong>
								<spring:message code="accsetup.jsp.accountexist" text="Account Name already exist, please try other." />
							</div>
							</div>
						</div>
						
						<!-- this for Alias Name -->
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="alias_label"><spring:message code="accsetup.jsp.Alias" text="Alias Name" /></label>
							<div class="col-sm-8">
								<input type="text" value="" id="aliasNameId" class="form-control-trx" name="aliasName" placeholder="<spring:message code="accsetup.jsp.Alias" text="Alias Name" />">
							</div>
						</div>
						
						
						
							
					<!-- this for account Group Name -->
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="accountGroup_label"><spring:message code="accsetup.jsp.accountgroupname" text="Account Group Name" /> <span class="required_star">*</span></label>
							<div class="col-sm-8">
								<div class="input-group">
									<select class="form-control-trx"  onchange="selectaccgroup()" name="accountGroupName" id="accountGroupNameId">
									
									</select>
									<div class="input-group-btn">
										<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openAddAccoutGroupModal()">
											<i class="fa fa-plus"></i>
										</button>
									</div>
								</div>
							</div>
						</div>
						
							 <!--  Opening Balance   Here    -->
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="accsetup.jsp.openingbalance" text="Opening Balance" />(${cur})</label>
							<div class="col-sm-8">
								<input type="text" id="openingBalanceId" value="" class="form-control-trx" name="openingBalanceName" placeholder="<spring:message code="accsetup.jsp.openingbalance" text="Opening Balance" />(${cur})">
							</div>
						</div>
								
					 	 <!--  (DR/CR)   Here    -->
					  <div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="accsetup.jsp.crdr" text="Dr/Cr" /></label>
							<div class="col-sm-8">
								<select class="form-control-trx" name="cr_dr" id="cr_dr" >
												
										         <option value="2">Dr</option>
										         <option value="1">Cr</option>
										 
									</select>
									
									<span  style="color: indianred;"> Assets / Expenses always have Dr balance and Liabilities / Incomes always have Cr balance</span>
								 
							</div>
							
					 
						</div>  
							 <!--  Credit limit or Debit Limit   Here    -->
						 
						  <div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="creditdebitlimit"><spring:message code="accsetup.jsp.creditlimitordebitlimit" text="Credit limit or Debit Limit" /></label>
							<div class="col-sm-8">
								<input type="text" id="crditlimitId" class="form-control-trx" name="crditlimitName" placeholder="<spring:message code="accsetup.jsp.creditlimitordebitlimit" text="Credit limit or Debit Limit" />">
							</div>
						</div> 
							<!--  Cash Discount Persentage Here    -->
					 
						  <div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="cashdiscountpercentage"><spring:message code="accsetup.jsp.cashdiscountpersentage" text="Cash Discount %" />  </label>
							<div class="col-sm-8">
								<input type="text" id="cashDiscountPersentageId"     class="form-control-trx" name="cashDiscountPersentageName" placeholder="<spring:message code="accsetup.jsp.cashdiscountpersentage" text="Cash Discount %" />">
							</div>
						</div>
				 
					  	<!-- phone  Number-->
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="phone_label"><spring:message code="dctr.jsp.phn" text="Phone No." /></label>
							<div class="col-sm-8">
								<input type="text" id="phoneno" value=""   maxlength="10" class="form-control-trx" name="phonenumber" placeholder="<spring:message code="dctr.jsp.phn" text="Phone No." />">
							</div>
						</div>  
					 <!-- email id  -->
				 	<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="email_label"><spring:message code="manufacturer.jsp.email" text="Email" /></label>
							<div class="col-sm-8">
								<input type="text" id="emailid" value="" class="form-control-trx" name="emailid" placeholder="<spring:message code="manufacturer.jsp.email" text="Email" />">
							</div>
						</div>  
					
					<!--   first  colum start end   -->
					 
						 
					</div>
				</div>
					
				<!--  first  colum start end  -->
				<!--   2 nd colum start here  -->
				  <div class="col-md-6 col-sm-12">
					<div class="form-horizontal">
					 		 	<!--  Pan Number here    -->
					 	<div class="form-group ">
							<label class="col-sm-4 col-sm-4 control-label" id="pan_label"><spring:message code="accsetup.jsp.panno" text="PAN No." /> </label>
							<div class="col-sm-8">
								<input type="text" id="panNoId"  maxlength="20" value="" class="form-control-trx" name="panNo" placeholder="<spring:message code="accsetup.jsp.panno" text="PAN No." />">
							</div>
						</div> 
					
						<!-- this for country name  -->
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="state_label"><spring:message code="dctr.jsp.country" text="Country" /> </label>
							<div class="col-sm-8">
								<div class="input-group">
									<select class="form-control-trx" name="countryName" id="countryId"  onchange="getStateByCountry();">
										<option value="0">Select...</option>
									<!-- 	<option value="101">india...</option> -->
									  <c:if test="${!empty contrylist}">
											<c:forEach items="${contrylist}" var="allcountry">
												<option value="${allcountry.id}">${allcountry.name}</option>
											</c:forEach>
										</c:if>  
									</select>
									<!-- <div class="input-group-btn">
										<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openSchMod()">
											<i class="fa fa-plus"></i>
										</button>
									</div> -->
								</div>
							</div>
						</div>
					<!-- this for stateName  -->
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="state_label"><spring:message code="accsetup.jsp.state" text="State" /> </label>
							<div class="col-sm-8">
						 
									<select class="form-control-trx" name="stateName" id="stateId" onChange="getCityByState()">
								 
								 
									</select>
									<!-- <div class="input-group-btn">
										<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openSchMod()">
											<i class="fa fa-plus"></i>
										</button>
									</div> -->
							 
							</div>
						</div>
						
							<!-- this for CityName  -->
				 <div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="city_label"><spring:message code="accsetup.jsp.city" text="City" /> </label>
							<div class="col-sm-8">
								<div class="input-group">
									<select class="form-control-trx" name="cityName" id="cityId" onChange="getZoneByCity()">
									 
									</select>
									<div class="input-group-btn">
										<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openCityModel()">
											<i class="fa fa-plus"></i>
										</button>
									</div>
								</div>
							</div>
						</div>
						
					 	<!-- this for Association Zonal Name  -->
				 <div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="zonal_label"><spring:message code="accsetup.jsp.associationzonalname" text="Zonal Name" /> </label>
							<div class="col-sm-8">
								<div class="input-group">
									<select class="form-control-trx" name="ZonalName" id="ZonalNameID" onChange="getAreaByZoneid();">
										 
									</select>
									<div class="input-group-btn">
										<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openZoneModel()">
											<i class="fa fa-plus"></i>
										</button>
									</div>
								</div>
							</div>
						</div>
					 
					 	 	<!-- this for Area    -->
				 <div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="area_label"><spring:message code="accsetup.jsp.area" text="Area" /> </label>
							<div class="col-sm-8">
								<div class="input-group">
									<select class="form-control-trx" name="AreaName" id="AreaId">
										 
									</select>
									<div class="input-group-btn">
										<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="openAreaModal()">
											<i class="fa fa-plus"></i>
										</button>
									</div>
								</div>
							</div>
						</div>
						
				
					 <!-- pin number -->
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="email_label"><spring:message code="dctr.jsp.pin" text="Pin" /></label>
							<div class="col-sm-8">
								<input type="text" id="pinno" maxlength="6" value="" class="form-control-trx" name="pinnumer" placeholder="<spring:message code="dctr.jsp.pin" text="Pin" />">
							</div>
						</div>
					 	 <!--address -->
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="email_label"><spring:message code="dctr.jsp.address" text="Address" /></label>
							<div class="col-sm-8">
								 <textarea  class="form-control-trx"  id="addressid" name="addressid" placeholder="<spring:message code="dctr.jsp.address" text="Address" />"></textarea>
							</div>
						</div>
					<%--   <!-- is active   -->
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="isactive_label"><spring:message code="accsetup.jsp.isactive" text="GST Reg. Number" /></label>
							<div class="col-sm-8">
							 <input type="checkbox"  onclick="gstcheck()" name="isactive" value="1" id="isactive" checked> 
							
							</div>
						</div> --%>
				
						<!-- GST Number-->
						<div class="form-group" id="gstnum">
							<label class="col-sm-4 col-sm-4 control-label" id="gstreg_label"><spring:message code="accsetup.jsp.gstregistrationnumber" text="GST Reg. Number" /></label>
							<div class="col-sm-8">
								<input type="text" id="GSTRegistrationNumberId" value="" class="form-control-trx" name="GSTRegistrationNumberName" placeholder="<spring:message code="accsetup.jsp.gstregistrationnumber" text="GST Reg. Number" />">
							</div>
						</div>
					 
			
						<!--  Pan Number here    -->
						<div class="form-group ">
							<label class="col-sm-4 col-sm-4 control-label" id="dlno_label"><spring:message code="accsetup.jsp.dlNo" text="D.L No." />  </label>
							<div class="col-sm-8">
								<input type="text" id="DLnoId"  value="" class="form-control-trx" name="DLNo" placeholder="<spring:message code="accsetup.jsp.dlNo" text="D.L No." />">
							</div>
						</div>
					 
						
						
						
						<%--  	<!--  Above Scheme  Here    -->
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="accsetup.jsp.abovescheme" text="Above Scheme" /></label>
							<div class="col-sm-8">
								<input type="text" id="aboveSchemeId" name="aboveScheme" class="form-control-trx" placeholder="<spring:message code="accsetup.jsp.abovescheme" text="Above Scheme" />" > 
							</div>
						</div> --%>
						
					<%-- 		<!-- extra discount here    -->
						<div class="form-group hide" id="extradiscountfield">
							<label class="col-sm-4 col-sm-4 control-label" id="extra_discount_label"><spring:message code="accsetup.jsp.extradicount" text="Extra Discount" /></label>
							<div class="col-sm-8">
								<input type="text" id="extra_discount" name="extra_discount"" class="form-control-trx" placeholder="<spring:message code="accsetup.jsp.extradicount" text="Extra Discount" />" > 
							</div>
						</div>
						 --%>
					 	
					
						
						
						 
				
					 
					 
					 	<%--  <!--  Due Day  Here    -->
						<div class="form-group hide">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="accsetup.jsp.dueday" text="Due Day" /></label>
							<div class="col-sm-8">
								<input type="text" id="dueday" value="" class="form-control-trx" name="duedayName" placeholder="<spring:message code="accsetup.jsp.dueday" text="Due Day" />">
							</div>
						</div> --%>
					 	<%--  <!--  BCDA registration Number Here    -->
						<div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label"><spring:message code="accsetup.jsp.bcdaregno" text="BCDA Reg. Number" /></label>
							<div class="col-sm-8">
								<input type="text" id="BCDAregistrationNumberId" value="" class="form-control-trx" name="BCDAregistrationNumber" placeholder="<spring:message code="accsetup.jsp.bcdaregno" text="BCDA Reg. Number" />">
							</div>
						</div> --%>
						
					
					
					</div>
				</div> 
			</div>
			<div class="modal-footer" style="border-top: 0px solid #e5e5e5;">
				<div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsgAccountSetup"></div>
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<spring:message code="cmn.jsp.btn.close" text="Close" />
				</button>
				<button class="btn btn-primary" type="button" id="accountsave" onclick="javascript:addnewAccountSetup()">
				 
		        <spring:message code="cmn.jsp.btn.save" text="SAVE" />
				</button>
			 
			</div>
		</div>
	</div>
</div>

<!-- add ledger modal end here -->
				
								<!-- Add/Edit accgrourpmodal Modal Starts -->
				
					<div class="modal fade" id="accgrourpmodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
						        <h4 class="modal-title" id="myModalLabel">
						        	<span id="accountgroupmodal"></span>
						        </h4>
						      </div>
						      <div class="modal-body">
						        <div class="form-horizontal">						        
						        	<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="acctypeid_label"><spring:message code="accgroup.jsp.acctype.name" text="Account Type" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                              				<select id="accTypeList" class="form-control">
                              					<option value="0.0">Select</option>
                              					<c:if test="${!empty allAccTypes}">
                              						<c:forEach items="${allAccTypes}" var="allAccType">
                              							<option value="${allAccType.id}">${allAccType.typeName}</option>
                              						</c:forEach>
                              					</c:if>
                              				</select>
                              			</div>
                          			</div>
						        	<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="accgrpname_label"><spring:message code="accgroup.jsp.name" text="Name" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                                  			<input type="text" id="accGroupName" class="form-control">
                              			</div>
                          			</div>
                          			<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="name_label2"><spring:message code="accgroup.jsp.code" text="Code" /><span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                                  			<input type="text" id="accGroupCode" class="form-control">
                              			</div>
                          			</div>
                          			<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label"><spring:message code="accgroup.jsp.desc" text="Description" /></label>
                              			<div class="col-sm-8">
                                  			<input type="text" id="accGroupDesc" class="form-control">
                              			</div>
                          			</div>
						        </div>
						        <input type="hidden" id="accGroupId" value="">
						        <div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="groupalertMsg"></div>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal" ><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
						        <button type="button" onclick="javascript:addEditaccGroup()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.save" text="SAVE" /></button>
						      </div>
						    </div>
						  </div>
				</div> 
				
				<!-- Add/Edit accgrourpmodal Modal Ends -->	
				
				
				<!-- add city modal start here  -->
				
				
			<div class="modal fade" id="cityAddEditModal" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
				data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
							<h4 class="modal-title" id="myModalLabel">
								<span id="headertextofcity"></span>
							</h4>
						</div>
						<div class="modal-body">
							<div class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-4 col-sm-4 control-label" id="name_label_countryincity"><spring:message code="city.jsp.countryname" text="Country" />
										<span class="required_star">*</span>
									</label>
									<div class="col-sm-8">
										<select class="form-control-trx" name="countryincity" id="countryidincity" onchange="getStateByCountryinCity()">
										 
											<option value="0">Select...</option>
												<c:if test="${!empty contrylist}">
											<c:forEach items="${contrylist}" var="allcountry">
												<option value="${allcountry.id}">${allcountry.name}</option>
											</c:forEach>
										</c:if>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 col-sm-4 control-label" id="name_label_stateincity"><spring:message code="city.jsp.statename" text="State" />
										<span class="required_star">*</span>
									</label>
									<div class="col-sm-8">
										<select class="form-control-trx" name="statelist" id="stateList">
											
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 col-sm-4 control-label" id="name_label_cityincity"><spring:message code="city.jsp.cityname" text="City" /><span class="required_star"> *</span></label>
									<div class="col-sm-8">
										<input type="text" id="cityName" class="form-control-trx">
									</div>
								</div>
							</div>
							<input type="hidden" id="selcityId" value="">
							<div
								style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;"
								id="alertMsgForcity"></div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">
								<spring:message code="cmn.jsp.btn.close" text="Close" />
							</button>
							<button type="button" id="savebtm" onclick="javascript:addEditCity()"
								class="btn btn-theme">
								<spring:message code="cmn.jsp.btn.save" text="SAVE" />
							</button>
						</div>
					</div>
				</div>
			</div>
				
				
				<!--  add city modal end here -->
				
				
				<!-- add zone modal start here  -->
				<div class="modal fade" id="zoneAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
						        <h4 class="modal-title" id="myModalLabel">
						        	<span id="headertextofzone"></span>
						        </h4>
						      </div>
						      <div class="modal-body">
						        <div class="form-horizontal">
						        	<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="country_labelinzone"><spring:message code="city.jsp.countryname" text="Country Name" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                              				<select id="countryidinzone" class="form-control" onchange="getStateByCountryinZone()">
                              					<option value="0">Select</option>
	                              				<c:if test="${!empty contrylist}">
													<c:forEach items="${contrylist}" var="countryMaster">
														<option value="${countryMaster.id}">${countryMaster.name}</option>
													</c:forEach>
												</c:if>
                              				</select>
                              			</div>
                          			</div>	
                          			<div class="form-group">
										<label class="col-sm-4 col-sm-4 control-label" id="state_labelinzone"><spring:message code="city.jsp.statename" text="State Name" />
											<span class="required_star">*</span>
										</label>
										<div class="col-sm-8">
											<select class="form-control-trx" name="statelist" id="stateListinzone" onchange="getCityByStateinzone()">
												
											</select>
										</div>
									</div>				        
						        	<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="city_labelinZone"><spring:message code="city.jsp.cityname" text="City Name" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                              				<select id="cityListinzone" name="cityList" class="form-control">
                              				</select>
                              			</div>
                          			</div>
						        	<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label"><spring:message code="city.jsp.districtname" text="District Name" /></label>
                              			<div class="col-sm-8">
                                  			<input type="text" id="districtNameinzone" class="form-control">
                              			</div>
                          			</div>
                          			<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="zonename_label"><spring:message code="city.jsp.zonename" text="Zone Name" /></label>
                              			<div class="col-sm-8">
                                  			<input type="text" id="zoneNameinzone" class="form-control">
                              			</div>
                          			</div>
						        </div>
						        <input type="hidden" id="zoneId" value="">
						        <div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsginzone"></div>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal" ><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
						        <button type="button" onclick="javascript:addEditZone()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.save" text="SAVE" /></button>
						      </div>
						    </div>
						  </div>
				</div> 
				<!--add zone modal end here   -->
				
				<!-- add area modal start here   -->
				<div class="modal fade" id="areaAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
						        <h4 class="modal-title" id="myModalLabel">
						        	<span id="headertextinarea"></span>
						        </h4>
						      </div>
						      <div class="modal-body">
						        <div class="form-horizontal">
						        	<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="country_labelinarea"><spring:message code="city.jsp.countryname" text="Country" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                              				<select id="countryListinarea" class="form-control" onchange="getStateByCountryinArea()">
                              					<option value="0">Select</option>
	                              				<c:if test="${!empty contrylist}">
													<c:forEach items="${contrylist}" var="countryMaster">
														<option value="${countryMaster.id}">${countryMaster.name}</option>
													</c:forEach>
												</c:if>
                              				</select>
                              			</div>
                          			</div>	
                          			<div class="form-group">
										<label class="col-sm-4 col-sm-4 control-label" id="state_labelinarea"><spring:message code="city.jsp.statename" text="State" />
											<span class="required_star">*</span>
										</label>
										<div class="col-sm-8">
											<select class="form-control-trx" name="statelist" id="stateListinarea" onchange="getCityByStateinarea()">
												
											</select>
										</div>
									</div>				        
						        	<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="city_labelinarea"><spring:message code="city.jsp.cityname" text="City" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                              				<select id="cityListinarea" name="cityList" class="form-control" onchange="getZoneByCityinArea()">
                              				</select>
                              			</div>
                          			</div>
						        	<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="zone_labelinarea"><spring:message code="city.jsp.zonename" text="Zone" /> <span class="required_star">*</span></label>
                              			<div class="col-sm-8">
                              				<select id="zoneListinarea" name="zoneList" class="form-control">
                              				</select>
                              			</div>
                          			</div>
                          			<div class="form-group">
                              			<label class="col-sm-4 col-sm-4 control-label" id="areaname_labelinarea"><spring:message code="city.jsp.areaname" text="Area Name" /></label>
                              			<div class="col-sm-8">
                                  			<input type="text" id="areaNameinarea" class="form-control">
                              			</div>
                          			</div>
						        </div>
						        <input type="hidden" id="areaId" value="">
						        <div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsginarea"></div>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal" ><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
						        <button type="button" onclick="javascript:addEditArea()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.save" text="SAVE" /></button>
						      </div>
						    </div>
						  </div>
				</div> 
				
				<!-- ======================================== end area modal=============================== -->
				
				
			  <span id="is_sys"></span>
			  
			 <!--  journal contra payment receipt  view add here  -->
			<!-- <div class="row"> -->
			
			<div class="col-sm-5">
			<form  action="loadaccntjrl.htm" method="post" id="entry" >
			<!-- <div class="form-group">
		 
                              			<label class="col-sm-2 col-sm-2 control-label"> </label>
                              			<div class="col-sm-6">
                             
                              		    	 
                              			</div>
                          			</div>	 -->
			</form>
			 	
			</div><!-- </div> -->
		
	 
				
				<table id="example" class="table table-bordered table-striped table-condensed table-hover display" cellspacing="0" width="100%">
				<thead>
				
	 
					<tr>
						<th><spring:message code="accgroup.jsp.type" text="Account Type" /></th>
				    	<th><spring:message code="accgroup.jsp.groupname" text="Group Name" /></th>
						<th><spring:message code="accsetup.jsp.partyaccountname" text="Account Name" /></th>
				 
						<th><spring:message code="accgroup.jsp.Currentbalance" text="Current Balance" /></th>
					</tr>
				</thead>
				
				<tbody>
			 
 
						
	 
				<c:if test="${!empty chartofaccounts }">
					<c:forEach items="${chartofaccounts}" var="ca" varStatus="index">
					<tr>
						<td>${ca.type_name}</td>	
						<td>${ca.group_name}</td>
						<td>${ca.account_name}</td>
					
						<td>
						
						<fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${ca.current_balance}" />
						<c:if test="${ca.current_balance>0}">
						 &nbsp;&nbsp;${ca.balance_type}
						
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
<script src="${pageContext.request.contextPath }/assets/js/accounts/journal/journal.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/accounts/journal/journal_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>

<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}

function showJournalDelModal(id)
{
	document.getElementById('confirmId').value=id;
	$('#confirmModal').modal('show');
		 
	
} 

function deleterow(x)
{
  
  $(x).parents("tr").remove();
}


 


$(document).ready(function() {
 
    $('#example').DataTable({
    	"lengthChange": false,
    	 
    });
    $(".dataTables_filter input").attr("placeholder", getjournalText.dataTablePlaceHolder);
    
    
    
    
    var currentDate = new Date();
    $('#entrydate').datepicker({
		format : 'yyyy-mm-dd',
		endDate : currentDate,
		autoclose : true,
	});
    
    var ason_Date="${ason_Date}";
  
	 if (ason_Date==0) {
		 $('#entrydate').val(moment(new Date()).format("YYYY-MM-DD"));
	}else
		{
		$('#entrydate').val(moment(ason_Date).format("YYYY-MM-DD"));
		}
	

	 $('#entrydate').on('changeDate', function(ev) {
         var date = $('#entrydate').val();
        
         $('#chart').submit();
     });
});


</script>