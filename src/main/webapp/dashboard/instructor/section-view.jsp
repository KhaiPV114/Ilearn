<%@page contentType="text/html" pageEncoding="UTF-8" buffer="8192kb" autoFlush="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="/layout/links.jsp" />
        <style>
            .truncate-4 {
                display: -webkit-box;
                -webkit-line-clamp: 4;
                -webkit-box-orient: vertical;
                overflow: hidden;
            }
            .truncate-5 {
                display: -webkit-box;
                -webkit-line-clamp: 5;
                -webkit-box-orient: vertical;
                overflow: hidden;
            }
            .truncate-6 {
                display: -webkit-box;
                -webkit-line-clamp: 6;
                -webkit-box-orient: vertical;
                overflow: hidden;
            }
            .card-header-custom {
                padding: 15px 20px !important;
            }
            .card-icon {
                font-size: 18px;
                display: flex !important;
                align-items: center;
                cursor: pointer;
            }
            .card-icon-sort {
                cursor: ns-resize;
            }
        </style>
        <script src="https://cdn.jsdelivr.net/npm/sortablejs@latest/Sortable.min.js"></script>
        <script>
            async function handleLessonItemMovingOnEnd(event) {
                let url = "${pageContext.request.contextPath}/instructor/lesson/move";
                let prefixItemId = "lesson-";
                return await handleItemMovingOnEnd(url, prefixItemId, event);
            }
        </script>
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
                                                            Content
                                                        </a>
                                                    </li>
                                                </ol>
                                            </nav>
                                        </div>
                                        <!-- End Title -->

                                        <form id="form-add" action="${pageContext.request.contextPath}/instructor/section/add" method="post">
                                            <input type="hidden" name="courseId" value="${courseId}">
                                            <div class="row">
                                                <div class="col-md-10">
                                                    <div class="mb-3">
                                                        <label for="name" class="form-label">Section name</label>
                                                        <input value="${name}" name="name" type="text" class="form-control" id="name" required>
                                                        <c:if test="${not empty nameError}">
                                                            <div class="form-text text-danger">${nameError}</div>
                                                        </c:if>
                                                    </div>
                                                </div>
                                                <div class="col-md-2">
                                                    <div class="mb-3">
                                                        <label for="name" class="form-label"></label>
                                                        <div class="tutor-btn" style="padding: 0.375rem;">
                                                            <a class="rbt-btn btn-md hover-icon-reverse w-100" href="#" onclick="document.getElementById('form-add').submit();">
                                                                <span class="icon-reverse-wrapper">
                                                                    <span class="btn-text">Add</span>
                                                                    <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                                                    <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                                                </span>
                                                            </a>
                                                        </div>  
                                                    </div>
                                                </div>
                                            </div>

                                        </form>

                                        <div class="section-title text-center mt-4 mb-2">
                                            <span class="subtitle bg-secondary-opacity">Active sections</span>
                                        </div>
                                        <div class="rbt-accordion-style rbt-accordion-01  accordion">
                                            <ul id="sectionList" class="accordion ps-0 list-group">
                                                <c:forEach var="section" items="${sections}">
                                                    <li id="section-${section.id}" key="${section.id}" class="accordion-item card section-list-handle list-group-item my-3">
                                                        <h2 class="accordion-header card-header card-header-custom d-flex align-baseline" id="headingTwo">
                                                            <div class="card-icon card-icon-sort me-3">
                                                                <i class="fa-solid fa-arrows-up-down"></i>
                                                            </div>
                                                            <button class="accordion-button collapsed me-4" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-${section.id}" aria-expanded="false" aria-controls="collapse-${section.id}">
                                                                ${section.name}
                                                            </button>
                                                            <div class="card-icon ms-3">
                                                                <i class="fa-solid fa-eye-slash"></i>
                                                            </div>
                                                            <div class="card-icon ms-3">
                                                                <i class="fa-solid fa-trash"></i>
                                                            </div>
                                                        </h2>
                                                        <div id="collapse-${section.id}" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#section-${section.id}">
                                                            <div class="accordion-body card-body bg-color-white">

                                                                <!-- Lession -->

                                                                <form id="form-add-lesson-${section.id}" action="${pageContext.request.contextPath}/instructor/lesson/add" method="post">
                                                                    <input type="hidden" name="courseId" value="${courseId}">
                                                                    <input type="hidden" name="sectionId" value="${section.id}">
                                                                    <div class="row">
                                                                        <div class="col-md-10">
                                                                            <div class="mb-3">
                                                                                <label for="name" class="form-label">Lession name</label>
                                                                                <input value="${name}" name="name" type="text" class="form-control" id="name" required>
                                                                                <c:if test="${not empty nameError}">
                                                                                    <div class="form-text text-danger">${nameError}</div>
                                                                                </c:if>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-2">
                                                                            <div class="mb-3">
                                                                                <label for="name" class="form-label"></label>
                                                                                <div class="tutor-btn" style="padding: 0.375rem;">
                                                                                    <a class="rbt-btn btn-md hover-icon-reverse w-100" href="#" onclick="document.getElementById('form-add-lesson-${section.id}').submit();">
                                                                                        <span class="icon-reverse-wrapper">
                                                                                            <span class="btn-text">Add</span>
                                                                                            <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                                                                            <span class="btn-icon"><i class="feather-arrow-right"></i></span>
                                                                                        </span>
                                                                                    </a>
                                                                                </div>  
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                </form>

                                                                <c:set var="sectionId" value="${section.id}"/>

                                                                <div class="rbt-accordion-style rbt-accordion-01  accordion">
                                                                    <ul id="lessons${sectionId}" class="accordion ps-0 list-group">
                                                                        <c:set var="lessons" value="${lessonsList[sectionId]}"/>
                                                                        <c:forEach var="lesson" items="${lessons}">
                                                                            <li id="lesson-${lesson.id}" key="${lesson.id}" class="accordion-item card lesson-list-handle-${sectionId} list-group-item my-3">
                                                                                <h2 class="accordion-header card-header card-header-custom d-flex align-baseline" id="headingTwo">
                                                                                    <div class="card-icon card-icon-sort me-3">
                                                                                        <i class="fa-solid fa-arrows-up-down"></i>
                                                                                    </div>
                                                                                    <button class="accordion-button collapsed me-4 text-dark" type="button"
                                                                                            onclick="document.location.href='${pageContext.request.contextPath}/instructor/lesson/edit?id=${lesson.id}'">
                                                                                        ${lesson.name}
                                                                                    </button>
                                                                                    <div class="card-icon ms-3">
                                                                                        <i class="fa-solid fa-eye-slash"></i>
                                                                                    </div>
                                                                                    <div class="card-icon ms-3">
                                                                                        <i class="fa-solid fa-trash"></i>
                                                                                    </div>
                                                                                </h2>
                                                                            </li>
                                                                        </c:forEach>
                                                                    </ul>
                                                                </div>

                                                                <script>
                                                                    Sortable.create(lessons${sectionId}, {
                                                                        group: 'shared1',
                                                                        animation: 100,
                                                                        handle: '.lesson-list-handle-${sectionId}',
                                                                        onEnd: handleLessonItemMovingOnEnd
                                                                    });
                                                                </script>

                                                                <!-- End lesson -->

                                                            </div>
                                                        </div>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>

                                    </div>

                                    <div class="d-flex mt-3" id="pageBar"></div>

                                    <!--Delete via post method-->                                                    
                                    <form id="deletion-form" action="${pageContext.request.contextPath}/instructor/course/delete" method="post">
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
    <script>
        Sortable.create(sectionList, {
            group: 'shared',
            animation: 100,
            handle: '.section-list-handle',
            onEnd: handleSectionItemMovingOnEnd
        });

        async function handleSectionItemMovingOnEnd(event) {
            let url = "${pageContext.request.contextPath}/instructor/section/move";
            let prefixItemId = "section-";
            return await handleItemMovingOnEnd(url, prefixItemId, event);
        }

        async function handleItemMovingOnEnd(url, prefixItemId, event) {
            let item = event.item;
            let previousItem = item.previousElementSibling;
            let nextItem = item.nextElementSibling;
            console.log(item);
            console.log(previousItem);
            console.log(nextItem);
            let itemKey = item.getAttribute("key"),
                    previousItemKey = null,
                    nextItemKey = null;
            if (previousItem)
                previousItemKey = previousItem.getAttribute("key");
            if (nextItem)
                nextItemKey = nextItem.getAttribute("key");
            let result = await postMovingRequest(url, {
            itemKey,
                    previousItemKey,
                    nextItemKey
            });
                    if (result?.success) {
            setTimeout(() => {
                const div = document.createElement("h6");
                div.textContent = "Updated successfully!";
                div.className = "text-success fw-bold m-0 mt-2 ms-1";
                document
                        .getElementById(prefixItemId + itemKey)
                        .insertAdjacentElement("beforeBegin", div);
                setTimeout(() => div.remove(), 1500);
            }, 0);
            }
            if (result?.error) {
            setTimeout(() => {
            const div = document.createElement("h6");
                    div.textContent =
                    "Updated failed! Please reload this page and try again!";
                    div.className = "text-danger fw-bold m-0 mt-2 ms-1";
                    document
                    .getElementById(prefixItemId + itemKey)
                    .insertAdjacentElement("beforeBegin", div);
            }, 0);
            }
            console.log(result);
        }

        async function postMovingRequest(
                url,
        { itemKey, previousItemKey, nextItemKey }
        ) {
            let data = {
                currentId: itemKey,
                ...(previousItemKey && {previousId: previousItemKey}),
                ...(nextItemKey && {nextId: nextItemKey})
            };

            const postData = new URLSearchParams();

            postData.append("currentId", itemKey);
            if (previousItemKey)
                postData.append("previousId", previousItemKey);
            if (nextItemKey)
                postData.append("nextId", nextItemKey);

            const response = await fetch(url, {
                method: "POST",
                body: postData
            });

            console.log(response);
            return response.json();
        }


