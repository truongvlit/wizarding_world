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
        <title>My Collection</title>
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
            
            const removeFromCollection = (id) => {
                xmlHttp = getXmlHttpObject();
                if (xmlHttp == null) {
                    alert("Your browser does not support ajax");
                    return;
                }
                const url = "remove-from-collection?id=" + id;
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
                        // remove current elements
                        document.getElementsByClassName('row w-100 justify-content-center overflow-scroll')[0].remove();
                        // get replacement elements
                        const parser = new DOMParser();
                        const newDoc = parser.parseFromString(tmp, "text/html");
                        const replacement = newDoc.getElementsByClassName('row w-100 justify-content-center overflow-scroll')[0];
                        // add replacement elements to DOM
                        const container = document.getElementsByClassName('row h-100 justify-content-center align-items-center')[0];
                        container.insertBefore(replacement, container.firstElementChild.nextElementSibling);
                    } else {
                        alert("Unexpected error happens!");
                    }
                }
            }
        </script>
    </head>
    <body class="noselect">
        <c:set var="xml" value="${requestScope.DOC}"/>
        <x:set var="products" select="$xml//productId"/>
        <div id="bg-container" class="bg">
            <jsp:include page="account-setting.jsp"/>
            <x:if select="$products">
                <div class="container h-100">
                    <div class="row h-100 justify-content-center align-items-center">
                        <div class="display-4">
                            MY COLLECTION
                        </div>
                        <div class="row w-100 overflow-scroll justify-content-center">
                            <x:forEach var="product" select="$products" varStatus="counter">
                                <div class="card col-3 m-3 p-3 custom-card" id="<x:out select="$product/id"/>">
                                    <div class="display-5 m-auto fs-25px">
                                        <x:out select="$product/name"/>
                                    </div>
                                    <div class="fs-20px">
                                        Category: <x:out select="$product/category"/>
                                    </div>
                                    <div class="fs-20px">
                                        Price: $<x:out select="$product/price"/>
                                    </div>
                                    <div class="product-image w-100" onmouseover="showButton(this)" onmouseout="hideButton(this)">
                                        <img src="<x:out select="$product/img"/>" class="w-100"/>
                                        <div class="display-none">
                                            <button class="btn btn-danger remove-button" onclick="removeFromCollection(<x:out select="$product/id"/>)">Remove from my collection</button>
                                        </div>
                                        <div class="display-none">
                                            <button class="btn btn-info detail-button" onclick="getDetail(<x:out select="$product/id"/>)">View detail</button>
                                        </div>
                                    </div>
                                </div>
                            </x:forEach>
                        </div>
                        <div class="col-10">
                            <div class="input-group text-center w-25 m-auto ">
                                <button class="btn btn-secondary custom-button form-control h-60-px" onclick="history.go(-1)">
                                    BACK
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </x:if>
            <x:if select="not($products)">
                <div class="container h-100">
                    <div class="row h-100 align-items-center text-center">
                        <div class="display-3">
                            Oops! You haven't had any product in your collection
                        </div>
                    </div>
                </div>
            </x:if>
        </div>
    </body>
</html>