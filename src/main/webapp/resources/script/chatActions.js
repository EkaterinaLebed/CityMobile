
var chatActions = (function(){
    var timerId  = null;
    var socket = new WebSocket("ws://localhost:8080/citymobile/chat");

    socket.onopen = function () {
        timerId  = window.setInterval(sendCmdUpdateUserList, 5000);
    };

    socket.onclose = function (event) {
        window.clearInterval(timerId);
        if (event.wasClean) { console.log("Closed!"); }
        else{ console.log("Error!"); }
        console.log('Еvent: code: ' + event.code + ' reason: ' + event.reason);
    };

    socket.onmessage = function (event) {
        var message = String(event.data);
        console.log(message);
        if (message!=null && message!=undefined && message.indexOf(":")!== -1) {
            var message_array = message.split(":");
            if (message_array[0] == "list") {
                document.getElementById("activeusers").value = message_array[1];
            }else{
                var text = document.getElementById("output").value;
                text = text + "\n" + message_array[0] + ":" + message_array[1];
                document.getElementById("output").value = text;
            }
        }
    };

    var module = {};

    module.send = function () {
        sendMessageTo(":"+document.getElementById("input").value);
    };

    function sendMessageTo(message){
        if(document.customer){
            var msg = message+":"+document.customer.name;
            socket.send(msg);
            console.log(msg)
        }
        else{
            console.log("sendMessageTo >> ERROR: customer is empty!")
        }
    }

    function sendCmdUpdateUserList() {
        socket.send("list:");
    }

    return module;
})();