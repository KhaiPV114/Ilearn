<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="/layout/links.jsp"/>
    </head>
    <body class="rbt-header-sticky">
        <jsp:include page="/layout/header.jsp"/>
        <div class="container">
            <h3>
                <a target="_blank" href="${pageContext.request.contextPath}/cart">View Cart</a>
            </h3>
            <hr/>
            <h3>Add course id to cart</h3>
            <form action="${pageContext.request.contextPath}/cart/testing" method="get">
                <select name="course-id-add">
                    <c:forEach var="course" items="${courses}">
                        <option value="${course.id}">${course.name}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="Add To Cart">
                <br>
            </form>

        </div>
        <jsp:include page="/layout/footer.jsp"/>
    </body>
    <jsp:include page="/layout/scripts.jsp"/>
</html>