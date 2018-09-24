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
		  		<div class="labels">
		  			<a href=""><spring:message code="index/register" /></a>
		  			<a href=""><spring:message code="index/login"/></a>
		  			<a href='<%= response.encodeURL(request.getContextPath() + "/property/register") %>'><spring:message code="index/publish"/></a>
					<a href='<%= response.encodeURL(request.getContextPath() + "/myproperties") %>'><spring:message code="index/myproperties"/></a> <!--hardcoded-->
					
		  		</div>

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
			
			<div class="filters-column">
				<div class="shadow-box filters-box">
					<div class="title-box">
						<span class="filters-title"><spring:message code="index/applied-filters"/></span>
					</div>
					<div class="filters-list">
						<div class="chip">Argentina</div>
						<%--<%@
						<div class="chip">Filter name 2<i class="close material-icons">close</i></div>
						<div class="chip">Filter name 3<i class="close material-icons">close</i></div>
						<div class="chip">Filter name 4<i class="close material-icons">close</i></div>
						<div class="chip">Filter name 5<i class="close material-icons">close</i></div>
						<div class="chip">Filter name 6<i class="close material-icons">close</i></div>
						%>--%>
					</div>
					<div class="footer">
						<%--<%@
						<a href=""><i class="material-icons left">delete</i><spring:message code="index/filters/delete-filters"/></a>
						%>--%>
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
						      		<div class="link-box">
						      			<a href="">Capital federal</a>
						      			<span>(28)</span>
						      		</div>
						      		<div class="link-box">
						      			<a href="">San isidro</a>
						      			<span>(21)</span>
						      		</div>
						      		<div class="link-box">
						      			<a href="">Quilmes</a>
						      			<span>(8)</span>
						      		</div>
						      		<div class="link-box">
						      			<a href="">Tigre</a>
						      			<span>(11)</span>
						      		</div>
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
						      <div class="collapsible-header"><i class="material-icons right">keyboard_arrow_right</i><spring:message code="index/filters/address"/></div>
						      <div class="collapsible-body">
						      	<div class="input-field">
						          <input placeholder="Ej: Av. del Libertador" id="address" type="text" class="validate">
						          <label for="address"><spring:message code="index/filters/address"/></label>
						        </div>
						      </div>
						    </li>
						    <li>
						      <div class="collapsible-header"><i class="material-icons right">keyboard_arrow_right</i><spring:message code="index/filters/type"/></div>
						      <div class="collapsible-body">
						      	<div class="link-list">
						      		<div class="link-box">
						      			<a href=""><spring:message code="index/filters/type/apartment"/></a>
						      			<span>(289)</span>
						      		</div>
						      		<div class="link-box">
						      			<a href=""><spring:message code="index/filters/type/house"/></a>
						      			<span>(211)</span>
						      		</div>
						      		<div class="link-box">
						      			<a href=""><spring:message code="index/filters/type/ph"/></a>
						      			<span>(88)</span>
						      		</div>
						      		<div class="link-box">
						      			<a href=""><spring:message code="index/filters/type/land"/></a>
						      			<span>(111)</span>
						      		</div>
						      		<div class="link-box">
						      			<a href=""><spring:message code="index/filters/type/office"/></a>
						      			<span>(10)</span>
						      		</div>
						      	</div>
						      </div>
						    </li>
						    <li>
						      <div class="collapsible-header"><i class="material-icons right">keyboard_arrow_right</i><spring:message code="index/filters/price"/> (US$)</div>
						      <div class="collapsible-body">

						      	<p class="range-field">
						      		<spring:message code="index/filters/min"/> 
							      <input type="range" id="min-price" min="1" max="1000000" />
							    </p>
							    <p class="range-field">
							    	<spring:message code="index/filters/max"/>
							      <input type="range" id="max-price" min="0" max="1000000" />
							    </p>
							    <a class="waves-effect waves-light btn top-margin" id="" href=""><spring:message code="index/filters/apply-filters"/></a>

						      </div>
						    </li>
						    <li>
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
						    </li>
						    <li>
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
						      <div class="collapsible-header"><i class="material-icons right">keyboard_arrow_right</i><spring:message code="index/filters/general"/></div>
						      <div class="collapsible-body">
						      	
						      	<div class="link-list">
						      		<label>
								        <input type="checkbox" class="filled-in" />
								        <span><spring:message code="index/filters/general/garage"/></span>
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
							      <input type="range" id="min-s" min="1" max="10000" />
							    </p>
							    <p class="range-field">
							    	<spring:message code="index/filters/max"/>
							      <input type="range" id="max-s" min="0" max="10000" />
							    </p>

							    <a class="waves-effect waves-light btn" id="" href=""><spring:message code="index/filters/apply-filters"/></a>
						      </div>
						    </li>
						    <li>
						      <div class="collapsible-header"><i class="material-icons right">keyboard_arrow_right</i><spring:message code="index/filters/published-time"/></div>
						      <div class="collapsible-body">
						      	<div class="link-list">
						      		<div class="link-box">
						      			<a href=""><spring:message code="index/filters/published/today"/></a>
						      			<span>(289)</span>
						      		</div>
						      		<div class="link-box">
						      			<a href=""><spring:message code="index/filters/published/last-week"/></a>
						      			<span>(211)</span>
						      		</div>
						      		<div class="link-box">
						      			<a href=""><spring:message code="index/filters/published/last-two-weeks"/></a>
						      			<span>(88)</span>
						      		</div>
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
				      <ul class="tabs">
				        <li class="tab col"><a class="active" href="#test1"><spring:message code="index/sell"/></a></li>
				        <li class="tab col"><a href="#test2"><spring:message code="index/rent"/></a></li>
				        <li class="tab col"><a href="#test3"><spring:message code="index/temp-rent"/></a></li>
				      </ul>
				    </div>
				</div>

				<div class="order-box">
					<span class="order-by-text"><spring:message code="index/sort-by"/></span>
					<a class='dropdown-trigger' data-target='dropdown2'><i class="material-icons right">keyboard_arrow_down</i>Relevantes</a>
		  			  <ul id='dropdown2' class='dropdown-content'>
		  			  	<li><a href="#!"><spring:message code="index/sort/relevant"/></a></li>
					    <li><a href="#!"><spring:message code="index/sort/cheap"/></a></li>
					    <li><a href="#!"><spring:message code="index/sort/expensive"/></a></li>
					    <li><a href="#!"><spring:message code="index/sort/new"/></a></li>
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
						    	<li class="<c:if test="${loop.index} == 0">active</c:if>"><a href="">${loop.index + 1}</a></li>
						    </c:forEach>
						    <li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
					  	</ul>
					</div>

				</div>


			</div>

		</div>

		<%--<%@

		<div class="full-container">

			<div class="group-container animated slideInLeft">
				<ul class="collection with-header">
					
					<li class="collection-header"><h4><spring:message code="groups-header" /></h4></li>
					<c:if test="${recommendedGroup != null}">
						<li class="collection-item avatar recommendedGroup">
							<i class="material-icons circle">error_outline</i>
							<a class="group-info wr-250">
								<i class="material-icons">whatshot</i>
								<span class="title"><c:out value="${recommendedGroup.name}"/></span>
								<p><c:out value="${recommendedGroup.description}"/><br>
								</p>
							</a>
							<i class="material-icons join-recommended-group-button" data-groupname="${recommendedGroup.name}">group_add</i>
						</li>
					</c:if>
					<c:forEach items="${groups}" var="group" varStatus="loop">
						<li data-id="${group.id}" class="collection-item avatar <c:if test="${currentGroup.id == group.id}">active</c:if> ">
							<i class="material-icons circle">grade</i>
							<a class="group-info" href="${pageContext.servletContext.contextPath}/${group.id}">
								<span class="title"><c:out value="${group.name}"/></span>
								<p><c:out value="${group.description}"/><br>
								</p>
							</a>
							<c:if test="${group.id != 1}">
								<i class="material-icons exit-group-button" data-groupname="${group.name}">exit_to_app</i>
							</c:if>
						</li>
					</c:forEach>
  				</ul>
			</div>

			<div class="main-container">

				<!-- <div id="map"></div> -->

				<div class="groupSearch animated slideInUp">
					<div class="input-field">
						<i class="material-icons prefix">search</i>
						<input id="inputSearchBar" type="text" class="autocomplete" data-target="autocomplete_options" />
						<label for="inputSearchBar"><spring:message code="search" /></label>
					</div>
				</div>


                <div class="no-people-available <c:if test="${fn:length(usersList) > 0}">invisible</c:if>">
                	<span><spring:message code="no-people-available"/> </span>
                	<div class="searchingImgContainer">
                		<img src="<c:url value="/resources/images/searching.gif"></c:url>">
                	</div>
                	<a id="refreshBtn" class="waves-effect waves-light btn"><spring:message code="refresh" /></a>
                </div>

				<div class="carousel carousel-slider animated slideInUp <c:if test="${fn:length(usersList) == 0}">invisible</c:if>">
					
					<c:forEach items="${usersList}" var="user" varStatus="loop">

		                <div class="carousel-item " id="person-${loop.index}" data-uid="${user.uId}">
		                	<div class="personContainer">

		                		<div class="dislikeBtn" data-uid="${user.uId}">
				  					<a><img class="actionBtn" src="<c:url value="/resources/images/cross.svg"></c:url>"></a>
								</div>


								<div class="personCard" id="card-${loop.index}" data-uid="${user.uId}">
									<a href="${pageContext.servletContext.contextPath}/user/${user.uId}">
										<div class="card ">
											
											<div class="card-image">
												<div class="profileImageContainer">
													<img class="" src="<c:url value="${user.mainImage}"></c:url>">
												</div>
											</div>

											<div class="card-content wr">
												<span class="card-title"><c:out value="${user.name}"/></span>
												<c:out value="${user.info}"/>
											</div>
										</div>
									</a>


								</div>
								
								<div class="likeBtn" data-uid="${user.uId}">
						  			<a><img class="actionBtn" src="<c:url value="/resources/images/heart.svg"></c:url>"></a>
								</div>

							</div>
		                </div>
	            	</c:forEach>
				  
		  		</div>
		  	</div>


		  	<div class="matches-container animated slideInRight">
		  		<ul class="collection with-header">
		  			<li class="collection-header matchTitle-header">
		  				
		  				<div class="column">
			  				<h4><spring:message code="matches-header" /><b class="matchs-quantity">(${fn:length(matchesList)})</b></h4>
			  				<c:if test="${fn:length(notifications) > 0}">
								  <span class="new badge" data-badge-caption=" <spring:message code="new-matches"/>">${fn:length(notifications)}</span>
							</c:if>
						</div>

		  				<a id="discoverBtn" href="${pageContext.servletContext.contextPath}/discover" class="btn-floating btn-large waves-effect waves-light"><i class="material-icons">map</i></a>
		  			</li>
  
		  			<c:if test="${fn:length(matchesList) > 0}">
		  				<c:forEach items="${matchesList}" var="userM">
						    <li class="collection-item avatar matchElement" data-uid="${userM.uId}">
                                <div class="blackT wr-250">
								<img src="${userM.mainImage}" alt="" class="circle">
						      	<span class="title"><c:out value="${userM.name}"/></span>
							  </div>
							   <p class="wr-250"><c:out value="${userM.mail}"/></p>

								<c:choose>
						       		<c:when test='${fn:contains(notifications, userM.uId)}'>
						       			<a target="_blank" class="newNotification secondary-content"><i class="material-icons">fiber_new</i></a>
						         	</c:when>
							        <c:otherwise>
							        	<a target="_blank" href="mailto:${userM.mail}" class="secondary-content"><i class="material-icons">send</i></a>
							        </c:otherwise>
						    	</c:choose>

						    </li>
					    </c:forEach>
					</c:if>   
				    
  				</ul>
		  	</div>
			
		</div>

		



  		<div id="matchModal" class="modal">
		    <div class="modal-content">
		      <h4><spring:message code="congratulations-modal-title" /><b id="matchmodal-name"></b></h4>
		      <div class="caca">
		      	<div class="heart">&#x2665;</div>
		      </div>
		    </div>
		    <div class="modal-footer">
		      <a class="modal-close waves-effect waves-green btn-flat"><spring:message code="congratulations-modal-button" /></a>
		    </div>
	  	</div>

		<div id="newGroupModal" class="modal">
			<div class="modal-content">
				<h5><spring:message code="newgroup-modal-title" /><b id="newgroup-name"></b>?</h5>
				<h5><spring:message code="newgroup-modal-description-input-title" /></h5>
				<input id="new-group-description" type="text">
			</div>
			<div class="modal-footer">
				<a class="modal-close waves-effect btn-flat"><spring:message code="newgroup-modal-cancel-button"/></a>
				<a id="new-group-confirm-button" class="modal-close waves-effect waves-green btn-flat"><spring:message code="newgroup-modal-confirm-button"/></a>
			</div>
		</div>

		<div id="joinGroupModal" class="modal">
			<div class="modal-content">
				<h5><spring:message code="joingroup-modal-title" /><b id="joingroup-name">?</b></h5>
			</div>
			<div class="modal-footer">
				<a class="modal-close waves-effect btn-flat"><spring:message code="newgroup-modal-cancel-button"/></a>
				<a id="join-group-confirm-button" class="modal-close waves-effect waves-green btn-flat"><spring:message code="joingroup-modal-confirm-button"/></a>
			</div>
		</div>

		<div id="exitGroupModal" class="modal">
			<div class="modal-content">
				<h5><spring:message code="exitgroup-modal-title" /><b id="exitgroup-name"></b>?</h5>
			</div>
			<div class="modal-footer">
				<a class="modal-close waves-effect btn-flat"><spring:message code="newgroup-modal-cancel-button"/></a>
				<a id="exit-group-confirm-button" class="modal-close waves-effect waves-green btn-flat"><spring:message code="exitgroup-modal-confirm-button"/></a>
			</div>
		</div>

		<footer>
		</footer>

		<div class="loading-modal"></div>
		%>--%>

		
		<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/index.js"></c:url>"></script>
		<!-- <script async defer
				src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA8Y4tkFGWovxIdXWcR_GyZLVIHeMzW9cQ&callback=initMap">
		</script> -->
	</body>
</html>


