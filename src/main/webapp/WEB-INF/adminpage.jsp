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
        <c:if test="${empty sessionScope.user}">
            <c:redirect url="/login.jsp"/>
        </c:if>
        <div class="row">
            <form action="UpdateOrdersList" method="post">
                <input type="submit" class="btn btn-primary" value="Opdater liste">
            </form>
        </div>
        <br>
        <div class="row">
            <div class="col-sm-9">
                <h2>Ordrer</h2>
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
                                <td>${order.getPrice()} kr.</td>
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
            <div class="col-sm-3">
                <h2>Brugere</h2>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Bruger</th>
                            <th>Role</th>
                            <th>Balance</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${sessionScope.users}" var="user">
                            <tr>
                                <td>
                                    <form action="AdminUserOverview" method="post">
                                        <input type="submit" class="form-control" name="chosenuser" id="chosenuser" value="${user.getEmail()}">
                                    </form>
                                </td>
                                <td>${user.getRole()}</td>
                                <td>${user.getBalance()} kr.</td>
                                <td>
                                    <form action="AddMoneyById" method="post">
                                        <input type="hidden" readonly="readonly" name="userEmail" id="userEmail" value="${user.getEmail()}" class="form-control">
                                        <input type="number" name="amount" id="amount" value="0" class="form-control">
                                        <input type="submit" value="Tilføj" class="btn btn-primary" style="float: right;">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                </table>
            </div>
        </div>
    </jsp:body>
</t:pagetemplate>