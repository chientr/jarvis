<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stark - Mark Details</title>
        <jsp:include page="head.jsp"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="w3-sidebar w3-bar-block w3-light-grey w3-card" style="width:200px">
            <h5 class="w3-bar-item">Mark information</h5>
            <button class="w3-bar-item w3-button tablink" id="buttonInformation" onclick="openCity(event, 'Information')">Information</button>
            <button class="w3-bar-item w3-button tablink" id="buttonChangeImage" onclick="openCity(event, 'ChangeImage')">Change Image</button>
        </div>

        <div style="margin-left:200px">
            <div id="Information" class="w3-container city">
                <form action="MainController" method="POST">
                    <input type="hidden" name="controller" value="Mark"/>
                    <h2>${requestScope.DTO.markCode} information</h2>
                    <hr/>
                    <input type="hidden" name="textMarkCode" value="${requestScope.DTO.markCode}"/>
                    <label><b>Name</b></label>
                    <input class="w3-input w3-theme-l5" type="text" name="textMarkName" value="${requestScope.DTO.markName}"/>
                    <font color="red">
                    ${requestScope.ERROROBJECT.markNameError}
                    </font><br/>
                    <label><b>Armor Type</b></label>
                    <input class="w3-input w3-theme-l5" type="text" name="textArmorType" value="${requestScope.DTO.armorType}"/>
                    <font color="red">
                    ${requestScope.ERROROBJECT.armorTypeError}
                    </font><br/>
                    <label><b>Description</b></label>
                    <textarea class="w3-input w3-theme-l5" cols="80" rows="5" name="textDescription">${requestScope.DTO.description}</textarea><br/>
                    <font color="red">
                    ${requestScope.ERROROBJECT.descriptionError}
                    </font><br/>
                    <input type="hidden" name="textSearchMarkName" value="${requestScope.SEARCH.searchMarkName}"/>
                    <input class="w3-btn w3-theme-l1" type="submit" name="action" value="Update"/>
                </form>
            </div>

            <div id="ChangeImage" class="w3-container city" style="display:none">
                <h2>Change Image</h2>
                <hr/>
                <img class="w3-circle" src="uploads/marks/${requestScope.DTO.markCode}" width="200" height="200" onerror="this.src='uploads/default'"/>
                <form action="MainController" method="POST" enctype="multipart/form-data">
                    <input type="hidden" name="controller" value="Mark"/>
                    <input type="hidden" name="textMarkCode" value="${requestScope.DTO.markCode}"/>
                    <input type="hidden" name="textSearchMarkName" value="${requestScope.SEARCH.searchMarkName}"/>
                    <input class="w3-btn" type="file" name="fileImage" accept="image/*"/>
                    <input class="w3-btn w3-theme-l1" type="submit" name="action" value="Change Image"/>
                </form>
            </div>
        </div>

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

            window.onload = function () {
                var button = document.getElementById('buttonChangeImage');
            <c:if test="${not empty requestScope.ERROROBJECT}">
                <c:if test="${not empty requestScope.ERROROBJECT.markNameError or not empty requestScope.ERROROBJECT.armorTypeError or not empty requestScope.ERROROBJECT.descriptionError}">
                button = document.getElementById('buttonInformation');
                </c:if>
            </c:if>
                button.click();
            };
        </script>
    </div>
</html>
