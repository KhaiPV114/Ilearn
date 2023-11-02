<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="userService" scope="request" class="com.onlinelearning.Services.Impl.UserServiceImpl" />

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iLearn | Cart</title>
        <jsp:include page="/layout/links.jsp"/>
    </head>

    <body class="rbt-header-sticky">
        <jsp:include page="/layout/header.jsp"/>

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
                                                <tr id="cart-item-${course.id}">
                                                    <td class="pro-thumbnail">
                                                        <a href="#">
                                                            <img src="${pageContext.request.contextPath}${course.imageUrl}" alt="Product">
                                                        </a>
                                                    </td>
                                                    <td class="pro-title">
                                                        <a href="#">${course.name}</a>
                                                    </td>
                                                    <td class="pro-description text-truncate" style="max-width: 350px; font-size: medium;">
                                                        <span>${course.description}</span>
                                                    </td>
                                                    <td class="pro-information">
                                                        <ul class="rbt-list-style-3">
                                                            <li>
                                                                <i class="feather-edit-2"></i>By ${userService.getUser(course.ownerId).username}</li>
                                                            <li>
                                                                <i class="feather-book"></i>${"8 lessons"}&nbsp;&nbsp;&nbsp;<i class="feather-users"></i>${"3 members"}
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
                                                        <span class="course-price" id="cart-item-${course.id}-price">
                                                            $${course.price}
                                                        </span>
                                                    </td>
                                                    <td class="pro-remove">
                                                        <a href="#" data-bs-toggle="modal" data-bs-target="#confirm-remove-cart-${course.id}">
                                                            <i class="feather-x"></i>
                                                        </a> 
                                                    </td>
                                            <div class="modal fade" id="confirm-remove-cart-${course.id}" tabindex="-1" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="staticBackdropLabel">Confirm to remove</h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            Remove this course from your cart?
                                                        </div>
                                                        <div class="modal-footer">
                                                            <a class="rbt-btn btn-white btn-sm btn-border" href="javascript:void(0);" data-bs-dismiss="modal">Cancel</a>
                                                            <input type="hidden" name="course-id" value="${course.id}" class="course-id">
                                                            <a class="rbt-btn btn-sm" href="javascript:void(0);" data-bs-dismiss="modal" onclick="removeCart(${course.id})">Remove</a>
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
                                                <h4 class="title mb--30">
                                                    Apply Coupon Code To Your Cart
                                                </h4>
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
                                                <p>Sub Total <span id="course-sub-total">$0.00</span></p>
                                                <p>Discount <span id="discount-sub-total">$0.00</span></p>
                                                <h2>Grand Total <span id="grand-total">$0.00</span></h2>
                                            </div>

                                            <div class="cart-submit-btn-group">
                                                <div class="single-button w-50">
                                                    <button class="rbt-btn rbt-switch-btn w-100 btn-border" onclick="window.location.href = '${pageContext.request.contextPath}/homepage'">
                                                        <span data-text="Return Homepage">Return Homepage</span>
                                                    </button>
                                                </div>
                                                <div class="single-button w-50">
                                                    <c:if test="${user!=null}">
                                                        <form action="${pageContext.request.contextPath}/cart/checkout" method="post" id="checkout-cart">
                                                            <input type="hidden" name="data" id="hidden-data-cart">
                                                        </form>
                                                        <button class="rbt-btn btn-gradient hover-icon-reverse w-100" onclick="checkoutCart()">
                                                            <span class="icon-reverse-wrapper">
                                                                <span class="btn-text">Checkout</span>
                                                                <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                                                <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                                            </span>
                                                        </button>
                                                    </c:if>
                                                    <c:if test="${user==null}">
                                                        <button class="rbt-btn btn-gradient rbt-switch-btn rbt-switch-y w-100" onclick="window.location.href = '${pageContext.request.contextPath}/authentication'">
                                                            <span data-text="Login to Checkout">Login to Checkout</span>
                                                        </button>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>

                            <c:if test="${coursesInCart.isEmpty()}">
                                <div class="container">
                                    <div class="row g-5">
                                        <div class="col-lg-12">
                                            <div class="section-title text-center">
                                                <h3>Your cart is empty</h3>
                                                <img src="https://assets.materialup.com/uploads/16e7d0ed-140b-4f86-9b7e-d9d1c04edb2b/preview.png" alt="empty cart" style="width: 40%"/>                                    </div>
                                        </div>
                                    </div>
                                    <div class="row g-5">
                                        <div class="rbt-categories-area bg-color-white">
                                            <div class="col-lg-12">
                                                <div class="section-title text-center">
                                                    <h5>Look likes you have not added anything to your cart. Go ahead and explore top courses</h5>
                                                    <div class="rbt-button-group">
                                                        <a class="rbt-moderbt-btn" href="${pageContext.request.contextPath}/search">
                                                            <span class="moderbt-btn-text">More courses</span>
                                                            <i class="feather-arrow-right"></i>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
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
        document.getElementById('open-cart-side-menu').style.display = 'none';
        document.getElementById('cart-side-menu').style.display = 'none';
    </script>

    <c:if test="${!coursesInCart.isEmpty()}">
        <script>
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
                discountSubTotal.innerHTML = '$' + parseFloat(newDiscount).toFixed(2);
                setCartPrice();
            }
            function displayAppliedCode(couponApplied, rootUlElement) {
                while (rootUlElement.firstChild) {
                    rootUlElement.removeChild(rootUlElement.firstChild);
                }

                for (let item of couponApplied) {
                    let li = document.createElement('li');
                    li.innerHTML = `<i class="feather-check"></i><strong>` + item + `</strong> is apllied`;
                    rootUlElement.appendChild(li);
                }
            }

            function removeCart(courseId) {
                let urlPath = "${pageContext.request.contextPath}/cart/remove";
                let data = "course-id=" + courseId;
                const xhttp = new XMLHttpRequest();
                xhttp.onload = function () {
                    if (xhttp.status === 200) {
                        location.reload();
                    } else {
                        console.log(xhttp.responseText);
                    }
                };
                xhttp.open("POST", urlPath);
                xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhttp.send(data);
            }

            let mapCouponAndCourse = new Map();
            const courseIdsInCart = document.getElementsByClassName('course-id');
            for (let item of courseIdsInCart) {
                mapCouponAndCourse.set(item.value, "");
            }

            //Get coupon code from servlet
            let appliedCoupons = [];
            function getCouponCode() {
                //Get elements
                const couponCodeInserted = document.getElementById('coupon-code');
                const couponCodeList = document.getElementById('coupon-code-list');
                const couponCodeMsg = document.getElementById('coupon-code-msg');

                let urlPath = "${pageContext.request.contextPath}/coupon/get";

                //Check if inserted coupon is applied
                if (appliedCoupons.includes(couponCodeInserted.value)) {
                    couponCodeMsg.style.display = "block";
                    couponCodeMsg.style.color = "red";
                    couponCodeMsg.innerHTML = "You already applied this code";
                    couponCodeInserted.value = "";
                    couponCodeInserted.placeholder = "Coupon Code";
                    return;
                } else {
                    mapCouponAndCourse.set('insertCoupon', couponCodeInserted.value);
                }

                const xhttp = new XMLHttpRequest();

                xhttp.onload = function () {
                    if (xhttp.status === 200) {
                        //Convert Json response to Map
                        mapCouponAndCourse = new Map(Object.entries(JSON.parse(xhttp.responseText)));

                        //If success applied coupon to cart
                        if (mapCouponAndCourse.has('appliedMsg')) {

                            //Display message of success
                            couponCodeMsg.style.display = "block";
                            couponCodeMsg.style.color = "green";
                            couponCodeMsg.innerHTML = mapCouponAndCourse.get('appliedMsg');

                            //Create an array to store applied code
                            let appliedCouponString = mapCouponAndCourse.get('appliedCoupons');
                            appliedCoupons = appliedCouponString.substring(1, appliedCouponString.length - 1).split(', ');

                            //Display all applied coupon
                            displayAppliedCode(appliedCoupons, couponCodeList);

                            //Recalculate total cart price
                            setNewDiscountValue(mapCouponAndCourse.get('discount'));

                        } else {
                            couponCodeMsg.style.display = "block";
                            couponCodeMsg.style.color = "red";
                            couponCodeMsg.innerHTML = mapCouponAndCourse.get('failedMsg');
                        }
                    } else {
                        couponCodeMsg.style.display = "block";
                        couponCodeMsg.style.color = "red";
                        couponCodeMsg.innerHTML = "Unknown Error";
                    }
                    couponCodeInserted.value = "";
                    couponCodeInserted.placeholder = "Coupon Code";
                };

                xhttp.open("POST", urlPath);
                xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
                xhttp.send(JSON.stringify(Array.from(mapCouponAndCourse.entries())));
            }

            function checkoutCart() {
                document.getElementById('hidden-data-cart').value = JSON.stringify(Array.from(mapCouponAndCourse.entries()));
                document.getElementById('checkout-cart').submit();
            }
        </script>
    </c:if>
    <jsp:include page="/layout/scripts.jsp"/>
</html>