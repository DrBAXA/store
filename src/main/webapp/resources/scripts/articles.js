//Variable that describe ordering and pagination 
var order = {
    orderby:'NAME',
    decrease:false,
    count:10,
    first:0
};
//Count pages in pagination
var pagesCount;
//Current loaded page(default 1)
var currentPage = 1;

/*
Set orderBy property
*/
jQuery(document).on("click", "li.orderByElement", function(event){
    jQuery("#orderBy").text(jQuery(event.currentTarget).text());
    switch (event.currentTarget.id){
        case "order_1":{
            order.orderby = "NAME";
            order.decrease = true;
        } break;
        case "order_2":{
            order.orderby = "NAME";
            order.decrease = false;
        } break;
        case "order_3":{
            order.orderby = "PRICE";
            order.decrease = true;
        } break;
        case "order_4":{
            order.orderby = "PRICE";
            order.decrease = false;
        }
    }
    order.first = 0;
    currentPage = 1;
    getArticles();
});

/*
Set articles count per page
*/
jQuery(document).on("click", "li.countElement", function(event){
    jQuery("#countOnPage").text(jQuery(event.currentTarget).text());
    switch (event.currentTarget.id){
        case "count_10":{
            order.count = 10;
        } break;
        case "count_20":{
            order.count = 20;
        } break;
        case "count_50":{
            order.count = 50;
        }
    }
    order.first = 0;
    currentPage = 1;
    getArticles();
});

/*
Click on paginator(each page)
*/
jQuery(document).on("click", "li.paginationElement", function(event){
    var pageNumber = jQuery(event.currentTarget).attr("pageNumber");
    changePage(pageNumber);
});
/*
Udate basket on load page
If user is logged will be loaded basket info from db(if user has any articles in basket)
If user isn't logged will be loaded basket info from session
*/
jQuery(document).ready(function(){
    getArticles();
    updateBasket();
});

/*
Set paginator on new page(by click on page number, previous or next page)
Load data for new page
*/
function changePage(pageNumber){
    jQuery(".paginationElement").removeClass("active");
    jQuery("#page_"+pageNumber).addClass("active");

    currentPage = pageNumber;

    if(jQuery("#page_"+pageNumber).attr("id") === "page_1"){
        jQuery("#previous").addClass("disabled")
    }else{
        jQuery("#previous").removeClass("disabled")
    }

    if(jQuery("#page_"+pageNumber).attr("id") === ("page_" + pagesCount)){
        jQuery("#next").addClass("disabled")
    }else{
        jQuery("#next").removeClass("disabled")
    }

    order.first = (currentPage-1)*order.count;
    getArticles();
}

function nexPage(){
    changePage(++currentPage);
}

function previousPage(){
    changePage(--currentPage);
}

/*
updating filter, loading data for first page
*/
function updateFilter(){
    currentPage = 1;
    order.first = 0;
    getArticles();
}

/*
Send request for articles(by current state of order and paginator) 
*/
function getArticles() {
    jQuery.ajax({
        url: getHomeUrl()+"articles",
        type: "GET",
        data: order,
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
Initialize paginator by data received from server, and curent page
If pages count less then 2 paginator will be hidden
*/
function initPaginator(pagesCount){
    jQuery("li").remove(".paginationElement");

    if(pagesCount <= 1){
        jQuery("#paginator").hide(false);
    }else{
        for(var i = 1; i<=pagesCount; i++){
            var pageNumber = pagesCount-i+1;
            var nextElement = '<li class="paginationElement" id="page_' + pageNumber + '" pageNumber="' + pageNumber + '"><a href="#">' + pageNumber + '<span class="sr-only"></span></a></li>';
            jQuery(jQuery.parseHTML(nextElement)).insertAfter("#previous")
        }
        jQuery("#page_"+currentPage).addClass("active");
        jQuery("#paginator").show(true);
    }
}

/*
Add article to page
*/
function addArticle(article){
    var articleElement = '<div class="col-sm-6 col-md-3">' +
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

