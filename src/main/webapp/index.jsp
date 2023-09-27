<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>JSP layout:</h1>
        <a target="_blank" href="${pageContext.request.contextPath}/testing/cart.jsp">Cart</a>        
        <br>
        <a target="_blank" href="${pageContext.request.contextPath}/dashboard/instructor/_draft.jsp">Instructor layout draft</a>  
        <br>
        <a target="_blank" href="${pageContext.request.contextPath}/dashboard/instructor/dashboard.jsp">Instructor dashboard</a>        
        <br>
        <a target="_blank" href="${pageContext.request.contextPath}/dashboard/instructor/profile.jsp">Instructor profile</a>        
        <br>
        <a target="_blank" href="${pageContext.request.contextPath}/dashboard/instructor/course.jsp">Instructor course</a>        
        <br>
        <a target="_blank" href="${pageContext.request.contextPath}/dashboard/learner/_draft.jsp">Learner layout draft</a>        
        <br>
        <a target="_blank" href="${pageContext.request.contextPath}/dashboard/learner/dashboard.jsp">Learner dashboard</a>        
        <br>
        <a target="_blank" href="${pageContext.request.contextPath}/dashboard/learner/profile.jsp">Learner profile</a>        
        <br>
        <a target="_blank" href="${pageContext.request.contextPath}/dashboard/learner/course.jsp">Learner course</a>        
        <br>
        <a target="_blank" href="${pageContext.request.contextPath}/dashboard/learner/wishlist.jsp">Learner wishlist</a>        
        <br>
        <a target="_blank" href="${pageContext.request.contextPath}/testing/upload-image">Test upload image</a>        
        <br>
        <a target="_blank" href="${pageContext.request.contextPath}/manager/category">Manager course category</a>        
        <br>
    </body>
</html>
