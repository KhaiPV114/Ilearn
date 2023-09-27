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
        <h3>Cart view</h3>
        <a target="_blank" href="${pageContext.request.contextPath}/cart">View Cart</a>
        <h3>Add cart as guest</h3>
        
        <form action="add-to-cart" method="post">
            <input type="text" name="course-id" placeholder="course id">
            <input type="submit" value="add course to cart">
            <p>${messageAddToCart}</p>
        </form>
        ${user.username}
        <br/>
        <button onclick="window.location.href='/online-learning/BeUser?status=Login'">
            Login
        </button>
        <br/>
        <button onclick="window.location.href='/online-learning/BeUser?status=Logout'">
            Logout
        </button>
        <br/>
        ${message}
        
    </body>
</html>
