<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--<%@page errorPage="error.jsp" isErrorPage="false" %>--%>

<t:pagetemplate>
    <jsp:attribute name="head">
        Mine ordre
    </jsp:attribute>

    <jsp:attribute name="header">
        <h1>Mine Ordre</h1>
    </jsp:attribute>

    <jsp:attribute name="footer">
        Se dine ordre her
    </jsp:attribute>

    <jsp:body>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Flavors</th>
                <th>ReadyTime</th>
                <th>Ordre status</th>
                <th>Price</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${sessionScope.user.getOrders()}">
                <tr>

                    <td>${order.getId()}</td>
                    <td>${order.getStatus()}</td>
                    <td>${order.getPrice()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${empty sessionScope.user.getOrders()}">
            <p>Du har ingen ordre</p>
        </c:if>

    </jsp:body>

</t:pagetemplate>