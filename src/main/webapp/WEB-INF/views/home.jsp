<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="<c:url value="/resources"/>/bootstrap/css/bootstrap-slider.css">
<script type="application/javascript" src="<c:url value="/resources"/>/bootstrap/js/bootstrap-slider.js" ></script>
<script type="application/javascript" src="<c:url value="/resources"/>/scripts/basket.js" ></script>
<script type="application/javascript" src="<c:url value="/resources"/>/scripts/articles.js" ></script>
<div class="row">
    <div class="col-lg-3">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Фільтр</h3>
            </div>
            <div class="panel-body">
                <div class="price-filter">
                    <h4>Ціна</h4>
                    <input id="priceFilter" type="text" class="span2" value="" data-slider-min="" data-slider-max="" data-slider-step="5" data-slider-value=""/>
                </div>
                <div class="tree">
                    <h4>Категорії</h4>
                    <ul id="categories">

                    </ul>
                </div>
                <button class="btn btn-primary" onclick="updateFilter()" style="float: right">Оновити фільтр</button>
            </div>
        </div>
    </div>
    <div class="col-lg-9">
        <div class="dropdown order-element orderBy" style="display: inline">
            Сортувати по
            <a class="dropdown-toggle" id="orderBy" data-toggle="dropdown" aria-expanded="true">
                назві(за зростанням)
                <span class="caret"></span>
            </a>
            <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                <li class="orderByElement" role="presentation" id="order_1"><a role="menuitem" href="#">назві(за спаданням)</a></li>
                <li class="orderByElement" role="presentation" id="order_2"><a role="menuitem" href="#">назві(за зростанням)</a></li>
                <li class="orderByElement" role="presentation" id="order_3"><a role="menuitem" href="#">ціні(за спаданням)</a></li>
                <li class="orderByElement" role="presentation" id="order_4"><a role="menuitem" href="#">ціні(за зростанням)</a></li>
            </ul>
        </div>
        <div class="dropdown order-element orderBy" style="display: inline">
            відобразити на одній сторінці
            <a class="dropdown-toggle" id="countOnPage" data-toggle="dropdown" aria-expanded="true">
                10
                <span class="caret"></span>
            </a>
            <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                <li class="countElement" role="presentation" id="count_10"><a role="menuitem" href="#">10</a></li>
                <li class="countElement" role="presentation" id="count_20"><a role="menuitem" href="#">20</a></li>
                <li class="countElement" role="presentation" id="count_50"><a role="menuitem" href="#">50</a></li>
            </ul>
        </div>

        <div class="row" id="articlesContainer">

        </div>
        <nav id="paginator" style="display: none">
            <ul class="pagination">
                <li id="previous" class="disabled" onclick="previousPage()"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                <li id="next"><a href="#" aria-label="Next" onclick="nexPage()"><span aria-hidden="true">&raquo;</span></a></li>
            </ul>
        </nav>
    </div>
</div>
