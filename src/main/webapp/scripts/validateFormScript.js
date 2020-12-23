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
    } else form["password"].className = "form-group has-success";


    if (errors.length > 0) {
        createErrorMessage(document.getElementsByTagName("form")[0], errors);
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