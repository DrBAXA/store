/*
 Update basket on load page
 If user is logged will be loaded basket info from db(if user has any articles in basket)
 If user isn't logged will be loaded basket info from session
 */
jQuery(document).ready(function(){
    initCategories();
    initPriceFilter();
    getArticles();
    updateBasket();
    jQuery(document).on("click", "td button.btn-primary", function(event){
        var id = jQuery(event.currentTarget).parents().eq(1).prop("id");
        var url =location.protocol + '//' + location.host + getHomeUrl() + "admin/articles/" + id;
        window.location.href = url;
        event.preventDefault();
    })
});

/*
 updating filter, loading data for first page
 */
function updateFilter(){
    filter.priceMax = jQuery("#priceFilter").attr("value").split(",")[1];
    filter.priceMin = jQuery("#priceFilter").attr("value").split(",")[0];
    filter.categories = [];
    var categoryCheckers = jQuery(document).find("input.category-checker");
    for(var i = 0; i < categoryCheckers.length; i++){
        if(jQuery(categoryCheckers[i]).prop('checked')){
            filter.categories.push(jQuery(categoryCheckers[i]).prop("id"))
        }
    }
    currentPage = 1;
    order.first = 0;
    getArticles();
}

/*
 Send request for articles(by current state of order and paginator)
 */
function getArticles() {

    var postData = {
        order: order,
        filter: filter
    };
    jQuery.ajax({
        url: getHomeUrl()+"articles",
        type: "POST",
        data: JSON.stringify(postData),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        statusCode: {
            200: addArticles
        }
    })
}

/*
 Clear articles from page,
 add new articles
 update paginator
 */
function addArticles(data){
    jQuery("#articlesContainer").empty();
    for(var i = 0; i < data.pageData.length; i++){
        addArticle(data.pageData[i]);
    }

    pagesCount =Math.ceil(data.countAll/order.count);
    initPaginator(pagesCount);

}
/*
 Add article to page
 */
function addArticle(article){
    var articleElement ='<tr class="article-row" id="' + article.id + '">' +
                            '<td>' + article.id + '</td>'+
                            '<td><img src="' + getHomeUrl() + 'resources/img/' + article.photo + '" height="40px"></td>'+
                            '<td>' + article.name + '</td>'+
                            '<td>' + article.price + '</td>'+
                            '<td>' +
                                '<button type="button" class="btn btn-danger btn-sm" onclick="deleteArticleModal(' + article.id + ')">' +
                                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                                '</button>' +
                            '</td>'+
                            '<td>' +
                                '<button type="button" class="btn btn-primary btn-sm">' +
                                    '<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>' +
                                '</button>' +
                            '</td>'+
                        '</tr>';
    jQuery("#articlesContainer").append(jQuery.parseHTML(articleElement));
}

/*
 On click "datais..." id of article that was clicked writes to cookies
 and redirect to article details page
 */
function articleDetails(id){
    jQuery.cookie("articleId", id);
    window.location.assign("/articles/details");
}

function deleteArticleModal(id){
    jQuery("#deleteConfirm").attr("onclick", "deleteArticle(" + id + ")");
    $("#questionModal").modal("show");
}

function deleteArticle(id){
    jQuery.ajax({
        url: getHomeUrl()+"articles/" + id,
        type: "DELETE",
        dataType: "json",
        statusCode: {
            200: function(){
                $("#questionModal").modal("hide");
                showAlert("alert alert-success alert-message", "Товар було видалено");
                getArticles();
            },
            406: function(){
                $("#questionModal").modal("hide");
                showAlert("alert alert-вфтпук alert-message", "Не можливо видалити товар." +
                                                              " Він знаходиться в замовленні користувача. " +
                                                              "Закрийте спочатку всі замовлення в яких міститься даний товар");
                getArticles();
            }
        }
    })
}

//show alert about result of operation
function showAlert(className, html) {
    $("#message").removeClass();
    $("#message").addClass(className);
    $("#resultDefinition").empty();
    $("#resultDefinition").append(html);
    $("#message").slideDown(500);
    setTimeout(hideAlert, 8000)
}
//hide alert about result of operation
function hideAlert(){

    $("#message").slideUp(500);
}

/*
 Send request for Send request for basket data from current user
 Fill basket page
 */
function initBasket(){
    jQuery("#basket").empty();
    jQuery.ajax({
        url: getHomeUrl() +  "users/basket_info",
        type: "GET",
        dataType: "json",
        statusCode: {
            200: function(data){
                jQuery("#fullPrice").text(data.price + '$');
                for(var key in data.articles){
                    if(data.articles.hasOwnProperty(key)){
                        getArticleElement(key, data.articles[key])
                    }
                }
            }
        }
    })
}

