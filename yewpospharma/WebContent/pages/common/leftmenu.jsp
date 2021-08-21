
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--sidebar start-->
<aside>

 

	<div id="sidebar" class="nav-collapse ">
		<!-- sidebar menu start-->
		<ul class="sidebar-menu" id="nav-accordion">

			<li class="mt"><c:if test="${home=='Y'}">
					<a class="active" href="${pageContext.request.contextPath }/home/welcome.htm"> <i class="fa fa-home"></i> <span>HOME</span>
					</a>
				</c:if> <c:if test="${home!='Y'}">
					<a href="${pageContext.request.contextPath }/home/welcome.htm"> <i class="fa fa-home"></i> <span>HOME</span>
					</a>
				</c:if></li>

			<c:if test="${! empty sesappMenuList}">
				<c:forEach items="${sesappMenuList}" var="appList">
					<li class="sub-menu"><c:choose>
							<c:when test="${menuselect.menu==appList.menuCode}">
							<c:if test="${appList.isNone==0 }">
								<a class="active" href="${appList.menuUrl}"> <c:if test="${appList.menuCode=='100'}">
										<i class="fa fa-desktop"></i>
									</c:if> <c:if test="${appList.menuCode=='200'}">
										<i class="fa fa-indent"></i>
									</c:if> <c:if test="${appList.menuCode=='300'}">
										<i class="fa fa-th"></i>
									</c:if> <c:if test="${appList.menuCode=='500'}">
										<i class="fa fa-wrench"></i>
									</c:if> <c:if test="${appList.menuCode=='700'}">
										<i class="fa fa-cogs"></i>
									</c:if> <c:if test="${appList.menuCode=='800'}">
										<i class="fa fa-file-text-o"></i>
									</c:if> 
									 <c:if test="${appList.menuCode=='400'}">
									<img alt="accounts" src="${pageContext.request.contextPath }/assets/images/accounting.png">
									</c:if> 
									
									<span>${appList.menuName}</span>
								</a>
								</c:if>
							</c:when>
							<c:otherwise>
							<c:if test="${appList.isNone==0 }">
								<a href="${appList.menuUrl}"> <c:if test="${appList.menuCode=='100'}">
										<i class="fa fa-desktop"></i>
									</c:if> <c:if test="${appList.menuCode=='200'}">
										<i class="fa fa-indent"></i>
									</c:if> <c:if test="${appList.menuCode=='300'}">
										<i class="fa fa-th"></i>
									</c:if> <c:if test="${appList.menuCode=='500'}">
										<i class="fa fa-wrench"></i>
									</c:if> <c:if test="${appList.menuCode=='700'}">
										<i class="fa fa-cogs"></i>
									</c:if> <c:if test="${appList.menuCode=='800'}">
										<i class="fa fa-file-text-o"></i>
									</c:if> 
									 <c:if test="${appList.menuCode=='400'}"> 
									<img alt="accounts" src="${pageContext.request.contextPath }/assets/images/accounting.png">
									</c:if> 
									<span>${appList.menuName}</span>
								</a>
								</c:if>
							</c:otherwise>
						</c:choose>
						<ul class="sub">
							<c:forEach items="${appList.subMenuList}" var="subMenu">
								<c:set var="submenuUrl" value="#"></c:set>
								<c:if test="${subMenu.menuUrl!='#'}">
									<c:set var="submenuUrl" value="${pageContext.request.contextPath}/${subMenu.menuUrl}.htm"></c:set>
								</c:if>
								<c:choose>
									<c:when test="${! empty subMenu.subMenuList}">
										<c:choose>
											<c:when test="${menuselect.subMenu==subMenu.menuCode}">
												<c:if test="${subMenu.isNone==0 }">
													<li><a class="active" href="${submenuUrl}">${subMenu.menuName}</a>
														<ul class="sub">
															<c:forEach items="${subMenu.subMenuList}" var="childsubMenu">
																<c:set var="childsubmenuUrl" value="#"></c:set>
																<c:if test="${childsubMenu.menuUrl!='#'}">
																	<c:set var="childsubmenuUrl" value="${pageContext.request.contextPath}/${childsubMenu.menuUrl}.htm"></c:set>
																</c:if>
																<c:choose>
																	<c:when test="${menuselect.childsubMenu==childsubMenu.menuCode}">
																	<c:if test="${childsubMenu.isNone==0 }">
																		<li  class="active"><a   href="${childsubmenuUrl}">${childsubMenu.menuName}</a></li>
																	</c:if>
																	</c:when>
																	<c:otherwise>
																	<c:if test="${childsubMenu.isNone==0 }">
																		<li><a href="${childsubmenuUrl}">${childsubMenu.menuName}</a></li>
																	</c:if>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
														</ul></li>
												</c:if>
											</c:when>
											<c:otherwise>
												<c:if test="${subMenu.isNone==0 }">
													<li><a href="${submenuUrl}">${subMenu.menuName}</a>
														<ul class="sub">
															<c:forEach items="${subMenu.subMenuList}" var="childsubMenu">
																<c:set var="childsubmenuUrl" value="#"></c:set>
																<c:if test="${childsubMenu.menuUrl!='#'}">
																	<c:set var="childsubmenuUrl" value="${pageContext.request.contextPath}/${childsubMenu.menuUrl}.htm"></c:set>
																</c:if>
																<c:if test="${childsubMenu.isNone==0 }">
																<li><a href="${childsubmenuUrl}">${childsubMenu.menuName}</a></li>
																</c:if>
															</c:forEach>
														</ul></li>
												</c:if>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${menuselect.subMenu==subMenu.menuCode}">
												<c:if test="${subMenu.isNone==0 }">
													<li class="active"><a href="${submenuUrl}">${subMenu.menuName}</a></li>
												</c:if>
											</c:when>
											<c:otherwise>
												<c:if test="${subMenu.isNone==0 }">
													<li><a href="${submenuUrl}">${subMenu.menuName}</a></li>
												</c:if>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</ul></li>
				</c:forEach>
			</c:if>
			<li class="sub-menu" style="cursor:pointer;">
				<a onclick="hotkeys()"> <i class="fa fa-key"></i> <span>HOTKEYS</span>
				</a>
				<!-- <div class="input-group-btn">
					<button class="btn btn-primary btn-sm" type="button" style="padding: 7px;" onclick="hotkeys()">
						<i class="fa fa-key"></i><span>HOTKEYS</span>
					</button>
				</div> -->
			</li>
		</ul>
		<!-- sidebar menu end-->
	</div>
