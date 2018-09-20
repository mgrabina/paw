<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix ="form" uri="http://www.springframework.org/tags/form"%>
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
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/register_property.css"></c:url>">
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
		  		<div class="labels invisible">
		  			<a href=""><spring:message code="index/register" /></a>
		  			<a href=""><spring:message code="index/login"/></a>
		  			<a href=""><spring:message code="index/publish"/></a>
		  		</div>

		  		<div class="extras">
		  			  <a class='dropdown-trigger' data-target='dropdown1'><i class="medium material-icons">menu</i></a>
		  			  <ul id='dropdown1' class='dropdown-content'>
					    <li><a href="#!">A</a></li>
					    <li><a href="#!">B</a></li>
					    <li class="divider" tabindex="-1"></li>
					    <li><a href="#!"><i class="material-icons">language</i><spring:message code="navbar/languages/english"/></a></li>
					    <li><a href="#!"><i class="material-icons">language</i><spring:message code="navbar/languages/spanish"/></a></li>
					  </ul>
		  		</div>
		  	</div>
		
		</nav>

		<div class="main-row-container">

			<div class="builder-column">
				<div class="shadow-box builder-box">
					<nav>
						<div class="nav-wrapper">
					    	<div class="col">
					        	<a href="#!" class="active breadcrumb">Publicación</a>
					        	<a href="#!" class="breadcrumb">Información de la propiedad</a>
					        	<a href="#!" class="breadcrumb">Imágenes</a>
					      	</div>
					    </div>
					</nav>


					<div id="" class="builder-inner-box">
						<form:form modelAttribute="newPropertyForm" action ='<%= response.encodeURL(request.getContextPath() + "/property/register") %>' method="post" >
							
							<div class="publication-div">
								
								<div class="row padding">
							    	<div class="input-field">
							        	<form:input id="p-title" path="adMessage" type="text" class="validate"/>
							        	<label for="p-title">Título</label>
							        </div>
						    	</div>

						    	<div class="row padding">
									<form class="">
										<div class="row">
											<div class="input-field">
												<form:textarea maxlength="300" id="p-desc" path="adDescription" class="materialize-textarea"/>
												<label for="textarea1">Descripción</label>
											</div>
										</div>
									</form>
								</div>

								<div class="row">

									 <div class="input-field col">
										<form:select name="typeSelector" id="operationSelector" path="operationType">
											<form:option value="0">Venta</form:option>
											<form:option value="1">Alquiler</form:option>
											<form:option value="2">Alquiler temporal</form:option>
											
										</form:select>
								    	<label>Tipo de operación</label>
									</div>

									 <div class="input-field col">
										<form:select name="typeSelector" id="delSelector" path="operationType">
											<form:option value="true">Entrega inmediata</form:option>
											<form:option value="false">En pozo</form:option>
											
										</form:select>
								    	<label>Entrega</label>
									</div>

							    	<div class="input-field col">
							        	<form:input id="p-price" path="price" type="number" class="validate"/>
							        	<label for="p-price">Precio (US$)</label>
							        </div>
							    </div>

							</div>

							<div class="information-div">

								<div class="row">
							    	<div class="input-field col">
							        	<form:input id="p-street" path="street" type="text" class="validate"/>
							        	<label for="p-street">Street</label>
							        </div>

							        <div class="input-field col">
							        	<form:input id="p-number" path="number" type="number" class="validate"/>
							        	<label for="p-number">Number</label>
							        </div>

							        <div class="input-field col">
							        	<form:input id="p-floor" path="floor" type="number" class="validate"/>
							        	<label for="p-floor">Floor</label>
							        </div>

							        <div class="input-field col">
							        	<form:input id="p-ap" path="apartment" type="text" class="validate"/>
							        	<label for="p-ap">Apartment</label>
							        </div>

							        <div class="input-field col">
							        	<form:input id="p-neigh" path="neighborhood" type="text" class="validate"/>
							        	<label for="p-neigh">Neighborhood</label>
							        </div>

							        <div class="input-field col">
										<form:select name="typeSelector" id="typeSelector" path="type">
											<form:option value="house">House</form:option>
											<form:option value="apartment">Apartment</form:option>
											<form:option value="duplex">Duplex</form:option>
											<form:option value="office">Office</form:option>
											<form:option value="land">Land</form:option>
										</form:select>
								    	<label>Tipo de propiedad</label>
									</div>

									<div class="input-field col">
							        	<form:input id="p-cArea" path="coveredArea" type="text" class="validate"/>
							        	<label for="p-cArea">Covered area</label>
							        </div>

							        <div class="input-field col">
							        	<form:input id="p-tArea" path="totalArea" type="text" class="validate"/>
							        	<label for="p-tArea">Total area</label>
							        </div>

							         <div class="input-field col">
							        	<form:input id="p-tPrice" path="taxPrice" type="number" class="validate"/>
							        	<label for="p-tPrice">Tax price</label>
							        </div>

							        <div class="input-field col">
							        	<form:input id="p-rooms" path="rooms" type="number" class="validate"/>
							        	<label for="p-rooms">Rooms</label>
							        </div>

							        <div class="input-field col">
							        	<form:input id="p-baths" path="baths" type="number" class="validate"/>
							        	<label for="p-baths">Baths</label>
							        </div>

							        
							        <div class="input-field col">
										<form:select name="typeSelector" id="garageSelector" path="garage">
											<form:option value="true">1 o más</form:option>
											<form:option value="false">Ninguno</form:option>
										</form:select>
								    	<label>Cocheras</label>
									</div>
							        
						    	</div>
						    	
							</div>

							



							<div class="pictures-div">
							</div>

						</form:form>
					</div>

				</div>
			</div>

			<div class="preview-column">
					<h5>PREVIEW</h5>
					<div class="shadow-box property-card">
							<div class="left">
								<div class="image-container">
									<c:choose>
										<c:when test="${fn:length(property.images) == 0}">
											<img src="<c:url value="/resources/images/no-image.png"/>">
										</c:when>
										<c:otherwise>
											<div class="carousel carousel-slider">
												<c:forEach items="${property.images}" var="imageSrc" varStatus="loop">
													<a class="carousel-item" href=""><img src="<c:out value="${imageSrc}"/>"></a>
												</c:forEach>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="price-text">
									<span id="price-preview">
         								US$ 0
     								</span>
								</div>
							</div>
							<div class="right">
								<div class="header">
									<div class="left">
										<a id="title-preview" href="" class="card-text-wrap title">Título</a>
										<a href="" class="card-text-wrap subtitle"><span id="street-preview"></span><span id="number-preview"></span><span id="neighborhood-preview"></span></a>
									</div>
									<div class="right">
										<img src="<c:out value="${property.publisherUser.imageSrc}"/>">
									</div>
								</div>

								<div class="description">
									<span class="bold"><c:out value="${property.coveredArea}"/> <spring:message code="index/card/meters"/></span> </br>
									<div class="pDesc">
										<span id="description-preview" style="overflow : hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 4;-webkit-box-orient: vertical;">
											Descripción
										</span> 
									</div>
								</div>

								<div class="footer">

									<div class="bold extra-info">
										<span id="delType-preview">Delivery type</span>
										<span>&#183;</span>
										<span><spring:message code="index/card/published-time-pre"/> 0 <spring:message code="index/card/published-time-post"/></span>
									</div>

									<div class="action">
										<a href=""><spring:message code="index/card/contact"/></a>
									</div>
								</div>

							</div>
						</div>
			</div>


		</div>

		<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/register_property.js"></c:url>"></script>
		
	</body>
</html>
