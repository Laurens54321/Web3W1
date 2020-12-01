<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${errors!=null}">
    <div class="alert-danger">
        <ul>
            <c:forEach items="${errors}" var="error">
                <li>${error}</li>
            </c:forEach>
        </ul>
    </div>
</c:if>

<c:if test="${person==null}">
    <form method="post" action="Servlet?command=Home" novalidate="novalidate">
        <!-- novalidate in order to be able to run tests correctly -->
        <p><label for="userid">User id</label><input type="text" id="userid" name="userid" value="${fn:excapeXml(useridPreviousValue)}"
                                                     required></p>
        <p><label for="password">Password</label><input type="password" id="password" name="password" required></p>
        <p><input type="submit" id="logIn" value="Log in"></p>
    </form>
</c:if>
<c:if test="${person!=null}">
    <p>Welcome ${person.getFirstName()}</p>
    <form method="post" action="Servlet?command=LogOut" novalidate="novalidate">
        <p><input type="submit" id="logOut" value="Log Out"></p>
    </form>
</c:if>


