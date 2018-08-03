<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <jsp:include page="head.jsp"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="w3-container">
            <c:if test="${empty requestScope.DTOS}">
                <h1>You have no mission!</h1>
            </c:if>
            <c:if test="${not empty requestScope.DTOS}">
                <h1>You missions</h1>
                <hr/>
                <div class="flex-container">
                    <c:forEach var="dto" items="${requestScope.DTOS}">
                        <div class="w3-card-4" style="width:600px">
                            <header class="w3-container w3-theme-l5">
                                <h3>${dto.missionName}</h3>
                            </header>
                            <div class="w3-container">
                                <br/>
                                <table>
                                    <tbody>
                                        <tr>
                                            <td>
                                                <img src="uploads/missions/${dto.missionCode}" alt="${dto.missionName}" class="w3-left w3-round w3-margin-right" style="width:200px" onerror="this.src='uploads/default'">
                                            </td>
                                            <td>
                                                <b>Code: </b>${dto.missionCode}<br/>
                                                <b>Location: </b>${dto.location}<br/>
                                                <b>State: </b>${dto.state}<br/>
                                                <p>${dto.description}</p><br/>
                                                <b>Start Date: </b>${dto.startDate}<br/>
                                                <c:if test="${dto.state == 'DONE'}">        
                                                    <b>Finish Date: </b>${dto.finishDate}</p>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>                                
                            </div>
                            <br/>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </body>
</html>
