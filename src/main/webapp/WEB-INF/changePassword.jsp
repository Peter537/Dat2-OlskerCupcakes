<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>

    <jsp:attribute name="head">
        Skift adgangskode
    </jsp:attribute>
    <jsp:attribute name="header">
        <h1>Skift adgangskode</h1>
    </jsp:attribute>

    <jsp:attribute name="footer">
        Skift adgangskode
    </jsp:attribute>

    <jsp:body>
        <c:if test="${empty sessionScope.user}">
            <c:redirect url="/login.jsp"/>
        </c:if>

        ${requestScope.changePasswordError}
        <form action="ChangePassword" method="post">
            <label for="newPassword">Skift adgangskode
                <input type="text" id="newPassword" name="newPassword" placeholder="New password"><br>
            </label>
            <input class="btn btn-primary" type="submit" value="Skift adgangskode"><br>
        </form>
    </jsp:body>
</t:pagetemplate>