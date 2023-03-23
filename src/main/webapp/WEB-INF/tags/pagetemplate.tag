<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="head" fragment="true" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE html>
<html lang="da">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><jsp:invoke fragment="head"/></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="icon" href="<%=request.getContextPath()%>/images/logocupcake.png" type="image/png" sizes="16x16">
</head>
<body>
<div style="background: var(--hero-color)">
    <a class="navbar-brand text-center" href="index.jsp" style="width: calc(var(--header-height)*var(--header-img-multi));">
        <img src="${pageContext.request.contextPath}/images/cupcake_banner.png" width="400px;" class="img-fluid hero-img"/>
    </a>
</div>
<header class="header" style="background: var(--hero-navbar)">
    <nav class="navbar navbar-expand-lg" style="margin-top: -1%;">
        <div class="container">
            <a class="justify-content-start" href="index.jsp">
                <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo" width="100px" >
            </a>
<%--            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"--%>
<%--                    aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">--%>
<%--                <span class="navbar-toggler-icon"></span>--%>
<%--            </button>--%>
            <div class="collapse navbar-collapse justify-content-start" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-item nav-link navtext" href="ToUserpage">Min side</a>
                    <a class="nav-item nav-link navtext" href="ToMyOrders">Mine ordre</a>
                    <a class="nav-item nav-link navtext" href="ToOrder">Bestil</a>
                    <c:if test="${sessionScope.user == null }">
                        <a class="nav-item nav-link navtext" href="${pageContext.request.contextPath}/login.jsp">Login</a>
                    </c:if>
                    <c:if test="${sessionScope.user != null }">
                        <a class="nav-item nav-link navtext" href="${pageContext.request.contextPath}/logout">Log out</a>
                    </c:if>
                </div>
            </div>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNavAltMarkup2">
                <div class="navbar-nav">
                    <c:if test="${sessionScope.user == null }">
                    </c:if>
                    <c:if test="${sessionScope.user != null }">
                        <a class="nav-item nav-link navtext" style="border: 0;" href="${pageContext.request.contextPath}/ToUserpage">${sessionScope.user.getEmail()} (${sessionScope.user.getBalance()} kr.)</a>
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/ToCart">
                            <img src="${pageContext.request.contextPath}/images/basket.png" alt="Logo" width="30px">
                        </a>
                    </c:if>
                </div>
            </div>
        </div>
    </nav>
</header>

<div id="body" class="container mt-4" style="min-height: 400px;">
    <jsp:invoke fragment="header"/>
    <jsp:doBody/>
</div>

<!-- Footer -->
<div class="container mt-3 footer">
    <hr/>
    <div class="row mt-4">
        <div class="col">
            Cupcakestreet 32<br/>
            3770 Olsker
        </div>
        <div class="col">
            <jsp:invoke fragment="footer"/><br/>
            <p>&copy; 2023 Olskercupcakes</p>
        </div>
        <div class="col">
            Kontakt information: <br/>
            Olsker@cupcakes.dk
        </div>
    </div>

</div>

<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

</body>
</html>

<style>
    html {
        margin: 0;
        padding: 0;
        scroll-behavior: smooth;
    }
    :root {
        --header-height: 11.6rem;
        --header-img-multi: 1.5;
        --hero-img-multi: 0.3;
        --hero-color: #3c1460;
        --hero-navbar: #471f6a;
        --navbar-text-color: #ffffff;
        --button-color: #e9ca97;
        --button-color-light: #f7d9a9;
    }
    .footer {
        /*position: fixed;*/
        left: 0;
        bottom: 5%;
        width: 100%;
        /*background-color: white;*/
        /*color: white;*/
        text-align: center;
    }
    .header {
        position: sticky;
        top: 0;
        width: 100%;
        background-size : cover;
        background-repeat : no-repeat;
    }
    .hero-img {
        width: calc(100%*var(--hero-img-multi));
        text-align: center;
        display: block;
        margin: auto;
    }
    .navtext {
        color: var(--navbar-text-color);
        border-right: 1px solid #00000038;
        border-radius: 3px 3px 3px 3px;
    }
    .btn-primary {
        background-color: var(--button-color-light);
        border-color: var(--button-color-light);
    }
    .btn-primary:hover {
        background-color: var(--button-color);
        border-color: var(--button-color);
    }
</style>