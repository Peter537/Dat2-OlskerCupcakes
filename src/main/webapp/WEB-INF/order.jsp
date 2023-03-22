<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--<%@page errorPage="error.jsp" isErrorPage="false" %>--%>

<t:pagetemplate>
    <jsp:attribute name="head">
        Bestil
    </jsp:attribute>

    <jsp:attribute name="header">
        <h1>Tilføj til kurv</h1>
    </jsp:attribute>

    <jsp:attribute name="footer">
        Bestil
    </jsp:attribute>

    <jsp:body>
        <c:if test="${empty sessionScope.user}">
            <c:redirect url="../login.jsp"/>
        </c:if>
        <div class="row">
            <form action="BuildCupcake" method="post">
                <label for="toppingID">Topping</label>
                <select name="toppingID" id="toppingID" required>
                    <option disabled selected value> -- vælg en topping -- </option>
                    <c:forEach var="item" items="${applicationScope.toppings}">
                        <c:choose>
                            <c:when test="${requestScope.topping != null && requestScope.topping.id == item.id}">
                                <option selected value="${item.id}">${item.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${item.id}">${item.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <label for="bottomID">Bund</label>
                <select name="bottomID" id="bottomID" required>
                    <option disabled selected value> -- vælg en bund -- </option>
                    <c:forEach var="item" items="${applicationScope.bottoms}">
                        <c:choose>
                            <c:when test="${requestScope.bottom != null && requestScope.bottom.id == item.id}">
                                <option selected value="${item.id}">${item.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${item.id}">${item.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <input type="submit" value="Vælg">
                <c:if test="${requestScope.cupcake != null}">
                    <input type="submit" formaction="CreateOrder" formmethod="post" value="Tilføj til kurv">
                </c:if>
            </form>
        </div>
        <div class="row">
            <c:if test="${requestScope.cupcake != null}">
                <p>Topping pris: ${requestScope.topping}</p>
                <p>Bund pris: ${requestScope.bottom}</p>
                <p>Pris: ${requestScope.cupcake.getPrice()} kr.</p>
            </c:if>
        </div>
    </jsp:body>

</t:pagetemplate>