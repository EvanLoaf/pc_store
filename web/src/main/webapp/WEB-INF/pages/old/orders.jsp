<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
    <c:set var="app" value="${pageContext.request.contextPath}"/>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Orders</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-2">
            <jsp:include page="/WEB-INF/pages/util/ads.jsp"/>
        </div>
        <div class="col-md-8">
            <div class="row">
                <div class="col-md-12">
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger" role="alert">
                            <c:out value="${error}"/>
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">OrderID</th>
                            <th scope="col">Created</th>
                            <th scope="col">Item vendor code</th>
                            <th scope="col">Item name</th>
                            <th scope="col">Item desc</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Item price</th>
                            <th scope="col">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="counter" value="1" scope="page"/>
                        <c:forEach items="${orders}" var="order">
                            <tr>
                                <th scope="row">
                                    <c:out value="${counter}"/>
                                </th>
                                <td>${order.orderUuid}</td>
                                <td>${order.createdDate}</td>
                                <td>${order.item.vendorCode}</td>
                                <td>${order.item.name}</td>
                                <td>${order.item.description}</td>
                                <td>${order.quantity}</td>
                                <fmt:formatNumber var="price" value="${order.item.price}"
                                                  maxFractionDigits="2"/>
                                <td>${price}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/dispatcher?command=delete_order&uuid=${order.orderUuid}"
                                       class="btn btn-primary" aria-pressed="true" role="button">DELETE</a>
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
            <c:out value="${sessionScope.user.name}"/>
            <div class="row">
                <a href="${app}/dispatcher?command=send_feedback" class="btn btn-primary"
                   aria-pressed="true" role="button">SEND FEEDBACK</a>
            </div>
            <div class="row">
                <a href="${app}/dispatcher?command=items" class="btn btn-dark"
                   aria-pressed="true" role="button">ITEMS</a>
            </div>
            <jsp:include page="/WEB-INF/pages/util/ads.jsp"/>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/pages/util/js.jsp"/>
</body>
</html>