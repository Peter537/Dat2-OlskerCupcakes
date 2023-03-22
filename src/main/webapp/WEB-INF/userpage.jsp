<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>

    <jsp:attribute name="head">

        MinSide

    </jsp:attribute>
    <jsp:attribute name="header">
        <h1>Velkommen tilbage, ${sessionScope.user.email}!</h1>
    </jsp:attribute>

    <jsp:attribute name="footer">
        Din side
    </jsp:attribute>

    <jsp:body>
        <c:if test="${empty sessionScope.user}">
            <c:redirect url="/login.jsp"/>
        </c:if>

        <div class="row">
            <div class="col-sm-4 border rounded" id="status">
                <p>Sidste ordre status:</p>
                <p>${sessionScope.user.getLastOrderStatus()}</p>
                <p></p>
                <p>Medlemskab:</p>
                <p>${sessionScope.user.getRole()}</p>
            </div>
            <div class="col-sm-4 border rounded" id="money">
                <p>Balance</p>
                <p>${sessionScope.user.balance}</p>
                <div class="col-md-6">
                    <a class="btn btn-primary" href="ToOrder">Bestil cupcakes</a>
                </div>
                <br>
                <form action="AddMoney" method="get">
                    <label for="addedAmount">
                        <input type="text" id="addedAmount" name="addedAmount" placeholder="Tilføj penge">
                    </label>
                    <input class="btn btn-primary" type="submit" value="Tilføj penge">
                </form>
            </div>
            <div class="col-sm-4 border rounded" id="account">
                <div class="col-md-6">
                    <a class="btn btn-primary" href="ToMyOrders">Mine ordre</a>
                </div>
                <div class="col-md-6">
                    <a class="btn btn-primary" href="ToChangePassword">Skift adgangskode</a>
                </div>
            </div>
        </div>

        <!--
        // TODO: Udkommenteret, men skal slettes
        <div class="row">
            <div class="col-md-6">
                <a class="btn btn-primary" href="ToMyOrders">Mine ordre</a>
            </div>
            <div class="col-md-6">
                <a class="btn btn-primary" href="ToOrder">Bestil cupcakes</a>
            </div>
        </div>
        -->

        <!--
        // TODO: Skal ind i en ny jsp side
        <form action="AddMoney" method="post">
            <label for="addedAmount">Tilføj penge
                <input type="text" id="addedAmount" name="addedAmount" placeholder="Tilføj penge"><br>
            </label>
            <input class="btn btn-primary" type="submit" value="Tilføj penge">
        </form>

        // TODO: Skal ind i en ny jsp side
        <form action="ChangePassword" method="post">
            <label for="newPassword">Skift adgangskode
                <input type="text" id="newPassword" name="newPassword" placeholder="New password"><br>
            </label>
            <input class="btn btn-primary" type="submit" value="Skift adgangskode"><br>
        </form>
        -->
    </jsp:body>


</t:pagetemplate>