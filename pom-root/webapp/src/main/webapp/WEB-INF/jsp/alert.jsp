<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html>
<head>
<title>Chozapp</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="<c:url value="/resources/css/bootstrap.min.css"></c:url>" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
<link href="<c:url value="/resources/css/style.css"></c:url>" rel='stylesheet' type='text/css' />
<link href="<c:url value="/resources/css/font-awesome.css"></c:url>" rel="stylesheet">
<script src="<c:url value="/resources/js/jquery.min.js"></c:url>" > </script>
<script src="<c:url value="/resources/js/bootstrap.min.js"></c:url>" > </script>
</head>
<body>
	<div class="four">
		<h1><spring:message code="alert/title"/></h1>
        <p><a href="${pageContext.servletContext.contextPath}/"><spring:message code="error-page/return-home"/></a></p>
	</div>
		<!---->
<div class="copy-right">

</div>
<!---->
<!--scrolling js-->
	<script src="<c:url value="/resources/js/jquery.nicescroll.js"></c:url>" ></script>
	<script src="<c:url value="/resources/js/scripts.js"></c:url>" ></script>
	<!--//scrolling js-->
</body>
</html>

