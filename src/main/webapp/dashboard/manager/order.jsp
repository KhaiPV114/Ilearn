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
                        <jsp:include page="/layout/dashboard-manager-card.jsp"/>
                        <div class="row g-5">
                            <jsp:include page="/layout/dashboard-manager-sidebar.jsp"/>
                            <!-- Start Content  -->
                            <div class="col-lg-9">
                                <div class="rbt-dashboard-content bg-color-white rbt-shadow-box">
                                    <div class="content">

                                        <div class="section-title">
                                            <h4 class="rbt-title-style-3">Pending Order</h4>
                                        </div>

                                        <div class="advance-tab-button mb--30">
                                            <ul class="nav nav-tabs tab-button-style-2 justify-content-start" id="myTab-4" role="tablist">
                                                <li role="presentation">
                                                    <a href="#" class="tab-button active" id="pending-tab-4" data-bs-toggle="tab" data-bs-target="#pending-4" role="tab" aria-controls="pending-4" aria-selected="true">
                                                        <span class="title">Pending</span>
                                                    </a>
                                                </li>
                                                <li role="presentation">
                                                    <a href="#" class="tab-button" id="failed-tab-4" data-bs-toggle="tab" data-bs-target="#failed-4" role="tab" aria-controls="failed-4" aria-selected="false">
                                                        <span class="title">Failed</span>
                                                    </a>
                                                </li>
                                                <li role="presentation">
                                                    <a href="#" class="tab-button" id="successful-tab-4" data-bs-toggle="tab" data-bs-target="#successful-4" role="tab" aria-controls="successful-4" aria-selected="false">
                                                        <span class="title">Successful</span>
                                                    </a>
                                                </li>
                                                <li role="presentation">
                                                    <a href="#" class="tab-button" id="rejected-tab-4" data-bs-toggle="tab" data-bs-target="#rejected-4" role="tab" aria-controls="rejected-4" aria-selected="false">
                                                        <span class="title">Rejected</span>
                                                    </a>
                                                </li>
                                                <li role="presentation">
                                                    <a href="#" class="tab-button" id="all-tab-4" data-bs-toggle="tab" data-bs-target="#all-4" role="tab" aria-controls="all-4" aria-selected="false">
                                                        <span class="title">All</span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="tab-content">
                                            <div class="tab-pane fade active show" id="pending-4" role="tabpanel" aria-labelledby="pending-tab-4">
                                                <div class="rbt-dashboard-table table-responsive mobile-table-750" id="content-display">
                                                    <table class="rbt-table table table-borderless" id="pending-order-table" data-page-length="10">
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
                                                            <c:forEach var="order" items="${pendingOrders}">
                                                                <tr>
                                                                    <th>#${order.id}</th>
                                                                    <td>
                                                                        <c:set var="price" value="${0}"/>
                                                                        <c:forEach var="orderItem" items="${OrderService.getOrderItemsByOrderId(order.id)}">
                                                                            <span>- ${CourseService.getCourseById(orderItem.courseId).name}</span><br/>
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
                                                                                <span class="rbt-badge-5 bg-color-warning-opacity color-warning">New</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status =='PENDING'}">
                                                                                <span class="rbt-badge-5 bg-primary-opacity">Pending</span>  
                                                                            </c:when>
                                                                            <c:when test="${order.status == 'SUCCESSFUL'}">
                                                                                <span class="rbt-badge-5 bg-color-success-opacity color-success">Success</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status=='DONE'}">
                                                                                <span class="rbt-badge-5 bg-color-success-opacity color-success">Done</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status=='FAILED'}">
                                                                                <span class="rbt-badge-5 bg-color-danger-opacity color-danger">Failed</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status=='REJECTED'}">
                                                                                <span class="rbt-badge-5 bg-color-danger-opacity color-danger">Rejected</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status=='EXPIRED'}">
                                                                                <span class="rbt-badge-5 bg-color-danger-opacity color-danger">Expired</span>
                                                                            </c:when>
                                                                        </c:choose>
                                                                    </td>     
                                                                    <td>
                                                                        <button type="button" class="btn btn-success btn-lg" style="width: 100%;" onclick="acceptOrder(${order.id})">Accept</button>

                                                                        <button type="button" class="btn btn-danger btn-lg" style="width: 100%; margin-top: 5px;" onclick="rejectOrder(${order.id})">Reject</button>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>                
                                            </div>
                                            <div class="tab-pane fade" id="failed-4" role="tabpanel" aria-labelledby="failed-tab-4">
                                                <div class="rbt-dashboard-table table-responsive mobile-table-750">
                                                    <table class="rbt-table table table-borderless" id="failed-order-table" data-page-length="10">
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
                                                            <c:forEach var="order" items="${failedOrders}">
                                                                <tr>
                                                                    <th>#${order.id}</th>
                                                                    <td>
                                                                        <c:set var="price" value="${0}"/>
                                                                        <c:forEach var="orderItem" items="${OrderService.getOrderItemsByOrderId(order.id)}">
                                                                            <span>- ${CourseService.getCourseById(orderItem.courseId).name}</span><br/>
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
                                                                                <span class="rbt-badge-5 bg-color-warning-opacity color-warning">New</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status =='PENDING'}">
                                                                                <span class="rbt-badge-5 bg-primary-opacity">Pending</span>  
                                                                            </c:when>
                                                                            <c:when test="${order.status == 'SUCCESSFUL'}">
                                                                                <span class="rbt-badge-5 bg-color-success-opacity color-success">Success</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status=='DONE'}">
                                                                                <span class="rbt-badge-5 bg-color-success-opacity color-success">Done</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status=='FAILED'}">
                                                                                <span class="rbt-badge-5 bg-color-danger-opacity color-danger">Failed</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status=='REJECTED'}">
                                                                                <span class="rbt-badge-5 bg-color-danger-opacity color-danger">Rejected</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status=='EXPIRED'}">
                                                                                <span class="rbt-badge-5 bg-color-danger-opacity color-danger">Expired</span>
                                                                            </c:when>
                                                                        </c:choose>
                                                                    </td>     
                                                                    <td>
                                                                        <button type="button" class="btn btn-success btn-lg" style="width: 100%;">Accept</button>

                                                                        <button type="button" class="btn btn-danger btn-lg" style="width: 100%; margin-top: 5px;">Reject</button>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>                
                                            </div>
                                            <div class="tab-pane fade" id="successful-4" role="tabpanel" aria-labelledby="successful-tab-4">
                                                <div class="rbt-dashboard-table table-responsive mobile-table-750">
                                                    <table class="rbt-table table table-borderless" id="successful-order-table" data-page-length="10">
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
                                                            <c:forEach var="order" items="${successfulOrders}">
                                                                <tr>
                                                                    <th>#${order.id}</th>
                                                                    <td>
                                                                        <c:set var="price" value="${0}"/>
                                                                        <c:forEach var="orderItem" items="${OrderService.getOrderItemsByOrderId(order.id)}">
                                                                            <span>- ${CourseService.getCourseById(orderItem.courseId).name}</span><br/>
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
                                                                                <span class="rbt-badge-5 bg-color-warning-opacity color-warning">New</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status =='PENDING'}">
                                                                                <span class="rbt-badge-5 bg-primary-opacity">Pending</span>  
                                                                            </c:when>
                                                                            <c:when test="${order.status == 'SUCCESSFUL'}">
                                                                                <span class="rbt-badge-5 bg-color-success-opacity color-success">Success</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status=='DONE'}">
                                                                                <span class="rbt-badge-5 bg-color-success-opacity color-success">Done</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status=='FAILED'}">
                                                                                <span class="rbt-badge-5 bg-color-danger-opacity color-danger">Failed</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status=='REJECTED'}">
                                                                                <span class="rbt-badge-5 bg-color-danger-opacity color-danger">Rejected</span>
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
                                            <div class="tab-pane fade" id="rejected-4" role="tabpanel" aria-labelledby="rejected-tab-4">
                                                <div class="rbt-dashboard-table table-responsive mobile-table-750">
                                                    <table class="rbt-table table table-borderless" id="rejected-order-table" data-page-length="10">
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
                                                            <c:forEach var="order" items="${rejectedOrders}">
                                                                <tr>
                                                                    <th>#${order.id}</th>
                                                                    <td>
                                                                        <c:set var="price" value="${0}"/>
                                                                        <c:forEach var="orderItem" items="${OrderService.getOrderItemsByOrderId(order.id)}">
                                                                            <span>- ${CourseService.getCourseById(orderItem.courseId).name}</span><br/>
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
                                                                                <span class="rbt-badge-5 bg-color-warning-opacity color-warning">New</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status =='PENDING'}">
                                                                                <span class="rbt-badge-5 bg-primary-opacity">Pending</span>  
                                                                            </c:when>
                                                                            <c:when test="${order.status == 'SUCCESSFUL'}">
                                                                                <span class="rbt-badge-5 bg-color-success-opacity color-success">Success</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status=='DONE'}">
                                                                                <span class="rbt-badge-5 bg-color-success-opacity color-success">Done</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status=='FAILED'}">
                                                                                <span class="rbt-badge-5 bg-color-danger-opacity color-danger">Failed</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status=='REJECTED'}">
                                                                                <span class="rbt-badge-5 bg-color-danger-opacity color-danger">Rejected</span>
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
                                            <div class="tab-pane fade" id="all-4" role="tabpanel" aria-labelledby="all-tab-4">
                                                <div class="rbt-dashboard-table table-responsive mobile-table-750">
                                                    <table class="rbt-table table table-borderless" id="all-order-table" data-page-length="10">
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
                                                            <c:forEach var="order" items="${allOrders}">
                                                                <tr>
                                                                    <th>#${order.id}</th>
                                                                    <td>
                                                                        <c:set var="price" value="${0}"/>
                                                                        <c:forEach var="orderItem" items="${OrderService.getOrderItemsByOrderId(order.id)}">
                                                                            <span>- ${CourseService.getCourseById(orderItem.courseId).name}</span><br/>
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
                                                                                <span class="rbt-badge-5 bg-color-warning-opacity color-warning">New</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status =='PENDING'}">
                                                                                <span class="rbt-badge-5 bg-primary-opacity">Pending</span>  
                                                                            </c:when>
                                                                            <c:when test="${order.status == 'SUCCESSFUL'}">
                                                                                <span class="rbt-badge-5 bg-color-success-opacity color-success">Success</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status=='DONE'}">
                                                                                <span class="rbt-badge-5 bg-color-success-opacity color-success">Done</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status=='FAILED'}">
                                                                                <span class="rbt-badge-5 bg-color-danger-opacity color-danger">Failed</span>
                                                                            </c:when>
                                                                            <c:when test="${order.status=='REJECTED'}">
                                                                                <span class="rbt-badge-5 bg-color-danger-opacity color-danger">Rejected</span>
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
                                </div>
                            </div>
                            <!-- End Content  -->
                        </div>
                    </div>
                </div>
            </div>
    </body>
    <jsp:include page="/layout/footer.jsp"/>
    <jsp:include page="/layout/scripts.jsp"/>
    <script src="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#pending-order-table').DataTable({
                "order": [0, 'desc'],
                "lengthChange": false,
                "searching": false
            });
        });
        $(document).ready(function () {
            $('#failed-order-table').DataTable({
                "order": [0, 'desc'],
                "lengthChange": false,
                "searching": false
            });
        });
        $(document).ready(function () {
            $('#successful-order-table').DataTable({
                "order": [0, 'desc'],
                "lengthChange": false,
                "searching": false
            });
        });
        $(document).ready(function () {
            $('#rejected-order-table').DataTable({
                "order": [0, 'desc'],
                "lengthChange": false,
                "searching": false
            });
        });
        $(document).ready(function () {
            $('#all-order-table').DataTable({
                "order": [0, 'desc'],
                "lengthChange": false,
                "searching": false
            });
        });
        
        function acceptOrder(orderId){
            let urlPath = "${pageContext.request.contextPath}/manager/order/accept";
            const xhttp = new XMLHttpRequest();
            xhttp.onload = function () {
                if (xhttp.status === 200) {
                    location.reload();
                } else {
//                    let errMsg = document.getElementById("message-error"+courseId);
//                    errMsg.innerHTML = xhttp.responseText;
                }
            };
            xhttp.open("POST", urlPath);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send("order-id=" + orderId);
        }
        
        function rejectOrder(orderId){
            let urlPath = "${pageContext.request.contextPath}/manager/order/reject";
            const xhttp = new XMLHttpRequest();
            xhttp.onload = function () {
                if (xhttp.status === 200) {
                    location.reload();
                } else {
//                    let errMsg = document.getElementById("message-error"+courseId);
//                    errMsg.innerHTML = xhttp.responseText;
                }
            };
            xhttp.open("POST", urlPath);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send("order-id=" + orderId);
        }
    </script>
</html>