<%@ include file="../WEB-INF/jsp_header.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<nav role="navigation" class="navbar navbar-default navbar-static-top">
  <div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a href="#" class="navbar-brand">Brand</a>
    </div>
    <!-- Collection of nav links and other content for toggling -->
    <div id="navbarCollapse" class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li class="active"><a href="index.jsp">Home</a></li>
        <li><a href="profile.jsp">Profile</a></li>
        <li class="dropdown">
          <a data-toggle="dropdown" class="dropdown-toggle" href="#">Administration <b class="caret"></b></a>
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
          <a data-toggle="dropdown" class="dropdown-toggle" href="#">Language<b class="caret"></b></a>
          <ul role="menu" class="dropdown-menu">
            <li><a href="#">TODO</a></li>
            <li><a href="#">TODO</a></li>
            <li><a href="#">TODO</a></li>
            <li class="divider"></li>
            <li><a href="#">TODO</a></li>
          </ul>
        <li><a href="pages/login.jsp">Login</a></li>
        </li>
      </ul>
    </div>
  </div>
</nav>
</body>
</html>
