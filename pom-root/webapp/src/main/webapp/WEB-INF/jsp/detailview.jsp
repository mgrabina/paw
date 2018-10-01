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
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Playfair+Display:700|Raleway:500.700'>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/materialize.css"></c:url>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/base.css"></c:url>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/index.css"></c:url>">
</head>
<body>
<nav class="navbar nav-header">

	<div class="navbar-logo">
		<a href="${pageContext.servletContext.contextPath}/" ><img class="logo" src="<c:url value="/resources/images/logo.png"></c:url>"></a>
	</div>

	<div class="search-box">
		<div class="nav-wrapper">
			<form action="<%= response.encodeURL(request.getContextPath() + "/search") %>" method="get">
				<div class="input-field">
					<input id="search" type="search" name="query" placeholder="Buscar" required>
					<label class="label-icon" for="search"><i class="material-icons icon-black">search</i></label>
					<i class="material-icons">close</i>
				</div>
			</form>
		</div>
	</div>

	<div class="buttons-box">
		<div class="labels">
			<c:if test="${empty myUser}">
				<div class="nav-item">
					<a href="<%= response.encodeURL(request.getContextPath() + "/user/register") %>"><spring:message code="index/register" /></a>
				</div>
				<div class="nav-item">
					<a href="<%= response.encodeURL(request.getContextPath() + "/user/login") %>"><spring:message code="index/login"/></a>
				</div>
			</c:if>
			<c:if test="${not empty myUser}">
				<div class="nav-item">
					<a href='<%= response.encodeURL(request.getContextPath() + "/myfavourites") %>'><spring:message code="index/myfavourites"/>
				</div>
			</c:if>
			<div class="nav-item">
				<a href='<%= response.encodeURL(request.getContextPath() + "/property/register") %>'><spring:message code="index/publish"/></a>
			</div>
		</div>

		<div class="extras">
			<a class='dropdown-trigger' data-target='dropdown1'><i class="medium material-icons">menu</i></a>
			<ul id='dropdown1' class='dropdown-content'>
				<c:if test="${not empty myUser}">
					<li><a href='<%= response.encodeURL(request.getContextPath() + "/myproperties") %>'><spring:message code="index/myproperties"/></a></li>
					<li><a href='<%= response.encodeURL(request.getContextPath() + "/user/logout") %>'><spring:message code="index/logout"/></a></li>
				</c:if>
				<li class="divider" tabindex="-1"></li>
				<li><a href="?language=en"><i class="material-icons">language</i><spring:message code="navbar/languages/english"/></a></li>
				<li><a href="?language=es_AR"><i class="material-icons">language</i><spring:message code="navbar/languages/spanish"/></a></li>
			</ul>
		</div>
	</div>

