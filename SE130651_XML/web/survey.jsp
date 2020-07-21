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
        <title>Survey</title>
        <link rel="icon" href="<c:url value='/styles/imgs/icon.png' />"/>
        <link rel="stylesheet" href="<c:url value='/styles/bootstrap.css' />"/>
        <link rel="stylesheet" href="<c:url value='/styles/common.css' />"/>
        <link rel="stylesheet" href="<c:url value='/styles/survey.css' />"/>
        <script type="text/javascript" src="<c:url value="/styles/common.js" />"></script>
    </head>
    <body class="noselect" onload="changeQuestion(getCurrentQuestion())">
        <div id="bg-container" class="bg bg-1">
            <jsp:include page="account-setting.jsp"/>
            <div class="container h-100">
                <div class="row h-100 justify-content-center align-items-center">
                    <form class="col-10" method="POST" action="result">
                        <c:if test="${requestScope.INFO != null && not empty requestScope.INFO}">
                            <c:set var="size" value="${requestScope.INFO.size()}"/>
                            <c:forEach items="${requestScope.INFO}" var="entry" varStatus="counter">
                                <div class="w-100 display-none" id="${entry.key.id}">
                                    <input type="hidden" name="questions" value="${entry.key.id}"/>
                                    <input type="hidden" name="types" value="${entry.key.type}"/>
                                    <input type="hidden" name="target" value="${entry.key.targetedAttribute}"/>
                                    <div class="form-group mb-5 display-3">
                                        ${entry.key.content}
                                    </div>
                                <c:choose>
                                    <c:when test="${entry.key.type == 1}">
                                        <div class="custom-form-check">
                                            <c:forEach items="${entry.value}" var="answer" varStatus="subCounter">
                                                <c:if test="${subCounter.count % 2 != 0}">
                                                    <div class="row">
                                                </c:if>
                                                    <div class="col-5">
                                                        <input class="custom-checkbox" name="${entry.key.id}" type="radio" value="${answer.content}" id="cb-${entry.key.id}-${subCounter.count}"
                                                               <c:if test="${subCounter.count == 1}">
                                                                   <c:set var="point" value="${answer.point}"/>
                                                                   checked="checked"
                                                               </c:if>
                                                               onchange="getChosenAnswerPoint(this, ${entry.key.id}, ${answer.point})"
                                                        >
                                                        <input type="hidden" name="sub-point-${entry.key.id}" value="${answer.point}"/>
                                                        <label class="form-check-label custom-label" for="cb-${entry.key.id}-${subCounter.count}">
                                                            ${answer.content}
                                                        </label>
                                                    </div>
                                                <c:if test="${subCounter.count % 2 == 0 || subCounter.last}">
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                        <input id="point-${entry.key.id}" type="hidden" name="point" value="${point}"/>
                                    </c:when>
                                    <c:when test="${entry.key.type == 2}">
                                        <div class="custom-form-check overflow-scroll">
                                            <c:forEach items="${entry.value}" var="answer" varStatus="subCounter">
                                                <c:if test="${subCounter.count == 1}">
                                                    <c:set var="point" value="${answer.point}"/>
                                                </c:if>
                                                <c:if test="${subCounter.count % 2 != 0}">
                                                    <div class="row ${subCounter.count}">
                                                </c:if>
                                                    <div class="col-5">
                                                        <input class="custom-checkbox" name="${entry.key.id}" type="checkbox" value="${answer.content}" id="cb-${entry.key.id}-${subCounter.count}">
                                                        <label class="form-check-label custom-label" for="cb-${entry.key.id}-${subCounter.count}">
                                                            ${answer.content}
                                                        </label>
                                                    </div>
                                                <c:if test="${subCounter.count % 2 == 0 || subCounter.last}">
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                        <input id="point-${entry.key.id}" type="hidden" name="point" value="${point}"/>
                                    </c:when>
                                    <c:when test="${entry.key.type == 3}">
                                        <div class="custom-form-check">
                                            <c:forEach items="${entry.value}" var="answer" varStatus="subCounter">
                                                <c:if test="${subCounter.count % 2 != 0}">
                                                    <div class="row">
                                                </c:if>
                                                    <div class="col-5">
                                                        <input class="custom-checkbox" name="${entry.key.id}" type="radio" value="${answer.content}" id="cb-${entry.key.id}-${subCounter.count}"
                                                               <c:if test="${subCounter.count == 1}">
                                                                   <c:set var="point" value="${answer.point}"/>
                                                                   checked="checked"
                                                               </c:if>
                                                               onchange="getChosenAnswerPoint(this, ${entry.key.id}, ${answer.point})"
                                                        >
                                                        <input type="hidden" name="sub-point-${entry.key.id}" value="${answer.point}"/>
                                                        <label class="form-check-label custom-label" for="cb-${entry.key.id}-${subCounter.count}">
                                                            ${answer.content}
                                                        </label>
                                                    </div>
                                                <c:if test="${subCounter.count % 2 == 0 || subCounter.last}">
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                        <input id="point-${entry.key.id}" type="hidden" name="point" value="${point}"/>
                                    </c:when>
                                    <c:when test="${entry.key.type == 4}">
                                        <div class="custom-form-check">
                                            <div class="row w-100">
                                                <div class="col-2 display-4">
                                                    From
                                                </div>
                                                <div class="col-8 m-auto">
                                                    <div class="range-value" id="min-value"></div>
                                                    <input id="min-price" name="${entry.key.id}" type="range" min="0" max="400" value="200" step="1"/>
                                                </div>
                                            </div>
                                            <div class="row w-100">
                                                <div class="col-2 display-4">
                                                    To
                                                </div>
                                                <div class="col-8 m-auto">
                                                    <div class="range-value" id="max-value"></div>
                                                    <input id="max-price" name="${entry.key.id}" type="range" min="0" max="400" value="200" step="1"/>
                                                </div>
                                            </div>
                                        </div>
                                        <c:set var="point" value="10"/>
                                        <input id="point-${entry.key.id}" type="hidden" name="point" value="${point}"/>
                                    </c:when>
                                </c:choose>
                                </div>
                            </c:forEach>
                        </c:if>
                        <div class="row mt-5 display-none" id="button-submit">
                            <input type="submit" value="SUBMIT ANSWER" class="btn btn-info custom-button form-control h-60-px w-50 m-auto"/>
                        </div>
                    </form>
                    <nav class="fixed-bottom w-100">
                        <ul class="pagination justify-content-center">
                            <li class="page-item">
                                <button class="page-link" aria-label="Previous" onclick="changeQuestion(getPreviousQuestion())">
                                    <span aria-hidden="true">&laquo;</span>
                                    <span class="sr-only">Previous</span>
                                </button>
                            </li>
                            <c:forEach items="${requestScope.INFO}" var="entry" varStatus="counter">
                                <li class="page-item" id="q-${counter.count}">
                                    <button class="page-link" onclick="changeQuestion(${counter.count});">
                                        <span>${counter.count}</span>
                                    </button>
                                </li>
                            </c:forEach>
                            <li class="page-item">
                                <button class="page-link" aria-label="Next" onclick="changeQuestion(getNextQuestion())">
                                    <span aria-hidden="true">&raquo;</span>
                                    <span class="sr-only">Next</span>
                                </button>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            const minElement = document.getElementById('min-price');
            const minValue = document.getElementById('min-value');
            const maxElement = document.getElementById('max-price');
            const maxValue = document.getElementById('max-value');

            const setMinValue = () => {
                const newValue = Number((minElement.value - minElement.min) * 100 / (minElement.max - minElement.min));
                const newPosition = 25 - (newValue * 0.5);
                minValue.innerHTML = "<span>$" + minElement.value + "</span>";
                minValue.style.left = "calc(" + newValue + "% + " + "(" + newPosition + "px))";
            }

            const setMaxValue = () => {
                const newValue = Number((maxElement.value - maxElement.min) * 100 / (maxElement.max - maxElement.min));
                const newPosition = 25 - (newValue * 0.5);
                maxValue.innerHTML = "<span>$" + maxElement.value + "</span>";
                maxValue.style.left = "calc(" + newValue + "% + " + "(" + newPosition + "px))";
            }

            const controlSubmitButton = () => {
                const position = getCurrentQuestion();
                if (position == ${size}) document.getElementById("button-submit").classList.remove("display-none");
                else document.getElementById("button-submit").classList.add("display-none");
            }

            const changeQuestion = (question) => {
                if (question == null) return;

                let prevQuestion = getCurrentQuestion();
                window.history.pushState({}, "", "?question=" + question);
                let nextQuestion = getCurrentQuestion();
                if (prevQuestion == nextQuestion) prevQuestion = 1;
                
                controlSubmitButton();
                document.getElementById(prevQuestion).classList.add("display-none");
                document.getElementById(nextQuestion).classList.remove("display-none");
                
                document.getElementById("bg-container").classList.remove("bg-" + prevQuestion);
                document.getElementById("bg-container").classList.add("bg-" + nextQuestion);
                document.getElementById("q-" + prevQuestion).classList.remove("active");
                document.getElementById("q-" + nextQuestion).classList.add("active");
            }

            const getCurrentQuestion = () => {
                let urlString = window.location.href;
                let url = new URL(urlString);
                let question = url.searchParams.get("question");
                return (question == null || isNaN(question) || question == "" || question < 1 || question > ${size}) ? 1 : question;
            }

            const getPreviousQuestion = () => {
                const currentQuestion = getCurrentQuestion();
                if (currentQuestion == 1) return null;
                return parseInt(currentQuestion) - 1;
            }

            const getNextQuestion = () => {
                const currentQuestion = getCurrentQuestion();
                if (currentQuestion == ${size}) return null;
                return parseInt(currentQuestion) + 1;
            }
            
            const getChosenAnswerPoint = (t, id, point) => {
                if (t.checked == true) {
                    document.getElementById('point-' + id).value = point;
                }
            }
            
            document.addEventListener("DOMContentLoaded", controlSubmitButton);
            document.addEventListener("DOMContentLoaded", setMinValue);
            document.addEventListener("DOMContentLoaded", setMaxValue);

            minElement.addEventListener('input', setMinValue);
            maxElement.addEventListener('input', setMaxValue);
        </script>
    </body>
</html>