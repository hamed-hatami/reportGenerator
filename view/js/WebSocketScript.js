$(document).ready(function () {
    var url = document.getElementById("url").value;
    var ws;

    $("#printButton").click(function () {
        var id = document.getElementById("printedID").value;
        var dbcd = document.getElementById("printedDBCD").value;
        var bic = document.getElementById("printedBIC").value;
        var queryString = "id=" + id + ";ftyp=pdf;type=request;dbic=" + bic + ";dbcd=" + dbcd;

        if (ws == undefined || ws.readyState != 1) {    //1:connection is open
            openConnection();
            setTimeout(function () {
                ws.send(queryString);
                hideWaitingPanel();
            }, 3000);

        } else {
            ws.send(queryString);
            hideWaitingPanel();
        }
    });

    function openConnection() {
        try {
            ws = new WebSocket(url);

            ws.onconnect = function (e) {
                console.log("connected, date: " + new Date());
            }
            ws.onerror = function (error) {
                console.log('WebSocket Error ' + error + ", date: " + new Date());
                showErrorDiv("بروز خطا در برقراری ارتباط با سیستم! لطفا با مدیرسیستم تماس برقرار نمایید", 'fa');
            };
            ws.onclose = function (event) {
                console.log("Remote host closed or refused WebSocket connection, date: " + new Date());
            };
            ws.onmessage = function (message) {
                var blob = message.data;
                var id = document.getElementById("printedID").value;
                saveAs(blob, id + ".pdf");
                console.log("save new Message, date: " + new Date());

            };
        } catch (e) {
            showErrorDiv("بروز خطا در برقراری ارتباط با سیستم! لطفا با مدیرسیستم تماس برقرار نمایید", 'fa');
        }
    }
});