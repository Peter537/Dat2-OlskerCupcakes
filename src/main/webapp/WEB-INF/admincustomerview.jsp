<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--<!-- <%@page errorPage="error.jsp" isErrorPage="false" %> -->--%>

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
        <div class="row">
            <div class="col-sm-12">
                <table class="table">

                </table>
            </div>
        </div>
    </jsp:body>
</t:pagetemplate>