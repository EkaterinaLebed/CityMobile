
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>[abonentInfo]</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"/>
    <script type="text/javascript" src="<c:url value="/resources/script/main.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/script/abonentActions.js"/>"></script>
    <script type="text/javascript"> document.customer={id:${customer.id}};</script>
</head>
<body>
    <div id="container">
        <jsp:include page="../parts/header.jsp"/>

        <div class="content">
            <div class="container-panel">
                <c:choose>
                    <c:when test="${customer.activated}">
                        <button onclick="abonentAction.deactivate(${customer.id})">Deactivate abonent</button>
                    </c:when>
                    <c:otherwise>
                        <button onclick="abonentAction.activate(${customer.id})">Activate abonent</button>
                    </c:otherwise>
                </c:choose>

                <div class="btn-message" id="message"></div>
            </div>

            <div class="panel">
                <div class="message">Abonent services</div>
                <div class="panel-block">
                    <%--suppress HtmlFormInputWithoutLabel --%>
                    <select id="serviceElem" class="serviceList">
                        <c:forEach var="service" items="${serviceList}">
                            <option value=${service.id}>${service.name}</option>
                        </c:forEach>
                        <option></option>
                    </select>
                    <button onclick="abonentAction.addService('INFO')">Add</button>
                </div>

                <table id="serviceTable">
                    <thead>
                        <tr>
                            <th>Service</th>
                            <th>Date activated</th>
                            <th>Date deactivated</th>
                            <th>Payment</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${empty customer.products}">
                                <tr id="serviceTableLineNull">
                                    <td>#</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="productItem" items="${customer.products}">
                                    <tr>
                                        <td>${productItem.name}</td>
                                        <td>${productItem.dateActivated}</td>
                                        <td>${productItem.dateDeactivated}</td>
                                        <td>${productItem.product.payment}</td>
                                        <td><button onclick="">deactivate</button></td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>

                    </tbody>
                </table>
            </div>
        </div>


        <jsp:include page="../parts/footer.jsp"/>
    </div>
</body>
</html>
