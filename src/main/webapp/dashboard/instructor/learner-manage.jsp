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
        <jsp:include page="/layout/header.jsp" />
        <jsp:include page="/layout/dashboard-header.jsp" />
        <div class="rbt-dashboard-area rbt-section-overlayping-top rbt-section-gapBottom">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <jsp:include page="/layout/dashboard-instructor-card.jsp" />
                        <div class="row g-5">
                            <jsp:include page="/layout/dashboard-instructor-sidebar.jsp" />
                            <!-- Start content -->
                            <div class="col-lg-9" id="content">
                                <div class="rbt-dashboard-content bg-color-white rbt-shadow-box">
                                    <div class="content">
                                        <!-- Start Title -->
                                        <div class="section-title">
                                            <nav class="rbt-title-style-3 h4 pb-0" aria-label="breadcrumb">
                                                <ol class="breadcrumb">
                                                    <li class="breadcrumb-item">
                                                        <a href="#">
                                                            Learner
                                                        </a>
                                                    </li>
                                                    <li class="breadcrumb-item active" aria-current="page">
                                                        <a class="color-primary" href="#">
                                                            Search
                                                        </a>
                                                    </li>
                                                </ol>
                                            </nav>
                                        </div>
                                        <!-- End Title -->
                                        <form action="${pageContext.request.contextPath}/search-learner" method="post">
                                            <div class="row">
                                                <div class="col-md-7 col-lg-8">
                                                    <div class="mb-3">
                                                        <label for="id">Username:</label>
                                                        <input type="text" name="userName" id="userName" class="form-control" placeholder="Username">
                                                    </div>
                                                </div>
                                            </div>
                                            <button type="submit" class="rbt-btn btn-md mt-3">Search</button>
                                        </form>

                                    </div>
                                </div>

                                <div class="rbt-dashboard-content bg-color-white rbt-shadow-box" style="margin-top: 30px">
                                    <div class="content">
                                        <!-- Start Title -->
                                        <div class="section-title">
                                            <nav class="rbt-title-style-3 h4 pb-0" aria-label="breadcrumb">
                                                <ol class="breadcrumb">
                                                    <li class="breadcrumb-item">
                                                        <a href="#">
                                                            Learner
                                                        </a>
                                                    </li>
                                                    <li class="breadcrumb-item active" aria-current="page">
                                                        <a class="color-primary" href="#">
                                                            Active
                                                        </a>
                                                    </li>
                                                </ol>
                                            </nav>
                                        </div>
                                        <!-- End Title -->
                                        <div class="row gy-5">
                                            <div class="col-lg-12">
                                                <div class="rbt-dashboard-table table-responsive" >
                                                    <table class="rbt-table table table-borderless ">
                                                        <thead>
                                                            <tr>
                                                                <th>Name</th>
                                                                <th>Course Enrolled</th>
                                                                <th>Email</th>
                                                                <th>Phone Number</th>
                                                                <th>Joined Time</th>
                                                                <th>Status</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${learnerList}" var="learner">
                                                            <form action="#" id="learner-${learner.id}">
                                                                <tr>
                                                                    <th>${learner.fullName}</th>
                                                                    <td>${learner.id}</td>
                                                                    <td>${learner.email}</td>
                                                                    <td>${learner.phoneNumber}</td>
                                                                    <td>${learner.createdAt}</td>
                                                                    <td><input class="btn btn-success" type="button" onclick="document.getElementById('learner-${learner.id}').submit()" name="ban" value="${learner.status}"/></td>
                                                                </tr>
                                                            </form>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="rbt-dashboard-content bg-color-white rbt-shadow-box" style="margin-top: 30px">
                                    <div class="content">
                                        <!-- Start Title -->
                                        <div class="section-title">
                                            <nav class="rbt-title-style-3 h4 pb-0" aria-label="breadcrumb">
                                                <ol class="breadcrumb">
                                                    <li class="breadcrumb-item">
                                                        <a href="#">
                                                            Learner
                                                        </a>
                                                    </li>
                                                    <li class="breadcrumb-item active" aria-current="page">
                                                        <a class="color-primary" href="#">
                                                            Banned
                                                        </a>
                                                    </li>
                                                </ol>
                                            </nav>
                                        </div>
                                        <!-- End Title -->
                                        <div class="row gy-5">
                                            <div class="col-lg-12">
                                                <div class="rbt-dashboard-table table-responsive">
                                                    <table class="rbt-table table table-borderless">
                                                        <thead>
                                                            <tr>
                                                                <th>Name</th>
                                                                <th>Course Enrolled</th>
                                                                <th>Email</th>
                                                                <th>Phone Number</th>
                                                                <th>Joined Time</th>
                                                                <th>Status</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                        <form action="#" id="user-${user.id}">
                                                            <tr>
                                                                <th><a href="#">Justin Bieber</a></th>
                                                                <td>Graphic</td>
                                                                <td>justinbieber@gmail.com</td>
                                                                <td>03548594945</td>
                                                                <td>LocalDateTime</td>
                                                                <td><input class="btn btn-danger" type="button" onclick="document.getElementById('user-${user.id}').submit()" name="ban" value="BANNED"/></td>
                                                            </tr>
                                                        </form>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- End Content -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/layout/footer.jsp" />
    </body>
    <jsp:include page="/layout/scripts.jsp"/>
</html>