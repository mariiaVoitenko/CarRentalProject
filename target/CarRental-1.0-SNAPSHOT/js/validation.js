function check_fields(formname) {
    var returnval = false;
    var msg = "";
    var re = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;

    if (formname.login.value == "") {
        msg += "You must fill your login. Try one more time\n";
    }
    if (formname.password.value == "") {
        msg += "You must fill your password. Try one more time\n";
    }
    if (formname.password.value.length < 8) {
        msg += "Password length must be at least 8 symbols. Try one more time\n";
    }
    if (isNaN(formname.login.value) == false) {
        msg += "Login can't contain only numbers. Try one more time\n";
    }
    if (re.test(formname.login.value) == false) {
        msg += "Login is your email. Try one more time\n";
    }
    if (msg == "") {
        returnval = true;
        return returnval;
    }
    else {
        alert(msg);
        return returnval;
    }
}

function submit_form(formname) {
    if (check_fields(formname)) {
        return true;
    }
    else {
        return false;
    }
}
