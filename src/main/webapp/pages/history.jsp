<%@ include file="/WEB-INF/jsp_header.jsp" %>
<%@ include file="/WEB-INF/header.jsp" %>
<html>
<head>
    <title>History</title>
</head>
<body>
<div class="container">

    <c:choose>
        <c:when test="${empty requestScope.rents}">
            <div class="middle-headline"><fmt:message key="no_rents"/></div>
        </c:when>
        <c:otherwise>
            <c:forEach items="${requestScope.rents}" var="rent">
                <div class="container rectangle">
                    <div class="left-div">
                        <div class="middle-headline">
                                ${rent.brandName}
                        </div>
                        <div class="middle-headline">
                                ${rent.model}
                        </div>
                        <img src="<c:url value="photo/${rent.photoPath}"/>" alt="Car" height="260" width="450">
                    </div>
                    <div class="gap-div40"></div>
                    <div class="right-div">
                        <img class="small-image" src="<c:url value="/images/passagerare.gif"/>" height="28"
                             width="15">x${rent.sitsCount}
                        <img class="small-image" src="<c:url value="/images/minibagage.gif"/>" height="28"
                             width="17">x${rent.smallLuggageCount}
                        <img class="small-image" src="<c:url value="/images/bagage.gif"/>" height="28"
                             width="23">x${rent.bigLuggageCount}
                        <c:if test="${rent.isHasConditioner()}">
                            <img class="small-image" src="<c:url value="/images/ac.gif"/>" height="24" width="23">
                        </c:if>
                        <span class="small-image paragraph-text">${rent.doorsCount}&nbsp;<fmt:message
                                key="doors"/></span>
                        <span class="price red">${rent.checkSum} &nbsp;<fmt:message key="HRN"/></span>
                        <br><br><br><br>

                        <div class="paragraph-text">
                            <fmt:message key="rental_period"/> : ${rent.startDate} - ${rent.endDate}
                        </div>
                        <br>

                        <div class="paragraph-text">
                            <fmt:message key="is_payed"/> :
                            <c:choose>
                                <c:when test="${rent.isPayed()}"><fmt:message key="yes"/></c:when>
                                <c:otherwise>
                                    <span class="red-message"><fmt:message key="no"/></span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <br>

                        <div class="paragraph-text">
                            <fmt:message key="is_driven"/> :
                            <c:choose>
                                <c:when test="${rent.isDriven()}"><fmt:message key="yes"/></c:when>
                                <c:otherwise><fmt:message key="no"/></c:otherwise>
                            </c:choose>
                        </div>
                        <br>

                        <div class="paragraph-text">
                            <fmt:message key="class"/> : ${rent.className}
                        </div>
                        <br>

                        <div class="paragraph-text">
                            <fmt:message key="color"/> : ${rent.colorName}
                        </div>
                        <br>

                        <div class="paragraph-text">
                            <fmt:message key="status"/> :
                            <c:choose>
                                <c:when test="${not empty rent.declineName}"><span class="red-message"><fmt:message
                                        key="denied"/>${rent.declineName}</span></c:when>
                                <c:when test="${rent.isApproved() == true && rent.isReturned() == false}"><fmt:message key="approven"/></c:when>
                                <c:when test="${rent.isApproved() == false}"><fmt:message key="not_approven"/></c:when>
                                <c:when test="${rent.isReturned() == true  && rent.isFinished() == false}"><fmt:message key="returned"/></c:when>
                                <c:when test="${rent.isFinished() == true}"><fmt:message key="finished"/></c:when>
                                <c:otherwise>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <br>
                        <form action="/return?id=${rent.rentId}" method="POST">
                            <c:if test="${(rent.isFinished() == false) && (rent.isReturned() == false) && (empty rent.declineName) && (rent.isApproved()==true)}">
                                <button class="btn btn-large btn-success" type="submit"><fmt:message key="return"/>
                                </button>
                            </c:if>
                        </form>
                        <br>
                    </div>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="/WEB-INF/footer.jsp" %>
</body>
</html>


