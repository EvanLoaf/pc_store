<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>Feedback</title>
</head>
<body>
<div class="container wide">
    <div class="row">
        <div class="col-md-2">
            <jsp:include page="/WEB-INF/pages/util/ads.jsp"/>
        </div>
        <div class="col-md-8">
            <div class="row">
                <div class="col-md-8">
                    <form action="${app_entry_path}/feedback/delete" method="post">
                        <div class="row">
                            <div class="col-md-12">
                                <security:authorize access="hasAuthority('delete_feedback')">
                                    <button type="submit" class="btn btn-primary">DELETE SELECTED</button>
                                </security:authorize>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <security:authorize access="hasAuthority('delete_feedback')">
                                            <th scope="col">Select</th>
                                        </security:authorize>
                                        <th scope="col">User email</th>
                                        <th scope="col">User name</th>
                                        <th scope="col">Message</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="counter" value="${pagination.startPosition}" scope="page"/>
                                    <c:forEach items="${feedback}" var="feedbackItem">
                                        <tr>
                                            <th scope="row">
                                                <c:out value="${counter}"/>
                                            </th>
                                            <security:authorize access="hasAuthority('delete_feedback')">
                                                <td>
                                                    <input type="checkbox" name="ids" value="${feedbackItem.id}">
                                                </td>
                                            </security:authorize>
                                            <td>${feedbackItem.user.email}</td>
                                            <td>${feedbackItem.user.name}</td>
                                            <td>${feedbackItem.message}</td>
                                        </tr>
                                        <c:set var="counter" value="${counter + 1}" scope="page"/>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="row">
                            <nav aria-label="...">
                                <ul class="pagination">
                                    <c:forEach items="${pagination.pageNumbers}" var="page">
                                        <c:choose>
                                            <c:when test="${page == pagination.page}">
                                                <li class="page-item active">
                                                    <a class="page-link"
                                                       href="${app_entry_path}/items?page=${page}">${page}</a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item"><a class="page-link"
                                                                         href="${app_entry_path}/items?page=${page}">${page}</a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </ul>
                            </nav>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-2">
            <security:authorize access="isAuthenticated()">
                Hello <security:authentication property="principal.name"/>
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
            <security:authorize access="hasAnyAuthority('view_orders_all')">
                <div class="row">
                    <a href="${app_entry_path}/orders/admin"
                       class="btn btn-outline-success" aria-pressed="true" role="button">SHOW ORDERS</a>
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