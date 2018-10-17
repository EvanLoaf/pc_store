<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
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
                <h1>Create order</h1>
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
                                <td>${item.price}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty item.discounts}">
                                            <c:out value="${item.discounts.iterator().next().percent} %"/>
                                        </c:when>
                                        <c:otherwise>
                                            0
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty user.discount}">
                                            <c:out value="${user.discount.percent} %"/>
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
                <form:form action="${app_entry_path}/orders" modelAttribute="order" method="post">
                    <form:errors path="quantity" cssClass="alert-danger" element="div"/>
                    <div class="form-group">
                        <form:label path="quantity">Quantity :</form:label>
                        <form:input type="number" path="quantity" min="1" class="form-control" id="quantity" maxlength="9"
                                    placeholder="Enter quantity"/>
                    </div>
                    <div class="form-group" hidden>
                        <form:label path="userEmail">email :</form:label>
                        <form:input type="email" path="userEmail" class="form-control" id="userEmail"
                                    />
                    </div>
                    <div class="form-group" hidden>
                        <form:label path="itemVendorCode">vendor code :</form:label>
                        <form:input type="text" path="itemVendorCode" class="form-control" id="itemVendorCode"
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
            <security:authorize access="hasAuthority('view_items')">
                <div class="row">
                    <a href="${app_entry_path}/items"
                       class="btn btn-outline-success" aria-pressed="true" role="button">ITEMS</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('view_user_self')">
                <div class="row">
                    <a href="${app_entry_path}/users/profile"
                       class="btn btn-outline-success" aria-pressed="true" role="button">PROFILE</a>
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
