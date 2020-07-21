<%-- 
    Document   : login
    Created on : Jul 5, 2020, 8:23:08 PM
    Author     : Nero
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="icon" href="<c:url value="/styles/imgs/icon.png" />"/>
        <link rel="stylesheet" href="<c:url value="/styles/bootstrap.css" />"/>
        <link rel="stylesheet" href="<c:url value="/styles/login.css" />"/>
    </head>
    <body class="noselect">
        <div class="container h-100">
            <div class="row h-100 justify-content-center align-items-center">
                <form action="login" method="POST" class="col-4">
                    <div class="form-group text-center">
                        <svg height="136" viewBox="0 0 264.99 198.28" width="138">
                            <g fill="#faf6f0" transform="translate(-3.59 -87.96)">
                                <g opacity="0.15">
                                    <path d="m264.15 171.81a138.26 138.26 0 0 1 -26 5.24 75.57 75.57 0 0 1 -19.52-.64c7.65-.53 21.16-2.22 30.65-7.66.26-.12 26.16-12.84 17.53-43.62a.78.78 0 0 0 -1.44-.12c0 .09-4.68 9.27-30.67 24.13-10 5.72-21.32 9.7-32.26 13.56-16.6 5.84-32.28 11.36-42.45 22.26-16.8 18-17.47 41.21-17.47 41.45a.76.76 0 0 0 .76.78.76.76 0 0 0 .44-.13c.15-.11 14.86-10.17 29.57-13.45s34.5 1.39 34.71 1.39a.77.77 0 0 0 .4-1.49 30.24 30.24 0 0 1 -17.58-13.93c6.77 4.4 26.32 14.33 49.88 1.35a.78.78 0 0 0 .38-.83.76.76 0 0 0 -.68-.61 145.61 145.61 0 0 1 -19.69-3.7 41.17 41.17 0 0 1 -13.16-6.31 53.82 53.82 0 0 0 33 1.25c19.1-5.58 24.36-17.38 24.57-17.88a.76.76 0 0 0 -.94-1z"></path>
                                    <path d="m69.72 162.7c-10.95-3.86-22.27-7.84-32.26-13.56-25.86-14.78-30.64-24.04-30.68-24.14a.76.76 0 0 0 -.76-.43.75.75 0 0 0 -.67.55c-8.64 30.78 17.26 43.5 17.48 43.6 9.52 5.45 23.06 7.15 30.71 7.68a75.63 75.63 0 0 1 -19.53.64 138.92 138.92 0 0 1 -26-5.24.77.77 0 0 0 -.8.21.79.79 0 0 0 -.14.83c.21.5 5.47 12.3 24.58 17.88a53.83 53.83 0 0 0 33-1.25 41.13 41.13 0 0 1 -13.17 6.31 145.31 145.31 0 0 1 -19.69 3.7.78.78 0 0 0 -.68.61.79.79 0 0 0 .39.83c23.56 13 43.12 3 49.88-1.35a30.18 30.18 0 0 1 -17.58 13.93.77.77 0 0 0 .39 1.49c.2-.05 20-4.67 34.69-1.39s29.43 13.34 29.57 13.45a.78.78 0 0 0 .44.13.74.74 0 0 0 .36-.09.76.76 0 0 0 .41-.69c0-.24-.68-23.42-17.48-41.45-10.18-10.89-25.86-16.41-42.46-22.25z"></path>
                                </g>
                                <path d="m156.73 157.42a3.24 3.24 0 0 0 .87-.2 22.68 22.68 0 0 0 -2.86-45.22c-.73 0-1.46.06-2.19.13a17.52 17.52 0 0 0 1.25-6.49 17.72 17.72 0 1 0 -35.44 0 17.52 17.52 0 0 0 1.25 6.49c-.73-.07-1.45-.13-2.19-.13a22.69 22.69 0 0 0 -2.32 45.26 3.5 3.5 0 0 0 .66.12c7.66.44 14.33 6.68 16.59 15.52a3.15 3.15 0 0 0 .32.7h-2.2a3.08 3.08 0 1 0 0 6.15h2.53v103.41a3.08 3.08 0 1 0 6.16 0v-6.9l15.29 5.84a2.69 2.69 0 1 0 1.92-5l-9.76-3.73 9.76-3.73a2.93 2.93 0 0 0 .53-.27l.07-.06a2.43 2.43 0 0 0 .47-.43l.1-.12a2.45 2.45 0 0 0 .36-.63 1 1 0 0 0 0-.17 2.76 2.76 0 0 0 .08-.27v-.26a2.28 2.28 0 0 0 0-.26 2.45 2.45 0 0 0 0-.27v-.25a2.29 2.29 0 0 0 -.08-.28 1 1 0 0 0 0-.17s0 0 0-.06a2.15 2.15 0 0 0 -.14-.26 2.12 2.12 0 0 0 -.11-.21l-.18-.22-.15-.18-.22-.19-.18-.14-.24-.14-.23-.11h-.08l-9.76-3.73 9.76-3.73a2.69 2.69 0 1 0 -1.92-5l-15.29 5.84v-78.28h2.54a3.08 3.08 0 0 0 0-6.15h-1.88a3.27 3.27 0 0 0 .3-.69c2.27-8.85 8.94-15.09 16.61-15.53zm-2-39.22a16.54 16.54 0 1 1 0 33.07 16.36 16.36 0 0 1 -10.74-4 14.83 14.83 0 0 0 0-25.11 16.4 16.4 0 0 1 10.75-3.96zm-18.66 25.24a8.71 8.71 0 1 1 8.71-8.71 8.72 8.72 0 0 1 -8.7 8.71zm0-49.32a11.57 11.57 0 1 1 -11.56 11.56 11.57 11.57 0 0 1 11.57-11.56zm-18.66 57.15a16.54 16.54 0 1 1 10.74-29.09 14.83 14.83 0 0 0 0 25.11 16.36 16.36 0 0 1 -10.73 3.98zm30.46 115.83-8.71 3.32v-6.65zm-11.63-101.37a26.9 26.9 0 0 0 -8.94-10.57 22.55 22.55 0 0 0 7.28-5.64 14.1 14.1 0 0 0 3 0 22.68 22.68 0 0 0 7.48 5.73 26.73 26.73 0 0 0 -8.82 10.48z"></path>
                            </g>
                        </svg>
                    </div>
                    <div class="form-group mb-5 text-center h1">
                        Login With<br/>Wizarding Passport
                    </div>
                    <div class="form-group">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Username</span>
                            </div>
                            <input name="username" type="text" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group mb-5">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Password</span>
                            </div>
                            <input name="password" type="password" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group text-center w-50 m-auto">
                            <input type="submit" id="Login" value="Login" class="btn btn-info rounded-button form-control h-50-px"/>
                        </div>
                    </div>
                </form>
                <div class="w-100 justify-content-center align-items-center text-center mt--100px">
                    Haven't had any passport yet? <a href="init-register" class="text-decoration-none">Register for a new one</a>
                </div>
            </div>
        </div>
    </body>
</html>
