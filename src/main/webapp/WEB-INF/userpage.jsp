<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="head">
        Olsker Cupcakes
    </jsp:attribute>
    <jsp:attribute name="header">
        <h1>Velkommen tilbage!</h1>
    </jsp:attribute>

    <jsp:body>
        <c:if test="${empty sessionScope.user}">
            <c:redirect url="/login.jsp"/>
        </c:if>

        <div class="row" style="height: 500px">
            <div class="item-box" id="status">
                <h2>Sidste ordre status:</h2>
                <h4> - ${sessionScope.user.getLastOrderStatus().getName()}</h4>
                <br>
                <br>
                <br>
                <h2>Medlemskab:</h2>
                <h4> - ${sessionScope.user.getRole().getName()}</h4>
            </div>
            <div class="item-box" id="money" style="width: 30%">
                <h2 style="text-align: center; text-decoration-line: underline">Tilføj penge</h2>
                <form action="AddMoney" method="post" style="text-align: center">
                    <input class="btn btn-primary" type="submit" value="Tilføj penge">
                    <label for="addedAmount">  -->
                        <input type="text" id="addedAmount" name="addedAmount" placeholder="Tilføj penge">
                    </label>
                </form>
                <br>
                <h2>Saldo:</h2>
                <h4>${sessionScope.user.balance} DKK</h4>
                <br>

            </div>
            <div class="item-box" id="account">
                <h2 style="text-align: center; text-decoration-line: underline">Konto information</h2>
                <div class="row">
                    <div class="col-sm-2">
                        <label>Email: </label>
                    </div>
                    <div class="col-sm-10">
                        <label style="text-decoration-line: underline; text-decoration-thickness: 1px">${sessionScope.user.email}</label>
                    </div>
                </div>
                <br>
                <div>
                    <a class="btn btn-primary" style="box-sizing: border-box; width: 100%; font-size: 25px;" href="ToChangePassword">Skift adgangskode</a>
                </div>
                <br>
                <div>
                    <a class="btn btn-primary" style="box-sizing: border-box; width: 100%; font-size: 30px;" href="ToMyOrders">Mine ordre</a>
                </div>
            </div>
            <div style="text-align: center; margin: 5px">
                <a class="btn btn-primary" href="ToOrder" style="box-sizing: border-box; width: 100%; font-size: 38px">Bestil cupcakes</a>
            </div>
        </div>
        <style>
            .item-box {
                border: 1px black solid;
                border-radius: 25px;
                width: 25%;
                margin: 40px;
                /*background-color: lightgray;*/
                box-shadow: 4px 4px 4px 0 black;
            }
        </style>

    </jsp:body>
</t:pagetemplate>