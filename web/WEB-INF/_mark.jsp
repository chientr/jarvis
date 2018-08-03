<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add new mark</title>
        <jsp:include page="head.jsp"/>
    </head>
    <body>        
        <jsp:include page="header.jsp"/>
        <div class="w3-container">
            <h1>Enter mark information</h1>
            <hr/>
            <form action="MainController" method="POST">
                <input type="hidden" name="controller" value="Mark"/>
                <label><b>Code</b></label>
                <input class="w3-input w3-theme-l5" type="text" name="textMarkCode" value="${requestScope.DTO.markCode}"/><br/>
                <font color="red">
                ${requestScope.ERROROBJECT.markCodeError}
                </font><br/>
                <label><b>Name</b></label>
                <input class="w3-input w3-theme-l5" type="text" name="textMarkName" value="${requestScope.DTO.markName}"/><br/>
                <font color="red">
                ${requestScope.ERROROBJECT.markNameError}
                </font><br/>
                <label><b>Armor Type</b></label>
                <input class="w3-input w3-theme-l5" type="text" name="textArmorType" value="${requestScope.DTO.armorType}"/><br/>
                <font color="red">
                ${requestScope.ERROROBJECT.armorTypeError}
                </font><br/>     
                <input class="w3-btn w3-theme-l1" type="submit" name="action" value="Insert"/>
            </form>
        </div>
    </body>
</html>
