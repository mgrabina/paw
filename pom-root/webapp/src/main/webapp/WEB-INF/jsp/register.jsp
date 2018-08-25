<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->

<!DOCTYPE HTML>
<html>
	<head>
		<title>Sign Up</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="../../resources/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
		<!-- Custom Theme files -->
		<link href="../../resources/css/signup.css" rel='stylesheet' type='text/css' />
		<link href="../../resources/css/font-awesome.css" rel="stylesheet">
		<script src="../../resources/js/jquery.min.js"> </script>
		<script src="../../resources/js/bootstrap.min.js"> </script>
	</head>
	<body>
		<form:form modelAttribute="registerForm" action ='<%= response.encodeURL(request.getContextPath() + "/user/register") %>' method="post" enctype="multipart/form-data">
			<div class="login-page">
			  <div class="form">
				<h1 class="title">Sign Up</h1>
				<form class="register-form">
				  <form:input type="text" path="username" placeholder="Name"/>
				  <form:input type="text" path="surname" placeholder="Surname"/>
				  <form:input type="password" path="password" placeholder="Password"/>
				  <input type="password" placeholder="Repeat password"/>
				  <form:input type="text" path="phone" placeholder="Phone"/>
				  <form:input type="text" path="mail" placeholder="Mail"/>
				  <button>Create account</button>
				  <p class="message">Already registered? <a href="#">Sign In</a></p>
				</form>
			  </div>
			</div>
		</form:form>
	</body>
</html>

