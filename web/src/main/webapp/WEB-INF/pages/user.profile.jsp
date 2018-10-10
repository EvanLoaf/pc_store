<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <c:set var="app" value="${pageContext.request.contextPath}"/>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Users</title>
</head>
<body>
<div class="container wide">
    <div class="row">
        <div class="col-xl-2">
            <jsp:include page="/WEB-INF/pages/util/ads.jsp"/>
        </div>
        <div class="col-xl-8">
            <%--<div class="row">
                <div class="col-lg-12">
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger" role="alert">
                            <c:out value="${error}"/>
                        </div>
                    </c:if>
                </div>
            </div>--%>
            <div class="row">
                <div class="col-lg-12">
                    <security:authorize access="isAuthenticated()">
                        <security:authentication property="principal.id" var="userid"/>
                    </security:authorize>
                    <form:form action="${app}/web/users/${user.id}" modelAttribute="user" method="post">
                        <form:errors path="password" cssClass="container-fluid" element="div"/>
                        <div class="form-group">
                            <form:label path="password">Password</form:label>
                            <form:input type="password" path="password" class="form-control" id="password"/>
                        </div>
                        <form:errors path="firstName" cssClass="container-fluid" element="div"/>
                        <div class="form-group">
                            <form:label path="firstName">First name</form:label>
                            <form:input type="text" path="firstName" class="form-control"
                                        id="firstName"/>
                        </div>
                        <form:errors path="lastName" cssClass="container-fluid" element="div"/>
                        <div class="form-group">
                            <form:label path="lastName">Last name</form:label>
                            <form:input type="text" path="lastName" class="form-control" id="lastName"/>
                        </div>
                        <form:errors path="profile.address" cssClass="container-fluid" element="div"/>
                        <div class="form-group">
                            <form:label path="profile.address">Last name</form:label>
                            <form:input type="text" path="profile.address" class="form-control" id="profile.address"/>
                        </div>
                        <form:errors path="profile.phoneNumber" cssClass="container-fluid" element="div"/>
                        <div class="form-group">
                            <form:label path="profile.phoneNumber">Last name</form:label>
                            <form:input type="text" path="profile.phoneNumber" class="form-control"
                                        id="profile.phoneNumber"/>
                        </div>
                        <div class="form-group">
                            <c:set value="${user.role.name}" var="curr_role"/>
                            <form:select path="role.id">
                                <c:forEach items="roles" var="role">
                                    <c:choose>
                                        <c:when test="${curr_role == role.name}">
                                            <form:option value="role.id" selected="true">${role.name}</form:option>
                                        </c:when>
                                        <c:otherwise>
                                            <form:option value="role.id">${role.name}</form:option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </form:select>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form:form>
                </div>
            </div>
            <security:authorize access="hasAnyAuthority('view_orders_self', 'view_orders_all')">
                <div class="row">
                    <a href="${app}/web/orders"
                       class="btn btn-outline-success" aria-pressed="true" role="button">SHOW ORDERS</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('view_news')">
                <div class="row">
                    <a href="${app}/web/news"
                       class="btn btn-outline-success" aria-pressed="true" role="button">NEWS</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('view_items')">
                <div class="row">
                    <a href="${app}/web/items"
                       class="btn btn-outline-success" aria-pressed="true" role="button">ITEMS</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('view_users_all')">
                <div class="row">
                    <a href="${app}/web/users"
                       class="btn btn-outline-success" aria-pressed="true" role="button">USERS</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('view_audit')">
                <div class="row">
                    <a href="${app}/web/audit"
                       class="btn btn-outline-success" aria-pressed="true" role="button">AUDIT</a>
                </div>
            </security:authorize>
        </div>
        <div class="col-xl-2">
            <security:authorize access="isAuthenticated()">
                Hello <security:authentication property="principal.name"/>
            </security:authorize>
            <jsp:include page="/WEB-INF/pages/util/ads.jsp"/>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/pages/util/js.jsp"/>
</body>
</html>
