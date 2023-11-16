<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<li class="account-access rbt-user-wrapper d-none d-xl-block">
        <a href="${pageContext.request.contextPath}/#"><i class="feather-user"></i>${user.fullName}</a>
        <div class="rbt-user-menu-list-wrapper">
            <div class="inner">
                <div class="rbt-admin-profile">
                    <div class="admin-thumbnail">
                        <img src="${pageContext.request.contextPath}/assets/images/team/avatar.jpg"
                             alt="User Images">
                    </div>
                    <div class="admin-info">
                        <span class="name">${user.username}</span>
                        <a class="rbt-btn-link color-primary" href="${pageContext.request.contextPath}/manager/profile">
                            View Profile
                        </a>
                    </div>
                </div>
                <ul class="user-list-wrapper">
                    <li>
                        <a href="${pageContext.request.contextPath}/manager/dashboard">
                            <i class="feather-home"></i>
                            <span>My Dashboard</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/manager/learner/search">
                            <i class="feather-users"></i>
                            <span>Manage learner</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/manager/category">
                            <i class="feather-book-open"></i>
                            <span>Manage Category</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/manager/order">
                            <i class="feather-shopping-bag"></i>
                            <span>Manage Order</span>
                        </a>
                    </li>
                </ul>
                <hr class="mt--10 mb--10">
                <ul class="user-list-wrapper">
                    <li>
                        <a href="${pageContext.request.contextPath}/manager/change-password">
                            <i class="feather-settings"></i>
                            <span>Change Password</span>
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
                        <a href="${pageContext.request.contextPath}/manager/order">
                            <i class="feather-shopping-bag"></i>
                            <span>Manager Order</span>
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
                    <a href="${pageContext.request.contextPath}/authentication/logout">
                        <i class="feather-log-out"></i>
                        <span>Logout</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</li>
