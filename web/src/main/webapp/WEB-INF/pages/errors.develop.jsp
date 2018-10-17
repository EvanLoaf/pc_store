<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <%-- APPLICATION CONTEXT PATH --%>
    <c:set var="app" value="${pageContext.request.contextPath}"/>

    <%-- PUBLIC ENTRY POINT PREFIX --%>
    <c:set var="entry_point_prefix" value="/web"/>

    <%-- INITIAL APP PATH --%>
    <c:set var="app_entry_path" value="${app}${entry_point_prefix}"/>

    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <meta http-equiv="Content-Type">
    <title>Error Page</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-2">
        </div>
        <div class="col-md-8">
            <h2>Application Error, please contact support.</h2>
            <h3>Debug information:</h3>
            <p>Requested URL = ${url}</p>
            <c:if test="${not empty tip}">
                <p>${tip}</p>
            </c:if>
            <br>
            <p>Exception = ${exception.message}</p>
            <br>
            <p>Exception Stack Trace :</p>
            <c:forEach items="${exception.stackTrace}" var="est">
                ${est}
            </c:forEach>
        </div>
        <div class="col-md-2">
            <security:authorize access="hasAuthority('view_items')">
                <div class="row">
                    <a href="${app_entry_path}/items"
                       class="btn btn-outline-success" aria-pressed="true" role="button">ITEMS</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('view_users_all')">
                <div class="row">
                    <a href="${app_entry_path}/users"
                       class="btn btn-outline-success" aria-pressed="true" role="button">USERS</a>
                </div>
            </security:authorize>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/pages/util/js.jsp"/>
</body>
</html>
