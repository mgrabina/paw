<%@taglib prefix ="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix ="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Playfair+Display:700|Raleway:500.700'>
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.11/css/all.css" integrity="sha384-p2jx59pefphTFIpeqCcISO9MdVfIm4pNnsL08A6v5vaQc4owkQqxMV8kg4Yvhaw/" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/materialize.css"></c:url>">
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/base.css"></c:url>">
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/register_property.css"></c:url>">
	</head>
	<body>
		<form:form modelAttribute="registerForm" action ='<%= response.encodeURL(request.getContextPath() + "/user/register") %>' method="post" enctype="multipart/form-data" >
			<div class="">
			  <div class="form row ">

				<div class="col-lg-3"></div>
				<div class="col-lg-6 card">
					<br>
					<div class="center-align">
						<h1 class="title bold"><spring:message code="register/title"/></h1>
					</div>

					<c:if test="${not empty errors}">
						<div class="card materialize-red-text padding" >
							<c:forEach var="error" items="${errors}">
								<span>~ ${error}</span><br>
							</c:forEach>
						</div>
					</c:if>

				  <form class="register-form">
					  <div class="row padding">
						  <div class="input-field">
							  <form:input id="u-username" type="text" path="username" />
							  <label for="u-username"><spring:message code="register/name"/></label>
						  </div>
					  </div>
					  <div class="row padding">
						  <div class="input-field">
							  <form:input id="u-password" type="password" path="password" />
							  <label for="u-password"><spring:message code="register/password"/></label>
						  </div>
					  </div>
					  <div class="row padding">
						  <div class="input-field">
							  <input type="password" id="u-repeat-password"/>
							  <label for="u-repeat-password"><spring:message code="register/repeat_password"/></label>
						  </div>
					  </div>
					  <div class="row padding">
						  <div class="input-field">
							  <form:input id="u-phone" type="text" path="phone" />
							  <label for="u-phone"><spring:message code="register/phone"/></label>
						  </div>
					  </div>
					  <div class="row padding">
						  <div class="input-field">
							  <form:input id="u-mail" type="text" path="mail" />
							  <label for="u-mail"><spring:message code="register/mail"/></label>
						  </div>
					  </div>
					  <div class="row padding">
						  <div class="file-field input-field">
							  <div class="btn">
								  <span><spring:message code="register/image"/></span>
								  <form:input type="file" id="profileImageFile" cssClass="form-control" style="" path="image"/>
							  </div>
							  <div class="file-path-wrapper">
								  <input class="file-path validate" type="text">
							  </div>
						  </div>
					  </div>
					  <div class="button-container center-align">
						  <input type="submit" class="btn btn-outline-success" value="<spring:message code="register/register"/>"/>
						  <br>
						  <p class="message"><spring:message code="register/already_registered"/><a href="<%= response.encodeURL(request.getContextPath() + "/user/login") %>"> <spring:message code="register/login"/></a></p>
					  </div>

				</form>
				</div>
			  <div class="col-lg-3"></div>
			  </div>
			</div>
		</form:form>
		<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>
		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA8Y4tkFGWovxIdXWcR_GyZLVIHeMzW9cQ&libraries=places&callback=initGoogleMapsAutocomplete"
				async defer></script>
		<script type="text/javascript" src="<c:url value="/resources/js/inputValidator.js"></c:url>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/register_property.js"></c:url>"></script>
	</body>
</html>

