
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>[abonentInfo]</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"/>
    <script type="text/javascript" src="<c:url value="/resources/script/main.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/script/abonentActions.js"/>"></script>
    <script type="text/javascript"> document.customer={id:${customer.id}};</script>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
    <div id="container">
        <jsp:include page="../parts/header.jsp"/>

        <div class="content">
            <div class="container-panel">
                <c:choose>
                    <c:when test="${customer.activated}">
                        <button id="btnActivateSwitch" onclick="abonentAction.deactivate(${customer.id})">
                            Deactivate abonent
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button id="btnActivateSwitch" onclick="abonentAction.activate(${customer.id})">
                            Activate abonent
                        </button>
                    </c:otherwise>
                </c:choose>
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

                <table class="table-stlT2" id="serviceTable">
                    <thead>
                        <tr>
                            <th class="col1">Service</th>
                            <th class="col2">Date <br>activated</th>
                            <th class="col3">Date <br>deactivated</th>
                            <th class="col4">Payment</th>
                            <th class="col5"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${empty customer.products}">
                                <tr id="serviceTableLineNull">
                                    <td>#</td><td></td><td></td><td></td><td></td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="productItem" items="${customer.products}">
                                    <tr id="prod${productItem.id}">
                                        <td>${productItem.name}</td>
                                        <td><fmt:formatDate value='${productItem.dateActivated}' type='date' pattern='dd.MM.yyyy'/></td>
                                        <td class="date-deactive"><fmt:formatDate value='${productItem.dateDeactivated}' type='date' pattern='dd.MM.yyyy'/></td>
                                        <td><fmt:formatNumber value='${productItem.product.payment}' pattern='0.0' minFractionDigits='2' maxFractionDigits='2'/></td>
                                        <td class='tb-action'><button class="deactivate" onclick="abonentAction.deactivateService(${productItem.id})">deactivate</button></td>
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
