
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
                    var table = document.getElementById("serviceTable");

                    if(table){
                        var tableLineNull = document.getElementById("serviceTableLineNull");
                        if(tableLineNull){
                            tableLineNull.parentNode.removeChild(tableLineNull);
                        }

                        var product = this.responseXML.getElementsByTagName("product")[0];

                        if(product!=null){
                            var name = product.getElementsByTagName("name")[0];
                            var actDate = product.getElementsByTagName("activated_date")[0];
                            var deactDate = product.getElementsByTagName("deactivated_date")[0];
                            var payment = product.getElementsByTagName("payment")[0];

                            var row = document.createElement("tr");
                            row.innerHTML =
                                "<td>"+name.childNodes[0].nodeValue+
                                "</td><td>"+actDate.childNodes[0].nodeValue+
                                "</td><td>"+deactDate.childNodes[0].nodeValue+
                                "</td><td>"+payment.childNodes[0].nodeValue+"</td>";
                            table.appendChild(row);
                        }
                    }
                }
            }
        };
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.send("abonentId=" + document.abonentId +
            "&serviceId=" + document.getElementById("serviceElem").value);
    };

    module.find = function() {
        var ptext = document.getElementById("id-name").value;
        var req = requestService.initRequest();
        req.open("GET", "/citymobile/abonent/search?text="+ptext, true);
        req.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                if (this.responseXML) {
                    var elemTable = document.getElementById("abonentTable");
                    var customers = this.responseXML.getElementsByTagName("customers")[0];
                    clearTable(elemTable);

                    if(elemTable && customers){
                        var tbody = elemTable.getElementsByTagName("tbody")[0];
                        if(customers.childNodes.length==0) {
                            var elemItem = document.createElement("tr");
                            elemItem.innerHTML = "<td>#</td><td></td>";
                            tbody.appendChild(elemItem);
                        }

                        for (loop = 0; loop < customers.childNodes.length; loop++) {
                            var customer = customers.childNodes[loop];

                            var id = customer.getElementsByTagName("id")[0];
                            var name = customer.getElementsByTagName("name")[0];

                            var elemItem = document.createElement("tr");
                            elemItem.innerHTML = "<td>" + name.childNodes[0].nodeValue +
                                "</td><td>" + id.childNodes[0].nodeValue + "</td>";
                            tbody.appendChild(elemItem);
                        }
                    }
                }
            }
        };

        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.send(null);
        };

        function clearTable(table){
            if(table){
                var tbody = table.getElementsByTagName("tbody")[0];
                for (loop = tbody.childNodes.length - 1; loop >= 0; loop--) {
                    tbody.removeChild(tbody.childNodes[loop]);
                }
            }
        }

        return module;
})();
