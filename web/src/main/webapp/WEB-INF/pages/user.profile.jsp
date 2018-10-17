<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
    <title>Profile</title>
</head>
<body>
<div class="container wide">
    <div class="row">
        <div class="col-xl-2">
            <jsp:include page="/WEB-INF/pages/util/ads.jsp"/>
        </div>
        <div class="col-xl-8">
            <div class="row">

                <div class="row">
                    <c:if test="${param.feedback == 'true'}">
                        <div class="alert alert-success" role="alert">
                            <p>Feedback submitted successfully</p>
                        </div>
                    </c:if>

                    <c:if test="${param.update == 'true'}">
                        <div class="alert alert-success" role="alert">
                            <p> Profile updated successfully</p>
                        </div>
                    </c:if>
                </div>

                <div class="row">
                    <h1>You may change your profile info</h1>
                </div>

                <div class="col-lg-12">
                    <form:form action="${app_entry_path}/users/${user.id}" modelAttribute="user" method="post">
                        <form:errors path="password" cssClass="alert-danger" element="div"/>
                        <div class="form-group">
                            <form:label path="password">Password</form:label>
                            <form:input type="password" path="password" class="form-control" id="password"/>
                        </div>
                        <form:errors path="firstName" cssClass="alert-danger" element="div"/>
                        <div class="form-group">
                            <form:label path="firstName">First name</form:label>
                            <form:input type="text" path="firstName" class="form-control"
                                        id="firstName"/>
                        </div>
                        <form:errors path="lastName" cssClass="alert-danger" element="div"/>
                        <div class="form-group">
                            <form:label path="lastName">Last name</form:label>
                            <form:input type="text" path="lastName" class="form-control" id="lastName"/>
                        </div>
                        <form:errors path="address" cssClass="alert-danger" element="div"/>
                        <div class="form-group">
                            <form:label path="address">Address</form:label>
                            <form:input type="text" path="address" class="form-control" id="address"/>
                        </div>
                        <form:errors path="phoneNumber" cssClass="alert-danger" element="div"/>
                        <div class="form-group">
                            <form:label path="phoneNumber">Phone number</form:label>
                            <form:input type="text" path="phoneNumber" class="form-control"
                                        id="phoneNumber"/>
                        </div>
                        <button type="submit" class="btn btn-primary">Update Info</button>
                    </form:form>
                </div>
            </div>
        </div>
        <div class="col-xl-2">
            <security:authorize access="isAuthenticated()">
                Hello <security:authentication property="principal.name"/>
            </security:authorize>
            <security:authorize access="hasAuthority('view_orders_self')">
                <div class="row">
                    <a href="${app_entry_path}/orders/self"
                       class="btn btn-outline-success" aria-pressed="true" role="button">SHOW ORDERS</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('create_feedback')">
                <div class="row">
                    <a href="${app_entry_path}/feedback/create"
                       class="btn btn-outline-success" aria-pressed="true" role="button">LEAVE FEEDBACK</a>
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
            <security:authorize access="hasAuthority('view_items')">
                <div class="row">
                    <a href="${app_entry_path}/items"
                       class="btn btn-outline-success" aria-pressed="true" role="button">ITEMS</a>
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
