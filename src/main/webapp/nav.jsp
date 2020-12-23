<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav>
    <ul>
        <li ${param.command == 'Home' ? "id=actual" : ""}><a href="Servlet?command=Home">Home</a></li>
        <li ${param.command == 'Overview' ? "id=actual" : ""}><a href="Servlet?command=Overview">Overview</a></li>
        <li ${param.command == 'Profile' ? "id=actual" : ""}><a href="Servlet?command=Profile">Profile</a></li>
        <c:if test="${not empty person}">
            <li ${param.command == 'RegisterTest' ? "id=actual" : ""}><a href="Servlet?command=Test">Register Test</a></li>
            <li ${param.command == 'YourReservations' ? "id=actual" : ""}><a href="Servlet?command=YourReservations">Your Reservations</a></li>
            <li ${param.command == 'Search' ? "id=actual" : ""}><a href="Servlet?command=Search">Search</a></li>
            <c:if test="${person.isAdmin()}">
                <li ${param.command == 'AllReservations' ? "id=actual" : ""}><a href="Servlet?command=AllReservations">All Reservations</a></li>
            </c:if>
        </c:if>
    </ul>
</nav>