
var abonentAction =(function(){
    var module = {};

    module.add = function() {
        var req = requestService.initRequest();
        req.open("POST", "/citymobile/abonent/create/do", true);
        req.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                if(this.responseXML){
                    var customer = this.responseXML.getElementsByTagName("customer")[0];

                    if(customer!=null){
                        var id = customer.getElementsByTagName("id")[0];
                        document["abonentId"] =id.childNodes[0].nodeValue;

                        var elem = document.getElementById("acceptedMsg");
                        elem.innerHTML = "OK";
                        elem.setAttribute("class","active-msg");
                    }
                }
            }
        };
        req.onerror = function() {
            var elem = document.getElementById("acceptedMsg");
            elem.innerHTML = "";
            elem.setAttribute("class","");
        };
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.send("name=" + document.getElementById("id-name").value +
            "&address=" + document.getElementById("id-address").value);
    };

    module.addService = function() {
        if(!document.abonentId){
            return;
        }

        var req = requestService.initRequest();
        req.open("POST", "/citymobile/abonent/add/service/do", true);
        req.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                if(this.responseXML){
                    console.log(this.responseXML);
                }
            }
        };
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.send("abonentId=" + document.abonentId +
            "&serviceId=" + document.getElementById("serviceElem").value);
    };

    return module;
})();
