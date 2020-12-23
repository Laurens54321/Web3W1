document.addEventListener("DOMContentLoaded", sortContactTable)

function sortContactTable() {
    let table, rows, switching, i, x, y;
    table = document.getElementById("contactsTable");
    switching = true;
    while (switching) {
        switching = false;
        rows = table.rows;
        for (i = 1; i < (rows.length - 1); i++) {
            x = rows[i].getElementsByTagName("TD");
            y = rows[i + 1].getElementsByTagName("TD");
            if (dateTimeToComparableInt(x) < dateTimeToComparableInt(y)) {
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
                break;
            }
        }
    }
}

function dateTimeToComparableInt(TDList) {
    dateArray = TDList[0].innerHTML.split("/")
    timeArray = TDList[1].innerHTML.split(":")
    return parseInt(dateArray[2] + dateArray[1] + dateArray[0] + timeArray[0] + timeArray[1])
}