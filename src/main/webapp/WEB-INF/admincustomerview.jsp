<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="head">
        ${requestScope.chosenuser.getEmail()}
    </jsp:attribute>

    <jsp:attribute name="header">
        <h1>Overblik over ${requestScope.chosenuser.getEmail()}</h1>
    </jsp:attribute>

    <jsp:attribute name="footer">
        ${requestScope.chosenuser.getEmail()}
    </jsp:attribute>

    <jsp:body>
        <c:if test="${empty sessionScope.user || empty requestScope.chosenuser}">
            <c:redirect url="/login.jsp"/>
        </c:if>
        <br>
        <a class="btn btn-primary" href="ToUserpage">Gå tilbage</a>
        <br>
        <br>
        <div class="row">
            <div class="col-sm-12">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Ordre nr.</th>
                        <th>Pris</th>
                        <th>Antal cupcakes</th>
                        <th>Status</th>
                        <th>Leverings tidspunk</th>
                        <th>Aflys</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.userorders}" var="order">
                            <tr>
                                <td>${order.getId()}</td>
                                <td>${order.getPrice()}</td>
                                <td>${order.getCupcakeCount()}</td>
                                <td>${order.getStatus()}</td>
                                <td>${order.getFormattedReadyTime()}</td>
                                <td>
                                    <form action="CancelOrder" method="post">
                                        <input type="hidden" readonly="readonly" name="orderId" id="orderId" value="${order.getId()}" class="form-control">
                                        <input type="submit" value="Aflys" class="btn btn-primary" style="float: right;">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

    </jsp:body>

</t:pagetemplate>