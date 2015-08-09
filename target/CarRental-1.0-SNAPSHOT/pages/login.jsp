<%@ include file="../WEB-INF/jsp_header.jsp" %>
<%@ include file="/WEB-INF/header.jsp" %>
<html>
<head>
    <title><fmt:message key="loginTitle"/></title>
</head>

<body>

<div class="container center-input">
  <form class="form-signin" action="/LoginServlet" method="POST">
    <h2 class="form-signin-heading"><fmt:message key="please_sign_in"/></h2>
    <input type="text" class="input-block-level" name="login" placeholder="<fmt:message key="email"/>">
    <input type="password" class="input-block-level" name="password" placeholder="<fmt:message key="password"/>">
    <label class="checkbox">
      <input type="checkbox" value="remember-me"> <fmt:message key="remember_me"/>
    </label>
    <button class="btn btn-large btn-primary" type="submit"><fmt:message key="sign_in"/></button>

  </form>
  <h4>${message}</h4>
</div>

<%@ include file="../WEB-INF/footer.jsp" %>

</body>
</html>
