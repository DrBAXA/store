
var filter = {
    priceMin : 0,
    priceMax:100000000,
    categories: []
};

function initPriceFilter(){
    jQuery.ajax({
        url: getHomeUrl()+"articles/price_limit",
        type: "POST",
        data: JSON.stringify(filter),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        statusCode: {
            200: function(data){
                var minElementValue = jQuery("#priceFilter").attr("value").split(",")[0]
                var minValue = (minElementValue  == "") || (minElementValue  == undefined) ? data.priceMin : minElementValue*1;
                var maxElementValue = jQuery("#priceFilter").attr("value").split(",")[1]
                var maxValue = (maxElementValue  == "") || (maxElementValue  == undefined) ? data.priceMax : maxElementValue*1;
                jQuery("#priceFilter").slider("destroy");
                jQuery("#priceFilter").slider({
                    tooltip: 'always',
                    value: [ minValue , maxValue],
                    min: data.priceMin,
                    max: data.priceMax
                });

            }
        }
    })
}

function initCategoryTree(){
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
                initCategoryTree();
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
        '<span title="' + category.description + '">' + category.name + '</span>' +
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