<%--
  Created by IntelliJ IDEA.
  User: magnu
  Date: 29/03/2023
  Time: 16.57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="icon" href="<%=request.getContextPath()%>/images/logocupcake.png" type="image/png" sizes="16x16">
<body>
    <div class="col-sm-5" style="margin-left: 2%;margin-top: 2%;">
        <form action="AddToCart" method="post" target="_self">
            <div class="row">
                <div class="col-sm-2"></div>
                <div class="col-sm-6 text-center">
                    <a id="msgA" class="bg-warning h3" href="ToCart">${requestScope.msg}</a>
                </div>
            </div>
            <div class="row">

                <div class="col-sm-12 border rounded">

                    <div class="row">
                        <c:if test="${sessionScope.cupcake != null}">
                            <p>Topping pris: ${sessionScope.cupcake.getTop().getPrice()} kr.</p>
                            <p>Bund pris: ${sessionScope.cupcake.getBottom().getPrice()} kr.</p>
                            <p>Pris: ${sessionScope.cupcake.getPrice()} kr.</p>
                        </c:if>
                    </div>

                    <div class="row">
                        <c:if test="${sessionScope.cupcake != null}">
                            <input type="submit" class="btn btn-primary" formaction="AddToCart" formmethod="post" value="Tilføj til kurv">
                        </c:if>
                    </div>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
