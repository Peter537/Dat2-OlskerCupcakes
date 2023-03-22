<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- <%@page errorPage="error.jsp" isErrorPage="false" %> -->

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
            <div class="col-sm-12">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Order nr.</th>
                            <th>Bruger</th>
                            <th>Pris</th>
                            <th>Leverings tidspunk</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${sessionScope.orders}" var="order">
                            <tr>
                                <td>${order.id}</td>
                                <td>${order.user.username}</td>
                                <td>${order.price}</td>
                                <td>${order.deliveryTime}</td>
                                <td>${order.status}</td>
                            </tr>
                        </c:forEach>
                </table>
            </div>
        </div>
    </jsp:body>
</t:pagetemplate>