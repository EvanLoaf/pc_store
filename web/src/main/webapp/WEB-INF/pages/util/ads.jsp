<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<c:choose>
    <c:when test="${sessionScope.user.role == 'USER'}">
        <img src="${app}/resources/images/commercial.jpg" class="img-fluid" alt="ADS" title="Some random ads">
    </c:when>
    <c:otherwise>
        <img src="${app}/resources/images/admin.jpg" class="img-fluid" alt="Hello admin" title="Admin">
    </c:otherwise>
</c:choose>