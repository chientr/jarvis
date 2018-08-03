<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>J.A.R.V.I.S - Login</title>
        <jsp:include page="head.jsp"/>
    </head>
    <body>
        <div style="height: 100px">
        </div>
        <div class="w3-card-4" style="width: 400px; margin: auto">
            <div class="w3-container w3-theme-d5">
                <h1>J.A.R.V.I.S - Login</h1>
            </div>
            <form class="w3-container" action="MainController" method="POST">
                <input type="hidden" name="controller" value="Home"/>
                <label><b>Username</b></label>
                <input class="w3-input w3-border w3-theme-l5" name="textUsername" type="text" value="${requestScope.DTO.username}"></p>
                <font color="red">
                ${requestScope.ERROROBJECT.usernameError}
                </font><br/> 
                <label><b>Password</b></label>
                <input class="w3-input w3-border w3-theme-l5" name="textPassword" type="password" value=""></p>
                <font color="red">
                ${requestScope.ERROROBJECT.passwordError}
                </font><br/>            
                <input class="w3-btn w3-theme-d5" type="submit" name="action" value="Sign In"/>
                <input class="w3-btn w3-theme-l2" style="float: right" type="submit" name="action" value="Register"/>
            </form>
            <br/>
        </div>
    </body>
</html>
