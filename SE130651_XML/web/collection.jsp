<%-- 
    Document   : init-survey
    Created on : Jul 8, 2020, 10:56:00 PM
    Author     : Nero
--%>

<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Collection</title>
        <link rel="icon" href="<c:url value="/styles/imgs/icon.png" />"/>
        <link rel="stylesheet" href="<c:url value="/styles/bootstrap.css" />"/>
        <link rel="stylesheet" href="<c:url value='/styles/common.css' />"/>
        <link rel="stylesheet" href="<c:url value="/styles/result.css" />"/>
        <script type="text/javascript" src="<c:url value="/styles/common.js" />"></script>
        <script type="text/javascript">
            let xhr;
            
            const showButton = (t) => {
                const buttons = t.childNodes;
                for (let i = 0; i < buttons.length; i++) {
                    if (buttons[i].tagName == "DIV") {
                        buttons[i].classList.remove("display-none");
                    }
                }
            }

            const hideButton = (t) => {
                const buttons = t.childNodes;
                for (let i = 0; i < buttons.length; i++) {
                    if (buttons[i].tagName == "DIV") {
                        buttons[i].classList.add("display-none");
                    }
                }
            }

            const getDetail = (id) => {
                window.location.href = "detail?id=" + id;
            }
            
            const addToCollection = (id) => {
                xhr = getXmlHttpObject();
                if (xhr == null) {
                    alert("Your browser does not support AJAX");
                    return;
                }
                const url = "add-to-collection?id=" + id;
                document.getElementById(id).classList.add("display-none");
                document.getElementById(id).classList.add("deleted");
                xhr.open("GET", url, true);
                xhr.send(null);
            }
            
            const update = (page, order) => {
                xhr = getXmlHttpObject();
                if (xhr == null) {
                    alert("Your browser does not support AJAX");
                    return;
                }
                const url = "collection?page=" + page + "&order=" + order;
                xhr.onreadystatechange = handleStateChange;
                xhr.open("GET", url, true);
                xhr.send(null);
            }

            const getXmlHttpObject = () => {
                xhr = null;
                try { // firefox, Opera 8.0++, Safari
                    xhr = new XMLHttpRequest();
                } catch (e) { // IE
                    try {
                        xhr = new ActiveXObject("Msxml2.XMLHTTP");
                    } catch (e) {
                        xhr = new ActiveXObject("Microsoft.XMLHTTP");
                    }
                }
                return xhr;
            }
            
            const handleStateChange = () => {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    const tmp = xhr.responseText;
                    if (tmp != null) {
                        // remove current elements
                        document.getElementsByClassName('row w-100 justify-content-center overflow-scroll')[0].remove();
                        // clear search
                        document.getElementsByName("search")[0].value = "";
                        // get replacement elements
                        const parser = new DOMParser();
                        const newDoc = parser.parseFromString(tmp, "text/html");
                        const replacement = newDoc.getElementsByClassName('row w-100 justify-content-center overflow-scroll')[0];
                        // add replacement elements to DOM
                        const container = document.getElementsByClassName('row h-100 justify-content-center align-items-center')[0];
                        container.insertBefore(replacement, container.firstElementChild.nextElementSibling.nextElementSibling);
                    } else {
                        alert("Unexpected error happens!");
                    }
                }
            }
            
            const removeChild = (e) => {
                while(e.firstChild) {
                    e.removeChild(e.firstChild);
                }
            }

            const updateData = () => {
                const page = getCurrentPage();
                const order = getCurrentOrder();
                update(page, order);
            }

            const search = (e) => {
                const search = e.value;
                document.getElementsByName("product").forEach((t) => {
                    if (t.childNodes[1].innerText.toLowerCase().indexOf(search.toLowerCase()) > -1) {
                        if (t.classList.contains("deleted")) {}
                        else t.classList.remove("display-none");
                    } else {
                        t.classList.add("display-none");
                    }
                });
            }

            const changePage = (page, order) => {
                if (page == null) return;

                let prevPage = getCurrentPage();
                window.history.pushState({}, "", "?page=" + page + "&order=" + order);
                let nextPage = getCurrentPage();
                if (prevPage == nextPage) prevPage = 1;
                
                document.getElementById("page-" + prevPage).classList.remove("active");
                document.getElementById("page-" + nextPage).classList.add("active");
            }

            const getCurrentPage = () => {
                let urlString = window.location.href;
                let url = new URL(urlString);
                let page = url.searchParams.get("page");
                return (page == null || isNaN(page) || page == "" || page < 1 || page > ${requestScope.MAX_PAGE}) ? 1 : page;
            }

            const getCurrentOrder = () => {
                let urlString = window.location.href;
                let url = new URL(urlString);
                let order = url.searchParams.get("order");
                return (order == null || order == "" || (order != "category" && order != "price ASC" && order != "price DESC")) ? "category" : order;
            }

            const getPreviousPage = () => {
                const currentPage = getCurrentPage();
                if (currentPage == 1) return null;
                return parseInt(currentPage) - 1;
            }

            const getNextPage = () => {
                const currentPage = getCurrentPage();
                if (currentPage == ${requestScope.MAX_PAGE}) return null;
                return parseInt(currentPage) + 1;
            }
        </script>
    </head>
    <body class="noselect" onload="changePage(getCurrentPage(), getCurrentOrder());">
        <c:set var="xml" value="${requestScope.DOC}"/>
        <x:set var="products" select="$xml//product"/>
        <div id="bg-container" class="bg bg-7">
            <jsp:include page="account-setting.jsp"/>
            <x:if select="$products">
                <div class="container h-100">
                    <div class="row h-100 justify-content-center align-items-center">
                        <div class="display-4">
                            Collection of the Wizarding World
                        </div>
                        <div class="row w-100 justify-content-center align-items-center">
                            <div class="col-8">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">Search</span>
                                    </div>
                                    <input name="search" type="text" class="form-control" onkeyup="search(this)"/>
                                </div>
                            </div>
                            <div class="col-2" style="height: 52px;">
                                <select name="order" class="custom-select h-100" onchange="changePage(getCurrentPage(), this.value); updateData()">
                                    <option value="" disabled hidden selected>Order By</option>
                                    <option value="category">Category</option>
                                    <option value="price ASC">Price (low to high)</option>
                                    <option value="price DESC">Price (high to low)</option>
                                </select>
                            </div>
                        </div>
                        <div class="row w-100 justify-content-center overflow-scroll">
                            <x:forEach var="product" select="$products" varStatus="counter">
                                <div class="card col-3 m-3 p-3 custom-card" id="<x:out select="$product/id"/>" name="product">
                                    <div class="display-5 m-auto fs-25px" name="product-name">
                                        <x:out select="$product/name"/>
                                    </div>
                                    <div class="fs-20px">
                                        Category: <x:out select="$product/category"/>
                                    </div>
                                    <div class="fs-20px">
                                        Price: $<x:out select="$product/price"/>
                                    </div>
                                    <div class="product-image w-100" onmouseover="showButton(this)" onmouseout="hideButton(this)">
                                        <img src="<x:out select="$product/img"/>" alt="<x:out select="$product/name"/>" class="w-100"/>
                                        <div class="display-none">
                                            <button class="btn btn-warning add-button" onclick="addToCollection(<x:out select="$product/id"/>);">Add to my collection</button>
                                        </div>
                                        <div class="display-none">
                                            <button class="btn btn-info detail-button" onclick="getDetail(<x:out select="$product/id"/>)">View detail</button>
                                        </div>
                                    </div>
                                </div>
                            </x:forEach>
                        </div>
                        <nav class="row w-100 justify-content-center">
                            <ul class="pagination justify-content-center">
                                <li class="page-item">
                                    <button class="page-link" aria-label="Previous" onclick="changePage(getPreviousPage(), getCurrentOrder())">
                                        <span aria-hidden="true">&laquo;</span>
                                        <span class="sr-only">Previous</span>
                                    </button>
                                </li>
                                <c:forEach begin="1" end="${requestScope.MAX_PAGE}" varStatus="counter">
                                    <li class="page-item" id="page-${counter.index}">
                                        <button class="page-link" onclick="changePage(${counter.index}, getCurrentOrder()); updateData()">
                                            <span>${counter.index}</span>
                                        </button>
                                    </li>
                                </c:forEach>
                                <li class="page-item">
                                    <button class="page-link" aria-label="Next" onclick="changePage(getNextPage(), getCurrentOrder())">
                                        <span aria-hidden="true">&raquo;</span>
                                        <span class="sr-only">Next</span>
                                    </button>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </x:if>
            <x:if select="not($products)">
                <div class="container h-100">
                    <div class="row h-100 display-3 align-items-center justify-content-center">
                        Oops! No product found
                    </div>
                </div>
            </x:if>
        </div>
    </body>
</html>