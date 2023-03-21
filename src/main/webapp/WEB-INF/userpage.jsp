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
               <a class="buttons" href="ToMyOrders">Mine ordrer</a>
           </div>
              <div class="col-md-6">
                <a class="buttons" href="ToOrder">Bestil cupcakes</a>
              </div>
       </div>

        <style>
            buttons{
                background-color: #f44336;
                border: none;
                color: white;
                padding: 15px 32px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
            }


        </style>




    </jsp:body>





</t:pagetemplate>