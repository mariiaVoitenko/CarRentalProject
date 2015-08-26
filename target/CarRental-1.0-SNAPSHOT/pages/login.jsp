<%@ include file="/WEB-INF/jsp_header.jsp" %>
<%@ include file="/WEB-INF/header.jsp" %>
<html>
<head>
    <title>Login</title>
</head>

<body>

<div class="container center-input">
  <form class="form-signin" action="/login" method="POST">
    <h2 class="large-headline"><fmt:message key="please_sign_in"/></h2>
    <br>
    <input type="text" class="input-block-level" name="login" placeholder="<fmt:message key="email"/>">
    <input type="password" class="input-block-level" name="password" placeholder="<fmt:message key="password"/>">
    <br>
    <button class="btn btn-large btn-primary" type="submit" name="submit" onClick="return submit_form(this.form)"><fmt:message key="sign_in"/></button>

  </form>
  <h4 class="paragraph-text"><fmt:message key="registration_message"/><a class="paragraph-text" href="/registration">&nbsp;<fmt:message key="registration_message_link"/></a></h4>
  <div class="middle-headline">${message}</div>
</div>

<%@ include file="../WEB-INF/footer.jsp" %>
<script src="<c:url value="/js/validation.js"/>"></script>
</body>
</html>
