
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>[login]</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"/>
</head>
<body>
    <div id="container">
        <jsp:include page="../parts/header.jsp"/>

        <div class="content">
            <form class="login-form" method="post" action="<c:url value="/login/do"/>">
                <div class="field">
                    <label for="id-login">login</label>
                    <input id="id-login" type="text" name="login">
                </div>

                <div class="field">
                    <label for="id-pwd">password</label>
                    <input id="id-pwd" type="password" name="pwd"/>
                </div>

                <div class="buttons">
                    <input class="submit-btn" type="submit" value="Login">
                </div>
            </form>
        </div>

        <jsp:include page="../parts/footer.jsp"/>
    </div>
</body>
</html>
