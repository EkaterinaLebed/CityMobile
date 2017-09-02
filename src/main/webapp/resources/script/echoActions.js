
var echoActions = (function(){
    var socket = new WebSocket("ws://localhost:8080/citymobile/echo");
    socket.onopen = function (){console.log("Connected");};
    socket.onclose = function (){
        if (event.wasClean) { console.log("Closed");}
        else {console.log("Error!!!");}
    };
    socket.onmessage = function (){
        var message = String(event.data);
        if (message!=null && message!=undefined){
            var text = document.getElementById("output").value + ("\n"+message);
            document.getElementById("output").value = text;
        }
    };

    var module = {};

    module.send = function (){
        socket.send(document.getElementById("input").value);
    };

    return module;

})();