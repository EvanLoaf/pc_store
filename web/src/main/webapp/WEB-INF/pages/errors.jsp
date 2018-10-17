<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <%-- APPLICATION CONTEXT PATH --%>
    <c:set var="app" value="${pageContext.request.contextPath}"/>

    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <meta http-equiv="Content-Type">
    <title>Error Develop Page</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4">
        </div>
        <div class="col-md-4">
            <div class="alert alert-primary" role="alert">
                Oops.. Something went wrong<br>
                Our engineers are already working on the problem
            </div>
            <div>
                <img src="${app}/resources/images/error_cat.jpg" class="img-fluid" alt="We are sorry" title="Sorry Cat">
            </div>
        </div>
        <div class="col-md-4">
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/pages/util/js.jsp"/>
</body>
</html>
