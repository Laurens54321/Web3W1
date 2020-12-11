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
        <c:if test="${errors!=null}">
            <div class="alert-danger">
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <form id="signup" method="post" action="Servlet?command=Register">
            <label for="userid">User id</label>
            <input type="text" id="userid" name="userid" required pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{3,20}$"  value="<c:out value ='${useridPreviousValue}'/>" required>

            <label for="firstName">First Name</label>
            <input type="text" id="firstName" name="firstName" required pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{3,20}$" value="<c:out value ='${firstNamePreviousValue}'/>" required value="">

            <label for="lastName">Last Name</label>
            <input type="text" id="lastName" name="lastName" required pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{3,20}$" value="<c:out value ='${ lastNamePreviousValue}'/>" required>

            <label for="email">Email</label>
            <input type="email" id="email" name="email" required pattern="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$" value="<c:out value ='${emailPreviousValue}'/>" required>


            <label for="password">Password</label>
            <input type="password" id="password" name="password" required pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{8,20}$" required>
            <meter max="4" id="password-strength-meter"></meter>
            <p id="password-strength-text"></p>


            <input type="submit" id="submit" value="Sign Up">
        </form>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>

<script>
    document.addEventListener("blur", checkField, true);  //check every field every time no field is selected

    document.addEventListener("submit", finalValidation, false); //submit button

    function checkField(event) {
        let error = hasError(event.target);
        if (error)
            showError(event.target, error);
        else
            removeError(event.target);
    }

    function finalValidation(event) {
        let fields = event.target.elements;
        let error, hasErrors;
        for (let i = 0; i < fields.length; i++) {
            error = hasError(fields[i]);
            if (error) {
                showError(fields[i], error);
                if (!hasErrors) {
                    hasErrors = fields[i];
                }
            }

        }

        if (hasErrors) {
            event.preventDefault();
            hasErrors.focus();
        }
    }

    function hasError(field) {
        if (field.disabled || field.type === "file" || field.type === "submit") //errors do not apply to these fields
            return;

        let validity = field.validity;
        if (validity == null || validity.valid) {
            return;
        }

        if (validity.valueMissing) {
            return "This field cannot be empty";
        }
        if (validity.typeMismatch) {
            return "Please use the correct input type";
        }
        if (validity.patternMismatch) {
            if (field.type === "text") {
                return "This field must contain at least 4 characters";
            }
        }
        return "Please complete the form correct";
    }

    /**
     * Removes all error messages and signals related to the given field
     * @param field
     */

    function removeError(field) {
        if (field.classList != null && field.classList.length > 0) {
            field.classList.remove("error");
            let id = field.id;
            let message = document.getElementById("error-for-" + id);
            if (message != null)
                message.parentNode.removeChild(message);
        }
    }

    /**
     * Generates error message with given text 'error' for the given field
     * The error message has id 'error-for-'+id with id the of the given field
     * The error message is inserted after the given field
     */

    function showError(field, error) {
        field.classList.add("error");
        let id = field.id;
        if (!id)
            return;
        let message = document.getElementById("error-for-" + id);
        if (!message) {
            message = document.createElement("span");
            message.className = "error";
            message.id = "error-for-" + id;
            field.parentNode.insertBefore(message, field.nextSibling);
        }
        message.innerHTML = error;
    }



</script>
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
