<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:set var="coursesInCart" value="${sessionScope['coursesInCart']}"/>

<div>
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
                                <a href="index.jsp">
                                    <img src="${pageContext.request.contextPath}/assets/images/logo/logo.png" alt="Education Logo Images">
                                </a>
                            </div>
                        </div>
                        <!-- Category Dropdown -->
                        <div class="header-info">
                            <jsp:include page="/layout/header/category-dropdown.jsp"/>
                        </div>
                    </div>    
                    <!-- Main Navigation -->
                    <div class="rbt-main-navigation d-none d-xl-block">
                        <nav class="mainmenu-nav">
                            <ul class="mainmenu">
                                <!-- Home Dropdown -->
                                <li class="with-megamenu has-menu-child-item position-static menu-item-open">
                                    <a href="#">Home <i class="feather-chevron-down"></i></a>
                                    <!-- Start Mega Menu  -->
                                    <div class="rbt-megamenu menu-skin-dark">
                                        <div class="wrapper">
                                            <div class="row row--15 home-plesentation-wrapper single-dropdown-menu-presentation">

                                                <!-- Start Single Demo  -->
                                                <div class="col-lg-12 col-xl-2 col-xxl-2 col-md-12 col-sm-12 col-12 single-mega-item">
                                                    <div class="demo-single">
                                                        <div class="inner">
                                                            <div class="thumbnail">
                                                                <a href="01-main-demo.html"><img src="${pageContext.request.contextPath}/assets/images/splash/demo/h1.jpg" alt="Demo Images"></a>
                                                            </div>
                                                            <div class="content">
                                                                <h4 class="title"><a href="01-main-demo.html">Home Demo <span class="btn-icon"><i class="feather-arrow-right"></i></span></a></h4>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- End Single Demo  -->

                                                <!-- Start Single Demo  -->
                                                <div class="col-lg-12 col-xl-2 col-xxl-2 col-md-12 col-sm-12 col-12 single-mega-item">
                                                    <div class="demo-single">
                                                        <div class="inner">
                                                            <div class="thumbnail">
                                                                <a href="12-marketplace.html"><img src="${pageContext.request.contextPath}/assets/images/splash/demo/h12.jpg" alt="Demo Images"></a>
                                                            </div>
                                                            <div class="content">
                                                                <h4 class="title"><a href="12-marketplace.html">Marketplace <span class="btn-icon"><i class="feather-arrow-right"></i></span></a></h4>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- End Single Demo  -->
                                                <!-- Start Single Demo  -->
                                                <div class="col-lg-12 col-xl-2 col-xxl-2 col-md-12 col-sm-12 col-12 single-mega-item">
                                                    <div class="demo-single">
                                                        <div class="inner">
                                                            <div class="thumbnail">
                                                                <a href="04-kindergarten.html"><img src="${pageContext.request.contextPath}/assets/images/splash/demo/h4.jpg" alt="Demo Images"></a>
                                                            </div>
                                                            <div class="content">
                                                                <h4 class="title"><a href="04-kindergarten.html">kindergarten <span class="btn-icon"><i class="feather-arrow-right"></i></span></a></h4>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- End Single Demo  -->
                                                <!-- Start Single Demo  -->
                                                <div class="col-lg-12 col-xl-2 col-xxl-2 col-md-12 col-sm-12 col-12 single-mega-item">
                                                    <div class="demo-single">
                                                        <div class="inner">
                                                            <div class="thumbnail">
                                                                <a href="13-university-classic.html"><img src="${pageContext.request.contextPath}/assets/images/splash/demo/h13.jpg" alt="Demo Images"></a>
                                                            </div>
                                                            <div class="content">
                                                                <h4 class="title"><a href="13-university-classic.html">University Classic <span class="btn-icon"><i class="feather-arrow-right"></i></span></a></h4>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- End Single Demo  -->

                                                <!-- Start Single Demo  -->
                                                <div class="col-lg-12 col-xl-2 col-xxl-2 col-md-12 col-sm-12 col-12 single-mega-item">
                                                    <div class="demo-single">
                                                        <div class="inner">
                                                            <div class="thumbnail">
                                                                <a href="14-home-elegant.html"><img src="${pageContext.request.contextPath}/assets/images/splash/demo/h14.jpg" alt="Demo Images"></a>
                                                            </div>
                                                            <div class="content">
                                                                <h4 class="title"><a href="14-home-elegant.html">Home Elegant <span class="btn-icon"><i class="feather-arrow-right"></i></span></a></h4>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- End Single Demo  -->

                                                <!-- Start Single Demo  -->
                                                <div class="col-lg-12 col-xl-2 col-xxl-2 col-md-12 col-sm-12 col-12 single-mega-item">
                                                    <div class="demo-single">
                                                        <div class="inner">
                                                            <div class="thumbnail">
                                                                <a href="09-gym-coaching.html"><img src="${pageContext.request.contextPath}/assets/images/splash/demo/h9.jpg" alt="Demo Images"></a>
                                                            </div>
                                                            <div class="content">
                                                                <h4 class="title"><a href="09-gym-coaching.html">Gym Coaching <span class="btn-icon"><i class="feather-arrow-right"></i></span></a></h4>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- End Single Demo  -->

                                                <!-- Start Single Demo  -->
                                                <div class="col-lg-12 col-xl-2 col-xxl-2 col-md-12 col-sm-12 col-12 single-mega-item">
                                                    <div class="demo-single">
                                                        <div class="inner">
                                                            <div class="thumbnail">
                                                                <a href="03-online-school.html" class="active"><img src="${pageContext.request.contextPath}/assets/images/splash/demo/h3.jpg" alt="Demo Images"></a>
                                                            </div>
                                                            <div class="content">
                                                                <h4 class="title"><a href="03-online-school.html" class="active">Online School <span class="btn-icon"><i class="feather-arrow-right"></i></span></a></h4>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- End Single Demo  -->

                                                <!-- Start Single Demo  -->
                                                <div class="col-lg-12 col-xl-2 col-xxl-2 col-md-12 col-sm-12 col-12 single-mega-item">
                                                    <div class="demo-single">
                                                        <div class="inner">
                                                            <div class="thumbnail">
                                                                <a href="06-university-status.html"><img src="${pageContext.request.contextPath}/assets/images/splash/demo/h6.jpg" alt="Demo Images"></a>
                                                            </div>
                                                            <div class="content">
                                                                <h4 class="title"><a href="06-university-status.html">University Status <span class="btn-icon"><i class="feather-arrow-right"></i></span></a></h4>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- End Single Demo  -->

                                                <!-- Start Single Demo  -->
                                                <div class="col-lg-12 col-xl-2 col-xxl-2 col-md-12 col-sm-12 col-12 single-mega-item">
                                                    <div class="demo-single">
                                                        <div class="inner">
                                                            <div class="thumbnail">
                                                                <a href="15-home-technology.html"><img src="${pageContext.request.contextPath}/assets/images/splash/demo/h15.jpg" alt="Demo Images"></a>
                                                            </div>
                                                            <div class="content">
                                                                <h4 class="title"><a href="15-home-technology.html">Home Technology <span class="btn-icon"><i class="feather-arrow-right"></i></span></a></h4>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- End Single Demo  -->

                                                <!-- Start Single Demo  -->
                                                <div class="col-lg-12 col-xl-2 col-xxl-2 col-md-12 col-sm-12 col-12 single-mega-item">
                                                    <div class="demo-single">
                                                        <div class="inner">
                                                            <div class="thumbnail">
                                                                <a href="07-instructor-portfolio.html"><img src="${pageContext.request.contextPath}/assets/images/splash/demo/h7.jpg" alt="Demo Images"></a>
                                                            </div>
                                                            <div class="content">
                                                                <h4 class="title"><a href="07-instructor-portfolio.html">Instructor Portfolio <span class="btn-icon"><i class="feather-arrow-right"></i></span></a></h4>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- End Single Demo  -->

                                                <!-- Start Single Demo  -->
                                                <div class="col-lg-12 col-xl-2 col-xxl-2 col-md-12 col-sm-12 col-12 single-mega-item">
                                                    <div class="demo-single">
                                                        <div class="inner">
                                                            <div class="thumbnail">
                                                                <a href="08-language-academy.html"><img src="${pageContext.request.contextPath}/assets/images/splash/demo/h8.jpg" alt="Demo Images"></a>
                                                            </div>
                                                            <div class="content">
                                                                <h4 class="title"><a href="08-language-academy.html">Language Academy <span class="btn-icon"><i class="feather-arrow-right"></i></span></a></h4>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- End Single Demo  -->

                                                <!-- Start Single Demo  -->
                                                <div class="col-lg-12 col-xl-2 col-xxl-2 col-md-12 col-sm-12 col-12 single-mega-item">
                                                    <div class="demo-single">
                                                        <div class="inner">
                                                            <div class="thumbnail">
                                                                <a href="11-single-course.html"><img src="${pageContext.request.contextPath}/assets/images/splash/demo/h11.jpg" alt="Demo Images"></a>
                                                            </div>
                                                            <div class="content">
                                                                <h4 class="title"><a href="11-single-course.html">Single Course <span class="btn-icon"><i class="feather-arrow-right"></i></span></a></h4>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- End Single Demo  -->

                                                <!-- Start Single Demo  -->
                                                <div class="col-lg-12 col-xl-2 col-xxl-2 col-md-12 col-sm-12 col-12 single-mega-item">
                                                    <div class="demo-single">
                                                        <div class="inner">
                                                            <div class="thumbnail">
                                                                <a href="10-online-course.html"><img src="${pageContext.request.contextPath}/assets/images/splash/demo/h10.jpg" alt="Demo Images"></a>
                                                            </div>
                                                            <div class="content">
                                                                <h4 class="title"><a href="10-online-course.html">Online Course <span class="btn-icon"><i class="feather-arrow-right"></i></span></a></h4>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- End Single Demo  -->

                                                <!-- Start Single Demo  -->
                                                <div class="col-lg-12 col-xl-2 col-xxl-2 col-md-12 col-sm-12 col-12 single-mega-item">
                                                    <div class="demo-single">
                                                        <div class="inner">
                                                            <div class="thumbnail">
                                                                <a href="05-classic-lms.html"><img src="${pageContext.request.contextPath}/assets/images/splash/demo/h5.jpg" alt="Demo Images"></a>
                                                            </div>
                                                            <div class="content">
                                                                <h4 class="title"><a href="05-classic-lms.html">Classic Lms <span class="btn-icon"><i class="feather-arrow-right"></i></span></a></h4>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- End Single Demo  -->

                                                <!-- Start Single Demo  -->
                                                <div class="col-lg-12 col-xl-2 col-xxl-2 col-md-12 col-sm-12 col-12 single-mega-item">
                                                    <div class="demo-single">
                                                        <div class="inner">
                                                            <div class="thumbnail">
                                                                <a href="02-course-school.html"><img src="${pageContext.request.contextPath}/assets/images/splash/demo/h2.jpg" alt="Demo Images"></a>
                                                            </div>
                                                            <div class="content">
                                                                <h4 class="title"><a href="02-course-school.html">Course School <span class="btn-icon"><i class="feather-arrow-right"></i></span></a></h4>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- End Single Demo  -->

                                                <!-- Start Single Demo  -->
                                                <div class="col-lg-12 col-xl-2 col-xxl-2 col-md-12 col-sm-12 col-12 single-mega-item coming-soon">
                                                    <div class="demo-single">
                                                        <div class="inner">
                                                            <div class="thumbnail">
                                                                <a href="#"><img src="${pageContext.request.contextPath}/assets/images/splash/demo/coming-soon-1.png" alt="Demo Images"></a>
                                                            </div>
                                                            <div class="content">
                                                                <h4 class="title"><a href="#">Coming Soon <span class="btn-icon"><i class="feather-arrow-right"></i></span></a></h4>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- End Single Demo  -->

                                                <!-- Start Single Demo  -->
                                                <div class="col-lg-12 col-xl-2 col-xxl-2 col-md-12 col-sm-12 col-12 single-mega-item coming-soon">
                                                    <div class="demo-single">
                                                        <div class="inner">
                                                            <div class="thumbnail">
                                                                <a href="#"><img src="${pageContext.request.contextPath}/assets/images/splash/demo/coming-soon-2.png" alt="Demo Images"></a>
                                                            </div>
                                                            <div class="content">
                                                                <h4 class="title"><a href="#">Coming Soon 2 <span class="btn-icon"><i class="feather-arrow-right"></i></span></a></h4>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- End Single Demo  -->

                                                <!-- Start Single Demo  -->
                                                <div class="col-lg-12 col-xl-2 col-xxl-2 col-md-12 col-sm-12 col-12 single-mega-item coming-soon">
                                                    <div class="demo-single">
                                                        <div class="inner">
                                                            <div class="thumbnail">
                                                                <a href="#"><img src="${pageContext.request.contextPath}/assets/images/splash/demo/coming-soon-3.png" alt="Demo Images"></a>
                                                            </div>
                                                            <div class="content">
                                                                <h4 class="title"><a href="#">Coming Soon 3 <span class="btn-icon"><i class="feather-arrow-right"></i></span></a></h4>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- End Single Demo  -->
                                            </div>

                                            <div class="load-demo-btn text-center">
                                                <a class="rbt-btn-link color-white" href="#">Scroll to view more <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down-up" viewBox="0 0 16 16">
                                                    <path fill-rule="evenodd" d="M11.5 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L11 2.707V14.5a.5.5 0 0 0 .5.5zm-7-14a.5.5 0 0 1 .5.5v11.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L4 13.293V1.5a.5.5 0 0 1 .5-.5z"></path>
                                                    </svg></a>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- End Mega Menu  -->
                                </li>
                                <!-- Course Dropdown -->
                                <jsp:include page="/layout/header/course-dropdown.jsp"/>
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
                                                <li><a href="instructor-enrolled-courses.html">Enrolled Courses</a></li>
                                                <li><a href="instructor-wishlist.html">Wishlist</a></li>
                                                <li><a href="instructor-reviews.html">Reviews</a></li>
                                                <li><a href="instructor-my-quiz-attempts.html">My Quiz Attempts</a></li>
                                                <li><a href="instructor-order-history.html">Order History</a></li>
                                                <li><a href="instructor-course.html">My Course</a></li>
                                                <li><a href="instructor-announcements.html">Announcements</a></li>
                                                <li><a href="instructor-quiz-attempts.html">Quiz Attempts</a></li>
                                                <li><a href="instructor-assignments.html">Assignments</a></li>
                                                <li><a href="instructor-settings.html">Settings</a></li>
                                            </ul>
                                        </li>
                                        <li class="has-dropdown"><a href="#">Student Dashboard</a>
                                            <ul class="submenu">
                                                <li><a href="student-dashboard.html">Dashboard</a></li>
                                                <li><a href="student-profile.html">Profile</a></li>
                                                <li><a href="student-enrolled-courses.html">Enrolled Courses</a></li>
                                                <li><a href="student-wishlist.html">Wishlist</a></li>
                                                <li><a href="student-reviews.html">Reviews</a></li>
                                                <li><a href="student-my-quiz-attempts.html">My Quiz Attempts</a></li>
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
                                                        <li><a href="academy-gallery.html">Academy Gallery</a></li>
                                                        <li><a href="admission-guide.html">Admission Guide</a></li>
                                                    </ul>
                                                </div>

                                                <div class="col-lg-12 col-xl-3 col-xxl-3 single-mega-item">
                                                    <h3 class="rbt-short-title">Get Started</h3>
                                                    <ul class="mega-menu-item">
                                                        <li><a href="profile.html">Profile</a></li>
                                                        <li><a href="contact.html">Contact Us</a></li>
                                                        <li><a href="become-a-teacher.html">Become a Teacher</a></li>
                                                        <li><a href="instructor.html">Instructor</a></li>
                                                        <li><a href="faqs.html">FAQS</a></li>
                                                        <li><a href="privacy-policy.html">Privacy Policy</a></li>
                                                        <li><a href="404.html">404 Page</a></li>
                                                        <li><a href="maintenance.html">Maintenance</a></li>
                                                    </ul>
                                                </div>

                                                <div class="col-lg-12 col-xl-3 col-xxl-3 single-mega-item">
                                                    <h3 class="rbt-short-title">Shop Pages</h3>
                                                    <ul class="mega-menu-item">
                                                        <li><a href="shop.html">Shop <span class="rbt-badge-card">Sale Anything</span></a></li>
                                                        <li><a href="single-product.html">Single Product</a></li>
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
                                                                <div class="image"><img src="${pageContext.request.contextPath}/assets/images/course/category-2.png" alt="Course images"></div>
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
                                                                <div class="image"><img src="${pageContext.request.contextPath}/assets/images/course/category-1.png" alt="Course images"></div>
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
                                                                <div class="image"><img src="${pageContext.request.contextPath}/assets/images/course/category-4.png" alt="Course images"></div>
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
                                                                <div class="image"><img src="${pageContext.request.contextPath}/assets/images/course/category-9.png" alt="Course images"></div>
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
                                                        <li><a href="style-guide.html">Style Guide <span class="rbt-badge-card">Hot</span></a></li>
                                                        <li><a href="accordion.html">Accordion</a></li>
                                                        <li><a href="advancetab.html">Advance Tab</a></li>
                                                        <li><a href="brand.html">Brand</a></li>
                                                        <li><a href="button.html">Button</a></li>
                                                        <li><a href="badge.html">Badge</a></li>
                                                        <li><a href="card.html">Card</a></li>
                                                        <li><a href="call-to-action.html">Call To Action</a></li>
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
                                                        <a class="rbt-btn btn-gradient hover-icon-reverse square btn-xl w-100 text-center mt--30 hover-transform-none" href="#">
                                                            <span class="icon-reverse-wrapper">
                                                                <span class="btn-text">Visit Histudy Template</span>
                                                                <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                                                <span class="btn-icon"><i class="feather-arrow-right"></i></span>
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
                                <jsp:include page="/layout/header/blog-dropdown.jsp"/>
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
                                <a class="rbt-cart-sidenav-activation rbt-round-btn" href="#">
                                    <i class="feather-shopping-cart"></i>
                                    <c:if test="${coursesInCart.size()>0}">
                                        <span class="rbt-cart-count">${coursesInCart.size()}</span>
                                    </c:if>
                                </a>
                            </li>
                            <!-- User -->
                            <li class="account-access rbt-user-wrapper d-none d-xl-block">
                                <a href="#"><i class="feather-user"></i>Admin</a>
                                <div class="rbt-user-menu-list-wrapper">
                                    <div class="inner">
                                        <div class="rbt-admin-profile">
                                            <div class="admin-thumbnail">
                                                <img src="${pageContext.request.contextPath}/assets/images/team/avatar.jpg" alt="User Images">
                                            </div>
                                            <div class="admin-info">
                                                <span class="name">Nipa Bali</span>
                                                <a class="rbt-btn-link color-primary" href="profile.html">View Profile</a>
                                            </div>
                                        </div>
                                        <ul class="user-list-wrapper">
                                            <li>
                                                <a href="instructor-dashboard.html">
                                                    <i class="feather-home"></i>
                                                    <span>My Dashboard</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <i class="feather-bookmark"></i>
                                                    <span>Bookmark</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="instructor-enrolled-courses.html">
                                                    <i class="feather-shopping-bag"></i>
                                                    <span>Enrolled Courses</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="instructor-wishlist.html">
                                                    <i class="feather-heart"></i>
                                                    <span>Wishlist</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="instructor-reviews.html">
                                                    <i class="feather-star"></i>
                                                    <span>Reviews</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="instructor-my-quiz-attempts.html">
                                                    <i class="feather-list"></i>
                                                    <span>My Quiz Attempts</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="instructor-order-history.html">
                                                    <i class="feather-clock"></i>
                                                    <span>Order History</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="instructor-quiz-attempts.html">
                                                    <i class="feather-message-square"></i>
                                                    <span>Question &amp; Answer</span>
                                                </a>
                                            </li>
                                        </ul>
                                        <hr class="mt--10 mb--10">
                                        <ul class="user-list-wrapper">
                                            <li>
                                                <a href="#">
                                                    <i class="feather-book-open"></i>
                                                    <span>Getting Started</span>
                                                </a>
                                            </li>
                                        </ul>
                                        <hr class="mt--10 mb--10">
                                        <ul class="user-list-wrapper">
                                            <li>
                                                <a href="instructor-settings.html">
                                                    <i class="feather-settings"></i>
                                                    <span>Settings</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="index.html">
                                                    <i class="feather-log-out"></i>
                                                    <span>Logout</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </li>

                            <li class="access-icon rbt-user-wrapper d-block d-xl-none">
                                <a class="rbt-round-btn" href="#"><i class="feather-user"></i></a>
                                <div class="rbt-user-menu-list-wrapper">
                                    <div class="inner">
                                        <div class="rbt-admin-profile">
                                            <div class="admin-thumbnail">
                                                <img src="${pageContext.request.contextPath}/assets/images/team/avatar.jpg" alt="User Images">
                                            </div>
                                            <div class="admin-info">
                                                <span class="name">Nipa Bali</span>
                                                <a class="rbt-btn-link color-primary" href="profile.html">View Profile</a>
                                            </div>
                                        </div>
                                        <ul class="user-list-wrapper">
                                            <li>
                                                <a href="instructor-dashboard.html">
                                                    <i class="feather-home"></i>
                                                    <span>My Dashboard</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <i class="feather-bookmark"></i>
                                                    <span>Bookmark</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="instructor-enrolled-courses.html">
                                                    <i class="feather-shopping-bag"></i>
                                                    <span>Enrolled Courses</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="instructor-wishlist.html">
                                                    <i class="feather-heart"></i>
                                                    <span>Wishlist</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="instructor-reviews.html">
                                                    <i class="feather-star"></i>
                                                    <span>Reviews</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="instructor-my-quiz-attempts.html">
                                                    <i class="feather-list"></i>
                                                    <span>My Quiz Attempts</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="instructor-order-history.html">
                                                    <i class="feather-clock"></i>
                                                    <span>Order History</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="instructor-quiz-attempts.html">
                                                    <i class="feather-message-square"></i>
                                                    <span>Question &amp; Answer</span>
                                                </a>
                                            </li>
                                        </ul>
                                        <hr class="mt--10 mb--10">
                                        <ul class="user-list-wrapper">
                                            <li>
                                                <a href="#">
                                                    <i class="feather-book-open"></i>
                                                    <span>Getting Started</span>
                                                </a>
                                            </li>
                                        </ul>
                                        <hr class="mt--10 mb--10">
                                        <ul class="user-list-wrapper">
                                            <li>
                                                <a href="instructor-settings.html">
                                                    <i class="feather-settings"></i>
                                                    <span>Settings</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="index.html">
                                                    <i class="feather-log-out"></i>
                                                    <span>Logout</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </li>
                        </ul>

                        <div class="rbt-btn-wrapper d-none d-xl-block">
                            <a class="rbt-btn rbt-marquee-btn marquee-auto btn-border-gradient radius-round btn-sm hover-transform-none" href="#">
                                <span data-text="Enroll Now">Enroll Now</span>
                            </a>
                        </div>

                        <!-- Start Mobile-Menu-Bar -->
                        <div class="mobile-menu-bar d-block d-xl-none">
                            <div class="hamberger">
                                <button class="hamberger-button rbt-round-btn">
                                    <i class="feather-menu"></i>
                                </button>
                            </div>
                        </div>
                        <!-- Start Mobile-Menu-Bar -->

                    </div>
                </div>
            </div>
            <!-- Start Search Dropdown  -->
            <jsp:include page="/layout/header/search-dropdown.jsp"/>
            <!-- End Search Dropdown  -->
        </div>

        <!-- USE FOR MOBILE -->
        <jsp:include page="/layout/header/mobile-category-side.jsp"/>
    </header>
    <!-- USE FOR MOBILE -->
    <jsp:include page="/layout/header/mobile-menu-side.jsp"/>
    <!-- Cart Side -->
    <jsp:include page="/layout/header/cart-side.jsp"/>      
</div>
