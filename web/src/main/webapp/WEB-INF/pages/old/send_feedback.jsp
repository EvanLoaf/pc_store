<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <c:set var="app" value="${pageContext.request.contextPath}"/>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Send feedback</title>
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
            <form action="${app}/dispatcher?command=submit_feedback" method="post">
                <div class="form-group">
                    <label for="input_feedback">Feedback message</label>
                    <textarea name="feedback" class="form-control"
                              id="input_feedback"
                              placeholder="I love you">
                        <c:out value="${feedback}"/>
                    </textarea>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
        <div class="col-md-4">
            <c:out value="${sessionScope.user.name}"/>
            <div class="row">
                <a href="${app}/dispatcher?command=orders" class="btn btn-primary"
                   aria-pressed="true" role="button">ORDERS</a>
            </div>
            <div class="row">
                <a href="${app}/dispatcher?command=items" class="btn btn-light"
                   aria-pressed="true" role="button">ITEMS PAGE</a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/pages/util/js.jsp"/>
</body>
</html>
