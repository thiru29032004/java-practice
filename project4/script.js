function calculate() {
    let n1 = Number(document.getElementById("operand1").value);
    let n2 = Number(document.getElementById("operand2").value);
    let op = document.getElementById("operator").value;

    let res;
    if (op === "+") {
        res = n1 + n2;
    } else if (op === "-") {
        res = n1 - n2;
    } else if (op === "*") {
        res = n1 * n2;
    } else if (op === "/") {
        res = n1 / n2;
    } else {
        res = "Invalid operator";
    }

    document.getElementById("result").innerHTML = res;
}
