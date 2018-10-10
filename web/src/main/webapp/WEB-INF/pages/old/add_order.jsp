<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                            <th scope="col">Vendor code</th>
                            <th scope="col">Name</th>
                            <th scope="col">Description</th>
                            <th scope="col">Price</th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>${item.vendorCode}</td>
                                <td>${item.name}</td>
                                <td>${item.description}</td>
                                <fmt:formatNumber var="price" value="${item.price}"
                                                  maxFractionDigits="2"/>
                                <td>${price}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <form action="${app}/dispatcher?command=confirm_order&vendor_code=${item.vendorCode}" method="post">
                <div class="form-group">
                    <label for="quantity">Quantity</label>
                    <input type="text" name="quantity" value="${quantity}" class="form-control" id="quantity"
                           placeholder="1">
                </div>
                <button type="submit" class="btn btn-primary">Order PC</button>
            </form>
        </div>
        <div class="col-md-2">
            <c:out value="${sessionScope.user.name}"/>
            <c:choose>
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
            </c:choose>
            <jsp:include page="/WEB-INF/pages/util/ads.jsp"/>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/pages/util/js.jsp"/>
</body>
</html>