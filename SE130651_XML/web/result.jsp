<%-- 
    Document   : init-survey
    Created on : Jul 8, 2020, 10:56:00 PM
    Author     : Nero
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Survey - Result</title>
        <link rel="icon" href="<c:url value="/styles/imgs/icon.png" />"/>
        <link rel="stylesheet" href="<c:url value="/styles/bootstrap.css" />"/>
        <link rel="stylesheet" href="<c:url value='/styles/common.css' />"/>
        <link rel="stylesheet" href="<c:url value="/styles/result.css" />"/>
        <script type="text/javascript" src="<c:url value="/styles/common.js" />"></script>
        <script type="text/javascript">
            let xmlHttp;
            let productId;
            
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
                xmlHttp = getXmlHttpObject();
                if (xmlHttp == null) {
                    alert("Your browser does not support AJAX");
                    return;
                }
                const url = "add-to-collection?id=" + id;
                productId = id;
                xmlHttp.onreadystatechange = handleStateChange;
                xmlHttp.open("GET", url, true);
                xmlHttp.send(null);
            }
            
            const getXmlHttpObject = () => {
                xmlHttp = null;
                try { // firefox, Opera 8.0++, Safari
                    xmlHttp = new XMLHttpRequest();
                } catch (e) { // IE
                    try {
                        xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
                    } catch (e) {
                        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                    }
                }
                return xmlHttp;
            }
            
            const handleStateChange = () => {
                if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                    const tmp = xmlHttp.responseText;
                    if (tmp != null) {
                        document.getElementById(productId).classList.add("display-none");
                    } else {
                        alert("Unexpected error happens!");
                    }
                }
            }
        </script>
    </head>
    <body class="noselect">
        <div id="bg-container" class="bg">
            <jsp:include page="account-setting.jsp"/>
            <div class="container h-100">
                <div class="row h-100 justify-content-center align-items-center">
                    <div class="display-4">
                        Products based on your answers
                    </div>
                    <div class="row w-100 overflow-scroll">
                        <c:forEach items="${requestScope.INFO}" var="product" varStatus="counter">
                            <div class="card col-3 m-3 p-3 custom-card" id="${product.id}">
                                <div class="display-5 m-auto fs-25px">
                                    ${product.name}
                                </div>
                                <div class="fs-20px">
                                    Category: ${product.category}
                                </div>
                                <div class="fs-20px">
                                    Price: $${product.price}
                                </div>
                                <div class="product-image w-100" onmouseover="showButton(this)" onmouseout="hideButton(this)">
                                    <img src="${product.image}" class="w-100"/>
                                    <div class="display-none">
                                        <button class="btn btn-warning add-button" onclick="addToCollection(${product.id})">Add to my collection</button>
                                    </div>
                                    <div class="display-none">
                                        <button class="btn btn-info detail-button" onclick="getDetail(${product.id})">View detail</button>
                                    </div>
                                </div>
                                <div class="point-badge d-flex">
                                    <div class="w-100 align-self-center text-center">
                                        ${product.point}
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="row w-100">
                        <div class="col-5">
                            <div class="input-group mb-5 text-center w-75 m-auto">
                                <button class="btn btn-secondary custom-button form-control h-60-px" onclick="window.location.href='home'">
                                    &lt; Back to home page
                                </button>
                            </div>
                        </div>
                        <div class="col-5">
                            <div class="input-group mb-5 text-center w-75 m-auto">
                                <button class="btn btn-info custom-button form-control h-60-px" onclick="window.location.href='survey'">
                                    Try again &gt;
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>