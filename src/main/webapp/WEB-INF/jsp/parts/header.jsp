
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<header>
    <div class="header-name">CityMobile</div>
    <div class="header-logo"></div>

    <div class="header-menu">
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <div class="welcome">WELCOME "${sessionScope.user.name}"!</div>
                <div class="item">
                    <a href="<c:url value="/logout"/>">
                        <div class="btn logout-btn">LOGOUT</div>
                    </a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="item">
                    <a href="<c:url value="/register"/>">
                        <div class="btn"><span class="lined">REGISTER</span></div>
                    </a>
                </div>
                <div class="item">
                    <a href="<c:url value="/login"/>">
                        <div class="btn login-btn">LOGIN</div>
                    </a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="header-intro">
        Telephone, Internet and TV Provider
    </div>
</header>
