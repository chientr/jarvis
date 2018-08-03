<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stark - Marks Hall</title>
        <jsp:include page="head.jsp"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="w3-container">
            <c:if test="${empty requestScope.DTOS}">
                <c:if test="${empty requestScope.SEARCH}">
                    <h1>There is no mark now. Please add some more marks!</h1>
                </c:if>   
                <c:if test="${not empty requestScope.SEARCH}">
                    <h1>No marks matched!</h1>
                </c:if>
            </c:if>
            <c:if test="${not empty requestScope.DTOS}">
                <c:if test="${empty requestScope.SEARCH}">
                </c:if>   
                <c:if test="${not empty requestScope.SEARCH}">
                    <h1>Searching Results</h1>
                </c:if>
                <div class="flex-container">
                    <c:forEach var="dto" items="${requestScope.DTOS}">
                        <div class="w3-card-4" style="width:180px">
                            <img src="uploads/marks/${dto.markCode}" alt="${dto.markName}" style="height: 200px; width: 100%" onerror="this.src='uploads/default'">
                            <div class="w3-container w3-center">
                                <p>${dto.markName} - ${dto.armorType}</p>
                                <form action="MainController" method="POST">
                                    <input type="hidden" name="controller" value="Mark"/>
                                    <input type="hidden" name="textMarkCode" value="${dto.markCode}"/>
                                    <input type="hidden" name="textSearchMarkName" value="${requestScope.SEARCH.searchMarkName}"/>
                                    <input class="w3-btn w3-theme-l2" style="float: right" type="submit" name="action" value="Delete" onclick="return confirm('Are your sure?')"/>
                                </form>
                                <form action="MainController" method="POST">
                                    <input type="hidden" name="controller" value="Mark"/>
                                    <input type="hidden" name="textMarkCode" value="${dto.markCode}"/>
                                    <input type="hidden" name="textSearchMarkName" value="${requestScope.SEARCH.searchMarkName}"/>
                                    <input class="w3-btn w3-theme-l5" style="float: right" type="submit" name="action" value="Edit"/>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </body>
</html>
