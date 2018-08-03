<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Jarvis - User Details</title>     
        <jsp:include page="head.jsp"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <div class="w3-sidebar w3-bar-block w3-theme-l5 w3-card" style="width:200px">
            <h5 class="w3-bar-item">User Manage</h5>
            <button class="w3-bar-item w3-button tablink" id="buttonInformation" onclick="openCity(event, 'Information')">Information</button>
            <button class="w3-bar-item w3-button tablink" id="buttonChangeImage" onclick="openCity(event, 'ChangeImage')">Change Image</button>
            <button class="w3-bar-item w3-button tablink" id="buttonResetPassword" onclick="openCity(event, 'SetPassword')">Set Password</button>
        </div>

        <div style="margin-left:200px">
            <div id="Information" class="w3-container city">
                <h1>${requestScope.DTO.username} information</h1>
                <hr/>
                <form action="MainController" method="POST">
                    <input type="hidden" name="controller" value="User"/>
                    <input type="hidden" name="textUsername" value="${requestScope.DTO.username}"/>
                    <label><b>Alias</b></label>
                    <input class="w3-input w3-theme-l5" type="text" name="textAlias" value="${requestScope.DTO.alias}"/>
                    <font color="red">
                    ${requestScope.ERROROBJECT.aliasError}
                    </font><br/>
                    <label><b>Real Name</b></label>
                    <input class="w3-input w3-theme-l5" type="text" name="textRealname" value="${requestScope.DTO.realname}"/>
                    <font color="red">
                    ${requestScope.ERROROBJECT.realnameError}
                    </font><br/>
                    <label><b>Description</b></label>
                    <textarea class="w3-input w3-theme-l5" cols="80" rows="5" name="textDescription">${requestScope.DTO.description}</textarea><br/>
                    <font color="red">
                    ${requestScope.ERROROBJECT.descriptionError}
                    </font><br/>
                    <input type="hidden" name="textSearchAlias" value="${requestScope.SEARCH.searchAlias}"/>
                    <input class="w3-btn w3-theme-l1" type="submit" name="action" value="Update"/>
                </form>
            </div>

            <div id="ChangeImage" class="w3-container city" style="display:none">                
                <h1>Change Image</h1>
                <hr/>
                <img class="w3-circle" src="uploads/users/${requestScope.DTO.username}" width="200" height="200" onerror="this.src='uploads/default'"/>
                <form action="MainController" method="POST" enctype="multipart/form-data">
                    <input type="hidden" name="controller" value="User"/>
                    <input type="hidden" name="textUsername" value="${requestScope.DTO.username}"/>
                    <input type="file" name="fileImage" accept="image/*"/>
                    <input class="w3-btn w3-theme-l1" type="submit" name="action" value="Change Image"/>
                </form>
            </div>

            <div id="SetPassword" class="w3-container city" style="display:none">                
                <h1>Set Password</h1>
                <hr/>
                <form action="MainController" method="POST">
                    <input type="hidden" name="controller" value="User"/>
                    <input type="hidden" name="textUsername" value="${requestScope.DTO.username}"/>
                    <label><b>New Password</b></label>
                    <input class="w3-input w3-theme-l5" type="password" name="textPassword" value=""/>
                    <font color="red">
                    ${requestScope.ERROROBJECT.passwordError}
                    </font><br/>
                    <label><b>Confirm New Password</b></label>
                    <input class="w3-input w3-theme-l5" type="password" name="textPasswordConfirm" value=""/>
                    <font color="red">
                    ${requestScope.ERROROBJECT.passwordConfirmError}
                    </font><br/>
                    <input class="w3-btn w3-theme-l1" type="submit" name="action" value="Set Password"/>
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
                <c:if test="${not empty requestScope.ERROROBJECT.aliasError or not empty requestScope.ERROROBJECT.realnameError or not empty requestScope.ERROROBJECT.descriptionError}">
                button = document.getElementById('buttonInformation');
                </c:if>
                <c:if test="${not empty requestScope.ERROROBJECT.passwordError or not empty requestScope.ERROROBJECT.passwordConfirmError}">
                button = document.getElementById('buttonResetPassword');
                </c:if>
            </c:if>
                button.click();
            };
        </script>
    </body>
</html>
