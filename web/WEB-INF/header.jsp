<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="w3-bar w3-theme-d5" style="position: fixed; top: 0; overflow: hidden">
    <form action="MainController" method="POST">
        <input type="hidden" name="controller" value="Home"/>
        <input type="hidden" name="action" value="Index"/>
        <input type="submit" class="w3-bar-item w3-button w3-theme-d1" value="J.A.R.V.I.S"/>
    </form>
    <form action="MainController" method="POST">
        <input type="hidden" name="controller" value="Home"/>
        <input type="submit" name="action" value="Sign Out" class="w3-bar-item w3-button w3-right w3-theme-l4"/>
    </form>
    <form action="MainController" method="POST">
        <input type="hidden" name="controller" value="Home"/>
        <input type="submit" name="action" value="Profile" class="w3-bar-item w3-button w3-right w3-theme-l2"/>
    </form>
    <c:if test="${sessionScope.USERNAME == 'jarvis'}">
        <form action="MainController" method="POST">
            <input type="hidden" name="controller" value="Mission"/>
            <input type="hidden" name="action" value="List"/>
            <input type="submit" value="Missions" class="w3-bar-item w3-button"/>
        </form>
        <form action="MainController" method="POST">
            <input type="hidden" name="controller" value="User"/>
            <input type="hidden" name="action" value="List"/>
            <input type="submit" value="Users" class="w3-bar-item w3-button"/>
        </form>
        <form action="MainController" method="POST">
            <input type="hidden" name="controller" value="Mission"/>
            <input class="w3-bar-item w3-button w3-theme-l2" type="submit" name="action" value="Add Mission"/>
        </form>        
        <form action="MainController" method="POST">
            <input type="hidden" name="controller" value="User"/>
            <input class="w3-bar-item w3-button w3-theme-l3" type="submit" name="action" value="Add User"/>
        </form>
    </c:if>
    <c:if test="${sessionScope.USERNAME == 'stark'}">
        <form action="MainController" method="POST">
            <input type="hidden" name="controller" value="Mark"/>
            <input type="hidden" name="action" value="List"/>
            <input type="submit" value="Marks" class="w3-bar-item w3-button"/>
        </form>
        <form action="MainController" method="POST">
            <input type="hidden" name="controller" value="Mark"/>
            <input class="w3-bar-item w3-button w3-blue" type="submit" name="action" value="Add New"/>
        </form>
        <form action="MainController" method="POST">
            <input type="hidden" name="controller" value="Mark"/>
            <input class="w3-bar-item w3-input w3-theme-l5" type="text" name="textSearchMarkName" value="${requestScope.SEARCH.searchMarkName}" placeholder="Search mark name..."/>
            <input class="w3-bar-item w3-button w3-theme-l1" type="submit" name="action" value="Search"/>
        </form>
    </c:if>
</div>

<div style="margin-top: 38px"></div>