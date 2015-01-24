<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


<h1>Hello World!</h1>

<p>This is the homepage!</p>

<div class="row">
    <c:forEach var="i" begin="1" end="10">
    <div class="col-sm-6 col-md-3">
        <div class="thumbnail">
            <img src="<c:url value="/resources"/>/img/product.png">
            <div class="caption">
                <h3>Thumbnail label</h3>
                <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                <p>
                    <a href="#" class="btn btn-primary" role="button">
                        <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
                        Add to basket
                    </a>
                </p>
            </div>
        </div>
    </div>
    </c:forEach>
</div>

