<%@ include file="jsp_header.jsp" %>

<form class="" method="get" action="sort?brand=true">
    <fmt:message key="find_by_brand"/> :
    <select name="brand">
        <c:forEach var="item" items="${brandsList}">
            <option value="${item.id}">${item.name}</option>
        </c:forEach>
    </select>
</form>

<form class="" method="get" action="sort?class=true">
    <fmt:message key="find_by_class"/> :
    <select name="class">
        <c:forEach var="item" items="${classessList}">
            <option value="${item.id}">${item.name}</option>
        </c:forEach>
    </select>
</form>

<form class="" method="get" action="sort?name=true">
    <fmt:message key="sort_by_name"/> :
    <select name="nameSort">
        <option value="asc">Ascending</option>
        <option value="desc">Descending</option>
    </select>
</form>

<form class="" method="get" action="sort?price=true">
    <fmt:message key="sort_by_price"/> :
    <select name="priceSort">
        <option value="asc">Ascending</option>
        <option value="desc">Descending</option>
    </select>
</form>

