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
                                            <h4 class="rbt-title-style-3">Change Password</h4>
                                        </div>

                                        <form action="${pageContext.request.contextPath}/learner/change-password" class="rbt-profile-row rbt-default-form row row--15" method="post">
                                            <div class="col-12">
                                                <div class="rbt-form-group">
                                                    <label for="currentPassword">Current Password</label>
                                                    <input id="currentPassword" name="currentPassword" type="password" placeholder="Current Password" required>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="rbt-form-group">
                                                    <label for="newPassword">New Password</label>
                                                    <input id="newPassword" name="newPassword" type="password" placeholder="New Password" required>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="rbt-form-group">
                                                    <label for="retypePassword">Re-type New Password</label>
                                                    <input id="retypePassword" name="retypePassword" type="password" placeholder="Re-type New Password" required>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <c:if test="${success_message != null}">
                                                    <p class="text-success">
                                                        ${success_message}
                                                    </p>
                                                </c:if>
                                                <c:if test="${error_message != null}">
                                                    <p class="text-danger">
                                                        ${error_message}
                                                    </p>
                                                </c:if>
                                            </div>
                                            <div class="col-12 mt--10">
                                                <div class="rbt-form-group">
                                                    <button class="rbt-btn btn-gradient" type="submit">Update Password</button>
                                                </div>
                                            </div>
                                        </form>
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