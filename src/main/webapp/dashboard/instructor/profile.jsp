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
                                    <!-- Start Instructor Profile  -->
                                    <div class="rbt-dashboard-content bg-color-white rbt-shadow-box">
                                        <div class="content">
                                            <div class="section-title">
                                                <h4 class="rbt-title-style-3">My Profile</h4>
                                            </div>
                                            <!-- Start Profile Row  -->
                                            <div class="rbt-profile-row row row--15">
                                                <div class="col-lg-4 col-md-4">
                                                    <div class="rbt-profile-content b2">Registration Date</div>
                                                </div>
                                                <div class="col-lg-8 col-md-8">
                                                    <div class="rbt-profile-content b2">February 25, 2025 6:01 am</div>
                                                </div>
                                            </div>
                                            <!-- End Profile Row  -->

                                            <!-- Start Profile Row  -->
                                            <div class="rbt-profile-row row row--15 mt--15">
                                                <div class="col-lg-4 col-md-4">
                                                    <div class="rbt-profile-content b2">First Name</div>
                                                </div>
                                                <div class="col-lg-8 col-md-8">
                                                    <div class="rbt-profile-content b2">John</div>
                                                </div>
                                            </div>
                                            <!-- End Profile Row  -->

                                            <!-- Start Profile Row  -->
                                            <div class="rbt-profile-row row row--15 mt--15">
                                                <div class="col-lg-4 col-md-4">
                                                    <div class="rbt-profile-content b2">Last Name</div>
                                                </div>
                                                <div class="col-lg-8 col-md-8">
                                                    <div class="rbt-profile-content b2">Doe</div>
                                                </div>
                                            </div>
                                            <!-- End Profile Row  -->

                                            <!-- Start Profile Row  -->
                                            <div class="rbt-profile-row row row--15 mt--15">
                                                <div class="col-lg-4 col-md-4">
                                                    <div class="rbt-profile-content b2">Username</div>
                                                </div>
                                                <div class="col-lg-8 col-md-8">
                                                    <div class="rbt-profile-content b2">instructor</div>
                                                </div>
                                            </div>
                                            <!-- End Profile Row  -->

                                            <!-- Start Profile Row  -->
                                            <div class="rbt-profile-row row row--15 mt--15">
                                                <div class="col-lg-4 col-md-4">
                                                    <div class="rbt-profile-content b2">Email</div>
                                                </div>
                                                <div class="col-lg-8 col-md-8">
                                                    <div class="rbt-profile-content b2">example@gmail.com</div>
                                                </div>
                                            </div>
                                            <!-- End Profile Row  -->

                                            <!-- Start Profile Row  -->
                                            <div class="rbt-profile-row row row--15 mt--15">
                                                <div class="col-lg-4 col-md-4">
                                                    <div class="rbt-profile-content b2">Phone Number</div>
                                                </div>
                                                <div class="col-lg-8 col-md-8">
                                                    <div class="rbt-profile-content b2">+1-202-555-0174</div>
                                                </div>
                                            </div>
                                            <!-- End Profile Row  -->

                                            <!-- Start Profile Row  -->
                                            <div class="rbt-profile-row row row--15 mt--15">
                                                <div class="col-lg-4 col-md-4">
                                                    <div class="rbt-profile-content b2">Skill/Occupation</div>
                                                </div>
                                                <div class="col-lg-8 col-md-8">
                                                    <div class="rbt-profile-content b2">Application Developer</div>
                                                </div>
                                            </div>
                                            <!-- End Profile Row  -->

                                            <!-- Start Profile Row  -->
                                            <div class="rbt-profile-row row row--15 mt--15">
                                                <div class="col-lg-4 col-md-4">
                                                    <div class="rbt-profile-content b2">Biography</div>
                                                </div>
                                                <div class="col-lg-8 col-md-8">
                                                    <div class="rbt-profile-content b2">I'm the Front-End Developer for #Rainbow IT in Bangladesh, OR. I have serious passion for UI effects, animations and creating intuitive, dynamic user experiences.</div>
                                                </div>
                                            </div>
                                            <!-- End Profile Row  -->
                                        </div>
                                    </div>
                                    <!-- End Instructor Profile  -->
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