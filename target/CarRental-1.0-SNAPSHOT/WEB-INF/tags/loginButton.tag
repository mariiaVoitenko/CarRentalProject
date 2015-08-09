<%@ include file="../jsp_header.jsp" %>
<c:choose>
    <c:when test="${sessionScope.userId!=null}">
        <a href="/LogoutServlet"><fmt:message key="logout"/></a>
    </c:when>
    <c:otherwise>
        <a href="/LoginServlet"><fmt:message key="login"/></a>
    </c:otherwise>
</c:choose>