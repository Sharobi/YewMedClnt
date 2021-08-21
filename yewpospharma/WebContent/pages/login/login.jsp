<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<form:form modelAttribute="user" class="form-login" action="${pageContext.request.contextPath }/authentication/dologin.htm" method="post">
		        <h2 class="form-login-heading"><spring:message code="login.jsp.please.signin" text="PLEASE SIGN IN" /></h2>
		        <div class="login-wrap">
		        	<form:errors path="*"  cssStyle="color:#a94442;"></form:errors>
					<input type="text" name="userName" class="form-control"  placeholder="<spring:message code="login.jsp.email" text="EMAIL-ID" />" autofocus/>
					<%-- <form:errors path="userName" cssStyle="color:#a94442;" /> --%>
		            <br>
		            <input type="password" name="password" class="form-control" placeholder="<spring:message code="login.jsp.password" text="PASSWORD" />"/>
		            <%-- <form:errors path="password" cssStyle="color:#a94442;" /> --%>
					<br>
					<input type="text" name="loginDate" id="loginDate" class="form-control" placeholder="Login Date" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />"/>
		            <br>
		           <%--  <input type="submit" value="<spring:message code="login.jsp.signin" text="SIGN IN" />" class="btn btn-theme btn-block"/> --%>
		            <button class="btn btn-theme btn-block" type="submit"><i class="fa fa-lock"></i> <spring:message code="login.jsp.signin" text="SIGN IN" /></button>
		            <hr>
		        </div>
</form:form>	  	
<script src="${pageContext.request.contextPath }/assets/js/bootstrap/bootstrap-datepicker.min.js"></script>
<script type="text/javascript">
var BASE_URL="${pageContext.request.contextPath}";
	
	var currentDate = new Date();
	$('#loginDate').datepicker({
		format : 'yyyy-mm-dd',
		endDate : currentDate,
		autoclose: true,
	});
	
	
	
</script>