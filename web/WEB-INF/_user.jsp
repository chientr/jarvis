<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add new avenger</title>
        <jsp:include page="head.jsp"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>  
        <div class="w3-container">
            <h1>Enter avenger information</h1>
            <hr/>
            <form action="MainController" method="POST">
                <input type="hidden" name="controller" value="User"/>
                <label><b>Username</b></label>
                <input class="w3-input w3-theme-l5" type="text" name="textUsername" value="${requestScope.DTO.username}"/><br/>
                <font color="red">
                ${requestScope.ERROROBJECT.usernameError}
                </font><br/>
                <label><b>Password</b></label>
                <input class="w3-input w3-theme-l5" type="password" name="textPassword" value=""/><br/>
                <font color="red">
                ${requestScope.ERROROBJECT.passwordError}
                </font><br/>
                <label><b>Password Confirm</b></label>
                <input class="w3-input w3-theme-l5" type="password" name="textPasswordConfirm" value=""/><br/>
                <font color="red">
                ${requestScope.ERROROBJECT.passwordConfirmError}
                </font><br/>
                <label><b>Alias</b></label>
                <input class="w3-input w3-theme-l5" type="text" name="textAlias" value="${requestScope.DTO.alias}"/><br/>
                <font color="red">
                ${requestScope.ERROROBJECT.aliasError}
                </font><br/>
                <label><b>Real Name</b></label>
                <input class="w3-input w3-theme-l5" type="text" name="textRealname" value="${requestScope.DTO.realname}"/><br/>
                <font color="red">
                ${requestScope.ERROROBJECT.realnameError}
                </font><br/>
                <input class="w3-btn w3-theme-l1" type="submit" name="action" value="Insert"/>
            </form>
        </div>
    </body>
</html>
