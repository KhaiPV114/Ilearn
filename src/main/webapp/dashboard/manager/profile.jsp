<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iLearn | My Profile</title>
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
                            <c:choose>
                                <c:when test="${roleService.isLearner(pageContext.request)}">
                                    <jsp:include page="/layout/dashboard-learner-sidebar.jsp" />
                                </c:when>
                                <c:when test="${roleService.isInstructor(pageContext.request)}">
                                    <jsp:include page="/layout/dashboard-instrutor-sidebar.jsp"/>
                                </c:when>
                                <c:when test="${roleService.isManager(pageContext.request)}">
                                    <jsp:include page="/layout/dashboard-manager-sidebar.jsp"/>
                                </c:when>
                            </c:choose>

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
                                                <div class="rbt-profile-content b2">
                                                    <fmt:parseDate value="${requestScope.user.createdAt}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                                                    <fmt:formatDate pattern="dd/MM/yyyy 'at' HH:mm" value="${parsedDateTime}" />
                                                </div>
                                            </div>
                                        </div>
                                        <!-- End Profile Row  -->

                                        <!-- Start Profile Row  -->
                                        <div class="rbt-profile-row row row--15 mt--15">
                                            <div class="col-lg-4 col-md-4">
                                                <div class="rbt-profile-content b2">Full Name</div>
                                            </div>
                                            <div class="col-lg-8 col-md-8">
                                                <div class="rbt-profile-content b2">${requestScope.user.fullName}</div>
                                            </div>
                                        </div>
                                        <!-- End Profile Row  -->

                                        <!-- Start Profile Row  -->
                                        <div class="rbt-profile-row row row--15 mt--15">
                                            <div class="col-lg-4 col-md-4">
                                                <div class="rbt-profile-content b2">Username</div>
                                            </div>
                                            <div class="col-lg-8 col-md-8">
                                                <div class="rbt-profile-content b2">${requestScope.user.username}</div>
                                            </div>
                                        </div>
                                        <!-- End Profile Row  -->

                                        <!-- Start Profile Row  -->
                                        <div class="rbt-profile-row row row--15 mt--15">
                                            <div class="col-lg-4 col-md-4">
                                                <div class="rbt-profile-content b2">Email</div>
                                            </div>
                                            <div class="col-lg-8 col-md-8">
                                                <div class="rbt-profile-content b2">
                                                    <c:if test="${requestScope.user.email != null}">
                                                        ${requestScope.user.email}
                                                    </c:if>
                                                    <c:if test="${requestScope.user.email == null}">
                                                        ${requestScope.user.googleEmail}
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- End Profile Row  -->

                                        <!-- Start Profile Row  -->
                                        <div class="rbt-profile-row row row--15 mt--15">
                                            <div class="col-lg-4 col-md-4">
                                                <div class="rbt-profile-content b2">Phone Number</div>
                                            </div>
                                            <div class="col-lg-8 col-md-8">
                                                <div class="rbt-profile-content b2">${requestScope.user.phoneNumber}</div>
                                            </div>
                                        </div>
                                        <!-- End Profile Row  -->

                                        <!-- Start Profile Row  -->
                                        <div class="rbt-profile-row row row--15 mt--15">
                                            <div class="col-lg-4 col-md-4">
                                                <div class="rbt-profile-content b2">Date Of Birth</div>
                                            </div>
                                            <div class="col-lg-8 col-md-8">
                                                <div class="rbt-profile-content b2">${requestScope.user.dob}</div>
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
        <jsp:include page="/layout/footer.jsp"/>
        <jsp:include page="/layout/scripts.jsp"/>
    </body>
</html>