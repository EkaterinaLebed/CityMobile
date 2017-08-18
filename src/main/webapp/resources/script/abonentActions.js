
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
                        var idValue = id.childNodes[0].nodeValue;
                        document["customer"] = {id: idValue};

                        var elem = document.getElementById("acceptedMsg");
                        elem.innerHTML = "OK";
                        elem.setAttribute("class","active-msg");

                        clearTableServices(document.getElementById("serviceTable"));
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

    module.activate = function(abonentId){
        var req = requestService.initRequest();
        req.open("GET", "/citymobile/abonent/activate?id="+abonentId, true);
        req.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                var btnActivate = document.getElementById("btnActivateSwitch");
                if(btnActivate){
                    btnActivate.innerHTML="Deactivate abonent";
                    btnActivate.onclick=function(){
                        abonentAction.deactivate(abonentId)};
                }
            }
        };
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.send(null);
    };

    module.deactivate = function(abonentId){
        var req = requestService.initRequest();
        req.open("GET", "/citymobile/abonent/deactivate?id="+abonentId, true);
        req.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                var btnActivate = document.getElementById("btnActivateSwitch");
                if(btnActivate){
                    btnActivate.innerHTML = "Activate abonent";
                    btnActivate.onclick=function(){
                        abonentAction.activate(abonentId)}
                }
            }
        };
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.send(null);
    };

    module.addService = function(PAGE_TYPE) {
        if(!document.customer){
            return;
        }

        var req = requestService.initRequest();
        req.open("POST", "/citymobile/abonent/add/service/do", true);
        req.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                if(this.responseXML){
                    var table = document.getElementById("serviceTable");
                    var products = this.responseXML.getElementsByTagName("products")[0];
                    if(table && products){
                        addItemsTableServices(table,products,PAGE_TYPE);
                    }
                }
            }
        };
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.send("abonentId=" + document.customer.id +
            "&serviceId=" + document.getElementById("serviceElem").value);
    };

    module.activateService = function(productId) {
        var elemClassButton="deactivate";
        var elemClassDateDeactive="date-deactive";
        var req = requestService.initRequest();
        req.open("POST", "/citymobile/abonent/activate/service/do",true);
        req.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                var elemItem = document.getElementById("prod"+productId);
                if(elemItem){
                    var elemButton = elemItem.getElementsByClassName(elemClassButton)[0];
                    var elemDateDeact = elemItem.getElementsByClassName(elemClassDateDeactive)[0];
                    if(elemButton){
                        elemButton.innerHTML="deactivate";
                        elemButton.onclick = function(){
                            abonentAction.deactivateService(productId)};
                    }
                    if(elemDateDeact){
                        elemDateDeact.innerHTML="";
                    }
                }
            }
        };
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.send("id=" + productId);
    };

    module.deactivateService = function(productId) {
        var elemClassButton="deactivate";
        var elemClassDateDeactive="date-deactive";
        var req = requestService.initRequest();
        req.open("POST", "/citymobile/abonent/deactivate/service/do",true);
        req.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                var elemItem = document.getElementById("prod"+productId);
                if(elemItem){
                    var elemButton = elemItem.getElementsByClassName(elemClassButton)[0];
                    var elemDateDeact = elemItem.getElementsByClassName(elemClassDateDeactive)[0];
                    if(elemButton){
                        elemButton.innerHTML="activate";
                        elemButton.onclick = function(){
                            abonentAction.activateService(productId)};
                    }
                    if(elemDateDeact){
                        var now = new Date();
                        var month = (1 + now.getMonth()).toString();
                        month = month.length > 1 ? month : '0' + month;
                        elemDateDeact.innerHTML = now.getDate()+"."+month+"."+now.getFullYear();
                    }
                }
            }
        };
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.send("id=" + productId);
    };

    module.find = function() {
        var ptext = document.getElementById("id-name").value;
        var req = requestService.initRequest();
        req.open("GET", "/citymobile/abonent/search?text="+ptext, true);
        req.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                if (this.responseXML) {
                    var table = document.getElementById("abonentTable");
                    var customers = this.responseXML.getElementsByTagName("customers")[0];
                    if(table && customers){
                        clearTable(table);
                        addItemsTableCustomers(table,customers);
                    }
                }
            }
        };

        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.send(null);
        };

    function clearTableServices(elemTable){
        var tbody = elemTable.getElementsByTagName("tbody")[0];

        for (loop = tbody.childNodes.length - 1; loop >= 0; loop--) {
            tbody.removeChild(tbody.childNodes[loop]);
        }

        var elemItem = document.createElement("tr");
        elemItem.id = "serviceTableLineNull";
        elemItem.innerHTML = "<td>#</td><td></td><td></td><td></td>";
        tbody.appendChild(elemItem);
    }

    function addItemsTableServices(elemTable,products,PAGE_TYPE){
        var tbody = elemTable.getElementsByTagName("tbody")[0];
        var elemItem = null;
        var idEmptyLine='serviceTableLineNull';

        if(products.childNodes.length==0){
            elemItem = document.createElement("tr");
            elemItem.id = idEmptyLine;
            if(PAGE_TYPE=="CREATE") elemItem.innerHTML = "<td>#</td><td></td><td></td><td></td>";
            else if(PAGE_TYPE=="INFO") elemItem.innerHTML = "<td>#</td><td></td><td></td><td></td><td></td>";
            tbody.appendChild(elemItem);
            return;
        }

        for (loop = 0; loop < products.childNodes.length; loop++) {
            var product = products.childNodes[loop];

            var id = product.getElementsByTagName("id")[0];
            var name = product.getElementsByTagName("name")[0];
            var actDate = product.getElementsByTagName("activated_date")[0];
            var deactDate = product.getElementsByTagName("deactivated_date")[0];
            var payment = product.getElementsByTagName("payment")[0];
            var idValue = id.childNodes[0].nodeValue;

            elemItem = document.createElement("tr");
            elemItem.id = "prod"+idValue;

            if(PAGE_TYPE=="CREATE"){
                elemItem.innerHTML =
                    "<td>"+name.childNodes[0].nodeValue+
                    "</td><td>"+actDate.childNodes[0].nodeValue+
                    "</td class='date-deactive'><td>"+deactDate.childNodes[0].nodeValue+
                    "</td><td>"+payment.childNodes[0].nodeValue+"</td>";
            }
            else if(PAGE_TYPE=="INFO"){
                elemItem.innerHTML =
                    "<td>"+name.childNodes[0].nodeValue+
                    "</td><td>"+actDate.childNodes[0].nodeValue+
                    "</td class='date-deactive'><td>"+deactDate.childNodes[0].nodeValue+
                    "</td><td>"+payment.childNodes[0].nodeValue+"</td>"+
                    "</td><td class='tb-action'><button class='deactivate' onclick='abonentAction.deactivateService("+idValue+")'>deactivate</button></td>";
            }

            tbody.appendChild(elemItem);

            elemItem = document.getElementById(idEmptyLine);
            if(elemItem) tbody.removeChild(elemItem);
        }
    }

    function addItemsTableCustomers(elemTable,customers){
        var tbody = elemTable.getElementsByTagName("tbody")[0];
        var elemItem = null;

        if(customers.childNodes.length==0){
            elemItem = document.createElement("tr");
            elemItem.innerHTML = "<td>#</td><td class='tb-action'></td>";
            tbody.appendChild(elemItem);
            return;
        }

        for (loop = 0; loop < customers.childNodes.length; loop++) {
            var customer = customers.childNodes[loop];

            var id = customer.getElementsByTagName("id")[0];
            var name = customer.getElementsByTagName("name")[0];
            var idValue = id.childNodes[0].nodeValue;
            var nameValue = name.childNodes[0].nodeValue;

            elemItem = document.createElement("tr");
            elemItem.innerHTML = "<td>" +nameValue +
                "</td><td class='tb-action'><button onclick=location.href='/citymobile/abonent/info?id="+idValue+"'>Show</button></td>";
            tbody.appendChild(elemItem);
        }
    }

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
