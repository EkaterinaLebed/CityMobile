
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>[findAbonent]</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"/>
    <script type="text/javascript" src="<c:url value="/resources/script/main.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/script/abonentActions.js"/>"></script>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
    <div id="container">
        <jsp:include page="../parts/header.jsp"/>

        <div class="content">
            <div class="login-form">
                <div class="field">
                    <label for="id-name">name</label>
                    <input id="id-name" type="text" name="name">

                    <div class="buttons">
                        <button class="submit-btn" onclick="abonentAction.find()">Find</button>
                    </div>
                </div>
            </div>

            <div class="panel panel-top">
                <div class="message"> </div>
                <table class="table-stlT2" id="abonentTable">
                    <thead>
                        <tr class="tb-header">
                            <th>Abonent</th>
                            <th class="tb-action"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>#</td>
                            <td class="tb-action"></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <jsp:include page="../parts/footer.jsp"/>
    </div>
</body>
</html>
