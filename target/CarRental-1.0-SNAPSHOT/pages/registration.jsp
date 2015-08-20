<%@ include file="/WEB-INF/jsp_header.jsp" %>
<%@ include file="/WEB-INF/header.jsp" %>
<html>
<head>
    <title><fmt:message key="registrationTitle"/></title>
</head>
<body>

<div class="container center-input">
    <form class="form-signin" name="registration" action="/registration" method="POST"
          enctype="multipart/form-data">
        <h2 class="large-headline"><fmt:message key="registration"/></h2>
        <br>
        <input type="text" class="input-block-level" name="login" placeholder="<fmt:message key="email"/>">
        <input type="text" class="input-block-level" name="fullName" placeholder="<fmt:message key="full_name"/>">
        <input type="text" class="input-block-level" name="passport" placeholder="<fmt:message key="passport"/>">
        <input type="password" class="input-block-level" name="password" placeholder="<fmt:message key="password"/>">
        <input type="password" class="input-block-level" name="password2"
               placeholder="<fmt:message key="repeat_password"/>">
        <input type="file" name="photo">
        <button class="btn btn-large btn-primary" type="submit"  onClick="return submit_registration_form(this.form)"><fmt:message key="register"/></button>
    </form>
</div>

<div class="center-input marginned">
    <h4>${photoMessage}</h4>
    <h4>${loginMessage}</h4>
</div>

<%@ include file="../WEB-INF/footer.jsp" %>
<script src="<c:url value="/js/registrationValidation.js"/>"></script>
</body>
</html>
