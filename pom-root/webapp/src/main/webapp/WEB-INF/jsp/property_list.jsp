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
		<link rel="shortcut icon" type="image/png" href="<c:url value="/resources/images/logo2.png"></c:url>"/>
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
		  		<div class="extras">
		  			  <a class='dropdown-trigger' data-target='dropdown1'><i class="medium material-icons">menu</i></a>
		  			  <ul id='dropdown1' class='dropdown-content'>
					    <li><a href='<%= response.encodeURL(request.getContextPath() + "/myfavourites") %>'><spring:message code="index/myfavourites"/></a> <!--hardcoded--></li>
					    <li><a href="#!">B</a></li>
					    <li class="divider" tabindex="-1"></li>
					    <li><a href="?language=en"><i class="material-icons">language</i><spring:message code="navbar/languages/english"/></a></li>
					    <li><a href="?language=es_AR"><i class="material-icons">language</i><spring:message code="navbar/languages/spanish"/></a></li>
					  </ul>
		  		</div>
		  	</div>
		
		</nav>

		<div class="main-row-container">

			<div class="properties-column">

				<span class="properties-count"><span id="pCount" class="bold">${propertiesCount}</span> <spring:message code="property_list/properties"/></span>

				<div class="properties-list">
                    <c:if test="${propertiesCount <= 0}">
                        <span><spring:message code="property_list/no_properties"/></span>
                    </c:if>
					<c:forEach items="${propertiesList}" var="property" varStatus="loop">

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
										<a href="" class="card-text-wrap title"><c:out value="${property.adMessage}"/></a>
										<a href="" class="card-text-wrap subtitle"><c:out value="${property.street}"/> <c:out value="${property.number}"/> - <c:out value="${property.neighborhood}"/></a>
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

									<div class="action">
										<a href=""><spring:message code="index/card/contact"/></a>
									</div>
								</div>

							</div>
						</div>

					</c:forEach>

					<div class="pagination-container">
						<ul class="pagination">
						    <li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
						    <c:forEach begin="0" end="${pagesCount}" varStatus="loop">
						    	<li class="<c:if test="${loop.index} == 0">active</c:if>"><a onclick="getPage(${loop.index + 1})">${loop.index + 1}</a></li>
						    </c:forEach>
						    <li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
					  	</ul>
					</div>

				</div>

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


