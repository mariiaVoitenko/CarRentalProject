<%@ include file="/WEB-INF/jsp_header.jsp" %>
<%@ include file="/WEB-INF/header.jsp" %>
<html>
<head>
    <title><fmt:message key="loginTitle"/></title>
</head>

<body>

<div class="container center-input">
  <form class="form-signin" action="/login" method="POST">
    <h2 class="large-headline"><fmt:message key="please_sign_in"/></h2>
    <br>
    <input type="text" class="input-block-level" name="login" placeholder="<fmt:message key="email"/>">
    <input type="password" class="input-block-level" name="password" placeholder="<fmt:message key="password"/>">
    <br>
    <button class="btn btn-large btn-primary" type="submit"><fmt:message key="sign_in"/></button>

  </form>
  <h4><a class="paragraph-text" href="/registration"><fmt:message key="registration_message"/></a></h4>
  <h4>${message}</h4>
</div>

<%@ include file="../WEB-INF/footer.jsp" %>

</body>
</html>
