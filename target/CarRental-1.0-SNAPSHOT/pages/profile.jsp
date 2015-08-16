<%@ include file="/WEB-INF/jsp_header.jsp" %>
<%@ include file="/WEB-INF/header.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="container rectangle">

    <div class="left-div">

        <img src="<c:url value="photo/${user.photoPath}"/>" alt="user" height="250" width="250">
        <br>
        <c:if test="${edit == true}">
            <form class="marginned20 center-input" action="<c:url value="/profile"/>" method="POST"
                  enctype="multipart/form-data">
                <input type="file" name="photo">
                <button class="btn btn-large btn-success marginned20" type="submit"><fmt:message key="save"/></button>
            </form>
        </c:if>

    </div>
    <div class="gap-div40"></div>
    <div class="right-div marginned20">
        <div class="left-div">
            <div class="middle-headline">
                <fmt:message key="full_name"/> : ${user.fullName}
            </div>
            <div class="middle-headline">
                <fmt:message key="login"/> : ${user.login}
            </div>
            <div class="middle-headline">
                <c:if test="${user.roleId == 4}"><fmt:message key="role"/> : <fmt:message key="manager"/></c:if>
                <c:if test="${user.roleId == 1}"><fmt:message key="role"/> : <fmt:message key="admin"/></c:if>
            </div>

            <div class="middle-headline">
                <fmt:message key="passport"/> : ${user.passportNumber}
            </div>
            <br>
        </div>
        <c:if test="${user.roleId == 1}">
            <form action="block?id=${user.id}" method="POST">
                <button class="btn btn-large btn-danger" type="submit">
                    <c:choose>
                        <c:when test="${user.isBlocked()}"><fmt:message key="unblock"/></c:when>
                        <c:otherwise><fmt:message key="block"/></c:otherwise>
                    </c:choose>
                </button>
            </form>
        </c:if>

        <c:if test="${user.roleId == 1}">
            <form action="makeManager?id=${user.id}" method="POST">
                <c:choose>
                    <c:when test="${user.roleId == 4}">
                        <button class="btn btn-large btn-primary" type="submit"><fmt:message
                                key="make_simple_user"/></button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-large btn-primary" type="submit"><fmt:message
                                key="make_manager"/></button>
                    </c:otherwise>
                </c:choose>
            </form>
        </c:if>
    </div>

</div>


</body>
</html>
