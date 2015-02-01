<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script type="application/javascript" src="<c:url value="/resources"/>/scripts/basket.js" ></script>
<script type="application/javascript" src="<c:url value="/resources"/>/scripts/adminArticle.js" ></script>
<form:form modelAttribute="article" cssClass="form-horizontal" method="PUT" role="form" enctype="multipart/form-data" onsubmit="return changeUrl()" action="articles/">
  <div class="row" style="margin-top: 15px">
    <div class="col-md-4 avablock">
      <img src="<c:url value="/resources"/>/img/${article.photo}" class="img-thumbnail"
      alt="photo"
      id="photo">

      <div class="controls clearfix">
                        <span class="btn btn-default btn-file" id="avatar" data-toggle="popover"
                              data-placement="bottom">
                            <i class="glyphicon glyphicon-camera"></i> <span>Змінити фото</span>
                            <input type="file" name="image" id="image"/>
                        </span>
      </div>
    </div>
    <div class="col-md-8">
      <form:hidden path="id"/>
      <div class="form-group">
        <label for="name" class="col-md-3 control-label">Назва</label>

        <div class="col-md-9">
          <form:input path="name"  cssClass="form-control"/>
        </div>
      </div>
      <div class="form-group">
        <label for="description" class="col-md-3 control-label">Опис(додавати теги форматування)</label>

        <div class="col-md-9">
          <form:textarea path="description" rows="20" cssClass="form-control"/>
        </div>
      </div>
      <div class="form-group">
        <label for="price" class="col-md-3 control-label">Ціна</label>

        <div class="col-md-9">
          <form:input path="price" cssClass="form-control"/>
        </div>
      </div>
      <div class="form-group">
        <label for="price" class="col-md-3 control-label">Категорія</label>

        <div class="col-md-9">
          <form:select path="category">
            <form:options items="${categories}" />
          </form:select>
        </div>
      </div>
      <div class="form-group">
        <div class="col-md-3"></div>
        <div class="col-md-9">
          <button type="submit" class="btn btn-success" id="save"
                  style="width: 200px;"><i class="glyphicon glyphicon-floppy-disk"></i>&nbsp;Зберегти</button>
        </div>
      </div>
    </div>
  </div>
</form:form>
<!--Result of operation -->
<div class="alert-message" role="alert" id="message" style="display: none">
  <div type="button" class="close" onclick="hideAlert()">
    <span aria-hidden="true">&times;</span>
    <span class="sr-only">Close</span>
  </div>
  <label id="resultDefinition"></label>
</div>