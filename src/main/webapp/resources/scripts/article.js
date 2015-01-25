jQuery(document).ready(function () {
    var articleId = jQuery.cookie("articleId");
    updateBasket();
    jQuery.ajax({
        url: "/articles/"+articleId,
        type: "GET",
        dataType: "json",
        statusCode: {
            200: fillData
        }
    })
});

function fillData(article){
    jQuery("#name").text(article.name);
    jQuery("#photo").attr("src", "/resources/img/"+article.photo);
    jQuery("#price").text(article.price + "$");
    jQuery("#buy").attr("onclick", 'addToBasket(' + article.id + ')')
    jQuery("#description").text(article.description);
}