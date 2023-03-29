<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
                    <td>${item.price} kr.</td>
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
                <td><b>Total pris: ${sessionScope.user.currentOrder.shoppingCart.totalPrice} kr.</b></td>
                <td></td>
            </tr>
        </table>

        <div class="row">
            <div class="col-sm-3">
                <a href="ToOrder">
                    <br>
                    <button class="btn btn-primary">Bestil flere cupcakes</button>
                </a>
            </div>
            <c:if test="${sessionScope.user.getCurrentOrder().getShoppingCart().getTotalAmount() > 0}">
                <div class="col-sm-9">
                    <form action="CreateOrder" method="post">
                        <div class="row">
                            <c:if test="${requestScope.msgReadyTime != null}">
                                <div class="col-sm-3">
                                    <h3 class="bg-danger">${requestScope.msgReadyTime}</h3>
                                </div>
                            </c:if>
                            <div class="col-sm-3">
                                <label for="readyTimeDate">Afhentningstid:

                                </label>
                                <input type="date" name="readyTimeDate" id="readyTimeDate" class="form-control"></div>
                            <div class="col-sm-2">
                                <label for="readyTimeHour">Time:

                                </label>
                                <input type="number" min="0" max="23" name="readyTimeHour" id="readyTimeHour" class="form-control"></div>
                            <div class="col-sm-2">
                                <label for="readyTimeMinute">Minut:

                                </label>
                                <input type="number" min="0" max="59" name="readyTimeMinute" id="readyTimeMinute" class="form-control"></div>
                            <div class="col-sm-2">
                                <br>
                                <button class="btn btn-primary form-control">Bekræft Ordre</button>
                            </div>
                        </div>
                    </form>
                </div>
            </c:if>
        </div>
    </jsp:body>

</t:pagetemplate>