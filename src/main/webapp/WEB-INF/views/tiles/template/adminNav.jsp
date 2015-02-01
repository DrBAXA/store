<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
  <c:if test="${not empty disabled}">
  $(document).ready(function () {
    $('.${disabled}').addClass('active');
  });
  </c:if>
</script>
<ul class="nav nav-tabs">
  <li class="articles" role="presentation"><a href="#">Товари</a></li>
  <li class="categories" role="presentation"><a href="#">Категорії</a></li>
  <li class="users" role="presentation"><a href="#">Користувачі</a></li>
</ul>
