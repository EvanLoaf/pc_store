<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <c:set var="app" value="${pageContext.request.contextPath}"/>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Update user</title>
</head>
<body>
<div class="container wide">
    <div class="row">
        <div class="col-xl-2">
            <jsp:include page="/WEB-INF/pages/util/ads.jsp"/>
        </div>
        <div class="col-xl-8">
            <div class="row">
                <div class="col-lg-12">
                    <form:form action="${app}/web/users/${user.id}" modelAttribute="user" method="post">
                        <form:errors path="password" cssClass="container-fluid" element="div"/>
                        <div class="form-group">
                            <form:label path="password">Password</form:label>
                            <form:input type="password" path="password" class="form-control" id="password"/>
                        </div>
                        <div class="form-group">
                            <c:set value="${user.role.name}" var="currRole"/>
                            <form:select path="role.id" name="role.id">
                                <c:forEach items="${roles}" var="role">
                                    <c:set value="${role.name}" var="rolename"/>
                                    <c:set value="${role.id}" var="roleid"/>
                                    <c:choose>
                                        <c:when test="${currRole==rolename}">
                                            <option value="${roleid}" selected>${rolename}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${roleid}">${rolename}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </form:select>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form:form>
                </div>
            </div>
        </div>
        <div class="col-xl-2">
            <security:authorize access="isAuthenticated()">
                Hello <security:authentication property="principal.name"/>
            </security:authorize>
            <security:authorize access="hasAuthority('view_users_all')">
                <div class="row">
                    <a href="${app}/web/users"
                       class="btn btn-outline-success" aria-pressed="true" role="button">USERS</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('view_audit')">
                <div class="row">
                    <a href="${app}/web/audit"
                       class="btn btn-outline-success" aria-pressed="true" role="button">AUDIT</a>
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
