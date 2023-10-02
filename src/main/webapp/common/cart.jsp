<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="userService" scope="request" class="com.onlinelearning.Services.Impl.UserServiceImpl" />
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
        <jsp:include page="/layout/links.jsp"/>
        <style>
            .pro-description{
                max-width: 500px;
                font-size: smaller;
            }
            .pro-description{
                max-width: 400px;
                font-size: smaller;
            }
        </style>
    </head>

    <body class="rbt-header-sticky">
        <jsp:include page="/layout/header.jsp"/>
        <!-- Breadcrumb -->
        <div class="rbt-breadcrumb-default ptb--100 ptb_md--50 ptb_sm--30 bg-gradient-1">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="breadcrumb-inner text-center">
                            <h2 class="title">Cart</h2>
                            <ul class="page-list">
                                <li class="rbt-breadcrumb-item"><a href="${pageContext.request.contextPath}/homepage">Home</a></li>
                                <li>
                                    <div class="icon-right"><i class="feather-chevron-right"></i>
                                    </div>
                                </li>
                                <li class="rbt-breadcrumb-item active">Cart</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="rbt-cart-area bg-color-white rbt-section-gap" id="content-display">
            <div class="cart_area">
                <div class="container">
                    <div class="row">
                        <div class="col-12">
                            <c:if test="${!coursesInCart.isEmpty()}">
                                <div class="cart-table table-responsive mb--60">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th class="pro-thumbnail">Image</th>
                                                <th class="pro-title">Course</th>
                                                <th class="pro-description">Description</th>
                                                <th class="pro-information">Infomation</th>
                                                <th class="pro-subtotal">Total</th>
                                                <th class="pro-remove">Remove</th>
                                            </tr>
                                        </thead>  

                                        <tbody>
                                            <c:forEach items="${sessionScope['coursesInCart']}" var="course">
                                                <tr>
                                                    <td class="pro-thumbnail">
                                                        <a href="#"><img src="${pageContext.request.contextPath}${course.imageUrl}" alt="Product"></a>
                                                    </td>
                                                    <td class="pro-title">
                                                        <a href="#">${course.name}</a>
                                                    </td>
                                                    <td class="pro-description text-truncate" style="max-width: 350px;">
                                                        <span>${course.description}</span>
                                                    </td>
                                                    <td class="pro-information">
                                                        <ul class="rbt-list-style-3">
                                                            <li>
                                                                <i class="feather-edit-2"></i>By ${userService.getUserFullNameById(course.ownerId)}</li>
                                                            <li>
                                                                <i class="feather-book"></i>${"numOfLesson"}s&nbsp;&nbsp;&nbsp;<i class="feather-users"></i>${"numOfLearner"}
                                                            </li>
                                                            <li>
                                                                <div class="rbt-review">
                                                                    <div class="rating">
                                                                        <i class="fas fa-star"></i>
                                                                        <i class="fas fa-star"></i>
                                                                        <i class="fas fa-star"></i>
                                                                        <i class="fas fa-star"></i>
                                                                        <i class="fas fa-star"></i>
                                                                    </div>
                                                                    <span class="rating-count"> (${"numOfReview"})</span>
                                                                </div>
                                                            </li>
                                                        </ul>
                                                    </td>
                                                    <td class="pro-subtotal">
                                                        <span class="course-price">$${course.price}</span>
                                                    </td>
                                                    <td class="pro-remove">
                                                        <a href="#" data-bs-toggle="modal" data-bs-target="#confirm-remove-cart-${course.id}"><i class="feather-x"></i></a> 

                                                    </td>
                                            <div class="modal fade" id="confirm-remove-cart-${course.id}" tabindex="-1" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="staticBackdropLabel">Remove this course from your cart</h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            Now or never?
                                                        </div>
                                                        <div class="modal-footer">
                                                            <a class="rbt-btn btn-white btn-sm btn-border radius-round-10" href="#" data-bs-dismiss="modal">Cancel</a>
                                                            <form action="remove-cart" method="post" id="remove-cart-${course.id}">
                                                                <input type="hidden" name="course-id" value="${course.id}">
                                                                <a class="rbt-btn btn-sm" href="#" onclick="document.getElementById('remove-cart-${course.id}').submit();">Remove</a>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>

                                <div class="row g-5">
                                    <!-- Discount Coupon -->
                                    <div class="col-lg-6 col-12">
                                        <div class="discount-coupon edu-bg-shade">
                                            <div class="section-title text-start">
                                                <h4 class="title mb--30">Apply Coupon Code To Your Cart</h4>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6 col-12 mb--25">
                                                    <input type="text" placeholder="Coupon Code" id="coupon-code">
                                                </div>
                                                <div class="col-md-6 col-12 mb--25">
                                                    <button class="rbt-btn btn-gradient hover-icon-reverse btn-sm" onclick="getCouponCode()">
                                                        <span class="icon-reverse-wrapper">
                                                            <span class="btn-text">Apply Code</span>
                                                            <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                                            <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                                        </span>
                                                    </button>
                                                </div>
                                            </div>
                                            <p id="coupon-code-msg" style="display: none">
                                            <ul class="plan-offer-list" id="coupon-code-list">
                                            </ul>
                                        </div>
                                    </div>

                                    <!-- Cart Summary -->
                                    <div class="col-lg-5 offset-lg-1 col-12">
                                        <div class="cart-summary">
                                            <div class="cart-summary-wrap">
                                                <div class="section-title text-start">
                                                    <h4 class="title mb--30">Cart Summary</h4>
                                                </div>
                                                <p>Sub Total <span id="course-sub-total">$0</span></p>
                                                <p>Discount <span id="discount-sub-total">$0</span></p>
                                                <h2>Grand Total <span id="grand-total">$0</span></h2>
                                            </div>

                                            <div class="cart-submit-btn-group">
                                                <div class="single-button w-50">
                                                    <button class="rbt-btn rbt-switch-btn rbt-switch-y w-100 btn-border" onclick="window.location.href = '${pageContext.request.contextPath}/homepage'">
                                                        <span data-text="Return Homepage">Return Homepage</span>
                                                    </button>
                                                </div>
                                                <div class="single-button w-50">
                                                    <form action="${pageContext.request.contextPath}/cart/checkout" id="checkout-form" method="post">
                                                        <button class="rbt-btn btn-gradient rbt-switch-btn rbt-switch-y w-100">
                                                            <span data-text="Checkout">Checkout</span>
                                                        </button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${coursesInCart.isEmpty()}">
                                <div class="col-lg-12">
                                    <div class="section-title text-center mb--60">
                                        <h5 class="title">Ohh, your cart is as empty as your wallet, let's fill it up</h5>
                                        <<img src="https://assets.materialup.com/uploads/16e7d0ed-140b-4f86-9b7e-d9d1c04edb2b/preview.png" alt="empty cart" style="width: 30%"/>
                                    </div>
                                </div>
                                <div class="rbt-categories-area bg-color-white">
                                    <div class="container">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="section-title text-center mb--60">
                                                    <h5 class="title">Chưa biết để title là gì cho ok? Đại khái chỗ này sẽ dùng thống kê lượt truy cập của từng loại khoá học, rồi chọn ra 5 thằng để hiển thị</h5>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row g-5">
                                            <!-- Start Category Box Layout  -->
                                            <div class="col-xl-2 col-lg-3 col-md-4 col-sm-6 col-12">
                                                <a href="course-filter-one-toggle.html" class="rbt-cat-box rbt-cat-box-1 variation-4 text-center">
                                                    <div class="inner">
                                                        <div class="icons">
                                                            <i class="theme-gradient feather-layout"></i>
                                                        </div>
                                                        <div class="content">
                                                            <h5 class="title">UI/UX Design Services</h5>
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                            <!-- End Category Box Layout  -->

                                            <!-- Start Category Box Layout  -->
                                            <div class="col-xl-2 col-lg-3 col-md-4 col-sm-6 col-12">
                                                <a href="course-filter-one-toggle.html" class="rbt-cat-box rbt-cat-box-1 variation-4 text-center">
                                                    <div class="inner">
                                                        <div class="icons">
                                                            <i class="theme-gradient feather-dollar-sign"></i>
                                                        </div>
                                                        <div class="content">
                                                            <h5 class="title">Finance &amp; Accounting</h5>
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                            <!-- End Category Box Layout  -->

                                            <!-- Start Category Box Layout  -->
                                            <div class="col-xl-2 col-lg-3 col-md-4 col-sm-6 col-12">
                                                <a href="course-filter-one-toggle.html" class="rbt-cat-box rbt-cat-box-1 variation-4 text-center">
                                                    <div class="inner">
                                                        <div class="icons">
                                                            <i class="theme-gradient feather-user-check"></i>
                                                        </div>
                                                        <div class="content">
                                                            <h5 class="title">Personal Development</h5>
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                            <!-- End Category Box Layout  -->

                                            <!-- Start Category Box Layout  -->
                                            <div class="col-xl-2 col-lg-3 col-md-4 col-sm-6 col-12">
                                                <a href="course-filter-one-toggle.html" class="rbt-cat-box rbt-cat-box-1 variation-4 text-center">
                                                    <div class="inner">
                                                        <div class="icons">
                                                            <i class="theme-gradient feather-cpu"></i>
                                                        </div>
                                                        <div class="content">
                                                            <h5 class="title">IT and Software</h5>
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                            <!-- End Category Box Layout  -->

                                            <!-- Start Category Box Layout  -->
                                            <div class="col-xl-2 col-lg-3 col-md-4 col-sm-6 col-12">
                                                <a href="course-filter-one-toggle.html" class="rbt-cat-box rbt-cat-box-1 variation-4 text-center">
                                                    <div class="inner">
                                                        <div class="icons">
                                                            <i class="theme-gradient feather-shopping-bag"></i>
                                                        </div>
                                                        <div class="content">
                                                            <h5 class="title">Sales Marketing</h5>
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                            <!-- End Category Box Layout  -->

                                            <!-- Start Category Box Layout  -->
                                            <div class="col-xl-2 col-lg-3 col-md-4 col-sm-6 col-12">
                                                <a href="course-filter-one-toggle.html" class="rbt-cat-box rbt-cat-box-1 variation-4 text-center">
                                                    <div class="inner">
                                                        <div class="icons">
                                                            <i class="theme-gradient feather-feather"></i>
                                                        </div>
                                                        <div class="content">
                                                            <h5 class="title">Art &amp; Humanities</h5>
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                            <!-- End Category Box Layout  -->
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="rbt-button-group">
                                    <a class="rbt-moderbt-btn" href="#">
                                        <span class="moderbt-btn-text">More courses</span>
                                        <i class="feather-arrow-right"></i>
                                    </a>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/layout/footer.jsp"/>
    </body>
    <script>
        //Auto scroll to the content of page with delay
        scrollToElementWithTime();
        function scrollToElementWithTime() {
            const targetElement = document.getElementById('content-display');
            const scrollDelay = 500;
            const targetPosition = targetElement.offsetTop;
            const startPosition = window.pageYOffset;
            const distance = targetPosition - startPosition;
            let startTime = null;
            function scrollStep(timestamp) {
                if (!startTime) {
                    startTime = timestamp;
                }
                const progress = timestamp - startTime;
                const percentage = Math.min(progress / scrollDelay, 1);
                window.scrollTo(0, startPosition + distance * percentage);
                if (progress < scrollDelay) {
                    window.requestAnimationFrame(scrollStep);
                }
            }
            window.requestAnimationFrame(scrollStep);
        }

        //Set all value of cart base on course price and coupon value
        setCartPrice();
        function setCartPrice() {
            const coursesPrice = document.getElementsByClassName("course-price");
            const courseSubTotal = document.getElementById('course-sub-total');
            const discountSubTotal = parseFloat(document.getElementById('discount-sub-total').innerHTML.replace("$", ""));
            const grandTotal = document.getElementById('grand-total');

            let totalCoursesPrice = 0;
            for (let price of coursesPrice) {
                totalCoursesPrice += parseFloat(price.innerHTML.replace("$", ""));
            }

            courseSubTotal.innerHTML = '$' + totalCoursesPrice.toFixed(2);
            grandTotal.innerHTML = '$' + (totalCoursesPrice - discountSubTotal).toFixed(2);
        }

        function setNewDiscountValue(newDiscount) {
            const discountSubTotal = document.getElementById('discount-sub-total');
            const oldDiscount = parseFloat(discountSubTotal.innerHTML.replace("$", ""));
            discountSubTotal.innerHTML = '$' + (oldDiscount + parseFloat(newDiscount)).toFixed(2);
        }


        //Get coupon code from servlet
        let numOfCouponCode = 0;
        function getCouponCode() {
            const couponCodeValue = document.getElementById('coupon-code');
            const couponCodeList = document.getElementById('coupon-code-list');
            const couponCodeItem = document.getElementsByClassName('coupon-code-item');
            const couponCodeMsg = document.getElementById('coupon-code-msg');
            let urlPath = "${pageContext.request.contextPath}/get-coupon-code?coupon-code=" + encodeURIComponent(couponCodeValue.value);

            const checkoutForm = document.getElementById('checkout-form');

            const xhttp = new XMLHttpRequest();

            xhttp.onload = function () {
                if (xhttp.status === 200) {
                    const resStrArr = xhttp.responseText.split(" ");
                    const key = resStrArr[0];
                    const value = resStrArr[1];
                    let isAppliedCode = false;
                    for (let item of couponCodeItem) {
                        if (item.id === key) {
                            couponCodeMsg.style.display = "block";
                            couponCodeMsg.style.color = "red";
                            couponCodeMsg.innerHTML = "You already add this coupon code!";
                            isAppliedCode = true;
                            break;
                        }
                    }
                    if (!isAppliedCode) {
                        let li = document.createElement('li');
                        li.classList.add('coupon-code-item');
                        li.id = key;
                        li.innerHTML = `<i class="feather-check"></i>Applied <strong>` + key + `</strong> to cart`;
                        couponCodeList.appendChild(li);
                        setNewDiscountValue(value);
                        setCartPrice();
                        //Add hidden input to submit checkout
                        let input = document.createElement('input');
                        input.type = "hidden";
                        input.name = "cId" + numOfCouponCode;
                        input.value = key;
                        checkoutForm.appendChild(input);
                        numOfCouponCode++;
                    }
                } else {
                    couponCodeMsg.style.display = "block";
                    couponCodeMsg.style.color = "red";
                    couponCodeMsg.innerHTML = xhttp.responseText;
                }
                couponCodeValue.value = "";
                couponCodeValue.placeholder = "Coupon Code";
            }

            xhttp.open("GET", urlPath);
            xhttp.send();
        }

        function checkout() {
            const xhttp = new XMLHttpRequest();
            let url = "${pageContext.request.contextPath}/cart/checkout";
            xhttp.open("POST", url);
            xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            const couponCodeItem = document.getElementsByClassName('coupon-code-item');
            let data = "";
            let index = 0;
            for (let item of couponCodeItem) {
                data += "cId" + index + "=" + item.id + "&";
                index++;
            }
            xhttp.send(data.substring(0, data.length - 1));
        }
    </script>
    <jsp:include page="/layout/scripts.jsp"/>
</html>