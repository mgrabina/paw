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
				<form:input type="text" placeholder="Floor" path="floor" required="" class="text-register-property"/>
				<form:input type="text" placeholder="Apartment" path="apartment" required="" class="text-register-property"/>
				<form:input type="text" placeholder="Neighborhood" path="neighborhood" required="" class="text-register-property"/>
                <form:select name="operationTypeSelector" id="operationTypeSelector" path="operationType" class="text-register-property">
                    <form:option value="sell">Sell</form:option>
                    <form:option value="rent">Rent</form:option>
                    <form:option value="temporal_rent">Temporal Rent</form:option>
                </form:select>
				<form:select name="typeSelector" id="typeSelector" path="type" class="text-register-property">
                    <form:option value="house">House</form:option>
					<form:option value="apartment">Apartment</form:option>
					<form:option value="duplex">Duplex</form:option>
					<form:option value="office">Office</form:option>
					<form:option value="land">Land</form:option>
				</form:select>
				<form:input type="text" placeholder="Price" path="price" required="" class="text-register-property"/>
				<form:input type="text" placeholder="Covered Area" path="coveredArea" required="" class="text-register-property"/>
				<form:input type="text" placeholder="Total Area" path="totalArea" required="" class="text-register-property"/>
				<form:input type="text" placeholder="Rooms" path="rooms" required="" class="text-register-property"/>
				<form:input type="text" placeholder="Baths" path="baths" required="" class="text-register-property"/>
				<form:select name="selector2" id="selector2" path="garage" class="text-register-property">
					<form:option value="true">Con Garage</form:option>
					<form:option value="false">Sin Garage</form:option>
				</form:select>
				<form:input type="text" placeholder="Tax Price" path="taxPrice" required="" class="text-register-property"/>
				<form:input type="text" placeholder="Ad Message" path="adMessage" id="adMessageInput" required="" class="text-register-property"/>
				<form:input type="text" placeholder="Ad Description" path="adDescription" required="" class="text-register-property"/>
                <form:select name="inmediateDeliverySelector" id="inmediateDeliverySelector" path="inmediateDelivery" class="text-register-property">
                    <form:option value="true">Entrega Inmediata</form:option>
                    <form:option value="false">Pozo</form:option>
                </form:select>
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

