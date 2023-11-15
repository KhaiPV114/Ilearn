<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <jsp:include page="/layout/links.jsp"/>
    </head>
    <body>
        <jsp:include page="/layout/header.jsp"/>
        <jsp:include page="/layout/dashboard-header.jsp"/>
        <div class="rbt-dashboard-area rbt-section-overlayping-top rbt-section-gapBottom">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <jsp:include page="/layout/dashboard-learner-card.jsp"/>
                        <div class="row g-5">
                            <jsp:include page="/layout/dashboard-learner-sidebar.jsp"/>
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
                                                            <h3 class="counter without-icon color-primary"><span class="odometer odometer-auto-theme" data-count="${enrolledCourses.size()}"><div class="odometer-inside"><span class="odometer-digit"><span class="odometer-digit-spacer">8</span><span class="odometer-digit-inner"><span class="odometer-ribbon"><span class="odometer-ribbon-inner"><span class="odometer-value">5</span></span></span></span></span><span class="odometer-digit"><span class="odometer-digit-spacer">8</span><span class="odometer-digit-inner"><span class="odometer-ribbon"><span class="odometer-ribbon-inner"><span class="odometer-value">8</span></span></span></span></span></div></span>
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
                                                            <h3 class="counter without-icon color-secondary"><span class="odometer odometer-auto-theme" data-count="${enrolledCourses.size()}"><div class="odometer-inside"><span class="odometer-digit"><span class="odometer-digit-spacer">8</span><span class="odometer-digit-inner"><span class="odometer-ribbon"><span class="odometer-ribbon-inner"><span class="odometer-value">5</span></span></span></span></span><span class="odometer-digit"><span class="odometer-digit-spacer">8</span><span class="odometer-digit-inner"><span class="odometer-ribbon"><span class="odometer-ribbon-inner"><span class="odometer-value">0</span></span></span></span></span></div></span>
                                                            </h3>
                                                            <span class="rbt-title-style-2 d-block">LEARNING COURSES</span>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                            <!-- End Single Card  -->

                                            <!-- Start Single Card  -->
<!--                                            <div class="col-lg-4 col-md-4 col-sm-6 col-12">
                                                <div class="rbt-counterup variation-01 rbt-hover-03 rbt-border-dashed bg-violet-opacity">
                                                    <div class="inner">
                                                        <div class="rbt-round-icon bg-violet-opacity">
                                                            <i class="feather-award"></i>
                                                        </div>
                                                        <div class="content">
                                                            <h3 class="counter without-icon color-violet"><span class="odometer odometer-auto-theme" data-count="7"><div class="odometer-inside"><span class="odometer-digit"><span class="odometer-digit-spacer">8</span><span class="odometer-digit-inner"><span class="odometer-ribbon"><span class="odometer-ribbon-inner"><span class="odometer-value">5</span></span></span></span></span></div></span>
                                                            </h3>
                                                            <span class="rbt-title-style-2 d-block">Completed Courses</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>-->
                                            <!-- End Single Card  -->
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