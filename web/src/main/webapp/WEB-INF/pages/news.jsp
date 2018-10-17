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
    <title>News</title>
</head>
<body>
<div class="container wide">
    <div class="row">
        <div class="col-md-2">
            <jsp:include page="/WEB-INF/pages/util/ads.jsp"/>
        </div>
        <div class="col-md-8">
            <form action="${app_entry_path}/news/delete" method="post">
                <div class="row">
                    <div class="col-md-12">
                        <security:authorize access="hasAuthority('create_news')">
                            <a href="${app_entry_path}/news/create"
                               class="btn btn-outline-primary" aria-pressed="true" role="button">ADD NEWS</a>
                        </security:authorize>
                        <security:authorize access="hasAuthority('remove_news_all')">
                            <button type="submit" class="btn btn-primary">REMOVE SELECTED</button>
                        </security:authorize>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <security:authorize access="hasAuthority('remove_news_all')">
                                    <th scope="col">Select</th>
                                </security:authorize>
                                <th scope="col">Title</th>
                                <th scope="col">Created</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:set var="counter" value="${pagination.startPosition}" scope="page"/>
                            <c:forEach items="${news}" var="news">
                                <tr>
                                    <th scope="row">
                                        <c:out value="${counter}"/>
                                    </th>
                                    <security:authorize access="hasAuthority('remove_news_all')">
                                        <td>
                                            <input type="checkbox" name="ids" value="${news.id}">
                                        </td>
                                    </security:authorize>
                                    <td>
                                        <a href="${app_entry_path}/news/${news.id}"
                                           class="page-link" aria-pressed="true"
                                           role="button">${news.title}</a>
                                    </td>
                                    <td>${news.created}</td>
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
                                            <a class="page-link" href="${app_entry_path}/news?page=${page}">${page}</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item"><a class="page-link" href="${app_entry_path}/news?page=${page}">${page}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </ul>
                    </nav>
                </div>
            </form>
        </div>
        <div class="col-md-2">
            <security:authorize access="isAuthenticated()">
                Hello <security:authentication property="principal.name"/>
            </security:authorize>
            <security:authorize access="hasAuthority('view_orders_self')">
                <div class="row">
                    <a href="${app_entry_path}/orders/self"
                       class="btn btn-outline-success" aria-pressed="true" role="button">SHOW ORDERS</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('view_orders_all')">
                <div class="row">
                    <a href="${app_entry_path}/orders/all"
                       class="btn btn-outline-success" aria-pressed="true" role="button">SHOW ORDERS</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('view_feedback')">
                <div class="row">
                    <a href="${app_entry_path}/feedback"
                       class="btn btn-outline-success" aria-pressed="true" role="button">SHOW FEEDBACK</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('view_user_self')">
                <div class="row">
                    <a href="${app_entry_path}/users/profile"
                       class="btn btn-outline-success" aria-pressed="true" role="button">PROFILE</a>
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
    <jsp:include page="/WEB-INF/pages/util/js.jsp"/>
</body>
</html>
