<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="application/javascript" src="<c:url value="/resources"/>/scripts/article.js"></script>
<script type="application/javascript" src="<c:url value="/resources"/>/scripts/basket.js"></script>
<div class="media">
  <div class="col-lg-12">
    <h2>${article.name}</h2>
    <hr/>
  </div>
  <div >
    <img src="<c:url value="/resources"/>/img/${article.photo}" class="img-thumbnail col-lg-6 col-sm-12 article-img">
    <div class="information">
      <div class="price">${article.price} грн.</div>
      <a href="#" class="btn btn-success" style="float: right" role="button" onclick="addToBasket(${article.id})">
        <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>Купити
      </a>
    </div>
    <br/>
    <div class="description-full">
      ${article.description}
    </div>
  </div>
</div>


