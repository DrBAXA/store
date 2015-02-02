<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="<c:url value="/resources"/>/bootstrap/css/bootstrap-slider.css">
<script type="application/javascript" src="<c:url value="/resources"/>/bootstrap/js/bootstrap-slider.js" ></script>
<script type="application/javascript" src="<c:url value="/resources"/>/scripts/order.js" ></script>
<script type="application/javascript" src="<c:url value="/resources"/>/scripts/filter.js" ></script>
<script type="application/javascript" src="<c:url value="/resources"/>/scripts/basket.js" ></script>
<script type="application/javascript" src="<c:url value="/resources"/>/scripts/adminArticles.js" ></script>
<div class="row">
    <div class="col-lg-3" style="margin-top: 15px">
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
    <div class="col-lg-9" style="margin-top: 15px">
        <div>
            <div class="dropdown order-element orderBy" style="display: inline">
                Сортувати по
                <a class="dropdown-toggle" id="orderBy" data-toggle="dropdown" aria-expanded="true">
                    назві(за зростанням)
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                    <li class="orderByElement" role="presentation" id="order_1"><a role="menuitem" href="#">назві(за
                        спаданням)</a></li>
                    <li class="orderByElement" role="presentation" id="order_2"><a role="menuitem" href="#">назві(за
                        зростанням)</a></li>
                    <li class="orderByElement" role="presentation" id="order_3"><a role="menuitem" href="#">ціні(за
                        спаданням)</a></li>
                    <li class="orderByElement" role="presentation" id="order_4"><a role="menuitem" href="#">ціні(за
                        зростанням)</a></li>
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
            <a href="/admin/articles/new" class="btn btn-sm btn-primary" style="float: right">
                <i class="glyphicon glyphicon-plus-sign"></i>Додати
            </a>
        </div>

        <table class="table table-hover" style="margin-top: 15px">
            <tbody id="articlesContainer">
            <tr style="max-height: 30px">
                <td>1</td>
                <td><img src="<c:url value="/resources"/>/img/product.png" height="40px"></td>
                <td>Назва</td>
                <td><button type="button" class="btn btn-danger btn-sm" onclick="deleteArticle(' + article.id + ')"/></td>
            </tr>
            </tbody>
        </table>
        <nav id="paginator" style="display: none">
            <ul class="pagination">
                <li id="previous" class="disabled" onclick="previousPage()"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                <li id="next"><a href="#" aria-label="Next" onclick="nexPage()"><span aria-hidden="true">&raquo;</span></a></li>
            </ul>
        </nav>
    </div>
</div>

<!-- modal for request on deleting -->
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
     aria-hidden="true" id="questionModal">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <strong>Ви справді хочете видалити цей товар</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Відмінити</button>
                <button type="button" id="deleteConfirm" class="btn btn-danger" onclick="deleteArticle()">Так</button>
            </div>
        </div>
    </div>
</div>
<!--Result of operation -->
<div class="alert-message" role="alert" id="message" style="display: none">
    <div type="button" class="close" onclick="hideAlert()">
        <span aria-hidden="true">&times;</span>
        <span class="sr-only">Close</span>
    </div>
    <label id="resultDefinition"></label>
</div>