</nav>
<br>
<div class="shadow-box property-card" id="property-${loop.index}">
	<div class="left">
		<div class="image-container">
			<c:choose>
				<c:when test="${fn:length(property.images) == 0}">
					<img src="<c:url value="/resources/images/no-image.png"/>">
				</c:when>
				<c:otherwise>
					<div id="carousel-${loop.index}" class="carousel carousel-slider" data-indicators="true">
						<c:if test="${fn:length(property.images) > 1}">
							<div class="carousel-fixed-item center middle-indicator">
								<div class="left">
									<a data-id="${loop.index}" class="movePrevCarousel middle-indicator-text content-indicator"><i class="material-icons left  middle-indicator-text">chevron_left</i></a>
								</div>

								<div class="right">
									<a data-id="${loop.index}" class="moveNextCarousel middle-indicator-text content-indicator"><i class="material-icons right middle-indicator-text">chevron_right</i></a>
								</div>

							</div>
						</c:if>
						<c:forEach items="${property.images}" var="imageSrc" varStatus="loop">
							<a class="carousel-item" href="">
								<div class="image-cont">
									<img src="<c:out value="${imageSrc}"/>">
								</div>
							</a>
						</c:forEach>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="price-text">
									<span>
         								US$ <fmt:formatNumber value ="${property.price}" type = "number"/>
     								</span>
		</div>
	</div>
	<div class="right">
		<div class="header">
			<div class="left">
				<a class="card-text-wrap title"><c:out value="${property.adMessage}"/></a>
				<a class="card-text-wrap subtitle"><c:out value="${property.street}"/> - <c:out value="${property.neighborhood}"/></a>
			</div>
			<div class="right">
				<img src="<c:out value="${property.publisherUser.imageSrc}"/>">
			</div>
		</div>

		<div class="description">
			<span class="bold"><c:out value="${property.coveredArea}"/> <spring:message code="index/card/meters"/></span> </br>
			<div class="pDesc">
										<span style="overflow : hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 4;-webkit-box-orient: vertical;">
											<c:out value="${property.adDescription}"/>
										</span>
			</div>
		</div>

		<div class="footer">

			<div class="bold extra-info">
				<c:choose>
					<c:when test="${property.inmediateDelivery}">
						<span><spring:message code="index/card/immediate"/></span>
					</c:when>
					<c:otherwise>
						<span><spring:message code="index/card/immediate"/></span>
					</c:otherwise>
				</c:choose>
				<span>&#183;</span>
				<span><spring:message code="index/card/published-time-pre"/> <c:out value="${property.adDate}"/> <spring:message code="index/card/published-time-post"/></span>
			</div>
		</div>

	</div>
</div>

<div class="building-content">
	<div class="detail-view-container card">
		<div class="about-container">
			<div class="about-title-container">
				<i class="material-icons">home</i>
				<span class="about"><spring:message code="detailview/about" /></span>
			</div>
			<c:if test="${not empty myUser && property.publisherUser.id == myUser.id}">
				<div class="right-align edit-property-link-container">
					<a class="modal-trigger" href="#edit-property-modal"><div class="about-title-container">
						<i class="material-icons">edit</i>
						<span class="about"><spring:message code="detailview/edit" /></span>
					</div></a>
				</div>
			</c:if>
			<div class="divider"></div>
			<div class="divider"></div>
			<div class="about-content-container">
				<p class="street bold" ><spring:message code="detailview/street" /></p>
				<p class="street-info" id="street">${property.street}</p>
				<p class="floor bold"><spring:message code="detailview/floor" /></p>
				<p class="floor-info">${property.floor}</p>
				<p class="neighborhood bold"><spring:message code="detailview/neigborhood" /></p>
				<p class="neighborhood-info"  id="neighborhood">${property.neighborhood}</p>
				<p class="type bold"><spring:message code="detailview/type" /></p>
				<p class="type-info">${property.type}</p>
				<p class="price bold"><spring:message code="detailview/price" /></p>
				<p class="price-info">${property.price}</p>
				<p class="coveredArea bold"><spring:message code="detailview/coveredArea" /></p>
				<p class="coveredArea-info">${property.coveredArea}</p>
				<p class="totalArea bold"><spring:message code="detailview/totalArea" /></p>
				<p class="totalArea-info">${property.totalArea}</p>
			</div>
		</div>
		<div class="location-container">
			<div class="location-title-container">
				<i class="material-icons">location_on</i>
				<span class="location"><spring:message code="detailview/location" /></span>
			</div>
			<div class="divider"></div>
			<%--Key: AIzaSyA8Y4tkFGWovxIdXWcR_GyZLVIHeMzW9cQ--%>
			<div class="location-content-container">
				<div id="map"></div>
				<div id="pano"></div>
				<script>
                    function initialize() {
                        var address = $("#street").html() + ", " + $("#neighborhood").html() + ", Argentina" ;
                        var geocoder = new google.maps.Geocoder();
                        var lat, long, location;

                        if (geocoder) {
                            geocoder.geocode( { 'address': address}, function(results, status) {
                                if (status == google.maps.GeocoderStatus.OK) {
                                    if (status != google.maps.GeocoderStatus.ZERO_RESULTS) {
                                        lat = results[0].geometry.location.lat();
                                        long = results[0].geometry.location.lng();
                                        var property = {lat: lat, lng: long};
                                        var map = new google.maps.Map(document.getElementById('map'), {
                                            center: property,
                                            zoom: 14
                                        });
                                        var panorama = new google.maps.StreetViewPanorama(
                                            document.getElementById('pano'), {
                                                position: property,
                                                pov: {
                                                    heading: 34,
                                                    pitch: 10
                                                }
                                            });
                                        map.setStreetView(panorama);
                                    } else {
                                        // alert("No results found");
                                    }
                                } else {
                                    // alert("Geocode was not successful for the following reason: " + status);
                                }
                            });
                        }
                    }
				</script>
				<script async defer
						src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA8Y4tkFGWovxIdXWcR_GyZLVIHeMzW9cQ&callback=initialize">
				</script>
			</div>
		</div>
		<br>
		<div class="contact-container">
			<div class="contact-title-container">
				<i class="material-icons">contact_mail</i>
				<span class="contact"><spring:message code="detailview/contact_us" /></span>
			</div>
			<div class="divider"></div>
			<div class="row">
				<form method="post" action="<%= response.encodeURL(request.getContextPath() + "/contact") %>">
					<div class="row">
						<div class="input-field">
							<textarea id="message" class="materialize-textarea"></textarea>
							<label for="message"><spring:message code="detailview/message" /></label>
						</div>
					</div>
					<div class="row" style="display: none;">
						<%-- Hide Data for form --%>
						<div class="input-field">
							<textarea id="propertyId" class="materialize-textarea" value="${property.id}"></textarea>
						</div>
					</div>
					<div class="row">
						<div class="input-field">
							<input id="email" type="email" class="validate">
							<label for="email"><spring:message code="detailview/email" /></label>
						</div>
					</div>
					<input type="submit" class="waves-effect waves-light btn submit-btn bold">
				</form>
			</div>
		</div>
	</div>
