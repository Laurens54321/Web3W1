<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: laure
  Date: 10/21/2020
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1>Sportzaal Heverlee</h1>
        <jsp:include page="nav.jsp">
            <jsp:param name="page" value="profile"/>
        </jsp:include>
        <h2>Error page</h2>

        <c:if test="${not empty errors}">
            <div class="alert-danger">
                <p>${errors}</p>
            </div>
        </c:if>

    </header>
    <main>


        <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
    </main>

</div>
</body>
</html>