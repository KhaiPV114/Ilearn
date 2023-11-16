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
                        <c:choose>
                            <c:when test="${roleService.isLearner(pageContext.request)}">
                                <jsp:include page="/layout/dashboard-learner-card.jsp" />
                            </c:when>
                            <c:when test="${roleService.isInstructor(pageContext.request)}">
                                <jsp:include page="/layout/dashboard-instructor-card.jsp"/>
                            </c:when>
                            <c:when test="${roleService.isManager(pageContext.request)}">
                                <jsp:include page="/layout/dashboard-manager-card.jsp"/>
                            </c:when>
                        </c:choose>
                        <div class="row g-5" id="content">
                            <c:choose>
                                <c:when test="${roleService.isLearner(pageContext.request)}">
                                    <jsp:include page="/layout/dashboard-learner-sidebar.jsp" />
                                </c:when>
                                <c:when test="${roleService.isInstructor(pageContext.request)}">
                                    <jsp:include page="/layout/dashboard-instructor-sidebar.jsp"/>
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
                                        <form action="${pageContext.request.contextPath}/learner/profile" class="rbt-profile-row rbt-default-form row row--15" method="post">
                                            <div class="col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="rbt-form-group">
                                                    <label for="username">Username</label>
                                                    <input id="username" type="text" value="${requestScope.user.username}" readonly>
                                                </div>
                                            </div>
                                            <div class="col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="rbt-form-group">
                                                    <label for="createAt">Registration Date</label>
                                                    <fmt:parseDate value="${requestScope.user.createdAt}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />

                                                    <input id="createAt" type="text" value="<fmt:formatDate pattern="dd/MM/yyyy 'at' HH:mm" value="${parsedDateTime}" />" readonly>
                                                </div>
                                            </div>
                                            <div class="col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="rbt-form-group">
                                                    <label for="fullname">Full Name <span style="color: red">(*)</span></label>
                                                    <input id="fullname" name="fullname" type="text" value="${requestScope.user.fullName}" required>
                                                </div>
                                            </div>
                                            <div class="col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="rbt-form-group">
                                                    <label for="phoneNumber">Phone Number <span style="color: red">(*)</span></label>
                                                    <input id="phoneNumber" name="phoneNumber" type="number" value="${requestScope.user.phoneNumber}" required/>
                                                </div>
                                            </div>
                                            <div class="col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="rbt-form-group">
                                                    <label for="dob">Date Of Birth <span style="color: red">(*)</span></label>
                                                    <input id="dob" name="dob" type="date" value="${requestScope.user.dob}" required/>
                                                </div>
                                            </div>
                                            <div class="col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="rbt-form-group">
                                                    <label for="email">Email</label>
                                                    <input id="email" type="email" value="${requestScope.user.email != null ? requestScope.user.email : requestScope.user.googleEmail}" readonly>
                                                </div>
                                            </div>
                                            <div class="color-success">
                                                ${message}
                                            </div>
                                            <div class="col-12 mt--20">
                                                <div class="rbt-form-group">
                                                    <button class="rbt-btn btn-gradient" type="submit">Update Info</button>
                                                </div>
                                            </div>
                                        </form>
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
        <script>
            document.getElementById("content").scrollIntoView({behavior: 'instant'});
            location.hash = '#content';
        </script>
    </body>
</html>