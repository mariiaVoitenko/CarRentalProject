<%@ include file="/WEB-INF/jsp_header.jsp" %>
<%@ include file="/WEB-INF/header.jsp" %>
<%@ taglib uri="/WEB-INF/tags/priceFormatter.tld" prefix="customPriceTag"%>
<html>
<head>
    <title>All cars></title>
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
                        <img src="<c:url value="/photo/${car.photoPath}"/>" alt="Car" height="260" width="450">
                    </div>
                    <div class="gap-div40"></div>
                    <div class="right-div marginned20">
                        <img class="small-image" src="<c:url value="/images/passagerare.gif"/>" height="28"
                             width="15">x${car.sitsCount}
                        <img class="small-image" src="<c:url value="/images/minibagage.gif"/>" height="28"
                             width="17">x${car.smallLuggageCount}
                        <img class="small-image" src="<c:url value="/images/bagage.gif"/>" height="28"
                             width="23">x${car.bigLuggageCount}
                        <c:if test="${car.hasConditioner}">
                            <img class="small-image" src="<c:url value="/images/ac.gif"/>" height="24" width="23">
                        </c:if>
                        <span class="small-image paragraph-text">${car.doorsCount}&nbsp;<fmt:message
                                key="doors"/></span>
                        <br><br>
                        <span class="price red">${car.price}&nbsp;<fmt:message key="HRN"/></span>
                        <br><br><br><br>

                        <div class="marginned20">
                            <div class="left-div">
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
                            </div>
                            <c:choose>
                                <c:when test="${roleId!=1}">

                                </c:when>
                                <c:otherwise>
                                    <div class="right-div">
                                        <form action="<c:url value="editCar?id=${car.id}"/>" method="POST">
                                            <button class="btn btn-large btn-primary btn-group-justified" type="submit">
                                                <fmt:message
                                                        key="edit"/></button>
                                        </form>

                                        <form action="<c:url value="deleteCar?id=${car.id}"/>" method="POST">
                                            <button class="btn btn-large btn-danger btn-group-justified" type="submit">
                                                <fmt:message
                                                        key="delete"/></button>
                                        </form>

                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="../WEB-INF/footer.jsp" %>
</body>
</html>
