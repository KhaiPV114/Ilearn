<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="courseService" scope="request" class="com.onlinelearning.Services.Impl.CourseServiceImpl" />
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="/layout/links.jsp"/>
    </head>
    <body class="rbt-header-sticky">
        <jsp:include page="/layout/header.jsp"/>
        <div class="rbt-breadcrumb-default ptb--100 ptb_md--50 ptb_sm--30 bg-gradient-1">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="breadcrumb-inner text-center">
                            <h2 class="title">Checkout</h2>
                            <ul class="page-list">
                                <li class="rbt-breadcrumb-item"><a href="index.html">Home</a></li>
                                <li><div class="icon-right"><i class="feather-chevron-right"></i></div></li>
                                <li class="rbt-breadcrumb-item"><a href="index.html">Cart</a></li>
                                <li><div class="icon-right"><i class="feather-chevron-right"></i></div></li>
                                <li class="rbt-breadcrumb-item active">Checkout</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="checkout_area bg-color-white rbt-section-gap" id="content-display">
            <div class="container">
            </div>
        </div>
        <jsp:include page="/layout/footer.jsp"/>
    </body>
    <script>
        const targetElement = document.getElementById('content-display');
        const scrollDuration = 500;
        scrollToElementWithTime(targetElement, scrollDuration);
        function scrollToElementWithTime(element, duration) {
            const targetPosition = element.offsetTop;
            const startPosition = window.pageYOffset;
            const distance = targetPosition - startPosition;
            let startTime = null;
            function scrollStep(timestamp) {
                if (!startTime) {
                    startTime = timestamp;
                }
                const progress = timestamp - startTime;
                const percentage = Math.min(progress / duration, 1);
                window.scrollTo(0, startPosition + distance * percentage);
                if (progress < duration) {
                    window.requestAnimationFrame(scrollStep);
                }
            }
            window.requestAnimationFrame(scrollStep);
        }
    </script>
    <jsp:include page="/layout/scripts.jsp"/>
</html>