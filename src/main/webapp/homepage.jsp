<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="categoryService" class="com.onlinelearning.Services.Impl.CategoryServiceImpl" />
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iLearn</title>
        <jsp:include page="/layout/links.jsp"/>
    </head>
    <body class="rbt-header-sticky">
        <jsp:include page="/layout/header.jsp"/>
        <main class="rbt-main-wrapper">
            <!-- Start Banner Area -->
            <div class="rbt-banner-area rbt-banner-1">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12 pb--120 pt--70">
                            <div class="content">
                                <div class="inner">
                                    <div class="rbt-new-badge rbt-new-badge-one">
                                        <span class="rbt-new-badge-icon">🏆</span> The Leader in Online Learning
                                    </div>

                                    <h1 class="title">
                                        Build The Skills <br> To Drive Your Career.
                                    </h1>
                                    <p class="description">
                                        Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint.
                                        <strong>Velit
                                            officia consequat.</strong>
                                    </p>
                                    <div class="slider-btn">
                                        <a class="rbt-btn btn-gradient hover-icon-reverse" href="${pageContext.request.contextPath}/search">
                                            <span class="icon-reverse-wrapper">
                                                <span class="btn-text">View Course</span>
                                                <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                                <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                            </span>
                                        </a>
                                    </div>
                                </div>
                                <div class="shape-wrapper" id="scene">
                                    <img src="${pageContext.request.contextPath}/assets/images/banner/banner-01.png" alt="Hero Image">
                                    <div class="hero-bg-shape-1 layer" data-depth="0.4">
                                        <img src="${pageContext.request.contextPath}/assets/images/shape/shape-01.png" alt="Hero Image Background Shape">
                                    </div>
                                    <div class="hero-bg-shape-2 layer" data-depth="0.4">
                                        <img src="${pageContext.request.contextPath}/assets/images/shape/shape-02.png" alt="Hero Image Background Shape">
                                    </div>
                                </div>

                                <div class="banner-card pb--60 mb--50 swiper rbt-dot-bottom-center banner-swiper-active">
                                    <div class="swiper-wrapper">

                                        <!-- Start Single Card  -->
                                        <div class="swiper-slide">
                                            <div class="rbt-card variation-01 rbt-hover">
                                                <div class="rbt-card-img">
                                                    <a href="course-details.html">
                                                        <img src="${pageContext.request.contextPath}/assets/images/course/course-01.jpg" alt="Card image">
                                                    </a>
                                                </div>
                                                <div class="rbt-card-body">
                                                    <ul class="rbt-meta">
                                                        <li><i class="feather-book"></i>12 Lessons</li>
                                                        <li><i class="feather-users"></i>50 Students</li>
                                                    </ul>
                                                    <h4 class="rbt-card-title"><a href="course-details.html">React</a>
                                                    </h4>
                                                    <p class="rbt-card-text">It is a long established fact that a reader
                                                        will be distracted.</p>
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
                                                    <div class="rbt-card-bottom">
                                                        <div class="rbt-price">
                                                            <span class="current-price">$70</span>
                                                            <span class="off-price">$120</span>
                                                        </div>
                                                        <a class="rbt-btn-link" href="course-details.html">Learn More<i
                                                                class="feather-arrow-right"></i></a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- End Single Card  -->

                                        <!-- Start Single Card  -->
                                        <div class="swiper-slide">
                                            <div class="rbt-card variation-01 rbt-hover">
                                                <div class="rbt-card-img">
                                                    <a href="course-details.html">
                                                        <img src="${pageContext.request.contextPath}/assets/images/course/classic-lms-01.jpg" alt="Card image">
                                                        <div class="rbt-badge-3 bg-white">
                                                            <span>-40%</span>
                                                            <span>Off</span>
                                                        </div>
                                                    </a>
                                                </div>
                                                <div class="rbt-card-body">
                                                    <ul class="rbt-meta">
                                                        <li><i class="feather-book"></i>12 Lessons</li>
                                                        <li><i class="feather-users"></i>50 Students</li>
                                                    </ul>
                                                    <h4 class="rbt-card-title"><a href="course-details.html">React</a>
                                                    </h4>
                                                    <p class="rbt-card-text">It is a long established fact that a reader
                                                        will be distracted.</p>
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
                                                    <div class="rbt-card-bottom">
                                                        <div class="rbt-price">
                                                            <span class="current-price">$70</span>
                                                            <span class="off-price">$120</span>
                                                        </div>
                                                        <a class="rbt-btn-link" href="course-details.html">Learn More<i
                                                                class="feather-arrow-right"></i></a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- End Single Card  -->

                                        <!-- Start Single Card  -->
                                        <div class="swiper-slide">
                                            <div class="rbt-card variation-01 rbt-hover">
                                                <div class="rbt-card-img">
                                                    <a href="course-details.html">
                                                        <img src="${pageContext.request.contextPath}/assets/images/course/course-online-02.jpg" alt="Card image">
                                                        <div class="rbt-badge-3 bg-white">
                                                            <span>-40%</span>
                                                            <span>Off</span>
                                                        </div>
                                                    </a>
                                                </div>
                                                <div class="rbt-card-body">
                                                    <ul class="rbt-meta">
                                                        <li><i class="feather-book"></i>12 Lessons</li>
                                                        <li><i class="feather-users"></i>50 Students</li>
                                                    </ul>
                                                    <h4 class="rbt-card-title"><a href="course-details.html">React</a>
                                                    </h4>
                                                    <p class="rbt-card-text">It is a long established fact that a reader
                                                        will be distracted.</p>
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
                                                    <div class="rbt-card-bottom">
                                                        <div class="rbt-price">
                                                            <span class="current-price">$70</span>
                                                            <span class="off-price">$120</span>
                                                        </div>
                                                        <a class="rbt-btn-link" href="course-details.html">Learn More<i
                                                                class="feather-arrow-right"></i></a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- End Single Card  -->

                                    </div>
                                    <div class="rbt-swiper-pagination"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End Banner Area -->

            <div class="rbt-categories-area bg-color-white rbt-section-gapBottom">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="section-title text-center">
                                <span class="subtitle bg-primary-opacity">CATEGORIES</span>
                                <h2 class="title">Explore Top Courses Caterories <br /> That Change Yourself</h2>
                            </div>
                        </div>
                    </div>
                    <div class="row g-5 mt--20">

                        <!-- Start Category Box Layout  -->
                        <c:forEach items="${allCategories}" var="category">
                            <div class="col-lg-3 col-md-6 col-sm-6 col-12">
                                <a class="rbt-cat-box rbt-cat-box-1 text-center" 
                                   href="${pageContext.request.contextPath}/search?filterCategory=${category.name}">
                                    <div class="inner">
                                        <div class="icons">
                                            <img src="${category.imageUrl}" alt="Images">
                                        </div>
                                        <div class="content">
                                            <h5 class="title" style="min-height: 5rem;">${category.name}</h5>
                                            <!--NEED FIX: Get course number of a category-->
                                            <div class="read-more-btn">
                                                <span class="rbt-btn-link">
                                                    ${categoryService.countNumberOfCourseByCategoryId(category.id)} Courses
                                                    <i class="feather-arrow-right"></i>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                        <!-- End Category Box Layout  -->   
                    </div>
                </div>
            </div>

            <!-- Start Course Area -->

            <div class="rbt-course-area bg-color-extra2 rbt-section-gap">
                <div class="container">
                    <div class="row mb--60">
                        <div class="col-lg-12">
                            <div class="section-title text-center">
                                <span class="subtitle bg-secondary-opacity">Top Popular Course</span>
                                <h2 class="title">iLearn Course student <br /> can join with us.</h2>
                            </div>
                        </div>
                    </div>
                    <!-- Start Card Area -->

                    <div class="row g-5">
                        <!-- Start Single Course  -->
                        <c:forEach items="${courses}" var="course">
                            <div class="col-lg-4 col-md-6 col-12">
                                <div class="rbt-card variation-01 rbt-hover">
                                    <div class="rbt-card-img">
                                        <a href="course-details.html">
                                            <img src="${course.imageUrl}" alt="Card image">
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
                                                <a class="rbt-round-btn" title="Bookmark" href="#"><i class="feather-bookmark"></i></a>
                                            </div>
                                        </div>

                                        <h4 class="rbt-card-title"><a href="course-details.html">${course.name}</a>
                                        </h4>
                                        <ul class="rbt-meta">
                                            <li><i class="feather-book"></i>20 Lessons</li>
                                            <li><i class="feather-users"></i>40 Students</li>
                                        </ul>
                                        <p class="rbt-card-text text-truncate">${course.description}</p>
                                        <div class="rbt-author-meta mb--20">
                                            <div class="rbt-avater">
                                                <a href="#">
                                                    <img src="${pageContext.request.contextPath}/assets/images/client/avater-01.png" alt="Sophia Jaymes">
                                                </a>
                                            </div>
                                            <div class="rbt-author-info">
                                                By <a href="profile.html">Patrick</a> In <a href="#">Languages</a>
                                            </div>
                                        </div>

                                        <div class="rbt-card-bottom">
                                            <div class="rbt-price">
                                                <span class="current-price">$${course.price}</span>
                                            </div>
                                            <a class="rbt-btn-link" href="course-details.html">Learn
                                                More<i class="feather-arrow-right"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <!-- End Single Course  -->
                    </div>
                    <!-- End Card Area -->

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="load-more-btn mt--60 text-center">
                                <a class="rbt-btn btn-gradient btn-lg hover-icon-reverse" href="${pageContext.request.contextPath}/search">
                                    <span class="icon-reverse-wrapper">
                                        <span class="btn-text">Load More Course (40)</span>
                                        <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                        <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                    </span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End Course Area -->


            <%--<jsp:include page="/layout/footer/newsletter.jsp" />--%>
        </main>
        <footer class="rbt-footer footer-style-1 bg-color-white overflow-hidden">
            <jsp:include page="/layout/footer/footer-without-newsletter.jsp" />
        </footer>

        <div class="rbt-separator-mid">
            <div class="container">
                <hr class="rbt-separator m-0">
            </div>
        </div>

        <!-- Start Copyright Area  -->
        <jsp:include page="/layout/footer/copy-right.jsp" />
        <!-- End Copyright Area  -->
    </body>
    <jsp:include page="/layout/scripts.jsp"/>
</html>