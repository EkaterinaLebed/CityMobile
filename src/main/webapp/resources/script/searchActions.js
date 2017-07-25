var search =(function(){
    var module = {};
    var isIE = false;

    module.doCompletion = function(text) {
        var req = requestService.initRequest(isIE);
        req.open("GET", "/citymobile/product/search?text="+text+"", true);
        req.setRequestHeader('Content-Type', 'application/xml');
        req.onreadystatechange = function(){
            var completeTable = document.getElementById("complete-table");
            clearTable(completeTable);
            if (this.readyState == 4 && this.status == 200) {
                if(this.responseXML){
                    parseMessages(completeTable,this.responseXML);
                    console.log(this.responseXML);
                }
            }
        };
        req.send(null);
    };

    function clearTable(completeTable) {
        completeTable.style.display = 'none';

        if (completeTable.getElementsByTagName("tr").length > 0) {
            for (loop = completeTable.childNodes.length -1; loop >= 0; loop--) 	{
                completeTable.removeChild(completeTable.childNodes[loop]);
            }
        }
    }

    function parseMessages(completeTable,responseXML) {
        if (responseXML == null) {
            return false;
        }

        var products = responseXML.getElementsByTagName("products")[0];

        if (products.childNodes.length > 0) {
            completeTable.setAttribute("bordercolor", "black");
            completeTable.setAttribute("border", "1");

            for (loop = 0; loop < products.childNodes.length; loop++) {
                var product = products.childNodes[loop];

                var id = product.getElementsByTagName("id")[0];
                var name = product.getElementsByTagName("name")[0];
                var price = product.getElementsByTagName("price")[0];

                appendItem(completeTable,
                    id.childNodes[0].nodeValue,
                    name.childNodes[0].nodeValue,
                    price.childNodes[0].nodeValue);
            }
        }
    }

    function appendItem(completeTable, id, name, price) {
        var row;
        var cell;
        var linkElement;

        if (isIE) {
            completeTable.style.display = 'block';
            row = completeTable.insertRow(completeTable.rows.length);
            cell = row.insertCell(0);
        } else {
            completeTable.style.display = 'table';
            row = document.createElement("tr");
            cell = document.createElement("td");
            row.appendChild(cell);
            completeTable.appendChild(row);
        }

        cell.className = "popupCell";

        linkElement = document.createElement("a");
        linkElement.className = "popupItem";
        linkElement.setAttribute("href", "product?id=" + id);
        linkElement.appendChild(document.createTextNode(name + " : " + price+" ₴"));
        cell.appendChild(linkElement);
    }

    return module;
})();
