<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
        <jsp:include page="/layout/links.jsp"/>
    </head>

    <body>
        <jsp:include page="/layout/header.jsp"/>
        <!-- Breadcrumb -->
        <div class="rbt-breadcrumb-default ptb--100 ptb_md--50 ptb_sm--30 bg-gradient-1">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="breadcrumb-inner text-center">
                            <h2 class="title">Cart</h2>
                            <ul class="page-list">
                                <li class="rbt-breadcrumb-item"><a href="index.html">Home</a></li>
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



        <div class="rbt-cart-area bg-color-white rbt-section-gap">
            <div class="cart_area">
                <div class="container">
                    <div class="row">
                        <div class="col-12">
                            <form action="#">
                                <!-- Cart Table -->
                                <div class="cart-table table-responsive mb--60">
                                    <table class="table">
                                        ${carts}
                                        <thead>
                                            <tr>
                                                <th class="pro-thumbnail">Image</th>
                                                <th class="pro-title">Product</th>
                                                <th class="pro-price">Price</th>
                                                <th class="pro-subtotal">Total</th>
                                                <th class="pro-remove">Remove</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td class="pro-thumbnail"><a href="#"><img src="${pageContext.request.contextPath}/assets/images/product/1.jpg" alt="Product"></a></td>
                                                <td class="pro-title"><a href="#">${"Tên khoá học Tên khoá học Tên khoá học"}</a></td>
                                                <td class="pro-price"><span>$${"00.00"}</span></td>
                                                <td class="pro-subtotal"><span>$${"00.00"}</span></td>
                                                <td class="pro-remove">
                                                    <a href="#"><i class="feather-x"></i></a>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </form>

                            <div class="row g-5">

                                <div class="col-lg-6 col-12">

                                    <!-- Discount Coupon -->
                                    <div class="discount-coupon edu-bg-shade">
                                        <div class="section-title text-start">
                                            <h4 class="title mb--30">Discount Coupon Code</h4>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6 col-12 mb--25">
                                                <input type="text" placeholder="Coupon Code" id="coupon-code">
                                            </div>
                                            <div class="col-md-6 col-12 mb--25">
                                                <button onclick="applyCouponCode()" class="rbt-btn btn-gradient hover-icon-reverse btn-sm">
                                                    <span class="icon-reverse-wrapper">
                                                        <span class="btn-text">Apply Code</span>
                                                        <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                                        <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                                    </span>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- Cart Summary -->

                                <div class="col-lg-5 offset-lg-1 col-12">
                                    <div class="cart-summary">
                                        <div class="cart-summary-wrap">
                                            <div class="section-title text-start">
                                                <h4 class="title mb--30">Cart Summary</h4>
                                            </div>
                                            <p>Sub Total <span>$1250.00</span></p>
                                            <p>Discount <span>$5.00</span></p>
                                            <!--<p>Shipping Cost <span>$00.00</span></p>-->
                                            <h2>Grand Total <span>$1250.00</span></h2>
                                        </div>

                                        <div class="cart-submit-btn-group">
                                            <div class="single-button w-50">
                                                <button class="rbt-btn btn-gradient rbt-switch-btn rbt-switch-y w-100">
                                                    <span data-text="Checkout">Checkout</span>
                                                </button>
                                            </div>
                                            <div class="single-button w-50">
                                                <button class="rbt-btn rbt-switch-btn rbt-switch-y w-100 btn-border">
                                                    <span data-text="Update Cart">Update Cart</span>
                                                </button>
                                            </div>

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
        <jsp:include page="/layout/scripts.jsp"/>
    </body>
</html>
