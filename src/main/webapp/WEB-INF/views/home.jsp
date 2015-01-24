<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<script type="application/javascript" src="<c:url value="/resources"/>/scripts/articles.js" ></script>
<h1>Hello World!</h1>

<a href="#" class="btn btn-primary" role="button" onclick="getArticles()">get all</a>

<div class="row" id="articlesContainer">

</div>

