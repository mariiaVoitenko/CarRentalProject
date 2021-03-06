<%@ include file="/WEB-INF/jsp_header.jsp" %>
<%@ include file="/WEB-INF/header.jsp" %>
<html>
<head>
  <title>All cars</title>
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
            <img src="<c:url value="photo/${car.photoPath}"/>" alt="Car" height="260" width="450">
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
                        <span class="small-image paragraph-text">${car.doorsCount}&nbsp;<fmt:message
                                key="doors"/></span>
            <span class="price red">${car.price} <fmt:message key="HRN"/></span>
            <br><br><br><br>

            <div class="paragraph-text">
              <fmt:message key="class"/> : ${car.className}
            </div>
            <br>

            <div class="paragraph-text">
              <fmt:message key="color"/> : ${car.colorName}
            </div>
            <br>

            <br>

            <form action="rentCar?id=${car.id}" method="POST">
              <button class="btn btn-large btn-primary" type="submit"><fmt:message key="rent"/></button>
            </form>

          </div>
        </div>
      </c:forEach>
    </c:otherwise>
  </c:choose>
</div>
<%@ include file="/WEB-INF/footer.jsp" %>
</body>
</html>

