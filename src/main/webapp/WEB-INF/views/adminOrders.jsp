<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form class="form-inline" style="margin-top: 15px" action="<c:url value="/"/>admin/orders" method="get">
  <input type="hidden" id="page" name="page" value="${currentPage}">
  <div class="form-group">
    <label for="sort">Сортувати по</label>
    <select id="sort" name="sort" class="form-control">
      <option value="date" <c:if test="${sort == 'date'}">selected="selected"</c:if>>даті</option>
      <option value="price" <c:if test="${sort == 'price'}">selected="selected"</c:if>>вартості</option>
      <option value="state" <c:if test="${sort == 'state'}">selected="selected"</c:if>>статусу</option>
    </select>
  </div>
  <div class="form-group">
    <label for="size">на сторінці </label>
    <select id="size" name="size" class="form-control">
      <option value="10" <c:if test="${pageSize == 10}">selected="selected"</c:if>>10</option>
      <option value="20" <c:if test="${pageSize == 20}">selected="selected"</c:if> >20</option>
      <option value="50" <c:if test="${pageSize == 50}">selected="selected"</c:if>>50</option>
    </select>
  </div>
  <button type="submit" class="btn btn-default">Оновити</button>
</form>
<br/>
<div>
  <label class="text-primary">Активні</label>
  <label class="text-success">Виконані</label>
  <label class="text-danger">Відмінені</label>
</div>
<table class="table table-hover">
  <tr>
    <th>#</th>
    <th>Дата</th>
    <th>№ тел. замовника</th>
    <th>E-mail</th>
    <th>Загальна вартість</th>
  </tr>
  <c:forEach var="order" items="${orderList.content}" >
    <c:choose>
      <c:when test="${order.state == 'DONE'}">
        <c:set var="color" value="success"/>
      </c:when>
      <c:when test="${order.state == 'READY'}">
        <c:set var="color" value="info"/>
      </c:when>
      <c:when test="${order.state == 'CANCELED'}">
        <c:set var="color" value="danger"/>
      </c:when>
    </c:choose>
    <tr class="${color}" onclick="<c:url value="/"/>admin/orders/${order.id}">
      <td>${order.id}</td>
      <td>${order.date}</td>
      <td>${order.userPhone}</td>
      <td>${order.userEmail}</td>
      <td>${order.price} грн.</td>
    </tr>
  </c:forEach>
</table>
<nav>
  <ul class="pagination">
    <c:forEach var="i" begin="1" end="${totalPages}">
      <li <c:if test="${i == currentPage+1}">class="active"</c:if>><a href="#" onclick="pageClick(${i-1})">${i}<span class="sr-only"></span></a></li>
    </c:forEach>
  </ul>
  <script>
    function pageClick(pageNumber){
      jQuery("#page").val(pageNumber);
      jQuery(".form-inline").submit();
    }
  </script>
</nav>
