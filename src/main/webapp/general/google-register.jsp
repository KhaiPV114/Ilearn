<%@page contentType="text/html" pageEncoding="UTF-8" buffer="8192kb" autoFlush="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iLearn | Register</title>
        <jsp:include page="/layout/links.jsp"/>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-lg-6 mx-auto">
                    <div class="rbt-contact-form contact-form-style-1 max-width-auto">
                        <h3 class="title">Register last step...</h3>
                        <h5>To finish sign up process, please choose your account type and add necessary information. Thank you!</h5>
                        <form action="${pageContext.request.contextPath}/authentication/register/google" method="post" class="max-width-auto">

                            <div class="form-group">
                                <span>
                                    Email address: <b>${g_email}</b>
                                </span>
                            </div>

                            <c:if test="${not empty g_fullname_error}">
                                <span class="text-warning">${g_fullname_error}</span>
                            </c:if>
                            <div class="form-group">
                                <input name="g_fullname" value="${g_fullname}" type="text" required>
                                <label>Full name *</label>
                                <span class="focus-border"></span>
                            </div>

                            <c:if test="${not empty g_dob_error}">
                                <span class="text-warning">${g_dob_error}</span>
                            </c:if>
                            <div class="form-group">
                                <span class="mb--0">Date of birth *</span>
                                <input class="mt--0" name="g_dob" value="${g_dob}" type="date" required>
                                <span class="focus-border"></span>
                            </div>

                            <c:if test="${not empty g_phoneNumber_error}">
                                <span class="text-warning">${g_phoneNumber_error}</span>
                            </c:if>
                            <div class="form-group">
                                <input name="g_phoneNumber" value="${g_phoneNumber}" type="text" required>
                                <label>Phone number *</label>
                                <span class="focus-border"></span>
                            </div>

                            <c:if test="${not empty g_role_error}">
                                <span class="text-warning">${g_role_error}</span>
                            </c:if>
                            <div class="mb--20">
                                <label for="field-4">Account type</label>
                                <div class="rbt-modern-select bg-transparent height-45 mb--10">
                                    <div class="dropdown bootstrap-select w-100">
                                        <select name="g_role" class="w-100" id="field-4" tabindex="null">
                                            <option value="LEARNER">Learner</option>
                                            <option value="INSTRUCTOR">Instructor</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="form-submit-group">
                                <button type="submit" class="rbt-btn btn-md btn-gradient hover-icon-reverse w-100">
                                    <span class="icon-reverse-wrapper">
                                        <span class="btn-text">Register</span>
                                        <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                        <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                    </span>
                                </button>
                            </div>

                        </form>
                        <c:if test="${not empty g_error}">
                            <p class="text-danger mt-4 fw-bold">${g_error}</p>
                        </c:if>
                        <c:if test="${not empty g_success}">
                            <p class="text-success mt-4 fw-bold">${g_success}</p>
                        </c:if>
                    </div>
                    <div class="mb--10">
                        <a class="link-warning fw-bold" href="${pageContext.request.contextPath}/authentication/logout">Want to logout? Click here!</a>
                    </div>
                </div>
            </div>
        </div>

        <div id="myModal" class="modal" tabindex="-1">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content border-1 border-color-card-3">
                    <div class="modal-header">
                        <h5 class="modal-title">Notification</h5>
                        <button type="rbt-btn" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <c:if test="${not empty g_success}">
                            <p class="text-success my-3 fw-bold">${g_success}</p>
                        </c:if>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="rbt-btn btn-sm btn-gradient" 
                                onclick="document.location.href = '${pageContext.request.contextPath}'" data-bs-dismiss="modal">Continue</button>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="/layout/scripts.jsp"/>
        <script>
            //SonNVQ: Fix overlap label when input have pre value attribute
            let inputs = document.getElementsByTagName("input");
            for (let i of inputs) {
                i.focus();
                i.blur();
            }
        </script>
        <c:if test="${not empty g_success}">
            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    var myModal = new bootstrap.Modal(document.getElementById('myModal'));
                    myModal.toggle();
                });
            </script>
        </c:if>
    </body>
</html>