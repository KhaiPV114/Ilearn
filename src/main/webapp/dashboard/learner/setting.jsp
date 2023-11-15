<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iLearn | My Profile</title>
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
                            <c:choose>
                                <c:when test="${roleService.isLearner(pageContext.request)}">
                                    <jsp:include page="/layout/dashboard-learner-sidebar.jsp" />
                                </c:when>
                                <c:when test="${roleService.isInstructor(pageContext.request)}">
                                    <jsp:include page="/layout/dashboard-instrutor-sidebar.jsp"/>
                                </c:when>
                                <c:when test="${roleService.isManager(pageContext.request)}">
                                    <jsp:include page="/layout/dashboard-manager-sidebar.jsp"/>
                                </c:when>
                            </c:choose>

                            <!-- Start Content  -->
                            <div class="col-lg-9">
                                <!-- Start Instructor Profile  -->
                                <div class="rbt-dashboard-content bg-color-white rbt-shadow-box">
                                    <div class="content">

                                        <div class="section-title">
                                            <h4 class="rbt-title-style-3">My Profile</h4>
                                        </div>

                                        <div class="advance-tab-button mb--30">
                                            <ul class="nav nav-tabs tab-button-style-2 justify-content-start" id="settinsTab-4" role="tablist">
                                                <li role="presentation">
                                                    <a href="#" class="tab-button active" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile" role="tab" aria-controls="profile" aria-selected="true">
                                                        <span class="title">Profile</span>
                                                    </a>
                                                </li>
                                                <li role="presentation">
                                                    <a href="#" class="tab-button" id="password-tab" data-bs-toggle="tab" data-bs-target="#password" role="tab" aria-controls="password" aria-selected="false">
                                                        <span class="title">Password</span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </div>

                                        <div class="tab-content">
                                            <div class="tab-pane fade active show" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                                                <div class="rbt-dashboard-content-wrapper">
                                                    <div class="tutor-bg-photo bg_image bg_image--22 height-245"></div>
                                                    <!-- Start Tutor Information  -->
                                                    <div class="rbt-tutor-information">
                                                        <div class="rbt-tutor-information-left">
                                                            <div class="thumbnail rbt-avatars size-lg position-relative">
                                                                <img src="assets/images/team/avatar.jpg" alt="Instructor">
                                                                <div class="rbt-edit-photo-inner">
                                                                    <button class="rbt-edit-photo" title="Upload Photo">
                                                                        <i class="feather-camera"></i>
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="rbt-tutor-information-right">
                                                            <div class="tutor-btn">
                                                                <a class="rbt-btn btn-sm btn-border color-white radius-round-10" href="#">Edit Cover Photo</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- End Tutor Information  -->
                                                </div>
                                                <!-- Start Profile Row  -->
                                                <form action="#" class="rbt-profile-row rbt-default-form row row--15">
                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-12">
                                                        <div class="rbt-form-group">
                                                            <label for="firstname">First Name</label>
                                                            <input id="firstname" type="text" value="John">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-12">
                                                        <div class="rbt-form-group">
                                                            <label for="lastname">Last Name</label>
                                                            <input id="lastname" type="text" value="Due">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-12">
                                                        <div class="rbt-form-group">
                                                            <label for="username">User Name</label>
                                                            <input id="username" type="text" value="johndue">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-12">
                                                        <div class="rbt-form-group">
                                                            <label for="phonenumber">Phone Number</label>
                                                            <input id="phonenumber" type="tel" value="+1-202-555-0174">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-12">
                                                        <div class="rbt-form-group">
                                                            <label for="skill">Skill/Occupation</label>
                                                            <input id="skill" type="text" value="Full Stack Developer">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-12">
                                                        <div class="filter-select rbt-modern-select">
                                                            <label for="displayname" class="">Display name publicly as</label>
                                                            <div class="dropdown bootstrap-select w-100"><select id="displayname" class="w-100">
                                                                    <option>John Due</option>
                                                                    <option>John</option>
                                                                    <option>Due</option>
                                                                    <option>Due John</option>
                                                                    <option>johndue</option>
                                                                </select><button type="button" tabindex="-1" class="btn dropdown-toggle btn-light" data-bs-toggle="dropdown" role="combobox" aria-owns="bs-select-1" aria-haspopup="listbox" aria-expanded="false" title="John Due" data-id="displayname"><div class="filter-option"><div class="filter-option-inner"><div class="filter-option-inner-inner">John Due</div></div> </div></button><div class="dropdown-menu "><div class="inner show" role="listbox" id="bs-select-1" tabindex="-1"><ul class="dropdown-menu inner show" role="presentation"></ul></div></div></div>
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="rbt-form-group">
                                                            <label for="bio">Bio</label>
                                                            <textarea id="bio" cols="20" rows="5">I'm the Front-End Developer for #Rainbow IT in Bangladesh, OR. I have serious passion for UI effects, animations and creating intuitive, dynamic user experiences.</textarea>
                                                        </div>
                                                    </div>
                                                    <div class="col-12 mt--20">
                                                        <div class="rbt-form-group">
                                                            <a class="rbt-btn btn-gradient" href="#">Update Info</a>
                                                        </div>
                                                    </div>
                                                </form>
                                                <!-- End Profile Row  -->
                                            </div>
                                            <div class="tab-pane fade" id="password" role="tabpanel" aria-labelledby="password-tab">
                                                <!-- Start Profile Row  -->
                                                <form action="#" class="rbt-profile-row rbt-default-form row row--15">
                                                    <div class="col-12">
                                                        <div class="rbt-form-group">
                                                            <label for="currentpassword">Current Password</label>
                                                            <input id="currentpassword" type="password" placeholder="Current Password">
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="rbt-form-group">
                                                            <label for="newpassword">New Password</label>
                                                            <input id="newpassword" type="password" placeholder="New Password">
                                                        </div>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="rbt-form-group">
                                                            <label for="retypenewpassword">Re-type New Password</label>
                                                            <input id="retypenewpassword" type="password" placeholder="Re-type New Password">
                                                        </div>
                                                    </div>
                                                    <div class="col-12 mt--10">
                                                        <div class="rbt-form-group">
                                                            <a class="rbt-btn btn-gradient" href="#">Update Password</a>
                                                        </div>
                                                    </div>
                                                </form>
                                                <!-- End Profile Row  -->
                                            </div>
                                        </div>
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