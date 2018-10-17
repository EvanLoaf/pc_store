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
    <title>Users</title>
</head>
<body>
<div class="container wide">
    <div class="row">
        <div class="col-md-2">
            <jsp:include page="/WEB-INF/pages/util/ads.jsp"/>
        </div>
        <div class="col-md-8">

            <div class="row">
                <c:if test="${param.update == 'true'}">
                    <div class="alert alert-success" role="alert">
                        <p> User updated successfully</p>
                    </div>
                </c:if>
            </div>

            <div class="row">
                <div class="col-md-8">
                    <form action="${app_entry_path}/users/delete" method="post">
                        <div class="row">
                            <div class="col-md-12">
                                <security:authorize access="hasAuthority('delete_user')">
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
                                        <th scope="col">Select</th>
                                        <th scope="col">Email</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Address</th>
                                        <th scope="col">Phone</th>
                                        <th scope="col">Discount</th>
                                        <th scope="col">Role</th>
                                        <th scope="col">Disabled</th>
                                        <th scope="col">Actions</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="counter" value="${pagination.startPosition}" scope="page"/>
                                    <c:forEach items="${users}" var="user">
                                        <tr>
                                            <th scope="row">
                                                <c:out value="${counter}"/>
                                            </th>
                                            <td>
                                                <input type="checkbox" name="ids" value="${user.id}">
                                            </td>
                                            <td>${user.email}</td>
                                            <td>${user.firstName} ${user.lastName}</td>
                                            <td>${user.address}</td>
                                            <td>${user.phoneNumber}</td>
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
                                            <td>${user.role.name}</td>
                                            <td>${user.disabled}</td>
                                            <td>
                                                <security:authorize access="hasAuthority('update_users_all')">
                                                    <a href="${app_entry_path}/users/${user.id}/update"
                                                       class="btn btn-primary" aria-pressed="true"
                                                       role="button">UPDATE</a>
                                                </security:authorize>
                                                <security:authorize access="hasAuthority('disable_user')">
                                                    <c:choose>
                                                        <c:when test="${user.disabled}">
                                                            <a href="${app_entry_path}/users/${user.id}/disable?disable=${!user.disabled}"
                                                               class="btn btn-primary" aria-pressed="true"
                                                               role="button">ENABLE</a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a href="${app_entry_path}/users/${user.id}/disable?disable=${!user.disabled}"
                                                               class="btn btn-primary" aria-pressed="true"
                                                               role="button">DISABLE</a>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </security:authorize>
                                            </td>
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