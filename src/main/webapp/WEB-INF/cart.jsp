<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="head">
        Indkøbskurv
    </jsp:attribute>

    <jsp:attribute name="header">
        <h1>Indkøbskurv</h1>
    </jsp:attribute>

    <jsp:attribute name="footer">
        Indkøbskurv
    </jsp:attribute>

    <jsp:body>
        <c:out value="${sessionScope.user.getShoppingCart().getCupcakeList()}" />
        <table>
            <tr>
                <th>Bund</th>
                <th>Topping</th>
                <th>Pris</th>
            </tr>
            <c:forEach items="${sessionScope.user.getShoppingCart().getCupcakeList()}" var="item">
                <tr>
                    <td>${item.getBund()}</td>
                    <td>${item.getTop()}</td>
                    <td>${item.getPrice()}</td>
                </tr>
            </c:forEach>
        </table>
    </jsp:body>

</t:pagetemplate>