</aside>
<!--sidebar end-->

<!-- HotKeys model start -->
<div class="modal fade" id="hotkeysModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
		        <h4 class="modal-title" id="myModalLabel">
		        	<span id="headertext">Shortcut Keys</span>
		        </h4>
		      </div>
		      <div class="modal-body">
		        <div class="form-horizontal">
		        	<div>
		        		<div class="large_table">
		        	
							<table width="100%" border="0" cellspacing="0" cellpadding="0" frame="hsides" rules="rows">
							   <tbody><tr class="band"><th><b>Shortcut key</b></th><th><b>Action</b></tr>
							   <tr style="background: #0dbacb; color:#fff;line-height: 25px;"><td colspan="2" align="center"><b>POS</b></td></tr>
							   <tr><td><b>F4</b></td><td>Cash Memo</td></tr>
							   <tr><td><b>Alt+A</b></td><td>ADD</td></tr>
							   <tr><td><b>Alt+U</b></td><td>UPDATE</td></tr>
							   <tr><td><b>Ctrl+Shft+C</b></td><td>CLEAR</td></tr>
							   <tr><td><b>Alt+N</b></td><td>NEW</td></tr>
							   <tr><td><b>Alt+M</b></td><td>SAVE</td></tr>
							   <tr><td><b>Alt+H</b></td><td>HOLD</td></tr>
							   <tr style="background: #0dbacb; color:#fff;line-height: 25px;"><td colspan="2" align="center"><b>PROCUREMENT</b></td></tr>
							   <!-- <tr><td><b>F7</b></td><td>Purchase Challan</td></tr> -->
							   <tr><td><b>F8</b></td><td>Purchase Invoice</td></tr>
							   <tr><td><b>Alt+I</b></td><td>ITEM</td></tr>
							   <tr><td><b>Alt+A</b></td><td>ADD</td></tr>
							   <tr><td><b>Alt+U</b></td><td>UPDATE</td></tr>
							   <tr><td><b>Ctrl+Shft+C</b></td><td>CLEAR</td></tr>
							   <tr><td><b>Alt+N</b></td><td>NEW</td></tr>
							   <tr><td><b>Alt+M</b></td><td>SAVE</td></tr>
							   <tr><td><b>Alt+R</b></td><td>RET</td></tr>
							   <tr><td><b>Alt+Shift+P</b></td><td>POST</td></tr>
							   <tr><td><b>Alt+D</b></td><td>DELETE</td></tr>
							   <tr style="background: #0dbacb; color:#fff;line-height: 25px;"><td colspan="2" align="center"><b>REPORTS</b></td></tr>
							   <tr><td><b>F9</b></td><td>Sales Summary</td></tr>
					   			</tbody>
						   </table>
						</div>
					</div>
                 </div>
		        <input type="hidden" id="grp_id" value=""></input>
		        <div style="text-align: center; color: #F60; font-size: 12px; font-weight: bold;" id="alertMsgGroup"></div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cmn.jsp.btn.close" text="Close" />Close</button>
		      </div>
		    </div>
		  </div>
	</div>
<!-- HotKeys modal end -->

<script type="text/javascript">
	function hotkeys() {
		document.getElementById('alertMsgGroup').innerHTML = '';
		$('#hotkeysModal').modal('show');
	}
</script>