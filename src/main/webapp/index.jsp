<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="head">
        Velkommen
    </jsp:attribute>

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
                <a href="ToUserpage">
                    <button class="btn btn-primary">Min side</button>
                </a>
            </div>

            </p>
        </c:if>

        <c:if test="${sessionScope.user == null}">
            <p>For at fortsætte din bestiling, skal du logge ind.
                <div class="row">
                    <div class="col-sm-3">
                    <a
                        href="login.jsp">
                    <button class="btn btn-primary">Login</button>
                    </a>
                    </div>
                    <div class="col-sm-3">
                    <a href="createuser.jsp">
                    <button class="btn btn-primary">Register</button>
                    </a>
                    </div>
                </div>
            </p>
        </c:if>

    </jsp:body>

</t:pagetemplate>