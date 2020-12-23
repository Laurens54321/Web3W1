<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="domain.model.Person" %>
<%@ page import="domain.model.ContactTracingService" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Overview</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
    <body>
        <div id="container">
            <header>
                <h1>Sportzaal Heverlee</h1>
                <jsp:include page="nav.jsp">
                    <jsp:param name="page" value="profile"/>
                </jsp:include>
                <h2>User Overview</h2>
            </header>

            <c:if test="${person == null}">
                <div class="message">
                    <p>Not a user? <a href="Servlet?command=Register">Sign Up</a></p>
                </div>
            </c:if>

            <main>
                <div>
                    <table id="Members">
                        <tr>
                            <th>E-mail</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>UserID</th>
                        </tr>


                        <c:forEach var="person" items="${DB}">
                            <tr class="members">
                                <td class="email"> ${person.getEmail()}</td>
                                <td class="firstName"> ${person.getFirstName()}</td>
                                <td class="lasName"> ${person.getLastName()}</td>
                                <td class="userid"> ${person.getUserid()}</td>
                            </tr>
                        </c:forEach>
                        <caption>Users overview</caption>
                    </table>

                    <c:if test="${person.isAdmin()}">
                        <form method="post" action="Servlet?command=RemoveAllPersons">
                            <p id="removeAllPersonButton">
                                <input type="submit" id="removeAllPersons" value="&#128128;">
                            </p>
                        </form>
                    </c:if>
                </div>
            </main>
            <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
        </div>
    </body>
</html>