<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <%-- APPLICATION CONTEXT PATH --%>
    <c:set var="app" value="${pageContext.request.contextPath}"/>

    <%-- PUBLIC ENTRY POINT PREFIX --%>
    <c:set var="entry_point_prefix" value="/web"/>

    <%-- INITIAL APP PATH --%>
    <c:set var="app_entry_path" value="${app}${entry_point_prefix}"/>

    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Update item discounts</title>
</head>
<body>
<div class="container wide">
    <div class="row">
        <div class="col-xl-2">
            <jsp:include page="/WEB-INF/pages/util/ads.jsp"/>
        </div>
        <div class="col-xl-8">
            <div class="row">
                <h1>Update discounts for all items</h1>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <security:authorize access="hasAuthority('update_discount_item')">
                        <form:form action="${app_entry_path}/items/discounts/update" modelAttribute="discountData" method="post">
                            <form:errors path="minPriceRange" cssClass="container-fluid" element="div"/>
                            <div class="form-group">
                                <form:label path="minPriceRange">Min item price range value</form:label>
                                <form:input type="number" path="minPriceRange" step="0.01" min="1" class="form-control" id="minPriceRange"/>
                            </div>
                            <form:errors path="maxPriceRange" cssClass="container-fluid" element="div"/>
                            <div class="form-group">
                                <form:label path="maxPriceRange">Max item price range value</form:label>
                                <form:input type="number" path="maxPriceRange" step="0.01" min="1" class="form-control" id="maxPriceRange"/>
                            </div>
                            <div class="form-group">
                                <form:select path="discountId" name="discountId">
                                    <c:forEach items="${discounts}" var="discount">
                                        <option value="${discount.id}">${discount.name} - ${discount.percent}%</option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form:form>
                    </security:authorize>
                </div>
            </div>
        </div>
        <div class="col-xl-2">
            <security:authorize access="isAuthenticated()">
                Hello <security:authentication property="principal.name"/>
            </security:authorize>
            <security:authorize access="hasAnyAuthority('view_orders_all')">
                <div class="row">
                    <a href="${app_entry_path}/orders/admin"
                       class="btn btn-outline-success" aria-pressed="true" role="button">SHOW ORDERS</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('view_feedback')">
                <div class="row">
                    <a href="${app_entry_path}/feedback"
                       class="btn btn-outline-success" aria-pressed="true" role="button">SHOW FEEDBACK</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('view_news')">
                <div class="row">
                    <a href="${app_entry_path}/news"
                       class="btn btn-outline-success" aria-pressed="true" role="button">NEWS</a>
                </div>
            </security:authorize>
            <jsp:include page="/WEB-INF/pages/util/ads.jsp"/>
            <div class="row">
                <a href="${app_entry_path}/logout"
                   class="btn btn-outline-success" aria-pressed="true" role="button">LOG OUT</a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/pages/util/js.jsp"/>
</body>
</html>

