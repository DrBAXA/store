<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:url var="homeUrl" value="/"/>
<c:url var="resourcesUrl" value="/resources"/>
<!DOCTYPE html>
<html>
<head>
    <script src="${resourcesUrl}/jQuery/jquery.min.js"></script>
    <link rel="stylesheet" href="${resourcesUrl}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${resourcesUrl}/bootstrap/css/bootstrap-theme.min.css">
    <script src="${resourcesUrl}/bootstrap/js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
</head>
<body>
<div class="container">
    <h3 class="col-lg-offset-2 col-sm-offset-2">Реєстрація</h3>
    <form:form commandName="user" cssClass="form-horizontal" role="form" method="POST" action="/signup">
        <div class="row">
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">Логін</label>

                <div class="col-md-4">
                    <form:input cssClass="form-control" path="name" placeholder="Логін"
                           data-content="" data-placement="right"
                           data-toggle="popover" data-original-title=""/>
                    <form:errors path="name" cssClass="text-danger"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">Електронна пошта</label>

                <div class="col-md-4">
                    <form:input cssClass="form-control" path="email" placeholder="someuser@somehost.com"
                           data-content="" data-placement="right"
                           data-toggle="popover" data-original-title=""/>
                    <form:errors path="email" cssClass="text-danger"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">Номер телефону</label>

                <div class="col-md-4">
                    <form:input cssClass="form-control" path="phone" placeholder="+380XXXXXXXXX"
                                data-content="" data-placement="right"
                                data-toggle="popover" data-original-title=""/>
                    <form:errors path="phone" cssClass="text-danger"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">Пароль</label>

                <div class="col-md-4">
                    <form:input type="password" cssClass="form-control" path="password"
                           placeholder="Пароль"/>
                    <form:errors path="password" cssClass="text-danger"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group">
                <label for="confirmPassword" class="col-sm-2 control-label">Підтвердіть пароль</label>

                <div class="col-md-4">
                    <input type="password" class="form-control" id="confirmPassword"
                           placeholder="Ще раз пароль">
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-success">Зареєструватись</button>
                </div>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>