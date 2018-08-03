<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Jarvis - Mission Management</title>
        <jsp:include page="head.jsp"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="w3-container">
            <form action="MainController" method="POST" accept-charset="" id="missionSearchForm">
                <input type="hidden" name="controller" value="Mission"/>
                <div class="w3-container w3-cell">
                    <br/>
                    <input class="w3-input w3-theme-l5" type="text" name="textSearchMissionName" value="${requestScope.SEARCH.searchMissionName}" placeholder="Mission Name..."/>
                </div>
                <div class="w3-container w3-cell">
                    <br/>
                    <select class="w3-input w3-theme-l5" name="textSearchLocation" form="missionSearchForm">
                        <option value="">Location</option>
                        <c:forEach var="location" items="${requestScope.LOCATIONS}">
                            <option value="${location}" <c:if test="${not empty requestScope.SEARCH.searchLocation and requestScope.SEARCH.searchLocation eq location}">selected</c:if>>${location}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="w3-container w3-cell">
                    <br/>
                    <select class="w3-input w3-theme-l5" name="textSearchState" form="missionSearchForm">
                        <option value="">All State</option>
                        <option value="RUNNING" <c:if test="${not empty requestScope.SEARCH.searchState and requestScope.SEARCH.searchState eq 'RUNNING'}">selected</c:if>>Running</option>
                        <option value="DONE" <c:if test="${not empty requestScope.SEARCH.searchState and requestScope.SEARCH.searchState eq 'DONE'}">selected</c:if>>Done</option>
                        </select>
                    </div>            
                    <div class="w3-container w3-cell">
                        <br/><input class="w3-btn w3-theme-l1" type="submit" name="action" value="Search"/>
                    </div>
                </form><br/>
            <c:if test="${empty requestScope.DTOS}">
                <c:if test="${empty requestScope.SEARCH}">
                    <h1>There is no mission now. Please add some more missions!</h1>
                </c:if>   
                <c:if test="${not empty requestScope.SEARCH}">
                    <h1>No missions matched!</h1>
                </c:if>  
            </c:if>
            <c:if test="${not empty requestScope.DTOS}">
                <div class="flex-container">
                    <c:forEach var="dto" items="${requestScope.DTOS}">
                        <div class="w3-card-4" style="width:45%">
                            <header class="w3-container w3-theme-l5">
                                <h3 class="w3-left">${dto.missionName}</h3><br/>
                                <form action="MainController" method="POST">
                                    <input type="hidden" name="controller" value="Mission"/>
                                    <input type="hidden" name="textMissionCode" value="${dto.missionCode}"/>
                                    <input type="hidden" name="textSearchMissionName" value="${requestScope.SEARCH.searchMissionName}"/>
                                    <input type="hidden" name="textSearchLocation" value="${requestScope.SEARCH.searchLocation}"/>
                                    <input type="hidden" name="textSearchState" value="${requestScope.SEARCH.searchState}"/>
                                    <input class="w3-btn w3-theme-l5" style="float: right" type="submit" name="action" value="Delete" onclick="return confirm('Are you sure?')"/>
                                </form>
                                <form action="MainController" method="POST">
                                    <input type="hidden" name="controller" value="Mission"/>
                                    <input type="hidden" name="textMissionCode" value="${dto.missionCode}"/>
                                    <input type="hidden" name="textSearchMissionName" value="${requestScope.SEARCH.searchMissionName}"/>
                                    <input type="hidden" name="textSearchLocation" value="${requestScope.SEARCH.searchLocation}"/>
                                    <input type="hidden" name="textSearchState" value="${requestScope.SEARCH.searchState}"/>
                                    <input class="w3-btn w3-theme-l1" style="float: right" type="submit" name="action" value="Edit"/>
                                </form>
                            </header>
                            <div class="w3-container">
                                <hr/>
                                <img src="uploads/missions/${dto.missionCode}" alt="${dto.missionName}" class="w3-left w3-round w3-margin-right" style="width:200px" onerror="this.src='uploads/default'">
                                <b>Code: </b>${dto.missionCode}<br/>
                                <b>Location: </b>${dto.location}<br/>
                                <b>State: </b>${dto.state}<br/>
                                <b>Start Date: </b>${dto.startDate}<br/>
                            </div>
                            <br/>
                        </div>
                    </c:forEach>
                </div>
            </c:if>   
        </div>
    </body>
</html>
