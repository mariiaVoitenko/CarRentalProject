<%@ include file="../WEB-INF/jsp_header.jsp" %>
<%@ include file="/WEB-INF/header.jsp" %>
<html>
<head>
    <title><fmt:message key="registrationTitle"/></title>
</head>
<body>

<div class="container center-input">
  <form class="form-signin" action="/RegistrationServlet" method="POST" enctype="multipart/form-data">
    <h2 class="form-signin-heading"><fmt:message key="registration"/></h2>
    <input type="text" class="input-block-level" name="login" placeholder="<fmt:message key="email"/>">
    <input type="password" class="input-block-level" name="password" placeholder="<fmt:message key="password"/>">
    <input type="text" class="input-block-level" name="fullName" placeholder="<fmt:message key="full_name"/>">
    <input type="text" class="input-block-level" name="passport" placeholder="<fmt:message key="passport"/>">
    <input type="file" name="photo">
    <button class="btn btn-large btn-primary" type="submit"><fmt:message key="register"/></button>
  </form>
  <h4>${message}</h4>
</div>

<%@ include file="../WEB-INF/footer.jsp" %>

</body>
</html>
