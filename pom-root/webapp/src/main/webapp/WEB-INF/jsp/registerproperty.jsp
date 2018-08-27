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
<title>Register Property</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../../resources/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
<link href="../../resources/css/registerproperty.css" rel='stylesheet' type='text/css' />
<link href="../../resources/css/font-awesome.css" rel="stylesheet">
<script src="../../resources/js/jquery.min.js"> </script>
<script src="../../resources/js/bootstrap.min.js"> </script>
</head>
<body>
<form:form modelAttribute="newPropertyForm" action ='<%= response.encodeURL(request.getContextPath() + "/property/register") %>' method="post" >
	<div class="login">
		<h1>Register Property</h1>
		<div class="login-bottom">
			<div class="container-form">

				<form:input type="text" placeholder="Street" path="street" required="" class="text-register-property"/>
				<form:input type="text" placeholder="Number" path="number" required="" class="text-register-property"/>
				<form:input type="text" placeholder="Apartment" path="apartment" required="" class="text-register-property"/>
				<select name="selector1" id="selector1" class="text-register-property">
					<option>Type</option>
					<option>Building</option>
					<option>House</option>
				</select>
				<form:input type="text" placeholder="Price" path="price" required="" class="text-register-property"/>
				<form:input type="text" placeholder="Covered Area" path="coveredArea" required="" class="text-register-property"/>
				<form:input type="text" placeholder="Total Area" path="totalArea" required="" class="text-register-property"/>
				<form:input type="text" placeholder="Rooms" path="rooms" required="" class="text-register-property"/>
				<form:input type="text" placeholder="Baths" path="baths" required="" class="text-register-property"/>
				<form:input type="text" placeholder="Garage" path="garage" required="" class="text-register-property"/>
				<form:input type="text" placeholder="Tax Price" path="taxPrice" required="" class="text-register-property"/>

				<input type="submit" value="Register" class="submit btn-primary btn"/>
			</div>
			
			<div class="clearfix"> </div>
		</div>
	</div>
	  
<!---->
<!--scrolling js-->
	<script src="../../resources/js/jquery.nicescroll.js"></script>
	<script src="../../resources/js/scripts.js"></script>
	<!--//scrolling js-->
</form:form>
</body>
</html>

