<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="CategoryService" scope="request" class="com.onlinelearning.Services.Impl.CategoryServiceImpl"/>
<jsp:useBean id="CourseService" scope="request" class="com.onlinelearning.Services.Impl.CourseServiceImpl"/>

<c:set var="allCategories" scope="request" value="${CategoryService.getAllCategories()}" />
<%@page import = "com.onlinelearning.Models.Role" %>
<jsp:useBean id="roleService" scope="request" class="com.onlinelearning.Services.Impl.RoleService" />
<jsp:useBean id="CartService" scope="request" class="com.onlinelearning.Services.Impl.CartServiceImpl" />

<c:set var="coursesInCart" scope="session" value="${CartService.getCourseInCart(pageContext.request, pageContext.response)}" />

<header class="rbt-header rbt-header-10">
    <div class="rbt-sticky-placeholder"></div>
    <div class="rbt-header-wrapper header-space-betwween header-sticky">
        <div class="container-fluid">
            <div class="mainbar-row rbt-navigation-center align-items-center">
                <!-- Left Header -->
                <div class="header-left rbt-header-content">
                    <!-- Logo -->
                    <div class="header-info">
                        <div class="logo mx-3" >
                            <a href="${pageContext.request.contextPath}/" style="width: 100px">
                                <img src="${pageContext.request.contextPath}/assets/images/logo/logo.png"
                                     alt="Education Logo Images" />
                            </a>
                        </div>
                    </div>
                    <!-- Category Dropdown -->
                    <div class="header-info">
                        <jsp:include page="/layout/header/category-dropdown.jsp" />
                    </div>
                </div>
                <!-- Main Navigation -->
                <div class="rbt-main-navigation d-none d-xl-block">
                    <nav class="mainmenu-nav">
                        <ul class="mainmenu">

                            <!-- Home Dropdown -->
                            <%--<jsp:include page="/layout/header/home-dropdown.jsp" />--%>
                            <!-- Course Dropdown -->
                            <%--<jsp:include page="/layout/header/course-dropdown.jsp" />--%>
                            <!-- Dashboard Dropdown -->
                            <!--                            <li class="has-dropdown has-menu-child-item">
                                                            <a href="#">
                                                                Dashboard<i class="feather-chevron-down"></i>
                                                            </a>
                            
                                                            <ul class="submenu">
                                                                <li class="has-dropdown"><a href="#">Instructor Dashboard</a>
                                                                    <ul class="submenu">
                                                                        <li><a href="instructor-dashboard.html">Dashboard</a></li>
                                                                        <li><a href="instructor-profile.html">Profile</a></li>
                                                                        <li><a href="instructor-enrolled-courses.html">Enrolled
                                                                                Courses</a></li>
                                                                        <li><a href="instructor-wishlist.html">Wishlist</a></li>
                                                                        <li><a href="instructor-reviews.html">Reviews</a></li>
                                                                        <li><a href="instructor-my-quiz-attempts.html">My Quiz
                                                                                Attempts</a></li>
                                                                        <li><a href="instructor-order-history.html">Order History</a>
                                                                        </li>
                                                                        <li><a href="instructor-course.html">My Course</a></li>
                                                                        <li><a href="instructor-announcements.html">Announcements</a>
                                                                        </li>
                                                                        <li><a href="instructor-quiz-attempts.html">Quiz Attempts</a>
                                                                        </li>
                                                                        <li><a href="instructor-assignments.html">Assignments</a></li>
                                                                        <li><a href="instructor-settings.html">Settings</a></li>
                                                                    </ul>
                                                                </li>
                                                                <li class="has-dropdown"><a href="#">Student Dashboard</a>
                                                                    <ul class="submenu">
                                                                        <li><a href="student-dashboard.html">Dashboard</a></li>
                                                                        <li><a href="student-profile.html">Profile</a></li>
                                                                        <li><a href="student-enrolled-courses.html">Enrolled Courses</a>
                                                                        </li>
                                                                        <li><a href="student-wishlist.html">Wishlist</a></li>
                                                                        <li><a href="student-reviews.html">Reviews</a></li>
                                                                        <li><a href="student-my-quiz-attempts.html">My Quiz Attempts</a>
                                                                        </li>
                                                                        <li><a href="student-order-history.html">Order History</a></li>
                                                                        <li><a href="student-settings.html">Settings</a></li>
                                                                    </ul>
                                                                </li>
                                                            </ul>
                                                        </li>-->
                            <!-- Pages Dropdown -->
                        </ul>
                    </nav>
                </div>
                <!-- Right Header -->
                <div class="header-right">
                    <!-- Navbar Icons -->
                    <ul class="quick-access">
                        <!-- Search -->
                        <!--                        <li class="access-icon">
                                                    <a class="search-trigger-active rbt-round-btn" href="#">
                                                        <i class="feather-search"></i>
                                                    </a>
                                                </li>-->
                        <!-- Cart -->
                        <c:if test="${roleService.isLearner(pageContext.request)}">
                            <li class="access-icon rbt-mini-cart">
                                <a class="rbt-cart-sidenav-activation rbt-round-btn" href="javascript:void(0);" id="open-cart-side-menu">
                                    <i class="feather-shopping-cart"></i>
                                    <c:if test="${coursesInCart.size()>0}">
                                        <span class="rbt-cart-count" id="cart-side-quantity">${coursesInCart.size()}</span>
                                    </c:if>
                                </a>
                            </li>
                        </c:if>

                        <!-- User Dropdown-->
                        <!--<li class="mx-2">
                        ${sessionScope['roles']}
                        </li>-->
                        <c:choose>
                            <c:when test="${sessionScope['user'] == null}">
                                <li class="account-access rbt-user-wrapper d-none d-xl-block">
                                    <a href="${pageContext.request.contextPath}/authentication"><i class="feather-log-in"></i>Login</a> 
                                </li>
                            </c:when>
                            <c:when test="${roleService.isLearner(pageContext.request)}">
                                <jsp:include page="/layout/header/learner-dropdown.jsp" />
                            </c:when>
                            <c:when test="${roleService.isInstructor(pageContext.request)}">
                                <jsp:include page="/layout/header/instructor-dropdown.jsp" />
                            </c:when>
                            <c:when test="${roleService.isManager(pageContext.request)}">
                                <jsp:include page="/layout/header/manager-dropdown.jsp" />
                            </c:when>
                        </c:choose>
                    </ul>
                    <div class="rbt-btn-wrapper d-none d-xl-block ms-5">
                        <a class="rbt-btn rbt-marquee-btn marquee-auto btn-border-gradient radius-round btn-sm hover-transform-none"
                           href="javascript:void(0);">
                            <span data-text="Enroll now">Enroll now</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>    
</header>

<!-- USE FOR MOBILE -->
<jsp:include page="/layout/header/mobile-menu-side.jsp" />

<!-- Cart Side -->
<jsp:include page="/layout/header/cart-side.jsp" /> 
