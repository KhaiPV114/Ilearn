<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="/layout/links.jsp"/>
    </head>
    <body>
        <jsp:include page="/layout/header.jsp" />
        <jsp:include page="/layout/dashboard-header.jsp" />
        <div class="rbt-dashboard-area rbt-section-overlayping-top rbt-section-gapBottom">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <jsp:include page="/layout/dashboard-manager-card.jsp" />
                        <div class="row g-5">
                            <jsp:include page="/layout/dashboard-manager-sidebar.jsp" />
                            <!-- Start content -->
                            <div class="col-lg-9" id="content">
                                <div class="rbt-dashboard-content bg-color-white rbt-shadow-box">
                                    <div class="content">
                                        <!-- Start Title -->
                                        <div class="section-title">
                                            <nav class="rbt-title-style-3 h4 pb-0" aria-label="breadcrumb">
                                                <ol class="breadcrumb">
                                                    <li class="breadcrumb-item">
                                                        <a href="#">
                                                            Coupon
                                                        </a>
                                                    </li>
                                                    <li class="breadcrumb-item active" aria-current="page">
                                                        <a class="color-primary" href="#">
                                                            Create
                                                        </a>
                                                    </li>
                                                </ol>
                                            </nav>
                                        </div>
                                        <!-- End Title -->
                                        <form action="${pageContext.request.contextPath}/manager/coupon/create" method="post">
                                            <input type="hidden" name="status" value="ACTIVE">
                                            <div class="row">
                                                <div class="col-md-7 col-lg-8">
                                                    <div class="mb-3">
                                                        <label for="code">Coupon code:</label>
                                                        <input type="text" name="code" id="code" class="form-control" placeholder="Coupon code"/>
                                                        <c:if test="${not empty codeError}">
                                                            <div class="form-text text-danger">${codeError}</div>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-7 col-lg-8">
                                                    <div class="mb-3">
                                                        <label for="courseId">For Course:</label>
                                                        <select name="courseId">
                                                            <option selected>Course Name</option>
                                                            <c:forEach items="${courses}" var = "course">
                                                                <option class="form-control" value="${course.id}">${course.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                        <c:if test="${not empty coureIdError}">
                                                            <div class="form-text text-danger">${coureIdError}</div>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-7 col-lg-8">
                                                    <div class="mb-3">
                                                        <label for="percent">Percent:</label>
                                                        <input type="text" name="percent" id="percent" class="form-control" placeholder="Percent"/>
                                                        <c:if test="${not empty percentError}">
                                                            <div class="form-text text-danger">${percentError}</div>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-7 col-lg-8">
                                                    <div class="mb-3">
                                                        <label for="quantity">Quantity:</label>
                                                        <input type="text" name="quantity" id="quantity" class="form-control" placeholder="Quantity"/>
                                                        <c:if test="${not empty quantityError}">
                                                            <div class="form-text text-danger">${quantityError}</div>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-7 col-lg-8">
                                                    <div class="mb-3">
                                                        <label for="started">Start at:</label>
                                                        <input type="datetime-local" name="started" id="started" class="form-control"/>
                                                        <c:if test="${not empty startedError}">
                                                            <div class="form-text text-danger">${startedError}</div>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-7 col-lg-8">
                                                    <div class="mb-3">
                                                        <label for="ended">End at:</label>
                                                        <input type="datetime-local" name="ended" id="ended" class="form-control"/>
                                                        <c:if test="${not empty endedError}">
                                                            <div class="form-text text-danger">${endedError}</div>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                            <button type="submit" class="rbt-btn btn-md mt-3">Create</button>
                                        </form>
                                        <c:if test="${not empty success}">
                                            <div class="form-text text-success fw-bold mt-3">${success}</div>
                                        </c:if>
                                            <c:if test="${not empty fail}">
                                            <div class="form-text text-success fw-bold mt-3">${fail}</div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <!-- End Content -->
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="/layout/footer.jsp" />
    </body>
    <jsp:include page="/layout/scripts.jsp"/>
</html>