<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--<%@page errorPage="error.jsp" isErrorPage="false" %>--%>

<t:pagetemplate>
    <jsp:attribute name="head">
        Mine ordrer
    </jsp:attribute>

    <jsp:attribute name="header">
        <h1>Mine Ordrer</h1>
    </jsp:attribute>

    <jsp:attribute name="footer">
        Se dine ordrer her
    </jsp:attribute>

    <jsp:body>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Afhentningstid</th>
                <th>Ordre status</th>
                <th>Pris</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${sessionScope.user.getOrders()}">
                <tr>

                    <td>${order.getFormattedReadyTime()}</td>
                    <td>${order.getStatus()}</td>
                    <td>${order.getPrice()} kr.</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${empty sessionScope.user.getOrders()}">
            <p>Du har ingen ordrer</p>
        </c:if>
    </jsp:body>

</t:pagetemplate>