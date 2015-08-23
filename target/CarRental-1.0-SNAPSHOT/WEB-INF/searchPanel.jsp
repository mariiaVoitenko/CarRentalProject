<%@ include file="jsp_header.jsp" %>

<div class="container paragraph-text center-input" id="sortingElements">
    <div id="col1">
        <fmt:message key="find_by_brand"/> :
        <select name="brandSelect" id="brandSelect">
            <option value=""></option>
            <c:forEach var="item" items="${brandsList}">
                <option value="${item.id}">${item.name}</option>
            </c:forEach>
        </select>
    </div>
    <div id="col2">
        <fmt:message key="find_by_class"/> :
        <select name="classSelect" id="classSelect">
            <option value=""></option>
            <c:forEach var="item" items="${classesList}">
                <option value="${item.id}">${item.name}</option>
            </c:forEach>
        </select>
    </div>
    <div id="col3">
        <fmt:message key="sort_by_name"/> :
        <select name="nameSortSelect" id="nameSortSelect">
            <option value=""></option>
            <option value="asc">Ascending</option>
            <option value="desc">Descending</option>
        </select>
    </div>
    <div id="col4">
        <fmt:message key="sort_by_price"/> :
        <select name="priceSortSelect" id="priceSortSelect">
            <option value=""></option>
            <option value="asc">Ascending</option>
            <option value="desc">Descending</option>
        </select>
    </div>
    <button class="btn btn-large btn-primary" type="submit" onClick="sendGetRequest()"><fmt:message key="sort"/></button>
</div>

<script src="<c:url value="/js/sorting.js"/>"></script>




