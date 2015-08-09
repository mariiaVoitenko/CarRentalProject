<%@ include file="jsp_header.jsp" %>
<html>
<head>
    <title></title>
    <script src="<c:url value="/js/locale.js"/>"></script>
</head>
<body>
<nav role="navigation" class="navbar navbar-default navbar-static-top ">
    <div class="container">
        <div class="navbar-header">
            <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="/" class="navbar-brand">Car Rental</a>
        </div>
        <div id="navbarCollapse" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li ><a href="index.jsp"><fmt:message key="home"/></a></li>
                <li>
                <li class="dropdown">
                    <c:choose>
                        <c:when test="${sessionScope.userId!=null}">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">${sessionScope.fullName}<b
                                    class="caret"></b></a>
                            <ul role="menu" class="dropdown-menu">
                                <li><a href="#">TODO</a></li>
                                <li><a href="#">TODO</a></li>
                                <li><a href="#">TODO</a></li>
                                <li class="divider"></li>
                                <li><a href="#">TODO</a></li>
                            </ul>
                        </c:when>
                        <c:otherwise>

                        </c:otherwise>
                    </c:choose>
                </li>
                </li>
                <li class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#"><fmt:message key="administration"/><b
                            class="caret"></b></a>
                    <ul role="menu" class="dropdown-menu">
                        <li><a href="#">TODO</a></li>
                        <li><a href="#">TODO</a></li>
                        <li><a href="#">TODO</a></li>
                        <li class="divider"></li>
                        <li><a href="#">TODO</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">

                <li class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#"><fmt:message key="language"/><b
                            class="caret"></b></a>
                    <ul role="menu" class="dropdown-menu">
                        <li><a href="#" onclick="setLocale('ru');"><img src="<c:url value="/images/rus.png"/>"></a></li>
                        <li><a href="#" onclick="setLocale('en');"><img src="<c:url value="/images/gb.png"/>"></a></li>
                    </ul>
                <li>
                    <c:choose>
                        <c:when test="${sessionScope.userId!=null}">
                            <a href="/LogoutServlet"><fmt:message key="logout"/></a>
                        </c:when>
                        <c:otherwise>
                            <a href="/LoginServlet"><fmt:message key="login"/></a>
                        </c:otherwise>
                    </c:choose>
                </li>
                </li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
