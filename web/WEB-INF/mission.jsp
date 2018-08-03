<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Jarvis - Mission Details</title>      
        <jsp:include page="head.jsp"/>

        <script>
            jQuery(document).ready(function () {
                jQuery('#vmap').vectorMap({
                    map: 'world_en',
                    color: '#ffffff',
                    hoverOpacity: 0.7,
                    selectedColor: '#4caf50',
                    enableZoom: true,
                    showTooltip: true,
                    scaleColors: ['#009687', '#009688'],
                    values: sample_data,
                    normalizeFunction: 'polynomial',
                    onRegionClick: function (event, code, region) {
                        textLocation = document.getElementById("textLocation");
                        textLocation.value = region;
                    }
                });
            });
        </script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <div class="w3-sidebar w3-bar-block w3-theme-l5 w3-card" style="width:200px; position: fixed; z-index: 1">
            <h5 class="w3-bar-item">Mission Manage</h5>
            <button class="w3-bar-item w3-button tablink" onclick="openCity(event, 'Information')">Information</button>
            <button class="w3-bar-item w3-button tablink" onclick="openCity(event, 'ChangeImage')">Change Image</button>
            <button class="w3-bar-item w3-button tablink" onclick="openCity(event, 'Crews')">Crews</button>
            <c:if test="${requestScope.DTO.state == 'RUNNING'}">
                <button class="w3-bar-item w3-button tablink" onclick="openCity(event, 'AddCrew')">Add Crew</button>
            </c:if>
        </div>

        <div style="margin-left:200px">
            <div id="Information" class="w3-container city">
                <h2>${requestScope.DTO.missionName}</h2>
                <hr/>
                <form action="MainController" method="POST" class="w3-container">
                    <input type="hidden" name="controller" value="Mission"/>
                    <input type="hidden" name="textMissionCode" value="${requestScope.DTO.missionCode}" readonly/>
                    <label><b>Mission Name</b></label>
                    <input class="w3-input w3-theme-l5" type="text" name="textMissionName" value="${requestScope.DTO.missionName}"/>
                    <font color="red">
                    ${requestScope.ERROROBJECT.missionNameError}
                    </font><br/>
                    <label><b>Location</b></label>
                    <div id="vmap" style="width: 600px; height: 400px;"></div>
                    <input class="w3-input" type="hidden" id="textLocation" name="textLocation" value="${requestScope.DTO.location}"/>
                    <font color="red">
                    ${requestScope.ERROROBJECT.locationError}
                    </font><br/>
                    <label><b>Description</b></label>
                    <textarea class="w3-input w3-theme-l5" cols="80" rows="5" name="textDescription">${requestScope.DTO.description}</textarea><br/>
                    <font color="red">
                    ${requestScope.ERROROBJECT.descriptionError}
                    </font><br/>
                    <p><b>Start Date:</b> ${requestScope.DTO.startDate}</p>
                    <c:if test="${requestScope.DTO.state == 'DONE'}">
                        <p><b>Finish Date:</b> ${requestScope.DTO.finishDate}</p>
                    </c:if>
                    <c:if test="${requestScope.DTO.state == 'RUNNING'}">
                        <input type="hidden" name="textSearchMissionName" value="${requestScope.SEARCH.searchMissionName}"/>
                        <input type="hidden" name="textSearchLocation" value="${requestScope.SEARCH.searchLocation}"/>
                        <input type="hidden" name="textSearchState" value="${requestScope.SEARCH.searchState}"/>
                        <input class="w3-btn w3-theme-l1" type="submit" name="action" value="Update"/>
                        <input class="w3-btn w3-theme-l5" type="submit" name="action" value="Finish" onclick="return confirm('Are you sure?')"/>
                    </c:if>
                </form>
            </div>

            <div id="ChangeImage" class="w3-container city" style="display:none">
                <h2>Change Image</h2>
                <hr/>          
                <img class="w3-circle" src="uploads/missions/${requestScope.DTO.missionCode}" width="200" height="200" onerror="this.src='uploads/default'"/>
                <c:if test="${requestScope.DTO.state == 'RUNNING'}">
                    <form action="MainController" method="POST" enctype="multipart/form-data">
                        <input type="hidden" name="controller" value="Mission"/>
                        <input type="hidden" name="textMissionCode" value="${requestScope.DTO.missionCode}"/>
                        <input type="file" name="fileImage" accept="image/*"/>
                        <input class="w3-btn w3-theme-l1" type="submit" name="action" value="Change Image"/>
                    </form>
                </c:if>
            </div>

            <div id="Crews" class="w3-container city" style="display:none">
                <h2>Avengers joined</h2>
                <hr/>
                <div class="flex-container">
                    <c:forEach var="currentUser" items="${requestScope.CURRENTUSERS}">
                        <img class="w3-circle" src="uploads/users/${currentUser.username}" title="${currentUser.alias}" width="75" height="75" onerror="this.src='uploads/default'"/>
                        <form action="MainController" method="POST">
                            <input type="hidden" name="controller" value="User"/>
                            <input type="hidden" name="textUsername" value="${currentUser.username}"/>
                            <input class="w3-btn w3-theme-l2" type="submit" name="action" value="Edit"/>
                        </form>
                        <c:if test="${requestScope.DTO.state == 'RUNNING'}">
                            <form action="MainController" method="POST">
                                <input type="hidden" name="controller" value="Mission"/>
                                <input type="hidden" name="textMissionCode" value="${requestScope.DTO.missionCode}"/>
                                <input type="hidden" name="textAvengerUsername" value="${currentUser.username}"/>
                                <input class="w3-btn w3-theme-l5" type="submit" name="action" value="Remove"/>
                            </form>
                        </c:if>
                        <br/>
                    </c:forEach>
                </div>
            </div>

            <c:if test="${requestScope.DTO.state == 'RUNNING'}">
                <div id="AddCrew" class="w3-container city" style="display:none">
                    <c:if test="${not empty requestScope.FREEUSERS}">
                        <h2>Available Crew</h2>
                        <hr/>
                        <form action="MainController" method="POST" id="addAvengerForm">
                            <input type="hidden" name="controller" value="Mission"/>
                            <input type="hidden" name="textMissionCode" value="${requestScope.DTO.missionCode}"/>
                            <div class="w3-cell">
                                <select class="w3-select w3-theme-l5" name="textAvengerUsername" id="avengerSelect" form="addAvengerForm" onchange="avengerChoose()">
                                    <c:forEach var="user" items="${requestScope.FREEUSERS}">
                                        <option value="${user.username}">${user.alias}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="w3-cell">
                                <select class="w3-select w3-theme-l5" name="textMarkCode" form="addAvengerForm" id="markSelect" style="display: none">
                                    <c:forEach var="mark" items="${requestScope.MARKS}">
                                        <option value="${mark.markCode}">${mark.markName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <input class="w3-btn w3-theme-l1" type="submit" name="action" value="Add"/>
                        </form>
                    </c:if>
                    <c:if test="${empty requestScope.FREEUSERS}">
                        <h1>There is no free avengers now.</h1>
                    </c:if>
                </div>
            </c:if>

            <script>
                function openCity(evt, cityName) {
                    var i, x, tablinks;
                    x = document.getElementsByClassName("city");
                    for (i = 0; i < x.length; i++) {
                        x[i].style.display = "none";
                    }
                    tablinks = document.getElementsByClassName("tablink");
                    for (i = 0; i < x.length; i++) {
                        tablinks[i].className = tablinks[i].className.replace(" w3-theme-l2", "");
                    }
                    document.getElementById(cityName).style.display = "block";
                    evt.currentTarget.className += " w3-theme-l2";
                }

                function avengerChoose() {
                    var avengerSelect = document.getElementById('avengerSelect');
                    var markSelect = document.getElementById('markSelect');
                    var avengerUsername = avengerSelect.value;
                    if (avengerUsername === 'stark') {
                        markSelect.style.display = 'block';
                    } else {
                        markSelect.style.display = 'none';
                    }
                }
            </script>
    </body>
</html>
