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
        <title>Survey - Getting Started</title>
        <link rel="icon" href="<c:url value="/styles/imgs/icon.png" />"/>
        <link rel="stylesheet" href="<c:url value="/styles/bootstrap.css" />"/>
        <link rel="stylesheet" href="<c:url value='/styles/common.css' />"/>
        <link rel="stylesheet" href="<c:url value="/styles/init-survey.css" />"/>
        <script type="text/javascript" src="<c:url value="/styles/common.js" />"></script>
    </head>
    <body>
        <div class="bg noselect">
            <jsp:include page="account-setting.jsp"/>
            <div class="container h-100">
                <div class="row h-100 justify-content-center align-items-center">
                    <div class="col-10">
                        <div class="row w-100 justify-content-center">
                            <div class="form-group mb-5 text-center display-3">
                                Which option do you want to create your own collection?
                            </div>
                        </div>
                        <div class="row w-100 justify-content-center">
                            <div class="col-5 mb-5 text-center m-auto fs-35px">
                                See full collection and choose anything you want
                            </div>
                            <div class="col-5 mb-5 text-center m-auto fs-35px">
                                Answer our questions and let us suggest suitable items for you
                            </div>
                        </div>
                        <div class="row w-100 justify-content-center">
                            <div class="input-group mb-5 text-center w-35 m-auto">
                                <button class="btn btn-info custom-button form-control h-60-px" onclick="location.href = 'collection'">
                                    SEE FULL COLLECTION
                                </button>
                            </div>
                            <div class="input-group mb-5 text-center w-35 m-auto">
                                <button class="btn btn-warning custom-button form-control h-60-px" onclick="location.href = 'survey'">
                                    GO TO SURVEY
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>