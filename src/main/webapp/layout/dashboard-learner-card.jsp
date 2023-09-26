<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Start Dashboard Top  -->
<div class="rbt-dashboard-content-wrapper">
    <div class="tutor-bg-photo bg_image bg_image--23 height-350"></div>
    <!-- Start Tutor Information  -->
    <div class="rbt-tutor-information">
        <div class="rbt-tutor-information-left">
            <div class="thumbnail rbt-avatars size-lg">
                <img src="${pageContext.request.contextPath}/assets/images/team/avatar-2.jpg" alt="Instructor">
            </div>
            <div class="tutor-content">
                <h5 class="title">Emily Hannah</h5>
                <ul class="rbt-meta rbt-meta-white mt--5">
                    <li><i class="feather-book"></i>5 Courses Enroled</li>
                    <li><i class="feather-award"></i>4 Certificate</li>
                </ul>
            </div>
        </div>
        <div class="rbt-tutor-information-right">
            <div class="tutor-btn">
                <a class="rbt-btn btn-md hover-icon-reverse" href="#">
                    <span class="icon-reverse-wrapper">
                        <span class="btn-text">Become an Instructor</span>
                        <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                        <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                    </span>
                </a>
            </div>
        </div>
    </div>
    <!-- End Tutor Information  -->
</div>
<!-- End Dashboard Top  -->
