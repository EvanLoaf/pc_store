<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <%-- APPLICATION CONTEXT PATH --%>
    <c:set var="app" value="${pageContext.request.contextPath}"/>

    <%-- PUBLIC ENTRY POINT PREFIX --%>
    <c:set var="entry_point_prefix" value="/web"/>

    <%-- INITIAL APP PATH --%>
    <c:set var="app_entry_path" value="${app}${entry_point_prefix}"/>

    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Create business card</title>
</head>
<body>
<div class="container wide">
    <div class="row">
        <div class="col-md-2">
            <jsp:include page="/WEB-INF/pages/util/ads.jsp"/>
        </div>
        <div class="col-md-8">
            <div class="row">
                <h1>Create business card</h1>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <form:form action="${app_entry_path}/cards" modelAttribute="businessCard" method="post">
                        <form:errors path="title" cssClass="alert-danger" element="div"/>
                        <div class="form-group">
                            <form:label path="title">Title</form:label>
                            <form:input type="text" path="title" class="form-control" id="title"/>
                        </div>
                        <form:errors path="fullName" cssClass="alert-danger" element="div"/>
                        <div class="form-group">
                            <form:label path="fullName">Full name</form:label>
                            <form:input type="text" path="fullName" class="form-control"
                                        id="fullName"/>
                        </div>
                        <form:errors path="workingTelephone" cssClass="alert-danger" element="div"/>
                        <div class="form-group">
                            <form:label path="workingTelephone">Working telephone</form:label>
                            <form:input type="text" path="workingTelephone" class="form-control" id="workingTelephone"/>
                        </div>
                        <button type="submit" class="btn btn-primary">Create</button>
                    </form:form>
                </div>
            </div>
        </div>
        <div class="col-md-2">
            <security:authorize access="isAuthenticated()">
                Hello <security:authentication property="principal.name"/>
            </security:authorize>
            <security:authorize access="hasAuthority('view_user_self')">
                <div class="row">
                    <a href="${app_entry_path}/users/profile"
                       class="btn btn-outline-success" aria-pressed="true" role="button">PROFILE</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('view_items')">
                <div class="row">
                    <a href="${app_entry_path}/items"
                       class="btn btn-outline-success" aria-pressed="true" role="button">ITEMS</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('manage_business_card')">
                <div class="row">
                    <a href="${app_entry_path}/cards"
                       class="btn btn-outline-success" aria-pressed="true" role="button">BUSINESS CARDS</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('view_news')">
                <div class="row">
                    <a href="${app_entry_path}/news"
                       class="btn btn-outline-success" aria-pressed="true" role="button">NEWS</a>
                </div>
            </security:authorize>
            <jsp:include page="/WEB-INF/pages/util/ads.jsp"/>
            <div class="row">
                <a href="${app_entry_path}/logout"
                   class="btn btn-outline-success" aria-pressed="true" role="button">LOG OUT</a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/pages/util/js.jsp"/>
</body>
</html>

