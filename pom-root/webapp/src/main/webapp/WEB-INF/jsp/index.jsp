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
<!-- 		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.11/css/all.css" integrity="sha384-p2jx59pefphTFIpeqCcISO9MdVfIm4pNnsL08A6v5vaQc4owkQqxMV8kg4Yvhaw/" crossorigin="anonymous">
 -->		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/materialize.css"></c:url>">
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

		<div class="main-row-container">
			
			<div class="filters-column">
				<div class="shadow-box filters-box">
					<div class="title-box">
						<span class="filters-title"><spring:message code="index/applied-filters"/></span>
					</div>
					<div class="filters-list">
						<div class="chip">Argentina</div>
						<div class="chip">Buenos Aires</div>

						<c:forEach items="${filterNames}" var="filterEntry" varStatus="loop">
		      				<div class="link-box capit">
		      					<div class="chip"><span class="chip-text">${filterEntry.value}</span><i data-field="${filterEntry.key}" class="chip-click close material-icons">close</i></div>
		      				</div>
		      			</c:forEach>
						
						
					</div>
					<div class="footer">
						
						<a id="remove-filters"><i class="material-icons left">delete</i><spring:message code="index/filters/delete-filters"/></a>
						
					</div>
				</div>

				<div class="shadow-box filters-box">
					<div class="title-box">
						<span class="filters-title"><spring:message code="index/filters"/></span>
					</div>

					<div class="filters">
						<ul id="filters-id" class="collapsible expandable">
						    <li class="active">
						      <div class="collapsible-header"><i class="material-icons right">keyboard_arrow_right</i><spring:message code="index/filters/location"/></div>
						      <div class="collapsible-body">
						      	<div class="link-list">
						      		<c:forEach items="${filters}" var="filter" varStatus="loop">
						      			<c:if test="${filter.key eq 1}">
							      			<c:forEach items="${filter.value}" var="filterEntry" varStatus="loop">

							      				<div class="link-box capit">
							      					<a class="filter-click" id="f-loc-${loop.index}" data-field="neighborhood"><c:out value="${filterEntry.key}"/></a>
							      					<span>(<c:out value="${filterEntry.value}"/>)</span>
							      				</div>
							      			</c:forEach>
						      			</c:if>
					      			</c:forEach>
						      	</div>

						      	<div class="link-list" style="display: none;">
						      		<label>
								        <input type="checkbox" class="filled-in"  />
								        <span> Barrio 1</span>
								    </label>
								    <label>
								        <input type="checkbox" class="filled-in"  />
								        <span> Barrio 2</span>
								    </label>
								    <label>
								        <input type="checkbox" class="filled-in" />
								        <span> Barrio 3</span>
								    </label>
						      	</div>

						      </div>
						    </li>
						    
						    <li>
						      <div class="collapsible-header"><i class="material-icons right">keyboard_arrow_right</i><spring:message code="index/filters/type"/></div>
						      <div class="collapsible-body">
						      	<div class="link-list">
						      		<c:forEach items="${filters}" var="filter" varStatus="loop">
						      			<c:if test="${filter.key eq 3}">
							      			<c:forEach items="${filter.value}" var="filterEntry" varStatus="loop">
							      				<spring:message code="${filterEntry.key}" var="typeName"/>
							      				<div class="link-box capit">
							      					<a class="filter-click" data-field="type"><c:out value="${typeName}"/></a>
							      					<span>(<c:out value="${filterEntry.value}"/>)</span>
							      				</div>
							      			</c:forEach>
						      			</c:if>
					      			</c:forEach>
						      	</div>
						      </div>
						    </li>
						    <li>
						      <div class="collapsible-header"><i class="material-icons right">keyboard_arrow_right</i><spring:message code="index/filters/price"/> (US$)</div>
						      <div class="collapsible-body">

						      	<p class="range-field">
						      		<spring:message code="index/filters/min"/> 
							      <input type="range" id="min-price" min="0" max="999999" step="10000"/>
							    </p>
							    <p class="range-field">
							    	<spring:message code="index/filters/max"/>
							      <input type="range" id="max-price" min="0" max="999999" step="10000"/>
							    </p>
							    <a class="waves-effect waves-light btn top-margin" id="price-filter"><spring:message code="index/filters/apply-filters"/></a>

						      </div>
						    </li>
						    <!-- <li>
						      <div class="collapsible-header"><i class="material-icons right">keyboard_arrow_right</i><spring:message code="index/filters/tax"/> (US$)</div>
						      <div class="collapsible-body">

						      	<p class="range-field">
						      		<spring:message code="index/filters/min"/> 
							      <input type="range" id="min-e" min="1" max="10000" />
							    </p>
							    <p class="range-field">
							    	<spring:message code="index/filters/max"/>
							      <input type="range" id="max-e" min="0" max="10000" />
							    </p>

							    <a class="waves-effect waves-light btn top-margin" id="" href=""><spring:message code="index/filters/apply-filters"/></a>
						      </div>
						    </li> -->
						    <li style="display: none;">
						      <div class="collapsible-header"><i class="material-icons right">keyboard_arrow_right</i><spring:message code="index/filters/amenities"/></div>
						      <div class="collapsible-body">
						      	
						      	<div class="link-list">
						      		<label>
								        <input type="checkbox" class="filled-in" />
								        <span><spring:message code="index/filters/amenities/pool"/></span>
								    </label>
								    <label>
								        <input type="checkbox" class="filled-in"  />
								        <span><spring:message code="index/filters/amenities/sauna"/></span>
								    </label>
								    <label>
								        <input type="checkbox" class="filled-in"  />
								        <span><spring:message code="index/filters/amenities/sum"/></span>
								    </label>
						      	</div>
						      	<a class="waves-effect waves-light btn top-margin" id="" href=""><spring:message code="index/filters/apply-filters"/></a>

						      </div>
						    </li>
						
						    <li>
						      <div class="collapsible-header"><i class="material-icons right">keyboard_arrow_right</i><spring:message code="index/filters/meters"/></div>
						      <div class="collapsible-body">
						      	<p class="range-field">
						      		<spring:message code="index/filters/min"/> 
							      <input type="range" id="min-s" min="0" max="1000" step="10"/>
							    </p>
							    <p class="range-field">
							    	<spring:message code="index/filters/max"/>
							      <input type="range" id="max-s" min="0" max="1000" step="10"/>
							    </p>

							    <a class="waves-effect waves-light btn" id="area-filter"><spring:message code="index/filters/apply-filters"/></a>
						      </div>
						    </li>
						  <!--   <li>
						      <div class="collapsible-header"><i class="material-icons right">keyboard_arrow_right</i><spring:message code="index/filters/baths"/></div>
						      <div class="collapsible-body">
						      	<div class="link-list">
						      		<c:forEach items="${filters}" var="filter" varStatus="loop">
						      			<c:if test="${filter.key eq 4}">
							      			<c:forEach items="${filter.value}" var="filterEntry" varStatus="loop">

							      				<div class="link-box capit">
							      					<a class="filter-click" id="f-loc-${loop.index}" data-field="baths"><c:out value="${filterEntry.key}"/></a>
							      					<span>(<c:out value="${filterEntry.value}"/>)</span>
							      				</div>
							      			</c:forEach>
						      			</c:if>
					      			</c:forEach>
						      	</div>
						      </div>
						    </li> -->
						    <li>
						      <div class="collapsible-header"><i class="material-icons right">keyboard_arrow_right</i><spring:message code="index/filters/rooms"/></div>
						      <div class="collapsible-body">
						      	<div class="link-list">
						      		<c:forEach items="${filters}" var="filter" varStatus="loop">
						      			<c:if test="${filter.key eq 5}">
							      			<c:forEach items="${filter.value}" var="filterEntry" varStatus="loop">

							      				<div class="link-box capit">
							      					<spring:message code="index/filters/rooms" var="roomsStatic"/>
							      					<a class="filter-click" id="f-loc-${loop.index}" data-field="rooms" data-value="${filterEntry.key}">
							      						<c:out value="${filterEntry.key} ${roomsStatic}"/>
							      					</a>
							      					<span>(<c:out value="${filterEntry.value}"/>)</span>
							      				</div>
							      			</c:forEach>
						      			</c:if>
					      			</c:forEach>
						      	</div>
						      </div>
						    </li>
						    <li>
						      <div class="collapsible-header"><i class="material-icons right">keyboard_arrow_right</i><spring:message code="index/filters/general"/></div>
						      <div class="collapsible-body">
						      	
						      	<div class="link-list">
						      		<label>
						      			<c:choose>
						      				<c:when test="${not empty filterNames['garage']}">
						      					<input id="garage-input" type="checkbox" class="filled-in" checked="true" />
						      				</c:when>
						      				<c:otherwise>
						      					<input id="garage-input" type="checkbox" class="filled-in" />
						      				</c:otherwise>
						      			</c:choose>
								        <span><spring:message code="index/filters/general/garage"/></span>
								    </label>
						      	</div>
						      	<a class="waves-effect waves-light btn top-margin" id="general-filter"><spring:message code="index/filters/apply-filters"/></a>

						      </div>
						    </li>
						    <li>
						      <div class="collapsible-header"><i class="material-icons right">keyboard_arrow_right</i><spring:message code="index/filters/published-time"/></div>
						      <div class="collapsible-body">
						      	<div class="link-list">
						      		<c:forEach items="${timeFilter}" var="filterEntry" varStatus="loop">
						      			<spring:message code="${filterEntry.key}" var="filterName"/> 
						      			<c:if test="${filterEntry.value gt 0}">
						      				<div class="link-box">
					      						<a class="filter-click" id="f-time-${loop.index}" data-field="date">${filterName}</a>
					      						<span>(${filterEntry.value})</span>
					      					</div>
					      				</c:if>
					      			</c:forEach>
						      	</div>
						      </div>
						    </li>
						</ul>
					</div>
				</div>
			</div>

			<div class="properties-column">

				<span class="properties-count"><span id="pCount" class="bold">${propertiesCount}</span> <spring:message code="index/properties-count"/> Argentina</span>

				<div class="tabs-container">
				    <div class="col">
				      <ul id="operationTypeContainer" class="tabs">
				        <li id="sell-button-c" class="tab col"><a id="sell-button" class='active filter-click' data-value="sell" data-field="operation"><spring:message code="index/sell"/></a></li>
				        <li id="rent-button-c" class="tab col"><a id="rent-button" class='filter-click' data-value="rent" data-field="operation"><spring:message code="index/rent"/></a></li>
				        <li class="tab col"><a href="#temporal-rent" id="temporal-rent-button" class='filter-click' data-value="temporal_rent" data-field="operation"><spring:message code="index/temp-rent"/></a></li>
				      </ul>
				    </div>
				</div>

				<div class="order-box">
					<span class="order-by-text"><spring:message code="index/sort-by"/></span>
					<a id="order-button" class='dropdown-trigger' data-target='dropdown2'>
						<i class="material-icons right">keyboard_arrow_down</i>
						<c:choose>
							<c:when test="${orderBy eq 'price_asc'}">
								<spring:message code="index/sort/cheap"/>
							</c:when>
							<c:when test="${orderBy eq 'price_desc'}">
								<spring:message code="index/sort/expensive"/>
							</c:when>
							<c:when test="${orderBy eq 'date_asc'}">
								<spring:message code="index/sort/new"/>
							</c:when>
							<c:when test="${orderBy eq 'date_desc'}">
								<spring:message code="index/sort/old"/>
							</c:when>
							<c:otherwise>
								<spring:message code="index/sort/relevant"/>
							</c:otherwise>
						</c:choose>
					</a>
		  			  <ul id='dropdown2' class='dropdown-content'>
		  			  	<li><a class="order-btn" data-id=" "><spring:message code="index/sort/relevant"/></a></li>
					    <li><a class="order-btn" data-id="price_asc"><spring:message code="index/sort/cheap"/></a></li>
					    <li><a class="order-btn" data-id="price_desc"><spring:message code="index/sort/expensive"/></a></li>
					    <li><a class="order-btn" data-id="date_asc"><spring:message code="index/sort/new"/></a></li>
					    <li><a class="order-btn" data-id="date_desc"><spring:message code="index/sort/old"/></a></li>

					  </ul>
				</div>

				<div class="properties-list">

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
										<a href="" class="card-text-wrap subtitle"><c:out value="${property.street}"/> - <c:out value="${property.neighborhood}"/></a>
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
										<a class="modal-trigger contact-modal-link" data-id="${property.id}" data-name="${property.adMessage}" href="#contact-modal"><spring:message code="index/card/contact"/></a>
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

		
		<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/index.js"></c:url>"></script>
		<!-- <script async defer
				src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA8Y4tkFGWovxIdXWcR_GyZLVIHeMzW9cQ&callback=initMap">
		</script> -->
	</body>
</html>


