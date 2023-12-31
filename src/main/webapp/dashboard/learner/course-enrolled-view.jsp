<%@page contentType="text/html" pageEncoding="UTF-8" buffer="8192kb" autoFlush="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enrolled Courses</title>
        <jsp:include page="/layout/links.jsp" />
        <style>
            .truncate-4 {
                display: -webkit-box;
                -webkit-line-clamp: 4;
                -webkit-box-orient: vertical;
                overflow: hidden;
            }
            .truncate-5 {
                display: -webkit-box;
                -webkit-line-clamp: 5;
                -webkit-box-orient: vertical;
                overflow: hidden;
            }
            .truncate-6 {
                display: -webkit-box;
                -webkit-line-clamp: 6;
                -webkit-box-orient: vertical;
                overflow: hidden;
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
                        <jsp:include page="/layout/dashboard-learner-card.jsp" />
                        <div class="row g-5">
                            <jsp:include page="/layout/dashboard-learner-sidebar.jsp" />
                            <!-- Start Content  -->
                            <div class="col-lg-9" id="content">
                                <div class="rbt-dashboard-content bg-color-white rbt-shadow-box">
                                    <div class="content">
                                        <!-- Start Title -->
                                        <div class="section-title">
                                            <!--<h4 class="rbt-title-style-3">Add course</h4>-->
                                            <nav class="rbt-title-style-3 h4 pb-0" aria-label="breadcrumb">
                                                <ol class="breadcrumb">
                                                    <li class="breadcrumb-item">
                                                        <a href="#">
                                                            Course
                                                        </a>
                                                    </li>
                                                    <li class="breadcrumb-item active" aria-current="page">
                                                        <a class="color-primary" href="#"> <!--link vào phần course categories-->
                                                            Enrolled Courses
                                                        </a>
                                                    </li>
                                                </ol>
                                            </nav>
                                        </div>
                                        <!-- End Title -->

                                        <div class="tutor-btn">
                                            <a class="rbt-btn btn-md hover-icon-reverse" href="${pageContext.request.contextPath}/search" >
                                                <span class="icon-reverse-wrapper">
                                                    <span class="btn-text">Learn a New Course</span>
                                                    <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                                    <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                                </span>
                                            </a>
                                        </div>

                                        <div class="rbt-dashboard-table table-responsive mobile-table-750 mt--30">
                                            <table class="rbt-table table table-borderless">
                                                <thead>
                                                    <tr>
                                                        <th>#</th>
                                                        <th style="width: 20%;">Course Name</th>
                                                        <th style="width: 25%;">Description</th>
                                                        <th>Image</th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody> 
                                                    <c:forEach var="enrolledCourse" items="${enrolledCourses}" varStatus="loop">
                                                        <tr>
                                                            <td>${loop.index + 1}</td>
                                                            <td>
                                                                <a style="font-weight: bolder; color: black">
                                                                    ${enrolledCourse.name}
                                                                </a>
                                                                
                                                            </td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${fn:length(enrolledCourse.description) <= 120}"> <!-- Điều kiện để kiểm tra độ dài -->
                                                                        ${enrolledCourse.description}
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        ${fn:substring(enrolledCourse.description, 0, 120)}... <!-- Giới hạn số ký tự -->
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>

                                                            <td>
                                                                <!-- Hiển thị hình ảnh của khóa học nếu có -->
                                                                <img src="${enrolledCourse.imageUrl}" alt="image"/>
                                                            </td>
                                                            
                                                            <td>
                                                                <div class="tutor-btn">
                                                                    <a class="rbt-btn btn-md hover-icon-reverse mt-3"
                                                                       href="${pageContext.request.contextPath}/learn?courseId=${enrolledCourse.id}" >
                                                                        <span class="icon-reverse-wrapper">
                                                                            <span class="btn-text">Learn</span>
                                                                            <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                                                            <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                                                        </span>
                                                                    </a>
                                                                </div>
                                                            </td>
                                                            
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>

                                            </table>
                                        </div>

                                        <div class="d-flex mt-3" id="pageBar"></div>

                                        <!--                                        Delete via post method                                                    -->
                                        <form id="deletion-form" action="${pageContext.request.contextPath}/learner/course/delete" method="post">
                                            <input id="deletion-id" name="id" value="" type="hidden">
                                        </form>

                                    </div>
                                </div>
                            </div>
                            <!-- End Content  -->
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="/layout/footer.jsp" />
        <jsp:include page="/layout/scripts.jsp" />

    </body>
</html>