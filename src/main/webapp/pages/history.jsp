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
      <fmt:message key="noItems"/>
    </c:when>
    <c:otherwise>
      <c:forEach items="${requestScope.rents}" var="rent">
        <div class="container rectangle">
          <div class="left-div">
            <div class="middle-headline">
                ${rent.car.brandName}
            </div>
            <div class="middle-headline">
                ${rent.car.model}
            </div>
            <img src="<c:url value="${rent.car.photoPath}"/>" alt="Car" height="260" width="450">
          </div>
          <div class="gap-div40"></div>
          <div class="right-div">
            <img class="small-image" src="<c:url value="/images/passagerare.gif"/>" height="28"
                 width="15">x${rent.car.sitsCount}
            <img class="small-image" src="<c:url value="/images/minibagage.gif"/>" height="28"
                 width="17">x${rent.car.smallLuggageCount}
            <img class="small-image" src="<c:url value="/images/bagage.gif"/>" height="28"
                 width="23">x${rent.car.bigLuggageCount}
            <c:if test="${rent.car.hasConditioner}">
              <img class="small-image" src="<c:url value="/images/ac.gif"/>" height="24" width="23">
            </c:if>
                        <span class="small-image paragraph-text">${rent.car.doorsCount}&nbsp;<fmt:message
                                key="doors"/></span>
            <span class="price red">${rent.check.sum} <fmt:message key="HRN"/></span>
            <br><br><br><br>

            <div class="paragraph-text">
              <fmt:message key="rental_start"/> : ${rent.startDate}
            </div>
            <br>

            <div class="paragraph-text">
              <fmt:message key="rental_end"/> : ${rent.endDate}
            </div>
            <br>

            <div class="paragraph-text">
              <fmt:message key="is_payed"/> :
              <c:choose>
                <c:when test="${rent.check.isPayed()}"><fmt:message key="yes"/></c:when>
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
              <fmt:message key="class"/> : ${rent.car.className}
            </div>
            <br>

            <div class="paragraph-text">
              <fmt:message key="color"/> : ${rent.car.colorName}
            </div>
            <br>

            <div class="paragraph-text">
              <fmt:message key="status"/> :
              <c:choose>
                <c:when test="${rent.decline.id == 5}"><fmt:message key="not_approven"/></c:when>
                <c:when test="${rent.decline.id == 6}"><fmt:message key="approven"/></c:when>
                <c:otherwise>
                  <span class="red-message"><fmt:message key="denied"/>${rent.decline.name}</span>
                </c:otherwise>
              </c:choose>
            </div>
            <br>

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


