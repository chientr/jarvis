<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>J.A.R.V.I.S - Register</title>      
        <jsp:include page="head.jsp"/>
    </head>
    <body>
        <div style="height: 100px">
        </div>
        <div class="w3-card-4" style="width: 400px; margin: auto">
            <div class="w3-container w3-theme-d5">
                <h1>J.A.R.V.I.S - Register</h1>
            </div>
            <form class="w3-container" action="MainController" method="POST">
                <input type="hidden" name="controller" value="Home"/>
                <label><b>Username</b></label>
                <input class="w3-input w3-border w3-light-grey" type="text" name="textUsername" value="${requestScope.DTO.username}"/>
                <font color="red">
                ${requestScope.ERROROBJECT.usernameError}
                </font><br/>
                <label><b>Password</b></label>
                <input class="w3-input w3-border w3-light-grey" type="password" name="textPassword" value=""/>
                <font color="red">
                ${requestScope.ERROROBJECT.passwordError}
                </font><br/>
                <label><b>Password Confirm</b></label>
                <input class="w3-input w3-border w3-light-grey" type="password" name="textPasswordConfirm" value=""/>
                <font color="red">
                ${requestScope.ERROROBJECT.passwordConfirmError}
                </font><br/>
                <label><b>Alias</b></label>
                <input class="w3-input w3-border w3-light-grey" type="text" name="textAlias" value="${requestScope.DTO.alias}"/>
                <font color="red">
                ${requestScope.ERROROBJECT.aliasError}
                </font><br/>
                <label><b>Real Name</b></label>
                <input class="w3-input w3-border w3-light-grey" type="text" name="textRealname" value="${requestScope.DTO.realname}"/>
                <font color="red">
                ${requestScope.ERROROBJECT.realnameError}
                </font><br/>
                <input class="w3-btn w3-theme-d5" type="submit" name="action" value="Sign Up"/></td>                            
                <input class="w3-btn w3-theme-l2" style="float: right" type="submit" name="action" value="Log in"/>
            </form>
            <br/>
        </div>
    </body>
</html>
