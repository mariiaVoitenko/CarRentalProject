<%@ include file="jsp_header.jsp" %>

<div class="container paragraph-text center-input" id="sortingElements">
    <div id="col1">
        <fmt:message key="find_by_brand"/> :
        <select name="brandSelect" id="brandSelect">
            <option value=""></option>
            ${criteria.brandValue}
            <c:forEach var="item" items="${brandsList}">
                <option value="${item.id}" ${criteria.brandValue == item.id? "selected" : ""}>${item.name}</option>
            </c:forEach>
        </select>
    </div>
    <div id="col2">
        <fmt:message key="find_by_class"/> :
        <select name="classSelect" id="classSelect">
            <option value=""></option>
            ${criteria.classValue}
            <c:forEach var="item" items="${classesList}">
                <option value="${item.id}" ${criteria.classValue == item.id? "selected" : ""}>${item.name}</option>
            </c:forEach>
        </select>
    </div>
    <div id="col3">
        <fmt:message key="sort_by_name"/> :
        <select name="nameSortSelect" id="nameSortSelect">
            <option value=""></option>
            <option value="asc" ${(criteria.sortColumn == "model" && criteria.sortType == "asc")? "selected" : ""}>
                Ascending
            </option>
            <option value="desc"  ${(criteria.sortColumn == "model" && criteria.sortType == "desc")? "selected" : ""}>
                Descending
            </option>
        </select>
    </div>
    <div id="col4">
        <fmt:message key="sort_by_price"/> :
        <select name="priceSortSelect" id="priceSortSelect">
            <option value=""></option>
            <option value="asc"  ${(criteria.sortColumn == "price" && criteria.sortType == "asc")? "selected" : ""}>
                Ascending
            </option>
            <option value="desc"  ${(criteria.sortColumn == "price" && criteria.sortType == "desc")? "selected" : ""}>
                Descending
            </option>
        </select>
    </div>
    <button class="btn btn-large btn-primary" type="submit" onClick="sendGetRequest()"><fmt:message
            key="sort"/></button>
</div>

<script src="<c:url value="/js/sorting.js"/>"></script>




