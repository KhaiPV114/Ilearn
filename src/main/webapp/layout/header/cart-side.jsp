<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="rbt-cart-side-menu" id="cart-side-menu">
    <div class="inner-wrapper">
        <div class="inner-top">
            <div class="content">
                <div class="title">
                    <h4 class="title mb--0">Your shopping cart</h4>
                </div>
                <div class="rbt-btn-close" id="btn_sideNavClose">
                    <button class="minicart-close-button rbt-round-btn"><i class="feather-x"></i></button>
                </div>
            </div>
        </div>

        <nav class="side-nav w-100">
            <ul class="rbt-minicart-wrapper">
                <c:forEach var="course" items="${coursesInCart}">
                    <li class="minicart-item" id="cart-side-item-${course.id}">
                        <input class="course-id-cart-side" type="hidden" value="${course.id}"/>
                        <div class="thumbnail">
                            <a href="#">
                                <img src="${pageContext.request.contextPath}${course.imageUrl}" alt="Product Images">
                            </a>
                        </div>
                        <div class="product-content">
                            <h6 class="title">
                                <a href="#" onclick="alert('View ${course.name}')" >${course.name}</a>
                            </h6>
                            <span class="course-price-cart-side" id="cart-side-item-${course.id}-price">$${course.price}</span>
                        </div>
                        <div class="close-btn">
                            <button class="rbt-round-btn" onclick="removeCartSide(${course.id})">
                                <i class="feather-x"></i>
                            </button>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </nav>

        <div class="rbt-minicart-footer">
            <hr class="mb--0">
            <div class="rbt-cart-subttotal" id="total-cart-display">
                <p class="subtotal"><strong>Subtotal:</strong></p>
                <p class="price" id="course-total-cart-side">$0</p>
            </div>
            <hr class="mb--0">
            <div class="rbt-minicart-bottom mt--20">
                <div class="view-cart-btn">
                    <a class="rbt-btn btn-border icon-hover w-100 text-center" href="${pageContext.request.contextPath}/cart">
                        <span class="btn-text">View Cart</span>
                        <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                    </a>
                </div>
                <c:if test="${!coursesInCart.isEmpty()}">
                    <c:if test="${user!=null}">
                        <div class="checkout-btn mt--20">
                            <a class="rbt-btn btn-gradient icon-hover w-100 text-center" href="javascript:void(0);" onclick="checkoutCartside()">
                                <span class="btn-text">Checkout</span>
                                <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                            </a>
                            <form action="${pageContext.request.contextPath}/cart/checkout" method="post" id="checkout-cart-side">
                                <input type="hidden" name="data" id="hidden-data-cart-side">
                            </form>
                        </div>
                    </c:if>
                    <c:if test="${user==null}">
                        <div class="checkout-btn mt--20">
                            <a class="rbt-btn btn-gradient icon-hover w-100 text-center" href="${pageContext.request.contextPath}/authentication">
                                <span class="btn-text">Login to checkout</span>
                                <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                            </a>
                        </div>
                    </c:if>
                </c:if>
                <c:if test="${coursesInCart.isEmpty()}">
                    <div class="checkout-btn mt--20">
                        <a class="rbt-btn btn-gradient icon-hover w-100 text-center" href="javascript:void(0);">
                            <span class="btn-text">Checkout</span>
                            <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                        </a>
                    </div>
                </c:if>
            </div>
        </div>

    </div>
</div>
<a class="close_side_menu" href="javascript:void(0);"></a>

<script>
    calculateCoursesPriceCartSide();
    function calculateCoursesPriceCartSide() {
        const coursePriceCartSide = document.getElementsByClassName("course-price-cart-side");
        const courseTotalCartSide = document.getElementById('course-total-cart-side');
        let total = 0;
        for (let price of coursePriceCartSide) {
            total += parseFloat(price.innerHTML.replace("$", ""));
        }
        courseTotalCartSide.innerHTML = '$' + total.toFixed(2);
    }

    let mapCouponAndCourseCartSide = new Map();
    const courseIdsInCartSide = document.getElementsByClassName('course-id-cart-side');
    for (let item of courseIdsInCartSide) {
        mapCouponAndCourseCartSide.set(item.value, "");
    }

    function removeCartSide(courseId) {
        let urlPath = "${pageContext.request.contextPath}/cart/remove";
        let data = "course-id=" + courseId;
        const xhttp = new XMLHttpRequest();
        xhttp.onload = function () {
            if (xhttp.status === 200) {
                document.getElementById("cart-side-item-" + courseId).style.display = 'none';
                document.getElementById("cart-side-item-" + courseId + "-price").className = '';
                calculateCoursesPriceCartSide();
                let cartSizeQuantity = document.getElementById("cart-side-quantity");
                let newCartSize = parseInt(cartSizeQuantity.innerHTML) - 1;
                if (newCartSize === 0) {
                    cartSizeQuantity.style.display = 'none';
                } else {
                    cartSizeQuantity.innerHTML = newCartSize;
                }
            } else {
                console.log(xhttp.responseText);
            }
        };

        xhttp.open("POST", urlPath);
        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhttp.send(data);
    }

    function checkoutCartside() {
        document.getElementById('hidden-data-cart-side').value = JSON.stringify(Array.from(mapCouponAndCourseCartSide.entries()));
        document.getElementById('checkout-cart-side').submit();
    }
</script>