<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <c:set var="app" value="${pageContext.request.contextPath}"/>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Orders</title>
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
                            <th scope="col">Created</th>
                            <th scope="col">User email</th>
                            <th scope="col">Item name</th>
                            <th scope="col">Vendor code</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Total price</th>
                            <th scope="col">Status</th>
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
                                <td>${order.created}</td>
                                <td>
                                    <security:authorize access="hasAuthority('view_orders_all')">
                                        ${order.user.email}
                                    </security:authorize>
                                    <security:authorize access="hasAuthority('view_orders_self')">
                                        <security:authentication property="principal.username"/>
                                    </security:authorize>
                                </td>
                                    <%--<fmt:formatNumber var="price" value="${item.price}"
                                                      maxFractionDigits="2"/>--%>
                                <td>${order.item.name}</td>
                                <td>${order.item.vendorCode}</td>
                                <td>${order.quantity}</td>
                                <td>${order.totalPrice}</td>
                                <td>${order.status}</td>
                                <td>
                                    <security:authorize access="hasAuthority('update_order_status')">
                                        <%--<a href="${app}/web/orders"
                                           class="btn btn-outline-success" aria-pressed="true" role="button">SHOW
                                            ORDERS</a>--%>
                                        <form action="${app}/web/orders/${order.uuid}" method="post">
                                            <div class="form-group">
                                                <%--<label for="status">Status</label>--%>
                                                <select name="status" class="custom-select-lg" id="status">
                                                    <c:set var="status" value="${order.status}"/>
                                                    <c:forEach items="${statusEnum}" var="status">
                                                        <c:choose>
                                                            <c:when test="${status == order.status}">
                                                                <option value="${status}" selected>${status}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${status}">${status}</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                    <%--<c:choose>
                                                        <c:when test="${status == 'NEW'}">
                                                            <option value="NEW" selected>NEW</option>
                                                            <option value="REVIEWING">REVIEWING</option>
                                                            <option value="IN_PROGRESS">IN_PROGRESS</option>
                                                            <option value="DELIVERED">DELIVERED</option>
                                                        </c:when>
                                                        <c:when test="${status == 'REVIEWING'}">
                                                            <option value="NEW">NEW</option>
                                                            <option value="REVIEWING" selected>REVIEWING</option>
                                                            <option value="IN_PROGRESS">IN_PROGRESS</option>
                                                            <option value="DELIVERED">DELIVERED</option>
                                                        </c:when>
                                                        <c:when test="${status == 'IN_PROGRESS'}">
                                                            <option value="NEW">NEW</option>
                                                            <option value="REVIEWING">REVIEWING</option>
                                                            <option value="IN_PROGRESS" selected>IN_PROGRESS</option>
                                                            <option value="DELIVERED">DELIVERED</option>
                                                        </c:when>
                                                        <c:when test="${status == 'DELIVERED'}">
                                                            <option value="NEW">NEW</option>
                                                            <option value="REVIEWING">REVIEWING</option>
                                                            <option value="IN_PROGRESS">IN_PROGRESS</option>
                                                            <option value="DELIVERED" selected>DELIVERED</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="NEW" selected>NEW</option>
                                                            <option value="REVIEWING">REVIEWING</option>
                                                            <option value="IN_PROGRESS">IN_PROGRESS</option>
                                                            <option value="DELIVERED">DELIVERED</option>
                                                        </c:otherwise>
                                                    </c:choose>--%>
                                                </select>
                                            </div>
                                            <button type="submit" class="btn btn-primary">CHANGE STATUS</button>
                                        </form>
                                    </security:authorize>
                                    <security:authorize access="hasAuthority('delete_order_self')">
                                        <a href="${app}/web/orders/${order.uuid}/delete"
                                           class="btn btn-outline-success" aria-pressed="true" role="button">DELETE</a>
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
            <security:authorize access="hasAuthority('view_user_self')">
                <security:authentication property="principal.id" var="userid"/>
                <div class="row">
                    <a href="${app}/web/users/${userid}"
                       class="btn btn-outline-success" aria-pressed="true" role="button">PROFILE</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('view_items')">
                <div class="row">
                    <a href="${app}/web/items"
                       class="btn btn-outline-success" aria-pressed="true" role="button">ITEMS</a>
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