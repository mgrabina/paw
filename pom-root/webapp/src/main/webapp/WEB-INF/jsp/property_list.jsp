<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>

	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<title><spring:message code="page-title" /></title>
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	    <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Playfair+Display:700|Raleway:500.700'>
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.11/css/all.css" integrity="sha384-p2jx59pefphTFIpeqCcISO9MdVfIm4pNnsL08A6v5vaQc4owkQqxMV8kg4Yvhaw/" crossorigin="anonymous">
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
						<li class="profile-container">
							<div class="profile-img-container">
								<img class="rounded-image" alt="profile picture" src="<c:out value="${myUser.imageSrc}"/>">
							</div>
							<span><c:out value="${myUser.name}"/></span>
						</li>
					</c:if>
					<li class="divider" tabindex="-1"></li>

					<c:if test="${not empty myUser}">
						<li><a href='<%= response.encodeURL(request.getContextPath() + "/myproperties") %>'><spring:message code="index/myproperties"/></a></li>
						<li><a href='<%= response.encodeURL(request.getContextPath() + "/user/logout") %>'><spring:message code="index/logout"/></a></li>
					</c:if>
					<li>
						<a id="english-btn">
							<div class="lang-container">
								<img class="lang-flag" src="<c:url value="/resources/images/usa.png"></c:url>">
								<span class="language"><spring:message code="navbar/languages/english"/></span>
							</div>
						</a>
					</li>
					<li>
						<a id="spanish-btn">
							<div class="lang-container">
								<img class="lang-flag" src="<c:url value="/resources/images/spain.png"></c:url>">
								<span class="language"><spring:message code="navbar/languages/spanish"/></span>
							</div>
						</a>
					</li>
				</ul>
			</div>
		</div>

	</nav>

		<div class="main-row-container">

			<div class="properties-column">
				<c:choose >
					<c:when test="${title == 0}">
						<h1 class="title"><spring:message code="listview/myProperties"/></h1>
					</c:when>
					<c:when test="${title == 1}">
						<h1 class="title"><spring:message code="listview/myFavourites"/></h1>
					</c:when>
					<c:when test="${title == 2}">
						<h1 class="title"><spring:message code="listview/search"/></h1>
					</c:when>
				</c:choose>


				<span class="properties-count"><span id="pCount" class="bold">${propertiesCount}</span> <spring:message code="property_list/properties"/></span>

				<div class="properties-list">
                    <c:if test="${propertiesCount <= 0}">
                        <span><spring:message code="property_list/no_properties"/></span>
                    </c:if>
					<c:forEach items="${propertiesList}" var="property" varStatus="loop">

						<div class="shadow-box property-card hoverable" id="property-${loop.index}">
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
										<a href="<%= response.encodeURL(request.getContextPath() + "/property/")%>${property.id}" class="card-text-wrap title"><c:out value="${property.adMessage}"/></a>
										<a href="<%= response.encodeURL(request.getContextPath() + "/property/")%>${property.id}" class="card-text-wrap subtitle"><c:out value="${property.street}"/> - <c:out value="${property.neighborhood}"/></a>
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

										<c:if test="${not empty myUser && property.publisherUser.id == myUser.id}">
											<i data-id="${property.id}" data-action="0" class="fa fa-trash delete-property-link modal-trigger " href="#deleteModal"></i>
										</c:if>

									</div>

									<div class="action">
										<c:if test="${not empty myUser && myUser.id != property.publisherUser.id}">
											<a class="modal-trigger contact-modal-link" data-id="${property.id}" data-name="${property.adMessage}" href="#contact-modal"><spring:message code="index/card/contact"/></a>
										</c:if>
									</div>
								</div>

							</div>
						</div>

					</c:forEach>

					<div class="pagination-container">
						<ul class="pagination">
							<li class="disabled"><a onclick="getPage(0)"><i class="material-icons">chevron_left</i><i class="material-icons">chevron_left</i><i class="material-icons">chevron_left</i></a></li>
							<c:forEach begin="1" end="${pagesCount}" varStatus="loop">
								<li class="<c:if test="${loop.index} == 0">active</c:if>"><a onclick="getPage(${loop.index})">${loop.index}</a></li>
							</c:forEach>
							<li class="waves-effect"><a onclick="getPage(${pagesCount})"><i class="material-icons">chevron_right</i><i class="material-icons">chevron_right</i><i class="material-icons">chevron_right</i></a></li>
						</ul>
					</div>

				</div>

			</div>

			<div id="deleteModal" class="modal">
				<div class="modal-content">
					<h4><spring:message code="index/delete_modal/title"/></h4>
					<p><spring:message code="index/delete_modal/message"/></p>
					<span id="property-id-to-delete" style="display: none"></span>
				</div>
				<div class="modal-footer">
					<a id="delete-property-button" class="modal-close waves-effect waves-green btn-flat"><spring:message code="index/delete_modal/confirm"/></a>
				</div>
			</div>

			<div id="contact-modal" class="modal modal-fixed-footer">
				<form class="col s12" method="post" action="<%= response.encodeURL(request.getContextPath() + "/contact") %>">
					<div class="modal-content">
						<h4><spring:message code="index/card/contact/modal/title"/></h4>
						<h5 id="property-name-input-modal"></h5>

						<div class="row" style="display: none">
							<div class="input-field col s12">
								<textarea id="property-id-input-modal" name="propertyId" class="materialize-textarea"></textarea>
								<label for="property-id-input-modal"><spring:message code="index/card/contact/modal/property-id"/></label>
							</div>
						</div>

						<div class="row">
							<div class="input-field col s12">
								<textarea id="message-input-modal" name="message" class="materialize-textarea"></textarea>
								<label for="message-input-modal"><spring:message code="index/card/contact/message"/></label>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<input type="submit" class="waves-effect waves-light btn">
					</div>
				</form>
			</div>


		</div>

		
		<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/index.js"></c:url>"></script>
		<!-- <script async defer
				src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA8Y4tkFGWovxIdXWcR_GyZLVIHeMzW9cQ&callback=initMap">
		</script> -->
	</body>
</html>


