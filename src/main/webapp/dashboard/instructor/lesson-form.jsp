<%@page contentType="text/html" pageEncoding="UTF-8" buffer="8192kb" autoFlush="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create lession</title>
        <jsp:include page="/layout/links.jsp" />
        <style>
            :root {
                --ck-sample-base-spacing: 2em;
                --ck-sample-color-white: #fff;
                --ck-sample-color-green: #279863;
                --ck-sample-color-blue: #1a9aef;
                --ck-sample-container-width: 1285px;
                --ck-sample-sidebar-width: 350px;
                --ck-sample-editor-min-height: 400px;
                --ck-sample-editor-z-index: 10;
            }
            /* --------- EDITOR STYLES  ---------------------------------------------------------------------------------------- */

            .editor__editable,
            /* Classic build. */
            main .ck-editor[role='application'] .ck.ck-content,
            /* Decoupled document build. */
            .ck.editor__editable[role='textbox'],
            .ck.ck-editor__editable[role='textbox'],
            /* Inline & Balloon build. */
            .ck.editor[role='textbox'] {
                width: 100%;
                background: #fff;
                font-size: 1em;
                line-height: 1.6em;
                min-height: var(--ck-sample-editor-min-height);
                padding: 1.5em 2em;
            }

            .ck.ck-editor__editable {
                background: #fff;
                border: 1px solid hsl(0, 0%, 70%);
                width: 100%;
            }

            /* Because of sidebar `position: relative`, Edge is overriding the outline of a focused editor. */
            .ck.ck-editor__editable {
                position: relative;
                z-index: var(--ck-sample-editor-z-index);
            }

            .editor-container {
                display: flex;
                flex-direction: row;
                flex-wrap: nowrap;
                position: relative;
                width: 100%;
                justify-content: center;
            }

            .editor-container--with-sidebar > .ck.ck-editor {
                width: calc( 100% - var(--ck-sample-sidebar-width) );
            }
        </style>
    </head>
    <body>
        <jsp:include page="/layout/header.jsp" />
        <jsp:include page="/layout/dashboard-header.jsp" />
        <div class="rbt-dashboard-area rbt-section-overlayping-top rbt-section-gapBottom" >
            <div class="container">
                <div class="centered">
                    <div class="row">
                        <h2>Editing lesson: ${lesson.name}</h2>
                    </div>
                    <form id="form-edit" action="${pageContext.request.contextPath}/instructor/lesson/edit" method="post">
                        <input type="hidden" name="id" value="${lesson.id}"/>
                        <div class="row row-editor">
                            <div class="col-12">
                                <div class="editor-container w-100">
                                    <textarea name="content" class="editor">
                                        ${lesson.content}
                                    </textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-9 tutor-btn d-inline mt-4">
                                <a class="rbt-btn btn-md hover-icon-reverse w-100" href="#" onclick="document.getElementById('form-edit').submit();">
                                    <span class="icon-reverse-wrapper">
                                        <span class="btn-text">Save</span>
                                        <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                        <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                    </span>
                                </a>
                            </div>  
                            <div class="col-md-3 tutor-btn d-inline mt-4">
                                <a class="rbt-btn btn-md btn-white hover-icon-reverse w-100" href="#" 
                                   onclick="document.location.href = '${pageContext.request.contextPath}/instructor/section?courseId=${courseId}';">
                                    <span class="icon-reverse-wrapper">
                                        <span class="btn-text">Cancel</span>
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
        <jsp:include page="/layout/footer.jsp" />
        <jsp:include page="/layout/scripts.jsp" />
        <script src="${pageContext.request.contextPath}/assets/js/vendor/ckeditor.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/vendor/ckeditor-watchdog.js"></script>
    </body>
</html>