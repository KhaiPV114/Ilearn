<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="/layout/links.jsp"/>
        <style>
            .resultTable td,th{
                padding-left: 10px;
                padding-right: 10px
            }
        </style>
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
                                                            Coupon
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
                                        <form action="${pageContext.request.contextPath}/manager/coupon/search" method="post">
                                            <div class="row">
                                                <div class="col-md-7 col-lg-8">
                                                    <div class="mb-3">
                                                        <label for="id">Course name:</label>
                                                        <input type="text" name="courseName" id="courseName" class="form-control" placeholder="ID"/>
                                                    </div>
                                                </div>
                                                <div class="col-md-7 col-lg-8">
                                                    <div class="mb-3">
                                                        <label for="id">Coupon Code:</label>
                                                        <input type="text" name="code" id="code" class="form-control" placeholder="Code"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <button type="submit" class="rbt-btn btn-md mt-3">Search</button>
                                            <a class="rbt-btn btn-md hover-icon-reverse mt-3" href="${pageContext.request.contextPath}/manager/coupon/create">Create new coupon</a>
                                        </form>

                                    </div>
                                </div>
                                <div class="row gy-5">
                                    <div class="col-lg-12">
                                        <div class="rbt-dashboard-table table-responsive" >
                                            <p style="margin-bottom: 5px; color: blue">ACTIVE: </p>
                                            <table class="rbt-table table table-borderless ">
                                                <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Course ID</th>
                                                    <th>Code</th>
                                                    <th>Percent</th>
                                                    <th>Quantity</th>
                                                    <th>Remain Quantity</th>
                                                    <th>Time created</th>
                                                    <th>Time start</th>
                                                    <th>Time end</th>
                                                    <th>Status</th>
                                                    <th></th>
                                                </tr>
                                                </thead>
                                                <c:forEach items="${coupons}" var = "coupon">
                                                    <form action="${pageContext.request.contextPath}/manager/coupon/edit" method="get">
                                                        <input type="hidden" name="couponId" value="${coupon.id}">
                                                        <tr>
                                                            <td>${coupon.id}</td>
                                                            <td>${coupon.courseId}</td>
                                                            <td>${coupon.code}</td>
                                                            <td>${coupon.percent}</td>
                                                            <td>${coupon.quantity}</td>
                                                            <td>${coupon.remainQuantity}</td>
                                                            <td>${coupon.createdAt}</td>
                                                            <td>${coupon.startTime}</td>
                                                            <td>${coupon.endTime}</td>
                                                            <td>${coupon.status}</td>
                                                            <td><button class="btn btn-info" type="submit">Edit</button></td>
                                                        </tr>
                                                    </form>
                                                </c:forEach>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- End Content -->
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="/layout/footer.jsp" />
    </body>
    <jsp:include page="/layout/scripts.jsp"/>
</html>