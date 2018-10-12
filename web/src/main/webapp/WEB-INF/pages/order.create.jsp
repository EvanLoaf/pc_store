<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <c:set var="app" value="${pageContext.request.contextPath}"/>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Create order</title>
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
                            <th scope="col">Vendor code</th>
                            <th scope="col">Name</th>
                            <th scope="col">Desc</th>
                            <th scope="col">Price</th>
                            <th scope="col">Item Discount</th>
                            <th scope="col">User Discount</th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
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
                                    <c:choose>
                                        <c:when test="${not empty user.discount}">
                                            <c:out value="${user.discount.percent}"/>
                                        </c:when>
                                        <c:otherwise>
                                            0
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <security:authorize access="hasAuthority('create_order')">
                <form:form action="${app}/web/orders" modelAttribute="order" method="post">
                    <form:errors path="quantity" cssClass="alert-danger" element="div"/>
                    <div class="form-group">
                        <form:label path="quantity">Quantity :</form:label>
                        <form:input type="number" path="quantity" min="1" class="form-control" id="quantity" maxlength="9"
                                    placeholder="Enter quantity"/>
                    </div>
                    <div class="form-group" hidden>
                        <form:label path="user.email">email :</form:label>
                        <form:input type="email" path="user.email" class="form-control" id="email"
                                    />
                    </div>
                    <div class="form-group" hidden>
                        <form:label path="item.vendorCode">vendor code :</form:label>
                        <form:input type="text" path="item.vendorCode" class="form-control" id="vendorcode"
                                    />
                    </div>
                    <button type="submit" class="btn btn-primary">Place order</button>
                </form:form>
            </security:authorize>
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
            <security:authorize access="hasAuthority('view_items')">
                <div class="row">
                    <a href="${app}/web/items"
                       class="btn btn-outline-success" aria-pressed="true" role="button">ITEMS</a>
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
