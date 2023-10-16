<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<li class="account-access rbt-user-wrapper d-none d-xl-block">
        <a href="${pageContext.request.contextPath}/#"><i class="feather-user"></i>${user.username}</a>
        <div class="rbt-user-menu-list-wrapper">
            <div class="inner">
                <div class="rbt-admin-profile">
                    <div class="admin-thumbnail">
                        <img src="${pageContext.request.contextPath}/assets/images/team/avatar.jpg"
                             alt="User Images">
                    </div>
                    <div class="admin-info">
                        <span class="name">${user.username}</span>
                        <a class="rbt-btn-link color-primary" href="${pageContext.request.contextPath}/profile.html">View
                            Profile</a>
                    </div>
                </div>
                <ul class="user-list-wrapper">
                    <li>
                        <a href="${pageContext.request.contextPath}/instructor-dashboard.html">
                            <i class="feather-home"></i>
                            <span>My Dashboard</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/instructor-reviews.html">
                            <i class="feather-star"></i>
                            <span>Reviews</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/instructor-my-quiz-attempts.html">
                            <i class="feather-list"></i>
                            <span>My Quiz Attempts</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/dashboard/learner/order/history">
                            <i class="feather-clock"></i>
                            <span>Order History</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/instructor-quiz-attempts.html">
                            <i class="feather-message-square"></i>
                            <span>Question &amp; Answer</span>
                        </a>
                    </li>
                </ul>
                <hr class="mt--10 mb--10">
                <ul class="user-list-wrapper">
                    <li>
                        <a href="${pageContext.request.contextPath}/instructor-settings.html">
                            <i class="feather-settings"></i>
                            <span>Settings</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/authentication/logout">
                            <i class="feather-log-out"></i>
                            <span>Logout</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
</li>

<!--Use for mobile-->
<li class="access-icon rbt-user-wrapper d-block d-xl-none">
    <a class="rbt-round-btn" href="${pageContext.request.contextPath}/#"><i class="feather-user"></i></a>
    <div class="rbt-user-menu-list-wrapper">
        <div class="inner">
            <div class="rbt-admin-profile">
                <div class="admin-thumbnail">
                    <img src="${pageContext.request.contextPath}/assets/images/team/avatar.jpg"
                         alt="User Images">
                </div>
                <div class="admin-info">
                    <span class="name">Nipa Bali</span>
                    <a class="rbt-btn-link color-primary" href="${pageContext.request.contextPath}/profile.html">View
                        Profile</a>
                </div>
            </div>
            <ul class="user-list-wrapper">
                <li>
                    <a href="${pageContext.request.contextPath}/instructor-dashboard.html">
                        <i class="feather-home"></i>
                        <span>My Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/#">
                        <i class="feather-bookmark"></i>
                        <span>Bookmark</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/instructor-enrolled-courses.html">
                        <i class="feather-shopping-bag"></i>
                        <span>Enrolled Courses</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/instructor-wishlist.html">
                        <i class="feather-heart"></i>
                        <span>Wishlist</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/instructor-reviews.html">
                        <i class="feather-star"></i>
                        <span>Reviews</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/instructor-my-quiz-attempts.html">
                        <i class="feather-list"></i>
                        <span>My Quiz Attempts</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/instructor-order-history.html">
                        <i class="feather-clock"></i>
                        <span>Order History</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/instructor-quiz-attempts.html">
                        <i class="feather-message-square"></i>
                        <span>Question &amp; Answer</span>
                    </a>
                </li>
            </ul>
            <hr class="mt--10 mb--10">
            <ul class="user-list-wrapper">
                <li>
                    <a href="${pageContext.request.contextPath}/#">
                        <i class="feather-book-open"></i>
                        <span>Getting Started</span>
                    </a>
                </li>
            </ul>
            <hr class="mt--10 mb--10">
            <ul class="user-list-wrapper">
                <li>
                    <a href="${pageContext.request.contextPath}/instructor-settings.html">
                        <i class="feather-settings"></i>
                        <span>Settings</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/index.html">
                        <i class="feather-log-out"></i>
                        <span>Logout</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</li>
