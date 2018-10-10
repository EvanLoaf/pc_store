<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <c:set var="app" value="${pageContext.request.contextPath}"/>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Items</title>
</head>
<body>
<div class="container wide">
    <div class="row">
        <div class="col-md-2">
            <jsp:include page="/WEB-INF/pages/util/ads.jsp"/>
        </div>
        <div class="col-md-8">
            <div class="row">
                <div class="col-md-12">
                    <%--<c:if test="${not empty error}">
                        <div class="alert alert-danger" role="alert">
                            <c:out value="${error}"/>
                        </div>
                    </c:if>--%>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Vendor code</th>
                            <th scope="col">Name</th>
                            <th scope="col">Description</th>
                            <th scope="col">Price</th>
                            <th scope="col">Discount</th>
                            <th scope="col">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="counter" value="1" scope="page"/>
                        <c:forEach items="${items}" var="item">
                            <tr>
                                <th scope="row">
                                    <c:out value="${counter}"/>
                                </th>
                                <td>${item.vendorCode}</td>
                                <td>${item.name}</td>
                                <td>${item.description}</td>
                                <%--<fmt:formatNumber var="price" value="${item.price}"
                                                  maxFractionDigits="2"/>--%>
                                <td>${item.price}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty item.discounts}">
                                            <c:out value="${item.discounts[0].percent}"/>
                                        </c:when>
                                        <c:otherwise>
                                            0
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <security:authorize access="hasAuthority('create_order')">
                                        <a href="${app}/web/orders/${item.id}/create"
                                           class="btn btn-primary" aria-pressed="true" role="button">ORDER</a>
                                    </security:authorize>
                                    <security:authorize access="hasAuthority('remove_item')">
                                        <a href="${app}/web/items/${item.id}/delete"
                                           class="btn btn-primary" aria-pressed="true" role="button">DELETE</a>
                                    </security:authorize>
                                    <security:authorize access="hasAuthority('copy_item')">
                                        <a href="${app}/web/items/${item.id}/copy"
                                           class="btn btn-primary" aria-pressed="true" role="button">COPY</a>
                                    </security:authorize>
                                </td>
                            </tr>
                            <c:set var="counter" value="${counter + 1}" scope="page"/>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="col-md-2">
            <security:authorize access="isAuthenticated()">
                Hello <security:authentication property="principal.name"/>
            </security:authorize>
            <%--<c:out value="${sessionScope.user.name}"/>--%>
            <%--<c:choose>

                <c:when test="${sessionScope.user.role == 'USER'}">
                    <div class="row">
                        <a href="${app}/dispatcher?command=orders"
                           class="btn btn-outline-success" aria-pressed="true" role="button">ORDERS</a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="row">
                        <a href="${app}/dispatcher?command=users"
                           class="btn btn-outline-success" aria-pressed="true" role="button">USERS</a>
                    </div>
                </c:otherwise>
            </c:choose>--%>
            <%--<security:authorize access="isAuthenticated()">
                <security:authentication property="principal.id" var="userid"/>
            </security:authorize>--%>
            <security:authorize access="hasAnyAuthority('view_orders_self', 'view_orders_all')">
                <div class="row">
                    <a href="${app}/web/orders"
                       class="btn btn-outline-success" aria-pressed="true" role="button">SHOW ORDERS</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('view_user_self')">
                <security:authentication property="principal.id" var="userid"/>
                <div class="row">
                    <a href="${app}/web/users/${userid}"
                       class="btn btn-outline-success" aria-pressed="true" role="button">PROFILE</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('view_news')">
                <div class="row">
                    <a href="${app}/web/news"
                       class="btn btn-outline-success" aria-pressed="true" role="button">NEWS</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('create_item')">
                <div class="row">
                    <a href="${app}/web/items/create"
                       class="btn btn-outline-success" aria-pressed="true" role="button">CREATE ITEM</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('upload_items')">
                <div class="row">
                    <a href="${app}/web/items/upload"
                       class="btn btn-outline-success" aria-pressed="true" role="button">UPLOAD ITEMS</a>
                </div>
            </security:authorize>
            <jsp:include page="/WEB-INF/pages/util/ads.jsp"/>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/pages/util/js.jsp"/>
</body>
</html>