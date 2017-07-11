
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>[createAbonent]</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"/>
</head>
<body>
    <div id="container">
        <jsp:include page="../parts/header.jsp"/>

        <div class="content">
            <form class="login-form" method="post" action="<c:url value="/abonent/create/do"/>">
                <div class="field">
                    <label for="id-name">name</label>
                    <input id="id-name" type="text" name="name">
                </div>

                <div class="field">
                    <label for="id-address">address</label>
                    <input id="id-address" type="text" name="address">
                </div>

                <input class="submit-btn" type="submit" value="Create">
            </form>

            <%--<div class="panel">--%>
                <%--Add abonent services--%>
            <%--</div>--%>
        </div>

        <jsp:include page="../parts/footer.jsp"/>
    </div>
</body>
</html>
