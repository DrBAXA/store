<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <script src="<c:url value="/resources"/>/jQuery/jquery.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources"/>/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/resources"/>/bootstrap/css/bootstrap-theme.min.css">
    <script src="<c:url value="/resources"/>/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<c:if test="${not empty error}">
    <div>ERROR!!!</div>
</c:if>
<div class="container">
    <form class="form-signin" role="form" action="/login" method="POST">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" class="form-control" name="name" placeholder="Login" required autofocus>
        <input type="password" class="form-control" name="password" placeholder="Password" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
    </form>
</div>
</body>
</html>