<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="OrderService" scope="request" class="com.onlinelearning.Services.Impl.OrderServiceImpl" />
<jsp:useBean id="CourseService" scope="request" class="com.onlinelearning.Services.Impl.CourseServiceImpl" />

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="/layout/links.jsp"/>
        <link href="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.css" rel="stylesheet">
    </head>
    <body class="rbt-header-sticky">
        <jsp:include page="/layout/header.jsp"/>
        <jsp:include page="/layout/dashboard-header.jsp"/>
        <div class="rbt-dashboard-area rbt-section-overlayping-top rbt-section-gapBottom">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <jsp:include page="/layout/dashboard-learner-card.jsp"/>
                        <div class="row g-5">
                            <jsp:include page="/layout/dashboard-learner-sidebar.jsp"/>
                            <!-- Start Content  -->
                            <div class="col-lg-9">
                                <div class="rbt-dashboard-content bg-color-white rbt-shadow-box">
                                    <div class="content">
                                        <div class="section-title">
                                            <h4 class="rbt-title-style-3">Order History</h4>
                                        </div>

                                        <div class="rbt-dashboard-table table-responsive mobile-table-750" id="content-display">
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
                                                            <th>#${order.id}</th>
                                                            <td>
                                                                <c:set var="price" value="${0}"/>
                                                                <c:forEach var="orderItem" items="${OrderService.getCoursesOfOrderByOrderId(order.id)}">
                                                                    - ${CourseService.getCourseById(orderItem.courseId).name}<br/>
                                                                    <c:set var="price" value="${price + orderItem.price}"/>
                                                                </c:forEach>
                                                            </td>

                                                            <td>
                                                                <fmt:parseDate value="${order.createdAt}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                                                                <fmt:formatDate pattern="dd/MM/yyyy 'at' HH:mm" value="${parsedDateTime}" />
                                                            </td>
                                                            <td>$${price}</td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${order.status=='NEW'}">
                                                                        <span class="rbt-badge-5 bg-color-warning-opacity color-warning">On Hold</span>
                                                                    </c:when>
                                                                    <c:when test="${order.status =='PENDING'}">
                                                                        <span class="rbt-badge-5 bg-primary-opacity">Pending</span>  
                                                                    </c:when>
                                                                    <c:when test="${order.status == 'SUCCESSFUL' || order.status=='DONE'}">
                                                                        <span class="rbt-badge-5 bg-color-success-opacity color-success">Success</span>
                                                                    </c:when>
                                                                    <c:when test="${order.status=='FAILED' || order.status=='REJECTED'}">
                                                                        <span class="rbt-badge-5 bg-color-danger-opacity color-danger">Failed</span>
                                                                    </c:when>
                                                                    <c:when test="${order.status=='EXPIRED'}">
                                                                        <span class="rbt-badge-5 bg-color-danger-opacity color-danger">Expired</span>
                                                                    </c:when>
                                                                </c:choose>
                                                            </td>          
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <!-- End Content  -->
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="/layout/footer.jsp"/>
            <jsp:include page="/layout/delayScrollToContent.jsp"/>
    </body>
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
</html>