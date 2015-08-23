function getCriterias() {
    var query = "";
    var selectedBrand = returnValue("brandSelect");
    query = buildQuery(query, "brand", selectedBrand);
    var selectedClass = returnValue("classSelect");
    query = buildQuery(query, "class", selectedClass);
    var selectedNameSort = returnValue("nameSortSelect");
    query = buildQuery(query, "nameSort", selectedNameSort);
    var selectedPriceSort = returnValue("priceSortSelect");
    query = buildQuery(query, "priceSort", selectedPriceSort);
    if (selectedNameSort == "" && selectedPriceSort == "" && selectedBrand=="" && selectedClass=="") {
        alert("You should select at least one parameter of sorting");
        return;
    }
    if (selectedNameSort != "" && selectedPriceSort != "") {
        alert("You should select only ONE type of sorting");
        return;
    }
    return query;
}

function returnValue(selectName) {
    var select = document.getElementById(selectName);
    return select.options[select.selectedIndex].value;
}

function buildQuery(query, option, selectedValue) {
    if (selectedValue != "") {
        query += option;
        query += "=";
        query += selectedValue;
        query += "&";
    }
    return query;
}

function sendGetRequest() {
    var url = "sort";
    var xhr = new XMLHttpRequest();
    var params = getCriterias();
    if (params != undefined) {
        xhr.open("GET", url + "?" + params, true);
        xhr.onload = function (e) {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    alert(xhr.responseText + "200");
                } else {
                    alert(xhr.statusText + xhr.status);
                }
            }
        };
        xhr.onerror = function (e) {
            alert(xhr.statusText + "error");
        };
        xhr.send(null);
    }
}