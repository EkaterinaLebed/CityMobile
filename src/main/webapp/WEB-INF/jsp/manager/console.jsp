
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>[managerConsole]</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"/>
    <script type="text/javascript" src="<c:url value="/resources/script/main.js"/>"></script>
</head>
<body>
    <div id="container">
        <jsp:include page="../parts/header.jsp"/>

        <div class="console-menu">
            <ul class="list">
                <li><a href="<c:url value="/abonent/create"/>">Create Abonent</a></li>
                <li><a href="<c:url value="/abonent/find"/>">Find Abonent</a></li>
                <li><a href="<c:url value="/load"/>">Load data from file</a></li>
                <li><a href="<c:url value="/billing"/>">Perform Billing</a></li>
            </ul>
        </div>

        <jsp:include page="../parts/footer.jsp"/>
    </div>
</body>
</html>