</div>

<c:if test="${not empty myUser && property.publisherUser.id == myUser.id}">
<div id="edit-property-modal" class="modal modal-fixed-footer">
	<form class="col s12" method="post" action="<%= response.encodeURL(request.getContextPath() + "/property/update") %>">
		<div class="modal-content">
			<h4><spring:message code="property/edit/title"/></h4>
			<h5 id="property-name-input-modal"></h5>

			<div class="row" style="display: none">
				<div class="input-field col s12">
					<input id="property-id-input-modal" name="id" value="${property.id}" class="materialize-textarea"></input>
					<label for="property-id-input-modal"><spring:message code="property/edit/property-id"/></label>
				</div>
			</div>

			<div class="row">
				<div class="input-field col s12">
					<textarea id="message-input-modal" name="message" class="materialize-textarea">${property.adMessage}</textarea>
					<label for="message-input-modal"><spring:message code="property/edit/message"/></label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12">
					<textarea id="desc-input-modal" name="desc" class="materialize-textarea">${property.adDescription}</textarea>
					<label for="desc-input-modal"><spring:message code="property/edit/desc"/></label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12">
					<input  type="number" id="price-input-modal"  value="${property.price}" name="price" class="materialize-textarea"></input>
					<label for="price-input-modal"><spring:message code="property/edit/price"/></label>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<input type="submit" class="waves-effect waves-light btn">
		</div>
	</form>
</div>
</c:if>
<script src="<c:url value="/resources/js/materialize.min.js"></c:url>"> </script>
<script src="<c:url value="/resources/js/jquery.min.js"></c:url>"> </script>
<script src="<c:url value="/resources/js/detailview.js"></c:url>" type="text/javascript"> </script>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/js/index.js"></c:url>"></script>

</body>
</html>