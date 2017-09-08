
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>[createAbonent]</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"/>
    <script type="text/javascript" src="<c:url value="/resources/script/main.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/script/abonentActions.js"/>"></script>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <meta name="_csrf_param" content="${_csrf.parameterName}"/>
</head>
<body>
    <div id="container">
        <jsp:include page="../parts/header.jsp"/>
        <div class="content">
            <div class="login-form">
                <div class="field">
                    <label for="id-name">name</label>
                    <input id="id-name" type="text" name="name">
                </div>

                <div class="field">
                    <label for="id-address">address</label>
                    <input id="id-address" type="text" name="address">
                </div>

                <div class="buttons-panel">
                    <div class="element">
                        <button class="submit-btn" onclick="abonentAction.add()">Create</button>
                        <div id="acceptedMsg"></div>
                    </div>
                </div>
            </div>

            <div class="panel">
                <div class="message">Add abonent services</div>
                <div class="panel-block">
                    <select id="serviceElem" class="serviceList">
                        <c:forEach var="service" items="${serviceList}">
                            <option value=${service.id}>${service.name}</option>
                        </c:forEach>
                        <option></option>
                    </select>
                    <button onclick="abonentAction.addService('CREATE')">Add</button>
                </div>

                <table class="table-stlT2" id="serviceTable">
                    <thead>
                        <tr>
                            <th>Service</th>
                            <th>Date activated</th>
                            <th>Date deactivated</th>
                            <th>Payment</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr id="serviceTableLineNull">
                            <td>#</td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <jsp:include page="../parts/footer.jsp"/>
    </div>
</body>
</html>
