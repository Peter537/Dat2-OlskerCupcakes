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
        <h1 style="margin-left: 25%;">Tilføj til kurv</h1>
    </jsp:attribute>

    <jsp:attribute name="footer">
        Bestil
    </jsp:attribute>

    <jsp:body>
        <c:if test="${empty sessionScope.user}">
            <c:redirect url="/login.jsp"/>
        </c:if>
        <br>
        <form action="BuildCupcake" method="post" target="iframe">
            <div class="row">
                <div class="col-sm-1"></div>
                <div class="col-sm-6">
                    <div class="row">
                        <div class="col-sm-6 text-center" style="font-size: 25px;">
                        <label for="toppingID" class="form-group">Topping</label>
                        <select name="toppingID" id="toppingID" class="form-select" required>
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
                        </div>
                        <div class="col-sm-6 text-center" style="font-size: 25px;">
                            <label for="bottomID">Bund</label>
                            <select name="bottomID" id="bottomID" class="form-select" required>
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
                        </div>
                    </div>
                    <br>
                    <br>
                    <div class="row text-center">
                        <div class="col-sm-4"></div>
                        <div class="co-sm-4">
                            <input type="submit" value="Vælg" class="btn btn-primary btn-lg">
                        </div>
                        <div class="col-sm-4"></div>
                    </div>
                </div>
                <div class="col-sm-5">


                                <iframe name="iframe" src="CalculatePrice" sandbox="allow-forms" onload="this.style.height=(this.contentWindow.document.body.scrollHeight+20)+'px';" style="height: 300px;width: 600px;margin-left: 2%;">
                                </iframe>
                </div>
            </div>
        </form>
        <style>
            #msgA {
                border-radius: 8px 8px 8px 8px;
                color: black;
                text-decoration: none !important;
            }
        </style>
    </jsp:body>

</t:pagetemplate>