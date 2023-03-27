<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="head">
        Bekræftelse
    </jsp:attribute>

    <jsp:attribute name="header">
        <h1>Ordre bekræftelse</h1>
    </jsp:attribute>

    <jsp:attribute name="footer">
        Bekræftelse
    </jsp:attribute>

    <jsp:body>
        <c:if test="${empty sessionScope.user}">
            <c:redirect url="/login.jsp"/>
        </c:if>
        <br>
        <div class="row">
            <p>Dine cupcakes er bestilt!</p>
        </div>
        <div class="row">
            <h4>Ordreinformation: </h4>
            <div class="col-sm-6">
                <table class="table">
                    <tr>
                        <td>Top:</td>
                        <td>Bund:</td>
                        <td>Pris:</td>
                    </tr>
                    <c:forEach items="${requestScope.currentcart.getCupcakeList()}" var="cupcake">
                        <tr>
                            <td>${cupcake.top.getName()}</td>
                            <td>${cupcake.bottom.getName()}</td>
                            <td>${cupcake.price} .kr</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td>Kan afhentes: ${requestScope.readyTime}</td>
                        <td></td>
                        <td><b>Total: ${requestScope.currentcart.getTotalPrice()} .kr</b></td>
                    </tr>
                </table>
            </div>
            <div class="col-sm-6">
                <br><br><br><br><br><br><br>
                <div class="row">
                    <div class="col-sm-12">
                        <a class="btn btn-primary" href="ToOrder">
                            Lav en ny ordre
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>

</t:pagetemplate>