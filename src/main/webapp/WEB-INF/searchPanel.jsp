<%@ include file="jsp_header.jsp" %>

<div class="container paragraph-text center-input">
    <div id="col1">
        <form class="paragraph-text" method="get" action="sort?brand=true">
            <fmt:message key="find_by_brand"/> :
            <select name="brand" onchange="this.form.submit()">
                <c:forEach var="item" items="${brandsList}">
                    ${item}
                    <option value="${item.id}">${item.name}</option>
                </c:forEach>
            </select>
        </form>
    </div>
    <div id="col2">
        <form class="paragraph-text" method="get" action="sort?class=true">
            <fmt:message key="find_by_class"/> :
            <select name="class" onchange="this.form.submit()">
                <c:forEach var="item" items="${classesList}">
                    <option value="${item.id}">${item.name}</option>
                </c:forEach>
            </select>
        </form>
    </div>
    <div id="col3">
        <form class="paragraph-text" method="get" action="sort?name=true">
            <fmt:message key="sort_by_name"/> :
            <select name="nameSort" onchange="this.form.submit()">
                <option value="asc">Ascending</option>
                <option value="desc">Descending</option>
            </select>
        </form>
    </div>
    <div id="col4">
        <form class="paragraph-text" method="get" action="sort?price=true">
            <fmt:message key="sort_by_price"/> :
            <select name="priceSort" onchange="this.form.submit()">
                <option value="asc">Ascending</option>
                <option value="desc">Descending</option>
            </select>
        </form>
    </div>
</div>




