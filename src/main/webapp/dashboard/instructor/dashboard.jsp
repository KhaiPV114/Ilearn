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
    <body>
        <jsp:include page="/layout/header.jsp"/>
        <jsp:include page="/layout/dashboard-header.jsp"/>
        <div class="rbt-dashboard-area rbt-section-overlayping-top rbt-section-gapBottom">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <jsp:include page="/layout/dashboard-instructor-card.jsp"/>
                        <div class="row g-5">
                            <jsp:include page="/layout/dashboard-instrutor-sidebar.jsp"/>
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
                                                            <h3 class="counter without-icon color-primary">
                                                                <span class="odometer odometer-auto-theme" >
                                                                    <div class="odometer-inside">
                                                                        <span class="odometer-digit">
                                                                            <span class="odometer-digit-spacer">8</span>
                                                                            <span class="odometer-digit-inner">
                                                                                <span class="odometer-ribbon">
                                                                                    <span class="odometer-ribbon-inner">
                                                                                        <span class="odometer-value">${totalCourse}
                                                                                        </span></span></span></span></span><span class="odometer-digit"><span class="odometer-digit-spacer">8</span><span class="odometer-digit-inner"><span class="odometer-ribbon"><span class="odometer-ribbon-inner"><span class="odometer-value"></span></span></span></span></span></div></span>
                                                            </h3>
                                                            <span class="rbt-title-style-2 d-block">Sold Courses</span>
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
                                                            <h3 class="counter without-icon color-secondary">
                                                                <span class="odometer odometer-auto-theme">
                                                                    <div class="odometer-inside">
                                                                        <span class="odometer-digit">
                                                                            <span class="odometer-digit-spacer">8</span>
                                                                            <span class="odometer-digit-inner">
                                                                                <span class="odometer-ribbon">
                                                                                    <span class="odometer-ribbon-inner">
                                                                                        <span class="odometer-value">${coursesP}</span></span></span></span></span><span class="odometer-digit"><span class="odometer-digit-spacer">8</span><span class="odometer-digit-inner"><span class="odometer-ribbon"><span class="odometer-ribbon-inner"><span class="odometer-value"></span></span></span></span></span></div></span>
                                                            </h3>
                                                            <span class="rbt-title-style-2 d-block">PUBLISHED COURSES</span>
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
                                                            <h3 class="counter without-icon color-violet"><span class="odometer odometer-auto-theme"><div class="odometer-inside"><span class="odometer-digit"><span class="odometer-digit-spacer">8</span><span class="odometer-digit-inner"><span class="odometer-ribbon"><span class="odometer-ribbon-inner"><span class="odometer-value">${coursesNew}</span></span></span></span></span></div></span>
                                                            </h3>
                                                            <span class="rbt-title-style-2 d-block">NEW COURSES</span>
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
                                                            <h3 class="counter without-icon color-pink">
                                                                <span class="odometer odometer-auto-theme">
                                                                    <div class="odometer-inside">
                                                                        <span class="odometer-digit">
                                                                            <span class="odometer-digit-spacer">8</span>
                                                                            <span class="odometer-digit-inner">
                                                                                <span class="odometer-ribbon"><span class="odometer-ribbon-inner"><span class="odometer-value">${totalLearner}</span></span></span></span></span></div></span>
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
                                                            <h3 class="counter without-icon color-coral"><span class="odometer odometer-auto-theme"><div class="odometer-inside"><span class="odometer-digit"><span class="odometer-digit-spacer">8</span><span class="odometer-digit-inner"><span class="odometer-ribbon"><span class="odometer-ribbon-inner"><span class="odometer-value">${coursesNew + coursesP}</span></span></span></span></span></div></span>
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
                                                            <h3 class="counter color-warning"><span class="odometer odometer-auto-theme"><div class="odometer-inside"><span class="odometer-digit"><span class="odometer-digit-spacer">8</span><span class="odometer-digit-inner"><span class="odometer-ribbon"><span class="odometer-ribbon-inner"><span class="odometer-value">${totalPrice}</span></span></span></span></span></div></span>
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
                                                    <h4 class="rbt-title-style-3">My Learner</h4>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row gy-5">
                                            <div class="col-lg-12">
                                                <div class="rbt-dashboard-table table-responsive">
                                                    <table class="rbt-table table table-borderless">
                                                        <thead>
                                                            <tr>
                                                                <th>Name</th>
                                                                <th>Email</th>
                                                                <th>Phone Number</th>
                                                                <th>Joined Time</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${users}" var="user" varStatus="i">
<<<<<<< Updated upstream
                                                                <c:if test="${i.count < 4}">
=======
                                                                <c:if test="${i.count < 5}">
>>>>>>> Stashed changes
                                                                    <tr >
                                                                        <th>${user.fullName}</th>
                                                                        <td>${user.email == null ? user.googleEmail : user.email}</td>
                                                                        <td>${user.phoneNumber}</td>
                                                                        <td>${user.createdAt.toString().replace('T',' - ')}</td>
                                                                    </tr>
                                                                </c:if>

                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>

                                                <div class="load-more-btn text-center">
                                                    <a class="rbt-btn-link" href="${pageContext.request.contextPath}/instructor/learner/search">Browse All Learner<i class="feather-arrow-right"></i></a>
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
        <jsp:include page="/layout/footer.jsp"/>
        <jsp:include page="/layout/scripts.jsp"/>
    </body>
</html>