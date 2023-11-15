<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="col-lg-3">
    <!-- Start Dashboard Sidebar  -->
    <div class="rbt-default-sidebar sticky-top rbt-shadow-box rbt-gradient-border">
        <div class="inner">
            <div class="content-item-content">

                <div class="rbt-default-sidebar-wrapper">
                    <div class="section-title mb--20">
                        <h6 class="rbt-title-style-2">Welcome, ${user.username}</h6>
                    </div>
                    <nav class="mainmenu-nav">
                        <ul class="dashboard-mainmenu rbt-default-sidebar-list">
                            <li>
                                <a href="${pageContext.request.contextPath}/manager/dashboard" class="active">
                                    <i class="feather-home"></i><span>Dashboard</span>
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/manager/profile">
                                    <i class="feather-user"></i><span>My Profile</span>
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/manager/category">
                                    <i class="feather-grid"></i><span>Manage Category</span>
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/manager/order">
                                    <i class="feather-shopping-bag"></i><span>Manager Order</span>
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/manager/earning">
                                    <i class="feather-dollar-sign"></i><span>Total Earnings</span>
                                </a>
                            </li>
                            <li>
                                <a href="instructor-quiz-attempts.html">
                                    <i class="feather-message-square"></i><span>Quiz Attempts</span>
                                </a>
                            </li>
                            <li>
                                <a href="instructor-assignments.html">
                                    <i class="feather-list"></i><span>Assignments</span>
                                </a>
                            </li>
                        </ul>
                    </nav>

                    <div class="section-title mt--40 mb--20">
                        <h6 class="rbt-title-style-2">User</h6>
                    </div>

                    <nav class="mainmenu-nav">
                        <ul class="dashboard-mainmenu rbt-default-sidebar-list">
                            <li><a href="instructor-settings.html"><i class="feather-settings"></i><span>Settings</span></a></li>
                            <li><a href="index.html"><i class="feather-log-out"></i><span>Logout</span></a></li>
                        </ul>
                    </nav>
                </div>

            </div>
        </div>
    </div>
    <!-- End Dashboard Sidebar  -->
</div>