//        async function handleSectionItemMovingOnEnd(event) {
//            let item = event.item;
//            let previousItem = item.previousElementSibling;
//            let nextItem = item.nextElementSibling;
//            console.log(item);
//            console.log(previousItem);
//            console.log(nextItem);
//            let itemKey = item.getAttribute("key"),
//                    previousItemKey = null,
//                    nextItemKey = null;
//            if (previousItem)
//                previousItemKey = previousItem.getAttribute("key");
//            if (nextItem)
//                nextItemKey = nextItem.getAttribute("key");
//            let result = await postHandlingSectionRequest({
//            itemKey,
//                    previousItemKey,
//                    nextItemKey
//            });
//                    if (result?.success) {
//            setTimeout(() => {
//                const div = document.createElement("h6");
//                div.textContent = "Updated successfully!";
//                div.className = "text-success fw-bold m-0 mt-2 ms-1";
//                document
//                        .getElementById("section-" + itemKey)
//                        .insertAdjacentElement("beforeBegin", div);
//                setTimeout(() => div.remove(), 1500);
//            }, 0);
//            }
//            if (result?.error) {
//            setTimeout(() => {
//            const div = document.createElement("h6");
//                    div.textContent =
//                    "Updated failed! Please reload this page and try again!";
//                    div.className = "text-danger fw-bold m-0 mt-2 ms-1";
//                    document
//                    .getElementById("section-" + itemKey)
//                    .insertAdjacentElement("beforeBegin", div);
//            }, 0);
//            }
//            console.log(result);
//        }
//
//        async function postHandlingSectionRequest( {
//        itemKey,
//                previousItemKey,
//                nextItemKey
//        }) {
//            let url = "${pageContext.request.contextPath}/instructor/section/move";
//            let data = {
//                currentId: itemKey,
//                ...(previousItemKey && {previousId: previousItemKey}),
//                ...(nextItemKey && {nextId: nextItemKey})
//            };
//
//            const postData = new URLSearchParams();
//
//            postData.append("currentId", itemKey);
//            if (previousItemKey)
//                postData.append("previousId", previousItemKey);
//            if (nextItemKey)
//                postData.append("nextId", nextItemKey);
//
//            const response = await fetch(url, {
//                method: "POST",
//                body: postData
//            });
//
//            console.log(response);
//            return response.json();
//        }

    </script>
</body>
</html>