<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Start Cart Side Menu -->
<div class="rbt-cart-side-menu">
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
                <li class="minicart-item" id="cart-side-item">
                        <div class="thumbnail">
                            <a href="#">
                                <img src="${pageContext.request.contextPath}/assets/images/course/course-online-01.jpg" alt="Product Images">
                            </a>
                        </div>
                        <div class="product-content">
                            <h6 class="title"><a href="single-product.html">Prototype</a></h6>

                            <span class="course-price-cart-side">$199.89</span>

                        </div>
                        <div class="close-btn">
                            <button class="rbt-round-btn" onclick=""><i class="feather-x"></i></button>
                        </div>
                    </li>
                <c:forEach var="course" items="${coursesInCart}">
                    <li class="minicart-item" id="cart-side-item">
                        <div class="thumbnail">
                            <a href="#">
                                <img src="${pageContext.request.contextPath}${course.imageUrl}" alt="Product Images">
                            </a>
                        </div>
                        <div class="product-content">
                            <h6 class="title"><a href="single-product.html">${course.name}</a></h6>

                            <span class="course-price-cart-side">$${course.price}</span>

                        </div>
                        <div class="close-btn">
                            <button class="rbt-round-btn" onclick=""><i class="feather-x"></i></button>
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
                <div class="checkout-btn mt--20">
                    <a class="rbt-btn btn-gradient icon-hover w-100 text-center" href="${pageContext.request.contextPath}/checkout">
                        <span class="btn-text">Checkout</span>
                        <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                    </a>
                </div>
            </div>
        </div>

    </div>
</div>
<!-- End Cart Side Menu -->
<a class="close_side_menu" href="javascript:void(0);"></a>