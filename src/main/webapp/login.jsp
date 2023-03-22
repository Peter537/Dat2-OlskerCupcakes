<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="head">
        Log-ind
    </jsp:attribute>

    <jsp:attribute name="header">
        <h1>Log-ind</h1>
    </jsp:attribute>

    <jsp:attribute name="footer">
        Log-ind
    </jsp:attribute>

    <jsp:body>

        <div>
            <h3>Indtast dit brugernavn og password</h3>
            <div class="row">
                <div class="col-sm-4">
                <form action="login" method="post">
                    <div class="form-group">
                        <label for="username">Username: </label>
                        <input class="form-control" type="text" id="username" name="username" placeholder="Brugernavn"/>
                    </div>
                    <br/>
                    <div class="form-group">
                        <label for="password">Password: </label>
                        <input class="form-control" type="password" id="password" name="password" placeholder="Password"/>
                    </div>
                    <br/>
                    <div class="form-group">
                        <input class="btn btn-primary" type="submit" value="Log-ind" style="width: 100%;"/>
                    </div>
                </form>
                </div>
                <div class="col-sm-8"></div>
            </div>
        </div>
    </jsp:body>

</t:pagetemplate>