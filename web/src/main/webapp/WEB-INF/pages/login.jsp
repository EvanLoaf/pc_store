<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <c:set var="app" value="${pageContext.request.contextPath}"/>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Login</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4">
        </div>
        <div class="col-md-4">

            <c:if test="${param.error == 'true'}">
                <div class="alert alert-danger" role="alert">
                    <p>Wrong Username or Password</p>
                </div>
            </c:if>

            <c:if test="${param.logout == 'true'}">
                <div class="alert alert-success" role="alert">
                    <p>Logged off successfully</p>
                </div>
            </c:if>

            <div class="row">
                <h1>Login into your account</h1>
            </div>

            <form action="${app}/web/login" method="post">
                <div class="form-group">
                    <label for="email">Email address</label>
                    <input type="email" name="email" class="form-control" id="email"
                                aria-describedby="emailHelp"
                                placeholder="your@mail.com"/>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" name="password" class="form-control" id="password"
                                placeholder="********"/>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
            <a href="${app}/web/register" class="btn btn-dark" aria-pressed="true" role="button">Create an account</a>
        </div>
        <div class="col-md-4">
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/pages/util/js.jsp"/>
</body>
</html>
