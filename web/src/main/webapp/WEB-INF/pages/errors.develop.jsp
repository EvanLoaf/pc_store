<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <meta http-equiv="Content-Type">
    <title>Error Page</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-2">
        </div>
        <div class="col-md-8">
            <h2>Application Error, please contact support.</h2>
            <h3>Debug information:</h3>
            <p>Requested URL = ${url}</p>
            <br>
            <p>Exception = ${exception.message}</p>
            <br>
            <b>Exception Stack Trace :</b>
            <c:forEach items="${exception.stackTrace}" var="est">
                ${est}
            </c:forEach>
        </div>
        <div class="col-md-2">
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/pages/util/js.jsp"/>
</body>
</html>
