<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>

<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/css/materialize.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/errorPage.css"></c:url>">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lato">
</head>

<body>
<div class="page-wrap">
    <div class="row">
        <c:if test="${statusCode >= 0}">
            <h1>${statusCode}</h1>
            <c:choose>
                <c:when test = "${statusCode == 400}">
                    <h2><spring:message code="error-page/400"/></h2>
                </c:when>

                <c:when test = "${statusCode == 401}">
                    <h2><spring:message code="error-page/401"/></h2>
                </c:when>

                <c:when test = "${statusCode == 404}">
                    <h2><spring:message code="error-page/404"/></h2>
                </c:when>

                <c:when test = "${statusCode == 500}">
                    <h2><spring:message code="error-page/500"/></h2>
                </c:when>

                <c:otherwise>
                    <h2><spring:message code="error-page/unknown"/></h2>
                </c:otherwise>
            </c:choose>
        </c:if>

        <p><a href="${pageContext.servletContext.contextPath}/"><spring:message code="error/return-home"/></a></p>
    </div>
</div>
</body>

</html>