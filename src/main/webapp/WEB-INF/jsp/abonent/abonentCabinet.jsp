
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>[abonentCabinet]</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"/>
    <script type="text/javascript" src="<c:url value="/resources/script/chatActions.js"/>"></script>
</head>
<body>
    <%--<div id="container">--%>
        <%--<jsp:include page="../parts/header.jsp"/>--%>
        <%--<jsp:include page="../parts/footer.jsp"/>--%>
    <%--</div>--%>

    <form>
        <textarea id="activeusers"></textarea>
        <textarea id="output"></textarea>
        <input type="text" id="input"/>
        <input type="button" onclick="chatActions.send()" name="Send" value="Send"/>
    </form>
</body>
</html>
