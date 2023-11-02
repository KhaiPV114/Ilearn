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
        <style>
        </style>
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
                                <li class="rbt-breadcrumb-item"><a href="${pageContext.request.contextPath}/homepage">Home</a></li>
                                <li><div class="icon-right"><i class="feather-chevron-right"></i></div></li>
                                <li class="rbt-breadcrumb-item"><a href="${pageContext.request.contextPath}/cart">Cart</a></li>
                                <li><div class="icon-right"><i class="feather-chevron-right"></i></div></li>
                                <li class="rbt-breadcrumb-item active">Checkout</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="checkout_area bg-color-white rbt-section-gap" id="content">
            <div class="container">
                <div class="row g-5 checkout-form">

                    <!-- Left side -->
                    <div class="col-lg-7">

                        <!-- Payment Method -->
                        <div class="col-12 mb--60">
                            <h4 class="checkout-title">Payment Method</h4>

                            <div class="checkout-payment-method">

                                <form action="${pageContext.request.contextPath}/cart/checkout/payment" method="post" id="payment-checkout-form">
                                    <input type="hidden" name="order-id" value="${order.id}">
                                    <input type="hidden" name="no-need-payment" value="${noNeedPayment}">
                                    <input type="hidden" name="amount" value="${grandTotal}">
                                    <div class="single-method">
                                        <input type="radio" id="payment_domestic" value="VNBANK" name="payment-method" class="payment-method" ${noNeedPayment?'disabled':''} onclick="disableErrMsg()">
                                        <label for="payment_domestic">ATM card/Domestic account</label>
                                        <p data-method="VNBANK">
                                            <img src="https://sandbox.vnpayment.vn/apis/assets/images/partner_atm_acc.png" style="width: 100%;"/>
                                        </p>
                                    </div>
                                    <div class="single-method">
                                        <input type="radio" id="payment_international" value="INTCARD" name="payment-method" class="payment-method" ${noNeedPayment?'disabled':''} onclick="disableErrMsg()">
                                        <label for="payment_international">International card</label>
                                        <p data-method="INTCARD">
                                            <img src="https://imageupload.io/ib/LpFcMT0SkLygXoK_1698822819.png" style="width: 100%;"/>
                                        </p>
                                    </div>

                                    <div class="single-method">
                                        <input type="radio" id="payment_qr" value="VNPAYQR" name="payment-method" class="payment-method" onclick="getPaymentQR(this.value)" ${noNeedPayment?'disabled':''} disabled>
                                        <label for="payment_qr">Bank QR</label>
                                        <p data-method="VNPAYQR" class="container text-center" id="qr-display">
                                            <img src="" style="max-height: 400px;" id="qr-image"/>
                                            <span id="qr-loading">
                                                <i class="fas fa-spinner fa-spin"></i>
                                            </span>
                                            <br/>
                                            <span id="qr-message" style="display: none">
                                                Thank you for choosing us! iLearn with luv <3
                                            </span>
                                        </p>
                                    </div>
                                </form>
                            </div>
                            <br/>
                            <p id="payment-message" class="text-center"></p>
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
                                                    Are you want to cancel this order?
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
                                    <button class="rbt-btn btn-gradient rbt-switch-btn rbt-switch-y w-100" onclick="placeOrder()">
                                        <span data-text="Place Order">Place Order</span>
                                    </button>
                                </div>

                            </div>
                        </div>
                    </div>

                    <!-- Right side -->
                    <div class="col-lg-5">
                        <div class="row pl--50 pl_md--0 pl_sm--0">

                            <!-- Cart Total -->
                            <div class="col-12 mb--60">
                                <h4 class="checkout-title">Cart Total</h4>

                                <div class="checkout-cart-total">
                                    <h4>Courses <span>Total</span></h4>

                                    <!--Display message of coupon applied error-->
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

                                    <h4 class="mt--30">
                                        Grand Total <span class="number">$${grandTotal}</span>
                                    </h4>
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
    <script src="https://pay.vnpay.vn/lib/vnpay/vnpay.min.js"></script>
    <script>
        
        document.getElementById("content").scrollIntoView({behavior: 'instant'});
        location.hash = '#content';
        
        //Diable cart side
        document.getElementById('open-cart-side-menu').style.display = 'none';
        document.getElementById('cart-side-menu').style.display = 'none';

        //Format all number display to 00.00
        const number = document.getElementsByClassName('number');
        for (var item of number) {
            let value = parseFloat(item.innerHTML.trim().replace("$", ""));
            item.innerHTML = '$' + value.toFixed(2);
        }

        document.getElementById('qr-image').addEventListener('load', () => {
            document.getElementById('qr-loading').style.display = 'none';
            document.getElementById('qr-message').style.display = 'inline';
        });

        function disableErrMsg() {
            let message = document.getElementById('payment-message');
            message.style.display = 'none';
        };

        //Check if user have choose payment method
        function placeOrder() {
            let radios = document.getElementsByClassName("payment-method");
            let checked = false;
            for (let item of radios) {
                if (item.checked) {
                    checked = true;
                    break;
                }
            }
            if (checked || ${noNeedPayment?'true':'false'}) {
                let urlPath = $("#payment-checkout-form").attr("action");
                $.ajax({
                    type: "POST",
                    url: urlPath,
                    data: $("#payment-checkout-form").serialize(),
                    contentType: "application/x-www-form-urlencoded",
                    success: function (res) {
                        let response = JSON.parse(res);
                        if (response.code === '00') {
                            if (window.vnpay) {
                                vnpay.open({width: 768, height: 600, url: response.data});
                            } else {
                                location.href = response.data;
                            }
                        } else {
                            alert(response.Message);
                        }
                    }
                });
            } else {
                let message = document.getElementById('payment-message');
                message.innerHTML = 'You need to choose a payment method to process the order!';
                message.style.color = 'red';
            }
        }

        //Get image QR
        function getPaymentQR(paymentMethod) {
            disableErrMsg();
            if (paymentMethod === 'bank') {
                let urlPath = "${pageContext.request.contextPath}/cart/checkout/payment";
                const xhttp = new XMLHttpRequest();
                xhttp.onload = function () {
                    if (xhttp.status === 200) {
                        let qrBanking = document.getElementById('qr-image');
                        qrBanking.src = xhttp.responseText;
                    } else {
                        document.getElementById('qr-loading').style.display = 'none';
                        document.getElementById('qr-message').innerHTML = xhttp.responseText;
                        document.getElementById('qr-message').style.color = 'red';
                        document.getElementById('qr-message').style.display = 'inline';
                    }
                };
                xhttp.open("POST", urlPath);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send("order-id=${order.id}&grandTotal=${grandTotal}");
            }
        }
    </script>
</html>