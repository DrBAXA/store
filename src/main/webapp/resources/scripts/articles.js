jQuery(document).on("click", "li.orderByElement", function(event){
    jQuery("#orderBy").text(jQuery(event.currentTarget).text());
    switch (event.currentTarget.id){
        case "order_1":{
            order.orderby = "NAME";
            order.down = true;
        } break;
        case "order_2":{
            order.orderby = "NAME";
            order.down = false;
        } break;
        case "order_3":{
            order.orderby = "PRICE";
            order.down = true;
        } break;
        case "order_4":{
            order.orderby = "PRICE";
            order.down = false;
        }
    }
});

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
});

jQuery(document).on("click", "li.paginationElement", function(event){
    var pageNumber = jQuery(event.currentTarget).attr("pageNumber");
    changePage(pageNumber);
});

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

var order = {
    orderby:'NAME',
    down:false,
    count:10,
    first:0
};
var pagesCount;
var currentPage = 1;

function updateFilter(){
    currentPage = 1;
    order.first = 0;
    getArticles();
}

function getArticles() {
    jQuery.ajax({
        url: "/articles",
        type: "GET",
        data: order,
        dataType: "json",
        statusCode: {
            200: addArticles
        }
    })
}

function addArticles(data){
    jQuery("#articlesContainer").empty();
    for(var i = 0; i < data.pageData.length; i++){
        addArticle(data.pageData[i]);
    }

    pagesCount =Math.ceil(data.countAll/order.count);
    initPaginator(pagesCount);

}

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

function addArticle(article){
    var articleElement = '<div class="col-sm-6 col-md-3">' +
                              '<div class="thumbnail">' +
                                   '<img src="/resources/img/' + article.photo + '"/>' +
                                   '<div class="caption">' +
                                        '<h3>' + article.name + '</h3>' +
                                        '<p>' + article.description + '</p>' +
                                        '<p><a href="#" class="btn btn-primary" role="button">' +
                                             '<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>' +
                                             'Add to basket' +
                                        '</a><span class="price">' + article.price + '$</span></p>' +
                                        //'<h3 class="price">' + article.price + '$</h3>' +
                                   '</div>' +
                              '</div>' +
                         '</div>';
    jQuery("#articlesContainer").append(jQuery.parseHTML(articleElement));
}