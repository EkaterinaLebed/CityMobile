
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>[createAbonent]</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"/>
    <script type="text/javascript" src="<c:url value="/resources/script/main.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/script/abonentAction.js"/>"></script>
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

                <div class="field">
                    <button class="submit-btn" onclick="abonentAction.add()">Create</button>
                    <div id="acceptedMsg"></div>
                </div>
            </div>

            <div class="panel">
                <div class="message">Add abonent services</div>
                <div class="block-add-new">
                    <select>
                        <option></option>
                        <option>Пункт 1</option>
                        <option>Пункт 2</option>
                    </select>
                    <button>Add</button>
                </div>

                <table>
                    <tr>
                        <th>Service</th>
                        <th>Date activated</th>
                        <th>Date deactivated</th>
                        <th>Payrol</th>
                    </tr>

                    <tr>
                        <td>1</td>
                        <td>2</td>
                        <td>3</td>
                        <td></td>
                    </tr>
                </table>
            </div>
        </div>

        <jsp:include page="../parts/footer.jsp"/>
    </div>
</body>
</html>
