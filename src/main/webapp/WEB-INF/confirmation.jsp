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
      <div class="row">
          <p>Data om din order: </p>
          <div class="col-sm-6">
              <table class="table">
                  <tr>
                      <td>Top: </td>
                      <td>Bund: </td>
                      <td>Pris: </td>
                  </tr>
                    <c:forEach items="${sessionScope.user.getShoppingCart().getCupcakeList()}" var="cupcake">
                        <tr>
                            <td>${cupcake.top.getName()}</td>
                            <td>${cupcake.bottom.getName()}</td>
                            <td>${cupcake.price} .kr</td>
                        </tr>
                    </c:forEach>
                  <tr>
                        <td>Kan afhentes: ${requestScope.order.getReadyTime()}</td>
                        <td></td>
                        <td><b>Total: ${sessionScope.user.getShoppingCart().getTotalPrice()} .kr</b></td>
                  </tr>
              </table>
          </div>
          <div class="col-sm-6">
              <br><br><br><br><br><br><br>
              <div class="row">
                  <div class="col-sm-12">
                      <a class="btn btn-primary" href="ToOrder">
                          Lav en ny order
                      </a>
                  </div>
              </div>
          </div>
      </div>
<%--      <div class="row">--%>
<%--          <div class="col-sm-7"></div>--%>
<%--          <div class="col-sm-5">--%>

<%--          </div>--%>
<%--      </div>--%>
  </jsp:body>

</t:pagetemplate>