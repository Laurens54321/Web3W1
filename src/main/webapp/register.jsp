<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="scripts/validateFormScript.js"></script>
</head>
<body>
<div id="container">
    <header>
        <h1>Sportzaal Heverlee</h1>
        <jsp:include page="nav.jsp">
            <jsp:param name="page" value="profile"/>
        </jsp:include>
        <h2>
            Register
        </h2>
    </header>
    <main>
        <c:if test="${errors!=null}">
            <div class="alert-danger">
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <form name="registerForm" id="signup" onsubmit="return validateRegisterForm()" method="post" action="Servlet?command=Register" novalidate>
            <label for="userid">User id</label>
            <input type="text" id="userid" name="userid" value="<c:out value ='${useridPreviousValue}'/>">

            <label for="firstName">First Name</label>
            <input type="text" id="firstName" name="firstName" value="<c:out value ='${firstNamePreviousValue}'/>" >

            <label for="lastName">Last Name</label>
            <input type="text" id="lastName" name="lastName" value="<c:out value ='${ lastNamePreviousValue}'/>">

            <label for="email">Email</label>
            <input type="email" id="email" name="email" value="<c:out value ='${emailPreviousValue}'/>">


            <label for="password">Password</label>
            <input type="password" id="password" name="password" >
            <meter max="4" id="password-strength-meter"></meter>
            <p id="password-strength-text"></p>


            <input type="submit" id="submit" value="Sign Up">
        </form>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/zxcvbn/4.2.0/zxcvbn.js">
    var password = document.getElementById('password');
    var meter = document.getElementById('password-strength-meter');
    var text = document.getElementById('password-strength-text');

    var strength = {
        0: "Worst",
        1: "Bad",
        2: "Weak",
        3: "Good",
        4: "Strong"
    }

    password.addEventListener('input', function() {
        var val = password.value;
        var result = zxcvbn(val);

        // Update the password strength meter
        meter.value = result.score;

        // Update the text indicator
        if (val !== "") {
            text.innerHTML = "Strength: " + strength[result.score];
        } else {
            text.innerHTML = "";
        }
    });
</script>

</body>
</html>
