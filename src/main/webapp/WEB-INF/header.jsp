<%@ include file="jsp_header.jsp" %>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="description" content="Car Rental - Rent a nice car">
    <meta name="author" content="Voitenko Mariia">
    <script src="<c:url value="/js/locale.js"/>"></script>

    <link rel="stylesheet" href="<c:url value="/bootstrap-3.3.5-dist/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="/js/jquery-1.11.3.min.js"></script>
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
            <a href="/" class="large-headline">Car Rental</a>
        </div>
        <div id="navbarCollapse" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <c:choose>
                        <c:when test="${sessionScope.userId!=null}">
                            <a class="middle-headline" data-toggle="dropdown" class="dropdown-toggle"
                               href="#">${sessionScope.fullName}<b
                                    class="caret"></b></a>
                            <ul role="menu" class="dropdown-menu">
                                <li><a href="#"></a></li>
                                <li><a class="paragraph-text" href="/profile"><fmt:message key="edit_profile"/></a></li>
                                <li><a class="paragraph-text" href="/history"><fmt:message key="show_rents"/></a></li>
                            </ul>
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li class="dropdown">
                    <c:choose>
                        <c:when test="${sessionScope.roleId==1}">
                            <a class="middle-headline" data-toggle="dropdown" class="dropdown-toggle"
                               href="#"><fmt:message key="administration"/><b
                                    class="caret"></b></a>
                            <ul role="menu" class="dropdown-menu">
                                <li><a class="paragraph-text" href="/admin/cars"><fmt:message key="cars"/></a></li>
                                <li><a class="paragraph-text" href="/admin/users"><fmt:message key="users"/></a></li>
                                <li><a class="paragraph-text" href="/admin/managers"><fmt:message key="managers"/></a>
                                </li>
                                <li class="divider"></li>
                                <li><a class="paragraph-text" href="/admin/addCar"><fmt:message key="add"/></a></li>
                            </ul>
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li class="dropdown">
                    <c:choose>
                        <c:when test="${sessionScope.roleId==4}">
                            <a class="middle-headline" data-toggle="dropdown" class="dropdown-toggle"
                               href="#"><fmt:message key="managership"/><b
                                    class="caret"></b></a>
                            <ul role="menu" class="dropdown-menu">
                                <li><a class="paragraph-text" href="<c:url value="/admin/applications"/>"><fmt:message
                                        key="applications"/></a></li>
                                <li><a class="paragraph-text" href="<c:url value="/admin/return"/>"><fmt:message
                                        key="returned_cars"/></a></li>
                            </ul>
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">

                <li class="dropdown">
                    <a class="middle-headline" data-toggle="dropdown" class="dropdown-toggle" href="#"><fmt:message
                            key="language"/><b
                            class="caret"></b></a>
                    <ul role="menu" class="dropdown-menu">
                        <li><a href="#" class="center-input" onclick="setLocale('ru');"><img src="<c:url value="/images/rus.png"/>"></a></li>
                        <li><a href="#" class="center-input" onclick="setLocale('en');"><img src="<c:url value="/images/gb.png"/>"></a></li>
                    </ul>
                <li>
                    <c:choose>
                        <c:when test="${sessionScope.userId!=null}">
                            <a class="middle-headline" href="/logout"><fmt:message key="logout"/></a>
                        </c:when>
                        <c:otherwise>
                            <a class="middle-headline" href="/login"><fmt:message key="login"/></a>
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
