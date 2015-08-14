<%@ include file="/WEB-INF/jsp_header.jsp" %>
<%@ include file="/WEB-INF/header.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:choose>
    <c:when test="${empty requestScope.users}">
        <fmt:message key="noItems"/>
    </c:when>
    <c:otherwise>
        <c:forEach items="${requestScope.users}" var="user">
            <div class="container rectangle">
                <div class="left-div">
                    <div class="middle-headline">
                            ${user.fullName}
                    </div>
                    <div class="middle-headline">
                            ${user.login}
                    </div>
                    <div class="middle-headline">
                        <c:if test="${user.roleId == 4}"><fmt:message key="manager"/></c:if>
                    </div>
                    <img src="<c:url value="${user.photoPath}"/>" alt="user" height="200" width="200">
                </div>
                <div class="gap-div40"></div>
                <div class="right-div">
                    <div class="paragraph-text">
                        <fmt:message key="passport"/> : ${user.passportNumber}
                    </div>
                    <br>

                    <div class="paragraph-text">
                        <fmt:message key="is_blocked"/> :
                        <c:choose>
                            <c:when test="${user.isBlocked()}"><fmt:message key="yes"/></c:when>
                            <c:otherwise><fmt:message key="no"/></c:otherwise>
                        </c:choose>
                        <br>
                        <br>

                        <form action="block?id=${user.id}" method="POST">
                            <button class="btn btn-large btn-primary" type="submit">
                                <c:choose>
                                    <c:when test="${user.isBlocked()}"><fmt:message key="unblock"/></c:when>
                                    <c:otherwise><fmt:message key="block"/></c:otherwise>
                                </c:choose>
                            </button>
                        </form>

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
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:otherwise>
</c:choose>

</body>
</html>
