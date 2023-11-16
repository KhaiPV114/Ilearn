<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!-- Start Side Vav -->
<div class="rbt-category-menu-wrapper">
    <div class="rbt-category-btn rbt-side-offcanvas-activation">
        <div class="rbt-offcanvas-trigger md-size icon">
            <span class="d-none d-xl-block">
                <i class="feather-grid"></i>
            </span>
            <i title="Category" class="feather-grid d-block d-xl-none"></i>
        </div>
        <span class="category-text d-none d-xl-block">Category</span>
    </div>

    <div class="category-dropdown-menu d-none d-xl-block">
        <div class="category-menu-item">
            <div class="rbt-vertical-nav">
                <ul class="rbt-vertical-nav-list-wrapper vertical-nav-menu">
                    <c:forEach var="category" items="${allCategories}">
                        <li class="vertical-nav-item">
                            <a href="#tab${category.id}">${category.name}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="rbt-vertical-nav-content">
                <c:forEach var="category" items="${allCategories}" varStatus="loop">
                    <!-- Start One Item -->
                    <div class="rbt-vertical-inner tab-content" id="tab${category.id}" style="display:block">
                        <div class="rbt-vertical-single">
                            <div class="row">
                                <div class="col-lg-12 col-sm-12 col-12">
                                    <div class="vartical-nav-content-menu">
                                        <h3 class="rbt-short-title">${category.name}</h3>
                                        <ul class="rbt-vertical-nav-list-wrapper">
                                            <c:set var="coursesByCategoryId" scope="request" value="${CourseService.getCourseByCategoryId(category.id)}" />
                                            <c:forEach var="course" items="${coursesByCategoryId}" varStatus="loop">
                                                <c:if test="${loop.count <= 3}">
                                                    <li><a href="#">${course.name}</a></li>
                                                    </c:if>
                                                </c:forEach>

                                        </ul>
                                        <div class="read-more-btn">
                                            <a class="rbt-btn-link" href="#">Learn More<i class="feather-arrow-right"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End One Item -->
                </c:forEach>
            </div>
        </div>
    </div>

</div>
<a class="rbt-close_side_menu" href="javascript:void(0);"></a>
<!-- End Side Vav -->

