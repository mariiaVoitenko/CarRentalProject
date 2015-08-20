function check_car_fields(formname) {
    var returnval = false;
    var msg = "";

    if (formname.model.value == "") {
        msg += "You must fill model input\n";
    }
    if (formname.sitsCount.value == "" || formname.sitsCount.value > 10 || formname.sitsCount.value < 2) {
        msg += "You must fill sits count correctly\n";
    }
    if (formname.bigLuggageCount.value == "" || formname.bigLuggageCount.value > 5 || formname.bigLuggageCount.value < 0 || isNaN(formname.bigLuggageCount.value)) {
        msg += "You must fill the count of baggage with capacity under 100 liters\n";
    }
    if (formname.smallLuggageCount.value == "" || formname.smallLuggageCount.value > 5 || formname.smallLuggageCount.value < 0 || isNaN(formname.smallLuggageCount.value)) {
        msg += "You must fill the count of baggage with capacity under 50 liters\n";
    }
    if (formname.price.value == "" || formname.price.value < 0 || isNaN(formname.price.value)) {
        msg += "You must fill the price of one day rental\n";
    }
    if (formname.doorsCount.value == "" || formname.doorsCount.value < 0 || formname.doorsCount.value > 5  ||isNaN(formname.doorsCount.value)) {
        msg += "You must fill the count of car's doors including luggage boot\n";
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

function submit_car_form(formname) {
    if (check_car_fields(formname)) {
        return true;
    }
    else {
        return false;
    }
}