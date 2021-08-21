<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <link href="${pageContext.request.contextPath }/assets/css/datatable/dataTables.bootstrap.min.css" rel="stylesheet"> 
		<section class="wrapper">
			<div class="row">  <!-- start row  -->
          		<div class="col-lg-12">
   				 
   				<div class="panel panel-default">
					<div class="panel-body">
					<div class="pull-left">
							<h3><spring:message code="accgroup.jsp.listofaccount" text="List of Accounts " /></h3> 
							 
						</div>
					
						<div class="pull-right">
							<c:if test="${menuByUserDTO.isAll==1}">
								<button class="btn btn-primary" type="button" onclick="javascript:openAddEditModal(0,'','',0,0,0,0,0,'','','',0,0,0,0,'','','',0,0,'')"><i class="fa fa-plus"></i>&nbsp;<spring:message code="cmn.jsp.btn.add" text="Add" /></button>
							</c:if>
						</div>
					</div>
				</div>
				
           		 
							

				
		 
		 <div class="row">
		 
			 <div class="col-sm-12">
			 
			 
			  <table id="example" class="table table-bordered table-striped table-condensed table-hover display" cellspacing="0" >
					<thead>
						<tr>
						 <th><spring:message code="accgroup.jsp.type" text="Account Type" /></th>
							<th><spring:message code="accgroup.jsp.groupname" text="Group Name" /></th>
							<th><spring:message code="accsetup.jsp.partyaccountname" text="Account Name " /></th>
							<th><spring:message code="accsetup.jsp.Alias" text="Alias Name" /></th>
						    <th><spring:message code="accsetup.jsp.openingbalance" text="Opening Balance" /> &nbsp;(${cur})</th>
							<th><spring:message code="accsetup.jsp.crdr" text="DR/CR" /></th>
							 
							<th><spring:message code="cmn.jsp.tblhdr.action" text="Action" /></th>
						</tr>
					</thead>
					
					<tbody>
				 
					<c:if test="${!empty accountlist }">
						<c:forEach items="${accountlist}" var="ac" varStatus="index">
							<tr>
							<td>
							
						  	${ac.type_name}  
							
							</td>
						   <td>${ac.group_name}</td>
					       <td>${ac.name}</td>
							<td>${ac.code}</td>
					
						    <td> <fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${ac.opBalance}" /> </td>	
						    <td>
						    
						    <c:if test="${ac.opBalance>0}">   ${ac.pst_type_code}</c:if>
						    
				  	 
						    </td>
						   	 	 	 
							<td>  
							 <c:if test="${menuByUserDTO.isAll==1}">
	<button class="btn btn-info btn-xs" type="button" onclick="javascript:openAddEditModal(${ac.id},'${ac.name}','${ac.code}','${ac.group_code}',${ac.opBalance},${ac.pst_type_id},${ac.transLimit},${ac.cashDiscountPercentage},
	'${ac.phone}','${ac.email}','${ac.panNo}',${ac.cityId},${ac.zoneId},${ac.areaId},${ac.pin},'${ac.address}','${ac.gstRegistrationNo}','${ac.dlNo}',${ac.state_id},${ac.country_id},'${ac.country_name}')"><i class="fa fa-pencil"></i>&nbsp;<spring:message code="cmn.jsp.btn.edit" text="Edit" /></button>
								</c:if>
								<c:if test="${menuByUserDTO.isView==1}">
									<button class="btn btn-theme04 btn-xs" type="button" onclick="showAccGroupDelModal(${ac.id})"><i class="fa fa-trash-o "></i>&nbsp;<spring:message code="cmn.jsp.btn.dlt" text="Delete" /></button>
								</c:if>  
							</td>
						</tr>
						</c:forEach>
					</c:if>
					</tbody>
				</table>
			 </div>
				
		 </div>
				
				
          		</div>
          		</div>
          		
          						<!-- Add/Edit accSetupAddEditModal Modal Starts -->
				
	<div class="modal fade" id="accSetupAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 1000px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel"><span id="accountheadertext"></span></h4>
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
								<input type="text" id="openingBalanceId"  onkeydown="numcheck(event)"  value="" class="form-control-trx" name="openingBalanceName" placeholder="<spring:message code="accsetup.jsp.openingbalance" text="Opening Balance" />(${cur})">
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
								<input type="text" id="crditlimitId" onkeydown="numcheck(event)" class="form-control-trx" name="crditlimitName" placeholder="<spring:message code="accsetup.jsp.creditlimitordebitlimit" text="Credit limit or Debit Limit" />">
							</div>
						</div> 
							<!--  Cash Discount Persentage Here    -->
					 
						  <div class="form-group">
							<label class="col-sm-4 col-sm-4 control-label" id="cashdiscountpercentage"><spring:message code="accsetup.jsp.cashdiscountpersentage" text="Cash Discount %" />  </label>
							<div class="col-sm-8">
								<input type="text" id="cashDiscountPersentageId" onkeydown="numcheck(event)"     class="form-control-trx" name="cashDiscountPersentageName" placeholder="<spring:message code="accsetup.jsp.cashdiscountpersentage" text="Cash Discount %" />">
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
				<button class="btn btn-info " type="button" id="accountupdate" onclick="javascript:addnewAccountSetup()">
					<i class="fa fa-pencil"></i>
					<spring:message code="cmn.jsp.btn.update" text="Update" />
				</button>

			</div>
		</div>
	</div>
