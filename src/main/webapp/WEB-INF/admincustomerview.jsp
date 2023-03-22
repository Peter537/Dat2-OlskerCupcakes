<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--<!-- <%@page errorPage="error.jsp" isErrorPage="false" %> -->--%>

<t:pagetemplate>
    <jsp:attribute name="head">
        ${requestScope.chosenuser}
    </jsp:attribute>

    <jsp:attribute name="header">
        <h1>Overblik over ${requestScope.chosenuser}</h1>
    </jsp:attribute>

    <jsp:attribute name="footer">
        ${requestScope.chosenuser}
    </jsp:attribute>

    <jsp:body>
        <div class="row">
            <div class="col-sm-12">

            </div>
        </div>
    </jsp:body>
</t:pagetemplate>