<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iLearn | Search</title>
        <jsp:include page="/layout/links.jsp"/>

    </head>
    <body class="rbt-header-sticky">
        <jsp:include page="/layout/header.jsp"/>
        <div class="rbt-page-banner-wrapper">
            <!-- Start Banner BG Image  -->
            <div class="rbt-banner-image"></div>
            <!-- End Banner BG Image  -->
            <div class="rbt-banner-content">

                <!-- Start Banner Content Top  -->
                <div class="rbt-banner-content-top">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12">
                                <!-- Start Breadcrumb Area  -->
                                <ul class="page-list">
                                    <li class="rbt-breadcrumb-item"><a href="index.html">Home</a></li>
                                    <li>
                                        <div class="icon-right"><i class="feather-chevron-right"></i></div>
                                    </li>
                                    <li class="rbt-breadcrumb-item active">All Courses</li>
                                </ul>
                                <!-- End Breadcrumb Area  -->

                                <div class=" title-wrapper">
                                    <h1 class="title mb--0">All Courses</h1>
                                    <a href="#" class="rbt-badge-2">
                                        <div class="image">🎉</div> 50 Courses
                                    </a>
                                </div>

                                <p class="description">Courses that help beginner designers become true unicorns. </p>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Banner Content Top  -->
                <!-- Start Course Top  -->
                <div class="rbt-course-top-wrapper mt--40 mt_sm--20">
                    <div class="container">
                        <div class="row g-5 align-items-center">

                            <div class="col-lg-5 col-md-12">
                                <div class="rbt-sorting-list d-flex flex-wrap align-items-center">
                                    <div class="rbt-short-item switch-layout-container">
                                        <ul class="course-switch-layout">
                                            <li class="course-switch-item"><button class="rbt-grid-view active" title="Grid Layout"><i class="feather-grid"></i> <span class="text">Grid</span></button></li>
                                            <li class="course-switch-item"><button class="rbt-list-view" title="List Layout"><i class="feather-list"></i> <span class="text">List</span></button></li>
                                        </ul>
                                    </div>
                                    <div class="rbt-short-item">
                                        <span class="course-index">Showing 1-9 of 19 results</span>
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-7 col-md-12">
                                <div class="rbt-sorting-list d-flex flex-wrap align-items-center justify-content-start justify-content-lg-end">
                                    <div class="rbt-short-item">
                                        <form method="get" id="courseKeywordForm" class="rbt-search-style me-0" onsubmit="event.preventDefault();
                                                return formSubmit();">
                                            <input type="text" id="courseKeyword" name="courseKeyword" value="${courseKeyword}" placeholder="Search Your Course..">
                                            <button type="submit" class="rbt-search-btn rbt-round-btn">
                                                <i class="feather-search"></i>
                                            </button>
                                        </form>
                                    </div>
                                    <div class="rbt-short-item">
                                        <div class="view-more-btn text-start text-sm-end">
                                            <button class="discover-filter-button discover-filter-activation rbt-btn btn-white btn-md radius-round">Filter<i class="feather-filter"></i></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Start Filter Toggle  -->
                        <div class="default-exp-wrapper default-exp-expand">
                            <form method="get" id="courseKeywordForm" class="rbt-search-style me-0" onsubmit="event.preventDefault();
                                    return formSubmit();">
                                <div class="filter-inner" style="justify-content: space-around">
                                    <div class="filter-select-option">
                                        <div class="filter-select rbt-modern-select">
                                            <span class="select-label d-block">Short By</span>  
                                            <select name="filterPrice" id="filterPrice">
                                                <option value="">------------</option>
                                                <option value="latest">Latest</option>
                                                <option value="lowtohigh">Price: low to high</option>
                                                <option value="hightolow">Price: high to low</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="filter-select-option">
                                        <div class="filter-select rbt-modern-select">
                                            <span class="select-label d-block">Short By Category</span>
                                            <select data-live-search="true" name="filterCategories" id="filterCategories">
                                                <option value="">--------------</option>
                                                <c:forEach items="${categorys}" var="categorys">
                                                    <option value="${categorys.name}">${categorys.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="filter-select-option">
                                        <div class="filter-select">
                                            <span class="select-label d-block">Price Range</span>

                                            <div class="price_filter s-filter clear">

                                                <div id="slider-range"></div>
                                                <div class="slider__range--output">
                                                    <div class="price__output--wrap">
                                                        <div class="price--output">
                                                            <span>Price :</span><input type="text" id="amount" name="priceRange" value="${priceRange}">
                                                        </div>
                                                    </div>                                                        <div class="price--filter">
                                                        <button type="submit" class="rbt-btn btn-gradient btn-sm">Filter</button>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <!-- End Filter Toggle  -->
                    </div>
                </div>
                <!-- End Course Top  -->
            </div>
        </div>
        <div class="rbt-section-overlayping-top rbt-section-gapBottom">
            <div class="inner">
                <div class="container">
                    <div class="rbt-course-grid-column">

                        <!-- Start Single Card  -->
                        <c:forEach items="${courses}" var="course">
                            <div class="course-grid-3">
                                <div class="rbt-card variation-01 rbt-hover">
                                    <div class="rbt-card-img">
                                        <a href="#">
                                            <img style="height: 245px" src="${course.imageUrl}" alt="Card image">
                                            <!--                                            <div class="rbt-badge-3 bg-white">
                                                                                            <span>-40%</span>
                                                                                            <span>Off</span>
                                                                                        </div>-->
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
                                                <a class="rbt-round-btn" title="Bookmark" href="#"><i
                                                        class="feather-bookmark"></i></a>
                                            </div>
                                        </div>

                                        <h4 class="rbt-card-title"><a href="#">${course.name}</a>
                                        </h4>

                                        <ul class="rbt-meta">
                                            <li><i class="feather-book"></i>12 Lessons</li>
                                            <li><i class="feather-users"></i>50 Students</li>
                                        </ul>

                                        <p class="rbt-card-text text-truncate">${course.description}</p>
                                        <div class="rbt-author-meta mb--10">
                                            <div class="rbt-avater">
                                                <a href="#">
                                                    <img src="assets/images/client/avatar-02.png" alt="Sophia Jaymes">
                                                </a>
                                            </div>
                                            <div class="rbt-author-info">
                                                By <a href="profile.html">Angela</a> In <a href="#">Development</a>
                                            </div>
                                        </div>
                                        <div class="rbt-card-bottom">
                                            <div class="rbt-price">
                                                <span class="current-price">$${course.price}</span>
                                                <!--<span class="off-price">${course.price}</span>-->
                                            </div>
                                            <c:if test="${coursesInCart.contains(course)}">
                                                <div>
                                                    <a class="rbt-btn-link" href="javascript:void(0);">
                                                        Learn More<i class="feather-arrow-right"></i>
                                                    </a>
                                                </div>
                                            </c:if>
                                            <c:if test="${!coursesInCart.contains(course)}">
                                                <div id="add-to-cart-btn${course.id}">
                                                    <a class="rbt-btn-link left-icon" href="javascript:void(0);" onclick="addToCart(${course.id})">
                                                        <i class="feather-shopping-cart"></i> Add To Cart
                                                    </a>
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                                <p id="message-error${course.id}" style="text-align: center; color: red"></p>
                            </div>
                        </c:forEach>
                        <!-- End Single Card  -->
                    </div>
                    <div class="row">
                        <div class="col-lg-12 mt--60">
                            <nav>
                                <ul class="rbt-pagination">
                                    <li><a href="#" aria-label="Previous"><i class="feather-chevron-left"></i></a></li>
                                    <li><a href="#">1</a></li>
                                    <li class="active"><a href="#">2</a></li>
                                    <li><a href="#">3</a></li>
                                    <li><a href="#" aria-label="Next"><i class="feather-chevron-right"></i></a></li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/layout/footer.jsp"/>
        <script type="text/javascript">
            function formSubmit() {
                let courseKeyword = document.getElementById("courseKeyword").value;
                let filterPrice = document.getElementById("filterPrice").value;
                let priceRange = document.getElementById("amount").value;
                let filterCategories = document.getElementById("filterCategories").value;
                let url = "${pageContext.request.contextPath}/search";
                let firstParamSet = false;
                if (courseKeyword !== "") {
                    if (firstParamSet === false) {
                        url = url + "?" + "courseKeyword=" + courseKeyword;
                        firstParamSet = true;
                    } else {
                        url = url + "&" + "courseKeyword=" + courseKeyword;
                    }
                }
                if (filterPrice !== "") {
                    if (firstParamSet === false) {
                        url = url + "?" + "filterPrice=" + filterPrice;
                        firstParamSet = true;
                    } else {
                        url = url + "&" + "filterPrice=" + filterPrice;
                    }
                }
                if (filterCategories !== "") {
                    if (firstParamSet === false) {
                        url = url + "?" + "filterCategory=" + filterCategories;
                        firstParamSet = true;
                    } else {
                        url = url + "&" + "filterCategory=" + filterCategories;
                    }
                }
                if (priceRange !== "") {
                    if (firstParamSet === false) {
                        url = url + "?" + "priceRange=" + priceRange;
                        firstParamSet = true;
                    } else {
                        url = url + "&" + "priceRange=" + priceRange;
                    }
                }
                window.location.href = url;
                console.log("form submited");
                console.log("${pageContext.request.contextPath}/course/find");
            }
        </script>
    </body>
    <jsp:include page="/layout/scripts.jsp"/>
    <script>
        function addToCart(courseId) {
            let urlPath = "${pageContext.request.contextPath}/cart/add";
//            const xhttp = new XMLHttpRequest();
//            xhttp.onload = function () {
//                if (xhttp.status === 200) {
//                    location.reload();
//                } else {
//                    let errMsg = document.getElementById("message-error"+courseId);
//                    errMsg.innerHTML = xhttp.responseText;
//                }
//            };
//            xhttp.open("POST", urlPath);
//            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//            xhttp.send("course-id=" + courseId);
            const postData = new URLSearchParams();
            postData.append("course-id", courseId);
            fetch(urlPath, {
                method: "POST",
                body: postData
            }).then((res) => {
                if (res.status === 200) {
                    location.reload();
                }
            }).catch((error) => {
                let errMsg = document.getElementById("message-error" + courseId);
                errMsg.innerHTML = error;
                console.log(error);
            });
        }

        function addToWishlist(courseId) {
            let urlPath = "${pageContext.request.contextPath}/wishlist/add";
            const xhttp = new XMLHttpRequest();
            xhttp.onload = function () {
                if (xhttp.status === 200) {
                    location.reload();
                } else {

                }
            };
            xhttp.open("POST", urlPath);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send("course-id=" + courseId);
        }
    </script>
</html>
