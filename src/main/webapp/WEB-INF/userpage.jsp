<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>

    <jsp:attribute name="head">

        MinSide

    </jsp:attribute>
    <jsp:attribute name="header">
        <h1>Log-ind</h1>
    </jsp:attribute>

    <jsp:attribute name="footer">
        Log-ind
    </jsp:attribute>

    <jsp:body>

       <div class="row">
           <div class="col-md-6">
                   <a class="btn btn-primary" href="/ToMyOrders">Mine ordre</a>
           </div>
              <div class="col-md-6">
                <a class="btn btn-primary" href="ToOrder">Bestil cupcakes</a>
              </div>
       </div>




    </jsp:body>





</t:pagetemplate>