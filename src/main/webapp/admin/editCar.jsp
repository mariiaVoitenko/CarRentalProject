<%@ include file="/WEB-INF/jsp_header.jsp" %>
<%@ include file="/WEB-INF/header.jsp" %>
<html>
<head>
    <title>Car details</title>
</head>
<body>
<div class="container">
    <form action="saveCar?id=${car.id}" method="POST" enctype="multipart/form-data">
        <div class="container rectangle">
            <div class="left-div">
                <div class="middle-headline">
                    <select name="brand">
                        <c:forEach var="item" items="${brandsList}">
                            <option value="${item.id}" ${item.id == car.brandId ? 'selected="selected"' : ''}>${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <br>

                <div class="middle-headline">
                    <input class="input-block-level" type="text" name="model" placeholder="<fmt:message key="model"/>"
                           value='<c:out value="${car.model}"/>'>
                </div>

                <img src="<c:url value="${car.photoPath}"/>" alt="Car" height="260" width="450">
                <input type="file" name="photo">
            </div>
            <div class="gap-div40"></div>
            <div class="right-div">
                <img class="small-image" src="<c:url value="/images/passagerare.gif"/>" height="28"
                     width="15">&nbsp;&nbsp;
                <input type="text" name="sitsCount" placeholder="<fmt:message key="sits_count"/>"
                       value='<c:out value="${car.sitsCount}"/>'>
                <br>
                <img class="small-image" src="<c:url value="/images/minibagage.gif"/>" height="28" width="17">&nbsp;&nbsp;
                <input type="text" name="smallLuggageCount" placeholder="<fmt:message key="small_luggage_count"/>"
                       value='<c:out value="${car.smallLuggageCount}"/>'>
                <br>
                <img class="small-image" src="<c:url value="/images/bagage.gif"/>" height="28" width="23">&nbsp;
                <input type="text" name="bigLuggageCount" placeholder="<fmt:message key="big_luggage_count"/>"
                       value='<c:out value="${car.bigLuggageCount}"/>'>
                <br><br>

                <div class="paragraph-text"><fmt:message key="price"/> :
                    <input type="text" name="price" placeholder="<fmt:message key="price"/>"
                           value='<c:out value="${car.price}"/>'>&nbsp; HRN
                </div>
                <br>

                <div class="paragraph-text"><fmt:message key="doors"/> :
                    <input type="text" name="doorsCount" placeholder="<fmt:message key="doors"/>"
                           value='<c:out value="${car.doorsCount}"/>'>
                </div>

                <br>

                <div class="paragraph-text">
                    <fmt:message key="conditioner"/> :
                    <select name="hasConditioner">
                        <option value="true"  <c:if
                                test="${param.selectValue == car.hasConditioner})"> selected </c:if>  >true
                        </option>
                        <option value="false"  <c:if
                                test="${param.selectValue == car.hasConditioner})"> selected </c:if>  >false
                        </option>
                    </select>
                </div>
                <br>

                <div class="paragraph-text">
                    <fmt:message key="class"/> :
                    <select name="class">
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
                    <select name="color">
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
                    <select name="status">
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
                <button class="btn btn-large btn-primary" type="submit"><fmt:message key="save"/></button>
            </div>
        </div>
    </form>

</div>
<div class="container center-input">
    <h3 class="middle-headline">${message}</h3>
</div>
<%@ include file="../WEB-INF/footer.jsp" %>
</body>
</html>
