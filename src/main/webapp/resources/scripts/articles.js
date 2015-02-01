/*
Udate basket on load page
If user is logged will be loaded basket info from db(if user has any articles in basket)
If user isn't logged will be loaded basket info from session
*/
jQuery(document).ready(function(){
    initCategories();
    initPriceFilter();
    getArticles();
    updateBasket();
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
    initPriceFilter();
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
    var articleElement = '<div class="col-sm-6 col-md-4">' +
                              '<div class="thumbnail">' +
                                   '<img src="/resources/img/' + article.photo + '"/>' +
                                   '<div class="caption">' +
                                        '<h3>' + article.name + '</h3>' +
                                        '<div class="description">' + article.description + '</div>' +
                                        '<p><a href="#" onclick="articleDetails(' + article.id + ')">детальніше...</a></p>' +
                                        '<p><a href="#" onclick="addToBasket(' + article.id + ')" class="btn btn-lg btn-success" role="button">' +
                                             '<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>' +
                                             'Купити' +
                                        '</a><span class="price price-right">' + article.price + '$</span></p>' +
                                   '</div>' +
                              '</div>' +
                         '</div>';
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
