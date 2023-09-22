<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="/layout/links.jsp"></jsp:include>
        </head>
        <body>
        <jsp:include page="/layout/header.jsp"></jsp:include>
        <jsp:include page="/layout/dashboard-header.jsp"></jsp:include>
            <div class="rbt-dashboard-area rbt-section-overlayping-top rbt-section-gapBottom">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                        <jsp:include page="/layout/dashboard-instructor-card.jsp"></jsp:include>
                            <div class="row g-5">
                            <jsp:include page="/layout/dashboard-instrutor-sidebar.jsp"></jsp:include>
                                <!-- Start Content  -->
                                <div class="col-lg-9">
                                    <div class="rbt-dashboard-content bg-color-white rbt-shadow-box mb--60">
                                        <div class="content">
                                            <div class="section-title">
                                                <h4 class="rbt-title-style-3">Dashboard</h4>
                                            </div>
                                            <div class="row g-5">

                                                <!-- Start Single Card  -->
                                                <div class="col-lg-4 col-md-4 col-sm-6 col-12">
                                                    <div class="rbt-counterup variation-01 rbt-hover-03 rbt-border-dashed bg-primary-opacity">
                                                        <div class="inner">
                                                            <div class="rbt-round-icon bg-primary-opacity">
                                                                <i class="feather-book-open"></i>
                                                            </div>
                                                            <div class="content">
                                                                <h3 class="counter without-icon color-primary"><span class="odometer odometer-auto-theme" data-count="30"><div class="odometer-inside"><span class="odometer-digit"><span class="odometer-digit-spacer">8</span><span class="odometer-digit-inner"><span class="odometer-ribbon"><span class="odometer-ribbon-inner"><span class="odometer-value">3</span></span></span></span></span><span class="odometer-digit"><span class="odometer-digit-spacer">8</span><span class="odometer-digit-inner"><span class="odometer-ribbon"><span class="odometer-ribbon-inner"><span class="odometer-value">0</span></span></span></span></span></div></span>
                                                                </h3>
                                                                <span class="rbt-title-style-2 d-block">Enrolled Courses</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- End Single Card  -->

                                                <!-- Start Single Card  -->
                                                <div class="col-lg-4 col-md-4 col-sm-6 col-12">
                                                    <div class="rbt-counterup variation-01 rbt-hover-03 rbt-border-dashed bg-secondary-opacity">
                                                        <div class="inner">
                                                            <div class="rbt-round-icon bg-secondary-opacity">
                                                                <i class="feather-monitor"></i>
                                                            </div>
                                                            <div class="content">
                                                                <h3 class="counter without-icon color-secondary"><span class="odometer odometer-auto-theme" data-count="10"><div class="odometer-inside"><span class="odometer-digit"><span class="odometer-digit-spacer">8</span><span class="odometer-digit-inner"><span class="odometer-ribbon"><span class="odometer-ribbon-inner"><span class="odometer-value">1</span></span></span></span></span><span class="odometer-digit"><span class="odometer-digit-spacer">8</span><span class="odometer-digit-inner"><span class="odometer-ribbon"><span class="odometer-ribbon-inner"><span class="odometer-value">0</span></span></span></span></span></div></span>
                                                                </h3>
                                                                <span class="rbt-title-style-2 d-block">ACTIVE COURSES</span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                                <!-- End Single Card  -->

                                                <!-- Start Single Card  -->
                                                <div class="col-lg-4 col-md-4 col-sm-6 col-12">
                                                    <div class="rbt-counterup variation-01 rbt-hover-03 rbt-border-dashed bg-violet-opacity">
                                                        <div class="inner">
                                                            <div class="rbt-round-icon bg-violet-opacity">
                                                                <i class="feather-award"></i>
                                                            </div>
                                                            <div class="content">
                                                                <h3 class="counter without-icon color-violet"><span class="odometer odometer-auto-theme" data-count="7"><div class="odometer-inside"><span class="odometer-digit"><span class="odometer-digit-spacer">8</span><span class="odometer-digit-inner"><span class="odometer-ribbon"><span class="odometer-ribbon-inner"><span class="odometer-value">7</span></span></span></span></span></div></span>
                                                                </h3>
                                                                <span class="rbt-title-style-2 d-block">Completed Courses</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- End Single Card  -->

                                                <!-- Start Single Card  -->
                                                <div class="col-lg-4 col-md-4 col-sm-6 col-12">
                                                    <div class="rbt-counterup variation-01 rbt-hover-03 rbt-border-dashed bg-pink-opacity">
                                                        <div class="inner">
                                                            <div class="rbt-round-icon bg-pink-opacity">
                                                                <i class="feather-users"></i>
                                                            </div>
                                                            <div class="content">
                                                                <h3 class="counter without-icon color-pink"><span class="odometer odometer-auto-theme" data-count="160"><div class="odometer-inside"><span class="odometer-digit"><span class="odometer-digit-spacer">8</span><span class="odometer-digit-inner"><span class="odometer-ribbon"><span class="odometer-ribbon-inner"><span class="odometer-value">0</span></span></span></span></span></div></span>
                                                                </h3>
                                                                <span class="rbt-title-style-2 d-block">Total Students</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- End Single Card  -->

                                                <!-- Start Single Card  -->
                                                <div class="col-lg-4 col-md-4 col-sm-6 col-12">
                                                    <div class="rbt-counterup variation-01 rbt-hover-03 rbt-border-dashed bg-coral-opacity">
                                                        <div class="inner">
                                                            <div class="rbt-round-icon bg-coral-opacity">
                                                                <i class="feather-gift"></i>
                                                            </div>
                                                            <div class="content">
                                                                <h3 class="counter without-icon color-coral"><span class="odometer odometer-auto-theme" data-count="20"><div class="odometer-inside"><span class="odometer-digit"><span class="odometer-digit-spacer">8</span><span class="odometer-digit-inner"><span class="odometer-ribbon"><span class="odometer-ribbon-inner"><span class="odometer-value">0</span></span></span></span></span></div></span>
                                                                </h3>
                                                                <span class="rbt-title-style-2 d-block">Total Courses</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- End Single Card  -->

                                                <!-- Start Single Card  -->
                                                <div class="col-lg-4 col-md-4 col-sm-6 col-12">
                                                    <div class="rbt-counterup variation-01 rbt-hover-03 rbt-border-dashed bg-warning-opacity">
                                                        <div class="inner">
                                                            <div class="rbt-round-icon bg-warning-opacity">
                                                                <i class="feather-dollar-sign"></i>
                                                            </div>
                                                            <div class="content">
                                                                <h3 class="counter color-warning"><span class="odometer odometer-auto-theme" data-count="25000"><div class="odometer-inside"><span class="odometer-digit"><span class="odometer-digit-spacer">8</span><span class="odometer-digit-inner"><span class="odometer-ribbon"><span class="odometer-ribbon-inner"><span class="odometer-value">0</span></span></span></span></span></div></span>
                                                                </h3>
                                                                <span class="rbt-title-style-2 d-block">Total Earnings</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- End Single Card  -->

                                            </div>
                                        </div>
                                    </div>
                                    <div class="rbt-dashboard-content bg-color-white rbt-shadow-box mb--60">
                                        <div class="content">
                                            <div class="row">
                                                <div class="col-lg-12">
                                                    <div class="section-title">
                                                        <h4 class="rbt-title-style-3">My Courses</h4>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row gy-5">
                                                <div class="col-lg-12">
                                                    <div class="rbt-dashboard-table table-responsive">
                                                        <table class="rbt-table table table-borderless">
                                                            <thead>
                                                                <tr>
                                                                    <th>Course Name</th>
                                                                    <th>Enrolled</th>
                                                                    <th>Rating</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <th><a href="#">Accounting</a></th>
                                                                    <td>50</td>
                                                                    <td>
                                                                        <div class="rating">
                                                                            <i class="fas fa-star"></i>
                                                                            <i class="fas fa-star"></i>
                                                                            <i class="fas fa-star"></i>
                                                                            <i class="fas fa-star"></i>
                                                                            <i class="fas fa-star"></i>
                                                                        </div>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th><a href="#">Marketing</a></th>
                                                                    <td>40</td>
                                                                    <td>
                                                                        <div class="rating">
                                                                            <i class="fas fa-star"></i>
                                                                            <i class="fas fa-star"></i>
                                                                            <i class="fas fa-star"></i>
                                                                            <i class="fas fa-star"></i>
                                                                            <i class="fas fa-star"></i>
                                                                        </div>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th><a href="#">Web Design</a></th>
                                                                    <td>75</td>
                                                                    <td>
                                                                        <div class="rating">
                                                                            <i class="fas fa-star"></i>
                                                                            <i class="fas fa-star"></i>
                                                                            <i class="fas fa-star"></i>
                                                                            <i class="fas fa-star"></i>
                                                                            <i class="fas fa-star"></i>
                                                                        </div>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th><a href="#">Graphic</a></th>
                                                                    <td>20</td>
                                                                    <td>
                                                                        <div class="rating">
                                                                            <i class="fas fa-star"></i>
                                                                            <i class="fas fa-star"></i>
                                                                            <i class="off fas fa-star"></i>
                                                                            <i class="off fas fa-star"></i>
                                                                            <i class="off fas fa-star"></i>
                                                                        </div>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>

                                                    <div class="load-more-btn text-center">
                                                        <a class="rbt-btn-link" href="#">Browse All Course<i class="feather-arrow-right"></i></a>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <!-- End Content  -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <jsp:include page="/layout/footer.jsp"></jsp:include>
        <jsp:include page="/layout/scripts.jsp"></jsp:include>
    </body>
</html>