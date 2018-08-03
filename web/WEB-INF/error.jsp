<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hmmm... Something went wrong!</title>     
        <jsp:include page="head.jsp"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="w3-container">
            <h1>${requestScope.ERROR}</h1>
        </div>
    </body>
</html>