<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Wishlist</title>
        <jsp:include page="/layout/links.jsp"/>
    </head>
    <body>
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
                                            <h4 class="rbt-title-style-3">Wishlist</h4>
                                        </div>
                                        <div class="row g-5">
                                            <!-- Start Single Course  -->
                                            <c:forEach var="course" items="${coursesInWishlist}">
                                                <div class="col-lg-4 col-md-6 col-12">
                                                    <div class="rbt-card variation-01 rbt-hover">
                                                        <div class="rbt-card-img">
                                                            <a href="course-details.html">
                                                                <img src="${pageContext.request.contextPath}${course.imageUrl}" alt="Card image">
                                                            </a>
                                                        </div>
                                                        <div class="rbt-card-body">
                                                            <div class="rbt-card-top">
                                                                <div class="rbt-review">
                                                                    <div class="rating">
                                                                        <i class="fas fa-star"></i>
                                                                        <i class="fas fa-star"></i>
                                                                        <i class="fas fa-star"></i>
                                                                        <i class="fas fa-star"></i>
                                                                        <i class="fas fa-star"></i>
                                                                    </div>
                                                                    <span class="rating-count"> (15 Reviews)</span>
                                                                </div>
                                                                <div class="rbt-bookmark-btn">
                                                                    <form action="${pageContext.request.contextPath}/learner/wishlist/delete" id="wishlist${course.id}" method="post">
                                                                        <input type="hidden" name="course-id" value="${course.id}">
                                                                        <a class="rbt-round-btn" title="Bookmark" href="#" onclick="document.getElementById('wishlist${course.id}').submit()">
                                                                            <i class="fa-solid fa-bookmark"></i>
                                                                        </a>
                                                                    </form>
                                                                </div>
                                                            </div>
                                                            <h4 class="rbt-card-title"><a href="course-details.html">${course.name}</a>
                                                            </h4>
                                                            <ul class="rbt-meta">
                                                                <li><i class="feather-book"></i>20 Lessons</li>
                                                                <li><i class="feather-users"></i>40 Students</li>
                                                            </ul>

                                                            <div class="rbt-card-bottom">
                                                                <div class="rbt-price">
                                                                    <span class="current-price">$${course.price}</span>
                                                                </div>
                                                                <a class="rbt-btn-link" href="course-details.html">
                                                                    Learn More<i class="feather-arrow-right"></i>
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                            <!-- End Single Course  -->
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
    </body>
</html>
