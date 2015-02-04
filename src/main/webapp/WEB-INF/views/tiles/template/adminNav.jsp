<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
  <c:if test="${not empty disabled}">
  $(document).ready(function () {
    $('.${disabled}').addClass('active');
  });
  </c:if>
</script>
<ul class="nav nav-tabs">
  <li class="articles" role="presentation"><a href="<c:url value="/"/>admin/articles">Товари</a></li>
  <li class="orders" role="presentation"><a href="<c:url value="/"/>admin/orders">Замовлення</a></li>
</ul>
