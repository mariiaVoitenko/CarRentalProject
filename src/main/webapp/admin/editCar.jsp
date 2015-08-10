<%@ include file="/WEB-INF/jsp_header.jsp" %>
<%@ include file="/WEB-INF/header.jsp" %>
<html>
<head>
    <title><fmt:message key="banned_title"/></title>
</head>
<body>
<div class="container">
    <div class="container rectangle">
        <div class="left-div">
            <div class="middle-headline">
                <select>
                    <c:forEach var="item" items="${brandsList}">
                        <option value="<c:out value='${item.name}' />"
                                <c:if test="${param.selectValue == item.name})"> selected </c:if>  >
                            <c:out value="${item.name}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <br>

            <div class="middle-headline">
                <input class="input-block-level" type="text" name="model" placeholder="<fmt:message key="model"/>"
                       value=' <c:out value="${car.model}"/>'>
            </div>

            <img src="<c:url value="${car.photoPath}"/>" alt="Car" height="260" width="450">
        </div>
        <div class="gap-div40"></div>
        <div class="right-div">
            <img class="small-image" src="<c:url value="/images/passagerare.gif"/>" height="28"
                 width="15">x<input type="text" name="model" placeholder="<fmt:message key="sits_count"/>"
                                    value=' <c:out value="${car.sitsCount}"/>'>
            <br>
            <img class="small-image" src="<c:url value="/images/minibagage.gif"/>" height="28"
                 width="17">x<input type="text" name="model" placeholder="<fmt:message key="small_luggage_count"/>"
                                    value=' <c:out value="${car.smallLuggageCount}"/>'>
            <br>
            <img class="small-image" src="<c:url value="/images/bagage.gif"/>" height="28"
                 width="23">x<input type="text" name="model" placeholder="<fmt:message key="big_luggage_count"/>"
                                    value=' <c:out value="${car.bigLuggageCount}"/>'>
            <br><br>

            <div class="paragraph-text">
                <fmt:message key="conditioner"/> :
                <select id="dropdown">
                    <option value="true"  <c:if test="${param.selectValue == 'true'})"> selected </c:if>  >true</option>
                    <option value="false"  <c:if test="${param.selectValue == 'false'})"> selected </c:if>  >false
                    </option>
                </select>

            </div>
            <br>
            <div class="paragraph-text">
                <fmt:message key="class"/> :
                <select>
                    <c:forEach var="item" items="${classesList}">
                        <option value="<c:out value='${item.name}' />"
                                <c:if test="${param.selectValue == item.name})"> selected </c:if>  >
                            <c:out value="${item.name}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <br>

            <div class="paragraph-text">
                <fmt:message key="color"/> :
                <select>
                    <c:forEach var="item" items="${colorsList}">
                        <option value="<c:out value='${item.name}' />"
                                <c:if test="${param.selectValue == item.name})"> selected </c:if>  >
                            <c:out value="${item.name}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <br>

            <div class="paragraph-text">
                <fmt:message key="status"/> :
                <select>
                    <c:forEach var="item" items="${statusesList}">
                        <option value="<c:out value='${item.name}' />"
                                <c:if test="${param.selectValue == item.name})"> selected </c:if>  >
                            <c:out value="${item.name}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <br>
            <br>
            <br>
            <br>

            <form action="editCar?id=${car.id}" method="POST">
                <button class="btn btn-large btn-primary" type="submit"><fmt:message key="save"/></button>
            </form>
        </div>
    </div>
</div>
<%@ include file="../WEB-INF/footer.jsp" %>
</body>
</html>
