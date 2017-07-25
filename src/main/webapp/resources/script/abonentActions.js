
var abonentAction =(function(){
    var module = {};

    module.add = function() {
        var req = requestService.initRequest();
        req.open("POST", "/citymobile/abonent/create/do", true);
        req.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                var elem = document.getElementById("acceptedMsg");
                elem.innerHTML = "OK";
                elem.setAttribute("visibility","visible")
            }
        };
        req.onerror = function() {
            var elem = document.getElementById("acceptedMsg");
            elem.innerHTML = "";
            elem.setAttribute("visibility","none")
        };
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.send("name=" + document.getElementById("id-name").value +
        "&address="+document.getElementById("id-address").value);
    };

    return module;
})();
