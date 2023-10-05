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
                <div class="row g-5 checkout-form">

                    <div class="col-lg-7">
                        <!-- Payment Method -->
                        <div class="col-12 mb--60">
                            <h4 class="checkout-title">Payment Method</h4>
                            <div class="checkout-payment-method">
                                <form action="${pageContext.request.contextPath}/cart/checkou/payment" method="post" id="payment-checkout-form">
                                    <input type="hidden" name="order-id" value="${order.id}">
                                    <input type="hidden" name="price" value="${grandTotal}">
                                    <div class="single-method">
                                        <input type="radio" id="payment_paypal" name="payment-method" value="paypal" required disabled>
                                        <label for="payment_paypal">Paypal - Unsupported</label>
                                        <p data-method="paypal">Unsupported</p>
                                    </div>
                                    <div class="single-method">
                                        <input type="radio" id="payment_bank" name="payment-method" value="bank" required>
                                        <label for="payment_bank">Bank</label>
                                        <p data-method="bank">BIDV: LUONG HUU DUC DUY</p>
                                    </div>
                                </form>
                            </div>
                            <br/>
                            <div class="cart-submit-btn-group">
                                <div class="single-button w-50">
                                    <button class="rbt-btn rbt-switch-btn rbt-switch-y w-100 btn-border" data-bs-toggle="modal" data-bs-target="#cancel-checkout">
                                        <span data-text="Cancel">Cancel</span>
                                    </button>
                                    <div class="modal fade" id="cancel-checkout" tabindex="-1" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="staticBackdropLabel">This order will be cancel</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    Are you sure?
                                                </div>
                                                <div class="modal-footer">
                                                    <a class="rbt-btn btn-white btn-sm btn-border radius-round-10" href="#" data-bs-dismiss="modal">Cancel</a>
                                                    <form action="${pageContext.request.contextPath}/cart/checkout/cancel" method="post" id="cancel-checkout-form">
                                                        <input type="hidden" name="order-id" value="${order.id}">
                                                        <a class="rbt-btn btn-sm" href="#" onclick="document.getElementById('cancel-checkout-form').submit();">Yes</a>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="single-button w-50">
                                    <button class="rbt-btn btn-gradient rbt-switch-btn rbt-switch-y w-100" onclick="document.getElementById('payment-checkout-form').submit()">
                                        <span data-text="Place Order">Place Order</span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-5">
                        <div class="row pl--50 pl_md--0 pl_sm--0">
                            <!-- Cart Total -->
                            <div class="col-12 mb--60">

                                <h4 class="checkout-title">Cart Total</h4>

                                <div class="checkout-cart-total">

                                    <h4>Courses <span>Total</span></h4>
                                    <c:if test="${!messageError.isEmpty()}">
                                        <ul class="plan-offer-list" >
                                            <c:forEach var="msg" items="${messageError}">
                                                <li class="off">
                                                    <i class="feather-x"></i>${msg}
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                    <ul>
                                        <c:forEach var="orderItem" items="${orderItems}">
                                            <li>
                                                ${courseService.getCourseById(orderItem.courseId).name}
                                                <c:if test="${orderItem.price==orderItem.originalPrice}">
                                                    <span class="number">$${orderItem.price}</span>
                                                </c:if>
                                                <c:if test="${orderItem.price<orderItem.originalPrice}">
                                                    <span class="number" >$${orderItem.price}</span><span>&nbsp;&nbsp;</span>
                                                    <span class="number" style="text-decoration: line-through; font-size: smaller;">$${orderItem.originalPrice}</span>
                                                </c:if>
                                            </li>
                                        </c:forEach>
                                    </ul>

                                    <p>Sub Total <span class="number">$${subTotal}</span></p>
                                    <p>Discount <span class="number">$${discount}</span></p>

                                    <h4 class="mt--30">Grand Total <span class="number">$${grandTotal}</span></h4>

                                </div>

                            </div>

                        </div>
                    </div>

                </div>
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

    <script>
        const number = document.getElementsByClassName('number');
        for (var item of number) {
            let value = parseFloat(item.innerHTML.trim().replace("$", ""));
            item.innerHTML = '$' + value.toFixed(2);
        }
    </script>
    <jsp:include page="/layout/scripts.jsp"/>
</html>