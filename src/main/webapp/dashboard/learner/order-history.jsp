<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="OrderService" scope="request" class="com.onlinelearning.Services.Impl.OrderServiceImpl" />
<jsp:useBean id="CourseService" scope="request" class="com.onlinelearning.Services.Impl.CourseServiceImpl" />

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order History</title>
        <jsp:include page="/layout/links.jsp"/>
        <link href="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.css" rel="stylesheet">
    </head>
    <body class="rbt-header-sticky">
        <jsp:include page="/layout/header.jsp"/>
        <jsp:include page="/layout/dashboard-header.jsp"/>
        <div class="rbt-dashboard-area rbt-section-overlayping-top">
            <div class="container mb-5">
                <div class="row">
                    <div class="col-lg-12">
                        <jsp:include page="/layout/dashboard-learner-card.jsp"/>
                        <div class="row g-5">
                            <jsp:include page="/layout/dashboard-learner-sidebar.jsp"/>
                            <!-- Start Content  -->
                            <div class="col-lg-9">

                                <c:if test="${unpaidOrder!=null}">
                                    <div class="rbt-dashboard-content bg-color-white rbt-shadow-box mb-5">
                                        <div class="content">
                                            <div class="section-title">
                                                <h4 class="rbt-title-style-3">Unpaid Order</h4>
                                            </div>
                                            <div class="rbt-dashboard-table table-responsive mobile-table-750" id="content-display">
                                                <table class="rbt-table table table-borderless" id="unfinish-order-table" data-page-length="10">
                                                    <thead>
                                                        <tr>
                                                            <th>Order ID</th>
                                                            <th>Course Name</th>
                                                            <th>Date</th>
                                                            <th>Price</th>
                                                            <th>Status</th>
                                                            <th>Actions</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td><b>#${unpaidOrder.id}</b></td>
                                                            <td>
                                                                <c:set var="price" value="${0}"/>
                                                                <c:forEach var="orderItem" items="${OrderService.getOrderItemsByOrderId(unpaidOrder.id)}">
                                                                    <c:set var="course" value="${CourseService.getCourseById(orderItem.courseId)}"/>
                                                                    <a href="${pageContext.request.contextPath}/course/${course.id}">${course.name}</a><br/>
                                                                    <c:set var="price" value="${price + orderItem.price}"/>
                                                                </c:forEach>
                                                            </td>
                                                            <td>
                                                                <fmt:parseDate value="${unpaidOrder.createdAt}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                                                                <fmt:formatDate pattern="dd/MM/yyyy 'at' HH:mm" value="${parsedDateTime}" />
                                                            </td>
                                                            <td>$${String.format("%.2f", price)}</td>
                                                            <td>
                                                                <span class="rbt-badge-5 bg-color-warning-opacity color-warning">On Hold</span>
                                                            </td>
                                                            <td>
                                                                <div class="rbt-button-group d-flex flex-column">
                                                                    <div>
                                                                        <form action="${pageContext.request.contextPath}/cart/checkout/continue" method="post" id="continue-order-form">
                                                                            <input type="hidden" name="order-id" value="${unpaidOrder.id}">
                                                                            <a class="rbt-btn-link left-icon" href="javascript: void(0);" style='color: blue;' onclick="document.getElementById('continue-order-form').submit();"><i class="feather-play"></i>Continue</a>
                                                                        </form>
                                                                    </div>
                                                                    <div>
                                                                        <form action="${pageContext.request.contextPath}/cart/checkout/cancel" method="post" id="cancel-order-form">
                                                                            <input type="hidden" name="order-id" value="${unpaidOrder.id}">
                                                                            <input type="hidden" name="view-order-history" value="true">
                                                                            <a class="rbt-btn-link left-icon" href="javascript:void(0);" data-bs-dismiss="modal" onclick="document.getElementById('cancel-order-form').submit();">     
                                                                                <i class="feather-trash-2"></i> Cancel
                                                                            </a>
                                                                        </form>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>

                                <div class="rbt-dashboard-content bg-color-white rbt-shadow-box">
                                    <div class="content">
                                        <div class="section-title">
                                            <h4 class="rbt-title-style-3">Order History</h4>
                                        </div>

                                        <div class="rbt-dashboard-table table-responsive mobile-table-750" id="content-display">
                                            <c:if test="${orders!=null && !orders.isEmpty()}">
                                                <table class="rbt-table table table-borderless" id="order-table" data-page-length="10">
                                                    <thead>
                                                        <tr>
                                                            <th>Order ID</th>
                                                            <th>Course Name</th>
                                                            <th>Date</th>
                                                            <th>Price</th>
                                                            <th>Status</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach var="order" items="${orders}">
                                                            <tr>
                                                                <td><b>#${order.id}</b></td>
                                                                <td>
                                                                    <c:set var="price" value="${0.0}"/>
                                                                    <c:forEach var="orderItem" items="${OrderService.getOrderItemsByOrderId(order.id)}">
                                                                        <c:set var="course" value="${CourseService.getCourseById(orderItem.courseId)}"/>
                                                                        <a href="${pageContext.request.contextPath}/course/${course.id}">${course.name}</a> <br/>
                                                                        <c:set var="price" value="${price + orderItem.price}"/>
                                                                    </c:forEach>
                                                                </td>
                                                                <td>
                                                                    <fmt:parseDate value="${order.createdAt}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                                                                    <fmt:formatDate pattern="dd/MM/yyyy 'at' HH:mm" value="${parsedDateTime}" />
                                                                </td>
                                                                <td>$${String.format("%.2f", price)}</td>
                                                                <td>
                                                                    <c:choose>
                                                                        <c:when test="${order.status=='UNPAID'}">
                                                                            <span class="rbt-badge-5 bg-color-warning-opacity color-warning">On Hold</span>
                                                                        </c:when>
                                                                        <c:when test="${order.status == 'SUCCESSFUL'}">
                                                                            <span class="rbt-badge-5 bg-color-success-opacity color-success">Success</span>
                                                                        </c:when>
                                                                        <c:when test="${order.status=='FAILED'}">
                                                                            <span class="rbt-badge-5 bg-color-danger-opacity color-danger">Failed</span>
                                                                        </c:when>
                                                                    </c:choose>
                                                                </td>          
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </c:if>
                                            <c:if test="${orders==null || orders.isEmpty()}">
                                                <p style="color: red; text-align: center;">You haven't place any order</p>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <!-- End Content  -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/layout/footer.jsp"/>
        <jsp:include page="/layout/scripts.jsp"/>
        <script src="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.js"></script>
        <script>
                                                                                $(document).ready(function () {
                                                                                    $('#order-table').DataTable({
                                                                                        "order": [0, 'desc'],
                                                                                        "lengthChange": false,
                                                                                        "searching": false
                                                                                    });
                                                                                });
        </script>
    </body>
</html>