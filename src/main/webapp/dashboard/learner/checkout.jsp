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
                                <li>
                                    <div class="icon-right"><i class="feather-chevron-right"></i></div>
                                </li>
                                <li class="rbt-breadcrumb-item active">Checkout</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="checkout_area bg-color-white rbt-section-gap">
            <div class="container">
                <div class="row g-5 checkout-form">

                    <div class="col-lg-7">
                        <div class="checkout-content-wrapper">

                            <!-- Billing Address -->
                            <div id="billing-form">
                                <h4 class="checkout-title">Billing Address</h4>

                                <div class="row">

                                    <div class="col-md-6 col-12 mb--20">
                                        <label>First Name*</label>
                                        <input type="text" placeholder="First Name">
                                    </div>

                                    <div class="col-md-6 col-12 mb--20">
                                        <label>Last Name*</label>
                                        <input type="text" placeholder="Last Name">
                                    </div>

                                    <div class="col-md-6 col-12 mb--20">
                                        <label>Email Address*</label>
                                        <input type="email" placeholder="Email Address">
                                    </div>

                                    <div class="col-md-6 col-12 mb--20">
                                        <label>Phone no*</label>
                                        <input type="text" placeholder="Phone number">
                                    </div>

                                    <div class="col-12 mb--20">
                                        <label>Company Name</label>
                                        <input type="text" placeholder="Company Name">
                                    </div>

                                    <div class="col-12 mb--20">
                                        <label>Address*</label>
                                        <input type="text" placeholder="Address line 1">
                                        <input type="text" placeholder="Address line 2">
                                    </div>

                                    <div class="col-md-6 col-12 mb--20">
                                        <label>Country*</label>
                                        <div class="rbt-modern-select bg-transparent height-45">
                                            <div class="dropdown bootstrap-select w-100"><select class="w-100">
                                                    <option>Dhaka</option>
                                                    <option>Barisal</option>
                                                    <option>Khulna</option>
                                                    <option>Comilla</option>
                                                    <option>Chittagong</option>
                                                </select><button type="button" tabindex="-1" class="btn dropdown-toggle btn-light" data-bs-toggle="dropdown" role="combobox" aria-owns="bs-select-1" aria-haspopup="listbox" aria-expanded="false" title="Dhaka"><div class="filter-option"><div class="filter-option-inner"><div class="filter-option-inner-inner">Dhaka</div></div> </div></button><div class="dropdown-menu "><div class="inner show" role="listbox" id="bs-select-1" tabindex="-1"><ul class="dropdown-menu inner show" role="presentation"></ul></div></div></div>
                                        </div>
                                    </div>

                                    <div class="col-md-6 col-12 mb--20">
                                        <label>Town/City*</label>
                                        <input type="text" placeholder="Town/City">
                                    </div>

                                    <div class="col-md-6 col-12 mb--20">
                                        <label>State*</label>
                                        <input type="text" placeholder="State">
                                    </div>

                                    <div class="col-md-6 col-12 mb--20">
                                        <label>Zip Code*</label>
                                        <input type="text" placeholder="Zip Code">
                                    </div>

                                    <div class="col-12 mb--20">
                                        <div class="check-box">
                                            <input type="checkbox" id="create_account">
                                            <label for="create_account">Create an Acount?</label>
                                        </div>
                                        <div class="check-box">
                                            <input type="checkbox" id="shiping_address" data-shipping="">
                                            <label for="shiping_address">Ship to Different Address</label>
                                        </div>
                                    </div>

                                </div>

                            </div>

                            <!-- Shipping Address -->
                            <div id="shipping-form" class="mt--20">
                                <h4 class="checkout-title">Shipping Address</h4>
                                <div class="row g-5">
                                    <div class="col-md-6 col-12">
                                        <label>First Name*</label>
                                        <input type="text" placeholder="First Name">
                                    </div>

                                    <div class="col-md-6 col-12">
                                        <label>Last Name*</label>
                                        <input type="text" placeholder="Last Name">
                                    </div>

                                    <div class="col-md-6 col-12">
                                        <label>Email Address*</label>
                                        <input type="email" placeholder="Email Address">
                                    </div>

                                    <div class="col-md-6 col-12">
                                        <label>Phone no*</label>
                                        <input type="text" placeholder="Phone number">
                                    </div>

                                    <div class="col-12">
                                        <label>Company Name</label>
                                        <input type="text" placeholder="Company Name">
                                    </div>

                                    <div class="col-12">
                                        <label>Address*</label>
                                        <input type="text" placeholder="Address line 1">
                                        <input type="text" placeholder="Address line 2">
                                    </div>

                                    <div class="col-md-6 col-12">
                                        <label>Country*</label>
                                        <div class="rbt-modern-select bg-transparent height-45">
                                            <div class="dropdown bootstrap-select w-100"><select class="w-100">
                                                    <option>Dhaka</option>
                                                    <option>Barisal</option>
                                                    <option>Khulna</option>
                                                    <option>Comilla</option>
                                                    <option>Chittagong</option>
                                                </select><button type="button" tabindex="-1" class="btn dropdown-toggle btn-light" data-bs-toggle="dropdown" role="combobox" aria-owns="bs-select-2" aria-haspopup="listbox" aria-expanded="false" title="Dhaka"><div class="filter-option"><div class="filter-option-inner"><div class="filter-option-inner-inner">Dhaka</div></div> </div></button><div class="dropdown-menu "><div class="inner show" role="listbox" id="bs-select-2" tabindex="-1"><ul class="dropdown-menu inner show" role="presentation"></ul></div></div></div>
                                        </div>
                                    </div>

                                    <div class="col-md-6 col-12">
                                        <label>Town/City*</label>
                                        <input type="text" placeholder="Town/City">
                                    </div>

                                    <div class="col-md-6 col-12">
                                        <label>State*</label>
                                        <input type="text" placeholder="State">
                                    </div>

                                    <div class="col-md-6 col-12">
                                        <label>Zip Code*</label>
                                        <input type="text" placeholder="Zip Code">
                                    </div>
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
                                <div class="plceholder-button mt--50">
                                    <button class="rbt-btn btn-gradient hover-icon-reverse">
                                        <span class="icon-reverse-wrapper">
                                            <span class="btn-text">Place order</span>
                                            <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                            <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                        </span>
                                    </button>
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