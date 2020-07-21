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
        <title>Detail</title>
        <link rel="icon" href="<c:url value="/styles/imgs/icon.png" />"/>
        <link rel="stylesheet" href="<c:url value="/styles/bootstrap.css" />"/>
        <link rel="stylesheet" href="<c:url value='/styles/common.css' />"/>
        <link rel="stylesheet" href="<c:url value="/styles/detail.css" />"/>
        <script type="text/javascript" src="<c:url value="/styles/common.js" />"></script>
    </head>
    <body class="noselect">
        <c:set var="product" value="${requestScope.INFO}"/>
        <div class="bg">
            <div class="container h-100">
                <div class="row h-100 justify-content-center align-items-center">
                    <div class="col-4">
                        <img src="${product.image}" class="w-100"/>
                    </div>
                    <div class="col-6 overflow-scroll">
                        <c:if test="${empty product}">
                            ${requestScope.ERROR}
                        </c:if>
                        <c:if test="${not empty product}">
                            <div class="form-group mb-2 text-center display-4">
                                ${product.name}
                            </div>
                            <div class="form-group mb-1 fs-25px">
                                Category: ${product.category}
                            </div>
                            <div class="form-group mb-1 fs-25px">
                                Price: $${product.price}
                            </div>
                            <div class="form-group mb-1 fs-25px">
                                Description: ${product.description}
                            </div>
                            <div class="form-group mb-1 fs-25px">
                                Note: ${product.note}
                            </div>
                        </c:if>
                    </div>
                    <div class="col-10">
                        <div class="input-group mb-5 text-center w-25 m-auto">
                            <button class="btn btn-secondary custom-button form-control h-60-px" onclick="history.go(-1)">
                                BACK
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>