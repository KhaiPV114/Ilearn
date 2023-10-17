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
    <body>
        <jsp:include page="/layout/header.jsp"/>
        <div class="container text-center">
            <div class="row my-5">
                <h1 class="mt-5 text-warning">404 - Not Found!</h1>
                <h3 class="text-warning">Resource not found!</h3>
                <a type="submit" class="rbt-btn btn-md btn-gradient hover-icon-reverse w-25 mx-auto" href="${pageContext.request.contextPath}">
                    <span class="icon-reverse-wrapper">
                        <span class="btn-text">Back to home</span>
                        <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                        <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                    </span>
                </a>
            </div>
        </div>
        <jsp:include page="/layout/footer.jsp"/>
        <jsp:include page="/layout/scripts.jsp"/>
    </body>
</html>