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
                            <jsp:include page="/layout/dashboard-instrutor-sidebar.jsp" />
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
                                        <form id="searchLearner"  method="get">
                                            <div class="row">
                                                <div class="col-md-7 col-lg-8">
                                                    <div class="mb-3">
                                                        <label for="id">Username:</label>
                                                        <input type="text" name="userName" value="${userName}" id="userName" onclick="event.preventDefault();
                                                                return formSubmit()" class="form-control" placeholder="Username">
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
                                                </ol>
                                            </nav>
                                        </div>
                                        <!-- End Title -->
                                        <div class="row gy-5">
                                            <div class="col-lg-12">
                                                <div class="rbt-dashboard-table table-responsive" >
                                                    <p style="margin-bottom: 5px; color: blue">ACTIVE: </p>
                                                    <table class="rbt-table table table-borderless ">
                                                        <thead>
                                                            <tr>
                                                                <th>Username</th>
                                                                <th>Course Enrolled</th>
                                                                <th>Email</th>
                                                                <th>Phone Number</th>
                                                                <th>Joined Time</th>
                                                                <th>Status</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${learnerListActive}" var="learner">
                                                            <form action="${pageContext.request.contextPath}/manager/leaner/status/change" method="get">
                                                                <input type="hidden" name="id" value="${learner.id}"/>
                                                                <input type="hidden" name="status" value="${learner.status}">
                                                                <tr>
                                                                    <th>${learner.username}</th>
                                                                    <td>${learner.id}</td>
                                                                    <td>${learner.email}</td>
                                                                    <td>${learner.phoneNumber}</td>
                                                                    <td>${learner.createdAt}</td>
                                                                    <td><button class="btn btn-info" type="submit">${learner.status}</button></td>
                                                                </tr>
                                                            </form>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- End Title -->
                                        <div class="row gy-5">
                                            <div class="col-lg-12">
                                                <div class="rbt-dashboard-table table-responsive" style="margin-top: 20px">
                                                    <p style="margin-bottom: 5px; color: blue">BANNED: </p>
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
                                                            <c:forEach items="${learnerListBan}" var="learner">
                                                            <form action="${pageContext.request.contextPath}/manager/leaner/status/change" method="get">
                                                                <input type="hidden" name="id" value="${learner.id}"/>
                                                                <input type="hidden" name="status" value="${learner.status}">
                                                                <tr>
                                                                    <th>${learner.username}</th>
                                                                    <td>${learner.id}</td>
                                                                    <td>${learner.email}</td>
                                                                    <td>${learner.phoneNumber}</td>
                                                                    <td>${learner.createdAt}</td>
                                                                    <td><button class="btn btn-danger" type="submit">${learner.status}</button></td>
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
                            </div>
                            <!-- End Content -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/layout/footer.jsp" />
        <script type="text/javascript">
            const formSubmit = () => {
                let username = document.getElementById("userName").value;
                let status = document.getElementById("status").value;
                let id = document.getElementById("id").value;
                let url = "${pageContext.request.contextPath}/learner/search";
                let url2 = "${pageContext.request.contextPath}/learner/search";
                let firstParamSet = false;
                if (username !== "") {
                    if (firstParamSet === false) {
                        url = url + "?" + "userName=" + username;
                        firstParamSet = true;
                    } else {
                        url = url + "&" + "userName=" + username;
                    }
            }
            window.location.href = url;
            console.log("form submited");
            console.log("${pageContext.request.contextPath}/course/find");
        </script>
    </body>
    <jsp:include page="/layout/scripts.jsp"/>
</html>