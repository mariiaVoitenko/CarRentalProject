<%@ include file="WEB-INF/jsp_header.jsp" %>
<%@ include file="WEB-INF/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="mainTitle"/></title>
</head>

<body>

<div class="jumbotron">
    <div class="container">
        <div class="left-div">
            <img src="<c:url value="/images/logo_trans.png"/>" alt="Logo" height="385" width="445">
        </div>
        <div class="right-div center-input marginned">
            <div class="large-headline">We can help you to rent a nice car</div>
            <br>
            <br>

            <form action="rentCar" method="GET">
                <div>
                    <fmt:message key="rental_start"/> : <input type="date" name="start">
                </div>
                <br>

                <div>
                    <fmt:message key="rental_end"/> : <input type="date" name="end">
                </div>
                <br>

                <div>
                    <fmt:message key="order_driver"/>&nbsp;<input name="driver" type="checkbox" checked>
                </div>
                <button class="btn btn-large btn-success marginned" type="submit"><fmt:message key="rent"/></button>
            </form>
        </div>
    </div>
</div>

<div id="div_1" class="marginned20">

    <div id="div_3">
        <c:forEach items="${requestScope.cars}" var="car">
            <div id="div_2" class="center-input">
                <div class="paragraph-text">
                        ${car.brandName}
                </div>
                <div class="paragraph-text">
                        ${car.model}
                </div>
                <img src="<c:url value="/photo/${car.photoPath}"/>" alt="Car" height="87" width="150">
                <div class="paragraph-text red-message ">
                        ${car.price} &nbsp;
                            <fmt:message key="HRN"/>
                </div>
                <form method="get" action="admin/cars">
                <button class="btn btn-xs btn-primary btn-block marginned20" type="submit">
                    <fmt:message key="continue"/>
                </button>
                </form>
            </div>
        </c:forEach>
    </div>
</div>
<div class="container center-input">
    <h3 class="middle-headline">${message}</h3>
</div>
<%@ include file="/WEB-INF/footer.jsp" %>

</body>
</html>