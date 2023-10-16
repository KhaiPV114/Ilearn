function renderPaginationBar(
        paginationBarId,
{ key, start, end, size, total }
) {
    let paginationBar = document.getElementById(paginationBarId);

    let navPagination = document.createElement("nav");
    let pageList = document.createElement("ul");
    pageList.className = "rbt-pagination justify-content-center";

    if (start > 1) {
        let pageItem = document.createElement("li");
        let link = document.createElement("a");
        link.innerText = "First";
        link.setAttribute("href", `?size=${size}&page=1`);
        pageItem.appendChild(link);
        pageList.appendChild(pageItem);
    }

    for (let i = start; i <= end; ++i) {
        let pageItem = document.createElement("li");
        let link = document.createElement("a");
        link.innerText = i;
        if (i === key) {
            pageItem.className = "active";
            link.setAttribute("href", "#");
        } else {
            link.setAttribute("href", `?size=${size}&page=${i}`);
        }
        pageItem.appendChild(link);
        pageList.appendChild(pageItem);
    }

    if (end < total) {
        let pageItem = document.createElement("li");
        let link = document.createElement("a");
        link.innerText = "Last";
        link.setAttribute("href", `?size=${size}&page=${total}`);
        pageItem.appendChild(link);
        pageList.appendChild(pageItem);
    }

    navPagination.appendChild(pageList);
    paginationBar.appendChild(navPagination);
}