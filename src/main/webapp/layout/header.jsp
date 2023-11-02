<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="CategoryService" scope="request" class="com.onlinelearning.Services.Impl.CategoryServiceImpl"/>
<jsp:useBean id="CourseService" scope="request" class="com.onlinelearning.Services.Impl.CourseServiceImpl"/>

<c:set var="allCategories" scope="request" value="${CategoryService.getAllCategories()}" />
<jsp:useBean id="CartService" scope="request" class="com.onlinelearning.Services.Impl.CartServiceImpl" />

<c:set var="coursesInCart" scope="session" value="${CartService.getCourseInCart(pageContext.request, pageContext.response)}" />

<header class="rbt-header rbt-header-10">
    <div class="rbt-sticky-placeholder"></div>
    <div class="rbt-header-wrapper header-space-betwween header-sticky">
        <div class="container-fluid">
            <div class="mainbar-row rbt-navigation-center align-items-center">
                <!-- Left Header -->
                <div class="header-left rbt-header-content">
                    <!-- Logo -->
                    <div class="header-info">
                        <div class="logo">
                            <a href="${pageContext.request.contextPath}/homepage">
                                <img src="${pageContext.request.contextPath}/assets/images/logo/logo.png"
                                     alt="Education Logo Images">
                            </a>
                        </div>
                    </div>
                    <!-- Category Dropdown -->
                    <div class="header-info">
                        <jsp:include page="/layout/header/category-dropdown.jsp" />
                    </div>
                </div>
                <!-- Main Navigation -->
                <div class="rbt-main-navigation d-none d-xl-block">
                    <nav class="mainmenu-nav">
                        <ul class="mainmenu">
                            <!-- Home Dropdown -->
                            <jsp:include page="/layout/header/home-dropdown.jsp" />
                            <!-- Course Dropdown -->
                            <jsp:include page="/layout/header/course-dropdown.jsp" />
                            <!-- Dashboard Dropdown -->
                            <li class="has-dropdown has-menu-child-item">
                                <a href="#">Dashboard
                                    <i class="feather-chevron-down"></i>
                                </a>
                                <ul class="submenu">
                                    <li class="has-dropdown"><a href="#">Instructor Dashboard</a>
                                        <ul class="submenu">
                                            <li><a href="instructor-dashboard.html">Dashboard</a></li>
                                            <li><a href="instructor-profile.html">Profile</a></li>
                                            <li><a href="instructor-enrolled-courses.html">Enrolled
                                                    Courses</a></li>
                                            <li><a href="instructor-wishlist.html">Wishlist</a></li>
                                            <li><a href="instructor-reviews.html">Reviews</a></li>
                                            <li><a href="instructor-my-quiz-attempts.html">My Quiz
                                                    Attempts</a></li>
                                            <li><a href="instructor-order-history.html">Order History</a>
                                            </li>
                                            <li><a href="instructor-course.html">My Course</a></li>
                                            <li><a href="instructor-announcements.html">Announcements</a>
                                            </li>
                                            <li><a href="instructor-quiz-attempts.html">Quiz Attempts</a>
                                            </li>
                                            <li><a href="instructor-assignments.html">Assignments</a></li>
                                            <li><a href="instructor-settings.html">Settings</a></li>
                                        </ul>
                                    </li>
                                    <li class="has-dropdown"><a href="#">Student Dashboard</a>
                                        <ul class="submenu">
                                            <li><a href="student-dashboard.html">Dashboard</a></li>
                                            <li><a href="student-profile.html">Profile</a></li>
                                            <li><a href="student-enrolled-courses.html">Enrolled Courses</a>
                                            </li>
                                            <li><a href="student-wishlist.html">Wishlist</a></li>
                                            <li><a href="student-reviews.html">Reviews</a></li>
                                            <li><a href="student-my-quiz-attempts.html">My Quiz Attempts</a>
                                            </li>
                                            <li><a href="student-order-history.html">Order History</a></li>
                                            <li><a href="student-settings.html">Settings</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                            <!-- Pages Dropdown -->
                            <li class="with-megamenu has-menu-child-item position-static">
                                <a href="#">Pages <i class="feather-chevron-down"></i></a>
                                <!-- Start Mega Menu  -->
                                <div class="rbt-megamenu grid-item-4">
                                    <div class="wrapper">
                                        <div class="row row--15">
                                            <div class="col-lg-12 col-xl-3 col-xxl-3 single-mega-item">
                                                <h3 class="rbt-short-title">Get Started</h3>
                                                <ul class="mega-menu-item">
                                                    <li><a href="about-us-01.html">About Us</a></li>
                                                    <li><a href="about-us-02.html">About Us 02</a></li>
                                                    <li><a href="event-grid.html">Event Grid</a></li>
                                                    <li><a href="event-list.html">Event List</a></li>
                                                    <li><a href="event-sidebar.html">Event Sidebar</a></li>
                                                    <li><a href="event-details.html">Event Details</a></li>
                                                    <li><a href="academy-gallery.html">Academy Gallery</a>
                                                    </li>
                                                    <li><a href="admission-guide.html">Admission Guide</a>
                                                    </li>
                                                </ul>
                                            </div>

                                            <div class="col-lg-12 col-xl-3 col-xxl-3 single-mega-item">
                                                <h3 class="rbt-short-title">Get Started</h3>
                                                <ul class="mega-menu-item">
                                                    <li><a href="profile.html">Profile</a></li>
                                                    <li><a href="contact.html">Contact Us</a></li>
                                                    <li><a href="become-a-teacher.html">Become a Teacher</a>
                                                    </li>
                                                    <li><a href="instructor.html">Instructor</a></li>
                                                    <li><a href="faqs.html">FAQS</a></li>
                                                    <li><a href="privacy-policy.html">Privacy Policy</a>
                                                    </li>
                                                    <li><a href="404.html">404 Page</a></li>
                                                    <li><a href="maintenance.html">Maintenance</a></li>
                                                </ul>
                                            </div>

                                            <div class="col-lg-12 col-xl-3 col-xxl-3 single-mega-item">
                                                <h3 class="rbt-short-title">Shop Pages</h3>
                                                <ul class="mega-menu-item">
                                                    <li><a href="shop.html">Shop <span
                                                                class="rbt-badge-card">Sale
                                                                Anything</span></a></li>
                                                    <li><a href="single-product.html">Single Product</a>
                                                    </li>
                                                    <li><a href="cart.html">Cart Page</a></li>
                                                    <li><a href="checkout.html">Checkout</a></li>
                                                    <li><a href="wishlist.html">Wishlist Page</a></li>
                                                    <li><a href="my-account.html">My Acount</a></li>
                                                    <li><a href="login.html">Login &amp; Register</a></li>
                                                    <li><a href="subscription.html">Subscription</a></li>
                                                </ul>
                                            </div>
                                            <div class="col-lg-12 col-xl-3 col-xxl-3 single-mega-item">
                                                <div class="mega-category-item">
                                                    <!-- Start Single Category  -->
                                                    <div class="nav-category-item">
                                                        <div class="thumbnail">
                                                            <div class="image"><img
                                                                    src="${pageContext.request.contextPath}/assets/images/course/category-2.png"
                                                                    alt="Course images"></div>
                                                            <a href="course-filter-one-toggle.html">
                                                                <span>Online Education</span>
                                                                <i class="feather-chevron-right"></i>
                                                            </a>
                                                        </div>
                                                    </div>
                                                    <!-- End Single Category  -->

                                                    <!-- Start Single Category  -->
                                                    <div class="nav-category-item">
                                                        <div class="thumbnail">
                                                            <div class="image"><img
                                                                    src="${pageContext.request.contextPath}/assets/images/course/category-1.png"
                                                                    alt="Course images"></div>
                                                            <a href="course-filter-one-toggle.html">
                                                                <span>Language Club</span>
                                                                <i class="feather-chevron-right"></i>
                                                            </a>
                                                        </div>
                                                    </div>
                                                    <!-- End Single Category  -->

                                                    <!-- Start Single Category  -->
                                                    <div class="nav-category-item">
                                                        <div class="thumbnail">
                                                            <div class="image"><img
                                                                    src="${pageContext.request.contextPath}/assets/images/course/category-4.png"
                                                                    alt="Course images"></div>
                                                            <a href="course-filter-one-toggle.html">
                                                                <span>University Status</span>
                                                                <i class="feather-chevron-right"></i>
                                                            </a>
                                                        </div>
                                                    </div>
                                                    <!-- End Single Category  -->

                                                    <!-- Start Single Category  -->
                                                    <div class="nav-category-item">
                                                        <div class="thumbnail">
                                                            <a href="course-filter-one-toggle.html">
                                                                <span>Course School</span>
                                                                <i class="feather-chevron-right"></i>
                                                            </a>
                                                        </div>
                                                    </div>
                                                    <!-- End Single Category  -->

                                                    <!-- Start Single Category  -->
                                                    <div class="nav-category-item">
                                                        <div class="thumbnail">
                                                            <div class="image"><img
                                                                    src="${pageContext.request.contextPath}/assets/images/course/category-9.png"
                                                                    alt="Course images"></div>
                                                            <a href="course-filter-one-toggle.html">
                                                                <span>Academy</span>
                                                                <i class="feather-chevron-right"></i>
                                                            </a>
                                                        </div>
                                                    </div>
                                                    <!-- End Single Category  -->

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- End Mega Menu  -->
                            </li>
                            <!-- Elements Dropdown -->
                            <li class="with-megamenu has-menu-child-item position-static">
                                <a href="#">Elements <i class="feather-chevron-down"></i></a>
                                <!-- Start Mega Menu  -->
                                <div class="rbt-megamenu grid-item-3">
                                    <div class="wrapper">
                                        <div class="row row--15 single-dropdown-menu-presentation">
                                            <div class="col-lg-4 col-xxl-4 single-mega-item">
                                                <ul class="mega-menu-item">
                                                    <li><a href="style-guide.html">Style Guide <span
                                                                class="rbt-badge-card">Hot</span></a></li>
                                                    <li><a href="accordion.html">Accordion</a></li>
                                                    <li><a href="advancetab.html">Advance Tab</a></li>
                                                    <li><a href="brand.html">Brand</a></li>
                                                    <li><a href="button.html">Button</a></li>
                                                    <li><a href="badge.html">Badge</a></li>
                                                    <li><a href="card.html">Card</a></li>
                                                    <li><a href="call-to-action.html">Call To Action</a>
                                                    </li>
                                                </ul>
                                            </div>

                                            <div class="col-lg-4 col-xxl-4 single-mega-item">
                                                <ul class="mega-menu-item">
                                                    <li><a href="counterup.html">Counter</a></li>
                                                    <li><a href="category.html">Categories</a></li>
                                                    <li><a href="header.html">Header Style</a></li>
                                                    <li><a href="newsletter.html">Newsletter</a></li>
                                                    <li><a href="team.html">Team</a></li>
                                                    <li><a href="social.html">Social</a></li>
                                                    <li><a href="list-style.html">List Style</a></li>
                                                    <li><a href="gallery.html">Gallery</a></li>
                                                </ul>
                                            </div>

                                            <div class="col-lg-4 col-xxl-4 single-mega-item">
                                                <ul class="mega-menu-item">
                                                    <li><a href="pricing.html">Pricing</a></li>
                                                    <li><a href="progressbar.html">Progressbar</a></li>
                                                    <li><a href="testimonial.html">Testimonial</a></li>
                                                    <li><a href="service.html">Service</a></li>
                                                    <li><a href="split.html">Split Area</a></li>
                                                    <li><a href="search.html">Search Style</a></li>
                                                    <li><a href="instagram.html">Instagram Style</a></li>
                                                    <li><a href="#">&amp; More Coming</a></li>

                                                </ul>
                                            </div>

                                        </div>
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="btn-wrapper">
                                                    <a class="rbt-btn btn-gradient hover-icon-reverse square btn-xl w-100 text-center mt--30 hover-transform-none"
                                                       href="#">
                                                        <span class="icon-reverse-wrapper">
                                                            <span class="btn-text">Visit Histudy
                                                                Template</span>
                                                            <span class="btn-icon"><i
                                                                    class="feather-arrow-right"></i></span>
                                                            <span class="btn-icon"><i
                                                                    class="feather-arrow-right"></i></span>
                                                        </span>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- End Mega Menu  -->
                            </li>
                            <!-- Blog Dropdown -->
                            <jsp:include page="/layout/header/blog-dropdown.jsp" />
                        </ul>
                    </nav>
                </div>
                <!-- Right Header -->
                <div class="header-right">
                    <!-- Navbar Icons -->
                    <ul class="quick-access">
                        <!-- Search -->
                        <li class="access-icon">
                            <a class="search-trigger-active rbt-round-btn" href="#">
                                <i class="feather-search"></i>
                            </a>
                        </li>
                        <!-- Cart -->
                        <li class="access-icon rbt-mini-cart">
                            <a class="rbt-cart-sidenav-activation rbt-round-btn" href="javascript:void(0);" id="open-cart-side-menu">
                                <i class="feather-shopping-cart"></i>
                                <c:if test="${coursesInCart.size()>0}">
                                    <span class="rbt-cart-count" id="cart-side-quantity">${coursesInCart.size()}</span>
                                </c:if>
                            </a>
                        </li>
                        <!-- User Dropdown-->
                        <c:if test="${sessionScope['user']==null}">
                            <li class="account-access rbt-user-wrapper d-none d-xl-block">
                                <a href="${pageContext.request.contextPath}/authentication"><i class="feather-log-in"></i>Login</a>
                            </li>
                        </c:if>
                        <c:forEach var="role" items="${sessionScope.roles}">
                            <c:choose>
                                <c:when test="${role == 'LEARNER'}">
                                    <jsp:include page="/layout/header/learner-dropdown.jsp" />
                                </c:when>
                                <c:when test="${role == 'INSTRUCTOR'}">
                                    <jsp:include page="/layout/header/instructor-dropdown.jsp" />
                                </c:when>
                                <c:when test="${role == 'MANAGER'}">
                                    <jsp:include page="/layout/header/manager-dropdown.jsp" />
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </ul>
                    <div class="rbt-btn-wrapper d-none d-xl-block">
                        <a class="rbt-btn rbt-marquee-btn marquee-auto btn-border-gradient radius-round btn-sm hover-transform-none"
                           href="javascript:void(0);">
                            <span data-text="iLearn">iLearn</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>    
</header>

<!-- USE FOR MOBILE -->
<jsp:include page="/layout/header/mobile-menu-side.jsp" />

<!-- Cart Side -->
<jsp:include page="/layout/header/cart-side.jsp" /> 
