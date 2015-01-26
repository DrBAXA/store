<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script>
    <c:if test="${not empty active}">
    $(document).ready(function () {
        $('.${active}').addClass('active');
    });
    </c:if>

	function getHomeUrl(){
		return '<c:url value="/"/>';
	}
</script>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Sombra store</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="basket">
                    <a class="navbar-link" href="/basket">
                        <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
                        Корзина
                        <strong id="basketCount"></strong>
                        (<i id="basketPrice" class="price-text"></i>)
                    </a>
                </li>
            </ul>
            <sec:authorize access="isAuthenticated()">
                <p class="navbar-text navbar-right">
                    Ви увійшли як <a href="#">${user}</a>(<a href="/logout">вийти</a>)
                </p>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <p class="navbar-text navbar-right"><a href="/login">увійти</a></p>
            </sec:authorize>
        </div>
    </div>
</nav>
