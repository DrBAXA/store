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
  <div class="navbar-header">
    <a class="navbar-brand" href="#">ATM locator</a>
  </div>
  <div>
    <ul class="navbar-nav">
      <li class="main">Home</li>
      <li class="basket">Basket</li>
      <li class="products">Products</li>
    </ul>
  </div>
</nav>
