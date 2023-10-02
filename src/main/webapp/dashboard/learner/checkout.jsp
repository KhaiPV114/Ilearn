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

                                <div class="single-method">
                                    <input type="radio" id="payment_check" name="payment-method" value="check">
                                    <label for="payment_check">Check Payment</label>
                                    <p data-method="check">Please send a Check to Store name with
                                        Store Street, Store Town, Store State, Store Postcode,
                                        Store Country.</p>
                                </div>

                                <div class="single-method">
                                    <input type="radio" id="payment_bank" name="payment-method" value="bank">
                                    <label for="payment_bank">Direct Bank Transfer</label>
                                    <p data-method="bank">Please send a Check to Store name with
                                        Store Street, Store Town, Store State, Store Postcode,
                                        Store Country.</p>
                                </div>

                                <div class="single-method">
                                    <input type="radio" id="payment_cash" name="payment-method" value="cash">
                                    <label for="payment_cash">Cash on Delivery</label>
                                    <p data-method="cash">Please send a Check to Store name with
                                        Store Street, Store Town, Store State, Store Postcode,
                                        Store Country.</p>
                                </div>

                                <div class="single-method">
                                    <input type="radio" id="payment_paypal" name="payment-method" value="paypal">
                                    <label for="payment_paypal">Paypal</label>
                                    <p data-method="paypal">Please send a Check to Store name with
                                        Store Street, Store Town, Store State, Store Postcode,
                                        Store Country.</p>
                                </div>

                                <div class="single-method">
                                    <input type="radio" id="payment_payoneer" name="payment-method" value="payoneer">
                                    <label for="payment_payoneer">Payoneer</label>
                                    <p data-method="payoneer">Please send a Check to Store name
                                        with Store Street, Store Town, Store State, Store Postcode,
                                        Store Country.</p>
                                </div>

                                <div class="single-method">
                                    <input type="checkbox" id="accept_terms">
                                    <label for="accept_terms">I’ve read and accept the terms &amp;
                                        conditions</label>
                                </div>
                            </div>
                            <br/>
                            <div class="cart-submit-btn-group">
                                <div class="single-button w-50">
                                    <button class="rbt-btn rbt-switch-btn rbt-switch-y w-100 btn-border" onclick="window.location.href = '${pageContext.request.contextPath}/homepage'">
                                        <span data-text="Return Homepage">Return Homepage</span>
                                    </button>
                                </div>
                                <div class="single-button w-50">
                                    <button class="rbt-btn btn-gradient rbt-switch-btn rbt-switch-y w-100" onclick="window.location.href = '${pageContext.request.contextPath}/learner/cart/checkout'">
                                        <span data-text="Checkout">Place Order</span>
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

                                    <h4>Product <span>Total</span></h4>

                                    <ul>
                                        <li>Samsome Notebook Pro 5 X 01 <span>$295.00</span></li>
                                        <li>Aquet Drone D 420 X 02 <span>$550.00</span></li>
                                        <li>Play Station X 22 X 01 <span>$295.00</span></li>
                                        <li>Roxxe Headphone Z 75 X 01 <span>$110.00</span></li>
                                    </ul>

                                    <p>Sub Total <span>$1250.00</span></p>
                                    <p>Shipping Fee <span>$00.00</span></p>

                                    <h4 class="mt--30">Grand Total <span>$1250.00</span></h4>

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
    <jsp:include page="/layout/scripts.jsp"/>
</html>