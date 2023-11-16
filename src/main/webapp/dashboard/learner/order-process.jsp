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
        <script src="https://cdn.lordicon.com/lordicon-1.1.0.js"></script>
    </head>
    <body class="rbt-header-sticky">
        <jsp:include page="/layout/header.jsp"/>

        <div class="rbt-pricing-area bg-color-white mt--100 mb--100">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 offset-lg-2">
                        <div class="advance-pricing">
                            <div class="inner">
                                <div class="row row--0">
                                    <div class="col-lg-6 col-md-6 col-12">
                                        <div class="pricing-left color-box bg-gradient-10">
                                            <c:choose>
                                                <c:when test="${responseCode=='00'}">
                                                    <lord-icon
                                                        src="https://cdn.lordicon.com/guqkthkk.json"
                                                        trigger="loop"
                                                        delay="1000"
                                                        style="width:350px;height:350px">
                                                    </lord-icon>
                                                </c:when>
                                                <c:when test="${responseCode=='24'}">
                                                    <img src="https://cdn-icons-png.flaticon.com/512/10621/10621089.png" alt="alt" style="height: 350px"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <lord-icon
                                                        src="https://cdn.lordicon.com/jxzkkoed.json"
                                                        trigger="loop"
                                                        delay="1000"
                                                        style="width:350px;height:350px">
                                                    </lord-icon>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-12" style="position: relative">
                                        <div class="pricing-right position-relative" >
                                            <div class="pricing-offer">
                                                <div class="single-list" style="text-align: center">
                                                    <c:choose>
                                                        <c:when test="${responseCode=='00'}">
                                                            <h2 style="color: #B966E7">Thank you!</h2>
                                                            <h4 class="price-title">Order id: ${orderId}</h4>
                                                            <p>Purchase order successful</p>
                                                        </c:when>
                                                        <c:when test="${responseCode=='24'}">
                                                            <h2 style="color: #B966E7">Thank you!</h2>
                                                            <h4 class="price-title">Order id: ${orderId}</h4>
                                                            <p>Order has been cancelled</p>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <h2 style="color: #FF0003">Opps!</h2>
                                                            <h5 class="price-title">Order id: ${orderId}</h5>
                                                            <p>The payment process encountered an error. Contact <a href="mailto:duyduc.luonghuu@gmail.com">ADMIN</a> to report.</p>
                                                            <p style='color: #FF0003'>Error Code: ${responseCode}x${transactionCode}</p>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="pricing-btn-group" style="padding: 0px 50px">
                                            <button class="rbt-btn btn-gradient w-100" style="margin: 5px 0px" onclick="window.location.href = '${pageContext.request.contextPath}/learner/order/history'">View Order History</button>
                                            <button class="rbt-btn btn-border w-100" style="margin: 5px 0px" onclick="window.location.href = '${pageContext.request.contextPath}/homepage'">Return Homepage</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="/layout/footer.jsp"/>
    </body>
    <jsp:include page="/layout/scripts.jsp"/>
</html>