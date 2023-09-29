<script>
//    function removeCartSide() {
//        const xhr = new XMLHttpRequest();
//        xhr.open('POST', 'online-learning/remove-cart', true);
//        xhr.onload = function () {
//            if (xhr.status >= 200 && xhr.status < 400) {
//                openCartSideMenu();
//            }
//        };
//        xhr.send();
//    }

    function openCartSideMenu() {
        const openCartButton = document.getElementById('open-cart-side-menu');
        openCartButton.click();
    }

    const coursePriceCartSide = document.getElementsByClassName("course-price-cart-side");
    const courseTotalCartSide = document.getElementById('course-total-cart-side');
    courseTotalCartSide.innerHTML = '$' + calculateCoursesPriceCartSide().toFixed(2);
    function calculateCoursesPriceCartSide() {
        let total = 0;
        for (let price of coursePriceCartSide) {
            total += parseFloat(price.innerHTML.replace("$", ""));
        }
        return total;
    }
</script>

<!-- JS -->
<!-- Modernizer JS -->
<script src="${pageContext.request.contextPath}/assets/js/vendor/modernizr.min.js"></script>
<!-- jQuery JS -->
<script src="${pageContext.request.contextPath}/assets/js/vendor/jquery.js"></script>
<!-- Bootstrap JS -->
<script src="${pageContext.request.contextPath}/assets/js/vendor/bootstrap.min.js"></script>
<!-- Parallax JS -->
<script src="${pageContext.request.contextPath}/assets/js/vendor/paralax.js"></script>
<!-- sal.js -->
<script src="${pageContext.request.contextPath}/assets/js/vendor/sal.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/vendor/swiper.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/vendor/magnify.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/vendor/jquery-appear.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/vendor/odometer.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/vendor/backtotop.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/vendor/isotop.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/vendor/imageloaded.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/vendor/countdown.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/vendor/wow.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/vendor/waypoint.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/vendor/easypie.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/vendor/text-type.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/vendor/jquery-one-page-nav.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/vendor/bootstrap-select.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/vendor/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/vendor/magnify-popup.min.js"></script>
<!-- Main JS -->
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
<!--Font Awesome-->
<script src="https://kit.fontawesome.com/81fcade62e.js" crossorigin="anonymous"></script>