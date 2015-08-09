<%@ include file="../WEB-INF/jsp_header.jsp" %>
<%@ include file="/WEB-INF/header.jsp" %>
<html>
<head>
    <title></title>
  <link rel="stylesheet" href="<c:url value="/bootstrap-3.3.5-dist/css/bootstrap.css"/>">
  <link rel="stylesheet" href="<c:url value="/css/main.css"/>">
</head>

<body>

<div class="container center-input">
  <form class="form-signin" action="/LoginServlet" method="POST">
    <h2 class="form-signin-heading">Please, sign in</h2>
    <input type="text" class="input-block-level" name="login" placeholder="Email address">
    <input type="password" class="input-block-level" name="password" placeholder="Password">
    <label class="checkbox">
      <input type="checkbox" value="remember-me"> Remember me
    </label>
    <button class="btn btn-large btn-primary" type="submit">Sign in</button>
      ${message}
  </form>
</div>

<%@ include file="../WEB-INF/footer.jsp" %>

</body>
</html>
