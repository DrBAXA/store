<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="application/javascript" src="<c:url value="/resources"/>/scripts/basket.js" ></script>
<script type="application/javascript" src="<c:url value="/resources"/>/scripts/article.js" ></script>
<div class="media">
  <div class="col-lg-12">
    <h2 id="name"></h2>
    <hr/>
  </div>
  <div >
    <img src="" id="photo" class="img-thumbnail col-lg-6 col-sm-12 article-img">
    <div class="info">
      <div id="price" class="price"></div>
      <a href="#" id="buy" onclick="" class="btn btn-success btn-lg price-right" role="button">
        <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>Купити
      </a>
    </div>
    <br/>
    <div id="description" class="description-full">
    </div>
  </div>
</div>


