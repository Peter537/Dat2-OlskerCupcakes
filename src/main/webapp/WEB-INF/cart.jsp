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
        <h3 class="bg-danger">${requestScope.msgmoney}</h3>
    </jsp:attribute>

    <jsp:attribute name="footer">
        Indkøbskurv
    </jsp:attribute>

    <jsp:body>
        <table class="table table-striped table-bordered table-hover">
            <tr>
                <th>Bund</th>
                <th>Topping</th>
                <th>Total pris</th>
                <th></th>
            </tr>
            <c:forEach items="${sessionScope.user.currentOrder.shoppingCart.cupcakeList}" var="item">
                <tr>
                    <td>${item.bottom.name}</td>
                    <td>${item.top.name}</td>
                    <td>${item.price} DKK</td>
                    <td>
                        <form action="RemoveCupcake" method="post">
                            <input type="hidden" name="cupcake" id="cupcake" value="${item.id}" class="form-control">
                            <input type="submit" value="Aflys" class="btn btn-primary" style="float: right;">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>
                <td><b>Total pris: ${sessionScope.user.currentOrder.shoppingCart.totalPrice} DKK</b></td>
                <td></td>
            </tr>
        </table>

        <div class="row">
            <div class="col-sm-3">
                <label for="readyTimeDate">Afhentningstid:
                    <input type="date" id="readyTimeDate" name="readyTimeDate">
                </label>
            </div>
            <div class="col-sm-2">
                <label for="readyTimeHour">Time:
                    <input type="number" min="0" max="23" id="readyTimeHour" name="readyTimeHour">
                </label>
            </div>
            <div class="col-sm-2">
                <label for="readyTimeMinute">Minut:
                    <input type="number" min="0" max="59" id="readyTimeMinute" name="readyTimeMinute">
                </label>
            </div>
        </div>
        <br>

        <div class="row">
            <div class="col-sm-3">
                <a
                        href="ToOrder">
                    <button class="btn btn-primary">Bestil flere cupcakes</button>
                </a>
            </div>
            <div class="col-sm-3">
                <form action="CreateOrder" method="post">
                    <button class="btn btn-primary">Bekræft Ordre</button>
                </form>
            </div>
        </div>
    </jsp:body>

</t:pagetemplate>