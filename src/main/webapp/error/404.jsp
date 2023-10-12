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
        <div class="rbt-error-area bg-gradient-11 rbt-section-gap">
            <div class="error-area">
                <div class="container">
                    <div class="row justify-content-center text-center">
                        <div class="col-10">
                            <h1 class="title">404!</h1>
                            <h3 class="sub-title">Page not found</h3>
                            <p>The page you were looking for could not be found.</p>
                            <a class="rbt-btn btn-gradient icon-hover" href="${pageContext.request.contextPath}/homepage">
                                <span class="btn-text">Back To Home</span>
                                <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
        <jsp:include page="/layout/footer.jsp"/>
    </body>
    <jsp:include page="/layout/scripts.jsp"/>
</html>