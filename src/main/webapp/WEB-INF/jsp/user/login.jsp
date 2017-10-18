
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>[login]</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"/>
    <script type="text/javascript" src="<c:url value="/resources/script/main.js"/>"></script>
</head>
<body>
    <div id="container">
        <jsp:include page="../parts/header.jsp"/>

        <div class="content">
            <c:url value="/login" var="loginUrl"/>
            <form class="login-form" action="${loginUrl}" method="POST">
                <div class="field">
                    <label for="id-login">login</label>
                    <input id="id-login" type="text" name="login">
                </div>

                <div class="field">
                    <label for="id-pwd">password</label>
                    <input id="id-pwd" type="password" name="pwd"/>
                </div>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="buttons">
                    <input class="submit-btn" type="submit" value="Login">
                </div>
                <c:if test="${param.error != null}">
                    <p>Invalid username and password. </p>
                </c:if>
                <c:if test="${param.logout != null}">
                    <p>You have been logged out.</p>
                </c:if>
            </form>
        </div>

        <jsp:include page="../parts/footer.jsp"/>
    </div>
</body>
</html>
