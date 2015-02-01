//Variable that describe ordering and pagination
var order = {
    orderBy:'NAME',
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

