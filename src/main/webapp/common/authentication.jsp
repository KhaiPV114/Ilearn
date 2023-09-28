<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Authentication</title>
        <jsp:include page="/layout/links.jsp"/>
        <style>
            .togglePassword {
                position: absolute;
                margin-left: -40px;
                margin-top: 15px;
                display: inline-block;
                font-size: 2.5rem;
                z-index: 100;
                cursor: pointer;
            }
        </style>
        <script src="https://accounts.google.com/gsi/client" async></script>
    </head>
    <body>
        <jsp:include page="/layout/header.jsp"/>
        <div class="rbt-breadcrumb-default ptb--100 ptb_md--50 ptb_sm--30 bg-gradient-1">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="breadcrumb-inner text-center">
                            <h2 class="title">Login &amp; Register</h2>
                            <ul class="page-list">
                                <li class="rbt-breadcrumb-item"><a href="index.html">Home</a></li>
                                <li>
                                    <div class="icon-right"><i class="feather-chevron-right"></i></div>
                                </li>
                                <li class="rbt-breadcrumb-item active">Login &amp; Register</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="rbt-elements-area bg-color-white rbt-section-gap">
            <div class="container">
                <div id="content" class="row gy-5 row--30">
                    <div class="col-lg-6">
                        <div class="rbt-contact-form contact-form-style-1 max-width-auto">
                            <h3 class="title">Login</h3>
                            <form action="${pageContext.request.contextPath}/authentication/login" method="post" class="max-width-auto">
                                <div class="form-group">
                                    <input name="l_username" value="${l_username}" id="l_username" type="text" required>
                                    <label>Username or email *</label>
                                </div>
                                <div class="form-group">
                                    <input name="l_password" value="${l_password}" id="l_password" type="password" required>
                                    <i class="feather-eye togglePassword" id="l_togglePassword"></i>
                                    <label>Password *</label>
                                    <span class="focus-border"></span>
                                </div>

                                <div class="row mb--30">
                                    <div class="col-lg-6">
                                        <div class="rbt-checkbox">
                                            <input type="checkbox" id="rememberme" 
                                                   name="l_rememberme"
                                                   value="true"
                                                   <c:if test="${l_username ne null}">
                                                       checked
                                                   </c:if>
                                                   >
                                            <label for="rememberme">Remember me</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="rbt-lost-password text-end">
                                            <a class="rbt-btn-link" href="#">Lost your password?</a>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-submit-group">
                                    <button type="submit" class="rbt-btn btn-md btn-gradient hover-icon-reverse w-100">
                                        <span class="icon-reverse-wrapper">
                                            <span class="btn-text">Log In</span>
                                            <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                            <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                        </span>
                                    </button>
                                </div>
                            </form>
                            <button id="custom-google-btn" class="rbt-btn btn-md btn-gradient hover-icon-reverse w-100 mt-4">
                                <span class="icon-reverse-wrapper">
                                    <span class="btn-text">Log in with Google</span>
                                    <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                    <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                </span>
                            </button>
                            <div class="g_id_signin mt-4 d-none"
                                 data-type="standard"
                                 data-shape="rectangular"
                                 data-theme="outline"
                                 data-text="signin_with"
                                 data-size="large"
                                 data-logo_alignment="center">
                            </div>
                            <c:if test="${not empty l_error}">
                                <p class="text-danger mt-4 fw-bold">${l_error}</p>
                            </c:if>
                            <c:if test="${not empty l_success}">
                                <p class="text-success mt-4 fw-bold">${l_success}</p>
                            </c:if>
                        </div>
                    </div>

                    <div class="col-lg-6">
                        <div class="rbt-contact-form contact-form-style-1 max-width-auto">
                            <h3 class="title">Register</h3>
                            <form action="${pageContext.request.contextPath}/authentication/register" method="post" class="max-width-auto">
                                <c:if test="${not empty r_username_error}">
                                    <span class="text-warning">${r_username_error}</span>
                                </c:if>
                                <div class="form-group">
                                    <input name="r_username" value="${r_username}" type="text" required>
                                    <label>Username *</label>
                                    <span class="focus-border"></span>
                                </div>

                                <c:if test="${not empty r_password_error}">
                                    <span class="text-warning">${r_password_error}</span>
                                </c:if>
                                <div class="form-group">
                                    <input name="r_password" value="${r_password}" id="r_password" type="password" required>
                                    <label>Password *</label>
                                    <i class="feather-eye togglePassword" id="r_togglePassword"></i>
                                    <span class="focus-border"></span>
                                </div>

                                <c:if test="${not empty r_email_error}">
                                    <span class="text-warning">${r_email_error}</span>
                                </c:if>
                                <div class="form-group">
                                    <input name="r_email" value="${r_email}" type="text" required>
                                    <label>Email address *</label>
                                    <span class="focus-border"></span>
                                </div>

                                <c:if test="${not empty r_fullname_error}">
                                    <span class="text-warning">${r_fullname_error}</span>
                                </c:if>
                                <div class="form-group">
                                    <input name="r_fullname" value="${r_fullname}" type="text" required>
                                    <label>Full name *</label>
                                    <span class="focus-border"></span>
                                </div>

                                <div class="form-group">
                                    <p class="mb--0">Date of birth *</p>
                                    <input class="mt--0" name="r_dob" value="${r_dob}" type="date" required>
                                    <span class="focus-border"></span>
                                </div>

                                <c:if test="${not empty r_phoneNumber_error}">
                                    <span class="text-warning">${r_phoneNumber_error}</span>
                                </c:if>
                                <div class="form-group">
                                    <input name="r_phoneNumber" value="${r_phoneNumber}" type="text" required>
                                    <label>Phone number *</label>
                                    <span class="focus-border"></span>
                                </div>

                                <c:if test="${not empty r_role_error}">
                                    <span class="text-warning">${r_role_error}</span>
                                </c:if>
                                <div class="mb--20">
                                    <label for="field-4">Account type</label>
                                    <div class="rbt-modern-select bg-transparent height-45 mb--10">
                                        <div class="dropdown bootstrap-select w-100">
                                            <select name="r_role" class="w-100" id="field-4" tabindex="null">
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
                            <c:if test="${not empty r_error}">
                                <p class="text-danger mt-4 fw-bold">${r_error}</p>
                            </c:if>
                            <c:if test="${not empty r_success}">
                                <p class="text-success mt-4 fw-bold">${r_success}</p>
                            </c:if>
                        </div>
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
                        <c:if test="${not empty r_success}">
                            <p class="text-success my-3 fw-bold">${r_success}</p>
                        </c:if>
                        <c:if test="${not empty g_error}">
                            <p class="text-danger my-3 fw-bold">${g_error}</p>
                        </c:if>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="rbt-btn btn-sm btn-gradient" data-bs-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/layout/footer.jsp"/>
        <jsp:include page="/layout/scripts.jsp"/>
        <c:if test="${(not empty r_success) or (not empty g_error)}">
            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    var myModal = new bootstrap.Modal(document.getElementById('myModal'));
                    myModal.toggle();
                });
            </script>
        </c:if>
        <script>
            //Toggle register password
            const r_togglePassword = document.querySelector("#r_togglePassword");
            const r_password = document.querySelector("#r_password");

            r_togglePassword.addEventListener("click", function () {
                const type = r_password.getAttribute("type") === "password" ? "text" : "password";
                r_password.setAttribute("type", type);
                // toggle the eye icon
                this.classList.toggle('feather-eye');
                this.classList.toggle('feather-eye-off');
            });

            //Toggle login password
            const l_togglePassword = document.querySelector("#l_togglePassword");
            const l_password = document.querySelector("#l_password");

            l_togglePassword.addEventListener("click", function () {
                const type = l_password.getAttribute("type") === "password" ? "text" : "password";
                l_password.setAttribute("type", type);
                // toggle the eye icon
                this.classList.toggle('feather-eye');
                this.classList.toggle('feather-eye-off');
            });

            //SonNVQ: Fix overlap label when input have pre value attribute
            let inputs = document.getElementsByTagName("input");
            for (let i of inputs) {
                i.focus();
                i.blur();
            }

            document.getElementById("content").scrollIntoView({behavior: 'instant'});
            location.hash = '#content';
        </script>
        <div id="g_id_onload"
             data-client_id="${initParam.GOOGLE_CLIENT_ID}"
             data-context="signin"
             data-ux_mode="redirect"
             data-login_uri="${pageContext.request.contextPath}/authentication/login-with-google"
             data-nonce=""
             data-itp_support="true">
        </div>
        <script>
            const googleBtn = document.getElementById('custom-google-btn');

            googleBtn.addEventListener('click', () => {
                document.querySelector('.g_id_signin div[role=button]').click();
            });
        </script>
    </body>
</html>