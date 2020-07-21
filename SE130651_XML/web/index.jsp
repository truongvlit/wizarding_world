<%-- 
    Document   : home
    Created on : Jun 20, 2020, 5:03:44 PM
    Author     : Nero
--%>

<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Homepage</title>
        <link rel="icon" href="<c:url value="/styles/imgs/icon.png" />"/>
        <link rel="stylesheet" href="<c:url value="/styles/bootstrap.css" />"/>
        <link rel="stylesheet" href="<c:url value="/styles/common.css" />"/>
        <link rel="stylesheet" href="<c:url value="/styles/init-survey.css" />"/>
        <script src="<c:url value="/styles/common.js" />" type="text/javascript"></script>
    </head>
    <script type="text/javascript">
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
    </script>
    <body>
        <div class="bg noselect">
            <jsp:include page="account-setting.jsp"/>
            <c:set var="trending" value="${requestScope.TRENDING}"/>
            <div class="container h-100">
                <div class="row h-100 justify-content-center align-items-center">
                    <div class="form-group mb-1 text-center display-3">
                        Welcome to the Wizarding World
                    </div>
                    <c:if test="${not empty trending}">
                        <x:if select="$trending">
                            <div class="row fs-40px w-100 text-left ml-60px">
                                On Trending
                            </div>
                            <div class="row w-100 overflow-scroll justify-content-center">
                                <x:forEach var="product" select="$trending//product" varStatus="counter">
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
                                                <button class="btn btn-info detail-button" onclick="getDetail(<x:out select="$product/id"/>)">View detail</button>
                                            </div>
                                        </div>
                                    </div>
                                </x:forEach>
                            </div>
                        </x:if>
                    </c:if>
                    <div class="form-group">
                        <div class="input-group text-center w-100 m-auto">
                            <button class="btn btn-info custom-button form-control h-60-px mt-1" onclick="location.href = 'init-survey'">
                                Create Your Collection
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>