<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <c:set var="app" value="${pageContext.request.contextPath}"/>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Show news</title>
</head>
<body>
<div class="container wide">
    <div class="row">
        <div class="col-md-2">
            <jsp:include page="/WEB-INF/pages/util/ads.jsp"/>
        </div>
        <div class="col-md-8">
            <security:authorize access="hasAuthority('update_news_all')">
                <div class="row">
                    <h1>You may update this piece of news</h1>
                </div>
                <form:form action="${app}/web/news/${news.id}" modelAttribute="news" method="post">
                    <div class="row">
                        <div class="col-md-12">
                            <a href="${app}/web/news/${news.id}/delete"
                               class="btn btn-outline-primary" aria-pressed="true" role="button">DELETE</a>
                        </div>
                    </div>
                    <div class="row">
                        <form:errors path="title" cssClass="alert-danger" element="div"/>
                        <div class="form-group">
                            <form:label path="title">Title</form:label>
                            <form:input type="text" path="title" class="form-control" id="title"/>
                        </div>
                    </div>
                    <div class="row">
                        <form:errors path="content" cssClass="alert-danger" element="div"/>
                        <div class="form-group">
                            <form:label path="content">Content</form:label>
                            <form:textarea type="text" path="content" class="form-control" cssClass="mytextarea"
                                           id="content"/>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Update</button>
                </form:form>
                <security:authorize access="hasAuthority('remove_comments_all')">
                    <div class="row">
                        <h3 class="panel-title">COMMENTS</h3>
                    </div>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Date</th>
                            <th scope="col">Posted by</th>
                            <th scope="col">Comment</th>
                            <th scope="col">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="counter" value="1" scope="page"/>
                        <c:forEach items="${news.comments}" var="comment">
                            <tr>
                                <th scope="row">
                                    <c:out value="${counter}"/>
                                </th>
                                <td>${comment.created}</td>
                                <td>${comment.user.name}</td>
                                <td>${comment.message}</td>
                                <td>
                                    <security:authorize access="hasAuthority('remove_comments_all')">
                                        <a href="${app}/web/news/${news.id}/comment/${comment.id}/delete"
                                           class="btn btn-primary" aria-pressed="true" role="button">REMOVE</a>
                                    </security:authorize>
                                </td>
                            </tr>
                            <c:set var="counter" value="${counter + 1}" scope="page"/>
                        </c:forEach>
                        </tbody>
                    </table>
                </security:authorize>
            </security:authorize>
            <security:authorize access="hasAuthority('create_comment')">
                <div class="row">
                    <h3 class="panel-title">Title : ${news.title}</h3>
                </div>
                <div class="row">
                    <h4 class="label-info">Created by : ${news.user.name}</h4>
                </div>
                <div class="row">
                    <h5 class="label-info">on ${news.created}</h5>
                </div>
                <div class="news">
                    ${news.content}
                </div>
                <div class="row">
                    <h3 class="panel-title">COMMENTS</h3>
                </div>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Date</th>
                        <th scope="col">Posted by</th>
                        <th scope="col">Comment</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="counter" value="1" scope="page"/>
                    <c:forEach items="${news.comments}" var="comment">
                        <tr>
                            <th scope="row">
                                <c:out value="${counter}"/>
                            </th>
                            <td>${comment.created}</td>
                            <td>${comment.user.name}</td>
                            <td>${comment.message}</td>
                        </tr>
                        <c:set var="counter" value="${counter + 1}" scope="page"/>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="row">
                    <div class="col-lg-12">
                        <form:form action="${app}/web/news/${news.id}/comment/create" modelAttribute="comment"
                                   method="post">
                            <form:errors path="message" cssClass="alert-danger" element="div"/>
                            <div class="form-group">
                                <form:label path="message">Comment</form:label>
                                <form:textarea type="text" path="message" class="form-control" cssClass="mytextarea"
                                               id="message"/>
                            </div>
                            <button type="submit" class="btn btn-primary">Submit comment</button>
                        </form:form>
                    </div>
                </div>
            </security:authorize>
        </div>
        <div class="col-md-2">
            <security:authorize access="isAuthenticated()">
                Hello <security:authentication property="principal.name"/>
            </security:authorize>
            <security:authorize access="hasAuthority('view_orders_self')">
                <div class="row">
                    <a href="${app}/web/orders/self"
                       class="btn btn-outline-success" aria-pressed="true" role="button">SHOW ORDERS</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('view_orders_all')">
                <div class="row">
                    <a href="${app}/web/orders/all"
                       class="btn btn-outline-success" aria-pressed="true" role="button">SHOW ORDERS</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('view_news')">
                <div class="row">
                    <a href="${app}/web/news"
                       class="btn btn-outline-success" aria-pressed="true" role="button">NEWS</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('view_user_self')">
                <security:authentication property="principal.id" var="userid"/>
                <div class="row">
                    <a href="${app}/web/users/${userid}"
                       class="btn btn-outline-success" aria-pressed="true" role="button">PROFILE</a>
                </div>
            </security:authorize>
            <security:authorize access="hasAuthority('view_items')">
                <div class="row">
                    <a href="${app}/web/items"
                       class="btn btn-outline-success" aria-pressed="true" role="button">ITEMS</a>
                </div>
            </security:authorize>
            <jsp:include page="/WEB-INF/pages/util/ads.jsp"/>
            <div class="row">
                <a href="${app}/web/logout"
                   class="btn btn-outline-success" aria-pressed="true" role="button">LOG OUT</a>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/pages/util/js.jsp"/>
</body>
</html>
