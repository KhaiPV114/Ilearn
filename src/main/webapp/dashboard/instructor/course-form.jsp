<%@page contentType="text/html" pageEncoding="UTF-8" buffer="8192kb" autoFlush="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course</title>
        <jsp:include page="/layout/links.jsp" />
    </head>
    <body>
        <jsp:include page="/layout/header.jsp" />
        <jsp:include page="/layout/dashboard-header.jsp" />
        <div class="rbt-dashboard-area rbt-section-overlayping-top rbt-section-gapBottom">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <jsp:include page="/layout/dashboard-instructor-card.jsp" />
                        <div class="row g-5">
                            <jsp:include page="/layout/dashboard-instructor-sidebar.jsp" />
                            <!-- Start Content  -->
                            <div class="col-lg-9" id="content">
                                <div class="rbt-dashboard-content bg-color-white rbt-shadow-box">
                                    <div class="content">
                                        <!-- Start Title -->
                                        <div class="section-title">
                                            <!--<h4 class="rbt-title-style-3">Add course</h4>-->
                                            <nav class="rbt-title-style-3 h4 pb-0" aria-label="breadcrumb">
                                                <ol class="breadcrumb">
                                                    <li class="breadcrumb-item">
                                                        <a href="${pageContext.request.contextPath}/instructor/course">
                                                            Course
                                                        </a>
                                                    </li>
                                                    <li class="breadcrumb-item active" aria-current="page">
                                                        <a class="color-primary" href="#">
                                                            <c:choose>
                                                                <c:when test="${isEditPage}">
                                                                    Edit
                                                                </c:when>
                                                                <c:otherwise>
                                                                    Add
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </a>
                                                    </li>
                                                </ol>
                                            </nav>
                                        </div>
                                        <!-- End Title -->

                                        <form action="#" method="post" enctype="multipart/form-data">
                                            <input type="hidden" name="id" value="${id}">
                                            <div class="row">
                                                <div class="col-md-7 col-lg-8">
                                                    <div class="mb-3">
                                                        <label for="name" class="form-label">Course name</label>
                                                        <input value="${name}" name="name" type="text" class="form-control" id="name" required>
                                                        <c:if test="${not empty nameError}">
                                                            <div class="form-text text-danger">${nameError}</div>
                                                        </c:if>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="image" class="form-label">Course image</label>
                                                        <input name="image" id="image" class="form-control form-control-lg" type="file"  accept=".jpg, .jpeg, .png">
                                                        <c:if test="${not empty imageError}">
                                                            <div class="form-text text-danger">${imageError}</div>
                                                        </c:if>
                                                    </div>
                                                    <c:if test="${not empty categoryError}">
                                                        <span class="text-warning">${categoryError}</span>
                                                    </c:if>
                                                    <div class="mb-3">
                                                        <label for="field-4">Course category</label>
                                                        <div class="rbt-modern-select bg-transparent height-50 mb--10 mt-2">
                                                            <div class="dropdown bootstrap-select w-100">
                                                                <select name="category" class="w-100" id="field-4" tabindex="null" required>
                                                                    <c:forEach var="item" items="${categories}">
                                                                        <option value="${item.id}">${item.name}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="price" class="form-label">Course price <i>(0 for free course)</i></label>
                                                        <input value="${price}" name="price" type="number" step="0.01" min="0" max="10000000" class="form-control" id="price" required>
                                                        <c:if test="${not empty priceError}">
                                                            <div class="form-text text-danger">${priceError}</div>
                                                        </c:if>
                                                    </div>
                                                </div>
                                                <div class="col d-inline-flex">
                                                    <img src="
                                                         <c:choose>
                                                             <c:when test="${not empty imageUrl}">
                                                                 ${imageUrl}
                                                             </c:when>
                                                             <c:otherwise>
                                                                 ${pageContext.request.contextPath}/assets/images/others/thumbnail-placeholder.svg
                                                             </c:otherwise>
                                                         </c:choose>
                                                         " alt="Preview image" class="mt-4 rounded img-fluid" id="image-preview">
                                                </div>
                                                <div class="mb-3">
                                                    <label for="description" class="form-label">Description</label>
                                                    <c:choose>
                                                        <c:when test="${not empty description}">
                                                            <textarea required name="description" type="text" rows="3" class="form-control" style="font-size: 16px" id="description">${description}</textarea>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <textarea required name="description" type="text" rows="3" class="form-control" style="font-size: 16px" id="description"></textarea>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:if test="${not empty descriptionError}">
                                                        <div class="form-text text-danger">${descriptionError}</div>
                                                    </c:if>
                                                </div>

                                            </div>
                                            <button type="submit" class="rbt-btn btn-md mt-3">Submit</button>
                                        </form>
                                        <c:if test="${not empty success}">
                                            <div class="form-text text-success fw-bold mt-3">${success}</div>
                                        </c:if>
                                        <c:if test="${not empty error}">
                                            <div class="form-text text-danger fw-bold mt-3">${error}</div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <!-- End Content  -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            let imagePreviewDiv = document.getElementById("image-preview");
            let imageInput = document.getElementById("image");
            imageInput.addEventListener('change', (event) => {
                if (event.target.files) {
                    let [image] = event.target.files;
                    imagePreviewDiv.src = URL.createObjectURL(image);
                }
            });
            document.getElementById("content").scrollIntoView({behavior: 'instant'});
            location.hash = '#content';
        </script>
        <jsp:include page="/layout/footer.jsp" />
        <jsp:include page="/layout/scripts.jsp" />
    </body>
</html>