
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<header>
    <div class="header-name">CityMobile</div>
    <div class="header-logo"></div>

    <div class="search" style="width: 600px;float: right;margin-right: 25%;">
        <input type="text" id="complete-field"
               name="searchParam" value="Поиск..."
               class="search-field"
               style="width: 100%;height: 20px;background: white;border: solid 1px;border-color: lightgrey;"
               onfocus="if(this.value == 'Поиск...') { this.value = ''; }"
               onblur="if(this.value == '') { this.value = 'Поиск...'; }"
               onkeyup="search.doCompletion(this.value)">
        <table id="complete-table" class="popupBox"></table>
    </div>

    <div class="header-menu">
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <div class="welcome">
                    <a href="<c:url value="/manager/console"/>">
                        WELCOME ${sessionScope.user.name}!
                    </a>
                </div>
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
