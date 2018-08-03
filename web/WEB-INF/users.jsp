<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Jarvis - User Management</title>       
        <jsp:include page="head.jsp"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="w3-container">
            <form action="MainController" method="POST">
                <input type="hidden" name="controller" value="User"/>
                <div class="w3-container w3-cell">
                    <br/>
                    <input class="w3-input w3-theme-l5" type="text" name="textSearchAlias" value="${requestScope.SEARCH.searchAlias}" placeholder="Enter Alias..."/>
                </div>
                <div class="w3-container w3-cell">
                    <br/>
                    <input class="w3-btn w3-theme-l1" type="submit" name="action" value="Search"/>
                </div>
            </form>
            <c:if test="${empty requestScope.DTOS}">
                <c:if test="${empty requestScope.SEARCH}">
                    <h1>There is no user now. Please add some more users!</h1>
                </c:if>   
                <c:if test="${not empty requestScope.SEARCH}">
                    <h1>No users matched!</h1>
                </c:if>  
            </c:if>
            <c:if test="${not empty requestScope.DTOS}">

                <div class="flex-container">
                    <c:forEach var="dto" items="${requestScope.DTOS}">
                        <div class="w3-card-4" style="width:180px">
                            <img src="uploads/users/${dto.username}" alt="${dto.alias}" style="height: 200px; width: 100%" onerror="this.src='uploads/default'">
                            <div class="w3-container w3-center">
                                <p class="w3-theme-l1">${dto.alias}</p>
                                <p class="w3-theme-l5">${dto.realname}</p>                                
                                <c:if test="${dto.username != 'stark'}">
                                    <form action="MainController" method="POST">
                                        <input type="hidden" name="controller" value="User"/>
                                        <input type="hidden" name="textUsername" value="${dto.username}"/>
                                        <input type="hidden" name="textSearchAlias" value="${requestScope.SEARCH.searchAlias}"/>
                                        <input class="w3-btn w3-theme-l5" style="float: right" type="submit" name="action" value="Delete" onclick="return confirm('Are you sure?')"/>
                                    </form>
                                </c:if>
                                <form action="MainController" method="POST">
                                    <input type="hidden" name="controller" value="User"/>
                                    <input type="hidden" name="textUsername" value="${dto.username}"/>
                                    <input type="hidden" name="textSearchAlias" value="${requestScope.SEARCH.searchAlias}"/>
                                    <input class="w3-btn w3-theme-l1" style="float: right" type="submit" name="action" value="Edit"/>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </body>
</html>
