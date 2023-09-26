<%@page contentType="text/html" pageEncoding="UTF-8" buffer="8192kb" autoFlush="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="/layout/links.jsp" />
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
                            <!-- Start Content  -->
                            <div class="col-lg-9" id="content">
                                <div class="rbt-dashboard-content bg-color-white rbt-shadow-box">
                                    <div class="content">
                                        <!-- Start Title -->
                                        <div class="section-title">
                                            <!--<h4 class="rbt-title-style-3">Add course category</h4>-->
                                            <nav class="rbt-title-style-3 h4 pb-0" aria-label="breadcrumb">
                                                <ol class="breadcrumb">
                                                    <li class="breadcrumb-item">
                                                        <a href="#">
                                                            Course category
                                                        </a>
                                                    </li>
                                                    <li class="breadcrumb-item active" aria-current="page">
                                                        <a class="color-primary" href="#">
                                                            View
                                                        </a>
                                                    </li>
                                                </ol>
                                            </nav>
                                        </div>
                                        <!-- End Title -->

                                        <div class="tutor-btn">
                                            <a class="rbt-btn btn-md hover-icon-reverse" href="${pageContext.request.contextPath}/manager/category/add">
                                                <span class="icon-reverse-wrapper">
                                                    <span class="btn-text">Create a New Category</span>
                                                    <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                                    <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                                </span>
                                            </a>
                                        </div>

                                        <div class="rbt-dashboard-table table-responsive mobile-table-750 mt--30">
                                            <table class="rbt-table table table-borderless">
                                                <thead>
                                                    <tr>
                                                        <th>#</th>
                                                        <th>Detail</th>
                                                        <th>Image</th>
                                                        <th style="min-width: 140px;"></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:set var="i" value="0"/>
                                                    <c:forEach var="item" items="${categories}">
                                                        <c:set var="i" value="${i + 1}"/>
                                                        <tr>
                                                            <th>
                                                                ${i}
                                                            </th>
                                                            <th>
                                                                <p class="h6 mb--5">
                                                                    <b>Name:</b> ${item.name}
                                                                </p>
                                                                <p class="b3">
                                                                    Description: ${item.description}
                                                                </p>
                                                            </th>
                                                            <td>
                                                                <img src="${pageContext.request.contextPath}${item.imageUrl}" alt="image"/>
                                                            </td>
                                                            <td>
                                                                <div class="rbt-button-group justify-content-end">
                                                                    <a class="rbt-btn btn-xs bg-primary-opacity radius-round" href="${pageContext.request.contextPath}/manager/category/edit?id=${item.id}" title="Edit"><i class="feather-edit pl--0"></i></a>
                                                                    <span class="rbt-btn btn-xs bg-color-danger-opacity radius-round color-danger" onclick="submitDeletionForm(${item.id})" title="Delete"><i class="feather-trash-2 pl--0"></i></span>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>

                                        <!--Delete via post method-->                                                    
                                        <form id="deletion-form" action="${pageContext.request.contextPath}/manager/category/delete" method="post">
                                            <input id="deletion-id" name="id" value="" type="hidden">
                                        </form>

                                    </div>
                                </div>
                            </div>
                            <!-- End Content  -->
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="/layout/footer.jsp" />
        <jsp:include page="/layout/scripts.jsp" />
        <script>
            const deletionForm = document.getElementById("deletion-form");
            const deletionId = document.getElementById("deletion-id");
            function submitDeletionForm(id) {
//                let check = confirm("Are you sure?");
                if (!confirm("Are you sure?")) {
                    return false;
                }
                deletionId.value = id;
                deletionForm.submit();
            }

            document.getElementById("content").scrollIntoView({behavior: 'instant'});
            location.hash = '#content';
        </script>
    </body>
</html>