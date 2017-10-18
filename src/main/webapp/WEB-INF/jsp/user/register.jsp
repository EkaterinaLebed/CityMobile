
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>[register]</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"/>
    <script type="text/javascript" src="<c:url value="/resources/script/main.js"/>"></script>
</head>
<body>
    <div id="container">
        <jsp:include page="../parts/header.jsp"/>

        <div class="content">
            <form class="login-form" method="post" action="<c:url value="/register/do"/>">
                <div class="field">
                    <label for="id-name">login</label>
                    <input id="id-name" type="text" name="login">
                </div>

                <div class="field">
                    <label for="id-pwd">password</label>
                    <input id="id-pwd" type="password" name="pwd"/>
                </div>

                <div class="buttons">
                    <input class="submit-btn" type="submit" value="Register">
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>

        <jsp:include page="../parts/footer.jsp"/>
    </div>
</body>
</html>