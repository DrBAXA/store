<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="application/javascript" src="<c:url value="/resources"/>/scripts/basket.js" ></script>
<script type="application/javascript">
  jQuery(document).ready(function(){
    updateBasket();
    initBasket();
  });
</script>
<div id="basket">

</div>
<div id="control" class="col-lg-2 col-lg-offset-8 doBuy" style="position: relative; top: -15px">
  <div style="text-align: center"><strong>Разом: </strong><i id="fullPrice" class="price-text"></i></div>
  <button class="btn btn-success col-lg-11" style="margin-bottom: 10px; width: 100%">Замовити</button>
</div>
