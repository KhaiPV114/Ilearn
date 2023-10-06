<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add to wishlist</title>
    </head>
    <body>
        <c:if test="${user != null}">
            <p>Welcome, ${user.fullName}</p>
            <h3>
                <a target="_blank" href="${pageContext.request.contextPath}/dashboard/learner/wishlist">View Wishlist</a>
            </h3>
            <hr/>
            <h3>Add course id to cart</h3>
            <form action="${pageContext.request.contextPath}/add-to-wishlist" method="post">
                <input type="text" name="course-id" placeholder="course id">
                <input type="submit" value="add to cart">
            </form>
            <hr/>
        </c:if>
        <c:if test="${user == null}">
            <a target="_blank" href="${pageContext.request.contextPath}/authentication">Login/Register</a>
        </c:if>
    </body>
</html>
