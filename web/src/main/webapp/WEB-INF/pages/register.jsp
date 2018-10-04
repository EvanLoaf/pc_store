<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <c:set var="app" value="${pageContext.request.contextPath}"/>
    <jsp:include page="${app}/WEB-INF/pages/util/head.jsp"/>
    <title>Registration</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4">
        </div>
        <div class="col-md-4">
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    <c:out value="${error}"/>
                </div>
            </c:if>

            <form:form action="${app}/users" modelAttribute="user" method="post">
                <form:errors path="*" cssClass="container-fluid" element="div"/>
                <div class="form-group">
                    <form:label path="email">Email address</form:label>
                    <form:input type="email" path="email" class="form-control" id="email"
                           aria-describedby="emailHelp"
                           placeholder="mail@mail.com"/>
                </div>
                <div class="form-group">
                    <form:label path="password">Password</form:label>
                    <form:input type="password" path="password" class="form-control" id="password"
                           placeholder="********"/>
                </div>
                <div class="form-group">
                    <form:label path="firstName">First name</form:label>
                    <form:input type="text" path="firstName"  class="form-control"
                           id="firstName"
                           placeholder="John"/>
                </div>
                <div class="form-group">
                    <form:label path="lastName">Last name</form:label>
                    <form:input type="text" path="lastName" class="form-control" id="lastName"
                           placeholder="Doe"/>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form:form>
            <%--<a href="${app}/dispatcher?command=account_exists" class="btn btn-dark" aria-pressed="true" role="button">Already
                got an account</a>--%>
        </div>
        <div class="col-md-4">
        </div>
    </div>
</div>
<jsp:include page="${app}/WEB-INF/pages/util/js.jsp"/>
</body>
</html>
