<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="domain.model.Person" %>
<%@ page import="domain.model.ContactTracingService" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Search</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="scripts/contactSorter.js" defer></script>
</head>
<body>
<div id="container">
    <header>
        <h1>Sportzaal Heverlee</h1>
        <jsp:include page="nav.jsp">
            <jsp:param name="page" value="profile"/>
        </jsp:include>

        <c:if test="${not empty errors}">
            <div class="alert-danger">
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>${errors}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <h2>Search</h2>
    </header>
    <main>
        <c:if test="${not empty message}">
            <div class="message">
                <p>${message}</p>
            </div>
        </c:if>
        <c:otherwise>
            <table id="contactsTable">
                <tr>
                    <th>Date</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Test Result</th>
                </tr>
                <c:forEach var="reservation" items="${reservations}">
                    <tr>
                        <td>${reservation.getDateString()}</td>
                        <td>${reservation.getUserid()}</td>
                        <td>${reservation.getEmail()}</td>
                        <td>${reservation.getPhonenr()}</td>
                    </tr>
                </c:forEach>

            </table>
        </c:otherwise>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>