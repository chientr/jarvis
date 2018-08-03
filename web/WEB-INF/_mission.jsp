<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add new mission</title>
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
        <div class="w3-container">
            <h1>Enter mission information</h1>
            <hr/>
            <form action="MainController" method="POST">
                <input type="hidden" name="controller" value="Mission"/>
                <table border="1">
                    <label><b>Location</b></label>
                    <div id="vmap" style="width: 600px; height: 400px;"></div>
                    <font color="red">
                    ${requestScope.ERROROBJECT.locationError}
                    </font><br/>
                    <label><b>Code</b></label>
                    <input class="w3-input w3-theme-l5" type="text" name="textMissionCode" value="${requestScope.DTO.missionCode}"/><br/>
                    <font color="red">
                    ${requestScope.ERROROBJECT.missionCodeError}
                    </font><br/>
                    <label><b>Name</b></label>
                    <input class="w3-input w3-theme-l5" type="text" name="textMissionName" value="${requestScope.DTO.missionName}"/><br/>
                    <font color="red">
                    ${requestScope.ERROROBJECT.missionNameError}
                    </font><br/>
                    <input class="w3-input" type="hidden" id="textLocation" name="textLocation" value="${requestScope.DTO.location}"/><br/>
                    <input class="w3-btn w3-theme-l1" type="submit" name="action" value="Insert"/>
            </form>
        </div>
        <script>
            document.onload = function () {
                textLocation = document.getElementById("textLocation");
                if (textLocation.value === null || textLocation.value === '')
                    textLocation.value = 'Vietnam';
            };
        </script>
    </body>
</html>
