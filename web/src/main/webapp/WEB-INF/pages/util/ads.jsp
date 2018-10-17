<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<security:authorize access="hasAnyAuthority('sales_admin_basic_permission', 'security_admin_basic_permission')">
    <img src="${app}/resources/images/admin.jpg" class="img-fluid" alt="Hello admin" title="Admin">
</security:authorize>
<security:authorize access="hasAuthority('user_basic_permission')">
    <img src="${app}/resources/images/commercial.jpg" class="img-fluid" alt="ADS" title="Some random ads">
</security:authorize>