function validateLogInForm() {
    let form, userid, password, errors;
    errors = [];
    form = document.forms["logInForm"];
    userid = form["userid"].value;
    password = form["password"].value;

    if (userid.trim() === "") {
        errors.push("No userid given");
        form["userid"].value = "";
        form["userid"].className = "has-error";
    } else form["userid"].className = "has-success";
    if (password.trim() === "") {
        errors.push("No password given");
        form["password"].value = "";
        form["password"].className = "has-error";
    } else form["password"].className = "has-success";

    if (errors.length > 0) {
        createErrorMessage(document.getElementsByTagName("form")[0],errors);
        return false;
    } else return true;
}

function validateRegisterForm(){
    let form, userid, firstName, lastName, email, password, errors;
    errors = [];
    form = document.forms["registerForm"];
    userid = form["userid"].value;
    firstName = form["firstName"].value;
    lastName = form["lastName"].value;
    email = form["email"].value;
    password = form["password"].value;

    if (userid.trim() === "") {
        errors.push("No userid given");
        form["userid"].value = "";
        form["userid"].className = "form-group has-error";
    } else form["userid"].className = "form-group has-success";
    if (firstName.trim() === "") {
        errors.push("No first name given");
        form["firstName"].value = "";
        form["firstName"].className = "form-group has-error";
    } else form["firstName"].className = "form-group has-success";
    if (lastName.trim() === "") {
        errors.push("No last name given");
        form["lastName"].value = "";
        form["lastName"].className = "form-group has-error";
    } else form["lastName"].className = "form-group has-success";
    if (email.trim() === "") {
        errors.push("No email given");
        form["email"].value = "";
        form["email"].className = "form-group has-error";
    } else {
        let EMAIL_PATTERN = new RegExp("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        if (!email.match(EMAIL_PATTERN)) {
            errors.push("Email is not valid");
            form["email"].value = "";
            form["email"].className = "form-group has-error";
        } else form["email"].className = "form-group has-success";
    }
    if (password.trim() === "") {
        errors.push("No password given");
        form["password"].value = "";
        form["password"].className = "has-error";
    } let EMAIL_PATTERN = new RegExp("^.{8,}$");
    if (!password.match(EMAIL_PATTERN)) {
        errors.push("Email is not valid");
        form["password"].value = "";
        form["password"].className = "form-group has-error";
    } else form["password"].className = "form-group has-success";


    if (errors.length > 0) {
        createErrorMessage(document.getElementsByTagName("form")[0], errors);
        return false;
    } else return true;
}

function getmydik(){
    form = document.forms["reservationForm"];
    startTime = form["startTime"].value;
    console.log(startTime);
}

function validateReservationForm(){
    let form, startTime, endTime, field, phonenr, email, errors;
    errors = [];
    form = document.forms["reservationForm"];
    startTime = form["startTime"].value;
    endTime = form["endTime"].value;
    field = form["field"].value;
    phonenr = form["phonenr"].value;
    email = form["email"].value;

    if (startTime.trim() === "") {
        errors.push("No startTime given");
        form["startTime"].value = "";
        form["startTime"].className = "form-group has-error";
    } else form["startTime"].className = "form-group has-success";
    if (endTime.trim() === "") {
        errors.push("No endTime given");
        form["endTime"].value = "";
        form["endTime"].className = "form-group has-error";
    } else form["endTime"].className = "form-group has-success";
    if (field.trim() === "") {
        errors.push("No field given");
        form["field"].value = "";
        form["field"].className = "form-group has-error";
    } else form["userid"].className = "form-group has-success";
    if (phonenr.trim() === "") {
        errors.push("No phonenr given");
        form["phonenr"].value = "";
        form["phonenr"].className = "form-group has-error";
    } else form["phonenr"].className = "form-group has-success";
    if (email.trim() === "") {
        errors.push("No email given");
        form["email"].value = "";
        form["email"].className = "form-group has-error";
    } else form["email"].className = "form-group has-success";

    if (errors.length > 0) {
        createErrorMessage(document.getElementsByTagName("form")[1], errors);
        return false;
    } else return true;
}


function createErrorList(error, place){
    removeErrors();

}

function createErrorMessage(element, errors){
    removeErrors()
    let errorDivs = document.getElementsByClassName("alert-danger"), ul;

    element.insertAdjacentHTML("beforebegin", "<div class=\"alert-danger\"><ul></ul></div>");
    ul = errorDivs[0].getElementsByTagName("ul")[0];
    for (let i = 0; i < errors.length; i++) {
        ul.insertAdjacentHTML("beforeend", "<li>" + errors[i] + "</li>");
    }
}

function removeErrors(){
    let errorDivs = document.getElementsByClassName("alert-danger");

    while(errorDivs[0]) {
        errorDivs[0].parentNode.removeChild(errorDivs[0]);
    }
}