</div>
				
				<!-- Add/Edit accSetupAddEditModal Modal Ends -->
 <!-- extra modal start here ==================================================================== -->
          		
          					<!-- Add/Edit accGroupAddEditModal Modal Starts -->
				
					<div class="modal fade" id="accGroupAddEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
						        <h4 class="modal-title" id="myModalLabel">
						        	<span id="headertextaccountgroup"></span>
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
                              			<label class="col-sm-4 col-sm-4 control-label" id="name_label2"><spring:message code="accgroup.jsp.code" text="Code" /> <span class="required_star">*</span></label>
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
						        <div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsg"></div>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal" ><spring:message code="cmn.jsp.btn.close" text="Close" /></button>
						        <button type="button" onclick="javascript:addEditaccGroup()" class="btn btn-theme"><spring:message code="cmn.jsp.btn.save" text="SAVE" /></button>
						      </div>
						    </div>
						  </div>
				</div> 
				
				<!-- Add/Edit accGroupAddEditModal Modal Ends -->
				
				
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
				
				<!-- Add/Edit accGroupAddEditModal Modal Ends -->
				<!--  add area model end here -->		
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
 <!-- extra modal end here ==================================================================== -->
           
		</section><!--/wrapper -->
<script src="${pageContext.request.contextPath }/assets/js/accounts/accountsetup/accountsetup.js"></script>
<c:if test="${pageContext.response.locale == 'en'}">
	<script src="${pageContext.request.contextPath}/assets/js/accounts/accountsetup/accountsetup_en.js"></script>
	<script src="${pageContext.request.contextPath }/assets/js/common/common_en.js"></script>
</c:if>

<script type="text/javascript">
$(document).ready(function() {
	getAccountGroup();
    $('#example').DataTable({
    	"lengthChange": false,
    });
    $('.dataTables_filter input').attr("placeholder", getAccountSetupText.dataTablePlaceHolder);
    
    
    $("#cashDiscountPersentageId").keyup(function(){
    	
 
       
     if (this.value>=100) {
    	 $("#cashDiscountPersentageId").val('');
		}  
    });
});


var BASE_URL="${pageContext.request.contextPath}";

function showConfirmModal()
{
	$('#confirmMessageModal').modal('show');
}

function showConfirmModalNewItem() {
	$('#confirmMessageModalNewItem').modal('show');
}
function showAccGroupDelModal(id)
{
	   document.getElementById('confirmId').value=id;
	   $('#confirmModal').modal('show');
} 
</script>