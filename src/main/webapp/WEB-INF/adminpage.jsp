<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--<!-- <%@page errorPage="error.jsp" isErrorPage="false" %> -->--%>

<t:pagetemplate>
    <jsp:attribute name="head">
        Admin
    </jsp:attribute>

    <jsp:attribute name="header">
        <h1>Overblik</h1>
    </jsp:attribute>

    <jsp:attribute name="footer">
        Admin
    </jsp:attribute>

    <jsp:body>
        <div class="row">
            <form action="UpdateOrdersList" method="post">
                <input type="submit" class="btn btn-primary" value="Opdater liste">
            </form>
            <br>
            <div class="col-sm-9">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Order nr.</th>
                            <th>Bruger</th>
                            <th>Pris</th>
                            <th>Antal cupcakes</th>
                            <th>Status</th>
                            <th>Leverings tidspunk</th>
                            <th>Aflys</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${sessionScope.orders}" var="order">
                            <tr>
                                <td>${order.getId()}</td>
                                <td>${order.getUser().getEmail()}</td>
                                <td>${order.getPrice()}</td>
                                <td>${order.getCupcakeCount()}</td>
                                <td>${order.getStatus()}</td>
                                <td>${order.getReadyTime()}</td>
                                <td>
                                    <form action="CancelOrder" method="post">
                                        <input type="hidden" readonly="readonly" name="orderId" id="orderId" value="${order.getId()}" class="form-control">
                                        <input type="submit" value="Aflys" class="btn btn-primary" style="float: right;">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                </table>
            </div>
        </div>
    </jsp:body>
</t:pagetemplate>