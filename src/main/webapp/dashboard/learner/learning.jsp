<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html class="sizes customelements history pointerevents postmessage webgl websockets cssanimations csscolumns csscolumns-width csscolumns-span csscolumns-fill csscolumns-gap csscolumns-rule csscolumns-rulecolor csscolumns-rulestyle csscolumns-rulewidth csscolumns-breakbefore csscolumns-breakafter csscolumns-breakinside flexbox picture srcset webworkers">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="/online-learning/assets/images/favicon.png">
        <!-- CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/vendor/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/vendor/slick.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/vendor/slick-theme.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/plugins/sal.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/plugins/feather.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/plugins/fontawesome.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/plugins/euclid-circulara.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/plugins/swiper.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/plugins/magnify.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/plugins/odometer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/plugins/animation.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/plugins/bootstrap-select.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/plugins/jquery-ui.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/plugins/magnigy-popup.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/custom.css">
        <style>
            .comment-card {
                position: relative;
                display: flex;
                padding:20px;
                flex-direction: column;
                min-width: 0;
                word-wrap: break-word;
                background-color: #fff;
                background-clip: border-box;
            }

            .comment-author {
                margin-bottom: 0;
            }

            .comment-media {
                display: flex;
                align-content: start;
            }

            .comment-media img{
                margin-right: 20px;
                width: 60px;
                height: 60px;
            }

            .comment-img{
                margin-right: 20px;
                width: 60px;
                height: 60px;
            }

            .comment-reply a {

                text-decoration: none;
            }
        </style>
    </head>

    <body class="rbt-header-sticky"><div hidden="" id="sprite-plyr"><!--?xml version="1.0" encoding="UTF-8"?--><svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"><symbol id="plyr-airplay" viewBox="0 0 18 18"><path d="M16 1H2a1 1 0 00-1 1v10a1 1 0 001 1h3v-2H3V3h12v8h-2v2h3a1 1 0 001-1V2a1 1 0 00-1-1z"></path><path d="M4 17h10l-5-6z"></path></symbol><symbol id="plyr-captions-off" viewBox="0 0 18 18"><path d="M1 1c-.6 0-1 .4-1 1v11c0 .6.4 1 1 1h4.6l2.7 2.7c.2.2.4.3.7.3.3 0 .5-.1.7-.3l2.7-2.7H17c.6 0 1-.4 1-1V2c0-.6-.4-1-1-1H1zm4.52 10.15c1.99 0 3.01-1.32 3.28-2.41l-1.29-.39c-.19.66-.78 1.45-1.99 1.45-1.14 0-2.2-.83-2.2-2.34 0-1.61 1.12-2.37 2.18-2.37 1.23 0 1.78.75 1.95 1.43l1.3-.41C8.47 4.96 7.46 3.76 5.5 3.76c-1.9 0-3.61 1.44-3.61 3.7 0 2.26 1.65 3.69 3.63 3.69zm7.57 0c1.99 0 3.01-1.32 3.28-2.41l-1.29-.39c-.19.66-.78 1.45-1.99 1.45-1.14 0-2.2-.83-2.2-2.34 0-1.61 1.12-2.37 2.18-2.37 1.23 0 1.78.75 1.95 1.43l1.3-.41c-.28-1.15-1.29-2.35-3.25-2.35-1.9 0-3.61 1.44-3.61 3.7 0 2.26 1.65 3.69 3.63 3.69z" fill-rule="evenodd" fill-opacity=".5"></path></symbol><symbol id="plyr-captions-on" viewBox="0 0 18 18"><path d="M1 1c-.6 0-1 .4-1 1v11c0 .6.4 1 1 1h4.6l2.7 2.7c.2.2.4.3.7.3.3 0 .5-.1.7-.3l2.7-2.7H17c.6 0 1-.4 1-1V2c0-.6-.4-1-1-1H1zm4.52 10.15c1.99 0 3.01-1.32 3.28-2.41l-1.29-.39c-.19.66-.78 1.45-1.99 1.45-1.14 0-2.2-.83-2.2-2.34 0-1.61 1.12-2.37 2.18-2.37 1.23 0 1.78.75 1.95 1.43l1.3-.41C8.47 4.96 7.46 3.76 5.5 3.76c-1.9 0-3.61 1.44-3.61 3.7 0 2.26 1.65 3.69 3.63 3.69zm7.57 0c1.99 0 3.01-1.32 3.28-2.41l-1.29-.39c-.19.66-.78 1.45-1.99 1.45-1.14 0-2.2-.83-2.2-2.34 0-1.61 1.12-2.37 2.18-2.37 1.23 0 1.78.75 1.95 1.43l1.3-.41c-.28-1.15-1.29-2.35-3.25-2.35-1.9 0-3.61 1.44-3.61 3.7 0 2.26 1.65 3.69 3.63 3.69z" fill-rule="evenodd"></path></symbol><symbol id="plyr-download" viewBox="0 0 18 18"><path d="M9 13c.3 0 .5-.1.7-.3L15.4 7 14 5.6l-4 4V1H8v8.6l-4-4L2.6 7l5.7 5.7c.2.2.4.3.7.3zm-7 2h14v2H2z"></path></symbol><symbol id="plyr-enter-fullscreen" viewBox="0 0 18 18"><path d="M10 3h3.6l-4 4L11 8.4l4-4V8h2V1h-7zM7 9.6l-4 4V10H1v7h7v-2H4.4l4-4z"></path></symbol><symbol id="plyr-exit-fullscreen" viewBox="0 0 18 18"><path d="M1 12h3.6l-4 4L2 17.4l4-4V17h2v-7H1zM16 .6l-4 4V1h-2v7h7V6h-3.6l4-4z"></path></symbol><symbol id="plyr-fast-forward" viewBox="0 0 18 18"><path d="M7.875 7.171L0 1v16l7.875-6.171V17L18 9 7.875 1z"></path></symbol><symbol id="plyr-logo-vimeo" viewBox="0 0 18 18"><path d="M17 5.3c-.1 1.6-1.2 3.7-3.3 6.4-2.2 2.8-4 4.2-5.5 4.2-.9 0-1.7-.9-2.4-2.6C5 10.9 4.4 6 3 6c-.1 0-.5.3-1.2.8l-.8-1c.8-.7 3.5-3.4 4.7-3.5 1.2-.1 2 .7 2.3 2.5.3 2 .8 6.1 1.8 6.1.9 0 2.5-3.4 2.6-4 .1-.9-.3-1.9-2.3-1.1.8-2.6 2.3-3.8 4.5-3.8 1.7.1 2.5 1.2 2.4 3.3z"></path></symbol><symbol id="plyr-logo-youtube" viewBox="0 0 18 18"><path d="M16.8 5.8c-.2-1.3-.8-2.2-2.2-2.4C12.4 3 9 3 9 3s-3.4 0-5.6.4C2 3.6 1.3 4.5 1.2 5.8 1 7.1 1 9 1 9s0 1.9.2 3.2c.2 1.3.8 2.2 2.2 2.4C5.6 15 9 15 9 15s3.4 0 5.6-.4c1.4-.3 2-1.1 2.2-2.4.2-1.3.2-3.2.2-3.2s0-1.9-.2-3.2zM7 12V6l5 3-5 3z"></path></symbol><symbol id="plyr-muted" viewBox="0 0 18 18"><path d="M12.4 12.5l2.1-2.1 2.1 2.1 1.4-1.4L15.9 9 18 6.9l-1.4-1.4-2.1 2.1-2.1-2.1L11 6.9 13.1 9 11 11.1zM3.786 6.008H.714C.286 6.008 0 6.31 0 6.76v4.512c0 .452.286.752.714.752h3.072l4.071 3.858c.5.3 1.143 0 1.143-.602V2.752c0-.601-.643-.977-1.143-.601L3.786 6.008z"></path></symbol><symbol id="plyr-pause" viewBox="0 0 18 18"><path d="M6 1H3c-.6 0-1 .4-1 1v14c0 .6.4 1 1 1h3c.6 0 1-.4 1-1V2c0-.6-.4-1-1-1zm6 0c-.6 0-1 .4-1 1v14c0 .6.4 1 1 1h3c.6 0 1-.4 1-1V2c0-.6-.4-1-1-1h-3z"></path></symbol><symbol id="plyr-pip" viewBox="0 0 18 18"><path d="M13.293 3.293L7.022 9.564l1.414 1.414 6.271-6.271L17 7V1h-6z"></path><path d="M13 15H3V5h5V3H2a1 1 0 00-1 1v12a1 1 0 001 1h12a1 1 0 001-1v-6h-2v5z"></path></symbol><symbol id="plyr-play" viewBox="0 0 18 18"><path d="M15.562 8.1L3.87.225c-.818-.562-1.87 0-1.87.9v15.75c0 .9 1.052 1.462 1.87.9L15.563 9.9c.584-.45.584-1.35 0-1.8z"></path></symbol><symbol id="plyr-restart" viewBox="0 0 18 18"><path d="M9.7 1.2l.7 6.4 2.1-2.1c1.9 1.9 1.9 5.1 0 7-.9 1-2.2 1.5-3.5 1.5-1.3 0-2.6-.5-3.5-1.5-1.9-1.9-1.9-5.1 0-7 .6-.6 1.4-1.1 2.3-1.3l-.6-1.9C6 2.6 4.9 3.2 4 4.1 1.3 6.8 1.3 11.2 4 14c1.3 1.3 3.1 2 4.9 2 1.9 0 3.6-.7 4.9-2 2.7-2.7 2.7-7.1 0-9.9L16 1.9l-6.3-.7z"></path></symbol><symbol id="plyr-rewind" viewBox="0 0 18 18"><path d="M10.125 1L0 9l10.125 8v-6.171L18 17V1l-7.875 6.171z"></path></symbol><symbol id="plyr-settings" viewBox="0 0 18 18"><path d="M16.135 7.784a2 2 0 01-1.23-2.969c.322-.536.225-.998-.094-1.316l-.31-.31c-.318-.318-.78-.415-1.316-.094a2 2 0 01-2.969-1.23C10.065 1.258 9.669 1 9.219 1h-.438c-.45 0-.845.258-.997.865a2 2 0 01-2.969 1.23c-.536-.322-.999-.225-1.317.093l-.31.31c-.318.318-.415.781-.093 1.317a2 2 0 01-1.23 2.969C1.26 7.935 1 8.33 1 8.781v.438c0 .45.258.845.865.997a2 2 0 011.23 2.969c-.322.536-.225.998.094 1.316l.31.31c.319.319.782.415 1.316.094a2 2 0 012.969 1.23c.151.607.547.865.997.865h.438c.45 0 .845-.258.997-.865a2 2 0 012.969-1.23c.535.321.997.225 1.316-.094l.31-.31c.318-.318.415-.781.094-1.316a2 2 0 011.23-2.969c.607-.151.865-.547.865-.997v-.438c0-.451-.26-.846-.865-.997zM9 12a3 3 0 110-6 3 3 0 010 6z"></path></symbol><symbol id="plyr-volume" viewBox="0 0 18 18"><path d="M15.6 3.3c-.4-.4-1-.4-1.4 0-.4.4-.4 1 0 1.4C15.4 5.9 16 7.4 16 9c0 1.6-.6 3.1-1.8 4.3-.4.4-.4 1 0 1.4.2.2.5.3.7.3.3 0 .5-.1.7-.3C17.1 13.2 18 11.2 18 9s-.9-4.2-2.4-5.7z"></path><path d="M11.282 5.282a.909.909 0 000 1.316c.735.735.995 1.458.995 2.402 0 .936-.425 1.917-.995 2.487a.909.909 0 000 1.316c.145.145.636.262 1.018.156a.725.725 0 00.298-.156C13.773 11.733 14.13 10.16 14.13 9c0-.17-.002-.34-.011-.51-.053-.992-.319-2.005-1.522-3.208a.909.909 0 00-1.316 0zm-7.496.726H.714C.286 6.008 0 6.31 0 6.76v4.512c0 .452.286.752.714.752h3.072l4.071 3.858c.5.3 1.143 0 1.143-.602V2.752c0-.601-.643-.977-1.143-.601L3.786 6.008z"></path></symbol></svg></div>

        <div class="rbt-lesson-area bg-color-white">
            <div class="rbt-lesson-content-wrapper">
                <div class="rbt-lesson-leftsidebar">
                    <div class="rbt-course-feature-inner rbt-search-activation">
                        <div class="section-title">
                            <h4 class="rbt-title-style-3">Course Content</h4>
                        </div>
                        <div class="rbt-progress-style-1 p-4">
                            <div class="single-progress">
                                <h6 class="title">Learning progress</h6>
                                <div class="progress">
                                    <div class="progress-bar wow fadeInLeft" data-wow-duration="0.5s" data-wow-delay=".3s" role="progressbar" 
                                         style="width: ${progress}%; visibility: visible; animation-duration: 0.5s; animation-delay: 0.3s; animation-name: fadeInLeft;" 
                                         aria-valuenow="${progress}" 
                                         aria-valuemin="0" 
                                         aria-valuemax="100">
                                    </div>
                                    <span class="progress-number">${progress}%</span>
                                </div>
                            </div>
                        </div>
                        <hr>

                        <div class="lesson-search-wrapper">
                            <form action="#" class="rbt-search-style-1">
                                <input class="rbt-search-active" type="text" placeholder="Search Lesson">
                                <button class="search-btn disabled"><i class="feather-search"></i></button>
                            </form>
                        </div>

                        <hr class="mt--10">

                        <div class="rbt-accordion-style rbt-accordion-02 for-right-content accordion">


                            <div class="accordion" id="accordionExampleb2">

                                <c:forEach var="section" items="${sections}" varStatus="sectionStatus">
                                    <div class="accordion-item card">
                                        <h2 class="accordion-header card-header" id="headingTwo1">
                                            <button class="accordion-button" type="button" data-bs-toggle="collapse" aria-expanded="true" data-bs-target="#collapse-section-${section.id}" aria-controls="collapseTwo1">
                                                ${section.name}
                                            </button>
                                        </h2>
                                        <c:set var="sectionId" value="${section.id}"/>
                                        <div id="collapse-section-${section.id}" class="accordion-collapse collapse show" aria-labelledby="headingTwo1">
                                            <div class="accordion-body card-body">
                                                <ul class="rbt-course-main-content liststyle">
                                                    <c:set var="lessons" value="${lessonsList[sectionId]}"/>
                                                    <c:forEach var="lesson" varStatus="lessonStatus" items="${lessons}">
                                                        <li>
                                                            <c:choose>
                                                                <c:when test="${lesson.id eq lessonId}">
                                                                    <c:if test="${lessonStatus.index - 1 >= 0}">
                                                                        <c:set var="previousLessonId" value="${lessons[lessonStatus.index - 1].id}"/>
                                                                    </c:if>

                                                                    <c:if test="${lessonStatus.index + 1 < lessons.size()}">
                                                                        <c:set var="nextLessonId" value="${lessons[lessonStatus.index + 1].id}"/>
                                                                    </c:if>
                                                                </c:when>
                                                            </c:choose>
                                                            <a href="${pageContext.request.contextPath}/learn?courseId=${courseId}&lessonId=${lesson.id}"
                                                               <c:if test="${lesson.id eq lessonId}">
                                                                   class="active"
                                                               </c:if>
                                                               >
                                                                <div class="course-content-left">
                                                                    <i class="feather-play-circle"></i> <span class="text">${lesson.name}</span>
                                                                </div>
                                                                <div class="course-content-right">
                                                                    <span class="min-lable">30 min</span>
                                                                    <span class="rbt-check">
                                                                        <c:choose>
                                                                            <c:when test="${trackings[lesson.id] eq 'FINISHED'}">
                                                                                <i class="feather-check"></i>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <i class="feather-loader"></i>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </span>
                                                                </div>
                                                            </a>
                                                        </li>
                                                    </c:forEach>     

                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>

                            </div>

                        </div>
                    </div>
                </div>

                <div class="rbt-lesson-rightsidebar overflow-hidden lesson-video">
                    <div class="lesson-top-bar">
                        <div class="lesson-top-left">
                            <div class="rbt-lesson-toggle">
                                <button class="lesson-toggle-active btn-round-white-opacity" title="Toggle Sidebar"><i class="feather-arrow-left"></i></button>
                            </div>
                            <h5>${lesson.name}</h5>
                        </div>
                        <div class="lesson-top-right">
                            <div class="rbt-btn-close">
                                <a href="${pageContext.request.contextPath}/" title="Go Back to Course" class="rbt-round-btn"><i class="feather-x"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="inner p-5">
                        ${lesson.content}
                    </div>

                    <div id="change-status" class="bg-color-extra2 ptb--15 overflow-hidden">
                        <div class="rbt-button-group">
                            <c:if test="${not empty previousLessonId}">
                                <a class="rbt-btn icon-hover icon-hover-left btn-md bg-primary-opacity" 
                                   href="${pageContext.request.contextPath}/learn?courseId=${courseId}&lessonId=${previousLessonId}">
                                    <span class="btn-icon"><i class="feather-arrow-left"></i></span>
                                    <span class="btn-text">Previous</span>
                                </a>
                            </c:if>
                            <form id="change-lesson-status" action="${pageContext.request.contextPath}/learn/status" method="POST">
                                <input type="hidden" name="lessonId" value="${lesson.id}">
                                <input type="hidden" name="courseId" value="${courseId}">
                            </form>
                            <a class="rbt-btn icon-hover icon-hover-left btn-md bg-primary-opacity" 
                               href="#"
                               onclick="document.getElementById('change-lesson-status').submit()"
                               >
                                <c:choose>
                                    <c:when test="${tracking.status eq 'FINISHED'}">
                                        <span class="btn-icon"><i class="feather-loader"></i></span>
                                        <span class="btn-text">Mark as learning</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="btn-icon"><i class="feather-check"></i></span>
                                        <span class="btn-text">Mark as completed</span>
                                    </c:otherwise>
                                </c:choose>
                            </a>
                            <c:if test="${not empty nextLessonId}">
                                <a class="rbt-btn icon-hover btn-md" 
                                   href="${pageContext.request.contextPath}/learn?courseId=${courseId}&lessonId=${nextLessonId}">
                                    <span class="btn-text">Next</span>
                                    <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                </a>
                            </c:if>
                        </div>
                    </div>

                    <div class="container mb-5 mt-5">

                        <div class="comment-card">
                            <div class="row">
                                <div class="col-md-12">
                                    <h3 class="text-center mb-5">
                                        Lesson discuss
                                    </h3>
                                    <div class="row">
                                        <div class="col-md-12">

                                            <!-- Add comment -->
                                            <form id="add-comment" action="${pageContext.request.contextPath}/learn/comment/add" method="POST">
                                                <input type="hidden" name="courseId" value="${courseId}"/>
                                                <input type="hidden" name="lessonId" value="${lessonId}"/>
                                                <div class="d-block mb-5">
                                                    <div class="mb-3 d-flex align-items-center">
                                                        <img
                                                            class="comment-img mr-3 rounded-circle"
                                                            src="https://i.imgur.com/qdiP4DB.jpg"
                                                            width="38"
                                                            />
                                                        <textarea
                                                            required
                                                            name="content"
                                                            type="text"
                                                            rows="2"
                                                            class="form-control me-3"
                                                            style="font-size: 16px"
                                                            id="comment"
                                                            ></textarea>

                                                        <div class="tutor-btn">
                                                            <a class="rbt-btn btn-md hover-icon-reverse w-100" href="#" onclick="document.getElementById('add-comment').submit();">
                                                                <span class="icon-reverse-wrapper">
                                                                    <span class="btn-text">Comment</span>
                                                                    <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                                                    <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                                                </span>
                                                            </a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>

                                            <c:forEach var="comment" items="${comments}">
                                                <div class="comment-media mt-5">
                                                    <img class="mr-3 rounded-circle" alt="Bootstrap Media Preview" src="https://i.imgur.com/stD0Q19.jpg" />
                                                    <div class="comment-media-body">
                                                        <div class="row">
                                                            <div class="d-flex">
                                                                <h5 class="comment-author me-2">${comment.user.fullName}</h5>
                                                                <span class="me-5">${comment.createdAt}</span>
                                                                <a href="#" onclick="setPid(${comment.id});" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                                                    <span class="d-flex justify-content-end align-items-center">
                                                                        <i class="fa fa-reply me-2"></i> 
                                                                        reply
                                                                    </span>
                                                                </a>
                                                            </div>
                                                        </div>	
                                                        <span>
                                                            ${comment.content}
                                                        </span>
                                                        <c:if test="${not empty comment.childComments}">
                                                            <c:forEach var="comment2" items="${comment.childComments}">
                                                                <div class="comment-media mt-4">
                                                                    <img class="mr-3 rounded-circle" alt="Bootstrap Media Preview" src="https://i.imgur.com/stD0Q19.jpg" />
                                                                    <div class="comment-media-body">
                                                                        <div class="row">
                                                                            <div class="d-flex">
                                                                                <h5 class="comment-author me-2">${comment2.user.fullName}</h5>
                                                                                <span class="me-5">${comment2.createdAt}</span>
                                                                                <a href="#" onclick="setPid(${comment.id});" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                                                                    <span class="d-flex justify-content-end align-items-center">
                                                                                        <i class="fa fa-reply me-2"></i> 
                                                                                        reply
                                                                                    </span>
                                                                                </a>
                                                                            </div>
                                                                        </div>	
                                                                        <span>
                                                                            ${comment2.content}
                                                                        </span>
                                                                    </div>
                                                                </div>
                                                            </c:forEach>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </c:forEach>

                                            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalLabel">Reply comment</h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <form id="reply-comment" action="${pageContext.request.contextPath}/learn/comment/add" method="POST">
                                                            <div class="modal-body">
                                                                <input type="hidden" name="courseId" value="${courseId}"/>
                                                                <input type="hidden" name="lessonId" value="${lessonId}"/>
                                                                <input type="hidden" id="pid" name="pid"/>
                                                                <div class="d-block">
                                                                    <div class="mb-3 d-flex align-items-center">
                                                                        <img
                                                                            class="comment-img mr-3 rounded-circle"
                                                                            src="https://i.imgur.com/qdiP4DB.jpg"
                                                                            width="38"
                                                                            />
                                                                        <textarea
                                                                            required
                                                                            name="content"
                                                                            type="text"
                                                                            rows="2"
                                                                            class="form-control me-3"
                                                                            style="font-size: 16px"
                                                                            id="comment"
                                                                            ></textarea>


                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <div class="tutor-btn">
                                                                    <a class="rbt-btn btn-md hover-icon-reverse w-100" href="#" onclick="document.getElementById('reply-comment').submit();">
                                                                        <span class="icon-reverse-wrapper">
                                                                            <span class="btn-text">Reply</span>
                                                                            <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                                                            <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                                                        </span>
                                                                    </a>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                            <script>
                                                function setPid(pid) {
                                                    let pidInput = document.getElementById("pid");
                                                    pidInput.value = pid;
                                                }
                                            </script>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>


        <div class="rbt-progress-parent rbt-backto-top-active">
            <svg class="rbt-back-circle svg-inner" width="100%" height="100%" viewBox="-1 -1 102 102">
            <path d="M50,1 a49,49 0 0,1 0,98 a49,49 0 0,1 0,-98" style="transition: stroke-dashoffset 10ms linear 0s; stroke-dasharray: 307.919, 307.919; stroke-dashoffset: 122.087;"></path>
            </svg>
        </div>

        <!-- JS
    ============================================ -->
        <!-- Modernizer JS -->
        <script src="${pageContext.request.contextPath}/assets/js/vendor/modernizr.min.js"></script>
        <!-- jQuery JS -->
        <script src="${pageContext.request.contextPath}/assets/js/vendor/jquery.js"></script>
        <!-- Bootstrap JS -->
        <script src="${pageContext.request.contextPath}/assets/js/vendor/bootstrap.min.js"></script>
        <!-- sal.js -->
        <script src="${pageContext.request.contextPath}/assets/js/vendor/sal.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/vendor/swiper.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/vendor/magnify.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/vendor/jquery-appear.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/vendor/odometer.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/vendor/backtotop.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/vendor/isotop.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/vendor/imageloaded.js"></script>

        <script src="${pageContext.request.contextPath}/assets/js/vendor/wow.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/vendor/waypoint.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/vendor/easypie.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/vendor/text-type.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/vendor/jquery-one-page-nav.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/vendor/bootstrap-select.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/vendor/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/vendor/magnify-popup.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/vendor/paralax-scroll.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/vendor/paralax.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/vendor/countdown.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/vendor/plyr.js"></script>
        <!-- Main JS -->
        <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>

        <%--<jsp:include page="/layout/scripts.jsp"/>--%>
    </body>
</html>