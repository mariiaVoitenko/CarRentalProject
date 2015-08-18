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
      <fmt:message key="no_rents"/>
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
            <img src="<c:url value="photo/${rent.car.photoPath}"/>" alt="Car" height="260" width="450">
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
            <span class="price red">${rent.check.sum}&nbsp; <fmt:message key="HRN"/></span>
            <br><br><br><br>

            <div>
              <div class="left-div">
                <div class="paragraph-text">
                  <fmt:message key="user"/> : <a href="<c:url value="/profile?id=${rent.user.id}&edit=false"/>"> ${rent.user.fullName}</a>
                </div>
                <br>

                <div class="paragraph-text">
                  <fmt:message key="passport"/> : ${rent.user.passportNumber}
                </div>
                <br>

                <div class="paragraph-text">
                  <fmt:message key="rental_start"/> : ${rent.startDate}
                </div>
                <br>

                <div class="paragraph-text">
                  <fmt:message key="rental_end"/> : ${rent.endDate}
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
              </div>
              <div class="right-div">
                <form action="<c:url value="setFinished?id=${rent.id}"/>" method="POST">
                  <button class="btn btn-large btn-success btn-group-justified" type="submit">
                    <fmt:message
                            key="accept"/></button>
                </form>
                <div class="text-center paragraph-text">
                  <fmt:message key="choose_damage"/> :
                  <br>
                  <form action="<c:url value="setDamage?id=${rent.id}"/>" method="POST">
                    <div class="paragraph-text">
                      <select name="decline" class="paragraph-text">
                        <c:forEach var="item" items="${damageList}">
                          <option value="${item.id}">${item.name}</option>
                        </c:forEach>
                      </select>
                      <br>
                    </div>
                    <button class="btn btn-large btn-danger btn-group-justified marginned20"
                            type="submit"><fmt:message key="create_damage_check"/></button>
                  </form>
                </div>
              </div>
              <br>
            </div>
          </div>
        </div>
      </c:forEach>
    </c:otherwise>
  </c:choose>
</div>
<%@ include file="/WEB-INF/footer.jsp" %>
</body>
</html>




