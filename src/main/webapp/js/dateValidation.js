function checkForm(form) {
    var start = new Date(form.start.value);
    var end = new Date(form.end.value);
    var now = new Date();
    if (start > end) {
        alert("Start date mush be less than end date ");
        form.start.focus();
        return false;
    }
    if (start < now) {
        alert("You have to enter future date");
        form.start.focus();
        return false;
    }
    return true;
}
