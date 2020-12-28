window.addEventListener("load", initPage, false);



var strength = {
    0: "Worst",
    1: "Bad",
    2: "Weak",
    3: "Good",
    4: "Strong"
}

function initPage() {
    document.getElementById('password').addEventListener("input", checkPassword, false);
}

function checkPassword(){
    var password = document.getElementById('password');
    var meter = document.getElementById('password-strength-meter');
    var text = document.getElementById('password-strength-text');

    let result = strengthChecker(password.value);

    // Update the password strength meter
    meter.value = result;

    // Update the text indicator
    if (result !== 0) {
        text.innerHTML = "Strength: " + strength[result];
    } else {
        text.innerHTML = "";
    }
}

function strengthChecker(password){
    if (!password){
        return 0;
    }
    var score = 0
    if (password.length >= 8){
        score += 1
    }
    if (password.match("[A-Z]")){
        score += 1
    }
    if (password.match("[0-9]")){
        score += 1
    }
    if (password.match("[@$!%*#?&]")){
        score += 1
    }
    return score
}