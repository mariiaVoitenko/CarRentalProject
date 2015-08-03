<%@ include file="WEB-INF/jsp_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>

    <link rel="stylesheet" href="<c:url value="/bootstrap-3.3.5-dist/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">
</head>

<body>

<div class="container">
    <form class="form-signin" action="/" method="POST">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" class="input-block-level" name="email" placeholder="Email address">
        <input type="password" class="input-block-level" name="password" placeholder="Password">
        <label class="checkbox">
            <input type="checkbox" value="remember-me"> Remember me
        </label>
        <button class="btn btn-large btn-primary" type="submit">Sign in</button>
    </form>
</div>
<!-- /container -->
</body>
</html>