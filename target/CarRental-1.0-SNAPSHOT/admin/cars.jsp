<%@ include file="/WEB-INF/jsp_header.jsp" %>
<%@ include file="/WEB-INF/header.jsp" %>
<html>
<head>
    <title><fmt:message key="banned_title"/></title>
</head>
<body>
<div class="container">
    <c:choose>
        <c:when test="${empty requestScope.cars}">
            <fmt:message key="noItems"/>
        </c:when>
        <c:otherwise>
            <c:forEach items="${requestScope.cars}" var="car">
                <div class="container rectangle">
                    <div class="left-div">
                        <div class="middle-headline">
                                ${car.brandName}
                        </div>
                        <div class="middle-headline">
                                ${car.model}
                        </div>
                        <img src="<c:url value="${car.photoPath}"/>" alt="Car" height="260" width="450">
                    </div>
                    <div class="gap-div40"></div>
                    <div class="right-div">
                        <img class="small-image" src="<c:url value="/images/passagerare.gif"/>" height="28"
                             width="15">x${car.sitsCount}
                        <img class="small-image" src="<c:url value="/images/minibagage.gif"/>" height="28"
                             width="17">x${car.smallLuggageCount}
                        <img class="small-image" src="<c:url value="/images/bagage.gif"/>" height="28"
                             width="23">x${car.bigLuggageCount}
                        <c:if test="${car.hasConditioner}">
                            <img class="small-image" src="<c:url value="/images/ac.gif"/>" height="24" width="23">
                        </c:if>
                        <br><br><br><br>

                        <div class="paragraph-text">
                            <fmt:message key="class"/> : ${car.className}
                        </div>
                        <br>

                        <div class="paragraph-text">
                            <fmt:message key="color"/> : ${car.colorName}
                        </div>
                        <br>

                        <div class="paragraph-text">
                            <fmt:message key="status"/> : ${car.statusName}
                        </div>
                        <br>
                        <br>
                        <br>
                        <br>
                        <button class="btn btn-large btn-primary" type="submit"><fmt:message key="edit"/></button>
                        <button class="btn btn-large btn-primary" type="submit"><fmt:message key="delete"/></button>
                    </div>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="../WEB-INF/footer.jsp" %>
</body>
</html>
