<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%--<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>

	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<%--<%@<title><spring:message code="page-title" /></title>%>--%>
		<title>Choza</title>
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
	      			<form>
				        <div class="input-field">
				          <input id="search" type="search" placeholder="Buscar" required>
				          <label class="label-icon" for="search"><i class="material-icons icon-black">search</i></label>
				          <i class="material-icons">close</i>
				        </div>
			      </form>
			    </div>
			</div>

		  	<div class="buttons-box">
		  		<div class="labels">
		  			<a href="">REGISTRARME</a>
		  			<a href="">INGRESAR</a>
		  			<a href="">PUBLICAR UN INMUEBLE</a>
		  		</div>

		  		<div class="extras">
		  			  <a class='dropdown-trigger' data-target='dropdown1'><i class="medium material-icons">menu</i></a>
		  			  <ul id='dropdown1' class='dropdown-content'>
					    <li><a href="#!">A</a></li>
					    <li><a href="#!">B</a></li>
					    <li class="divider" tabindex="-1"></li>
					    <li><a href="#!"><i class="material-icons">view_module</i>English</a></li>
					    <li><a href="#!"><i class="material-icons">cloud</i>Spanish</a></li>
					  </ul>
		  		</div>
		  	</div>
		
		</nav>

		<div class="main-row-container">

			<div class="filters-column">
				<div class="shadow-box filters-box">
				</div>

				<div class="shadow-box filters-box test-height">
				</div>
			</div>

			<div class="properties-column">

				<span class="properties-count"><span id="pCount" class="bold">1.260</span> propiedades en venta en Argentina</span>

				<div class="tabs-container">
				    <div class="col">
				      <ul class="tabs">
				        <li class="tab col"><a class="active" href="#test1">Venta</a></li>
				        <li class="tab col"><a href="#test2">Alquiler</a></li>
				        <li class="tab col"><a href="#test3">Alquiler temporal</a></li>
				      </ul>
				    </div>
				</div>

				<div class="order-box">
					<span class="order-by-text">Ordenar por:</span>
					<a class='dropdown-trigger' data-target='dropdown2'><i class="material-icons right">keyboard_arrow_down</i>Relevantes</a>
		  			  <ul id='dropdown2' class='dropdown-content'>
		  			  	<li><a href="#!">Relevantes</a></li>
					    <li><a href="#!">Baratos</a></li>
					    <li><a href="#!">Caros</a></li>
					    <li><a href="#!">Nuevos</a></li>
					  </ul>
				</div>

				<div class="properties-list">

					<div class="shadow-box property-card">
						<div class="left">
							<div class="image-container">
								<div class="carousel carousel-slider">
									<a class="carousel-item" href="#one!"><img src="https://imgar.zonapropcdn.com/avisos/1/00/43/72/39/68/360x266/1676384293.jpg"></a>
									<a class="carousel-item" href="#two!"><img src="https://imgar.zonapropcdn.com/avisos/1/00/43/72/39/68/360x266/1676384289.jpg"><</a>
									<a class="carousel-item" href="#three!"><img src="https://imgar.zonapropcdn.com/avisos/1/00/43/72/39/68/360x266/1676384284.jpg"><</a>
								</div>
							</div>
							<div class="price-text">
								<span>U$S 38.000</span>
							</div>
						</div>
						<div class="right">
							<div class="header">
								<div class="left">
									<a href="" class="card-text-wrap title">Edificio corporativo 5 plantas 300 m2 con muchas cosas</a>
									<a href="" class="card-text-wrap subtitle">Peron, Juan Domingo 518 - Microcentro</a>
								</div>
								<div class="right">
									<img src="https://imgar.zonapropcdn.com/empresas/1/00/17/02/92/12/130x70/logo_dunod_2.jpg">
								</div>
							</div>

							<div class="description">
								<span class="bold">300 m2 cubiertos</span> </br>
								<div class="pDesc">
									<span class="">Edificio desarrollado en Subsuelo, Planta Baja y 5 pisos.
									2 Ascensores principales - 1 uno de servicio que llega hasta el 4to piso - Grupo electrógeno para el 100% del edificio - Local en Planta Baja - Bóveda en subsuelo. 

									Características: 

									Plantas Libres - Cielorraso desmontable con luminarias - Aire Acondicionado Central y refuerzos con Splits - Detectores de Humo - 2 baños generales ambos sexos - 2 baños privados. 
									Plantas Libres - Cielorraso desmontable con luminarias - Aire Acondicionado Central y refuerzos con Splits - Detectores de Humo - 2 baños generales ambos sexos - 2 baños privados.
									</span> 
								</div>
							</div>

							<div class="footer">

								<div class="bold extra-info">
									<span>Entrega inmediata</span>
									<span>&#183;</span>
									<span>Publicado hace 45 dias</span>
								</div>

								<div class="action">
									<a href="">CONTACTAR</a>
								</div>
							</div>

						</div>
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
                	<span><spring:message code="no-people-available"></spring:message> </span>
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
								  <span class="new badge" data-badge-caption=" <spring:message code="new-matches"></spring:message>">${fn:length(notifications)}</span>
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


