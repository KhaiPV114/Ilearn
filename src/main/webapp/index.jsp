<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="roleService" scope="session" class="com.onlinelearning.Services.Impl.RoleService" />

<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <c:if test="${not empty sessionScope.user}">
            <h1>Authentication testing:</h1>
            <h3>Username: ${sessionScope.user.username}</h3>
            <h3>Fullname: ${sessionScope.user.fullName}</h3>
            <h3>Email:    ${sessionScope.user.email}</h3>
            <h3>Role: </h3>
            <ul>
                <c:forEach var="role" items="${sessionScope.roles}">
                    <li>${role}</li>
                    </c:forEach>
            </ul>
            <a href="${pageContext.request.contextPath}/authentication/logout">Logout</a>
        </c:if>
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
        <a target="_blank" href="${pageContext.request.contextPath}/authentication">Login/Register</a>        
        <br>

        <c:if test="${roleService.isGuest(pageContext.request)}">
            <script src="https://accounts.google.com/gsi/client"></script>
            <div id="g_id_onload"
                 data-client_id="${initParam.GOOGLE_CLIENT_ID}"
                 data-context="signin"
                 data-ux_mode="redirect"
                 data-login_uri="${pageContext.request.contextPath}/authentication/login-with-google"
                 data-nonce=""
                 data-itp_support="true">
            </div>
        </c:if>
    </body>
</html>
