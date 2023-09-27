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
                                            <!--<h4 class="rbt-title-style-3">Add course category</h4>-->
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
                                        <form action="./CouponSearch" method="post" enctype="multipart/form-data">
                                            <div class="row">
                                                <div class="col-md-7 col-lg-8">
                                                    <div class="mb-3">
                                                        <label for="id">Coupon ID:</label>
                                                        <input type="text" name="${id}" id="id" class="form-control"/>

                                                    </div>
                                                </div>
                                                <div class="col-md-7 col-lg-8">
                                                    <div class="mb-3">
                                                        <label for="id">Coupon Code:</label>
                                                        <input type="text" name="id" id="id" class="form-control"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <button type="submit" class="rbt-btn btn-md mt-3">Search</button>
                                        </form>
                                        <div class="Result">
                                            <table style="border: 1px solid black">
                                                Result
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Code</th>
                                                    <th>Percent</th>
                                                </tr>
                                                <c:forEach items="${coupons}" var = "coupon">
                                                    <tr>
                                                        <td>${coupon.id}</td>
                                                        <td>${coupon.code}</td>
                                                        <td>${coupon.percent}</td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- End Content -->

                            <jsp:include page="/layout/scripts.jsp"></jsp:include>
                                </body>
                                <footer>
                                <jsp:include page="/layout/footer.jsp" />
                            </footer>
                            </html>