<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="head">
        Bekræftelse
    </jsp:attribute>

    <jsp:attribute name="header">
        <h1>Order bekræftelse</h1>
    </jsp:attribute>

  <jsp:attribute name="footer">
        Bekræftelse
    </jsp:attribute>

  <jsp:body>
      <br>
      <div class="row">
          <p>Dine cupcakes er bestilt!</p>
      </div>
  </jsp:body>

</t:pagetemplate>