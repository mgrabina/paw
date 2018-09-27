<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
<title>Detail View</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/resources/css/materialize.min.css"></c:url>" rel='stylesheet' type='text/css' />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="<c:url value="/resources/css/detailview.css"></c:url>" rel='stylesheet' type='text/css' />

<script src="<c:url value="/resources/js/materialize.min.js"></c:url>"> </script>
<script src="<c:url value="/resources/js/jquery.min.js"></c:url>"> </script>
<script src="<c:url value="/resources/js/detailview.js"></c:url>" type="text/javascript"> </script>

</head>
<body>
	<div class="detail-view-container">
		<div class="image-container">
			<img src="<c:url value="/resources/images/b5.jpg"></c:url>" class="image"/>
		</div>

		<label title="Add to my favourites" class="heart right-align" idProperty="${id}" addedToFavourites="${addedToFavourites}"></label>

		<div class="building-content">
			<div class="building-title-container">
				<h3 class="building-title">${name}</h3>
				<a class="waves-effect waves-light btn-small black edit-building-info"><i class="material-icons">edit</i></a>
			</div>

			<div class="rating-container">
                <fieldset class="rating">
                    <label class = "full"></label>
                    <label class="half"></label>
                    <label class = "full"></label>
                    <label class="half fill"></label>
                    <label class = "full fill"></label>
                    <label class="half fill"></label>
                    <label class = "full fill"></label>
                    <label class="half fill"></label>
                    <label class = "full fill"></label>
                    <label class="half fill"></label>
                </fieldset>
				<h5 class="rating-text"><spring:message code="detailview/rating" /> ${rating}</h5>
			</div>

			<div class="description-container">
				<div class="description-title-container">
					<i class="material-icons">description</i>
					<span class="description"><spring:message code="detailview/description" /></span>
				</div>
				<div class="divider"></div>
				<div class="description-content-container">
					<p>${description}</p>
				</div>
			</div>

			<div class="about-container">
				<div class="about-title-container">
					<i class="material-icons">home</i>
					<span class="about"><spring:message code="detailview/about" /></span>
				</div>
				<div class="divider"></div>
				<div class="divider"></div>
				<div class="about-content-container">
					<p class="street"><spring:message code="detailview/street" /></p>
					<p class="street-info">${street}</p>
					<p class="number"><spring:message code="detailview/number" /></p>
					<p class="number-info">${number}</p>
					<p class="floor"><spring:message code="detailview/floor" /></p>
					<p class="floor-info">${floor}</p>
					<p class="type"><spring:message code="detailview/type" /></p>
					<p class="type-info">${type}</p>
					<p class="price"><spring:message code="detailview/price" /></p>
					<p class="price-info">${price}</p>
					<p class="area"><spring:message code="detailview/area" /></p>
					<p class="area-info">${area}</p>
				</div>
			</div>

			<div class="contact-container">
				<div class="contact-title-container">
					<i class="material-icons">contact_mail</i>
					<span class="contact"><spring:message code="detailview/contact_us" /></span>
				</div>
				<div class="divider"></div>
				<div class="row">
				    <form>
				      <div class="row">
				        <div class="input-field">
				          <textarea id="message" class="materialize-textarea"></textarea>
				          <label for="message"><spring:message code="detailview/message" /></label>
				        </div>
				      </div>
				      <div class="row">
				        <div class="input-field">
				          <input id="email" type="email" class="validate">
				          <label for="email"><spring:message code="detailview/email" /></label>
				        </div>
				      </div>
				      <div class="row">
				        <div class="input-field">
				          <input id="name" type="text" class="validate">
				          <label for="name"><spring:message code="detailview/name" /></label>
				        </div>
				      </div>
				      <div class="row">
				        <div class="input-field">
				          <input id="phone" type="tel" class="validate">
				          <label for="phone"><spring:message code="detailview/phone_number" /></label>
				        </div>
				      </div>
				      <a class="waves-effect waves-light btn black"><spring:message code="detailview/send_message" /></a>
				    </form>
			  	</div>
		    </div>

			<div class="location-container">
				<div class="location-title-container">
					<i class="material-icons">location_on</i>
					<span class="location"><spring:message code="detailview/location" /></span>
				</div>
				<div class="divider"></div>
				<div class="location-content-container">
					<iframe src="https://www.google.com/maps/embed?pb" width="100%" height="600" frameborder="0" style="border:0" allowfullscreen></iframe>
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>