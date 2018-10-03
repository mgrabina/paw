<%@taglib prefix ="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix ="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <!--
        Author: W3layouts
        Author URL: http://w3layouts.com
        License: Creative Commons Attribution 3.0 Unported
        License URL: http://creativecommons.org/licenses/by/3.0/
        -->
<!DOCTYPE HTML>
<html>
    <head>
        <%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <link href="<c:url value="/resources/css/bootstrap.min.css"></c:url>" rel='stylesheet' type='text/css' />
        <!-- Custom Theme files -->
        <link href="<c:url value="/resources/css/style.css"></c:url>" rel='stylesheet' type='text/css' />
        <link href="<c:url value="/resources/css/font-awesome.css"></c:url>" rel="stylesheet">
        <script src="<c:url value="/resources/js/jquery.min.js"></c:url>" > </script>
        <script src="<c:url value="/resources/js/bootstrap.min.js"></c:url>" > </script>
            <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/materialize.css"></c:url>">

    </head>


    <body>
        <form:form modelAttribute="loginForm" action='<%= response.encodeURL(request.getContextPath() + "/user/login") %>' method="post" enctype="application/x-www-form-urlencoded">
            <div class="login">
                <h1 class="card-title"><a><spring:message code="app_name"/></a></h1>
                <div class="login-bottom">
                    <form>
                        <div class="col-md-6">
                            <div class="input-field">
                                <%--<spring:message code='login/email' var="auxEmail"/>--%>
                                <form:input type="text" placeholder="${auxEmail}" required="" id="mail" path="mail" name="mail"/>
                                <%--<i class="fa fa-envelope"></i>--%>
                                <label for="mail"><spring:message code="login/email"/></label>
                            </div>
                            <div class="input-field">
                                <%--<spring:message code="login/password" var="auxPassword"/>--%>
                                <form:input type="password" placeholder='${auxPassword}' required="" id="password" path="password" name="password"/>
                                <%--<i class="fa fa-lock"></i>--%>
                                <label for="password"><spring:message code="login/password"/></label>
                            </div>
                                <%--<div class="login-mail">--%>
                                <%--<spring:message code='login/email' var="auxEmail"/>--%>
                                <%--<form:input type="text" placeholder="${auxEmail}" required="" id="mail" path="mail" name="mail"/>--%>
                                <%--<i class="fa fa-envelope"></i>--%>
                            <%--</div>--%>
                            <%--<div class="login-mail">--%>
                                <%--<spring:message code="login/password" var="auxPassword"/>--%>
                                <%--<form:input type="password" placeholder='${auxPassword}' required="" id="password" path="password" name="password"/>--%>
                                <%--<i class="fa fa-lock"></i>--%>
                            <%--</div>--%>
                        </div>
                        <div class="col-md-6 login-do">
                            <br>
                            <label class="hvr-shutter-in-horizontal login-sub">
                                <input type="submit" class="btn btn-outline-secondary" value='<spring:message code="login/login"/>'/>
                            </label>
                            <p><spring:message code="login/dont-have-user"/></p>
                            <a href='<%= response.encodeURL(request.getContextPath() + "/user/register") %>' class="hvr-shutter-in-horizontal"><spring:message code="login/singup"/></a>
                        </div>
                        <div class="clearfix"> </div>
                    </form>
                </div>
            </div>
        </form:form>
        <script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
        <script type="text/javascript" src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA8Y4tkFGWovxIdXWcR_GyZLVIHeMzW9cQ&libraries=places&callback=initGoogleMapsAutocomplete"
                async defer></script>
        <script type="text/javascript" src="<c:url value="/resources/js/inputValidator.js"></c:url>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/register_property.js"></c:url>"></script>

    </body>
</html>
