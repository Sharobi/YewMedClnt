<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="keyword" content="">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>.: POS :: <tiles:getAsString name="title" /> :.</title>
	<link rel="icon" type="image/png" id="myfavicon" href="${pageContext.request.contextPath}/assets/images/favicon/favicon-16x16.png">
	 <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath }/assets/css/bootstrap/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/assets/css/bootstrap/datepicker.css" rel="stylesheet">
    <!--external css-->
    <link href="${pageContext.request.contextPath }/assets/css/font-awesome.css" rel="stylesheet" />
        
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath }/assets/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/assets/css/style-responsive.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="${pageContext.request.contextPath }/assets/js/common/jquery-1.12.3.js"></script>
</head>
<body>
	 <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->

	  <div id="login-page">
	  	<div class="container">
          			<tiles:insertAttribute name="body" />
        </div>
	  </div>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath }/assets/js/common/jquery-1.12.3.js"></script>
    <script src="${pageContext.request.contextPath }/assets/js/bootstrap/bootstrap.min.js"></script>

    <!--BACKSTRETCH-->
    <!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
    <script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/common/jquery.backstretch.min.js"></script>
    <script>
        $.backstretch("${pageContext.request.contextPath }/assets/images/login/yewpos-home.jpg", {speed: 500});
    </script>
</body>
</html>