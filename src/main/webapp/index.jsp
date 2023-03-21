<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Velkommen til Olsker Cupcakes
    </jsp:attribute>

    <jsp:attribute name="footer">
        Olsker Cupcakes
    </jsp:attribute>

    <jsp:body>

        <c:if test="${sessionScope.user != null}">
            <p>Du er logget ind som "${sessionScope.user.role}".
                Gå til din side.
            <div class="container">
                <a href="user.jsp">
                    <button type ="button">Min side</button>
                </a>
            </div>

            </p>
        </c:if>

        <c:if test="${sessionScope.user == null}">
            <p>For at fortsætte din bestiling, skal du logge ind.
                <div class="container">
                <a
                    href="login.jsp">
                <button type="button">Login</button>
                </a>
                <a href="register.jsp">
                <button type="button">Register</button>
                </a>
                </div>

            </p>
        </c:if>

    </jsp:body>

</t:pagetemplate>