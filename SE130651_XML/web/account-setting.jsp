<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="account">
    <div class="fs-25px">
        ${sessionScope.USER}
        <img class="setting-img" onclick="showSettingItems()" src="<c:url value="/styles/imgs/setting.svg" />">
    </div>
    <div class="setting-dropdown-menu w-100">
        <button name="dropdown-item" class="btn btn-secondary display-none" onclick="window.location.href = 'home'">Home Page</button>
        <button name="dropdown-item" class="btn btn-secondary display-none" onclick="window.location.href = 'my-collection'">Collection</button>
        <c:if test="${sessionScope.ROLE == 'admin'}">
            <button name="dropdown-item" class="btn btn-secondary display-none" onclick="window.location.href = 'crawl'">Crawl</button>
        </c:if>
        <button name="dropdown-item" class="btn btn-secondary display-none" onclick="window.location.href = 'logout'">Logout</button>
    </div>
</div>