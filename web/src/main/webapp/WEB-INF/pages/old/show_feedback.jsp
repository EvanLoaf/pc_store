<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <c:set var="app" value="${pageContext.request.contextPath}"/>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Registration</title>
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
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger" role="alert">
                            <c:out value="${error}"/>
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Email</th>
                            <th scope="col">First name</th>
                            <th scope="col">Last name</th>
                            <th scope="col">Message</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="counter" value="1" scope="page"/>
                        <c:forEach items="${feedback}" var="fb">
                            <tr>
                                <th scope="row">
                                    <c:out value="${counter}"/>
                                </th>
                                <td>${fb.user.email}</td>
                                <td>${fb.user.firstName}</td>
                                <td>${fb.user.lastName}</td>
                                <td>${fb.message}</td>
                            </tr>
                            <c:set var="counter" value="${counter + 1}" scope="page"/>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row">
                <a href="${app}/dispatcher?command=users"
                   class="btn btn-outline-success" aria-pressed="true" role="button">USERS</a>
            </div>
            <a href="${app}/dispatcher?command=items" class="btn btn-light"
               aria-pressed="true" role="button">ITEMS PAGE</a>
        </div>
        <div class="col-xl-2">
            <c:out value="${sessionScope.user.name}"/>
            <jsp:include page="/WEB-INF/pages/util/ads.jsp"/>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/pages/util/js.jsp"/>
</body>
</html>
