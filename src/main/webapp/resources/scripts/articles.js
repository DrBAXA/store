//Variable that describe ordering and pagination 
var order = {
    orderBy:'NAME',
    decrease:false,
    count:10,
    first:0
};

var filter = {
    priceMin : 0,
    priceMax:1000000,
    categories: []
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
            order.orderBy = "NAME";
            order.decrease = true;
        } break;
        case "order_2":{
            order.orderBy = "NAME";
            order.decrease = false;
        } break;
        case "order_3":{
            order.orderBy = "PRICE";
            order.decrease = true;
        } break;
        case "order_4":{
            order.orderBy = "PRICE";
            order.decrease = false;
        }
    }
    order.first = 0;
    currentPage = 1;
    updateFilter();
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
    updateFilter();
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
    initCategories();
    initPriceFilter();
    getArticles();
    updateBasket();
});

function initPriceFilter(){
    var postData = {
        priceMin : 0,
        priceMax:1000000,
        categories : JSON.stringify(filter.categories)
    };
    jQuery.ajax({
        url: getHomeUrl()+"articles/price_limit",
        type: "POST",
        data: JSON.stringify(filter),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        statusCode: {
            200: function(data){
                jQuery("#priceFilter").attr("data-slider-min", data.priceMin);
                jQuery("#priceFilter").attr("data-slider-max", data.priceMax);
                jQuery("#priceFilter").attr("data-slider-value", "[" + data.priceMin + "," + data.priceMax + "]");
                jQuery("#priceFilter").slider({tooltip: 'always'});

            }
        }
    })
}

function initTree(){
    jQuery('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
    jQuery('.tree  ul li.parent_li span i.glyphicon').on('click', function (e) {
        var children = jQuery(this).parents().eq(1).find(' > ul > li');
        if (children.is(":visible")) {
            children.hide('fast');
            jQuery(this).addClass('glyphicon-plus-sign').removeClass('glyphicon-minus-sign');
        } else {
            children.show('fast');
            jQuery(this).addClass('glyphicon-minus-sign').removeClass('glyphicon-plus-sign');
        }
        e.stopPropagation();
    });
}

//.tree ul li.parent_li span i.glyphicon.glyphicon-minus-sign
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

function initCategories(){
    var container = jQuery("#categories");
    jQuery.ajax({
        url: getHomeUrl()+"articles/categories",
        type: "GET",
        async:false,
        dataType: "json",
        statusCode: {
           200: function(data){
               for(var i = 0; i < data.length; i++){
                   addCategory(container, data[i])
               }
               initTree();
           }
        }
    });

    jQuery(document).on("click", ".category-checker", function(event){
        var id = jQuery(event.currentTarget).attr("id")*1;

        if(jQuery(event.currentTarget).prop('checked')){
            filter.categories.push(id);
            jQuery(event.currentTarget).parents().eq(2).find("input.category-checker").prop("checked", true);
        }else{
            uncheckParent(event.currentTarget);
            var index = filter.categories.indexOf(id);
            if (index > -1) {
                filter.categories.splice(index, 1);
            }
        }
    })
}

function uncheckParent(element){
    var levels = jQuery(element).parents("li.parent_li");
    for(var i = 0; i < levels.length; i++){
        var parent_checker = jQuery(levels[i]).find("span  span.tree-item  input.category-checker")[0];
        jQuery(parent_checker).prop("checked", false);
    }
    jQuery(element).parents().eq(2).find("input.category-checker").prop("checked", false);
}

function addCategory(container, category){
    filter.categories.push(category.id);
    var treeElementHeaderHtml = '<span>' +
                                    '<i class="glyphicon glyphicon-minus-sign"></i>' +
                                    '<span class="tree-item">' +
                                        '<input type="checkbox" class="category-checker" checked="true" id="' + category.id + '">' +
                                        category.name +
                                    '</span>' +
                                '</span>';
    var treeElementHeader = jQuery.parseHTML(treeElementHeaderHtml);

    var childCategoriesContainer ;
    if(category.categories.length != 0){
        var childCategoriesContainer = jQuery.parseHTML("<ul></ul>");
        for(var i = 0; i < category.categories.length; i++){
            addCategory(childCategoriesContainer, category.categories[i])
        }
    }
    var treeElement = jQuery.parseHTML("<li></li>");
    jQuery(treeElement).append(treeElementHeader);
    jQuery(treeElement).append(childCategoriesContainer);
    jQuery(container).append(treeElement);
}
