function addToBasket(id){
    jQuery.ajax({
        url: "/users/basket/"+id,
        type: "PUT",
        statusCode: {
            200: updateBasket
        }
    })
}

function updateBasket(){
    jQuery.ajax({
        url: "/users/basket",
        type: "GET",
        dataType: "json",
        statusCode: {
            200: fillBasketData
        }
    })
}

function fillBasketData(data){
    jQuery("#basketCount").text(data.articles.length);
    jQuery("#basketPrice").text(data.price+'$');
}

function initBasket(){
    jQuery.ajax({
        url: "/users/basket",
        type: "GET",
        dataType: "json",
        statusCode: {
            200: function(data){
                for(var i = 0; i < data.articles.length; i++){
                    getArticleElement(data.articles[i])
                }
            }
        }
    })
}

function getArticleElement(id){
    jQuery.ajax({
        url: "/users/basket",
        type: "GET",
        dataType: "json",
        statusCode: {
            200: addArticleElement
        }
    })
}

function addArticleElement(article){
    var element = '<div class="media">' +
                      '<div class="media-left">' +
                          '<img class="media-object" src="/resources/img/' + article.photo + '">' +
                      '</div>' +
                      '<div class="media-body">' +
                          '<h4 class="media-heading">' + article.name + '</h4>' +
                          '<p class="price price-color">' + article.price + '$</p>' +
                      '</div>' +
                      '<div class="media-right">' +
                          '<button type="button" class="btn btn-danger btn-sm" onclick="deleteFromBasket(' + article.id + ')">' +
                              '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                          '</button>' +
                      '</div>' +
                  '</div>' +
                  '<hr/>';
    jQuery("#basket").append(jQuery.parse(element));
}