<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">

    <c:if test="${empty person}" >
        <title>Sign In</title>
    </c:if>

    <c:if test="${not empty person}" >
        <title>Profile</title>
    </c:if>

    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>XXX</span></h1>
        <jsp:include page="nav.jsp">
            <jsp:param name="page" value="profile"/>
        </jsp:include>
        <h2>
            Register
        </h2>
    </header>
    <main>
        <c:choose>
            <c:when test="${empty person}">
                <c:if test="${not empty errors}">
                    <div class="alert-danger">
                        <ul>
                            <c:forEach items="${errors}" var="error">
                                <li>${error}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>

                <form method="post" action="Servlet?command=LogIn" novalidate>
                    <p>
                        <label for="userid">User id</label>
                        <input type="text" id="userid" name="userid" required value="${fn:excapeXml(userIdPreviousValue)}">
                    </p>
                    <p>
                        <label for="password">Password</label>
                        <input type="password" id="password" name="password" required>
                    </p>
                    <p>
                        <input type="submit" id="logIn" value="Log In">
                    </p>
                </form>
            </c:when>
            <c:otherwise>

                <c:if test="${not empty errors}">
                    <div class="alert-danger">
                        <ul>
                            <c:forEach items="${errors}" var="error">
                                <li>${error}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>


                <p>Welcome ${person.getFirstName()}!</p>

                <p>Manage Account :</p>

                <form method="post" action="Servlet?command=LogOut" novalidate>
                    <p>
                        <input type="submit" id="logOut" value="Log Out">
                    </p>
                </form>

                <p>Create Reservation</p>

                <form method="post" action="Servlet?command=MakeReservation" novalidate="novalidate">
                    <!-- novalidate in order to be able to run tests correctly -->
                    <p><label for="startTime">Start of reservation</label><input type="datetime-local" id="startTime" name="startTime"
                                                                                 value="${startTimePreviousValue}" required></p>
                    <p><label for="endTime">End of reservation</label><input type="time" id="endTime"
                                                                             name="endTime" value="${endTimePreviousValue}"
                                                                             required value=""></p>
                    <p><label for="field">Field</label><input type="text" id="field" name="field"
                                                              value="${fieldPreviousValue}" required></p>
                    <p><label for="phonenr">Phone Number</label><input type="text" id="phonenr" name="phonenr"
                                                                value="${phonenrPreviousValue}" required></p>
                    <p><label for="email">Email</label><input type="email" id="email" name="email"
                                                                       value="${emailPreviousValue}" required></p>
                    <p><input type="submit" id="makeReservation" value="Make Reservation"></p>
                </form>

            </c:otherwise>
        </c:choose>
    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>