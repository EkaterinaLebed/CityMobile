
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--${pageContext.request.contextPath}--%>

<html>
  <head>
      <meta charset="UTF-8">
      <title>CityMobile || Telephone, Internet and TV Provider</title>
      <link rel="stylesheet"  href="resources/css/main.css"/>

      <link rel="stylesheet" href="http://www.citywest.ca/dev/themes/css/reset.css">
      <link rel="stylesheet" href="http://www.citywest.ca/dev/themes/css/primary-feb-2017-4.css?v=1.8">
      <link rel="stylesheet" href="http://www.citywest.ca/dev/themes/js/thickbox/thickbox.css" />
  </head>

  <body>
    <div id="container">
        <jsp:include page="header.jsp"/>
        <jsp:include page="navigation.jsp"/>

        <div id="contentarea" class="centered" role="main">
            <c:forEach var="service" items="${serviceList}">
                <div class="box col2 productFeature">
                    <img src="/citymobile/resources/icons/product/busphone2_100_100_c1.jpg">
                    <div class="innerWrap">
                        <h3><a href="<c:url value='/service?id=${service.id}'/>">${service.name}</a></h3>
                        <p>${service.description}<br>
                        <a href="<c:url value="/service?id=${service.id}"/>">Learn More »</a></p>
                    </div>
                </div>
            </c:forEach>
        </div>

        </div>
    </div>
  </body>
</html>
