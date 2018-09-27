<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
<title>My properties</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/resources/css/materialize.min.css"></c:url>" rel='stylesheet' type='text/css' />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="<c:url value="/resources/css/viewmyproperties.css"></c:url>" rel='stylesheet' type='text/css' />

<script src="<c:url value="/resources/js/materialize.min.js"></c:url>"> </script>
<script src="<c:url value="/resources/js/jquery.min.js"></c:url>"> </script>

</head>
<body>

	<c:forEach items="${propertiesList}" var="property" varStatus="loop">
		<a href="<c:url value="/property/${property.id}"></c:url>">
            <div class="property-container">
                <div class="image-container">
                    <img src="<c:url value="/resources/images/b1.jpg"></c:url>" class="image" />
                </div>
                <div class="content-container">
                    <div>
                        <h3 class="building-title">${property.adMessage}</h3>
                    </div>
                    <h5>${property.street} ${property.number} ${property.floor} ${property.apartment}</h5>
                    <h4 class="green-text">US$${property.price}</h4>
                </div>
            </div>
        </a>
	</c:forEach>
	<!--<div class="property-container">
		<div class="image-container">
			<img src="/resources/images/b1.jpg" class="image" />
		</div>
		<div class="content-container">
			<div>
				<h3 class="building-title">Building name</h3>
			</div>
			<h5>Av. Juramento 2786</h5>
			<h4 class="green-text">US$500,000</h4>
		</div>
	</div>
	<div class="property-container">
		<div class="image-container">
			<img src="/resources/images/b2.jpg" class="image" />
		</div>
		<div class="content-container">
			<div>
				<h3 class="building-title">Building name</h3>
			</div>
			<h5>Av. Juramento 2786</h5>
			<h4 class="green-text">US$500,000</h4>
		</div>
	</div>
	<div class="property-container">
		<div class="image-container">
			<img src="/resources/images/b3.jpg" class="image" />
		</div>
		<div class="content-container">
			<div>
				<h3 class="building-title">Building name</h3>
			</div>
			<h5>Av. Juramento 2786</h5>
			<h4 class="green-text">US$500,000</h4>
		</div>
	</div>-->
</body>
</html>