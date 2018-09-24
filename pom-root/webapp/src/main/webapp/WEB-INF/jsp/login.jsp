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
        <link href="<c:url value="/resources/css/bootstrap.min.css"></c:url>" rel='stylesheet' type='text/css' />
        <!-- Custom Theme files -->
        <link href="<c:url value="/resources/css/style.css"></c:url>" rel='stylesheet' type='text/css' />
        <link href="<c:url value="/resources/css/font-awesome.css"></c:url>" rel="stylesheet">
        <script src="<c:url value="/resources/js/jquery.min.js"></c:url>" > </script>
        <script src="<c:url value="/resources/js/bootstrap.min.js"></c:url>" > </script>
    </head>


    <body>
        <form:form modelAttribute="loginForm" action='<%= response.encodeURL(request.getContextPath() + "/user/login") %>' method="post" enctype="application/x-www-form-urlencoded">
            <div class="login">
                <h1><a href="index.jsp"><spring:message code="app_name"/></a></h1>
                <div class="login-bottom">
                    <h2><spring:message code="login/login"/></h2>
                    <form>
                        <div class="col-md-6">
                            <div class="login-mail">
                                <spring:message code='login/email' var="auxEmail"/>
                                <form:input type="text" placeholder="${auxEmail}" required="" id="mail" path="mail" name="mail"/>
                                <i class="fa fa-envelope"></i>
                            </div>
                            <div class="login-mail">
                                <spring:message code="login/password" var="auxPassword"/>
                                <form:input type="password" placeholder='auxPassword' required="" id="password" path="password" name="password"/>
                                <i class="fa fa-lock"></i>
                            </div>
                        </div>
                        <div class="col-md-6 login-do">
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
    </body>
</html>
