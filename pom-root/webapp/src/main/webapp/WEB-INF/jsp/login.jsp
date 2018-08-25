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
                <h1><a href="index.jsp">Minimal </a></h1>
                <div class="login-bottom">
                    <h2>Login</h2>
                    <form>
                        <div class="col-md-6">
                            <div class="login-mail">
                                <form:input type="text" placeholder="Email" required="" id="mail" path="mail" name="mail"/>
                                <i class="fa fa-envelope"></i>
                            </div>
                            <div class="login-mail">
                                <form:input type="password" placeholder="Password" required="" id="password" path="password" name="password"/>
                                <i class="fa fa-lock"></i>
                            </div>
                            <a class="news-letter " href="#">
                                <label class="checkbox1"><input type="checkbox" name="checkbox" ><i> </i>Forget Password</label>
                            </a>
                        </div>
                        <div class="col-md-6 login-do">
                            <label class="hvr-shutter-in-horizontal login-sub">
                                <input type="submit" class="btn btn-outline-secondary" value="login"/>
                            </label>
                            <p>Do not have an account?</p>
                            <a href="signup.jsp" class="hvr-shutter-in-horizontal">Signup</a>
                        </div>
                        <div class="clearfix"> </div>
                    </form>
                </div>
            </div>
        </form:form>
    </body>
</html>
