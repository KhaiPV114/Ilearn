<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add to cart</title>
    </head>
    <body>
        <h3>
            <a target="_blank" href="${pageContext.request.contextPath}/cart">View Cart</a>
        </h3>
        <hr/>
        <h3>Add course id to cart</h3>
        <form action="${pageContext.request.contextPath}/add-to-cart" method="post">
            <input type="text" name="course-id" placeholder="course id">
            <input type="submit" value="add to cart">
            <p>${messageAddToCart}</p>
        </form>
        <hr/>
        <h3>Authentication</h3>
        <c:if test="${user != null}">
            <p>Welcome, ${user.fullName}</p>
        </c:if>
        <form action="${pageContext.request.contextPath}/BeUser">
            <input type="hidden" name="status" value="Login"/>
            <input type="number" name="user-id"/>
            <button type="submit">
                Login
            </button>
        </form>
        <br/>
        <button onclick="window.location.href = '/online-learning/BeUser?status=Logout&user-id=0'">
            Logout
        </button>
        <br/>
        ${message}
    </body>
</html>
