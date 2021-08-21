<%@ page language="java" isErrorPage="true" import="java.io.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="keyword" content="">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>:. POS :: Error Page :.</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath }/assets/css/bootstrap/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="${pageContext.request.contextPath }/assets/css/font-awesome.css" rel="stylesheet" />
        
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath }/assets/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/assets/css/style-responsive.css" rel="stylesheet">
     <!-- HTML5 Shiv and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<section id="container" >
    <jsp:include page="/pages/common/header.jsp"></jsp:include>
    <section id="main-content">
    <section class="wrapper">
    	<div class="row">
          		<div class="col-lg-12">
          			<div align="center" style="font-weight: bold;font-size: 16px">
        				OOPS Error Occurred!!!
        			</div>
        			<div align="center" style="font-weight: bold;font-size: 14px">
        				Cause :${pageContext.exception}
        			</div>
        			<div align="center" style="font-weight: bold;font-size: 14px">
        				Details :${pageContext.exception}
        				<c:forEach var="trace" items="${pageContext.exception.stackTrace}" end="1">
        					${trace}<br/>
    					</c:forEach>
        			</div>
          		</div>
        </div>	
     	</section>
     	</section>
     </section>
    <!-- CONTENT-WRAPPER SECTION END-->
    
    <!-- js placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath }/assets/js/common/jquery-1.12.3.js"></script>
    <script src="${pageContext.request.contextPath }/assets/js/bootstrap/bootstrap.min.js"></script>
  
   </body>
</html>