<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <c:set var="app" value="${pageContext.request.contextPath}"/>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Create item</title>
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
                    <h1>Create item</h1>
                </div>
            <div class="row">
                <div class="col-lg-12">
                    <form:form action="${app}/web/items" modelAttribute="item" method="post">
                        <form:errors path="name" cssClass="alert-danger" element="div"/>
                        <div class="form-group">
                            <form:label path="name">Name</form:label>
                            <form:input type="text" path="name" class="form-control" id="name"/>
                        </div>
                        <form:errors path="vendorCode" cssClass="alert-danger" element="div"/>
                        <div class="form-group">
                            <form:label path="vendorCode">Vendor code</form:label>
                            <form:input type="text" path="vendorCode" class="form-control"
                                        id="vendorCode"/>
                        </div>
                        <form:errors path="description" cssClass="alert-danger" element="div"/>
                        <div class="form-group">
                            <form:label path="description">Description</form:label>
                            <form:input type="text" path="description" class="form-control" id="description"/>
                        </div>
                        <form:errors path="price" cssClass="alert-danger" element="div"/>
                        <div class="form-group">
                            <form:label path="price">Price</form:label>
                            <form:input type="number" step="0.01" min="1" path="price" class="form-control" id="price"/>
                        </div>
                        <button type="submit" class="btn btn-primary">Create</button>
                    </form:form>
                </div>
            </div>
        </div>
        <div class="col-xl-2">
            <security:authorize access="isAuthenticated()">
                Hello <security:authentication property="principal.name"/>
            </security:authorize>
            <security:authorize access="hasAuthority('view_news')">
                <div class="row">
                    <a href="${app}/web/news"
                       class="btn btn-outline-success" aria-pressed="true" role="button">NEWS</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('view_orders_all')">
                <div class="row">
                    <a href="${app}/web/orders/all"
                       class="btn btn-outline-success" aria-pressed="true" role="button">SHOW ORDERS</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('view_items')">
                <div class="row">
                    <a href="${app}/web/items"
                       class="btn btn-outline-success" aria-pressed="true" role="button">ITEMS</a>
                </div>
            </security:authorize>
            <jsp:include page="/WEB-INF/pages/util/ads.jsp"/>
            <div class="row">
                <a href="${app}/web/logout"
                   class="btn btn-outline-success" aria-pressed="true" role="button">LOG OUT</a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/pages/util/js.jsp"/>
</body>
</html>
