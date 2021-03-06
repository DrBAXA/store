<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="application/javascript" src="<c:url value="/resources"/>/scripts/basket.js"></script>
<script type="application/javascript">
    jQuery(document).ready(function () {
        updateBasket();
        initBasket();
    });
</script>
<div id="basket">

</div>
<div id="control" class="col-lg-2 col-lg-offset-8 doBuy" style="position: relative; top: -15px">
    <div style="text-align: center"><strong>Разом: </strong><i id="fullPrice" class="price-text"></i></div>
    <button class="btn btn-success col-lg-11" onclick="buyWindow()"
            style="margin-bottom: 10px; width: 100%">Замовити
    </button>
</div>

<div class="modal fade" id="buyModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Замовлення</h4>
            </div>
            <div class="modal-body">
                <table id="bill" rules="groups">
                    <thead>
                    <tr>
                        <td><strong>№</strong></td>
                        <td><strong>Назва</strong></td>
                        <td><strong>Кількість</strong></td>
                        <td><strong>Ціна</strong></td>
                    </tr>
                    </thead>
                    <tbody id="bill-positions">

                    </tbody>
                    <tfoot>
                    <tr>
                        <td></td>
                        <td></td>
                        <td><strong>Разом:</strong></td>
                        <td><strong id="fullBill"></strong></td>
                    </tr>
                    </tfoot>
                </table>
                <br/>
                <form class="form-horizontal">
                    <div class="col-lg-8 col-lg-offset-4" id="emailError" style="display: none">
                        <span class="text-danger">Невірний емейл!</span>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-4 control-label">Електронна пошта</label>
                        <div class="col-sm-8">
                            <input type="email" class="form-control" name="email" id="email" placeholder="user@somehost.com" onblur="checkValidData()">
                        </div>
                    </div>
                    <div class="col-lg-8 col-lg-offset-4" id="phoneError" style="display: none">
                        <span class="text-danger">Невірний № телефону!</span>
                    </div>
                    <div class="form-group">
                        <label for="phone" class="col-sm-4 control-label">№ телефону</label>
                        <div class="col-sm-8">
                            <input type="tel" class="form-control" name="phone" id="phone" placeholder="+380XXXXXXXXX"  onblur="checkValidData()">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Відмінити</button>
                        <button type="button" class="btn btn-success" onclick="doBuy()" id="buyButton">Оформити замовлення</button>
                    </div>
                </form>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<div class="modal fade bs-example-modal-sm" id="succes-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Замовлення</h4>
            </div>
            <div class="modal-body">
                <p>Замовлення прийнято. Найближчим часом з Вами зв'яжеться менеджер для уточнення деталей.</p>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
