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
		  			<c:if test="${empty myUser}">
			  			<div class="nav-item">
			  				<a href=""><spring:message code="index/register" /></a>
			  			</div>
			  			<div class="nav-item">
			  				<a href=""><spring:message code="index/login"/></a>
			  			</div>
			  		</c:if>
		  			<div class="nav-item">
		  				<a href='<%= response.encodeURL(request.getContextPath() + "/property/register") %>'><spring:message code="index/publish"/></a>
		  			</div>
		  			
		  			<c:if test="${not empty myUser}">
			  			<div class="nav-item">
			  				<a href='<%= response.encodeURL(request.getContextPath() + "/myfavourites") %>'><spring:message code="index/myfavourites"/>
			  			</div>
			  		</c:if>
		  			
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
					    <li class="divider" tabindex="-1"></li>
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

			<div class="builder-column">
				<div class="shadow-box builder-box">
					<nav>
						<div class="nav-wrapper">
					    	<div  id="bread-wrapper-col" class="col">
					        	<a id="bread-publi" href="#!" class="active breadcrumb"><spring:message code="register_property/builder/publication"/></a>
					        	<a id="bread-info" href="#!" class="breadcrumb"><spring:message code="register_property/builder/information"/></a>
					        	<a id="bread-img" href="#!" class="breadcrumb"><spring:message code="register_property/builder/pictures"/></a>
					      	</div>
					    </div>
					</nav>


					<div class="builder-inner-box">
						<form:form id="mainForm" modelAttribute="newPropertyForm" action ='<%= response.encodeURL(request.getContextPath() + "/property/register") %>' method="post" enctype="multipart/form-data">
							
							<div class="publication-div form-group">
								
								<div class="padding">
							    	<div class="input-field">
							        	<form:input id="p-title" path="adMessage" type="text" class="validate" maxlength="80" data-length="80"/>
							        	<label for="p-title"><spring:message code="register_property/builder/title"/></label>
							        	<span class="helper-text" data-error="<spring:message code="register_property/error/general"/>" data-success="OK"></span>
							        </div>
						    	</div>

						    	<div class="padding">
									<div class="">
										<div class="input-field">
											<form:textarea maxlength="300" id="p-desc" path="adDescription" class="materialize-textarea validate" data-length="300"/>
											<label for="p-desc"><spring:message code="register_property/builder/description"/></label>
											<span class="helper-text" data-error="<spring:message code="register_property/error/general"/>" data-success="OK"></span>
										</div>
									</div>
								</div>

								<div class="row-container">

									 <div class="input-field">
										<form:select id="operationSelector" path="operationType">
											<form:option value="sell"><spring:message code="index/sell"/></form:option>
											<form:option value="rent"><spring:message code="index/rent"/></form:option>
											<form:option value="temporal_rent"><spring:message code="index/temp-rent"/></form:option>
										
										</form:select>
								    	<label><spring:message code="register_property/builder/op-type"/></label>
									</div>

									 <div class="input-field">
										<form:select id="delSelector" path="inmediateDelivery">
											<form:option value="true"><spring:message code="index/card/immediate"/></form:option>
											<form:option value="false"><spring:message code="index/card/no-immediate"/></form:option>
											
										</form:select>
								    	<label><spring:message code="register_property/builder/del-type"/></label>
									</div>

							    	<div class="input-field">
							        	<form:input id="p-price" path="price" type="number" onKeyPress="if(this.value.length==10) return false;" class="validate"/>
							        	<label for="p-price"><spring:message code="register_property/builder/price"/></label>
							        	<span class="helper-text" data-error="<spring:message code="register_property/error/general"/>" data-success="OK"></span>
							        </div>
							    </div>

							    <div class="row center">
							    	<button type="button" class="waves-effect waves-light btn top-margin" id="nextZero"><spring:message code="register_property/next-button"/></button>
							   	</div>

							</div>

							<div class="information-div form-group">

								<div class="column-container">

									<div class="row-container-basic">
								    	<div class="input-field f-2 mr-20">
								    		<spring:message code="register_property/builder/autocomplete-placeholder" var="nPlace" />
								        	<form:input id="p-street" path="street" type="text" class="validate" placeholder="${nPlace}" onFocus="geolocate()"/>
								        	<label for="p-street"><spring:message code="register_property/builder/street"/></label>
								        	<span class="helper-text" data-error="<spring:message code="register_property/error/general"/>" data-success="OK"></span>
								        </div>

								       
								        <div class="input-field f-1">
								        	<form:input id="p-floor" path="floor" type="text" class="validate"/>
								        	<label for="p-floor"><spring:message code="register_property/builder/floor"/></label>
								        	<span class="helper-text" data-error="<spring:message code="register_property/error/general"/>" data-success="OK"></span>
								        </div>
								    </div>

								   	<div class="row-container">
								        <div class="input-field">
								        	<form:input id="p-ap" path="apartment" type="text" class="validate"/>
								        	<label for="p-ap"><spring:message code="register_property/builder/apartment"/></label>
								        	<span class="helper-text" data-error="<spring:message code="register_property/error/general"/>" data-success="OK"></span>
								        </div>

								        <div class="input-field">
								        	<form:input id="p-neigh" path="neighborhood" placeholder="-" type="text" class="" readonly="true"/>
								        	<label for="p-neigh"><spring:message code="register_property/builder/neighborhood"/></label>
								        	<span class="helper-text" data-error="<spring:message code="register_property/error/general"/>" data-success="OK"></span>
								        </div>

								        <div class="input-field">
											<form:select name="typeSelector" id="typeSelector" path="type">
												<form:option value="house"><spring:message code="index/filters/type/house"/></form:option>
												<form:option value="apartment"><spring:message code="index/filters/type/apartment"/></form:option>
												<form:option value="ph"><spring:message code="index/filters/type/ph"/></form:option>
												<form:option value="office"><spring:message code="index/filters/type/office"/></form:option>
												<form:option value="land"><spring:message code="index/filters/type/land"/></form:option>
											</form:select>
									    	<label><spring:message code="register_property/builder/type"/></label>
									    	<span class="helper-text" data-error="<spring:message code="register_property/error/general"/>" data-success="OK"></span>
										</div>
									</div>

									<div class="row-container">
										
										<div class="input-field">
								        	<form:input id="p-cArea" path="coveredArea" type="number" class="validate"/>
								        	<label for="p-cArea"><spring:message code="register_property/builder/covered-area"/></label>
								        	<span class="helper-text" data-error="<spring:message code="register_property/error/general"/>" data-success="OK"></span>
								        </div>

								        <div class="input-field">
								        	<form:input id="p-tArea" path="totalArea" type="number" class="validate"/>
								        	<label for="p-tArea"><spring:message code="register_property/builder/total-area"/></label>
								        	<span class="helper-text" data-error="<spring:message code="register_property/error/general"/>" data-success="OK"></span>
								        </div>

								         <div class="input-field">
								        	<form:input id="p-tPrice" path="taxPrice" type="number" class="validate"/>
								        	<label for="p-tPrice"><spring:message code="register_property/builder/tax-price"/></label>
								        	<span class="helper-text" data-error="<spring:message code="register_property/error/general"/>" data-success="OK"></span>
								        </div>
								    </div>

								    <div class="row-container">
								        
								        <div class="input-field">
								        	<form:input id="p-rooms" path="rooms" type="number" class="validate"/>
								        	<label for="p-rooms"><spring:message code="register_property/builder/rooms"/></label>
								        	<span class="helper-text" data-error="<spring:message code="register_property/error/general"/>" data-success="OK"></span>
								        </div>

								        <div class="input-field">
								        	<form:input id="p-baths" path="baths" type="number" class="validate"/>
								        	<label for="p-baths"><spring:message code="register_property/builder/baths"/></label>
								        	<span class="helper-text" data-error="<spring:message code="register_property/error/general"/>" data-success="OK"></span>
								        </div>
								        
								        <div class="input-field" id="garageSelectorDiv">
											<form:select name="typeSelector" id="garageSelector" path="garage">
												<form:option value="true"><spring:message code="index/filters/general/garage-one-or-more"/></form:option>
												<form:option value="false"><spring:message code="index/filters/general/no-garage"/></form:option>
											</form:select>
									    	<label><spring:message code="register_property/builder/garages"/></label>
										</div>
									</div>
								        
							    	<div class="row center">
								    	<button type="button" class="waves-effect waves-light btn top-margin" id="nextOne"><spring:message code="register_property/next-button"/></button>
								   	</div>
								   	
								</div>
						    	
							</div>


							<div class="pictures-div form-group">

								<div class="file-field input-field">
							    	<div class="btn">
							       		<span><spring:message code="register_property/upload-button"/></span>
							        	<form:input id="imagesFiles" type="file" path="images" multiple="multiple"/>
							      	</div>
							      	<div class="file-path-wrapper">
							        	<input id="file-upload-line" class="file-path validate" type="text" placeholder="<spring:message code="register_property/upload-message"/>"/>
							        	<span class="helper-text" data-error="<spring:message code="register_property/error/general"/>" data-success="OK"></span>
							      	</div>
							    </div>

							    <div class="row">
							    	<ul id="imagesList" class="collection">
								    </ul>
							    </div>

							    <div class="row center">
							    	<button type="button" class="waves-effect waves-light btn top-margin" id="submitBtn"><spring:message code="register_property/submit-button"/></button>
							   	</div>

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
									<div id="imageCarousel" class="carousel carousel-slider">

										<div id="carouselArrows" class="carousel-fixed-item center middle-indicator with-indicators invisible">
									    	<div class="left">
									     		<a data-id="${loop.index}" class="movePrevCarousel middle-indicator-text content-indicator"><i class="material-icons left  middle-indicator-text">chevron_left</i></a>
									    	</div>

									    	<div class="right">
										    	<a data-id="${loop.index}" class="moveNextCarousel middle-indicator-text content-indicator"><i class="material-icons right middle-indicator-text">chevron_right</i></a>
										    </div>

										</div>

										<a id="noPicture" class="carousel-item" href="">
											<div class="image-cont">
												<img src="<c:url value="/resources/images/no-image.png"/>">
											</div>
										</a>
									</div>
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
										<a id="title-preview" href="" class="card-text-wrap title"><spring:message code="register_property/preview/title"/></a>
										<a href="" class="card-text-wrap subtitle"><span id="street-preview"><spring:message code="register_property/preview/address"/></span></span> - <span id="neighborhood-preview"></span></a>
									</div>
									<div class="right">
										<c:choose>
											<c:when test="${not empty myUser.imageSrc}">
												<img src="<c:out value="${myUser.imageSrc}"/>">
											</c:when>
											<c:otherwise>
												<img src="<c:url value="/resources/images/anon-user.png"/>">
											</c:otherwise>
										</c:choose>
										
									</div>
								</div>

								<div class="description">
									<div class="area-preview bold">
										<span id="cArea-preview"></span> <span id="meters-post" class="invisible"><spring:message code="index/card/meters"/></span></br>
									</div>
									<div class="pDesc">
										<span id="description-preview" style="width:450px; overflow : hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 4;-webkit-box-orient: vertical;">
											<spring:message code="register_property/preview/description"/>
										</span> 
									</div>
								</div>

								<div class="footer">

									<div class="bold extra-info">
										<span id="delType-preview"><spring:message code="index/card/immediate"/></span>
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
	    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA8Y4tkFGWovxIdXWcR_GyZLVIHeMzW9cQ&libraries=places&callback=initGoogleMapsAutocomplete"
        async defer></script>
	    <script type="text/javascript" src="<c:url value="/resources/js/inputValidator.js"></c:url>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/register_property.js"></c:url>"></script>
		
	</body>
</html>
