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
        <jsp:include page="/layout/header.jsp"/>
        <jsp:include page="/layout/dashboard-header.jsp"/>
        <div class="rbt-dashboard-area rbt-section-overlayping-top rbt-section-gapBottom">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <jsp:include page="/layout/dashboard-instructor-card.jsp"/>
                        <div class="row g-5">
                            <jsp:include page="/layout/dashboard-instrutor-sidebar.jsp"/>
                            <!-- Start Content  -->
                            <div class="col-lg-9">
                                <!-- Start Instructor Profile  -->
                                <div class="rbt-dashboard-content bg-color-white rbt-shadow-box">
                                    <div class="content">
                                        <div class="section-title">
                                            <h4 class="rbt-title-style-3">My Profile</h4>
                                        </div>
                                        <!-- Start Profile Row  -->
                                        <div class="rbt-profile-row row row--15">
                                            <div class="col-lg-4 col-md-4">
                                                <div class="rbt-profile-content b2">Registration Date: ${CreatedAt}</div>
                                            </div>
                                           
                                        </div>
                                        
                                        <form action="#" method="post" enctype="multipart/form-data">
                                            <input type="hidden" name="id" value="${id}">
                                            <div class="row">
                                                <div class="col-md-7 col-lg-8">
                                                    <div class="mb-3">
                                                        <label for="name" class="form-label">Full Name</label>
                                                        <input value="${name}" name="name" type="text" class="form-control" id="name" required>
                                                        <c:if test="${not empty nameError}">
                                                            <div class="form-text text-danger">${nameError}</div>
                                                        </c:if>
                                                    </div>

                                                    <div class="rbt-profile-row row row--15 mt--15">
                                                        <div class="col-lg-4 col-md-4">
                                                            <div class="rbt-profile-content b2">Username: ${username}</div>
                                                        </div>
                                                    </div>

                                                    <div class="rbt-profile-row row row--15 mt--15">
                                                        <div class="col-lg-4 col-md-4">
                                                            <div class="rbt-profile-content b2">Email: ${email}${gmail}</div>
                                                        </div>
                                                    </div>    




                                                    <div class="rbt-profile-row row row--15 mt--15">
                                                        <div class="col-lg-4 col-md-4">
                                                            <div class="rbt-profile-content b2">Phone number: ${phone}</div>
                                                        </div>
                                                    </div>    

                                                    <div class="mb-3">
                                                        <label for="image" class="form-label">Profile Picture</label>
                                                        <input name="image" id="image" class="form-control form-control-lg" type="file"  accept=".jpg, .jpeg, .png">
                                                        <c:if test="${not empty imageError}">
                                                            <div class="form-text text-danger">${imageError}</div>
                                                        </c:if>
                                                    </div>




                                                </div>
                                                <div class="col d-inline-flex">
                                                    <img class="mt-4" src="
                                                         <c:choose>
                                                             <c:when test="${not empty imageUrl}">
                                                                 ${imageUrl}
                                                             </c:when>
                                                             <c:otherwise>
                                                                 ${pageContext.request.contextPath}/assets/images/others/thumbnail-placeholder.svg
                                                             </c:otherwise>
                                                         </c:choose>
                                                         " alt="Preview image" class="rounded img-fluid" id="image-preview">
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
                    <!-- End Instructor Profile  -->
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