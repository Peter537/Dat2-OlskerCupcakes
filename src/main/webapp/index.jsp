<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="head">
        Velkommen
    </jsp:attribute>

    <jsp:attribute name="header">
        <h1>Velkommen til Olsker Cupcakes</h1>
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

            <p>
            <h5 style="text-align: center;">Hos Olsker Cupcakes kan du købe cupcakes alle dage på ugen til Danmarks bedste pris.
                Vi har altid friske cupcakes på lager, og vi har altid en masse forskellige smage.
                Har du en specifik smag i tankerne, kan du bygge din helt egen cupcake med dine favorit smage.</h5>
            <div class="row">

                <div class="col-sm-5">

                </div>

                <div class="col-sm-2">
                    <a href="login.jsp">
                        <br>
                        <br>
                        <button class="btn btn-primary" style="width: 200px;height: 100px;font-size: 35px;font-weight: bold;text-align: center;">Log-in</button>
                        <br>
                        <br>
                    </a>
                </div>
            </div>

            </p>

            <div class="row">

                <div class="col-sm-5">

                </div>

                <div class="col-sm-4">
                Ikke allerede medlem?
                <a href="createuser.jsp">Opret dig her!</a>
            </div>

            </div>
        </c:if>

    </jsp:body>

</t:pagetemplate>