<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                    <form method="post" action="${app}/web/items/upload" enctype="multipart/form-data">
                        <table>
                            <tr>
                                <td>
                                    <label for="file">Select a file to upload</label>
                                </td>
                                <td>
                                    <input type="file" id="file" name="file"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="submit" value="Upload"/>
                                </td>
                            </tr>
                        </table>
                    </form>
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
            <security:authorize access="hasAuthority('view_items')">
                <div class="row">
                    <a href="${app}/web/items"
                       class="btn btn-outline-success" aria-pressed="true" role="button">ITEMS</a>
                </div>
            </security:authorize>
            <jsp:include page="/WEB-INF/pages/util/ads.jsp"/>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/pages/util/js.jsp"/>
</body>
</html>