<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<li class="with-megamenu has-menu-child-item position-static">
    <a href="#">Blog <i class="feather-chevron-down"></i></a>
    <!-- Start Mega Menu  -->
    <div class="rbt-megamenu grid-item-3">
        <div class="wrapper">
            <div class="row row--15">
                <div class="col-lg-12 col-xl-4 col-xxl-4 single-mega-item">
                    <h3 class="rbt-short-title">Blog Styles</h3>
                    <ul class="mega-menu-item">
                        <li><a href="blog-list.html">Blog List</a></li>
                        <li><a href="blog.html">Blog Grid</a></li>
                        <li><a href="blog-grid-minimal.html">Blog Grid Minimal</a></li>
                        <li><a href="blog-with-sidebar.html">Blog With Sidebar</a></li>
                        <li><a href="blog-details.html">Blog Details</a></li>
                        <li><a href="post-format-standard.html">Post Format Standard</a></li>
                        <li><a href="post-format-gallery.html">Post Format Gallery</a></li>
                    </ul>
                </div>

                <div class="col-lg-12 col-xl-4 col-xxl-4 single-mega-item">
                    <h3 class="rbt-short-title">Get Started</h3>
                    <ul class="mega-menu-item">
                        <li><a href="post-format-quote.html">Post Format Quote</a></li>
                        <li><a href="post-format-audio.html">Post Format Audio</a></li>
                        <li><a href="post-format-video.html">Post Format Video</a></li>
                        <li><a href="#">Media Under Title <span class="rbt-badge-card">Coming</span></a></li>
                        <li><a href="#">Sticky Sidebar <span class="rbt-badge-card">Coming</span></a></li>
                        <li><a href="#">Auto Masonry <span class="rbt-badge-card">Coming</span></a></li>
                        <li><a href="#">Meta Overlaid <span class="rbt-badge-card">Coming</span></a></li>
                    </ul>
                </div>

                <div class="col-lg-12 col-xl-4 col-xxl-4 single-mega-item">
                    <div class="rbt-ads-wrapper">
                        <a class="d-block" href="#"><img src="${pageContext.request.contextPath}/assets/images/service/mobile-cat.jpg" alt="Education Images"></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End Mega Menu  -->
</li>
