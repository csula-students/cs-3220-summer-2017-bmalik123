var $ = function (id) {
    return document.getElementById(id);
}
var calculateClick = function () {
    var bill = parseFloat($("bill").value);
    var tipPercentage = parseFloat($("percentTip").value);


    if (isNaN(bill) || bill <= 0) {
        return false;
    }

    else if (isNaN(tipPercentage) || tipPercentage <= 0) {
       return false;
    }

    else {
        var tip = bill * (tipPercentage / 100);
        var totalamount = bill + tip;
        $("tip").value = tip.toFixed(2);
        $("totalamount").value = totalamount.toFixed(2);
    }
}

window.onload = function () {
    $("calculate").onclick = calculateClick;
    $("bill").focus();
}