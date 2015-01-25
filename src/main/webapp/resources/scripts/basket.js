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
    jQuery("#basket").empty();
    jQuery.ajax({
        url: "/users/basket",
        type: "GET",
        dataType: "json",
        statusCode: {
            200: function(data){
                jQuery("#fullPrice").text(data.price + '$');
                for(var i = 0; i < data.articles.length; i++){
                    getArticleElement(data.articles[i])
                }
            }
        }
    })
}

function getArticleElement(id){
    jQuery.ajax({
        url: "/articles/"+id,
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
                          '<button type="button" class="btn btn-danger btn-sm" onclick="deleteFromBasket(' + article.id + ')">' +
                              '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' +
                          '</button>' +
                          '<a href="#">' +
                              '<img class="media-object" src="/resources/img/' + article.photo + '" style="height: 128px;vertical-align: top">' +
                          '</a>' +
                      '</div>' +
                      '<div class="media-body">' +
                          '<h4 class="media-heading">' + article.name + '</h4>' +
                          '<p class="price price-text">' + article.price + '$</p>' +
                      '</div>' +
                  '</div>' +
                  '<hr/>';
    jQuery("#basket").append(jQuery.parseHTML(element));
}

function deleteFromBasket(id){
    jQuery.ajax({
        url: "/users/basket/"+id,
        type: "DELETE",
        async: false,
        dataType: "json"
    })
    updateBasket();
    initBasket();
}