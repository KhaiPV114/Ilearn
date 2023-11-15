<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import = "java.util.Arrays" %>

<jsp:useBean id="OrderService" scope="request" class="com.onlinelearning.Services.Impl.OrderServiceImpl" />
<jsp:useBean id="CourseService" scope="request" class="com.onlinelearning.Services.Impl.CourseServiceImpl" />

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iLearn | Dashboard</title>
        <jsp:include page="/layout/links.jsp"/>
    </head>
    <body class="rbt-header-sticky">
        <jsp:include page="/layout/header.jsp"/>
        <jsp:include page="/layout/dashboard-header.jsp"/>
        <div class="rbt-dashboard-area rbt-section-overlayping-top rbt-section-gapBottom" >
            <div class="container">
                <div class="row">
                    <div class="col-lg-12" id="content">
                        <jsp:include page="/layout/dashboard-manager-card.jsp"/>
                        <div class="row g-5" >
                            <jsp:include page="/layout/dashboard-manager-sidebar.jsp"/>
                            <!-- Start Content  -->
                            <div class="col-lg-9" >
                                <div class="rbt-dashboard-content bg-color-white rbt-shadow-box mb--60">
                                    <div class="content">
                                        <form action="${pageContext.request.contextPath}/manager/earning" method="post">
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="section-title">
                                                        <h4 class="rbt-title-style-3">Total Earnings</h4>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row d-flex align-items-center">
                                                        <div class="col-lg-4">
                                                            <div class="rbt-modern-select bg-transparent height-45">
                                                                <select class="w-100" title="Select Year" name="year">
                                                                    <c:forEach var="year" items="${years}">
                                                                        <option value="${year}" ${choosedYear == year ? 'selected' : ''} >${year}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-4">
                                                            <div class="rbt-modern-select bg-transparent height-45">
                                                                <select class="w-100" title="Select Month" name="month">
                                                                    <option value="1" ${choosedMonth == 1 ? 'selected': ''}>January</option>
                                                                    <option value="2" ${choosedMonth == 2 ? 'selected': ''}>February</option>
                                                                    <option value="3" ${choosedMonth == 3 ? 'selected': ''}>March</option>
                                                                    <option value="4" ${choosedMonth == 4 ? 'selected': ''}>April</option>
                                                                    <option value="5" ${choosedMonth == 5 ? 'selected': ''}>May</option>
                                                                    <option value="6" ${choosedMonth == 6 ? 'selected': ''}>June</option>
                                                                    <option value="7" ${choosedMonth == 7 ? 'selected': ''}>July</option>
                                                                    <option value="8" ${choosedMonth == 8 ? 'selected': ''}>August</option>
                                                                    <option value="9" ${choosedMonth == 9 ? 'selected': ''}>September</option>  
                                                                    <option value="10" ${choosedMonth == 10 ? 'selected': ''}>October</option>
                                                                    <option value="11" ${choosedMonth == 11 ? 'selected': ''}>November</option>
                                                                    <option value="12" ${choosedMonth == 12 ? 'selected': ''}>December</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-3  offset-1">
                                                            <button class="rbt-btn btn-sm">Show</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                        <div class="row gy-5">
                                            <div class="col-lg-12">
                                                <canvas id="myChart"></canvas>
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
        </div>
    </body>
    <c:set var="earningOfMonth" value="${requestScope.earningOfMonth}"/>
    <jsp:include page="/layout/footer.jsp"/>
    <jsp:include page="/layout/scripts.jsp"/>
    <script src="${pageContext.request.contextPath}/assets/js/chart.custom.utils.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
        document.getElementById("content").scrollIntoView({behavior: 'instant'});
        location.hash = '#content';
        const ctx = document.getElementById('myChart');

        const data = {
            labels: Samples.utils.labels({suffix: '/${choosedMonth}', max: ${daysOfMonth}}),
            datasets: [
                {
                    label: 'Earning',
                    data: ${Arrays.toString(earningOfMonth)},
                    borderColor: window.chartColors.red,
                    backgroundColor: Samples.utils.transparentize(255, 0, 0, 0.5),
                    pointStyle: 'circle',
                    pointRadius: 10,
                    pointHoverRadius: 15
                }
            ]
        };
        new Chart(ctx, {
            type: 'line',
            data: data,
            options: {
                responsive: true,
                plugins: {
                    title: {
                        display: true,
                        align: 'end',
                        font: {weight: 'bold', size: 20},
                        text: (ctx) => 'Totals: ' + '${totalEarningOfMonth}' + '$'
                    }
                }
            }
        });
    </script>
</html>