<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script>
    <c:if test="${not empty active}">
    $(document).ready(function () {
        $('.${active}').addClass('active');
    });
    </c:if>
</script>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">ATM locator</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="products"><a class="navbar-link">Products</a></li>
                <li class="basket"><a class="navbar-link">Basket</a></li>
            </ul>
            <sec:authorize access="isAuthenticated()">
                <p class="navbar-text navbar-right">Signed in as <a href="#">${user}</a></p>
            </sec:authorize>
        </div>
    </div>
</nav>
