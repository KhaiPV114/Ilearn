<%-- Created on : Oct 5, 2023, 6:14:37 PM by DuyDuc94 --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
    //Auto scroll to the content of page with delay
    scrollToElementWithTime();
    function scrollToElementWithTime() {
        const targetElement = document.getElementById('content-display');
        const scrollDelay = 500;
        const targetPosition = targetElement.offsetTop;
        const startPosition = window.pageYOffset;
        const distance = targetPosition - startPosition;
        let startTime = null;
        function scrollStep(timestamp) {
            if (!startTime) {
                startTime = timestamp;
            }
            const progress = timestamp - startTime;
            const percentage = Math.min(progress / scrollDelay, 1);
            window.scrollTo(0, startPosition + distance * percentage);
            if (progress < scrollDelay) {
                window.requestAnimationFrame(scrollStep);
            }
        }
        window.requestAnimationFrame(scrollStep);
    }
</script>